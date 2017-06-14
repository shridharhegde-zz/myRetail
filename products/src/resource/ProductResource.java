package resource;

import com.google.inject.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dto.GetProductResponse;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * Created by shridhar.hegde on 14/06/17.
 */

@Path("/products")
@Slf4j
@Singleton
public class ProductResource {

  @GET
  @Path("/get/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public GetProductResponse getProductResponse(@PathParam("id") String productId,@Context Jedis jedis){
    String product = jedis.get(productId);
    log.info("Product name got :{} ",product);
    GetProductResponse getProductResponse = new GetProductResponse();
    getProductResponse.setProduct(product);
    return getProductResponse;
  }
}
