package pelajar;

import java.util.*;
import java.sql.*;
import kasir.*;
import sak.*;
import org.apache.commons.lang3.*;

public class Biodata implements Filter<Profil>{
    public static final String namaColName = "Nama", tanggalLahirColName = "TanggalLahir";
    public static final String tempatLahirColName = "TempatLahir", alamatColName = "Alamat";
    public static final String telpon1ColName = "Telpon1", telpon2ColName = "Telpon2";
    public static final String namaAyahColName = "NamaAyah", namaIbuColName = "NamaIbu";
    public static final String umurColName = "Umur", kelaminColName = "JenisKelamin", agamaColName = "Agama", asalSekolahColName = "AsalSekolah";
    
    public enum Kelamin{L,P};
    public enum Agama{ISLAM, KRISTEN_PROTESTAN, KRISTEN_KATOLIK, HINDU, BUDHA, ALIRAN_KEPERCAYAAN, LAINNYA};
    
    public Agama agama;
    public String nama;
    public Kelamin kelamin;
    public Kalender tanggalLahir;
    public String tempatLahir;
    public String alamat;
    public String telpon1;
    public String telpon2;
    public String namaAyah;
    public String namaIbu;
    public String asalSekolah;
    public int umur;
    
    //create filter
    public Biodata(String nama, Kelamin kelamin, Agama agama, String asalSekolah, Kalender tglLahir, String tmpLahir, String alamat, String tlp1, String tlp2, String ayah, String ibu) {
        this.nama = nama;   this.kelamin = kelamin; this.agama = agama; this.asalSekolah = asalSekolah; tanggalLahir = tglLahir;
        tempatLahir = tmpLahir; this.alamat = alamat;
        telpon1 = tlp1; telpon2 = tlp2;
        namaAyah = ayah;    namaIbu = ibu;
        
        if (tglLahir != null && tglLahir.get(Calendar.YEAR) >= 1970)
            umur = new Kalender(System.currentTimeMillis()).get(Calendar.YEAR) - tglLahir.get(Calendar.YEAR);
    }
    
    //create for insertion
    public Biodata(String nama, Kelamin kelamin, Agama agama, String asalSekolah, String alamat, String tlp1){
        this(nama, kelamin, agama, asalSekolah, null, null, alamat, tlp1, null, null, null);
    }
    
    //create from db
    public Biodata() {}
    
    public Biodata(Biodata bio) {
        this(bio.nama, bio.kelamin, bio.agama, bio.asalSekolah, bio.tanggalLahir, bio.tempatLahir, bio.alamat, bio.telpon1, bio.telpon2, bio.namaAyah, bio.namaIbu);
    }
    

    public static String toStringHeader(){
        String tmp = namaColName + "|" + umurColName + "|" + kelaminColName + "|" + agamaColName + "|" + "|" + asalSekolahColName + "|" + tanggalLahirColName + "|" + tempatLahirColName;
        return tmp + "|" + alamatColName + "|" + telpon1ColName + "|" + telpon2ColName + "|" + namaAyahColName + "|" + namaIbuColName;
    }
    public String toString() {
        String tmp = nama + "|" + umur + "|" + kelamin + "|" + agama + "|" + "|" + asalSekolah + "|" + tanggalLahir + "|" + tempatLahir + "|" + alamat;
        return tmp + "|" + telpon1 + "|" + telpon2 + "|" + namaAyah + "|" + namaIbu;
    }
    public boolean equals(Biodata bio) {
        return bio == null ? false : nama.equalsIgnoreCase(bio.nama) && kelamin.equals(bio.kelamin) && agama.equals(bio.agama) && asalSekolah.equals(bio.asalSekolah) && tanggalLahir.equals(bio.tanggalLahir) && tempatLahir.equalsIgnoreCase(bio.tempatLahir) && alamat.equalsIgnoreCase(bio.alamat) && telpon1.equals(bio.telpon1) && telpon2.equals(bio.telpon2) && namaAyah.equalsIgnoreCase(bio.namaAyah) && namaIbu.equalsIgnoreCase(bio.namaIbu);
    }    
    public boolean isEmpty(){
        return nama == null && kelamin == null && agama == null && asalSekolah == null && tanggalLahir == null && tempatLahir == null && alamat == null && telpon1 == null && telpon2 == null && namaAyah == null && namaIbu == null;
    }    
    

    /* ret null if isEmpty = true, ie. empty rs */
    public Biodata dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException{       
        Biodata bio = onCallingObj? this : new Biodata();
        
        bio.nama = rs.getString(namaColName);
        
        String tmpKelamin = rs.getString(kelaminColName);
        if(tmpKelamin != null)
            bio.kelamin = tmpKelamin.equalsIgnoreCase("L")? Kelamin.L : Kelamin.P;
        
        String tmpAgama = rs.getString(agamaColName);
        if(tmpAgama != null){
            if(tmpAgama.equalsIgnoreCase("ISLAM")){
                bio.agama = Agama.ISLAM;
            }else if(tmpAgama.equalsIgnoreCase("KRISTEN_PROTESTAN")){
                bio.agama = Agama.KRISTEN_PROTESTAN;
            }else if(tmpAgama.equalsIgnoreCase("KRISTEN_KATOLIK")){
                bio.agama = Agama.KRISTEN_KATOLIK;
            }else if(tmpAgama.equalsIgnoreCase("HINDU")){
                bio.agama = Agama.HINDU;
            }else if(tmpAgama.equalsIgnoreCase("BUDHA")){
                bio.agama = Agama.BUDHA;
            }else if(tmpAgama.equalsIgnoreCase("ALIRAN_KEPERCAYAAN")){
                bio.agama = Agama.ALIRAN_KEPERCAYAAN;
            }else{
                bio.agama = Agama.LAINNYA;
            }
        }
        bio.asalSekolah = rs.getString(asalSekolahColName);
        bio.tanggalLahir = Kalender.fromResultSet(rs,tanggalLahirColName);
        bio.tempatLahir = rs.getString(tempatLahirColName);
        bio.alamat = rs.getString(alamatColName);
        bio.telpon1 = rs.getString(telpon1ColName);
        bio.telpon2 = rs.getString(telpon2ColName);
        bio.namaAyah = rs.getString(namaAyahColName);
        bio.namaIbu = rs.getString(namaIbuColName);
        
        return bio.isEmpty()? null : bio;
    }
    
