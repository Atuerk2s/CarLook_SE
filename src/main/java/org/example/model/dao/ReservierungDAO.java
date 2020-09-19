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
        String sql = "insert into oemerdb.booking values (default,?,?,?,?,?,?,?);";
        PreparedStatement statement = this.getPreparedStatement(sql);

        //Zeilenweise Abbildung der Dateb auf die Spalten der erzeugten Zeile
        try{
            //TODO, Date(int,int,int) deprecated aber kA wie die richtige Konvertierung funktionieren soll
            statement.setDate(1, new java.sql.Date(reservieren.getAnreise().getYear()-1900, reservieren.getAnreise().getMonthValue()-1, reservieren.getAnreise().getDayOfMonth()));
            statement.setDate(2, new java.sql.Date(reservieren.getAbreise().getYear()-1900, reservieren.getAbreise().getMonthValue()-1, reservieren.getAbreise().getDayOfMonth()));
            statement.setString(3, reservieren.getIban());
            statement.setInt(4, reservieren.getNumber());
            statement.setString(5, reservieren.getUser().getEmail());
            statement.setDate(6, new java.sql.Date(reservieren.getDatumBuchung().getYear()-1900, reservieren.getDatumBuchung().getMonthValue()-1, reservieren.getDatumBuchung().getDayOfMonth()));
            statement.setInt(7, reservieren.getHotel().getId());

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
            rs = statement.executeQuery("SELECT max(oemerdb.booking.id) "
                    + "FROM oemerdb.booking"
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
            statement.execute("DELETE FROM oemerdb.booking WHERE oemerdb.booking.id = \'" + id + "\';");

        } catch(SQLException ex){
            System.out.println("BuchungDAO deleteBookingBy(id)");
        }
    }

    public List<ReservierenDetail> getAllBookingsForUser(User user){
        Statement statement = this.getStatement();

        ResultSet rs = null;
        try{
            rs = statement.executeQuery(
                    "SELECT oemerdb.hotel.name, oemerdb.booking.id, oemerdb.booking.anreise, oemerdb.booking.abreise, oemerdb.booking.datumbuchung "
                    + "FROM oemerdb.booking JOIN oemerdb.hotel "
                    + "ON ( oemerdb.booking.hotelid = oemerdb.hotel.id )"
                    + "WHERE oemerdb.booking.userid = \'" + user.getEmail() + "\' ");
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        if(rs == null) return null;

        List<ReservierenDetail> liste = new ArrayList<>();
        ReservierenDetail booking;

        try{
            while(rs.next()){
                booking = new ReservierenDetail();
                booking.setAuto(rs.getString(1));
                booking.setId(rs.getInt(2));
                booking.setVon(rs.getDate(3));
                booking.setBis(rs.getDate(4));
                booking.setDatumReservierung(rs.getDate(5));

                liste.add(booking);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return liste;
    }

}
