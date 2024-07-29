package br.ufscar.dc.dsw.model;

import java.time.LocalDateTime;

import java.util.UUID;

public class BikeRental
{
    private String rentalId;
    private String clientId;
    private String rentalCompanyId;
    private LocalDateTime rentDateHour;

    public BikeRental() {}

    public BikeRental(String clientId, String rentalCompanyId, LocalDateTime rentDateHour)
    {
        this.rentalId = UUID.randomUUID().toString();       
        this.clientId = clientId;
        this.rentalCompanyId = rentalCompanyId;
        this.rentDateHour = rentDateHour;
    }

    public BikeRental(String rentalId, String clientId, String rentalCompanyId, LocalDateTime rentDateHour)
    {
        this.rentalId = rentalId;
        this.clientId = clientId;
        this.rentalCompanyId = rentalCompanyId;
        this.rentDateHour = rentDateHour;
    }

    public String getUUID()
    {
        return rentalId;
    }
    public void setClientId(String clientId)
    {
        this.clientId = clientId;
    }

    public String getClientId()
    {
        return clientId;
    }

    public void setRentalCompanyId(String rentalCompanyId)
    {
        this.rentalCompanyId = rentalCompanyId;
    }

    public String getRentalCompanyId()
    {
        return rentalCompanyId;
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