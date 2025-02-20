# WorldCup Scoreboard 
## Assumptions taken/Comments
1. No external libraries used apart of testing library.
1. Operation from exercise _"4. Get a summary of games by total score [...]"_ is implemented by using
LinkedHashMap to maintain insertion order and sorting by total goals as requested.
1. As exercise requested simple implementation, this one is simple and should not be considered thread-safe.  
In order to cover multithreaded usage of WorldCupScoreboard it would need improvements. 
Possible improvements:
   1. Use `synchronized` on methods where Collections are accessed in WorldCupScoreboard.
   2. Change Collections to thread-safe.

## How to use
To run tests
```
./gradlew test
```

To run  
(Main class just performs _"4. Get summary of games by total score [...]"_ test case from exercise description)
```
./gradlew run
```

## Technologies used
```
JDK Azul Zulu@21.0.4
gradle (wrapper version)
Spock 
```
