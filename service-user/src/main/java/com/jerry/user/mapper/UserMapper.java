package com.jerry.user.mapper;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jerry.user.service.User;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/23
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    Integer countUserByPhone(String phone);
}
