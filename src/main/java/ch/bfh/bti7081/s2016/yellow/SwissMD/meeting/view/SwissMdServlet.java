package ch.bfh.bti7081.s2016.yellow.SwissMD.meeting.view;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

@WebServlet(urlPatterns = "/*", name = "SwissMdServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = SwissMdUI.class, productionMode = false)
public class SwissMdServlet extends VaadinServlet {

}
