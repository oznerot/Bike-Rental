package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;

import br.ufscar.dc.dsw.dao.BikeRentalDAO;
import br.ufscar.dc.dsw.model.BikeRental;
import br.ufscar.dc.dsw.Database;

public class BikeRentalDAOImpl implements BikeRentalDAO
{

    //CRUD - Read
    @Override
    public BikeRental get(String uuid)
    {
        BikeRental rental = null;
        String sql = "SELECT * FROM bike_rental WHERE rental_id = ?";

        //Here I'm using try-with-resources statement to let the Automatic Resource Management close
        //Connection, preparedStatement and ResultSet objects for me so I don't mess things up.
        try(Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, uuid);

            try(ResultSet rs = ps.executeQuery())
            {
                if(rs.next())
                {
                    String rentalUUID = rs.getString("rental_id");
                    String clientUUID = rs.getString("client_id");
                    String companyUUID = rs.getString("company_id");
                    LocalDateTime dateHour = rs.getTimestamp("date_hour").toLocalDateTime();

                    rental = new BikeRental(rentalUUID, clientUUID, companyUUID, dateHour);
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return rental;
    }
    
    @Override
    public List<BikeRental> getAll()
    {
        List<BikeRental> rentalList = new ArrayList<>();
        String sql = "SELECT * FROM bike_rental";

        try(Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql))
        {
            while(rs.next())
            {
                String rentalUUID = rs.getString("rental_id");
                String clientUUID = rs.getString("client_id");
                String companyUUID = rs.getString("company_id");
                LocalDateTime dateHour = rs.getTimestamp("date_hour").toLocalDateTime();
                
                BikeRental rental = new BikeRental(rentalUUID, clientUUID, companyUUID, dateHour);

                rentalList.add(rental);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return rentalList;
    }

    @Override
    public int insert(BikeRental rental)
    {
        int result = 0;

        String sql = "INSERT INTO bike_rental (rental_id, client_id, company_id, date_hour) VALUES (?, ?, ?, ?)";

        try(Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, rental.getUUID());
            ps.setString(2, rental.getClientId());
            ps.setString(3, rental.getRentalCompanyId());
            ps.setTimestamp(4, Timestamp.valueOf(rental.getDateHour()));
            
            result = ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }
    
    @Override
    public int update(BikeRental rental)
    {
        int result = 0;
        String sql= "UPDATE bike_rental SET date_hour = ? WHERE rental_id = ?";

        try(Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
                ps.setTimestamp(1, Timestamp.valueOf(rental.getDateHour()));
                ps.setString(2, rental.getUUID());
                result = ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int delete(BikeRental rental)
    {
        int result = 0;
        String sql = "DELETE FROM bike_rental WHERE rental_id = ?";

        try(Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, rental.getUUID());

            result = ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }
}