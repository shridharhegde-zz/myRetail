package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

  private String name;

  private CurrentPrice currentPrice;

  @Data
  @JsonSnakeCase
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class CurrentPrice{
    private float value;
    private String currencyCode;
  }
}
