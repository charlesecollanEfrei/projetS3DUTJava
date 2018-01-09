package graphic_interface;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import session.Session;

public class Week extends CurrentView {
	
	public enum WeekDayNames {
		EMPTYDAY("",""),
		MONDAY("monday","mon"),
		TUESDAY("tuesday","tue"),
		WEDNESDAY("wednesday","wed"),
		THURSDAY("thursday","thu"),
		FRIDAY("friday","fri"),
		SATURDAY("saturday","sat"),
		SUNDAY("sunday","sun");
		
		private String name;
		private String shortName;
		
		WeekDayNames(String name,String shortName) {
			this.name = name;
			this.shortName = shortName;
		}
		public String getShortName() {
			return Session.instance().getString(shortName);
		}
		public String toString() {
			return Session.instance().getString(name);
		}
		public String toStringEng() {
			return name;
		}
		public String toStringL() {
			return shortName;
		}
	}

	/**
	 * Constructor of week class
	 */
	public Week() {
		super(ActiveView.WEEK_VIEW);
		
		GridLayout daysOfWeekLayout = new GridLayout(1,7);		
		this.setLayout(daysOfWeekLayout);
		for (int di = 0;di<7;di++)	{
			JPanel day = new Day(ActiveView.WEEK_VIEW,WeekDayNames.values()[di+1]);
			day.setBorder(BorderFactory.createLineBorder(Color.black));
			this.add(day);
		}
	}
}