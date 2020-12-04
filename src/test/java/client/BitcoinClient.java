package client;

import com.googlecode.jsonrpc4j.JsonRpcMethod;
import java.math.BigDecimal;
import java.util.List;

public interface BitcoinClient {
  @JsonRpcMethod("getbalance")
  BigDecimal getBalance();

  @JsonRpcMethod("getnewaddress")
  String getNewAddress();

  @JsonRpcMethod("generatetoaddress")
  List<String> generateToAddress(int numberOfBlocks, String address);

  @JsonRpcMethod("sendtoaddress")
  String sendToAddress(String address, BigDecimal amount);
}
