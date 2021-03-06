package lj.springcloud.sample.common.handler;

import com.netflix.client.ClientException;
import lj.springcloud.sample.common.domian.Result;
import lj.springcloud.sample.common.execption.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lijian
 * @date 2021-09-22 16:08
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlers {

	@ExceptionHandler(ClientException.class)
	public Result<?> handler(ClientException e) {
		e.printStackTrace();
		log.info(e.getMessage());
		return Result.failed(e.getMessage());
	}

	@ExceptionHandler(BusinessException.class)
	public Result<?> handler(BusinessException e) {
		e.printStackTrace();
		log.info(e.getMessage());
		return Result.failed(e.getMessage());
	}

	@ExceptionHandler(BindException.class)
	public Result<?> handler(BindException e) {
		e.printStackTrace();
		log.info(e.getMessage());
		return Result.failed(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
	}

	@ExceptionHandler(Exception.class)
	public Result<?> handler(Exception e) {
		e.printStackTrace();
		log.info(e.getMessage());
		return Result.failed(e.getMessage());
	}
}
