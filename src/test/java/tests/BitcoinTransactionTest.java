package tests;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BitcoinTransactionTest extends TestBase {

  @ParameterizedTest(name = "should perform transaction successfully")
  @CsvSource({
      "39.99999991,59.99997189",
      "11.00000001,88.99997179"
  })
  public void verifyTransaction(BigDecimal amountToSend, BigDecimal resultingAmount) {

    // GIVEN
    final var addressA = nodeClientA.getNewAddress();
    nodeClientA.generateToAddress(101, addressA);
    final var addressB = nodeClientB.getNewAddress();

    // WHEN
    nodeClientA.sendToAddress(addressB, amountToSend);
    nodeClientA.generateToAddress(1, addressA);

    // THEN
    final BigDecimal balanceA = nodeClientA.getBalance();
    final BigDecimal balanceB = nodeClientB.getBalance();
    assertThat(balanceA).isEqualTo(resultingAmount);
    assertThat(balanceB).isEqualTo(amountToSend);
  }

}
