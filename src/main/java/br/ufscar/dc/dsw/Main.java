package br.ufscar.dc.dsw;

import java.sql.Connection;
import java.sql.SQLException;

import java.time.LocalDate;

import java.util.List;

import br.ufscar.dc.dsw.Database;
import br.ufscar.dc.dsw.dao.ClientDAO;
import br.ufscar.dc.dsw.dao.ClientDAOImpl;
import br.ufscar.dc.dsw.dao.RentalCompanyDAO;
import br.ufscar.dc.dsw.dao.RentalCompanyDAOImpl;
import br.ufscar.dc.dsw.dao.UserDAO;
import br.ufscar.dc.dsw.dao.UserDAOImpl;
import br.ufscar.dc.dsw.model.Client;
import br.ufscar.dc.dsw.model.RentalCompany;
import br.ufscar.dc.dsw.model.User;

public class Main
{
    public static void main(String[] args) throws SQLException
    { 
        /*
        Connection conn = Database.getConnection();

        if(conn != null)
        {
            System.out.println("Database connection successful!");
        }


        
        ClientDAO clientDAO = new ClientDAOImpl();

        List<Client> clientList = clientDAO.getAll();

        for(int i = 0; i < clientList.size(); i++)
        {
            System.out.println(clientList.get(i).getId());
        }
        


        ClientDAO clientDAO = new ClientDAOImpl();
        
        Client client = new Client(0, "Vitor Costa", "user6@example.com", "aaabbb", "12312312300", "xxxxxxxxxxx", "Male", LocalDate.of(2001, 4, 12));

        int result = clientDAO.insert(client);

        System.out.println(result);
        */
        
        /*
        ClientDAO clientDAO = new ClientDAOImpl();

        Client client = new Client(6, "Vitor Bosta", "uzer6@example.com", "aaacbb", "12312312300", "xxxxxxxxxxx", "Male", LocalDate.of(2001, 4, 13));

        int result = clientDAO.update(client);

        System.out.println(result);
        

       ClientDAO clientDAO = new ClientDAOImpl();
       Client client = clientDAO.get(6);

       int result = clientDAO.delete(client);

       System.out.println(result);
       */
    
        UserDAO userDAO = new UserDAOImpl();

        User user = new User("adfdbc58-63a6-4e83-b0ff-eab698e2c566", "Vitor Bosta", "user6@example.com", "aaabbb", 1);
        
        int result = userDAO.update(user);
        
        System.out.println(user.getName());

        //int result = clientDAO.delete(client);

        //System.out.println(clientDAO.get("1aa605ff-7202-4e2a-86c4-7b036af25ef7").getName());

    }   
}