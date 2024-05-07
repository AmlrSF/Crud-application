package views;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import controllers.EtudiantControleur;
import dao.DAOGroupe;
import models.Etudiant;
import models.Groupe;
public class AjoutEtudiant extends JFrame {
	JLabel lmatricule = new JLabel ("Matricule");
    JTextField tmatricule = new JTextField();
    JLabel lnom = new JLabel ("Nom");
    JTextField tnom = new JTextField();
    JLabel lprenom = new JLabel ("Prénom");
    JTextField tprenom = new JTextField();
    JLabel lgroupe = new JLabel ("Groupe");
    JList lstgroupe ;
    JButton btnSave = new JButton ("Enregistrer");
    JPanel p = new JPanel (new GridLayout (0,2));
    Etudiant v;
    DefaultListModel<String> modelgroupe = new  DefaultListModel<String>();
    public AjoutEtudiant()
    {
    	super ("Ajouter un étudiant");
    	
    	p.add(lmatricule); p.add(tmatricule);
    	p.add(lnom); p.add(tnom); 
    	p.add(lprenom); p.add(tprenom);
    	DAOGroupe da = new DAOGroupe();
    	ArrayList<Groupe> lg = da.findAll();
    	for (int i=0; i<lg.size(); i++)
    		modelgroupe.add(i, lg.get(i).getIdgrp());
    	lstgroupe = new JList(modelgroupe);
    	p.add(lgroupe); p.add(lstgroupe); 
    	p.add(btnSave);
    	this.setSize(new Dimension (400, 300));
    	getContentPane().add(p);    	
    	btnSave.addActionListener(new EtudiantControleur(this) );
    	setVisible(true);
    }
   public Etudiant getInfoEtudiant ()
    {	Etudiant v = new Etudiant();
    	v.setMatricule(tmatricule.getText());
		v.setNom(tnom.getText());
		v.setPrenom(tprenom.getText());
		v.setIdgrp((String)lstgroupe.getSelectedValue());
		return v;
	    
    }
	
	
		
}
