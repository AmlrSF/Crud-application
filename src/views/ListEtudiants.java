package views;

import java.awt.Dimension;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controllers.EtudiantControleur;
import dao.DAOEtudiant;
import models.Etudiant;

public class ListEtudiants extends JFrame {
	JTable table;
	JScrollPane pt;
	JPanel p1, p2, p;
	JButton btnAdd, btnModify, btnRemove, btnGroupe;
	String col[] = { "Matricule", "Nom", "Prénom", "Groupe" };
	String cont[][];
	DefaultTableModel modeltable;
	Etudiant v = new Etudiant();

	public ListEtudiants(List<Etudiant> list) {
		super("Liste des étudiants");

		cont = Remplir(list);
		modeltable = new DefaultTableModel(cont, col);
		table = new JTable(modeltable);
		pt = new JScrollPane(table);
		pt.setPreferredSize(new Dimension(600, 300));
		p1 = new JPanel();
		p1.add(pt);
		p2 = new JPanel();
		btnAdd = new JButton("Ajouter");
		btnModify = new JButton("Modifier");
		btnRemove = new JButton("Supprimer");
		p2.add(btnAdd);
		p2.add(btnModify);
		p2.add(btnRemove);
		p = new JPanel();
		p.add(p1);
		p.add(p2);
		this.setSize(new Dimension(700, 500));
		this.setVisible(true);
		getContentPane().add(p);
		btnAdd.addActionListener(new EtudiantControleur(this));
		btnModify.addActionListener(new EtudiantControleur(this));
		btnRemove.addActionListener(new EtudiantControleur(this));

	}

	public String[][] Remplir(List<Etudiant> c) {
		String[][] t = new String[c.size()][4];
		for (int i = 0; i < c.size(); i++) {
			Etudiant etudiant = c.get(i);
			t[i][0] = etudiant.getMatricule();
			t[i][1] = etudiant.getNom();
			t[i][2] = etudiant.getPrenom();
			t[i][3] = etudiant.getIdgrp();
		}
		return t;
	}

	public String getMatriculeSelectionne() {
		String s = (String) table.getValueAt(table.getSelectedRow(), 0);
		return s;
	}

	public Etudiant getEtudiantSelectionne() {
		int indice = table.getSelectedRow();
		if (indice == -1)
			return null;
		else {
			Etudiant e = new Etudiant();
			e.setMatricule((String) table.getValueAt(indice, 0));
			e.setNom((String) table.getValueAt(indice, 1));
			e.setPrenom((String) table.getValueAt(indice, 2));
			e.setIdgrp((String) table.getValueAt(indice, 3));
			return e;
		}

	}

	public void update(Etudiant v, String mode) {
		if (mode.equals("ajouter")) {
			String[] newRow = { v.getMatricule(), v.getNom(), v.getPrenom(), v.getIdgrp() };
			modeltable.addRow(newRow);
		} else if (mode.equals("modifier")) {
			int rowIndex = table.getSelectedRow();
			modeltable.setValueAt(v.getMatricule(), rowIndex, 0);
			modeltable.setValueAt(v.getNom(), rowIndex, 1);
			modeltable.setValueAt(v.getPrenom(), rowIndex, 2);
			modeltable.setValueAt(v.getIdgrp(), rowIndex, 3);
		} else if (mode.equals("supprimer")) {
			int rowIndex = table.getSelectedRow();
			modeltable.removeRow(rowIndex);
		}
	}

	public static void main(String[] args) {
		DAOEtudiant daoEtudiant = new DAOEtudiant();
		new ListEtudiants(daoEtudiant.findAll());
	}

}
