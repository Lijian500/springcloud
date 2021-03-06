package lj.springcloud.sample.getaway.api.fallback;

import feign.hystrix.FallbackFactory;
import lj.springcloud.sample.getaway.api.client.TokenClient;
import lj.springcloud.sample.getaway.domian.dto.UpdateDTO;
import lj.springcloud.sample.getaway.domian.vo.UpdateVO;
import lj.springcloud.sample.common.domian.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lijian
 * @date 2021-08-04 09:11
 */
@Component
@Slf4j
public class TokenClientFallBack implements FallbackFactory<TokenClient> {


    @Override
    public TokenClient create(Throwable cause) {
        log.info("调用失败, cause:[{}]", cause.getMessage());

        return new TokenClient() {
            @Override
            public Result<String> ping(String name) {
                return Result.failed("服务降级，请稍后再试活联系管理员");
            }

            @Override
            public Result<UpdateVO> update(UpdateDTO updateDTO) {
                return Result.failed("服务降级，请稍后再试活联系管理员");
            }
        };
    }
}
