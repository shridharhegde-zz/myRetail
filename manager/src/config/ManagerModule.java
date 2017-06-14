package config;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.setup.Environment;

/**
 * Created by shridhar.hegde on 13/06/17.
 */
public class ManagerModule extends AbstractModule {

  @Override
  protected void configure() {

  }

  @Provides
  @Singleton
  ObjectMapper providesObjectMapper(Provider<Environment> environmentProvider) {
    return environmentProvider.get().getObjectMapper();
  }

  @Provides
  TargetClientConfiguration providesSfsClientConfiguration(Provider<ManagerConfiguration> provider) {
    return provider.get().getTargetClientConfiguration();
  }

  @Provides
  @Singleton
  public JerseyClientConfiguration providesClientConfiguration(
      Provider<ManagerConfiguration> configuration) {
    return configuration.get().getClientConfiguration();
  }
}
