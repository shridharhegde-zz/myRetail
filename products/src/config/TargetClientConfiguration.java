package config;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import lombok.Data;

/**
 * Created by shridhar.hegde on 14/06/17.
 */
@Data
public class TargetClientConfiguration {

  @URL
  @NotEmpty
  private String url;
}
