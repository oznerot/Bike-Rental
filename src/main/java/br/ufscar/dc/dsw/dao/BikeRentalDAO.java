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

import br.ufscar.dc.dsw.model.BikeRental;
import br.ufscar.dc.dsw.model.Client;
import br.ufscar.dc.dsw.model.RentalCompany;

public class BikeRentalDAO extends GenericDAO<BikeRental>
{
    //CRUD - Create
    @Override
    public void insert(BikeRental rental)
    {
        String sql = "INSERT INTO bike_rental (client_id, company_id, date_hour) VALUES (?, ?, ?)";

        try
        {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, rental.getClient().getId());
            ps.setInt(2, rental.getCompany().getId());
            ps.setTimestamp(3, Timestamp.valueOf(rental.getDateHour()));
            
            ps.executeUpdate();

            ps.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    //CRUD - Read
    @Override
    public BikeRental getById(int id)
    {
        BikeRental rental = null;
        String sql = "SELECT * from bike_rental where rental_id = ?";

        try
        {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                int clientId = rs.getInt("client_id");
                int companyId = rs.getInt("company_id");
                LocalDateTime dateHour = rs.getTimestamp("date_hour").toLocalDateTime();

                Client client = new ClientDAO().getById(clientId);
                RentalCompany company = new RentalCompanyDAO().getById(companyId);

                rental = new BikeRental(id, client, company, dateHour);
            }

            rs.close();
            ps.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return rental;
    }
    
    public List<BikeRental> getAllByClient(Client client)
    {
        List<BikeRental> rentalList = new ArrayList<>();

        String sql = "SELECT * FROM bike_rental WHERE client_id = ? ORDER BY rental_id";

        try
        {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, client.getId());

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int rentalId = rs.getInt("rental_id");
                int companyId = rs.getInt("company_id");
                LocalDateTime dateHour = rs.getTimestamp("date_hour").toLocalDateTime();
                
                RentalCompany company = new RentalCompanyDAO().getById(companyId);

                BikeRental rental = new BikeRental(rentalId, client, company, dateHour);

                rentalList.add(rental);
            }

            rs.close();
            ps.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return rentalList;
    }

    public List<BikeRental> getAllByCompany(RentalCompany company)
    {
        List<BikeRental> rentalList = new ArrayList<>();

        String sql = "SELECT * FROM bike_rental WHERE company_id = ? ORDER BY rental_id";

        try
        {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, company.getId());

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int rentalId = rs.getInt("rental_id");
                int clientId = rs.getInt("client_id");
                LocalDateTime dateHour = rs.getTimestamp("date_hour").toLocalDateTime();
                
                Client client = new ClientDAO().getById(clientId);

                BikeRental rental = new BikeRental(rentalId, client, company, dateHour);

                rentalList.add(rental);
            }

            rs.close();
            ps.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return rentalList;
    }

    @Override
    public List<BikeRental> getAll()
    {
        return null;
    }

    public boolean verifyRental(BikeRental rental)
    {
        List<BikeRental> rentalList = new ArrayList<>();

        String sql = "SELECT * from bike_rental WHERE (client_id = ? OR company_id = ?) AND date_hour = ?";

        try
        {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, rental.getClient().getId());
            ps.setInt(2, rental.getCompany().getId());
            ps.setTimestamp(3, Timestamp.valueOf(rental.getDateHour()));

            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                int rentalId = rs.getInt("rental_id");
                int clientId = rs.getInt("client_id");
                int companyId = rs.getInt("company_id");
                LocalDateTime dateHour = rs.getTimestamp("date_hour").toLocalDateTime();

                Client client = new ClientDAO().getById(clientId);
                RentalCompany company = new RentalCompanyDAO().getById(companyId);

                BikeRental newRental = new BikeRental(rentalId, client, company, dateHour);

                rentalList.add(newRental);
            }

            rs.close();
            ps.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return rentalList.isEmpty();
    }

    @Override
    public void update(BikeRental rental)
    {
        String sql= "UPDATE bike_rental SET date_hour = ? WHERE rental_id = ?";

        try
        {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setTimestamp(1, Timestamp.valueOf(rental.getDateHour()));
            ps.setInt(2, rental.getId());

            ps.executeUpdate();

            ps.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void delete(BikeRental rental) {}
}