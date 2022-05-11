# Hibernate CteInsertStrategy Exception

## Prerequisites

- OpenJDK 17
- Docker & Docker Compose

## Steps for reproducing the error

```
git clone git@github.com:haba713/hibernate-cte-insert-strategy-issue.git
cd hibernate-cte-insert-strategy-issue/
docker-compose up -d                                # Start DB.
./gradlew test                                      # Fails.
cat build/test-results/test/TEST-haba713.MyTest.xml # See the exception.
git checkout hibernate568
./gradlew test                                      # Works fine.
```
