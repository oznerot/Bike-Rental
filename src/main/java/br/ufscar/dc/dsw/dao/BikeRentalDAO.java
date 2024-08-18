package br.ufscar.dc.dsw.dao;

import java.util.List;

import br.ufscar.dc.dsw.model.BikeRental;
import br.ufscar.dc.dsw.dao.GenericDAO;

public interface BikeRentalDAO extends GenericDAO<BikeRental>
{
    List<BikeRental> getClientRentalList(String uuid);

    List<BikeRental> getCompanyRentalList(String uuid);
}