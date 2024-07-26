package br.ufscar.dc.dsw.model;

import java.time.LocalDate;

public class Client extends User
{
    private String cpf;
    private String phone;
    private String gender;
    private LocalDate dateOfBirth;

    public Client() {}

    public Client (int id, String name, String email, String password, String cpf, String phone, String gender, LocalDate dateOfBirth)
    {
        super(id, name, email, password);

        this.cpf = cpf;
        this.phone = phone;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setGender(String gender) {
        this.gender = gender;
    } 

    public String getGender() {
        return gender;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}