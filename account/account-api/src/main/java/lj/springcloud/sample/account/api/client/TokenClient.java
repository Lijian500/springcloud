package lj.springcloud.sample.account.api.client;

import lj.springcloud.sample.account.api.fallback.TokenClientFallBack;
import lj.springcloud.sample.account.domian.dto.UpdateDTO;
import lj.springcloud.sample.account.domian.vo.UpdateVO;
import lj.springcloud.sample.common.domian.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotBlank;

/**
 * @author lijian
 * @date 2021-08-03 17:25
 */
@FeignClient(value = "account", path = "token", fallbackFactory = TokenClientFallBack.class)
public interface TokenClient {

    /**
     * 测试
     *
     * @param name name
     * @return {@link Result}
     */
    @GetMapping("/ping")
    Result<String> ping(@Validated @NotBlank(message = "名称不能为空") String name);

    /**
     * 更新测试
     *
     * @param updateDTO updateDTO
     * @return {@link Result}
     */
    @PostMapping("/update")
    Result<UpdateVO> update(@Validated UpdateDTO updateDTO);
}
