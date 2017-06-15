package action;

import com.google.inject.Inject;

import javax.ws.rs.core.Response;

import client.TargetClient;
import dto.PriceDetails;
import dto.ProductResponse;

/**
 * Created by shridhar.hegde on 15/06/17.
 */
public class UpdatePriceDetailsAction implements MyRetailAction<Response> {

  private ProductResponse productDetails;
  private String productId;
  private final TargetClient targetClient;

  @Inject
  public UpdatePriceDetailsAction(TargetClient targetClient) {
    this.targetClient = targetClient;
  }

  @Override
  public Response invoke() {
    PriceDetails priceDetails = new PriceDetails();
    priceDetails.setCurrentPrice(productDetails.getCurrentPrice());
    return targetClient.updatePrice(productId,priceDetails);
  }

  public UpdatePriceDetailsAction withRequest(ProductResponse productDetails){
    this.productDetails = productDetails;
    return this;
  }

  public UpdatePriceDetailsAction withProductId(String productId){
    this.productId = productId;
    return this;
  }
}
