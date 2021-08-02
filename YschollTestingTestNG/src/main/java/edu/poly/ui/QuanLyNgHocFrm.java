/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.ui;

import edu.poly.dao.NguoiHocDao;
import edu.poly.dao.NhanVienDao;
import edu.poly.helper.DateHelper;
import edu.poly.helper.DialogHelper;
import edu.poly.helper.ShareHelper;
import edu.poly.model.NguoiHoc;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class QuanLyNgHocFrm extends javax.swing.JFrame {

    DefaultTableModel tblModel;
    int index = 0;
    NguoiHocDao dao = new NguoiHocDao();

    /**
     * Creates new form QuanLyNgHocFrm
     */
    public QuanLyNgHocFrm() {
        initComponents();
        tblModel = (DefaultTableModel) tblNguoihoc.getModel();
        loadData();
        this.load();

        this.setStatus(true);
        initTable();
    }

    void initTable() {
        tblModel.setColumnIdentifiers(new String[]{"Mã NH", "Họ Tên", "Ngày Sinh", "Giới Tính", "Điện Thoại", "Email", "Mã NV", "Ngày ĐK"});
        tblNguoihoc.setModel(tblModel);
    }

    private void loadData() {
        try {
            while (tblModel.getRowCount() > 0) {
                tblModel.removeRow(0);
            }
            NguoiHocDao dao = new NguoiHocDao();
            List<NguoiHoc> list = dao.select();
            for (NguoiHoc nguoihoc : list) {
                Vector row = new Vector();
                row.add(nguoihoc.getMaNH());
                row.add(nguoihoc.getHoTen());
                row.add(nguoihoc.getNgaySinh());
                row.add(nguoihoc.getGioiTinh());
                row.add(nguoihoc.getDienThoai());
                row.add(nguoihoc.getEmail());
                row.add(nguoihoc.getMaNV());
                row.add(nguoihoc.getNgayDK());
                tblModel.addRow(row);
            }

            tblModel.fireTableDataChanged();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblNguoihoc.getModel();
        model.setRowCount(0);
        try {
            String keyword = txttimkiem.getText();
            List<NguoiHoc> list = dao.selectByKeyword(keyword);
            for (NguoiHoc nh : list) {
                Object[] row = {
                    nh.getMaNH(),
                    nh.getHoTen(),
                    nh.getGioiTinh() ? "Nam" : "Nữ",
                    DateHelper.toString(nh.getNgaySinh()),
                    nh.getDienThoai(),
                    nh.getEmail(),
                    nh.getMaNV(),
                    DateHelper.toString(nh.getNgayDK())
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void insert() {
        NguoiHoc model = getModel();
        try {
            dao.insert(model);
            this.load();
            this.clear();
            DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            
            DialogHelper.alert(this, "Trùng mã người học!");
        }
    }

    void update() {
        NguoiHoc model = getModel();
        try {
            dao.update(model);
            this.load();
            this.clear();
           
            DialogHelper.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Cập nhật thất bại!");
        }

    }

    void delete() {
        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa người học này?")) {
            String manh = txtmanguoihoc.getText();
            try {
                dao.delete(manh);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại!");
            }
        }
    }

    void clear() {
        NguoiHoc model = new NguoiHoc();
        model.setMaNV(ShareHelper.USER.getMaNV());
        model.setNgayDK(DateHelper.now());
        this.setModel(model);
        btnThemNguoihoc.setEnabled(true);
    }

    void edit() {
        try {
            String manh = (String) tblNguoihoc.getValueAt(this.index, 0);
            NguoiHoc model = dao.findById(manh);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void setModel(NguoiHoc model) {
        txtmanguoihoc.setText(model.getMaNH());
        txtHoten.setText(model.getHoTen());
        cbxgioitinh.setSelectedIndex(model.getGioiTinh() ? 0 : 1);
        txtngaysinh.setText(DateHelper.toString(model.getNgaySinh()));
        txtdienthoai.setText(model.getDienThoai());
        txtEmail.setText(model.getEmail());
        txtareaGhichu.setText(model.getGhiChu());
    }

    NguoiHoc getModel() {
        NguoiHoc model = new NguoiHoc();
        model.setMaNH(txtmanguoihoc.getText());
        model.setHoTen(txtHoten.getText());
        model.setGioiTinh(cbxgioitinh.getSelectedIndex() == 0);
        model.setNgaySinh(DateHelper.toDate(txtngaysinh.getText()));
        model.setDienThoai(txtdienthoai.getText());
        model.setEmail(txtEmail.getText());
        model.setGhiChu(txtareaGhichu.getText());
        model.setMaNV(ShareHelper.USER.getMaNV());
        model.setNgayDK(DateHelper.now());
        return model;
    }

    void setStatus(boolean insertable) {
        //  txtmanguoihoc.setEditable(insertable);
        btnThemNguoihoc.setEnabled(insertable);
        btnCapnhatnguoihoc.setEnabled(!insertable);
        btnXoanguoihoc.setEnabled(!insertable);

        boolean first = this.index > 0;
        boolean last = this.index < tblNguoihoc.getRowCount() - 1;
        btnPrev.setEnabled(!insertable && first);
        btnFirst.setEnabled(!insertable && first);
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);
    }
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean verifyEmail(String email) {
        if (email == null) {
            return false;
        }
        return email.matches(EMAIL_PATTERN);
    }
    boolean flag = false;

    void check() {
        if ((txtmanguoihoc.getText()).length() < 6 || (txtmanguoihoc.getText()).length() > 8) {
            if ((txtmanguoihoc.getText()).equals("")) {
                DialogHelper.alert(this, "Mã người học không được để trống");
            } else {
                DialogHelper.alert(this, "Mã người học phải nhập 7 ký tự");
            }
        } else if ((txtHoten.getText()).equals("")) {
            DialogHelper.alert(this, "Họ tên không được để trống");
        } //else if (!txtHoten.getText().matches("[a-zA-Z][a-zA-Z ]+")) {
           // DialogHelper.alert(this, "Họ tên chỉ chứa alphabet và ký tự trắng");
        //} 
        else if (txtdienthoai.getText().equals("")) {
            DialogHelper.alert(this, "Số điện thoại không được để trống");
        } else if (txtdienthoai.getText().length() < 10 || txtdienthoai.getText().length() > 12) {
            DialogHelper.alert(this, "Số điện thoại phải nhập đủ 10 hoặc 11 số.");
        } else if (!txtdienthoai.getText().matches("[0-9]+")) {
            DialogHelper.alert(this, "Số điện thoại chỉ nhập số");
        } else if (txtEmail.getText().equals("")) {
            DialogHelper.alert(this, "Email không đươc để trống");
        } else if (verifyEmail(txtEmail.getText()) == false) {
            DialogHelper.alert(this, "Định dạng email bạn nhập không chính xác");
        } else if (txtngaysinh.getText().equals("")) {
            DialogHelper.alert(this, "Ngày sinh không đươc để trống");
        } else if (!txtngaysinh.getText().equals("")) {
            if ((txtngaysinh.getText().length() != 10)) {
                DialogHelper.alert(this, "Định dạng ngày nhập vào chưa chính xác!");
            }
            if (txtngaysinh.getText().length() == 10) {
                String ngaysinh = txtngaysinh.getText();
                SimpleDateFormat DATE_FORMATER = new SimpleDateFormat("dd/MM/yyyy");
                String kiemtra = ngaysinh;

                ParsePosition position = new ParsePosition(0);
                DATE_FORMATER.setLenient(false);

                Date ngaymua = new Date();
                String ngay2 = DATE_FORMATER.format(ngaymua);
                int namthucte = Integer.parseInt(ngay2.substring(6, 10));
                int ngaythucte = Integer.parseInt(ngay2.substring(0, 2));
                int thangthucte = Integer.parseInt(ngay2.substring(3, 5));
                int namngaysinh = Integer.parseInt(ngaysinh.substring(6, 10));
                int thangngaysinh = Integer.parseInt(ngaysinh.substring(3, 5));
                int tinhtuoi = (namthucte - namngaysinh);
                int tinhthangtt = 12 - thangthucte;
                int tingthangns = 12 - thangngaysinh;
                boolean chec = (thangthucte > thangngaysinh);
                boolean checkngay = false;
                try {
                    System.out.println((DATE_FORMATER.parse(kiemtra, position)).toString());
                    checkngay = true;
                } catch (Exception e) {
                    checkngay = false;
                }
                if (checkngay == true) {

                    if (tinhtuoi < 16) {
                        DialogHelper.alert(this, "Người học này chưa đủ tuổi");
                    } else if (tinhtuoi == 16 && chec == true) {
                        DialogHelper.alert(this, "Người học này còn " + ((tinhthangtt + 12) - (tingthangns + 12) + " tháng nữa để đủ 16 tuổi"));
                    } else {
                        flag = true;    
                    }
                } else {
                    DialogHelper.alert(this, "Ngày sinh không tồn tại");
                    
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

        jPanel1 = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        lblquanlynguoihoc = new javax.swing.JLabel();
        lblmanguoihoc = new javax.swing.JLabel();
        txtmanguoihoc = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lblGioitinh = new javax.swing.JLabel();
        txtHoten = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        lblhotennguoihoc = new javax.swing.JLabel();
        cbxgioitinh = new javax.swing.JComboBox<>();
        lblngaysinh = new javax.swing.JLabel();
        txtngaysinh = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        lbldienthoai = new javax.swing.JLabel();
        txtdienthoai = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        lblhoten5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtareaGhichu = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNguoihoc = new javax.swing.JTable();
        btnThemNguoihoc = new javax.swing.JButton();
        btnXoanguoihoc = new javax.swing.JButton();
        btnCapnhatnguoihoc = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        txttimkiem = new javax.swing.JTextField();
        btntimkiem = new javax.swing.JButton();

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
        lblquanlynguoihoc.setText("QUẢN LÝ NGƯỜI HỌC");

        lblmanguoihoc.setBackground(new java.awt.Color(255, 255, 255));
        lblmanguoihoc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblmanguoihoc.setForeground(new java.awt.Color(102, 0, 102));
        lblmanguoihoc.setText("Mã Người Học");

        txtmanguoihoc.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtmanguoihoc.setBorder(null);

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 20));

        lblGioitinh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblGioitinh.setForeground(new java.awt.Color(102, 0, 102));
        lblGioitinh.setText("Giới Tính");

        txtHoten.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtHoten.setBorder(null);

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setPreferredSize(new java.awt.Dimension(50, 20));

        lblhotennguoihoc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblhotennguoihoc.setForeground(new java.awt.Color(102, 0, 102));
        lblhotennguoihoc.setText("Họ và Tên");

        cbxgioitinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        lblngaysinh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblngaysinh.setForeground(new java.awt.Color(102, 0, 102));
        lblngaysinh.setText("Ngày Sinh");

        txtngaysinh.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtngaysinh.setBorder(null);

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setPreferredSize(new java.awt.Dimension(50, 20));

        lbldienthoai.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbldienthoai.setForeground(new java.awt.Color(102, 0, 102));
        lbldienthoai.setText("Điện Thoại");

        txtdienthoai.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtdienthoai.setBorder(null);

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setPreferredSize(new java.awt.Dimension(50, 20));

        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(102, 0, 102));
        lblEmail.setText("Email");

        txtEmail.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtEmail.setBorder(null);

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator6.setPreferredSize(new java.awt.Dimension(50, 20));

        lblhoten5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblhoten5.setForeground(new java.awt.Color(102, 0, 102));
        lblhoten5.setText("Ghi chú");

        txtareaGhichu.setColumns(20);
        txtareaGhichu.setRows(5);
        jScrollPane1.setViewportView(txtareaGhichu);

        tblNguoihoc.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblNguoihoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9"
            }
        ));
        tblNguoihoc.setGridColor(new java.awt.Color(255, 255, 255));
        tblNguoihoc.setSelectionBackground(new java.awt.Color(102, 0, 102));
        tblNguoihoc.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblNguoihoc.setShowHorizontalLines(false);
        tblNguoihoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNguoihocMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNguoihoc);

        btnThemNguoihoc.setBackground(new java.awt.Color(255, 255, 255));
        btnThemNguoihoc.setForeground(new java.awt.Color(255, 255, 255));
        btnThemNguoihoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgButtonThemHover.png"))); // NOI18N
        btnThemNguoihoc.setBorder(null);
        btnThemNguoihoc.setBorderPainted(false);
        btnThemNguoihoc.setContentAreaFilled(false);
        btnThemNguoihoc.setDefaultCapable(false);
        btnThemNguoihoc.setFocusPainted(false);
        btnThemNguoihoc.setFocusable(false);
        btnThemNguoihoc.setIconTextGap(0);
        btnThemNguoihoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNguoihocActionPerformed(evt);
            }
        });

        btnXoanguoihoc.setBackground(new java.awt.Color(255, 255, 255));
        btnXoanguoihoc.setForeground(new java.awt.Color(255, 255, 255));
        btnXoanguoihoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgButtonXoaHover.png"))); // NOI18N
        btnXoanguoihoc.setBorder(null);
        btnXoanguoihoc.setBorderPainted(false);
        btnXoanguoihoc.setContentAreaFilled(false);
        btnXoanguoihoc.setDefaultCapable(false);
        btnXoanguoihoc.setFocusPainted(false);
        btnXoanguoihoc.setFocusable(false);
        btnXoanguoihoc.setIconTextGap(0);
        btnXoanguoihoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoanguoihocActionPerformed(evt);
            }
        });

        btnCapnhatnguoihoc.setBackground(new java.awt.Color(255, 255, 255));
        btnCapnhatnguoihoc.setForeground(new java.awt.Color(255, 255, 255));
        btnCapnhatnguoihoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgButtonCapNhatHover.png"))); // NOI18N
        btnCapnhatnguoihoc.setBorder(null);
        btnCapnhatnguoihoc.setBorderPainted(false);
        btnCapnhatnguoihoc.setContentAreaFilled(false);
        btnCapnhatnguoihoc.setDefaultCapable(false);
        btnCapnhatnguoihoc.setFocusPainted(false);
        btnCapnhatnguoihoc.setFocusable(false);
        btnCapnhatnguoihoc.setIconTextGap(0);
        btnCapnhatnguoihoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapnhatnguoihocActionPerformed(evt);
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

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgDauHover.png"))); // NOI18N
        btnFirst.setBorder(null);
        btnFirst.setBorderPainted(false);
        btnFirst.setContentAreaFilled(false);
        btnFirst.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFirst.setDefaultCapable(false);
        btnFirst.setFocusPainted(false);
        btnFirst.setFocusable(false);
        btnFirst.setIconTextGap(0);
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
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
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(127, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbxgioitinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblmanguoihoc)
                                            .addComponent(lblGioitinh)
                                            .addComponent(txtmanguoihoc)
                                            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblhotennguoihoc)
                                            .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(169, 169, 169)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lblngaysinh)
                                                .addComponent(txtngaysinh)
                                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lbldienthoai)
                                                .addComponent(txtdienthoai)
                                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lblEmail)
                                                .addComponent(txtEmail)
                                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jScrollPane1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(378, 378, 378)
                                        .addComponent(lblhoten5)))
                                .addGap(36, 36, 36))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addComponent(btntimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(429, 429, 429))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblquanlynguoihoc, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(btnThemNguoihoc, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(btnXoanguoihoc, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(btnCapnhatnguoihoc, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator5)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblquanlynguoihoc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btntimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblmanguoihoc)
                    .addComponent(lblngaysinh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtmanguoihoc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbldienthoai)
                    .addComponent(lblhotennguoihoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(txtHoten, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGioitinh)
                        .addGap(18, 18, 18)
                        .addComponent(cbxgioitinh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtdienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addComponent(lblhoten5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNguoihoc, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoanguoihoc, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapnhatnguoihoc, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btntimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntimkiemActionPerformed
        // TODO add your handling code here:
        this.load();
        this.clear();
        this.edit();

    }//GEN-LAST:event_btntimkiemActionPerformed

    private void btnThemNguoihocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNguoihocActionPerformed
        // TODO add your handling code here:
        check();
        if (flag == true) {
            insert();
        }

    }//GEN-LAST:event_btnThemNguoihocActionPerformed

    private void btnXoanguoihocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoanguoihocActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnXoanguoihocActionPerformed

    private void btnCapnhatnguoihocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapnhatnguoihocActionPerformed
        // TODO add your handling code here:
        check();
        if (flag == true) {
            update();
        }

    }//GEN-LAST:event_btnCapnhatnguoihocActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void tblNguoihocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNguoihocMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            this.index = tblNguoihoc.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();

            }
        }
    }//GEN-LAST:event_tblNguoihocMouseClicked

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        this.index = 0;
        this.edit();
    }//GEN-LAST:event_btnFirstActionPerformed

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
        this.index = tblNguoihoc.getRowCount() - 1;
        this.edit();
    }//GEN-LAST:event_btnLastActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.load();
        this.setStatus(true);
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
            java.util.logging.Logger.getLogger(QuanLyNgHocFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyNgHocFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyNgHocFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyNgHocFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyNgHocFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapnhatnguoihoc;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnThemNguoihoc;
    private javax.swing.JButton btnXoanguoihoc;
    private javax.swing.JButton btntimkiem;
    private javax.swing.JComboBox<String> cbxgioitinh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioitinh;
    private javax.swing.JLabel lbldienthoai;
    private javax.swing.JLabel lblhoten5;
    private javax.swing.JLabel lblhotennguoihoc;
    private javax.swing.JLabel lblmanguoihoc;
    private javax.swing.JLabel lblngaysinh;
    private javax.swing.JLabel lblquanlynguoihoc;
    private javax.swing.JTable tblNguoihoc;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoten;
    private javax.swing.JTextArea txtareaGhichu;
    private javax.swing.JTextField txtdienthoai;
    private javax.swing.JTextField txtmanguoihoc;
    private javax.swing.JTextField txtngaysinh;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables
}
