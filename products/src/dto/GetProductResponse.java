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
public class GetProductResponse {

  private int id;

  private List<String> name = Lists.newArrayList();

  private CurrentPrice currentPrice;

  @Data
  @JsonSnakeCase
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class CurrentPrice{
    private float value;
    private String currencyCode;
  }
}
