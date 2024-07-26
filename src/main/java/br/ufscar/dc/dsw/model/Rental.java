package br.ufscar.dc.dsw.model;

import java.time.LocalDateTime;

public class Rental
{
    private String clientCpf;
    private String rentalCompanyCnpj;
    private LocalDateTime rentDateHour;

    public Rental() {}

    public Rental(String clientCpf, String rentalCompanyCnpj, LocalDateTime rentDateHour)
    {
        this.clientCpf = clientCpf;
        this.rentalCompanyCnpj = rentalCompanyCnpj;
        this.rentDateHour = rentDateHour;
    }

    public void setClientCpf(String clientCpf)
    {
        this.clientCpf = clientCpf;
    }

    public String getClientCpf()
    {
        return clientCpf;
    }

    public void setRentalCompanyCnpj(String rentalCompanyCnpj)
    {
        this.rentalCompanyCnpj = rentalCompanyCnpj;
    }

    public String getRentalCompanyCnpj()
    {
        return rentalCompanyCnpj;
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