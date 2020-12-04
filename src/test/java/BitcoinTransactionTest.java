import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import client.BitcoinClient;
import client.BitcoinClientFactory;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BitcoinTransactionTest {

  protected static BitcoinClient nodeClientA;

  @BeforeAll
  public static void setup() throws MalformedURLException {
    nodeClientA = new BitcoinClientFactory().createClient(
        "nodeA",
        "secretpassword1",
        "127.0.0.1",
        "18443");
  }

  @Test
  public void verifyTransaction() {

    final BigDecimal balanceA = nodeClientA.getBalance();
    assertThat(balanceA).isEqualTo(new BigDecimal(0));
  }

}
