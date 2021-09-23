package lj.springcloud.sample.account.contorller;

import lj.springcloud.sample.account.api.client.TokenClient;
import lj.springcloud.sample.account.domian.dto.UpdateDTO;
import lj.springcloud.sample.account.domian.dto.UserSearchDTO;
import lj.springcloud.sample.account.domian.model.User;
import lj.springcloud.sample.account.domian.vo.UpdateVO;
import lj.springcloud.sample.account.service.UserService;
import lj.springcloud.sample.common.domian.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author lijian
 * @date 2021-08-03 17:51
 */
@RestController
@RequestMapping("/token")
@RefreshScope
@Slf4j
public class TokenController implements TokenClient {

	@Value("${server.port}")
	private Integer port;

	@Value("${demo.test}")
	private String text;

	@Value("${redis.port}")
	private String redisPort;

	@Value("${mysql.port}")
	private String mysqlPort;

	@Autowired
	private UserService userService;

	@Override
	public Result<String> ping(String name) {
		String text = "text: " + this.text + ", redisPort: " + redisPort + ", mysqlPort: " + mysqlPort;
		return Result.success(text);
	}

	@Override
	public Result<UpdateVO> update(UpdateDTO updateDTO) {
		return Result.success(new UpdateVO(889, updateDTO.getName()));
	}

	@Override
	public Result<User> getUser(Integer userId) {
		return Result.success(userService.getUser(userId));
	}

	@Override
	public Result<List<User>> listUser(UserSearchDTO userSearchDTO) {
		return Result.success(userService.listUser(userSearchDTO));
	}


	public static void main(String[] args) throws Exception {
		Result<String> s = Result.success("sss");
		String s1 = s.get(s);

		Result.INIT.get(s);


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
//		int[] array = {4,5};
//		Result<int[]> success = Result.success(array);
//		System.out.println(Arrays.toString(success.getNullable()));
//		System.out.println(Arrays.toString(success.get()));

		Arrays.asList(new HashMap<>(), new TreeMap<>(), new LinkedHashMap<>(), new WeakHashMap<>(),
				new Hashtable<>(), new ConcurrentHashMap<>())
				.forEach(TokenController::isMap);

		Arrays.asList(new ArrayList<>(), new HashSet<>(), new LinkedList<>(), new HashSet<>())
				.forEach(TokenController::isCollection);

	}

	private static <T> void isCollection(T t) {
		System.out.println(t instanceof Collection);
	}

	private static <T> void isMap(T t) {
		System.out.println(t instanceof Map);
	}
}
