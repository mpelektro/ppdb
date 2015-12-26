/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package printout;

import iuran.IPP;
import java.io.Serializable;
import java.util.Collection;
import java.util.Vector;
import pelajar.Profil;

/**
 *
 * @author Master
 */
public class TunggakanBean implements Serializable{
    private Profil profil;
    private String nama;
    private String noInduk;
    private String kelas;
    private String subKelas;
    private String sekolah;
    private int tahun;
    private Float tunggakanIPP;
    private Float tunggakanIKS;
    private Float tunggakanIUS;
    private Float tunggakanOSIS;
    private Float tunggakanPVT;
    private Float tunggakanCicilanHutang;
    private Float tunggakanAlmamater;
    private Float tunggakanIUAP;
    
    public TunggakanBean(){}

    public Profil getProfil() {
        return profil;
    }
    
    public String getNama(){
        return nama;
    }
    
    public String getNoInduk(){
        return noInduk;
    }
    
    public String getKelas(){
        return kelas;
    }
    
    public String getSubKelas(){
        return subKelas;
    }
    
    public String getSekolah(){
        return sekolah;
    }
    
    public int getTahun(){
        return tahun;
    }
    
    public void setProfil(Profil profil) {
        this.profil = profil;
        this.nama = profil.biodata.nama;
        this.noInduk = profil.noInduk;
        this.sekolah = profil.currentLevel.level1.toString();
        this.kelas = profil.currentLevel.level2.toString();
        this.subKelas = profil.currentLevel.level3.toString();
        this.tahun = profil.currentLevel.tahun;
        
    }
    
    
    public Float getTunggakanIPP() {
        return tunggakanIPP;
    }
    public void setTunggakanIPP(Float tunggakanIPP) {
        this.tunggakanIPP = tunggakanIPP;
    }
    
    public Float getTunggakanIKS() {
        return tunggakanIKS;
    }
    public void setTunggakanIKS(Float tunggakanIKS) {
        this.tunggakanIKS = tunggakanIKS;
    }
    
    public Float getTunggakanIUS(){
        return tunggakanIUS;
    }
    
    public void setTunggakanIUS(Float tunggakanIUS){
        this.tunggakanIUS = tunggakanIUS;
    }
    public Float getTunggakanPVT(){
        return tunggakanPVT;
    }
    
    public void setTunggakanPVT(Float tunggakanPVT){
        this.tunggakanPVT = tunggakanPVT;
    }
    public Float getTunggakanOSIS(){
        return tunggakanOSIS;
    }
    
    public void setTunggakanOSIS(Float tunggakanOSIS){
        this.tunggakanOSIS = tunggakanOSIS;
    }
    
    public Float getTunggakanCicilanHutang(){
        return tunggakanCicilanHutang;
    }
    
    public void setTunggakanCicilanHutang(Float tunggakanCicilanHutang){
        this.tunggakanCicilanHutang = tunggakanCicilanHutang;
    }
    
    public Float getTunggakanAlmamater(){
        return tunggakanAlmamater;
    }
    
    public void setTunggakanAlmamater(Float tunggakanAlmamater){
        this.tunggakanAlmamater = tunggakanAlmamater;
    }
    
    public Float getTunggakanIUAP(){
        return tunggakanIUAP;
    }
    
    public void setTunggakanIUAP(Float tunggakanIUAP){
        this.tunggakanIUAP = tunggakanIUAP;
    }
}
