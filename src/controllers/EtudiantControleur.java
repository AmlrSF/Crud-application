package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import dao.DAOEtudiant;
import models.Etudiant;
import views.AjoutEtudiant;
import views.ListEtudiants;
import views.ModifEtudiant;

public class EtudiantControleur implements ActionListener {
	static AjoutEtudiant fr1;
	static ModifEtudiant fr2;
	static ListEtudiants fr3;
	Etudiant vm;
	DAOEtudiant ed = new DAOEtudiant();

	public EtudiantControleur(JFrame f) {
		if (f instanceof AjoutEtudiant)
			fr1 = (AjoutEtudiant) f;
		if (f instanceof ModifEtudiant)
			fr2 = (ModifEtudiant) f;
		if (f instanceof ListEtudiants)
			fr3 = (ListEtudiants) f;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton b = (JButton) arg0.getSource();
		if (b.getText().equals("Ajouter")) {
			fr1 = new AjoutEtudiant();
		} else if (b.getText().equals("Modifier")) {
			Etudiant etudiant = fr3.getEtudiantSelectionne();
			if (etudiant != null) {
				fr2 = new ModifEtudiant(etudiant);
			} else {
				JOptionPane.showMessageDialog(null, "Veuillez sélectionner un étudiant à modifier.");
			}
		} else if (b.getText().equals("Enregistrer")) {
			Etudiant etudiant = fr1.getInfoEtudiant();
			ed.insert(etudiant);
			fr3.update(etudiant, "ajouter");
			fr1.dispose();
		} else if (b.getText().equals("Valider")) {
			Etudiant etudiant = fr2.getInfoEtudiant();
			ed.update(etudiant);
			fr3.update(etudiant, "modifier");
			fr2.dispose();
		} else if (b.getText().equals("Supprimer")) {
			Etudiant etudiant = fr3.getEtudiantSelectionne();
			if (etudiant != null) {
				int option = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to delete this student?", "Confirmation", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					ed.delete(etudiant);
					fr3.update(etudiant, "supprimer");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Veuillez sélectionner un étudiant à supprimer.");
			}
		}
	}
}
