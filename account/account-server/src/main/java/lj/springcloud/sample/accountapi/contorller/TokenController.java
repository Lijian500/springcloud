package lj.springcloud.sample.accountapi.contorller;

import lj.springcloud.sample.accountapi.api.client.TokenClient;
import lj.springcloud.sample.common.domian.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lijian
 * @date 2021-08-03 17:51
 */
@RestController
@RequestMapping("/token")
public class TokenController implements TokenClient {


    public Result<String> ping(String name) {
        return Result.success("success ping");
    }
}
