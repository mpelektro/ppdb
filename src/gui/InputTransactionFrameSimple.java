/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import iuran.*;
import java.awt.Color;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.util.*;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import kasir.Clerk;
import kasir.Control;
import kasir.DBSR;
import kasir.Filter;
import org.openide.util.Exceptions;
import pelajar.Profil;
import sak.Kalender;
import sak.KasirException;
import org.apache.commons.collections4.keyvalue.DefaultMapEntry;
import org.netbeans.validation.api.Validator;
import org.netbeans.validation.api.ValidatorUtils;
import org.netbeans.validation.api.ui.swing.ValidationPanel;
import org.netbeans.validation.api.builtin.stringvalidation.StringValidators;
import org.openide.util.Exceptions;
import pelajar.Level;
import java.util.UUID;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import pelajar.Biodata;
import printout.BuktiPembayaran;
import printout.TunggakanBean;
import printout.TunggakanBeanFactory;
/**
 *
 * @author Master
 */
public class InputTransactionFrameSimple extends javax.swing.JFrame {
    private TableModel tableModelSeragam;
    private TableModel tableModelBuku;
    private TableModel tableModelIPP;
    private TableModel tableModelIKS;
    private final ValidationPanel validationPanel = new ValidationPanel();
    private IPP ippFromDB;
    private IPP ippCurrent;
    private IPP ippStoreToDB;
    private IKS iksFromDB;
    private IKS iksCurrent;
    private IKS iksStoreToDB;
    private ComboBoxModel tahunComboBoxModel;
    private final String[] namaBulan = {"JULI", "AGUSTUS", "SEPTEMBER", "OKTOBER", "NOPEMBER", "DESEMBER", "JANUARI", "PEBRUARI", "MARET", "APRIL", "MEI", "JUNI"};
    private ArrayList<Float> iDDAmounts;
    private ArrayList<Float> beasiswaAmounts;
    private ArrayList<Float> beasiswaCostAmounts;
    private UUID ipspTDetailUUID;
    private UUID tSumUUID;
    private UUID ippTDetailUUID;
    private UUID iksTDetailUUID;
    private UUID seragamTDetailUUID;
    private UUID ikkTDetailUUID;
    private UUID osisTDetailUUID;
    private UUID ipsbTDetailUUID;
    private UUID iusTDetailUUID;
    private UUID iuaTDetailUUID;
    private UUID illTDetailUUID;
    private UUID sumbanganTDetailUUID;
    private Profil profil;
    private Clerk clerk;
    private TransactionSummary transactionSummary;
    private IPSPTransactionDetail iPSPTransactionDetail;
    private SeragamTransactionDetail seragamTransactionDetail;
    private Kalender theCreatedDate;
    private IPSP ipsp;
    private IDD idd;
    private IDDTransactionDetail iddTDetail;
    private float minIpsp;
    private float maxIpsp;
    private Seragam seragam;
    private List<Seragam> seragamS;
    private List<Seragam> seragamSToDB;
    private Buku buku;
    private List<Buku> bukuS;
    private List<Buku> bukuSToDB;
    private float totalAmount = 0f;
    private float unpaidIPP = 0f;
    private float unpaidIKS = 0f;
    private float unpaidSeragam = 0f;
    private float unpaidBuku = 0f;
    private List<Integer> tahunIPP;
    private List<Integer> tahunIKS;
    private String tunggakanIPPString = new String("");
    private String tunggakanIPPEachAmountString = new String("");
    private String tunggakanIKSString = new String("");
    private String tunggakanIKSEachAmountString = new String("");
    /* JasperReport is the object
    that holds our compiled jrxml file */
    JasperReport jasperReport;


    /* JasperPrint is the object contains
    report after result filling process */
    JasperPrint jasperPrint;
    private UUID bukuTDetailUUID;
    private BukuTransactionDetail bukuTransactionDetail;

    /**
     * Creates new form InputTransactionFrame
     */
    public InputTransactionFrameSimple() {
        initComponents();
    }
    
