# SCIM 2 Jersey example

This is a simple example that demonstrates the basics of writing a SCIM 2 service 
using the [SCIM 2 SDK](https://github.com/pingidentity/scim2) and 
[Jersey](https://eclipse-ee4j.github.io/jersey/) (JAX-RS).

For more example code, it may be helpful to look at the unit tests for the
**scim2-sdk-server** component. For general usage information on the SCIM 2 SDK, 
visit the [SCIM 2 SDK wiki](https://github.com/pingidentity/scim2/wiki).


## Running this example

Build using standard Maven goals: E.g., `mvn compile` or `mvn package`

To run with Jetty: `mvn jetty:run`

### SCIM 2 endpoints

By default, the base URL for SCIM 2 requests is `http://localhost:8888/scim2`, and the following 
endpoints are available:

* `http://localhost:8888/scim2/ServiceProviderConfig`
* `http://localhost:8888/scim2/Schemas`
* `http://localhost:8888/scim2/ResourceTypes`
* `http://localhost:8888/scim2/Users`

The `Users` endpoint provides two example users with the IDs `123` and `ABC`. 
These user resources are simply loaded on the fly from memory. A real-world SCIM 2 service would 
retrieve the user data from a data source such as an RDBMS or directory server, of course.

### Customizing the base URL

To change the HTTP port, change the `httpConnector.port` value for the `org.eclipse.jetty` build 
plugin in the POM.

To change base path, change the `servlet-mapping.url-pattern` value in `web.xml`.

## Overview of how this works

To create your own SCIM 2 server, you first need to add the 
`com.unboundid.product.scim2.scim2-sdk-server` dependency to a JAX-RS application.

(If you're looking for general information about working with JAX-RS, the 
[Restful Java with JAX-RS gitbook](https://dennis-xlc.gitbooks.io/restful-java-with-jax-rs-2-0-2rd-edition/content/) 
is a good place to start.)

You can then use the scim2-sdk-server classes to set up your SCIM 2 server's capabilities:

* Provide `ServiceProviderConfig` endpoint by providing an implementation of 
  `AbstractServiceProviderConfigEndpoint`. See the `ServiceProviderConfigEndpoint` class for an 
  example.
* For any resource types that you need to implement (i.e., users), provide a standard JAX-RS service 
  class and annotate it with `@ResourceType`. See the `UsersEndpoint` class for an example.
* Register your server's endpoints, filters, exception mappers, and JSON 
  serialization/deserialization provider by providing an implementation of `ResourceConfig`.
  See `Scim2Application` for an example.
 