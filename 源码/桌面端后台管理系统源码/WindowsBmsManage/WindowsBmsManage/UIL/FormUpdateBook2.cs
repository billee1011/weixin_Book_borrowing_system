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
using WindowsBmsManage.MODEL;

namespace WindowsBmsManage.UIL
{
    public partial class FormUpdateBook2 : Form
    {
        public FormUpdateBook2()
        {
            InitializeComponent();
        }
        BookBll bb = new BookBll();
        private void FormUpdateBook2_Load(object sender, EventArgs e)
        {
            tb_bn.Text = Book.bookname;
            tb_isbn.Text = Book.isbn;
            tb_author.Text = Book.author;
            tb_pcategory.Text = Book.pcategory;
            tb_category.Text = Book.category;
            tb_publisher.Text = Book.publisher;
            tb_printtime.Text = Book.printingtime;
            tb_image.Text = Book.img;
            tb_booksize.Text = Book.booksize;
            tb_pinyin.Text = Book.Pinyin;
            tb_callnumber.Text = Book.callnumber;
            tb_initial.Text = Book.initial;
            tb_collplace.Text = Book.collectionplace;
            tb_introduce.Text = Book.simpintroduce;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (tb_bn.Text.ToString().Trim().Equals("") || tb_pcategory.Text.ToString().Trim().Equals("") || tb_category.Text.ToString().Trim().Equals(""))
            {
                MessageBox.Show("*内容不能为空！", "操作提示",
 MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
            else if (tb_bn.Text.ToString().Trim().Equals(Book.bookname)&&
                tb_author.Text.ToString().Trim().Equals(Book.author) &&
                tb_pcategory.Text.ToString().Trim().Equals(Book.pcategory) &&
                tb_category.Text.ToString().Trim().Equals(Book.category) &&
                tb_publisher.Text.ToString().Trim().Equals(Book.publisher) &&
                tb_printtime.Text.ToString().Trim().Equals(Book.printingtime) &&
                tb_image.Text.ToString().Trim().Equals(Book.img) &&
                tb_booksize.Text.ToString().Trim().Equals(Book.booksize) &&
                tb_pinyin.Text.ToString().Trim().Equals(Book.Pinyin) &&
                tb_callnumber.Text.ToString().Trim().Equals(Book.callnumber) &&
                tb_initial.Text.ToString().Trim().Equals(Book.initial) &&
                tb_collplace.Text.ToString().Trim().Equals(Book.collectionplace) &&
                tb_introduce.Text.ToString().Trim().Equals(Book.simpintroduce))
            {
                MessageBox.Show("您未做任何修改！", "操作提示",
 MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
            else
            {
                int n = bb.UpdateBook(tb_bn.Text.ToString().Trim(),
                    tb_author.Text.ToString().Trim(),
                    tb_pcategory.Text.ToString().Trim(),
                    tb_category.Text.ToString().Trim(),
                    tb_publisher.Text.ToString().Trim(),
                    tb_printtime.Text.ToString().Trim(),
                    tb_image.Text.ToString().Trim(),
                    tb_booksize.Text.ToString().Trim(),
                    tb_pinyin.Text.ToString().Trim(),
                    tb_callnumber.Text.ToString().Trim(),
                    tb_initial.Text.ToString().Trim(),
                    tb_collplace.Text.ToString().Trim(),
                    tb_introduce.Text.ToString().Trim(), 
                    tb_isbn.Text.ToString().Trim());
                if (n == 1)
                {
                    MessageBox.Show("修改成功！", "操作提示",
 MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    FormUpdateBook fb=(FormUpdateBook)FormActivity.f;
                    fb.load_dataGridView();

                }
                else
                {

                }
            }
        }
    }
}
