package br.ufscar.dc.dsw.model;

public class User
{
    private int id;
    private String email;
    private String password;
    private String name;
    private String role;

    public User() {}

    //Used when signing up a new user
    public User (String name, String email, String password, String role)
    {
        this.id = -1;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    //Used when retrieving user data from database
    public User (int id, String name, String email, String password, String role)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId()
    {
        return id;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

    public void setName(String name)
    {
        this.name = name;
    } 

    public String getName()
    {
        return name;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getRole()
    {
        return role;
    }
}