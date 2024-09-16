package br.ufscar.dc.dsw.model;

import java.time.LocalDateTime;

public class BikeRental
{
    private int id;
    private Client client;
    private RentalCompany company;

    private LocalDateTime rentDateHour;

    public BikeRental() {}

    public BikeRental(Client client, RentalCompany company, LocalDateTime rentDateHour)
    {
        this.id = -1;       
        this.client = client;
        this.company = company;
        this.rentDateHour = rentDateHour;
    }

    public BikeRental(int id, Client client, RentalCompany company, LocalDateTime rentDateHour)
    {
        this.id = id;
        this.client = client;
        this.company = company;
        this.rentDateHour = rentDateHour;
    }

    public int getId()
    {
        return id;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public Client getClient()
    {
        return client;
    }

    public void setCompany(RentalCompany company)
    {
        this.company = company;
    }

    public RentalCompany getCompany()
    {
        return company;
    }

    public void setDateHour(LocalDateTime rentDateHour)
    {
        this.rentDateHour = rentDateHour;
    }

    public LocalDateTime getDateHour()
    {
        return rentDateHour;
    }
}