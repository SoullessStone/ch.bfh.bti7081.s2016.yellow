package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.IllnessDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.SessionUtil;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Fasst eine Liste von Krankheiten zusammen
 * 
 * @author Suter, Zumstein
 *
 */
@SuppressWarnings("serial")
public class MultipleIllnessTile extends Tile {
	private static final String MAKE_DIAGNOSIS_CAPTION = "Diagnose erstellen";
	private static final String BROWSER = "Browser";
	private static final String ILLNESS = "Krankheit";
	private static final String MORE = "Mehr...";
	private static final String MAKE_DIAGNOSIS = "Diagnostizieren";
	private static final String DESCRIPTION = "Beschreibung";
	private static final String NAME = "Name";
	private static final String CODE = "Code";
	private static final String NO_ILLNESS_FOUND = "Keine Krankheiten gefunden!";
	private List<IllnessDTO> illnesses;
	private VerticalLayout layout;
	
	public MultipleIllnessTile(List<IllnessDTO> illnesses) {
		this.illnesses = illnesses;
		layout = new VerticalLayout();
		addComponent(layout);
		updateTileValue();
	}

	private void updateTileValue() {
		layout.removeAllComponents();
		if (this.illnesses == null || this.illnesses.isEmpty()) {
			layout.addComponent(new Label(NO_ILLNESS_FOUND));
			return;
		}

		Table table = new Table();
		table.addContainerProperty(CODE, String.class, null);
		table.addContainerProperty(NAME, String.class, null);
		table.addContainerProperty(DESCRIPTION, Button.class, null);
		table.addContainerProperty(MAKE_DIAGNOSIS, Button.class, null);

		for (IllnessDTO illness : this.illnesses) {

			String code = illness.getCode();
			String name = "";
			if (illness.getName().length() > 47) {
				name = illness.getName().substring(0, 47);
			} else {
				name = illness.getName();
			}

			Button descriptionButton = new Button(MORE);
			descriptionButton
					.addClickListener(getClickListenerForIllnessDescription(illness));

			Button diagnosisButton = new Button(MAKE_DIAGNOSIS);
			diagnosisButton
					.addClickListener(getClickListenerForIllnessDiagnosis(illness));

			table.addItem(new Object[] { code, name, descriptionButton,
					diagnosisButton }, this.illnesses.indexOf(illness) + 1);
		}

		table.setPageLength(table.size());
		this.layout.addComponent(table);
	}

	public ClickListener getClickListenerForIllnessDescription(
			IllnessDTO illnessToShow) {
		return new ClickListener() {

			@SuppressWarnings("static-access")
			@Override
			public void buttonClick(ClickEvent event) {
				final Window window = new Window(ILLNESS);
				window.setWidth(1000.0f, Unit.PIXELS);
				window.center();
				window.setModal(true);
				window.setResizable(false);

				String search = illnessToShow.getCode().substring(0, 3);
				BrowserFrame browser = new BrowserFrame(BROWSER,
						new ExternalResource(
								"http://www.icd-code.de/suche/icd/code/"
										+ search + ".-.html"));
				browser.setWidth("1000px");
				browser.setHeight("700px");
				window.setContent(browser);
				getUI().getCurrent().addWindow(window);
			}
		};
	}

	public ClickListener getClickListenerForIllnessDiagnosis(
			IllnessDTO illnessToShow) {
		return new ClickListener() {

			@SuppressWarnings("static-access")
			@Override
			public void buttonClick(ClickEvent event) {
				final Window window = new Window(MAKE_DIAGNOSIS_CAPTION);
				window.setWidth(300.0f, Unit.PIXELS);
				window.center();
				window.setModal(true);
				window.setResizable(false);
				PatientDTO patientInSession = SessionUtil.getPatientInSession(UI.getCurrent().getSession());
				if (patientInSession == null) {
					getUI().getNavigator().navigateTo(
							NavigationIndex.PERSONSEARCHVIEW
									.getNavigationPath());
				} else {
					window.setContent(new CreateDiagnosisTile(illnessToShow,
							patientInSession, window));
					getUI().getCurrent().addWindow(window);
				}
			}
		};
	}

	public void setIllnesses(List<IllnessDTO> illnesses) {
		this.illnesses = illnesses;
		updateTileValue();
	}
}
