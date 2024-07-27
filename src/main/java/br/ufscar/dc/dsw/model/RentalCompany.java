package br.ufscar.dc.dsw.model;

import br.ufscar.dc.dsw.model.User;

public class RentalCompany extends User
{
    private String cnpj;
    private String city;

    public RentalCompany() {}

    public RentalCompany(String name, String email, String password, String cnpj, String city)
    {
        super(name, email, password, 2);

        this.cnpj = cnpj;
        this.city = city;
    }

    public RentalCompany(String uuid, String name, String email, String password, String cnpj, String city)
    {
        super(uuid, name, email, password, 2);

        this.cnpj = cnpj;
        this.city = city;
    }

    public void setCnpj(String cnpj)
    {
        this.cnpj = cnpj;
    }
    
    public String getCnpj()
    {
        return cnpj;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCity()
    {
        return city;
    }
}