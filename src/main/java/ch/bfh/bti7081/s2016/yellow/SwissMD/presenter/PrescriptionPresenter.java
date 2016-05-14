package ch.bfh.bti7081.s2016.yellow.SwissMD.presenter;

import ch.bfh.bti7081.s2016.yellow.SwissMD.view.PrescriptionView;

public class PrescriptionPresenter {
	// TODO: Knows the model
	private PrescriptionView prescriptionView;

	public PrescriptionPresenter(PrescriptionView prescriptionView) {
		System.out.println("init PrescriptionPresenter");
		this.prescriptionView = prescriptionView;
	}
}
