### Test Infrastructure
Self-contained tests which spin up 2 Bitcoin nodes in Docker containers using [Testcontainers](https://www.testcontainers.org/)
* Containers automatically start and quit during test execution, guaranteeing clean state for the tests
* For each test method clean version of containers is started

Just run 
```bash
./gradlew clean test allureReport
```

#### Running Nodes separately for local development
Or you can start containers separately
```bash
docker-compose -f src/test/resources/docker/docker-compose.yml up
```

### Reporting
[Allure](http://allure.qatools.ru/) report by default is generated at `build/reports/allure-report`


### What was left out due to limited time:
* better BDD support - add Cucumber-JVM or Spock, and therefore better reporting with detailed test steps
* testing negative cases (e.g. sending bitcoins when there are any)
* verifying status codes, error response - with current implementation it's not possible. Need to rewrite code to more low level, add HTTP interceptor. However, current implementation is much simpler and easier to use
