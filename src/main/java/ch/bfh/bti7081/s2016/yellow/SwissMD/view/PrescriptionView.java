package ch.bfh.bti7081.s2016.yellow.SwissMD.view;

import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PrescriptionDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.MeetingStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.PrescriptionPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.CreatePrescriptionTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.MultiplePrescriptionTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.components.PrescriptionTile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.BaseLayout;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.LayoutFactory.LayoutType;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.TileLayoutFactory;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class PrescriptionView extends CustomComponent implements View {
	private PrescriptionPresenter prescriptionPresenter = new PrescriptionPresenter();

	private BaseLayout layout;

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

		
		List<PrescriptionDTO> prescriptions = null;
		try {
			PatientDTO patientInSession = (PatientDTO) getUI().getSession()
					.getAttribute("currentPatient");
			if (patientInSession == null) {
				getUI().getNavigator().navigateTo(
						NavigationIndex.PERSONSEARCHVIEW.getNavigationPath());
			}
			prescriptions = prescriptionPresenter
					.getPrescriptionsForPatient(patientInSession);
		} catch (MeetingStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		layout.addComponent(new MultiplePrescriptionTile(prescriptions));
	}

}
