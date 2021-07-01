package com.pingidentity.example.scim2.jersey;

import com.fasterxml.jackson.jaxrs.cfg.JaxRSFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.unboundid.scim2.common.utils.JsonUtils;
import com.unboundid.scim2.server.providers.DefaultContentTypeFilter;
import com.unboundid.scim2.server.providers.DotSearchFilter;
import com.unboundid.scim2.server.providers.JsonProcessingExceptionMapper;
import com.unboundid.scim2.server.providers.RuntimeExceptionMapper;
import com.unboundid.scim2.server.providers.ScimExceptionMapper;
import com.unboundid.scim2.server.resources.ResourceTypesEndpoint;
import com.unboundid.scim2.server.resources.SchemasEndpoint;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * A simple SCIM 2 service implemented with Jersey.
 */
public class Scim2Application extends ResourceConfig
{
  public Scim2Application()
  {
    packages("com.pingidentity.example.scim2.jersey");

    // Exception Mappers
    register(ScimExceptionMapper.class);
    register(RuntimeExceptionMapper.class);
    register(JsonProcessingExceptionMapper.class);

    // JSON provider
    JacksonJsonProvider provider =
        new JacksonJsonProvider(JsonUtils.createObjectMapper());
    provider.configure(JaxRSFeature.ALLOW_EMPTY_INPUT, false);
    register(provider);

    // Filters
    register(DotSearchFilter.class);
    register(DefaultContentTypeFilter.class);

    // Metadata endpoints
    register(ResourceTypesEndpoint.class);
    register(SchemasEndpoint.class);
    register(ServiceProviderConfigEndpoint.class);

    // Resource type endpoints
    register(UsersEndpoint.class);
  }
}
