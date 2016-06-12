package ch.bfh.bti7081.s2016.yellow.SwissMD.presenter;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.IllnessDao;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.IllnessDaoImpl;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao.WebEntityManagerProvider;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto.IllnessDTO;
import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.Illness;
import ch.bfh.bti7081.s2016.yellow.SwissMD.view.WikiView;

/**
 * Presenter für die WikiView
 * 
 * @author Zumstein
 *
 */
public class WikiPresenter {
	private IllnessDao illnessDao;

	public WikiPresenter(WikiView wikiView) {
		System.out.println("init WikiPresenter");
		this.illnessDao = new IllnessDaoImpl(new WebEntityManagerProvider());
	}

	public IllnessDTO findIllnessById(Long illnessId) {
		return new IllnessDTO(illnessDao.read(illnessId));
	}

	public List<IllnessDTO> searchIllnesses(String searchString) {
		List<Illness> searchResult = illnessDao.findByCodeOrName(searchString);
		List<IllnessDTO> illnessDTOs = new ArrayList<>();
		for (Illness illness : searchResult) {
			illnessDTOs.add(new IllnessDTO(illness));
		}
		return illnessDTOs;
	}
}
