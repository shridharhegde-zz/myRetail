package client;

import com.netflix.hystrix.exception.HystrixBadRequestException;

import javax.ws.rs.core.Response;

import static java.lang.String.format;

/**
 * Created by shridhar.hegde on 14/06/17.
 */
public interface BaseClient {

  default void checkResponse(Response response) {
    int status = response.getStatus();
    if (status >= 400 && status < 500) {
      throw new HystrixBadRequestException(format("Request failed with status: %s. Response: %s",
          status, response.readEntity(String.class)));
    } else if (status > 500) {
      response.close();
      throw new HystrixBadRequestException(format("Request failed with status: %s", status));
    }
  }
}
