package org.admin.dao.impl;

import java.sql.*;
import org.admin.dao.DaoFactory;
import org.admin.dao.DaoException;
import org.admin.dao.EcueDao;

public class EcueDaoImpl implements EcueDao{
	private DaoFactory daoFactory;

	public EcueDaoImpl(DaoFactory daoFactory){
		this.daoFactory = daoFactory;
	}

	public void addEcue(String nom, int id_ue, float credit, String responsable, int id_parcours, String niveau) throws DaoException{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try{
			connexion = daoFactory.getConnection();
			String query = "INSERT INTO t_ecue(nom_ecue, id_ue, credit, responsable, id_parcours, niveau) VALUES(?,?,?,?,?,?)";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setString(1, nom);
			preparedStatement.setInt(2, id_ue);
			preparedStatement.setFloat(3, credit);
			preparedStatement.setString(4, responsable);
			preparedStatement.setInt(5, id_parcours);
			preparedStatement.setString(6, niveau);
			preparedStatement.executeQuery();

			connexion.close();
			preparedStatement.close();
		}catch(SQLException e){
			throw new DaoException("Erreur: " + e.getMessage());
		}
	}
}