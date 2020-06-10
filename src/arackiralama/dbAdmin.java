package arackiralama;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class dbAdmin {
    private Connection conn;
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
public boolean arabaEkle(String marka,String model,String fiyat){
        
        if(conn == null){
            baglan();
        }
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("Insert Into RUMSUFK.\"arabaozellikleri\" (marka,model,ucret) values ('"+marka+"','"+model+"','"+fiyat+"')");
               System.out.println("");
            return true;
           }catch(Exception e){
               System.out.println(e.getMessage());
            return false;
                }

} 
public boolean arabaSil (String arabaninId){
        
        if(conn == null){
            baglan();
        }
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM RUMSUFK.\"arabaozellikleri\" WHERE id="+arabaninId);
                           System.out.println("silinmis" +arabaninId );

           return true;
           }catch(Exception e){
               System.out.println(e.getMessage());
            return false;
    }

} 
public boolean kiralananSil (String kiralamaId){
        
        if(conn == null){
            baglan();
        }
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM RUMSUFK.\"kiralama\" WHERE id="+kiralamaId);
                           System.out.println("silinmis" +kiralamaId );

           return true;
           }catch(Exception e){
               System.out.println(e.getMessage());
            return false;
    }

} 

public String arabaGuncelle(String arabaninId,String marka,String model,String fiyat){
        
        if(conn == null){
            baglan();
        }
        try{
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("UPDATE RUMSUFK.\"arabaozellikleri\" SET MARKA='"+marka+"', Model = '"+model+"', Ucret = '"+fiyat+"' WHERE id="+arabaninId);
           return "kayıt güncellendi";
           }catch(Exception e){
            return "kayıt güncellenmedi sebep: \n "+ e.getMessage();
    }

} 

public String kiralananGuncelle(String kiralananId,String musteri_id,String araba_id,String alis_tarihi,String donus_tarihi,String marka,String model){
        
        if(conn == null){
            baglan();
        }
        try{
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("UPDATE RUMSUFK.\"kiralama\" SET  musteri_id = "+musteri_id+",araba_id = "+araba_id+",alis_tarihi = '"+alis_tarihi+"',donus_tarihi = '"+donus_tarihi+"',MARKA='"+marka+"', Model = '"+model+"' WHERE id="+kiralananId);
           return "güncellendi";
           }catch(Exception e){
            return "güncellenmedi sebep: \n "+ e.getMessage();
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
         yourModel.addRow(new Object[]{id,marka, model, ucret,"musait","sil","değiştir"});
    }else{
         yourModel.addRow(new Object[]{id,marka, model, ucret,"kiralanmış","sil","değiştir"});
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

     public void kullanicilar(JTable arabalarTable){
DefaultTableModel dtm = (DefaultTableModel) arabalarTable.getModel();
dtm.setRowCount(0);
if(conn == null){
            System.out.println("Veritabanı Bağlı Değil, Bağlanıyor");
            baglan();
        }
        try{  
            Statement stmt = conn.createStatement();  
            ResultSet rs = stmt.executeQuery("select * from RUMSUFK.KULLANICI ORDER BY ID DESC");
              DefaultTableModel yourModel = (DefaultTableModel) arabalarTable.getModel();

while(rs.next())
{
    String id = rs.getString("id");
    String ad = rs.getString("ad");
    String soyad = rs.getString("soyad");
    String telefon = rs.getString("telefon");
    String adres = rs.getString("adres");
    String sifre = rs.getString("sifre");
    String email = rs.getString("email");
    String admin = rs.getString("admin");
    if(admin.contains("false")){
    admin = "Admin Yap";
    }else{
    admin = "Adminden Çıkar";
        }
    
         yourModel.addRow(new Object[]{id,ad,soyad,telefon, adres, sifre,email,admin,"değiştir","sil"});
}        
           }catch(Exception e){
               e.printStackTrace();
    }
} 
public String kullaniciGuncelle(String kullaniciId,String ad,String soyad,String telefon,String adres,String sifre,String email){
        
        if(conn == null){
            baglan();
        }
        try{
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("UPDATE RUMSUFK.KULLANICI SET  ad = '"+ad+"',soyad = '"+soyad+"',telefon = '"+telefon+"',adres = '"+adres+"',sifre='"+sifre+"', email = '"+email+"' WHERE id="+kullaniciId);
           return "güncellendi";
           }catch(Exception e){
            return "güncellenmedi sebep: \n "+ e.getMessage();
    }

} 

public String kullaniciAdminYap(String kullaniciId,String admin){
        
        if(conn == null){
            baglan();
        }
        try{
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("UPDATE RUMSUFK.KULLANICI SET  admin = '"+admin+"' WHERE id="+kullaniciId);
           return "güncellendi";
           }catch(Exception e){
            return "güncellenmedi sebep: \n "+ e.getMessage();
    }

} 
public boolean kullaniciSil (String kullaniciId){
        
        if(conn == null){
            baglan();
        }
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM RUMSUFK.KULLANICI WHERE id="+kullaniciId);
                           System.out.println("silinmis" +kullaniciId );

           return true;
           }catch(Exception e){
               System.out.println(e.getMessage());
            return false;
    }

} 
          public void kiralananArabalar(JTable arabalarTable){
DefaultTableModel dtm = (DefaultTableModel) arabalarTable.getModel();
dtm.setRowCount(0);
if(conn == null){
            System.out.println("Veritabanı Bağlı Değil, Bağlanıyor");
            baglan();
        }
        try{  
            Statement stmt = conn.createStatement();  
            ResultSet rs = stmt.executeQuery("select * from RUMSUFK.\"kiralama\" ORDER BY ALIS_TARIHI DESC");
              DefaultTableModel yourModel = (DefaultTableModel) arabalarTable.getModel();

while(rs.next())
{
    String id = rs.getString("id");
    String musteri_id = rs.getString("musteri_id");
    String araba_id = rs.getString("araba_id");
    String marka = rs.getString("Marka");
    String model = rs.getString("model");
    String alis_tarihi = rs.getString("alis_tarihi");
    String donus_tarihi = rs.getString("donus_tarihi");
         yourModel.addRow(new Object[]{id,araba_id,musteri_id,marka, model, alis_tarihi,donus_tarihi,"değiştir","sil"});
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

    void arabaSil(int arabaninId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    }
 