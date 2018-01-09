package filter_pattern;

import java.util.ArrayList;

import presentation.Document;

public class CriteriaDocNotInit implements CriteriaDocument {
	
	public ArrayList<Document> meetCriteria(ArrayList<?> list) {

		ArrayList<Document> doc = new ArrayList<Document>(); 
		for (Object obj : list) {
			if(obj.getClass().getName().toLowerCase().equals("presentation.document")){
				doc.add((Document) obj);
			}
		}
		
		ArrayList<Document> docNotSettled = new ArrayList<Document>(); 

		for (Document event : doc) {
			if(event.getDocumentName().equals("")){
				docNotSettled.add(event);
			}
		}
		return docNotSettled;
		
	}

}
