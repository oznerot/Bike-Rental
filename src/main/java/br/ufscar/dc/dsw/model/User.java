package br.ufscar.dc.dsw.model;

import java.util.UUID;

public class User
{
    private String uuid;
    private String email;
    private String password;
    private String name;

    public User() {}

    //Used when signing up a new user
    public User (String name, String email, String password)
    {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //Used when retrieving user data from database
    public User (String uuid, String name, String email, String password)
    {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getUUID()
    {
        return uuid;
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
}