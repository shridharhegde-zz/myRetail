package action;

import com.google.inject.Inject;

import java.util.stream.Collectors;

import client.TargetClient;
import dto.GetProductResponse;
import dto.ProductDetails;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by shridhar.hegde on 14/06/17.
 */
@Slf4j
public class GetProductDetailsAction implements MyRetailAction<GetProductResponse> {

  private String productId;

  private final TargetClient targetClient;

  @Inject
  public GetProductDetailsAction(TargetClient targetClient) {
    this.targetClient = targetClient;
  }


  @Override
  public GetProductResponse invoke() {
    ProductDetails productDetails = targetClient.getProductName(productId);
    GetProductResponse getProductResponse = new GetProductResponse();
    getProductResponse.setCurrentPrice(targetClient.getPrice(productId).getCurrentPrice());
    getProductResponse.setName(productDetails.getProductCompositeResponse().getItems().stream()
        .map(ProductDetails.Items::getGeneralDescription).collect(Collectors.toList()));
    getProductResponse.setId(Integer.valueOf(productId));
    return getProductResponse;
  }

  public GetProductDetailsAction withProductId(String productId) {
    this.productId = productId;
    return this;
  }
}
