using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsBmsManage.DAL
{
    class PersonDal
    {
        SqlHelper sh = new SqlHelper();
        public object selectAllStaff()
        {
            string sql = "select employnum,name,phone from staff";
            return sh.selectAll(sql, "staff");
        }
        public object selectSingleStaff(string info)
        {
            string sql = "select employnum,name,phone from staff where name=@arg1 or employnum= @arg1";
            return sh.selectAll(sql, "staff",info);
        }
        public int deleteStaff(string info)
        {
            string sql = "delete from staff where employnum=@arg3";
            string sql1 = "update registcode set registcode=@arg4,state='0' where employnum=@arg5";
          //  string sql2 = "insert into registcode values(@arg6,@arg7,@arg8)";
            Random random = new Random();
            int r = random.Next(899999)+ 100000;
            string[] sqls = new string[] { sql,sql1};
            return sh.sqlTran(sqls, 1, 2,info,r.ToString(),info);
        }
    }
}
