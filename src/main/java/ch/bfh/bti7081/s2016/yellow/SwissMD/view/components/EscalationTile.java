package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.DangerStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DangerStateType;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

/**
 * 
 * Tile for the Escalation service. Show a drop down menu (ComboBox) with the current available danger states.
 * If a patient has set an danger state, the correct one will de chosen. 
 * Once the state is changed, the data will be saved and an e-mail will be sent to the house doctor.
 * 
 * @author Dominique Halter
 *
 */
@SuppressWarnings("serial")
public class EscalationTile extends Tile {

	private final static String COULD_NOT_SEND_MAIL = "E-Mail konnte nicht gesendet werden";
	private final static String COULD_NOT_SET_DANGER_STATE = "Der Gefährdungsstatus konnte nicht gesetzt werden";
	private MeetingDTO meetingDTO;
	private List<String> dangerStates;
	private ComboBox dangerStatesCBox;

	public EscalationTile(MeetingDTO meetingDTO) {
		this.meetingDTO = meetingDTO;
		setTitle("Eskalation");
		dangerStates = new ArrayList<String>(); 
		dangerStates.add("Harmlos");
		dangerStates.add("Krise");
		dangerStates.add("Eigengefaehrdung");
		dangerStates.add("Fremdgefaehrdung");
		dangerStatesCBox = new ComboBox();
		for (String dangerState : dangerStates) {
			dangerStatesCBox.addItem(dangerState);
		}
		if (meetingDTO.getPatient().getDangerState()== DangerStateType.HARMLESS){
			dangerStatesCBox.setValue(dangerStates.get(0));
		}
		else if(meetingDTO.getPatient().getDangerState() == DangerStateType.CRISIS){
			dangerStatesCBox.setValue(dangerStates.get(1));
		}
		else if(meetingDTO.getPatient().getDangerState() == DangerStateType.DANGER_TO_HIMSELF){
			dangerStatesCBox.setValue(dangerStates.get(2));
		}
		else if (meetingDTO.getPatient().getDangerState() == DangerStateType.DANGER_TO_OTHERS){
			dangerStatesCBox.setValue(dangerStates.get(3));
		}
		else {

		}

		addComponent(dangerStatesCBox);
		addComponent(sendButton());
	}

	/*
	 * Button for sending a mail to the house doctor
	 */
	private Button sendButton() {
		Button button = new Button("Meldung", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// Create a new e-mail
				HtmlEmail email = new HtmlEmail();
				email.setHostName("smtp.gmail.com");
				email.setSmtpPort(465);
				email.setSSLOnConnect(true);
				email.setAuthentication("swissmdbfh@gmail.com",
						"SEaD_2016&Yellow");
				try {
					// try to send the mail
					email.setFrom("swissmdbfh@gmail.com");
					email.addTo("dhalter@gmx.ch");
					email.setSubject("Gefährdungsmeldung für Ihren Patienten");
					email.setHtmlMsg("Name: "
							+ meetingDTO.getPatient().getName()
							+ "; Geburtsdatum: "
							+ meetingDTO.getPatient().getBirthdate()
							+ "; Adresse: "
							+ meetingDTO.getPatient().getAddress() + ", "
							+ meetingDTO.getPatient().getCity()
							+ "; Gefaehrdungsstufe: "
							+ dangerStatesCBox.getValue().toString());
					email.send();
					Notification notify = new Notification("Erfolgreich",
							"E-Mail erfolgreich versendet",
							Notification.Type.ASSISTIVE_NOTIFICATION);
					notify.setDelayMsec(500);
					notify.setPosition(Position.MIDDLE_CENTER);
					notify.show(Page.getCurrent());
				} catch (EmailException e) {
					Notification.show(COULD_NOT_SEND_MAIL, Type.ERROR_MESSAGE);
					e.printStackTrace();
				}
				// persist the new danger state
				switch(dangerStatesCBox.getValue().toString()){
				case "Harmlos":
					try {
						meetingDTO.getPatient().setDangerStateHarmless();
					} catch (DangerStateException e) {
						Notification.show(COULD_NOT_SET_DANGER_STATE, Type.ERROR_MESSAGE);
						e.printStackTrace();
					}
				case "Krise":
					try {
						meetingDTO.getPatient().setDangerStateCrisis();
					} catch (DangerStateException e) {
						Notification.show(COULD_NOT_SET_DANGER_STATE, Type.ERROR_MESSAGE);
						e.printStackTrace();
					}				
				case "Eigengefaehrdung":
					try {
						meetingDTO.getPatient().setDangerStateDangerToHimself();
					} catch (DangerStateException e) {
						Notification.show(COULD_NOT_SET_DANGER_STATE, Type.ERROR_MESSAGE);
						e.printStackTrace();
					}				
				case "Fremdgefaehrdung":
					try {
						meetingDTO.getPatient().setDangerStateDangerToOthers();
					} catch (DangerStateException e) {
						Notification.show(COULD_NOT_SET_DANGER_STATE, Type.ERROR_MESSAGE);
						e.printStackTrace();
					}				
				default:
					break;
				}
			}
		});
		return button;
	}

}
