package oss.akrzelj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import oss.akrzelj.config.RsaKeyConfig;

@EnableConfigurationProperties(RsaKeyConfig.class)
@SpringBootApplication
public class CoolinarkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoolinarkaApplication.class, args);
    }
}