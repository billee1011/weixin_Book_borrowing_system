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
    public partial class FormAddNewBook : Form
    {
        public FormAddNewBook()
        {
            InitializeComponent();
        }
        BookBll bb = new BookBll();
        private void button1_Click(object sender, EventArgs e)
        {
           string[] args = new string[]{
                tb_bn.Text.ToString().Trim(),//0
                tb_introduce.Text.ToString().Trim(),//1
                tb_printtime.Text.ToString().Trim(),//2
                tb_booksize.Text.ToString().Trim(),//3
                tb_isbn.Text.ToString().Trim(),//4
                tb_category.Text.ToString().Trim(),//5
                tb_pcategory.Text.ToString().Trim(),//6
                tb_image.Text.ToString().Trim(),//7
                tb_collnum.Text.ToString().Trim(),//8
                tb_pinyin.Text.ToString().Trim(),//9
                tb_initial.Text.ToString().Trim(),//10
                tb_callnumber.Text.ToString().Trim(),//11
                tb_author.Text.ToString().Trim(),//12
                tb_publisher.Text.ToString().Trim(),//13
                tb_collplace.Text.ToString().Trim() };//14

            DialogResult dr;
            dr = MessageBox.Show("确定要添加该书？", "确认添加",
                                      MessageBoxButtons.OKCancel, MessageBoxIcon.Exclamation);
            if (dr == DialogResult.OK)
            {
                if (args[4].Equals(""))
                {
                    MessageBox.Show("ISBN不能为空！", "添加失败",
                         MessageBoxButtons.OK, MessageBoxIcon.Exclamation);

                }
                else if (args[0].Equals(""))
                {
                    MessageBox.Show("书名不能为空！", "添加失败",
                            MessageBoxButtons.OK, MessageBoxIcon.Exclamation);

                }
                else if (args[5].Equals(""))
                {
                    MessageBox.Show("二级分类不能为空！", "添加失败",
                            MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                }
                else if (args[6].Equals(""))
                {
                    MessageBox.Show("一级分类不能为空！", "添加失败",
                            MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                }
                else
                {
                    object result = bb.AddNewBook(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10], args[11], args[12], args[13], args[14]);
                    if (result == null)
                    {

                    }
                    else
                    {
                        if ((int)result == 1)
                        {
                            MessageBox.Show("添加成功！", "添加成功",
                                                MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                        }
                        else if ((int)result == 2)
                        {
                            MessageBox.Show("该书已存在！", "添加失败",
                                               MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                        }
                        else
                        {
                            MessageBox.Show("添加失败！", "添加失败",
                                              MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                        }
                    }
                }
            }
        
        }

        private void button2_Click(object sender, EventArgs e)
        {
            int i = int.Parse(tb_collnum.Text.Trim().ToString());
            if (i > 0)
            {
                i--;
                tb_collnum.Text = i.ToString();
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            int i = int.Parse(tb_collnum.Text.Trim().ToString());
            if (i < 99)
            {
                i++;
                tb_collnum.Text = i.ToString();
            }
        }

        private void tb_collnum_KeyPress(object sender, KeyPressEventArgs e)
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
