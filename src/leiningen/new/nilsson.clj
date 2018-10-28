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
      ["src/main/clj/{{sanitized}}/db.clj" (render "src/main/clj/xxx/db.clj" data)]
      ["src/main/clj/{{sanitized}}/routes.clj" (render "src/main/clj/xxx/routes.clj" data)]
      ["src/main/clj/{{sanitized}}/system.clj" (render "src/main/clj/xxx/system.clj" data)]
      ["src/main/clj/{{sanitized}}/database_migrations.clj" (render "src/main/clj/xxx/database_migrations.clj" data)]
      ["src/main/clj/{{sanitized}}/components/c3p0.clj" (render "src/main/clj/xxx/components/c3p0.clj" data)]
      ["src/main/clj/{{sanitized}}/components/jetty.clj" (render "src/main/clj/xxx/components/jetty.clj" data)]
      ["config/config.edn" (render "config/config.edn" data)]
      ["config/private.edn" (render "config/private.edn" data)]
      ["dev/dev.clj" (render "dev/dev.clj" data)]
      ["dev/user.clj" (render "dev/user.clj" data)]
      ["package.json" (render "package.json" data)]
      ["project.clj" (render "project.clj" data)]
      ["README.md" (render "README.md" data)]
      ["webpack.config.js" (render "webpack.config.js" data)]
      [".gitignore" (render ".gitignore" data)]
      [".babelrc" (render ".babelrc" data)]
      [".eslintrc.js" (render ".eslintrc.js" data)]
      ["src/main/js/AppContainer.jsx" (render "src/main/js/AppContainer.jsx" data)]
      ["src/main/js/SuperHeroList.jsx" (render "src/main/js/SuperHeroList.jsx" data)]
      ["src/main/js/SuperHeroListItem.jsx" (render "src/main/js/SuperHeroListItem.jsx" data)]
      ["src/main/js/app.js" (render "src/main/js/app.js" data)]
      ["src/main/resources/index.html" (render "src/main/resources/index.html" data)]
      ["src/main/resources/logback.xml" (render "src/main/resources/logback.xml" data)]
      ["src/main/resources/migrations/001-heroes.up.sql" (render "src/main/resources/migrations/001-heroes.up.sql" data)]
      ["src/main/resources/migrations/001-heroes.down.sql" (render "src/main/resources/migrations/001-heroes.down.sql" data)]
      ["src/main/sass/main.scss" (render "src/main/sass/main.scss" data)]
      )))
