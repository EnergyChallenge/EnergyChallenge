# EnergyChallenge

The **EnergyChallenge** is a web application to motivate members of organizations (like universities, companies,...) to save energy in their daily environment.

Organization members can log in to the app and select what acitivies they did to save energy. (e.g. using the stairs instead of the elevator) The users are getting points for every acitivity they did and based on this a ranging can be generated.

Further features are:
* Users can organize themselves in teams
* Profiles for user and teams
* Search function for profiles
* Timeout for activities, so they can only be selected once in a specific time interval
* Users can suggest new activities; These suggestions can be commented and rated by other users
* Some statictics about common acitivities
* Full administration user interface
* Full responsive design
* A *beta version* of an andorid app

## Getting started

The web application is build with [Grails](https://grails.org/), a powerful web framework for the Java platform. It is located in the the ``Server`` directory.

The ``app`` directory contains a beta version of an android app (android >= 5.0).

#### Login data for development

The following users are generated:

* (Default) user:
Username: ``user@example.com``
Password: ``password``

* Administrator:
Username: ``admin@example.com``
Password: ``password``

#### Layout and design

The current design was created for the EnergyChallenge at Kiel University and fits in the universities corporate design (this is the reason for the purple color). To create something that fits better to your organization please edit style cheats, images,... accordingly.

#### Date constants

To adjust the dates when registration/login should be enabled/disbaled, edit lines 30-32 in file:
``EnergyChallenge/Server/grails-app/controllers/de/unikiel/klik/control/web/AuthController.groovy``


## Usage at Kiel University

This application was developed by computer science students at Kiel University´s [Software Engineering Group](http://www.se.uni-kiel.de/en). In June 2015 Kiel University organized the EnergyChallenge project.
Further informations can be found in this articles:
* [Kieler Universität startet Energiesparwettbewerb](http://www.uni-kiel.de/pressemeldungen/index.php?pmid=2015-169-energiesparen) *(in german)*
* [Preisverleihung EnergyChallenge](http://www.uni-kiel.de/pressemeldungen/index.php?pmid=2015-253-energy-challenge) *(in german)*
* [CAU fordert zur EnergyChallenge heraus!](http://www.einfachgutelehre.uni-kiel.de/allgemein/energychallenge) *(in german)*
