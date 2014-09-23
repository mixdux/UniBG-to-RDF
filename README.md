UniBG-to-RDF
============

A tool that is used to transfer the data, about buildings of University of Belgrade, from a spreadsheet to an RDF database.

1. About the Project
=
University of Belgrade does not have any data source that contains precise geographical information about buildings that consist it. This project addresses the very problem, by creating an RDF repository that is populated with the necessary data. Alongside geographical information, other data has been included in our data model - in order to create an unified, free to use, publicly available, linked data source. Faculty buildings, Halls, Restaurants and Libraries have been covered, using some of the most common ontologies available.

2. Domain Model
=
Data has been collected from several university and non-university (run) sites.  [Southampton's](http://www.schema.org/Organization) ontologies have been analysed and they serve as the foundation of our model. They have created the data set which several of their applications use, and our (data set) has been moulded by it. After a thorough analysis and need assessment, following domain model is created:

![alt tag](https://dl.dropboxusercontent.com/u/29400255/Fax/BGUNIRDF.png)

Class *Building* contains all necessary data about an university building; such as it's name, construction date, short description, it's features (i.e. library, copier, store) and website about it. Also, geographicall data is included - longitude and latitude of the building, and a [WKT](http://en.wikipedia.org/wiki/Well-known_text) polygon that represents the base of the building and can be drawn on the map (egdes are latitude-longitude pairs).
It is linked to it's *FormalOrganization* and *Resource*. *Site* references it.

*FormalOrganization* class represents the University of Belgrade and contains it's name.

*Resource* class describes in what purposes is the building used - is it a Faculty, Library, Restaurant or a Hall. It also contains only the name of the purpose.

*Site* class contains a set of *Building*s that are whithin it. Every building has it's site, the ground it is built upon - a piece of land (yard) on which the building is situated. Apart of the set of buildings, a site also contains it's name.

3. The Solution
=
In order to begin creating the database, all necessary data first needed to be collected. As was mentioned in the section 1, several sites were targeted for data extraction. Among them are [University of Belgrade](http://www.bg.ac.rs/en/index.php) and [Student's center Belgrade](http://www.sc.rs/sc/index.php). Google Earth, Google Maps and Bing Maps were utilised in the geo-mapping process.
Based on the model created, relevant data was collected and inputed into a pre-formated Google Spreadsheet. There are several worksheets in it, each for different class from the model. The spreadsheet can be found [here](https://docs.google.com/spreadsheets/d/1Vt64U_lFliaTGr0sz_dYkpL0XiuR5yKRKcD2FbsaG9o) (it can also be accessed  programmatically).
With the help of several libraries, and by annotating Java classes that correspond to (describe) classes form the domain model, the process of storing data into RDF repository could begin.

4. Technical Realization
=
This application is written in programming language Java, using NetBeans IDE 8.0. The application uses [Jenabean library](https://code.google.com/p/jenabean/) for mapping Java objects into RDF triplets using annotations. Jenabean provides explicit bindings between Java classes and RDFS/OWL classes, and also between Java properties and the corresponding RDF properties. [Jena TDB library](http://jena.apache.org/documentation/tdb/) is used for data storage in an RDF repository. TDB is a component of Jena for RDF storage and query. It supports the full range of Jena APIs.

In order to extract the data from the remote spreadsheet, [Google Sheets API v3.0](https://developers.google.com/google-apps/spreadsheets/) was utilised. It is one of the Google APIs, created to make Google spreadsheet management available form the program code.

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
- southampton: http://id.southampton.ac.uk/ns/
- foaf: http://xmlns.com/foaf/0.1/
