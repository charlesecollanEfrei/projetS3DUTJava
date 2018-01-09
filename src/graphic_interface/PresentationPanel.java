package graphic_interface;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.text.MaskFormatter;

import exception.ApplyException;
import frame.MainFrame;
import presentation.Classroom;
import presentation.Document;
import presentation.Person;
import presentation.Person.PersonFunction;
import presentation.Presentation;
import session.Session;

public class PresentationPanel extends JPanel implements ActionListener {
	
	private JTabbedPane tabbedPane;
	private JPanel pnlAdd;
	private JPanel pnlMod;
	private JPanel pnlDel;
	private ArrayList<Presentation> presentations;
	
	//Attributs de l onglet ajouter
	private JSpinner dateSpinner;
	private JTextField firstnamestudent;
	private JTextField lastnamestudent;
	private JTextField mailstudent;
	private JFormattedTextField phonestudent;
	private JTextField classroom;
	
	private ArrayList<Person> jury;
	private JTextField firstnamejury;
	private JTextField lastnamejury;
	private JTextField mailjury;
	private JFormattedTextField phonejury;
	private JLabel lblNbJuryCount;
	private JButton btnAjoutJury;
	
	private JButton btnAjoutDoc;
	private String[] docs;
	private ArrayList<Document> doc;
	private JComboBox<String> docjtf;
	
	private JButton btnAjout;
	private JButton btnErase;
	
	//Attributs de l'onglet modifier
	private int indexOfMod;
	private ArrayList<JButton> selectionMod;
	private JButton btnModify;
	
	private JSpinner dateSpinnerMod;
	private JTextField firstnamestudentMod;
	private JTextField lastnamestudentMod;
	private JTextField mailstudentMod;
	private JFormattedTextField phonestudentMod;
	private JTextField classroomMod;
	
	private ArrayList<Person> juryMod;
	private JTextField firstnamejuryMod;
	private JTextField lastnamejuryMod;
	private JTextField mailjuryMod;
	private JFormattedTextField phonejuryMod;
	private int indexOfModJury;
	
	private JButton btnAjoutDocMod;
	private String[] docsMod;
	private ArrayList<Document> docMod;
	private JComboBox<String> docjtfMod;
	private JPanel pnlJury;
	
	private JButton btnEraseMod;
	
	//Attributs de l onglet supprimer
	private ArrayList<JButton> selectionSup;
	
	public PresentationPanel(ArrayList<Presentation> presentations){
		
		dateSpinnerMod = new JSpinner();
		firstnamestudentMod = new JTextField();
		lastnamestudentMod = new JTextField();
		mailstudentMod = new JTextField();
		phonestudentMod = new JFormattedTextField();
		classroomMod = new JTextField();
		
		juryMod = new ArrayList<Person>();
		firstnamejuryMod = new JTextField();
		lastnamejuryMod = new JTextField();
		mailjuryMod = new JTextField();
		phonejuryMod = new JFormattedTextField();
		
		btnAjoutDocMod = new JButton();
		docMod = new ArrayList<Document>();
		docjtfMod = new JComboBox<String>();
		pnlJury = new JPanel();
		
		this.tabbedPane = new JTabbedPane();
		this.presentations = presentations;
		this.jury = new ArrayList<Person>();
		this.doc = new ArrayList<Document>();
		
		this.selectionMod = new ArrayList<JButton>();
		this.selectionSup = new ArrayList<JButton>();
		//ImageIcon icon = createImageIcon("image path");

		this.pnlAdd = setAddPanel();
		this.tabbedPane.addTab(Session.instance().getString("add"), null, pnlAdd, Session.instance().getString("addmessage"));
		this.tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		pnlMod = setModifyPanel();
		this.tabbedPane.addTab(Session.instance().getString("modify"), null, pnlMod, Session.instance().getString("modifymessage"));
		this.tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		pnlDel = setDeletePanel();
		this.tabbedPane.addTab(Session.instance().getString("delete"), null, pnlDel, Session.instance().getString("deletemessage"));
		this.tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		
		this.add(this.tabbedPane);
		
	}
	
