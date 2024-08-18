package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.model.User;
import br.ufscar.dc.dsw.dao.GenericDAO;

public interface UserDAO extends GenericDAO<User>
{
    User getByEmail(String email);

}