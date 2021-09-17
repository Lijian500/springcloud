package lj.springcloud.sample.account.contorller;

import lj.springcloud.sample.account.api.client.TokenClient;
import lj.springcloud.sample.account.domian.dto.UpdateDTO;
import lj.springcloud.sample.account.domian.vo.UpdateVO;
import lj.springcloud.sample.common.domian.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.applet.Main;

import java.lang.reflect.Array;
import java.util.*;

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



	public static void main(String[] args) throws Exception{
		//Result<String> result = Result.success("13353");
		//Result<List<Object>> result1 = Result.success(Collections.emptyList());
		//Result<Set<Object>> result2 = Result.success(Collections.emptySet());
		//Result<Map> result3 = Result.success(Collections.EMPTY_MAP);
		//Result<Map> result4 = Result.success(new TreeMap());
		//System.out.println("result:" + result.get());
		//System.out.println(result1.get());
		//System.out.println("result2:" + result2.get());
		//System.out.println("result3:" + result3.get());
		//System.out.println("result4:" + result4.getNullable());
		//int[] array = {2,3};
		Integer[] array = {6, 1, 9, 3, 7};
		Result<Integer[]> success = Result.success(array);

//		Integer[] resultData = success.getResultData();
//		if (resultData.getClass().isArray()) {
//			Class<?> componentType = resultData.getClass().getComponentType();
//			Integer[] integer = resultData.getClass().newInstance();
//			System.out.println("这个长度 = " + integer.length);
//			System.out.println(componentType);
//			System.out.println("length = " + Array.getLength(array.getClass().getComponentType()));
//			System.out.println(Array.getLength(componentType));
//		}

		//System.out.println(Arrays.toString(success.getNullable()));
		 System.out.println(success.get());

	}
}
