package com.lncui.shiro.service.impl;

import com.lncui.shiro.mapper.BaseMapper;
import com.lncui.shiro.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	public abstract BaseMapper<T> getBaseMapper();

	private final static Logger log = LoggerFactory.getLogger(Class.class);

	@Override
	public T getByID(Integer id) {
		return getBaseMapper().selectByPrimaryKey(id);
	}

	@Override
	public int insert(T obj) {
		return getBaseMapper().insertSelective(obj);
	}

	@Override
	public int delete(Integer id) {
		return getBaseMapper().deleteByPrimaryKey(id);
	}

	@Override
	public int update(T obj) {
		return getBaseMapper().updateByPrimaryKeySelective(obj);
	}
	
}
