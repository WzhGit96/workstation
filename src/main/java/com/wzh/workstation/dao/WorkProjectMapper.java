package com.wzh.workstation.dao;

import com.wzh.workstation.entity.WorkProject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkProject record);

    int insertSelective(WorkProject record);

    WorkProject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkProject record);

    int updateByPrimaryKey(WorkProject record);

    /**
     * 查询需求列表
     *
     * @param workProject
     * @return
     */
    List<WorkProject> selectList(WorkProject workProject);
}