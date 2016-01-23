package cmi.bdo.oauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BdoOauthApplication {

    private static final Logger log = LoggerFactory.getLogger(BdoOauthApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication bdoLrs = new SpringApplication(BdoOauthApplication.class);
        bdoLrs.setBannerMode(Banner.Mode.CONSOLE);
        bdoLrs.setRegisterShutdownHook(false);
        Environment environment = bdoLrs.run(args).getEnvironment();
        log.info("Access URLs:"
                        + "\n"
                        + "----------------------------------------------------------"
                        + "\n\t"
                        + "Local: \t\thttp://127.0.0.1:{}"
                        + "\n\t"
                        + "External: \thttp://{}:{}"
                        + "\n"
                        + "----------------------------------------------------------",
                environment.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                environment.getProperty("server.port"));

    }
}