package org.example.model.dao;

import org.example.model.objects.dto.BookingDetail;
import org.example.model.objects.dto.User;
import org.example.model.objects.entities.Booking;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BuchungDAO extends AbstractDAO {

    public static BuchungDAO dao = null;

    private BuchungDAO(){

    }

    public static BuchungDAO getInstance(){
        if(dao == null){
            dao = new BuchungDAO();
        }
        return dao;
    }

    public boolean addBooking(Booking booking){
        String sql = "insert into oemerdb.booking values (default,?,?,?,?,?,?,?);";
        PreparedStatement statement = this.getPreparedStatement(sql);

        //Zeilenweise Abbildung der Dateb auf die Spalten der erzeugten Zeile
        try{
            //TODO, Date(int,int,int) deprecated aber kA wie die richtige Konvertierung funktionieren soll
            statement.setDate(1, new java.sql.Date(booking.getAnreise().getYear()-1900, booking.getAnreise().getMonthValue()-1, booking.getAnreise().getDayOfMonth()));
            statement.setDate(2, new java.sql.Date(booking.getAbreise().getYear()-1900, booking.getAbreise().getMonthValue()-1, booking.getAbreise().getDayOfMonth()));
            statement.setString(3, booking.getIban());
            statement.setInt(4, booking.getNumber());
            statement.setString(5, booking.getUser().getEmail());
            statement.setDate(6, new java.sql.Date(booking.getDatumBuchung().getYear()-1900, booking.getDatumBuchung().getMonthValue()-1, booking.getDatumBuchung().getDayOfMonth()));
            statement.setInt(7, booking.getHotel().getId());

            statement.executeUpdate();

            setBuchungsID(booking);
            return  true;

        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    private void setBuchungsID(Booking booking){
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
        booking.setId(currentValue);
    }

    public void deleteBookingBy(int id){
        Statement statement = this.getStatement();
        try{
            statement.execute("DELETE FROM oemerdb.booking WHERE oemerdb.booking.id = \'" + id + "\';");

        } catch(SQLException ex){
            System.out.println("BuchungDAO deleteBookingBy(id)");
        }
    }

    public List<BookingDetail> getAllBookingsForUser(User user){
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

        List<BookingDetail> liste = new ArrayList<>();
        BookingDetail booking;

        try{
            while(rs.next()){
                booking = new BookingDetail();
                booking.setHotel(rs.getString(1));
                booking.setId(rs.getInt(2));
                booking.setAnreise(rs.getDate(3));
                booking.setAbreise(rs.getDate(4));
                booking.setDatumBuchung(rs.getDate(5));

                liste.add(booking);
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return liste;
    }

}
