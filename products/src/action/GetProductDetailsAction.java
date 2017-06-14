package action;

import com.google.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import javax.ws.rs.core.Context;

import dto.GetProductResponse;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * Created by shridhar.hegde on 14/06/17.
 */
@Slf4j
public class GetProductDetailsAction implements MyRetailAction<GetProductResponse> {

  private String productId;

  @Context
  private final Jedis jedis;

  private final ObjectMapper mapper;

  @Inject
  public GetProductDetailsAction(Jedis jedis, ObjectMapper mapper) {
    this.jedis = jedis;
    this.mapper = mapper;
  }


  @Override
  public GetProductResponse invoke() {
    String productDetails = jedis.get(productId);
    GetProductResponse getProductResponse = new GetProductResponse();
    log.info("Product Details from Redis : {}",productDetails);
    try {
      JsonNode productJson = mapper.readTree(productDetails);
      getProductResponse = mapper.treeToValue(productJson,GetProductResponse.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return getProductResponse;
  }

  public GetProductDetailsAction withProductId(String productId) {
    this.productId = productId;
    return this;
  }
}
