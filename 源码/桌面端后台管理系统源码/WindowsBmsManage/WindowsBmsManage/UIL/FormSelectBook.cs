using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using WindowsBmsManage.BLL;

namespace WindowsBmsManage.UIL
{
    public partial class FormSelectBook : Form
    {
        public FormSelectBook()
        {
            InitializeComponent();
        }


        BookBll bb = new BookBll();
        private void button1_Click(object sender, EventArgs e)
        { string info = textBox1.Text.ToString().Trim();
            if (info.Equals(""))
            {
                MessageBox.Show("请输入书籍的编号或书名！", "操作提示",
   MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
            else
            {
                DataTable dt = bb.SelectAllBook(info);
                if (dt != null)
                {
                    dataGridView1.ColumnHeadersVisible = true;
                    dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.ColumnHeader;
                    dataGridView1.DataSource = dt;
                   

                }

            }
        }

        private void FormSelectBook_Load(object sender, EventArgs e)
        {
            dataGridView1.ColumnHeadersVisible = false;
            dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
        }
    }
}
