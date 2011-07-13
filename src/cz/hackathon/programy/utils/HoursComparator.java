package cz.hackathon.programy.utils;

import java.util.Comparator;

import cz.hackathon.programy.dto.StageEvent;

public class HoursComparator implements Comparator<StageEvent> {

	@Override
	public int compare(StageEvent arg0, StageEvent arg1) {
		
		String from0 = arg0.from;
		String from1 = arg1.from;
		
		String hour_str0 = from0.substring(0, 2); 
		String hour_str1 = from1.substring(0, 2);

		String minute_str0 = from0.substring(3); 
		String minute_str1 = from1.substring(3);
		
		int hour0 = Integer.parseInt(hour_str0);
		int hour1 = Integer.parseInt(hour_str1);
		
		int minute0 = Integer.parseInt(minute_str0);
		int minute1 = Integer.parseInt(minute_str1);

		if (hour0 > hour1) {
			return 1;
		} else {
			if (hour1 > hour0) {
				return -1;
			} else {
				if (minute0 > minute1) {
					return 1;
				} else {
					if (minute0 < minute1) {
						return -1;
					} else {
						return 0;
					}
				}
			}
		}
	}

}
