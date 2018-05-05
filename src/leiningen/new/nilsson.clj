(ns leiningen.new.nilsson
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "nilsson"))

(defn nilsson
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' nilsson project.")
    (->files data
      ["src/main/clj/{{sanitized}}/db.clj" (render "db.clj" data)]
      ["src/main/clj/{{sanitized}}/routes.clj" (render "routes.clj" data)]
      ["src/main/clj/{{sanitized}}/system.clj" (render "system.clj" data)]
      ["src/main/clj/{{sanitized}}/component/hikaricp.clj" (render "hikaricp.clj" data)]
      ["src/main/clj/{{sanitized}}/component/jetty.clj" (render "jetty.clj" data)]
      ["dev/dev.clj" (render "dev.clj" data)]
      ["dev/user.clj" (render "user.clj" data)]
      ["package.json" (render "package.json" data)]
      ["project.clj" (render "project.clj" data)]
      ["README.md" (render "README.md" data)]
      ["webpack.config.js" (render "webpack.config.js" data)]
      [".gitignore" (render ".gitignore" data)]
      [".babelrc" (render ".babelrc" data)]
      [".eslintrc.js" (render ".eslintrc.js" data)]
      ["src/main/js/AppContainer.jsx" (render "AppContainer.jsx" data)]
      ["src/main/js/SuperHeroList.jsx" (render "SuperHeroList.jsx" data)]
      ["src/main/js/SuperHeroListItem.jsx" (render "SuperHeroListItem.jsx" data)]
      ["src/main/js/app.js" (render "app.js" data)]
      ["src/main/resources/index.html" (render "index.html" data)]
      ["src/main/resources/logback.xml" (render "logback.xml" data)]
      ["src/main/resources/migrations/001-heroes.up.sql" (render "001-heroes.up.sql" data)]
      ["src/main/resources/migrations/001-heroes.down.sql" (render "001-heroes.down.sql" data)]
      ["src/main/sass/main.scss" (render "main.scss" data)]
      )))
