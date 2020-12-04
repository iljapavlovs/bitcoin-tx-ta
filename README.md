### Test Infrastructure
Self-contained tests which spin up 2 Bitcoin nodes in Docker containers using [Testcontainers](https://www.testcontainers.org/)
* Containers automatically start and quit during test execution, guaranteeing clean state for the tests
* For each test method clean version of containers is started

Just run 
```bash
./gradlew clean test
```