    public InputTransactionFrameSimple(Clerk cl, Profil profil) {
        this.clerk = cl;
        Clerk.current.id = this.clerk.id;
        this.profil = profil;
        try {
            this.ipsp = Control.selectIuran(Iuran.Tipe.IPSP, IPSP.noIndukColName, false, profil.noInduk);
            minIpsp = 0F;
            maxIpsp = this.ipsp.amount - this.ipsp.totalInstallment;
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            JOptionPane.showMessageDialog(rootPane, "IPSP Belum Di Setting!\r\n".concat(ex.toString()));
            this.dispose();
        } catch (KasirException ex) {
            Exceptions.printStackTrace(ex);
            JOptionPane.showMessageDialog(rootPane, "IPSP Belum Di Setting!\r\n".concat(ex.toString()));
            this.dispose();
        }
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogTransactionSummary = new javax.swing.JDialog();
        jPanelDialogMain = new javax.swing.JPanel();
        jPanelDialogIPSP = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldIPSPAmount1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxDialogIPSP = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldIPSPNote1 = new javax.swing.JTextField();
        jPanelDialogSeragam = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableDialogSeragam = new javax.swing.JTable();
        jButtonSave1 = new javax.swing.JButton();
        jButtonDialogCancel = new javax.swing.JButton();
        jPanelTransactionSummary1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldTransactionSummaryNote1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldTSumTotalAmount = new javax.swing.JTextField();
        jPanelDialogProfil = new javax.swing.JPanel();
        jLableProfilTitle1 = new javax.swing.JLabel();
        jLabelNama1 = new javax.swing.JLabel();
        jLabelLevel2Level4 = new javax.swing.JLabel();
        jLabelNoInduk1 = new javax.swing.JLabel();
        jLabelLevel2 = new javax.swing.JLabel();
        jTextFieldNama1 = new javax.swing.JTextField();
        jTextFieldNomorInduk1 = new javax.swing.JTextField();
        jTextFieldLevel2Level4 = new javax.swing.JTextField();
        jPanelDialogIPP = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableIPP1 = new javax.swing.JTable();
        jLabelTahun1 = new javax.swing.JLabel();
        jComboBoxTahun1 = new javax.swing.JComboBox();
        jPanelDialogBuku = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTableDialogBuku = new javax.swing.JTable();
        jPanelDialogMain1 = new javax.swing.JPanel();
        jPanelDialogIPSP1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldIPSPAmount2 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jComboBoxDialogIPSP1 = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        jTextFieldIPSPNote2 = new javax.swing.JTextField();
        jPanelDialogSeragam1 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableDialogSeragam1 = new javax.swing.JTable();
        jButtonSave2 = new javax.swing.JButton();
        jButtonDialogCancel1 = new javax.swing.JButton();
        jPanelTransactionSummary2 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jTextFieldTransactionSummaryNote2 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextFieldTSumTotalAmount1 = new javax.swing.JTextField();
        jPanelDialogProfil1 = new javax.swing.JPanel();
        jLableProfilTitle2 = new javax.swing.JLabel();
        jLabelNama2 = new javax.swing.JLabel();
        jLabelLevel2Level5 = new javax.swing.JLabel();
        jLabelNoInduk2 = new javax.swing.JLabel();
        jLabelLevel3 = new javax.swing.JLabel();
        jTextFieldNama2 = new javax.swing.JTextField();
        jTextFieldNomorInduk2 = new javax.swing.JTextField();
        jTextFieldLevel2Level5 = new javax.swing.JTextField();
        jPanelDialogIPP1 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableIPP2 = new javax.swing.JTable();
        jLabelTahun2 = new javax.swing.JLabel();
        jComboBoxTahun2 = new javax.swing.JComboBox();
        jFrameIPP = new javax.swing.JFrame();
        jPanelIPP = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabelTahun = new javax.swing.JLabel();
        jComboBoxTahun = new javax.swing.JComboBox();
        jLabel21 = new javax.swing.JLabel();
        jUnpaidIPP = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableIPP = new javax.swing.JTable();
        jButtonBayarIPP = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextFieldIDDSaldo = new javax.swing.JFormattedTextField();
        jFrameIDD = new javax.swing.JFrame();
        jPanelIDD = new javax.swing.JPanel();
        jLabelIDDTitle = new javax.swing.JLabel();
        jLabelAmount3 = new javax.swing.JLabel();
        jTextFieldIDDAmount = new javax.swing.JTextField();
        jLabelNote2 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextAreaIDDNote = new javax.swing.JTextArea();
        jLabelAmount4 = new javax.swing.JLabel();
        jTextFieldIDDTransactionName = new javax.swing.JTextField();
        jButtonIDDOK = new javax.swing.JButton();
        jFrameIPSP = new javax.swing.JFrame();
        jPanelIPSP = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldIPSPAmount = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxIPSP = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldIPSPNote = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldIPSPIuranAmount = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldIPSPSisaAmount = new javax.swing.JTextField();
        jButtonIPSPOK = new javax.swing.JButton();
        jFrameSeragam = new javax.swing.JFrame();
        jPanelSeragam = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSeragam = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jUnpaidSeragam = new javax.swing.JTextField();
        jButtonBayarSeragam = new javax.swing.JButton();
        jFrameBuku = new javax.swing.JFrame();
        jPaneBuku = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableBuku = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        jUnpaidBuku = new javax.swing.JTextField();
        jButtonBayarBuku = new javax.swing.JButton();
        jFrameIKS = new javax.swing.JFrame();
        jPanelIKS = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabelTahun3 = new javax.swing.JLabel();
        jComboBoxTahun3 = new javax.swing.JComboBox();
        jLabel33 = new javax.swing.JLabel();
        jUnpaidIKS = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTableIKS = new javax.swing.JTable();
        jButtonBayarIKS = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jFormattedTextFieldIDDSaldo1 = new javax.swing.JFormattedTextField();
        DefaultComboBoxModel paymentMethodComboBoxModel = new DefaultComboBoxModel(TransactionDetail.PaymentMethod.values());
        tableModelSeragam = new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, new Boolean(false)},
                {null, null, null, new Boolean(true)},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Seragam", "Jumlah", "Catatan", "CheckBox"
            }
        );

        tableModelBuku = new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, new Boolean(false)},
                {null, null, null, new Boolean(true)},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Buku", "Jumlah", "Catatan", "CheckBox"
            }
        );

        tableModelIPP = new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"JULI", null},
                {"AGUSTUS", null},
                {"SEPTEMBER", null},
                {"OKTOBER", null},
                {"NOVEMBER", null},
                {"DESEMBER", null},
                {"JANUARI", null},
                {"FEBRUARI", null},
                {"MARET", null},
                {"APRIL", null},
                {"MEI", null},
                {"JUNI", null}

            },
            new String [] {
                "Bulan", "Biaya IPP"
            }
        ){
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class
            };

            boolean[] canEdit = new boolean [] {
                false, true
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            //        @Override
            //        public boolean isCellEditable(int rowIndex, int columnIndex) {
                //            if(editable){
                    //                return canEdit [columnIndex];
                    //
                    //            }else{
                    //                return false;
                    //            }
                //        }
        };

        tableModelIKS = new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Tahun Pertama", null},
                {"Tahun Kedua", null},
                {"Tahun Ketiga", null}
            },
            new String [] {
                "Tahun", "Biaya IKS"
            }
        ){
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class
            };

            boolean[] canEdit = new boolean [] {
                false, true
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        };

        try{
            //tableModelSeragam = buildSeragamTableModel(profil);
            tableModelIPP = buildIPPTableModel(profil, profil.currentLevel.tahun);
            tableModelIKS = buildIKSTableModel(profil, profil.currentLevel.tahun);
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(KasirException ex){
            ex.printStackTrace();
        }
        jPanelMain = new javax.swing.JPanel();
        jPanelProfil = new javax.swing.JPanel();
        jLableProfilTitle = new javax.swing.JLabel();
        jLabelNama = new javax.swing.JLabel();
        jLabelLevel2Level3 = new javax.swing.JLabel();
        jLabelNoInduk = new javax.swing.JLabel();
        jLabelLevel1 = new javax.swing.JLabel();
        jTextFieldNama = new javax.swing.JTextField();
        jTextFieldNomorInduk = new javax.swing.JTextField();
        jTextFieldLevel2Level3 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldTransactionSummaryNote = new javax.swing.JTextField();
        jButtonSubmit = new javax.swing.JButton();
        jButtonIPP = new javax.swing.JButton();
        jButtonIPSP = new javax.swing.JButton();
        jButtonSeragam = new javax.swing.JButton();
        jButtonIDD = new javax.swing.JButton();
        jTextFieldIPPAmountSimple = new javax.swing.JFormattedTextField();
        jTextFieldIPSPAmountSimple = new javax.swing.JFormattedTextField();
        jTextFieldIDDAmountSimple = new javax.swing.JFormattedTextField();
        jTextFieldSeragamAmountSimple = new javax.swing.JFormattedTextField();
        jButtonReportIPP = new javax.swing.JButton();
        jButtonBuku = new javax.swing.JButton();
        jTextFieldBukuAmountSimple = new javax.swing.JFormattedTextField();
        jButtonIKS = new javax.swing.JButton();
        jTextFieldIKSAmountSimple = new javax.swing.JFormattedTextField();
        jButtonILL = new javax.swing.JButton();
        jTextFieldILLAmountSimple = new javax.swing.JFormattedTextField();
        jButtonIPSB = new javax.swing.JButton();
        jTextFieldIPSBAmountSimple = new javax.swing.JFormattedTextField();
        jButtonIUA = new javax.swing.JButton();
        jTextFieldIUAAmountSimple = new javax.swing.JFormattedTextField();
        jButtonIUS = new javax.swing.JButton();
        jTextFieldIUSAmountSimple = new javax.swing.JFormattedTextField();
        jButtonOSIS = new javax.swing.JButton();
        jTextFieldOSISAmountSimple = new javax.swing.JFormattedTextField();
        jButtonPASB = new javax.swing.JButton();
        jTextFieldPASBAmountSimple = new javax.swing.JFormattedTextField();
        jButtonPVT = new javax.swing.JButton();
        jTextFieldPVTAmountSimple = new javax.swing.JFormattedTextField();
        jButtonTabungan = new javax.swing.JButton();
        jTextFieldTabunganAmountSimple = new javax.swing.JFormattedTextField();
        jButtonSumbangan = new javax.swing.JButton();
        jTextFieldSumbanganAmountSimple = new javax.swing.JFormattedTextField();

        jDialogTransactionSummary.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialogTransactionSummary.setMinimumSize(new java.awt.Dimension(1024, 768));

        jPanelDialogMain.setMinimumSize(new java.awt.Dimension(1024, 768));
        jPanelDialogMain.setPreferredSize(new java.awt.Dimension(1024, 768));

        jLabel6.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel6.text")); // NOI18N

        jLabel7.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel7.text")); // NOI18N

        jTextFieldIPSPAmount1.setEditable(false);
        jTextFieldIPSPAmount1.setFocusable(false);

        jLabel8.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel8.text")); // NOI18N

        jComboBoxDialogIPSP.setModel(paymentMethodComboBoxModel);
        jComboBoxDialogIPSP.setEnabled(false);
        jComboBoxDialogIPSP.setFocusable(false);
        jComboBoxDialogIPSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDialogIPSPActionPerformed(evt);
            }
        });

        jLabel9.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel9.text")); // NOI18N

        jTextFieldIPSPNote1.setEditable(false);
        jTextFieldIPSPNote1.setFocusable(false);

        javax.swing.GroupLayout jPanelDialogIPSPLayout = new javax.swing.GroupLayout(jPanelDialogIPSP);
        jPanelDialogIPSP.setLayout(jPanelDialogIPSPLayout);
        jPanelDialogIPSPLayout.setHorizontalGroup(
            jPanelDialogIPSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogIPSPLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldIPSPAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxDialogIPSP, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldIPSPNote1))
        );
        jPanelDialogIPSPLayout.setVerticalGroup(
            jPanelDialogIPSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogIPSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6)
                .addComponent(jLabel7)
                .addComponent(jTextFieldIPSPAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel8)
                .addComponent(jComboBoxDialogIPSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel9)
                .addComponent(jTextFieldIPSPNote1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel12.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel12.text")); // NOI18N

        jTableDialogSeragam.setModel(tableModelSeragam);
        jTableDialogSeragam.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableDialogSeragamPropertyChange(evt);
            }
        });
        jScrollPane4.setViewportView(jTableDialogSeragam);

        javax.swing.GroupLayout jPanelDialogSeragamLayout = new javax.swing.GroupLayout(jPanelDialogSeragam);
        jPanelDialogSeragam.setLayout(jPanelDialogSeragamLayout);
        jPanelDialogSeragamLayout.setHorizontalGroup(
            jPanelDialogSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogSeragamLayout.createSequentialGroup()
                .addGroup(jPanelDialogSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDialogSeragamLayout.setVerticalGroup(
            jPanelDialogSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogSeragamLayout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButtonSave1.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonSave1.text")); // NOI18N
        jButtonSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSave1ActionPerformed(evt);
            }
        });

        jButtonDialogCancel.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonDialogCancel.text")); // NOI18N
        jButtonDialogCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDialogCancelActionPerformed(evt);
            }
        });

        jLabel15.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel15.text")); // NOI18N

        jTextFieldTransactionSummaryNote1.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldTransactionSummaryNote1.text")); // NOI18N

        jLabel16.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel16.text")); // NOI18N

        jTextFieldTSumTotalAmount.setEditable(false);
        jTextFieldTSumTotalAmount.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextFieldTSumTotalAmount.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldTSumTotalAmount.text")); // NOI18N
        jTextFieldTSumTotalAmount.setFocusable(false);

        javax.swing.GroupLayout jPanelTransactionSummary1Layout = new javax.swing.GroupLayout(jPanelTransactionSummary1);
        jPanelTransactionSummary1.setLayout(jPanelTransactionSummary1Layout);
        jPanelTransactionSummary1Layout.setHorizontalGroup(
            jPanelTransactionSummary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTransactionSummary1Layout.createSequentialGroup()
                .addGroup(jPanelTransactionSummary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelTransactionSummary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldTSumTotalAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addComponent(jTextFieldTransactionSummaryNote1)))
        );
        jPanelTransactionSummary1Layout.setVerticalGroup(
            jPanelTransactionSummary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTransactionSummary1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanelTransactionSummary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextFieldTransactionSummaryNote1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelTransactionSummary1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextFieldTSumTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLableProfilTitle1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLableProfilTitle1.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLableProfilTitle1.text")); // NOI18N

        jLabelNama1.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabelNama1.text")); // NOI18N

        jLabelLevel2Level4.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabelLevel2Level4.text")); // NOI18N

        jLabelNoInduk1.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabelNoInduk1.text")); // NOI18N

        jLabelLevel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelLevel2.setText(profil.currentLevel.level1.toString());

        jTextFieldNama1.setEditable(false);
        jTextFieldNama1.setText(profil.biodata.nama);
        jTextFieldNama1.setFocusable(false);
        jTextFieldNama.setText(profil.biodata.nama);

        jTextFieldNomorInduk1.setEditable(false);
        jTextFieldNomorInduk1.setText(profil.noInduk);
        jTextFieldNomorInduk1.setFocusable(false);

        jTextFieldLevel2Level4.setEditable(false);
        jTextFieldLevel2Level4.setText(profil.currentLevel.level2.toString().concat(" "+profil.currentLevel.level3.toString()));
        jTextFieldLevel2Level4.setFocusable(false);

        javax.swing.GroupLayout jPanelDialogProfilLayout = new javax.swing.GroupLayout(jPanelDialogProfil);
        jPanelDialogProfil.setLayout(jPanelDialogProfilLayout);
        jPanelDialogProfilLayout.setHorizontalGroup(
            jPanelDialogProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogProfilLayout.createSequentialGroup()
                .addGroup(jPanelDialogProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDialogProfilLayout.createSequentialGroup()
                        .addComponent(jLableProfilTitle1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLevel2))
                    .addGroup(jPanelDialogProfilLayout.createSequentialGroup()
                        .addGroup(jPanelDialogProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNama1)
                            .addComponent(jLabelNoInduk1)
                            .addComponent(jLabelLevel2Level4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelDialogProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldNomorInduk1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNama1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldLevel2Level4, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDialogProfilLayout.setVerticalGroup(
            jPanelDialogProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogProfilLayout.createSequentialGroup()
                .addGroup(jPanelDialogProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLableProfilTitle1)
                    .addComponent(jLabelLevel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDialogProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNama1)
                    .addComponent(jTextFieldNama1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanelDialogProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNoInduk1)
                    .addComponent(jTextFieldNomorInduk1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDialogProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLevel2Level4)
                    .addComponent(jTextFieldLevel2Level4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel11.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel11.text")); // NOI18N

        jTableIPP1.setModel(tableModelIPP);
        jScrollPane3.setViewportView(jTableIPP1);

        jLabelTahun1.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabelTahun1.text")); // NOI18N

        try{
            tahunComboBoxModel = buildIPPtahunComboBoxModel(profil);
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(KasirException ex){
            ex.printStackTrace();
        }
        jComboBoxTahun1.setModel(tahunComboBoxModel);
        jComboBoxTahun1.setEnabled(false);
        jComboBoxTahun1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTahun1ActionPerformed(evt);
            }
        });
        jComboBoxTahun1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBoxTahun1PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabelTahun1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxTahun1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTahun1)
                    .addComponent(jComboBoxTahun1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );

        javax.swing.GroupLayout jPanelDialogIPPLayout = new javax.swing.GroupLayout(jPanelDialogIPP);
        jPanelDialogIPP.setLayout(jPanelDialogIPPLayout);
        jPanelDialogIPPLayout.setHorizontalGroup(
            jPanelDialogIPPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogIPPLayout.createSequentialGroup()
                .addComponent(jLabel11)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelDialogIPPLayout.setVerticalGroup(
            jPanelDialogIPPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogIPPLayout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel31.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel31.text")); // NOI18N

        jTableDialogBuku.setModel(tableModelSeragam);
        jTableDialogBuku.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableDialogBukuPropertyChange(evt);
            }
        });
        jScrollPane10.setViewportView(jTableDialogBuku);

        javax.swing.GroupLayout jPanelDialogBukuLayout = new javax.swing.GroupLayout(jPanelDialogBuku);
        jPanelDialogBuku.setLayout(jPanelDialogBukuLayout);
        jPanelDialogBukuLayout.setHorizontalGroup(
            jPanelDialogBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogBukuLayout.createSequentialGroup()
                .addGroup(jPanelDialogBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDialogBukuLayout.setVerticalGroup(
            jPanelDialogBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogBukuLayout.createSequentialGroup()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanelDialogMainLayout = new javax.swing.GroupLayout(jPanelDialogMain);
        jPanelDialogMain.setLayout(jPanelDialogMainLayout);
        jPanelDialogMainLayout.setHorizontalGroup(
            jPanelDialogMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogMainLayout.createSequentialGroup()
                .addGroup(jPanelDialogMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDialogMainLayout.createSequentialGroup()
                        .addComponent(jPanelDialogProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelTransactionSummary1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDialogMainLayout.createSequentialGroup()
                        .addComponent(jButtonSave1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDialogCancel))
                    .addGroup(jPanelDialogMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanelDialogIPP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelDialogIPSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelDialogMainLayout.createSequentialGroup()
                        .addComponent(jPanelDialogSeragam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelDialogBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 90, Short.MAX_VALUE))
        );
        jPanelDialogMainLayout.setVerticalGroup(
            jPanelDialogMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogMainLayout.createSequentialGroup()
                .addGroup(jPanelDialogMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelTransactionSummary1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelDialogProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDialogIPP, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDialogIPSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDialogMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelDialogSeragam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelDialogBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDialogMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave1)
                    .addComponent(jButtonDialogCancel))
                .addContainerGap(188, Short.MAX_VALUE))
        );

        jPanelDialogMain1.setMinimumSize(new java.awt.Dimension(1024, 768));

        jLabel18.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel18.text")); // NOI18N

        jLabel22.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel22.text")); // NOI18N

        jTextFieldIPSPAmount2.setEditable(false);
        jTextFieldIPSPAmount2.setFocusable(false);

        jLabel23.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel23.text")); // NOI18N

        jComboBoxDialogIPSP1.setModel(paymentMethodComboBoxModel);
        jComboBoxDialogIPSP1.setEnabled(false);
        jComboBoxDialogIPSP1.setFocusable(false);
        jComboBoxDialogIPSP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDialogIPSP1ActionPerformed(evt);
            }
        });

        jLabel24.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel24.text")); // NOI18N

        jTextFieldIPSPNote2.setEditable(false);
        jTextFieldIPSPNote2.setFocusable(false);

        javax.swing.GroupLayout jPanelDialogIPSP1Layout = new javax.swing.GroupLayout(jPanelDialogIPSP1);
        jPanelDialogIPSP1.setLayout(jPanelDialogIPSP1Layout);
        jPanelDialogIPSP1Layout.setHorizontalGroup(
            jPanelDialogIPSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogIPSP1Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldIPSPAmount2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxDialogIPSP1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldIPSPNote2))
        );
        jPanelDialogIPSP1Layout.setVerticalGroup(
            jPanelDialogIPSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogIPSP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel18)
                .addComponent(jLabel22)
                .addComponent(jTextFieldIPSPAmount2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel23)
                .addComponent(jComboBoxDialogIPSP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel24)
                .addComponent(jTextFieldIPSPNote2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel25.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel25.text")); // NOI18N

        jTableDialogSeragam1.setModel(tableModelSeragam);
        jTableDialogSeragam1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableDialogSeragam1PropertyChange(evt);
            }
        });
        jScrollPane6.setViewportView(jTableDialogSeragam1);

        javax.swing.GroupLayout jPanelDialogSeragam1Layout = new javax.swing.GroupLayout(jPanelDialogSeragam1);
        jPanelDialogSeragam1.setLayout(jPanelDialogSeragam1Layout);
        jPanelDialogSeragam1Layout.setHorizontalGroup(
            jPanelDialogSeragam1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogSeragam1Layout.createSequentialGroup()
                .addGroup(jPanelDialogSeragam1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(149, Short.MAX_VALUE))
        );
        jPanelDialogSeragam1Layout.setVerticalGroup(
            jPanelDialogSeragam1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogSeragam1Layout.createSequentialGroup()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButtonSave2.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonSave2.text")); // NOI18N
        jButtonSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSave2ActionPerformed(evt);
            }
        });

        jButtonDialogCancel1.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonDialogCancel1.text")); // NOI18N
        jButtonDialogCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDialogCancel1ActionPerformed(evt);
            }
        });

        jLabel26.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel26.text")); // NOI18N

        jTextFieldTransactionSummaryNote2.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldTransactionSummaryNote2.text")); // NOI18N

        jLabel27.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel27.text")); // NOI18N

        jTextFieldTSumTotalAmount1.setEditable(false);
        jTextFieldTSumTotalAmount1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextFieldTSumTotalAmount1.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldTSumTotalAmount1.text")); // NOI18N
        jTextFieldTSumTotalAmount1.setFocusable(false);

        javax.swing.GroupLayout jPanelTransactionSummary2Layout = new javax.swing.GroupLayout(jPanelTransactionSummary2);
        jPanelTransactionSummary2.setLayout(jPanelTransactionSummary2Layout);
        jPanelTransactionSummary2Layout.setHorizontalGroup(
            jPanelTransactionSummary2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTransactionSummary2Layout.createSequentialGroup()
                .addGroup(jPanelTransactionSummary2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelTransactionSummary2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldTSumTotalAmount1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addComponent(jTextFieldTransactionSummaryNote2)))
        );
        jPanelTransactionSummary2Layout.setVerticalGroup(
            jPanelTransactionSummary2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTransactionSummary2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanelTransactionSummary2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jTextFieldTransactionSummaryNote2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelTransactionSummary2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTextFieldTSumTotalAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLableProfilTitle2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLableProfilTitle2.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLableProfilTitle2.text")); // NOI18N

        jLabelNama2.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabelNama2.text")); // NOI18N

        jLabelLevel2Level5.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabelLevel2Level5.text")); // NOI18N

        jLabelNoInduk2.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabelNoInduk2.text")); // NOI18N

        jLabelLevel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelLevel3.setText(profil.currentLevel.level1.toString());

        jTextFieldNama2.setEditable(false);
        jTextFieldNama2.setText(profil.biodata.nama);
        jTextFieldNama2.setFocusable(false);
        jTextFieldNama.setText(profil.biodata.nama);

        jTextFieldNomorInduk2.setEditable(false);
        jTextFieldNomorInduk2.setText(profil.noInduk);
        jTextFieldNomorInduk2.setFocusable(false);

        jTextFieldLevel2Level5.setEditable(false);
        jTextFieldLevel2Level5.setText(profil.currentLevel.level2.toString().concat(" "+profil.currentLevel.level3.toString()));
        jTextFieldLevel2Level5.setFocusable(false);

        javax.swing.GroupLayout jPanelDialogProfil1Layout = new javax.swing.GroupLayout(jPanelDialogProfil1);
        jPanelDialogProfil1.setLayout(jPanelDialogProfil1Layout);
        jPanelDialogProfil1Layout.setHorizontalGroup(
            jPanelDialogProfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogProfil1Layout.createSequentialGroup()
                .addGroup(jPanelDialogProfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDialogProfil1Layout.createSequentialGroup()
                        .addComponent(jLableProfilTitle2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLevel3))
                    .addGroup(jPanelDialogProfil1Layout.createSequentialGroup()
                        .addGroup(jPanelDialogProfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNama2)
                            .addComponent(jLabelNoInduk2)
                            .addComponent(jLabelLevel2Level5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelDialogProfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldNomorInduk2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNama2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldLevel2Level5, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDialogProfil1Layout.setVerticalGroup(
            jPanelDialogProfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogProfil1Layout.createSequentialGroup()
                .addGroup(jPanelDialogProfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLableProfilTitle2)
                    .addComponent(jLabelLevel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDialogProfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNama2)
                    .addComponent(jTextFieldNama2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanelDialogProfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNoInduk2)
                    .addComponent(jTextFieldNomorInduk2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDialogProfil1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLevel2Level5)
                    .addComponent(jTextFieldLevel2Level5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel28.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel28.text")); // NOI18N

        jTableIPP2.setModel(tableModelIPP);
        jScrollPane7.setViewportView(jTableIPP2);

        jLabelTahun2.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabelTahun2.text")); // NOI18N

        try{
            tahunComboBoxModel = buildIPPtahunComboBoxModel(profil);
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(KasirException ex){
            ex.printStackTrace();
        }
        jComboBoxTahun2.setModel(tahunComboBoxModel);
        jComboBoxTahun2.setEnabled(false);
        jComboBoxTahun2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTahun2ActionPerformed(evt);
            }
        });
        jComboBoxTahun2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBoxTahun2PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabelTahun2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxTahun2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTahun2)
                    .addComponent(jComboBoxTahun2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );

        javax.swing.GroupLayout jPanelDialogIPP1Layout = new javax.swing.GroupLayout(jPanelDialogIPP1);
        jPanelDialogIPP1.setLayout(jPanelDialogIPP1Layout);
        jPanelDialogIPP1Layout.setHorizontalGroup(
            jPanelDialogIPP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogIPP1Layout.createSequentialGroup()
                .addComponent(jLabel28)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelDialogIPP1Layout.setVerticalGroup(
            jPanelDialogIPP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogIPP1Layout.createSequentialGroup()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelDialogMain1Layout = new javax.swing.GroupLayout(jPanelDialogMain1);
        jPanelDialogMain1.setLayout(jPanelDialogMain1Layout);
        jPanelDialogMain1Layout.setHorizontalGroup(
            jPanelDialogMain1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogMain1Layout.createSequentialGroup()
                .addGroup(jPanelDialogMain1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDialogMain1Layout.createSequentialGroup()
                        .addComponent(jPanelDialogProfil1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelTransactionSummary2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDialogMain1Layout.createSequentialGroup()
                        .addComponent(jButtonSave2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDialogCancel1))
                    .addGroup(jPanelDialogMain1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanelDialogSeragam1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelDialogIPP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelDialogIPSP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelDialogMain1Layout.setVerticalGroup(
            jPanelDialogMain1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogMain1Layout.createSequentialGroup()
                .addGroup(jPanelDialogMain1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelTransactionSummary2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelDialogProfil1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDialogIPP1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDialogIPSP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelDialogSeragam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDialogMain1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave2)
                    .addComponent(jButtonDialogCancel1))
                .addContainerGap(193, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogTransactionSummaryLayout = new javax.swing.GroupLayout(jDialogTransactionSummary.getContentPane());
        jDialogTransactionSummary.getContentPane().setLayout(jDialogTransactionSummaryLayout);
        jDialogTransactionSummaryLayout.setHorizontalGroup(
            jDialogTransactionSummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelDialogMain, javax.swing.GroupLayout.PREFERRED_SIZE, 843, Short.MAX_VALUE)
            .addComponent(jPanelDialogMain1, javax.swing.GroupLayout.PREFERRED_SIZE, 843, Short.MAX_VALUE)
        );
        jDialogTransactionSummaryLayout.setVerticalGroup(
            jDialogTransactionSummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogTransactionSummaryLayout.createSequentialGroup()
                .addComponent(jPanelDialogMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDialogMain1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jFrameIPP.setMinimumSize(new java.awt.Dimension(680, 450));

        jPanelIPP.setMinimumSize(new java.awt.Dimension(570, 380));
        jPanelIPP.setPreferredSize(new java.awt.Dimension(680, 450));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel20.text")); // NOI18N

        jLabelTahun.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabelTahun.text")); // NOI18N

        try{
            tahunComboBoxModel = buildIPPtahunComboBoxModel(profil);
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(KasirException ex){
            ex.printStackTrace();
        }
        jComboBoxTahun.setModel(tahunComboBoxModel);
        jComboBoxTahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTahunActionPerformed(evt);
            }
        });
        jComboBoxTahun.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBoxTahunPropertyChange(evt);
            }
        });

        jLabel21.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel21.text")); // NOI18N

        jUnpaidIPP.setText(String.valueOf(unpaidIPP));
        jUnpaidIPP.setEnabled(false);

        jTableIPP.setModel(tableModelIPP);
        jTableIPP.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableIPP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTableIPPFocusLost(evt);
            }
        });
        jTableIPP.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableIPPPropertyChange(evt);
            }
        });
        jScrollPane5.setViewportView(jTableIPP);

        jButtonBayarIPP.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonBayarIPP.text")); // NOI18N
        jButtonBayarIPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBayarIPPActionPerformed(evt);
            }
        });

        jLabel2.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel2.text")); // NOI18N

        jFormattedTextFieldIDDSaldo.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jFormattedTextFieldIDDSaldo.text")); // NOI18N
        jFormattedTextFieldIDDSaldo.setEnabled(false);

        javax.swing.GroupLayout jPanelIPPLayout = new javax.swing.GroupLayout(jPanelIPP);
        jPanelIPP.setLayout(jPanelIPPLayout);
        jPanelIPPLayout.setHorizontalGroup(
            jPanelIPPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIPPLayout.createSequentialGroup()
                .addGroup(jPanelIPPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelIPPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5))
                    .addGroup(jPanelIPPLayout.createSequentialGroup()
                        .addGroup(jPanelIPPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelIPPLayout.createSequentialGroup()
                                .addGap(248, 248, 248)
                                .addComponent(jButtonBayarIPP))
                            .addGroup(jPanelIPPLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(39, 39, 39)
                                .addComponent(jLabelTahun)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addGroup(jPanelIPPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelIPPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jUnpaidIPP, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextFieldIDDSaldo))))
                        .addGap(0, 74, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelIPPLayout.setVerticalGroup(
            jPanelIPPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIPPLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanelIPPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTahun)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jUnpaidIPP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelIPPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jFormattedTextFieldIDDSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBayarIPP)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrameIPPLayout = new javax.swing.GroupLayout(jFrameIPP.getContentPane());
        jFrameIPP.getContentPane().setLayout(jFrameIPPLayout);
        jFrameIPPLayout.setHorizontalGroup(
            jFrameIPPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelIPP, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
        );
        jFrameIPPLayout.setVerticalGroup(
            jFrameIPPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelIPP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );

        jFrameIDD.setMinimumSize(new java.awt.Dimension(480, 390));
        jFrameIDD.setName(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jFrameIDD.name")); // NOI18N

        jPanelIDD.setMinimumSize(new java.awt.Dimension(480, 390));
        jPanelIDD.setPreferredSize(new java.awt.Dimension(1024, 176));

        jLabelIDDTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelIDDTitle.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabelIDDTitle.text")); // NOI18N

        jLabelAmount3.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabelAmount3.text")); // NOI18N

        jTextFieldIDDAmount.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldIDDAmount.text")); // NOI18N
        jTextFieldIDDAmount.setName(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldIDDAmount.name")); // NOI18N
        jTextFieldIDDAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIDDAmountActionPerformed(evt);
            }
        });
        jTextFieldIDDAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldIDDAmountKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldIDDAmountKeyTyped(evt);
            }
        });

        jLabelNote2.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabelNote2.text")); // NOI18N

        jTextAreaIDDNote.setColumns(20);
        jTextAreaIDDNote.setRows(5);
        jScrollPane8.setViewportView(jTextAreaIDDNote);

        jLabelAmount4.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabelAmount4.text")); // NOI18N

        jTextFieldIDDTransactionName.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldIDDTransactionName.text")); // NOI18N
        jTextFieldIDDTransactionName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIDDTransactionNameActionPerformed(evt);
            }
        });
        jTextFieldIDDTransactionName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldIDDTransactionNameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldIDDTransactionNameKeyTyped(evt);
            }
        });

        jButtonIDDOK.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonIDDOK.text")); // NOI18N
        jButtonIDDOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIDDOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelIDDLayout = new javax.swing.GroupLayout(jPanelIDD);
        jPanelIDD.setLayout(jPanelIDDLayout);
        jPanelIDDLayout.setHorizontalGroup(
            jPanelIDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIDDLayout.createSequentialGroup()
                .addGroup(jPanelIDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelIDDTitle)
                    .addGroup(jPanelIDDLayout.createSequentialGroup()
                        .addGroup(jPanelIDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAmount3)
                            .addComponent(jLabelNote2)
                            .addComponent(jLabelAmount4))
                        .addGap(29, 29, 29)
                        .addGroup(jPanelIDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldIDDAmount, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(jTextFieldIDDTransactionName))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanelIDDLayout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addComponent(jButtonIDDOK)
                .addContainerGap())
        );
        jPanelIDDLayout.setVerticalGroup(
            jPanelIDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIDDLayout.createSequentialGroup()
                .addComponent(jLabelIDDTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelIDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAmount4)
                    .addComponent(jTextFieldIDDTransactionName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelIDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAmount3)
                    .addComponent(jTextFieldIDDAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelIDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNote2)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonIDDOK)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrameIDDLayout = new javax.swing.GroupLayout(jFrameIDD.getContentPane());
        jFrameIDD.getContentPane().setLayout(jFrameIDDLayout);
        jFrameIDDLayout.setHorizontalGroup(
            jFrameIDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 407, Short.MAX_VALUE)
            .addGroup(jFrameIDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jFrameIDDLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelIDD, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jFrameIDDLayout.setVerticalGroup(
            jFrameIDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 265, Short.MAX_VALUE)
            .addGroup(jFrameIDDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jFrameIDDLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelIDD, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(35, Short.MAX_VALUE)))
        );

        jFrameIPSP.setMinimumSize(new java.awt.Dimension(440, 480));

        jPanelIPSP.setMinimumSize(new java.awt.Dimension(440, 480));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel1.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel3.text")); // NOI18N

        jTextFieldIPSPAmount.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldIPSPAmount.text")); // NOI18N
        jTextFieldIPSPAmount.setName(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldIPSPAmount.name")); // NOI18N
        jTextFieldIPSPAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIPSPAmountActionPerformed(evt);
            }
        });
        jTextFieldIPSPAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldIPSPAmountFocusLost(evt);
            }
        });
        jTextFieldIPSPAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldIPSPAmountKeyTyped(evt);
            }
        });

        jLabel4.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel4.text")); // NOI18N

        jComboBoxIPSP.setModel(paymentMethodComboBoxModel);
        jComboBoxIPSP.setEnabled(false);
        jComboBoxIPSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxIPSPActionPerformed(evt);
            }
        });

        jLabel5.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel5.text")); // NOI18N

        jTextFieldIPSPNote.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldIPSPNote.text")); // NOI18N

        jLabel13.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel13.text")); // NOI18N

        jTextFieldIPSPIuranAmount.setEditable(false);
        jTextFieldIPSPIuranAmount.setText(String.valueOf(ipsp.amount));
        jTextFieldIPSPIuranAmount.setFocusable(false);
        jTextFieldIPSPIuranAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIPSPIuranAmountActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel17.text")); // NOI18N

        jTextFieldIPSPSisaAmount.setEditable(false);
        jTextFieldIPSPSisaAmount.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextFieldIPSPSisaAmount.setText(String.valueOf(maxIpsp));
        jTextFieldIPSPSisaAmount.setFocusable(false);
        jTextFieldIPSPSisaAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIPSPSisaAmountActionPerformed(evt);
            }
        });

        jButtonIPSPOK.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonIPSPOK.text")); // NOI18N
        jButtonIPSPOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIPSPOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelIPSPLayout = new javax.swing.GroupLayout(jPanelIPSP);
        jPanelIPSP.setLayout(jPanelIPSPLayout);
        jPanelIPSPLayout.setHorizontalGroup(
            jPanelIPSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
            .addGroup(jPanelIPSPLayout.createSequentialGroup()
                .addGroup(jPanelIPSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelIPSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelIPSPLayout.createSequentialGroup()
                        .addGroup(jPanelIPSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBoxIPSP, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldIPSPAmount, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldIPSPIuranAmount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addGap(12, 12, 12)
                        .addComponent(jTextFieldIPSPSisaAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldIPSPNote, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanelIPSPLayout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(jButtonIPSPOK))
        );
        jPanelIPSPLayout.setVerticalGroup(
            jPanelIPSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIPSPLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addGroup(jPanelIPSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextFieldIPSPIuranAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jTextFieldIPSPSisaAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelIPSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldIPSPAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelIPSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxIPSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelIPSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldIPSPNote, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonIPSPOK)
                .addContainerGap(270, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrameIPSPLayout = new javax.swing.GroupLayout(jFrameIPSP.getContentPane());
        jFrameIPSP.getContentPane().setLayout(jFrameIPSPLayout);
        jFrameIPSPLayout.setHorizontalGroup(
            jFrameIPSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
            .addGroup(jFrameIPSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrameIPSPLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelIPSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jFrameIPSPLayout.setVerticalGroup(
            jFrameIPSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
            .addGroup(jFrameIPSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrameIPSPLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelIPSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jFrameSeragam.setMinimumSize(new java.awt.Dimension(640, 480));

        jPanelSeragam.setMinimumSize(new java.awt.Dimension(440, 550));
        jPanelSeragam.setPreferredSize(new java.awt.Dimension(480, 302));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel10.text")); // NOI18N

        try{
            tableModelSeragam = buildSeragamTableModel(profil);
        }catch(Exception e){
            e.printStackTrace();
        }
        jTableSeragam.setModel(tableModelSeragam);
        jTableSeragam.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableSeragamPropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(jTableSeragam);

        jLabel19.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel19.text")); // NOI18N

        jUnpaidSeragam.setText(String.valueOf(unpaidSeragam));
        jUnpaidSeragam.setEnabled(false);

        jButtonBayarSeragam.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonBayarSeragam.text")); // NOI18N
        jButtonBayarSeragam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBayarSeragamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSeragamLayout = new javax.swing.GroupLayout(jPanelSeragam);
        jPanelSeragam.setLayout(jPanelSeragamLayout);
        jPanelSeragamLayout.setHorizontalGroup(
            jPanelSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSeragamLayout.createSequentialGroup()
                .addGroup(jPanelSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSeragamLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(104, 104, 104)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jUnpaidSeragam, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelSeragamLayout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(jButtonBayarSeragam)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanelSeragamLayout.setVerticalGroup(
            jPanelSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSeragamLayout.createSequentialGroup()
                .addGroup(jPanelSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel19)
                    .addComponent(jUnpaidSeragam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBayarSeragam)
                .addContainerGap(230, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrameSeragamLayout = new javax.swing.GroupLayout(jFrameSeragam.getContentPane());
        jFrameSeragam.getContentPane().setLayout(jFrameSeragamLayout);
        jFrameSeragamLayout.setHorizontalGroup(
            jFrameSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrameSeragamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelSeragam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jFrameSeragamLayout.setVerticalGroup(
            jFrameSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrameSeragamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelSeragam, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        jFrameBuku.setMinimumSize(new java.awt.Dimension(640, 480));
        jFrameBuku.setPreferredSize(new java.awt.Dimension(522, 480));

        jPaneBuku.setMinimumSize(new java.awt.Dimension(440, 550));
        jPaneBuku.setPreferredSize(new java.awt.Dimension(480, 302));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel29.text")); // NOI18N

        try{
            tableModelBuku = buildBukuTableModel(profil);
        }catch(Exception e){
            e.printStackTrace();
        }
        jTableBuku.setModel(tableModelBuku);
        jTableBuku.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableBukuPropertyChange(evt);
            }
        });
        jScrollPane9.setViewportView(jTableBuku);

        jLabel30.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel30.text")); // NOI18N

        jUnpaidBuku.setText(String.valueOf(unpaidBuku));
        jUnpaidBuku.setEnabled(false);

        jButtonBayarBuku.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonBayarBuku.text")); // NOI18N
        jButtonBayarBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBayarBukuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPaneBukuLayout = new javax.swing.GroupLayout(jPaneBuku);
        jPaneBuku.setLayout(jPaneBukuLayout);
        jPaneBukuLayout.setHorizontalGroup(
            jPaneBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneBukuLayout.createSequentialGroup()
                .addGroup(jPaneBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPaneBukuLayout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(104, 104, 104)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jUnpaidBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPaneBukuLayout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(jButtonBayarBuku)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPaneBukuLayout.setVerticalGroup(
            jPaneBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneBukuLayout.createSequentialGroup()
                .addGroup(jPaneBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jUnpaidBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBayarBuku)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrameBukuLayout = new javax.swing.GroupLayout(jFrameBuku.getContentPane());
        jFrameBuku.getContentPane().setLayout(jFrameBukuLayout);
        jFrameBukuLayout.setHorizontalGroup(
            jFrameBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrameBukuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPaneBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jFrameBukuLayout.setVerticalGroup(
            jFrameBukuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrameBukuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPaneBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        jFrameIKS.setMinimumSize(new java.awt.Dimension(680, 450));

        jPanelIKS.setMinimumSize(new java.awt.Dimension(570, 380));
        jPanelIKS.setPreferredSize(new java.awt.Dimension(680, 450));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel32.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel32.text")); // NOI18N

        jLabelTahun3.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabelTahun3.text")); // NOI18N

        try{
            tahunComboBoxModel = buildIKStahunComboBoxModel(profil);
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(KasirException ex){
            ex.printStackTrace();
        }
        jComboBoxTahun3.setModel(tahunComboBoxModel);
        jComboBoxTahun3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTahun3ActionPerformed(evt);
            }
        });
        jComboBoxTahun3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBoxTahun3PropertyChange(evt);
            }
        });

        jLabel33.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel33.text")); // NOI18N

        jUnpaidIKS.setText(String.valueOf(unpaidIPP));
        jUnpaidIKS.setEnabled(false);

        jTableIKS.setModel(tableModelIKS);
        jTableIKS.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableIKS.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTableIKSFocusLost(evt);
            }
        });
        jTableIKS.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableIKSPropertyChange(evt);
            }
        });
        jScrollPane11.setViewportView(jTableIKS);

        jButtonBayarIKS.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonBayarIKS.text")); // NOI18N
        jButtonBayarIKS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBayarIKSActionPerformed(evt);
            }
        });

        jLabel34.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel34.text")); // NOI18N

        jFormattedTextFieldIDDSaldo1.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jFormattedTextFieldIDDSaldo1.text")); // NOI18N
        jFormattedTextFieldIDDSaldo1.setEnabled(false);

        javax.swing.GroupLayout jPanelIKSLayout = new javax.swing.GroupLayout(jPanelIKS);
        jPanelIKS.setLayout(jPanelIKSLayout);
        jPanelIKSLayout.setHorizontalGroup(
            jPanelIKSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIKSLayout.createSequentialGroup()
                .addGroup(jPanelIKSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelIKSLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane11))
                    .addGroup(jPanelIKSLayout.createSequentialGroup()
                        .addGroup(jPanelIKSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelIKSLayout.createSequentialGroup()
                                .addGap(248, 248, 248)
                                .addComponent(jButtonBayarIKS))
                            .addGroup(jPanelIKSLayout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addGap(39, 39, 39)
                                .addComponent(jLabelTahun3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxTahun3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addGroup(jPanelIKSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel34))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelIKSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jUnpaidIKS, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextFieldIDDSaldo1))))
                        .addGap(0, 74, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelIKSLayout.setVerticalGroup(
            jPanelIKSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIKSLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanelIKSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTahun3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTahun3)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(jUnpaidIKS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelIKSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jFormattedTextFieldIDDSaldo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBayarIKS)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrameIKSLayout = new javax.swing.GroupLayout(jFrameIKS.getContentPane());
        jFrameIKS.getContentPane().setLayout(jFrameIKSLayout);
        jFrameIKSLayout.setHorizontalGroup(
            jFrameIKSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelIKS, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
        );
        jFrameIKSLayout.setVerticalGroup(
            jFrameIKSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelIKS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 768));

        jPanelMain.setMinimumSize(new java.awt.Dimension(1024, 768));
        jPanelMain.setPreferredSize(new java.awt.Dimension(1024, 768));

        jLableProfilTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLableProfilTitle.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLableProfilTitle.text")); // NOI18N

        jLabelNama.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabelNama.text")); // NOI18N

        jLabelLevel2Level3.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabelLevel2Level3.text")); // NOI18N

        jLabelNoInduk.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabelNoInduk.text")); // NOI18N

        jLabelLevel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelLevel1.setText(profil.currentLevel.level1.toString());

        jTextFieldNama.setText(profil.biodata.nama);
        jTextFieldNama.setFocusable(false);
        jTextFieldNama.setText(profil.biodata.nama);
        jTextFieldNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNamaActionPerformed(evt);
            }
        });

        jTextFieldNomorInduk.setText(profil.noInduk);
        jTextFieldNomorInduk.setFocusable(false);

        jTextFieldLevel2Level3.setText(profil.currentLevel.level2.toString().concat(" - "+profil.currentLevel.level3.toString()));
        jTextFieldLevel2Level3.setFocusable(false);

        jLabel14.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jLabel14.text")); // NOI18N

        jTextFieldTransactionSummaryNote.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldTransactionSummaryNote.text")); // NOI18N

        javax.swing.GroupLayout jPanelProfilLayout = new javax.swing.GroupLayout(jPanelProfil);
        jPanelProfil.setLayout(jPanelProfilLayout);
        jPanelProfilLayout.setHorizontalGroup(
            jPanelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProfilLayout.createSequentialGroup()
                .addGroup(jPanelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelProfilLayout.createSequentialGroup()
                        .addComponent(jLableProfilTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLevel1))
                    .addGroup(jPanelProfilLayout.createSequentialGroup()
                        .addGroup(jPanelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNama)
                            .addComponent(jLabelNoInduk)
                            .addComponent(jLabelLevel2Level3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldNomorInduk, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNama, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldLevel2Level3, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelProfilLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldTransactionSummaryNote)))
                .addContainerGap(170, Short.MAX_VALUE))
        );
        jPanelProfilLayout.setVerticalGroup(
            jPanelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProfilLayout.createSequentialGroup()
                .addGroup(jPanelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLableProfilTitle)
                    .addComponent(jLabelLevel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNama)
                    .addComponent(jTextFieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNoInduk)
                    .addComponent(jTextFieldNomorInduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLevel2Level3)
                    .addComponent(jTextFieldLevel2Level3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextFieldTransactionSummaryNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jButtonSubmit.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonSubmit.text")); // NOI18N
        jButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubmitActionPerformed(evt);
            }
        });

        jButtonIPP.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonIPP.text")); // NOI18N
        jButtonIPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIPPActionPerformed(evt);
            }
        });

        jButtonIPSP.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonIPSP.text")); // NOI18N
        jButtonIPSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIPSPActionPerformed(evt);
            }
        });

        jButtonSeragam.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonSeragam.text")); // NOI18N
        jButtonSeragam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeragamActionPerformed(evt);
            }
        });

        jButtonIDD.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonIDD.text")); // NOI18N
        jButtonIDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIDDActionPerformed(evt);
            }
        });

        jTextFieldIPPAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldIPPAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldIPPAmountSimple.text")); // NOI18N

        jTextFieldIPSPAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldIPSPAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldIPSPAmountSimple.text")); // NOI18N

        jTextFieldIDDAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldIDDAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldIDDAmountSimple.text")); // NOI18N

        jTextFieldSeragamAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldSeragamAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldSeragamAmountSimple.text")); // NOI18N

        jButtonReportIPP.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonReportIPP.text")); // NOI18N
        jButtonReportIPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReportIPPActionPerformed(evt);
            }
        });

        jButtonBuku.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonBuku.text")); // NOI18N
        jButtonBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBukuActionPerformed(evt);
            }
        });

        jTextFieldBukuAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldBukuAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldBukuAmountSimple.text")); // NOI18N

        jButtonIKS.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonIKS.text")); // NOI18N
        jButtonIKS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIKSActionPerformed(evt);
            }
        });

        jTextFieldIKSAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldIKSAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldIKSAmountSimple.text")); // NOI18N

        jButtonILL.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonILL.text")); // NOI18N
        jButtonILL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonILLActionPerformed(evt);
            }
        });

        jTextFieldILLAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldILLAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldILLAmountSimple.text")); // NOI18N

        jButtonIPSB.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonIPSB.text")); // NOI18N
        jButtonIPSB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIPSBActionPerformed(evt);
            }
        });

        jTextFieldIPSBAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldIPSBAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldIPSBAmountSimple.text")); // NOI18N

        jButtonIUA.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonIUA.text")); // NOI18N
        jButtonIUA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIUAActionPerformed(evt);
            }
        });

        jTextFieldIUAAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldIUAAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldIUAAmountSimple.text")); // NOI18N

        jButtonIUS.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonIUS.text")); // NOI18N
        jButtonIUS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIUSActionPerformed(evt);
            }
        });

        jTextFieldIUSAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldIUSAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldIUSAmountSimple.text")); // NOI18N

        jButtonOSIS.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonOSIS.text")); // NOI18N
        jButtonOSIS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOSISActionPerformed(evt);
            }
        });

        jTextFieldOSISAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldOSISAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldOSISAmountSimple.text")); // NOI18N

        jButtonPASB.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonPASB.text")); // NOI18N
        jButtonPASB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPASBActionPerformed(evt);
            }
        });

        jTextFieldPASBAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldPASBAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldPASBAmountSimple.text")); // NOI18N

        jButtonPVT.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonPVT.text")); // NOI18N
        jButtonPVT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPVTActionPerformed(evt);
            }
        });

        jTextFieldPVTAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldPVTAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldPVTAmountSimple.text")); // NOI18N

        jButtonTabungan.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonTabungan.text")); // NOI18N
        jButtonTabungan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTabunganActionPerformed(evt);
            }
        });

        jTextFieldTabunganAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldTabunganAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldTabunganAmountSimple.text")); // NOI18N

        jButtonSumbangan.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jButtonSumbangan.text")); // NOI18N
        jButtonSumbangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSumbanganActionPerformed(evt);
            }
        });

        jTextFieldSumbanganAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldSumbanganAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSimple.class, "InputTransactionFrameSimple.jTextFieldSumbanganAmountSimple.text")); // NOI18N

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonIPSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonSeragam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonIPP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonIDD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonBuku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonIKS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonILL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonIPSB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonIUA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonIUS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonOSIS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonPASB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonPVT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonTabungan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonSumbangan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelMainLayout.createSequentialGroup()
                                .addComponent(jTextFieldIPPAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonReportIPP))
                            .addComponent(jTextFieldIPSPAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSeragamAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldIDDAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldBukuAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldIKSAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldILLAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldIPSBAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldIUAAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldIUSAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldOSISAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldPASBAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldPVTAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTabunganAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSumbanganAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                .addComponent(jPanelProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonIPP)
                    .addComponent(jTextFieldIPPAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonReportIPP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonIPSP)
                    .addComponent(jTextFieldIPSPAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSeragam)
                    .addComponent(jTextFieldSeragamAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonIDD)
                    .addComponent(jTextFieldIDDAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBuku)
                    .addComponent(jTextFieldBukuAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonIKS)
                    .addComponent(jTextFieldIKSAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonILL)
                    .addComponent(jTextFieldILLAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonIPSB)
                    .addComponent(jTextFieldIPSBAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonIUA)
                    .addComponent(jTextFieldIUAAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonIUS)
                    .addComponent(jTextFieldIUSAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonOSIS)
                    .addComponent(jTextFieldOSISAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPASB)
                    .addComponent(jTextFieldPASBAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPVT)
                    .addComponent(jTextFieldPVTAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonTabungan)
                    .addComponent(jTextFieldTabunganAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSumbangan)
                    .addComponent(jTextFieldSumbanganAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jButtonSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, 633, Short.MAX_VALUE)
                .addContainerGap())
        );

        setContentPane(validationPanel);
        validationPanel.setInnerComponent(jPanelMain);
        Validator<String> d = StringValidators.trimString(ValidatorUtils.merge(
            StringValidators.REQUIRE_VALID_NUMBER,
            StringValidators.REQUIRE_NON_NEGATIVE_NUMBER,
            StringValidators.numberRange(minIpsp, maxIpsp)));
    validationPanel.getValidationGroup().add(jTextFieldIPSPAmount, d);
    jButtonSubmit.setEnabled(false);

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNamaActionPerformed

    private void jComboBoxDialogIPSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDialogIPSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxDialogIPSPActionPerformed

    private void jButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubmitActionPerformed
        try {
            // TODO add your handling code here:
            prepareSubmitObjects();
            continueToConfirmationDialog();
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        } catch (KasirException ex) {
            Exceptions.printStackTrace(ex);
        }
    }//GEN-LAST:event_jButtonSubmitActionPerformed

    private void jButtonSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSave1ActionPerformed
        try {
            // TODO add your handling code here:
            saveToDB();
            if (JOptionPane.showConfirmDialog(null, "Print Bukti Pembayaran?", "WARNING",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                        // yes option
                        printBuktiPembayaran(totalAmount);
                } catch (JRException ex) {
                    Exceptions.printStackTrace(ex);
                    JOptionPane.showMessageDialog(rootPane, "Print Bukti Pembayaran Gagal!".concat("\r\n"+ex.toString()));
                } catch (PrinterException ex) {
                        Exceptions.printStackTrace(ex);
                        JOptionPane.showMessageDialog(rootPane, "Print Bukti Pembayaran Gagal!".concat("\r\n"+ex.toString()));
                }
            } else {
                // no option
                JOptionPane.showMessageDialog(rootPane, "Simpan Data Ke Database Berhasil!");
            }
            this.jDialogTransactionSummary.dispose();
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            JOptionPane.showMessageDialog(rootPane, "Input Transaksi Gagal!".concat("\r\n"+ex.toString()));
        } catch (KasirException ex) {
            Exceptions.printStackTrace(ex);
            JOptionPane.showMessageDialog(rootPane, "Input Transaksi Gagal!".concat("\r\n"+ex.toString()));
        }
    }//GEN-LAST:event_jButtonSave1ActionPerformed

    private void jTableSeragamPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableSeragamPropertyChange
        // TODO add your handling code here:
        seragamSToDB = new ArrayList<>();
        if(jTableSeragam.getRowCount() > 0){
            for(int i = 0; i < jTableSeragam.getRowCount(); i++){
//                if((Boolean)jTableSeragam.getValueAt(i,3) && (seragamS.get(i).transactDetailIDs.size() == 0)){
                    
                    seragamSToDB.add(seragamS.get(i));
                    //                    try {
                        //seragam = Control.selectIuran(Iuran.Tipe.Seragam, Seragam.noIndukColName, false , profil.noInduk);
                        //seragamS.remove(i);
//                    } catch (SQLException ex) {
//                        Exceptions.printStackTrace(ex);
//                    } catch (KasirException ex) {
//                        Exceptions.printStackTrace(ex);
//                    }
//                }
            }
        }
    }//GEN-LAST:event_jTableSeragamPropertyChange

    private void jComboBoxIPSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxIPSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxIPSPActionPerformed

    private void jComboBoxTahun1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTahun1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTahun1ActionPerformed

    private void jComboBoxTahun1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBoxTahun1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTahun1PropertyChange

    private void jTableDialogSeragamPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableDialogSeragamPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableDialogSeragamPropertyChange

    private void jButtonDialogCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDialogCancelActionPerformed
        // TODO add your handling code here:
        jTableIPP.setModel(tableModelIPP);
        this.jDialogTransactionSummary.setVisible(false);
        totalAmount = 0;
        this.setVisible(true);
    }//GEN-LAST:event_jButtonDialogCancelActionPerformed

    private void jTextFieldIPSPAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIPSPAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIPSPAmountActionPerformed

    private void jTextFieldIPSPIuranAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIPSPIuranAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIPSPIuranAmountActionPerformed

    private void jTextFieldIPSPAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldIPSPAmountKeyTyped
        // TODO add your handling code here:
        jButtonSubmit.setEnabled(validatePanel());
    }//GEN-LAST:event_jTextFieldIPSPAmountKeyTyped

    private void jTextFieldIPSPAmountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIPSPAmountFocusLost
        // TODO add your handling code here:
        jButtonSubmit.setEnabled(validatePanel());
    }//GEN-LAST:event_jTextFieldIPSPAmountFocusLost

    private void jTextFieldIPSPSisaAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIPSPSisaAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIPSPSisaAmountActionPerformed

    private void jButtonIPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIPPActionPerformed
        // TODO add your handling code here:
        try{
            tableModelIPP = buildIPPTableModel(profil, profil.currentLevel.tahun);
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(KasirException ex){
            ex.printStackTrace();
        }
        jFrameIPP.setVisible(true);
    }//GEN-LAST:event_jButtonIPPActionPerformed

    private void jButtonSeragamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeragamActionPerformed
        // TODO add your handling code here:
        try{
            tableModelSeragam = buildSeragamTableModel(profil);
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(KasirException ex){
            ex.printStackTrace();
        }
        jFrameSeragam.setVisible(true);
    }//GEN-LAST:event_jButtonSeragamActionPerformed

    private void jButtonIPSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIPSPActionPerformed
        // TODO add your handling code here:
        jFrameIPSP.setVisible(true);
    }//GEN-LAST:event_jButtonIPSPActionPerformed

    private void jComboBoxTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTahunActionPerformed
        // TODO add your handling code here:
        try{
            this.tableModelIPP = (DefaultTableModel) buildIPPTableModel(this.profil, this.tahunIPP.get(jComboBoxTahun.getSelectedIndex()));
            jTableIPP.setModel(this.tableModelIPP);
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(KasirException ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jComboBoxTahunActionPerformed

    private void jComboBoxTahunPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBoxTahunPropertyChange
        // TODO add your handling code here:
        try{
            if(this.jComboBoxTahun.getSelectedItem() !=null){
                this.tableModelIPP = (DefaultTableModel) buildIPPTableModel(this.profil, this.tahunIPP.get(jComboBoxTahun.getSelectedIndex()));
                jTableIPP.setModel(this.tableModelIPP);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Koneksi Error!\r\n".concat(ex.toString()));
        }catch(KasirException ex){
            JOptionPane.showMessageDialog(rootPane, "IPP Belum Di Setting!\r\n".concat(ex.toString()));
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jComboBoxTahunPropertyChange

    private void jTableIPPFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableIPPFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableIPPFocusLost

    private void jTableIPPPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableIPPPropertyChange
        // TODO add your handling code here:
        //IDD, Beasiswa, Beasiswa Cost
        iDDAmounts = new ArrayList();
        beasiswaAmounts = new ArrayList();
        beasiswaCostAmounts = new ArrayList();
        /////BLOOOOOOOOOOM SELESAIIIIIIIIIIIIIIIIIIII
        int i = 0;
        ippCurrent=new IPP();
        ippStoreToDB= new IPP();
        ArrayList<Entry> entry = new ArrayList<>();
        if(ippFromDB.entries.size() !=0){
            for(i=0; i< jTableIPP.getRowCount();i++){
                ippCurrent.entries.add((Boolean)jTableIPP.getValueAt(i,2)? ((ippFromDB.entries.get(i).transactDetailIDs.size() > 0)? new Entry(i, ippFromDB.entries.get(i).amount, ippFromDB.entries.get(i).debt, ippFromDB.entries.get(i).transactDetailIDs): new Entry(i, ippFromDB.entries.get(i).amount)): null);
                //belon condition is when jTableIPP checkBox is UNCHECK ????
                if(ippCurrent.entries.get(i)==null){
                    ippStoreToDB.entries.add(null);
                    jTableIPP.setValueAt(0f, i,3);
                    jTableIPP.setValueAt(0f, i,4);
                    jTableIPP.setValueAt(0f, i,5);
                    jTableIPP.setValueAt(0f, i,6);
                    iDDAmounts.add(0f);
                    beasiswaAmounts.add(0f);
                    beasiswaCostAmounts.add(0f);
                //below is when jTableIPP property changed, especially check box is Changed or Editing IDD, Beasiswa, Beasiswa Cost 
                }else if ((ippCurrent.entries.get(i) !=null) ^ (ippFromDB.entries.get(i).transactDetailIDs.size() > 0)){
                    System.out.println("IDD dari db ");
                    if(idd != null){
                        System.out.println(idd.amount);
                    }
                    iDDAmounts.add((Float)jTableIPP.getValueAt(i,4));
                    beasiswaAmounts.add((Float)jTableIPP.getValueAt(i,5));
                    beasiswaCostAmounts.add((Float)jTableIPP.getValueAt(i,6));
                    jTableIPP.setValueAt(ippFromDB.entries.get(i).amount - (iDDAmounts.get(i) + beasiswaAmounts.get(i)+beasiswaCostAmounts.get(i)), i,3);
                    ippStoreToDB.entries.add(new Entry(i, ippFromDB.entries.get(i).amount));
                    
                }else{
                    ippStoreToDB.entries.add(null);
                    iDDAmounts.add(0f);
                    beasiswaAmounts.add(0f);
                    beasiswaCostAmounts.add(0f);
                    
                }
                //ippCurrent.entries.add((Boolean)jTableIPP.getValueAt(i,2)? new IPP(profil.noInduk, new Level(null,null,null,(Integer)jComboBoxTahun.getSelectedItem()),entry).entries.get(i): null);
                //ippStoreToDB.entries.add(ippCurrent.entries.get(i)==null?null:(((ippCurrent.entries.get(i)!=null)^(ippFromDB.entries.get(i)!=null))? new IPP(profil.noInduk, new Level(null,null,null,(int)jComboBoxTahun.getSelectedItem()),entry).entries.get(i):ippCurrent.entries.get(i)));
                //System.out.println("IPP Period: "+ippCurrent.entries.get(i).period);
            }
        }
    }//GEN-LAST:event_jTableIPPPropertyChange

    private void jComboBoxDialogIPSP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDialogIPSP1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxDialogIPSP1ActionPerformed

    private void jTableDialogSeragam1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableDialogSeragam1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableDialogSeragam1PropertyChange

    private void jButtonSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSave2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSave2ActionPerformed

    private void jButtonDialogCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDialogCancel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonDialogCancel1ActionPerformed

    private void jComboBoxTahun2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTahun2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTahun2ActionPerformed

    private void jComboBoxTahun2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBoxTahun2PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTahun2PropertyChange

    private void jTextFieldIDDAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIDDAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIDDAmountActionPerformed

    private void jTextFieldIDDAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldIDDAmountKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIDDAmountKeyReleased

    private void jTextFieldIDDAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldIDDAmountKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIDDAmountKeyTyped

    private void jTextFieldIDDTransactionNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIDDTransactionNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIDDTransactionNameActionPerformed

    private void jTextFieldIDDTransactionNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldIDDTransactionNameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIDDTransactionNameKeyReleased

    private void jTextFieldIDDTransactionNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldIDDTransactionNameKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIDDTransactionNameKeyTyped

    private void jButtonIDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIDDActionPerformed
        // TODO add your handling code here:
        jFrameIDD.setVisible(true);
    }//GEN-LAST:event_jButtonIDDActionPerformed

    private void jButtonBayarIPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBayarIPPActionPerformed
        // TODO add your handling code here:
        float ippAmountTemp = 0f;
        for(int i = 0 ; i<12; i++){
            if(ippStoreToDB.entries.get(i) != null){
                ippAmountTemp = ippAmountTemp + ippFromDB.entries.get(i).amount;
            }
        }
        jTextFieldIPPAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldIPPAmountSimple.setValue(ippAmountTemp);
        try {
            jTextFieldIPPAmountSimple.commitEdit();
        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        }
        this.jFrameIPP.setVisible(false);
        
    }//GEN-LAST:event_jButtonBayarIPPActionPerformed

    private void jButtonIDDOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIDDOKActionPerformed
        // TODO add your handling code here:
        jTextFieldIDDAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        
        try {
            jTextFieldIDDAmountSimple.setValue(Float.valueOf(jTextFieldIDDAmount.getText()));
            jTextFieldIDDAmountSimple.commitEdit();
        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        }
        try{
            idd = Control.selectIuran(Iuran.Tipe.IDD, IDD.noIndukColName, false, profil.noInduk);
        }catch(SQLException ex){
            idd = null;
        }catch(KasirException ex){
            idd = null;
        }
        this.jFrameIDD.setVisible(false);
    }//GEN-LAST:event_jButtonIDDOKActionPerformed

    private void jButtonIPSPOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIPSPOKActionPerformed
        // TODO add your handling code here:
        jTextFieldIPSPAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        
        try {
            jTextFieldIPSPAmountSimple.setValue(Float.valueOf(jTextFieldIPSPAmount.getText()));
            jTextFieldIPSPAmountSimple.commitEdit();
        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        }
        try{
            ipsp = Control.selectIuran(Iuran.Tipe.IPSP, IPSP.noIndukColName, false, profil.noInduk);
        }catch(SQLException ex){
            //idd = null;
        }catch(KasirException ex){
            //idd = null;
        }
        this.jFrameIPSP.setVisible(false);
    }//GEN-LAST:event_jButtonIPSPOKActionPerformed

    private void jButtonReportIPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReportIPPActionPerformed
        // TODO add your handling code here:
        printTunggakanPerKelas();
