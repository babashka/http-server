# Table of contents
-  [`babashka.http-server`](#babashkahttp-server) 
    -  [`default-mime-types`](#default-mime-types) - A map of file extensions to mime-types.
    -  [`serve`](#serve) - Serves static assets using web server.
# babashka.http-server 





## `default-mime-types`

A map of file extensions to mime-types.
<br><sub>[source](https://github.com/babashka/http-server/blob/main/src/babashka/http_server.clj#L20-L113)</sub>
## `serve`
``` clojure

(serve opts)
```


Serves static assets using web server.
  Options:
  * :dir - directory from which to serve assets
  * :port - port 
<br><sub>[source](https://github.com/babashka/http-server/blob/main/src/babashka/http_server.clj#L154-L180)</sub>
