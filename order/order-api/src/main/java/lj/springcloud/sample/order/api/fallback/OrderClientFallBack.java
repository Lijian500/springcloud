package lj.springcloud.sample.order.api.fallback;

import feign.hystrix.FallbackFactory;
import lj.springcloud.sample.common.domian.Result;
import lj.springcloud.sample.order.api.client.OrderClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lijian
 * @date 2021-08-05 09:34
 */
@Component
@Slf4j
public class OrderClientFallBack implements FallbackFactory<OrderClient> {

	@Override
	public OrderClient create(Throwable cause) {
		log.info("調用失败，cause: [{}]", cause.getMessage());

		return new OrderClient() {

			@Override
			public Result<?> getOrder(String orderId) {
				return Result.failed("服务降级，请重试或联系管理员");
			}
		};
	}
}
