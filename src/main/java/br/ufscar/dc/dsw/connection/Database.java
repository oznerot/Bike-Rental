package br.ufscar.dc.dsw.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Database
{
    private static String url = "jdbc:mysql://localhost:3306/bikerent";
    private static String user = "root";
    private static String password = "admin123";

    private Database() {}

    public static Connection getConnection() throws SQLException
    {
        Connection connection = null;
        connection = DriverManager.getConnection(url, user, password);

        return connection;
    }

    public static void closeConnection(Connection connection)
    {
        try 
        {
            connection.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void closeStatement(Statement statement)
    {
        try
        {
            statement.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement)
    {
        try
        {
            preparedStatement.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void closeResultSet(ResultSet resultSet)
    {
        try
        {
            resultSet.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}