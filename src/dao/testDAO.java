package dao;

import java.util.ArrayList;

import models.Etudiant;
import models.Groupe;

public class testDAO {

	public static void main(String[] args) {
		DAOGroupe dag = new DAOGroupe();
		
		Groupe g1 = new Groupe ("MDW3.1", "Multimédia et Développement Web", 2,"TI");
		dag.insert(g1);
		Groupe g2 = new Groupe ("DSI3.1", "Développement de Système d'information", 2,"TI");
		dag.insert(g2);
		Groupe g3 = new Groupe ("RSI3.1", "Réseaux et Systèmes Informatiques", 2,"TI");
		dag.insert(g3);
		ArrayList<Groupe> rs1 = dag.findAll();
		System.out.println ("Liste des groupes");
		for (int i=0; i<rs1.size(); i++)
			System.out.println ( rs1.get(i));
		g2.setLibGroupe("Développement des Systèmes informatiques");
		dag.update(g2);
		System.out.println ("Liste des groupes après modification");
		for (int i=0; i<rs1.size(); i++)
			System.out.println ( rs1.get(i));
		dag.delete(g3);
		System.out.println ("Liste des groupes après suppression");
		for (int i=0; i<rs1.size(); i++)
			System.out.println ( rs1.get(i));
		Etudiant e1, e2, e3, e4;
		e1= new Etudiant ("1001", "Ben Saleh", "Mohamed", "MDW3.1");
		e2= new Etudiant ("1002", "Ben Saleh", "Karim", "DSI3.1");
		e3= new Etudiant ("1003", "Kilani", "Rim", "MDW3.1");
		e4= new Etudiant ("1004", "Ben Mohamed", "Riheb", "DSI3.1");
		DAOEtudiant de = new DAOEtudiant();
		de.insert(e1);
		de.insert(e2);
		de.insert(e3);
		de.insert(e4);
		ArrayList<Etudiant> rs2 = de.findAll();
		System.out.println ("Liste des étudiants");
		for (int i=0; i<rs2.size(); i++)
			System.out.println (rs2.get(i));
		Etudiant e = de.findByID("1003");
		System.out.println ("L'étudiant ayant le matricule 1003 :\n" + e);
	}

}
