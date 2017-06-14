package config;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import org.glassfish.jersey.logging.LoggingFeature;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;

import client.TargetClient;
import internal.HystrixTargetClient;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.setup.Environment;

/**
 * Created by shridhar.hegde on 14/06/17.
 */
public class TargetClientModule extends AbstractModule {

  @Override
  protected void configure() {
   bind(TargetClient.class).to(HystrixTargetClient.class);
  }

  @Provides
  @Singleton
  public Client providesClient(Provider<JerseyClientConfiguration> clientConfiguration,
                               Provider<Environment> environment) {
    Client client = new JerseyClientBuilder(environment.get())
        .using(clientConfiguration.get())
        .build("client");

    client.register(new LoggingFeature(Logger.getLogger(LoggingFeature.class.getName()),
        Level.INFO, LoggingFeature.Verbosity.PAYLOAD_TEXT,
        5 * 1024));
    return client;
  }
}
