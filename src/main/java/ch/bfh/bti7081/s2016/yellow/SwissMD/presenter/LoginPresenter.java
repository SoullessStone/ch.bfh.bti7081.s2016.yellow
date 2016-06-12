package ch.bfh.bti7081.s2016.yellow.SwissMD.presenter;

import ch.bfh.bti7081.s2016.yellow.SwissMD.view.LoginView;

/**
 * Presenter für das Loginfenster
 * 
 * @author Mutz
 *
 */
public class LoginPresenter {
	private LoginView loginView;

	public LoginPresenter(LoginView loginView) {
		System.out.println("init LoginPresenter");
		this.loginView = loginView;
	}
}
