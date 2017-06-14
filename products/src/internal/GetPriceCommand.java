package internal;

import com.google.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import javax.ws.rs.core.Context;

import dto.PriceDetails;
import lombok.experimental.Accessors;
import redis.clients.jedis.Jedis;

/**
 * Created by shridhar.hegde on 14/06/17.
 */
public class GetPriceCommand extends HystrixCommand<PriceDetails> {

  private final static String GROUP_KEY = "myRetail-work-client";
  private final ObjectMapper mapper;

  @Context
  private final Jedis jedis;

  @Accessors(fluent = true)
  @lombok.Setter
  private String productId;

  @Inject
  protected GetPriceCommand(ObjectMapper mapper, Jedis jedis) {
    super(HystrixCommandGroupKey.Factory.asKey(GROUP_KEY));
    this.mapper = mapper;
    this.jedis = jedis;
  }

  @Override
  protected PriceDetails run() throws Exception {
    String priceDetails = jedis.get(productId);
    JsonNode priceJson = mapper.readTree(priceDetails);
    return mapper.treeToValue(priceJson, PriceDetails.class);
  }
}
