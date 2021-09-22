package lj.springcloud.sample.common.execption;


/**
 * @author lijian
 * @date 2021-09-22 16:09
 */
public class BusinessException extends RuntimeException {

	public BusinessException(String message) {
		super(message);
	}
}
