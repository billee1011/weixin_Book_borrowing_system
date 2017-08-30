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
    public partial class FormUpdateBook : Form
    {
        public FormUpdateBook()
        {
            InitializeComponent();
        }
        BookBll bb = new BookBll();
        string info;
        private void button1_Click(object sender, EventArgs e)
        {
             info = textBox1.Text.ToString().Trim();
            if (info.Equals(""))
            {
                MessageBox.Show("请输入书籍的编号或书名！", "操作提示",
   MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
            else
            {
               DataTable dt= bb.SelectForUpdate(info);
                if (dt != null)
                {
                    dataGridView1.ColumnHeadersVisible = true;
                    dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.ColumnHeader;
                    dataGridView1.DataSource = dt;
                    init();
                    lable_bind(dt);

                }
            }
        }

        private void FormUpdateBook_Load(object sender, EventArgs e)
        {
            dataGridView1.ColumnHeadersVisible = false;
            dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            init();
            init_invisible();
        }
        public void init()
        {
            label2.DataBindings.Clear();
            label3.DataBindings.Clear();
            label4.DataBindings.Clear();
            label5.DataBindings.Clear();
            label6.DataBindings.Clear();
            label7.DataBindings.Clear();
            label8.DataBindings.Clear();
            label9.DataBindings.Clear();
            label10.DataBindings.Clear();
            label11.DataBindings.Clear();
            label12.DataBindings.Clear();
            label13.DataBindings.Clear();
            label14.DataBindings.Clear();
            label15.DataBindings.Clear();
            label2.Text = "";
            label3.Text = "";
            label4.Text = "";
            label5.Text = "";
            label6.Text = "";
            label7.Text = "";
            label8.Text = "";
            label9.Text = "";
            label10.Text = "";
            label11.Text = "";
            label12.Text = "";
            label13.Text = "";
            label14.Text = "";
            label15.Text = "";
        }
        public void init_invisible()
        {
            label2.Visible = false;
            label3.Visible = false;
            label4.Visible = false;
            label5.Visible = false;
            label6.Visible = false;
            label7.Visible = false;
            label8.Visible = false;
            label9.Visible = false;
            label10.Visible = false;
            label11.Visible = false;
            label12.Visible = false;
            label13.Visible = false;
            label14.Visible = false;
            label15.Visible = false;
        }
        public void lable_bind(DataTable dt)
        {
            label2.DataBindings.Add("Text", dt, "bookname");
            label3.DataBindings.Add("Text", dt, "ISBN");
            label4.DataBindings.Add("Text", dt, "author");
            label5.DataBindings.Add("Text", dt, "pcategory");
            label6.DataBindings.Add("Text", dt, "category");
            label7.DataBindings.Add("Text", dt, "publisher");
            label8.DataBindings.Add("Text", dt, "printingtime");
            label9.DataBindings.Add("Text", dt, "img");
            label10.DataBindings.Add("Text", dt, "booksize");
            label11.DataBindings.Add("Text", dt, "Pinyin");
            label12.DataBindings.Add("Text", dt, "callnumber");
            label13.DataBindings.Add("Text", dt, "initial");
            label14.DataBindings.Add("Text", dt, "collectionplace");
            label15.DataBindings.Add("Text", dt, "simpintroduce");
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (label3.Text.ToString().Trim().Equals(""))
            {
                MessageBox.Show("请先查询书籍信息！", "操作提示",
  MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
            else
            {
                Book.bookname = label2.Text;
                Book.isbn = label3.Text;
                Book.author = label4.Text;
                Book.pcategory = label5.Text;
                Book.category = label6.Text;
                Book.publisher = label7.Text;
                Book.printingtime = label8.Text;
                Book.img = label9.Text;
                Book.booksize = label10.Text;
                Book.Pinyin = label11.Text;
                Book.callnumber = label12.Text;
                Book.initial = label13.Text;
                Book.collectionplace = label14.Text;
                Book.simpintroduce = label15.Text;
               
                FormUpdateBook2 fub2 = new FormUpdateBook2();
                fub2.ShowDialog();
            }
          
        }
        public void load_dataGridView()
        {
            DataTable dt = bb.SelectForUpdate(info);
            if (dt != null)
            {
                dataGridView1.ColumnHeadersVisible = true;
                dataGridView1.DataSource = dt;
                init();
                lable_bind(dt);

            }
        }
    }
}
