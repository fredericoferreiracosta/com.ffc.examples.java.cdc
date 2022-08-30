# Consumer-Driven Contracts example

This is a multi-module Maven project to simulate two microservices: product and order.
You can run them individually by running ```mvn spring-boot:run``` in each submodule.

Urls are: http://127.0.0.1:8080/product/1 and http://localhost:8081/order/1.

## Provider

The provider submodule has a contract configuration that will generate the stub after testing/building. Check the generated file ```ContractVerifierTest``` after building the project.

## Consumer

The provider stub will be used in the integration test ```OrderIntegrationTest``` in the consumer submodule.

You may run ```mvn test``` on each submodule to see the tests running and the contract being fulfilled.