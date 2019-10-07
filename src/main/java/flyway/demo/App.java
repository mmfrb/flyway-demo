package flyway.demo;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.jdbi.v3.core.Jdbi;

public class App {
    private Jdbi jdbi;

    public App(String dbHost, int dbPort) {
        this.jdbi = Jdbi.create(
          String.format("jdbc:postgresql://%s:%d/postgres?user=postgres", dbHost, dbPort));
    }

    private void transfer(String fromUser, String toUser, Integer amount) {
        jdbi.withHandle(handle ->
          handle.execute("SELECT bank.TRANSFER(?, ?, ?)", fromUser, toUser, amount));
    }

    public static void main(String[] args) {
        String fromUser = args[0];
        String toUser = args[1];
        Integer amount = Integer.parseInt(args[2]);

        Config config = ConfigFactory.load();
        App app = new App(config.getString("database.host"), config.getInt("database.port"));

        app.transfer(fromUser, toUser, amount);
    }
}
