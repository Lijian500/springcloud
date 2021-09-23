package lj.springcloud.sample.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 启动类
 *
 * @author lijian
 * @date 2021-08-03 17:51
 */
@EnableFeignClients(basePackages = "lj.springcloud.sample")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "lj.springcloud.*")
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@RestController
	class TestController {

		@GetMapping("demo")
		public String test() {
			return "fasdfa";
		}
	}
}
