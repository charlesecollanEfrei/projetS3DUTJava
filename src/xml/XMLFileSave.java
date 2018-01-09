package xml;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import presentation.Presentation;
import exception.ApplyException;
import frame.MainFrame;

public class XMLFileSave {

	/**Demand to the user to enter a name for the new .xml file and call the WriteWMLFile method to create it
	 * @param presentations
	 * @throws ParserConfigurationException
	 * @throws TransformerConfigurationException 
	 */
	public static void saveFile(ArrayList<Presentation> presentations) throws ParserConfigurationException, TransformerConfigurationException {

		String newName = JOptionPane.showInputDialog(MainFrame.instance(), "please enter a name for the new file", "Enter a name", JOptionPane.INFORMATION_MESSAGE);
		WriteXMLFile(presentations, newName);

	}

	/**This constructor create a new xml file with the information given by the parameter
	 * The parameter must be an ArrayList of presentations
	 * A presentations is caracterised by a student, a jury, documents, a date and a classroom
	 * The duration of all presentations is the same : 1 hour
	 * @param presentations, name
	 * @throws ParserConfigurationException
	 * @throws TransformerConfigurationException 
	 */
	private static void WriteXMLFile(ArrayList<Presentation> presentations, String name) throws ParserConfigurationException, TransformerConfigurationException {

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			Element root = document.createElement("agenda");

			for (int event_i = 0 ; event_i < presentations.size(); event_i++) {
				Element soutenance = document.createElement("presentation");
				soutenance.setAttribute("date", convertDateToString(presentations.get(event_i).getDate()));
				soutenance.setAttribute("duration", "01:00:00");

				Element eventStudent = document.createElement("Student");
				eventStudent.setAttribute("Firstname", presentations.get(event_i).getStudent().getFirstname());
				eventStudent.setAttribute("Lastname", presentations.get(event_i).getStudent().getLastname());
				eventStudent.setAttribute("Email", presentations.get(event_i).getStudent().getMail());
				eventStudent.setAttribute("Phone", presentations.get(event_i).getStudent().getTel());

				Element eventJury = document.createElement("Jury_Members");
				Element juryMember;
				for (int i = 0 ; i < presentations.get(event_i).getJury().size() ; i++) {
					
					juryMember = document.createElement("juryMember");
					juryMember.setAttribute("Firstname", presentations.get(event_i).getJury().get(i).getFirstname());
					juryMember.setAttribute("Lastname", presentations.get(event_i).getJury().get(i).getLastname());
					juryMember.setAttribute("Email", presentations.get(event_i).getJury().get(i).getMail());
					juryMember.setAttribute("Phone", presentations.get(event_i).getJury().get(i).getTel());
					eventJury.appendChild(juryMember);
				}

				Element eventRoom = document.createElement("Room");
				eventRoom.setAttribute("Number", presentations.get(event_i).getClassroom().getClassRoomNumber());

				Element eventDocument = document.createElement("Documents");
				Element docName;
				for (int j = 0 ; j < presentations.get(event_i).getDocuments().size() ; j++) {
					docName = document.createElement("document");
					docName.setAttribute("Name", presentations.get(event_i).getDocuments().get(j).getDocumentName());
					eventDocument.appendChild(docName);
				}

				soutenance.appendChild(eventStudent);
				soutenance.appendChild(eventJury);
				soutenance.appendChild(eventRoom);
				soutenance.appendChild(eventDocument);
				root.appendChild(soutenance);
			}
			document.appendChild(root);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult sortie = new StreamResult(new File(name +".xml"));
			transformer.transform(source,  sortie);
			
			JOptionPane.showMessageDialog(MainFrame.instance(), "File succesfully saved", "Saving", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			System.err.println("An error has occured : " + e);
			new ApplyException(e.toString());
		}
	}

	/**Method to convert a date into a String Object.
	 * The method is only accessible for the Class of the xml package
	 * so only method to write or read a .xml file can use this method
	 * @param indate
	 * @return dateString
	 */
	protected static String convertDateToString(Date indate) {
		String dateString = null;
		DateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try{
			dateString = sdfr.format( indate );
		}
		catch (Exception ex ){
			new ApplyException(ex.toString());
		}
		return dateString;
	}

	protected static Date convertStringToDate(String indate) {
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try {
			date = formatter.parse(indate);
		} catch (ParseException e) {
			new ApplyException(e.toString());
		}
		return date;
	}
}
