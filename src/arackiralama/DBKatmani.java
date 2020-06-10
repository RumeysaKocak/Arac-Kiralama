/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arackiralama;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import javax.swing.JTextField;


/**
 *
 * @author ASUS
 */
public class DBKatmani {
    private Connection conn;
    public static boolean isAdmin = false;
    String dburl = "jdbc:derby://localhost:1527/rentcar";
    String user = "rumsufk";
    String password = "1402019";
    public Connection baglan(){
        try{
        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            System.out.println("Bağlantı Başarılı");
            conn = DriverManager.getConnection(dburl,user,password);
    }
        catch(Exception e){
            System.out.println("Bağlantıda Sorun Var");
        }
        return conn;
}
    public void kulaniciListesi(){
        if(conn == null){
            System.out.println("Veritabanı Bağlı Değil, Bağlanıyor");
            baglan();
        }
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from kullanici");
            System.out.println("kullanici id \t ad \t soyad \t telefon \t adres \t email \t sifre");
           
           }catch(Exception e){
               e.printStackTrace();
    }
} 

        public boolean giris(String email,String sifre) throws InterruptedException{
        if(conn == null){
            System.out.println("Veritabanı Bağlı Değil, Bağlanıyor");
            baglan();
TimeUnit.SECONDS.sleep(4);
            return false;
        }
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from kullanici WHERE email='"+email+"' AND sifre='"+sifre+"'");
int row_count = 0;
try {
    while(rs.next()){
        row_count++;
        if(rs.getString("id") != null){
         aracKiralama.musteriId = rs.getString("id");
         isAdmin = Boolean.parseBoolean(rs.getString("admin"));
        }
    }
}
catch(Exception ex) { 
    System.out.println(ex.getMessage());}
if(row_count==1){
return true;
}else {

return false;
}
           
           }catch(Exception e){
                   System.out.println(e.getMessage());

return false;
    }
} 
public String kayit(String ad,String soyad,String telefon,String adres,String email ,String sifre){
        
        if(conn == null){
            baglan();
        }
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into kullanici (AD, SOYAD, TELEFON, ADRES, EMAIL,SIFRE) values ('"+ad+"', '"+soyad+"', '"+telefon+"', '"+adres+"', '"+email+"', '"+sifre+"')");
           return "kayıt başarılı";
           }catch(Exception e){
            return "kayıt başarılı degil sebep : \n "+ e.getMessage();
    }

} 
public String dataGuncelle(String musteriId,String ad,String soyad,String telefon,String adres,String email ,String sifre){
        
        if(conn == null){
            baglan();
        }
        try{
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("UPDATE RUMSUFK.KULLANICI SET AD='"+ad+"', SOYAD = '"+soyad+"', TELEFON = '"+telefon+"', ADRES = '"+adres+"', EMAIL = '"+email+"' ,SIFRE='"+sifre+"' WHERE id="+musteriId);
           return "kayıt güncellendi";
           }catch(Exception e){
            return "kayıt güncellenmedi sebep: \n "+ e.getMessage();
    }

} 
public boolean arabaAl(String marka,String model,String musteriId,int arabaId,java.sql.Date alisTarihi,java.sql.Date donusTarihi){
        
        if(conn == null){
            baglan();
        }
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into RUMSUFK.\"kiralama\" (marka,model,musteri_id,araba_id, alis_tarihi, donus_tarihi) values ('"+marka+"','"+model+"',"+musteriId+", "+arabaId+", '"+alisTarihi+"', '"+donusTarihi+"')");
           return true;
           }catch(Exception e){
               System.out.println(e.getMessage());
            return false;
    }

} 
    
     public void arabalar(JTable arabalarTable ){
DefaultTableModel dtm = (DefaultTableModel) arabalarTable.getModel();
dtm.setRowCount(0);

if(conn == null){
            System.out.println("Veritabanı Bağlı Değil, Bağlanıyor");
            baglan();
        }
        try{  
            Statement stmt = conn.createStatement();
            java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            ArrayList<String> alinanArabalarArray = new ArrayList<String>(); // Create an ArrayList object
            ResultSet alinanArabalar = stmt.executeQuery("select araba_id from RUMSUFK.\"kiralama\" WHERE donus_tarihi > '"+currentDate+"' AND alis_tarihi <= '"+currentDate+"'");
            int i=0;    
            while(alinanArabalar.next())
                {
                      alinanArabalarArray.add(alinanArabalar.getString("araba_id"));
                }

                        
            ResultSet rs = stmt.executeQuery("select * from RUMSUFK.\"arabaozellikleri\"");
              DefaultTableModel yourModel = (DefaultTableModel) arabalarTable.getModel();

while(rs.next())
{
    String id = rs.getString("id");
    String marka = rs.getString("Marka");
    String model = rs.getString("model");
    String ucret = rs.getString("ucret");
    if(!alinanArabalarArray.contains(id)){
         yourModel.addRow(new Object[]{id,marka, model, ucret,"sınırsız","kirala"});
    }
}        
Statement stmt2 = conn.createStatement();
 ResultSet musaitTarihler = stmt2.executeQuery("select araba_id,alis_tarihi from RUMSUFK.\"kiralama\" WHERE alis_tarihi > '"+currentDate+"'");
  while(musaitTarihler.next()){
 for(int ixx = 0; ixx < arabalarTable.getRowCount(); ixx++){//For each row
           if(arabalarTable.getModel().getValueAt(ixx, 0).equals(musaitTarihler.getString("araba_id"))){//Search the model
              yourModel.setValueAt(musaitTarihler.getString("alis_tarihi"), ixx, 4);
           }

    }//For loop inner
}//For loop outer
           }catch(Exception e){
               e.printStackTrace();
    }
} 

     public void kiraladigimArabalar(JTable arabalarTable , String musteriId){
DefaultTableModel dtm = (DefaultTableModel) arabalarTable.getModel();
dtm.setRowCount(0);
if(conn == null){
            System.out.println("Veritabanı Bağlı Değil, Bağlanıyor");
            baglan();
        }
        try{  
            Statement stmt = conn.createStatement();  
            ResultSet rs = stmt.executeQuery("select * from RUMSUFK.\"kiralama\" WHERE musteri_id="+musteriId+" ORDER BY ALIS_TARIHI DESC");
              DefaultTableModel yourModel = (DefaultTableModel) arabalarTable.getModel();

while(rs.next())
{
    String id = rs.getString("id");
    String araba_id = rs.getString("araba_id");
    String marka = rs.getString("Marka");
    String model = rs.getString("model");
    String alis_tarihi = rs.getString("alis_tarihi");
    String donus_tarihi = rs.getString("donus_tarihi");
         yourModel.addRow(new Object[]{id,araba_id,marka, model, alis_tarihi,donus_tarihi});
}        
           }catch(Exception e){
               e.printStackTrace();
    }
} 
     public void kullaniciBilgileri(String musteriId , JTextField ad ,JTextField soyad ,JTextField telefon ,JTextField adres ,JTextField email ,JTextField sifre ){

if(conn == null){
            System.out.println("Veritabanı Bağlı Değil, Bağlanıyor");
            baglan();
        }
        try{  
            Statement stmt = conn.createStatement();  
            ResultSet rs = stmt.executeQuery("select * from RUMSUFK.KULLANICI WHERE id="+musteriId);

while(rs.next())
{
    ad.setText(rs.getString("ad"));
    soyad.setText(rs.getString("soyad"));
    telefon.setText(rs.getString("telefon"));
    adres.setText(rs.getString("adres"));
    email.setText(rs.getString("email"));
    sifre.setText(rs.getString("sifre"));
}        
           }catch(Exception e){
               e.printStackTrace();
    }
} 

    
      public void tarihler(){
        if(conn == null){
            System.out.println("Veritabanı Bağlı Değil, Bağlanıyor");
            baglan();
        }
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from tarihler");
            System.out.println("tarih id \t kayitgunu \t donustarihi");
           
           }catch(Exception e){
               e.printStackTrace();
    }
} 
    public static void main(String args[]) {
   
    }


    
    }
 