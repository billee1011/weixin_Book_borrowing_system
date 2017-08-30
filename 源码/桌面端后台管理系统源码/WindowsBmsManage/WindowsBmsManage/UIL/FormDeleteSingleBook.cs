using MySql.Data.MySqlClient;
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
    public partial class FormDeleteSingleBook : Form
    {
        public FormDeleteSingleBook()
        {
            InitializeComponent();
        }
        string infozz;
        private void button2_Click(object sender, EventArgs e)//查询
        {
            infozz=textBox1.Text.Trim();
            if (infozz.Equals(""))
            {
                init();
                MessageBox.Show("请输入书籍的ISBN号或书名！", "操作提示",
      MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
            else
            {
                load_datagridview(infozz);
            }
            
        }

        private void FormDeleteSingleBook_Load(object sender, EventArgs e)
        {
            init();

        }
        public void init()
        {
            label1.Visible = false;
            label2.Visible = false;
            label3.Visible = false;
            label5.Visible = false;
            label6.Visible = false;
            label7.Visible = false;
            label8.Visible = false;
            label9.Visible = false;
            dataGridView1.ColumnHeadersVisible = false;
            dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dataGridView1.DataSource = null;
            label5.DataBindings.Clear();
            label5.Text = "";
            label6.DataBindings.Clear();
            label6.Text = "";
            label7.DataBindings.Clear();
            label7.Text = "";
            label8.DataBindings.Clear();
            label8.Text = "";
            label9.DataBindings.Clear();
            label9.Text = "";

        }

        private void button1_Click(object sender, EventArgs e)//删除
        { string collenum = label7.Text;
            string state = label8.Text;
            string isbn = label6.Text;
            string id = label9.Text;
            if (collenum.Equals(""))
            {
                MessageBox.Show("请先查询出要删除的书籍！", "操作提示",
    MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }else if (collenum.Equals("0"))
            {
                MessageBox.Show("书库中已没有馆藏量！", "操作提示",
   MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }else if (state.Equals("使用中"))
            {
                MessageBox.Show("该书在使用中不可删除！", "操作提示",
  MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
            else
            {
                DialogResult dr;
                dr = MessageBox.Show("确定要删除吗？", "确认删除",
                                          MessageBoxButtons.OKCancel, MessageBoxIcon.Exclamation);
                if (dr == DialogResult.OK)
                {
                    BookBll bb = new BookBll();
                    int i = bb.DeleteBookInfo(isbn, id, collenum);
                    if (i == 1)
                    {
                        MessageBox.Show("删除成功！", "操作提示",
      MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                        load_datagridview2(infozz);
                    }
                    else
                    {
                        MessageBox.Show("删除失败！", "操作提示",
     MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    }
                }
                    
            }
        }
        public void load_datagridview2(string info)
        {
            BookBll bb = new BookBll();
            object obj = bb.SelectBookANDBookinfo(info);
            if (obj == null)
            {
                init();
                return;

            }
            else
            {
                MySqlDataReader msdr = (MySqlDataReader)obj;
                DataTable dt = new DataTable();
                dt.Columns.Add(new DataColumn("ISBN", typeof(string)));
                dt.Columns.Add(new DataColumn("bookname", typeof(string)));
                dt.Columns.Add(new DataColumn("id", typeof(string)));
                dt.Columns.Add(new DataColumn("state", typeof(string)));
                dt.Columns.Add(new DataColumn("collectionnum", typeof(string)));
                DataRow dr = null;
                while (msdr.Read())
                {


                    dr = dt.NewRow();
                    dr["ISBN"] = msdr[0];
                    dr["bookname"] = msdr[1];
                    dr["id"] = msdr[3];
                    if (msdr[4].Equals("A"))
                    {
                        dr["state"] = "未使用";
                    }
                    else if (msdr[4].ToString().Trim().Equals(""))
                    {
                        dr["state"] = "";
                    }
                    else
                    {
                        Console.WriteLine(msdr[4].ToString());
                        dr["state"] = "使用中";
                    }


                    dr["collectionnum"] = msdr[2];
                    dt.Rows.Add(dr);


                }
                msdr.Close();
                if (dr == null)
                {
                    init();
                }
                else
                {
                    dataGridView1.ColumnHeadersVisible = true;
                    dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
                    dataGridView1.Columns[4].Visible = false;//隐藏最后一列
                    dataGridView1.DataSource = dt;
                    label1.Visible = true;
                    label2.Visible = true;
                    label3.Visible = true;
                    label5.DataBindings.Clear();
                    label5.DataBindings.Add("Text", dt, "bookname");
                    label6.DataBindings.Clear();
                    label6.DataBindings.Add("Text", dt, "ISBN");
                    label7.DataBindings.Clear();
                    label7.DataBindings.Add("Text", dt, "collectionnum");
                    label8.DataBindings.Clear();
                    label8.DataBindings.Add("Text", dt, "state");
                    label9.DataBindings.Clear();
                    label9.DataBindings.Add("Text", dt, "id");
                    label5.Visible = true;
                    label6.Visible = true;
                    label7.Visible = true;



                }

            }
        }
        public void load_datagridview(string info)
        {
            BookBll bb = new BookBll();
            object obj = bb.SelectBookANDBookinfo(info);
            if (obj == null)
            {
                init();
                return;

            }
            else
            {
                MySqlDataReader msdr = (MySqlDataReader)obj;
                DataTable dt = new DataTable();
                dt.Columns.Add(new DataColumn("ISBN", typeof(string)));
                dt.Columns.Add(new DataColumn("bookname", typeof(string)));
                dt.Columns.Add(new DataColumn("id", typeof(string)));
                dt.Columns.Add(new DataColumn("state", typeof(string)));
                dt.Columns.Add(new DataColumn("collectionnum", typeof(string)));
                DataRow dr = null;
                while (msdr.Read())
                {


                    dr = dt.NewRow();
                    dr["ISBN"] = msdr[0];
                    dr["bookname"] = msdr[1];
                    dr["id"] = msdr[3];
                    if (msdr[4].Equals("A"))
                    {
                        dr["state"] = "未使用";
                    }
                    else if (msdr[4].ToString().Trim().Equals(""))
                    {
                        dr["state"] = "";
                    }
                    else
                    {
                     
                        dr["state"] = "使用中";
                    }


                    dr["collectionnum"] = msdr[2];
                    dt.Rows.Add(dr);


                }
                msdr.Close();
                if (dr == null)
                {
                    init();
                    MessageBox.Show("您查询的内容不存在！", "操作提示",
  MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                }
                else
                {
                    dataGridView1.ColumnHeadersVisible = true;
                    dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
                    dataGridView1.Columns[4].Visible = false;//隐藏最后一列
                    dataGridView1.DataSource = null;
                    dataGridView1.DataSource = dt;
                    label1.Visible = true;
                    label2.Visible = true;
                    label3.Visible = true;
                    label5.DataBindings.Clear();
                    label5.DataBindings.Add("Text", dt, "bookname");
                    label6.DataBindings.Clear();
                    label6.DataBindings.Add("Text", dt, "ISBN");
                    label7.DataBindings.Clear();
                    label7.DataBindings.Add("Text", dt, "collectionnum");
                    label8.DataBindings.Clear();
                    label8.DataBindings.Add("Text", dt, "state");
                    label9.DataBindings.Clear();
                    label9.DataBindings.Add("Text", dt, "id");
                    label5.Visible = true;
                    label6.Visible = true;
                    label7.Visible = true;



                }

            }
        }
    }
}
