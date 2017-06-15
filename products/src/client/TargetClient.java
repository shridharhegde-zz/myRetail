package client;

import javax.ws.rs.core.Response;

import dto.PriceDetails;
import dto.ProductDetails;

/**
 * Created by shridhar.hegde on 14/06/17.
 */
public interface TargetClient {

  ProductDetails getProductName(String productId);

  PriceDetails getPrice(String productId);

  Response updatePrice(String productId,PriceDetails priceDetails);
}
