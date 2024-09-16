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

import br.ufscar.dc.dsw.model.User;

public class UserDAO extends GenericDAO<User>
{
    //CRUD - Create
    @Override
    public void insert(User user)
    {
        String sql = "INSERT INTO user (user_id, name, email, password, user_role) VALUES (?, ?, ?, ?, ?)";

        try
        {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole());

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
    public User getById(int id)
    {
        User user = null;
        String sql = "SELECT * FROM user WHERE user_id = ?";

        try
        {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                String userName = rs.getString("name");
                String userEmail = rs.getString("email");
                String userPassword = rs.getString("password");
                String userRole = rs.getString("user_role");

                user = new User(id, userName, userEmail, userPassword, userRole);
            }
            
            rs.close();
            ps.close();
            conn.close();
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
        String sql = "SELECT * FROM user";

        try
        {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                int userId = rs.getInt("user_id");
                String userName = rs.getString("name");
                String userEmail = rs.getString("email");
                String userPassword = rs.getString("password");
                String userRole = rs.getString("user_role");
                
                User user = new User(userId, userName, userEmail, userPassword, userRole);

                userList.add(user);
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return userList;
    }

    public User getByEmail(String email)
    {
        User user = null;
        String sql = "SELECT * FROM user WHERE email = ?";

        try
        {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                int userId = rs.getInt("user_id");
                String userName = rs.getString("name");
                String userPassword = rs.getString("password");
                String userRole = rs.getString("user_role");

                user = new User(userId, userName, email, userPassword, userRole);
            }
            rs.close();
            ps.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return user;
    }
    
    //CRUD - Update
    @Override
    public void update(User user)
    {
        int result = 0;
        String sql = "UPDATE user SET name = ?, email = ?, password = ?, user_role = ? WHERE user_id = ?";

        try
        {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.setInt(5, user.getId());

            ps.executeUpdate();

            ps.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    //CRUD - Delete
    @Override
    public void delete(User user)
    {
        String sql = "DELETE FROM user WHERE user_id = ?";

        try
        {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, user.getId());

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