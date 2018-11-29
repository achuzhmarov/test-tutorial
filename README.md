This application demonstrates how to write unit and integration tests for a java REST API. Detailed tutorial is available in Russian on [habr.com](https://habr.com/company/custis/blog/427603/).

### Prerequisites
* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Gradle](https://gradle.org/gradle-download/) (optional)

### Running locally

    gradle bootRun

If you do not have gradle installed, you can use
    
    ./gradlew bootRun

You can use curl to send some requests:

    curl -X POST http://localhost:8080/product/new -H 'Content-Type: application/json' -d '{"name": "product_name", "isAdvertised": true, "price": 1.01}'
    curl http://localhost:8080/product/0

### Running the tests
Unit tests:

    gradle test

Integration tests:
 
    gradle integrationTest