/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.lowagie.text.Font;
import iuran.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import kasir.Clerk;
import kasir.Control;
import pelajar.Profil;
import sak.Kalender;
import sak.KasirException;
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
import javax.swing.*;
import kasir.DBSR;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import printout.BuktiPembayaran;
import printout.TunggakanBean;
import printout.TunggakanBeanFactory;

/**
 *
 * @author Master
 */
public class InputTransactionFrameSeparated extends javax.swing.JFrame {
    
    private AppFrame appFrame;
    private AppFramePendaftaran appFramePendaftaran;
    //Tunggakan Iuran-iuran
    
    private ArrayList<IPP> tIPPs;
    private ArrayList<IUAP> tIUAPs;
    private ArrayList<CicilanHutang> tCicilanHutangs;
    private ArrayList<Seragam> tSeragams;
    private ArrayList<Almamater> tAlmamaters;
    private ArrayList<Buku> tBukus;
    private ArrayList<IKS> tIKSs;
    private ArrayList<ILL> tILLs;
    private IPSB tIPSB;
    private IUA tIUA;
    private IPSP tIPSP;
    private PASB tPASB;
    private ArrayList<IUS> tIUSs;
    private ArrayList<OSIS> tOSISs;
    private ArrayList<Attribute> tAttributes;
    private ArrayList<PVT> tPVTs;
    private ArrayList<Tabungan> tTabungans;
    private ArrayList<Sumbangan> tSumbangans;
    
    
    //IPP PART
    private InputTransactionIPP inputTransactionIPP;
    private TableModel tableModelIPP;
    public IPP ippFromDB;
    public IPP ippCurrent;
    public IPP ippStoreToDB;
    private ArrayList<Float> iPPAmounts;
    private UUID ippTDetailUUID;
    private float unpaidIPP = 0f;
    private List<Integer> tahunIPP;
    private String tunggakanIPPString = new String("");
    private String tunggakanIPPEachAmountString = new String("");
    
    //IUAP PART
    private InputTransactionIUAP inputTransactionIUAP;
    private TableModel tableModelIUAP;
    public IUAP iuapFromDB;
    public IUAP iuapCurrent;
    public IUAP iuapStoreToDB;
    private ArrayList<Float> iUAPAmounts;
    private UUID iuapTDetailUUID;
    private float unpaidIUAP = 0f;
    private List<Integer> tahunIUAP;
    private String tunggakanIUAPString = new String("");
    private String tunggakanIUAPEachAmountString = new String("");
    
    //CicilanHutang PART
    private InputTransactionCicilanHutang inputTransactionCicilanHutang;
    private TableModel tableModelCicilanHutang;
    public CicilanHutang cicilanHutangFromDB;
    public CicilanHutang cicilanHutangCurrent;
    public CicilanHutang cicilanHutangStoreToDB;
    private ArrayList<Float> cicilanHutangAmounts;
    private UUID cicilanHutangTDetailUUID;
    private float unpaidCicilanHutang = 0f;
    private List<Integer> tahunCicilanHutang;
    private String tunggakanCicilanHutangString = new String("");
    private String tunggakanCicilanHutangEachAmountString = new String("");
    
    //IPSP PART
    private InputTransactionIPSP inputTransactionIPSP;
    private UUID ipspTDetailUUID;
    private IPSPTransactionDetail iPSPTransactionDetail;
    public IPSP ipsp;
    public IPSP ipspFromDB;
    public IPSP ipspCurrent;
    public IPSP ipspStoreToDB;
    private float minIpsp;
    private float maxIpsp;
    
    //IPSB PART
    private InputTransactionIPSB inputTransactionIPSB;
    private UUID ipsbTDetailUUID;
    private IPSB ipsb;
    private IPSBTransactionDetail iPSBTransactionDetail;
    
    //IKS PART
    private TableModel tableModelIKS;
    private InputTransactionIKS inputTransactionIKS;
    public IKS iksFromDB;
    public IKS iksCurrent;
    public IKS iksStoreToDB;
    private UUID iksTDetailUUID;
    private float unpaidIKS = 0f;
    private List<Integer> tahunIKS;
    private String tunggakanIKSString = new String("");
    private String tunggakanIKSEachAmountString = new String("");
    
    //PVT PART
    private TableModel tableModelPVT;
    private InputTransactionPVT inputTransactionPVT;
    public PVT pvtFromDB;
    public PVT pvtCurrent;
    public PVT pvtStoreToDB;
    private UUID pvtTDetailUUID;
    private float unpaidPVT = 0f;
    private List<Integer> tahunPVT;
    private String tunggakanPVTString = new String("");
    private String tunggakanPVTEachAmountString = new String("");
    
    //OSIS PART
    private TableModel tableModelOSIS;
    private InputTransactionOSIS inputTransactionOSIS;
    public OSIS osisFromDB;
    public OSIS osisCurrent;
    public OSIS osisStoreToDB;
    private UUID osisTDetailUUID;
    private float unpaidOSIS = 0f;
    private List<Integer> tahunOSIS;
    private String tunggakanOSISString = new String("");
    private String tunggakanOSISEachAmountString = new String("");
    
    //Seragam PART
    private InputTransactionSeragam inputTransactionSeragam;
    private TableModel tableModelSeragam;
    private UUID seragamTDetailUUID;
    private SeragamTransactionDetail seragamTransactionDetail;
    private Seragam seragam;
    public List<Seragam> seragamS;
    public List<Seragam> seragamSToDB;
    public float unpaidSeragam = 0f;
    
    //Almamater PART
    private InputTransactionAlmamater inputTransactionAlmamater;
    private TableModel tableModelAlmamater;
    private UUID almamaterTDetailUUID;
    private AlmamaterTransactionDetail almamaterTransactionDetail;
    private Almamater almamater;
    public List<Almamater> almamaterS;
    public List<Almamater> almamaterSToDB;
    public float unpaidAlmamater = 0f;
    
    //Attribute PART
    private InputTransactionAttribute inputTransactionAttribute;
    private TableModel tableModelAttribute;
    private UUID attributeTDetailUUID;
    private AttributeTransactionDetail attributeTransactionDetail;
    private Attribute attribute;
    public List<Attribute> attributeS;
    public List<Attribute> attributeSToDB;
    public float unpaidAttribute = 0f;
    
    //Buku PART
    private InputTransactionBuku inputTransactionBuku;
    private TableModel tableModelBuku;
    private Buku buku;
    public List<Buku> bukuS;
    public List<Buku> bukuSToDB;
    private UUID bukuTDetailUUID;
    private BukuTransactionDetail bukuTransactionDetail;
    public float unpaidBuku = 0f;
    
    //ILL PART
    private InputTransactionILL inputTransactionILL;
    private TableModel tableModelILL;
    private ILLTransactionDetail illTransactionDetail;
    private ILL ill;
    public List<ILL> illS;
    public List<ILL> illSToDB;
    public float unpaidILL = 0f;
    private UUID illTDetailUUID;
    
    
    //IUA PART
    private InputTransactionIUA inputTransactionIUA;
    private UUID iuaTDetailUUID;
    private IUATransactionDetail iUATransactionDetail;
    private IUA iua;
    
    
    //IUS PART
    private InputTransactionIUS inputTransactionIUS;
    private TableModel tableModelIUS;
    public IUS iusFromDB;
    public IUS iusCurrent;
    public IUS iusStoreToDB;
    private UUID iusTDetailUUID;
    private float unpaidIUS = 0f;
    private List<Integer> tahunIUS;
    
    //Tabungan PART
    
    private InputTransactionTabungan inputTransactionTabungan;
    private TableModel tableModelTabungan;
    private Tabungan tabungan;
    public List<Tabungan> tabunganS;
    public List<Tabungan> tabunganSToDB;
    public float unpaidTabungan = 0f;
    private UUID tabunganTDetailUUID;
    private TabunganTransactionDetail tabunganTransactionDetail;
    
    
    //Sumbangan PART
    private InputTransactionSumbangan inputTransactionSumbangan;
    private TableModel tableModelSumbangan;
    private UUID sumbanganTDetailUUID;
    private Sumbangan sumbangan;
    public List<Sumbangan> sumbanganS;
    public List<Sumbangan> sumbanganSToDB;
    public float unpaidSumbangan = 0f;
    private SumbanganTransactionDetail sumbanganTransactionDetail;
    
    //PASB PART
    //private InputTransactionPASB inputTransactionPASB;
    private UUID pasbTDetailUUID;
    private PASB pasb;
    private PASBTransactionDetail pasbTransactionDetail;
    private float minPasb;
    private float maxPasb;
    
    //IDD PART
    private ArrayList<Float> iDDAmounts;
    public IDD idd;
    private IDDTransactionDetail iddTDetail;
    private float iddRequest = 0f;
    
    
    //Beasiswa PART
    private ArrayList<Float> beasiswaAmounts;
    private float beasiswaRequest = 0f;
    
    
    //Beasiswa Cost PART
    private ArrayList<Float> beasiswaCostAmounts;
    private float beasiswaCostRequest = 0f;
    //separated   ^^^
    
    private final ValidationPanel validationPanel = new ValidationPanel();
   
    private ComboBoxModel tahunComboBoxModel;
    private final String[] namaBulan = {"JULI", "AGUSTUS", "SEPTEMBER", "OKTOBER", "NOPEMBER", "DESEMBER", "JANUARI", "PEBRUARI", "MARET", "APRIL", "MEI", "JUNI"};
    private UUID tSumUUID;
    private UUID ikkTDetailUUID;
    
    private Profil profil;
    private Clerk clerk;
    private TransactionSummary transactionSummary;
    
    
    private Kalender theCreatedDate;
    
    private float totalAmount = 0f;
    
    /* JasperReport is the object
    that holds our compiled jrxml file */
    JasperReport jasperReport;


    /* JasperPrint is the object contains
    report after result filling process */
    JasperPrint jasperPrint;
    private TableModel allTransactionTableModel;
    private ArrayList<Transaction> transactionList;
    private TableModel tunggakanAllTahunTableModel;
    private JFormattedTextField jTotalTunggakanIPP;

    /**
     * Creates new form InputTransactionFrame
     */
    public InputTransactionFrameSeparated() {
        initComponents();
    }
    
    public InputTransactionFrameSeparated(Clerk cl, Profil profil) {
        this.clerk = cl;
        Clerk.current.id = this.clerk.id;
        this.profil = profil;
//        try {
//            this.ipsp = Control.selectIuran(Iuran.Tipe.IPSP, IPSP.noIndukColName, false, profil.noInduk);
//            minIpsp = 0F;
//            maxIpsp = this.ipsp.amount - this.ipsp.totalInstallment;
//        } catch (SQLException ex) {
//            Exceptions.printStackTrace(ex);
//            JOptionPane.showMessageDialog(rootPane, "IPSP Belum Di Setting!\r\n".concat(ex.toString()));
//            this.dispose();
//        } catch (KasirException ex) {
//            Exceptions.printStackTrace(ex);
//            JOptionPane.showMessageDialog(rootPane, "IPSP Belum Di Setting!\r\n".concat(ex.toString()));
//            this.dispose();
//        }
        initComponents();
    }
    
    public InputTransactionFrameSeparated(Clerk cl, Profil profil, IPSP paramIPSP) {
        this.clerk = cl;
        Clerk.current.id = this.clerk.id;
        this.profil = profil;
        this.ipsp = paramIPSP;
        initComponents();
    }
    
    public InputTransactionFrameSeparated(AppFrame af, Clerk cl, Profil profil, ArrayList<IPP> paramIPPs, IPSP paramIPSP, ArrayList<Seragam> paramSeragams, ArrayList<Buku> paramBukus, ArrayList<IKS> paramIKSs, ArrayList<ILL> paramILLs, IPSB paramIPSB, IUA paramIUA, ArrayList<IUS> paramIUSs, ArrayList<OSIS> paramOSISs, ArrayList<Attribute> paramAttributes, ArrayList<PVT> paramPVTs, ArrayList<Tabungan> paramTabungans, ArrayList<Sumbangan> paramSumbangans, PASB paramPASB, ArrayList<CicilanHutang> paramCicilanHutangs, ArrayList<Almamater> paramAlmamaters, ArrayList<IUAP> paramIUAPs) {
        this.appFrame = af;
        this.clerk = cl;
        Clerk.current.id = this.clerk.id;
        this.profil = profil;
        tIPPs = paramIPPs;
        tIUAPs = paramIUAPs;
        tCicilanHutangs = paramCicilanHutangs;
        tIPSP = paramIPSP;
        tSeragams = paramSeragams;
        tAlmamaters = paramAlmamaters;
        tBukus = paramBukus;
        tIKSs = paramIKSs;
        tILLs = paramILLs;
        tIPSB = paramIPSB;
        tIUA = paramIUA;
        tIUSs = paramIUSs;
        tOSISs = paramOSISs;
        tAttributes = paramAttributes;
        tPVTs = paramPVTs;
        tTabungans = paramTabungans;
        tSumbangans= paramSumbangans;
        tPASB = paramPASB;
//        if(DBSR.dbURL.equals("jdbc:mysql://ark3.dayarka.com/rusly_ppdbdb")){
//            System.out.println("PPDB");
//            
//        }else{
//            System.out.println("Kasir");
//        }
        
        initComponents();
    }
    