    /* ret null if isEmpty = true */
    public static Biodata fromResultSet(ResultSet rs) throws SQLException {       
        return new Biodata().dynFromResultSet(rs, true);
    }
    
    /* noInduk may not be null / empty
     * ret null if isEmpty = true / matching row isn't found
     * throws KasirException(ROW_NOT_FOUND, noInduk) if no matching row found
     */
    public static Biodata fromResultSet(ResultSet rs, String noInduk) throws SQLException, KasirException {
        assert noInduk != null && !noInduk.isEmpty() : "fromResultSet(ResultSet, String noInduk = null)";
        
        if(DBSR.searchRow(rs, Profil.noIndukColName, false, noInduk) > 0)
            return fromResultSet(rs);
        throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, noInduk);
    }
    
    /* never ret false, only throws exception */
    public boolean flushResultSet(ResultSet rs) throws SQLException {
        rs.updateString(namaColName, nama);
        
        if(kelamin != null)
            rs.updateString(kelaminColName, kelamin.toString());
        
        if(agama !=null)
            rs.updateString(agamaColName, agama.toString());
        
        rs.updateString(asalSekolahColName, asalSekolah);
        
        if(tanggalLahir != null)
            tanggalLahir.flushResultSet(rs, tanggalLahirColName);
        
        rs.updateString(tempatLahirColName, tempatLahir);
        rs.updateString(alamatColName, alamat);
        rs.updateString(telpon1ColName, telpon1);
        rs.updateString(telpon2ColName, telpon2);
        rs.updateString(namaAyahColName, namaAyah);
        rs.updateString(namaIbuColName, namaIbu);        
        return true;
    }

    //ret empty if all vars are empty
    public String asWhereClause() {
        LinkedList<String> whereClause = new LinkedList<>();
        if (nama != null && !nama.isEmpty())
            whereClause.add(namaColName + " LIKE '%" + nama + "%'");
        if (kelamin != null)
            whereClause.add(kelaminColName + " = '" + kelamin + "'");
        if (agama != null)
            whereClause.add(agamaColName + " = '" + agama + "'");
        if (asalSekolah != null && !asalSekolah.isEmpty())
            whereClause.add(asalSekolahColName + " LIKE '%" + asalSekolah + "%'");
        if (tanggalLahir != null)
                whereClause.add(tanggalLahirColName + " LIKE '%" + tanggalLahir.asWhereClause(true) + "%'");
        if (tempatLahir != null && !tempatLahir.isEmpty())
            whereClause.add(tempatLahirColName + " LIKE '%" + tempatLahir + "%'");
        if (alamat != null && !alamat.isEmpty())
            whereClause.add(alamatColName + " LIKE '%" + alamat + "%'");
        if (telpon1 != null && !telpon1.isEmpty())
            whereClause.add(telpon1ColName + " LIKE '%" + telpon1 + "%'");
        if (telpon2 != null && !telpon2.isEmpty())
            whereClause.add(telpon2ColName + " LIKE '%" + telpon2 + "%'");
        if (namaAyah != null && !namaAyah.isEmpty())
            whereClause.add(namaAyahColName + " LIKE '%" + namaAyah + "%'");
        if (namaIbu != null && !namaIbu.isEmpty())
            whereClause.add(namaIbuColName + " LIKE '%" + namaIbu + "%'");

        return whereClause.isEmpty()? "" : StringUtils.join(whereClause, " AND ");
    }
    public String asWhereClauseExact() {
        LinkedList<String> whereClause = new LinkedList<>();
        if (nama != null && !nama.isEmpty())
            whereClause.add(namaColName + " = '" + nama + "'");
        if (kelamin != null)
            whereClause.add(kelaminColName + " = '" + kelamin + "'");
        if (agama != null)
            whereClause.add(agamaColName + " = '" + agama + "'");
        if (asalSekolah != null && !asalSekolah.isEmpty())
            whereClause.add(asalSekolahColName + " LIKE '%" + asalSekolah + "%'");
        if (tanggalLahir != null)
                whereClause.add(tanggalLahirColName + " = '" + tanggalLahir.asWhereClause(true) + "'");
        if (tempatLahir != null && !tempatLahir.isEmpty())
            whereClause.add(tempatLahirColName + " = '" + tempatLahir + "'");
        if (alamat != null && !alamat.isEmpty())
            whereClause.add(alamatColName + " LIKE '%" + alamat + "%'");
        if (telpon1 != null && !telpon1.isEmpty())
            whereClause.add(telpon1ColName + " LIKE '%" + telpon1 + "%'");
        if (telpon2 != null && !telpon2.isEmpty())
            whereClause.add(telpon2ColName + " LIKE '%" + telpon2 + "%'");
        if (namaAyah != null && !namaAyah.isEmpty())
            whereClause.add(namaAyahColName + " LIKE '%" + namaAyah + "%'");
        if (namaIbu != null && !namaIbu.isEmpty())
            whereClause.add(namaIbuColName + " LIKE '%" + namaIbu + "%'");

        return whereClause.isEmpty()? "" : StringUtils.join(whereClause, " AND ");
    }
}