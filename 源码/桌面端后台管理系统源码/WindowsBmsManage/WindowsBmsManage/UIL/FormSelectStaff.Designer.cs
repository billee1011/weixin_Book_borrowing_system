namespace WindowsBmsManage.UIL
{
    partial class FormSelectStaff
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
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.Column1 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Column2 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Column3 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.btn_export = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridView1
            // 
            this.dataGridView1.AllowUserToAddRows = false;
            this.dataGridView1.AllowUserToDeleteRows = false;
            this.dataGridView1.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.dataGridView1.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.Column1,
            this.Column2,
            this.Column3});
            this.dataGridView1.Location = new System.Drawing.Point(0, 0);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.ReadOnly = true;
            this.dataGridView1.RowTemplate.Height = 27;
            this.dataGridView1.Size = new System.Drawing.Size(689, 337);
            this.dataGridView1.TabIndex = 0;
            // 
            // Column1
            // 
            this.Column1.DataPropertyName = "employnum";
            this.Column1.HeaderText = "员工编号";
            this.Column1.Name = "Column1";
            this.Column1.ReadOnly = true;
            this.Column1.Width = 215;
            // 
            // Column2
            // 
            this.Column2.DataPropertyName = "name";
            this.Column2.HeaderText = "员工姓名";
            this.Column2.Name = "Column2";
            this.Column2.ReadOnly = true;
            this.Column2.Width = 216;
            // 
            // Column3
            // 
            this.Column3.DataPropertyName = "phone";
            this.Column3.HeaderText = "手机号码";
            this.Column3.Name = "Column3";
            this.Column3.ReadOnly = true;
            this.Column3.Width = 215;
            // 
            // btn_export
            // 
            this.btn_export.Anchor = System.Windows.Forms.AnchorStyles.None;
            this.btn_export.AutoSize = true;
            this.btn_export.Location = new System.Drawing.Point(281, 368);
            this.btn_export.Name = "btn_export";
            this.btn_export.Size = new System.Drawing.Size(128, 37);
            this.btn_export.TabIndex = 1;
            this.btn_export.Text = "导出到Excel表";
            this.btn_export.UseVisualStyleBackColor = true;
            this.btn_export.Click += new System.EventHandler(this.btn_export_Click);
            // 
            // FormSelectStaff
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(690, 428);
            this.Controls.Add(this.btn_export);
            this.Controls.Add(this.dataGridView1);
            this.Name = "FormSelectStaff";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "查询人员";
            this.Load += new System.EventHandler(this.FormSelectStaff_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.Button btn_export;
        private System.Windows.Forms.DataGridViewTextBoxColumn Column1;
        private System.Windows.Forms.DataGridViewTextBoxColumn Column2;
        private System.Windows.Forms.DataGridViewTextBoxColumn Column3;
    }
}