import java.sql.*;
import java.util.ArrayList;

public class CalisanIslemleri {

     private Connection connection = null;
     private Statement statement = null;
     private PreparedStatement preparedStatement = null;

     public boolean girisYap(String kullanıcıadı,String parola){

         String sorgu = "Select * From adminler where username = ? and password = ?";
         try {
             preparedStatement = connection.prepareStatement(sorgu);
             preparedStatement.setString(1, kullanıcıadı);
             preparedStatement.setString(2, parola);
             ResultSet resultSet = preparedStatement.executeQuery();

             return resultSet.next(); //true veya false dönücek duruma göre

         } catch (SQLException e) {
             throw new RuntimeException(e);
         }

     }

     public void calisanEkle(String ad,String soyad,String departman,String maaş){

         String sorgu = "Insert into calisanlar (ad,soyad,departman,maas) values(?,?,?,?)";
         try {
             preparedStatement = connection.prepareStatement(sorgu);
             preparedStatement.setString(1, ad);
             preparedStatement.setString(2, soyad);
             preparedStatement.setString(3, departman);
             preparedStatement.setString(4,maaş);
             preparedStatement.executeUpdate();
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
     }

     public void calısanGuncelle(int id,String yeniad,String yenisoyad,String yenidepartman,String yenimaaş){
         String sorgu = "Update calisanlar set ad=?,soyad=?,departman=?,maas=? where id=?";
         try {
             preparedStatement = connection.prepareStatement(sorgu);
             preparedStatement.setString(1, yeniad);
             preparedStatement.setString(2, yenisoyad);
             preparedStatement.setString(3, yenidepartman);
             preparedStatement.setString(4,yenimaaş);
             preparedStatement.setInt(5, id);
             preparedStatement.executeUpdate();
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }

     }

     public void calisanSil(int id){
         String sorgu = "Delete from calisanlar where id=?";
         try {
             preparedStatement = connection.prepareStatement(sorgu);
             preparedStatement.setInt(1, id);
             preparedStatement.executeUpdate();
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }

     }


     public ArrayList<Calısan> calısanlarıgetir(){

         ArrayList<Calısan> çıktı = new ArrayList<Calısan>();
         try {
             statement = connection.createStatement();
             String sorgu = "select * from calisanlar";
             ResultSet resultSet = statement.executeQuery(sorgu);

             while (resultSet.next()) {
                 int id = resultSet.getInt("id");
                 String ad = resultSet.getString("ad");
                 String soyad = resultSet.getString("soyad");
                 String departman = resultSet.getString("departman");
                 String maas = resultSet.getString("maas");
                 çıktı.add(new Calısan(id,ad,soyad,departman,maas));
             }
             return çıktı;
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
     }

     public CalisanIslemleri(){

         String url = "jdbc:mysql://" + Database.host + ":" + Database.port + "/" + Database.Databaseismi + "?useUnicode=true&characterEncoding=UTF-8";

         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
         } catch (ClassNotFoundException e) {
             System.out.println("Driver bulunamadı");
         }

         try {
             connection = DriverManager.getConnection(url,Database.kullanici_adi,Database.parola);
             System.out.println("Bağlantı başarılı");
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }


     }

}

