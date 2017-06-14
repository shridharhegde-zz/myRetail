package resource;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import action.GetProductDetailsAction;
import dto.GetProductResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by shridhar.hegde on 14/06/17.
 */

@Path("/products")
@Slf4j
@Singleton
public class ProductResource {

  private final Provider<GetProductDetailsAction> getProductDetailsActionProvider;

  @Inject
  public ProductResource(Provider<GetProductDetailsAction> getProductDetailsActionProvider) {
    this.getProductDetailsActionProvider = getProductDetailsActionProvider;
  }

  @GET
  @Path("/get/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public GetProductResponse getProductResponse(@PathParam("id") String productId){
    return getProductDetailsActionProvider.get().withProductId(productId).invoke();
  }
}
