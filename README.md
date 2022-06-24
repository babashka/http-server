# http-server

[![Clojars Project](https://img.shields.io/clojars/v/org.babashka/http-server.svg)](https://clojars.org/org.babashka/http-server)

Serve static assets.

Works in [clojure JVM](#clojure) and [babashka](#babashka).

## [API](API.md)

## Clojure

To your `deps.edn` add an alias:

``` clojure
:serve {:deps {org.babashka/http-server {:mvn/version "0.1.3"}}
        :main-opts ["-m" "babashka.http-server"]
        :exec-fn babashka.http-server/exec}
```

Then run from the command line:

``` clojure
clj -M:serve :port 1339 :dir "."
```

or:

``` clojure
clj -X:serve :port 1339 :dir '"."'
```

Or install as a tool:

``` clojure
$ clj -Ttools install io.github.babashka/http-server '{:git/tag "v0.1.3"}' :as serve
$ clj -Tserve exec
```

## Babashka

In a script, e.g. `/usr/local/bin/http-server`:

``` clojure
#!/usr/bin/env bb

(require '[babashka.deps :as deps])
(deps/add-deps
 '{:deps {org.babashka/http-server {:mvn/version "0.1.3"}}})

(require '[babashka.http-server :as http-server])

(apply http-server/-main *command-line-args*)
```

Then invoke using:

``` clojure
$ http-server --port 8888 --dir resources/public
```

In `bb.edn` [tasks](https://book.babashka.org/#tasks):

``` clojure
{:deps {org.babashka/http-server {:mvn/version "0.1.3"}
        org.babashka/cli {:mvn/version "0.2.23"}}
 :tasks
 {:requires ([babashka.cli :as cli])
  :init (def cli-opts (cli/parse-opts *command-line-args* {:coerce {:port :int}}))

  serve {:doc "Serve static assets"
         :requires ([babashka.http-server :as server])
         :task (server/exec (merge {:port 1337
                                    :dir "."}
                                   cli-opts))}

  prn {:task (clojure "-X clojure.core/prn" cli-opts)}

  -dev {:depends [serve prn]}

  dev {:task (run '-dev {:parallel true})}}}
```

``` clojure
$ bb dev --port 1338
Serving assets at http://localhost:1338
{:port 1338}
```

## Credits

Thanks to [Jakub Holy](https://github.com/holyjak) who made this
[gist](https://gist.github.com/holyjak/36c6284c047ffb7573e8a34399de27d8) which
was in turn based on
[this](https://github.com/babashka/babashka/blob/master/examples/image-viewer.clj)
babashka example. I used several variations of this in my own projects, which
eventually became this repo.

## License

Copyright © 2022 Michiel Borkent

Distributed under the MIT License. See LICENSE.
