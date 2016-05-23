package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;

/**
 * Dieses Interface muss implementiert werden, wenn man die neu verschriebene
 * PrescriptionDTO einer CreatePrescriptionTile erfahren möchte.
 * 
 * @author SoullessStone
 *
 */
public interface CreationPrescriptiontileObserver {
	/**
	 * Die implementierende Klasse bekommt die in der CreatePrescriptionTile
	 * erstellte PrescriptionDTO
	 * 
	 * @param prescriptionDTO
	 */
	public void perscriptionCreated(PrescriptionDTO prescriptionDTO);
}