	private JPanel makePanel() {
	    JPanel panel = new JPanel(false);
	    panel.setLayout(new GridLayout(1, 1));
	    return panel;
	}
	
	private JPanel setAddPanel() {
		JPanel panel = makePanel();
		panel.setLayout(new GridLayout(11,1));
		
		//Premiere ligne : la date
		JPanel pnlDate = new JPanel();
		pnlDate.setLayout(new FlowLayout());
			JLabel lblDate = new JLabel("Date : ", JLabel.CENTER);
		pnlDate.add(lblDate);
		
			dateSpinner = new JSpinner();
			dateSpinner.setModel(new SpinnerDateModel());
			dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy hh:mm:ss"));

			pnlDate.add(dateSpinner);
		
		//Seconde ligne : l eleve
		JLabel lblEleve = new JLabel(Session.instance().getString("student"), JLabel.CENTER);
		JPanel pnlEleve = new JPanel();
			pnlEleve.setLayout(new GridLayout(2,4));
				JPanel pnlNom = new JPanel();
				pnlNom.setLayout(new GridLayout());
				pnlNom.add(new JLabel(Session.instance().getString("name") + " : ", JLabel.CENTER));
				this.firstnamestudent = new JTextField();
				pnlNom.add(firstnamestudent);
			pnlEleve.add(pnlNom);
				JPanel pnlPrenom = new JPanel();
				pnlPrenom.setLayout(new GridLayout());
				pnlPrenom.add(new JLabel(Session.instance().getString("lname") + " : ", JLabel.CENTER));
				this.lastnamestudent = new JTextField();
				pnlPrenom.add(lastnamestudent);
			pnlEleve.add(pnlPrenom);
				JPanel pnlMail = new JPanel();
				pnlMail.setLayout(new GridLayout());
				pnlMail.add(new JLabel(Session.instance().getString("mail") + " : ", JLabel.CENTER));
				this.mailstudent = new JTextField();
				pnlMail.add(mailstudent);
			pnlEleve.add(pnlMail);
				JPanel pnlPhone = new JPanel();
				pnlPhone.setLayout(new GridLayout());
				pnlPhone.add(new JLabel(Session.instance().getString("phone") + " : ", JLabel.CENTER));
				try{
					  MaskFormatter tel2 = new MaskFormatter("##-##-##-##-##");
					  phonestudent = new JFormattedTextField(tel2);
				}catch(ParseException e){new ApplyException(e.toString());}
				pnlPhone.add(phonestudent);
			pnlEleve.add(pnlPhone);
			
		//Troisieme ligne : la salle de classe et les documents
		JLabel lblClassroom = new JLabel(Session.instance().getString("classdoc") + " : ", JLabel.CENTER);
		JPanel pnlClassroom = new JPanel();
		pnlClassroom.setLayout(new GridLayout(2, 3));
			JPanel pnlClassName = new JPanel();
			pnlClassName.setLayout(new GridLayout(1, 2));
			pnlClassName.add(new JLabel(Session.instance().getString("classname") + " : ", JLabel.CENTER));
			this.classroom = new JTextField();
			pnlClassName.add(classroom);
			JPanel pnldoc = new JPanel();
			pnldoc.setLayout(new FlowLayout());
			pnldoc.add(new JLabel(Session.instance().getString("docname") + " : ", JLabel.CENTER));
				docs = new String[5];
				docs[0]=Session.instance().getString("doc1"); docs[1]=Session.instance().getString("doc2"); docs[2]=Session.instance().getString("doc3");
				docs[3]=Session.instance().getString("doc4"); docs[4]=Session.instance().getString("doc5");
				this.docjtf = new JComboBox<String>(docs);
			pnldoc.add(docjtf);
				btnAjoutDoc = new JButton(Session.instance().getString("adddoc"));
				btnAjoutDoc.addActionListener(this);
			pnldoc.add(btnAjoutDoc);
		pnlClassroom.add(pnldoc);
		pnlClassroom.add(pnlClassName);
		
		//Quatrieme ligne : le jury
		JLabel lblJury = new JLabel(Session.instance().getString("jury"), JLabel.CENTER);
		JPanel pnlJury = new JPanel();
			pnlJury.setLayout(new GridLayout(3,1));
				JPanel pnlNomJury = new JPanel();
				pnlNomJury.setLayout(new GridLayout());
				pnlNomJury.add(new JLabel(Session.instance().getString("name") + " : ", JLabel.CENTER));
				this.firstnamejury = new JTextField();
				pnlNomJury.add(firstnamejury);
			pnlJury.add(pnlNomJury);
				JPanel pnlPrenomJury = new JPanel();
				pnlPrenomJury.setLayout(new GridLayout());
				pnlPrenomJury.add(new JLabel(Session.instance().getString("lname") + ": ", JLabel.CENTER));
				this.lastnamejury = new JTextField();
				pnlPrenomJury.add(lastnamejury);
			pnlJury.add(pnlPrenomJury);
				JPanel pnlMailJury = new JPanel();
				pnlMailJury.setLayout(new GridLayout());
				pnlMailJury.add(new JLabel(Session.instance().getString("mail") + " : ", JLabel.CENTER));
				this.mailjury = new JTextField();
				pnlMailJury.add(mailjury);
			pnlJury.add(pnlMailJury);
				JPanel pnlPhoneJury = new JPanel();
				pnlPhoneJury.setLayout(new GridLayout());
				pnlPhoneJury.add(new JLabel(Session.instance().getString("phone") + " : ", JLabel.CENTER));
				try{
					  MaskFormatter tel = new MaskFormatter("##-##-##-##-##");
					  phonejury = new JFormattedTextField(tel);
				}catch(ParseException e){new ApplyException(e.toString());}
				pnlPhoneJury.add(phonejury);
			pnlJury.add(pnlPhoneJury);
				JPanel pnlLbl = new JPanel();
				pnlLbl.setLayout(new GridLayout(1, 2));
				JLabel lblNbJury = new JLabel(Session.instance().getString("memberjury"), JLabel.CENTER);
				lblNbJuryCount = new JLabel(String.valueOf(jury.size()), JLabel.CENTER);
				pnlLbl.add(lblNbJury);
				pnlLbl.add(lblNbJuryCount);
			pnlJury.add(pnlLbl);
				this.btnAjoutJury = new JButton(Session.instance().getString("addmember"));
				btnAjoutJury.addActionListener(this);
			pnlJury.add(btnAjoutJury);
			
		
		//Derniere ligne : bouton ajouter et effacer
		JPanel pnlbtn1 = new JPanel();
		pnlbtn1.setLayout(new FlowLayout());
		this.btnAjout = new JButton(Session.instance().getString("add"));
		this.btnErase = new JButton(Session.instance().getString("erase"));
		pnlbtn1.add(btnAjout);
		pnlbtn1.add(btnErase);
		this.btnAjout.addActionListener(this);
		this.btnErase.addActionListener(this);
		
		
		//On ajoute tout les panels
		
		panel.add(lblJury);
		panel.add(pnlJury);
		
		panel.add(lblEleve);
		panel.add(pnlEleve);
		
		panel.add(lblClassroom);
		panel.add(pnlClassroom);
		
		panel.add(pnlDate);
		
		panel.add(pnlbtn1);
		
		return panel;
	}
	
