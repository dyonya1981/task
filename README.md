# Live Football World Cup Scoreboard Library

## Project Description

This project implements a live scoreboard for football matches using a simple in-memory store.
This library allows you to manage live football matches, update their scores, and retrieve a summary of all ongoing matches.

The scoreboard allows you to:
- Start a match with a default score of 0-0.
- Update the score of ongoing matches.
- Finish a match, removing it from the scoreboard.
- Get a summary of matches ordered by total score.

## Assumptions
- Each match is between two distinct teams.
- Scores cannot be negative.
- Matches are managed in an in-memory data structure, no persistence.

## Usage

### Start a new match

IScoreboard scoreboard = new Scoreboard();
scoreboard.startMatch("Mexico", "Canada");


## Key Features
- Start and update football matches.
- Retrieve match summaries sorted by total score.
- Full unit test coverage.
- Input data sanitization.

## Dependencies:
- Java 17
- Maven/Gradle
- testImplementation 'org.junit.jupiter:junit-jupiter-api:5.8.1'
- testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.8.1'

## How to Build
- Run `mvn clean install` to build the project.

## How to Run Tests
- Run `mvn test` to execute the unit tests.

## Design Principles
- **Single Responsibility Principle**: The logic for managing matches and formatting output is separated.
- **Clean Code**: The codebase is simple, modular, and easy to maintain.
- DRY, KISS, YAGNI, SOLID, OKAMA RAZOR etc.

## Further Enhancements (Future Work)
- **Logging**: Integrate a logging framework for better tracing and debugging.
- **Thread Safety**: Ensure the `Scoreboard` class is thread-safe in multi-threaded environments.
- **Dependency Injection**: The `Scoreboard` will use `MatchFactory` to create Matches, allowing for easy extensibility.
- **Performance Optimization**: Optimize sorting logic using `PriorityQueue` for better performance as the number of matches grows.