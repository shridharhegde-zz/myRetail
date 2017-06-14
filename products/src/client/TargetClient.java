package client;

import dto.PriceDetails;
import dto.ProductDetails;

/**
 * Created by shridhar.hegde on 14/06/17.
 */
public interface TargetClient {

  ProductDetails getProductName(String productId);

  PriceDetails getPrice(String productId);
}
