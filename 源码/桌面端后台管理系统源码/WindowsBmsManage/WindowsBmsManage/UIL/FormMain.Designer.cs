namespace WindowsBmsManage.UIL
{
    partial class FormMain
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.PersonManageToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.SelectPersonToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.DeletePersonToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.BookManagerToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.BookSingleToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.AddSingleBookToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.DeleteSingleBookToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.SelectborrowToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.BookTypeToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.AddBookToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.DeleteBookToolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.UpdateBookToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.SelectBookInfoToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.ExitToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // menuStrip1
            // 
            this.menuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.PersonManageToolStripMenuItem,
            this.BookManagerToolStripMenuItem,
            this.ExitToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(859, 28);
            this.menuStrip1.TabIndex = 0;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // PersonManageToolStripMenuItem
            // 
            this.PersonManageToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.SelectPersonToolStripMenuItem,
            this.DeletePersonToolStripMenuItem});
            this.PersonManageToolStripMenuItem.Name = "PersonManageToolStripMenuItem";
            this.PersonManageToolStripMenuItem.Size = new System.Drawing.Size(81, 24);
            this.PersonManageToolStripMenuItem.Text = "人员管理";
            // 
            // SelectPersonToolStripMenuItem
            // 
            this.SelectPersonToolStripMenuItem.Name = "SelectPersonToolStripMenuItem";
            this.SelectPersonToolStripMenuItem.Size = new System.Drawing.Size(144, 26);
            this.SelectPersonToolStripMenuItem.Text = "查询人员";
            this.SelectPersonToolStripMenuItem.Click += new System.EventHandler(this.SelectPersonToolStripMenuItem_Click);
            // 
            // DeletePersonToolStripMenuItem
            // 
            this.DeletePersonToolStripMenuItem.Name = "DeletePersonToolStripMenuItem";
            this.DeletePersonToolStripMenuItem.Size = new System.Drawing.Size(144, 26);
            this.DeletePersonToolStripMenuItem.Text = "删除人员";
            this.DeletePersonToolStripMenuItem.Click += new System.EventHandler(this.DeletePersonToolStripMenuItem_Click);
            // 
            // BookManagerToolStripMenuItem
            // 
            this.BookManagerToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.BookSingleToolStripMenuItem,
            this.BookTypeToolStripMenuItem});
            this.BookManagerToolStripMenuItem.Name = "BookManagerToolStripMenuItem";
            this.BookManagerToolStripMenuItem.Size = new System.Drawing.Size(81, 24);
            this.BookManagerToolStripMenuItem.Text = "书籍管理";
            // 
            // BookSingleToolStripMenuItem
            // 
            this.BookSingleToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.AddSingleBookToolStripMenuItem,
            this.DeleteSingleBookToolStripMenuItem,
            this.SelectborrowToolStripMenuItem});
            this.BookSingleToolStripMenuItem.Name = "BookSingleToolStripMenuItem";
            this.BookSingleToolStripMenuItem.Size = new System.Drawing.Size(181, 26);
            this.BookSingleToolStripMenuItem.Text = "单本书籍管理";
            // 
            // AddSingleBookToolStripMenuItem
            // 
            this.AddSingleBookToolStripMenuItem.Name = "AddSingleBookToolStripMenuItem";
            this.AddSingleBookToolStripMenuItem.Size = new System.Drawing.Size(174, 26);
            this.AddSingleBookToolStripMenuItem.Text = "增加复本";
            this.AddSingleBookToolStripMenuItem.Click += new System.EventHandler(this.AddSingleBookToolStripMenuItem_Click);
            // 
            // DeleteSingleBookToolStripMenuItem
            // 
            this.DeleteSingleBookToolStripMenuItem.Name = "DeleteSingleBookToolStripMenuItem";
            this.DeleteSingleBookToolStripMenuItem.Size = new System.Drawing.Size(174, 26);
            this.DeleteSingleBookToolStripMenuItem.Text = "删除书籍";
            this.DeleteSingleBookToolStripMenuItem.Click += new System.EventHandler(this.DeleteSingleBookToolStripMenuItem_Click);
            // 
            // SelectborrowToolStripMenuItem
            // 
            this.SelectborrowToolStripMenuItem.Name = "SelectborrowToolStripMenuItem";
            this.SelectborrowToolStripMenuItem.Size = new System.Drawing.Size(174, 26);
            this.SelectborrowToolStripMenuItem.Text = "查看借阅状态";
            this.SelectborrowToolStripMenuItem.Click += new System.EventHandler(this.SelectborrowToolStripMenuItem_Click);
            // 
            // BookTypeToolStripMenuItem
            // 
            this.BookTypeToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.AddBookToolStripMenuItem,
            this.DeleteBookToolStripMenuItem1,
            this.UpdateBookToolStripMenuItem,
            this.SelectBookInfoToolStripMenuItem});
            this.BookTypeToolStripMenuItem.Name = "BookTypeToolStripMenuItem";
            this.BookTypeToolStripMenuItem.Size = new System.Drawing.Size(181, 26);
            this.BookTypeToolStripMenuItem.Text = "单种书籍管理";
            // 
            // AddBookToolStripMenuItem
            // 
            this.AddBookToolStripMenuItem.Name = "AddBookToolStripMenuItem";
            this.AddBookToolStripMenuItem.Size = new System.Drawing.Size(181, 26);
            this.AddBookToolStripMenuItem.Text = "增加新书";
            this.AddBookToolStripMenuItem.Click += new System.EventHandler(this.AddBookToolStripMenuItem_Click);
            // 
            // DeleteBookToolStripMenuItem1
            // 
            this.DeleteBookToolStripMenuItem1.Name = "DeleteBookToolStripMenuItem1";
            this.DeleteBookToolStripMenuItem1.Size = new System.Drawing.Size(181, 26);
            this.DeleteBookToolStripMenuItem1.Text = "删除书籍";
            this.DeleteBookToolStripMenuItem1.Click += new System.EventHandler(this.DeleteBookToolStripMenuItem1_Click);
            // 
            // UpdateBookToolStripMenuItem
            // 
            this.UpdateBookToolStripMenuItem.Name = "UpdateBookToolStripMenuItem";
            this.UpdateBookToolStripMenuItem.Size = new System.Drawing.Size(181, 26);
            this.UpdateBookToolStripMenuItem.Text = "更改书籍信息";
            this.UpdateBookToolStripMenuItem.Click += new System.EventHandler(this.UpdateBookToolStripMenuItem_Click);
            // 
            // SelectBookInfoToolStripMenuItem
            // 
            this.SelectBookInfoToolStripMenuItem.Name = "SelectBookInfoToolStripMenuItem";
            this.SelectBookInfoToolStripMenuItem.Size = new System.Drawing.Size(181, 26);
            this.SelectBookInfoToolStripMenuItem.Text = "查询书籍信息";
            this.SelectBookInfoToolStripMenuItem.Click += new System.EventHandler(this.SelectBookInfoToolStripMenuItem_Click);
            // 
            // ExitToolStripMenuItem
            // 
            this.ExitToolStripMenuItem.Name = "ExitToolStripMenuItem";
            this.ExitToolStripMenuItem.Size = new System.Drawing.Size(81, 24);
            this.ExitToolStripMenuItem.Text = "退出系统";
            this.ExitToolStripMenuItem.Click += new System.EventHandler(this.ExitToolStripMenuItem_Click);
            // 
            // FormMain
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(859, 490);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "FormMain";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "百书馆后台管理系统";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.FormMain_FormClosing);
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.frmMain_FormClosed);
            this.Load += new System.EventHandler(this.FormMain_Load);
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem PersonManageToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem SelectPersonToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem DeletePersonToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem BookManagerToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem ExitToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem BookSingleToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem BookTypeToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem AddSingleBookToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem DeleteSingleBookToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem AddBookToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem DeleteBookToolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem UpdateBookToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem SelectBookInfoToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem SelectborrowToolStripMenuItem;
    }
}