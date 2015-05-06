/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import iuran.*;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import kasir.Clerk;
import kasir.Control;
import pelajar.Biodata;
import pelajar.Level;
import pelajar.Profil;
import org.netbeans.validation.api.Validator;
import org.netbeans.validation.api.ValidatorUtils;
import org.netbeans.validation.api.ui.swing.ValidationPanel;
import org.netbeans.validation.api.builtin.stringvalidation.StringValidators;
import org.openide.util.Exceptions;
import sak.Kalender;
import sak.KasirException;

/**
 *
 * @author Master
 */
public class InputIuranRegularFrame extends javax.swing.JFrame {
    private final ValidationPanel validationPanel = new ValidationPanel();
    private Clerk clerk;
    /**
     * Creates new form InputIuranFrame
     */
    public InputIuranRegularFrame() {
        initComponents();
        this.clerk = new Clerk("12","rudy","clerky","rusly", "123456");
        this.profil = new Profil();
    }
    
    public InputIuranRegularFrame(Clerk clerk, Profil profil) {
        this.clerk = clerk;
        this.profil = profil;
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

        jPanelProfil = new javax.swing.JPanel();
        jLableProfilTitle = new javax.swing.JLabel();
        jLabelNama = new javax.swing.JLabel();
        jLabelLevel2Level3 = new javax.swing.JLabel();
        jLabelNoInduk = new javax.swing.JLabel();
        jLabelLevel1 = new javax.swing.JLabel();
        jTextFieldNama = new javax.swing.JTextField();
        jTextFieldNomorInduk = new javax.swing.JTextField();
        jTextFieldLevel2Level3 = new javax.swing.JTextField();
        Level level = new Level();
        DefaultComboBoxModel level1ComboBoxModel = new DefaultComboBoxModel(level.level1.values());
        DefaultComboBoxModel level2ComboBoxModel = new DefaultComboBoxModel(level.level2.values());
        DefaultComboBoxModel level3ComboBoxModel = new DefaultComboBoxModel(level.level3.values());
        jPanelSeragam = new javax.swing.JPanel();
        jLabelSeragamTitle = new javax.swing.JLabel();
        jLabelAmount = new javax.swing.JLabel();
        jTextFieldSeragamAmount = new javax.swing.JTextField();
        jLabelIKSChargedLevel = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabelIKSChargedLevel2Level3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jLabelNote = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabelAmount1 = new javax.swing.JLabel();
        jTextFieldSeragamTransactionName = new javax.swing.JTextField();
        jPanelButton = new javax.swing.JPanel();
        jButtonSaveAll = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLableProfilTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLableProfilTitle.setText(org.openide.util.NbBundle.getMessage(InputIuranRegularFrame.class, "InputIuranRegularFrame.jLableProfilTitle.text")); // NOI18N

        jLabelNama.setText(org.openide.util.NbBundle.getMessage(InputIuranRegularFrame.class, "InputIuranRegularFrame.jLabelNama.text")); // NOI18N

        jLabelLevel2Level3.setText(org.openide.util.NbBundle.getMessage(InputIuranRegularFrame.class, "InputIuranRegularFrame.jLabelLevel2Level3.text")); // NOI18N

        jLabelNoInduk.setText(org.openide.util.NbBundle.getMessage(InputIuranRegularFrame.class, "InputIuranRegularFrame.jLabelNoInduk.text")); // NOI18N

        jLabelLevel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelLevel1.setText(org.openide.util.NbBundle.getMessage(InputIuranRegularFrame.class, "InputIuranRegularFrame.jLabelLevel1.text")); // NOI18N

        jTextFieldNama.setText(profil.biodata.nama);
        jTextFieldNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNamaActionPerformed(evt);
            }
        });

        jTextFieldNomorInduk.setText(org.openide.util.NbBundle.getMessage(InputIuranRegularFrame.class, "InputIuranRegularFrame.jTextFieldNomorInduk.text")); // NOI18N
        jTextFieldNomorInduk.setText(profil.noInduk);

        jTextFieldLevel2Level3.setText(org.openide.util.NbBundle.getMessage(InputIuranRegularFrame.class, "InputIuranRegularFrame.jTextFieldLevel2Level3.text")); // NOI18N
        jTextFieldLevel2Level3.setText(profil.currentLevel.level2.toString().concat(" "+ profil.currentLevel.level3.toString()));

        javax.swing.GroupLayout jPanelProfilLayout = new javax.swing.GroupLayout(jPanelProfil);
        jPanelProfil.setLayout(jPanelProfilLayout);
        jPanelProfilLayout.setHorizontalGroup(
            jPanelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProfilLayout.createSequentialGroup()
                .addGroup(jPanelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(jTextFieldLevel2Level3, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelProfilLayout.setVerticalGroup(
            jPanelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProfilLayout.createSequentialGroup()
                .addGroup(jPanelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLableProfilTitle)
                    .addComponent(jLabelLevel1))
                .addGap(24, 24, 24)
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
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanelSeragam.setPreferredSize(new java.awt.Dimension(1024, 176));

        jLabelSeragamTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelSeragamTitle.setText(org.openide.util.NbBundle.getMessage(InputIuranRegularFrame.class, "InputIuranRegularFrame.jLabelSeragamTitle.text")); // NOI18N

        jLabelAmount.setText(org.openide.util.NbBundle.getMessage(InputIuranRegularFrame.class, "InputIuranRegularFrame.jLabelAmount.text")); // NOI18N

        jTextFieldSeragamAmount.setText(org.openide.util.NbBundle.getMessage(InputIuranRegularFrame.class, "InputIuranRegularFrame.jTextFieldSeragamAmount.text")); // NOI18N
        jTextFieldSeragamAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSeragamAmountActionPerformed(evt);
            }
        });
        jTextFieldSeragamAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSeragamAmountKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldSeragamAmountKeyTyped(evt);
            }
        });

        jLabelIKSChargedLevel.setText(org.openide.util.NbBundle.getMessage(InputIuranRegularFrame.class, "InputIuranRegularFrame.jLabelIKSChargedLevel.text")); // NOI18N

        jComboBox1.setModel(level1ComboBoxModel);

        jLabelIKSChargedLevel2Level3.setText(org.openide.util.NbBundle.getMessage(InputIuranRegularFrame.class, "InputIuranRegularFrame.jLabelIKSChargedLevel2Level3.text")); // NOI18N

        jComboBox2.setModel(level2ComboBoxModel);

        jComboBox3.setModel(level3ComboBoxModel);

        jLabelNote.setText(org.openide.util.NbBundle.getMessage(InputIuranRegularFrame.class, "InputIuranRegularFrame.jLabelNote.text")); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabelAmount1.setText(org.openide.util.NbBundle.getMessage(InputIuranRegularFrame.class, "InputIuranRegularFrame.jLabelAmount1.text")); // NOI18N

        jTextFieldSeragamTransactionName.setText(org.openide.util.NbBundle.getMessage(InputIuranRegularFrame.class, "InputIuranRegularFrame.jTextFieldSeragamTransactionName.text")); // NOI18N
        jTextFieldSeragamTransactionName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSeragamTransactionNameActionPerformed(evt);
            }
        });
        jTextFieldSeragamTransactionName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSeragamTransactionNameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldSeragamTransactionNameKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanelSeragamLayout = new javax.swing.GroupLayout(jPanelSeragam);
        jPanelSeragam.setLayout(jPanelSeragamLayout);
        jPanelSeragamLayout.setHorizontalGroup(
            jPanelSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSeragamLayout.createSequentialGroup()
                .addGroup(jPanelSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSeragamTitle)
                    .addGroup(jPanelSeragamLayout.createSequentialGroup()
                        .addGroup(jPanelSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAmount)
                            .addComponent(jLabelIKSChargedLevel)
                            .addComponent(jLabelNote)
                            .addComponent(jLabelAmount1))
                        .addGap(29, 29, 29)
                        .addGroup(jPanelSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldSeragamTransactionName)
                            .addGroup(jPanelSeragamLayout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelIKSChargedLevel2Level3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1)
                            .addComponent(jTextFieldSeragamAmount))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelSeragamLayout.setVerticalGroup(
            jPanelSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSeragamLayout.createSequentialGroup()
                .addComponent(jLabelSeragamTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAmount1)
                    .addComponent(jTextFieldSeragamTransactionName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAmount)
                    .addComponent(jTextFieldSeragamAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIKSChargedLevel)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelIKSChargedLevel2Level3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNote)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButtonSaveAll.setText(org.openide.util.NbBundle.getMessage(InputIuranRegularFrame.class, "InputIuranRegularFrame.jButtonSaveAll.text")); // NOI18N
        jButtonSaveAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelButtonLayout = new javax.swing.GroupLayout(jPanelButton);
        jPanelButton.setLayout(jPanelButtonLayout);
        jPanelButtonLayout.setHorizontalGroup(
            jPanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonLayout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(jButtonSaveAll)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelButtonLayout.setVerticalGroup(
            jPanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelButtonLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jButtonSaveAll)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelProfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelSeragam, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
            .addComponent(jPanelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelSeragam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94)
                .addComponent(jPanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNamaActionPerformed

    private void jTextFieldSeragamAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSeragamAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSeragamAmountActionPerformed

    private void jTextFieldSeragamAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSeragamAmountKeyReleased
        // TODO add your handling code here:
        jButtonSaveAll.setEnabled(!validationPanel.isFatalProblem());
    }//GEN-LAST:event_jTextFieldSeragamAmountKeyReleased

    private void jTextFieldSeragamAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSeragamAmountKeyTyped
        // TODO add your handling code here:
        jButtonSaveAll.setEnabled(!validationPanel.isFatalProblem());
    }//GEN-LAST:event_jTextFieldSeragamAmountKeyTyped

    private void jButtonSaveAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveAllActionPerformed
        try {
            // TODO add your handling code here:
            saveInsertIuran();
            JOptionPane.showMessageDialog(rootPane, "Input Iuran Berhasil");
            this.dispose();
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            JOptionPane.showMessageDialog(rootPane, "Input Iuran Gagal!\r\n".concat(ex.toString()));
        } catch (KasirException ex) {
            Exceptions.printStackTrace(ex);
            JOptionPane.showMessageDialog(rootPane, "Input Iuran Gagal!\r\n".concat(ex.toString()));
        }
    }//GEN-LAST:event_jButtonSaveAllActionPerformed

    private void jTextFieldSeragamTransactionNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSeragamTransactionNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSeragamTransactionNameActionPerformed

    private void jTextFieldSeragamTransactionNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSeragamTransactionNameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSeragamTransactionNameKeyReleased

    private void jTextFieldSeragamTransactionNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSeragamTransactionNameKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSeragamTransactionNameKeyTyped

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
            java.util.logging.Logger.getLogger(InputIuranRegularFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputIuranRegularFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputIuranRegularFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputIuranRegularFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new InputIuranRegularFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSaveAll;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabelAmount;
    private javax.swing.JLabel jLabelAmount1;
    private javax.swing.JLabel jLabelIKSChargedLevel;
    private javax.swing.JLabel jLabelIKSChargedLevel2Level3;
    private javax.swing.JLabel jLabelLevel1;
    private javax.swing.JLabel jLabelLevel2Level3;
    private javax.swing.JLabel jLabelNama;
    private javax.swing.JLabel jLabelNoInduk;
    private javax.swing.JLabel jLabelNote;
    private javax.swing.JLabel jLabelSeragamTitle;
    private javax.swing.JLabel jLableProfilTitle;
    private javax.swing.JPanel jPanelButton;
    private javax.swing.JPanel jPanelProfil;
    private javax.swing.JPanel jPanelSeragam;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldLevel2Level3;
    private javax.swing.JTextField jTextFieldNama;
    private javax.swing.JTextField jTextFieldNomorInduk;
    private javax.swing.JTextField jTextFieldSeragamAmount;
    private javax.swing.JTextField jTextFieldSeragamTransactionName;
    // End of variables declaration//GEN-END:variables
    private Profil profil;

    private void saveInsertIuran() throws SQLException, KasirException {
        Seragam iks = new Seragam(profil.noInduk, profil.currentLevel, jTextFieldSeragamTransactionName.getText(), Float.valueOf(jTextFieldSeragamAmount.getText()), jTextArea1.getText());
        //Temen-temennya IPSP coding disini, sama nanti insert juga
        Control.insertIuran(Iuran.Tipe.Seragam, iks);
        
    }
}
