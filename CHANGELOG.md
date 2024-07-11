# Changelog

[Http-server](https://github.com/babashka/http-server): Serve static assets with [babashka](https://babashka.org/)

## Unreleased

- [#13](https://github.com/babashka/http-server/issues/13): add an ending slash to the dir link, and don't encode the slashes ([@KDr2](https://github.com/KDr2))
- [#12](https://github.com/babashka/http-server/issues/12): Add headers to index page (rather than just file responses)

## 0.1.12

- Add default `:headers` option ([@rafaeldelboni](https://github.com/rafaeldelboni))
- Allow omitting `.html` in paths ([@anderseknert](https://github.com/anderseknert))
- Add `.wasm` mime type ([@hahahahaman](https://github.com/hahahahaman))
- Move router to top level definition for re-use ([@keychera](https://github.com/keychera))
