using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using WindowsBmsManage.BLL;

namespace WindowsBmsManage.UIL
{
    public partial class FormSelectState : Form
    {
        public FormSelectState()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            BookBll bb = new BookBll();
            string info = textBox1.Text.Trim();
            if (info.Equals(""))
            {
                init();
                MessageBox.Show("请输入书籍的编号或书名！", "操作提示",
     MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
            else
            {
                object ob=bb.SelectBookState(info);
                if (ob == null)
                {
                    return;
                }
                else
                { 
                    dataGridView1.ColumnHeadersVisible = true;
                    dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
                    dataGridView1.DataSource = (DataTable)ob;
                }
            }
        }

        private void FormSelectState_Load(object sender, EventArgs e)
        {
            init();
        }
        public void init()
        {
            dataGridView1.ColumnHeadersVisible = false;
            dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
        }
       
    }
}
