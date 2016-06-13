package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PatientDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.CouldNotSaveException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.exception.DangerStateException;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.util.DangerStateType;
import ch.bfh.bti7081.s2016.yellow.SwissMD.presenter.PersonPresenter;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
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
	private PatientDTO patientDTO;
	private List<String> dangerStates;
	private ComboBox dangerStatesCBox;
	private CheckBox sendMail;
	private PersonPresenter personPresenter;

	public EscalationTile(MeetingDTO meetingDTO, PatientDTO patientDTO) {
		this.meetingDTO = meetingDTO;
		this.patientDTO = patientDTO;
		this.personPresenter = new PersonPresenter();
		setTitle("Gefährdungsstatus des Patienten");
		dangerStates = new ArrayList<String>(); 
		dangerStates.add("Harmlos");
		dangerStates.add("Krise");
		dangerStates.add("Eigengefaehrdung");
		dangerStates.add("Fremdgefaehrdung");
		dangerStatesCBox = new ComboBox();
		for (String dangerState : dangerStates) {
			dangerStatesCBox.addItem(dangerState);
		}
		if (patientDTO.getDangerState()== DangerStateType.HARMLESS){
			dangerStatesCBox.setValue(dangerStates.get(0));
		}
		else if(patientDTO.getDangerState() == DangerStateType.CRISIS){
			dangerStatesCBox.setValue(dangerStates.get(1));
		}
		else if(patientDTO.getDangerState() == DangerStateType.DANGER_TO_HIMSELF){
			dangerStatesCBox.setValue(dangerStates.get(2));
		}
		else if (patientDTO.getDangerState() == DangerStateType.DANGER_TO_OTHERS){
			dangerStatesCBox.setValue(dangerStates.get(3));
		}
		else {

		}
		addComponent(dangerStatesCBox);
		sendMail = new CheckBox("E-Mail an Hausarzt senden?");
		addComponent(sendMail);
		addComponent(sendButton());
	}

	/*
	 * Button for saving the new danger state and, if the state is critical, sending an e-mail to the house doctor
	 */
	private Button sendButton() {
		Button button = new Button("Meldung", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// persist the new danger state
				if (dangerStatesCBox.getValue().toString().equalsIgnoreCase("Harmlos")){
					try {
						if (sendMail.getValue()==true){
							patientDTO.setDangerStateHarmless();
							personPresenter.updateDangerState(patientDTO);
							sendMailAlert();
						}
						else {
							patientDTO.setDangerStateHarmless();
							personPresenter.updateDangerState(patientDTO);
						}	
					} catch (DangerStateException e) {
						Notification.show(COULD_NOT_SET_DANGER_STATE, Type.ERROR_MESSAGE);
						e.printStackTrace();
					} catch (CouldNotSaveException e) {
						Notification.show(COULD_NOT_SET_DANGER_STATE, Type.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
				else if(dangerStatesCBox.getValue().toString().equalsIgnoreCase("Krise")){
					try {
						if (sendMail.getValue()==true){
							patientDTO.setDangerStateCrisis();
							personPresenter.updateDangerState(patientDTO);
							sendMailAlert();
						}
						else {
							patientDTO.setDangerStateCrisis();
						}	
					} catch (DangerStateException e) {
						Notification.show(COULD_NOT_SET_DANGER_STATE, Type.ERROR_MESSAGE);
						e.printStackTrace();
					} catch (CouldNotSaveException e) {
						Notification.show(COULD_NOT_SET_DANGER_STATE, Type.ERROR_MESSAGE);
						e.printStackTrace();
					}	
				}
				else if(dangerStatesCBox.getValue().toString().equalsIgnoreCase("Eigengefaehrdung")){
					try {
						if (sendMail.getValue()==true){
							patientDTO.setDangerStateDangerToHimself();
							personPresenter.updateDangerState(patientDTO);
							sendMailAlert();
						}
						else {
							patientDTO.setDangerStateDangerToHimself();
							personPresenter.updateDangerState(patientDTO);
						}	
					} catch (DangerStateException e) {
						Notification.show(COULD_NOT_SET_DANGER_STATE, Type.ERROR_MESSAGE);
						e.printStackTrace();
					} catch (CouldNotSaveException e) {
						Notification.show(COULD_NOT_SET_DANGER_STATE, Type.ERROR_MESSAGE);
						e.printStackTrace();
					}	
				}
				else if (dangerStatesCBox.getValue().toString().equalsIgnoreCase("Fremdgefaehrdung")){
					try {
						if (sendMail.getValue()==true){
							patientDTO.setDangerStateDangerToOthers();
							personPresenter.updateDangerState(patientDTO);
							sendMailAlert();
						}
						else {
							patientDTO.setDangerStateDangerToOthers();
							personPresenter.updateDangerState(patientDTO);
						}	
					} catch (DangerStateException e) {
						Notification.show(COULD_NOT_SET_DANGER_STATE, Type.ERROR_MESSAGE);
						e.printStackTrace();
					} catch (CouldNotSaveException e) {
						Notification.show(COULD_NOT_SET_DANGER_STATE, Type.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
			}
			/*
			 * Send an e-mail to the house doctor if the danger state is critical
			 */
			public void sendMailAlert(){
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
			}
		});
		return button;
	}

}
