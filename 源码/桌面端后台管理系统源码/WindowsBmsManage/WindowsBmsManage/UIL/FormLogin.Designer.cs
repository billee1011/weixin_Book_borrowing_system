namespace WindowsBmsManage.UIL
{
    partial class FormLogin
    {
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows 窗体设计器生成的代码

        /// <summary>
        /// 设计器支持所需的方法 - 不要修改
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            this.button1 = new System.Windows.Forms.Button();
            this.tb_password = new System.Windows.Forms.TextBox();
            this.tb_username = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.panel_login = new System.Windows.Forms.Panel();
            this.BtnClose = new System.Windows.Forms.Button();
            this.BtnMin = new System.Windows.Forms.Button();
            this.panel_login.SuspendLayout();
            this.SuspendLayout();
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(202, 301);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(119, 29);
            this.button1.TabIndex = 0;
            this.button1.Text = "登陆";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // tb_password
            // 
            this.tb_password.Location = new System.Drawing.Point(236, 224);
            this.tb_password.MaxLength = 20;
            this.tb_password.Name = "tb_password";
            this.tb_password.PasswordChar = '*';
            this.tb_password.Size = new System.Drawing.Size(130, 25);
            this.tb_password.TabIndex = 1;
            this.tb_password.Text = "root";
            // 
            // tb_username
            // 
            this.tb_username.Location = new System.Drawing.Point(236, 164);
            this.tb_username.MaxLength = 20;
            this.tb_username.Name = "tb_username";
            this.tb_username.Size = new System.Drawing.Size(130, 25);
            this.tb_username.TabIndex = 2;
            this.tb_username.Text = "root";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(152, 174);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(52, 15);
            this.label1.TabIndex = 3;
            this.label1.Text = "用户名";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(152, 234);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(37, 15);
            this.label2.TabIndex = 4;
            this.label2.Text = "密码";
            // 
            // label3
            // 
            this.label3.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("黑体", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label3.Location = new System.Drawing.Point(137, 92);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(246, 25);
            this.label3.TabIndex = 5;
            this.label3.Text = "百书馆后台管理系统";
            // 
            // panel_login
            // 
            this.panel_login.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.panel_login.AutoSize = true;
            this.panel_login.Controls.Add(this.BtnClose);
            this.panel_login.Controls.Add(this.BtnMin);
            this.panel_login.Controls.Add(this.label3);
            this.panel_login.Controls.Add(this.label2);
            this.panel_login.Controls.Add(this.button1);
            this.panel_login.Controls.Add(this.label1);
            this.panel_login.Controls.Add(this.tb_password);
            this.panel_login.Controls.Add(this.tb_username);
            this.panel_login.Location = new System.Drawing.Point(0, 2);
            this.panel_login.Name = "panel_login";
            this.panel_login.Size = new System.Drawing.Size(539, 402);
            this.panel_login.TabIndex = 6;
            this.panel_login.Paint += new System.Windows.Forms.PaintEventHandler(this.panel_login_Paint);
            this.panel_login.MouseDown += new System.Windows.Forms.MouseEventHandler(this.panellogin_MouseDown);
            this.panel_login.MouseMove += new System.Windows.Forms.MouseEventHandler(this.panellogin_MouseMove);
            // 
            // BtnClose
            // 
            this.BtnClose.BackColor = System.Drawing.Color.Transparent;
            this.BtnClose.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.BtnClose.ForeColor = System.Drawing.SystemColors.ControlText;
            this.BtnClose.Location = new System.Drawing.Point(503, 0);
            this.BtnClose.Margin = new System.Windows.Forms.Padding(4);
            this.BtnClose.Name = "BtnClose";
            this.BtnClose.Size = new System.Drawing.Size(31, 29);
            this.BtnClose.TabIndex = 11;
            this.BtnClose.Text = "×";
            this.BtnClose.UseVisualStyleBackColor = false;
            this.BtnClose.Click += new System.EventHandler(this.BtnClose_Click);
            // 
            // BtnMin
            // 
            this.BtnMin.BackColor = System.Drawing.Color.Transparent;
            this.BtnMin.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.BtnMin.Location = new System.Drawing.Point(467, 0);
            this.BtnMin.Margin = new System.Windows.Forms.Padding(4);
            this.BtnMin.Name = "BtnMin";
            this.BtnMin.Size = new System.Drawing.Size(31, 29);
            this.BtnMin.TabIndex = 10;
            this.BtnMin.Text = "_";
            this.BtnMin.UseVisualStyleBackColor = false;
            this.BtnMin.Click += new System.EventHandler(this.BtnMin_Click);
            // 
            // FormLogin
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(535, 403);
            this.Controls.Add(this.panel_login);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Name = "FormLogin";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.panel_login.ResumeLayout(false);
            this.panel_login.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.TextBox tb_password;
        private System.Windows.Forms.TextBox tb_username;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Panel panel_login;
        private System.Windows.Forms.Button BtnClose;
        private System.Windows.Forms.Button BtnMin;
    }
}

