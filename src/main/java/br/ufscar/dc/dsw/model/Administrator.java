package br.ufscar.dc.dsw.model;

public class Administrator extends User
{
    public Administrator() {}

    public Administrator(int id, String name, String email, String password)
    {
        super(id, name, email, password);
    }
}