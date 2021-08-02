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
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class QuanLyHocVien2Frm extends javax.swing.JFrame {

    Integer MaKH;
    HocVienDao dao = new HocVienDao();
    NguoiHocDao nhdao = new NguoiHocDao();

    /**
     * Creates new form QuanLyHocVien2Frm
     */
    public QuanLyHocVien2Frm() {
        initComponents();
        init();
        fillComboBox();
        fillGridView();

    }

    public QuanLyHocVien2Frm(Integer MaKH) {
        initComponents();
        this.MaKH = MaKH;
        init();
        fillComboBox();
        fillGridView();
    }

    void init() {
        // setIconImage(ShareHelper.APP_ICON); 
        setLocationRelativeTo(null);
    }

    void fillComboBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNguoiHoc.getModel();
        model.removeAllElements();
        try {
            List<NguoiHoc> list = nhdao.selectByCourse(MaKH);
            for (NguoiHoc nh : list) {
                model.addElement(nh);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn học viên!");
        }
    }

    void fillGridView() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        try {
            String sql = "SELECT hv.*, nh.HoTen FROM HocVien hv "
                    + " JOIN NguoiHoc nh ON nh.MaNH=hv.MaNH WHERE MaKH=?";
            ResultSet rs = JdbcHelper.executeQuery(sql, MaKH);
            while (rs.next()) {
                double diem = rs.getDouble("Diem");
                Object[] row = {
                    rs.getInt("MaHV"), rs.getString("MaNH"),
                    rs.getString("HoTen"), diem, false
                };
                if (rdoTatCa.isSelected()) {
                    model.addRow(row);
                } else if (rdoDaNhap.isSelected() && diem >= 0) {
                    model.addRow(row);
                } else if (rdoChuaNhap.isSelected() && diem < 0) {
                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            DialogHelper.alert(this, "Lỗi truy vấn học viên!");
        }
    }
    boolean flag = false;

    void check() {
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
    }

    void insert() {
        NguoiHoc nguoiHoc = (NguoiHoc) cboNguoiHoc.getSelectedItem();

        HocVien model = new HocVien();
        model.setMaKH(MaKH);
        model.setMaNH(nguoiHoc.getMaNH());
        model.setDiem(Double.valueOf(txtDiem.getText()));

        try {
            dao.insert(model);
            fillComboBox();
            fillGridView();
            DialogHelper.alert(this, "Thêm thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi thêm học viên vào khóa học!");
        }
    }

    void update() {

        for (int i = 0; i < jTable1.getRowCount(); i++) {
            Integer mahv = (Integer) jTable1.getValueAt(i, 0);
            String manh = (String) jTable1.getValueAt(i, 1);
            Double diem = (Double) jTable1.getValueAt(i, 3);
            Boolean isDelete = (Boolean) jTable1.getValueAt(i, 4);
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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        rdoTatCa = new javax.swing.JRadioButton();
        rdoDaNhap = new javax.swing.JRadioButton();
        rdoChuaNhap = new javax.swing.JRadioButton();
        btnCapnhatHocVien = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cboNguoiHoc = new javax.swing.JComboBox<>();
        txtDiem = new javax.swing.JTextField();
        btnThemHocVienKhac = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HỌC VIÊN TRONG CỦA KHÓA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(102, 0, 102))); // NOI18N

        jTable1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "MÃ HV", "MÃ NH", "HỌ VÀ TÊN", "ĐIỂM", "XÓA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setSelectionBackground(new java.awt.Color(102, 0, 102));
        jScrollPane1.setViewportView(jTable1);

        buttonGroup1.add(rdoTatCa);
        rdoTatCa.setText("Tất cả");
        rdoTatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoTatCaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoDaNhap);
        rdoDaNhap.setText("Đã nhập điểm");
        rdoDaNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoDaNhapActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoChuaNhap);
        rdoChuaNhap.setText("Chưa nhập điểm");

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(rdoTatCa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdoDaNhap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoChuaNhap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCapnhatHocVien)
                .addGap(29, 29, 29))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCapnhatHocVien)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdoTatCa)
                        .addComponent(rdoDaNhap)
                        .addComponent(rdoChuaNhap)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "HỌC VIÊN KHÁC", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18), new java.awt.Color(102, 0, 102))); // NOI18N

        cboNguoiHoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(cboNguoiHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThemHocVienKhac, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnThemHocVienKhac)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboNguoiHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void btnThemHocVienKhacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHocVienKhacActionPerformed
        // TODO add your handling code here:
        check();
        if (flag == true) {
            insert();
            txtDiem.setText("");
        }
    }//GEN-LAST:event_btnThemHocVienKhacActionPerformed

    private void btnCapnhatHocVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapnhatHocVienActionPerformed
        // TODO add your handling code here:
        if (txtDiem.getText().equals("")) {
            update();
        } else {
            check();
            if (flag == true) {
                update();
            }        // TODO add your handling code here:
        }
    }//GEN-LAST:event_btnCapnhatHocVienActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLyHocVien2Frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyHocVien2Frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyHocVien2Frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyHocVien2Frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyHocVien2Frm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapnhatHocVien;
    private javax.swing.JButton btnThemHocVienKhac;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboNguoiHoc;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton rdoChuaNhap;
    private javax.swing.JRadioButton rdoDaNhap;
    private javax.swing.JRadioButton rdoTatCa;
    private javax.swing.JTextField txtDiem;
    // End of variables declaration//GEN-END:variables
}
