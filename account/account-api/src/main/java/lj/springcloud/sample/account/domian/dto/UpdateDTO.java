package lj.springcloud.sample.account.domian.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lijian
 * @date 2021-08-04 09:26
 */
@Data
public class UpdateDTO {

    @NotNull(message = "id不能为空")
    private String id;

    @NotBlank(message = "name不能为空")
    private String name;

    @NotNull(message = "年龄不能为空")
    private Integer age;
}
