package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;

/**
 * This Interface must be implemented if a class must be informed about a new created PrescriptionDTO 
 * in the CreatePrescriptionTile
 *
 * @author SoullessStone
 *
 */
public interface CreationPrescriptiontileObserver {
	/**
	 * The implemented class gets the created PrescriptionDTO from the CreatePrescriptionTile
	 * 
	 * @param prescriptionDTO
	 */
	public void perscriptionCreated(PrescriptionDTO prescriptionDTO);
}
