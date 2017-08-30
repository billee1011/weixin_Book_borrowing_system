using System;
using System.Windows.Forms;
using System.Configuration;
using MySql.Data.MySqlClient;
using System.Data;
using System.Data.SqlClient;
using System.Collections.Generic;

namespace WindowsBmsManage.DAL
{
    class SqlHelper
    {
        MySqlConnection conn;
        MySqlCommand comm;
         private static readonly string connectionString = ConfigurationManager.OpenExeConfiguration(ConfigurationUserLevel.None).AppSettings.Settings["DBConnectionString"].Value;
        //private static readonly string connectionString = "Server=39.108.6.0;user id=root;password=14159265jkl;database=weixin;Integrated Security=True";

        public static string ConnectionString => connectionString;

        public Boolean Open()
        {
            Boolean flag = false;
            conn = new MySqlConnection();
            conn.ConnectionString = ConnectionString;
            try
            {
                conn.Open(); // 打开数据库连接
                flag = true;
            }
            catch (Exception ex)
            {
                MessageBox.Show("连接数据库错误，请检查网络状况", "错误信息", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
            return flag;
        }
        public void Close()
        {
            ///判断连接是否已经创建
            if (conn != null)
            {
                ///判断连接的状态是否打开
                if (conn.State == ConnectionState.Open)
                {
                    conn.Close();
                }
            }
        }
        public object getReader(string sql, params object[] args)
        {
            object value = null;
            try
            {

                getComm(sql, args);
                if (comm != null)
                {
                    value = comm.ExecuteReader();
                }



            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "错误信息", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
            return value;
        }
        public object getSingleValue(string sql, params object[] args)//查询单条信息（联机模式）
        {
            object value = null;
            try
            {

                getComm(sql, args);
                if (comm != null)
                {
                    value = comm.ExecuteScalar();
                }
             

               
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "错误信息", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
            finally
            {
                this.Close();// 关闭数据库连接
            }
            return value;
        }
        public int nonQuery(string sql, params object[] args)//处理数据的增加/删除/修改（联机模式）
        {
            int num = -1;
            try
            {

                getComm(sql, args);
                if(comm!=null)
                num = comm.ExecuteNonQuery();

            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "错误信息", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
            finally
            {
                this.Close();// 关闭数据库连接
            }
            return num;
        }
        public object selectAll(string sql, string tablename, params object[] args)
        {
            getComm(sql, args);
            if (comm != null)
            {
                try
                {
                    MySqlDataAdapter da = new MySqlDataAdapter();
                    DataSet ds = new DataSet();
                    da.SelectCommand = comm;
                    ds.Clear();
                    da.Fill(ds, tablename);
                    return ds.Tables[tablename];
                }catch(MySqlException mysql)
                {
                    MessageBox.Show(mysql.Message, "错误信息", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return null;
                }
             
            }
            else
            {
                return null;
            }
          
        }
        public MySqlParameter[] getSqlParameters(params object[] args)
        {

            List<MySqlParameter> list = new List<MySqlParameter>();
            int num = args.Length;
            for (int i = 0; i < num; i++)
            {
                list.Add(new MySqlParameter("@arg" + (i + 1), (string)args[i]));
            }
            MySqlParameter[] pms = list.ToArray();
            return pms;
        }
        public void getComm(string sql, params object[] args)
        {
            Boolean s=true;
            if (conn == null || conn.State == ConnectionState.Closed)
               
               s=this.Open();
            //创建命令对象
            if (s == true)
            {
                using (comm = new MySqlCommand(sql, conn))
                {
                    MySqlParameter[] pms = this.getSqlParameters(args);
                    comm.Parameters.AddRange(pms);
                }
            }
            else
            {
                comm = null;
            }
           

        }
        public int sqlTran1(string[] sqls)
        {
            MySqlTransaction mytran;
            Boolean s = true;
            if (conn == null || conn.State == ConnectionState.Closed)

                s = this.Open();
            //创建命令对象
     
            if (s == true)
            {
                mytran = conn.BeginTransaction();
                try
                {
                    comm = new MySqlCommand();

                    comm.Transaction = mytran;
                    comm.Connection = conn;
                    int n = sqls.Length;
                    for (int i = 0; i < n; i++)
                    {
                        comm = new MySqlCommand(sqls[i], conn);
                        comm.ExecuteNonQuery();
                    
                    }
                    mytran.Commit();//提交数据库事务
                    return 1;

                }
                catch (Exception ex)
                {
                    mytran.Rollback();//回滚（出错的时候)
                    return -1;
                }
                finally
                {
                    conn.Close();
                }
            }
            else
            {
                return -1;
            }
        }
        public int sqlTran(string[] sqls, params object[] args)
        {
            MySqlTransaction mytran;
            Boolean s = true;
            if (conn == null || conn.State == ConnectionState.Closed)

                s = this.Open();
            //创建命令对象
            if (s == true)
            {
                mytran = conn.BeginTransaction();
                try
                {
                  comm= new MySqlCommand();

                    comm.Transaction = mytran;
                    comm.Connection = conn;
                    int n=sqls.Length;
                    int m = n;
                    for (int i = 0; i < m; i++)
                    {
                        using (comm = new MySqlCommand(sqls[i], conn))
                        {
                            MySqlParameter[] pms = this.getSqlParameters(n,n+(int)args[i],args);
                            comm.Parameters.AddRange(pms);
                        }
                        comm.ExecuteNonQuery();
                        n = n + (int)args[i];
                    }
                    mytran.Commit();//提交数据库事务
                    return 1;
                  
                }
                catch (Exception ex)
                {
                    mytran.Rollback();//回滚（出错的时候)
                    return -1;
                }
                finally
                {
                    conn.Close();
                }
            }
            else
            {
                return -1;
            }

        }
        public int sqlTran2(string[] sqls, params object[] args)
        {
            MySqlTransaction mytran;
            Boolean s = true;
            if (conn == null || conn.State == ConnectionState.Closed)

                s = this.Open();
            //创建命令对象
            if (s == true)
            {
                mytran = conn.BeginTransaction();
                try
                {
                    comm = new MySqlCommand();

                    comm.Transaction = mytran;
                    comm.Connection = conn;
                    int n = sqls.Length;
        
                    for (int i = 0; i < n; i++)
                    {
                        if (i == 0)
                        {
                            using (comm = new MySqlCommand(sqls[i], conn))
                            {
                                MySqlParameter[] pms = this.getSqlParameters(args);
                                comm.Parameters.AddRange(pms);
                            }
                            comm.ExecuteNonQuery();

                        }
                        else
                        {
                            comm = new MySqlCommand(sqls[i], conn);
                            comm.ExecuteNonQuery();

                        }
                     
                    }
                    mytran.Commit();//提交数据库事务
                    return 1;

                }
                catch (Exception ex)
                {
                    mytran.Rollback();//回滚（出错的时候)
                    return -1;
                }
                finally
                {
                    conn.Close();
                }
            }
            else
            {
                return -1;
            }

        }
        public MySqlParameter[] getSqlParameters(int i,int num,params object[] args)
        {

            List<MySqlParameter> list = new List<MySqlParameter>();
            for (; i < num; i++)
            {
                list.Add(new MySqlParameter("@arg" + (i + 1), (string)args[i]));
            }
            MySqlParameter[] pms = list.ToArray();
            return pms;
        }

    }
}
