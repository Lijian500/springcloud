package lj.springcloud.sample.account.contorller;

import lj.springcloud.sample.getaway.api.client.TokenClient;
import lj.springcloud.sample.getaway.domian.dto.UpdateDTO;
import lj.springcloud.sample.getaway.domian.vo.UpdateVO;
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


    @Override
    public Result<String> ping(String name) {
        return Result.success("success ping :" + name);
    }


    @Override
    public Result<UpdateVO> update(UpdateDTO updateDTO) {
        return Result.success(new UpdateVO(889, updateDTO.getName()));
    }
}
