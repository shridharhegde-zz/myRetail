package internal;

import com.google.inject.Inject;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import client.BaseClient;
import config.TargetClientConfiguration;
import dto.ProductDetails;
import lombok.experimental.Accessors;

/**
 * Created by shridhar.hegde on 14/06/17.
 */
public class GetProductNameCommand extends HystrixCommand<ProductDetails> implements BaseClient {

  private final static String GROUP_KEY = "myRetail-work-client";
  private static final String GET_PRODUCT_PATH = "/products/v3/{id}";

  private final Client client;
  private final TargetClientConfiguration targetClientConfiguration;

  @Accessors(fluent = true)
  @lombok.Setter
  private String productId;

  @Accessors(fluent = true)
  @lombok.Setter
  private String fields;

  @Accessors(fluent = true)
  @lombok.Setter
  private String idType;

  @Accessors(fluent = true)
  @lombok.Setter
  private String key;


  @Inject
  protected GetProductNameCommand(Client client, TargetClientConfiguration targetClientConfiguration) {
    super(HystrixCommandGroupKey.Factory.asKey(GROUP_KEY));
    HystrixCommandProperties.Setter()
        .withExecutionTimeoutInMilliseconds(30000);
    this.client = client;
    this.targetClientConfiguration = targetClientConfiguration;
  }

  @Override
  protected ProductDetails run() throws Exception {
    URI uri = UriBuilder.fromUri(targetClientConfiguration.getUrl())
        .path(GET_PRODUCT_PATH)
        .queryParam("fields", fields)
        .queryParam("id_type", idType)
        .queryParam("key", key)
        .build(productId);


    Response response = client.target(uri)
        .request(MediaType.APPLICATION_JSON_TYPE)
        .get();
    checkResponse(response);
    return response.readEntity(ProductDetails.class);
  }
}
