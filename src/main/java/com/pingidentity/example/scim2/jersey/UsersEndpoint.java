package com.pingidentity.example.scim2.jersey;

import com.unboundid.scim2.common.ScimResource;
import com.unboundid.scim2.common.exceptions.ResourceNotFoundException;
import com.unboundid.scim2.common.exceptions.ScimException;
import com.unboundid.scim2.common.types.UserResource;
import com.unboundid.scim2.server.annotations.ResourceType;
import com.unboundid.scim2.server.utils.ResourcePreparer;
import com.unboundid.scim2.server.utils.ResourceTypeDefinition;
import com.unboundid.scim2.server.utils.SimpleSearchResults;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import static com.unboundid.scim2.common.utils.ApiConstants.MEDIA_TYPE_SCIM;

/**
 * Example SCIM 2 Users endpoint.
 */
@ResourceType(
    description = "User Account",
    name = "User",
    schema = UserResource.class)
@Path("/Users")
public class UsersEndpoint
{
  private static final ResourceTypeDefinition RESOURCE_TYPE_DEFINITION =
      ResourceTypeDefinition.fromJaxRsResource(UsersEndpoint.class);

  /**
   * Simple search implementation without filter support.
   *
   * @param uriInfo
   *          The UriInfo.
   * @return The search results.
   * @throws ScimException
   *          if an error occurs.
   */
  @GET
  @Produces({MEDIA_TYPE_SCIM, MediaType.APPLICATION_JSON})
  public SimpleSearchResults<UserResource> search(
      @Context final UriInfo uriInfo) throws ScimException
  {
    SimpleSearchResults<UserResource> results =
        new SimpleSearchResults<>(RESOURCE_TYPE_DEFINITION, uriInfo);
    results.add(user123());
    results.add(userABC());

    return results;
  }

  /**
   * Retrieve a resource by ID.
   *
   * @param id
   *          The ID of the resource to retrieve.
   * @param uriInfo
   *          The UriInfo.
   * @return The result.
   * @throws ScimException
   *          if an error occurs.
   */
  @Path("{id}")
  @GET
  @Produces({MEDIA_TYPE_SCIM, MediaType.APPLICATION_JSON})
  public ScimResource retrieve(
      @PathParam("id") final String id, @Context final UriInfo uriInfo)
      throws ScimException
  {
    UserResource resource = null;

    if (id.equals("123"))
    {
      resource = user123();
    }
    else if (id.equals("ABC"))
    {
      resource = userABC();
    }

    if (resource != null)
    {
      ResourcePreparer<UserResource> resourcePreparer =
          new ResourcePreparer<>(RESOURCE_TYPE_DEFINITION, uriInfo);
      return resourcePreparer.trimRetrievedResource(resource);
    }
    throw new ResourceNotFoundException("No resource with ID " + id);
  }

  private UserResource user123()
  {
    UserResource resource = new UserResource().setUserName("user123");
    resource.setId("123");
    resource.setDisplayName("User 123");
    resource.setNickName("123");
    return resource;
  }

  private UserResource userABC()
  {
    UserResource resource = new UserResource().setUserName("userABC");
    resource.setId("ABC");
    resource.setDisplayName("User ABC");
    resource.setNickName("ABC");
    return resource;
  }
}
