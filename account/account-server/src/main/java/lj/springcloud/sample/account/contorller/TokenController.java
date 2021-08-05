package lj.springcloud.sample.account.contorller;

import lj.springcloud.sample.account.api.client.TokenClient;
import lj.springcloud.sample.account.domian.dto.UpdateDTO;
import lj.springcloud.sample.account.domian.vo.UpdateVO;
import lj.springcloud.sample.common.domian.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lijian
 * @date 2021-08-03 17:51
 */
@RestController
@RequestMapping("/token")
@Slf4j
public class TokenController implements TokenClient {

	@Value("${server.port}")
	private Integer port;

	@Override
	public Result<String> ping(String name) {
		log.info("端口：{}", port);
		return Result.success("success ping :" + name);
	}

	@Override
	public Result<UpdateVO> update(UpdateDTO updateDTO) {
		return Result.success(new UpdateVO(889, updateDTO.getName()));
	}
}
