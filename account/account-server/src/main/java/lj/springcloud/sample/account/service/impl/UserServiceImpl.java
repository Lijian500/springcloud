package lj.springcloud.sample.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lj.springcloud.sample.account.domian.dto.UserSearchDTO;
import lj.springcloud.sample.account.domian.model.User;
import lj.springcloud.sample.account.mapper.UserMapper;
import lj.springcloud.sample.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lijian
 * @date 2021-09-23 17:07
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUser(Integer userId) {
		return userMapper.selectById(userId);
	}

	@Override
	public List<User> listUser(UserSearchDTO userSearchDTO) {
		QueryWrapper<User> lt = new QueryWrapper<User>().gt("create_time", userSearchDTO.getStartTime())
				.lt("create_time", userSearchDTO.getEndTime());
		return userMapper.selectList(lt);
	}
}
