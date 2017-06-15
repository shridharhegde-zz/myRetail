package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.dropwizard.jackson.JsonSnakeCase;
import lombok.Data;

import static dto.ProductResponse.*;

/**
 * Created by shridhar.hegde on 14/06/17.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSnakeCase
public class PriceDetails {

  private CurrentPrice currentPrice;
}
