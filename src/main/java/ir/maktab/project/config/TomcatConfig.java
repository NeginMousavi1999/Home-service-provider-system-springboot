package ir.maktab.project.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Negin Mousavi
 */

@Configuration
public class TomcatConfig {
    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        tomcatServletWebServerFactory.addConnectorCustomizers((Connector connector) -> {
            connector.setProperty("relaxedPathChars", "\"{\\}^`{|}");
            connector.setProperty("relaxedQueryChars", "\"{\\}^`{|}");
        });
        return tomcatServletWebServerFactory;
    }
}
