package top.luoyuanxiang.api;

import org.dromara.x.file.storage.spring.EnableFileStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Thrive xServer 应用程序
 *
 * @author luoyuanxiang
 */
@EnableFileStorage
@EnableScheduling
@SpringBootApplication
public class ThriveXServerApplication {
    public static ConfigurableApplicationContext context;
    public static void main(String[] args) {
        context = SpringApplication.run(ThriveXServerApplication.class, args);
    }

}
