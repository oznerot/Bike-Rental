package br.ufscar.dc.dsw.dao;

import java.util.List;

import br.ufscar.dc.dsw.model.RentalCompany;
import br.ufscar.dc.dsw.dao.GenericDAO;

public interface RentalCompanyDAO extends GenericDAO<RentalCompany>
{
    List<RentalCompany> getByCity(String city);
}