/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.ui;

import edu.poly.dao.NhanVienDao;
import edu.poly.helper.DialogHelper;
import edu.poly.helper.ShareHelper;
import edu.poly.model.NhanVien;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class QuanLyNhanVienFrm extends javax.swing.JFrame {

    DefaultTableModel tblModel;

    /**
     * Creates new form QuanLyNhanVienFrm
     */
    public QuanLyNhanVienFrm() {
        initComponents();
        tblModel = (DefaultTableModel) tblNhanvien.getModel();
         this.load();
        this.setStatus(true);
        initTable();
    }
    void initTable(){
        tblModel.setColumnIdentifiers(new String[]{"Mã Nhân Viên", "Mật Khẩu" ,"Họ Tên", "Vai Trò "});
        tblNhanvien.setModel(tblModel);
    }
    

    private void loadData() {
        try {
            while (tblModel.getRowCount() > 0) {
                tblModel.removeRow(0);
            }
            NhanVienDao dao = new NhanVienDao();
            List<NhanVien> list = dao.select();
            for (NhanVien nhanvien : list) {
                Vector row = new Vector();
                row.add(nhanvien.getMaNV());
                row.add(nhanvien.getHoTen());
                row.add(nhanvien.getMatKhau());
                row.add(nhanvien.getVaiTro());

                tblModel.addRow(row);
            }

            tblModel.fireTableDataChanged();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    int index = 0; // vị trí của nhân viên đang hiển thị trên form 
    NhanVienDao dao = new NhanVienDao();

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblNhanvien.getModel();
        model.setRowCount(0);
        try {
            List<NhanVien> list = dao.select();
            for (NhanVien nv : list) {
                Object[] row = {
                    nv.getMaNV(),
                    nv.getMatKhau(),
                    nv.getHoTen(),
                    nv.getVaiTro() ? "Trưởng phòng" : "Nhân viên"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void setStatus(boolean insertable) {// set các  trạng thái của button
       // txtmanhanvien.setEditable(insertable);
        btnThemnhanvien.setEnabled(insertable);
        btnCapnhatnhanvien.setEnabled(!insertable);
        btnXoanhanvien.setEnabled(!insertable);

        boolean first = this.index > 0;
        boolean last = this.index < tblNhanvien.getRowCount() - 1;
        btnPrev.setEnabled(!insertable && first);
        btnFrist.setEnabled(!insertable && first);
        btnLast.setEnabled(!insertable && last);
        btnNext.setEnabled(!insertable && last);
    }

    NhanVien getModel() {
        NhanVien model = new NhanVien();
        model.setMaNV(txtmanhanvien.getText());
        model.setHoTen(txtHoten.getText());
        model.setMatKhau(new String(txtpassword.getPassword()));
        model.setVaiTro(rbtntruongphong.isSelected());
        return model;
    }
    void setModel(NhanVien model) {
        txtmanhanvien.setText(model.getMaNV());
        txtHoten.setText(model.getHoTen());
        txtpassword.setText(model.getMatKhau());
        txtxacnhanpassword.setText(model.getMatKhau());
        rbtntruongphong.setSelected(model.getVaiTro());
        rbtnnhanvien.setSelected(!model.getVaiTro());
    }
    void clear() {
        this.setModel(new NhanVien());
        this.setStatus(true);
    }
     void edit() {
        try {
            String manv = (String) tblNhanvien.getValueAt(this.index, 0);
            NhanVien model = dao.findById(manv);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
       void delete() {
        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa nhân viên này?")) {
            String manv = txtmanhanvien.getText();
            try {
                dao.delete(manv);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại!");
            }
        }
    }
        void update() {
        NhanVien model = getModel();

        String confirm = new String(txtxacnhanpassword.getPassword());
        if (!confirm.equals(model.getMatKhau())) {
            DialogHelper.alert(this, "Xác nhận mật khẩu không đúng!");
        } else {
            try {
                dao.update(model);
                this.load();
                DialogHelper.alert(this, "Cập nhật thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Cập nhật thất bại!");
            }
        }
    }
        void insert() {
        NhanVien model = getModel();

        String confirm = new String(txtxacnhanpassword.getPassword());
        if (confirm.equals(model.getMatKhau())) {
            try {
                dao.insert(model);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Thêm mới thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Mã nhân viên đã trùng");
            }
        } else {
            DialogHelper.alert(this, "Xác nhận mật khẩu không đúng!");
        }
    }
         boolean flag=false;
void check(){
     String MatKhau = new String(txtpassword.getPassword());
      String XacnhanMatKhau = new String(txtxacnhanpassword.getPassword());
    if(txtmanhanvien.getText().length()<3){
    if(txtmanhanvien.getText().length()==0){
        DialogHelper.alert(this, "Mã nhân viên không được để trống");  
    }
      if(txtmanhanvien.getText().length()>0&&txtmanhanvien.getText().length()<3){
      DialogHelper.alert(this, "Mã nhân viên phải nhập ít nhất 3 ký tự"); }
    }
    else if(MatKhau.length()<3){
     if(MatKhau.length()==0){
        DialogHelper.alert(this, "Mật khẩu không được để trống");  
    }if(MatKhau.length()<3){
      DialogHelper.alert(this, "Mật khẩu phải nhập ít nhất 3 ký tự"); }
    }
     else if(XacnhanMatKhau.length()<3){
         if(XacnhanMatKhau.length()==0){
          DialogHelper.alert(this, "Xác nhận mật khẩu không được để trống");
     }}else if(txtHoten.getText().length()==0){
            DialogHelper.alert(this, "Họ tên không được để trống");
     }//else if (!txtHoten.getText().matches("[a-zA-Z][a-zA-Z ]+")) {
         //   DialogHelper.alert(this, "Họ tên chỉ chứa alphabet và ký tự trắng");
       // }

    else{
         flag=true;
     }
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
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblquanlysv = new javax.swing.JLabel();
        lblmanhanvien = new javax.swing.JLabel();
        lblhoten = new javax.swing.JLabel();
        lblmatkhau = new javax.swing.JLabel();
        lblxacnhanmatkhau = new javax.swing.JLabel();
        lblvaitro = new javax.swing.JLabel();
        txtmanhanvien = new javax.swing.JTextField();
        txtHoten = new javax.swing.JTextField();
        txtpassword = new javax.swing.JPasswordField();
        txtxacnhanpassword = new javax.swing.JPasswordField();
        rbtntruongphong = new javax.swing.JRadioButton();
        rbtnnhanvien = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        btnThemnhanvien = new javax.swing.JButton();
        btnXoanhanvien = new javax.swing.JButton();
        btnCapnhatnhanvien = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanvien = new javax.swing.JTable();
        jSeparator5 = new javax.swing.JSeparator();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnFrist = new javax.swing.JButton();
        txttimkiem = new javax.swing.JTextField();
        btntimkiem = new javax.swing.JButton();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        lblquanlysv.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblquanlysv.setForeground(new java.awt.Color(102, 0, 102));
        lblquanlysv.setText("QUẢN LÝ NHÂN VIÊN");

        lblmanhanvien.setBackground(new java.awt.Color(255, 255, 255));
        lblmanhanvien.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblmanhanvien.setForeground(new java.awt.Color(102, 0, 102));
        lblmanhanvien.setText("Mã Nhân Viên");

        lblhoten.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblhoten.setForeground(new java.awt.Color(102, 0, 102));
        lblhoten.setText("Họ và Tên");

        lblmatkhau.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblmatkhau.setForeground(new java.awt.Color(102, 0, 102));
        lblmatkhau.setText("Mật Khẩu");

        lblxacnhanmatkhau.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblxacnhanmatkhau.setForeground(new java.awt.Color(102, 0, 102));
        lblxacnhanmatkhau.setText("Xác Nhận Mật Khẩu");

        lblvaitro.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblvaitro.setForeground(new java.awt.Color(102, 0, 102));
        lblvaitro.setText("Vai Trò");

        txtmanhanvien.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtmanhanvien.setBorder(null);

        txtHoten.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtHoten.setBorder(null);

        txtpassword.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtpassword.setBorder(null);

        txtxacnhanpassword.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtxacnhanpassword.setBorder(null);

        buttonGroup1.add(rbtntruongphong);
        rbtntruongphong.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbtntruongphong.setForeground(new java.awt.Color(102, 0, 102));
        rbtntruongphong.setText("Trưởng Phòng");

        buttonGroup1.add(rbtnnhanvien);
        rbtnnhanvien.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        rbtnnhanvien.setForeground(new java.awt.Color(102, 0, 102));
        rbtnnhanvien.setText("Nhân Viên");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 20));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setPreferredSize(new java.awt.Dimension(50, 20));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setPreferredSize(new java.awt.Dimension(50, 20));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setPreferredSize(new java.awt.Dimension(50, 20));

        btnThemnhanvien.setBackground(new java.awt.Color(255, 255, 255));
        btnThemnhanvien.setForeground(new java.awt.Color(255, 255, 255));
        btnThemnhanvien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgButtonThemHover.png"))); // NOI18N
        btnThemnhanvien.setBorder(null);
        btnThemnhanvien.setBorderPainted(false);
        btnThemnhanvien.setContentAreaFilled(false);
        btnThemnhanvien.setDefaultCapable(false);
        btnThemnhanvien.setFocusPainted(false);
        btnThemnhanvien.setFocusable(false);
        btnThemnhanvien.setIconTextGap(0);
        btnThemnhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemnhanvienActionPerformed(evt);
            }
        });

        btnXoanhanvien.setBackground(new java.awt.Color(255, 255, 255));
        btnXoanhanvien.setForeground(new java.awt.Color(255, 255, 255));
        btnXoanhanvien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgButtonXoaHover.png"))); // NOI18N
        btnXoanhanvien.setBorder(null);
        btnXoanhanvien.setBorderPainted(false);
        btnXoanhanvien.setContentAreaFilled(false);
        btnXoanhanvien.setDefaultCapable(false);
        btnXoanhanvien.setFocusPainted(false);
        btnXoanhanvien.setFocusable(false);
        btnXoanhanvien.setIconTextGap(0);
        btnXoanhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoanhanvienActionPerformed(evt);
            }
        });

        btnCapnhatnhanvien.setBackground(new java.awt.Color(255, 255, 255));
        btnCapnhatnhanvien.setForeground(new java.awt.Color(255, 255, 255));
        btnCapnhatnhanvien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgButtonCapNhatHover.png"))); // NOI18N
        btnCapnhatnhanvien.setBorder(null);
        btnCapnhatnhanvien.setBorderPainted(false);
        btnCapnhatnhanvien.setContentAreaFilled(false);
        btnCapnhatnhanvien.setDefaultCapable(false);
        btnCapnhatnhanvien.setFocusPainted(false);
        btnCapnhatnhanvien.setFocusable(false);
        btnCapnhatnhanvien.setIconTextGap(0);
        btnCapnhatnhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapnhatnhanvienActionPerformed(evt);
            }
        });

        btnMoi.setBackground(new java.awt.Color(255, 255, 255));
        btnMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgButtonMoiHover.png"))); // NOI18N
        btnMoi.setBorder(null);
        btnMoi.setBorderPainted(false);
        btnMoi.setContentAreaFilled(false);
        btnMoi.setDefaultCapable(false);
        btnMoi.setFocusPainted(false);
        btnMoi.setFocusable(false);
        btnMoi.setIconTextGap(0);
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        tblNhanvien.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblNhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblNhanvien.setAutoscrolls(false);
        tblNhanvien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblNhanvien.setFocusable(false);
        tblNhanvien.setGridColor(new java.awt.Color(255, 255, 255));
        tblNhanvien.setSelectionBackground(new java.awt.Color(102, 0, 102));
        tblNhanvien.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblNhanvien.setShowHorizontalLines(false);
        tblNhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanvienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanvien);

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgCuoiHover.png"))); // NOI18N
        btnLast.setBorder(null);
        btnLast.setBorderPainted(false);
        btnLast.setContentAreaFilled(false);
        btnLast.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLast.setDefaultCapable(false);
        btnLast.setFocusPainted(false);
        btnLast.setFocusable(false);
        btnLast.setIconTextGap(0);
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgNextHover.png"))); // NOI18N
        btnNext.setBorder(null);
        btnNext.setBorderPainted(false);
        btnNext.setContentAreaFilled(false);
        btnNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNext.setDefaultCapable(false);
        btnNext.setFocusPainted(false);
        btnNext.setFocusable(false);
        btnNext.setIconTextGap(0);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgPreHover.png"))); // NOI18N
        btnPrev.setBorder(null);
        btnPrev.setBorderPainted(false);
        btnPrev.setContentAreaFilled(false);
        btnPrev.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrev.setDefaultCapable(false);
        btnPrev.setFocusPainted(false);
        btnPrev.setFocusable(false);
        btnPrev.setIconTextGap(0);
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnFrist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgDauHover.png"))); // NOI18N
        btnFrist.setBorder(null);
        btnFrist.setBorderPainted(false);
        btnFrist.setContentAreaFilled(false);
        btnFrist.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFrist.setDefaultCapable(false);
        btnFrist.setFocusPainted(false);
        btnFrist.setFocusable(false);
        btnFrist.setIconTextGap(0);
        btnFrist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFristActionPerformed(evt);
            }
        });

        txttimkiem.setBorder(null);

        btntimkiem.setBackground(new java.awt.Color(255, 255, 255));
        btntimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/icons8-search-40.png"))); // NOI18N
        btntimkiem.setBorder(null);
        btntimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimkiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator5)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(btnXoanhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94)
                .addComponent(btnCapnhatnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblmanhanvien)
                            .addComponent(lblhoten)
                            .addComponent(txtmanhanvien)
                            .addComponent(txtHoten)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(229, 229, 229)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rbtntruongphong)
                                .addGap(18, 18, 18)
                                .addComponent(rbtnnhanvien))
                            .addComponent(lblvaitro)
                            .addComponent(lblmatkhau)
                            .addComponent(lblxacnhanmatkhau)
                            .addComponent(txtpassword)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtxacnhanpassword)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(79, 79, 79)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 924, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(435, 435, 435)
                            .addComponent(btnFrist, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(81, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblquanlysv, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btntimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txttimkiem, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblquanlysv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                    .addComponent(btntimkiem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(128, 128, 128)
                            .addComponent(lblhoten)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(lblmanhanvien)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtmanhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lblmatkhau)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblxacnhanmatkhau)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtxacnhanpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(lblvaitro)
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbtntruongphong)
                            .addComponent(rbtnnhanvien))))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoanhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapnhatnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFrist, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblNhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanvienMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            this.index = tblNhanvien.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
               
            }
        }
    }//GEN-LAST:event_tblNhanvienMouseClicked

    private void btnThemnhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemnhanvienActionPerformed
        // TODO add your handling code here:
       
             check();
            if (flag==true) {
                insert();
            }
        
      
        
        
    }//GEN-LAST:event_btnThemnhanvienActionPerformed

    private void btnXoanhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoanhanvienActionPerformed
        // TODO add your handling code here:
        String manv = txtmanhanvien.getText();
        
        if (manv.equalsIgnoreCase(ShareHelper.USER.getMaNV())) {
           DialogHelper.alert(this, "Vui lòng không xóa chính mình");
        }else{
            delete();
        }
        
    }//GEN-LAST:event_btnXoanhanvienActionPerformed

    private void btnCapnhatnhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapnhatnhanvienActionPerformed
        // TODO add your handling code here:
        String MaNV = txtmanhanvien.getText();
        String MatKhau = new String(txtpassword.getPassword());
        if (MaNV.equals("")) {
            JOptionPane.showMessageDialog(this, "Mã NV không được để trống");
        } else if (MatKhau.length() < 3) {
            JOptionPane.showMessageDialog(this, " Mật khẩu trên 3 kí tự");
        } else {
            update();
        }
    }//GEN-LAST:event_btnCapnhatnhanvienActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnFristActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFristActionPerformed
        // TODO add your handling code here:
        this.index=0;
        this.edit();
    }//GEN-LAST:event_btnFristActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
         this.index--;
        this.edit();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
         this.index++;
        this.edit();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        this.index = tblNhanvien.getRowCount() - 1;
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btntimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkiemActionPerformed
        // TODO add your handling code here:
        if (txttimkiem.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên để tìm kiếm");
            return;
        }
        try {
            
        
        NhanVien nv = dao.findById(txttimkiem.getText());
     
        if (nv!=null) {
            txtmanhanvien.setText(nv.getMaNV());
            txtHoten.setText(nv.getHoTen());
            txtpassword.setText(nv.getMatKhau());
            rbtnnhanvien.setSelected(nv.getVaiTro());
            rbtntruongphong.setSelected(nv.getVaiTro());
            
        }
        else{
            JOptionPane.showMessageDialog(this, "Mã nhân viên không tìm thấy");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btntimkiemActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLyNhanVienFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVienFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVienFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVienFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyNhanVienFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapnhatnhanvien;
    private javax.swing.JButton btnFrist;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnThemnhanvien;
    private javax.swing.JButton btnXoanhanvien;
    private javax.swing.JButton btntimkiem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lblhoten;
    private javax.swing.JLabel lblmanhanvien;
    private javax.swing.JLabel lblmatkhau;
    private javax.swing.JLabel lblquanlysv;
    private javax.swing.JLabel lblvaitro;
    private javax.swing.JLabel lblxacnhanmatkhau;
    private javax.swing.JRadioButton rbtnnhanvien;
    private javax.swing.JRadioButton rbtntruongphong;
    private javax.swing.JTable tblNhanvien;
    private javax.swing.JTextField txtHoten;
    private javax.swing.JTextField txtmanhanvien;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txttimkiem;
    private javax.swing.JPasswordField txtxacnhanpassword;
    // End of variables declaration//GEN-END:variables
}
