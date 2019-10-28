package com.lncui.shiro.mapper;


import java.util.List;

public interface BaseMapper<T> {

	/**
	 * 方法描述: 根据主键删除单条数据<br>
	 *
	 * @param id 主键
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);


	/**
	 * 方法描述: 插入数据（全部属性插入）<br>
	 *
	 * @param record 对象
	 * @return
	 */
	int insert(T record);

	/**
	 * 方法描述: 插入数据（只插入不为null的属性）<br>
	 *
	 * @param record 对象
	 * @return
	 */
	int insertSelective(T record);

	/**
	 * 方法描述: 根据主键查询对象<br>
	 *
	 * @param id 主键
	 * @return 单条数据
	 */
	T selectByPrimaryKey(Integer id);

	/**
	 * 方法描述: 更新数据（只插入不为null的属性）<br>
	 *
	 * @param record 对象
	 * @return
	 */
	int updateByPrimaryKeySelective(T record);

	/**
	 * 方法描述: 更新数据（全部属性更新）<br>
	 *
	 * @param record 对象
	 * @return
	 */
	int updateByPrimaryKey(T record);

	/**
	 * 方法描述: 查询所有数据<br>
	 *
	 * @return
	 */
	List<T> selectAll();

}
