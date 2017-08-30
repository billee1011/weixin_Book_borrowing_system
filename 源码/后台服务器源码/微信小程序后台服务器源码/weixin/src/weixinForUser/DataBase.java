package weixinForUser;

import java.sql.ResultSet;

import weixinNeedDate.user.User;

public interface DataBase<T extends User> {
	public void closeAll();
	public ResultSet SelectOne(T t);
	public int deleteone(T t);
	public int insertone(T t);
	public int updateOne(T t);
	public int updateSome(String sql,String...strings);
	public ResultSet selectOther(String sql,String...strings);
	public boolean ishasOne(String sql,String...strings);
	public int bindWeiXin(String sql,String sql1);
}
