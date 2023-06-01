package com.jerry.user.mapper;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jerry.common.dto.PassengerUser;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/23
 */
@Repository
public interface PassengerUserMapper extends BaseMapper<PassengerUser> {

    Integer countUserByPhone(String phone);

}
