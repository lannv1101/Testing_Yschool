/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.ui;

import edu.poly.dao.ChuyenDeDao;
import edu.poly.dao.KhoaHocDao;
import edu.poly.helper.DateHelper;
import edu.poly.helper.DialogHelper;
import edu.poly.helper.ShareHelper;
import edu.poly.model.ChuyenDe;
import edu.poly.model.KhoaHoc;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class QuanLyKhoaHoc2Frm extends javax.swing.JFrame {

    /**
     * Creates new form QuanLyKhoaHoc2Frm
     */
    public QuanLyKhoaHoc2Frm() {
        initComponents();
        init();
        setStatus(true);
    }
    int index = 0;
    KhoaHocDao dao = new KhoaHocDao();
    ChuyenDeDao cddao = new ChuyenDeDao();
    public Integer ma;

    void init() {
        // setIconImage(ShareHelper.APP_ICON);
        setLocationRelativeTo(null);
        if (ShareHelper.USER != null) {
            this.fillComboBox();

            this.clear();
            txtNgaykhaigiang.setText("");
            this.setStatus(true);
            this.load();
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập");
            this.tabs.removeAll();
        }
    }

    void load() {
        btnInsert.setEnabled(false);
        DefaultTableModel model = (DefaultTableModel) tblGridView.getModel();
        model.setRowCount(0);
        try {
            List<KhoaHoc> list = dao.select();
            for (KhoaHoc kh : list) {
                Object[] row = {
                    kh.getMaKH(),
                    kh.getMaCD(),
                    kh.getThoiLuong(),
                    kh.getHocPhi(),
                    DateHelper.toString(kh.getNgayKG()),
                    kh.getMaNV(),
                    DateHelper.toString(kh.getNgayTao())
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void insert() {
        KhoaHoc model = getModel();
        model.setNgayTao(new Date());
        try {
            dao.insert(model);
            this.load();
            this.clear();
            DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (HeadlessException e) {
            DialogHelper.alert(this, "Thêm mới thất bại!");
        }
    }

    void update() {
        KhoaHoc model = getModel();

        try {
            dao.update(model);
            this.load();
            DialogHelper.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Cập nhật thất bại!");
        }
    }

    void delete() {
        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa khóa học này?")) {
            Integer makh = Integer.valueOf(cbochuyende.getToolTipText());
            try {
                dao.delete(makh);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại!");
            }
        }
    }

    void clear() {

        KhoaHoc model = new KhoaHoc();
        ChuyenDe chuyenDe = (ChuyenDe) cbochuyende.getSelectedItem();
        model.setMaCD(chuyenDe.getMaCD());
        model.setMaNV(ShareHelper.USER.getMaNV());
        model.setNgayKG(DateHelper.add(60));
        model.setNgayTao(DateHelper.now());

        this.setModel(model);
         btnInsert.setEnabled(true);
    }

    void edit() {
        try {

            Integer makh = (Integer) tblGridView.getValueAt(this.index, 0);
            KhoaHoc model = dao.findById(makh);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);

            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void setModel(KhoaHoc model) {
        cbochuyende.setToolTipText(String.valueOf(model.getMaKH()));
        cbochuyende.setSelectedItem(cddao.findById(model.getMaCD()));
        txtNgaykhaigiang.setText(DateHelper.toString(model.getNgayKG()));
        txtHocphi.setText(String.valueOf(model.getHocPhi()));
        txtThoiluongkhoahoc.setText(String.valueOf(model.getThoiLuong()));

        txtNgaytao.setText(DateHelper.toString(model.getNgayTao()));
        txtareaghichu.setText(model.getGhiChu());
    }

    KhoaHoc getModel() {
        KhoaHoc model = new KhoaHoc();
        ChuyenDe chuyenDe = (ChuyenDe) cbochuyende.getSelectedItem();
        model.setMaCD(chuyenDe.getMaCD());
        model.setNgayKG(DateHelper.toDate(txtNgaykhaigiang.getText()));
        model.setHocPhi(Double.valueOf(txtHocphi.getText()));
        model.setThoiLuong(Integer.valueOf(txtThoiluongkhoahoc.getText()));
        model.setGhiChu(txtareaghichu.getText());
        model.setMaNV(ShareHelper.USER.getMaNV());
        model.setNgayTao(DateHelper.toDate(txtNgaytao.getText()));
        model.setMaKH(Integer.valueOf(cbochuyende.getToolTipText()));

        return model;
    }

    void setStatus(boolean insertable) {
        btnInsert.setEnabled(insertable);
        btnUpdate.setEnabled(!insertable);
        btnDelete.setEnabled(!insertable);

        boolean first = this.index > 0;
        boolean last = this.index < tblGridView.getRowCount() - 1;
        btnFisrt.setEnabled(!insertable && first);
        btnPrev.setEnabled(!insertable && first);
        btnLast.setEnabled(!insertable && last);
        btnNext.setEnabled(!insertable && last);

        btnStudents.setVisible(!insertable);
    }

    void selectComboBox() {
        ChuyenDe chuyenDe = (ChuyenDe) cbochuyende.getSelectedItem();
        txtThoiluongkhoahoc.setText(String.valueOf(chuyenDe.getThoiLuong()));
        txtHocphi.setText(String.valueOf(chuyenDe.getHocPhi()));
        txtNguoitao.setText(ShareHelper.USER.getMaNV());
    }

    void fillComboBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbochuyende.getModel();
        model.removeAllElements();
        try {
            List<ChuyenDe> list = cddao.select();
            for (ChuyenDe cd : list) {
                model.addElement(cd);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }

    }
    boolean flag = false;

    void check() {
        if (txtThoiluongkhoahoc.getText().equals("") || !txtHocphi.getText().equals("")) {
            checktl();
        } else if (txtNgaykhaigiang.getText().equals("")) {
            DialogHelper.alert(this, "Không bỏ trống ngày khai giảng!");
        } else if (txtNgaytao.getText().equals("")) {
            DialogHelper.alert(this, "Không bỏ trống ngày tạo!");
        } else {
            flag = true;
        }
    }

    void checktl() {
        if (txtThoiluongkhoahoc.getText().equals("0") || txtThoiluongkhoahoc.getText().equals("")) {
            DialogHelper.alert(this, "Không bỏ trống thời lượng!");
        } else if (txtHocphi.getText().equals("") || txtHocphi.getText().equals("0.0")) {
            DialogHelper.alert(this, "Không bỏ trống học phí!");
        } else if (!txtThoiluongkhoahoc.getText().equals("0") || !txtHocphi.getText().equals("0.0")) {
            String hp = "java.lang.NumberFormatException: For input string: ";
            String tl2 = "java.lang.NumberFormatException: For input string: ";
            String tl = "";
            try {
                if (!txtThoiluongkhoahoc.getText().equals("0") || !txtHocphi.getText().equals("0.0")) {
                    int thoiluong = Integer.parseInt(txtThoiluongkhoahoc.getText());
                    int hocphi = Integer.parseInt(txtHocphi.getText());

                    if (thoiluong <= 0) {
                        DialogHelper.alert(this, "Thời lượng là số dương và phải lớn hơn 0");
                    } else if (hocphi <= 0) {
                        DialogHelper.alert(this, "Học phí là số dương và phải lớn hơn 0");
                    } else {
                        flag = true;

                    }
                }

            } catch (Exception e) {
                System.out.println(e);
                tl += e.toString();
                hp += "\"" + (txtHocphi.getText()).toString() + "\"";
                tl2 += "\"" + (txtThoiluongkhoahoc.getText()).toString() + "\"";
                System.out.println(hp);
                System.out.println(tl2);
                if (tl.equals(hp)) {
                    DialogHelper.alert(this, "Học phí phải truyền vào kiểu số!");
                } else if (tl.equals(tl2)) {
                    DialogHelper.alert(this, "Thời lượng phải truyền vào kiểu số!");
                }

            }
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

        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        lblchuyende = new javax.swing.JLabel();
        cbochuyende = new javax.swing.JComboBox<>();
        txtHocphi = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lblhocphi = new javax.swing.JLabel();
        txtNguoitao = new javax.swing.JTextField();
        lblnguoitao = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtareaghichu = new javax.swing.JTextArea();
        lblmanguoihoc8 = new javax.swing.JLabel();
        txtNgaytao = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        lblngaytao = new javax.swing.JLabel();
        txtThoiluongkhoahoc = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        lblThoiluong = new javax.swing.JLabel();
        txtNgaykhaigiang = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        lblngaykhaigiang = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnInsert = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnStudents = new javax.swing.JButton();
        btnFisrt = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGridView = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1050, 620));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tabs.setBackground(new java.awt.Color(255, 255, 255));
        tabs.setForeground(new java.awt.Color(102, 0, 102));
        tabs.setPreferredSize(new java.awt.Dimension(1060, 630));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1050, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblchuyende.setBackground(new java.awt.Color(255, 255, 255));
        lblchuyende.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblchuyende.setForeground(new java.awt.Color(102, 0, 102));
        lblchuyende.setText("Chuyên Đề");
        jPanel1.add(lblchuyende, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        cbochuyende.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbochuyendeMouseClicked(evt);
            }
        });
        cbochuyende.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbochuyendeActionPerformed(evt);
            }
        });
        jPanel1.add(cbochuyende, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 420, 35));

        txtHocphi.setEditable(false);
        txtHocphi.setBackground(new java.awt.Color(255, 255, 255));
        txtHocphi.setBorder(null);
        txtHocphi.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtHocphi, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 420, 40));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 20));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 420, 10));

        lblhocphi.setBackground(new java.awt.Color(255, 255, 255));
        lblhocphi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblhocphi.setForeground(new java.awt.Color(102, 0, 102));
        lblhocphi.setText("Học Phí");
        jPanel1.add(lblhocphi, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, -1, -1));

        txtNguoitao.setEditable(false);
        txtNguoitao.setBackground(new java.awt.Color(255, 255, 255));
        txtNguoitao.setBorder(null);
        txtNguoitao.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtNguoitao, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 420, 40));

        lblnguoitao.setBackground(new java.awt.Color(255, 255, 255));
        lblnguoitao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblnguoitao.setForeground(new java.awt.Color(102, 0, 102));
        lblnguoitao.setText("Người Tạo");
        jPanel1.add(lblnguoitao, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, -1, -1));

        txtareaghichu.setColumns(20);
        txtareaghichu.setRows(5);
        jScrollPane3.setViewportView(txtareaghichu);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 341, 940, 80));

        lblmanguoihoc8.setBackground(new java.awt.Color(255, 255, 255));
        lblmanguoihoc8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblmanguoihoc8.setForeground(new java.awt.Color(102, 0, 102));
        lblmanguoihoc8.setText("Ghi Chú");
        jPanel1.add(lblmanguoihoc8, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 310, -1, -1));

        txtNgaytao.setEditable(false);
        txtNgaytao.setBackground(new java.awt.Color(255, 255, 255));
        txtNgaytao.setBorder(null);
        txtNgaytao.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtNgaytao.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtNgaytao, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 250, 390, 40));

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator6.setPreferredSize(new java.awt.Dimension(50, 20));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 290, 390, 20));

        lblngaytao.setBackground(new java.awt.Color(255, 255, 255));
        lblngaytao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblngaytao.setForeground(new java.awt.Color(102, 0, 102));
        lblngaytao.setText("Ngày Tạo");
        jPanel1.add(lblngaytao, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 220, -1, 20));

        txtThoiluongkhoahoc.setEditable(false);
        txtThoiluongkhoahoc.setBackground(new java.awt.Color(255, 255, 255));
        txtThoiluongkhoahoc.setBorder(null);
        txtThoiluongkhoahoc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtThoiluongkhoahoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThoiluongkhoahocActionPerformed(evt);
            }
        });
        jPanel1.add(txtThoiluongkhoahoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 160, 390, 40));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setPreferredSize(new java.awt.Dimension(50, 20));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 200, 390, 10));

        lblThoiluong.setBackground(new java.awt.Color(255, 255, 255));
        lblThoiluong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblThoiluong.setForeground(new java.awt.Color(102, 0, 102));
        lblThoiluong.setText("Thời Lượng (Giờ)");
        jPanel1.add(lblThoiluong, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, -1, -1));

        txtNgaykhaigiang.setBorder(null);
        jPanel1.add(txtNgaykhaigiang, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, 400, 40));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setPreferredSize(new java.awt.Dimension(50, 20));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, 390, 9));

        lblngaykhaigiang.setBackground(new java.awt.Color(255, 255, 255));
        lblngaykhaigiang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblngaykhaigiang.setForeground(new java.awt.Color(102, 0, 102));
        lblngaykhaigiang.setText("Ngày Khai Giảng");
        jPanel1.add(lblngaykhaigiang, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, -1, -1));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 420, 9));

        btnInsert.setBackground(new java.awt.Color(255, 255, 255));
        btnInsert.setForeground(new java.awt.Color(255, 255, 255));
        btnInsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgButtonThemHover.png"))); // NOI18N
        btnInsert.setBorder(null);
        btnInsert.setBorderPainted(false);
        btnInsert.setContentAreaFilled(false);
        btnInsert.setDefaultCapable(false);
        btnInsert.setFocusPainted(false);
        btnInsert.setFocusable(false);
        btnInsert.setIconTextGap(0);
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });
        jPanel1.add(btnInsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 460, -1, -1));

        btnDelete.setBackground(new java.awt.Color(255, 255, 255));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgButtonXoaHover.png"))); // NOI18N
        btnDelete.setBorder(null);
        btnDelete.setBorderPainted(false);
        btnDelete.setContentAreaFilled(false);
        btnDelete.setDefaultCapable(false);
        btnDelete.setFocusPainted(false);
        btnDelete.setFocusable(false);
        btnDelete.setIconTextGap(0);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 460, -1, -1));

        btnUpdate.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgButtonCapNhatHover.png"))); // NOI18N
        btnUpdate.setBorder(null);
        btnUpdate.setBorderPainted(false);
        btnUpdate.setContentAreaFilled(false);
        btnUpdate.setDefaultCapable(false);
        btnUpdate.setFocusPainted(false);
        btnUpdate.setFocusable(false);
        btnUpdate.setIconTextGap(0);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 460, -1, -1));

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
        jPanel1.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 460, -1, -1));

        btnStudents.setBackground(new java.awt.Color(255, 255, 255));
        btnStudents.setForeground(new java.awt.Color(255, 255, 255));
        btnStudents.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgButtonhocvien.png"))); // NOI18N
        btnStudents.setBorder(null);
        btnStudents.setBorderPainted(false);
        btnStudents.setContentAreaFilled(false);
        btnStudents.setDefaultCapable(false);
        btnStudents.setFocusPainted(false);
        btnStudents.setFocusable(false);
        btnStudents.setIconTextGap(0);
        btnStudents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudentsActionPerformed(evt);
            }
        });
        jPanel1.add(btnStudents, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 460, -1, -1));

        btnFisrt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgDauHover.png"))); // NOI18N
        btnFisrt.setBorder(null);
        btnFisrt.setBorderPainted(false);
        btnFisrt.setContentAreaFilled(false);
        btnFisrt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFisrt.setDefaultCapable(false);
        btnFisrt.setFocusPainted(false);
        btnFisrt.setFocusable(false);
        btnFisrt.setIconTextGap(0);
        btnFisrt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFisrtActionPerformed(evt);
            }
        });
        jPanel1.add(btnFisrt, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 560, -1, -1));

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
        jPanel1.add(btnPrev, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 560, -1, -1));

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
        jPanel1.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 560, -1, -1));

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
        jPanel1.add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 560, -1, -1));

        tabs.addTab("QUẢN LÝ KHÓA HỌC", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblGridView.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tblGridView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "MÃ KH", "CHUYÊN ĐỀ", "THỜI LƯỢNG", "HỌC PHÍ", "KHAI GIẢNG", "TẠO BỞI", "NGÀY TẠO"
            }
        ));
        tblGridView.setGridColor(new java.awt.Color(255, 255, 255));
        tblGridView.setSelectionBackground(new java.awt.Color(102, 0, 102));
        tblGridView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGridViewMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGridView);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1055, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE))
        );

        tabs.addTab("DANH SÁCH KHÓA HỌC", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblGridViewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGridViewMouseClicked
        // TODO add your handling code here:\

        if (evt.getClickCount() == 1) {

            if (this.index >= 0) {
                this.edit();
                this.index = tblGridView.rowAtPoint(evt.getPoint());
                String name = (String) tblGridView.getValueAt(this.index, 5);
                Integer ma2 = (Integer) tblGridView.getValueAt(this.index, 0);
                ma = ma2;
                txtNguoitao.setText(name);
                tabs.setSelectedIndex(0);

            }
        }
    }//GEN-LAST:event_tblGridViewMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.fillComboBox();
        this.load();
        this.clear();
        this.setStatus(true);
    }//GEN-LAST:event_formWindowOpened

    private void cbochuyendeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbochuyendeMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_cbochuyendeMouseClicked

    private void cbochuyendeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbochuyendeActionPerformed
        // TODO add your handling code here:
        selectComboBox();
    }//GEN-LAST:event_cbochuyendeActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:

        try {
            String ngay11 = txtNgaykhaigiang.getText();
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(ngay11);
            if (date.before(new Date())) {

                DialogHelper.alert(this, "Ngày khai giảng phai sau ngày hiện tại!");
                return;
            } else {
                insert();
            }

        } catch (Exception e) {
            DialogHelper.alert(this, "Sai định dạng ngày!");
        }
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        check();
        if (flag == true) {

            try {
                String ngay11 = txtNgaykhaigiang.getText();
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(ngay11);
                if (date.after(new Date())) {

                    DialogHelper.alert(this, "Ngày khai giảng phai sau ngày hiện tại!");
                    return;
                } else {
                    update();
                }

            } catch (Exception e) {
                DialogHelper.alert(this, "Sai định dạng ngày!");
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clear();
       
        //txtNguoiTao.setText("");
        Date ngaymua = new Date();
        SimpleDateFormat DATE_FORMATER = new SimpleDateFormat("dd/MM/yyyy");
        String ngay2 = DATE_FORMATER.format(ngaymua);

        String[] key = ngay2.split("/");
        int ngay = Integer.parseInt(key[0]);
        int thang = Integer.parseInt(key[1]);
        String time = "" + (ngay + 1) + "/" + key[1] + "/" + key[2] + "";
        String time1;

        try {

            Date date = DATE_FORMATER.parse(time);
            String ngay3 = DATE_FORMATER.format(date);
            //   txtNgayKG.setText(ngay3);
            //   txtNgayTao.disable();
            //    txtNgayTao.setText(ngay2);
            //  txtNguoiTao.setText(ShareHelper.USER.getMaNV());
        } catch (ParseException ex) {

        }

    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudentsActionPerformed
        try {
            //s TODO add your handling code here:
            //  if (ma == null) {
            // DialogHelper.alert(this, "Chưa chọn học viên");
            // } else {
            //  new QuanLyHocVienFrm(ma).setVisible(true);
            //System.out.println(ma);
            //}
            Integer id = Integer.valueOf(cbochuyende.getToolTipText());
            new QuanLyHocVien2Frm(id).setVisible(true);

        } catch (Exception ex) {
            Logger.getLogger(QuanLyKhaoHocFrm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnStudentsActionPerformed

    private void btnFisrtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFisrtActionPerformed
        // TODO add your handling code here:
        this.index = 0;
        this.edit();
    }//GEN-LAST:event_btnFisrtActionPerformed

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
        this.index = tblGridView.getRowCount() - 1;
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void txtThoiluongkhoahocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThoiluongkhoahocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThoiluongkhoahocActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLyKhoaHoc2Frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhoaHoc2Frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhoaHoc2Frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhoaHoc2Frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyKhoaHoc2Frm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFisrt;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnStudents;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbochuyende;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lblThoiluong;
    private javax.swing.JLabel lblchuyende;
    private javax.swing.JLabel lblhocphi;
    private javax.swing.JLabel lblmanguoihoc8;
    private javax.swing.JLabel lblngaykhaigiang;
    private javax.swing.JLabel lblngaytao;
    private javax.swing.JLabel lblnguoitao;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblGridView;
    private javax.swing.JTextField txtHocphi;
    private javax.swing.JTextField txtNgaykhaigiang;
    private javax.swing.JTextField txtNgaytao;
    private javax.swing.JTextField txtNguoitao;
    private javax.swing.JTextField txtThoiluongkhoahoc;
    private javax.swing.JTextArea txtareaghichu;
    // End of variables declaration//GEN-END:variables
}
