# projekatclojure

Project Clojure is made as part of the Faculty of Organizational Sciences course work. This application is made
for buyers and sellers of the apartments mainly and other real estates. In further text, functionalities will be described
in detail. But firstly, a word about technical details.

## Environments

Project is created and developed in Java EE Eclipse IDE with installed Counterclockwise plugin for Clojure code.
This plugin includes Leiningen support and everything else needed for the development of one Clojure project.
For handling, the database is used SQLyog.

## Setup

Before running the application, the database should be created in MySQL or other similar environments. Name of the base
should be "clojurebaza". To setup, the configuration for the database enters the file config.edn. There you can
change username and the password. Also, the same parameters should be written in file migratus-config.edn.

Now, you should run migratus in lein command line. You get the line with the right click on your project and picking Leiningen 
-> Generic Leiningen Command-Line. When you open it, type 

lein migratus migrate

Finally, to start the server, in the same command line type

lein ring server

## Short Functionality Description 

The application includes a wide range of pages (home page, login and registration pages, search pages, etc.).
There are 3 types of users: 
	1. Buyers
	2. Owners
	3. Admin

All 3 types should log in or register (admin does not) to enter the appropriate Main Forms. If users are not
logged in, a user could just search all apartments and project.

As a buyer, you can search all available apartments and projects by location, price or space, 
and you can mark as favorite the ones that you want. Also, these ones are later available in the table of favorites.

As an owner, you can search your apartments and projects by location, price or space, update or delete them,
and create new ones.

As admin, you can search all apartments and projects by location, price or space, you can see all users
(both, buyers and owners), and you can delete the ones you want.

## Short Library Description

Leiningen is a build automation and dependency management tool for Clojure projects. Leiningen script is available
on the following link https://leiningen.org/. 

For execution of the web part of the application and requests is used Ring dependency.

All objects used in the project should have routes for the pages that should be performed in the application.
For the implementation of these routes is used Compojure library. These files are in projectclojure/routes package.

For the development of database schema is used Migratus library. We use it when we set up the database before running
the project. 

Korma is the library used for handling SQL queries. In the file komunikacija.clj are all functions directly connected
to database. At the beginning of the file are imported requred libraries. One of them is Korma. Later in the file, it is used as 'k'.

In all routes files are defined structured which are used in the implementation and database. For handling and validation
of the structures is used Struct library.

Selmer library is used as templating language. All pages should be rendered when they are called, and this function is executed
with this library.

Librator library is used for exposing the data as resources. It is mainly used for search, update and delete functions. 

All dependencies are defined in project.clj file. And included and the other files as required.

## License

Copyright © 2019 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