    public InputTransactionFrameSeparated(AppFramePendaftaran af, Clerk cl, Profil profil, ArrayList<IPP> paramIPPs, IPSP paramIPSP, ArrayList<Seragam> paramSeragams, ArrayList<Buku> paramBukus, ArrayList<IKS> paramIKSs, ArrayList<ILL> paramILLs, IPSB paramIPSB, IUA paramIUA, ArrayList<IUS> paramIUSs, ArrayList<OSIS> paramOSISs, ArrayList<Attribute> paramAttributes, ArrayList<PVT> paramPVTs, ArrayList<Tabungan> paramTabungans, ArrayList<Sumbangan> paramSumbangans, ArrayList<CicilanHutang> paramCicilanHutangs, ArrayList<Almamater> paramAlmamaters, ArrayList<IUAP> paramIUAPs) {
        this.appFramePendaftaran = af;
        this.clerk = cl;
        Clerk.current.id = this.clerk.id;
        this.profil = profil;
        tIPPs = paramIPPs;
        tIUAPs = paramIUAPs;
        tCicilanHutangs = paramCicilanHutangs;
        tIPSP = paramIPSP;
        tSeragams = paramSeragams;
        tBukus = paramBukus;
        tIKSs = paramIKSs;
        tILLs = paramILLs;
        tIPSB = paramIPSB;
        tIUA = paramIUA;
        tIUSs = paramIUSs;
        tOSISs = paramOSISs;
        tAttributes = paramAttributes;
        tPVTs = paramPVTs;
        tTabungans = paramTabungans;
        tSumbangans= paramSumbangans;
        tAlmamaters = paramAlmamaters;
        System.out.println("App Frame Pendaftara");
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
        jPanelDialogBuku = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTableDialogBuku = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        allTransactionTable = new javax.swing.JTable();
        jPanelDialogAlmamater = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTableDialogAlmamater = new javax.swing.JTable();
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
        jFrameCicilanHutang = new javax.swing.JFrame();
        jPanelCicilanHutang = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabelTahun1 = new javax.swing.JLabel();
        jComboBoxTahun1 = new javax.swing.JComboBox();
        jLabel36 = new javax.swing.JLabel();
        jUnpaidCicilanHutang = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTableCicilanHutang = new javax.swing.JTable();
        jButtonBayarCicilanHutang = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jFormattedTextFieldIDDSaldo2 = new javax.swing.JFormattedTextField();
        jFrameAlmamater = new javax.swing.JFrame();
        jPanelAlmamater = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTableAlmamater = new javax.swing.JTable();
        jLabel38 = new javax.swing.JLabel();
        jUnpaidAlmamater = new javax.swing.JTextField();
        jButtonBayarAlmamater = new javax.swing.JButton();
        jFrameIUAP = new javax.swing.JFrame();
        jPanelIUAP = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabelTahun4 = new javax.swing.JLabel();
        jComboBoxTahunIUAP = new javax.swing.JComboBox();
        jLabel41 = new javax.swing.JLabel();
        jUnpaidIUAP = new javax.swing.JTextField();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTableIUAP = new javax.swing.JTable();
        jButtonBayarIUAP = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jFormattedTextFieldIDDSaldo3 = new javax.swing.JFormattedTextField();
        DefaultComboBoxModel paymentMethodComboBoxModel = new DefaultComboBoxModel(TransactionDetail.PaymentMethod.values());
        tableModelSeragam = new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Seragam", "Jumlah", "Catatan", "CheckBox"
            }
        );

        tableModelAlmamater = new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Almamater", "Jumlah", "Catatan", "CheckBox"
            }
        );

        tableModelBuku = new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
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
        };

        tableModelIUAP = new javax.swing.table.DefaultTableModel(
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
                "Bulan", "Biaya IUAP"
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

        tableModelCicilanHutang = new javax.swing.table.DefaultTableModel(
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
                "Bulan", "Biaya Cicilan Hutang"
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

        tableModelIUS = new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"SEMESTER 1", null},
                {"SEMESTER 2", null}
            },
            new String [] {
                "Bulan", "Biaya IUS"
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

        tunggakanAllTahunTableModel = new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Tahun Pertama", null},
                {"Tahun Kedua", null},
                {"Tahun Ketiga", null}
            },
            new String [] {
                "Tahun", "Tunggakan"
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
            //tableModelIPP = inputTransactionIPP.buildIPPTableModel(profil, profil.currentLevel.tahun);
            tableModelIKS = buildIKSTableModel(profil, profil.currentLevel.tahun);
            tunggakanAllTahunTableModel = buildTunggakanAllTahunTableModel(profil);
            allTransactionTableModel = buildAllTransactionTableModel(new ArrayList<Transaction>());

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
        jButtonIBF = new javax.swing.JButton();
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
        jButtonAttribute = new javax.swing.JButton();
        jTextFieldAttributeAmountSimple = new javax.swing.JFormattedTextField();
        jButtonPVT = new javax.swing.JButton();
        jTextFieldPVTAmountSimple = new javax.swing.JFormattedTextField();
        jButtonTabungan = new javax.swing.JButton();
        jTextFieldTabunganAmountSimple = new javax.swing.JFormattedTextField();
        jButtonSumbangan = new javax.swing.JButton();
        jTextFieldSumbanganAmountSimple = new javax.swing.JFormattedTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableTunggakanAll = new javax.swing.JTable();
        jButtonCicilanHutang = new javax.swing.JButton();
        jTextFieldCicilanHutangAmountSimple = new javax.swing.JFormattedTextField();
        jButtonAlmamater = new javax.swing.JButton();
        jTextFieldAlmamaterAmountSimple = new javax.swing.JFormattedTextField();

        jDialogTransactionSummary.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jDialogTransactionSummary.setMinimumSize(new java.awt.Dimension(1024, 768));

        jPanelDialogMain.setMinimumSize(new java.awt.Dimension(1024, 768));
        jPanelDialogMain.setPreferredSize(new java.awt.Dimension(1024, 768));

        jLabel6.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel6.text")); // NOI18N

        jLabel7.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel7.text")); // NOI18N

        jTextFieldIPSPAmount1.setEditable(false);
        jTextFieldIPSPAmount1.setFocusable(false);

        jLabel8.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel8.text")); // NOI18N

        jComboBoxDialogIPSP.setModel(paymentMethodComboBoxModel);
        jComboBoxDialogIPSP.setEnabled(false);
        jComboBoxDialogIPSP.setFocusable(false);
        jComboBoxDialogIPSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDialogIPSPActionPerformed(evt);
            }
        });

        jLabel9.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel9.text")); // NOI18N

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
                .addComponent(jTextFieldIPSPNote1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
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

        jLabel12.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel12.text")); // NOI18N

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

        jButtonSave1.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonSave1.text")); // NOI18N
        jButtonSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSave1ActionPerformed(evt);
            }
        });

        jButtonDialogCancel.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonDialogCancel.text")); // NOI18N
        jButtonDialogCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDialogCancelActionPerformed(evt);
            }
        });

        jLabel15.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel15.text")); // NOI18N

        jTextFieldTransactionSummaryNote1.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldTransactionSummaryNote1.text")); // NOI18N

        jLabel16.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel16.text")); // NOI18N

        jTextFieldTSumTotalAmount.setEditable(false);
        jTextFieldTSumTotalAmount.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextFieldTSumTotalAmount.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldTSumTotalAmount.text")); // NOI18N
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
        jLableProfilTitle1.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLableProfilTitle1.text")); // NOI18N

        jLabelNama1.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabelNama1.text")); // NOI18N

        jLabelLevel2Level4.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabelLevel2Level4.text")); // NOI18N

        jLabelNoInduk1.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabelNoInduk1.text")); // NOI18N

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

        jLabel31.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel31.text")); // NOI18N

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

        allTransactionTable.setAutoCreateRowSorter(true);
        allTransactionTable.setModel(allTransactionTableModel);
        jScrollPane1.setViewportView(allTransactionTable);

        jLabel39.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel39.text")); // NOI18N

        jTableDialogAlmamater.setModel(tableModelAlmamater);
        jTableDialogAlmamater.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableDialogAlmamaterPropertyChange(evt);
            }
        });
        jScrollPane14.setViewportView(jTableDialogAlmamater);

        javax.swing.GroupLayout jPanelDialogAlmamaterLayout = new javax.swing.GroupLayout(jPanelDialogAlmamater);
        jPanelDialogAlmamater.setLayout(jPanelDialogAlmamaterLayout);
        jPanelDialogAlmamaterLayout.setHorizontalGroup(
            jPanelDialogAlmamaterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogAlmamaterLayout.createSequentialGroup()
                .addGroup(jPanelDialogAlmamaterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDialogAlmamaterLayout.setVerticalGroup(
            jPanelDialogAlmamaterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogAlmamaterLayout.createSequentialGroup()
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addGroup(jPanelDialogMainLayout.createSequentialGroup()
                        .addGroup(jPanelDialogMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelDialogIPSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelDialogSeragam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(464, 464, 464)
                        .addComponent(jPanelDialogBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanelDialogMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelDialogMainLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanelDialogAlmamater, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1055, Short.MAX_VALUE)))
        );
        jPanelDialogMainLayout.setVerticalGroup(
            jPanelDialogMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDialogMainLayout.createSequentialGroup()
                .addGroup(jPanelDialogMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelTransactionSummary1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelDialogProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126)
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
            .addGroup(jPanelDialogMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDialogMainLayout.createSequentialGroup()
                    .addContainerGap(439, Short.MAX_VALUE)
                    .addComponent(jPanelDialogAlmamater, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(212, 212, 212)))
        );

        jPanelDialogMain1.setMinimumSize(new java.awt.Dimension(1024, 768));

        jLabel18.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel18.text")); // NOI18N

        jLabel22.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel22.text")); // NOI18N

        jTextFieldIPSPAmount2.setEditable(false);
        jTextFieldIPSPAmount2.setFocusable(false);

        jLabel23.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel23.text")); // NOI18N

        jComboBoxDialogIPSP1.setModel(paymentMethodComboBoxModel);
        jComboBoxDialogIPSP1.setEnabled(false);
        jComboBoxDialogIPSP1.setFocusable(false);
        jComboBoxDialogIPSP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDialogIPSP1ActionPerformed(evt);
            }
        });

        jLabel24.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel24.text")); // NOI18N

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

        jLabel25.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel25.text")); // NOI18N

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

        jButtonSave2.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonSave2.text")); // NOI18N
        jButtonSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSave2ActionPerformed(evt);
            }
        });

        jButtonDialogCancel1.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonDialogCancel1.text")); // NOI18N
        jButtonDialogCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDialogCancel1ActionPerformed(evt);
            }
        });

        jLabel26.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel26.text")); // NOI18N

        jTextFieldTransactionSummaryNote2.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldTransactionSummaryNote2.text")); // NOI18N

        jLabel27.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel27.text")); // NOI18N

        jTextFieldTSumTotalAmount1.setEditable(false);
        jTextFieldTSumTotalAmount1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextFieldTSumTotalAmount1.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldTSumTotalAmount1.text")); // NOI18N
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
        jLableProfilTitle2.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLableProfilTitle2.text")); // NOI18N

        jLabelNama2.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabelNama2.text")); // NOI18N

        jLabelLevel2Level5.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabelLevel2Level5.text")); // NOI18N

        jLabelNoInduk2.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabelNoInduk2.text")); // NOI18N

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

        jLabel28.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel28.text")); // NOI18N

        jTableIPP2.setModel(tableModelIPP);
        jScrollPane7.setViewportView(jTableIPP2);

        jLabelTahun2.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabelTahun2.text")); // NOI18N

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
        jLabel20.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel20.text")); // NOI18N

        jLabelTahun.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabelTahun.text")); // NOI18N

        try{
            tahunComboBoxModel = buildIPPtahunComboBoxModel(profil);
            System.out.println("lalala");
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

        jLabel21.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel21.text")); // NOI18N

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

        jButtonBayarIPP.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonBayarIPP.text")); // NOI18N
        jButtonBayarIPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBayarIPPActionPerformed(evt);
            }
        });

        jLabel2.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel2.text")); // NOI18N

        jFormattedTextFieldIDDSaldo.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jFormattedTextFieldIDDSaldo.text")); // NOI18N
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
                        .addGap(0, 126, Short.MAX_VALUE)))
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
                .addContainerGap(100, Short.MAX_VALUE))
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
        jFrameIDD.setName(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jFrameIDD.name")); // NOI18N

        jPanelIDD.setMinimumSize(new java.awt.Dimension(480, 390));
        jPanelIDD.setPreferredSize(new java.awt.Dimension(1024, 176));

        jLabelIDDTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelIDDTitle.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabelIDDTitle.text")); // NOI18N

        jLabelAmount3.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabelAmount3.text")); // NOI18N

        jTextFieldIDDAmount.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldIDDAmount.text")); // NOI18N
        jTextFieldIDDAmount.setName(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldIDDAmount.name")); // NOI18N
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

        jLabelNote2.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabelNote2.text")); // NOI18N

        jTextAreaIDDNote.setColumns(20);
        jTextAreaIDDNote.setRows(5);
        jScrollPane8.setViewportView(jTextAreaIDDNote);

        jLabelAmount4.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabelAmount4.text")); // NOI18N

        jTextFieldIDDTransactionName.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldIDDTransactionName.text")); // NOI18N
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

        jButtonIDDOK.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonIDDOK.text")); // NOI18N
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jLabel1.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel1.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel3.text")); // NOI18N

        jTextFieldIPSPAmount.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldIPSPAmount.text")); // NOI18N
        jTextFieldIPSPAmount.setName(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldIPSPAmount.name")); // NOI18N
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

        jLabel4.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel4.text")); // NOI18N

        jComboBoxIPSP.setModel(paymentMethodComboBoxModel);
        jComboBoxIPSP.setEnabled(false);
        jComboBoxIPSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxIPSPActionPerformed(evt);
            }
        });

        jLabel5.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel5.text")); // NOI18N

        jTextFieldIPSPNote.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldIPSPNote.text")); // NOI18N

        jLabel13.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel13.text")); // NOI18N

        jTextFieldIPSPIuranAmount.setEditable(false);
        jTextFieldIPSPIuranAmount.setFocusable(false);
        jTextFieldIPSPIuranAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIPSPIuranAmountActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel17.text")); // NOI18N

        jTextFieldIPSPSisaAmount.setEditable(false);
        jTextFieldIPSPSisaAmount.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextFieldIPSPSisaAmount.setText(String.valueOf(maxIpsp));
        jTextFieldIPSPSisaAmount.setFocusable(false);
        jTextFieldIPSPSisaAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIPSPSisaAmountActionPerformed(evt);
            }
        });

        jButtonIPSPOK.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonIPSPOK.text")); // NOI18N
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
        jLabel10.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel10.text")); // NOI18N

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

        jLabel19.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel19.text")); // NOI18N

        jUnpaidSeragam.setText(String.valueOf(unpaidSeragam));
        jUnpaidSeragam.setEnabled(false);

        jButtonBayarSeragam.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonBayarSeragam.text")); // NOI18N
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jPaneBuku.setMinimumSize(new java.awt.Dimension(440, 550));
        jPaneBuku.setPreferredSize(new java.awt.Dimension(480, 302));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel29.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel29.text")); // NOI18N

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

        jLabel30.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel30.text")); // NOI18N

        jUnpaidBuku.setText(String.valueOf(unpaidBuku));
        jUnpaidBuku.setEnabled(false);

        jButtonBayarBuku.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonBayarBuku.text")); // NOI18N
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
        jLabel32.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel32.text")); // NOI18N

        jLabelTahun3.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabelTahun3.text")); // NOI18N

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

        jLabel33.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel33.text")); // NOI18N

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

        jButtonBayarIKS.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonBayarIKS.text")); // NOI18N
        jButtonBayarIKS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBayarIKSActionPerformed(evt);
            }
        });

        jLabel34.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel34.text")); // NOI18N

        jFormattedTextFieldIDDSaldo1.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jFormattedTextFieldIDDSaldo1.text")); // NOI18N
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

        jFrameCicilanHutang.setMinimumSize(new java.awt.Dimension(680, 450));

        jPanelCicilanHutang.setMinimumSize(new java.awt.Dimension(570, 380));
        jPanelCicilanHutang.setPreferredSize(new java.awt.Dimension(680, 450));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel35.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel35.text")); // NOI18N
        jLabel35.setToolTipText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel35.toolTipText")); // NOI18N

        jLabelTahun1.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabelTahun1.text")); // NOI18N

        try{
            tahunComboBoxModel = buildCicilanHutangtahunComboBoxModel(profil);
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(KasirException ex){
            ex.printStackTrace();
        }
        jComboBoxTahun1.setModel(tahunComboBoxModel);
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

        jLabel36.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel36.text")); // NOI18N

        jUnpaidCicilanHutang.setText(String.valueOf(unpaidCicilanHutang));
        jUnpaidCicilanHutang.setEnabled(false);

        jTableCicilanHutang.setModel(tableModelCicilanHutang);
        jTableCicilanHutang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableCicilanHutang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTableCicilanHutangFocusLost(evt);
            }
        });
        jTableCicilanHutang.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableCicilanHutangPropertyChange(evt);
            }
        });
        jScrollPane12.setViewportView(jTableCicilanHutang);

        jButtonBayarCicilanHutang.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonBayarCicilanHutang.text")); // NOI18N
        jButtonBayarCicilanHutang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBayarCicilanHutangActionPerformed(evt);
            }
        });

        jLabel11.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel11.text")); // NOI18N

        jFormattedTextFieldIDDSaldo2.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jFormattedTextFieldIDDSaldo2.text")); // NOI18N
        jFormattedTextFieldIDDSaldo2.setEnabled(false);

        javax.swing.GroupLayout jPanelCicilanHutangLayout = new javax.swing.GroupLayout(jPanelCicilanHutang);
        jPanelCicilanHutang.setLayout(jPanelCicilanHutangLayout);
        jPanelCicilanHutangLayout.setHorizontalGroup(
            jPanelCicilanHutangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCicilanHutangLayout.createSequentialGroup()
                .addGroup(jPanelCicilanHutangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCicilanHutangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane12))
                    .addGroup(jPanelCicilanHutangLayout.createSequentialGroup()
                        .addGroup(jPanelCicilanHutangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCicilanHutangLayout.createSequentialGroup()
                                .addGap(248, 248, 248)
                                .addComponent(jButtonBayarCicilanHutang))
                            .addGroup(jPanelCicilanHutangLayout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(39, 39, 39)
                                .addComponent(jLabelTahun1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxTahun1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addGroup(jPanelCicilanHutangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelCicilanHutangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jUnpaidCicilanHutang, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextFieldIDDSaldo2))))
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelCicilanHutangLayout.setVerticalGroup(
            jPanelCicilanHutangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCicilanHutangLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanelCicilanHutangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTahun1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTahun1)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36)
                    .addComponent(jUnpaidCicilanHutang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCicilanHutangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jFormattedTextFieldIDDSaldo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBayarCicilanHutang)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrameCicilanHutangLayout = new javax.swing.GroupLayout(jFrameCicilanHutang.getContentPane());
        jFrameCicilanHutang.getContentPane().setLayout(jFrameCicilanHutangLayout);
        jFrameCicilanHutangLayout.setHorizontalGroup(
            jFrameCicilanHutangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelCicilanHutang, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
        );
        jFrameCicilanHutangLayout.setVerticalGroup(
            jFrameCicilanHutangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelCicilanHutang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );

        jFrameAlmamater.setMinimumSize(new java.awt.Dimension(640, 480));

        jPanelAlmamater.setMinimumSize(new java.awt.Dimension(440, 550));
        jPanelAlmamater.setPreferredSize(new java.awt.Dimension(480, 302));

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel37.text")); // NOI18N

        try{
            tableModelAlmamater = buildAlmamaterTableModel(profil);
        }catch(Exception e){
            e.printStackTrace();
        }
        jTableAlmamater.setModel(tableModelAlmamater);
        jTableAlmamater.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableAlmamaterPropertyChange(evt);
            }
        });
        jScrollPane13.setViewportView(jTableAlmamater);

        jLabel38.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel38.text")); // NOI18N

        jUnpaidAlmamater.setText(String.valueOf(unpaidSeragam));
        jUnpaidAlmamater.setEnabled(false);

        jButtonBayarAlmamater.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonBayarAlmamater.text")); // NOI18N
        jButtonBayarAlmamater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBayarAlmamaterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAlmamaterLayout = new javax.swing.GroupLayout(jPanelAlmamater);
        jPanelAlmamater.setLayout(jPanelAlmamaterLayout);
        jPanelAlmamaterLayout.setHorizontalGroup(
            jPanelAlmamaterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlmamaterLayout.createSequentialGroup()
                .addGroup(jPanelAlmamaterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAlmamaterLayout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addGap(104, 104, 104)
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jUnpaidAlmamater, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelAlmamaterLayout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(jButtonBayarAlmamater)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanelAlmamaterLayout.setVerticalGroup(
            jPanelAlmamaterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAlmamaterLayout.createSequentialGroup()
                .addGroup(jPanelAlmamaterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38)
                    .addComponent(jUnpaidAlmamater, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBayarAlmamater)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrameAlmamaterLayout = new javax.swing.GroupLayout(jFrameAlmamater.getContentPane());
        jFrameAlmamater.getContentPane().setLayout(jFrameAlmamaterLayout);
        jFrameAlmamaterLayout.setHorizontalGroup(
            jFrameAlmamaterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrameAlmamaterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelAlmamater, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jFrameAlmamaterLayout.setVerticalGroup(
            jFrameAlmamaterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrameAlmamaterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelAlmamater, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        jFrameIUAP.setMinimumSize(new java.awt.Dimension(680, 450));

        jPanelIUAP.setMinimumSize(new java.awt.Dimension(570, 380));
        jPanelIUAP.setPreferredSize(new java.awt.Dimension(680, 450));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel40.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel40.text")); // NOI18N

        jLabelTahun4.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabelTahun4.text")); // NOI18N

        try{
            tahunComboBoxModel = buildIUAPtahunComboBoxModel(profil);
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(KasirException ex){
            ex.printStackTrace();
        }
        jComboBoxTahunIUAP.setModel(tahunComboBoxModel);
        jComboBoxTahunIUAP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTahunIUAPActionPerformed(evt);
            }
        });
        jComboBoxTahunIUAP.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBoxTahunIUAPPropertyChange(evt);
            }
        });

        jLabel41.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel41.text")); // NOI18N

        jUnpaidIUAP.setText(String.valueOf(unpaidIPP));
        jUnpaidIUAP.setEnabled(false);

        jTableIUAP.setModel(tableModelIUAP);
        jTableIUAP.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableIUAP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTableIUAPFocusLost(evt);
            }
        });
        jTableIUAP.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableIUAPPropertyChange(evt);
            }
        });
        jScrollPane15.setViewportView(jTableIUAP);

        jButtonBayarIUAP.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonBayarIUAP.text")); // NOI18N
        jButtonBayarIUAP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBayarIUAPActionPerformed(evt);
            }
        });

        jLabel42.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel42.text")); // NOI18N

        jFormattedTextFieldIDDSaldo3.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jFormattedTextFieldIDDSaldo3.text")); // NOI18N
        jFormattedTextFieldIDDSaldo3.setEnabled(false);

        javax.swing.GroupLayout jPanelIUAPLayout = new javax.swing.GroupLayout(jPanelIUAP);
        jPanelIUAP.setLayout(jPanelIUAPLayout);
        jPanelIUAPLayout.setHorizontalGroup(
            jPanelIUAPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIUAPLayout.createSequentialGroup()
                .addGroup(jPanelIUAPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelIUAPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane15))
                    .addGroup(jPanelIUAPLayout.createSequentialGroup()
                        .addGroup(jPanelIUAPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelIUAPLayout.createSequentialGroup()
                                .addGap(248, 248, 248)
                                .addComponent(jButtonBayarIUAP))
                            .addGroup(jPanelIUAPLayout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addGap(39, 39, 39)
                                .addComponent(jLabelTahun4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxTahunIUAP, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addGroup(jPanelIUAPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel41)
                                    .addComponent(jLabel42))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelIUAPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jUnpaidIUAP, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextFieldIDDSaldo3))))
                        .addGap(0, 117, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelIUAPLayout.setVerticalGroup(
            jPanelIUAPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIUAPLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanelIUAPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTahunIUAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTahun4)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41)
                    .addComponent(jUnpaidIUAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelIUAPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jFormattedTextFieldIDDSaldo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBayarIUAP)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrameIUAPLayout = new javax.swing.GroupLayout(jFrameIUAP.getContentPane());
        jFrameIUAP.getContentPane().setLayout(jFrameIUAPLayout);
        jFrameIUAPLayout.setHorizontalGroup(
            jFrameIUAPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelIUAP, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
        );
        jFrameIUAPLayout.setVerticalGroup(
            jFrameIUAPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelIUAP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 768));

        jPanelMain.setMinimumSize(new java.awt.Dimension(1024, 768));
        jPanelMain.setPreferredSize(new java.awt.Dimension(6, 28));

        jLableProfilTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLableProfilTitle.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLableProfilTitle.text")); // NOI18N

        jLabelNama.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabelNama.text")); // NOI18N

        jLabelLevel2Level3.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabelLevel2Level3.text")); // NOI18N

        jLabelNoInduk.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabelNoInduk.text")); // NOI18N

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

        jLabel14.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jLabel14.text")); // NOI18N

        jTextFieldTransactionSummaryNote.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldTransactionSummaryNote.text")); // NOI18N

        jButtonIBF.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonIBF.text")); // NOI18N
        jButtonIBF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIBFActionPerformed(evt);
            }
        });

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
                        .addComponent(jLabelNama)
                        .addGap(44, 44, 44)
                        .addComponent(jTextFieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelProfilLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldTransactionSummaryNote)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProfilLayout.createSequentialGroup()
                        .addComponent(jLabelNoInduk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldNomorInduk, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelLevel2Level3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLevel2Level3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonIBF))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jTextFieldNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNoInduk)
                    .addComponent(jTextFieldNomorInduk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLevel2Level3)
                    .addComponent(jTextFieldLevel2Level3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextFieldTransactionSummaryNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonIBF))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonSubmit.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonSubmit.text")); // NOI18N
        jButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubmitActionPerformed(evt);
            }
        });

        jButtonIPP.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonIPP.text")); // NOI18N
        jButtonIPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIPPActionPerformed(evt);
            }
        });

        jButtonIPSP.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonIPSP.text")); // NOI18N
        jButtonIPSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIPSPActionPerformed(evt);
            }
        });

        jButtonSeragam.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonSeragam.text")); // NOI18N
        jButtonSeragam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeragamActionPerformed(evt);
            }
        });

        jButtonIDD.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonIDD.text")); // NOI18N
        jButtonIDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIDDActionPerformed(evt);
            }
        });

        jTextFieldIPPAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldIPPAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldIPPAmountSimple.text")); // NOI18N
        jTextFieldIPPAmountSimple.setPreferredSize(new java.awt.Dimension(6, 25));

        jTextFieldIPSPAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldIPSPAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldIPSPAmountSimple.text")); // NOI18N
        jTextFieldIPSPAmountSimple.setPreferredSize(new java.awt.Dimension(6, 25));

        jTextFieldIDDAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldIDDAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldIDDAmountSimple.text")); // NOI18N
        jTextFieldIDDAmountSimple.setPreferredSize(new java.awt.Dimension(6, 25));

        jTextFieldSeragamAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldSeragamAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldSeragamAmountSimple.text")); // NOI18N
        jTextFieldSeragamAmountSimple.setPreferredSize(new java.awt.Dimension(6, 25));

        jButtonReportIPP.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonReportIPP.text")); // NOI18N
        jButtonReportIPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReportIPPActionPerformed(evt);
            }
        });

        jButtonBuku.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonBuku.text")); // NOI18N
        jButtonBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBukuActionPerformed(evt);
            }
        });

        jTextFieldBukuAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldBukuAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldBukuAmountSimple.text")); // NOI18N
        jTextFieldBukuAmountSimple.setPreferredSize(new java.awt.Dimension(6, 25));

        jButtonIKS.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonIKS.text")); // NOI18N
        jButtonIKS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIKSActionPerformed(evt);
            }
        });

        jTextFieldIKSAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldIKSAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldIKSAmountSimple.text")); // NOI18N
        jTextFieldIKSAmountSimple.setPreferredSize(new java.awt.Dimension(6, 25));

        jButtonILL.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonILL.text")); // NOI18N
        jButtonILL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonILLActionPerformed(evt);
            }
        });

        jTextFieldILLAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldILLAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldILLAmountSimple.text")); // NOI18N
        jTextFieldILLAmountSimple.setPreferredSize(new java.awt.Dimension(6, 25));

        jButtonIPSB.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonIPSB.text")); // NOI18N
        jButtonIPSB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIPSBActionPerformed(evt);
            }
        });

        jTextFieldIPSBAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldIPSBAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldIPSBAmountSimple.text")); // NOI18N
        jTextFieldIPSBAmountSimple.setPreferredSize(new java.awt.Dimension(6, 25));

        jButtonIUA.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonIUA.text")); // NOI18N
        jButtonIUA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIUAActionPerformed(evt);
            }
        });

        jTextFieldIUAAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldIUAAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldIUAAmountSimple.text")); // NOI18N
        jTextFieldIUAAmountSimple.setPreferredSize(new java.awt.Dimension(6, 25));

        jButtonIUS.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonIUS.text")); // NOI18N
        jButtonIUS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIUSActionPerformed(evt);
            }
        });

        jTextFieldIUSAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldIUSAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldIUSAmountSimple.text")); // NOI18N
        jTextFieldIUSAmountSimple.setPreferredSize(new java.awt.Dimension(6, 25));

        jButtonOSIS.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonOSIS.text")); // NOI18N
        jButtonOSIS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOSISActionPerformed(evt);
            }
        });

        jTextFieldOSISAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldOSISAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldOSISAmountSimple.text")); // NOI18N
        jTextFieldOSISAmountSimple.setPreferredSize(new java.awt.Dimension(6, 25));

        jButtonAttribute.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonAttribute.text")); // NOI18N
        jButtonAttribute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAttributeActionPerformed(evt);
            }
        });

        jTextFieldAttributeAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldAttributeAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldAttributeAmountSimple.text")); // NOI18N
        jTextFieldAttributeAmountSimple.setPreferredSize(new java.awt.Dimension(6, 25));

        jButtonPVT.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonPVT.text")); // NOI18N
        jButtonPVT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPVTActionPerformed(evt);
            }
        });

        jTextFieldPVTAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldPVTAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldPVTAmountSimple.text")); // NOI18N
        jTextFieldPVTAmountSimple.setPreferredSize(new java.awt.Dimension(6, 25));

        jButtonTabungan.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonTabungan.text")); // NOI18N
        jButtonTabungan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTabunganActionPerformed(evt);
            }
        });

        jTextFieldTabunganAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldTabunganAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldTabunganAmountSimple.text")); // NOI18N
        jTextFieldTabunganAmountSimple.setPreferredSize(new java.awt.Dimension(6, 25));

        jButtonSumbangan.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonSumbangan.text")); // NOI18N
        jButtonSumbangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSumbanganActionPerformed(evt);
            }
        });

        jTextFieldSumbanganAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldSumbanganAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldSumbanganAmountSimple.text")); // NOI18N
        jTextFieldSumbanganAmountSimple.setPreferredSize(new java.awt.Dimension(6, 25));

        jScrollPane3.setMinimumSize(new java.awt.Dimension(23, 500));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(452, 200));

        jTableTunggakanAll.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTableTunggakanAll.setModel(tunggakanAllTahunTableModel);
        jTableTunggakanAll.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableTunggakanAll.setDoubleBuffered(true);
        jTableTunggakanAll.setIntercellSpacing(new java.awt.Dimension(1, 11));
        jTableTunggakanAll.setRowHeight(32);
        jScrollPane3.setViewportView(jTableTunggakanAll);

        jButtonCicilanHutang.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonCicilanHutang.text")); // NOI18N
        jButtonCicilanHutang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCicilanHutangActionPerformed(evt);
            }
        });

        jTextFieldCicilanHutangAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldCicilanHutangAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldCicilanHutangAmountSimple.text")); // NOI18N
        jTextFieldCicilanHutangAmountSimple.setPreferredSize(new java.awt.Dimension(6, 25));

        jButtonAlmamater.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jButtonAlmamater.text")); // NOI18N
        jButtonAlmamater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlmamaterActionPerformed(evt);
            }
        });

        jTextFieldAlmamaterAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jTextFieldAlmamaterAmountSimple.setText(org.openide.util.NbBundle.getMessage(InputTransactionFrameSeparated.class, "InputTransactionFrameSeparated.jTextFieldAlmamaterAmountSimple.text")); // NOI18N
        jTextFieldAlmamaterAmountSimple.setPreferredSize(new java.awt.Dimension(6, 25));

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelMainLayout.createSequentialGroup()
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
                            .addComponent(jButtonAttribute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonPVT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonTabungan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonSumbangan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonCicilanHutang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAlmamater, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldSumbanganAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldTabunganAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldPVTAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldAttributeAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldOSISAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldIUSAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldIUAAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldIPSBAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldILLAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldIKSAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldIPSPAmountSimple, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                    .addComponent(jTextFieldIPPAmountSimple, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldIDDAmountSimple, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                    .addComponent(jTextFieldBukuAmountSimple, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                    .addComponent(jTextFieldSeragamAmountSimple, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jTextFieldCicilanHutangAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldAlmamaterAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonReportIPP))
                    .addComponent(jPanelProfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addComponent(jPanelProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonReportIPP)
                            .addGroup(jPanelMainLayout.createSequentialGroup()
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonIPP)
                                    .addComponent(jTextFieldIPPAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addComponent(jButtonAttribute)
                                    .addComponent(jTextFieldAttributeAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonCicilanHutang)
                                    .addComponent(jTextFieldCicilanHutangAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonAlmamater)
                                    .addComponent(jTextFieldAlmamaterAmountSimple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, 929, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, 679, Short.MAX_VALUE)
                .addContainerGap())
        );

        setContentPane(validationPanel);
        validationPanel.setInnerComponent(jPanelMain);
        Validator<String> d = StringValidators.trimString(ValidatorUtils.merge(
            StringValidators.REQUIRE_VALID_NUMBER,
            StringValidators.REQUIRE_NON_NEGATIVE_NUMBER,
            StringValidators.numberRange(minIpsp, maxIpsp)));
    validationPanel.getValidationGroup().add(jTextFieldIPSPAmount, d);

    calculateTotalTunggakan(profil);

    //jButtonSubmit.setEnabled(false);

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
            
            if(beasiswaRequest > 0f){
                Beasiswa b = Control.selectIuran(Iuran.Tipe.Beasiswa, Beasiswa.noIndukColName, false, profil.noInduk);
                if(b.amount < beasiswaRequest){
                    JOptionPane.showMessageDialog(rootPane, "Beasiswa Tidak Mencukupi", "Beasiswa Tidak Mencukupi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            if(iddRequest > 0f){
                IDD b = Control.selectIuran(Iuran.Tipe.IDD, IDD.noIndukColName, false, profil.noInduk);
                if(b.amount < iddRequest){
                    JOptionPane.showMessageDialog(rootPane, "Iuran Dibayar Dimuka Tidak Mencukupi", "Iuran Dibayar Dimuka Tidak Mencukupi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            if(beasiswaCostRequest > 0f){
                BeasiswaCost b = Control.selectIuran(Iuran.Tipe.BeasiswaCost, Beasiswa.noIndukColName, false, profil.noInduk);
                if(b.amount < beasiswaCostRequest){
                    JOptionPane.showMessageDialog(rootPane, "Beasiswa Yayasan Tidak Mencukupi", "Beasiswa Yayasan Tidak Mencukupi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            if(totalAmount <= 0){
                JOptionPane.showMessageDialog(rootPane, "Tidak Ada Transaksi\r\nTidak Ada Data Yang Disimpan ke Database", "Tidak Ada Transaksi", JOptionPane.WARNING_MESSAGE);
                return;
            }
        
            saveToDB();
            if (JOptionPane.showConfirmDialog(null, "Print Bukti Pembayaran?", "WARNING",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                        // yes option
                        //printBuktiPembayaran(totalAmount);
                        printBuktiPembayaran(transactionSummary, appFrame.buildTunggakanProfilTableModel(profil), appFrame.totalDebt);
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
//        seragamSToDB = new ArrayList<>();
//        if(jTableSeragam.getRowCount() > 0){
//            for(int i = 0; i < jTableSeragam.getRowCount(); i++){
////                if((Boolean)jTableSeragam.getValueAt(i,3) && (seragamS.get(i).transactDetailIDs.size() == 0)){
//                    
//                    seragamSToDB.add(seragamS.get(i));
//                    //                    try {
//                        //seragam = Control.selectIuran(Iuran.Tipe.Seragam, Seragam.noIndukColName, false , profil.noInduk);
//                        //seragamS.remove(i);
////                    } catch (SQLException ex) {
////                        Exceptions.printStackTrace(ex);
////                    } catch (KasirException ex) {
////                        Exceptions.printStackTrace(ex);
////                    }
////                }
//            }
//        }
    }//GEN-LAST:event_jTableSeragamPropertyChange

    private void jComboBoxIPSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxIPSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxIPSPActionPerformed

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
          inputTransactionIPP = new InputTransactionIPP(profil, this);
          inputTransactionIPP.setVisible(true);
    }//GEN-LAST:event_jButtonIPPActionPerformed

    private void jButtonSeragamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeragamActionPerformed
        inputTransactionSeragam = new InputTransactionSeragam(this.profil, this);
        inputTransactionSeragam.setVisible(true);
    }//GEN-LAST:event_jButtonSeragamActionPerformed

    private void jButtonIPSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIPSPActionPerformed
        // TODO add your handling code here:
        //jFrameIPSP.setVisible(true);
        inputTransactionIPSP = new InputTransactionIPSP(this.profil, this);
        inputTransactionIPSP.setVisible(true);
        
    }//GEN-LAST:event_jButtonIPSPActionPerformed

    private void jComboBoxTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTahunActionPerformed
        // TODO add your handling code here:
        try{
            this.tableModelIPP = (DefaultTableModel) inputTransactionIPP.buildIPPTableModel(this.profil, this.tahunIPP.get(jComboBoxTahun.getSelectedIndex()));
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
                this.tableModelIPP = (DefaultTableModel) inputTransactionIPP.buildIPPTableModel(this.profil, this.tahunIPP.get(jComboBoxTahun.getSelectedIndex()));
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
        iPPAmounts = inputTransactionIPP.iPPAmounts;
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
                    //jTableIPP.setValueAt(ippFromDB.entries.get(i).amount - (iDDAmounts.get(i) + beasiswaAmounts.get(i)+beasiswaCostAmounts.get(i)), i,3);
                    ippStoreToDB.entries.add(new Entry(i, inputTransactionIPP.iPPAmounts.get(i)+iDDAmounts.get(i)+beasiswaAmounts.get(i)+beasiswaCostAmounts.get(i)));
                    
                }else{
                    try {
                        if(inputTransactionIPP.isIPPEnough(ippFromDB.entries.get(i).transactDetailIDs, ippFromDB.entries.get(i).amount)){
                            
                            iDDAmounts.add(0f);
                            beasiswaAmounts.add(0f);
                            beasiswaCostAmounts.add(0f);
                            ippStoreToDB.entries.add(null);
                        }else{
                            
                            iDDAmounts.add((Float) jTableIPP.getValueAt(i, 4));
                            beasiswaAmounts.add((Float) jTableIPP.getValueAt(i, 5));
                            beasiswaCostAmounts.add((Float) jTableIPP.getValueAt(i, 6));
                            ippStoreToDB.entries.add(new Entry(i, inputTransactionIPP.iPPAmounts.get(i)+iDDAmounts.get(i)+beasiswaAmounts.get(i)+beasiswaCostAmounts.get(i)));
                        }
                    } catch (SQLException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (KasirException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                    
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
        inputTransactionBuku = new InputTransactionBuku(this.profil, this);
        inputTransactionBuku.setVisible(true);
    }//GEN-LAST:event_jButtonBukuActionPerformed

    private void jButtonIKSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIKSActionPerformed
        // TODO add your handling code here:
        inputTransactionIKS = new InputTransactionIKS(profil, this);
        inputTransactionIKS.setVisible(true);
    }//GEN-LAST:event_jButtonIKSActionPerformed

    private void jButtonILLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonILLActionPerformed
        // TODO add your handling code here:
        inputTransactionILL = new InputTransactionILL(this.profil, this);
        inputTransactionILL.setVisible(true);
    }//GEN-LAST:event_jButtonILLActionPerformed

    private void jButtonIPSBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIPSBActionPerformed
        // TODO add your handling code here:
        inputTransactionIPSB = new InputTransactionIPSB(this.profil, this);
        inputTransactionIPSB.setVisible(true);
    }//GEN-LAST:event_jButtonIPSBActionPerformed

    private void jButtonIUAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIUAActionPerformed
        // TODO add your handling code here:
        if(this.profil.currentLevel.level1.toString().equals("SMP")){
            inputTransactionIUA = new InputTransactionIUA(this.profil, this);
            inputTransactionIUA.setVisible(true);
        }else if(this.profil.currentLevel.level1.toString().equals("SMA")){
            inputTransactionIUAP = new InputTransactionIUAP(profil, this);
            inputTransactionIUAP.setVisible(true);
        }else if(this.profil.currentLevel.level1.toString().equals("SMK")){
            inputTransactionIUA = new InputTransactionIUA(this.profil, this);
            inputTransactionIUA.setVisible(true);
        }
    }//GEN-LAST:event_jButtonIUAActionPerformed

    private void jButtonIUSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIUSActionPerformed
        // TODO add your handling code here:
        inputTransactionIUS = new InputTransactionIUS(profil, this);
        inputTransactionIUS.setVisible(true);
    }//GEN-LAST:event_jButtonIUSActionPerformed

    private void jButtonOSISActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOSISActionPerformed
        // TODO add your handling code here:
        inputTransactionOSIS = new InputTransactionOSIS(profil, this);
        inputTransactionOSIS.setVisible(true);
    }//GEN-LAST:event_jButtonOSISActionPerformed

    private void jButtonAttributeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAttributeActionPerformed
        // TODO add your handling code here:
        inputTransactionAttribute = new InputTransactionAttribute(this.profil, this);
        inputTransactionAttribute.setVisible(true);
    }//GEN-LAST:event_jButtonAttributeActionPerformed

    private void jButtonPVTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPVTActionPerformed
        // TODO add your handling code here:
        inputTransactionPVT = new InputTransactionPVT(profil, this);
        inputTransactionPVT.setVisible(true);
    }//GEN-LAST:event_jButtonPVTActionPerformed

    private void jButtonTabunganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTabunganActionPerformed
        // TODO add your handling code here:
        inputTransactionTabungan = new InputTransactionTabungan(this.profil, this);
        inputTransactionTabungan.setVisible(true);
    }//GEN-LAST:event_jButtonTabunganActionPerformed

    private void jButtonSumbanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSumbanganActionPerformed
        // TODO add your handling code here:
        inputTransactionSumbangan = new InputTransactionSumbangan(this.profil, this);
        inputTransactionSumbangan.setVisible(true);
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

    private void jButtonIBFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIBFActionPerformed
        // TODO add your handling code here:
        new InputBeasiswaFrame(this.clerk, this.profil).setVisible(true);
    }//GEN-LAST:event_jButtonIBFActionPerformed

    private void jButtonCicilanHutangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCicilanHutangActionPerformed
        // TODO add your handling code here:
        inputTransactionCicilanHutang =  new InputTransactionCicilanHutang(profil, this);
        inputTransactionCicilanHutang.setVisible(true);
        
    }//GEN-LAST:event_jButtonCicilanHutangActionPerformed

    private void jComboBoxTahun1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTahun1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTahun1ActionPerformed

    private void jComboBoxTahun1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBoxTahun1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTahun1PropertyChange

    private void jTableCicilanHutangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableCicilanHutangFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableCicilanHutangFocusLost

    private void jTableCicilanHutangPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableCicilanHutangPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableCicilanHutangPropertyChange

    private void jButtonBayarCicilanHutangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBayarCicilanHutangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonBayarCicilanHutangActionPerformed

    private void jTableAlmamaterPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableAlmamaterPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableAlmamaterPropertyChange

    private void jButtonBayarAlmamaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBayarAlmamaterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonBayarAlmamaterActionPerformed

    private void jButtonAlmamaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlmamaterActionPerformed
        // TODO add your handling code here:
        inputTransactionAlmamater =  new InputTransactionAlmamater(profil, this);
        inputTransactionAlmamater.setVisible(true);
    }//GEN-LAST:event_jButtonAlmamaterActionPerformed

    private void jTableDialogAlmamaterPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableDialogAlmamaterPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableDialogAlmamaterPropertyChange

    private void jComboBoxTahunIUAPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTahunIUAPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTahunIUAPActionPerformed

    private void jComboBoxTahunIUAPPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBoxTahunIUAPPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTahunIUAPPropertyChange

    private void jTableIUAPFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableIUAPFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableIUAPFocusLost

    private void jTableIUAPPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableIUAPPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableIUAPPropertyChange

    private void jButtonBayarIUAPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBayarIUAPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonBayarIUAPActionPerformed

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
            java.util.logging.Logger.getLogger(InputTransactionFrameSeparated.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputTransactionFrameSeparated.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputTransactionFrameSeparated.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputTransactionFrameSeparated.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new InputTransactionFrameSeparated().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable allTransactionTable;
    private javax.swing.JButton jButtonAlmamater;
    private javax.swing.JButton jButtonAttribute;
    private javax.swing.JButton jButtonBayarAlmamater;
    private javax.swing.JButton jButtonBayarBuku;
    private javax.swing.JButton jButtonBayarCicilanHutang;
    private javax.swing.JButton jButtonBayarIKS;
    private javax.swing.JButton jButtonBayarIPP;
    private javax.swing.JButton jButtonBayarIUAP;
    private javax.swing.JButton jButtonBayarSeragam;
    private javax.swing.JButton jButtonBuku;
    private javax.swing.JButton jButtonCicilanHutang;
    private javax.swing.JButton jButtonDialogCancel;
    private javax.swing.JButton jButtonDialogCancel1;
    private javax.swing.JButton jButtonIBF;
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
    private javax.swing.JComboBox jComboBoxTahunIUAP;
    public javax.swing.JDialog jDialogTransactionSummary;
    private javax.swing.JFormattedTextField jFormattedTextFieldIDDSaldo;
    private javax.swing.JFormattedTextField jFormattedTextFieldIDDSaldo1;
    private javax.swing.JFormattedTextField jFormattedTextFieldIDDSaldo2;
    private javax.swing.JFormattedTextField jFormattedTextFieldIDDSaldo3;
    private javax.swing.JFrame jFrameAlmamater;
    private javax.swing.JFrame jFrameBuku;
    private javax.swing.JFrame jFrameCicilanHutang;
    private javax.swing.JFrame jFrameIDD;
    private javax.swing.JFrame jFrameIKS;
    private javax.swing.JFrame jFrameIPP;
    private javax.swing.JFrame jFrameIPSP;
    private javax.swing.JFrame jFrameIUAP;
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
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
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
    private javax.swing.JLabel jLabelTahun4;
    private javax.swing.JLabel jLableProfilTitle;
    private javax.swing.JLabel jLableProfilTitle1;
    private javax.swing.JLabel jLableProfilTitle2;
    private javax.swing.JPanel jPaneBuku;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanelAlmamater;
    private javax.swing.JPanel jPanelCicilanHutang;
    private javax.swing.JPanel jPanelDialogAlmamater;
    private javax.swing.JPanel jPanelDialogBuku;
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
    private javax.swing.JPanel jPanelIUAP;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelProfil;
    private javax.swing.JPanel jPanelSeragam;
    private javax.swing.JPanel jPanelTransactionSummary1;
    private javax.swing.JPanel jPanelTransactionSummary2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTableAlmamater;
    private javax.swing.JTable jTableBuku;
    private javax.swing.JTable jTableCicilanHutang;
    private javax.swing.JTable jTableDialogAlmamater;
    private javax.swing.JTable jTableDialogBuku;
    private javax.swing.JTable jTableDialogSeragam;
    private javax.swing.JTable jTableDialogSeragam1;
    private javax.swing.JTable jTableIKS;
    private javax.swing.JTable jTableIPP;
    private javax.swing.JTable jTableIPP2;
    private javax.swing.JTable jTableIUAP;
    private javax.swing.JTable jTableSeragam;
    private javax.swing.JTable jTableTunggakanAll;
    private javax.swing.JTextArea jTextAreaIDDNote;
    public javax.swing.JFormattedTextField jTextFieldAlmamaterAmountSimple;
    public javax.swing.JFormattedTextField jTextFieldAttributeAmountSimple;
    public javax.swing.JFormattedTextField jTextFieldBukuAmountSimple;
    public javax.swing.JFormattedTextField jTextFieldCicilanHutangAmountSimple;
    private javax.swing.JTextField jTextFieldIDDAmount;
    private javax.swing.JFormattedTextField jTextFieldIDDAmountSimple;
    private javax.swing.JTextField jTextFieldIDDTransactionName;
    public javax.swing.JFormattedTextField jTextFieldIKSAmountSimple;
    public javax.swing.JFormattedTextField jTextFieldILLAmountSimple;
    public javax.swing.JFormattedTextField jTextFieldIPPAmountSimple;
    public javax.swing.JFormattedTextField jTextFieldIPSBAmountSimple;
    private javax.swing.JTextField jTextFieldIPSPAmount;
    private javax.swing.JTextField jTextFieldIPSPAmount1;
    private javax.swing.JTextField jTextFieldIPSPAmount2;
    public javax.swing.JFormattedTextField jTextFieldIPSPAmountSimple;
    private javax.swing.JTextField jTextFieldIPSPIuranAmount;
    private javax.swing.JTextField jTextFieldIPSPNote;
    private javax.swing.JTextField jTextFieldIPSPNote1;
    private javax.swing.JTextField jTextFieldIPSPNote2;
    private javax.swing.JTextField jTextFieldIPSPSisaAmount;
    public javax.swing.JFormattedTextField jTextFieldIUAAmountSimple;
    public javax.swing.JFormattedTextField jTextFieldIUSAmountSimple;
    private javax.swing.JTextField jTextFieldLevel2Level3;
    private javax.swing.JTextField jTextFieldLevel2Level4;
    private javax.swing.JTextField jTextFieldLevel2Level5;
    private javax.swing.JTextField jTextFieldNama;
    private javax.swing.JTextField jTextFieldNama1;
    private javax.swing.JTextField jTextFieldNama2;
    private javax.swing.JTextField jTextFieldNomorInduk;
    private javax.swing.JTextField jTextFieldNomorInduk1;
    private javax.swing.JTextField jTextFieldNomorInduk2;
    public javax.swing.JFormattedTextField jTextFieldOSISAmountSimple;
    public javax.swing.JFormattedTextField jTextFieldPVTAmountSimple;
    public javax.swing.JFormattedTextField jTextFieldSeragamAmountSimple;
    public javax.swing.JFormattedTextField jTextFieldSumbanganAmountSimple;
    private javax.swing.JTextField jTextFieldTSumTotalAmount;
    private javax.swing.JTextField jTextFieldTSumTotalAmount1;
    public javax.swing.JFormattedTextField jTextFieldTabunganAmountSimple;
    private javax.swing.JTextField jTextFieldTransactionSummaryNote;
    private javax.swing.JTextField jTextFieldTransactionSummaryNote1;
    private javax.swing.JTextField jTextFieldTransactionSummaryNote2;
    private javax.swing.JTextField jUnpaidAlmamater;
    private javax.swing.JTextField jUnpaidBuku;
    private javax.swing.JTextField jUnpaidCicilanHutang;
    private javax.swing.JTextField jUnpaidIKS;
    private javax.swing.JTextField jUnpaidIPP;
    private javax.swing.JTextField jUnpaidIUAP;
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
        //PART IDD
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
        
        //PART IPSP
        if(this.iPSPTransactionDetail!=null){
            this.iPSPTransactionDetail.transactSummaryID= transactionSummary.id;
            ArrayList<IPSPTransactionDetail> iPSPTransactionDetails = new ArrayList<>();
            
            if(inputTransactionIPSP.ipspTunaiAmount > 0f){
                IPSPTransactionDetail temp = new IPSPTransactionDetail(ipspTDetailUUID,
                                        ipsp.id,
                                        clerk.id,
                                        transactionSummary.id,
                                        profil.noInduk,
                                        profil.currentLevel.level1,
                                        inputTransactionIPSP.ipspTunaiAmount,
                                        TransactionDetail.PaymentMethod.CASH,
                                        ipsp.note, false);
                iPSPTransactionDetails.add(temp);
            }
            if(inputTransactionIPSP.ipspBeasiswaAmount > 0f){
                IPSPTransactionDetail temp = new IPSPTransactionDetail(ipspTDetailUUID,
                                        ipsp.id,
                                        clerk.id,
                                        transactionSummary.id,
                                        profil.noInduk,
                                        profil.currentLevel.level1,
                                        inputTransactionIPSP.ipspBeasiswaAmount,
                                        TransactionDetail.PaymentMethod.BEASISWA,
                                        ipsp.note, false);
                iPSPTransactionDetails.add(temp);
            }
            if(inputTransactionIPSP.ipspBeasiswaCostAmount > 0f){
                IPSPTransactionDetail temp = new IPSPTransactionDetail(ipspTDetailUUID,
                                        ipsp.id,
                                        clerk.id,
                                        transactionSummary.id,
                                        profil.noInduk,
                                        profil.currentLevel.level1,
                                        inputTransactionIPSP.ipspBeasiswaCostAmount,
                                        TransactionDetail.PaymentMethod.BEASISWA_COST,
                                        ipsp.note, false);
                iPSPTransactionDetails.add(temp);
                BeasiswaCost.transactOut(profil, transactionSummary.id, inputTransactionIPSP.ipspBeasiswaCostAmount);
            }
            
            
            this.iPSPTransactionDetail.lastUpdateDate = theCreatedDate;
            
            Control.insertTDetails(TransactionDetail.Tipe.IPSPTransaction, iPSPTransactionDetails);
                    iPSPTransactionDetails.clear();
                    iPSPTransactionDetails = new ArrayList(Control.selectTDetails(TransactionDetail.Tipe.IPSPTransaction, TransactionDetail.uuidColName, false, ipspTDetailUUID.toString()));
                    for(int j = 0 ; j < iPSPTransactionDetails.size(); j++){
                        ipspFromDB.transactDetailIDs.add(iPSPTransactionDetails.get(j).id);
                        ipspFromDB.totalInstallment = ipspFromDB.totalInstallment + iPSPTransactionDetails.get(j).amount;
                        ipspFromDB.debt -= iPSPTransactionDetails.get(j).amount;
                
                    }
            
            ipspStoreToDB.id = ipspFromDB.id;
            ipspStoreToDB.noInduk = ipspFromDB.noInduk;
            ipspStoreToDB.chargedLevel = ipspFromDB.chargedLevel;

           
            if(ipspStoreToDB != null){
                ipspStoreToDB.amount = ipspFromDB.amount;
                ipspStoreToDB.debt = ipspFromDB.debt;
                ipspStoreToDB.transactDetailIDs = ipspFromDB.transactDetailIDs;

                Control.updateIuran(Iuran.Tipe.IPSP, ipspStoreToDB);
                this.transactionSummary.totalAmount = totalAmount;
                Control.updateTSummary(transactionSummary);

            }
                
//            Control.insertTDetail(TransactionDetail.Tipe.IPSPTransaction, this.iPSPTransactionDetail);
//            Set<IPSPTransactionDetail> filterIPSP = new HashSet<>();
//            filterIPSP.add(this.iPSPTransactionDetail);
//            Map<Long,IPSPTransactionDetail> map = new HashMap<>();
//            Control.filterSelectTDetails(TransactionDetail.Tipe.IPSPTransaction, filterIPSP).get(this.iPSPTransactionDetail);
//            this.iPSPTransactionDetail = Control.selectTDetail(TransactionDetail.Tipe.IPSPTransaction, TransactionDetail.uuidColName, false, ipspTDetailUUID.toString());
//            ipsp.transactDetailIDs.add(this.iPSPTransactionDetail.id);
//            ipsp.totalInstallment = ipsp.totalInstallment + this.iPSPTransactionDetail.amount;
//            ipsp.debt = ipsp.amount - ipsp.totalInstallment;
//            Control.updateIuran(Iuran.Tipe.IPSP, ipsp);
//            //this.transactionSummary.totalAmount = totalAmount;
//            Control.updateTSummary(transactionSummary);
        }
        
        //PART PASB
        if(this.pasbTransactionDetail!=null){
            this.pasbTransactionDetail.transactSummaryID= transactionSummary.id;
            this.pasbTransactionDetail.lastUpdateDate = theCreatedDate;
            this.pasbTransactionDetail.note = "Administrasi PPDB ".concat(profil.currentLevel.level1.toString());
           
            Control.insertTDetail(TransactionDetail.Tipe.PASBTransaction, this.pasbTransactionDetail);
            Set<PASBTransactionDetail> filterPASB = new HashSet<>();
            filterPASB.add(this.pasbTransactionDetail);
            Map<Long,PASBTransactionDetail> map = new HashMap<>();
            Control.filterSelectTDetails(TransactionDetail.Tipe.PASBTransaction, filterPASB).get(this.pasbTransactionDetail);
            this.pasbTransactionDetail = Control.selectTDetail(TransactionDetail.Tipe.PASBTransaction, TransactionDetail.uuidColName, false, pasbTDetailUUID.toString());
            pasb.transactDetailIDs.add(this.pasbTransactionDetail.id);
            pasb.debt -= this.pasbTransactionDetail.amount;
            pasb.transactName = this.pasbTransactionDetail.note;
            Control.updateIuran(Iuran.Tipe.PASB, pasb);
            //this.transactionSummary.totalAmount = totalAmount;
            Control.updateTSummary(transactionSummary);
        }
        
        //PART IPSB
        if(this.iPSBTransactionDetail!=null){
            this.iPSBTransactionDetail.transactSummaryID= transactionSummary.id;
            this.iPSBTransactionDetail.lastUpdateDate = theCreatedDate;
            Control.insertTDetail(TransactionDetail.Tipe.IPSBTransaction, this.iPSBTransactionDetail);
            Set<IPSBTransactionDetail> filterIPSB = new HashSet<>();
            filterIPSB.add(this.iPSBTransactionDetail);
            Map<Long,IPSBTransactionDetail> map = new HashMap<>();
            Control.filterSelectTDetails(TransactionDetail.Tipe.IPSBTransaction, filterIPSB).get(this.iPSBTransactionDetail);
            this.iPSBTransactionDetail = Control.selectTDetail(TransactionDetail.Tipe.IPSBTransaction, TransactionDetail.uuidColName, false, ipsbTDetailUUID.toString());
            ipsb.transactDetailIDs.add(this.iPSBTransactionDetail.id);
            ipsb.totalInstallment = ipsb.totalInstallment + this.iPSBTransactionDetail.amount;
            ipsb.debt = ipsb.amount - ipsb.totalInstallment;
            Control.updateIuran(Iuran.Tipe.IPSB, ipsb);
            //this.transactionSummary.totalAmount = totalAmount;
            Control.updateTSummary(transactionSummary);
        }
        
        //PART IUA
        if(this.iUATransactionDetail!=null){
            this.iUATransactionDetail.transactSummaryID= transactionSummary.id;
            this.iUATransactionDetail.lastUpdateDate = theCreatedDate;
            Control.insertTDetail(TransactionDetail.Tipe.IUATransaction, this.iUATransactionDetail);
            Set<IUATransactionDetail> filterIUA = new HashSet<>();
            filterIUA.add(this.iUATransactionDetail);
            Map<Long,IUATransactionDetail> map = new HashMap<>();
            Control.filterSelectTDetails(TransactionDetail.Tipe.IUATransaction, filterIUA).get(this.iUATransactionDetail);
            this.iUATransactionDetail = Control.selectTDetail(TransactionDetail.Tipe.IUATransaction, TransactionDetail.uuidColName, false, iuaTDetailUUID.toString());
            iua.transactDetailIDs.add(this.iUATransactionDetail.id);
            iua.totalInstallment = iua.totalInstallment + this.iUATransactionDetail.amount;
            iua.debt = iua.amount - iua.totalInstallment;
            Control.updateIuran(Iuran.Tipe.IUA, iua);
            //this.transactionSummary.totalAmount = totalAmount;
            Control.updateTSummary(transactionSummary);
        }
        
        //PART Seragam
        if(seragamSToDB != null){
            for(int i = 0; i<seragamSToDB.size(); i++){
                if((Float)jTableSeragam.getModel().getValueAt(i,5)>0f){
                    seragam = seragamSToDB.get(i);
                    seragamTDetailUUID = UUID.randomUUID();
                    this.seragamTransactionDetail = new SeragamTransactionDetail(seragamTDetailUUID,seragam.id, clerk.id, transactionSummary.id, profil.noInduk, profil.currentLevel.level1, (Float)jTableSeragam.getModel().getValueAt(i,5), TransactionDetail.PaymentMethod.CASH, new Kalender(), new Kalender(), seragam.note, false, false);
                    Control.insertTDetail(TransactionDetail.Tipe.SeragamTransaction, this.seragamTransactionDetail);
                    this.seragamTransactionDetail = Control.selectTDetail(TransactionDetail.Tipe.SeragamTransaction, TransactionDetail.uuidColName, false, seragamTDetailUUID.toString());
                    seragam.transactDetailIDs.add(this.seragamTransactionDetail.id);
                    Seragam temp = Control.selectIuran(Iuran.Tipe.Seragam, seragam.id);
                    seragam.amount = temp.amount;
                    seragam.debt -= this.seragamTransactionDetail.amount;
                    seragam.transactDetailIDs.addAll(temp.transactDetailIDs);
                    Control.updateIuran(Iuran.Tipe.Seragam, seragam);
                    this.transactionSummary = Control.selectTSummary(this.transactionSummary.id);
                    //this.transactionSummary.totalAmount = (Float)jTableSeragam.getModel().getValueAt(i,3);
                    Control.updateTSummary(this.transactionSummary);
                }else{
                    System.out.println("Seragam transaction : nothing");
                }
            }
        }
        
        //PART Almamater
        if(almamaterSToDB != null){
            for(int i = 0; i<almamaterSToDB.size(); i++){
                if((Float)jTableAlmamater.getModel().getValueAt(i,5)>0f){
                    almamater = almamaterSToDB.get(i);
                    almamaterTDetailUUID = UUID.randomUUID();
                    this.almamaterTransactionDetail = new AlmamaterTransactionDetail(almamaterTDetailUUID,almamater.id, clerk.id, transactionSummary.id, profil.noInduk, profil.currentLevel.level1, (Float)jTableAlmamater.getModel().getValueAt(i,5), TransactionDetail.PaymentMethod.CASH, new Kalender(), new Kalender(), almamater.note, false, false);
                    Control.insertTDetail(TransactionDetail.Tipe.AlmamaterTransaction, this.almamaterTransactionDetail);
                    this.almamaterTransactionDetail = Control.selectTDetail(TransactionDetail.Tipe.AlmamaterTransaction, TransactionDetail.uuidColName, false, almamaterTDetailUUID.toString());
                    almamater.transactDetailIDs.add(this.almamaterTransactionDetail.id);
                    Almamater temp = Control.selectIuran(Iuran.Tipe.Almamater, almamater.id);
                    almamater.amount = temp.amount;
                    almamater.debt -= this.almamaterTransactionDetail.amount;
                    almamater.transactDetailIDs.addAll(temp.transactDetailIDs);
                    Control.updateIuran(Iuran.Tipe.Almamater, almamater);
                    this.transactionSummary = Control.selectTSummary(this.transactionSummary.id);
                    //this.transactionSummary.totalAmount = (Float)jTableAlmamater.getModel().getValueAt(i,3);
                    Control.updateTSummary(this.transactionSummary);
                }else{
                    System.out.println("Almamater transaction : nothing");
                }
            }
        }
        
        //PART Attribute
        if(attributeSToDB != null){
            for(int i = 0; i<attributeSToDB.size(); i++){
                
                if((Float)inputTransactionAttribute.jTableAttribute.getModel().getValueAt(i,5)>0f){
                    attribute = attributeSToDB.get(i);
                    attributeTDetailUUID = UUID.randomUUID();
                    this.attributeTransactionDetail = new AttributeTransactionDetail(attributeTDetailUUID,attribute.id, clerk.id, transactionSummary.id, profil.noInduk, profil.currentLevel.level1, (Float)inputTransactionAttribute.jTableAttribute.getModel().getValueAt(i,5), TransactionDetail.PaymentMethod.CASH, new Kalender(), new Kalender(), attribute.note, false, false);
                    Control.insertTDetail(TransactionDetail.Tipe.AttributeTransaction, this.attributeTransactionDetail);
                    this.attributeTransactionDetail = Control.selectTDetail(TransactionDetail.Tipe.AttributeTransaction, TransactionDetail.uuidColName, false, attributeTDetailUUID.toString());
                    attribute.transactDetailIDs.add(this.attributeTransactionDetail.id);
                    Attribute temp = Control.selectIuran(Iuran.Tipe.Attribute, attribute.id);
                    attribute.amount = temp.amount;
                    attribute.debt -= this.attributeTransactionDetail.amount;
                    attribute.transactDetailIDs.addAll(temp.transactDetailIDs);
                    Control.updateIuran(Iuran.Tipe.Attribute, attribute);
                    this.transactionSummary = Control.selectTSummary(this.transactionSummary.id);
                    //this.transactionSummary.totalAmount = (Float)jTableAttribute.getModel().getValueAt(i,3);
                    Control.updateTSummary(this.transactionSummary);
                }else{
                    System.out.println("Attribute transaction : nothing");
                }
            }
        }
        
        //PART Buku
        if(bukuSToDB != null){
            for(int i = 0; i<bukuSToDB.size(); i++){
                if((Float)jTableBuku.getModel().getValueAt(i,5)>0f){
                    buku = bukuSToDB.get(i);
                    bukuTDetailUUID = UUID.randomUUID();
                    this.bukuTransactionDetail = new BukuTransactionDetail(bukuTDetailUUID,buku.id, clerk.id, transactionSummary.id, profil.noInduk, profil.currentLevel.level1, (Float)jTableBuku.getModel().getValueAt(i,5), TransactionDetail.PaymentMethod.CASH, new Kalender(), new Kalender(), buku.note, false, false);
                    Control.insertTDetail(TransactionDetail.Tipe.BukuTransaction, this.bukuTransactionDetail);
                    this.bukuTransactionDetail = Control.selectTDetail(TransactionDetail.Tipe.BukuTransaction, TransactionDetail.uuidColName, false, bukuTDetailUUID.toString());
                    buku.transactDetailIDs.add(this.bukuTransactionDetail.id);
                    Buku temp = Control.selectIuran(Iuran.Tipe.Buku, buku.id);
                    buku.amount = temp.amount;
                    buku.debt -= this.bukuTransactionDetail.amount;
                    buku.transactDetailIDs.addAll(temp.transactDetailIDs);
                    Control.updateIuran(Iuran.Tipe.Buku, buku);
                    this.transactionSummary = Control.selectTSummary(this.transactionSummary.id);
                    //this.transactionSummary.totalAmount = (Float)jTableBuku.getModel().getValueAt(i,3);
                    Control.updateTSummary(this.transactionSummary);
                }else{
                    System.out.println("Buku transaction : nothing");
                }
            }
        }
        
        //PART ILL
        if(illSToDB != null){
            for(int i = 0; i<illSToDB.size(); i++){
                
                if((Float)inputTransactionILL.jTableILL.getModel().getValueAt(i,5)>0f){
                    ill = illSToDB.get(i);
                    illTDetailUUID = UUID.randomUUID();
                    this.illTransactionDetail = new ILLTransactionDetail(illTDetailUUID,ill.id, clerk.id, transactionSummary.id, profil.noInduk, profil.currentLevel.level1, (Float)inputTransactionILL.jTableILL.getModel().getValueAt(i,5), TransactionDetail.PaymentMethod.CASH, new Kalender(), new Kalender(), ill.note, false, false);
                    Control.insertTDetail(TransactionDetail.Tipe.ILLTransaction, this.illTransactionDetail);
                    this.illTransactionDetail = Control.selectTDetail(TransactionDetail.Tipe.ILLTransaction, TransactionDetail.uuidColName, false, illTDetailUUID.toString());
                    ill.transactDetailIDs.add(this.illTransactionDetail.id);
                    ILL temp = Control.selectIuran(Iuran.Tipe.ILL, ill.id);
                    ill.amount = temp.amount;
                    ill.debt -= this.illTransactionDetail.amount;
                    ill.transactDetailIDs.addAll(temp.transactDetailIDs);
                    Control.updateIuran(Iuran.Tipe.ILL, ill);
                    this.transactionSummary = Control.selectTSummary(this.transactionSummary.id);
                    //this.transactionSummary.totalAmount = (Float)jTableILL.getModel().getValueAt(i,3);
                    Control.updateTSummary(this.transactionSummary);
                }else{
                    System.out.println("ILL transaction : nothing");
                }
            }
        }
        
        //PART Tabungan
        if(tabunganSToDB != null){
            for(int i = 0; i<tabunganSToDB.size(); i++){
                
                if((Float)inputTransactionTabungan.jTableTabungan.getModel().getValueAt(i,5)>0f){
                    tabungan = tabunganSToDB.get(i);
                    tabunganTDetailUUID = UUID.randomUUID();
                    this.tabunganTransactionDetail = new TabunganTransactionDetail(tabunganTDetailUUID,tabungan.id, clerk.id, transactionSummary.id, profil.noInduk, profil.currentLevel.level1, (Float)inputTransactionTabungan.jTableTabungan.getModel().getValueAt(i,5), TransactionDetail.PaymentMethod.CASH, new Kalender(), new Kalender(), tabungan.note, false, false);
                    Control.insertTDetail(TransactionDetail.Tipe.TabunganTransaction, this.tabunganTransactionDetail);
                    this.tabunganTransactionDetail = Control.selectTDetail(TransactionDetail.Tipe.TabunganTransaction, TransactionDetail.uuidColName, false, tabunganTDetailUUID.toString());
                    tabungan.transactDetailIDs.add(this.tabunganTransactionDetail.id);
                    Tabungan temp = Control.selectIuran(Iuran.Tipe.Tabungan, tabungan.id);
                    tabungan.amount = temp.amount;
                    tabungan.debt -= this.tabunganTransactionDetail.amount;
                    tabungan.transactDetailIDs.addAll(temp.transactDetailIDs);
                    Control.updateIuran(Iuran.Tipe.Tabungan, tabungan);
                    this.transactionSummary = Control.selectTSummary(this.transactionSummary.id);
                    //this.transactionSummary.totalAmount = (Float)jTableTabungan.getModel().getValueAt(i,3);
                    Control.updateTSummary(this.transactionSummary);
                }else{
                    System.out.println("Tabungan transaction : nothing");
                }
            }
        }
        
        //PART Sumbangan
        if(sumbanganSToDB != null){
            for(int i = 0; i<sumbanganSToDB.size(); i++){
                
                if((Float)inputTransactionSumbangan.jTableSumbangan.getModel().getValueAt(i,5)>0f){
                    sumbangan = sumbanganSToDB.get(i);
                    sumbanganTDetailUUID = UUID.randomUUID();
                    this.sumbanganTransactionDetail = new SumbanganTransactionDetail(sumbanganTDetailUUID,sumbangan.id, clerk.id, transactionSummary.id, profil.noInduk, profil.currentLevel.level1, (Float)inputTransactionSumbangan.jTableSumbangan.getModel().getValueAt(i,5), TransactionDetail.PaymentMethod.CASH, new Kalender(), new Kalender(), sumbangan.note, false, false);
                    Control.insertTDetail(TransactionDetail.Tipe.SumbanganTransaction, this.sumbanganTransactionDetail);
                    this.sumbanganTransactionDetail = Control.selectTDetail(TransactionDetail.Tipe.SumbanganTransaction, TransactionDetail.uuidColName, false, sumbanganTDetailUUID.toString());
                    sumbangan.transactDetailIDs.add(this.sumbanganTransactionDetail.id);
                    Sumbangan temp = Control.selectIuran(Iuran.Tipe.Sumbangan, sumbangan.id);
                    sumbangan.amount = temp.amount;
                    sumbangan.debt -= this.sumbanganTransactionDetail.amount;
                    sumbangan.transactDetailIDs.addAll(temp.transactDetailIDs);
                    Control.updateIuran(Iuran.Tipe.Sumbangan, sumbangan);
                    this.transactionSummary = Control.selectTSummary(this.transactionSummary.id);
                    //this.transactionSummary.totalAmount = (Float)jTableSumbangan.getModel().getValueAt(i,3);
                    Control.updateTSummary(this.transactionSummary);
                }else{
                    System.out.println("Sumbangan transaction : nothing");
                }
            }
        }
        
        //PART IPP
        if(jTextFieldIPPAmountSimple.getValue() != null){
            for(int i = 0 ; i < 12 ; i++){
                ippTDetailUUID = UUID.randomUUID();
                if(ippStoreToDB.entries.get(i)!=null){
                    if(!inputTransactionIPP.isIPPEnough(ippFromDB.entries.get(i).transactDetailIDs, ippFromDB.entries.get(i).amount)){
                    ArrayList<IPPTransactionDetail> ippTransactionDetails = new ArrayList();
                    if(inputTransactionIPP.iPPAmounts.get(i) > 0f){
                        IPPTransactionDetail ippTransactionDetail = new IPPTransactionDetail(ippTDetailUUID, 
                                                                                            ippFromDB.id, 
                                                                                            clerk.id, 
                                                                                            transactionSummary.id, profil.noInduk, profil.currentLevel.level1, (inputTransactionIPP.iPPAmounts.get(i)), TransactionDetail.PaymentMethod.CASH,
                                                                                            jTableIPP.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);

                        ippTransactionDetails.add(ippTransactionDetail);
                    }
                    if(iDDAmounts.get(i) > 0f){
                        IPPTransactionDetail ippTransactionDetail = new IPPTransactionDetail(ippTDetailUUID, 
                                                                                            ippFromDB.id, 
                                                                                            clerk.id, 
                                                                                            transactionSummary.id, profil.noInduk, profil.currentLevel.level1, iDDAmounts.get(i), TransactionDetail.PaymentMethod.IDD,
                                                                                            jTableIPP.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                        ippTransactionDetails.add(ippTransactionDetail);
                        // transactOUT IDD , kurangin IDD amount sama insert TDetail IDD

                        IDD.transactOut(profil, transactionSummary.id, iDDAmounts.get(i));
                    }
                    if(beasiswaAmounts.get(i) > 0f){
                        if(Beasiswa.transactOut(profil, transactionSummary.id, beasiswaAmounts.get(i))){
                        IPPTransactionDetail ippTransactionDetail = new IPPTransactionDetail(ippTDetailUUID, 
                                                                                            ippFromDB.id, 
                                                                                            clerk.id, 
                                                                                            transactionSummary.id, profil.noInduk, profil.currentLevel.level1, beasiswaAmounts.get(i), TransactionDetail.PaymentMethod.BEASISWA,
                                                                                            jTableIPP.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                        ippTransactionDetails.add(ippTransactionDetail);
                        }
                    }
                    if(beasiswaCostAmounts.get(i) > 0f){
                        IPPTransactionDetail ippTransactionDetail = new IPPTransactionDetail(ippTDetailUUID, 
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
                        ippFromDB.entries.get(i).transactDetailIDs.add(ippTransactionDetails.get(j).id);
                        ippFromDB.entries.get(i).debt -= ippTransactionDetails.get(j).amount;
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

            for(int i = 0 ; i < ippStoreToDB.entries.size(); i++){
                if(ippStoreToDB.entries.get(i) != null){
                    ippStoreToDB.entries.get(i).amount = ippFromDB.entries.get(i).amount;
                    ippStoreToDB.entries.get(i).debt = ippFromDB.entries.get(i).debt;
                    ippStoreToDB.entries.get(i).transactDetailIDs = ippFromDB.entries.get(i).transactDetailIDs;

                }
            }
            Control.updateIuran(Iuran.Tipe.IPP, ippStoreToDB);
            this.transactionSummary.totalAmount = totalAmount;
            Control.updateTSummary(transactionSummary);

        }
        
        //PART IUAP
        if(jTextFieldIUAAmountSimple.getValue() != null){
            for(int i = 0 ; i < 12 ; i++){
                iuapTDetailUUID = UUID.randomUUID();
                if(iuapStoreToDB.entries.get(i)!=null){
                    if(!inputTransactionIUAP.isIUAPEnough(iuapFromDB.entries.get(i).transactDetailIDs, iuapFromDB.entries.get(i).amount)){
                    ArrayList<IUAPTransactionDetail> iuapTransactionDetails = new ArrayList();
                    if(inputTransactionIUAP.iUAPAmounts.get(i) > 0f){
                        IUAPTransactionDetail iuapTransactionDetail = new IUAPTransactionDetail(iuapTDetailUUID, 
                                                                                            iuapFromDB.id, 
                                                                                            clerk.id, 
                                                                                            transactionSummary.id, profil.noInduk, profil.currentLevel.level1, (inputTransactionIUAP.iUAPAmounts.get(i)), TransactionDetail.PaymentMethod.CASH,
                                                                                            jTableIUAP.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);

                        iuapTransactionDetails.add(iuapTransactionDetail);
                    }
                    if(iDDAmounts.get(i) > 0f){
                        IUAPTransactionDetail iuapTransactionDetail = new IUAPTransactionDetail(iuapTDetailUUID, 
                                                                                            iuapFromDB.id, 
                                                                                            clerk.id, 
                                                                                            transactionSummary.id, profil.noInduk, profil.currentLevel.level1, iDDAmounts.get(i), TransactionDetail.PaymentMethod.IDD,
                                                                                            jTableIUAP.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                        iuapTransactionDetails.add(iuapTransactionDetail);
                        // transactOUT IDD , kurangin IDD amount sama insert TDetail IDD

                        IDD.transactOut(profil, transactionSummary.id, iDDAmounts.get(i));
                    }
                    if(beasiswaAmounts.get(i) > 0f){
                        if(Beasiswa.transactOut(profil, transactionSummary.id, beasiswaAmounts.get(i))){
                        IUAPTransactionDetail iuapTransactionDetail = new IUAPTransactionDetail(iuapTDetailUUID, 
                                                                                            iuapFromDB.id, 
                                                                                            clerk.id, 
                                                                                            transactionSummary.id, profil.noInduk, profil.currentLevel.level1, beasiswaAmounts.get(i), TransactionDetail.PaymentMethod.BEASISWA,
                                                                                            jTableIUAP.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                        iuapTransactionDetails.add(iuapTransactionDetail);
                        }
                    }
                    if(beasiswaCostAmounts.get(i) > 0f){
                        IUAPTransactionDetail iuapTransactionDetail = new IUAPTransactionDetail(iuapTDetailUUID, 
                                                                                            iuapFromDB.id, 
                                                                                            clerk.id, 
                                                                                            transactionSummary.id, profil.noInduk, profil.currentLevel.level1, beasiswaCostAmounts.get(i), TransactionDetail.PaymentMethod.BEASISWA_COST,
                                                                                            jTableIUAP.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                        iuapTransactionDetails.add(iuapTransactionDetail);
                        BeasiswaCost.transactOut(profil, transactionSummary.id, beasiswaCostAmounts.get(i));
                    }
                    Control.insertTDetails(TransactionDetail.Tipe.IUAPTransaction, iuapTransactionDetails);
                    iuapTransactionDetails.clear();
                    iuapTransactionDetails = new ArrayList(Control.selectTDetails(TransactionDetail.Tipe.IUAPTransaction, TransactionDetail.uuidColName, false, iuapTDetailUUID.toString()));
                    for(int j = 0 ; j < iuapTransactionDetails.size(); j++){
                        iuapFromDB.entries.get(i).transactDetailIDs.add(iuapTransactionDetails.get(j).id);
                        iuapFromDB.entries.get(i).debt -= iuapTransactionDetails.get(j).amount;
                    }
    //                Control.insertTDetail(TransactionDetail.Tipe.IUAPTransaction, iuapTransactionDetail);
    //                iuapTransactionDetail = Control.selectTDetail(TransactionDetail.Tipe.IUAPTransaction, TransactionDetail.uuidColName, false, iuapTDetailUUID.toString());
    //                iuapStoreToDB.entries.get(i).transactDetailIDs.add(iuapTransactionDetail.id);

                    }
                }
            }
            iuapStoreToDB.id = iuapFromDB.id;
            iuapStoreToDB.noInduk = iuapFromDB.noInduk;
            iuapStoreToDB.chargedLevel = iuapFromDB.chargedLevel;

            for(int i = 0 ; i < iuapStoreToDB.entries.size(); i++){
                if(iuapStoreToDB.entries.get(i) != null){
                    iuapStoreToDB.entries.get(i).amount = iuapFromDB.entries.get(i).amount;
                    iuapStoreToDB.entries.get(i).debt = iuapFromDB.entries.get(i).debt;
                    iuapStoreToDB.entries.get(i).transactDetailIDs = iuapFromDB.entries.get(i).transactDetailIDs;

                }
            }
            Control.updateIuran(Iuran.Tipe.IUAP, iuapStoreToDB);
            this.transactionSummary.totalAmount = totalAmount;
            Control.updateTSummary(transactionSummary);

        }
        
        //PART CicilanHutang
        if(jTextFieldCicilanHutangAmountSimple.getValue() != null){
            for(int i = 0 ; i < 12 ; i++){
                cicilanHutangTDetailUUID = UUID.randomUUID();
                if(cicilanHutangStoreToDB.entries.get(i)!=null){
                    if(!inputTransactionCicilanHutang.isCicilanHutangEnough(cicilanHutangFromDB.entries.get(i).transactDetailIDs, cicilanHutangFromDB.entries.get(i).amount)){
                    ArrayList<CicilanHutangTransactionDetail> cicilanHutangTransactionDetails = new ArrayList();
                    if(inputTransactionCicilanHutang.cicilanHutangAmounts.get(i) > 0f){
                        CicilanHutangTransactionDetail cicilanHutangTransactionDetail = new CicilanHutangTransactionDetail(cicilanHutangTDetailUUID, 
                                                                                            cicilanHutangFromDB.id, 
                                                                                            clerk.id, 
                                                                                            transactionSummary.id, profil.noInduk, profil.currentLevel.level1, (inputTransactionCicilanHutang.cicilanHutangAmounts.get(i)), TransactionDetail.PaymentMethod.CASH,
                                                                                            jTableCicilanHutang.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);

                        cicilanHutangTransactionDetails.add(cicilanHutangTransactionDetail);
                    }
                    if(iDDAmounts.get(i) > 0f){
                        CicilanHutangTransactionDetail cicilanHutangTransactionDetail = new CicilanHutangTransactionDetail(cicilanHutangTDetailUUID, 
                                                                                            cicilanHutangFromDB.id, 
                                                                                            clerk.id, 
                                                                                            transactionSummary.id, profil.noInduk, profil.currentLevel.level1, iDDAmounts.get(i), TransactionDetail.PaymentMethod.IDD,
                                                                                            jTableCicilanHutang.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                        cicilanHutangTransactionDetails.add(cicilanHutangTransactionDetail);
                        // transactOUT IDD , kurangin IDD amount sama insert TDetail IDD

                        IDD.transactOut(profil, transactionSummary.id, iDDAmounts.get(i));
                    }
                    if(beasiswaAmounts.get(i) > 0f){
                        if(Beasiswa.transactOut(profil, transactionSummary.id, beasiswaAmounts.get(i))){
                        CicilanHutangTransactionDetail cicilanHutangTransactionDetail = new CicilanHutangTransactionDetail(cicilanHutangTDetailUUID, 
                                                                                            cicilanHutangFromDB.id, 
                                                                                            clerk.id, 
                                                                                            transactionSummary.id, profil.noInduk, profil.currentLevel.level1, beasiswaAmounts.get(i), TransactionDetail.PaymentMethod.BEASISWA,
                                                                                            jTableCicilanHutang.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                        cicilanHutangTransactionDetails.add(cicilanHutangTransactionDetail);
                        }
                    }
                    if(beasiswaCostAmounts.get(i) > 0f){
                        CicilanHutangTransactionDetail cicilanHutangTransactionDetail = new CicilanHutangTransactionDetail(cicilanHutangTDetailUUID, 
                                                                                            cicilanHutangFromDB.id, 
                                                                                            clerk.id, 
                                                                                            transactionSummary.id, profil.noInduk, profil.currentLevel.level1, beasiswaCostAmounts.get(i), TransactionDetail.PaymentMethod.BEASISWA_COST,
                                                                                            jTableCicilanHutang.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                        cicilanHutangTransactionDetails.add(cicilanHutangTransactionDetail);
                        BeasiswaCost.transactOut(profil, transactionSummary.id, beasiswaCostAmounts.get(i));
                    }
                    Control.insertTDetails(TransactionDetail.Tipe.CicilanHutangTransaction, cicilanHutangTransactionDetails);
                    cicilanHutangTransactionDetails.clear();
                    cicilanHutangTransactionDetails = new ArrayList(Control.selectTDetails(TransactionDetail.Tipe.CicilanHutangTransaction, TransactionDetail.uuidColName, false, cicilanHutangTDetailUUID.toString()));
                    for(int j = 0 ; j < cicilanHutangTransactionDetails.size(); j++){
                        cicilanHutangFromDB.entries.get(i).transactDetailIDs.add(cicilanHutangTransactionDetails.get(j).id);
                        cicilanHutangFromDB.entries.get(i).debt -= cicilanHutangTransactionDetails.get(j).amount;
                    }
    //                Control.insertTDetail(TransactionDetail.Tipe.CicilanHutangTransaction, cicilanHutangTransactionDetail);
    //                cicilanHutangTransactionDetail = Control.selectTDetail(TransactionDetail.Tipe.CicilanHutangTransaction, TransactionDetail.uuidColName, false, cicilanHutangTDetailUUID.toString());
    //                cicilanHutangStoreToDB.entries.get(i).transactDetailIDs.add(cicilanHutangTransactionDetail.id);

                    }
                }
            }
            cicilanHutangStoreToDB.id = cicilanHutangFromDB.id;
            cicilanHutangStoreToDB.noInduk = cicilanHutangFromDB.noInduk;
            cicilanHutangStoreToDB.chargedLevel = cicilanHutangFromDB.chargedLevel;

            for(int i = 0 ; i < cicilanHutangStoreToDB.entries.size(); i++){
                if(cicilanHutangStoreToDB.entries.get(i) != null){
                    cicilanHutangStoreToDB.entries.get(i).amount = cicilanHutangFromDB.entries.get(i).amount;
                    cicilanHutangStoreToDB.entries.get(i).debt = cicilanHutangFromDB.entries.get(i).debt;
                    cicilanHutangStoreToDB.entries.get(i).transactDetailIDs = cicilanHutangFromDB.entries.get(i).transactDetailIDs;

                }
            }
            Control.updateIuran(Iuran.Tipe.CicilanHutang, cicilanHutangStoreToDB);
            this.transactionSummary.totalAmount = totalAmount;
            Control.updateTSummary(transactionSummary);

        }
        
        //PART IUS
        if(jTextFieldIUSAmountSimple.getValue() != null){
            for(int i = 0 ; i < 2 ; i++){
                iusTDetailUUID = UUID.randomUUID();
                if(iusStoreToDB.entries.get(i)!=null){
                    if(!inputTransactionIUS.isIUSEnough(iusFromDB.entries.get(i).transactDetailIDs, iusFromDB.entries.get(i).amount)){
                    ArrayList<IUSTransactionDetail> iusTransactionDetails = new ArrayList();
                    if(inputTransactionIUS.iUSAmounts.get(i) > 0f){
                        IUSTransactionDetail iusTransactionDetail = new IUSTransactionDetail(iusTDetailUUID, 
                                                                                            iusFromDB.id, 
                                                                                            clerk.id, 
                                                                                            transactionSummary.id, profil.noInduk, profil.currentLevel.level1, (inputTransactionIUS.iUSAmounts.get(i)), TransactionDetail.PaymentMethod.CASH,
                                                                                            inputTransactionIUS.jTableIUS.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);

                        iusTransactionDetails.add(iusTransactionDetail);
                    }
                    if(inputTransactionIUS.iDDAmounts.get(i) > 0f){
                        IUSTransactionDetail iusTransactionDetail = new IUSTransactionDetail(iusTDetailUUID, 
                                                                                            iusFromDB.id, 
                                                                                            clerk.id, 
                                                                                            transactionSummary.id, profil.noInduk, profil.currentLevel.level1, inputTransactionIUS.iDDAmounts.get(i), TransactionDetail.PaymentMethod.IDD,
                                                                                            inputTransactionIUS.jTableIUS.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                        iusTransactionDetails.add(iusTransactionDetail);
                        // transactOUT IDD , kurangin IDD amount sama insert TDetail IDD

                        IDD.transactOut(profil, transactionSummary.id, inputTransactionIUS.iDDAmounts.get(i));
                    }
                    if(inputTransactionIUS.beasiswaAmounts.get(i) > 0f){
                        if(Beasiswa.transactOut(profil, transactionSummary.id, inputTransactionIUS.beasiswaAmounts.get(i))){
                        IUSTransactionDetail iusTransactionDetail = new IUSTransactionDetail(iusTDetailUUID, 
                                                                                            iusFromDB.id, 
                                                                                            clerk.id, 
                                                                                            transactionSummary.id, profil.noInduk, profil.currentLevel.level1, beasiswaAmounts.get(i), TransactionDetail.PaymentMethod.BEASISWA,
                                                                                            inputTransactionIUS.jTableIUS.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                        iusTransactionDetails.add(iusTransactionDetail);
                        }
                    }
                    if(inputTransactionIUS.beasiswaCostAmounts.get(i) > 0f){
                        IUSTransactionDetail iusTransactionDetail = new IUSTransactionDetail(iusTDetailUUID, 
                                                                                            iusFromDB.id, 
                                                                                            clerk.id, 
                                                                                            transactionSummary.id, profil.noInduk, profil.currentLevel.level1, beasiswaCostAmounts.get(i), TransactionDetail.PaymentMethod.BEASISWA_COST,
                                                                                            inputTransactionIUS.jTableIUS.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                        iusTransactionDetails.add(iusTransactionDetail);
                        BeasiswaCost.transactOut(profil, transactionSummary.id, inputTransactionIUS.beasiswaCostAmounts.get(i));
                    }
                    Control.insertTDetails(TransactionDetail.Tipe.IUSTransaction, iusTransactionDetails);
                    iusTransactionDetails.clear();
                    iusTransactionDetails = new ArrayList(Control.selectTDetails(TransactionDetail.Tipe.IUSTransaction, TransactionDetail.uuidColName, false, iusTDetailUUID.toString()));
                    for(int j = 0 ; j < iusTransactionDetails.size(); j++){
                        iusFromDB.entries.get(i).transactDetailIDs.add(iusTransactionDetails.get(j).id);
                        iusFromDB.entries.get(i).debt -= iusTransactionDetails.get(j).amount;
                    }
    //                Control.insertTDetail(TransactionDetail.Tipe.IUSTransaction, iusTransactionDetail);
    //                iusTransactionDetail = Control.selectTDetail(TransactionDetail.Tipe.IUSTransaction, TransactionDetail.uuidColName, false, iusTDetailUUID.toString());
    //                iusStoreToDB.entries.get(i).transactDetailIDs.add(iusTransactionDetail.id);

                    }
                }
            }
            iusStoreToDB.id = iusFromDB.id;
            iusStoreToDB.noInduk = iusFromDB.noInduk;
            iusStoreToDB.chargedLevel = iusFromDB.chargedLevel;

            for(int i = 0 ; i < iusStoreToDB.entries.size(); i++){
                if(iusStoreToDB.entries.get(i) != null){
                    iusStoreToDB.entries.get(i).amount = iusFromDB.entries.get(i).amount;
                    iusStoreToDB.entries.get(i).debt = iusFromDB.entries.get(i).debt;
                    iusStoreToDB.entries.get(i).transactDetailIDs = iusFromDB.entries.get(i).transactDetailIDs;

                }
            }
            Control.updateIuran(Iuran.Tipe.IUS, iusStoreToDB);
        
            this.transactionSummary.totalAmount = totalAmount;
            Control.updateTSummary(transactionSummary);

            }
        
        //PART IKS
        if(jTextFieldIKSAmountSimple.getValue() != null){
            for(int i = 0 ; i < 1 ; i++){
                iksTDetailUUID = UUID.randomUUID();
                if(iksStoreToDB.entries.get(i)!=null){
                    if(!inputTransactionIKS.isIKSEnough(iksFromDB.entries.get(i).transactDetailIDs, iksFromDB.entries.get(i).amount)){
                    ArrayList<IKSTransactionDetail> iksTransactionDetails = new ArrayList();
                    if(inputTransactionIKS.iKSAmounts.get(i) > 0f){
                        IKSTransactionDetail iksTransactionDetail = new IKSTransactionDetail(iksTDetailUUID, 
                                                                                            iksFromDB.id, 
                                                                                            clerk.id, 
                                                                                            transactionSummary.id, profil.noInduk, profil.currentLevel.level1, (inputTransactionIKS.iKSAmounts.get(i)), TransactionDetail.PaymentMethod.CASH,
                                                                                            inputTransactionIKS.jTableIKS.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);

                        iksTransactionDetails.add(iksTransactionDetail);
                    }
                    if(inputTransactionIKS.iDDAmounts.get(i) > 0f){
                        IKSTransactionDetail iksTransactionDetail = new IKSTransactionDetail(iksTDetailUUID, 
                                                                                            iksFromDB.id, 
                                                                                            clerk.id, 
                                                                                            transactionSummary.id, profil.noInduk, profil.currentLevel.level1, inputTransactionIKS.iDDAmounts.get(i), TransactionDetail.PaymentMethod.IDD,
                                                                                            inputTransactionIKS.jTableIKS.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                        iksTransactionDetails.add(iksTransactionDetail);
                        // transactOUT IDD , kurangin IDD amount sama insert TDetail IDD

                        IDD.transactOut(profil, transactionSummary.id, inputTransactionIKS.iDDAmounts.get(i));
                    }
                    if(inputTransactionIKS.beasiswaAmounts.get(i) > 0f){
                        if(Beasiswa.transactOut(profil, transactionSummary.id, inputTransactionIKS.beasiswaAmounts.get(i))){
                        IKSTransactionDetail iksTransactionDetail = new IKSTransactionDetail(iksTDetailUUID, 
                                                                                            iksFromDB.id, 
                                                                                            clerk.id, 
                                                                                            transactionSummary.id, profil.noInduk, profil.currentLevel.level1, beasiswaAmounts.get(i), TransactionDetail.PaymentMethod.BEASISWA,
                                                                                            inputTransactionIKS.jTableIKS.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                        iksTransactionDetails.add(iksTransactionDetail);
                        }
                    }
                    if(inputTransactionIKS.beasiswaCostAmounts.get(i) > 0f){
                        IKSTransactionDetail iksTransactionDetail = new IKSTransactionDetail(iksTDetailUUID, 
                                                                                            iksFromDB.id, 
                                                                                            clerk.id, 
                                                                                            transactionSummary.id, profil.noInduk, profil.currentLevel.level1, beasiswaCostAmounts.get(i), TransactionDetail.PaymentMethod.BEASISWA_COST,
                                                                                            inputTransactionIKS.jTableIKS.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                        iksTransactionDetails.add(iksTransactionDetail);
                        BeasiswaCost.transactOut(profil, transactionSummary.id, inputTransactionIKS.beasiswaCostAmounts.get(i));
                    }
                    Control.insertTDetails(TransactionDetail.Tipe.IKSTransaction, iksTransactionDetails);
                    iksTransactionDetails.clear();
                    iksTransactionDetails = new ArrayList(Control.selectTDetails(TransactionDetail.Tipe.IKSTransaction, TransactionDetail.uuidColName, false, iksTDetailUUID.toString()));
                    for(int j = 0 ; j < iksTransactionDetails.size(); j++){
                        iksFromDB.entries.get(i).transactDetailIDs.add(iksTransactionDetails.get(j).id);
                        iksFromDB.entries.get(i).debt -= iksTransactionDetails.get(j).amount;
                    }
    //                Control.insertTDetail(TransactionDetail.Tipe.IKSTransaction, iksTransactionDetail);
    //                iksTransactionDetail = Control.selectTDetail(TransactionDetail.Tipe.IKSTransaction, TransactionDetail.uuidColName, false, iksTDetailUUID.toString());
    //                iksStoreToDB.entries.get(i).transactDetailIDs.add(iksTransactionDetail.id);

                    }
                }
            }
            iksStoreToDB.id = iksFromDB.id;
            iksStoreToDB.noInduk = iksFromDB.noInduk;
            iksStoreToDB.chargedLevel = iksFromDB.chargedLevel;

            for(int i = 0 ; i < iksStoreToDB.entries.size(); i++){
                if(iksStoreToDB.entries.get(i) != null){
                    iksStoreToDB.entries.get(i).amount = iksFromDB.entries.get(i).amount;
                    iksStoreToDB.entries.get(i).debt = iksFromDB.entries.get(i).debt;
                    iksStoreToDB.entries.get(i).transactDetailIDs = iksFromDB.entries.get(i).transactDetailIDs;

                }
            }
            Control.updateIuran(Iuran.Tipe.IKS, iksStoreToDB);
        
            this.transactionSummary.totalAmount = totalAmount;
            Control.updateTSummary(transactionSummary);

            }
        
        //PART PVT
        if(jTextFieldPVTAmountSimple.getValue() != null){
            for(int i = 0 ; i < 1 ; i++){
                pvtTDetailUUID = UUID.randomUUID();
                if(pvtStoreToDB.entries.get(i)!=null){
                    if(!inputTransactionPVT.isPVTEnough(pvtFromDB.entries.get(i).transactDetailIDs, pvtFromDB.entries.get(i).amount)){
                        ArrayList<PVTTransactionDetail> pvtTransactionDetails = new ArrayList();
                        if(inputTransactionPVT.pVTAmounts.get(i) > 0f){
                            PVTTransactionDetail pvtTransactionDetail = new PVTTransactionDetail(pvtTDetailUUID, 
                                                                                                pvtFromDB.id, 
                                                                                                clerk.id, 
                                                                                                transactionSummary.id, profil.noInduk, profil.currentLevel.level1, (inputTransactionPVT.pVTAmounts.get(i)), TransactionDetail.PaymentMethod.CASH,
                                                                                                inputTransactionPVT.jTablePVT.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);

                            pvtTransactionDetails.add(pvtTransactionDetail);
                        }
                        if(inputTransactionPVT.iDDAmounts.get(i) > 0f){
                            PVTTransactionDetail pvtTransactionDetail = new PVTTransactionDetail(pvtTDetailUUID, 
                                                                                                pvtFromDB.id, 
                                                                                                clerk.id, 
                                                                                                transactionSummary.id, profil.noInduk, profil.currentLevel.level1, inputTransactionPVT.iDDAmounts.get(i), TransactionDetail.PaymentMethod.IDD,
                                                                                                inputTransactionPVT.jTablePVT.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                            pvtTransactionDetails.add(pvtTransactionDetail);
                            IDD.transactOut(profil, transactionSummary.id, inputTransactionPVT.iDDAmounts.get(i));
                        }
                        if(inputTransactionPVT.beasiswaAmounts.get(i) > 0f){
                            if(Beasiswa.transactOut(profil, transactionSummary.id, inputTransactionPVT.beasiswaAmounts.get(i))){
                            PVTTransactionDetail pvtTransactionDetail = new PVTTransactionDetail(pvtTDetailUUID, 
                                                                                                pvtFromDB.id, 
                                                                                                clerk.id, 
                                                                                                transactionSummary.id, profil.noInduk, profil.currentLevel.level1, beasiswaAmounts.get(i), TransactionDetail.PaymentMethod.BEASISWA,
                                                                                                inputTransactionPVT.jTablePVT.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                            pvtTransactionDetails.add(pvtTransactionDetail);
                            }
                        }
                        if(inputTransactionPVT.beasiswaCostAmounts.get(i) > 0f){
                            PVTTransactionDetail pvtTransactionDetail = new PVTTransactionDetail(pvtTDetailUUID, 
                                                                                                pvtFromDB.id, 
                                                                                                clerk.id, 
                                                                                                transactionSummary.id, profil.noInduk, profil.currentLevel.level1, beasiswaCostAmounts.get(i), TransactionDetail.PaymentMethod.BEASISWA_COST,
                                                                                                inputTransactionPVT.jTablePVT.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                            pvtTransactionDetails.add(pvtTransactionDetail);
                            BeasiswaCost.transactOut(profil, transactionSummary.id, inputTransactionPVT.beasiswaCostAmounts.get(i));
                        }
                        Control.insertTDetails(TransactionDetail.Tipe.PVTTransaction, pvtTransactionDetails);
                        pvtTransactionDetails.clear();
                        pvtTransactionDetails = new ArrayList(Control.selectTDetails(TransactionDetail.Tipe.PVTTransaction, TransactionDetail.uuidColName, false, pvtTDetailUUID.toString()));
                        for(int j = 0 ; j < pvtTransactionDetails.size(); j++){
                            pvtFromDB.entries.get(i).transactDetailIDs.add(pvtTransactionDetails.get(j).id);
                            pvtFromDB.entries.get(i).debt -= pvtTransactionDetails.get(j).amount;
                        }
                    }
                }
            }
            pvtStoreToDB.id = pvtFromDB.id;
            pvtStoreToDB.noInduk = pvtFromDB.noInduk;
            pvtStoreToDB.chargedLevel = pvtFromDB.chargedLevel;

            for(int i = 0 ; i < pvtStoreToDB.entries.size(); i++){
                if(pvtStoreToDB.entries.get(i) != null){
                    pvtStoreToDB.entries.get(i).amount = pvtFromDB.entries.get(i).amount;
                    pvtStoreToDB.entries.get(i).debt = pvtFromDB.entries.get(i).debt;
                    pvtStoreToDB.entries.get(i).transactDetailIDs = pvtFromDB.entries.get(i).transactDetailIDs;

                }
            }
            Control.updateIuran(Iuran.Tipe.PVT, pvtStoreToDB);
            this.transactionSummary.totalAmount = totalAmount;
            Control.updateTSummary(transactionSummary);
        }
        
        //PART OSIS
        if(jTextFieldOSISAmountSimple.getValue() != null){
            for(int i = 0 ; i < 1 ; i++){
                osisTDetailUUID = UUID.randomUUID();
                if(osisStoreToDB.entries.get(i)!=null){
                    if(!inputTransactionOSIS.isOSISEnough(osisFromDB.entries.get(i).transactDetailIDs, osisFromDB.entries.get(i).amount)){
                        ArrayList<OSISTransactionDetail> osisTransactionDetails = new ArrayList();
                        if(inputTransactionOSIS.oSISAmounts.get(i) > 0f){
                            OSISTransactionDetail osisTransactionDetail = new OSISTransactionDetail(osisTDetailUUID, 
                                                                                                osisFromDB.id, 
                                                                                                clerk.id, 
                                                                                                transactionSummary.id, profil.noInduk, profil.currentLevel.level1, (inputTransactionOSIS.oSISAmounts.get(i)), TransactionDetail.PaymentMethod.CASH,
                                                                                                inputTransactionOSIS.jTableOSIS.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);

                            osisTransactionDetails.add(osisTransactionDetail);
                        }
                        if(inputTransactionOSIS.iDDAmounts.get(i) > 0f){
                            OSISTransactionDetail osisTransactionDetail = new OSISTransactionDetail(osisTDetailUUID, 
                                                                                                osisFromDB.id, 
                                                                                                clerk.id, 
                                                                                                transactionSummary.id, profil.noInduk, profil.currentLevel.level1, inputTransactionOSIS.iDDAmounts.get(i), TransactionDetail.PaymentMethod.IDD,
                                                                                                inputTransactionOSIS.jTableOSIS.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                            osisTransactionDetails.add(osisTransactionDetail);
                            IDD.transactOut(profil, transactionSummary.id, inputTransactionOSIS.iDDAmounts.get(i));
                        }
                        if(inputTransactionOSIS.beasiswaAmounts.get(i) > 0f){
                            if(Beasiswa.transactOut(profil, transactionSummary.id, inputTransactionOSIS.beasiswaAmounts.get(i))){
                            OSISTransactionDetail osisTransactionDetail = new OSISTransactionDetail(osisTDetailUUID, 
                                                                                                osisFromDB.id, 
                                                                                                clerk.id, 
                                                                                                transactionSummary.id, profil.noInduk, profil.currentLevel.level1, beasiswaAmounts.get(i), TransactionDetail.PaymentMethod.BEASISWA,
                                                                                                inputTransactionOSIS.jTableOSIS.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                            osisTransactionDetails.add(osisTransactionDetail);
                            }
                        }
                        if(inputTransactionOSIS.beasiswaCostAmounts.get(i) > 0f){
                            OSISTransactionDetail osisTransactionDetail = new OSISTransactionDetail(osisTDetailUUID, 
                                                                                                osisFromDB.id, 
                                                                                                clerk.id, 
                                                                                                transactionSummary.id, profil.noInduk, profil.currentLevel.level1, beasiswaCostAmounts.get(i), TransactionDetail.PaymentMethod.BEASISWA_COST,
                                                                                                inputTransactionOSIS.jTableOSIS.getValueAt(i,0).toString().concat(" Tahun ajaran ").concat(jComboBoxTahun.getSelectedItem().toString()),false);
                            osisTransactionDetails.add(osisTransactionDetail);
                            BeasiswaCost.transactOut(profil, transactionSummary.id, inputTransactionOSIS.beasiswaCostAmounts.get(i));
                        }
                        Control.insertTDetails(TransactionDetail.Tipe.OSISTransaction, osisTransactionDetails);
                        osisTransactionDetails.clear();
                        osisTransactionDetails = new ArrayList(Control.selectTDetails(TransactionDetail.Tipe.OSISTransaction, TransactionDetail.uuidColName, false, osisTDetailUUID.toString()));
                        for(int j = 0 ; j < osisTransactionDetails.size(); j++){
                            osisFromDB.entries.get(i).transactDetailIDs.add(osisTransactionDetails.get(j).id);
                            osisFromDB.entries.get(i).debt -= osisTransactionDetails.get(j).amount;
                        }
                    }
                }
            }
            osisStoreToDB.id = osisFromDB.id;
            osisStoreToDB.noInduk = osisFromDB.noInduk;
            osisStoreToDB.chargedLevel = osisFromDB.chargedLevel;

            for(int i = 0 ; i < osisStoreToDB.entries.size(); i++){
                if(osisStoreToDB.entries.get(i) != null){
                    osisStoreToDB.entries.get(i).amount = osisFromDB.entries.get(i).amount;
                    osisStoreToDB.entries.get(i).debt = osisFromDB.entries.get(i).debt;
                    osisStoreToDB.entries.get(i).transactDetailIDs = osisFromDB.entries.get(i).transactDetailIDs;

                }
            }
            Control.updateIuran(Iuran.Tipe.OSIS, osisStoreToDB);
            this.transactionSummary.totalAmount = totalAmount;
            Control.updateTSummary(transactionSummary);
        }
    }

    private void prepareSubmitObjects() throws SQLException, KasirException {
        transactionList = new ArrayList<Transaction>();
        
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
            transactionList.add(new Transaction(Iuran.Tipe.IDD, iddTDetail.amount, iddTDetail.note));
        }
        
        //IPSP Part
        if(!jTextFieldIPSPAmountSimple.getText().equals("")){
            if((Long)(jTextFieldIPSPAmountSimple.getValue()) > 0F){
                IPSP ipsp = Control.selectIuran(Iuran.Tipe.IPSP, IPSP.noIndukColName,false, profil.noInduk);
                this.ipsp = ipsp;
                ipspTDetailUUID = UUID.randomUUID();
                IPSPTransactionDetail ipspTransactionDetail = new IPSPTransactionDetail(ipspTDetailUUID,ipsp.id, this.clerk.id, 0L, profil.noInduk, profil.currentLevel.level1, Float.valueOf(inputTransactionIPSP.jTextFieldIPSPAmount.getText()),
                                                                        (TransactionDetail.PaymentMethod)jComboBoxIPSP.getSelectedItem(), new sak.Kalender(System.currentTimeMillis()), null, inputTransactionIPSP.jTextFieldIPSPNote.getText(), false,false);
                theCreatedDate = new sak.Kalender(System.currentTimeMillis());
                this.iPSPTransactionDetail = ipspTransactionDetail;
                jTextFieldIPSPAmount1.setText(jTextFieldIPSPAmountSimple.getText());
                jTextFieldIPSPNote1.setText(inputTransactionIPSP.jTextFieldIPSPNote.getText());
                totalAmount = totalAmount + (((Long)jTextFieldIPSPAmountSimple.getValue()).floatValue());
                beasiswaRequest +=inputTransactionIPSP.ipspBeasiswaAmount;
                beasiswaCostRequest += inputTransactionIPSP.ipspBeasiswaCostAmount;
                transactionList.add(new Transaction(Iuran.Tipe.IPSP, ipspTransactionDetail.amount, ipspTransactionDetail.note));
            }
        }
        
        //PASB Part (automatic PASB)
        if(DBSR.dbURL.equals("jdbc:mysql://ark3.dayarka.com/rusly_ppdbdb")){
            PASB pasb = Control.selectIuran(Iuran.Tipe.PASB, PASB.noIndukColName,false, profil.noInduk);
            this.pasb = pasb;
            if(pasb != null){
                if(pasb.debt > 0f){
                    pasbTDetailUUID = UUID.randomUUID();
                    PASBTransactionDetail pasbTransactionDetail = new PASBTransactionDetail(pasbTDetailUUID,pasb.id, this.clerk.id, 0L, profil.noInduk, profil.currentLevel.level1, pasb.amount,
                                                                        TransactionDetail.PaymentMethod.CASH, new sak.Kalender(System.currentTimeMillis()), null, pasb.note, false,false);
                    theCreatedDate = new sak.Kalender(System.currentTimeMillis());
                    this.pasbTransactionDetail = pasbTransactionDetail;
                    totalAmount = totalAmount + pasb.amount;
                    transactionList.add(new Transaction(Iuran.Tipe.PASB, pasbTransactionDetail.amount, pasbTransactionDetail.note));
                }
            }
        }
        
        //IPSB Part
        if(!jTextFieldIPSBAmountSimple.getText().equals("")){
            if((Long)(jTextFieldIPSBAmountSimple.getValue()) > 0F){
                IPSB ipsb = Control.selectIuran(Iuran.Tipe.IPSB, IPSB.noIndukColName,false, profil.noInduk);
                this.ipsb = ipsb;
                ipsbTDetailUUID = UUID.randomUUID();
                IPSBTransactionDetail ipsbTransactionDetail = new IPSBTransactionDetail(ipsbTDetailUUID,ipsb.id, this.clerk.id, 0L, profil.noInduk, profil.currentLevel.level1, Float.valueOf(inputTransactionIPSB.jTextFieldIPSBAmount.getText()),
                                                                        (TransactionDetail.PaymentMethod.CASH), new sak.Kalender(System.currentTimeMillis()), null, inputTransactionIPSB.jTextFieldIPSBNote.getText(), false,false);
                theCreatedDate = new sak.Kalender(System.currentTimeMillis());
                this.iPSBTransactionDetail = ipsbTransactionDetail;
                //jTextFieldIPSBAmount1.setText(jTextFieldIPSBAmountSimple.getText());
                //jTextFieldIPSBNote1.setText(inputTransactionIPSB.jTextFieldIPSBNote.getText());
                totalAmount = totalAmount + (((Long)jTextFieldIPSBAmountSimple.getValue()).floatValue());
                transactionList.add(new Transaction(Iuran.Tipe.IPSB, ipsbTransactionDetail.amount, ipsbTransactionDetail.note));
            }
        }
        
        //IUA Part
        if(this.profil.currentLevel.level1.toString().equals("SMP") || this.profil.currentLevel.level1.toString().equals("SMK")){
            if(!jTextFieldIUAAmountSimple.getText().equals("")){
                if((Long)(jTextFieldIUAAmountSimple.getValue()) > 0F){
                    IUA iua = Control.selectIuran(Iuran.Tipe.IUA, IUA.noIndukColName,false, profil.noInduk);
                    this.iua = iua;
                    iuaTDetailUUID = UUID.randomUUID();
                    IUATransactionDetail iuaTransactionDetail = new IUATransactionDetail(iuaTDetailUUID,iua.id, this.clerk.id, 0L, profil.noInduk, profil.currentLevel.level1, Float.valueOf(inputTransactionIUA.jTextFieldIUAAmount.getText()),
                                                                            (TransactionDetail.PaymentMethod.CASH), new sak.Kalender(System.currentTimeMillis()), null, inputTransactionIUA.jTextFieldIUANote.getText(), false,false);
                    theCreatedDate = new sak.Kalender(System.currentTimeMillis());
                    this.iUATransactionDetail = iuaTransactionDetail;
                    //jTextFieldIUAAmount1.setText(jTextFieldIUAAmountSimple.getText());
                    //jTextFieldIUANote1.setText(inputTransactionIUA.jTextFieldIUANote.getText());
                    totalAmount = totalAmount + (((Long)jTextFieldIUAAmountSimple.getValue()).floatValue());
                    transactionList.add(new Transaction(Iuran.Tipe.IUA, iuaTransactionDetail.amount, iuaTransactionDetail.note));
                }
            }
        }
//Seragam PART
        if(seragamSToDB != null){
            this.tableModelSeragam = inputTransactionSeragam.buildSeragamSubmitTableModel(this.profil, seragamSToDB);
            jTableSeragam = new JTable();
            jTableSeragam.setModel(this.tableModelSeragam);
            jTableDialogSeragam.setModel(this.tableModelSeragam);
            for(int i = 0 ; i < seragamSToDB.size(); i++){
                
                    totalAmount = totalAmount + seragamSToDB.get(i).amount;
                    transactionList.add(new Transaction(Iuran.Tipe.Seragam, seragamSToDB.get(i).amount, seragamSToDB.get(i).transactName));
            }
        }
        
//Almamater PART
        if(almamaterSToDB != null){
            this.tableModelAlmamater = inputTransactionAlmamater.buildAlmamaterSubmitTableModel(this.profil, almamaterSToDB);
            jTableAlmamater = new JTable();
            jTableAlmamater.setModel(this.tableModelAlmamater);
            jTableDialogAlmamater.setModel(this.tableModelAlmamater);
            for(int i = 0 ; i < almamaterSToDB.size(); i++){
                
                    totalAmount = totalAmount + almamaterSToDB.get(i).amount;
                    transactionList.add(new Transaction(Iuran.Tipe.Almamater, almamaterSToDB.get(i).amount, almamaterSToDB.get(i).transactName));
            }
        }
        
//Attribute PART
        if(attributeSToDB != null){
            this.tableModelAttribute = inputTransactionAttribute.buildAttributeSubmitTableModel(this.profil, attributeSToDB);
            for(int i = 0 ; i < attributeSToDB.size(); i++){
                
                    totalAmount = totalAmount + attributeSToDB.get(i).amount;
                    transactionList.add(new Transaction(Iuran.Tipe.Attribute, attributeSToDB.get(i).amount, attributeSToDB.get(i).transactName));
            }
        }
        
//Buku PART
        if(bukuSToDB != null){
            this.tableModelBuku = inputTransactionBuku.buildBukuSubmitTableModel(this.profil, bukuSToDB);
            jTableBuku = new JTable();
            jTableBuku.setModel(this.tableModelBuku);
            jTableDialogBuku.setModel(this.tableModelBuku);
            for(int i = 0 ; i < bukuSToDB.size(); i++){
                
                    totalAmount = totalAmount + bukuSToDB.get(i).amount;
                    transactionList.add(new Transaction(Iuran.Tipe.Buku, bukuSToDB.get(i).amount, bukuSToDB.get(i).transactName));
            }
        }
//ILL PART
        if(illSToDB != null){
            this.tableModelILL = inputTransactionILL.buildILLSubmitTableModel(this.profil, illSToDB);
//            jTableILL = new JTable();
//            jTableILL.setModel(this.tableModelILL);
//            jTableDialogILL.setModel(this.tableModelILL);
            for(int i = 0 ; i < illSToDB.size(); i++){
                
                    totalAmount = totalAmount + illSToDB.get(i).amount;
                    transactionList.add(new Transaction(Iuran.Tipe.ILL, illSToDB.get(i).amount, illSToDB.get(i).transactName));
            }
        }     
        
        
//Tabungan PART
        if(tabunganSToDB != null){
            this.tableModelTabungan = inputTransactionTabungan.buildTabunganSubmitTableModel(this.profil, tabunganSToDB);
//            jTableTabungan = new JTable();
//            jTableTabungan.setModel(this.tableModelTabungan);
//            jTableDialogTabungan.setModel(this.tableModelTabungan);
            for(int i = 0 ; i < tabunganSToDB.size(); i++){
                
                    totalAmount = totalAmount + tabunganSToDB.get(i).amount;
                    transactionList.add(new Transaction(Iuran.Tipe.Tabungan, tabunganSToDB.get(i).amount, tabunganSToDB.get(i).transactName));
            }
        }  
        
        //Sumbangan PART
        if(sumbanganSToDB != null){
            this.tableModelSumbangan = inputTransactionSumbangan.buildSumbanganSubmitTableModel(this.profil, sumbanganSToDB);
//            jTableSumbangan = new JTable();
//            jTableSumbangan.setModel(this.tableModelSumbangan);
//            jTableDialogSumbangan.setModel(this.tableModelSumbangan);
            for(int i = 0 ; i < sumbanganSToDB.size(); i++){
                
                    totalAmount = totalAmount + sumbanganSToDB.get(i).amount;
                    transactionList.add(new Transaction(Iuran.Tipe.Sumbangan, sumbanganSToDB.get(i).amount, sumbanganSToDB.get(i).transactName));
            }
        }    
        
        
//IPP PART
        if(jTextFieldIPPAmountSimple.getValue() != null){
            this.tableModelIPP = inputTransactionIPP.buildIPPSubmitTableModel(this.profil, this.tahunIPP.get(jComboBoxTahun.getSelectedIndex()));
            jTableIPP.setModel(this.tableModelIPP);
//            jTableIPP1.setModel(this.tableModelIPP);
            
            
            for(int i = 0 ; i<12; i++){
                if(ippStoreToDB.entries.get(i) != null){
                    //totalAmount = totalAmount + (ippFromDB.entries.get(i).amount - iDDAmounts.get(i));
                    totalAmount += iDDAmounts.get(i)+inputTransactionIPP.iPPAmounts.get(i)+inputTransactionIPP.beasiswaAmounts.get(i)+inputTransactionIPP.beasiswaCostAmounts.get(i);
                    beasiswaRequest +=inputTransactionIPP.beasiswaAmounts.get(i);
                    beasiswaCostRequest += inputTransactionIPP.beasiswaCostAmounts.get(i);
                    iddRequest += iDDAmounts.get(i);
                    if(ippStoreToDB.entries.get(i).amount != 0.0f)
                    transactionList.add(new Transaction(Iuran.Tipe.IPP, ippStoreToDB.entries.get(i).amount, "IPP Bulan ".concat(getMonthName(i+1))));
                }
            }
            
        }
        
        //IUAP PART
        if(this.profil.currentLevel.level1.toString().equals("SMA")){
            if(jTextFieldIUAAmountSimple.getValue() != null){
                this.tableModelIUAP = inputTransactionIUAP.buildIUAPSubmitTableModel(this.profil, this.tahunIUAP.get(jComboBoxTahunIUAP.getSelectedIndex()));
                jTableIUAP.setModel(this.tableModelIUAP);
    //            jTableIUAP1.setModel(this.tableModelIUAP);


                for(int i = 0 ; i<12; i++){
                    if(iuapStoreToDB.entries.get(i) != null){
                        //totalAmount = totalAmount + (iuapFromDB.entries.get(i).amount - iDDAmounts.get(i));
                        totalAmount += iDDAmounts.get(i)+inputTransactionIUAP.iUAPAmounts.get(i)+inputTransactionIUAP.beasiswaAmounts.get(i)+inputTransactionIUAP.beasiswaCostAmounts.get(i);
                        beasiswaRequest +=inputTransactionIUAP.beasiswaAmounts.get(i);
                        beasiswaCostRequest += inputTransactionIUAP.beasiswaCostAmounts.get(i);
                        iddRequest += iDDAmounts.get(i);
                        if(iuapStoreToDB.entries.get(i).amount != 0.0f)
                        transactionList.add(new Transaction(Iuran.Tipe.IUAP, iuapStoreToDB.entries.get(i).amount, "IUAP Bulan ".concat(getMonthName(i+1))));
                    }
                }
            }
        }        
        //CicilanHutang PART
        if(jTextFieldCicilanHutangAmountSimple.getValue() != null){
            this.tableModelCicilanHutang = inputTransactionCicilanHutang.buildCicilanHutangSubmitTableModel(this.profil, this.tahunCicilanHutang.get(jComboBoxTahun.getSelectedIndex()));
            jTableCicilanHutang.setModel(this.tableModelCicilanHutang);
//            jTableCicilanHutang1.setModel(this.tableModelCicilanHutang);
            
            
            for(int i = 0 ; i<12; i++){
                if(cicilanHutangStoreToDB.entries.get(i) != null){
                    //totalAmount = totalAmount + (cicilanHutangFromDB.entries.get(i).amount - iDDAmounts.get(i));
                    totalAmount += iDDAmounts.get(i)+inputTransactionCicilanHutang.cicilanHutangAmounts.get(i)+inputTransactionCicilanHutang.beasiswaAmounts.get(i)+inputTransactionCicilanHutang.beasiswaCostAmounts.get(i);
                    beasiswaRequest +=inputTransactionCicilanHutang.beasiswaAmounts.get(i);
                    beasiswaCostRequest += inputTransactionCicilanHutang.beasiswaCostAmounts.get(i);
                    iddRequest += iDDAmounts.get(i);
                    if(cicilanHutangStoreToDB.entries.get(i).amount != 0.0f)
                    transactionList.add(new Transaction(Iuran.Tipe.CicilanHutang, cicilanHutangStoreToDB.entries.get(i).amount, "CicilanHutang Bulan ".concat(getMonthName(i+1))));
                }
            }
            
        }
        
        //IUS PART
        if(jTextFieldIUSAmountSimple.getValue() != null){
            this.tahunIUS = inputTransactionIUS.tahunIUS;
            this.tableModelIUS = inputTransactionIUS.buildIUSSubmitTableModel(this.profil, this.tahunIUS.get(inputTransactionIUS.jComboBoxTahun.getSelectedIndex()));
            //jTableIUS.setModel(this.tableModelIUS);
//            jTableIUS1.setModel(this.tableModelIUS);
            
            
            for(int i = 0 ; i<2; i++){
                if(iusStoreToDB.entries.get(i) != null){
                    //totalAmount = totalAmount + (iusFromDB.entries.get(i).amount - iDDAmounts.get(i));
                    totalAmount += inputTransactionIUS.iDDAmounts.get(i)+inputTransactionIUS.iUSAmounts.get(i)+inputTransactionIUS.beasiswaAmounts.get(i)+inputTransactionIUS.beasiswaCostAmounts.get(i);
                    beasiswaRequest +=inputTransactionIUS.beasiswaAmounts.get(i);
                    beasiswaCostRequest += inputTransactionIUS.beasiswaCostAmounts.get(i);
                    iddRequest += inputTransactionIUS.iDDAmounts.get(i);
                    transactionList.add(new Transaction(Iuran.Tipe.IUS, iusStoreToDB.entries.get(i).amount, "IUS Semester "));
                }
            }
            
        }
        
        //IKS PART
        if(jTextFieldIKSAmountSimple.getValue() != null){
            this.tahunIKS = inputTransactionIKS.tahunIKS;
            this.tableModelIKS = inputTransactionIKS.buildIKSSubmitTableModel(this.profil, this.tahunIKS.get(inputTransactionIKS.jComboBoxTahun.getSelectedIndex()));
            for(int i = 0 ; i<1; i++){
                if(iksStoreToDB.entries.get(i) != null){
                    totalAmount += inputTransactionIKS.iDDAmounts.get(i)+inputTransactionIKS.iKSAmounts.get(i)+inputTransactionIKS.beasiswaAmounts.get(i)+inputTransactionIKS.beasiswaCostAmounts.get(i);
                    beasiswaRequest +=inputTransactionIKS.beasiswaAmounts.get(i);
                    beasiswaCostRequest += inputTransactionIKS.beasiswaCostAmounts.get(i);
                    iddRequest += inputTransactionIKS.iDDAmounts.get(i);
                    transactionList.add(new Transaction(Iuran.Tipe.IKS, iksStoreToDB.entries.get(i).amount, "IKS Semester "));
                }
            }
        }
        
        //PVT PART
        if(jTextFieldPVTAmountSimple.getValue() != null){
            this.tahunPVT = inputTransactionPVT.tahunPVT;
            this.tableModelPVT = inputTransactionPVT.buildPVTSubmitTableModel(this.profil, this.tahunPVT.get(inputTransactionPVT.jComboBoxTahun.getSelectedIndex()));
            for(int i = 0 ; i<1; i++){
                if(pvtStoreToDB.entries.get(i) != null){
                    totalAmount += inputTransactionPVT.iDDAmounts.get(i)+inputTransactionPVT.pVTAmounts.get(i)+inputTransactionPVT.beasiswaAmounts.get(i)+inputTransactionPVT.beasiswaCostAmounts.get(i);
                    beasiswaRequest +=inputTransactionPVT.beasiswaAmounts.get(i);
                    beasiswaCostRequest += inputTransactionPVT.beasiswaCostAmounts.get(i);
                    iddRequest += inputTransactionPVT.iDDAmounts.get(i);
                    transactionList.add(new Transaction(Iuran.Tipe.PVT, pvtStoreToDB.entries.get(i).amount, "PVT Semester "));
                }
            }
        }
        
        //OSIS PART
        if(jTextFieldOSISAmountSimple.getValue() != null){
            this.tahunOSIS = inputTransactionOSIS.tahunOSIS;
            this.tableModelOSIS = inputTransactionOSIS.buildOSISSubmitTableModel(this.profil, this.tahunOSIS.get(inputTransactionOSIS.jComboBoxTahun.getSelectedIndex()));
            for(int i = 0 ; i<1; i++){
                if(osisStoreToDB.entries.get(i) != null){
                    totalAmount += inputTransactionOSIS.iDDAmounts.get(i)+inputTransactionOSIS.oSISAmounts.get(i)+inputTransactionOSIS.beasiswaAmounts.get(i)+inputTransactionOSIS.beasiswaCostAmounts.get(i);
                    beasiswaRequest +=inputTransactionOSIS.beasiswaAmounts.get(i);
                    beasiswaCostRequest += inputTransactionOSIS.beasiswaCostAmounts.get(i);
                    iddRequest += inputTransactionOSIS.iDDAmounts.get(i);
                    transactionList.add(new Transaction(Iuran.Tipe.OSIS, osisStoreToDB.entries.get(i).amount, "OSIS Semester "));
                }
            }
        }
        
        
        
        
        
        allTransactionTableModel = buildAllTransactionTableModel(transactionList);
        allTransactionTable.setModel(allTransactionTableModel);
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
            data[i][3] = 0f;
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
    
    private TableModel buildAlmamaterTableModel(Profil profil) throws SQLException, KasirException{
       String columnNames[] = {"Transaksi Almamater", "Biaya", "Catatan", "Pembayaran"};
       Set<Almamater> almamaterFilters = new HashSet<>();
       almamaterFilters.add(new Almamater(profil.noInduk,null, null, 0F, null));
       Map<Long, Almamater> searchResultMap = new HashMap<>();
       searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.Almamater, almamaterFilters);
       
       int j = 0;
       almamaterS = new ArrayList<>();       
       if(searchResultMap.size() > 0){
        for(Map.Entry<Long, Almamater> entry: searchResultMap.entrySet()){
            if(entry.getValue().transactDetailIDs.size() == 0){
                almamaterS.add(entry.getValue());
            }
            j++;
        }
       }
       Object[][] data = new Object[almamaterS.size()][5];
       for(int i =0; i < almamaterS.size(); i++){
            data[i][0]= almamaterS.get(i).transactName;
            data[i][1]= almamaterS.get(i).amount;
            data[i][2]= almamaterS.get(i).note;
            data[i][3]= 0f;
//            if(almamaterS.get(i).transactDetailIDs.size()>0){
//                data[i][3] = new Boolean(true);
//            }else{
//                data[i][3] = new Boolean(false);
//            }
       }
       calculateUnpaidAlmamater(profil);
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
    
    private TableModel buildAlmamaterSubmitTableModel(Profil profil, List<Almamater> stdb) throws SQLException, KasirException{
       String columnNames[] = {"Transaksi Almamater", "Biaya", "Catatan", "Pembayaran"};
       
       Set<Almamater> almamaterFilters = new HashSet<>();
       almamaterFilters.addAll(stdb);
       Map<Long, Almamater> searchResultMap = new HashMap<>();
       searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.Almamater, almamaterFilters);
       
       int j = 0;
       almamaterS = new ArrayList<>();       
       if(searchResultMap.size() > 0){
        for(Map.Entry<Long, Almamater> entry: searchResultMap.entrySet()){
            if(entry.getValue().transactDetailIDs.size() == 0){
                almamaterS.add(entry.getValue());
            }
            j++;
        }
       }
       Object[][] data = new Object[almamaterS.size()][5];
       for(int i =0; i < almamaterS.size(); i++){
            data[i][0]= almamaterS.get(i).transactName;
            data[i][1]= almamaterS.get(i).amount;
            data[i][2]= almamaterS.get(i).note;
            data[i][3] = 0f;
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
    
    private ComboBoxModel buildIUAPtahunComboBoxModel(Profil profil) throws SQLException, KasirException {
       Set<IUAP> iuapFilters = new HashSet<>();
       ArrayList<Entry> entries = new ArrayList();
       iuapFilters.add(new IUAP(profil.noInduk, null, entries));
       Map<Long, IUAP> searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.IUAP, iuapFilters);
       this.tahunIUAP = new ArrayList<>();
       List<String> tahunAjaran = new ArrayList<>();
       Object[][] data = new Object[searchResultMap.size()][12];
       int i = 0;
       if(searchResultMap.size() > 0){
        for(Map.Entry<Long, IUAP> entry: searchResultMap.entrySet()){
            data[i][0]= entry.getValue().entries.get(i);
            data[i][1]= entry.getValue().entries.get(i).amount;
            
            this.tahunIUAP.add(entry.getValue().chargedLevel.tahun);
            tahunAjaran.add(String.valueOf(entry.getValue().chargedLevel.tahun).concat(" - ").concat(String.valueOf(entry.getValue().chargedLevel.tahun+1)));
            i++;
        }
       }
       calculateUnpaidIUAP(profil, tahunIUAP);
       //== bikin tunggakan beans ===
       
       
       //== end bikin tunggakan beans ===
       
       tahunComboBoxModel = new DefaultComboBoxModel(tahunAjaran.toArray());
       return tahunComboBoxModel;
    }
    
    private ComboBoxModel buildCicilanHutangtahunComboBoxModel(Profil profil) throws SQLException, KasirException {
       Set<CicilanHutang> cicilanHutangFilters = new HashSet<>();
       ArrayList<Entry> entries = new ArrayList();
       cicilanHutangFilters.add(new CicilanHutang(profil.noInduk, null, entries));
       Map<Long, CicilanHutang> searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.CicilanHutang, cicilanHutangFilters);
       this.tahunCicilanHutang = new ArrayList<>();
       List<String> tahunAjaran = new ArrayList<>();
       Object[][] data = new Object[searchResultMap.size()][12];
       int i = 0;
       if(searchResultMap.size() > 0){
        for(Map.Entry<Long, CicilanHutang> entry: searchResultMap.entrySet()){
            data[i][0]= entry.getValue().entries.get(i);
            data[i][1]= entry.getValue().entries.get(i).amount;
            
            this.tahunCicilanHutang.add(entry.getValue().chargedLevel.tahun);
            tahunAjaran.add(String.valueOf(entry.getValue().chargedLevel.tahun).concat(" - ").concat(String.valueOf(entry.getValue().chargedLevel.tahun+1)));
            i++;
        }
       }
       calculateUnpaidCicilanHutang(profil, tahunCicilanHutang);
       //== bikin tunggakan beans ===
       
       
       //== end bikin tunggakan beans ===
       
       tahunComboBoxModel = new DefaultComboBoxModel(tahunAjaran.toArray());
       return tahunComboBoxModel;
    }

    private TableModel buildIPPTableModel(Profil profil, int tahun) throws SQLException, KasirException {
       String columnNames[] = {"Bulan", "Biaya IPP", "Check Box", "Tunai", "Iuran Dibayar Dimuka", "Beasiswa", "Beasiswa Yayasan"};
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
       String columnNames[] = {"Bulan", "Biaya IPP", "Check Box", "Tunai", "Iuran Dibayar Dimuka", "Beasiswa", "Beasiswa Yayasan"};
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
    
    private TableModel buildTunggakanAllTahunTableModel(Profil profil) throws SQLException, KasirException {
       float temp= 0f;
       Set<IPP> ippFilters = new HashSet<>();
       ArrayList<Entry> entries = new ArrayList();
       ippFilters.add(new IPP(profil.noInduk, null, entries));
       Map<Long, IPP> searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.IPP, ippFilters);
       this.tahunIPP = new ArrayList<>();
       List<String> tahunAjaran = new ArrayList<>();
       List<Integer> tahunAjaranInt = new ArrayList<>();
       int i = 0;
       if(searchResultMap.size() > 0){
        for(Map.Entry<Long, IPP> entry: searchResultMap.entrySet()){
            tahunAjaran.add(String.valueOf(entry.getValue().chargedLevel.tahun).concat(" - ").concat(String.valueOf(entry.getValue().chargedLevel.tahun+1)));
            tahunAjaranInt.add(entry.getValue().chargedLevel.tahun);
            i++;
        }
       }
       tahunAjaran.add(0, "Total Tunggakan");
       Object[][] data = new Object[18][tahunAjaran.size()];
       Float[] tunggakanPerYear = new Float[tahunAjaran.size()];
       for(int j = 0 ; j < tahunAjaran.size(); j++){
           tunggakanPerYear[j] = 0f;
       }
       JFormattedTextField tungs = new JFormattedTextField();
       tungs.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("Rp #,##0"))));
       //Tunggakan Total dan Tunggakan Per TP
       //IPP
//       Float totalTunggakanIPP = 0f;
//        for(int j = 0 ; j < tahunAjaranInt.size(); j++){
//            totalTunggakanIPP += calculateUnpaidIPP(profil, tahunAjaranInt.subList(j, j+1));
//            tungs.setValue(calculateUnpaidIPP(profil, tahunAjaranInt.subList(j, j+1)));
//            data[0][j+1]=tungs.getText();
//        }
//       jTotalTunggakanIPP = new JFormattedTextField(totalTunggakanIPP);
//       jTotalTunggakanIPP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("Rp #,##0"))));
//       data[0][0] = jTotalTunggakanIPP.getText();
       
       
       //IPP
       temp = 0f;
       for(int j = 0 ; j < tahunAjaranInt.size(); j++){
           if(tIPPs.isEmpty()){
               tungs.setValue(0f);
               data[0][j+1] = tungs.getText();
           }
           for(int k = 0 ; k < tIPPs.size(); k++){
               if(tIPPs.get(k).chargedLevel.tahun == tahunAjaranInt.get(j)){
                   for(Entry e : tIPPs.get(k).entries){
                       temp += e.debt;
                   }
                   tungs.setValue(temp);
                   data[0][j+1] = tungs.getText();
                   tunggakanPerYear[j] += temp;
               }
               temp = 0f;
           }
           if(data[0][j+1] == null){
               tungs.setValue(0f);
               data[0][j+1] = tungs.getText();
           }
       }
       if(tIPPs !=null){
        for(int j = 0 ; j< tIPPs.size(); j++){
            for(Entry e : tIPPs.get(j).entries){
                    temp += e.debt;
            }
        }
       }
       tungs.setValue(temp);
       data[0][0]=tungs.getText();
       temp =0f;
       
       
       //IPSP
       if(tIPSP != null){
        tungs.setValue(tIPSP.debt);
        tunggakanPerYear[0] += tIPSP.debt;
       }else{
           tungs.setValue(0f);
       }
       data[1][0] = tungs.getText();
       data[1][1] = tungs.getText();
       
       //PASB
       if(tPASB != null){
        tungs.setValue(tPASB.debt);
        tunggakanPerYear[0] += tPASB.debt;
       }else{
           tungs.setValue(0f);
       }
       
       //Seragam
       if(tSeragams != null){
        for(int j = 0 ; j < tahunAjaranInt.size(); j++){
            for(int k = 0 ; k < tSeragams.size(); k++){
                if(tSeragams.get(k).chargedLevel.tahun == tahunAjaranInt.get(j)){
                    temp += tSeragams.get(k).debt;
                    tungs.setValue(temp);
                    data[2][j+1] = tungs.getText();
                    tunggakanPerYear[j] += temp;
                }
            }
            temp = 0f;
        }
        for(int j = 0 ; j< tSeragams.size(); j++){
            temp += tSeragams.get(j).debt;
        }
        tungs.setValue(temp);
        data[2][0]=tungs.getText();
        temp =0f;
       }else{
           for(int j = 0 ; j < tahunAjaranInt.size(); j++){
               data[2][j+1] = 0;
           }
       }
       //BUKU
       temp = 0f;
       for(int j = 0 ; j < tahunAjaranInt.size(); j++){
           for(int k = 0 ; k < tBukus.size(); k++){
               if(tBukus.get(k).chargedLevel.tahun == tahunAjaranInt.get(j)){
                   temp += tBukus.get(k).debt;
                   tungs.setValue(temp);
                   data[4][j+1] = tungs.getText();
                   tunggakanPerYear[j] += temp;
               }
               
               
           }
           temp = 0f;
       }
       for(int j = 0 ; j< tBukus.size(); j++){
           temp += tBukus.get(j).debt;
       }
       tungs.setValue(temp);
       data[4][0]=tungs.getText();
       temp =0f;
       
       //IKS
       if(tIKSs != null){
        temp = 0f;
        for(int j = 0 ; j < tahunAjaranInt.size(); j++){
            for(int k = 0 ; k < tIKSs.size(); k++){
                if(tIKSs.get(k).chargedLevel.tahun == tahunAjaranInt.get(j)){
                    temp += tIKSs.get(k).entries.get(0).debt;
                    tungs.setValue(temp);
                    data[5][j+1] = tungs.getText();
                    tunggakanPerYear[j] += temp;
                }


            }
            temp = 0f;
        }
        for(int j = 0 ; j< tIKSs.size(); j++){
            temp += tIKSs.get(j).entries.get(0).debt;
        }
        tungs.setValue(temp);
        data[5][0]=tungs.getText();
        temp =0f;
       }else{
           for(int j = 0 ; j < tahunAjaranInt.size(); j++){
               data[5][j+1] = 0;
           }
       }
       
       //ILL
       temp = 0f;
       for(int j = 0 ; j < tahunAjaranInt.size(); j++){
           for(int k = 0 ; k < tILLs.size(); k++){
               if(tILLs.get(k).chargedLevel.tahun == tahunAjaranInt.get(j)){
                   temp += tILLs.get(k).debt;
                   tungs.setValue(temp);
                   data[6][j+1] = tungs.getText();
                   tunggakanPerYear[j] += temp;
               }
               
               
           }
           temp = 0f;
       }
       for(int j = 0 ; j< tILLs.size(); j++){
           temp += tILLs.get(j).debt;
       }
       tungs.setValue(temp);
       data[6][0]=tungs.getText();
       temp =0f;
       
       //IPSB
       if(tIPSB != null){
           tungs.setValue(tIPSB.debt);
           tunggakanPerYear[0] += tIPSB.debt;
       }else{
           tungs.setValue(0f);
       }
       data[7][0] = tungs.getText();
       data[7][1] = tungs.getText();
       
       
       //IUA
       if(this.profil.currentLevel.level1.equals("SMP") || this.profil.currentLevel.level1.equals("SMK")){
        if(tIUA != null){
         tungs.setValue(tIUA.debt);
         tunggakanPerYear[0] += tIUA.debt;
        }else{
            tungs.setValue(0f);
        }
        data[8][0] = tungs.getText();
        data[8][1] = tungs.getText();
       }else if(tIUAPs !=null){
       //IUAP
        temp = 0f;
        for(int j = 0 ; j < tahunAjaranInt.size(); j++){
            if(tIUAPs.isEmpty()){
                tungs.setValue(temp);
                data[8][j+1] = tungs.getText();
            }
            for(int k = 0 ; k < tIUAPs.size(); k++){
                if(tIUAPs.get(k).chargedLevel.tahun == tahunAjaranInt.get(j)){
                    for(Entry e : tIUAPs.get(k).entries){
                        temp += e.debt;
                    }
                    tungs.setValue(temp);
                    data[8][j+1] = tungs.getText();
                    tunggakanPerYear[j] += temp;
                }
                temp = 0f;
            }
            if(data[8][j+1] == null){
                tungs.setValue(0f);
                data[8][j+1] = tungs.getText();
            }
        }
        if(tIUAPs !=null){
         for(int j = 0 ; j< tIUAPs.size(); j++){
             for(Entry e : tIUAPs.get(j).entries){
                     temp += e.debt;
             }
         }
        }
        tungs.setValue(temp);
        data[8][0]=tungs.getText();
        temp =0f;
       }
       
       
       //IUS
       if(tIUSs != null){
        temp = 0f;
        for(int j = 0 ; j < tahunAjaranInt.size(); j++){
            for(int k = 0 ; k < tIUSs.size(); k++){
                if(tIUSs.get(k).chargedLevel.tahun == tahunAjaranInt.get(j)){
                    for(Entry e : tIUSs.get(k).entries){
                        temp += e.debt;
                    }
                    tungs.setValue(temp);
                    data[9][j+1] = tungs.getText();
                    tunggakanPerYear[j] += temp;
                }

                temp = 0f;
            }

        }
        for(int j = 0 ; j< tIUSs.size(); j++){
            for(Entry e : tIUSs.get(j).entries){
                    temp += e.debt;
            }
        }
        tungs.setValue(temp);
        data[9][0]=tungs.getText();
        temp =0f;
       }else{
           for(int j = 0 ; j < tahunAjaranInt.size(); j++){
               data[9][j+1] = 0;
           }
       }
       //OSIS
       temp = 0f;
       if(tOSISs != null){
        for(int j = 0 ; j < tahunAjaranInt.size(); j++){
            for(int k = 0 ; k < tOSISs.size(); k++){
                if(tOSISs.get(k).chargedLevel.tahun == tahunAjaranInt.get(j)){
                    for(Entry e : tOSISs.get(k).entries){
                        temp += e.debt;
                    }
                    tungs.setValue(temp);
                    data[10][j+1] = tungs.getText();
                    tunggakanPerYear[j] += temp;
                }
                temp = 0f;
            }

        }
        for(int j = 0 ; j< tOSISs.size(); j++){
            for(Entry e : tOSISs.get(j).entries){
                    temp += e.debt;
            }
        }
        tungs.setValue(temp);
        data[10][0]=tungs.getText();
        temp =0f;
       }else{
           for(int j = 0 ; j < tahunAjaranInt.size(); j++){
               data[10][j+1] = 0;
           }
       }
       //ATTRIBUTE
       temp = 0f;
       for(int j = 0 ; j < tahunAjaranInt.size(); j++){
           for(int k = 0 ; k < tAttributes.size(); k++){
               if(tAttributes.get(k).chargedLevel.tahun == tahunAjaranInt.get(j)){
                   temp += tAttributes.get(k).debt;
                   tungs.setValue(temp);
                   data[11][j+1] = tungs.getText();
                   tunggakanPerYear[j] += temp;
               }
               
               
           }
           temp = 0f;
       }
       for(int j = 0 ; j< tAttributes.size(); j++){
           temp += tAttributes.get(j).debt;
       }
       tungs.setValue(temp);
       data[11][0]=tungs.getText();
       temp =0f;
       
       //PVT
       
       temp = 0f;
       if(tPVTs != null){
       for(int j = 0 ; j < tahunAjaranInt.size(); j++){
           for(int k = 0 ; k < tPVTs.size(); k++){
               if(tPVTs.get(k).chargedLevel.tahun == tahunAjaranInt.get(j)){
                   temp += tPVTs.get(k).entries.get(0).debt;
                   tungs.setValue(temp);
                   data[12][j+1] = tungs.getText();
                   tunggakanPerYear[j] += temp;
               }
               
               
           }
           temp = 0f;
       }
       for(int j = 0 ; j< tPVTs.size(); j++){
           temp += tPVTs.get(j).entries.get(0).debt;
       }
       tungs.setValue(temp);
       data[12][0]=tungs.getText();
       temp =0f;
       }else{
           for(int j = 0 ; j < tahunAjaranInt.size(); j++){
               data[12][j+1] = 0f;
           }
       }
       
       //TABUNGAN
       temp = 0f;
       for(int j = 0 ; j < tahunAjaranInt.size(); j++){
           for(int k = 0 ; k < tTabungans.size(); k++){
               if(tTabungans.get(k).chargedLevel.tahun == tahunAjaranInt.get(j)){
                   temp += tTabungans.get(k).debt;
                   tungs.setValue(temp);
                   data[13][j+1] = tungs.getText();
                   tunggakanPerYear[j] += temp;
               }
               
               
           }
           temp = 0f;
       }
       for(int j = 0 ; j< tTabungans.size(); j++){
           temp += tTabungans.get(j).debt;
       }
       tungs.setValue(temp);
       data[13][0]=tungs.getText();
       temp =0f;
       
       //SUMBANGAN
       temp = 0f;
       for(int j = 0 ; j < tahunAjaranInt.size(); j++){
           for(int k = 0 ; k < tSumbangans.size(); k++){
               if(tSumbangans.get(k).chargedLevel.tahun == tahunAjaranInt.get(j)){
                   temp += tSumbangans.get(k).debt;
                   tungs.setValue(temp);
                   data[14][j+1] = tungs.getText();
                   tunggakanPerYear[j] += temp;
               }
               
               
           }
           temp = 0f;
       }
       for(int j = 0 ; j< tSumbangans.size(); j++){
           temp += tSumbangans.get(j).debt;
       }
       tungs.setValue(temp);
       data[14][0]=tungs.getText();
       
       //Cicilan Hutang
       temp = 0f;
       for(int j = 0 ; j < tahunAjaranInt.size(); j++){
           if(tCicilanHutangs != null){
            if(tCicilanHutangs.isEmpty()){
                data[15][j+1] = 0;
            }
            for(int k = 0 ; k < tCicilanHutangs.size(); k++){
                if(tCicilanHutangs.get(k).chargedLevel.tahun == tahunAjaranInt.get(j)){
                    for(Entry e : tCicilanHutangs.get(k).entries){
                        temp += e.debt;
                    }
                    tungs.setValue(temp);
                    data[15][j+1] = tungs.getText();
                    tunggakanPerYear[j] += temp;
                }
                temp = 0f;
            }
            if(data[15][j+1] == null){
                tungs.setValue(0f);
                data[15][j+1] = tungs.getText();
            }
           }
       }
       if(tCicilanHutangs !=null){
        for(int j = 0 ; j< tCicilanHutangs.size(); j++){
            for(Entry e : tCicilanHutangs.get(j).entries){
                    temp += e.debt;
            }
        }
       }
       tungs.setValue(temp);
       data[15][0]=tungs.getText();
       
        //Almamater
       if(tAlmamaters != null){
        for(int j = 0 ; j < tahunAjaranInt.size(); j++){
            for(int k = 0 ; k < tAlmamaters.size(); k++){
                if(tAlmamaters.get(k).chargedLevel.tahun == tahunAjaranInt.get(j)){
                    temp += tAlmamaters.get(k).debt;
                    tungs.setValue(temp);
                    data[16][j+1] = tungs.getText();
                    tunggakanPerYear[j] += temp;
                }
            }
            temp = 0f;
        }
        for(int j = 0 ; j< tAlmamaters.size(); j++){
            temp += tAlmamaters.get(j).debt;
        }
        tungs.setValue(temp);
        data[16][0]=tungs.getText();
        temp =0f;
       }else{
           for(int j = 0 ; j < tahunAjaranInt.size(); j++){
               data[16][j+1] = 0;
           }
       }
       
       temp =0f;
       
       for(int j = 0 ; j < tahunAjaranInt.size(); j++){
           tungs.setValue(tunggakanPerYear[j]);
           data[17][j+1] = tungs.getText();
           temp += tunggakanPerYear[j];
       }
       tungs.setValue(temp);
       data[17][0] = tungs.getText();
       
       Object[] columnNames = tahunAjaran.toArray();
       TableModel tm = new DefaultTableModel(data, columnNames){
            boolean[] chooseEdit = new boolean[]{false,false,true};
            
           @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false for editable
                    return false;
            }
            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }
       };
       tunggakanAllTahunTableModel = tm;
//       jTableTunggakanAll.setModel(tunggakanAllTahunTableModel);
       
       return tunggakanAllTahunTableModel;
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
                    if((entry.getValue().entries.get(j).transactDetailIDs.isEmpty()) 
                            && ((entry.getValue().chargedLevel.tahun < targetYear)? true:(entry.getValue().entries.get(j).period <= targetMonth))){
                        retVal += entry.getValue().entries.get(j).amount;
                    }else if(entry.getValue().entries.get(j).transactDetailIDs.size() > 0){
                        Float temp = 0f;
                        for(Long l : entry.getValue().entries.get(j).transactDetailIDs){
                            temp += Control.selectTDetail(TransactionDetail.Tipe.IPPTransaction, l).amount;
                        }
                        if(temp < entry.getValue().entries.get(j).amount){
                            retVal += temp;
                        } 
                    }
                }
            }
        }
       unpaidIPP = retVal;
       return retVal;
    }
    
    private float calculateUnpaidIUAP(Profil profil, List<Integer> tahuns) throws SQLException, KasirException {
       Set<IUAP> iuapFilters = new HashSet<>();
       float retVal = 0;
       ArrayList<Entry> entries = new ArrayList<>();
       iuapFilters.clear();
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
           iuapFilters.add(new IUAP(profil.noInduk, new Level(null,null,null,tahuns.get(i)), entries));
           
        }
       Map<Long, IUAP> searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.IUAP, iuapFilters);
        if(searchResultMap.size() > 0){
            for(Map.Entry<Long, IUAP> entry: searchResultMap.entrySet()){
                for(int j =0 ; j< entry.getValue().entries.size(); j++){
                    if((entry.getValue().entries.get(j).transactDetailIDs.isEmpty()) 
                            && ((entry.getValue().chargedLevel.tahun < targetYear)? true:(entry.getValue().entries.get(j).period <= targetMonth))){
                        retVal += entry.getValue().entries.get(j).amount;
                    }else if(entry.getValue().entries.get(j).transactDetailIDs.size() > 0){
                        Float temp = 0f;
                        for(Long l : entry.getValue().entries.get(j).transactDetailIDs){
                            temp += Control.selectTDetail(TransactionDetail.Tipe.IUAPTransaction, l).amount;
                        }
                        if(temp < entry.getValue().entries.get(j).amount){
                            retVal += temp;
                        } 
                    }
                }
            }
        }
       unpaidIUAP = retVal;
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
               Float totalAmountIPPTDetails = 0f;
               for(Long l : test.get(i).entries.get(j).transactDetailIDs){
                   totalAmountIPPTDetails += Control.selectTDetail(TransactionDetail.Tipe.IPPTransaction, l).amount;
               }
               if((totalAmountIPPTDetails < test.get(i).entries.get(j).amount)
                   && ((test.get(i).chargedLevel.tahun < targetYear? true: (test.get(i).entries.get(j).period <= targetMonth)))){
                    
                            
                            retVal += test.get(i).entries.get(j).amount-totalAmountIPPTDetails;
                            tunggakanIPPString = tunggakanIPPString.concat("IPP Bulan ");
                            tunggakanIPPString = tunggakanIPPString.concat(getMonthName(test.get(i).entries.get(j).period));
                            tunggakanIPPString = tunggakanIPPString.concat(" "+String.valueOf(test.get(i).chargedLevel.tahun)+"/"+String.valueOf(1+test.get(i).chargedLevel.tahun));
                            tunggakanIPPString = tunggakanIPPString.concat("\r\n");
                            
                            tunggakanIPPEachAmountString = tunggakanIPPEachAmountString.concat("Rp ");
                            tunggakanIPPEachAmountString = tunggakanIPPEachAmountString.concat(String.valueOf(Math.round(test.get(i).entries.get(j).amount-totalAmountIPPTDetails)));
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
    
    private float calculateUnpaidCicilanHutang(Profil profil, List<Integer> tahuns) throws SQLException, KasirException {
       Set<CicilanHutang> cicilanHutangFilters = new HashSet<>();
       float retVal = 0;
       ArrayList<Entry> entries = new ArrayList<>();
       cicilanHutangFilters.clear();
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
           cicilanHutangFilters.add(new CicilanHutang(profil.noInduk, new Level(null,null,null,tahuns.get(i)), entries));
           
        }
       Map<Long, CicilanHutang> searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.CicilanHutang, cicilanHutangFilters);
        if(searchResultMap.size() > 0){
            for(Map.Entry<Long, CicilanHutang> entry: searchResultMap.entrySet()){
                for(int j =0 ; j< entry.getValue().entries.size(); j++){
                    if((entry.getValue().entries.get(j).transactDetailIDs.isEmpty()) 
                            && ((entry.getValue().chargedLevel.tahun < targetYear)? true:(entry.getValue().entries.get(j).period <= targetMonth))){
                        retVal += entry.getValue().entries.get(j).amount;
                    }else if(entry.getValue().entries.get(j).transactDetailIDs.size() > 0){
                        Float temp = 0f;
                        for(Long l : entry.getValue().entries.get(j).transactDetailIDs){
                            temp += Control.selectTDetail(TransactionDetail.Tipe.CicilanHutangTransaction, l).amount;
                        }
                        if(temp < entry.getValue().entries.get(j).amount){
                            retVal += temp;
                        } 
                    }
                }
            }
        }
       unpaidCicilanHutang = retVal;
       return retVal;
    }
    
    private ArrayList<TunggakanBean> calculateUnpaidCicilanHutangs(ArrayList<Profil> profils, List<Integer> tahuns) throws SQLException, KasirException {
       Set<CicilanHutang> cicilanHutangFilters = new HashSet<>();
       ArrayList<TunggakanBean> retVals = new ArrayList<>();
       float retVal = 0;
       ArrayList<Entry> entries = new ArrayList<>();
       cicilanHutangFilters.clear();
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
                cicilanHutangFilters.add(new CicilanHutang(profils.get(i).noInduk, new Level(null,null,null,tahuns.get(j)), entries));

            }
       }
       List<CicilanHutang> test = Control.selectIurans(Iuran.Tipe.CicilanHutang, CicilanHutang.noIndukColName, false, noInduks.toArray(new String[0]));
       
       for(int i = 0 ; i< test.size(); i++){
           TunggakanBean tb = new TunggakanBean();
           for(int j = 0 ; j < test.get(i).entries.size(); j++){
               if(j==0) retVal=0;
               Float totalAmountCicilanHutangTDetails = 0f;
               for(Long l : test.get(i).entries.get(j).transactDetailIDs){
                   totalAmountCicilanHutangTDetails += Control.selectTDetail(TransactionDetail.Tipe.CicilanHutangTransaction, l).amount;
               }
               if((totalAmountCicilanHutangTDetails < test.get(i).entries.get(j).amount)
                   && ((test.get(i).chargedLevel.tahun < targetYear? true: (test.get(i).entries.get(j).period <= targetMonth)))){
                    
                            
                            retVal += test.get(i).entries.get(j).amount-totalAmountCicilanHutangTDetails;
                            tunggakanCicilanHutangString = tunggakanCicilanHutangString.concat("CicilanHutang Bulan ");
                            tunggakanCicilanHutangString = tunggakanCicilanHutangString.concat(getMonthName(test.get(i).entries.get(j).period));
                            tunggakanCicilanHutangString = tunggakanCicilanHutangString.concat(" "+String.valueOf(test.get(i).chargedLevel.tahun)+"/"+String.valueOf(1+test.get(i).chargedLevel.tahun));
                            tunggakanCicilanHutangString = tunggakanCicilanHutangString.concat("\r\n");
                            
                            tunggakanCicilanHutangEachAmountString = tunggakanCicilanHutangEachAmountString.concat("Rp ");
                            tunggakanCicilanHutangEachAmountString = tunggakanCicilanHutangEachAmountString.concat(String.valueOf(Math.round(test.get(i).entries.get(j).amount-totalAmountCicilanHutangTDetails)));
                            tunggakanCicilanHutangEachAmountString = tunggakanCicilanHutangEachAmountString.concat("\r\n");
               }
                
               
               
           }
           tb.setProfil(profils.get(i));
           tb.setTunggakanCicilanHutang(retVal);
           retVals.add(tb);
           System.out.println(test.get(i).noInduk);
       }
//       Map<Long, CicilanHutang> searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.CicilanHutang, cicilanHutangFilters);
//        if(searchResultMap.size() > 0){
//            for(Map.Entry<Long, CicilanHutang> entry: searchResultMap.entrySet()){
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
       unpaidCicilanHutang = retVal;
       return retVals;
    }
    
    //PART IKS
    
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
       String columnNames[] = {"Tahun", "Biaya IKS", "Check Box", "Tunai", "Iuran Dibayar Dimuka", "Beasiswa", "Beasiswa Yayasan"};
       Set<IKS> iksFilters = new HashSet<>();
       ArrayList<Entry> entries = new ArrayList<>();
       iksFilters.clear();
       iksFilters.add(new IKS(profil.noInduk, new Level(null,null,null,0), entries));
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
       String columnNames[] = {"Tahun", "Biaya IKS", "Check Box", "Tunai", "Iuran Dibayar Dimuka", "Beasiswa", "Beasiswa Yayasan"};
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
    
    private TableModel buildAllTransactionTableModel(ArrayList<Transaction> at) throws SQLException, KasirException {
       String columnNames[] = {"Tipe Iuran", "Jumlah", "Catatan"};
       Object[][] data = new Object[at.size()][3];
         
        for(int i = 0 ; i < at.size(); i++){
            data[i][0]= at.get(i).iuranTipe;
            data[i][1]= at.get(i).amount; 
            data[i][2]= at.get(i).note;
        }
       
       TableModel tm = new DefaultTableModel(data, columnNames){
           @Override
            public boolean isCellEditable(int row, int column) {
                    return false;
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
//       Set<Seragam> seragamFilters = new HashSet<>();
//       seragamFilters.add(new Seragam(profil.noInduk,null, null, 0F, null));
//       Map<Long, Seragam> searchResultMap = new HashMap<>();
//       
//       searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.Seragam, seragamFilters);
//       seragamS = new ArrayList<>();       
//       if(searchResultMap.size() > 0){
//        for(Map.Entry<Long, Seragam> entry: searchResultMap.entrySet()){
//            if(entry.getValue().transactDetailIDs.size()== 0){
//                
//                        seragamS.add(entry.getValue());
//                    
//                
//            }
//        }
//       }
//       for(int i =0; i < seragamS.size(); i++){
//                retVal += seragamS.get(i).amount;
//       }
//       unpaidSeragam = retVal;
       return retVal;
    }
    
    private float calculateUnpaidAlmamater(Profil profil) throws SQLException, KasirException {
       float retVal = 0;
//       Set<Seragam> seragamFilters = new HashSet<>();
//       seragamFilters.add(new Seragam(profil.noInduk,null, null, 0F, null));
//       Map<Long, Seragam> searchResultMap = new HashMap<>();
//       
//       searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.Seragam, seragamFilters);
//       seragamS = new ArrayList<>();       
//       if(searchResultMap.size() > 0){
//        for(Map.Entry<Long, Seragam> entry: searchResultMap.entrySet()){
//            if(entry.getValue().transactDetailIDs.size()== 0){
//                
//                        seragamS.add(entry.getValue());
//                    
//                
//            }
//        }
//       }
//       for(int i =0; i < seragamS.size(); i++){
//                retVal += seragamS.get(i).amount;
//       }
//       unpaidSeragam = retVal;
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
    
    private boolean isBeasiswaEnough(float request, Long iuranID) throws SQLException, KasirException {
        Beasiswa b = Control.selectIuran(Iuran.Tipe.IPP, iuranID);
        if (request > b.amount){
            return false;
        }else{
            return true;
        }
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
        
        String tunggakanSeragam = "Tunggakan Seragam";
        
        String tunggakanBuku = "Tunggakan Buku";
        
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
        
        
        // Tunggakan IPP
        jasperParameter.put("Param_Tunggakan_IPP", tunggakanIPP);
        jasperParameter.put("Param_Tunggakan_IPP_Amount", unpaidIPP);
        jasperParameter.put("Param_Tunggakan_IPP_Each_Amount", tunggakanIPPEachAmountString);
        
        // Tunggakan IPSP
        
        
        // Tunggakan Seragam
        jasperParameter.put("Param_Tunggakan_Seragam", tunggakanSeragam);
        jasperParameter.put("Param_Tunggakan_Seragam_Amount", unpaidSeragam);
        
        // Tunggakan Buku
        jasperParameter.put("Param_Tunggakan_Buku", tunggakanBuku);
        jasperParameter.put("Param_Tunggakan_Buku_Amount", unpaidBuku);
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
    
    public void printBuktiPembayaran(TransactionSummary ts) throws JRException, PrinterException {
        // connection is the data source we used to fetch the data from
        ArrayList<Profil> temp = new ArrayList<>();
        transactionSummary = ts;
        try {
            temp.add(Control.selectProfil(ts.noInduk));
            List<IPP> printIPP = Control.selectIurans(Iuran.Tipe.IPP, IPP.noIndukColName, false, ts.noInduk);
            tahunIPP.clear();
            for(int i = 0 ; i < printIPP.size(); i++){
                tahunIPP.add(printIPP.get(i).chargedLevel.tahun);
            }
            calculateUnpaidIPPs(temp, tahunIPP);
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        } catch (KasirException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        
        String tunggakanIPP = tunggakanIPPString;
        float tunggakanIPPAmount = 0.0f;
        
        String tunggakanSeragam = "Tunggakan Seragam";
        
        String tunggakanBuku = "Tunggakan Buku";
        
        KelasTerbilang totalAmountTerbilang = new KelasTerbilang();
        totalAmountTerbilang.setNum(Math.round(ts.totalAmount));
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
        
        
        // Tunggakan IPP
        jasperParameter.put("Param_Tunggakan_IPP", tunggakanIPP);
        jasperParameter.put("Param_Tunggakan_IPP_Amount", unpaidIPP);
        jasperParameter.put("Param_Tunggakan_IPP_Each_Amount", tunggakanIPPEachAmountString);
        
        // Tunggakan IPSP
        
        
        // Tunggakan Seragam
        jasperParameter.put("Param_Tunggakan_Seragam", tunggakanSeragam);
        jasperParameter.put("Param_Tunggakan_Seragam_Amount", unpaidSeragam);
        
        // Tunggakan Buku
        jasperParameter.put("Param_Tunggakan_Buku", tunggakanBuku);
        jasperParameter.put("Param_Tunggakan_Buku_Amount", unpaidBuku);
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
    
    public void printBuktiPembayaran(TransactionSummary ts, TableModel tm, Float totalDebt) throws JRException, PrinterException {
        // connection is the data source we used to fetch the data from
        ArrayList<Profil> temp = new ArrayList<>();
        transactionSummary = ts;
        try {
            temp.add(Control.selectProfil(ts.noInduk));
            List<IPP> printIPP = Control.selectIurans(Iuran.Tipe.IPP, IPP.noIndukColName, false, ts.noInduk);
            tahunIPP.clear();
            for(int i = 0 ; i < printIPP.size(); i++){
                tahunIPP.add(printIPP.get(i).chargedLevel.tahun);
            }
            calculateUnpaidIPPs(temp, tahunIPP);
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        } catch (KasirException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        
        String tunggakanIPP = tunggakanIPPString;
        float tunggakanIPPAmount = 0.0f;
        
        String tunggakanSeragam = "Tunggakan Seragam";
        
        String tunggakanBuku = "Tunggakan Buku";
        
        KelasTerbilang totalAmountTerbilang = new KelasTerbilang();
        totalAmountTerbilang.setNum(Math.round(ts.totalAmount));
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
        
        
        // Tunggakan IPP
//        jasperParameter.put("Param_Tunggakan_IPP", tunggakanIPP);
//        jasperParameter.put("Param_Tunggakan_IPP_Amount", unpaidIPP);
//        jasperParameter.put("Param_Tunggakan_IPP_Each_Amount", tunggakanIPPEachAmountString);
        
        // Tunggakan IPSP
        
        
        // Tunggakan Seragam
//        jasperParameter.put("Param_Tunggakan_Seragam", tunggakanSeragam);
//        jasperParameter.put("Param_Tunggakan_Seragam_Amount", unpaidSeragam);
        
        // Tunggakan Buku
//        jasperParameter.put("Param_Tunggakan_Buku", tunggakanBuku);
//        jasperParameter.put("Param_Tunggakan_Buku_Amount", unpaidBuku);
        
        
        // Tunggakan DOANG
        String tunggakanDoang = "";
        String tunggakanDoangAmount = "";
        
        for(int i = 0; i < tm.getRowCount(); i++){
            tunggakanDoang = tunggakanDoang.concat(tm.getValueAt(i, 2).toString()).concat("\r\n");
            tunggakanDoangAmount = tunggakanDoangAmount.concat(tm.getValueAt(i, 1).toString()).concat("\r\n");
            System.out.println(tm.getValueAt(i,0));
            System.out.println(tm.getValueAt(i,1));
            System.out.println(tm.getValueAt(i,2));
        }
        String tunggakanTotalAmount = NumberFormat.getInstance().format(totalDebt);
        tunggakanTotalAmount = "Rp ".concat(tunggakanTotalAmount);
        
        //TEMPORARY SET NULL PARAM_TUNGGAKAN A.K.A. tunggakanDoang tunggakanDoangAmount tunggakanTotalAmount
        tunggakanDoang = "";
        tunggakanDoangAmount ="";
        tunggakanTotalAmount = "";
        
        
        jasperParameter.put("Param_Tunggakan", tunggakanDoang);
        jasperParameter.put("Param_Tunggakan_Amount", tunggakanDoangAmount); 
        jasperParameter.put("Param_Tunggakan_Total_Amount", tunggakanTotalAmount);
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
        jasperReport = JasperCompileManager.compileReport
        ("C://printout//PrintoutBuktiPembayaran.jrxml");

        // filling report with data from data source

        jasperPrint = JasperFillManager.fillReport(jasperReport,jasperParameter, connection); 
        // exporting process
        // 1- export to PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, "C://printout//sample_report.pdf");

        // 2- export to HTML
        JasperExportManager.exportReportToHtmlFile(jasperPrint, "C://printout//sample_report.html" ); 

        // 3- export to Excel sheet
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "C://printout//sample_report.xls" );

        exporter.exportReport();
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

    private void calculateTotalTunggakan(Profil p) {
          
        //PART IDD, karena IDD bukan merupakan Iuran, jadi gak usah di cek deh
//        try {
//            IDD pIDD = Control.selectIuran(Iuran.Tipe.IDD, IDD.noIndukColName,false,profil.noInduk);
//            
//        } catch (SQLException ex) {
//            Exceptions.printStackTrace(ex);
//        } catch (KasirException ex) {
//            Exceptions.printStackTrace(ex);
//        }
                
              
        
        //PART IPSP
        IPSP pIPSP = new IPSP(p.noInduk, null, 0f, null);
        Set<IPSP> filterIPSP = new HashSet<>();
        filterIPSP.add(pIPSP);
        
        Map<Long,IPSP> map = new HashMap<>();
        try {
            //pIPSP = Control.selectIuran(Iuran.Tipe.IPSP, IPSP.noIndukColName, false, p.noInduk);
            map = Control.exactFilterSelectIurans(Iuran.Tipe.IPSP, filterIPSP);
//            jTotalTunggakanIPSP.setValue(pIPSP.debt);
            int k = 0;
            for(Map.Entry<Long, IPSP> entry: map.entrySet()){
                pIPSP =  entry.getValue();
                k++;
            }
        } catch (SQLException ex) {
//            jTotalTunggakanIPSP.setValue(0);
            Exceptions.printStackTrace(ex);
        } catch (KasirException ex) {
//            jTotalTunggakanIPSP.setValue(0);
            
            Exceptions.printStackTrace(ex);
        }
        
        
        //PART IPSB
        IPSB pIPSB = new IPSB(p.noInduk, null, 0f, null);
        Set<IPSB> filterIPSB = new HashSet<>();
        filterIPSB.add(pIPSB);
        Map<Long,IPSB> ipsbMap = new HashMap<>();
        try {
//            pIPSB = Control.selectIuran(Iuran.Tipe.IPSB, IPSB.noIndukColName, false, p.noInduk);
            ipsbMap = Control.exactFilterSelectIurans(Iuran.Tipe.IPSB, filterIPSB);
//            jTotalTunggakanIPSP.setValue(pIPSP.debt);
            int k = 0;
            for(Map.Entry<Long, IPSB> entry: ipsbMap.entrySet()){
                pIPSB =  entry.getValue();
                k++;
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        } catch (KasirException ex) {
            Exceptions.printStackTrace(ex);
        }
            
        int k = 0;
        //Seragam
        Float tunggakanSeragam1 = 0f;
       Set<Seragam> seragamFilters = new HashSet<>();
       seragamFilters.add(new Seragam(profil.noInduk,null, null, 0F, null));
       Map<Long, Seragam> srmSeragam = new HashMap<>();
        try {
            srmSeragam = Control.exactFilterSelectIurans(Iuran.Tipe.Seragam, seragamFilters);
            k = 0;
            for(Map.Entry<Long, Seragam> entry: srmSeragam.entrySet()){
                if(entry.getValue().debt > 0)
                    tunggakanSeragam1 += entry.getValue().debt;
                k++;
            }
//            jTotalTunggakanSeragam.setValue(tunggakanSeragam1);
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
//            jTotalTunggakanSeragam.setValue(0f);
        } catch (KasirException ex) {
            Exceptions.printStackTrace(ex);
//            jTotalTunggakanSeragam.setValue(0f);
        }
        
        //Almamater
        Float tunggakanAlmamater1 = 0f;
       Set<Almamater> almamaterFilters = new HashSet<>();
       almamaterFilters.add(new Almamater(profil.noInduk,null, null, 0F, null));
       Map<Long, Almamater> srmAlmamater = new HashMap<>();
        try {
            srmAlmamater = Control.exactFilterSelectIurans(Iuran.Tipe.Almamater, almamaterFilters);
            k = 0;
            for(Map.Entry<Long, Almamater> entry: srmAlmamater.entrySet()){
                if(entry.getValue().debt > 0)
                    tunggakanAlmamater1 += entry.getValue().debt;
                k++;
            }
//            jTotalTunggakanAlmamater.setValue(tunggakanAlmamater1);
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
//            jTotalTunggakanAlmamater.setValue(0f);
        } catch (KasirException ex) {
            Exceptions.printStackTrace(ex);
//            jTotalTunggakanAlmamater.setValue(0f);
        }
       
       
        //PART Buku
        Float tunggakanBuku1 = 0f;
       Set<Buku> bukuFilters = new HashSet<>();
       bukuFilters.add(new Buku(profil.noInduk,null, null, 0F, null));
       Map<Long, Buku> srmBuku = new HashMap<>();
        try {
            srmBuku = Control.exactFilterSelectIurans(Iuran.Tipe.Buku, bukuFilters);
            k = 0;
            for(Map.Entry<Long, Buku> entry: srmBuku.entrySet()){
                if(entry.getValue().debt > 0)
                    tunggakanBuku1 += entry.getValue().debt;
                k++;
            }
//            jTotalTunggakanBuku.setValue(tunggakanBuku1);
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
//            jTotalTunggakanBuku.setValue(0f);
        } catch (KasirException ex) {
            Exceptions.printStackTrace(ex);
//            jTotalTunggakanBuku.setValue(0f);
        }
        //PART IPP
        
       Set<IPP> ippFilters = new HashSet<>();
       ArrayList<Entry> entries = new ArrayList<>();
       ippFilters.clear();
       ippFilters.add(new IPP(p.noInduk, p.currentLevel, entries));
       Map<Long, IPP> searchResultMap;
       Float tunggakanIPP1 = 0f;
       int i = 0;
        try {
            searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.IPP, ippFilters);
            if(searchResultMap.size() > 0){
                for(Map.Entry<Long, IPP> entry: searchResultMap.entrySet()){
                    for(int j =0 ; j< entry.getValue().entries.size(); j++){
                        tunggakanIPP1 += entry.getValue().entries.get(j).debt;
                    }
                    i++;
                }
                tunggakanIPP1 = 0f;
            }
//            jTotalTunggakanIPP.setValue(tunggakanIPP1);
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
//            jTotalTunggakanIPP.setValue(0f);
        } catch (KasirException ex) {
            Exceptions.printStackTrace(ex);
//            jTotalTunggakanIPP.setValue(0f);
        }

    }    
}
class Transaction{
    Iuran.Tipe iuranTipe;
    Float amount;
    String note;
    public Transaction(Iuran.Tipe it, Float a, String n){
        iuranTipe = it;
        amount = a;
        note = n;
    }
}
