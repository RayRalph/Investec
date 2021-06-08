package evaluation.candidate.test;

import evaluation.candidate.restapi2.configuration.HomeController;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private HomeController homeController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(homeController).isNotNull();
    }
}