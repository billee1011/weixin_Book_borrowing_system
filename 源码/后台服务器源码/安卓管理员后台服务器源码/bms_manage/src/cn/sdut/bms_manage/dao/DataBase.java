package cn.sdut.bms_manage.dao;

import java.sql.ResultSet;

import cn.sdut.bms_manage.bean.Manager;



public interface DataBase<T extends Manager > {
	public void closeAll();
	public int insertone(T t);
	public int updateSome(String sql,String...strings);
	public ResultSet selectOther(String sql,String...strings);
	boolean ishasOne(String sql, String[] strings);
	public int registerInsert(String sql, String sql1);
}
