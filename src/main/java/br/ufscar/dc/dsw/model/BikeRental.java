package br.ufscar.dc.dsw.model;

import java.time.LocalDateTime;

import java.util.UUID;

public class BikeRental
{
    private String rentalId;
    private String clientId;
    private String clientName;
    private String clientEmail;
    private String rentalCompanyId;
    private String rentalCompanyName;
    private String rentalCompanyEmail;

    private LocalDateTime rentDateHour;

    public BikeRental() {}

    public BikeRental(String clientId, String clientName, String clientEmail, String rentalCompanyId,
                      String rentalCompanyName, String rentalCompanyEmail, LocalDateTime rentDateHour)
    {
        this.rentalId = UUID.randomUUID().toString();       
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.rentalCompanyId = rentalCompanyId;
        this.rentalCompanyName = rentalCompanyName;
        this.rentalCompanyEmail = rentalCompanyEmail;
        this.rentDateHour = rentDateHour;
    }

    public BikeRental(String rentalId, String clientId, String clientName, String clientEmail, String rentalCompanyId,
                      String rentalCompanyName, String rentalCompanyEmail, LocalDateTime rentDateHour)
    {
        this.rentalId = rentalId;
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.rentalCompanyId = rentalCompanyId;
        this.rentalCompanyName = rentalCompanyName;
        this.rentalCompanyEmail = rentalCompanyEmail;
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

    public void setClientName(String clientName)
    {
        this.clientName = clientName;
    }

    public String getClientName()
    {
        return clientName;
    }

    public void setClientEmail(String clientEmail)
    {
        this.clientEmail = clientEmail;
    }

    public String getClientEmail()
    {
        return clientEmail;
    }

    public void setCompanyId(String rentalCompanyId)
    {
        this.rentalCompanyId = rentalCompanyId;
    }

    public String getCompanyId()
    {
        return rentalCompanyId;
    }

    public void setCompanyName(String rentalCompanyName)
    {
        this.rentalCompanyName = rentalCompanyName;
    }

    public String getCompanyName()
    {
        return rentalCompanyName;
    }

    public void setCompanyEmail(String rentalCompanyEmail)
    {
        this.rentalCompanyEmail = rentalCompanyEmail;
    }

    public String getCompanyEmail()
    {
        return rentalCompanyEmail;
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