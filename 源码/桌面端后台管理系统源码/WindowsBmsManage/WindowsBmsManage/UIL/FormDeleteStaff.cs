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
    public partial class FormDeleteStaff : Form
    {
        public FormDeleteStaff()
        {
            InitializeComponent();
        }
        string info;
        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            info = tb_staffinfo.Text.Trim();
            if (!info.Equals(""))
            {
                PersonBll pb = new PersonBll();
                DataTable result = (DataTable)pb.selectSingleStaff(info);
                dataGridView1.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.EnableResizing;
                dataGridView1.ColumnHeadersVisible = true;
                if (result != null && result.Rows.Count > 0)
                {
                    dataGridView1.DataSource = result;
                    label3.DataBindings.Clear();
                    label3.DataBindings.Add("Text", result,"employnum");
                }
                else if (result == null)
                {
                    this.Dispose();
                    this.Close();
                }
                else
                {
                    dataGridView1.ColumnHeadersVisible = false;
                    dataGridView1.DataSource = null;
                    label3.DataBindings.Clear();
                    label3.Text = "";
                    MessageBox.Show("没有查询到信息！", "操作提示",
                   MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                }
            }
            else
            {
                dataGridView1.ColumnHeadersVisible = false;
                dataGridView1.DataSource = null;
                label3.DataBindings.Clear();
                label3.Text = "";
                MessageBox.Show("请输入要员工信息！", "操作提示",
                                MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
         

        }

        private void FormDeleteStaff_Load(object sender, EventArgs e)
        {
            dataGridView1.ColumnHeadersVisible = false;
            dataGridView1.AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill;
            dataGridView1.DataSource = null;
            label3.Visible = false;
            label3.Text = "";
        }

        private void panel1_Paint(object sender, PaintEventArgs e)
        {

        }

        private void btn_delete_Click(object sender, EventArgs e)
        {
            if (label3.Text.Equals(""))
            {
                MessageBox.Show("请先查询出要操作的员工信息！", "操作提示",
                                                         MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
            else
            {
                DialogResult dr;
                dr = MessageBox.Show("确定要删除编号为"+label3.Text+"的员工吗？", "确认退出",
                                          MessageBoxButtons.OKCancel, MessageBoxIcon.Exclamation);
                if (dr == DialogResult.OK)
                {
                    PersonBll pb = new PersonBll();
                    int i = pb.deleteStaff(label3.Text);
                    if (i == -1)
                    {
                        MessageBox.Show("删除失败！", "操作提示",
                                                         MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    }
                    else if (i == 0)
                    {
                        MessageBox.Show("删除失败！", "操作提示",
                                                          MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    }
                    else
                    {
                        MessageBox.Show("删除成功！", "操作提示",
                                   MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                        if (!info.Equals(""))
                        {
                            PersonBll pb1 = new PersonBll();
                            DataTable result = (DataTable)pb1.selectSingleStaff(info);
                            dataGridView1.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.EnableResizing;
                            dataGridView1.ColumnHeadersVisible = true;
                            if (result != null && result.Rows.Count > 0)
                            {
                                dataGridView1.DataSource = result;
                                label3.DataBindings.Clear();
                                label3.DataBindings.Add("Text", result, "employnum");
                            }
                            else if (result == null)
                            {
                                this.Dispose();
                                this.Close();
                            }
                            else
                            {
                                dataGridView1.ColumnHeadersVisible = false;
                                dataGridView1.DataSource = null;
                                label3.DataBindings.Clear();
                                label3.Text = "";
                             
                            }
                        }
                    }
                }
         
               
            }
        }
    }
}
