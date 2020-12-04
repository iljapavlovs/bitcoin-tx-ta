package tests;

import static java.lang.Thread.sleep;
import static org.awaitility.Awaitility.await;

import client.BitcoinClient;
import client.BitcoinClientFactory;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.google.common.io.Resources;


@Testcontainers
public class TestBase {
  protected BitcoinClient nodeClientA;
  protected BitcoinClient nodeClientB;

  @Container
  public DockerComposeContainer ECOSYSTEM =
      new DockerComposeContainer(locateInClasspath("docker/docker-compose.yml"))
          .withLocalCompose(true)
          .withPull(true)
          .withTailChildContainers(true);

  @BeforeEach
  public void setup() throws MalformedURLException, InterruptedException {

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

    waitUntilEcosystemIsUpAnRunning();
  }

  private File locateInClasspath(String pathToFile) {
    var resource = Resources.getResource(pathToFile);
    try {
      return new File(resource.toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  private void waitUntilEcosystemIsUpAnRunning() throws InterruptedException {

    // todo - couldn't find a proper way to verify whether nodes are up and running and connected
    sleep(5000);
    await()
        .atMost(10, TimeUnit.SECONDS)
        .until(() -> nodeClientA.getConnectionCount() == 2);
  }

}
