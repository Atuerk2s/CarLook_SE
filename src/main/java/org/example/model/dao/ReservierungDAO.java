package org.example.model.dao;

import org.example.model.objects.dto.ReservierenDetail;
import org.example.model.objects.dto.User;
import org.example.model.objects.entities.Reservieren;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReservierungDAO extends AbstractDAO {

    public static ReservierungDAO dao = null;

    private ReservierungDAO(){

    }

    public static ReservierungDAO getInstance(){
        if(dao == null){
            dao = new ReservierungDAO();
        }
        return dao;
    }

    public boolean addReservierung(Reservieren reservieren){
        String sql = "insert into oemerdb.reservierung values (default,?,?,?,?,?,?);";
        PreparedStatement statement = this.getPreparedStatement(sql);

        //Zeilenweise Abbildung der Daten auf die Spalten der erzeugten Zeile
        try{
            //TODO, Date(int,int,int) deprecated aber kA wie die richtige Konvertierung funktionieren soll
            statement.setDate(1, new java.sql.Date(reservieren.getVon().getYear()-1900, reservieren.getVon().getMonthValue()-1, reservieren.getVon().getDayOfMonth()));
            statement.setDate(2, new java.sql.Date(reservieren.getBis().getYear()-1900, reservieren.getBis().getMonthValue()-1, reservieren.getBis().getDayOfMonth()));
            statement.setString(3, reservieren.getTelefonnummer());
            statement.setString(4, reservieren.getUser().getEmail());
            statement.setDate(5, new java.sql.Date(reservieren.getDatumBuchung().getYear()-1900, reservieren.getDatumBuchung().getMonthValue()-1, reservieren.getDatumBuchung().getDayOfMonth()));
            statement.setInt(6, reservieren.getAuto().getId());

            statement.executeUpdate();

            setBuchungsID(reservieren);
            return  true;

        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    private void setBuchungsID(Reservieren reservieren){
        Statement statement = this.getStatement();

        ResultSet rs = null;

        try{
            rs = statement.executeQuery("SELECT max(oemerdb.reservierung.id) "
                    + "FROM oemerdb.reservierung"
                    );
        } catch(SQLException ex){
            ex.printStackTrace();
        }

        int currentValue = 0;

        try{
            rs.next();
            currentValue = rs.getInt(1);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        reservieren.setId(currentValue);
    }

    public void deleteBookingBy(int id){
        Statement statement = this.getStatement();
        try{
            statement.execute("DELETE FROM oemerdb.reservierung WHERE oemerdb.reservierung.id = \'" + id + "\';");

        } catch(SQLException ex){
            System.out.println("ReservierungAO deleteBookingBy(id)");
        }
    }

    public List<ReservierenDetail> getAllBookingsForUser(User user){
        Statement statement = this.getStatement();

        ResultSet rs = null;
        try{
            rs = statement.executeQuery(
                    "SELECT oemerdb.hotel.name, oemerdb.reservierung.id, oemerdb.reservierung.von, oemerdb.reservierung.bis, oemerdb.reservierung.datumbuchung "
                    + "FROM oemerdb.reservierung JOIN oemerdb.auto "
                    + "ON ( oemerdb.reservierung.autoid = oemerdb.auto.id )"
                    + "WHERE oemerdb.reservierung.userid = \'" + user.getEmail() + "\' ");
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        if(rs == null) return null;

        List<ReservierenDetail> liste = new ArrayList<>();
        ReservierenDetail reserv;

        try{
            while(rs.next()){
                reserv = new ReservierenDetail();
                reserv.setAuto(rs.getString(1));
                reserv.setId(rs.getInt(2));
                reserv.setVon(rs.getDate(3));
                reserv.setBis(rs.getDate(4));
                reserv.setDatumReservierung(rs.getDate(5));

                liste.add(reserv);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return liste;
    }

}
