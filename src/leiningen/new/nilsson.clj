(ns leiningen.new.nilsson
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "nilsson"))

(defn nilsson
  "FIXME: write documentation"
  [name]
  (let [data {:name      name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' nilsson project.")
    (->files data
             ["dev/user.clj" (render "dev/user.clj" data)]
             ["src/{{sanitized}}/db.clj" (render "src/asdf/db.clj" data)]
             ["src/{{sanitized}}/main.clj" (render "src/asdf/main.clj" data)]
             ["src/{{sanitized}}/routes.clj" (render "src/asdf/routes.clj" data)]
             ["src/{{sanitized}}/system.clj" (render "src/asdf/system.clj" data)]
             ["src/{{sanitized}}/components/db.clj" (render "src/asdf/components/db.clj" data)]
             ["src/{{sanitized}}/components/http.clj" (render "src/asdf/components/http.clj" data)]
             ["src/config.edn" (render "src/config.edn" data)]
             ["src/config.private.edn" (render "src/config.private.edn" data)]
             ["src/logback.xml" (render "src/logback.xml" data)]
             ["ui/src/index.html" (render "ui/src/index.html" data)]
             ["ui/src/index.js" (render "ui/src/index.js" data)]
             ["ui/src/style/style.css" (render "ui/src/style/style.css" data)]
             ["ui/.babelrc" (render "ui/.babelrc" data)]
             ["ui/package.json" (render "ui/package.json" data)]
             ["ui/webpack.config.js" (render "ui/webpack.config.js" data)]
             [".dir-locals.el" (render "dir-locals.el" data)]
             [".gitignore" (render "gitignore" data)]
             ["deps.edn" (render "deps.edn" data)]
             ["README.md" (render "README.md" data)]
             )))
