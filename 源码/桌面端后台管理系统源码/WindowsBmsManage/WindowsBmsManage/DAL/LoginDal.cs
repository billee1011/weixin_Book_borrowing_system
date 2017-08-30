using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsBmsManage.DAL
{
    class LoginDal
    {
        public object selectForLogin(string username,string password)
        {
            SqlHelper sh = new SqlHelper();
            string sql = "select count(*) from manager where username=@arg1 and password=@arg2";
            return sh.getSingleValue(sql,username,password);
        }
    }
}
