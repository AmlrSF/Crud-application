package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Groupe;
import utilitaires.Utilitaire;

public class DAOGroupe implements IDAO<Groupe> {

	private Connection connection;

	public DAOGroupe() {
		// Initialize connection here
		Utilitaire.seConnecter("connectionPar.properties");

		connection = Utilitaire.getConnection();
	}

	@Override
	public Groupe findByID(Object id) {
		Groupe groupe = null;
		try {
			String query = "SELECT * FROM groupe WHERE idgrp=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, (String) id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				groupe = new Groupe(
						resultSet.getString("idgrp"),
						resultSet.getString("libGrp"),
						resultSet.getInt("niveau"),
						resultSet.getString("specialite")
				);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groupe;
	}

	@Override
	public ArrayList<Groupe> findAll() {
		ArrayList<Groupe> groupes = new ArrayList<>();
		try {
			String query = "SELECT * FROM groupe";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Groupe groupe = new Groupe(
						resultSet.getString("idgrp"),
						resultSet.getString("libGrp"),
						resultSet.getInt("niveau"),
						resultSet.getString("specialite")
				);
				groupes.add(groupe);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groupes;
	}

	@Override
	public boolean update(Groupe o) {
		try {
			String query = "UPDATE groupe SET libGrp=?, niveau=?, specialite=? WHERE idgrp=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, o.getLibGroupe());
			statement.setInt(2, o.getNiveau());
			statement.setString(3, o.getSpecialite());
			statement.setString(4, o.getIdgrp());
			int rowsUpdated = statement.executeUpdate();
			statement.close();
			return rowsUpdated > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Groupe o) {
		try {
			String query = "DELETE FROM groupe WHERE idgrp=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, o.getIdgrp());
			int rowsDeleted = statement.executeUpdate();
			statement.close();
			return rowsDeleted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public boolean insert(Groupe o) {
		try {
			String query = "INSERT INTO groupe (idgrp, libGrp, niveau, specialite) VALUES (?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, o.getIdgrp());
			statement.setString(2, o.getLibGroupe());
			statement.setInt(3, o.getNiveau());
			statement.setString(4, o.getSpecialite());
			int rowsInserted = statement.executeUpdate();
			statement.close();
			return rowsInserted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
