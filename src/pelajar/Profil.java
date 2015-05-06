package pelajar;

import java.util.*;
import java.sql.*;
import kasir.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import sak.*;

public class Profil extends KasirObject<Profil,Profil,String>{
    public static final String tableName = "Profil";
    public static final String noIndukColName = "NomorInduk";
    public static final String currentLevelColName = "CurrentLevel";
    public static final String tanggalMasukColName = "TanggalMasuk";
    public static final String tanggalLulusColName = "TanggalLulus";
    
    public String noInduk; //db-primary-key
    public Level currentLevel;
    public Biodata biodata;
    public Kalender tanggalMasuk;
    public Kalender tanggalLulus;
    
    //special for ppdb
    public Kalender lastUpdateDate;
    public enum Gelombang{GELOMBANG_1,GELOMBANG_2};
    public Gelombang gelombang;
    public float totalIuran;
    //create filter
    public Profil(String noInduk, Level curLV, Biodata bio, Kalender tglMasuk, Kalender tglLulus) {
        this.noInduk = noInduk; currentLevel = curLV;
        biodata = bio;  tanggalMasuk = tglMasuk;
        tanggalLulus = tglLulus;
    }
    
    //create for insertion
    public Profil(String noInduk, String nama, Biodata.Kelamin kelamin, Biodata.Agama agama, String asalSekolah, String alamat, String tlp1, String tlp2, Gelombang gelombang){
        this(noInduk, null, new Biodata(nama, kelamin, agama, asalSekolah, alamat, tlp1), null, null);
    }

    //create from db
    public Profil() {}
    
    public Profil(Profil profil) {
        this(profil.noInduk, profil.currentLevel, profil.biodata, profil.tanggalMasuk, profil.tanggalLulus);
    }
    

    public static String toStringHeader(){
        return noIndukColName + "|" + currentLevelColName + "|" + Biodata.toStringHeader() + "|" + tanggalMasukColName + "|" + tanggalLulusColName;
    }
    public String toString() {
        return noInduk + "|" + currentLevel + "|" + biodata + "|" + tanggalMasuk + "|" + tanggalLulus;
    }
    public boolean equals(Profil profil) {
        return profil == null ? false : noInduk.equals(profil.noInduk) && biodata.equals(profil.biodata) && currentLevel.equals(profil.currentLevel) && tanggalMasuk.equals(profil.tanggalMasuk) && tanggalLulus.equals(profil.tanggalLulus);
    }
    
    
    //==============================
    /* true if all required fields (db-not-null fields) are valid */
    public boolean isDBValid(){
        return noInduk != null && !noInduk.isEmpty();
    }
    public boolean isInsertDBValid(){
        return isDBValid();
    }
    
    public String getKey(){
        return noInduk;
    }

    /* rs.isBeforeFirst must be false
     * throws KasirException(BAD_RECORD, tableName) if the resulting Profil.isDBValid = false
     * never ret null
     */
    public Profil dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst() : "rs.isBeforeFirst = true";
        
        Profil profil = onCallingObj? this : new Profil();
        
        profil.noInduk = rs.getString(noIndukColName);
        profil.currentLevel = Level.fromResultSet(rs, currentLevelColName);
        profil.biodata = Biodata.fromResultSet(rs);
        profil.tanggalMasuk = Kalender.fromResultSet(rs, tanggalMasukColName);
        profil.tanggalLulus = Kalender.fromResultSet(rs, tanggalLulusColName);
        
