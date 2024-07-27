package br.ufscar.dc.dsw.model;

import java.time.LocalDateTime;

public class Rental
{
    private String clientId;
    private String rentalCompanyId;
    private LocalDateTime rentDateHour;

    public Rental() {}

    public Rental(String clientId, String rentalCompanyId, LocalDateTime rentDateHour)
    {
        this.clientId = clientId;
        this.rentalCompanyId = rentalCompanyId;
        this.rentDateHour = rentDateHour;
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

    public void setRentDateHour(LocalDateTime rentDateHour)
    {
        this.rentDateHour = rentDateHour;
    }

    public LocalDateTime getRentDateHour()
    {
        return rentDateHour;
    }
}