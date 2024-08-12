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
import br.ufscar.dc.dsw.connection.Database;

public class RentalCompanyDAOImpl implements RentalCompanyDAO
{
    @Override
    public RentalCompany get(String uuid)
    {
        RentalCompany company = null;
        String sql = "SELECT rc.*, u.name, u.email, u.password " +
                     "FROM rental_company rc " +
                     "JOIN user u ON rc.company_id = u.user_id " +
                     "WHERE company_id = ?";
        
        try(Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, uuid);

            try(ResultSet rs = ps.executeQuery())
            {
                if(rs.next())
                {
                    String companyUUID = rs.getString("company_id");
                    String companyName = rs.getString("name");
                    String companyEmail = rs.getString("email");
                    String companyPassword = rs.getString("password");
                    String companyCnpj = rs.getString("cnpj");
                    String companyCity = rs.getString("city");

                    company = new RentalCompany(companyUUID, companyName, companyEmail, companyPassword, companyCnpj, companyCity);
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
        String sql = "SELECT rc.*, u.name u.email, u.password " +
                     "FROM rental_company rc " +
                     "JOIN user u ON rc.company_id = u.user_id" +
                     "WHERE city = ?";
        
        try(Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, city);

            try(ResultSet rs = ps.executeQuery())
            {
                while(rs.next())
                {
                    String companyUUID = rs.getString("company_id");
                    String companyName = rs.getString("name");
                    String companyEmail = rs.getString("email");
                    String companyPassword = rs.getString("password");
                    String companyCnpj = rs.getString("cnpj");
                    String companyCity = rs.getString("city");

                    RentalCompany company = new RentalCompany(companyUUID, companyName, companyEmail, companyPassword, companyCnpj, companyCity);

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
        String sql = "SELECT rc.*, u.name u.email, u.password " +
                     "FROM rental_company rc " +
                     "JOIN user u ON rc.company_id = u.user_id";
        
        try(Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql))
        {
            while(rs.next())
            {
                String companyUUID = rs.getString("company_id");
                String companyName = rs.getString("name");
                String companyEmail = rs.getString("email");
                String companyPassword = rs.getString("password");
                String companyCnpj = rs.getString("cnpj");
                String companyCity = rs.getString("city");

                RentalCompany company = new RentalCompany(companyUUID, companyName, companyEmail, companyPassword, companyCnpj, companyCity);

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
        String userSQL = "INSERT INTO user (user_id, name, email, password, user_role) VALUES " +
                         "(?, ?, ?, ?, (SELECT role_id FROM roles WHERE role_name = 'rental_company'))";
        
        String companySQL = "INSERT INTO rental_company (company_id, cnpj, city) VALUES " +
                            "(?, ?, ?)";
        
        try(Connection conn = Database.getConnection())
        {
            conn.setAutoCommit(false);

            try(PreparedStatement userPstmt = conn.prepareStatement(userSQL);
                PreparedStatement companyPstmt = conn.prepareStatement(companySQL))
            {
                userPstmt.setString(1, company.getUUID());
                userPstmt.setString(2, company.getName());
                userPstmt.setString(3, company.getEmail());
                userPstmt.setString(4, company.getPassword());
                result = userPstmt.executeUpdate();

                companyPstmt.setString(1, company.getUUID());
                companyPstmt.setString(2, company.getCnpj());
                companyPstmt.setString(3, company.getCity());
                result = companyPstmt.executeUpdate();

                conn.commit();
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
        String userSQL = "UPDATE user SET name = ?, email = ?, password = ? " +
                         "WHERE user_id = ?";
        
        String companySQL = "UPDATE rental_company SET cnpj = ?, city = ? " +
                            "WHERE company_id = ?";
        
        try(Connection conn = Database.getConnection())
        {
            conn.setAutoCommit(false);

            try(PreparedStatement userPstmt = conn.prepareStatement(userSQL);
                PreparedStatement companyPstmt = conn.prepareStatement(companySQL))
            {
                userPstmt.setString(1, company.getName());
                userPstmt.setString(2, company.getEmail());
                userPstmt.setString(3, company.getPassword());
                userPstmt.setString(4, company.getUUID());
                result = userPstmt.executeUpdate(); 

                companyPstmt.setString(1, company.getCnpj());
                companyPstmt.setString(2, company.getCity());
                companyPstmt.setString(3, company.getUUID());
                result = companyPstmt.executeUpdate();

                conn.commit();
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
        String sql = "DELETE FROM user WHERE user_id = ?";

        try(Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, company.getUUID());

            result = ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }
}