//        Set<String> noInduks = new HashSet<>();
//        ArrayList<IPP> ippSekelas = new ArrayList<>();
//        ArrayList<Float> tunggakanIPP = new ArrayList<>();
//        List<Integer> tahuns = new ArrayList<>();
//        List<TunggakanBean> tunggakanBeans = new ArrayList<TunggakanBean>();
//        ArrayList<Profil> profils = new ArrayList<>();
//        TunggakanBeanFactory tbf = new TunggakanBeanFactory();
//        tahuns.add(profil.currentLevel.tahun);
//        try {
//            profils = Control.selectProfils(profil.currentLevel.toString());
//            for(int i = 0; i< profils.size();i++){
//                tunggakanIPP.add(calculateUnpaidIPP(profils.get(i), tahuns));
//                System.out.println(tunggakanIPP.get(i));
//                System.out.println("+++^^^+++ TUNGGAKAN SI  ".concat(profils.get(i).biodata.nama));
//                
//                
//                TunggakanBean rb = new TunggakanBean();
//                rb.setProfil(profils.get(i));
//                rb.setTunggakanIPP(tunggakanIPP.get(i));
//                tunggakanBeans.add(rb);
//                tbf.addTunggakanBean(rb);
//            }
//        } catch (SQLException ex) {
//            Exceptions.printStackTrace(ex);
//        } catch (KasirException ex) {
//            Exceptions.printStackTrace(ex);
//        }
//        
//        
//        try{
//            File path = new File(".");
//            String fileName = "C:/Users/Master/Documents/NetBeansProjects/Kasir7/src/printout/PrintOutTunggakanPerKelas.jrxml";
//            String filetoPrint = "C:/Users/user/Documents/NetBeansProjects/PandiBillingPDF/src/pandibillingpdf/report/pandireport.jrprint";
//            String filetoFill = "C:/Users/user/Documents/NetBeansProjects/PandiBillingPDF/src/pandibillingpdf/report/pandireport.jasper";
//            String filePdf = "C:/Report_Tunggakan_Kelas".concat(profil.currentLevel.toString()).concat(".pdf");
//            InputStream inputStream = new FileInputStream (fileName);
//            Collection col = TunggakanBeanFactory.getBeanCollection();
//            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(col);
//            JasperCompileManager.compileReportToFile(fileName);
//            Map param = new HashMap();
//            File f = new File("images");
//            String reportsDirPath = f.getAbsolutePath();
//            param.put("reportsDirPath", reportsDirPath);
//            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
//            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
//            JasperExportManager.exportReportToPdfFile(jasperPrint, filePdf); 
//            JasperExportManager.exportReportToPdf(jasperPrint);
//            PrinterJob job = PrinterJob.getPrinterJob();
//            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
//            printRequestAttributeSet.add(OrientationRequested.PORTRAIT);
//            printRequestAttributeSet.add(MediaSizeName.ISO_A6); 
//            MediaSizeName mediaSizeName = MediaSize.findMedia(64,25,MediaPrintableArea.MM);
//            printRequestAttributeSet.add(mediaSizeName);
//            printRequestAttributeSet.add(new Copies(1));
//            job.print(printRequestAttributeSet);
//        }catch(Exception ex){
//            JOptionPane.showMessageDialog(rootPane, "Error!\r\n");
//        }
        
    }//GEN-LAST:event_jButtonReportIPPActionPerformed

    private void jButtonBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBukuActionPerformed
        // TODO add your handling code here:
        try{
            tableModelBuku = buildBukuTableModel(profil);
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(KasirException ex){
            ex.printStackTrace();
        }
        jFrameBuku.setVisible(true);
    }//GEN-LAST:event_jButtonBukuActionPerformed

    private void jButtonIKSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIKSActionPerformed
        // TODO add your handling code here:
        try{
            tableModelIKS = buildIKSTableModel(profil, profil.currentLevel.tahun);
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(KasirException ex){
            ex.printStackTrace();
        }
        jFrameIKS.setVisible(true);
    }//GEN-LAST:event_jButtonIKSActionPerformed

    private void jButtonILLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonILLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonILLActionPerformed

    private void jButtonIPSBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIPSBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonIPSBActionPerformed

    private void jButtonIUAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIUAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonIUAActionPerformed

    private void jButtonIUSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIUSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonIUSActionPerformed

    private void jButtonOSISActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOSISActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonOSISActionPerformed

    private void jButtonPASBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPASBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPASBActionPerformed

    private void jButtonPVTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPVTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPVTActionPerformed

    private void jButtonTabunganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTabunganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonTabunganActionPerformed

    private void jButtonSumbanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSumbanganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSumbanganActionPerformed

    private void jButtonBayarSeragamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBayarSeragamActionPerformed
        // TODO add your handling code here:
        float seragamAmountTemp = 0f;
        
        for(int i = 0 ; i<seragamSToDB.size(); i++){
            if(seragamSToDB.get(i) != null){
                
                    seragamAmountTemp = seragamAmountTemp + (Float)jTableSeragam.getModel().getValueAt(i, 3);
                
                
            }
        }
        jTextFieldSeragamAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldSeragamAmountSimple.setValue(seragamAmountTemp);
        try {
            jTextFieldSeragamAmountSimple.commitEdit();
        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        }
        this.jFrameSeragam.setVisible(false);
    }//GEN-LAST:event_jButtonBayarSeragamActionPerformed

    private void jTableBukuPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableBukuPropertyChange
        // TODO add your handling code here:
        bukuSToDB = new ArrayList<>();
        if(jTableBuku.getRowCount() > 0){
            for(int i = 0; i < jTableBuku.getRowCount(); i++){
//                if((Boolean)jTableBuku.getValueAt(i,3) && (bukuS.get(i).transactDetailIDs.size() == 0)){
                    if(!bukuS.isEmpty())
                    bukuSToDB.add(bukuS.get(i));
                    //                    try {
                        //buku = Control.selectIuran(Iuran.Tipe.Buku, Buku.noIndukColName, false , profil.noInduk);
                        //bukuS.remove(i);
//                    } catch (SQLException ex) {
//                        Exceptions.printStackTrace(ex);
//                    } catch (KasirException ex) {
//                        Exceptions.printStackTrace(ex);
//                    }
//                }
            }
        }
    }//GEN-LAST:event_jTableBukuPropertyChange

    private void jButtonBayarBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBayarBukuActionPerformed
        // TODO add your handling code here:
        float bukuAmountTemp = 0f;
        
        for(int i = 0 ; i<bukuSToDB.size(); i++){
            if(bukuSToDB.get(i) != null){
                bukuAmountTemp = bukuAmountTemp + bukuSToDB.get(i).amount;
            }
        }
        jTextFieldBukuAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldBukuAmountSimple.setValue(bukuAmountTemp);
        try {
            jTextFieldBukuAmountSimple.commitEdit();
        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        }
        this.jFrameBuku.setVisible(false);
    }//GEN-LAST:event_jButtonBayarBukuActionPerformed

    private void jTableDialogBukuPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableDialogBukuPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableDialogBukuPropertyChange

    private void jComboBoxTahun3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTahun3ActionPerformed
        // TODO add your handling code here:
        try{
            this.tableModelIKS = (DefaultTableModel) buildIKSTableModel(this.profil, this.tahunIKS.get(jComboBoxTahun3.getSelectedIndex()));
            jTableIKS.setModel(this.tableModelIKS);
        }catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Koneksi Error!\r\n".concat(ex.toString()));
        }catch(KasirException ex){
            JOptionPane.showMessageDialog(rootPane, "IKS Belum Di Setting!\r\n".concat(ex.toString()));
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jComboBoxTahun3ActionPerformed

    private void jComboBoxTahun3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBoxTahun3PropertyChange
        // TODO add your handling code here:
        try{
            if(this.jComboBoxTahun.getSelectedItem() !=null){
                this.tableModelIKS = (DefaultTableModel) buildIKSTableModel(this.profil, this.tahunIKS.get(jComboBoxTahun3.getSelectedIndex()));
                jTableIKS.setModel(this.tableModelIKS);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Koneksi Error!\r\n".concat(ex.toString()));
        }catch(KasirException ex){
            JOptionPane.showMessageDialog(rootPane, "IKS Belum Di Setting!\r\n".concat(ex.toString()));
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jComboBoxTahun3PropertyChange

    private void jTableIKSFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableIKSFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableIKSFocusLost

    private void jTableIKSPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableIKSPropertyChange
        // TODO add your handling code here:
                // TODO add your handling code here:
        //IDD, Beasiswa, Beasiswa Cost
        iDDAmounts = new ArrayList();
        beasiswaAmounts = new ArrayList();
        beasiswaCostAmounts = new ArrayList();
        /////BLOOOOOOOOOOM SELESAIIIIIIIIIIIIIIIIIIII
        int i = 0;
        iksCurrent=new IKS();
        iksStoreToDB= new IKS();
        ArrayList<Entry> entry = new ArrayList<>();
        if(iksFromDB.entries.size() !=0){
            for(i=0; i< jTableIKS.getRowCount();i++){
                iksCurrent.entries.add((Boolean)jTableIKS.getValueAt(i,2)? ((iksFromDB.entries.get(i).transactDetailIDs.size() > 0)? new Entry(i, iksFromDB.entries.get(i).amount, iksFromDB.entries.get(i).debt, iksFromDB.entries.get(i).transactDetailIDs): new Entry(i, iksFromDB.entries.get(i).amount)): null);
                //belon condition is when jTableIKS checkBox is UNCHECK ????
                if(iksCurrent.entries.get(i)==null){
                    iksStoreToDB.entries.add(null);
                    jTableIKS.setValueAt(0f, i,3);
                    jTableIKS.setValueAt(0f, i,4);
                    jTableIKS.setValueAt(0f, i,5);
                    jTableIKS.setValueAt(0f, i,6);
                    iDDAmounts.add(0f);
                    beasiswaAmounts.add(0f);
                    beasiswaCostAmounts.add(0f);
                //below is when jTableIKS property changed, especially check box is Changed or Editing IDD, Beasiswa, Beasiswa Cost 
                }else if ((iksCurrent.entries.get(i) !=null) ^ (iksFromDB.entries.get(i).transactDetailIDs.size() > 0)){
                    
                    iDDAmounts.add((Float)jTableIKS.getValueAt(i,4));
                    beasiswaAmounts.add((Float)jTableIKS.getValueAt(i,5));
                    beasiswaCostAmounts.add((Float)jTableIKS.getValueAt(i,6));
                    jTableIKS.setValueAt(iksFromDB.entries.get(i).amount - (iDDAmounts.get(i) + beasiswaAmounts.get(i)+beasiswaCostAmounts.get(i)), i,3);
                    iksStoreToDB.entries.add(new Entry(i, iksFromDB.entries.get(i).amount));
                    
                }else{
                    iksStoreToDB.entries.add(null);
                    iDDAmounts.add(0f);
                    beasiswaAmounts.add(0f);
                    beasiswaCostAmounts.add(0f);
                    
                }
                //iksCurrent.entries.add((Boolean)jTableIKS.getValueAt(i,2)? new IKS(profil.noInduk, new Level(null,null,null,(Integer)jComboBoxTahun.getSelectedItem()),entry).entries.get(i): null);
                //iksStoreToDB.entries.add(iksCurrent.entries.get(i)==null?null:(((iksCurrent.entries.get(i)!=null)^(iksFromDB.entries.get(i)!=null))? new IKS(profil.noInduk, new Level(null,null,null,(int)jComboBoxTahun.getSelectedItem()),entry).entries.get(i):iksCurrent.entries.get(i)));
                //System.out.println("IKS Period: "+iksCurrent.entries.get(i).period);
            }
        }
    }//GEN-LAST:event_jTableIKSPropertyChange

    private void jButtonBayarIKSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBayarIKSActionPerformed
        // TODO add your handling code here:
        float iksAmountTemp = 0f;
        for(int i = 0 ; i<1; i++){
            if(iksStoreToDB.entries.get(i) != null){
                iksAmountTemp = iksAmountTemp + iksFromDB.entries.get(i).amount;
            }
        }
        jTextFieldIKSAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldIKSAmountSimple.setValue(iksAmountTemp);
        try {
            jTextFieldIKSAmountSimple.commitEdit();
        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        }
        this.jFrameIKS.setVisible(false);
    }//GEN-LAST:event_jButtonBayarIKSActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InputTransactionFrameSimple.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputTransactionFrameSimple.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputTransactionFrameSimple.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputTransactionFrameSimple.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new InputTransactionFrameSimple().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBayarBuku;
    private javax.swing.JButton jButtonBayarIKS;
    private javax.swing.JButton jButtonBayarIPP;
    private javax.swing.JButton jButtonBayarSeragam;
    private javax.swing.JButton jButtonBuku;
    private javax.swing.JButton jButtonDialogCancel;
    private javax.swing.JButton jButtonDialogCancel1;
    private javax.swing.JButton jButtonIDD;
    private javax.swing.JButton jButtonIDDOK;
    private javax.swing.JButton jButtonIKS;
    private javax.swing.JButton jButtonILL;
    private javax.swing.JButton jButtonIPP;
    private javax.swing.JButton jButtonIPSB;
    private javax.swing.JButton jButtonIPSP;
    private javax.swing.JButton jButtonIPSPOK;
    private javax.swing.JButton jButtonIUA;
    private javax.swing.JButton jButtonIUS;
    private javax.swing.JButton jButtonOSIS;
    private javax.swing.JButton jButtonPASB;
    private javax.swing.JButton jButtonPVT;
    private javax.swing.JButton jButtonReportIPP;
    private javax.swing.JButton jButtonSave1;
    private javax.swing.JButton jButtonSave2;
    private javax.swing.JButton jButtonSeragam;
    private javax.swing.JButton jButtonSubmit;
    private javax.swing.JButton jButtonSumbangan;
    private javax.swing.JButton jButtonTabungan;
    private javax.swing.JComboBox jComboBoxDialogIPSP;
    private javax.swing.JComboBox jComboBoxDialogIPSP1;
    private javax.swing.JComboBox jComboBoxIPSP;
    private javax.swing.JComboBox jComboBoxTahun;
    private javax.swing.JComboBox jComboBoxTahun1;
    private javax.swing.JComboBox jComboBoxTahun2;
    private javax.swing.JComboBox jComboBoxTahun3;
    private javax.swing.JDialog jDialogTransactionSummary;
    private javax.swing.JFormattedTextField jFormattedTextFieldIDDSaldo;
    private javax.swing.JFormattedTextField jFormattedTextFieldIDDSaldo1;
    private javax.swing.JFrame jFrameBuku;
    private javax.swing.JFrame jFrameIDD;
    private javax.swing.JFrame jFrameIKS;
    private javax.swing.JFrame jFrameIPP;
    private javax.swing.JFrame jFrameIPSP;
    private javax.swing.JFrame jFrameSeragam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAmount3;
    private javax.swing.JLabel jLabelAmount4;
    private javax.swing.JLabel jLabelIDDTitle;
    private javax.swing.JLabel jLabelLevel1;
    private javax.swing.JLabel jLabelLevel2;
    private javax.swing.JLabel jLabelLevel2Level3;
    private javax.swing.JLabel jLabelLevel2Level4;
    private javax.swing.JLabel jLabelLevel2Level5;
    private javax.swing.JLabel jLabelLevel3;
    private javax.swing.JLabel jLabelNama;
    private javax.swing.JLabel jLabelNama1;
    private javax.swing.JLabel jLabelNama2;
    private javax.swing.JLabel jLabelNoInduk;
    private javax.swing.JLabel jLabelNoInduk1;
    private javax.swing.JLabel jLabelNoInduk2;
    private javax.swing.JLabel jLabelNote2;
    private javax.swing.JLabel jLabelTahun;
    private javax.swing.JLabel jLabelTahun1;
    private javax.swing.JLabel jLabelTahun2;
    private javax.swing.JLabel jLabelTahun3;
    private javax.swing.JLabel jLableProfilTitle;
    private javax.swing.JLabel jLableProfilTitle1;
    private javax.swing.JLabel jLableProfilTitle2;
    private javax.swing.JPanel jPaneBuku;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelDialogBuku;
    private javax.swing.JPanel jPanelDialogIPP;
    private javax.swing.JPanel jPanelDialogIPP1;
    private javax.swing.JPanel jPanelDialogIPSP;
    private javax.swing.JPanel jPanelDialogIPSP1;
    private javax.swing.JPanel jPanelDialogMain;
    private javax.swing.JPanel jPanelDialogMain1;
    private javax.swing.JPanel jPanelDialogProfil;
    private javax.swing.JPanel jPanelDialogProfil1;
    private javax.swing.JPanel jPanelDialogSeragam;
    private javax.swing.JPanel jPanelDialogSeragam1;
    private javax.swing.JPanel jPanelIDD;
    private javax.swing.JPanel jPanelIKS;
    private javax.swing.JPanel jPanelIPP;
    private javax.swing.JPanel jPanelIPSP;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelProfil;
    private javax.swing.JPanel jPanelSeragam;
    private javax.swing.JPanel jPanelTransactionSummary1;
    private javax.swing.JPanel jPanelTransactionSummary2;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTableBuku;
    private javax.swing.JTable jTableDialogBuku;
    private javax.swing.JTable jTableDialogSeragam;
    private javax.swing.JTable jTableDialogSeragam1;
    private javax.swing.JTable jTableIKS;
    private javax.swing.JTable jTableIPP;
    private javax.swing.JTable jTableIPP1;
    private javax.swing.JTable jTableIPP2;
    private javax.swing.JTable jTableSeragam;
    private javax.swing.JTextArea jTextAreaIDDNote;
    private javax.swing.JFormattedTextField jTextFieldBukuAmountSimple;
    private javax.swing.JTextField jTextFieldIDDAmount;
    private javax.swing.JFormattedTextField jTextFieldIDDAmountSimple;
    private javax.swing.JTextField jTextFieldIDDTransactionName;
    private javax.swing.JFormattedTextField jTextFieldIKSAmountSimple;
    private javax.swing.JFormattedTextField jTextFieldILLAmountSimple;
    private javax.swing.JFormattedTextField jTextFieldIPPAmountSimple;
    private javax.swing.JFormattedTextField jTextFieldIPSBAmountSimple;
    private javax.swing.JTextField jTextFieldIPSPAmount;
    private javax.swing.JTextField jTextFieldIPSPAmount1;
    private javax.swing.JTextField jTextFieldIPSPAmount2;
    private javax.swing.JFormattedTextField jTextFieldIPSPAmountSimple;
    private javax.swing.JTextField jTextFieldIPSPIuranAmount;
    private javax.swing.JTextField jTextFieldIPSPNote;
    private javax.swing.JTextField jTextFieldIPSPNote1;
    private javax.swing.JTextField jTextFieldIPSPNote2;
    private javax.swing.JTextField jTextFieldIPSPSisaAmount;
    private javax.swing.JFormattedTextField jTextFieldIUAAmountSimple;
    private javax.swing.JFormattedTextField jTextFieldIUSAmountSimple;
    private javax.swing.JTextField jTextFieldLevel2Level3;
    private javax.swing.JTextField jTextFieldLevel2Level4;
    private javax.swing.JTextField jTextFieldLevel2Level5;
    private javax.swing.JTextField jTextFieldNama;
    private javax.swing.JTextField jTextFieldNama1;
    private javax.swing.JTextField jTextFieldNama2;
    private javax.swing.JTextField jTextFieldNomorInduk;
    private javax.swing.JTextField jTextFieldNomorInduk1;
    private javax.swing.JTextField jTextFieldNomorInduk2;
    private javax.swing.JFormattedTextField jTextFieldOSISAmountSimple;
    private javax.swing.JFormattedTextField jTextFieldPASBAmountSimple;
    private javax.swing.JFormattedTextField jTextFieldPVTAmountSimple;
    private javax.swing.JFormattedTextField jTextFieldSeragamAmountSimple;
    private javax.swing.JFormattedTextField jTextFieldSumbanganAmountSimple;
    private javax.swing.JTextField jTextFieldTSumTotalAmount;
    private javax.swing.JTextField jTextFieldTSumTotalAmount1;
    private javax.swing.JFormattedTextField jTextFieldTabunganAmountSimple;
    private javax.swing.JTextField jTextFieldTransactionSummaryNote;
    private javax.swing.JTextField jTextFieldTransactionSummaryNote1;
    private javax.swing.JTextField jTextFieldTransactionSummaryNote2;
    private javax.swing.JTextField jUnpaidBuku;
    private javax.swing.JTextField jUnpaidIKS;
    private javax.swing.JTextField jUnpaidIPP;
    private javax.swing.JTextField jUnpaidSeragam;
    // End of variables declaration//GEN-END:variables
    
    private void continueToConfirmationDialog() {
        this.setVisible(false);
        //this.dispose();
        jDialogTransactionSummary.setVisible(true);
    }

    private void saveToDB() throws SQLException, KasirException {
        Control.insertTSummary(new TransactionSummary(this.transactionSummary.uuid, this.transactionSummary.noInduk, this.transactionSummary.idClerk, this.transactionSummary.totalAmount, this.transactionSummary.note));
        
        this.transactionSummary = Control.selectTSummary(TransactionSummary.uuidColName, false, tSumUUID.toString());
        //Bagian IDD
        if(this.iddTDetail != null){
            this.iddTDetail.transactSummaryID = transactionSummary.id;
            this.iddTDetail.lastUpdateDate = theCreatedDate;
            this.iddTDetail.idClerk = this.clerk.id;
            this.iddTDetail.note = jTextFieldIDDTransactionName.getText().concat(" - ").concat(jTextAreaIDDNote.getText());
            if((Long)idd.id > 0L){
                IDD.transactIn(profil, transactionSummary.id, ((Long)jTextFieldIDDAmountSimple.getValue()).floatValue());
            }else{
                Control.insertIuran(Iuran.Tipe.IDD, idd);
                idd = Control.selectIuran(Iuran.Tipe.IDD, IDD.noIndukColName,false,profil.noInduk);
                
                this.iddTDetail = new IDDTransactionDetail(tSumUUID, idd.id, clerk.id, transactionSummary.id, profil.noInduk, profil.currentLevel.level1, iddTDetail.amount, TransactionDetail.PaymentMethod.CASH, iddTDetail.note);
                
                Control.insertTDetail(TransactionDetail.Tipe.IDDTransaction, this.iddTDetail);
            }
        }
        
        //Bagian IPSP
        if(this.iPSPTransactionDetail!=null){
            this.iPSPTransactionDetail.transactSummaryID= transactionSummary.id;
            this.iPSPTransactionDetail.lastUpdateDate = theCreatedDate;
            Control.insertTDetail(TransactionDetail.Tipe.IPSPTransaction, this.iPSPTransactionDetail);
            Set<IPSPTransactionDetail> filterIPSP = new HashSet<>();
            filterIPSP.add(this.iPSPTransactionDetail);
            Map<Long,IPSPTransactionDetail> map = new HashMap<>();
            Control.filterSelectTDetails(TransactionDetail.Tipe.IPSPTransaction, filterIPSP).get(this.iPSPTransactionDetail);
            this.iPSPTransactionDetail = Control.selectTDetail(TransactionDetail.Tipe.IPSPTransaction, TransactionDetail.uuidColName, false, ipspTDetailUUID.toString());
            ipsp.transactDetailIDs.add(this.iPSPTransactionDetail.id);
            ipsp.totalInstallment = ipsp.totalInstallment + this.iPSPTransactionDetail.amount;
            Control.updateIuran(Iuran.Tipe.IPSP, ipsp);
            this.transactionSummary.totalAmount = totalAmount;
            Control.updateTSummary(transactionSummary);
        }
        //Bagian Seragam
        if(seragamSToDB != null){
            for(int i = 0; i<seragamSToDB.size(); i++){
                if((Float)jTableSeragam.getModel().getValueAt(i,3)>0f){
                    seragam = seragamSToDB.get(i);
                    seragamTDetailUUID = UUID.randomUUID();
                    this.seragamTransactionDetail = new SeragamTransactionDetail(seragamTDetailUUID,seragamS.get(i).id, clerk.id, transactionSummary.id, profil.noInduk, profil.currentLevel.level1, (Float)jTableSeragam.getModel().getValueAt(i,3), TransactionDetail.PaymentMethod.CASH, new Kalender(), new Kalender(), seragamS.get(i).note, false, false);
                    Control.insertTDetail(TransactionDetail.Tipe.SeragamTransaction, this.seragamTransactionDetail);
                    this.seragamTransactionDetail = Control.selectTDetail(TransactionDetail.Tipe.SeragamTransaction, TransactionDetail.uuidColName, false, seragamTDetailUUID.toString());
                    seragam.transactDetailIDs.add(this.seragamTransactionDetail.id);
                    Control.updateIuran(Iuran.Tipe.Seragam, seragam);
                    this.transactionSummary = Control.selectTSummary(this.transactionSummary.id);
                    this.transactionSummary.totalAmount = (Float)jTableSeragam.getModel().getValueAt(i,3);
                    Control.updateTSummary(this.transactionSummary);
                }else{
                    System.out.println("Seragam transaction : nothing");
                }
            }
        }
        
        //Bagian Buku
        if(bukuSToDB != null){
            for(int i = 0; i<bukuSToDB.size(); i++){
                if((Boolean)jTableBuku.getModel().getValueAt(i,3)){
                    buku = bukuSToDB.get(i);
                    bukuTDetailUUID = UUID.randomUUID();
                    this.bukuTransactionDetail = new BukuTransactionDetail(bukuTDetailUUID,bukuS.get(i).id, clerk.id, transactionSummary.id, profil.noInduk, profil.currentLevel.level1, bukuS.get(i).amount, TransactionDetail.PaymentMethod.CASH, new Kalender(), new Kalender(), bukuS.get(i).note, false, false);
                    Control.insertTDetail(TransactionDetail.Tipe.BukuTransaction, this.bukuTransactionDetail);
                    this.bukuTransactionDetail = Control.selectTDetail(TransactionDetail.Tipe.BukuTransaction, TransactionDetail.uuidColName, false, bukuTDetailUUID.toString());
                    buku.transactDetailIDs.add(this.bukuTransactionDetail.id);
                    Control.updateIuran(Iuran.Tipe.Buku, buku);
                    this.transactionSummary = Control.selectTSummary(this.transactionSummary.id);
                    this.transactionSummary.totalAmount = buku.amount;
                    Control.updateTSummary(this.transactionSummary);
                }else{
                    System.out.println("Buku transaction : nothing");
                }
            }
        }
        
        //Bagian IPP
        if(jTextFieldIPPAmountSimple.getValue() != null){
        for(int i = 0 ; i < 12 ; i++){
            ippTDetailUUID = UUID.randomUUID();
            if(ippStoreToDB.entries.get(i)!=null){
                if(ippFromDB.entries.get(i).transactDetailIDs.size() == 0){
                ArrayList<IPPTransactionDetail> ippTransactionDetails = new ArrayList();
                    IPPTransactionDetail ippTransactionDetail = new IPPTransactionDetail(ippTDetailUUID, 
                                                                                        ippFromDB.id, 
                                                                                        clerk.id, 
                                                                                        transactionSummary.id, profil.noInduk, profil.currentLevel.level1, (ippFromDB.entries.get(i).amount-iDDAmounts.get(i)-beasiswaAmounts.get(i)-beasiswaCostAmounts.get(i)), TransactionDetail.PaymentMethod.CASH,
                                                                                        jTableIPP.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                
                ippTransactionDetails.add(ippTransactionDetail);
                if(iDDAmounts.get(i) > 0f){
                    ippTransactionDetail = new IPPTransactionDetail(ippTDetailUUID, 
                                                                                        ippFromDB.id, 
                                                                                        clerk.id, 
                                                                                        transactionSummary.id, profil.noInduk, profil.currentLevel.level1, iDDAmounts.get(i), TransactionDetail.PaymentMethod.IDD,
                                                                                        jTableIPP.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                    ippTransactionDetails.add(ippTransactionDetail);
                    // transactOUT IDD , kurangin IDD amount sama insert TDetail IDD
                    
                    IDD.transactOut(profil, transactionSummary.id, iDDAmounts.get(i));
                }
                if(beasiswaAmounts.get(i) > 0f){
                    ippTransactionDetail = new IPPTransactionDetail(ippTDetailUUID, 
                                                                                        ippFromDB.id, 
                                                                                        clerk.id, 
                                                                                        transactionSummary.id, profil.noInduk, profil.currentLevel.level1, beasiswaAmounts.get(i), TransactionDetail.PaymentMethod.BEASISWA,
                                                                                        jTableIPP.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                    ippTransactionDetails.add(ippTransactionDetail);
                    Beasiswa.transactOut(profil, transactionSummary.id, beasiswaAmounts.get(i));
                }
                if(beasiswaCostAmounts.get(i) > 0f){
                    ippTransactionDetail = new IPPTransactionDetail(ippTDetailUUID, 
                                                                                        ippFromDB.id, 
                                                                                        clerk.id, 
                                                                                        transactionSummary.id, profil.noInduk, profil.currentLevel.level1, beasiswaCostAmounts.get(i), TransactionDetail.PaymentMethod.BEASISWA_COST,
                                                                                        jTableIPP.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                    ippTransactionDetails.add(ippTransactionDetail);
                    BeasiswaCost.transactOut(profil, transactionSummary.id, beasiswaCostAmounts.get(i));
                }
                Control.insertTDetails(TransactionDetail.Tipe.IPPTransaction, ippTransactionDetails);
                ippTransactionDetails.clear();
                ippTransactionDetails = new ArrayList(Control.selectTDetails(TransactionDetail.Tipe.IPPTransaction, TransactionDetail.uuidColName, false, ippTDetailUUID.toString()));
                for(int j = 0 ; j < ippTransactionDetails.size(); j++){
                    ippStoreToDB.entries.get(i).transactDetailIDs.add(ippTransactionDetails.get(j).id);
                }
//                Control.insertTDetail(TransactionDetail.Tipe.IPPTransaction, ippTransactionDetail);
//                ippTransactionDetail = Control.selectTDetail(TransactionDetail.Tipe.IPPTransaction, TransactionDetail.uuidColName, false, ippTDetailUUID.toString());
//                ippStoreToDB.entries.get(i).transactDetailIDs.add(ippTransactionDetail.id);
                
                }
            }
        }
        ippStoreToDB.id = ippFromDB.id;
        ippStoreToDB.noInduk = ippFromDB.noInduk;
        ippStoreToDB.chargedLevel = ippFromDB.chargedLevel;
        Control.updateIuran(Iuran.Tipe.IPP, ippStoreToDB);
        this.transactionSummary.totalAmount = totalAmount;
        Control.updateTSummary(transactionSummary);
        }
    }

    private void prepareObjects() throws SQLException, KasirException {
        IPSP ipsp = Control.selectIuran(Iuran.Tipe.IPSP, IPSP.noIndukColName,false, profil.noInduk);
        jTextFieldIPSPIuranAmount.setText(String.valueOf(ipsp.amount));
        if(!jTextFieldIPSPAmount.getText().equals("")){
            if(Float.valueOf(jTextFieldIPSPAmount.getText()) > 0F){
                this.ipsp = ipsp;
                
                ipspTDetailUUID = UUID.randomUUID();
                IPSPTransactionDetail ipspTransactionDetail = new IPSPTransactionDetail(ipspTDetailUUID,ipsp.id, this.clerk.id, 0L, profil.noInduk, profil.currentLevel.level1, Float.valueOf(jTextFieldIPSPAmount.getText()),
                                                                        (TransactionDetail.PaymentMethod)jComboBoxIPSP.getSelectedItem(), new sak.Kalender(System.currentTimeMillis()), null, jTextFieldIPSPNote.getText(), false,false);
                theCreatedDate = new sak.Kalender(System.currentTimeMillis());
                tSumUUID = UUID.randomUUID();
                TransactionSummary transactionSummary = new TransactionSummary(tSumUUID,profil.noInduk, this.clerk.id, theCreatedDate, theCreatedDate, Float.valueOf(jTextFieldIPSPAmount.getText()), "TSum Note");
                this.iPSPTransactionDetail = ipspTransactionDetail;
                this.transactionSummary = transactionSummary;
            }
        }
        this.tableModelSeragam = buildSeragamTableModel(this.profil);
        jTableSeragam.setModel(this.tableModelSeragam);
        
        this.tableModelBuku = buildBukuTableModel(this.profil);
        jTableBuku.setModel(this.tableModelBuku);
        
        this.tableModelIPP = (DefaultTableModel) buildIPPTableModel(this.profil, this.tahunIPP.get(jComboBoxTahun.getSelectedIndex()));
        jTableIPP.setModel(this.tableModelIPP);
    }
    
    private void prepareSubmitObjects() throws SQLException, KasirException {
        tSumUUID = UUID.randomUUID();
        TransactionSummary transactionSummary = new TransactionSummary(tSumUUID,profil.noInduk, this.clerk.id, theCreatedDate, theCreatedDate, totalAmount, jTextFieldTransactionSummaryNote.getText());
        
        //IDD Part
        if(!jTextFieldIDDAmountSimple.getText().equals("") && !jTextFieldIDDAmountSimple.getText().equals("0.0")){
            if(idd == null){
                idd = new IDD(profil.noInduk,profil.currentLevel, jTextFieldIDDTransactionName.getText(), ((Long)jTextFieldIDDAmountSimple.getValue()).floatValue(), jTextAreaIDDNote.getText());
                iddTDetail = new IDDTransactionDetail(tSumUUID, 0L, this.clerk.id, 0L, profil.noInduk, profil.currentLevel.level1, ((Long)jTextFieldIDDAmountSimple.getValue()).floatValue(), TransactionDetail.PaymentMethod.CASH, jTextAreaIDDNote.getText());
            }else if (idd != null){
                jTextFieldIDDAmountSimple.getValue();
                iddTDetail = new IDDTransactionDetail(tSumUUID, idd.id, this.clerk.id, 0L, profil.noInduk, profil.currentLevel.level1
                        , ((Long)jTextFieldIDDAmountSimple.getValue()).floatValue(), TransactionDetail.PaymentMethod.CASH, jTextAreaIDDNote.getText());
            }
            totalAmount = totalAmount + ((Long)jTextFieldIDDAmountSimple.getValue()).floatValue();
        }
        
        //IPSP Part
        if(!jTextFieldIPSPAmount.getText().equals("")){
            if(Float.valueOf(jTextFieldIPSPAmount.getText()) > 0F){
                IPSP ipsp = Control.selectIuran(Iuran.Tipe.IPSP, IPSP.noIndukColName,false, profil.noInduk);
                this.ipsp = ipsp;
                ipspTDetailUUID = UUID.randomUUID();
                IPSPTransactionDetail ipspTransactionDetail = new IPSPTransactionDetail(ipspTDetailUUID,ipsp.id, this.clerk.id, 0L, profil.noInduk, profil.currentLevel.level1, Float.valueOf(jTextFieldIPSPAmount.getText()),
                                                                        (TransactionDetail.PaymentMethod)jComboBoxIPSP.getSelectedItem(), new sak.Kalender(System.currentTimeMillis()), null, jTextFieldIPSPNote.getText(), false,false);
                theCreatedDate = new sak.Kalender(System.currentTimeMillis());
                this.iPSPTransactionDetail = ipspTransactionDetail;
                jTextFieldIPSPAmount1.setText(jTextFieldIPSPAmount.getText());
                jTextFieldIPSPNote1.setText(jTextFieldIPSPNote.getText());
                totalAmount = totalAmount + (Float.valueOf(jTextFieldIPSPAmount.getText()));
            }
        }
//Seragam PART
        if(seragamSToDB != null){
            this.tableModelSeragam = buildSeragamSubmitTableModel(this.profil, seragamSToDB);
            jTableSeragam.setModel(this.tableModelSeragam);
            jTableDialogSeragam.setModel(this.tableModelSeragam);
            for(int i = 0 ; i < seragamSToDB.size(); i++){
                if((Boolean)jTableSeragam.getModel().getValueAt(i,3)){
                    totalAmount = totalAmount + seragamSToDB.get(i).amount;
                }
            }
        }
//Buku PART
        if(bukuSToDB != null){
            this.tableModelBuku = buildBukuSubmitTableModel(this.profil, bukuSToDB);
            jTableBuku.setModel(this.tableModelBuku);
            jTableDialogBuku.setModel(this.tableModelBuku);
            for(int i = 0 ; i < bukuSToDB.size(); i++){
                if((Boolean)jTableBuku.getModel().getValueAt(i,3)){
                    totalAmount = totalAmount + bukuSToDB.get(i).amount;
                }
            }
        }        
//IPP PART
        if(jTextFieldIPPAmountSimple.getValue() != null){
            this.tableModelIPP = (DefaultTableModel) buildIPPSubmitTableModel(this.profil, this.tahunIPP.get(jComboBoxTahun.getSelectedIndex()));
            jTableIPP.setModel(this.tableModelIPP);
            jTableIPP1.setModel(this.tableModelIPP);

            for(int i = 0 ; i<12; i++){
                if(ippStoreToDB.entries.get(i) != null){
                    totalAmount = totalAmount + (ippFromDB.entries.get(i).amount - iDDAmounts.get(i));
                }
            }
        }
//TRANSACTION SUMMARY PART        
        transactionSummary.totalAmount = totalAmount;
        transactionSummary.note = jTextFieldTransactionSummaryNote.getText();
        transactionSummary.createDate = new Kalender(System.currentTimeMillis());
        this.transactionSummary = transactionSummary;
        jTextFieldTransactionSummaryNote1.setText(jTextFieldTransactionSummaryNote.getText());
        jTextFieldTSumTotalAmount.setText(String.valueOf(totalAmount));
        
    }
    
    private TableModel buildSeragamTableModel(Profil profil) throws SQLException, KasirException{
       String columnNames[] = {"Transaksi Seragam", "Biaya", "Catatan", "Pembayaran"};
       Set<Seragam> seragamFilters = new HashSet<>();
       seragamFilters.add(new Seragam(profil.noInduk,null, null, 0F, null));
       Map<Long, Seragam> searchResultMap = new HashMap<>();
       searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.Seragam, seragamFilters);
       
       int j = 0;
       seragamS = new ArrayList<>();       
       if(searchResultMap.size() > 0){
        for(Map.Entry<Long, Seragam> entry: searchResultMap.entrySet()){
            if(entry.getValue().transactDetailIDs.size() == 0){
                seragamS.add(entry.getValue());
            }
            j++;
        }
       }
       Object[][] data = new Object[seragamS.size()][5];
       for(int i =0; i < seragamS.size(); i++){
            data[i][0]= seragamS.get(i).transactName;
            data[i][1]= seragamS.get(i).amount;
            data[i][2]= seragamS.get(i).note;
            data[i][3]= 0f;
//            if(seragamS.get(i).transactDetailIDs.size()>0){
//                data[i][3] = new Boolean(true);
//            }else{
//                data[i][3] = new Boolean(false);
//            }
       }
       calculateUnpaidSeragam(profil);
       if(data.length > 0){
       TableModel tm = new DefaultTableModel(data, columnNames){
           @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false for editable
               if(jDialogTransactionSummary.isVisible()){
                    return false;
               }else{
                    return true;
               }
            }
            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }
       };
       
       return tm;
       }else{
           TableModel tm = new DefaultTableModel(columnNames, 4);
           return tm;
       }
   }
    
    private TableModel buildSeragamSubmitTableModel(Profil profil, List<Seragam> stdb) throws SQLException, KasirException{
       String columnNames[] = {"Transaksi Seragam", "Biaya", "Catatan", "Pembayaran"};
       
       Set<Seragam> seragamFilters = new HashSet<>();
       seragamFilters.addAll(stdb);
       Map<Long, Seragam> searchResultMap = new HashMap<>();
       searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.Seragam, seragamFilters);
       
       int j = 0;
       seragamS = new ArrayList<>();       
       if(searchResultMap.size() > 0){
        for(Map.Entry<Long, Seragam> entry: searchResultMap.entrySet()){
            if(entry.getValue().transactDetailIDs.size() == 0){
                seragamS.add(entry.getValue());
            }
            j++;
        }
       }
       Object[][] data = new Object[seragamS.size()][5];
       for(int i =0; i < seragamS.size(); i++){
            data[i][0]= seragamS.get(i).transactName;
            data[i][1]= seragamS.get(i).amount;
            data[i][2]= seragamS.get(i).note;
            data[i][3] = new Boolean(true);
       }
       if(data.length > 0){
       TableModel tm = new DefaultTableModel(data, columnNames){
           @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false for editable
               if(jDialogTransactionSummary.isVisible()){
                    return false;
               }else{
                    return true;
               }
            }
            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }
       };
       
       return tm;
       }else{
           TableModel tm = new DefaultTableModel(columnNames, 4);
           return tm;
       }
   }
    
   private TableModel buildBukuTableModel(Profil profil) throws SQLException, KasirException{
       String columnNames[] = {"Transaksi Buku", "Biaya", "Catatan", "Check Box"};
       Set<Buku> bukuFilters = new HashSet<>();
       bukuFilters.add(new Buku(profil.noInduk,null, null, 0F, null));
       Map<Long, Buku> searchResultMap = new HashMap<>();
       searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.Buku, bukuFilters);
       
       int j = 0;
       bukuS = new ArrayList<>();       
       if(searchResultMap.size() > 0){
        for(Map.Entry<Long, Buku> entry: searchResultMap.entrySet()){
            if(entry.getValue().transactDetailIDs.size() == 0){
                bukuS.add(entry.getValue());
            }
            j++;
        }
       }
       Object[][] data = new Object[bukuS.size()][5];
       for(int i =0; i < bukuS.size(); i++){
            data[i][0]= bukuS.get(i).transactName;
            data[i][1]= bukuS.get(i).amount;
            data[i][2]= bukuS.get(i).note;
            if(bukuS.get(i).transactDetailIDs.size()>0){
                //data[i][3] = new Boolean(true);
                
//            }else if(jTableBuku != null && (Boolean)jTableBuku.getValueAt(i,3)){
//                data[i][3] = jTableBuku.getValueAt(i,3);
            }else{
                //data[i][3] = new Boolean(false);
            }
            data[i][3] = "";
       }
       calculateUnpaidBuku(profil);
       if(data.length > 0){
       TableModel tm = new DefaultTableModel(data, columnNames){
           @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false for editable
               if(jDialogTransactionSummary.isVisible()){
                    return false;
               }else{
                    return true;
               }
            }
            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }
       };
       
       return tm;
       }else{
           TableModel tm = new DefaultTableModel(columnNames, 4);
           return tm;
       }
   }
    
    private TableModel buildBukuSubmitTableModel(Profil profil, List<Buku> stdb) throws SQLException, KasirException{
       String columnNames[] = {"Transaksi Buku", "Biaya", "Catatan", "Check Box"};
       
       Set<Buku> bukuFilters = new HashSet<>();
       bukuFilters.addAll(stdb);
       Map<Long, Buku> searchResultMap = new HashMap<>();
       searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.Buku, bukuFilters);
       
       int j = 0;
       bukuS = new ArrayList<>();       
       if(searchResultMap.size() > 0){
        for(Map.Entry<Long, Buku> entry: searchResultMap.entrySet()){
            if(entry.getValue().transactDetailIDs.size() == 0){
                bukuS.add(entry.getValue());
            }
            j++;
        }
       }
       Object[][] data = new Object[bukuS.size()][5];
       for(int i =0; i < bukuS.size(); i++){
            data[i][0]= bukuS.get(i).transactName;
            data[i][1]= bukuS.get(i).amount;
            data[i][2]= bukuS.get(i).note;
//            data[i][3] = new Boolean(true);
            data[i][3] ="";
       }
       if(data.length > 0){
       TableModel tm = new DefaultTableModel(data, columnNames){
           @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false for editable
               if(jDialogTransactionSummary.isVisible()){
                    return false;
               }else{
                    return true;
               }
            }
            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }
       };
       
       return tm;
       }else{
           TableModel tm = new DefaultTableModel(columnNames, 4);
           return tm;
       }
   }
    
    private ComboBoxModel buildIPPtahunComboBoxModel(Profil profil) throws SQLException, KasirException {
       Set<IPP> ippFilters = new HashSet<>();
       ArrayList<Entry> entries = new ArrayList();
       ippFilters.add(new IPP(profil.noInduk, null, entries));
       Map<Long, IPP> searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.IPP, ippFilters);
       this.tahunIPP = new ArrayList<>();
       List<String> tahunAjaran = new ArrayList<>();
       Object[][] data = new Object[searchResultMap.size()][12];
       int i = 0;
       if(searchResultMap.size() > 0){
        for(Map.Entry<Long, IPP> entry: searchResultMap.entrySet()){
            data[i][0]= entry.getValue().entries.get(i);
            data[i][1]= entry.getValue().entries.get(i).amount;
            
            this.tahunIPP.add(entry.getValue().chargedLevel.tahun);
            tahunAjaran.add(String.valueOf(entry.getValue().chargedLevel.tahun).concat(" - ").concat(String.valueOf(entry.getValue().chargedLevel.tahun+1)));
            i++;
        }
       }
       calculateUnpaidIPP(profil, tahunIPP);
       //== bikin tunggakan beans ===
       
       
       //== end bikin tunggakan beans ===
       
       tahunComboBoxModel = new DefaultComboBoxModel(tahunAjaran.toArray());
       return tahunComboBoxModel;
    }

    private TableModel buildIPPTableModel(Profil profil, int tahun) throws SQLException, KasirException {
       String columnNames[] = {"Bulan", "Biaya IPP", "Check Box", "Tunai", "Iuran Dibayar Dimuka", "Beasiswa", "Beasiswa Prestasi"};
       Set<IPP> ippFilters = new HashSet<>();
       ArrayList<Entry> entries = new ArrayList<>();
       ippFilters.clear();
       ippFilters.add(new IPP(profil.noInduk, new Level(null,null,null,tahun), entries));
       Map<Long, IPP> searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.IPP, ippFilters);
       Object[][] data = new Object[12][7];
       int i = 0;
       final boolean[] canEdit = new boolean [12];
       ippFromDB = new IPP();
       float amountIPPinTable;
       float amountIPPinTDetail;
       if(searchResultMap.size() > 0){
        for(Map.Entry<Long, IPP> entry: searchResultMap.entrySet()){
            for(int j =0 ; j< entry.getValue().entries.size(); j++){
                data[j][0]= namaBulan[j];//entry.getValue().entries.get(j).period;
                data[j][1]= entry.getValue().entries.get(j).amount; // ANEH NIH MASA BEGINI, DI KALI DUA SIH?
                amountIPPinTable = entry.getValue().entries.get(j).amount;
                
                //if(jTable2 != null){data[j][2]= jTable2.getModel().getValueAt(j,2);}else{data[j][2]= new Boolean(false);}
                if(entry.getValue().entries.get(j).transactDetailIDs.size() > 0){
                        
//                    if(jTableIPP != null){
//                        data[j][2]=jTableIPP.getValueAt(j,2);
//                    }else{
                            data[j][2] = new Boolean(true);
                            if(entry.getValue().entries.get(j).transactDetailIDs.size()== 1){
                                data[j][3] = entry.getValue().entries.get(j).amount;
                                data[j][4] = 0f;
                                data[j][5] = 0f;
                                data[j][6] = 0f;
                            }else if(entry.getValue().entries.get(j).transactDetailIDs.size() > 1){
                                for(Long setTDetailIds:entry.getValue().entries.get(j).transactDetailIDs){
                                    System.out.println(setTDetailIds + " Set TDetailsID");
                                    if(Control.selectTDetail(TransactionDetail.Tipe.IPPTransaction, setTDetailIds).paymentMethod == TransactionDetail.PaymentMethod.CASH){
                                        data[j][3] = Control.selectTDetail(TransactionDetail.Tipe.IPPTransaction, setTDetailIds).amount;
                                    }
                                    if(Control.selectTDetail(TransactionDetail.Tipe.IPPTransaction, setTDetailIds).paymentMethod == TransactionDetail.PaymentMethod.IDD){
                                        data[j][4] = Control.selectTDetail(TransactionDetail.Tipe.IPPTransaction, setTDetailIds).amount;
                                    }
                                    if(Control.selectTDetail(TransactionDetail.Tipe.IPPTransaction, setTDetailIds).paymentMethod == TransactionDetail.PaymentMethod.BEASISWA){
                                        data[j][5] = Control.selectTDetail(TransactionDetail.Tipe.IPPTransaction, setTDetailIds).amount;
                                    }
                                    if(Control.selectTDetail(TransactionDetail.Tipe.IPPTransaction, setTDetailIds).paymentMethod == TransactionDetail.PaymentMethod.BEASISWA_COST){
                                        data[j][6] = Control.selectTDetail(TransactionDetail.Tipe.IPPTransaction, setTDetailIds).amount;
                                    }
                                }
                            }
//                    }
                }else{
//                    if(jTableIPP !=null){
//                        data[j][2]=jTableIPP.getValueAt(j,2);
//                    }else{
                        data[j][2] = new Boolean(false);
                        data[j][3] = 0f;
                        data[j][4] = 0f;
                        data[j][5] = 0f;
                        data[j][6] = 0f;
//                    }
                }
                
                ippFromDB.entries.add(entry.getValue().entries.get(j));
                //canEdit[j] = (entry.getValue().entries.get(j).transactDetailIDs.size() > 0);
                
                for(Long id : entry.getValue().entries.get(j).transactDetailIDs){
                    canEdit[j] = Control.selectTDetail(TransactionDetail.Tipe.IPPTransaction, id).settled;
                }
                
                
            }
            ippFromDB.id = entry.getValue().id;
            ippFromDB.noInduk = entry.getValue().noInduk;
            ippFromDB.chargedLevel = entry.getValue().chargedLevel;
            i++;
        }
        TableModel tm = new DefaultTableModel(data, columnNames){
            boolean[] chooseEdit = new boolean[]{false,false,true,true,true,true,true};
            
           @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false for editable
                if(!jDialogTransactionSummary.isVisible()){
                    if((Boolean)getValueAt(row, 2)){
                        if((column == 0 || column ==1)){
                            return column == 2;
                        //return false;
                        }
                        return !canEdit[row];
                    }
                    if((column == 0 && column ==1)|| column == 2){
                            //return column == 2;
                        return chooseEdit[column];
                    }

                    return false;
               }else{
                    return false;
                }
            }
            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }
       };
       
       return tm;
       }else{
           TableModel tm = new DefaultTableModel(data, columnNames);
           JOptionPane.showMessageDialog(rootPane, "IPP Belum Di Setting!\r\n");
           this.dispose();
           return null;
       }
       
       
    }
    private TableModel buildIPPSubmitTableModel(Profil profil, int tahun) throws SQLException, KasirException {
       String columnNames[] = {"Bulan", "Biaya IPP", "Check Box", "Tunai", "Iuran Dibayar Dimuka", "Beasiswa", "Beasiswa Prestasi"};
       Object[][] data = new Object[12][7];
       
       final boolean[] canEdit = new boolean [12];
         
        for(int i = 0 ; i < ippCurrent.entries.size(); i++){
                data[i][0]= namaBulan[i];//entry.getValue().entries.get(j).period;
                data[i][1]= ippFromDB.entries.get(i).amount; // ANEH NIH MASA BEGINI, DI KALI DUA SIH?
                if(ippCurrent.entries.get(i) != null){
//                    if(jTableIPP != null){
//                        data[j][2]=jTableIPP.getValueAt(j,2);
//                    }else{
                        data[i][2] = new Boolean(true);
                        data[i][3] = ippFromDB.entries.get(i).amount-(iDDAmounts.get(i)+beasiswaAmounts.get(i)+beasiswaCostAmounts.get(i));
                        data[i][4] = iDDAmounts.get(i);
                        data[i][5] = beasiswaAmounts.get(i);
                        data[i][6] = beasiswaCostAmounts.get(i);
//                    }
                }else{
//                    if(jTableIPP !=null){
//                        data[j][2]=jTableIPP.getValueAt(j,2);
//                    }else{
                        data[i][2] = new Boolean(false);
                        data[i][3] = ippFromDB.entries.get(i).amount-(iDDAmounts.get(i)+beasiswaAmounts.get(i)+beasiswaCostAmounts.get(i));
                        data[i][4] = iDDAmounts.get(i);
                        data[i][5] = beasiswaAmounts.get(i);
                        data[i][6] = beasiswaCostAmounts.get(i);
//                    }
                }
       }
       
       TableModel tm = new DefaultTableModel(data, columnNames){
            boolean[] chooseEdit = new boolean[]{false,false,true};
            
           @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false for editable
                if(!jDialogTransactionSummary.isVisible()){
                    if((Boolean)getValueAt(row, 2)){
                        if((column == 0 || column ==1)){
                            return column == 2;
                        //return false;
                        }
                        return !canEdit[row];
                    }
                    if((column == 0 && column ==1)|| column == 2){
                            //return column == 2;
                        return chooseEdit[column];
                    }

                    return false;
               }else{
                    return false;
                }
            }
            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }
       };
       
       return tm;


    }
    
    private float calculateUnpaidIPP(Profil profil, List<Integer> tahuns) throws SQLException, KasirException {
       Set<IPP> ippFilters = new HashSet<>();
       float retVal = 0;
       ArrayList<Entry> entries = new ArrayList<>();
       ippFilters.clear();
       int targetMonth = Calendar.getInstance().get(Calendar.MONTH);
       int targetYear = Calendar.getInstance().get(Calendar.YEAR);
       if(targetMonth>5){//JULY - DECEMBER
           targetMonth = targetMonth - 5;
       }else{//JANUARY - JUNE
           targetMonth = targetMonth + 7;
           targetYear = targetYear - 1;
       }
       System.out.println("Month INT: "+Calendar.getInstance().get(Calendar.MONTH));
       for(int i = 0; i< tahuns.size(); i++){
           ippFilters.add(new IPP(profil.noInduk, new Level(null,null,null,tahuns.get(i)), entries));
           
        }
       Map<Long, IPP> searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.IPP, ippFilters);
        if(searchResultMap.size() > 0){
            for(Map.Entry<Long, IPP> entry: searchResultMap.entrySet()){
                for(int j =0 ; j< entry.getValue().entries.size(); j++){
                    if((entry.getValue().entries.get(j).transactDetailIDs.size() == 0) 
                            && ((entry.getValue().chargedLevel.tahun < targetYear)? true:(entry.getValue().entries.get(j).period <= targetMonth))){
                            retVal += entry.getValue().entries.get(j).amount;
                    }
                }
            }
        }
       unpaidIPP = retVal;
       return retVal;
    }
    
    private ArrayList<TunggakanBean> calculateUnpaidIPPs(ArrayList<Profil> profils, List<Integer> tahuns) throws SQLException, KasirException {
       Set<IPP> ippFilters = new HashSet<>();
       ArrayList<TunggakanBean> retVals = new ArrayList<>();
       float retVal = 0;
       ArrayList<Entry> entries = new ArrayList<>();
       ippFilters.clear();
       int targetMonth = Calendar.getInstance().get(Calendar.MONTH);
       int targetYear = Calendar.getInstance().get(Calendar.YEAR);
       if(targetMonth>5){//JULY - DECEMBER
           targetMonth = targetMonth - 5;
       }else{//JANUARY - JUNE
           targetMonth = targetMonth + 7;
           targetYear = targetYear - 1;
       }
       System.out.println("Month INT: "+Calendar.getInstance().get(Calendar.MONTH));
       ArrayList<String> noInduks = new ArrayList<>();
       for(int i = 0; i< profils.size() ; i++){
           noInduks.add(profils.get(i).noInduk);
            for(int j = 0; j< tahuns.size(); j++){
                ippFilters.add(new IPP(profils.get(i).noInduk, new Level(null,null,null,tahuns.get(j)), entries));

            }
       }
       List<IPP> test = Control.selectIurans(Iuran.Tipe.IPP, IPP.noIndukColName, false, noInduks.toArray(new String[0]));
       
       for(int i = 0 ; i< test.size(); i++){
           TunggakanBean tb = new TunggakanBean();
           for(int j = 0 ; j < test.get(i).entries.size(); j++){
               if(j==0) retVal=0;
               if((test.get(i).entries.get(j).transactDetailIDs.size() == 0 )
                   && ((test.get(i).chargedLevel.tahun < targetYear? true: (test.get(i).entries.get(j).period <= targetMonth)))){
                    
                            
                            retVal += test.get(i).entries.get(j).amount;
                            tunggakanIPPString = tunggakanIPPString.concat("IPP Bulan ");
                            tunggakanIPPString = tunggakanIPPString.concat(getMonthName(test.get(i).entries.get(j).period));
                            tunggakanIPPString = tunggakanIPPString.concat(" "+String.valueOf(test.get(i).chargedLevel.tahun)+"/"+String.valueOf(1+test.get(i).chargedLevel.tahun));
                            tunggakanIPPString = tunggakanIPPString.concat("\r\n");
                            
                            tunggakanIPPEachAmountString = tunggakanIPPEachAmountString.concat("Rp. ");
                            tunggakanIPPEachAmountString = tunggakanIPPEachAmountString.concat(String.valueOf(Math.round(test.get(i).entries.get(j).amount)));
                            tunggakanIPPEachAmountString = tunggakanIPPEachAmountString.concat("\r\n");
               }
                
               
               
           }
           tb.setProfil(profils.get(i));
           tb.setTunggakanIPP(retVal);
           retVals.add(tb);
           System.out.println(test.get(i).noInduk);
       }
//       Map<Long, IPP> searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.IPP, ippFilters);
//        if(searchResultMap.size() > 0){
//            for(Map.Entry<Long, IPP> entry: searchResultMap.entrySet()){
//                for(int j =0 ; j< entry.getValue().entries.size(); j++){
//                    if((entry.getValue().entries.get(j).transactDetailIDs.size() == 0) 
//                            && ((entry.getValue().chargedLevel.tahun < targetYear)? true:(entry.getValue().entries.get(j).period <= targetMonth))){
//                            TunggakanBean temp = new TunggakanBean();
//                            
//                            retVal += entry.getValue().entries.get(j).amount;
//                            retVals.add(temp);
//                            System.out.println(retVal);
//                    }
//                }
//            }
//        }
       unpaidIPP = retVal;
       return retVals;
    }
    
    //BAGIAN IKS
    
    private ComboBoxModel buildIKStahunComboBoxModel(Profil profil) throws SQLException, KasirException {
       Set<IKS> iksFilters = new HashSet<>();
       ArrayList<Entry> entries = new ArrayList();
       iksFilters.add(new IKS(profil.noInduk, null, entries));
       Map<Long, IKS> searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.IKS, iksFilters);
       this.tahunIKS = new ArrayList<>();
       List<String> tahunAjaran = new ArrayList<>();
       Object[][] data = new Object[searchResultMap.size()][2];
       int i = 0;
       if(searchResultMap.size() > 0){
        for(Map.Entry<Long, IKS> entry: searchResultMap.entrySet()){
            data[i][0]= entry.getValue().entries.get(0);
            data[i][1]= entry.getValue().entries.get(0).amount;
            
            this.tahunIKS.add(entry.getValue().chargedLevel.tahun);
            tahunAjaran.add(String.valueOf(entry.getValue().chargedLevel.tahun).concat(" - ").concat(String.valueOf(entry.getValue().chargedLevel.tahun+1)));
            i++;
        }
       }
       calculateUnpaidIKS(profil, tahunIKS);
       //== bikin tunggakan beans ===
       
       
       //== end bikin tunggakan beans ===
       
       tahunComboBoxModel = new DefaultComboBoxModel(tahunAjaran.toArray());
       return tahunComboBoxModel;
    }

    private TableModel buildIKSTableModel(Profil profil, int tahun) throws SQLException, KasirException {
       String columnNames[] = {"Tahun", "Biaya IKS", "Check Box", "Tunai", "Iuran Dibayar Dimuka", "Beasiswa", "Beasiswa Prestasi"};
       Set<IKS> iksFilters = new HashSet<>();
       ArrayList<Entry> entries = new ArrayList<>();
       iksFilters.clear();
       iksFilters.add(new IKS(profil.noInduk, new Level(null,null,null,tahun), entries));
       Map<Long, IKS> searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.IKS, iksFilters);
       Object[][] data = new Object[1][7];
       int i = 0;
       final boolean[] canEdit = new boolean [1];
       iksFromDB = new IKS();
       float amountIKSinTable;
       float amountIKSinTDetail;
       if(searchResultMap.size() > 0){
        for(Map.Entry<Long, IKS> entry: searchResultMap.entrySet()){
            for(int j =0 ; j< entry.getValue().entries.size(); j++){
                data[j][0]= entry.getValue().chargedLevel.tahun;//entry.getValue().entries.get(j).period;
                data[j][1]= entry.getValue().entries.get(j).amount; // ANEH NIH MASA BEGINI, DI KALI DUA SIH?
                amountIKSinTable = entry.getValue().entries.get(j).amount;
                
                //if(jTable2 != null){data[j][2]= jTable2.getModel().getValueAt(j,2);}else{data[j][2]= new Boolean(false);}
                if(entry.getValue().entries.get(j).transactDetailIDs.size() > 0){
                        
//                    if(jTableIKS != null){
//                        data[j][2]=jTableIKS.getValueAt(j,2);
//                    }else{
                            data[j][2] = new Boolean(true);
                            if(entry.getValue().entries.get(j).transactDetailIDs.size()== 1){
                                data[j][3] = entry.getValue().entries.get(j).amount;
                                data[j][4] = 0f;
                                data[j][5] = 0f;
                                data[j][6] = 0f;
                            }else if(entry.getValue().entries.get(j).transactDetailIDs.size() > 1){
                                for(Long setTDetailIds:entry.getValue().entries.get(j).transactDetailIDs){
                                    System.out.println(setTDetailIds + " Set TDetailsID");
                                    if(Control.selectTDetail(TransactionDetail.Tipe.IKSTransaction, setTDetailIds).paymentMethod == TransactionDetail.PaymentMethod.CASH){
                                        data[j][3] = Control.selectTDetail(TransactionDetail.Tipe.IKSTransaction, setTDetailIds).amount;
                                    }
                                    if(Control.selectTDetail(TransactionDetail.Tipe.IKSTransaction, setTDetailIds).paymentMethod == TransactionDetail.PaymentMethod.IDD){
                                        data[j][4] = Control.selectTDetail(TransactionDetail.Tipe.IKSTransaction, setTDetailIds).amount;
                                    }
                                    if(Control.selectTDetail(TransactionDetail.Tipe.IKSTransaction, setTDetailIds).paymentMethod == TransactionDetail.PaymentMethod.BEASISWA){
                                        data[j][5] = Control.selectTDetail(TransactionDetail.Tipe.IKSTransaction, setTDetailIds).amount;
                                    }
                                    if(Control.selectTDetail(TransactionDetail.Tipe.IKSTransaction, setTDetailIds).paymentMethod == TransactionDetail.PaymentMethod.BEASISWA_COST){
                                        data[j][6] = Control.selectTDetail(TransactionDetail.Tipe.IKSTransaction, setTDetailIds).amount;
                                    }
                                }
                            }
//                    }
                }else{
//                    if(jTableIKS !=null){
//                        data[j][2]=jTableIKS.getValueAt(j,2);
//                    }else{
                        data[j][2] = new Boolean(false);
                        data[j][3] = 0f;
                        data[j][4] = 0f;
                        data[j][5] = 0f;
                        data[j][6] = 0f;
//                    }
                }
                
                iksFromDB.entries.add(entry.getValue().entries.get(j));
                //canEdit[j] = (entry.getValue().entries.get(j).transactDetailIDs.size() > 0);
                
                for(Long id : entry.getValue().entries.get(j).transactDetailIDs){
                    canEdit[j] = Control.selectTDetail(TransactionDetail.Tipe.IKSTransaction, id).settled;
                }
                
                
            }
            iksFromDB.id = entry.getValue().id;
            iksFromDB.noInduk = entry.getValue().noInduk;
            iksFromDB.chargedLevel = entry.getValue().chargedLevel;
            i++;
        }
        TableModel tm = new DefaultTableModel(data, columnNames){
            boolean[] chooseEdit = new boolean[]{false,false,true,true,true,true,true};
            
           @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false for editable
                if(!jDialogTransactionSummary.isVisible()){
                    if((Boolean)getValueAt(row, 2)){
                        if((column == 0 || column ==1)){
                            return column == 2;
                        //return false;
                        }
                        return !canEdit[row];
                    }
                    if((column == 0 && column ==1)|| column == 2){
                            //return column == 2;
                        return chooseEdit[column];
                    }

                    return false;
               }else{
                    return false;
                }
            }
            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }
       };
       
       return tm;
       }else{
           TableModel tm = new DefaultTableModel(data, columnNames);
           JOptionPane.showMessageDialog(rootPane, "IKS Belum Di Setting!\r\n");
           this.dispose();
           return null;
       }
       
       
    }
    private TableModel buildIKSSubmitTableModel(Profil profil, int tahun) throws SQLException, KasirException {
       String columnNames[] = {"Tahun", "Biaya IKS", "Check Box", "Tunai", "Iuran Dibayar Dimuka", "Beasiswa", "Beasiswa Prestasi"};
       Object[][] data = new Object[1][7];
       
       final boolean[] canEdit = new boolean [1];
         
        for(int i = 0 ; i < iksCurrent.entries.size(); i++){
                data[i][0]= iksFromDB.chargedLevel.tahun;//entry.getValue().entries.get(j).period;
                data[i][1]= iksFromDB.entries.get(i).amount; // ANEH NIH MASA BEGINI, DI KALI DUA SIH?
                if(iksCurrent.entries.get(i) != null){
//                    if(jTableIKS != null){
//                        data[j][2]=jTableIKS.getValueAt(j,2);
//                    }else{
                        data[i][2] = new Boolean(true);
                        data[i][3] = iksFromDB.entries.get(i).amount-(iDDAmounts.get(i)+beasiswaAmounts.get(i)+beasiswaCostAmounts.get(i));
                        data[i][4] = iDDAmounts.get(i);
                        data[i][5] = beasiswaAmounts.get(i);
                        data[i][6] = beasiswaCostAmounts.get(i);
//                    }
                }else{
//                    if(jTableIKS !=null){
//                        data[j][2]=jTableIKS.getValueAt(j,2);
//                    }else{
                        data[i][2] = new Boolean(false);
                        data[i][3] = iksFromDB.entries.get(i).amount-(iDDAmounts.get(i)+beasiswaAmounts.get(i)+beasiswaCostAmounts.get(i));
                        data[i][4] = iDDAmounts.get(i);
                        data[i][5] = beasiswaAmounts.get(i);
                        data[i][6] = beasiswaCostAmounts.get(i);
//                    }
                }
       }
       
       TableModel tm = new DefaultTableModel(data, columnNames){
            boolean[] chooseEdit = new boolean[]{false,false,true};
            
           @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false for editable
                if(!jDialogTransactionSummary.isVisible()){
                    if((Boolean)getValueAt(row, 2)){
                        if((column == 0 || column ==1)){
                            return column == 2;
                        //return false;
                        }
                        return !canEdit[row];
                    }
                    if((column == 0 && column ==1)|| column == 2){
                            //return column == 2;
                        return chooseEdit[column];
                    }

                    return false;
               }else{
                    return false;
                }
            }
            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }
       };
       
       return tm;


    }
    
    private float calculateUnpaidIKS(Profil profil, List<Integer> tahuns) throws SQLException, KasirException {
       Set<IKS> iksFilters = new HashSet<>();
       float retVal = 0;
       ArrayList<Entry> entries = new ArrayList<>();
       iksFilters.clear();
       int targetMonth = Calendar.getInstance().get(Calendar.MONTH);
       int targetYear = Calendar.getInstance().get(Calendar.YEAR);
       if(targetMonth>5){//JULY - DECEMBER
           targetMonth = targetMonth - 5;
       }else{//JANUARY - JUNE
           targetMonth = targetMonth + 7;
           targetYear = targetYear - 1;
       }
       System.out.println("Month INT: "+Calendar.getInstance().get(Calendar.MONTH));
       for(int i = 0; i< tahuns.size(); i++){
           iksFilters.add(new IKS(profil.noInduk, new Level(null,null,null,tahuns.get(i)), entries));
           
        }
       Map<Long, IKS> searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.IKS, iksFilters);
        if(searchResultMap.size() > 0){
            for(Map.Entry<Long, IKS> entry: searchResultMap.entrySet()){
                for(int j =0 ; j< entry.getValue().entries.size(); j++){
                    if((entry.getValue().entries.get(j).transactDetailIDs.size() == 0) 
                            && ((entry.getValue().chargedLevel.tahun < targetYear)? true:(entry.getValue().entries.get(j).period <= targetMonth))){
                            retVal += entry.getValue().entries.get(j).amount;
                    }
                }
            }
        }
       unpaidIKS = retVal;
       return retVal;
    }
    
    private ArrayList<TunggakanBean> calculateUnpaidIKSs(ArrayList<Profil> profils, List<Integer> tahuns) throws SQLException, KasirException {
       Set<IKS> iksFilters = new HashSet<>();
       ArrayList<TunggakanBean> retVals = new ArrayList<>();
       float retVal = 0;
       ArrayList<Entry> entries = new ArrayList<>();
       iksFilters.clear();
       int targetMonth = Calendar.getInstance().get(Calendar.MONTH);
       int targetYear = Calendar.getInstance().get(Calendar.YEAR);
       if(targetMonth>5){//JULY - DECEMBER
           targetMonth = targetMonth - 5;
       }else{//JANUARY - JUNE
           targetMonth = targetMonth + 7;
           targetYear = targetYear - 1;
       }
       System.out.println("Month INT: "+Calendar.getInstance().get(Calendar.MONTH));
       ArrayList<String> noInduks = new ArrayList<>();
       for(int i = 0; i< profils.size() ; i++){
           noInduks.add(profils.get(i).noInduk);
            for(int j = 0; j< tahuns.size(); j++){
                iksFilters.add(new IKS(profils.get(i).noInduk, new Level(null,null,null,tahuns.get(j)), entries));

            }
       }
       List<IKS> test = Control.selectIurans(Iuran.Tipe.IKS, IKS.noIndukColName, false, noInduks.toArray(new String[0]));
       
       for(int i = 0 ; i< test.size(); i++){
           TunggakanBean tb = new TunggakanBean();
           for(int j = 0 ; j < test.get(i).entries.size(); j++){
               if(j==0) retVal=0;
               if((test.get(i).entries.get(j).transactDetailIDs.size() == 0 )
                   && ((test.get(i).chargedLevel.tahun < targetYear? true: (test.get(i).entries.get(j).period <= targetMonth)))){
                    
                            
                            retVal += test.get(i).entries.get(j).amount;
                            tunggakanIKSString = tunggakanIKSString.concat("IKS Bulan ");
                            tunggakanIKSString = tunggakanIKSString.concat(getMonthName(test.get(i).entries.get(j).period));
                            tunggakanIKSString = tunggakanIKSString.concat(" "+String.valueOf(test.get(i).chargedLevel.tahun)+"/"+String.valueOf(1+test.get(i).chargedLevel.tahun));
                            tunggakanIKSString = tunggakanIKSString.concat("\r\n");
                            
                            tunggakanIKSEachAmountString = tunggakanIKSEachAmountString.concat("Rp. ");
                            tunggakanIKSEachAmountString = tunggakanIKSEachAmountString.concat(String.valueOf(Math.round(test.get(i).entries.get(j).amount)));
                            tunggakanIKSEachAmountString = tunggakanIKSEachAmountString.concat("\r\n");
               }
                
               
               
           }
           tb.setProfil(profils.get(i));
           tb.setTunggakanIKS(retVal);
           retVals.add(tb);
           System.out.println(test.get(i).noInduk);
       }
//       Map<Long, IKS> searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.IKS, iksFilters);
//        if(searchResultMap.size() > 0){
//            for(Map.Entry<Long, IKS> entry: searchResultMap.entrySet()){
//                for(int j =0 ; j< entry.getValue().entries.size(); j++){
//                    if((entry.getValue().entries.get(j).transactDetailIDs.size() == 0) 
//                            && ((entry.getValue().chargedLevel.tahun < targetYear)? true:(entry.getValue().entries.get(j).period <= targetMonth))){
//                            TunggakanBean temp = new TunggakanBean();
//                            
//                            retVal += entry.getValue().entries.get(j).amount;
//                            retVals.add(temp);
//                            System.out.println(retVal);
//                    }
//                }
//            }
//        }
       unpaidIKS = retVal;
       return retVals;
    }
    
    private float calculateUnpaidSeragam(Profil profil) throws SQLException, KasirException {
       float retVal = 0;
       Set<Seragam> seragamFilters = new HashSet<>();
       seragamFilters.add(new Seragam(profil.noInduk,null, null, 0F, null));
       Map<Long, Seragam> searchResultMap = new HashMap<>();
       
       searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.Seragam, seragamFilters);
       seragamS = new ArrayList<>();       
       if(searchResultMap.size() > 0){
        for(Map.Entry<Long, Seragam> entry: searchResultMap.entrySet()){
            if(entry.getValue().transactDetailIDs.size()== 0){
                
                        seragamS.add(entry.getValue());
                    
                
            }
        }
       }
       for(int i =0; i < seragamS.size(); i++){
                retVal += seragamS.get(i).amount;
       }
       unpaidSeragam = retVal;
       return retVal;
    }
    
    private float calculateUnpaidBuku(Profil profil) throws SQLException, KasirException {
       float retVal = 0;
       Set<Buku> bukuFilters = new HashSet<>();
       bukuFilters.add(new Buku(profil.noInduk,null, null, 0F, null));
       Map<Long, Buku> searchResultMap = new HashMap<>();
       
       searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.Buku, bukuFilters);
       bukuS = new ArrayList<>();       
       if(searchResultMap.size() > 0){
        for(Map.Entry<Long, Buku> entry: searchResultMap.entrySet()){
            if(entry.getValue().transactDetailIDs.size()== 0){
                
                        bukuS.add(entry.getValue());
                    
                
            }
        }
       }
       for(int i =0; i < bukuS.size(); i++){
                retVal += bukuS.get(i).amount;
       }
       unpaidBuku = retVal;
       return retVal;
    }
    
    private boolean validatePanel(){
        return !validationPanel.isFatalProblem();
    }
    
    private String getMonthName(int p){
        String ret;
        switch(p){
            case 1: ret = "JULI";
                break;
            case 2: ret = "AGUSTUS";
                break;
            case 3: ret = "SEPTEMBER";
                break;
            case 4: ret = "OKTOBER";
                break;
            case 5: ret = "NOPEMBER";
                break;
            case 6: ret = "DESEMBER";
                break;
            case 7: ret = "JANUARI";
                break;
            case 8: ret = "FEBRUARI";
                break;
            case 9: ret = "MARET";
                break;
            case 10: ret = "APRIL";
                break;
            case 11: ret = "MEI";
                break;
            case 12: ret = "JUNI";
                break;
            default: ret = "Invalid";
                break;
        }
        return ret;
        
    }

    private void printBuktiPembayaran(float paramTotalAmount) throws JRException, PrinterException {
        // connection is the data source we used to fetch the data from
        ArrayList<Profil> temp = new ArrayList<>();
        temp.add(profil);
        try {
            calculateUnpaidIPPs(temp, tahunIPP);
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        } catch (KasirException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        
        String tunggakanIPP = tunggakanIPPString;
        float tunggakanIPPAmount = 0.0f;
        
        
        
        KelasTerbilang totalAmountTerbilang = new KelasTerbilang();
        totalAmountTerbilang.setNum(Math.round(paramTotalAmount));
        String totalATString = totalAmountTerbilang.toString().toUpperCase();
           
        
        printout.BuktiPembayaran pb = new BuktiPembayaran();
        Connection connection = pb.establishConnection(); 
        // jasperParameter is a Hashmap contains the parameters
        // passed from application to the jrxml layout
        HashMap jasperParameter = new HashMap();
        jasperParameter.put("Param_TransactionSummary_ID", ((Long)transactionSummary.id).intValue());
        jasperParameter.put("Param_Clerk_ID", transactionSummary.idClerk);
        jasperParameter.put("Param_Profil_ID", profil.noInduk);
        jasperParameter.put("SUBREPORT_DIR", "C://printout//");
        jasperParameter.put("Param_TotalAmountTerbilang", totalATString);
        
        jasperParameter.put("Param_Tunggakan_IPP", tunggakanIPP);
        jasperParameter.put("Param_Tunggakan_IPP_Amount", unpaidIPP);
        jasperParameter.put("Param_Tunggakan_IPP_Each_Amount", tunggakanIPPEachAmountString);
        //dari HKD
        
        String fileName = "C://printout//PrintoutBuktiPembayaran.jrxml";
            String filetoPrint = "C://printout//PrintoutBuktiPembayaran.jrprint";
            String filetoFill = "C://printout//PrintoutBuktiPembayaran.jasper";
            String filePdf = "C://printout//PrintoutBuktiPembayaran.pdf";
            
            //JasperCompileManager always make file named: "report name.jasper"
            JasperCompileManager.compileReportToFile(fileName);
            
            
            JasperFillManager.fillReportToFile(filetoFill, jasperParameter , connection);
            JasperPrint jp = JasperFillManager.fillReport(filetoFill, jasperParameter, connection);
            JasperViewer.viewReport(jp, false);
            JasperExportManager.exportReportToPdfFile(jp, filePdf);
            JasperPrintManager.printReport(filetoPrint, true);
        
        //end dari HKD
        
        
        
        // jrxml compiling process
//        jasperReport = JasperCompileManager.compileReport
//        ("C://Users//Master//Documents//NetBeansProjects//Kasir7//src//printout//PrintoutBuktiPembayaran.jrxml");
        jasperReport = JasperCompileManager.compileReport
        ("C://printout//PrintoutBuktiPembayaran.jrxml");

        // filling report with data from data source

        jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection); 
        // exporting process
        // 1- export to PDF
//        JasperExportManager.exportReportToPdfFile(jasperPrint, "F://sample_report.pdf");
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C://printout//sample_report.pdf");

        // 2- export to HTML
//        JasperExportManager.exportReportToHtmlFile(jasperPrint, "F://sample_report.html" ); 
        JasperExportManager.exportReportToHtmlFile(jasperPrint, "C://printout//sample_report.html" ); 

        // 3- export to Excel sheet
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        //exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "F://sample_report.xls" );
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "C://printout//sample_report.xls" );

        exporter.exportReport();
        
