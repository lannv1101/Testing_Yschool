/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.ui;

import edu.poly.dao.ChuyenDeDao;
import edu.poly.helper.DialogHelper;
import edu.poly.helper.ShareHelper;
import edu.poly.model.ChuyenDe;
import java.io.File;
import java.util.List;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class QuanLyChuyenDeFrm extends javax.swing.JFrame {
 DefaultTableModel tblModel;
    /**
     * Creates new form QuanLyChuyenDeFrm
     */
    public QuanLyChuyenDeFrm() {
        initComponents();
        tblModel = (DefaultTableModel) tblChuyende.getModel();
       
        this.load();
        initTable();
    }
     void initTable(){
        tblModel.setColumnIdentifiers(new String[]{"Mã Chuyên Đề", "Tên Chuyên Đề" ,"Học Phí", "Thời Lượng","Hình","Mô Tả CD"});
        tblChuyende.setModel(tblModel);
    }
    int index = 0;
    ChuyenDeDao dao = new ChuyenDeDao();

     private void loadData() {
        try {
            while (tblModel.getRowCount() > 0) {
                tblModel.removeRow(0);
            }
            ChuyenDeDao dao = new ChuyenDeDao();
            List<ChuyenDe> list = dao.select();
            for (ChuyenDe chuyende : list) {
                Vector row = new Vector();
                row.add(chuyende.getMaCD());
                row.add(chuyende.getTenCD());
                row.add(chuyende.getHocPhi());
                row.add(chuyende.getThoiLuong());
                row.add(chuyende.getHinh());
                row.add(chuyende.getMoTa());

                tblModel.addRow(row);
            }

            tblModel.fireTableDataChanged();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
      void load() {
        DefaultTableModel model = (DefaultTableModel) tblChuyende.getModel();
        model.setRowCount(0);
        try {
            List<ChuyenDe> list = dao.select();
            for (ChuyenDe cd : list) {
                Object[] row = {
                    cd.getMaCD(),
                    cd.getTenCD(),
                    cd.getHocPhi(),
                    cd.getThoiLuong(),
                    cd.getHinh(),
                    cd.getMoTa()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
      
    void insert() {
        ChuyenDe model = getModel();
        try {
            dao.insert(model);
            this.load();
            this.clear();
            DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Thêm mới thất bại!");
        }

    }
     void update() {
        ChuyenDe model = getModel();
        try {
            dao.update(model);
            this.load();
            DialogHelper.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Cập nhật thất bại!");
        }

    }

    void delete() {
        if (DialogHelper.confirm(this, "Bạn có muốn xóa hay không?")) {
            String macd = txtMachuyende.getText();
            try {
                dao.delete(macd);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại!");
            }
        }
    }

    void clear() {
        this.setModel(new ChuyenDe());
        this.setStatus(true);
        lblhinh.setIcon(null);
        
        
    }

    void edit() {
        try {
            String macd = (String) tblChuyende.getValueAt(this.index, 0);
            ChuyenDe model = dao.findById(macd);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    ChuyenDe getModel() {
        ChuyenDe model = new ChuyenDe();
        model.setMaCD(txtMachuyende.getText());
        model.setTenCD(txtTenchuyende.getText());
        model.setThoiLuong(Integer.valueOf(txtThoiluong.getText()));
        model.setHocPhi(Double.valueOf(txtHocphi.getText()));
        model.setHinh(lblhinh.getToolTipText());
        model.setMoTa(txtareaMotachuyende.getText());
        return model;
    }

    void setStatus(boolean insertable) {
      //  txtMachuyende.setEditable(insertable);
        btnThemChuyende.setEnabled(insertable);
        btnCapnhatchuyende.setEnabled(!insertable);
        btnXoaChuyende.setEnabled(!insertable);

        boolean first = this.index > 0;
        boolean last = this.index < tblChuyende.getRowCount() - 1;
        btnFrist.setEnabled(!insertable && first);
        btnPrev.setEnabled(!insertable && first);
        btnNexts.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);
    }
       void setModel(ChuyenDe model) {

        txtMachuyende.setText(model.getMaCD());
        txtTenchuyende.setText(model.getTenCD());
        txtThoiluong.setText(String.valueOf(model.getThoiLuong()));
        txtHocphi.setText(String.valueOf(model.getHocPhi()));
        txtareaMotachuyende.setText(model.getMoTa());
        lblhinh.setToolTipText(model.getHinh());
        if (model.getHinh() != null) {
            lblhinh.setIcon(ShareHelper.readLogo(model.getHinh()));
        }
    }
        void selectImage() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();
            if (ShareHelper.saveLogo(file)) {
                // Hiển thị hình lên form 
              lblhinh.setIcon(ShareHelper.readLogo(file.getName()));
                lblhinh.setToolTipText(file.getName());
            }
        }
    }
        boolean flag=false;
     void check(){
          if(txtMachuyende.getText().length()>5||txtMachuyende.getText().length()<5){
              DialogHelper.alert(this, "Mã chuyên đề phải nhập đúng 5 ký tự");  
         }
          else if(txtMachuyende.getText().equals("")){
              DialogHelper.alert(this, "Mã chuyên đề không được để trống");  
         }else if(txtTenchuyende.getText().length()==0){
              DialogHelper.alert(this, "Tên chuyên đề không được bỏ trống");  
         }//else if(!txtTenchuyende.getText().matches("[a-zA-Z][a-zA-Z ]+")){
       // DialogHelper.alert(this, "Tên chuyên đề chỉ chứa alphabet và ký tự trắng");
         //}
        else if(lblhinh.getIcon()==null){
            DialogHelper.alert(this,"Bạn chưa chọn hình! click vào khu vực hình để chọn");
        }else if(txtThoiluong.getText().equals("")||!txtHocphi.getText().equals("")){
           checktl();
        }else{
             flag=true;
         } 
     }
    void checktl(){
     if(txtThoiluong.getText().equals("")){
            DialogHelper.alert(this, "Không bỏ trống thời lượng!"); 
     }else if(txtHocphi.getText().equals("")){
           DialogHelper.alert(this, "Không bỏ trống học phí!"); 
    }else if(!txtThoiluong.getText().equals("")||!txtHocphi.getText().equals("")){
         String hp="java.lang.NumberFormatException: For input string: ";
         String tl2="java.lang.NumberFormatException: For input string: ";
         String tl="";
        try {
            if(!txtThoiluong.getText().equals("")||!txtHocphi.getText().equals("")){
            int thoiluong = Integer.parseInt(txtThoiluong.getText());
            int hocphi = Integer.parseInt(txtHocphi.getText());
            
            if (thoiluong <= 0) {
                DialogHelper.alert(this, "Thời lượng là số dương và phải lớn hơn 0");
            }
            else if (hocphi <= 0) {
                DialogHelper.alert(this, "Học phí là số dương và phải lớn hơn 0");
            }else{
               flag=true;
               
            }
            }
          
        } catch (Exception e) {
              System.out.println(e);
              tl+=e.toString();
              hp+="\""+(txtHocphi.getText()).toString()+"\"";
              tl2+="\""+(txtThoiluong.getText()).toString()+"\"";
              System.out.println(hp);
               System.out.println(tl2);
              if(tl.equals(hp)){
                   DialogHelper.alert(this, "Học phí phải truyền vào kiểu số!"); 
              }else if(tl.equals(tl2)){
                  DialogHelper.alert(this, "Thời lượng phải truyền vào kiểu số!"); 
              }
             
         
 
        }
    }}
  


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblquanlynguoihoc = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        lblhinhlogo = new javax.swing.JLabel();
        lblhinh = new javax.swing.JLabel();
        txtMachuyende = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lblmanguoihoc1 = new javax.swing.JLabel();
        lblmanguoihoc2 = new javax.swing.JLabel();
        txtTenchuyende = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        txtThoiluong = new javax.swing.JTextField();
        lblmanguoihoc3 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txtHocphi = new javax.swing.JTextField();
        lblmanguoihoc4 = new javax.swing.JLabel();
        lblMotachuyende = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtareaMotachuyende = new javax.swing.JTextArea();
        btnThemChuyende = new javax.swing.JButton();
        btnXoaChuyende = new javax.swing.JButton();
        btnCapnhatchuyende = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChuyende = new javax.swing.JTable();
        btnNexts = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnFrist = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1078, 630));

        lblquanlynguoihoc.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblquanlynguoihoc.setForeground(new java.awt.Color(102, 0, 102));
        lblquanlynguoihoc.setText("QUẢN LÝ CHUYÊN ĐỀ");

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        lblhinhlogo.setBackground(new java.awt.Color(255, 255, 255));
        lblhinhlogo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblhinhlogo.setForeground(new java.awt.Color(102, 0, 102));
        lblhinhlogo.setText("Hình Logo");

        lblhinh.setBackground(new java.awt.Color(255, 255, 255));
        lblhinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblhinh.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 0, 102), 1, true));
        lblhinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblhinhMouseClicked(evt);
            }
        });

        txtMachuyende.setBorder(null);

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 20));

        lblmanguoihoc1.setBackground(new java.awt.Color(255, 255, 255));
        lblmanguoihoc1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblmanguoihoc1.setForeground(new java.awt.Color(102, 0, 102));
        lblmanguoihoc1.setText("Mã Chuyên Đề");

        lblmanguoihoc2.setBackground(new java.awt.Color(255, 255, 255));
        lblmanguoihoc2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblmanguoihoc2.setForeground(new java.awt.Color(102, 0, 102));
        lblmanguoihoc2.setText("Tên Chuyên Đề");

        txtTenchuyende.setBorder(null);

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setPreferredSize(new java.awt.Dimension(50, 20));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setPreferredSize(new java.awt.Dimension(50, 20));

        txtThoiluong.setBorder(null);

        lblmanguoihoc3.setBackground(new java.awt.Color(255, 255, 255));
        lblmanguoihoc3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblmanguoihoc3.setForeground(new java.awt.Color(102, 0, 102));
        lblmanguoihoc3.setText("Thời Lượng(Giờ)");

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setPreferredSize(new java.awt.Dimension(50, 20));

        txtHocphi.setBorder(null);

        lblmanguoihoc4.setBackground(new java.awt.Color(255, 255, 255));
        lblmanguoihoc4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblmanguoihoc4.setForeground(new java.awt.Color(102, 0, 102));
        lblmanguoihoc4.setText("Học Phí");

        lblMotachuyende.setBackground(new java.awt.Color(255, 255, 255));
        lblMotachuyende.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMotachuyende.setForeground(new java.awt.Color(102, 0, 102));
        lblMotachuyende.setText("Mô Tả Chuyên Đề");

        txtareaMotachuyende.setColumns(20);
        txtareaMotachuyende.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtareaMotachuyende.setRows(5);
        txtareaMotachuyende.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 0, 102), 2, true));
        jScrollPane1.setViewportView(txtareaMotachuyende);

        btnThemChuyende.setBackground(new java.awt.Color(255, 255, 255));
        btnThemChuyende.setForeground(new java.awt.Color(255, 255, 255));
        btnThemChuyende.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgButtonThemHover.png"))); // NOI18N
        btnThemChuyende.setBorder(null);
        btnThemChuyende.setBorderPainted(false);
        btnThemChuyende.setContentAreaFilled(false);
        btnThemChuyende.setDefaultCapable(false);
        btnThemChuyende.setFocusPainted(false);
        btnThemChuyende.setFocusable(false);
        btnThemChuyende.setIconTextGap(0);
        btnThemChuyende.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemChuyendeActionPerformed(evt);
            }
        });

        btnXoaChuyende.setBackground(new java.awt.Color(255, 255, 255));
        btnXoaChuyende.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaChuyende.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgButtonXoaHover.png"))); // NOI18N
        btnXoaChuyende.setBorder(null);
        btnXoaChuyende.setBorderPainted(false);
        btnXoaChuyende.setContentAreaFilled(false);
        btnXoaChuyende.setDefaultCapable(false);
        btnXoaChuyende.setFocusPainted(false);
        btnXoaChuyende.setFocusable(false);
        btnXoaChuyende.setIconTextGap(0);
        btnXoaChuyende.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaChuyendeActionPerformed(evt);
            }
        });

        btnCapnhatchuyende.setBackground(new java.awt.Color(255, 255, 255));
        btnCapnhatchuyende.setForeground(new java.awt.Color(255, 255, 255));
        btnCapnhatchuyende.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgButtonCapNhatHover.png"))); // NOI18N
        btnCapnhatchuyende.setBorder(null);
        btnCapnhatchuyende.setBorderPainted(false);
        btnCapnhatchuyende.setContentAreaFilled(false);
        btnCapnhatchuyende.setDefaultCapable(false);
        btnCapnhatchuyende.setFocusPainted(false);
        btnCapnhatchuyende.setFocusable(false);
        btnCapnhatchuyende.setIconTextGap(0);
        btnCapnhatchuyende.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapnhatchuyendeActionPerformed(evt);
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

        tblChuyende.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblChuyende.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tblChuyende.setGridColor(new java.awt.Color(255, 255, 255));
        tblChuyende.setSelectionBackground(new java.awt.Color(102, 0, 102));
        tblChuyende.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblChuyende.setShowVerticalLines(false);
        tblChuyende.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChuyendeMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblChuyende);

        btnNexts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/poly/ui/images/bgNextHover.png"))); // NOI18N
        btnNexts.setBorder(null);
        btnNexts.setBorderPainted(false);
        btnNexts.setContentAreaFilled(false);
        btnNexts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNexts.setDefaultCapable(false);
        btnNexts.setFocusPainted(false);
        btnNexts.setFocusable(false);
        btnNexts.setIconTextGap(0);
        btnNexts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextsActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator5)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(lblhinh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(lblhinhlogo)))
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblmanguoihoc1)
                    .addComponent(txtMachuyende)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblmanguoihoc2)
                    .addComponent(txtTenchuyende)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                    .addComponent(lblmanguoihoc3)
                    .addComponent(txtThoiluong)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                    .addComponent(lblmanguoihoc4)
                    .addComponent(txtHocphi)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblMotachuyende)
                        .addGap(76, 76, 76))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblquanlynguoihoc, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 181, Short.MAX_VALUE)
                        .addComponent(btnThemChuyende, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(btnXoaChuyende, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(btnCapnhatchuyende, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(181, 181, 181))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFrist, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnNexts, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(432, 432, 432))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblquanlynguoihoc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblhinhlogo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblhinh, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblmanguoihoc1)
                            .addComponent(lblMotachuyende))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtMachuyende, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblmanguoihoc2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenchuyende, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblmanguoihoc3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtThoiluong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblmanguoihoc4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHocphi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemChuyende, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaChuyende, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapnhatchuyende, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFrist, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNexts, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemChuyendeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemChuyendeActionPerformed
        check();
        if (flag==true) {
            insert();
        }
      
    }//GEN-LAST:event_btnThemChuyendeActionPerformed

    private void btnXoaChuyendeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaChuyendeActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnXoaChuyendeActionPerformed

    private void btnCapnhatchuyendeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapnhatchuyendeActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnCapnhatchuyendeActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void tblChuyendeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChuyendeMouseClicked
        // TODO add your handling code here:
          if (evt.getClickCount() == 1) {
            this.index = tblChuyende.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
               
            }
        }
    }//GEN-LAST:event_tblChuyendeMouseClicked

    private void lblhinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblhinhMouseClicked
        // TODO add your handling code here:
        this.selectImage();
    }//GEN-LAST:event_lblhinhMouseClicked

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

    private void btnNextsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextsActionPerformed
        // TODO add your handling code here:
         this.index++;
        this.edit();
    }//GEN-LAST:event_btnNextsActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        
          this.index = tblChuyende.getRowCount() - 1;
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
            java.util.logging.Logger.getLogger(QuanLyChuyenDeFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyChuyenDeFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyChuyenDeFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyChuyenDeFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyChuyenDeFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapnhatchuyende;
    private javax.swing.JButton btnFrist;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNexts;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnThemChuyende;
    private javax.swing.JButton btnXoaChuyende;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblMotachuyende;
    private javax.swing.JLabel lblhinh;
    private javax.swing.JLabel lblhinhlogo;
    private javax.swing.JLabel lblmanguoihoc1;
    private javax.swing.JLabel lblmanguoihoc2;
    private javax.swing.JLabel lblmanguoihoc3;
    private javax.swing.JLabel lblmanguoihoc4;
    private javax.swing.JLabel lblquanlynguoihoc;
    private javax.swing.JTable tblChuyende;
    private javax.swing.JTextField txtHocphi;
    private javax.swing.JTextField txtMachuyende;
    private javax.swing.JTextField txtTenchuyende;
    private javax.swing.JTextField txtThoiluong;
    private javax.swing.JTextArea txtareaMotachuyende;
    // End of variables declaration//GEN-END:variables
}
