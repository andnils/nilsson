# nilsson

[![Clojars Project](https://img.shields.io/clojars/v/nilsson/lein-template.svg)](https://clojars.org/nilsson/lein-template)

A Leiningen template for a clojure HTTP API with Postgresql, and ES6+React frontend.


## Usage

To create a new project

```shell
lein new nilsson <your-project-name>
```

Start a REPL

```shell
clj -A:dev
```

...and start the system:

```clojure
user> (start)
:ok
```

...and compile and serve the ui:
```shell
npm install
npm start
```

## License


Copyright © 2020 Anders Nilsson

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
