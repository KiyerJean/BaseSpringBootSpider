package com.yunshan.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunshan.dao.NewhouseMapper;
import com.yunshan.model.Newhouse;
import com.yunshan.service.NewhouseService;
@Transactional
@Service
public class NewhouseServiceImpl implements NewhouseService{
	@Autowired
	private NewhouseMapper newhouseMapper;
	@Override
	 public int insert(Newhouse record) {
		 return newhouseMapper.insertSelective(record);
	 }

}
