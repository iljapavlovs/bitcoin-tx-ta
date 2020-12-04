import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import client.BitcoinClient;
import client.BitcoinClientFactory;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BitcoinTransactionTest {

  private static BitcoinClient nodeClientA;
  private static BitcoinClient nodeClientB;

  @BeforeAll
  public static void setup() throws MalformedURLException {
    nodeClientA = new BitcoinClientFactory().createClient(
        "nodeA",
        "secretpassword1",
        "127.0.0.1",
        "18443");

    nodeClientB = new BitcoinClientFactory().createClient(
        "nodeB",
        "secretpassword2",
        "127.0.0.1",
        "28443");
  }

  @Test
  public void verifyTransaction() {

    // GIVEN
    final var addressA = nodeClientA.getNewAddress();
    nodeClientA.generateToAddress(101, addressA);
    final var addressB = nodeClientB.getNewAddress();

    // WHEN
    nodeClientA.sendToAddress(addressB, new BigDecimal("39.99999991"));
    nodeClientA.generateToAddress(1, addressA);

    // THEN
    final BigDecimal balanceA = nodeClientA.getBalance();
    final BigDecimal balanceB = nodeClientB.getBalance();
    assertThat(balanceA).isEqualTo("59.99997189");
    assertThat(balanceB).isEqualTo("39.99999991");
  }

}
