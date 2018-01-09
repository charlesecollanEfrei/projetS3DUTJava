package graphic_interface;

import graphic_interface.Week.WeekDayNames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputListener;

import session.Session;

public class Day extends CurrentView {

	/**
	 * Call the CurrentView constructor
	 * @param active
	 */
	public Day(CurrentView.ActiveView active) {
		super(active);
	}

	/**
	 * setup the display of days depending of the view
	 * @param askedView
	 * @param weekDayNames
	 */
	public Day(CurrentView.ActiveView askedView, WeekDayNames weekDayNames) {

		super(askedView);
		
		switch (askedView) {
		case DAY_VIEW:
			JPanel topPanel = new JPanel();
			JPanel underPanel = new JPanel();
			GridLayout dayLayout = new GridLayout(4, 6);
			underPanel.setLayout(dayLayout);
			this.setLayout(new BorderLayout());
			
			String currentDay = Session.instance().getString(weekDayNames.toStringEng().toLowerCase());
			JLabel day = new JLabel(currentDay);
			topPanel.add(day);
			topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			
			for (int am = 0 ; am < 4 ; am ++) {
				for (int hi = 0;hi<6;hi++) {
					JPanel hour = new JPanel();
					hour.add(new JLabel(new Integer(hi+(6*am)).toString()));
					hour.setBorder(BorderFactory.createLineBorder(Color.black));
					underPanel.add(hour);
				}
			}
			
			this.add(topPanel, BorderLayout.PAGE_START);
			this.add(underPanel, BorderLayout.CENTER);
			
			break;
		case WEEK_VIEW:
			GridLayout daysLayout;
			switch(weekDayNames) {
			case EMPTYDAY:
				daysLayout = new GridLayout(24,1);
				this.setLayout(daysLayout);
				break;
			default:
				daysLayout = new GridLayout(25,1);
				this.setLayout(daysLayout);
				JLabel unLabel = new JLabel(weekDayNames.toString());
				unLabel.setHorizontalAlignment(SwingConstants.CENTER);
				unLabel.setBorder(BorderFactory.createLineBorder(Color.black));
				this.add(unLabel);
			}
			
			for (int hi = 0;hi<24;hi++) {
				JPanel hour = new JPanel();
				hour.add(new JLabel(new Integer(hi).toString()));
				this.add(hour);
			}
			break;
		case MONTH_VIEW:
			JPanel Day = new JPanel();
			if (weekDayNames.equals(WeekDayNames.EMPTYDAY)){
				Day.add(new JLabel(weekDayNames.toStringL()));
			}else {
				Day.add(new JLabel(Session.instance().getString(weekDayNames.toStringL().toLowerCase())));
			}
			
			this.add(Day);
			break;
		}
	}
}


