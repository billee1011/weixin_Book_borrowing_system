using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows.Forms;
using WindowsBmsManage.DAL;

namespace WindowsBmsManage.BLL
{
    class BookBll
    {
        BookDal bd = new BookDal();
      public int AddSingleBook(string isbn,string num,string collectionnum)
        {
            
            return bd.AddSingleBook(isbn, num,collectionnum);
        }
        public object SelectBookNameAndCollec(string info)
        {
            return bd.SelectBookNum(info);
        }
        public object SelectBookANDBookinfo(string info)
        {
            return bd.SelectBookAndBookInfo(info);
        }
        public int DeleteBookInfo(string isbn, string id, string collectionnum)
        {
            return bd.DeleteBookInfo(isbn,id,collectionnum);
        }
        public object SelectBookState(string info)
        {
            string[] sarray = info.Split('_');
            if (sarray.Length == 2)
            {
              object obj= bd.SelectBookState(sarray[0], sarray[1]);
                if (obj == null)
                {
                    return null;
                }
                else
                {
                    object obj2=getDataTable2(obj);


                    if (obj2!=null)
                    {
                        return obj2;
                    }
                    else
                    {
                       
                        object obj1 = bd.SelectBookState(info);
                        if (obj1 == null)
                        {
                            return null;
                        }
                        else
                        {
                            object obj3 = getDataTable(obj1);
                            return obj3;

                        }
                    }
                }
            }
            else
            {
                object obj2 = bd.SelectBookState(info);
                if (obj2 == null)
                {
                    return null;
                }
                else
                {

                    return getDataTable(obj2);
                }
            }
         }
        public DateTime ConvertJavaMiliSecondToDateTime(long javaMS)
        {
            DateTime UTCBaseTime = new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc);
            DateTime dt = UTCBaseTime.Add(new TimeSpan(javaMS * TimeSpan.TicksPerMillisecond)).ToLocalTime();
            return dt;
        }
         public  object getDataTable(object obj) {
            MySqlDataReader msdr = (MySqlDataReader)obj;
            if (msdr.HasRows)
            {

                DataTable dt = new DataTable();
                dt.Columns.Add(new DataColumn("ISBN", typeof(string)));
                dt.Columns.Add(new DataColumn("bookname", typeof(string)));
                dt.Columns.Add(new DataColumn("id", typeof(string)));
                dt.Columns.Add(new DataColumn("state", typeof(string)));
                dt.Columns.Add(new DataColumn("date", typeof(string)));
                dt.Columns.Add(new DataColumn("userphone", typeof(string)));
                DataRow dr = null;
                while (msdr.Read())
                {


                    dr = dt.NewRow();
                    dr["ISBN"] = msdr[0];
                    dr["bookname"] = msdr[5];
                    dr["id"] = msdr[1];
                    dr["userphone"] = msdr[2];
                    if (msdr[3].ToString().Trim().Equals("A"))
                    {
                        dr["state"] = "可借阅";
                    }
                    else if (msdr[3].ToString().Trim().Equals("B"))
                    {
                        dr["state"] = "被预定";
                    }
                    else if (msdr[3].ToString().Trim().Equals("C"))
                    {
                        dr["state"] = "待借阅";
                    }
                    else
                    {
                        dr["state"] = "借阅中";
                    }
                    if (!msdr[4].ToString().Equals(""))
                    {
                        long miao = long.Parse(msdr[4].ToString());
                        DateTime s = ConvertJavaMiliSecondToDateTime(miao);
                        string time = s.ToString("yyyy/MM/dd");
                        dr["date"] = time;
                    }
                    else
                    {
                        dr["date"] = "";
                    }

                    dt.Rows.Add(dr);


                }
                msdr.Close();
                return dt;
            }
            else
            {  
                MessageBox.Show("没有查询到信息！", "操作提示",
                   MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                msdr.Close();
                return null;
            }

            }
        public object getDataTable2(object obj)
        {
            MySqlDataReader msdr = (MySqlDataReader)obj;
            if (msdr.HasRows)
            {

                DataTable dt = new DataTable();
                dt.Columns.Add(new DataColumn("ISBN", typeof(string)));
                dt.Columns.Add(new DataColumn("bookname", typeof(string)));
                dt.Columns.Add(new DataColumn("id", typeof(string)));
                dt.Columns.Add(new DataColumn("state", typeof(string)));
                dt.Columns.Add(new DataColumn("date", typeof(string)));
                dt.Columns.Add(new DataColumn("userphone", typeof(string)));
                DataRow dr = null;
                while (msdr.Read())
                {


                    dr = dt.NewRow();
                    dr["ISBN"] = msdr[0];
                    dr["bookname"] = msdr[5];
                    dr["id"] = msdr[1];
                    dr["userphone"] = msdr[2];
                    if (msdr[3].ToString().Trim().Equals("A"))
                    {
                        dr["state"] = "可借阅";
                    }
                    else if (msdr[3].ToString().Trim().Equals("B"))
                    {
                        dr["state"] = "被预定";
                    }
                    else if (msdr[3].ToString().Trim().Equals("C"))
                    {
                        dr["state"] = "待借阅";
                    }
                    else
                    {
                        dr["state"] = "借阅中";
                    }
                    if (!msdr[4].ToString().Equals(""))
                    {
                        long miao = long.Parse(msdr[4].ToString());
                        DateTime s = ConvertJavaMiliSecondToDateTime(miao);
                        string time = s.ToString("yyyy/MM/dd");
                        dr["date"] = time;
                    }
                    else
                    {
                        dr["date"] = "";
                    }

                    dt.Rows.Add(dr);


                }
                msdr.Close();
                return dt;
            }
            else
            {
              
                msdr.Close();
                return null;
            }

        }
        public object AddNewBook(params object[] args)
        {
            object ob=bd.SelectBookISBN(args[4].ToString());
            if (ob == null)
            {
                return null;
            }
            else
            {
                string obst = ob.ToString();
                if (int.Parse(obst) != 0)
                {
                    return 2;//isbn已存在
                }
                else
                {
                    return bd.AddNewBook(args);
                }
            }
        }
        public object SelectForDelectAllBook(string info)
        {
            object result=bd.SelectBookForDelete(info);
            if (result == null)
            {
                return  null;
            }
            else
            {
                MySqlDataReader msdr = (MySqlDataReader)result;
                if (msdr.Read())
                {
                    if (msdr[3].ToString().Trim().Equals("0") && !msdr[2].ToString().Trim().Equals(msdr[3].ToString().Trim()))
                    {
                        msdr.Close();
                        MessageBox.Show("您查询的内容不存在！", "操作提示",
    MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                        return null;//查询为空
                    }
                    else 
                    {
                        DataTable dt = new DataTable();
                        dt.Columns.Add(new DataColumn("ISBN", typeof(string)));
                        dt.Columns.Add(new DataColumn("bookname", typeof(string)));
                        dt.Columns.Add(new DataColumn("collectionnumber", typeof(string)));
                        dt.Columns.Add(new DataColumn("state", typeof(string)));
                        DataRow dr = dt.NewRow();
                        dr["bookname"] = msdr[0];
                        dr["ISBN"] = msdr[1];
                        dr["collectionnumber"] = msdr[2];
                        if (msdr[2].ToString().Trim().Equals(msdr[3].ToString().Trim()))
                        {
                            dr["state"] = "不存在使用中的书籍";
                        }
                        else
                        {
                            dr["state"] = "存在使用中的书籍";
                        }
                        dt.Rows.Add(dr);
                        msdr.Close();
                        return dt;
                    }
                }
                else
                {
                    return null;
                }
            }
        }
        public int DeleteAllBook(string info)
        {
            return bd.DeleteAllBook(info);
        }
        public DataTable SelectForUpdate(string info)
        {
            object obj = bd.SelectForUpdate(info);
            if (obj == null)
            {
                return null;
            }
            else
            { DataTable dt = (DataTable)obj;
                if (dt.Rows.Count > 0)
                {
                    return dt;
                }
                else
                {
                    MessageBox.Show("您查询的内容不存在！", "查询失败",
                            MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return null;
                }
            }
        }
        public int UpdateBook(params object[] args)
        {
            return bd.UpdateBook(args);
        }
        public DataTable SelectAllBook(string info)
        {
            object ob = bd.SelectAllBookInfo(info);
            if (ob == null)
            {
                return null;
            }
            else
            {
                DataTable dt = (DataTable)ob;
                if (dt.Rows.Count > 0)
                {
                    return dt;
                }
                else
                {

                    MessageBox.Show("您查询的内容不存在！", "查询失败",
                            MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return null;
                }
            }
        }
       
    }
}