//        PrinterJob job = PrinterJob.getPrinterJob();
//        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
//        printRequestAttributeSet.add(OrientationRequested.LANDSCAPE);
//        printRequestAttributeSet.add(MediaSizeName.ISO_A5); 
//        MediaSizeName mediaSizeName = MediaSize.findMedia(64,25,MediaPrintableArea.MM);
//        printRequestAttributeSet.add(mediaSizeName);
//        printRequestAttributeSet.add(new Copies(1));
//        JRPrintServiceExporter exporter1;
//        exporter1 = new JRPrintServiceExporter();
//        //jasperParameter.put(JRExporterParameter.JASPER_PRINT, jasperPrint);
//        jasperParameter.put(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
//        jasperParameter.put(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
//        jasperParameter.put(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
//        jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection); 
//        jasperParameter.put(JRExporterParameter.JASPER_PRINT, jasperPrint);
//        exporter1.setParameters(jasperParameter);
//        //command print dialog with java
//        exporter1.exportReport();
//        job.print(printRequestAttributeSet);
    }
    
    private void printTunggakanPerKelas() {
        ArrayList<Float> tunggakanIPP = new ArrayList<>();
        List<Integer> tahuns = new ArrayList<>();
        List<TunggakanBean> tunggakanBeans = new ArrayList<TunggakanBean>();
        ArrayList<Profil> profils = new ArrayList<>();
        TunggakanBeanFactory tbf = new TunggakanBeanFactory();
        tbf.clearTunggakanBean();
        tahuns.add(profil.currentLevel.tahun);
        try {
            profils = Control.selectProfils(profil.currentLevel.toString());
            tunggakanBeans = calculateUnpaidIPPs(profils, tahuns);
            for(int i = 0; i< tunggakanBeans.size(); i++){
                tbf.addTunggakanBean(tunggakanBeans.get(i));
            }
//            for(int i = 0; i< profils.size();i++){
//                tunggakanIPP.add(calculateUnpaidIPP(profils.get(i), tahuns));
//                System.out.println(tunggakanIPP.get(i));
//                System.out.println("+++^^^+++ TUNGGAKAN SI  ".concat(profils.get(i).biodata.nama));
//                TunggakanBean rb = new TunggakanBean();
//                rb.setProfil(profils.get(i));
//                rb.setTunggakanIPP(tunggakanIPP.get(i));
//                tunggakanBeans.add(rb);
//                tbf.addTunggakanBean(rb);
//            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        } catch (KasirException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        
        try{
            String fileName = "C://printout//PrintOutTunggakanPerKelas.jrxml";
            String filePdf = "C://Report_Tunggakan_Kelas".concat(profil.currentLevel.toString()).concat(".pdf");
            InputStream inputStream = new FileInputStream (fileName);
            Collection col = TunggakanBeanFactory.getBeanCollection();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(col);
            JasperCompileManager.compileReportToFile(fileName);
            Map param = new HashMap();
            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, param, dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint, filePdf); 
            //JasperExportManager.exportReportToPdf(jasperPrint);
            
            PrinterJob job = PrinterJob.getPrinterJob();
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            printRequestAttributeSet.add(OrientationRequested.LANDSCAPE);
            printRequestAttributeSet.add(MediaSizeName.ISO_A4); 
            MediaSizeName mediaSizeName = MediaSize.findMedia(64,25,MediaPrintableArea.MM);
            printRequestAttributeSet.add(mediaSizeName);
            printRequestAttributeSet.add(new Copies(1));
            JRPrintServiceExporter exporter1;
            exporter1 = new JRPrintServiceExporter();
            HashMap jasperParameter = new HashMap();
            
            param.put(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
            param.put(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
            param.put(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);
            //jasperPrint = JasperFillManager.fillReport(jasperReport,param, dataSource); 
            param.put(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter1.setParameters(param);
            exporter1.exportReport();
            job.print(printRequestAttributeSet);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, "Error!\r\n".concat(ex.toString()));
            System.err.println();
        }
    }
            
}
