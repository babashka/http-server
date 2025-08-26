# Changelog

[Http-server](https://github.com/babashka/http-server): Serve static assets with [babashka](https://babashka.org/)

## 0.1.14

- [#21](https://github.com/babashka/http-server/issues/21): Add `:not-found` option for handling unfound files. The option is a function of the request and should return a map with `:status` and `:body`.

## 0.1.13

- [#16](https://github.com/babashka/http-server/issues/16): support range requests ([jmglov](https://github.com/jmglov))
- [#13](https://github.com/babashka/http-server/issues/13): add an ending slash to the dir link, and don't encode the slashes ([@KDr2](https://github.com/KDr2))
- [#12](https://github.com/babashka/http-server/issues/12): Add headers to index page (rather than just file responses)

## 0.1.12

- Add default `:headers` option ([@rafaeldelboni](https://github.com/rafaeldelboni))
- Allow omitting `.html` in paths ([@anderseknert](https://github.com/anderseknert))
- Add `.wasm` mime type ([@hahahahaman](https://github.com/hahahahaman))
- Move router to top level definition for re-use ([@keychera](https://github.com/keychera))
