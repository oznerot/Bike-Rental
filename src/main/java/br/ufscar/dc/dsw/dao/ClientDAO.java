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

import br.ufscar.dc.dsw.model.Client;

public class ClientDAO extends GenericDAO<Client>
{
    //CRUD - Create
    @Override
    public void insert(Client client)
    {
        String userSQL = "INSERT INTO user (name, email, password, user_role) VALUES (?, ?, ?, ?)";

        String clientSQL = "INSERT INTO client (client_id, cpf, phone, gender, birth) VALUES (?, ?, ?, ?, ?)";

        try
        {
            Connection conn = this.getConnection();

            try
            {
                PreparedStatement userPstmt = conn.prepareStatement(userSQL, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement clientPstmt = conn.prepareStatement(clientSQL);
                conn.setAutoCommit(false);

                userPstmt.setString(1, client.getName());
                userPstmt.setString(2, client.getEmail());
                userPstmt.setString(3, client.getPassword());
                userPstmt.setString(4, client.getRole());
                userPstmt.executeUpdate();

                ResultSet generatedKeys = userPstmt.getGeneratedKeys();
                if(generatedKeys.next())
                {
                    int clientId = generatedKeys.getInt(1);

                    clientPstmt.setInt(1, clientId);
                    clientPstmt.setString(2, client.getCpf());
                    clientPstmt.setString(3, client.getPhone());
                    clientPstmt.setString(4, client.getGender());
                    clientPstmt.setDate(5, Date.valueOf(client.getDateOfBirth()));

                    clientPstmt.executeUpdate();
                }
                else
                {
                    throw new SQLException("Failed to retrieve generated user ID.");
                }

                conn.commit();

                generatedKeys.close();
                clientPstmt.close();
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

    //CRUD - Read
    @Override
    public Client getById(int id)
    {
        Client client = null;
        String sql = "SELECT c.*, u.name, u.email, u.password FROM client c JOIN user u ON c.client_id = u.user_id WHERE client_id = ?";

        try
        {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                String clientName = rs.getString("name");
                String clientEmail = rs.getString("email");
                String clientPassword = rs.getString("password");
                String clientCpf = rs.getString("cpf");
                String clientPhone = rs.getString("phone");
                String clientGender = rs.getString("gender");
                LocalDate clientDateOfBirth = rs.getDate("birth").toLocalDate();

                client = new Client(id, clientName, clientEmail, clientPassword, clientCpf, clientPhone, clientGender, clientDateOfBirth);
            }

            rs.close();
            ps.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return client;
    }
    
    //CRUD - Read All
    @Override
    public List<Client> getAll()
    {
        List<Client> clientList = new ArrayList<>();
        
        String sql = "SELECT c.*, u.name, u.email, u.password FROM client c JOIN user u ON c.client_id = u.user_id";

        try
        {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                int clientId = rs.getInt("client_id");
                String clientName = rs.getString("name");
                String clientEmail = rs.getString("email");
                String clientPassword = rs.getString("password");
                String clientCpf = rs.getString("cpf");
                String clientPhone = rs.getString("phone");
                String clientGender = rs.getString("gender");
                LocalDate clientDateOfBirth = rs.getDate("birth").toLocalDate();
                
                Client client = new Client(clientId, clientName, clientEmail, clientPassword, clientCpf, clientPhone, clientGender, clientDateOfBirth);
                clientList.add(client);
            }

            rs.close();
            stmt.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return clientList;
    }
    
    @Override
    public void update(Client client)
    {
        String userSQL = "UPDATE user SET name = ?, email = ?, password = ? WHERE user_id = ?";

        String clientSQL = "UPDATE client SET cpf = ?, phone = ?, gender = ?, birth = ? WHERE client_id = ?";

        try
        {
            Connection conn = this.getConnection();

            try
            {
                PreparedStatement userPstmt = conn.prepareStatement(userSQL);
                PreparedStatement clientPstmt = conn.prepareStatement(clientSQL);
                conn.setAutoCommit(false);

                userPstmt.setString(1, client.getName());
                userPstmt.setString(2, client.getEmail());
                userPstmt.setString(3, client.getPassword());
                userPstmt.setInt(4, client.getId());
                userPstmt.executeUpdate();

                clientPstmt.setString(1, client.getCpf());
                clientPstmt.setString(2, client.getPhone());
                clientPstmt.setString(3, client.getGender());
                clientPstmt.setDate(4, Date.valueOf(client.getDateOfBirth()));
                clientPstmt.setInt(5, client.getId());
                clientPstmt.executeUpdate();

                conn.commit();

                clientPstmt.close();
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
    public void delete(Client client)
    {
        String sql = "DELETE FROM user WHERE user_id = ?";

        try
        {
            Connection conn = this.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, client.getId());

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