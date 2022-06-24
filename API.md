# Table of contents
-  [`babashka.http-server`](#babashkahttp-server) 
    -  [`cli-opts`](#cli-opts)
    -  [`default-mime-types`](#default-mime-types) - A map of file extensions to mime-types.
    -  [`exec`](#exec) - Exec function, intended for command line usage
    -  [`serve`](#serve) - Serves static assets using web server.
# babashka.http-server 





## `cli-opts`
<sub>[source](https://github.com/babashka/http-server/blob/main/src/babashka/http_server.clj#L188-L188)</sub>
## `default-mime-types`

A map of file extensions to mime-types.
<br><sub>[source](https://github.com/babashka/http-server/blob/main/src/babashka/http_server.clj#L21-L114)</sub>
## `exec`
``` clojure

(exec opts)
```


Exec function, intended for command line usage. Same API as `serve` but
  blocks until process receives SIGINT.
<br><sub>[source](https://github.com/babashka/http-server/blob/main/src/babashka/http_server.clj#L190-L198)</sub>
## `serve`
``` clojure

(serve {:keys [port], :or {port 8090}, :as opts})
```


Serves static assets using web server.
Options:
  * `:dir` - directory from which to serve assets
  * `:port` - port
<br><sub>[source](https://github.com/babashka/http-server/blob/main/src/babashka/http_server.clj#L155-L186)</sub>
