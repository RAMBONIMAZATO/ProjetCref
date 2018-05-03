package org.admin.dao.impl;

import java.sql.*;
import org.admin.dao.DaoFactory;
import org.admin.dao.DaoException;
import org.admin.dao.UeDao;
import java.util.List;
import java.util.ArrayList;
import org.admin.beans.scolarite.Ue; 

public class UeDaoImpl implements UeDao{
	private DaoFactory daoFactory;

	public UeDaoImpl(DaoFactory daoFactory){
		this.daoFactory = daoFactory;
	}

	public void addUe(String nom, String niveau, int parcours, String responsable, float credit, int id_mention, String semestre) throws DaoException{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try{
			connexion = daoFactory.getConnection();
			String query = "INSERT INTO t_ue(nom_ue, id_parcours, niveau, responsable, credit, id_mention, semestre) VALUES(?,?,?,?,?,?,?)";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setString(1, nom);
			preparedStatement.setInt(2, parcours);
			preparedStatement.setString(3, niveau);
			preparedStatement.setString(4, responsable);
			preparedStatement.setFloat(5, credit);
			preparedStatement.setInt(6, id_mention);
			preparedStatement.setString(7, semestre);
			preparedStatement.executeQuery();

			connexion.close();
			preparedStatement.close();
		} catch(SQLException e){
			throw new DaoException("Erreur SQL le izy : " + e.getMessage());
		}
	}

	public int getIdUe(String nom, String niveau) throws DaoException{
		int id;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try{
			connexion = daoFactory.getConnection();
			String query = "SELECT * FROM \"Vue_ue\" WHERE \"nom_ue\" = ? AND \"niveau\" = ? ";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setString(1, nom);
			preparedStatement.setString(2, niveau);
			resultat = preparedStatement.executeQuery();
			resultat.next();
			id = resultat.getInt("id_ue");

			connexion.close();
			preparedStatement.close();
			resultat.close();
		}catch(SQLException e){
			throw new DaoException("Erreur SQL UE izy: " + e.getMessage());
		}
		System.out.println("Azo ilay id : " + id);
		return id;
	}

	public List<Ue> getAllUeByParcours(int id_parcours) throws DaoException{
		List<Ue> lesUe = new ArrayList<Ue>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		System.out.println("tafiditra ato @ getAllUeByParcours");
		try{
			connexion = daoFactory.getConnection();
			String query = "SELECT * FROM \"Vue_ue\" WHERE \"id_parcours\" = ?";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, id_parcours);
			resultat = preparedStatement.executeQuery();

			while(resultat.next()){
				String nom = resultat.getString("nom_ue");
				String resp = resultat.getString("responsable");
				int id = resultat.getInt("id_ue");
				float credit = resultat.getFloat("credit");
				String semestre = resultat.getString("semestre");
				Ue ue = new Ue(nom, resp, id, credit, semestre);
				ue.setId_parcours(id_parcours);
				// System.out.println(ue.toString());
				lesUe.add(ue);
			}

			connexion.close();
			preparedStatement.close();
			resultat.close();

		}catch(SQLException e){
			throw new DaoException("Erreur SQL misay: " + e.getMessage());
		}

		return lesUe;
	}

	public List<Ue> getAllUeByMention(int id_mention) throws DaoException{
		List<Ue> listeUe = new ArrayList<Ue>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try{
			connexion = daoFactory.getConnection();
			String query = "SELECT * FROM \"Vue_ue\" WHERE \"id_mention\" = ?";
			preparedStatement = connexion.prepareStatement(query);
			preparedStatement.setInt(1, id_mention);
			resultat = preparedStatement.executeQuery();

			while(resultat.next()){
				String nom = resultat.getString("nom_ue");
				String resp = resultat.getString("responsable");
				int id = resultat.getInt("id_ue");
				float credit = resultat.getFloat("credit");
				int id_parcours = resultat.getInt("id_parcours");
				String semestre = resultat.getString("semestre");
				Ue ue = new Ue(nom, resp, id, credit, semestre);
				ue.setId_parcours(id_parcours);
				// System.out.println(ue.toString());
				listeUe.add(ue);
			}

			connexion.close();
			preparedStatement.close();
			resultat.close();
		}catch(SQLException e){
			throw new DaoException("Erreur SQL misay: " + e.getMessage());
		}

		return listeUe;
	}
}