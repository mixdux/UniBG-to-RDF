UniBG-to-RDF
============

A tool that is used to transfer data, about buildings of University of Belgrade, from a spreadsheet to an RDF database.

1. About the Project
=
This project addressees the problem of University of Belgrade not having any data source that contains precise geographical information about buildings that consist it. It creates an RDF repository that is populated with the necessary data, based on the similar work on this subject by [University of Southampton](http://data.southampton.ac.uk/dataset/places.html). Alongside geographical information, other data has been included in the data model - in order to create an unified, free to use, publicly available, linked data source. Faculty buildings, Halls, Restaurants and Libraries have been covered, using some of the most common ontologies available.

2. Domain Model
=
Data has been collected from several university and non-university (run) sites.  [Southampton's](http://data.southampton.ac.uk/dataset/places.html) ontologies have been analysed and current data model has been created in accordance with theirs. After thorough analysis and need assessment, following domain model is created:

![alt tag](https://dl.dropboxusercontent.com/u/29400255/Fax/BGUNIRDF.png)

Class *Building* contains all necessary data needed for describing a university building - its name, construction date, short description, its features (i.e. library, copier, store) and website about it. Geographicall data is also included. It consists of longitude and latitude of a buildings position, and a [WKT](http://en.wikipedia.org/wiki/Well-known_text) polygon that represents building's outline and is optimised for drawing on the map (egdes are pairs of longitude latitude values).
Building is linked to its *FormalOrganization* and *Resource*. *Site* references it.

*FormalOrganization* class represents the University of Belgrade and contains only it's name.

*Thing* class describes what is situated in that building. A building can host a Faculty, Library, Restaurant or a Hall. It also contains only the name (type) of the hosted institution.

*Site* class contains a set of *Buildings* that are contained whithin it. Every building has its site, a piece of land (yard) on which it is situated. Apart of the set of buildings, a site also contains its name.

3. The Solution
=
In order to begin creating the database, all necessary data first needed to be collected. As was mentioned in the section 1, several sites were targeted for data extraction. Among them are [University of Belgrade](http://www.bg.ac.rs/en/index.php) and [Student's center Belgrade](http://www.sc.rs/sc/index.php). Google Earth, Google Maps and Bing Maps were utilised in the geo-mapping process.

Based on the model created, relevant data was collected and inputed into a pre-formated Google Spreadsheet. [Spreadsheet](https://docs.google.com/spreadsheets/d/1Vt64U_lFliaTGr0sz_dYkpL0XiuR5yKRKcD2FbsaG9o) uses as a temporary data host. It consists of several worksheets, each containing the data about different class from the model. This form of temporary data storing has been chosen because of the ability to access the desired spreadsheet programmatically (from whithin applications code).

Project is able to collect data from it, which is further transformed into RDF triplets based on several ontologies, and resulting triplets are persisted into an RDF repository.

4. Technical Realization
=
This application is written in programming language Java, using NetBeans IDE 8.0. The application uses [Jenabean library](https://code.google.com/p/jenabean/) for mapping Java objects into RDF triplets using annotations. Jenabean provides explicit bindings between Java classes and RDFS/OWL classes, and also between Java properties and the corresponding RDF properties. [Jena TDB library](http://jena.apache.org/documentation/tdb/) is used for data storage in an RDF repository. TDB is a component of Jena for RDF storage and query. It supports the full range of Jena APIs.

In order to extract the data from the remote spreadsheet, [Google Sheets API v3.0](https://developers.google.com/google-apps/spreadsheets/) was utilised. It is one of the Google APIs, created to make Google spreadsheet management available form whithin the program code.

[Google OAuth 2.0 client library](https://code.google.com/p/google-oauth-java-client/) is utilised in order to sign-in with Google and retrieve the data form the spreadsheet. OAuth is an open protocol that allows simple and secure authorization.

Other libraries contained in this project serve only as a support to aforementioned ones.

Here's a list of the ontologies used in the creation of the domain model:

- rdf: http://www.w3.org/1999/02/22-rdf-syntax-ns#
- rdfs: http://www.w3.org/2000/01/rdf-schema#
- dcterms: http://purl.org/dc/terms/
- rooms: http://vocab.deri.ie/rooms#
- oo: http://purl.org/openorg/
- org: http://www.w3.org/ns/org#
- geo: http://www.w3.org/2003/01/geo/wgs84_pos#
- xsd: http://www.w3.org/2001/XMLSchema#
- foaf: http://xmlns.com/foaf/0.1/
- southampton: http://id.southampton.ac.uk/ns/
5. Running in Your Environment
=
If you would like to run the project on your device, several things should be taken into account:
* Change Path-file and Path-TDB variables in util.Constants to reflect your file system
* Request access to the data spreadsheet, prior to running the project. It is required that the spreadsheet is contained on your Google drive. You can send an e-main to mixdux@yahoo.com and the spreadsheet will be shared with you.
