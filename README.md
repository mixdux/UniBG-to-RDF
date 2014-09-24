UniBG-to-RDF
============

UniBG-to-RDF is a tool used to transform data about buildings of the University of Belgrade, Serbia from tabular format (a spreadsheet) into RDF data.

1. About the Project
=
Project enables the University of Belgrade, Serbia to have a data source that contains precise geographical information about buildings it consists of. It creates an RDF repository that is populated with the necessary data, based on the similar work by the University of Southampton called [Buildings and Places](http://data.southampton.ac.uk/dataset/places.html). Alongside geographical information, data about each building has been included in the data repository in order to create an unified, free to use, publicly available, linked data source. Data about faculty buildings, student halls, restaurants and libraries have been captured, using some of the most common RDF ontologies available.

2. Domain Model
=
Ontologies used in Southampton's [Buildings and Places project](http://data.southampton.ac.uk/dataset/places.html) have been analysed and and classes and properties used for this project are only those for which the data could be obtained. Following domain model is created:

![Domain model](https://dl.dropboxusercontent.com/u/29400255/Fax/BGUNIRDF.png)

Class *Building* contains all necessary data needed for describing a university building - its name, construction date, short description, its features (i.e. library, copier, store) and website about it. Geographical data is also included. It consists of longitude and latitude of a building position, and a [WKT](http://en.wikipedia.org/wiki/Well-known_text) polygon that represents the building outline that is optimised for drawing on the map (edges are pairs of longitude latitude values). The *Building* class is linked to its *FormalOrganization* and *Resource*. *Site* references it.

*FormalOrganization* class represents the University of Belgrade and contains only a name.

*Thing* class describes what is situated in that building. A building can host a Faculty, Library, Restaurant or a Hall. It also contains only the name (type) of the hosted institution.

*Site* class contains a set of *Buildings* that are contained within it. Every building has its site, a piece of land (yard) on which it is situated. Apart of the set of buildings, a site also contains a name.

3. The Solution
=
In order to create the data repository with the university data, all necessary data first needed to be collected. Several websites were used for data extraction, among them official website of the University of Belgrade [http://www.bg.ac.rs/en/](http://www.bg.ac.rs/en/index.php) and official website of Student Center of the University of Belgrade [http://www.sc.rs](http://www.sc.rs/sc/index.php). Both websites are in Serbian language and the data from it was colelcted manually. [Google Earth](https://www.google.com/earth/), [Google Maps](https://maps.google.com/) and [Bing Maps](www.bing.com/maps/) were used for collecting geo data.

Based on the domain model created, relevant data was collected and inputted into a pre-formatted [Google Spreadsheet](https://docs.google.com/spreadsheets/d/1Vt64U_lFliaTGr0sz_dYkpL0XiuR5yKRKcD2FbsaG9o/edit?pli=1#gid=0). It consists of several sheets, each containing data about a different class from the model. This form of storing data in an online space has been chosen because of the ability to easily change the data by multiple users and also to access the data programmatically.

The programm fetches the data from the spreadsheet, transform it into RDF triplets based on the domain model, and resulting triplets are persisted into an RDF repository.

4. Technical Realization
=
This application is written in the Java programming language. 

The application uses [Jenabean library](https://code.google.com/p/jenabean/) for mapping Java objects into RDF triplets using annotations. Jenabean provides explicit bindings between Java classes and RDFS/OWL classes, and also between Java properties and the corresponding RDF properties. [Jena TDB library](http://jena.apache.org/documentation/tdb/) is used for data storage in an RDF repository. TDB is a component of Jena for RDF storage and query. It supports the full range of Jena APIs.

In order to extract the data from the remote spreadsheet, [Google Sheets API v3.0](https://developers.google.com/google-apps/spreadsheets/) was utilised. It is one of the Google APIs, created to make Google spreadsheet management available from within the program code.

[Google OAuth 2.0 client library](https://code.google.com/p/google-oauth-java-client/) is utilised in order to sign-in with Google account and retrieve data from a spreadsheet. OAuth is an open protocol that allows simple and secure authorization.

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
- 
5. Running in Your Environment
=
If you would like to run the project on your machine, several things need to be configured:
* Change Path-file and Path-TDB variables in util.Constants to reflect your file system
* Request access to the data spreadsheet, prior to running the project. It is required that the spreadsheet is contained on your Google drive. You can send an e-main to mixdux@yahoo.com and the spreadsheet will be shared with you.
