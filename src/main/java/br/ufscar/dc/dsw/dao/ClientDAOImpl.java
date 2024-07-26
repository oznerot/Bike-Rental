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
    public Client get(int id)
    {
        Client client = null;
        String sql = "SELECT id, name, email, password, cpf, phone, gender, birth FROM client WHERE id = ?";

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
                    int clientId = rs.getInt("id");
                    String clientName = rs.getString("name");
                    String clientEmail = rs.getString("email");
                    String clientPassword = rs.getString("password");
                    String clientCpf = rs.getString("cpf");
                    String clientPhone = rs.getString("phone");
                    String clientGender = rs.getString("gender");
                    LocalDate clientDateOfBirth = rs.getDate("birth").toLocalDate();

                    client = new Client(clientId, clientName, clientEmail, clientPassword, clientCpf, clientPhone, clientGender, clientDateOfBirth);

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
        String sql = "SELECT id, name, email, password, cpf, phone, gender, birth FROM client";

        try(Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql))
        {
            while(rs.next())
            {
                int clientId = rs.getInt("id");
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
        String sql = "INSERT INTO client (name, email, password, cpf, phone, gender, birth) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, client.getName());
            ps.setString(2, client.getEmail());
            ps.setString(3, client.getPassword());
            ps.setString(4, client.getCpf());
            ps.setString(5, client.getPhone());
            ps.setString(6, client.getGender());
            ps.setDate(7, Date.valueOf(client.getDateOfBirth()));

            result = ps.executeUpdate();
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
        String sql = "UPDATE client SET name = ?, email = ?, password = ?, cpf = ?, phone = ?, gender = ?, birth = ? WHERE id = ?";

        try(Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setString(1, client.getName());
            ps.setString(2, client.getEmail());
            ps.setString(3, client.getPassword());
            ps.setString(4, client.getCpf());
            ps.setString(5, client.getPhone());
            ps.setString(6, client.getGender());
            ps.setDate(7, Date.valueOf(client.getDateOfBirth()));
            ps.setInt(8, client.getId());

            result = ps.executeUpdate();
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
        String sql = "DELETE FROM client WHERE id = ?";

        try(Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, client.getId());

            result = ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }
}