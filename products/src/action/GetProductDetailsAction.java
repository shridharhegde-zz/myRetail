package action;

import com.google.inject.Inject;

import java.util.stream.Collectors;

import client.TargetClient;
import dto.ProductResponse;
import dto.ProductDetails;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by shridhar.hegde on 14/06/17.
 */
@Slf4j
public class GetProductDetailsAction implements MyRetailAction<ProductResponse> {

  private String productId;

  private final TargetClient targetClient;

  @Inject
  public GetProductDetailsAction(TargetClient targetClient) {
    this.targetClient = targetClient;
  }


  @Override
  public ProductResponse invoke() {
    ProductDetails productDetails = targetClient.getProductName(productId);
    ProductResponse productResponse = new ProductResponse();
    productResponse.setCurrentPrice(targetClient.getPrice(productId).getCurrentPrice());
    productResponse.setName(productDetails.getProductCompositeResponse().getItems().stream()
        .map(ProductDetails.Items::getGeneralDescription).collect(Collectors.toList()));
    productResponse.setId(Integer.valueOf(productId));
    return productResponse;
  }

  public GetProductDetailsAction withProductId(String productId) {
    this.productId = productId;
    return this;
  }
}
