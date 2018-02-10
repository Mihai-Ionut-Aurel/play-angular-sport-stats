[![MIT License][license-badge]][LICENSE]

# Demo for Job Interview

This project was build as a showcase for a job interview.

## Used Summary

* [Play Framework: 2.6.7](https://www.playframework.com/documentation/2.6.x/Home)
* [Angular: 5.2.0](https://angular.io/)
* [Angular CLI: 1.6.6](https://cli.angular.io/)

## How to use it?

### Prerequisites

* [Node.js](https://nodejs.org/)
* [JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [scala](https://www.scala-lang.org/download/)

### Let's get started,

* Fork or clone this repository.

* Used any of the following [SBT](http://www.scala-sbt.org/) commands which will intern trigger frontend associated npm scripts.

```
    sbt clean           # Clean existing build artifacts

    sbt stage           # Build your application from your project’s source directory

    sbt run             # Run both backend and frontend builds in watch mode

    sbt dist            # Build both backend and frontend sources into a single distribution artifact

    sbt test            # Run both backend and frontend unit tests
```


* Project based on the template (https://github.com/yohangz/scala-play-angular-seed)

In addition added:
```
├── /app/                       # The backend (scala) application sources (controllers, models, views, assets)  
│     ├──controllers/
│        ├── FrontendAPIController.scala         #Controller that serve data to the front end
│     ├── models                # Model cases created to handle specific data structures for the assignment
│     └── utility               # Utility class for common operation to be executed on the data to be aggregated
├── /test/                      # Tests for the controller and for the defined utility class
├── /ui/                        # Angular front end sources
│     ├── /app/                 # End to end tests folder
│        ├── /_components/   #Several Angualr2 components that show the data aggregation to the user
│            ├── /competitions  # Angualar 2 Component with unit tests defined for it
│            ├── /leaderboard   # Angualar 2 Component with unit tests
│        ├── models             # Data structures for the data received from the back-end
│        ├── services           # API service. Communicates with the back-end to send and receive data
│     ├── enviroments           # Added configurations variables in the environment.ts


### What the app does:
* Allows to upload a CSV file with sport actions
* Allows the user to select the competion
* Showcases the leaderboard for a given competition
* Showcase the matches and results for a given competition

### Extra functions that are not used by the front-end
* Get player based on a team : SportActionUtilities.getTeamPlayers
* Get all players : SportActionUtilities.getPlayers
* Get matches of a given team: SportActionUtilities.GetTeamMatches
* Get all teams in the given file : FrontendAPIController.teams