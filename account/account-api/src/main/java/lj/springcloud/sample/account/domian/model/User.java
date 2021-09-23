package lj.springcloud.sample.account.domian.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * user实体对象
 *
 * @author lijian
 * @date 2021-09-23 17:03
 */
@Data
@TableName("user")
public class User implements Serializable {

	@TableId
	private Integer userId;

	private String name;

	private Integer age;

	private String address;

	private String password;

	private LocalDateTime createTime;

	private LocalDateTime updateTime;
}
