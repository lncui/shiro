package com.lncui.shiro.service;

public interface BaseService<T> {


	/**
	 * 方法描述: 根据主键查询对象<br>
	 *
	 * @param id 主键
	 * @return 单条数据
	 */
	public T getByID(Integer id);

	/**
	 * 方法描述: 插入数据（只插入不为null的属性）<br>
	 *
	 * @param obj 对象
	 * @return
	 */
	public int insert(T obj);

	/**
	 * 方法描述: 根据主键删除单条数据<br>
	 *
	 * @param id 主键
	 * @return
	 */
	public int delete(Integer id);

	/**
	 * 方法描述: 更新数据（只插入不为null的属性）<br>
	 *
	 * @param obj 对象
	 * @return
	 */
	public int update(T obj);

}