        if(profil.isDBValid())
            return profil;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, tableName);
    }
    
    /* never ret null
     * throws KasirException(BAD_RECORD, tableName) if the resulting Profil.isDBValid = false
     */
    public static Profil fromResultSet(ResultSet rs) throws SQLException, KasirException{
        return new Profil().dynFromResultSet(rs, true);
    }
    
    public static Profil fromResultSet(ResultSet rs, String colName, Number val) throws SQLException, KasirException{
        assert colName != null && !colName.isEmpty();
        
        if(DBSR.searchRow(rs, colName, val) > 0)
            return fromResultSet(rs);
        else
            throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, val);
    }
    public static Profil fromResultSet(ResultSet rs, String colName, boolean caseSensitive, String val) throws SQLException, KasirException{
        assert colName != null && !colName.isEmpty();
        
        if(DBSR.searchRow(rs, colName, caseSensitive, val) > 0)
            return fromResultSet(rs);
        else
            throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, val);
    }
    
    /* noInduk may not be null / empty
     * never ret null
     * throws KasirException(ROW_NOT_FOUND, noInduk) if no matching row found
     * throws KasirException(BAD_RECORD, tableName) if the resulting Profil.isDBValid = false
     */
    public static Profil fromResultSet(ResultSet rs, String noInduk) throws SQLException, KasirException {
        assert noInduk != null && !noInduk.isEmpty() : "fromResultSet(ResultSet, String noInduk = null / empty)";
        
        return fromResultSet(rs, noIndukColName, false, noInduk);
    }

    /* caller of this method must ensure that isDBValid = true & RS cursor points to the intended row
     * never ret false
     */
    public boolean flushResultSet(ResultSet rs, boolean flushNoInduk) throws SQLException {
        if(flushNoInduk)
            rs.updateString(noIndukColName, noInduk);
        if(currentLevel != null)
            currentLevel.flushResultSet(rs, currentLevelColName);
        if (biodata != null)
            biodata.flushResultSet(rs);
        if(tanggalMasuk != null)
            tanggalMasuk.flushResultSet(rs, tanggalMasukColName);
        if(tanggalLulus != null)
            tanggalLulus.flushResultSet(rs, tanggalLulusColName);
        return true;
    }
    
    /* caller of this method must ensure that RS cursor is at the insert row & that no existing row has the same noInduk & call rs.insertRow() when all done
     * throws KasirException(DB_INVALID, this) if isInsertDBValid = false
     * never ret false
     */
    public boolean insertResultSet(ResultSet rs) throws SQLException, KasirException{
        if(isInsertDBValid())
            return flushResultSet(rs, true);
        else
            throw new KasirException(KasirException.Tipe.DB_INVALID, this);
    }
    
    /* updates without updating noInduk
     * if noInduk is changed prior to calling this method, the new noInduk is still isn't stored
     * throws KasirException(DB_INVALID, this) if isDBValid = false
     * throws KasirException(ROW_NOT_FOUND, this) if no row matches
     * never ret false
     */
    public boolean updateResultSet(ResultSet rs) throws SQLException, KasirException{
        if(!isDBValid())
            throw new KasirException(KasirException.Tipe.DB_INVALID, this);
        
        if(DBSR.searchRow(rs, noIndukColName, false, noInduk) > 0)
            return flushResultSet(rs, false);
        else
            throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, this);
    }
    
    /* updates including noInduk. if oldNoInduk.equals(noInduk) = true, then it's the same as flushResultSet(rs)
     * oldNoInduk may not be null
     * throws KasirException(DB_INVALID, this) if isDBValid = false
     * throws KasirException(ROW_NOT_FOUND, this/oldNoInduk) if no row matches
     * throws KasirException(DUPLICATE_PRIMARY_KEY, this) if the new noInduk has already existed in db
     * never ret false
     */
    public boolean updateResultSetNoInduk(ResultSet rs, String oldNoInduk) throws SQLException, KasirException {
        assert oldNoInduk != null : "oldNoInduk = null";
        
        if(!isDBValid())
            throw new KasirException(KasirException.Tipe.DB_INVALID, this);
        
        if(noInduk.equals(oldNoInduk)){
            if(DBSR.searchRow(rs, noIndukColName, false, noInduk) > 0)
                return flushResultSet(rs, false);
            else
                throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, this);
        }else{
            if(DBSR.searchRow(rs, noIndukColName, false, noInduk) > 0)
                throw new KasirException(KasirException.Tipe.DUPLICATE_PRIMARY_KEY, this);
            
            if(DBSR.searchRow(rs, noIndukColName, false, oldNoInduk) > 0)
                return flushResultSet(rs, true);
            else
                throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, oldNoInduk);
        }
    }
    
    //ret empty if all vars are empty
    public String asWhereClause() {
        LinkedList<String> whereClause = new LinkedList<>();
        
        if (noInduk != null && !noInduk.isEmpty())
            whereClause.add(noIndukColName + " LIKE '%" + noInduk + "%'");
        
        if (currentLevel != null)
                whereClause.add(currentLevelColName + " LIKE '%" + currentLevel.asWhereClause() + "%'");
        
        if (biodata != null){
            String wc = biodata.asWhereClause();
            if(wc != null && !wc.isEmpty())
                whereClause.add(wc);
        }
            
        if (tanggalMasuk != null)
                whereClause.add(tanggalMasukColName + " LIKE '%" + tanggalMasuk.asWhereClause(true) + "%'");
            
        if (tanggalLulus != null)
                whereClause.add(tanggalLulusColName + " LIKE '%" + tanggalLulus.asWhereClause(true) + "%'");

        return whereClause.isEmpty()? "" : StringUtils.join(whereClause, " AND ");
    }
    public String asWhereClauseExact() {
        LinkedList<String> whereClause = new LinkedList<>();
        
        if (noInduk != null && !noInduk.isEmpty())
            whereClause.add(noIndukColName + " = '" + noInduk + "'");
        
        if (currentLevel != null)
                whereClause.add(currentLevelColName + " LIKE '" + currentLevel.asWhereClause() + "'");
        
        if (biodata != null){
            String wc = biodata.asWhereClauseExact();
            if(wc != null && !wc.isEmpty())
                whereClause.add(wc);
        }
            
        if (tanggalMasuk != null)
                whereClause.add(tanggalMasukColName + " = '" + tanggalMasuk.asWhereClause(true) + "'");
            
        if (tanggalLulus != null)
                whereClause.add(tanggalLulusColName + " = '" + tanggalLulus.asWhereClause(true) + "'");

        return whereClause.isEmpty()? "" : StringUtils.join(whereClause, " AND ");
    }
    
    
    //============================SELECT
    public static Profil select(String colName, Number val) throws SQLException, KasirException{
        ResultSet rs = DBSR.takeResultSetByNumber(tableName, colName, 1, val);
        return Profil.fromResultSet(rs, colName, val);
    }
    public static Profil select(String colName, boolean caseSensitive, String val) throws SQLException, KasirException{
        ResultSet rs = DBSR.takeResultSetByString(tableName, colName, 1, val);
        return Profil.fromResultSet(rs, colName, caseSensitive, val);
    }
    
    /* noInduk may not be null / empty
     * never ret null
     * throws KasirException(ROW_NOT_FOUND, noInduk)
     */
    public static Profil select(String noInduk) throws SQLException, KasirException{
        assert noInduk != null && !noInduk.isEmpty() : "selectProfil(String, String, String noInduk = null)";

        return select(noIndukColName, false, noInduk);
    }
    
    
    
    /* null / empty elts of noInduks are ignored
     * ret all Profil if noInduks = null
     * throws KasirException(BAD_RECORD, tableName). possible only if noInduks = null
     * ret empty Map if (noInduks = null & table is empty) / (all elts of noInduks aren't valid (null / empty)) / noInduks.isEmpty = true
     * ret Map<noInduk,profil>. never null
     * Map.get(noInduks[i]) = null if no row has noInduks[i]
     * Map.size() <= noInduks.length, because duplicate elts of ids are treated as 1 map entry
     */
    public static Map<String,Profil> selectS(Set<String> noInduks) throws SQLException, KasirException{
        ResultSet rs;
        Map<String,Profil> noIndukProfil;
        if(noInduks == null){
            rs = DBSR.takeResultSetByStringColl(tableName, noIndukColName, -1, null);

            noIndukProfil = new HashMap<>(DBSR.rowCountRS(rs));                
            rs.beforeFirst();
            while(rs.next()){
                Profil p = Profil.fromResultSet(rs);
                noIndukProfil.put(p.noInduk, p);
            }
        }else{
            Set<String> validNoInduk = new HashSet<>(CollectionUtils.subtract(noInduks, Arrays.asList(null, "")));
            rs = DBSR.takeResultSetByStringColl(tableName, noIndukColName, -1, validNoInduk);

            noIndukProfil = new HashMap<>(validNoInduk.size());                
            for(String noInduk : validNoInduk){
                try{
                    noIndukProfil.put(noInduk, Profil.fromResultSet(rs, noInduk));
                }catch(KasirException e){
                    noIndukProfil.put(noInduk, null);
                }
            }
        }

        return noIndukProfil;
    }
    
    public static ArrayList<Profil> selectS(String curLev) throws SQLException, KasirException{
        ResultSet rs;
        ArrayList<Profil> profils;
        if(curLev == null){
            rs = DBSR.takeResultSetByStringCollMpe(tableName, currentLevelColName, -1, null);

            profils = new ArrayList<>();                
            rs.beforeFirst();
            while(rs.next()){
                Profil p = Profil.fromResultSet(rs);
                profils.add(p);
            }
        }else{
            //Set<String> validNoInduk = new HashSet<>(CollectionUtils.subtract(noInduks, Arrays.asList(null, "")));
            rs = DBSR.takeResultSetByStringCollMpe(tableName, currentLevelColName, -1, curLev);

            profils = new ArrayList<>();  
            profils = new ArrayList<>();                
            rs.beforeFirst();
            while(rs.next()){
                Profil p = Profil.fromResultSet(rs);
                profils.add(p);
            }
//            for(String curLev : validNoInduk){
//                try{
//                    noIndukProfil.put(noInduk, Profil.fromResultSet(rs, noInduk));
//                }catch(KasirException e){
//                    noIndukProfil.put(noInduk, null);
//                }
//            }
        }

        return profils;
    }

    public static Profil filterSelect(Filter<Profil> filter) throws SQLException, KasirException{
        ResultSet rs = DBSR.takeResultSetByFilter(tableName, null, 1, filter);
        if(rs.next())
            return fromResultSet(rs);
        else
            throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, filter);
    }
    
    /* filters is either ArrayList<Profil> or ArrayList<Biodata>
     * null elts of filters are ignored
     * ret all Profil if filters == null
     * throws KasirException(BAD_RECORD, tableName). possible only if filters = null
     * ret empty Map if (filters = null & table is empty) / all elts of filters = null / filters.isEmpty = true
     * ret Map<noInduk,profil>. never null
     */
    public static Map<String,Profil> filterSelectS(Set<? extends Filter<Profil>> filters) throws SQLException, KasirException{
        ResultSet rs;
        if(filters == null)
            rs = DBSR.takeResultSetByFilterColl(tableName, null, -1, null);
        else{
            //remove null filters / filters with empty asWhereClause, so BAD_RECORD is only detected if filters = null
            //arg filters isn't modified
            Set<Filter<Profil>> validFilters = new HashSet<>();
            for(Filter<Profil> filter : filters){
                if(filter != null && !"".equals(filter.asWhereClause()))
                    validFilters.add(filter);
            }
            rs = DBSR.takeResultSetByFilterColl(tableName, null, -1, validFilters);
        }                        

        Map<String,Profil> noIndukProfil = new HashMap<>(DBSR.rowCountRS(rs));
        while(rs.next()){
            Profil p = Profil.fromResultSet(rs);
            noIndukProfil.put(p.noInduk, p);
        }

        return noIndukProfil;
    }
    
    
    //============================INSERT
    public boolean insert() throws SQLException, KasirException{
        return DBSR.insertKasirO(tableName, noIndukColName, this);
    }
    public static int insertS(ArrayList<Profil> profils) throws SQLException, KasirException{
        return DBSR.insertKasirOs(tableName, noIndukColName, profils, true);
    }
    
    
    //============================UPDATE
    /* flush current states (including noInduk) of profil into db
     * oldNoInduk & profil may not be null. oldNoInduk is the previous val of profil.noInduk
     * throws KasirException(DB_INVALID, profil) if profil.isDBValid = false
     * throws KasirException(ROW_NOT_FOUND, oldNoInduk) if noIndukOld record = not in db
     * throws KasirException(DUPLICATE_PRIMARY_KEY, profil) if profil.noInduk has already existed in db
     * never ret false
     */
    public boolean updateNoInduk(String oldNoInduk) throws SQLException, KasirException{
        assert oldNoInduk != null;

        //profil.noInduk is also retrieved to check for noInduk duplication
        ResultSet rs = DBSR.takeResultSetByString(tableName, noIndukColName, 1, oldNoInduk, noInduk);

        if(updateResultSetNoInduk(rs, oldNoInduk)){
            rs.updateRow();
            return true;
        }else
            return false;
    }       
    public boolean update() throws SQLException, KasirException{
        return DBSR.updateKasirO(tableName, noIndukColName, this);
    }

    /* flush current states (except noInduk) of profils into db
     * for each profil, if profil.noInduk is changed prior to calling this method then either not finding the record / updating the wrong record
     * 'profils' may not be null
     * null elts of 'profils' are removed, note this removal done directly on the arraylist given as 'profils'
     * throws KasirException(DB_INVALID, Set<Integer>), Set elts are indexes such that profils.get(index).isDBValid() = false
     * throws KasirException(ROW_NOT_FOUND, Set<Integer>), Set elts are indexes such that profils.get(index) = not in db
     * ret num of profil successfully flushed
     * ret -1 if profils.isEmpty() = true before null elts removal
     * ret -2 if profils.isEmpty() = true after null elts removal
     */
    public static int updateS(ArrayList<Profil> profils) throws SQLException, KasirException{
        return DBSR.updateKasirOs(tableName, noIndukColName, profils);            
    }


    //========================DELETE    
    public boolean delete() throws SQLException, KasirException{
        return DBSR.deleteKasirO(tableName, noIndukColName, false, noInduk);
    }

    /* noInduk may be null / "", which in this case del the first found bad record
     * throws KasirException(ROW_NOT_FOUND, noInduk)
     */
    public static boolean delete(String noInduk) throws SQLException, KasirException{
        return DBSR.deleteKasirO(tableName, noIndukColName, false, noInduk);
    }

    /* del all rows having any elt of noInduks on colName, including null/empty (ie. del all bad records)
     * del all rows if noInduks = null
     */
    public static int deleteS(Set<String> noInduks) throws SQLException{
        return DBSR.deleteKasirOsByString(tableName, noIndukColName, noInduks);
    }

    /* profils may not be null
     * this is equal to deleteProfils(user, pass, Set) where Set contains noInduks of all profils, including bad noInduk
     */
    public static int deleteS(ArrayList<Profil> profils) throws SQLException{  //arraylist because name clash
        Set<String> noInduks = new HashSet<>(profils.size());
        for(Profil p : profils)
            noInduks.add(p.noInduk);
        return deleteS(noInduks);
    }
}