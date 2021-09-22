package lj.springcloud.sample.getaway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * 启动类
 *
 * @author lijian
 * @date 2021-08-03 17:51
 */
@SpringBootApplication(scanBasePackages = "lj.springcloud.*")
@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
