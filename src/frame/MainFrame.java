package frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import exception.ApplyException;
import presentation.Presentation;
import session.Planning;
import session.Session;
import xml.XMLFileLoader;
import xml.XMLFileSave;
import graphic_interface.CurrentView;
import graphic_interface.CurrentView.ActiveView;
import graphic_interface.Month;
import graphic_interface.PresentationPanel;

public class MainFrame extends JFrame {
	
	private static MainFrame frame;
	
	private Planning planning;
	
	private JPanel mainPane;
	private JPanel dayView;
	private JPanel weekView;
	private JPanel monthView;
	private JPanel disponibility;
	private JPanel menuPane;
	private CardLayout mainPaneLayout;
	private CurrentView cv;
	
	/**Initialise the main elements of the frame like the panel containing the month view, the day view and the week view
	 * And the panel containing the menu and the date selector
	 * This method also initialise the three view of the first panel
	 */
	public void init() {
		
		this.mainPane = new JPanel();
		this.menuPane = new JPanel();
		this.mainPaneLayout = new CardLayout();
		this.mainPane.setLayout(mainPaneLayout);
		
		this.dayView = cv.getView(ActiveView.DAY_VIEW);
		this.weekView = cv.getView(ActiveView.WEEK_VIEW);
		this.monthView = cv.getView(ActiveView.MONTH_VIEW);
	}
	
	/**
	 * setup the element of the frame, like the three view, the menu and the date selector
	 */
	public void setupUI() {
		
		this.mainPane.add(this.dayView,ActiveView.DAY_VIEW.name());
		this.mainPane.add(this.weekView,ActiveView.WEEK_VIEW.name());
		this.mainPane.add(this.monthView,ActiveView.MONTH_VIEW.name());

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,menuPane, mainPane);
		this.setContentPane(splitPane);

		JMenuBar menuBar = new JMenuBar();
		JMenu menu, menuSub;		
		JMenuItem menuItem;

		menu = new JMenu(Session.instance().getString("file"));
		menuBar.add(menu);
			menuItem = new JMenuItem(Session.instance().getString("load"));
			//A implémenter : charger un doc XML
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						MainFrame.instance().addPresentations(XMLFileLoader.LoadXMLFile());
					} catch (Exception e) {new ApplyException(e.toString());}
				}			
			});
			menu.add(menuItem);
			menuItem = new JMenuItem(Session.instance().getString("save"));
			//A implémenter : sauver l agenda actuel dans un doc XML
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						XMLFileSave.saveFile(MainFrame.instance().getPlanning());
					} catch (Exception e) {new ApplyException(e.toString());}
				}			
			});
			menu.add(menuItem);
			menuItem = new JMenuItem(Session.instance().getString("quit"));
			
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int response = JOptionPane.showConfirmDialog(null, 
							Session.instance().getString("quitChoice"), "Information",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if (response != JOptionPane.CANCEL_OPTION) {
						if (response == JOptionPane.YES_OPTION){
							//Sauvegarder dans un document XML
							try {
								XMLFileSave.saveFile(MainFrame.instance().getPlanning());
							} catch (Exception e) {new ApplyException(e.toString());}
							
							//Message d'attente
							String message = Session.instance().getString("messageQuit");
							String messageName = Session.instance().getString("quit");
						    JOptionPane.showMessageDialog(null,message,messageName, JOptionPane.INFORMATION_MESSAGE);
						
						}else{
							System.exit(0);
						}
					}
				}			
			});
			menu.add(menuItem);
		
		// Menu Edit
		menu = new JMenu(Session.instance().getString("edit"));
		menuBar.add(menu);
			menuSub = new JMenu(Session.instance().getString("view"));
			menu.add(menuSub);
				menuItem = new JMenuItem(Session.instance().getString("month"));
				menuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
					    mainPaneLayout.show(mainPane, ActiveView.MONTH_VIEW.name());		
					}			
				});
				menuSub.add(menuItem);
				menuItem = new JMenuItem(Session.instance().getString("week"));
				menuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
					    mainPaneLayout.show(mainPane, ActiveView.WEEK_VIEW.name());			
					}			
				});
				menuSub.add(menuItem);
				menuItem = new JMenuItem(Session.instance().getString("day"));
				menuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
					    mainPaneLayout.show(mainPane, ActiveView.DAY_VIEW.name());			
					}			
				});
				menuSub.add(menuItem);
	
		// Menu Help
		menu = new JMenu(Session.instance().getString("help"));
		menuBar.add(menu);
			menuItem = new JMenuItem(Session.instance().getString("display"));
			//A implémenter : implémenter documentation sur l'utilisation de l'agenda
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					//Message d'attente
					String message = Session.instance().getString("messageDisplay");
					String messageName = Session.instance().getString("display");
				    JOptionPane.showMessageDialog(null,message,messageName, JOptionPane.INFORMATION_MESSAGE);
				}			
			});
			menu.add(menuItem);
			
			menuItem = new JMenuItem(Session.instance().getString("about"));
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String message = Session.instance().getString("messageAbout");
					String messageName = Session.instance().getString("about");
				    JOptionPane.showMessageDialog(null,message,messageName, JOptionPane.INFORMATION_MESSAGE);
				}			
			});
			menu.add(menuItem);
			
			menuSub = new JMenu(Session.instance().getString("changeLanguage"));
			menu.add(menuSub);
				menuItem = new JMenuItem(Session.instance().getString("french"));
				menuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Session.instance().setLocale(Locale.FRENCH);
						init();
						setupUI();
						
					}			
				});
				menuSub.add(menuItem);
				menuItem = new JMenuItem(Session.instance().getString("english"));
				menuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Session.instance().setLocale(Locale.US);
						init();
						setupUI();
					}			
				});
				menuSub.add(menuItem);

		this.menuPane.setLayout(new BorderLayout());
		this.menuPane.add(menuBar, BorderLayout.NORTH);
		this.menuPane.add(new PresentationPanel(this.planning), BorderLayout.CENTER);
		this.menuPane.setSize(mainPane.getWidth()/3, menuPane.getHeight());
		this.pack();
		this.mainPaneLayout.next(this.mainPane);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	/**Constructor for the MainFrame Class
	 * This class is a singleton
	 * @param title
	 */
	public MainFrame(String title) {
		// TODO Auto-generated constructor stub
		super(title);
		addWindowListener (new WindowAdapter(){
			public void windowClosing (WindowEvent e){
				System.exit(0);
			}
		});
		this.planning = new Planning();
		this.cv = new CurrentView();
		
		this.init();
		this.setupUI();
		this.setVisible(true);
		
	}
	
	/**Return the unique instance of MainFrame
	 * @return MainFrame
	 */
	public static MainFrame instance(){
		if (frame == null){
			frame = new MainFrame("Agenda");
		}
		return frame;
	}
	
	public GregorianCalendar getCalendar(){
		return ((Month) this.monthView).getCalendar();
	}
	
	public ArrayList<Presentation> getPlanning(){
		return this.planning;
	}
	
	public void addPresentation(Presentation presentations){
		this.planning.add(presentations);
	}
	
	public void changePresentation(Presentation presentation, int index){
		this.planning.remove(index);
		this.planning.add(index, presentation);
	}
	
	public void addPresentations(ArrayList<Presentation> presentations){
		this.planning.addAll(presentations);
		init();
		setupUI();
	}

}
