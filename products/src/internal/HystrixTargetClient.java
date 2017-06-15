package internal;

import com.google.inject.Inject;
import com.google.inject.Provider;

import javax.ws.rs.core.Response;

import client.TargetClient;
import dto.PriceDetails;
import dto.ProductDetails;

/**
 * Created by shridhar.hegde on 14/06/17.
 */
public class HystrixTargetClient implements TargetClient {

  private final Provider<GetProductNameCommand> getProductNameCommandProvider;
  private final Provider<GetPriceCommand> getPriceCommandProvider;
  private final Provider<UpdatePriceDetailCommand> updatePriceDetailCommandProvider;
  private final String key = "43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz";
  private final String idType = "TCIN";
  private final String fields = "descriptions";


  @Inject
  public HystrixTargetClient(Provider<GetProductNameCommand> getProductNameCommandProvider,
                             Provider<GetPriceCommand> getPriceCommandProvider,
                             Provider<UpdatePriceDetailCommand> updatePriceDetailCommandProvider) {
    this.getProductNameCommandProvider = getProductNameCommandProvider;
    this.getPriceCommandProvider = getPriceCommandProvider;
    this.updatePriceDetailCommandProvider = updatePriceDetailCommandProvider;
  }

  @Override
  public ProductDetails getProductName(String productId) {
    return getProductNameCommandProvider.get().productId(productId)
        .fields(fields)
        .idType(idType)
        .key(key)
        .execute();
  }

  @Override
  public PriceDetails getPrice(String productId) {
    return getPriceCommandProvider.get().productId(productId).execute();
  }

  @Override
  public Response updatePrice(String productId, PriceDetails priceDetails) {
    return updatePriceDetailCommandProvider.get().productId(productId).priceDetails(priceDetails)
        .execute();
  }
}
