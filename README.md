# EnergyChallenge

The **EnergyChallenge** is a web application to motivate members of organizations (like universities, companies,...) to save in their daily environment.

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

The following users are generated:

User:
Username: ``user@example.com``
Password: ``password``

Administrator:
Username: ``admin@example.com``
Password: ``password``

To adjust the dates when registration/login should be enabled/disbaled, edit lines 30-32 in file:
``EnergyChallenge/Server/grails-app/controllers/de/unikiel/klik/control/web/AuthController.groovy``

