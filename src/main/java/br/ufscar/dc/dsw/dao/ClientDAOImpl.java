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

import br.ufscar.dc.dsw.dao.ClientDAO;
import br.ufscar.dc.dsw.model.Client;
import br.ufscar.dc.dsw.Database;

public class ClientDAOImpl implements ClientDAO
{

    //CRUD - Read
    @Override
    public Client get(String uuid)
    {
        Client client = null;
        String sql = "SELECT c.*, u.name, u.email, u.password FROM client c JOIN user u ON c.client_id = u.user_id WHERE client_id = ?";

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
                    String clientUUID = rs.getString("client_id");
                    String clientName = rs.getString("name");
                    String clientEmail = rs.getString("email");
                    String clientPassword = rs.getString("password");
                    String clientCpf = rs.getString("cpf");
                    String clientPhone = rs.getString("phone");
                    String clientGender = rs.getString("gender");
                    LocalDate clientDateOfBirth = rs.getDate("birth").toLocalDate();

                    client = new Client(clientUUID, clientName, clientEmail, clientPassword, clientCpf, clientPhone, clientGender, clientDateOfBirth);
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return client;
    }
    
    @Override
    public List<Client> getAll()
    {
        List<Client> clientList = new ArrayList<>();
        String sql = "SELECT c.*, u.name, u.email, u.password FROM client c JOIN user u ON c.client_id = u.user_id";

        try(Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql))
        {
            while(rs.next())
            {
                String clientUUID = rs.getString("client_id");
                String clientName = rs.getString("name");
                String clientEmail = rs.getString("email");
                String clientPassword = rs.getString("password");
                String clientCpf = rs.getString("cpf");
                String clientPhone = rs.getString("phone");
                String clientGender = rs.getString("gender");
                LocalDate clientDateOfBirth = rs.getDate("birth").toLocalDate();
                
                Client client = new Client(clientUUID, clientName, clientEmail, clientPassword, clientCpf, clientPhone, clientGender, clientDateOfBirth);

                clientList.add(client);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return clientList;
    }

    @Override
    public int insert(Client client)
    {
        int result = 0;
        String userSQL = "INSERT INTO user (user_id, name, email, password, user_role) VALUES (?, ?, ?, ?, " +
                            "(SELECT role_id FROM roles WHERE role_name = 'client'))";

        String clientSQL = "INSERT INTO client (client_id, cpf, phone, gender, birth) VALUES (?, ?, ?, ?, ?)";

        try(Connection conn = Database.getConnection())
        {
            try(PreparedStatement userPstmt = conn.prepareStatement(userSQL);
                PreparedStatement clientPstmt = conn.prepareStatement(clientSQL))
            {
                conn.setAutoCommit(false);

                userPstmt.setString(1, client.getUUID());
                userPstmt.setString(2, client.getName());
                userPstmt.setString(3, client.getEmail());
                userPstmt.setString(4, client.getPassword());
                result = userPstmt.executeUpdate();

                clientPstmt.setString(1, client.getUUID());
                clientPstmt.setString(2, client.getCpf());
                clientPstmt.setString(3, client.getPhone());
                clientPstmt.setString(4, client.getGender());
                clientPstmt.setDate(5, Date.valueOf(client.getDateOfBirth()));

                result = clientPstmt.executeUpdate();

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
    public int update(Client client)
    {
        int result = 0;
        String userSQL = "UPDATE user SET name = ?, email = ?, password = ? WHERE user_id = ?";

        String clientSQL = "UPDATE client SET cpf = ?, phone = ?, gender = ?, birth = ? WHERE client_id = ?";

        try(Connection conn = Database.getConnection())
        {
            conn.setAutoCommit(false);

            try(PreparedStatement userPstmt = conn.prepareStatement(userSQL);
                PreparedStatement clientPstmt = conn.prepareStatement(clientSQL))
            {
                userPstmt.setString(1, client.getName());
                userPstmt.setString(2, client.getEmail());
                userPstmt.setString(3, client.getPassword());
                userPstmt.setString(4, client.getUUID());
                result = userPstmt.executeUpdate();

                clientPstmt.setString(1, client.getCpf());
                clientPstmt.setString(2, client.getPhone());
                clientPstmt.setString(3, client.getGender());
                clientPstmt.setDate(4, Date.valueOf(client.getDateOfBirth()));
                clientPstmt.setString(5, client.getUUID());
                result = clientPstmt.executeUpdate();

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
    public int delete(Client client)
    {
        int result = 0;
        String sql = "DELETE FROM user WHERE user_id = ?";

        try(Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, client.getUUID());

            result = ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }
}