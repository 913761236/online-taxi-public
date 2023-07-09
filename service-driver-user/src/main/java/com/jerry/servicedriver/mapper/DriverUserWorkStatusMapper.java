package com.jerry.servicedriver.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jerry.common.dto.DriverUserWorkStatus;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@Repository
public interface DriverUserWorkStatusMapper extends BaseMapper<DriverUserWorkStatus> {

    int updateWorkStatus(@Param("driverId") long driverId, @Param("workStatus") int workStatus);

}
