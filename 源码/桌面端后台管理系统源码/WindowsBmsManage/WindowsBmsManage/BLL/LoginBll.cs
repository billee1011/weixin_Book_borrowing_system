using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WindowsBmsManage.DAL;

namespace WindowsBmsManage.BLL
{
    class LoginBll
    {
        LoginDal ld = new LoginDal();
        public int Login(string username,string password)
        {
            if (username.Equals(""))
            {
                return 2;
            }else if (password.Equals(""))
            {
                return 3;
            }
            else
            {
                object result = ld.selectForLogin(username, password);
                if (result != null)
                {
                    return int.Parse(result.ToString());
                }
                else return 4;
            }
        }
    }
}