	private JPanel setModifyPanel() {
		
		this.selectionMod = new ArrayList<JButton>();
		
		JPanel panel = makePanel();
		JLabel lblSout;
		JButton btnSelect;
		JPanel pnlSout = new JPanel();
		JPanel pnlBtn = new JPanel();
		
		panel.setLayout(new GridLayout(presentations.size(), 1));
		pnlSout.setLayout(new GridLayout(presentations.size(), 2));
		pnlBtn.setLayout(new FlowLayout());
		
		int i = 1;
		for(Presentation element : this.presentations) {
			
			lblSout = new JLabel(Session.instance().getString("presof") + " " + element.getStudent().getFirstname()  + " " + element.getStudent().getLastname(), JLabel.CENTER);
			btnSelect = new JButton(Session.instance().getString("modpres") + i);
			btnSelect.addActionListener(this);
			selectionMod.add(btnSelect);
			pnlSout.add(lblSout);
			pnlSout.add(selectionMod.get(i-1));
			i++;
			
		}
		panel.add(pnlSout);
		
		return panel;
	}
	
	private JPanel setDeletePanel() {
		
		this.selectionSup = new ArrayList<JButton>();
		
		JPanel panel = makePanel();
		JLabel lblSout;
		JButton btnSup;
		JPanel pnlSout = new JPanel();
		pnlSout.setLayout(new FlowLayout());
		panel.setLayout(new GridLayout(presentations.size(), 1));
		pnlSout.setLayout(new GridLayout(presentations.size(), 2));
		int i = 1;
		for(Presentation element : presentations) {
			
			lblSout = new JLabel(Session.instance().getString("presof") + " " + element.getStudent().getFirstname()  + " " + element.getStudent().getLastname(), JLabel.CENTER);
			btnSup = new JButton(Session.instance().getString("delpres")  + i);
			btnSup.addActionListener(this);
			selectionSup.add(btnSup);
			pnlSout.add(lblSout);
			pnlSout.add(selectionSup.get(i-1));
			i++;
			
		}
		panel.add(pnlSout);
		
		return panel;
	}

	
	
