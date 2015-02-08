# dataPumpDemo [![Build Status](https://travis-ci.org/Endron/dataPumpDemo.svg)](https://travis-ci.org/Endron/dataPumpDemo)

This is a demo for how to just pump the context of a CSV file into a database using Spring Boot, Spring Data and
Spring Batch. Most database clients already support this usecas in some way. The advantage of this aproch is mainly
that it can be packt into a JAR and executed wherever you have a JRE available. 

An other benefit would be to use the power of Java to transform the data from the CSV in some way before writing to
the database. 
