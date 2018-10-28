# {{name}}

TODO: description

## Usage

You should start with setting up a PostgreSQL database.
Next, edit `config/private.edn` and set your db connection
parameters.

Start a REPL

```shell
lein repl
```

...and start the system:

```clojure
user> (dev)
:ok
dev> (start)
:ok
dev> (db-migrate)
...
```


## License

Copyright © 2018 <your-name>

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
