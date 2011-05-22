package cz.hackathon.programy.provider;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import cz.hackathon.programy.dto.Action;
import cz.hackathon.programy.dto.Stage;
import cz.hackathon.programy.dto.StageEvent;

/**
 * User: lukas.marek@cleverlance.com Date: 22.5.11 Time: 12:28
 */
public class FakeProvider implements ActionProvider {
	private List<Action> actions;


	public FakeProvider() {
		List<Action> result = new ArrayList<Action>();
		{
			Action a1 = new Action();
			a1.id = "01";
			a1.name = "Sázava Fest - pátek";
			a1.description = "Už 10 let pro Vás připravujeme Sázavafest. Festival zasazený do romantické přírody posázaví, který je dnes už tradicí. A to především díky Vám, věrným fanouškům a návštěvníkům!";
			a1.imageUrl = "http://www.sazavafest.cz/sites/default/files/hp-vizual.jpg";
			a1.locationLat = "50.026932";
			a1.locationLong = "14.4944";
            a1.webUrl = "http://www.sazavafest.cz";
//			a1.stages = new ArrayList<Stage>();
            {
                Stage s1 = new Stage();
                s1.name = "Stage1";
                s1.desc = "Hlavní stage";
                s1.events.add(new StageEvent("Akce01", "8:00", "10:00"));
                s1.events.add(new StageEvent("Akce02", "8:00", "10:00"));
                a1.stages.add(s1);
            }
            {
                Stage s1 = new Stage();
                s1.name = "Stage2";
                s1.desc = "Vedlejší stage";
                s1.events.add(new StageEvent("Akce01", "8:00", "10:00"));
                s1.events.add(new StageEvent("Akce02", "8:00", "10:00"));
                a1.stages.add(s1);
            }
            {
                Stage s1 = new Stage();
                s1.name = "Stage3";
                s1.desc = "Vedlejší stage";
                s1.events.add(new StageEvent("Akce01", "8:00", "10:00"));
                s1.events.add(new StageEvent("Akce02", "8:00", "10:00"));
                a1.stages.add(s1);
            }
            {
                Stage s1 = new Stage();
                s1.name = "Stage4";
                s1.desc = "Vedlejší stage";
                s1.events.add(new StageEvent("Akce01", "8:00", "10:00"));
                s1.events.add(new StageEvent("Akce02", "8:00", "10:00"));
                a1.stages.add(s1);
            }
            {
                Stage s1 = new Stage();
                s1.name = "Stage5";
                s1.desc = "Vedlejší stage";
                s1.events.add(new StageEvent("Akce01", "8:00", "10:00"));
                s1.events.add(new StageEvent("Akce02", "8:00", "10:00"));
                a1.stages.add(s1);
            }


			result.add(a1);
		}
		{
			Action a1 = new Action();
			a1.id = "02";
			a1.name = "Sázava Fest - sobota";
			a1.description = "Už 10 let pro Vás připravujeme Sázavafest. Festival zasazený do romantické přírody posázaví, který je dnes už tradicí. A to především díky Vám, věrným fanouškům a návštěvníkům!";
			a1.imageUrl = "http://www.sazavafest.cz/sites/default/files/hp-vizual.jpg";
			a1.locationLat = "123";
			a1.locationLong = "456";
//			a1.stages = new ArrayList<Stage>();
			Stage s2 = new Stage();
			s2.name = "Stage1";
			s2.desc = "Hlavní stage";
			s2.events.add(new StageEvent("Akce01", "8:00", "10:00"));
			s2.events.add(new StageEvent("Akce02", "8:00", "10:00"));
			a1.stages.add(s2);
			result.add(a1);
		}
		this.actions = result;
	}

	public List<Action> getAvailableActions() {
		return this.actions;
	}

	public Action getAction(int actionId) {
		return this.actions.get(actionId);
	}

	public Stage getStages(int actionId, int stageId) {
		return this.actions.get(actionId).stages.get(stageId);
	}

	public void addXml(String path) {
		Log.d("FakeProvider", "Going to fetch:" + path);
	}
}
