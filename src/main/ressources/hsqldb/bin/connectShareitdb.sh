#!/usr/bin/env bash
java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/shareitdb --dbname.0 shareitdb