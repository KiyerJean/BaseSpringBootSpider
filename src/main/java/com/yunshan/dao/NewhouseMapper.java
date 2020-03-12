package com.yunshan.dao;

import com.yunshan.model.Newhouse;

public interface NewhouseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Newhouse record);

    int insertSelective(Newhouse record);

    Newhouse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Newhouse record);

    int updateByPrimaryKey(Newhouse record);
}