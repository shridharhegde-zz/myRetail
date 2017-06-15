package resource;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import action.GetProductDetailsAction;
import action.UpdatePriceDetailsAction;
import dto.ProductResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by shridhar.hegde on 14/06/17.
 */

@Path("/products")
@Slf4j
@Singleton
public class ProductResource {

  private final Provider<GetProductDetailsAction> getProductDetailsActionProvider;
  private final Provider<UpdatePriceDetailsAction> updatePriceDetailsActionProvider;

  @Inject
  public ProductResource(Provider<GetProductDetailsAction> getProductDetailsActionProvider,
                         Provider<UpdatePriceDetailsAction> updatePriceDetailsActionProvider) {
    this.getProductDetailsActionProvider = getProductDetailsActionProvider;
    this.updatePriceDetailsActionProvider = updatePriceDetailsActionProvider;
  }

  @GET
  @Path("/details/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public ProductResponse getProductResponse(@PathParam("id") String productId){
    return getProductDetailsActionProvider.get().withProductId(productId).invoke();
  }

  @PUT
  @Path("/details/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response updatePriceDetails(@PathParam("id") String productId, ProductResponse productResponse){
    return updatePriceDetailsActionProvider.get().withRequest(productResponse)
        .withProductId(productId).invoke();
  }
}
