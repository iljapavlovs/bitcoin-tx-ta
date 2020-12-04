package client;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Map;

public class BitcoinClientFactory {

  public BitcoinClient createClient(
      String user,
      String password,
      String host,
      String port
  ) throws MalformedURLException {
    JsonRpcHttpClient jsonRpcHttpClient = new JsonRpcHttpClient(
        new URL("http://%s:%s".formatted(host, port)),
        Map.of("Authorization", computeBasicAuth(user, password)));

    return ProxyUtil.createClientProxy(
        BitcoinClientFactory.class.getClassLoader(),
        BitcoinClient.class,
        jsonRpcHttpClient
    );
  }

  private String computeBasicAuth(String user, String password) {
    var auth = user + ":" + password;
    byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
    return "Basic " + new String(encodedAuth);
  }

}
