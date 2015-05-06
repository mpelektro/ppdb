/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iuran;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import pelajar.Level;
import sak.Kalender;
import sak.KasirException;

/**
 *
 * @author kedra
 */
public class TabunganTransactionDetail extends TransactionDetailRegular<TabunganTransactionDetail, Tabungan>{
    public static final String tableName = Tipe.TabunganTransaction.toString();
    
    public TabunganTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, Kalender cDate, Kalender luDate, String note, boolean settled, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, cDate, luDate, note, settled, piutang);
    }
    
    //create for insertion
    public TabunganTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, String note, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, note, piutang);
    }
    
    //create from db
    public TabunganTransactionDetail(){}
    
    public TabunganTransactionDetail(TabunganTransactionDetail tabunganTDetail){
        super(tabunganTDetail);
    }
    

    public Tipe getTipe() {
        return Tipe.SeragamTransaction;
    }
    public Iuran.Tipe getTipeIuran() {
        return Iuran.Tipe.Seragam;
    }

    
    public TabunganTransactionDetail dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException {
        assert !rs.isBeforeFirst();
        
        TabunganTransactionDetail tabunganTDetail = onCallingObj? this : new TabunganTransactionDetail();
        tabunganTDetail.dynFromResultSet(rs);
        
        if(tabunganTDetail.isDBValid())
            return tabunganTDetail;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }    
}
