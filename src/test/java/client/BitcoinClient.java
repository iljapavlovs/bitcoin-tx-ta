package client;

import com.googlecode.jsonrpc4j.JsonRpcMethod;
import java.math.BigDecimal;

public interface BitcoinClient {

  @JsonRpcMethod("getbalance")
  BigDecimal getBalance();
}
