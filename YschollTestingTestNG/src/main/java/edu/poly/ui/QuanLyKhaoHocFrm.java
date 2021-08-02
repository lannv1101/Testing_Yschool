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
public class QuanLyKhaoHocFrm extends javax.swing.JFrame {

    /**
     * Creates new form QuanLyKhaoHocFrm
     */
    public QuanLyKhaoHocFrm() {
        initComponents();
        init();
    }
    int index = 0;
    KhoaHocDao dao = new KhoaHocDao();
    ChuyenDeDao cddao = new ChuyenDeDao();
    public Integer ma;
    
    void init() {
        
        if (ShareHelper.USER != null) {
            this.fillComboBox();
            
            this.clear();
            txtNgaykhaigiang.setText("");
            this.setStatus(true);
            this.load();
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập");
            
        }
    }

    void load() {
        btnThemKhoahoc.setEnabled(false);
        DefaultTableModel model = (DefaultTableModel) tblKhoaHoc.getModel();
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
            DialogHelper.alert(this, "Lỗi Truy Vấn Dữ Liệu");
        }
    }

    boolean check() {
        try {
            SimpleDateFormat patern = new SimpleDateFormat("dd/MM/yyyy");
            Date input = patern.parse(txtNgaykhaigiang.getText());
            Date now = new Date();
            if (now.compareTo(input) > 0) {
                DialogHelper.alert(this, "Ngày khai giảng không hợp lệ!");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    void insert() {
        
        KhoaHoc model = getModel();
        model.setNgayTao(new Date());
        try {
            dao.insert(model);
            this.load();
            this.clear();
            DialogHelper.alert(this, "Thêm Mới Thành Công");
        } catch (Exception e) {
            DialogHelper.alert(this, "Thêm Mới Thất Bại");
            DialogHelper.alert(this, e.toString());
        }
        
    }
    
    void update() {
        KhoaHoc model = getModel();
        try {
            dao.update(model);
            this.load();
            DialogHelper.alert(this, "Cập Nhật Thành Công");
        } catch (Exception e) {
            DialogHelper.alert(this, "Cập Nhật Thất Bại");
        }
    }
    
    void delete() {
        if (DialogHelper.confirm(this, "Bạn có chắc muốn xóa đối tượng này?")) {
            Integer makh = Integer.valueOf(cbochuyende.getToolTipText());
            try {
                dao.delete(makh);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Xóa Thành Công");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa Thất Bại");
            }
        }
    }
    
    void clear() {
        KhoaHoc model = new KhoaHoc();
        ChuyenDe chuyenDe = (ChuyenDe) cbochuyende.getSelectedItem();
        model.setMaCD(chuyenDe.getMaCD());
        model.setMaNV(ShareHelper.USER.getMaNV()); //xem lai cai nay
        model.setNgayKG(DateHelper.add(30));
        model.setNgayTao(DateHelper.now());
        this.setModel(model);
    }
    
    void edit() {
        try {
            Integer makh = (Integer) tblKhoaHoc.getValueAt(this.index, 0);
            KhoaHoc model = dao.findById(makh);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi Truy Vấn Dữ Liệu");
        }
    }
    
    void setModel(KhoaHoc model) {
        cbochuyende.setToolTipText(String.valueOf(model.getMaKH()));
        cbochuyende.setSelectedItem(cddao.findById(model.getMaCD()));
        txtNgaykhaigiang.setText(DateHelper.toString(model.getNgayKG()));
        txtHocphi.setText(String.valueOf(model.getHocPhi()));
        txtThoiluongkhoahoc.setText(String.valueOf(model.getThoiLuong()));
        txtNguoitao.setText(model.getMaNV());
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
        btnThemKhoahoc.setEnabled(insertable);
        btnCapnhatKhoaHoc.setEnabled(!insertable);
        btnXoaKhoahoc.setEnabled(!insertable);
        boolean first = this.index > 0;
        boolean last = this.index < tblKhoaHoc.getRowCount() - 1;
        btnFrist.setEnabled(!insertable && first);
        btnPrev.setEnabled(!insertable && first);
        btnNextt.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);
        
    }
    
    void selectCombo() {
        ChuyenDe cd = (ChuyenDe) cbochuyende.getSelectedItem();
        txtThoiluongkhoahoc.setText(String.valueOf(cd.getThoiLuong()));
        txtHocphi.setText(String.valueOf(cd.getHocPhi()));
        
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        lblquanlynguoihoc = new javax.swing.JLabel();
        lblchuyende = new javax.swing.JLabel();
        cbochuyende = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        txtHocphi = new javax.swing.JTextField();
        lblhocphi = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txtNgaykhaigiang = new javax.swing.JTextField();
        lblnguoitao = new javax.swing.JLabel();
        lblngaykhaigiang = new javax.swing.JLabel();
        txtNguoitao = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        txtNgaytao = new javax.swing.JTextField();
        lblThoiluong = new javax.swing.JLabel();
        lblngaytao = new javax.swing.JLabel();
        txtThoiluongkhoahoc = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        btnThemKhoahoc = new javax.swing.JButton();
        btnXoaKhoahoc = new javax.swing.JButton();
        btnCapnhatKhoaHoc = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblKhoaHoc = new javax.swing.JTable();
        btnLast = new javax.swing.JButton();
        btnNextt = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnFrist = new javax.swing.JButton();
        lblmanguoihoc8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtareaghichu = new javax.swing.JTextArea();
        btnHocvien = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1078, 630));

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        lblquanlynguoihoc.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblquanlynguoihoc.setForeground(new java.awt.Color(102, 0, 102));
        lblquanlynguoihoc.setText("QUẢN LÝ KHÓA HỌC");

        lblchuyende.setBackground(new java.awt.Color(255, 255, 255));
        lblchuyende.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblchuyende.setForeground(new java.awt.Color(102, 0, 102));
        lblchuyende.setText("Chuyên Đề");

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

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 20));

        txtHocphi.setEditable(false);
        txtHocphi.setBorder(null);

        lblhocphi.setBackground(new java.awt.Color(255, 255, 255));
        lblhocphi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblhocphi.setForeground(new java.awt.Color(102, 0, 102));
        lblhocphi.setText("Học Phí");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setPreferredSize(new java.awt.Dimension(50, 20));

        txtNgaykhaigiang.setBorder(null);

        lblnguoitao.setBackground(new java.awt.Color(255, 255, 255));
        lblnguoitao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblnguoitao.setForeground(new java.awt.Color(102, 0, 102));
        lblnguoitao.setText("Người Tạo");

        lblngaykhaigiang.setBackground(new java.awt.Color(255, 255, 255));
        lblngaykhaigiang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblngaykhaigiang.setForeground(new java.awt.Color(102, 0, 102));
        lblngaykhaigiang.setText("Ngày Khai Giảng");

        txtNguoitao.setEditable(false);
        txtNguoitao.setBorder(null);

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setPreferredSize(new java.awt.Dimension(50, 20));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setPreferredSize(new java.awt.Dimension(50, 20));

        txtNgaytao.setEditable(false);
        txtNgaytao.setBorder(null);

        lblThoiluong.setBackground(new java.awt.Color(255, 255, 255));
        lblThoiluong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblThoiluong.setForeground(new java.awt.Color(102, 0, 102));
        lblThoiluong.setText("Thời Lượng (Giờ)");

        lblngaytao.setBackground(new java.awt.Color(255, 255, 255));
        lblngaytao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblngaytao.setForeground(new java.awt.Color(102, 0, 102));
        lblngaytao.setText("Ngày Tạo");

        txtThoiluongkhoahoc.setEditable(false);
        txtThoiluongkhoahoc.setBorder(null);

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator6.setPreferredSize(new java.awt.Dimension(50, 20));

        btnThemKhoahoc.setBackground(new java.awt.Color(255, 255, 255));
        btnThemKhoahoc.setForeground(new java.awt.Color(255, 255, 255));
        btnThemKhoahoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgButtonThemHover.png"))); // NOI18N
        btnThemKhoahoc.setBorder(null);
        btnThemKhoahoc.setBorderPainted(false);
        btnThemKhoahoc.setContentAreaFilled(false);
        btnThemKhoahoc.setDefaultCapable(false);
        btnThemKhoahoc.setFocusPainted(false);
        btnThemKhoahoc.setFocusable(false);
        btnThemKhoahoc.setIconTextGap(0);
        btnThemKhoahoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKhoahocActionPerformed(evt);
            }
        });

        btnXoaKhoahoc.setBackground(new java.awt.Color(255, 255, 255));
        btnXoaKhoahoc.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaKhoahoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgButtonXoaHover.png"))); // NOI18N
        btnXoaKhoahoc.setBorder(null);
        btnXoaKhoahoc.setBorderPainted(false);
        btnXoaKhoahoc.setContentAreaFilled(false);
        btnXoaKhoahoc.setDefaultCapable(false);
        btnXoaKhoahoc.setFocusPainted(false);
        btnXoaKhoahoc.setFocusable(false);
        btnXoaKhoahoc.setIconTextGap(0);
        btnXoaKhoahoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKhoahocActionPerformed(evt);
            }
        });

        btnCapnhatKhoaHoc.setBackground(new java.awt.Color(255, 255, 255));
        btnCapnhatKhoaHoc.setForeground(new java.awt.Color(255, 255, 255));
        btnCapnhatKhoaHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgButtonCapNhatHover.png"))); // NOI18N
        btnCapnhatKhoaHoc.setBorder(null);
        btnCapnhatKhoaHoc.setBorderPainted(false);
        btnCapnhatKhoaHoc.setContentAreaFilled(false);
        btnCapnhatKhoaHoc.setDefaultCapable(false);
        btnCapnhatKhoaHoc.setFocusPainted(false);
        btnCapnhatKhoaHoc.setFocusable(false);
        btnCapnhatKhoaHoc.setIconTextGap(0);
        btnCapnhatKhoaHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapnhatKhoaHocActionPerformed(evt);
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

        tblKhoaHoc.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblKhoaHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã KH", "Chuyên Đề", "Thời Lượng", "Học Phí", "Khai Giảng", "Tạo Bởi", "Ngày Tạo"
            }
        ));
        tblKhoaHoc.setGridColor(new java.awt.Color(255, 255, 255));
        tblKhoaHoc.setSelectionBackground(new java.awt.Color(102, 0, 102));
        tblKhoaHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhoaHocMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblKhoaHoc);

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

        btnNextt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgNextHover.png"))); // NOI18N
        btnNextt.setBorder(null);
        btnNextt.setBorderPainted(false);
        btnNextt.setContentAreaFilled(false);
        btnNextt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNextt.setDefaultCapable(false);
        btnNextt.setFocusPainted(false);
        btnNextt.setFocusable(false);
        btnNextt.setIconTextGap(0);
        btnNextt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNexttActionPerformed(evt);
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

        lblmanguoihoc8.setBackground(new java.awt.Color(255, 255, 255));
        lblmanguoihoc8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblmanguoihoc8.setForeground(new java.awt.Color(102, 0, 102));
        lblmanguoihoc8.setText("Ghi Chú");

        txtareaghichu.setColumns(20);
        txtareaghichu.setRows(5);
        jScrollPane3.setViewportView(txtareaghichu);

        btnHocvien.setBackground(new java.awt.Color(255, 255, 255));
        btnHocvien.setForeground(new java.awt.Color(255, 255, 255));
        btnHocvien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgButtonhocvien.png"))); // NOI18N
        btnHocvien.setBorder(null);
        btnHocvien.setBorderPainted(false);
        btnHocvien.setContentAreaFilled(false);
        btnHocvien.setDefaultCapable(false);
        btnHocvien.setFocusPainted(false);
        btnHocvien.setFocusable(false);
        btnHocvien.setIconTextGap(0);
        btnHocvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHocvienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblquanlynguoihoc, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 1293, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblchuyende)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbochuyende, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblhocphi)
                                            .addComponent(txtHocphi, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblnguoitao)
                                            .addComponent(txtNguoitao, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(132, 132, 132))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lblmanguoihoc8)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtThoiluongkhoahoc, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgaytao, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNgaykhaigiang, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblThoiluong)
                                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblngaytao)
                                            .addComponent(lblngaykhaigiang)))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 846, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(451, 451, 451)
                        .addComponent(btnFrist)
                        .addGap(10, 10, 10)
                        .addComponent(btnPrev)
                        .addGap(30, 30, 30)
                        .addComponent(btnNextt)
                        .addGap(10, 10, 10)
                        .addComponent(btnLast))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(btnThemKhoahoc)
                        .addGap(80, 80, 80)
                        .addComponent(btnXoaKhoahoc)
                        .addGap(90, 90, 90)
                        .addComponent(btnCapnhatKhoaHoc)
                        .addGap(100, 100, 100)
                        .addComponent(btnMoi)
                        .addGap(74, 74, 74)
                        .addComponent(btnHocvien)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblquanlynguoihoc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblchuyende)
                    .addComponent(lblngaykhaigiang))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtNgaykhaigiang, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(lblThoiluong)
                        .addGap(11, 11, 11)
                        .addComponent(txtThoiluongkhoahoc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblngaytao)
                        .addGap(6, 6, 6)
                        .addComponent(txtNgaytao, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                .addComponent(lblmanguoihoc8)
                                .addGap(76, 76, 76))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(cbochuyende, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(lblhocphi)
                        .addGap(6, 6, 6)
                        .addComponent(txtHocphi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblnguoitao)
                        .addGap(6, 6, 6)
                        .addComponent(txtNguoitao, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemKhoahoc)
                    .addComponent(btnXoaKhoahoc)
                    .addComponent(btnCapnhatKhoaHoc)
                    .addComponent(btnMoi)
                    .addComponent(btnHocvien))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFrist)
                    .addComponent(btnPrev)
                    .addComponent(btnNextt)
                    .addComponent(btnLast))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1293, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemKhoahocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKhoahocActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnThemKhoahocActionPerformed

    private void btnXoaKhoahocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKhoahocActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnXoaKhoahocActionPerformed

    private void btnCapnhatKhoaHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapnhatKhoaHocActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnCapnhatKhoaHocActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clear();
        btnThemKhoahoc.setEnabled(true);
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

    private void btnFristActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFristActionPerformed
        // TODO add your handling code here:
        this.index = 0;
        this.edit();
    }//GEN-LAST:event_btnFristActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        this.index--;
        this.edit();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNexttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNexttActionPerformed
        // TODO add your handling code here:
        this.index++;
        this.edit();
    }//GEN-LAST:event_btnNexttActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        this.index = tblKhoaHoc.getRowCount() - 1;
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void cbochuyendeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbochuyendeMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbochuyendeMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.fillComboBox();
        this.load();
        this.clear();
        this.setStatus(true);
    }//GEN-LAST:event_formWindowOpened

    private void tblKhoaHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhoaHocMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            
            if (this.index >= 0) {
                this.edit();
                this.index = tblKhoaHoc.rowAtPoint(evt.getPoint());
                String name = (String) tblKhoaHoc.getValueAt(this.index, 5);
                Integer ma2 = (Integer) tblKhoaHoc.getValueAt(this.index, 0);
                ma = ma2;
                txtNguoitao.setText(name);
                
            }
        }
    }//GEN-LAST:event_tblKhoaHocMouseClicked

    private void cbochuyendeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbochuyendeActionPerformed
        // TODO add your handling code here:
        selectCombo();
    }//GEN-LAST:event_cbochuyendeActionPerformed

    private void btnHocvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHocvienActionPerformed
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
    }//GEN-LAST:event_btnHocvienActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLyKhaoHocFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhaoHocFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhaoHocFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhaoHocFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyKhaoHocFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapnhatKhoaHoc;
    private javax.swing.JButton btnFrist;
    private javax.swing.JButton btnHocvien;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNextt;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnThemKhoahoc;
    private javax.swing.JButton btnXoaKhoahoc;
    private javax.swing.JComboBox<String> cbochuyende;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lblThoiluong;
    private javax.swing.JLabel lblchuyende;
    private javax.swing.JLabel lblhocphi;
    private javax.swing.JLabel lblmanguoihoc8;
    private javax.swing.JLabel lblngaykhaigiang;
    private javax.swing.JLabel lblngaytao;
    private javax.swing.JLabel lblnguoitao;
    private javax.swing.JLabel lblquanlynguoihoc;
    private javax.swing.JTable tblKhoaHoc;
    private javax.swing.JTextField txtHocphi;
    private javax.swing.JTextField txtNgaykhaigiang;
    private javax.swing.JTextField txtNgaytao;
    private javax.swing.JTextField txtNguoitao;
    private javax.swing.JTextField txtThoiluongkhoahoc;
    private javax.swing.JTextArea txtareaghichu;
    // End of variables declaration//GEN-END:variables
}
