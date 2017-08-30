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
    public partial class FormAddSingleBook : Form
    {
        public FormAddSingleBook()
        {
            InitializeComponent();
        }

        private void button2_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)//减号
        {
            int i = int.Parse(textBox2.Text.Trim().ToString());
            if ( i>1)
            {
                i--;
                textBox2.Text = i.ToString();
            }
        }

        private void textBox2_KeyPress(object sender, KeyPressEventArgs e)
        {
            if ((e.KeyChar >= '0' && e.KeyChar <= '9') || (byte)(e.KeyChar) == 8)
            {
            }
            else
            {
                e.Handled = true;
            }
        }

        private void button2_Click_1(object sender, EventArgs e)
        {
            int i = int.Parse(textBox2.Text.Trim().ToString());
            if (i < 99)
            {
                i++;
                textBox2.Text = i.ToString();
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            string isbn = textBox1.Text;
            if (isbn.Equals(""))
            {

                MessageBox.Show("请输入书籍的ISBN号！", "操作提示",
       MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
            else
            {
                int i = int.Parse(textBox2.Text.Trim().ToString());
                if (i > 0)
                {
                    BookBll bb = new BookBll();

                    object obj = bb.SelectBookNameAndCollec(isbn);
                    if (obj == null)
                    {
                        return;
                      
                    }
                    else
                    { MySqlDataReader dr = (MySqlDataReader)obj;
  
                        string bookname=null;
                        string collectionnum=null;
                        while (dr.Read())
                        {
                            collectionnum = dr[0].ToString();
                            bookname = dr[1].ToString();
                            

                        }
                        dr.Close();
                        if (bookname == null)
                        {
                            MessageBox.Show("没有查询到该书，如果该种书籍本馆还未登记过，请先添加该种书籍后，再进行单本添加操作！", "操作提示",
                MessageBoxButtons.OK, MessageBoxIcon.Exclamation);

                        }
                        else
                        {
                            DialogResult drt;
                            drt = MessageBox.Show("确定要添加书名为<<" + bookname + ">>的书吗？添加数量为"+i+"本", "添加确认",
                                                      MessageBoxButtons.OKCancel, MessageBoxIcon.Exclamation);
                            if (drt == DialogResult.OK)
                            {
                               int n= bb.AddSingleBook(isbn, i.ToString(), collectionnum);
                                if (n == 1)
                                {
                                    //添加成功

                                    MessageBox.Show("添加成功！", "操作提示",
                           MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                                }
                                else
                                {

                                    MessageBox.Show("添加失败！", "操作提示",
                           MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                                }
                            }
                        }
                       
                    }
                }
                else
                {

                    MessageBox.Show("数量不能为0！", "操作提示",
           MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                }
            }
        }

        private void textBox1_KeyPress(object sender, KeyPressEventArgs e)
        {
            if ((e.KeyChar >= '0' && e.KeyChar <= '9') || (byte)(e.KeyChar) == 8)
            {
            }
            else
            {
                e.Handled = true;
            }
        }
    }
}
