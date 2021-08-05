package lj.springcloud.sample.order.contorller;

import com.alibaba.fastjson.JSON;
import lj.springcloud.sample.account.api.client.TokenClient;
import lj.springcloud.sample.common.domian.Result;
import lj.springcloud.sample.order.api.client.OrderClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lijian
 * @date 2021-08-05 09:32
 */
@RestController
@RequestMapping("test")
@Slf4j
public class OrderController implements OrderClient {


	@Autowired
	private TokenClient tokenClient;

	@Override
	public Result<?> getOrder(String orderId) {
		Result<String> ping = tokenClient.ping(orderId);
		log.info("調用返回信息：【{}】", JSON.toJSONString(ping));
		return Result.success(orderId);
	}

	@GetMapping("/ping")
	public Result<?> test() {
		return Result.success("success");
	}
}
