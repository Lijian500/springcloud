package lj.springcloud.sample.accountapi.api.client;

import lj.springcloud.sample.common.domian.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lijian
 * @date 2021-08-03 17:25
 */
@FeignClient(value = "account", path = "token")
public interface TokenClient {

    @GetMapping("/ping")
    Result<String> ping(@Validated String name);
}
