/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.ui;

import edu.poly.dao.HocVienDao;
import edu.poly.dao.NguoiHocDao;
import edu.poly.helper.DialogHelper;
import edu.poly.helper.JdbcHelper;
import edu.poly.model.HocVien;
import edu.poly.model.NguoiHoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class QuanLyHocVienFrm extends javax.swing.JFrame {
    public Integer MaKH;
    HocVienDao dao = new HocVienDao();
    NguoiHocDao nhdao = new NguoiHocDao();
    /**
     * Creates new form QuanLyHocVienFrm
     */
    public QuanLyHocVienFrm() throws Exception {
        initComponents();
            init();
         fillComboBox();
        fillGridView();

    }

     QuanLyHocVienFrm(Integer id)throws Exception {
        MaKH = id;
        init();
        
    }
    

       void init() {
      
        setLocationRelativeTo(null);
     
        
    }

    void fillComboBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboHocVien.getModel();
        model.removeAllElements();
        try {
            List<NguoiHoc> list = nhdao.selectByCourse(MaKH);
            for (NguoiHoc nh : list) {
                model.addElement(nh);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn học viên!");
            e.printStackTrace();
        }
    }

    void fillGridView() {
        DefaultTableModel model = (DefaultTableModel) tblHocVien.getModel();
        model.setRowCount(0);
        try {
            String sql = "SELECT hv.*, nh.HoTen FROM dbo.HocVien hv"
                    + " JOIN dbo.NguoiHoc nh ON nh.MaNH = hv.MaNH WHERE MaKH = ?";
            ResultSet rs = JdbcHelper.executeQuery(sql, MaKH);
            while (rs.next()) {
                double diem = rs.getDouble("Diem");
                Object[] row = {
                    rs.getInt("MaHV"),
                    rs.getString("MaNH"),
                    rs.getString("HoTen"),
                    diem,
                    false
                };
                if (rdoTatCa.isSelected()) {
                    model.addRow(row);
                } else if (rdoDaNhap.isSelected() && diem > 0) {
                    model.addRow(row);
                } else if (rdoChuaNhap.isSelected() && diem < 0) {
                    model.addRow(row);
                }

            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn học viên");
        }
    }
    boolean flag = false;

    void check() {
        try {
            if (txtDiem.getText().length() > 2) {
                DialogHelper.alert(this, "Điểm chỉ nhập từ 0 đến 10");
            } else {
                if (txtDiem.getText().length() == 0) {
                    DialogHelper.alert(this, "Hãy nhập vào điểm");
                } else {
                    int diem = Integer.parseInt(txtDiem.getText());
                    if (diem < 0 || diem > 10) {
                        DialogHelper.alert(this, "Điểm chỉ nhập từ 0 đến 10");
                    } else {
                        flag = true;
                    }
                }
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Hãy nhập điểm là số");
        }
    }

    void insert() {

        try {
            NguoiHoc nguoiHoc = (NguoiHoc) cboHocVien.getSelectedItem();

            HocVien model = new HocVien();

            model.setMaKH(MaKH);
            model.setMaNH(nguoiHoc.getMaNH());
            model.setDiem(Double.valueOf(txtDiem.getText()));

            dao.insert(model);
            fillComboBox();
            fillGridView();
            DialogHelper.alert(this, "Thêm thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi thêm học viên vào khóa học!");
            e.getMessage();
        }
    }

    void update() {

        for (int i = 0; i < tblHocVien.getRowCount(); i++) {
            Integer mahv = (Integer) tblHocVien.getValueAt(i, 0);
            String manh = (String) tblHocVien.getValueAt(i, 1);
            Double diem = (Double) tblHocVien.getValueAt(i, 3);
            Boolean isDelete = (Boolean) tblHocVien.getValueAt(i, 4);
            if (diem < 0 || diem > 10) {
                DialogHelper.alert(this, "Điểm truyền vào không hợp lệ không");
            }
            if (isDelete) {
                dao.delete(mahv);
            } else {
                HocVien model = new HocVien();
                model.setMaHV(mahv);
                model.setMaKH(MaKH);
                model.setMaNH(manh);
                model.setDiem(diem);

                dao.update(model);
            }
        }
        this.fillComboBox();
        this.fillGridView();
        DialogHelper.alert(this, "Cập nhật thành công!");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        cboHocVien = new javax.swing.JComboBox<>();
        txtDiem = new javax.swing.JTextField();
        btnThemHocVienKhac = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHocVien = new javax.swing.JTable();
        rdoTatCa = new javax.swing.JRadioButton();
        rdoDaNhap = new javax.swing.JRadioButton();
        rdoChuaNhap = new javax.swing.JRadioButton();
        btnCapnhatHocVien = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        lblquanlynguoihoc = new javax.swing.JLabel();
        lblquanlynguoihoc1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cboHocVien.setForeground(new java.awt.Color(153, 0, 153));
        jPanel2.add(cboHocVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 634, 50));
        jPanel2.add(txtDiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 34, 57, -1));

        btnThemHocVienKhac.setBackground(new java.awt.Color(255, 255, 255));
        btnThemHocVienKhac.setForeground(new java.awt.Color(255, 255, 255));
        btnThemHocVienKhac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgButtonThemHover.png"))); // NOI18N
        btnThemHocVienKhac.setBorder(null);
        btnThemHocVienKhac.setBorderPainted(false);
        btnThemHocVienKhac.setContentAreaFilled(false);
        btnThemHocVienKhac.setDefaultCapable(false);
        btnThemHocVienKhac.setFocusPainted(false);
        btnThemHocVienKhac.setFocusable(false);
        btnThemHocVienKhac.setIconTextGap(0);
        btnThemHocVienKhac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHocVienKhacActionPerformed(evt);
            }
        });
        jPanel2.add(btnThemHocVienKhac, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 19, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblHocVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblHocVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã HV", "Mã NH", "Họ Tên", "Điểm", "Xóa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblHocVien.setSelectionBackground(new java.awt.Color(102, 0, 102));
        jScrollPane1.setViewportView(tblHocVien);

        buttonGroup1.add(rdoTatCa);
        rdoTatCa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoTatCa.setText("Tất Cả");
        rdoTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTatCaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoDaNhap);
        rdoDaNhap.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoDaNhap.setText("Đã Nhập Điểm");
        rdoDaNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDaNhapActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoChuaNhap);
        rdoChuaNhap.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoChuaNhap.setText("Chưa Nhập Điểm");
        rdoChuaNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoChuaNhapActionPerformed(evt);
            }
        });

        btnCapnhatHocVien.setBackground(new java.awt.Color(255, 255, 255));
        btnCapnhatHocVien.setForeground(new java.awt.Color(255, 255, 255));
        btnCapnhatHocVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgButtonCapNhatHover.png"))); // NOI18N
        btnCapnhatHocVien.setBorder(null);
        btnCapnhatHocVien.setBorderPainted(false);
        btnCapnhatHocVien.setContentAreaFilled(false);
        btnCapnhatHocVien.setDefaultCapable(false);
        btnCapnhatHocVien.setFocusPainted(false);
        btnCapnhatHocVien.setFocusable(false);
        btnCapnhatHocVien.setIconTextGap(0);
        btnCapnhatHocVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapnhatHocVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(rdoTatCa)
                .addGap(18, 18, 18)
                .addComponent(rdoDaNhap)
                .addGap(18, 18, 18)
                .addComponent(rdoChuaNhap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCapnhatHocVien, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoTatCa)
                        .addComponent(rdoDaNhap)
                        .addComponent(rdoChuaNhap))
                    .addComponent(btnCapnhatHocVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        lblquanlynguoihoc.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblquanlynguoihoc.setForeground(new java.awt.Color(102, 0, 102));
        lblquanlynguoihoc.setText("QUẢN LÝ HỌC VIÊN KHÁC");

        lblquanlynguoihoc1.setBackground(new java.awt.Color(255, 255, 255));
        lblquanlynguoihoc1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblquanlynguoihoc1.setForeground(new java.awt.Color(102, 0, 102));
        lblquanlynguoihoc1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblquanlynguoihoc1.setText("Học Viên Trong Khóa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator5)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblquanlynguoihoc, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(372, 372, 372)
                .addComponent(lblquanlynguoihoc1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblquanlynguoihoc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(lblquanlynguoihoc1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdoTatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoTatCaActionPerformed
        // TODO add your handling code here:
        this.fillGridView();
    }//GEN-LAST:event_rdoTatCaActionPerformed

    private void rdoDaNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoDaNhapActionPerformed
        // TODO add your handling code here:
        this.fillGridView();
    }//GEN-LAST:event_rdoDaNhapActionPerformed

    private void rdoChuaNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoChuaNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoChuaNhapActionPerformed

    private void btnCapnhatHocVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapnhatHocVienActionPerformed
        // TODO add your handling code here:
        if (txtDiem.getText().equals("")) {
              DialogHelper.alert(this, "Không để trống điểm");
        } else {
            check();
            if (flag == true) {
                update();
            }
        }
    }//GEN-LAST:event_btnCapnhatHocVienActionPerformed

    private void btnThemHocVienKhacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHocVienKhacActionPerformed
        // TODO add your handling code here:
        check();
        if (flag == true) {
            insert();
        }
    }//GEN-LAST:event_btnThemHocVienKhacActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
       this.fillComboBox();
       this.fillGridView();
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLyHocVienFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyHocVienFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyHocVienFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyHocVienFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new QuanLyHocVienFrm().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(QuanLyHocVienFrm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapnhatHocVien;
    private javax.swing.JButton btnThemHocVienKhac;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboHocVien;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lblquanlynguoihoc;
    private javax.swing.JLabel lblquanlynguoihoc1;
    private javax.swing.JRadioButton rdoChuaNhap;
    private javax.swing.JRadioButton rdoDaNhap;
    private javax.swing.JRadioButton rdoTatCa;
    private javax.swing.JTable tblHocVien;
    private javax.swing.JTextField txtDiem;
    // End of variables declaration//GEN-END:variables
}
