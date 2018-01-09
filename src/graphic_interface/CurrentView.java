package graphic_interface;

import java.util.Calendar;
import java.util.GregorianCalendar;

import graphic_interface.Week.WeekDayNames;

import javax.swing.JPanel;

public class CurrentView extends JPanel{
	
	public enum ActiveView {
		MONTH_VIEW("Month View"),
		WEEK_VIEW("Week View"),
		DAY_VIEW("Day View");
		
		private String activeView;
		
		ActiveView(String activeView) {
			this.activeView = activeView;
		}
		
		public String toString() {
			return activeView;
		}		
	}
	
	protected CurrentView.ActiveView activeView;
	
	public CurrentView(ActiveView activeView) {
		this.activeView = activeView;
	}

	/**
	 * Choose a pre-determined default view
	 */
	public CurrentView() {
		this.activeView = ActiveView.MONTH_VIEW;
	}

	/**
	 * Return an initialisation of a view, depending of the parameter
	 * @param active
	 * @return JPanel
	 */
	public JPanel getView(ActiveView active) {
			
			JPanel agendaView = null;
			switch (active) {
				case MONTH_VIEW:
					Month monthPanel = new Month();
					agendaView = monthPanel;
					break;
				case WEEK_VIEW:
					Week weekPanel = new Week();
					agendaView = weekPanel;
					break;
				case DAY_VIEW:
					int dayOfMonth = new GregorianCalendar().get(Calendar.DAY_OF_MONTH) % 7;
					Day dayPanel = new Day(active, WeekDayNames.values()[dayOfMonth+1]);
					agendaView = dayPanel;
					break;
				default:
					break;
			}
			return agendaView;
	}
}