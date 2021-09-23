package lj.springcloud.sample.account.service;

import lj.springcloud.sample.account.domian.dto.UserSearchDTO;
import lj.springcloud.sample.account.domian.model.User;

import java.util.List;

/**
 * @author lijian
 * @date 2021-09-23 17:07
 */
public interface UserService {


	User getUser(Integer userId);

	List<User> listUser(UserSearchDTO userSearchDTO);

}
