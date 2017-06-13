import com.hubspot.dropwizard.guice.GuiceBundle;

import config.ManagerConfiguration;
import config.ManagerModule;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by shridhar.hegde on 13/06/17.
 */
@Slf4j
public class ManagerApplication extends Application<ManagerConfiguration> {

  public static void main(String[] args) throws Exception {
    new ManagerApplication().run(args);
  }

  @Override
  public String getName() {
    return "myRetail";
  }

  @Override
  public void initialize(Bootstrap<ManagerConfiguration> bootstrap) {
    GuiceBundle<ManagerConfiguration> guiceBundle =
        GuiceBundle.<ManagerConfiguration>newBuilder()
            .addModule(new ManagerModule())
            .setConfigClass(ManagerConfiguration.class)
            .build();
    bootstrap.addBundle(guiceBundle);
  }

  @Override
  public void run(ManagerConfiguration configuration, Environment environment)
      throws Exception {
    log.info("Application has started!!");
  }
}
