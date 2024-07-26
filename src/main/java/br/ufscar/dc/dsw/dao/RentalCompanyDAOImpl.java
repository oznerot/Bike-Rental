package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

import br.ufscar.dc.dsw.dao.RentalCompanyDAO;
import br.ufscar.dc.dsw.model.RentalCompany;
import br.ufscar.dc.dsw.Database;

public class RentalCompanyDAOImpl implements RentalCompanyDAO
{
    @Override
    public RentalCompany get(int id)
    {
        RentalCompany company = null;
        String sql = "SELECT id, name, email, password, cnpj, city FROM rental_company WHERE id = ?";

        //Here I'm using try-with-resources statement to let the Automatic Resource Management close
        //Connection, preparedStatement and ResultSet objects for me so I don't mess things up.
        try(Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, id);

            try(ResultSet rs = ps.executeQuery())
            {
                if(rs.next())
                {
                    int companyId = rs.getInt("id");
                    String companyName = rs.getString("name");
                    String companyEmail = rs.getString("email");
                    String companyPassword = rs.getString("password");
                    String companyCnpj = rs.getString("cnpj");
                    String companyCity = rs.getString("city");

                    company = new RentalCompany(companyId, companyName, companyEmail, companyPassword, companyCnpj, companyCity);

                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return company;
    }
    @Override
    public List<RentalCompany> getByCity(String city)
    {
        List<RentalCompany> companyList = new ArrayList<>();
        String sql = "SELECT id, name, email, password, cnpj, city FROM rental_company WHERE city = ?";

        try(Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, city);
            try(ResultSet rs = ps.executeQuery())
            {
                while(rs.next())
                {
                    int companyId = rs.getInt("id");
                    String companyName = rs.getString("name");
                    String companyEmail = rs.getString("email");
                    String companyPassword = rs.getString("password");
                    String companyCnpj = rs.getString("cnpj");
                    String companyCity = rs.getString("city");

                    RentalCompany company = new RentalCompany(companyId, companyName, companyEmail, companyPassword, companyCnpj, companyCity);

                    companyList.add(company);
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return companyList;
    }

    @Override
    public List<RentalCompany> getAll()
    {
        List<RentalCompany> companyList = new ArrayList<>();
        String sql = "SELECT id, name, email, password, cnpj, city FROM rental_company";

        try(Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql))
        {
            while(rs.next())
            {
                int companyId = rs.getInt("id");
                String companyName = rs.getString("name");
                String companyEmail = rs.getString("email");
                String companyPassword = rs.getString("password");
                String companyCnpj = rs.getString("cnpj");
                String companyCity = rs.getString("city");
                
                RentalCompany company = new RentalCompany(companyId, companyName, companyEmail, companyPassword, companyCnpj, companyCity);

                companyList.add(company);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return companyList;
    }

    @Override
    public int insert(RentalCompany company)
    {
        int result = 0;
        String sql = "INSERT INTO rental_company (name, email, password, cnpj, city) VALUES (?, ?, ?, ?, ?)";

        try(Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, company.getName());
            ps.setString(2, company.getEmail());
            ps.setString(3, company.getPassword());
            ps.setString(4, company.getCnpj());
            ps.setString(5, company.getCity());

            result = ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }
    
    @Override
    public int update(RentalCompany company)
    {
        int result = 0;
        String sql = "UPDATE rental_company SET name = ?, email = ?, password = ?, cnpj = ?, city = ? WHERE id = ?";

        try(Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, company.getName());
            ps.setString(2, company.getEmail());
            ps.setString(3, company.getPassword());
            ps.setString(4, company.getCnpj());
            ps.setString(5, company.getCity());
            ps.setInt(6, company.getId());

            result = ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int delete(RentalCompany company)
    {
        int result = 0;
        String sql = "DELETE FROM rental_company WHERE id = ?";

        try(Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, company.getId());

            result = ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }
}