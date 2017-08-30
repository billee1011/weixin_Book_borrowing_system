using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using WindowsBmsManage.MODEL;

namespace WindowsBmsManage.UIL
{
    public partial class FormMain : Form
    {
        public FormMain()
        {
            InitializeComponent();
        }

        private void SelectPersonToolStripMenuItem_Click(object sender, EventArgs e)
        {
            FormSelectStaff fss = new FormSelectStaff();
            fss.ShowDialog();
        }

        private void DeletePersonToolStripMenuItem_Click(object sender, EventArgs e)
        {
            FormDeleteStaff fds = new FormDeleteStaff();
            fds.ShowDialog();
        }
        private void frmMain_FormClosed(object sender, FormClosedEventArgs e)
        {
         
                Application.Exit();
      

        }

        private void ExitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            DialogResult dr;
            dr = MessageBox.Show("确定要退出系统？", "确认退出",
                                      MessageBoxButtons.OKCancel, MessageBoxIcon.Exclamation);
            if (dr == DialogResult.OK)
            {
                this.Dispose();
                Application.Exit();
            }
               

        }

        private void FormMain_FormClosing(object sender, FormClosingEventArgs e)
        {

            DialogResult dr;
            dr = MessageBox.Show("确定要退出系统？", "确认退出",
                                   MessageBoxButtons.OKCancel, MessageBoxIcon.Exclamation);
            if (dr == DialogResult.OK)
            {
                this.Dispose();
            }
            else
            {
                e.Cancel = true;
            }
        }

        private void FormMain_Load(object sender, EventArgs e)
        {

        }

     

        private void AddSingleBookToolStripMenuItem_Click(object sender, EventArgs e)
        {
            FormAddSingleBook fasb = new FormAddSingleBook();
            fasb.ShowDialog();
        }

        private void DeleteSingleBookToolStripMenuItem_Click(object sender, EventArgs e)
        {
            FormDeleteSingleBook fdsb = new FormDeleteSingleBook();
            fdsb.ShowDialog();
        }

        private void SelectborrowToolStripMenuItem_Click(object sender, EventArgs e)
        {
            FormSelectState fss = new FormSelectState();
            fss.ShowDialog();
        }

        private void AddBookToolStripMenuItem_Click(object sender, EventArgs e)
        {
            FormAddNewBook fanb = new FormAddNewBook();
            fanb.ShowDialog();
        }

        private void DeleteBookToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            FormDeleteBook fdb = new FormDeleteBook();
            fdb.ShowDialog();

        }

        private void UpdateBookToolStripMenuItem_Click(object sender, EventArgs e)
        {
            FormUpdateBook fub = new FormUpdateBook();
            FormActivity.f = fub;
            fub.ShowDialog();
        }

        private void SelectBookInfoToolStripMenuItem_Click(object sender, EventArgs e)
        {
            FormSelectBook fsb = new FormSelectBook();
            fsb.ShowDialog();
        }
    }
}
