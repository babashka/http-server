# Table of contents
-  [`babashka.http-server`](#babashkahttp-server) 
    -  [`default-mime-types`](#default-mime-types) - A map of file extensions to mime-types.
    -  [`exec`](#exec) - Exec function, intended for command line usage
    -  [`serve`](#serve) - Serves static assets using web server.
# babashka.http-server 





## `default-mime-types`

A map of file extensions to mime-types.
<br><sub>[source](https://github.com/babashka/http-server/blob/main/src/babashka/http_server.clj#L23-L119)</sub>
## `exec`
``` clojure

(exec opts)
```


Exec function, intended for command line usage. Same API as `serve` but
  blocks until process receives SIGINT.
<br><sub>[source](https://github.com/babashka/http-server/blob/main/src/babashka/http_server.clj#L260-L268)</sub>
## `serve`
``` clojure

(serve {:keys [port], :or {port 8090}, :as opts})
```


Serves static assets using web server.
Options:
  * `:dir` - directory from which to serve assets
  * `:port` - port
  * `:headers` - map of headers {key value}
  * `:not-found` - response function of request map when file isn't found
<br><sub>[source](https://github.com/babashka/http-server/blob/main/src/babashka/http_server.clj#L240-L256)</sub>
