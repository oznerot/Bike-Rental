package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

import br.ufscar.dc.dsw.model.RentalCompany;

public class RentalCompanyDAO extends GenericDAO<RentalCompany>
{
    @Override
    public void insert(RentalCompany company)
    {
        String userSQL = "INSERT INTO user (name, email, password, user_role) VALUES (?, ?, ?, ?)";
        
        String companySQL = "INSERT INTO rental_company (company_id, cnpj, city) VALUES (?, ?, ?)";
        
        try
        {
            Connection conn = this.getConnection();

            try
            {
                PreparedStatement userPstmt = conn.prepareStatement(userSQL, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement companyPstmt = conn.prepareStatement(companySQL);
                conn.setAutoCommit(false);

                userPstmt.setString(1, company.getName());
                userPstmt.setString(2, company.getEmail());
                userPstmt.setString(3, company.getPassword());
                userPstmt.setString(4, company.getRole());
                userPstmt.executeUpdate();

                ResultSet generatedKeys = userPstmt.getGeneratedKeys();
                if(generatedKeys.next())
                {
                    int companyId = generatedKeys.getInt(1);
                    companyPstmt.setInt(1, companyId);
                    companyPstmt.setString(2, company.getCnpj());
                    companyPstmt.setString(3, company.getCity());

                    companyPstmt.executeUpdate();
                }
                else
                {
                    throw new SQLException("Failed to retrieve generated user ID.");
                }

                conn.commit();

                generatedKeys.close();
                companyPstmt.close();
                userPstmt.close();
            }
            catch(SQLException e)
            {
                if(conn != null)
                {
                    try
                    {
                        conn.rollback();
                    }
                    catch(SQLException rollbackEx)
                    {
                        rollbackEx.printStackTrace();
                    }
                }

                e.printStackTrace();
            }

            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public RentalCompany getById(int id)
    {
        RentalCompany company = null;
        String sql = "SELECT rc.*, u.name, u.email, u.password " +
                     "FROM rental_company rc " +
                     "JOIN user u ON rc.company_id = u.user_id " +
                     "WHERE company_id = ?";
        
        try
        {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                String companyName = rs.getString("name");
                String companyEmail = rs.getString("email");
                String companyPassword = rs.getString("password");
                String companyCnpj = rs.getString("cnpj");
                String companyCity = rs.getString("city");

                company = new RentalCompany(id, companyName, companyEmail, companyPassword, companyCnpj, companyCity);
            }

            rs.close();
            ps.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return company;
    }

    public List<RentalCompany> getByCity(String city)
    {
        List<RentalCompany> companyList = new ArrayList<>();
        String sql = "SELECT rc.*, u.name, u.email, u.password " +
                     "FROM rental_company rc " +
                     "JOIN user u ON rc.company_id = u.user_id " +
                     "WHERE city = ?";
        
        try
        {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, city);

            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                int companyId = rs.getInt("company_id");
                String companyName = rs.getString("name");
                String companyEmail = rs.getString("email");
                String companyPassword = rs.getString("password");
                String companyCnpj = rs.getString("cnpj");
                String companyCity = rs.getString("city");

                RentalCompany company = new RentalCompany(companyId, companyName, companyEmail, companyPassword, companyCnpj, companyCity);

                companyList.add(company);
            }

            rs.close();
            ps.close();
            conn.close();
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

        String sql = "SELECT rc.*, u.name, u.email, u.password " +
                     "FROM rental_company rc " +
                     "JOIN user u ON rc.company_id = u.user_id";
        
        try
        {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                int companyId = rs.getInt("company_id");
                String companyName = rs.getString("name");
                String companyEmail = rs.getString("email");
                String companyPassword = rs.getString("password");
                String companyCnpj = rs.getString("cnpj");
                String companyCity = rs.getString("city");

                RentalCompany company = new RentalCompany(companyId, companyName, companyEmail, companyPassword, companyCnpj, companyCity);

                companyList.add(company);
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return companyList;
    }
    
    //CRUD - Update
    @Override
    public void update(RentalCompany company)
    {
        String userSQL = "UPDATE user SET name = ?, email = ?, password = ? WHERE user_id = ?";
        
        String companySQL = "UPDATE rental_company SET cnpj = ?, city = ? WHERE company_id = ?";
        
        try
        {
            Connection conn = this.getConnection();

            try
            {
                PreparedStatement userPstmt = conn.prepareStatement(userSQL);
                PreparedStatement companyPstmt = conn.prepareStatement(companySQL);
                conn.setAutoCommit(false);

                userPstmt.setString(1, company.getName());
                userPstmt.setString(2, company.getEmail());
                userPstmt.setString(3, company.getPassword());
                userPstmt.setInt(4, company.getId());
                userPstmt.executeUpdate(); 

                companyPstmt.setString(1, company.getCnpj());
                companyPstmt.setString(2, company.getCity());
                companyPstmt.setInt(3, company.getId());
                companyPstmt.executeUpdate();

                conn.commit();

                companyPstmt.close();
                userPstmt.close();
            }
            catch(SQLException e)
            {
                if(conn != null)
                {
                    try
                    {
                        conn.rollback();
                    }
                    catch(SQLException rollbackEx)
                    {
                        rollbackEx.printStackTrace();
                    }
                }

                e.printStackTrace();
            }

            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    //CRUD - Delete
    @Override
    public void delete(RentalCompany company)
    {
        String sql = "DELETE FROM user WHERE user_id = ?";

        try
        {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, company.getId());

            ps.executeUpdate();

            ps.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}