	private void updateTab() {
		
		this.remove(this.tabbedPane);
		this.tabbedPane = new JTabbedPane();
		
		this.tabbedPane.addTab(Session.instance().getString("add"), null, pnlAdd, Session.instance().getString("addmessage"));

		pnlMod = setModifyPanel();
		this.tabbedPane.addTab(Session.instance().getString("modify"), null, pnlMod, Session.instance().getString("modifymessage"));
		this.tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		

		pnlDel = setDeletePanel();
		this.tabbedPane.addTab(Session.instance().getString("delete"), null, pnlDel, Session.instance().getString("deletemessage"));
		this.tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		this.tabbedPane.revalidate();
		this.tabbedPane.repaint();
		
		this.add(this.tabbedPane);
		this.revalidate();
		this.repaint();
	}
	
	private void modifyPresentation () {
		
		this.juryMod = new ArrayList<Person>(presentations.get(indexOfMod).getJury());
		this.docMod = new ArrayList<Document>(presentations.get(indexOfMod).getDocuments());
		Presentation p1 = this.presentations.get(indexOfMod);
		JPanel panel = makePanel();
		panel.setLayout(new GridLayout(11,1));
		
		//Premiere ligne : la date
		JPanel pnlDate = new JPanel();
		pnlDate.setLayout(new FlowLayout());
			JLabel lblDate = new JLabel("Date : ", JLabel.CENTER);
		pnlDate.add(lblDate);
		
			dateSpinnerMod = new JSpinner();
			dateSpinnerMod.setModel(new SpinnerDateModel());
			dateSpinnerMod.setEditor(new JSpinner.DateEditor(dateSpinnerMod, "dd/MM/yyyy hh:mm:ss"));
			dateSpinnerMod.setValue(p1.getDate());
			pnlDate.add(dateSpinnerMod);
		
		//Seconde ligne : l eleve
		JLabel lblEleve = new JLabel(Session.instance().getString("student"), JLabel.CENTER);
		JPanel pnlEleve = new JPanel();
			pnlEleve.setLayout(new GridLayout(2,4));
				JPanel pnlNom = new JPanel();
				pnlNom.setLayout(new GridLayout());
				pnlNom.add(new JLabel(Session.instance().getString("name") + " : ", JLabel.CENTER));
				this.firstnamestudentMod = new JTextField(p1.getStudent().getFirstname());
				pnlNom.add(firstnamestudentMod);
			pnlEleve.add(pnlNom);
				JPanel pnlPrenom = new JPanel();
				pnlPrenom.setLayout(new GridLayout());
				pnlPrenom.add(new JLabel(Session.instance().getString("lname") + " : ", JLabel.CENTER));
				this.lastnamestudentMod = new JTextField(p1.getStudent().getLastname());
				pnlPrenom.add(lastnamestudentMod);
			pnlEleve.add(pnlPrenom);
				JPanel pnlMail = new JPanel();
				pnlMail.setLayout(new GridLayout());
				pnlMail.add(new JLabel(Session.instance().getString("mail") + " : ", JLabel.CENTER));
				this.mailstudentMod = new JTextField(p1.getStudent().getMail());
				pnlMail.add(mailstudentMod);
			pnlEleve.add(pnlMail);
				JPanel pnlPhone = new JPanel();
				pnlPhone.setLayout(new GridLayout());
				pnlPhone.add(new JLabel(Session.instance().getString("phone") + " : ", JLabel.CENTER));
				try{
					  MaskFormatter tel2 = new MaskFormatter("##-##-##-##-##");
					  phonestudentMod = new JFormattedTextField(tel2);
					  phonestudentMod.setText(p1.getStudent().getTel());
				}catch(ParseException e){new ApplyException(e.toString());}
				pnlPhone.add(phonestudentMod);
			pnlEleve.add(pnlPhone);
			
		//Troisieme ligne : la salle de classe et les documents
		JLabel lblClassroom = new JLabel(Session.instance().getString("classdoc") + " : ", JLabel.CENTER);
		JPanel pnlClassroom = new JPanel();
		pnlClassroom.setLayout(new GridLayout(2, 3));
			JPanel pnlClassName = new JPanel();
			pnlClassName.setLayout(new GridLayout(1, 2));
			pnlClassName.add(new JLabel(Session.instance().getString("classname") + " : ", JLabel.CENTER));
			this.classroomMod = new JTextField(p1.getClassroom().getClassRoomNumber());
			pnlClassName.add(classroomMod);
			JPanel pnldoc = new JPanel();
			pnldoc.setLayout(new FlowLayout());
			pnldoc.add(new JLabel(Session.instance().getString("docname") + " : ", JLabel.CENTER));
				docsMod = new String[5];
				docsMod[0]=Session.instance().getString("doc1"); docsMod[1]=Session.instance().getString("doc2"); docsMod[2]=Session.instance().getString("doc3");
				docsMod[3]=Session.instance().getString("doc4"); docsMod[4]=Session.instance().getString("doc5");
				this.docjtfMod = new JComboBox<String>(docsMod);
			pnldoc.add(docjtfMod);
				btnAjoutDocMod = new JButton(Session.instance().getString("adddoc"));
				btnAjoutDocMod.addActionListener(this);
			pnldoc.add(btnAjoutDocMod);
		pnlClassroom.add(pnldoc);
		pnlClassroom.add(pnlClassName);
		
		//Premiere ligne : le jury
		JLabel lblJury = new JLabel(Session.instance().getString("jury"), JLabel.CENTER);
		this.pnlJury.removeAll();
		this.pnlJury = new JPanel();
			pnlJury.setLayout(new GridLayout(p1.getJury().size()+1,1));
			int i = 0;
			this.juryMod = p1.getJury();
			JPanel pnlJuryMember = new JPanel();
			pnlJuryMember.setLayout(new GridLayout(this.juryMod.size(), 2));
			for (Person juryMember : this.juryMod) {
				JLabel lblNomJury = new JLabel(juryMember.getFirstname() + "  " + juryMember.getLastname(), JLabel.CENTER);
				JButton btnSelectJury = new JButton(Session.instance().getString("selectjurymember") + " " + (i+1));
				btnSelectJury.addActionListener(this);
				i++;
				pnlJuryMember.add(lblNomJury);
				pnlJuryMember.add(btnSelectJury);
			}
			pnlJury.add(pnlJuryMember);
			JPanel pnlTemp = new JPanel();
			pnlTemp.setLayout(new FlowLayout());
			JButton btnAddMember = new JButton(Session.instance().getString("addmember "));
			btnAddMember.addActionListener(this);
			pnlTemp.add(btnAddMember);
			pnlJury.add(pnlTemp);
				
			
		//Derniere ligne : bouton ajouter et effacer
		JPanel pnlbtn1 = new JPanel();
		pnlbtn1.setLayout(new FlowLayout());
		this.btnModify = new JButton(Session.instance().getString("modify"));
		this.btnEraseMod = new JButton(Session.instance().getString("erase") + " ");
		pnlbtn1.add(btnModify);
		pnlbtn1.add(btnEraseMod);
		this.btnModify.addActionListener(this);
		this.btnEraseMod.addActionListener(this);
			
			
		//On ajoute tout les panels
			
		panel.add(lblJury);
		panel.add(pnlJury);
			
		panel.add(lblEleve);
		panel.add(pnlEleve);
			
		panel.add(lblClassroom);
		panel.add(pnlClassroom);
			
		panel.add(pnlDate);
			
		panel.add(pnlbtn1);
			
		pnlMod = panel;
		pnlMod.revalidate();
		pnlMod.repaint();
			
		this.remove(this.tabbedPane);
		this.tabbedPane = new JTabbedPane();
		
		this.tabbedPane.addTab(Session.instance().getString("add"), null, pnlAdd, Session.instance().getString("addmessage"));

		this.tabbedPane.addTab(Session.instance().getString("modify"), null, pnlMod, Session.instance().getString("modifymessage"));
		this.tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		pnlDel = setDeletePanel();
		this.tabbedPane.addTab(Session.instance().getString("delete"), null, pnlDel, Session.instance().getString("deletemessage"));
		this.tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		this.tabbedPane.revalidate();
		this.tabbedPane.repaint();
		
		this.add(this.tabbedPane);
		this.revalidate();
		this.repaint();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String btnPresse = ((String) arg0.getActionCommand().toLowerCase());
		switch (btnPresse){
		
		case "add document" :
		case "ajouter document" :
			doc.add(new Document((String) docjtf.getSelectedItem()));
			docjtf.removeItem(docjtf.getSelectedItem());
			break;
		case "add member" :
		case "ajouter un membre" :
			if (!(firstnamejury.getText() == null || lastnamejury.getText() == null || mailjury.getText() == null || 
													phonejury.getText() == null || jury.size() >= 4 || firstnamejury.getText().equals("") || 
													lastnamejury.getText().equals("") || mailjury.getText().equals("") || 
													phonejury.getText().equals(""))){
				Person person = new Person(PersonFunction.JURY, firstnamejury.getText(), lastnamejury.getText(), mailjury.getText(), phonejury.getText());
				jury.add(person);
				lblNbJuryCount.setText(String.valueOf(jury.size()));
				firstnamejury.setText("");
				lastnamejury.setText("");
				mailjury.setText("");
				phonejury.setText("");
			}
			break;
		case "add member " :
		case "ajouter un membre " :
			this.pnlJury.removeAll();
			pnlJury.setLayout(new GridLayout(3,1));
					JPanel pnlNomJury2 = new JPanel();
					pnlNomJury2.setLayout(new GridLayout());
					pnlNomJury2.add(new JLabel(Session.instance().getString("name") + " : ", JLabel.CENTER));
					this.firstnamejuryMod = new JTextField();
					pnlNomJury2.add(firstnamejuryMod);
				pnlJury.add(pnlNomJury2);
					JPanel pnlPrenomJury2 = new JPanel();
					pnlPrenomJury2.setLayout(new GridLayout());
					pnlPrenomJury2.add(new JLabel(Session.instance().getString("lname") + ": ", JLabel.CENTER));
					this.lastnamejuryMod = new JTextField();
					pnlPrenomJury2.add(lastnamejuryMod);
				pnlJury.add(pnlPrenomJury2);
					JPanel pnlMailJury2 = new JPanel();
					pnlMailJury2.setLayout(new GridLayout());
					pnlMailJury2.add(new JLabel(Session.instance().getString("mail") + " : ", JLabel.CENTER));
					this.mailjuryMod = new JTextField();
					pnlMailJury2.add(mailjuryMod);
				pnlJury.add(pnlMailJury2);
					JPanel pnlPhoneJury2 = new JPanel();
					pnlPhoneJury2.setLayout(new GridLayout());
					pnlPhoneJury2.add(new JLabel(Session.instance().getString("phone") + " : ", JLabel.CENTER));
					try{
						  MaskFormatter tel = new MaskFormatter("##-##-##-##-##");
						  phonejuryMod = new JFormattedTextField(tel);
					}catch(ParseException e){new ApplyException(e.toString());}
					pnlPhoneJury2.add(phonejuryMod);
				pnlJury.add(pnlPhoneJury2);
				JButton btnAddJury = new JButton(Session.instance().getString("add "));
				btnAddJury.addActionListener(this);
				pnlJury.add(btnAddJury);
			
			this.pnlJury.revalidate();
			this.pnlJury.repaint();
			this.pnlMod.revalidate();
			this.pnlMod.repaint();
			break;
		case "add ":
		case "ajouter ":
			presentations.get(indexOfMod).addJury(new Person(PersonFunction.JURY, firstnamejuryMod.getText(), lastnamejuryMod.getText(), mailjuryMod.getText(), phonejuryMod.getText()));
			this.modifyPresentation();
			this.tabbedPane.setSelectedIndex(1);
			break;
		case "add" :
		case "ajouter" :
			if (! (jury.size() < 2 || jury.size() > 4 || doc.size()== 0 || firstnamestudent == null || firstnamestudent.getText().equals("") ||
			lastnamestudent == null || lastnamestudent.getText().equals("") || mailstudent == null || mailstudent.getText().equals("") || 
			phonestudent == null || phonestudent.getText().equals("") || classroom == null || classroom.getText().equals("") )){
				Date date = (Date)dateSpinner.getValue();
				Person student = new Person(PersonFunction.STUDENT, firstnamestudent.getText(), lastnamestudent.getText(), mailstudent.getText(), phonestudent.getText());
				ArrayList<Person> jurytemp = new ArrayList<Person>(jury);
				ArrayList<Document> doctemp = new ArrayList<Document>(doc);
				Presentation p1 = new Presentation(date, student, jurytemp, new Classroom(classroom.getText()), doctemp);
				MainFrame.instance().addPresentation(p1);
				this.updateTab();
			}else break;
		case "erase" :
		case "effacer" :
			firstnamejury.setText("");
			lastnamejury.setText("");
			mailjury.setText("");
			phonejury.setText("");
			firstnamestudent.setText("");
			lastnamestudent.setText("");
			mailstudent.setText("");
			phonestudent.setText("");
			classroom.setText("");
			docjtf.removeAllItems();
			docjtf.addItem(docs[0]);
			docjtf.addItem(docs[1]);
			docjtf.addItem(docs[2]);
			docjtf.addItem(docs[3]);
			docjtf.addItem(docs[4]);
			doc.removeAll(doc);
			jury.removeAll(jury);
			lblNbJuryCount.setText(String.valueOf(jury.size()));
			break;
		case "modify" :
		case "modifier" :
			if (! (juryMod.size() < 2 || juryMod.size() > 4 || docMod.size()== 0 || firstnamestudentMod == null || firstnamestudentMod.getText().equals("") ||
			lastnamestudentMod == null || lastnamestudentMod.getText().equals("") || mailstudentMod == null || mailstudentMod.getText().equals("") || 
			phonestudentMod == null || phonestudentMod.getText().equals("") || classroomMod == null || classroomMod.getText().equals("") )){
				Date date = (Date)dateSpinnerMod.getValue();
				Person student = new Person(PersonFunction.STUDENT, firstnamestudentMod.getText(), lastnamestudentMod.getText(), mailstudentMod.getText(), phonestudentMod.getText());
				Presentation p1 = new Presentation(date, student, this.juryMod, new Classroom(classroomMod.getText()), docMod);
				MainFrame.instance().changePresentation(p1, this.indexOfMod);
				this.updateTab();
			}else break;
			break;
		case "erase " :
		case "effacer " :
			firstnamejuryMod.setText("");
			lastnamejuryMod.setText("");
			mailjuryMod.setText("");
			phonejuryMod.setText("");
			firstnamestudentMod.setText("");
			lastnamestudentMod.setText("");
			mailstudentMod.setText("");
			phonestudentMod.setText("");
			classroomMod.setText("");
			docjtfMod.removeAllItems();
			docjtfMod.addItem(docsMod[0]);
			docjtfMod.addItem(docsMod[1]);
			docjtfMod.addItem(docsMod[2]);
			docjtfMod.addItem(docsMod[3]);
			docjtfMod.addItem(docsMod[4]);
			docMod.removeAll(docMod);
			juryMod.removeAll(juryMod);
			break;
		case "modify jury member" :
		case "modifier le membre du jury" :
			presentations.get(indexOfMod).changeOnePersonInJury(new Person(PersonFunction.JURY, firstnamejuryMod.getText(), lastnamejuryMod.getText(), mailjuryMod.getText(), phonejuryMod.getText()), juryMod.get(indexOfModJury));
			this.modifyPresentation();
			break;
		default : 
			String recup = btnPresse.substring(0, btnPresse.length()-1);
			int recup2 = Integer.parseInt(btnPresse.substring(btnPresse.length()-1, btnPresse.length())) - 1;
			switch (recup) {
				case "modify presentation n°" :
				case "modifier soutenance n°" :
					indexOfMod= recup2;
					this.modifyPresentation();
					this.tabbedPane.setSelectedIndex(1);
					break;
				case "delete presentation n°" :
				case "supprimer soutenance n°" :
					int response = JOptionPane.showConfirmDialog(null, 
							Session.instance().getString("delChoice"), "Information",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if (response != JOptionPane.CANCEL_OPTION) {
						if (response == JOptionPane.YES_OPTION){
							presentations.remove(recup2);
							this.updateTab();
							String suppr = Session.instance().getString("messageQuit");
							String messageSuppr = Session.instance().getString("quit");
						    JOptionPane.showMessageDialog(null,suppr,messageSuppr, JOptionPane.INFORMATION_MESSAGE);
						
						}
					}
					break;
				case "select " :
				case "selectionner " :
					Person member = juryMod.get(recup2);
					this.pnlJury.removeAll();
					pnlJury.setLayout(new GridLayout(3,1));
							JPanel pnlNomJury = new JPanel();
							pnlNomJury.setLayout(new GridLayout());
							pnlNomJury.add(new JLabel(Session.instance().getString("name") + " : ", JLabel.CENTER));
							this.firstnamejuryMod = new JTextField(member.getFirstname());
							pnlNomJury.add(firstnamejuryMod);
						pnlJury.add(pnlNomJury);
							JPanel pnlPrenomJury = new JPanel();
							pnlPrenomJury.setLayout(new GridLayout());
							pnlPrenomJury.add(new JLabel(Session.instance().getString("lname") + ": ", JLabel.CENTER));
							this.lastnamejuryMod = new JTextField(member.getLastname());
							pnlPrenomJury.add(lastnamejuryMod);
						pnlJury.add(pnlPrenomJury);
							JPanel pnlMailJury = new JPanel();
							pnlMailJury.setLayout(new GridLayout());
							pnlMailJury.add(new JLabel(Session.instance().getString("mail") + " : ", JLabel.CENTER));
							this.mailjuryMod = new JTextField(member.getMail());
							pnlMailJury.add(mailjuryMod);
						pnlJury.add(pnlMailJury);
							JPanel pnlPhoneJury = new JPanel();
							pnlPhoneJury.setLayout(new GridLayout());
							pnlPhoneJury.add(new JLabel(Session.instance().getString("phone") + " : ", JLabel.CENTER));
							try{
								  MaskFormatter tel = new MaskFormatter("##-##-##-##-##");
								  phonejuryMod = new JFormattedTextField(tel);
								  phonejuryMod.setText(member.getTel());
							}catch(ParseException e){new ApplyException(e.toString());}
							pnlPhoneJury.add(phonejuryMod);
						pnlJury.add(pnlPhoneJury);
						JButton btnModifyJury = new JButton(Session.instance().getString("modjurymember"));
						btnModifyJury.addActionListener(this);
						pnlJury.add(btnModifyJury);
					
					this.pnlJury.revalidate();
					this.pnlJury.repaint();
					
					break;
				default: break;
			}
		
		}
			
	}

}