package lj.springcloud.sample.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lj.springcloud.sample.account.domian.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lijian
 * @date 2021-09-23 17:10
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

