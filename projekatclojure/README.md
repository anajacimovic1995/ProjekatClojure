# projekatclojure

Project Clojure is made as part of Faculty of Organizational Sciences course work. This application is made
for buyers and sellers of the appartments mainly and other realestates. In further text, functionalities will be described
in detail. But firstly, a word about technical details.

## Environments

Project is created and developed in Java EE Eclipse IDE with installed Counterclockwise plugin for Clojure code.
This plugin include Leiningen support, and everything else needed for development of one Clojure project.
For handling database is used SQLyog.

## Setup

Before running the aplication, database should be created in MySQL or other similar environment. Name of the base
should be "clojurebaza". To setup the configuration for database enter the file config.edn. There you can
change username and the password. Also, the same parameters should be written in file migratus-config.edn.

Now, you should run migratus in lein command line. You get the line with the right click on your project and picking Leiningen 
-> Generic Leiningen Command Line. When you open it, type 

lein migratus migrate

Finally, to start the server, in the same command line type

lein ting server

## License

Copyright © 2019 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
