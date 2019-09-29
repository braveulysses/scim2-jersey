/*
 * Copyright 2019 Ping Identity Corporation
 * All Rights Reserved.
 */
package com.pingidentity.example.scim2.jersey;

import com.unboundid.scim2.common.exceptions.ScimException;
import com.unboundid.scim2.common.types.AuthenticationScheme;
import com.unboundid.scim2.common.types.BulkConfig;
import com.unboundid.scim2.common.types.ChangePasswordConfig;
import com.unboundid.scim2.common.types.ETagConfig;
import com.unboundid.scim2.common.types.FilterConfig;
import com.unboundid.scim2.common.types.PatchConfig;
import com.unboundid.scim2.common.types.ServiceProviderConfigResource;
import com.unboundid.scim2.common.types.SortConfig;
import com.unboundid.scim2.server.resources.AbstractServiceProviderConfigEndpoint;

import java.util.Collections;

/**
 * Example SCIM 2 ServiceProviderConfig endpoint.
 */
public class ServiceProviderConfigEndpoint
    extends AbstractServiceProviderConfigEndpoint
{
  @Override
  public ServiceProviderConfigResource getServiceProviderConfig() throws ScimException
  {
    return new ServiceProviderConfigResource(
        "https://example.com",
        new PatchConfig(false),
        new BulkConfig(false, 0, 0),
        new FilterConfig(true, 100),
        new ChangePasswordConfig(false),
        new SortConfig(false),
        new ETagConfig(false),
        Collections.singletonList(authenticationScheme()));
  }

  private AuthenticationScheme authenticationScheme()
  {
    return new AuthenticationScheme("None", "No authentication", null, null, "none", true);
  }
}
