package flyway.demo;

import org.jdbi.v3.core.Jdbi;

public class App {
  private Jdbi jdbi;

  App(Jdbi jdbi) {
    this.jdbi = jdbi;
  }

  void transfer(String fromUser, String toUser, Integer amount) {
    jdbi.withHandle(
        handle -> handle.execute("SELECT bank.TRANSFER(?, ?, ?)", fromUser, toUser, amount));
  }

  public static void main(String[] args) {
    String fromUser = args[0];
    String toUser = args[1];
    Integer amount = Integer.parseInt(args[2]);

    Jdbi jdbi = JdbiFactory.create();

    App app = new App(jdbi);
    app.transfer(fromUser, toUser, amount);
  }
}
