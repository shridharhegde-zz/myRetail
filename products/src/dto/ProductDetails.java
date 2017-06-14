package dto;

import com.google.common.collect.Lists;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import io.dropwizard.jackson.JsonSnakeCase;
import lombok.Data;

/**
 * Created by shridhar.hegde on 14/06/17.
 */
@Data
@JsonSnakeCase
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDetails {

  private ProductCompositeResponse productCompositeResponse;

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  @JsonSnakeCase
  public static class ProductCompositeResponse{

    private List<Items> items = Lists.newArrayList();
  }

  @Data
  @JsonSnakeCase
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Items{
    private String generalDescription;
  }
}
