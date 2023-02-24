package com.wzh.workstation.dao;

import com.wzh.workstation.entity.WorkUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkUser record);

    int insertSelective(WorkUser record);

    WorkUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkUser record);

    int updateByPrimaryKey(WorkUser record);
}
