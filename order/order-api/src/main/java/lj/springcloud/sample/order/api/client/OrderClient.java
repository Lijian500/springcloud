package lj.springcloud.sample.order.api.client;

import lj.springcloud.sample.common.domian.Result;
import lj.springcloud.sample.order.api.fallback.OrderClientFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lijian
 * @date 2021-08-05 09:33
 */
@FeignClient(value = "order", path = "test", fallbackFactory = OrderClientFallBack.class)
public interface OrderClient {

	/**
	 * 获取订单
	 *
	 * @param orderId 订单id
	 * @return 订单信息
	 */
	@GetMapping("/{orderId}")
	Result<?> getOrder(@PathVariable String orderId);
}
