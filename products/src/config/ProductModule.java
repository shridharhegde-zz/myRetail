package config;

import com.google.inject.AbstractModule;

import resource.ProductResource;

/**
 * Created by shridhar.hegde on 14/06/17.
 */
public class ProductModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ProductResource.class);
  }
}
