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
    public partial class FormDeleteBook : Form
    {
        public FormDeleteBook()
        {
            InitializeComponent();
        }
        BookBll bb = new BookBll();
        private void button1_Click(object sender, EventArgs e)
        {
            string info = textBox1.Text.ToString().Trim();
            if (info.Equals(""))
            {
                MessageBox.Show("请输入要删除的书目信息并进行查询！", "操作提示",
   MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
            else
            {
              object result=  bb.SelectForDelectAllBook(info);
                if (result == null)
                {
                    label2.DataBindings.Clear();
                    label2.Text = "";
                    return;
                }
                else
                {
                    DataTable dt=(DataTable)result;
                    dataGridView1.ColumnHeadersVisible = true;
                    dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
                    dataGridView1.DataSource = dt;
                    label2.DataBindings.Clear();
                    label2.DataBindings.Add("Text", dt, "ISBN");
                }
            }
            
        }

        private void FormDeleteBook_Load(object sender, EventArgs e)
        {
            dataGridView1.ColumnHeadersVisible = false;
            dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            label2.Visible = false;
            label2.Text = "";
        }

        private void button2_Click(object sender, EventArgs e)
        {
            DialogResult dr;
            dr = MessageBox.Show("确定要删除该书？", "确认删除",
                                      MessageBoxButtons.OKCancel, MessageBoxIcon.Exclamation);
            if (dr == DialogResult.OK)
            {
                if (label2.Text.ToString().Trim().Equals(""))
                {
                    MessageBox.Show("请输入要删除的书目信息并进行查询！", "操作提示",
                       MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                }
                else
                {
                    object x = this.dataGridView1.Rows[0].Cells["Column4"].Value;

                    if (x.ToString().Trim().Equals("不存在使用中的书籍"))
                    {
                        int n = bb.DeleteAllBook(label2.Text.ToString().Trim());
                        if (n == 1)
                        {
                            MessageBox.Show("删除成功！", "操作提示",
                       MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                            dataGridView1.ColumnHeadersVisible = false;
                            DataTable dt = new DataTable();
                            dt.Columns.Add(new DataColumn("ISBN", typeof(string)));
                            dt.Columns.Add(new DataColumn("bookname", typeof(string)));
                            dt.Columns.Add(new DataColumn("collectionnumber", typeof(string)));
                            dt.Columns.Add(new DataColumn("state", typeof(string)));
                            dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
                            dataGridView1.DataSource = dt;

                        }
                        else
                        {
                            MessageBox.Show("删除失败！", "操作提示",
                       MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                        }
                    }
                    else
                    {
                        MessageBox.Show("该类书存在使用中的书籍，不可删除！", "操作提示",
                                          MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    }
                }
            }
       
        }
    }
}
