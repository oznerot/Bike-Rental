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

import br.ufscar.dc.dsw.dao.UserDAO;
import br.ufscar.dc.dsw.model.User;
import br.ufscar.dc.dsw.Database;

public class UserDAOImpl implements UserDAO
{

    //CRUD - Read
    @Override
    public User get(String uuid)
    {
        User user = null;
        String sql = "SELECT user_id, name, email, password, user_role FROM user WHERE user_id = ?";

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
                    String userUUID = rs.getString("user_id");
                    String userName = rs.getString("name");
                    String userEmail = rs.getString("email");
                    String userPassword = rs.getString("password");
                    int userRole = rs.getInt("user_role");

                    user = new User(userUUID, userName, userEmail, userPassword, userRole);
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return user;
    }
    
    @Override
    public List<User> getAll()
    {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT user_id, name, email, password, user_role FROM user";

        try(Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql))
        {
            while(rs.next())
            {
                String userUUID = rs.getString("user_id");
                String userName = rs.getString("name");
                String userEmail = rs.getString("email");
                String userPassword = rs.getString("password");
                int userRole = rs.getInt("user_role");
                
                User user = new User(userUUID, userName, userEmail, userPassword, userRole);

                userList.add(user);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public int insert(User user)
    {
        int result = 0;
        String sql = "INSERT INTO user (user_id, name, email, password, user_role) VALUES (?, ?, ?, ?, ?)";

        try(Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, user.getUUID());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRole());

            result = ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }
    
    @Override
    public int update(User user)
    {
        int result = 0;
        String sql = "UPDATE user SET name = ?, email = ?, password = ?, user_role = ? WHERE user_id = ?";

        try(Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getRole());
            ps.setString(5, user.getUUID());

            result = ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int delete(User user)
    {
        int result = 0;
        String sql = "DELETE FROM user WHERE user_id = ?";

        try(Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, user.getUUID());

            result = ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }
}