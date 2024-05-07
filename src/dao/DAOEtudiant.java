package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Etudiant;
import utilitaires.Utilitaire;

public class DAOEtudiant implements IDAO<Etudiant> {

	private Connection connection;

	public DAOEtudiant() {
		// Initialize connection here
		Utilitaire.seConnecter("connectionPar.properties");

		connection = Utilitaire.getConnection();
	}

	@Override
	public Etudiant findByID(Object id) {
		Etudiant etudiant = null;
		try {
			String query = "SELECT * FROM etudiant WHERE matricule=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, (String) id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				etudiant = new Etudiant(
						resultSet.getString("matricule"),
						resultSet.getString("nom"),
						resultSet.getString("prenom"),
						resultSet.getString("idgrp")
				);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return etudiant;
	}

	@Override
	public ArrayList<Etudiant> findAll() {
		ArrayList<Etudiant> students = new ArrayList<>();
		try {
			String query = "SELECT * FROM etudiant";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Etudiant student = new Etudiant(
						resultSet.getString("matricule"),
						resultSet.getString("nom"),
						resultSet.getString("prenom"),
						resultSet.getString("idgrp")
				);
				students.add(student);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	public ArrayList<Etudiant> findByGroupe(String g) {
		ArrayList<Etudiant> students = new ArrayList<>();
		try {
			String query = "SELECT * FROM etudiant WHERE idgrp=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, g);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Etudiant student = new Etudiant(
						resultSet.getString("matricule"),
						resultSet.getString("nom"),
						resultSet.getString("prenom"),
						resultSet.getString("idgrp")
				);
				students.add(student);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	@Override
	public boolean update(Etudiant o) {
		try {
			String query = "UPDATE etudiant SET nom=?, prenom=?, idgrp=? WHERE matricule=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, o.getNom());
			statement.setString(2, o.getPrenom());
			statement.setString(3, o.getIdgrp());
			statement.setString(4, o.getMatricule());
			int rowsUpdated = statement.executeUpdate();
			statement.close();
			return rowsUpdated > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Etudiant o) {
		try {
			String query = "DELETE FROM etudiant WHERE matricule=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, o.getMatricule());
			int rowsDeleted = statement.executeUpdate();
			statement.close();
			return rowsDeleted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean insert(Etudiant o) {
		try {
			String query = "INSERT INTO etudiant (matricule, nom, prenom, idgrp) VALUES (?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, o.getMatricule());
			statement.setString(2, o.getNom());
			statement.setString(3, o.getPrenom());
			statement.setString(4, o.getIdgrp());
			int rowsInserted = statement.executeUpdate();
			statement.close();
			return rowsInserted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
