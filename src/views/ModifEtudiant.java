package views;

import javax.swing.*;

import controllers.EtudiantControleur;
import dao.DAOGroupe;
import models.Etudiant;
import models.Groupe;

import java.awt.*;
import java.util.ArrayList;
public class ModifEtudiant extends JFrame {
	
    JLabel lmatricule = new JLabel ("Matricule");
    JTextField tmatricule ;
    JLabel lnom = new JLabel ("Nom");
    JTextField tnom ;
    JLabel lprenom = new JLabel ("Prénom");
    JTextField tprenom ;
    JLabel lgroupe = new JLabel ("Groupe");
    JList lstgroupe;
    DefaultListModel<String> modelgroupe = new  DefaultListModel<String>();
    JButton btnValidate = new JButton ("Valider");
    JPanel p = new JPanel (new GridLayout (0,2));
    Etudiant v;
    public ModifEtudiant(Etudiant vt)
    {
    	super ("Modifier un étudiant");
    	v= vt;
    	tmatricule = new JTextField(v.getMatricule());
    	p.add(lmatricule); p.add(tmatricule); tmatricule.setEnabled(false);
    	p.add(lnom); tnom = new JTextField(v.getNom());p.add(tnom); 
    	p.add(lprenom); tprenom = new JTextField(v.getPrenom());p.add(tprenom); 
    	p.add(lgroupe); 
    	DAOGroupe da = new DAOGroupe();
    	ArrayList<Groupe> lg = da.findAll();
    	int indice =0;
    	for (int i=0; i<lg.size(); i++)
    		{modelgroupe.add(i, lg.get(i).getIdgrp());
    			if (lg.get(i).getIdgrp().equals(v.getIdgrp())) 
    				indice =i;
    		}
    	
    	lstgroupe = new JList(modelgroupe);
    	lstgroupe.setSelectedIndex(indice);
    	p.add(lstgroupe); 
    	p.add(btnValidate);
    	this.setSize(new Dimension (400, 300));
    	getContentPane().add(p);
    	setVisible(true);
    	
    	btnValidate.addActionListener(new EtudiantControleur(this) );
    	
    }
    public Etudiant getInfoEtudiant ()
    {	Etudiant v=new Etudiant();
    	v.setMatricule(tmatricule.getText());
		v.setNom(tnom.getText());
		v.setPrenom(tprenom.getText());
		v.setIdgrp(modelgroupe.get(lstgroupe.getSelectedIndex()));
		System.out.println (v);
		return v;
	  
    }

}
