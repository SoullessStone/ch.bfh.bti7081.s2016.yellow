package ch.bfh.bti7081.s2016.yellow.SwissMD.view.components;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.vaadin.server.Page;
import com.vaadin.server.Page.Styles;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.MeetingDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.PersonDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.layout.Tile;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.navigation.NavigationIndex;

/**
 * 
 * Tile for the Escalation service.
 * @author Dominique Halter
 *
 */
public class EscalationTile extends Tile {
	
	private final static String COULD_NOT_SEND_MAIL = "E-Mail konnte nicht gesendet werden";
	private MeetingDTO meetingDTO;
	private List<String> dangerStates;
	private ComboBox dangerStatesCBox;
	
	public EscalationTile(MeetingDTO meetingDTO)  {
		this.meetingDTO=meetingDTO;
		setTitle("Eskalation");
        dangerStates = new ArrayList<String>(); //zu Testzwecken
        dangerStates.add("Krise");
        dangerStates.add("Eigengefaehrdung");
        dangerStates.add("Fremdgefaehrdung");
        dangerStatesCBox = new ComboBox();
		for (String dangerState : dangerStates) {
			dangerStatesCBox.addItem(dangerState);
		}
		addComponent(dangerStatesCBox);
		addComponent(sendButton());
	}
	
	/*
	 * Button for sending a mail to the house doctor
	 */
	private Button sendButton(){
		Button button = new Button("Meldung", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// Create a new e-mail
				HtmlEmail email = new HtmlEmail();
				email.setHostName("smtp.gmail.com");
				email.setSmtpPort(465);
				email.setSSLOnConnect(true);
				email.setAuthentication(
				       "swissmdbfh@gmail.com", "SEaD_2016&Yellow");
			    try {
			    	// try to send the mail
					email.setFrom("swissmdbfh@gmail.com");
					email.addTo("dhalter@gmx.ch");
					email.setSubject("Gefährdungsmeldung für Ihren Patienten");
					email.setHtmlMsg("Name: " + meetingDTO.getPatient().getName() + "; Geburtsdatum: " + meetingDTO.getPatient().getBirthdate() 
							+ "; Adresse: " + meetingDTO.getPatient().getAddress() + ", " + meetingDTO.getPatient().getCity() + "; Gefaehrdungsstufe: " + dangerStatesCBox.getValue().toString());
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
