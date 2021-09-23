package lj.springcloud.sample.common.domian;

import lj.springcloud.sample.common.constant.ResultCodeConstant;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.*;

@Data
public class Result<T> {

	/**
	 * 不需要返回 resultData 时，使用该常量返回，减少JVM创建对象的时间
	 */
	public static final Result SUCCESS = of(ResultCodeConstant.SUCCESS, "操作成功");

	public static final Result INIT = new Result();

	//  private static final Logger log = LoggerFactory.getLogger(Result.class);

	private int resultCode;
	private String resultMsg;
	private T resultData;

	/**
	 * 推荐使用静态方法 success() failed() of() 创建对象
	 */
	@Deprecated
	public Result() {
	}

	/**
	 * 推荐使用静态方法 success() failed() of() 创建对象
	 */
	@Deprecated
	public Result(T resultData) {
		this(SUCCESS.resultCode, SUCCESS.resultMsg, resultData);
	}

	/**
	 * 推荐使用静态方法 success() failed() of() 创建对象
	 */
	@Deprecated
	public Result(int resultCode, String resultMsg, T resultData) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
		this.resultData = resultData;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public T getResultData() {
		return resultData;
	}

	public void setResultData(T resultData) {
		this.resultData = resultData;
	}

	@Override
	public String toString() {
		return "Result{" +
				"resultCode=" + resultCode +
				", resultMsg='" + resultMsg + '\'' +
				", resultData=" + resultData +
				'}';
	}

	/**
	 * 通过静态方法创建对象，推荐使用
	 */
	public static <T> Result<T> of(int resultCode, String resultMsg) {
		return of(resultCode, resultMsg, null);
	}

	/**
	 * 通过静态方法创建对象，推荐使用
	 */
	public static <T> Result<T> of(int resultCode, String resultMsg, T resultData) {
		if (ResultCodeConstant.SUCCESS != resultCode) {
			//   log.info("code：{}，resultMsg={}，errorData={}", resultCode, resultMsg, resultData);
		}
		return new Result<>(resultCode, resultMsg, resultData);
	}

	/**
	 * 成功时调用
	 */
	public static <T> Result<T> success(T resultData) {
		return of(SUCCESS.resultCode, SUCCESS.resultMsg, resultData);
	}


	/**
	 * 失败时调用
	 */
	public static <T> Result<T> failed(String resultMsg) {
		return of(ResultCodeConstant.FAIL_MSG, resultMsg);
	}


