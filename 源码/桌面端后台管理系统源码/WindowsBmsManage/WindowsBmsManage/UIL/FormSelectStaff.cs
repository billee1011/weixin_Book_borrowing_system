using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using WindowsBmsManage.BLL;

namespace WindowsBmsManage.UIL
{
    public partial class FormSelectStaff : Form
    {
        public FormSelectStaff()
        {
            InitializeComponent();
        }

        private void FormSelectStaff_Load(object sender, EventArgs e)
        {

            PersonBll pb = new PersonBll();
            DataTable result = (DataTable)pb.selectAllStaff();
            dataGridView1.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.EnableResizing;
            dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            if (result!=null&&result.Rows.Count > 0)
            {   
                dataGridView1.DataSource = result;
            }
            else if(result==null)
            {
                this.Dispose();
                this.Close();
            }
            else
            {
                dataGridView1.ColumnHeadersVisible = false;
                dataGridView1.DataSource = null;
            }
         

        }

        private void btn_export_Click(object sender, EventArgs e)
        {
           
            if (dataGridView1.DataSource!=null&&dataGridView1.ColumnCount > 0)
            {
                DataTable dt = new DataTable();
                dt = (DataTable)dataGridView1.DataSource;
                Microsoft.Office.Interop.Excel.Application excel = new Microsoft.Office.Interop.Excel.Application();
                Microsoft.Office.Interop.Excel.Workbook book = excel.Application.Workbooks.Add(Missing.Value);
                excel.Visible = true;
                int i = 0;
                int j = 1;
                Microsoft.Office.Interop.Excel.Worksheet sheet = (Microsoft.Office.Interop.Excel.Worksheet)book.Worksheets["Sheet1"];
                sheet.Cells[1, 1] = "员工编号";
                sheet.Cells[1, 2] = "员工姓名";
                sheet.Cells[1, 3] = "手机号码";
       
                foreach (DataRow mDr in dt.Rows)
                {
                    i = 0;
                    j++;
                    foreach (DataColumn mDc in dt.Columns)
                    {
                        i++;
                        sheet.Cells[j, i] = mDr[mDc].ToString();

                    }
                }
            }
            else
            {
                MessageBox.Show("暂无信息可以导出！", "操作提示",
                MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
        }
    }
}
