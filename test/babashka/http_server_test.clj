(ns babashka.http-server-test
  (:require [babashka.fs :as fs]
            [babashka.http-client :as http]
            [babashka.http-server :as server]
            [clojure.test :refer [deftest is testing use-fixtures]])
  (:import [java.util Arrays]))

(def port 8999)
(def base (str "http://localhost:" port))

;; 1000 bytes with a distinct value at every position (mod 256), so any
;; off-by-one or mis-seek shows up as a content mismatch, not just a length.
(def data (byte-array (map #(mod % 256) (range 1000))))

(defonce server-fixture
  (delay
    (let [dir (fs/create-temp-dir)]
      (fs/write-bytes (fs/file (fs/file dir) "f.bin") data)
      (server/serve {:dir (str dir) :port port})
      (Thread/sleep 100))))

(use-fixtures :once (fn [f] @server-fixture (f)))

(defn- get-range [range-header]
  (-> (http/get (str base "/f.bin")
                {:headers {"Range" range-header}
                 :as :bytes
                 :throw false})
      (select-keys [:status :body :headers])))

(defn- expected-bytes ^bytes [start end-exclusive]
  (Arrays/copyOfRange ^bytes data (int start) (int end-exclusive)))

(defn- byte-diff
  "nil when the two byte arrays are identical; otherwise a compact map
  locating the first difference. Keeps assertion failures readable instead
  of dumping thousand-element sequences."
  [^bytes expected ^bytes actual]
  (let [le (alength expected)
        la (alength actual)
        n  (min le la)
        i  (loop [i 0]
             (cond
               (= i n) (when (not= le la) i)
               (not= (aget expected i) (aget actual i)) i
               :else (recur (inc i))))]
    (when i
      (cond-> {:expected-len le :actual-len la :first-diff-at i}
        (< i le) (assoc :expected-byte (aget expected i))
        (< i la) (assoc :actual-byte (aget actual i))))))

(deftest bounded-range-test
  (testing "bytes=0-99 is inclusive on both ends: exactly 100 bytes (RFC 9110 §14.1.2)"
    (let [{:keys [status body headers]} (get-range "bytes=0-99")]
      (is (= 206 status))
      (is (= 100 (alength ^bytes body)))
      (is (nil? (byte-diff (expected-bytes 0 100) body)))
      (is (= "bytes 0-99/1000" (get headers "content-range"))
          "Content-Range last-pos is inclusive as well"))))

(deftest mid-file-range-test
  (testing "a range not starting at 0 seeks correctly"
    (let [{:keys [status body headers]} (get-range "bytes=500-599")]
      (is (= 206 status))
      (is (nil? (byte-diff (expected-bytes 500 600) body)))
      (is (= "bytes 500-599/1000" (get headers "content-range"))))))

(deftest suffix-range-test
  (testing "bytes=-100 means the final 100 bytes"
    (let [{:keys [status body headers]} (get-range "bytes=-100")]
      (is (= 206 status))
      (is (nil? (byte-diff (expected-bytes 900 1000) body)))
      (is (= "bytes 900-999/1000" (get headers "content-range"))))))

(deftest open-ended-range-test
  (testing "bytes=950- runs to the end of the file"
    (let [{:keys [status body headers]} (get-range "bytes=950-")]
      (is (= 206 status))
      (is (nil? (byte-diff (expected-bytes 950 1000) body)))
      (is (= "bytes 950-999/1000" (get headers "content-range"))))))

(deftest overlong-range-test
  (testing "a last-pos beyond EOF is clamped to the file size"
    (let [{:keys [status body headers]} (get-range "bytes=0-5000")]
      (is (= 206 status))
      (is (nil? (byte-diff (expected-bytes 0 1000) body)))
      (is (= "bytes 0-999/1000" (get headers "content-range"))))))
