package xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import presentation.Classroom;
import presentation.Person;
import presentation.Person.PersonFunction;
import presentation.Presentation;
import exception.ApplyException;
import frame.MainFrame;

public class XMLFileLoader {
	
	/**Load the information in a xml file selected by the user and return an ArrayList of Presentation Object from this file.
	 * @return ArrayList<Presentation>
	 * @throws Exception
	 */
	public static ArrayList<Presentation> LoadXMLFile() throws Exception {
		
		ArrayList<Presentation> presentations = new ArrayList<Presentation>();
		try{
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse (new File(new XMLFileLoader().chooseFile()));
			
			doc.getDocumentElement().normalize();
			
			NodeList listOfPresentation = doc.getElementsByTagName("presentation");
            for (int event_i = 0 ; event_i < listOfPresentation.getLength() ; event_i++) {
            	
            	Classroom c1 = new Classroom();
            	ArrayList<presentation.Document> docPres = new ArrayList<presentation.Document>();
            	Person student = new Person();
            	ArrayList<Person> jury = new ArrayList<Person>();
            	Date date = new Date();
            	
            	NodeList juryEl;
            	
            	Element presentation = (Element) listOfPresentation.item(event_i);
            	String attr1 = presentation.getAttribute("date");
            	String attr2 = presentation.getAttribute("duration");
            	
            	date = XMLFileSave.convertStringToDate(attr1+attr2);
            	
            	NodeList studentList = presentation.getElementsByTagName("Student");
            	Element stu = (Element) studentList.item(0);
            	String firstname = stu.getAttribute("Firstname");
            	String lastName = stu.getAttribute("Lastname");
            	String email = stu.getAttribute("Email");
            	String phone = stu.getAttribute("Phone");
            	student = new Person(PersonFunction.STUDENT, firstname, lastName, email, phone);
            	
            	NodeList juryList = presentation.getElementsByTagName("Jury_Members");
            	for (int i = 0 ; i < juryList.getLength(); i++) {
            		Element juryElement = (Element) juryList.item(i);
            		firstname = juryElement.getAttribute("Firstname");
                	lastName = juryElement.getAttribute("Lastname");
                	email = juryElement.getAttribute("Email");
                	phone = juryElement.getAttribute("Phone");
                	jury.add(new Person(PersonFunction.JURY, firstname, lastName, email, phone));
            	}
            	
            	NodeList classList = presentation.getElementsByTagName("Room");
            	Element e = (Element) classList.item(0);
            	String classroomnumber =  e.getAttribute("Number");
            	c1.setClassroomNumber(classroomnumber);
            	
            	String docName = "";
            	NodeList docList = presentation.getElementsByTagName("Documents");
            	for (int i = 0 ; i < docList.getLength(); i++) {
            		Element docElement = (Element) docList.item(i);
            		docName = docElement.getAttribute("Name");
                	docPres.add(new presentation.Document(docName));
            	}
            	presentations.add(new Presentation(date, student, jury, c1, docPres));
            }
			
		}catch(Exception e){
			System.err.println("Error while loading the XML file");
			new ApplyException(e.toString());
		}
		
		return presentations;
	}
	
	/**This method demand to the user to select a xml file, by using a JFileChooser Object
	 * The selected xml file must contain the correct informations in order to be used
	 * @return
	 */
	private String chooseFile(){
		
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "XML files", "xml");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(MainFrame.instance());
	    if(returnVal == JFileChooser.APPROVE_OPTION) return chooser.getSelectedFile().getAbsolutePath();
	    else return null;
		
	}

}
