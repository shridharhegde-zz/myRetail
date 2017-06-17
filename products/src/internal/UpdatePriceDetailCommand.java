package internal;

import com.google.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import dto.PriceDetails;
import lombok.experimental.Accessors;
import redis.clients.jedis.Jedis;

/**
 * Created by shridhar.hegde on 15/06/17.
 */
public class UpdatePriceDetailCommand extends HystrixCommand<Response> {

  private final static String GROUP_KEY = "myRetail-work-client";
  private final ObjectMapper mapper;

  @Context
  private final Jedis jedis;

  @Accessors(fluent = true)
  @lombok.Setter
  private String productId;

  @Accessors(fluent = true)
  @lombok.Setter
  private PriceDetails priceDetails;

  @Inject
  protected UpdatePriceDetailCommand(ObjectMapper mapper, Jedis jedis) {
    super(HystrixCommandGroupKey.Factory.asKey(GROUP_KEY));
    this.mapper = mapper;
    this.jedis = jedis;
  }

  @Override
  protected Response run() throws Exception {
    jedis.set(productId,String.valueOf(mapper.convertValue(priceDetails, JsonNode.class)));
    return Response.ok().build();
  }
}
