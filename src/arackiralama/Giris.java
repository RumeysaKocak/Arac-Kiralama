/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arackiralama;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class Giris extends javax.swing.JFrame {
   DBKatmani db = new DBKatmani();
   
    /**
     * Creates new form login
     */
    public Giris() {
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

        jPanel1 = new javax.swing.JPanel();
        emailLabel = new javax.swing.JLabel();
        sifreLabel = new javax.swing.JLabel();
        emailTextBox = new javax.swing.JTextField();
        sifreTextBox = new javax.swing.JPasswordField();
        girisButton = new javax.swing.JButton();
        kaydolButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Giriş Ekranı");
        setBackground(new java.awt.Color(255, 255, 0));
        setForeground(new java.awt.Color(153, 204, 255));
        setLocation(new java.awt.Point(500, 200));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 153, 204));

        emailLabel.setText("  E mail");
        emailLabel.setMaximumSize(new java.awt.Dimension(30, 15));

        sifreLabel.setText("  Şifre");

        sifreTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sifreTextBoxActionPerformed(evt);
            }
        });

        girisButton.setBackground(new java.awt.Color(204, 153, 255));
        girisButton.setText("Giriş Yap");
        girisButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        girisButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                girisButtonClicked(evt);
            }
        });
        girisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                girisButtonActionPerformed(evt);
            }
        });

        kaydolButton.setBackground(new java.awt.Color(255, 255, 51));
        kaydolButton.setText("Kaydol");
        kaydolButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        kaydolButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kaydolButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(emailTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(sifreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(sifreTextBox)))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(girisButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(kaydolButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sifreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sifreTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(girisButton)
                .addGap(18, 18, 18)
                .addComponent(kaydolButton)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sifreTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sifreTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sifreTextBoxActionPerformed

    private void girisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_girisButtonActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_girisButtonActionPerformed

    private void girisButtonClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_girisButtonClicked
                                  
      
        try{
     String email = emailTextBox.getText();
     String sifre = sifreTextBox.getText();
      
     boolean giris = db.giris(email,sifre);
     if(giris){
    JOptionPane.showMessageDialog(null, "giriş başarılı", "", JOptionPane.INFORMATION_MESSAGE);
    if(db.isAdmin){
    admin adminFrom = new admin();
    adminFrom.setVisible(true);
    
    }else{
    aracKiralama aracKiralamaForm = new aracKiralama();
    aracKiralamaForm.setVisible(true);
    }
      this.setVisible(true);     

     }
     else{
                 JOptionPane.showMessageDialog(null, "giriş başarısız", "", JOptionPane.INFORMATION_MESSAGE);

     }

      }
      catch(Exception ex){
      
      }

                                       

    }//GEN-LAST:event_girisButtonClicked

    private void kaydolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kaydolButtonActionPerformed
        // TODO add your handling code here:
     Kayit kayitForm = new Kayit();
    kayitForm.setVisible(true);
    this.setVisible(false);
    }//GEN-LAST:event_kaydolButtonActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        db.baglan();
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
            java.util.logging.Logger.getLogger(Giris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Giris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Giris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Giris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Giris().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextBox;
    private javax.swing.JButton girisButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton kaydolButton;
    private javax.swing.JLabel sifreLabel;
    private javax.swing.JPasswordField sifreTextBox;
    // End of variables declaration//GEN-END:variables
}
