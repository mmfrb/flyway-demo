package flyway.demo;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.jdbi.v3.core.Jdbi;

class JdbiFactory {

  static Jdbi create() {
    Config config = ConfigFactory.load();

    return Jdbi.create(
        String.format(
            "jdbc:postgresql://%s:%d/postgres?user=postgres",
            config.getString("database.host"), config.getInt("database.port")));
  }
}
