package ch.bfh.bti7081.s2016.yellow.SwissMD.presenter;

import ch.bfh.bti7081.s2016.yellow.SwissMD.view.WikiView;

public class WikiPresenter {
	// TODO: Knows the model
	private WikiView wikiView;
	
	public WikiPresenter(WikiView wikiView) {
		System.out.println("init WikiPresenter");
		this.wikiView = wikiView;
	}
}
