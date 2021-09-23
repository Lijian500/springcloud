package lj.springcloud.sample.account.domian.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author lijian
 * @date 2021-09-23 17:40
 */
@Data
public class UserSearchDTO {

	private String keyword;

	@NotNull(message = "开始时间不能为空")
	private LocalDateTime startTime;

	@NotNull(message = "结束时间不能为空")
	private LocalDateTime endTime;

}
