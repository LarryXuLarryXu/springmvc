package test.java.com.jetsly.webapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import main.java.com.jetsly.webapp.Application;
import static org.junit.Assert.assertEquals;



@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port:0")
@DirtiesContext
public class ApplicationTests extends AbstractTestNGSpringContextTests {

  @Value("${local.server.port}")
	private int port;

  @Test
  public void testHome() throws Exception {
      ResponseEntity<String> entity = new TestRestTemplate().getForEntity(
          "http://localhost:" + this.port, String.class);
      assertEquals(HttpStatus.OK, entity.getStatusCode());
      assertEquals("Hello World", entity.getBody());
    }

}
