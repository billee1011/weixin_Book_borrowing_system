namespace WindowsBmsManage.UIL
{
    partial class FormDeleteStaff
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
            this.panel1 = new System.Windows.Forms.Panel();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.button1 = new System.Windows.Forms.Button();
            this.tb_staffinfo = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.btn_delete = new System.Windows.Forms.Button();
            this.employnum = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.name = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.phone = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // panel1
            // 
            this.panel1.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.panel1.Controls.Add(this.label3);
            this.panel1.Controls.Add(this.label2);
            this.panel1.Controls.Add(this.dataGridView1);
            this.panel1.Controls.Add(this.button1);
            this.panel1.Controls.Add(this.tb_staffinfo);
            this.panel1.Controls.Add(this.label1);
            this.panel1.Controls.Add(this.btn_delete);
            this.panel1.Location = new System.Drawing.Point(1, -1);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(519, 352);
            this.panel1.TabIndex = 4;
            this.panel1.Paint += new System.Windows.Forms.PaintEventHandler(this.panel1_Paint);
            // 
            // dataGridView1
            // 
            this.dataGridView1.AllowUserToAddRows = false;
            this.dataGridView1.AllowUserToDeleteRows = false;
            this.dataGridView1.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.employnum,
            this.name,
            this.phone});
            this.dataGridView1.Location = new System.Drawing.Point(0, 0);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.ReadOnly = true;
            this.dataGridView1.RowTemplate.Height = 27;
            this.dataGridView1.Size = new System.Drawing.Size(520, 150);
            this.dataGridView1.TabIndex = 5;
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(139, 258);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 32);
            this.button1.TabIndex = 7;
            this.button1.Text = "查询";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // tb_staffinfo
            // 
            this.tb_staffinfo.Location = new System.Drawing.Point(286, 184);
            this.tb_staffinfo.MaxLength = 10;
            this.tb_staffinfo.Name = "tb_staffinfo";
            this.tb_staffinfo.Size = new System.Drawing.Size(139, 25);
            this.tb_staffinfo.TabIndex = 6;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(103, 187);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(157, 15);
            this.label1.TabIndex = 5;
            this.label1.Text = "请输入员工编号或姓名";
            // 
            // btn_delete
            // 
            this.btn_delete.Location = new System.Drawing.Point(302, 258);
            this.btn_delete.Name = "btn_delete";
            this.btn_delete.Size = new System.Drawing.Size(75, 32);
            this.btn_delete.TabIndex = 4;
            this.btn_delete.Text = "删除";
            this.btn_delete.UseVisualStyleBackColor = true;
            this.btn_delete.Click += new System.EventHandler(this.btn_delete_Click);
            // 
            // employnum
            // 
            this.employnum.DataPropertyName = "employnum";
            this.employnum.HeaderText = "员工编号";
            this.employnum.Name = "employnum";
            this.employnum.ReadOnly = true;
            this.employnum.Width = 159;
            // 
            // name
            // 
            this.name.DataPropertyName = "name";
            this.name.HeaderText = "员工姓名";
            this.name.Name = "name";
            this.name.ReadOnly = true;
            this.name.Width = 159;
            // 
            // phone
            // 
            this.phone.DataPropertyName = "phone";
            this.phone.HeaderText = "手机号码";
            this.phone.Name = "phone";
            this.phone.ReadOnly = true;
            this.phone.Width = 159;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(14, 187);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(0, 15);
            this.label2.TabIndex = 8;
            this.label2.Visible = false;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(14, 267);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(55, 15);
            this.label3.TabIndex = 9;
            this.label3.Text = "label3";
            // 
            // FormDeleteStaff
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(518, 351);
            this.Controls.Add(this.panel1);
            this.Name = "FormDeleteStaff";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "删除人员";
            this.Load += new System.EventHandler(this.FormDeleteStaff_Load);
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.TextBox tb_staffinfo;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button btn_delete;
        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.DataGridViewTextBoxColumn employnum;
        private System.Windows.Forms.DataGridViewTextBoxColumn name;
        private System.Windows.Forms.DataGridViewTextBoxColumn phone;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
    }
}