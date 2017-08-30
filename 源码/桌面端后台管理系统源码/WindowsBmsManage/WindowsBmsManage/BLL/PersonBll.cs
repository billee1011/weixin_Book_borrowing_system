using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WindowsBmsManage.DAL;

namespace WindowsBmsManage.BLL
{
    class PersonBll
    {
        PersonDal pd = new PersonDal();
        public object selectAllStaff()
        {
            object result = pd.selectAllStaff();

            return result;
        }
        public object selectSingleStaff(string info)
        {
            object result = pd.selectSingleStaff(info);

            return result;
        }
        public int deleteStaff(string info)
        {
            int result = pd.deleteStaff(info);
            return result;
        }
        
    }
}
