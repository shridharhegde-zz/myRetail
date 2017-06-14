package config;

/**
 * Created by shridhar.hegde on 13/06/17.
 */

import com.bendb.dropwizard.redis.JedisFactory;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import lombok.Data;

@Data
public class ManagerConfiguration extends Configuration {

  @Valid
  @NotNull
  private JerseyClientConfiguration clientConfiguration;

  @NotNull
  @JsonProperty
  private JedisFactory redis;

  public JedisFactory getJedisFactory() {
    return redis;
  }
}
