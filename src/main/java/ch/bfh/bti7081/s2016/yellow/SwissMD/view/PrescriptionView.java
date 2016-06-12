package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.DangerStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.PrescriptionPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.CreatePrescriptionTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.CreationPrescriptiontileObserver;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.MultiplePrescriptionTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.BaseLayout;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory.LayoutType;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.TileLayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

/**
 * Hier werden Verschreibungen angezeigt
 * 
 * @author SoullessStone
 *
 */
@SuppressWarnings("serial")
public class PrescriptionView extends CustomComponent implements View,
		CreationPrescriptiontileObserver {
	private static final String MEETING_STATE_ERROR = "Falscher Meetingstatus für den Patienten. Bitte anpassen.";
	private static final String DANGER_STATE_ERROR = "Der Patient hat keinen Gefährdungsstatus gesetzt!";
	private PrescriptionPresenter prescriptionPresenter = new PrescriptionPresenter();

	private List<PrescriptionDTO> prescriptions;
	private BaseLayout layout;

	private MultiplePrescriptionTile multiPrescriptionTile;

	public PrescriptionView() throws MeetingStateException {
		try {
			layout = LayoutFactory.getInstance(LayoutType.TILE_LAYOUT)
					.createLayout(
							TileLayoutFactory.Arguments.ELEMENTS_PER_ROW
									.getName() + ":3");
		} catch (Exception e1) {
			// TODO Go to error View
			e1.printStackTrace();
		}
		setCompositionRoot(layout);
	}

	@Override
	public void enter(ViewChangeEvent event) {

		try {
			PatientDTO patientInSession = (PatientDTO) getUI().getSession()
					.getAttribute("currentPatient");
			if (patientInSession == null) {
				getUI().getNavigator().navigateTo(
						NavigationIndex.PERSONSEARCHVIEW.getNavigationPath());
			} else {
				CreatePrescriptionTile createPrescriptionTile = new CreatePrescriptionTile(
						prescriptionPresenter.getPossibleDrugs(),
						patientInSession, false);
				createPrescriptionTile.addObserver(this);
				layout.addComponent(createPrescriptionTile);

				this.prescriptions = prescriptionPresenter
						.getPrescriptionsForPatient(patientInSession);

				this.multiPrescriptionTile = new MultiplePrescriptionTile(
						this.prescriptions);
				layout.addComponent(multiPrescriptionTile);
			}
		} catch (MeetingStateException e) {
			Notification.show(MEETING_STATE_ERROR, Type.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (DangerStateException e) {
			Notification.show(DANGER_STATE_ERROR, Type.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	@Override
	public void perscriptionCreated(PrescriptionDTO prescriptionDTO) {
		prescriptionPresenter.savePrescription(prescriptionDTO);
		this.prescriptions.add(prescriptionDTO);
		this.multiPrescriptionTile.setPrescriptions(this.prescriptions);
	}

}
