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
    public partial class FormLogin : Form
    {
        private Point mPoint = new Point();
        int status;

        private void panellogin_MouseDown(object sender, MouseEventArgs e)
        {
            mPoint.X = e.X;
            mPoint.Y = e.Y;
        }

        private void panellogin_MouseMove(object sender, MouseEventArgs e)
        {
            if (e.Button == MouseButtons.Left)
            {
                Point myPosittion = MousePosition;
                myPosittion.Offset(-mPoint.X, -mPoint.Y);
                Location = myPosittion;
            }
        }
        public FormLogin()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            LoginBll lb = new LoginBll();
            string name = tb_username.Text;
            string pwd = tb_password.Text;
         int i=   lb.Login(name,pwd);
            if (i == 0)
            {
                MessageBox.Show("用户名或密码不正确！", "登录失败",
                    MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }else if (i == 1)
            {
                FormMain fm = new FormMain();
                fm.Show();
                this.Hide();
            }else if (i == 2)
            {
                MessageBox.Show("用户名不能为空！", "登录失败",
                    MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
            }
            else if(i==3)
            {
                MessageBox.Show("密码不能为空！", "登录失败",
                   MessageBoxButtons.OK, MessageBoxIcon.Exclamation);

            }
         }

        private void BtnMin_Click(object sender, EventArgs e)
        {
            WindowState = FormWindowState.Minimized;
        }

        private void BtnClose_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void panel_login_Paint(object sender, PaintEventArgs e)
        {

        }
    }
}
