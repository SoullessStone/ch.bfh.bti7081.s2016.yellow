package ch.bfh.bti7081.s2016.yellow.SwissMD.presenter;

import ch.bfh.bti7081.s2016.yellow.SwissMD.view.WikiView;

public class WikiPresenter {
	// TODO: Knows the model
	private WikiView wikiView;
	
	public enum ArtikelId{
		KOPFSCHMERZEN, ANGSTZUSTAENDE
	}
	
	public WikiPresenter(WikiView wikiView) {
		System.out.println("init WikiPresenter");
		this.wikiView = wikiView;
	}

	public String getWikiText(ArtikelId artikelToShow) {
		// TODO: Get wikitest for the requested artikel
		if (artikelToShow.equals(ArtikelId.ANGSTZUSTAENDE)){
			return "<b>ANGST, MANN</b>";
		}
		if (artikelToShow.equals(ArtikelId.KOPFSCHMERZEN)){
			return "<b>KOPFSCHMERZEN, AUA!</b>";
		}
		return "Artikel not found";
	}
}