	/**
	 * 是否成功
	 *
	 * @return {@code true} 成功
	 */
	public boolean isSuccess() {
		return Objects.equals(this.getResultCode(), ResultCodeConstant.SUCCESS);
	}

//    /**
//     * 检查 Result 是否不为null
//     *
//     * @param result Result
//     * @throws BusinessException e
//     */
//    public static void checkResultNotNull(Result result) throws BusinessException {
//        if (Objects.isNull(result)) {
//            throw new BusinessException(ResultCodeConstant.FAIL_MSG, new String[]{"返回结果为空"});
//        }
//        log.info("代理返回结果为：{}", JSONObject.toJSONString(result));
//    }
//
//    /**
//     * 检查 Result 是否为成功操作
//     *
//     * @param result Result
//     * @throws BusinessException e
//     */
//    public static void checkResultSuccess(Result result) throws BusinessException {
//        checkResultNotNull(result);
//        // 异常
//        if (!Objects.equals(result.getResultCode(), ResultCodeConstant.SUCCESS)) {
//            throw new BusinessException(ResultCodeConstant.FAIL_MSG, new String[]{result.getResultMsg()});
//        }
//    }
//
//    /**
//     * 检查 ResultData 是否不为空 并返回 ResultData
//     *
//     * @param result Result
//     * @return ResultData
//     * @throws BusinessException e
//     */
//    public static T checkResultDataNotNull(Result result) throws BusinessException {
//        checkResultNotNull(result);
//        Object resultData = result.getResultData();
//        if (Objects.isNull(resultData)) {
//            String resultMsg = result.getResultMsg();
//
//            throw new BusinessException(ResultCode.FAIL_MSG,
//                    new String[]{StringUtils.isEmpty(resultMsg) ? "返回结果Data为空" : resultMsg});
//        }
//        return result.getResultData();
//    }
//
//    /**
//     * 检查 ResultData 是否不为空 并返回 JSONObject
//     *
//     * @param result Result
//     * @return JSONObject
//     * @throws BusinessException e
//     */
//    public static JSONObject checkSuccessAndDataNotNull(Result result) throws BusinessException {
//        checkResultSuccess(result);
//        Object data = checkResultDataNotNull(result);
//        return JSONObject.parseObject(JSONObject.toJSONString(data, SerializerFeature.WriteMapNullValue));
//    }
//
//    /**
//     * 检查 ResultData 是否不为空 并返回 JSONArray
//     *
//     * @param result Result
//     * @return JSONArray
//     * @throws BusinessException e
//     */
//    public static JSONArray checkSuccessAndDataNotNullToArray(Result result) throws BusinessException {
//        checkResultSuccess(result);
//        Object data = checkResultDataNotNull(result);
//        return JSONArray.parseArray(JSONObject.toJSONString(data, SerializerFeature.WriteMapNullValue));
//    }
//
//    /**
//     * 解析 result 并返回 JSONArray
//     *
//     * @param result Result
//     * @return JSONArray
//     */
//    public static JSONArray parseArray(Result result) {
//        if (Objects.isNull(result)) {
//            return null;
//        }
//        if (Objects.isNull(result.getResultData())) {
//            return null;
//        }
//        return JSONArray.parseArray(JSONObject.toJSONString(result.getResultData(), SerializerFeature.WriteMapNullValue));
//    }

	/**
	 * 方法返回值
	 * resultData 为null，或者当resultData为
	 * String, 空串
	 * Collections 空集合
	 * Map        空map
	 * 都会抛异常
	 * <p>
	 * 如果接口返回值接受resultData=null的场景，请调用 {@linkplain #getNullable()}
	 *
	 * @return T 泛型对象
	 * @throws Exception
	 */
	private T get() throws Exception {
		if (!isSuccess()) {
			throw new Exception("远程调用失败");
		}
		if (resultData == null) {
			throw new Exception("方法返回结果为null");
		}
		if (resultData instanceof String) {
			if (StringUtils.isBlank((String) resultData)) {
				throw new Exception("方法返回为空字符串");
			}
		} else if (resultData instanceof Collection) {
			if (CollectionUtils.isEmpty((Collection<?>) resultData)) {
				throw new Exception("方法返回为空集合");
			}
		} else if (resultData instanceof Map) {
			if (CollectionUtils.isEmpty((Map) resultData)) {
				throw new Exception("方法返回为空map");
			}
		} else if (resultData.getClass().isArray()) {
			if (Array.getLength(resultData) == 0) {
				throw new Exception("方法返回空数组");
			}
		}

		return getResultData();
	}


	/**
	 * 返回值可能为null, 集合、map、数组对象没有元素
	 *
	 * 如果不接受返回值为null的情况，请调用{@linkplain #get()}
	 * @return
	 * @throws Exception
	 */
	private T getNullable() throws Exception {
		if (!isSuccess()) {
			throw new Exception("远程调用失败");
		}
		return getResultData();
	}


	/**
	 * result对象可能为null时，用该方法。
	 *
	 * @param result 調用返回对象
	 * @return T
	 * @throws Exception e
	 */
	public T  get(Result<T> result) throws Exception {
		if (result == null) {
			throw new Exception("result为null");
		}

		this.resultCode = result.getResultCode();
		this.resultData = result.getResultData();
		this.resultMsg = result.getResultMsg();

		return get();
	}
}