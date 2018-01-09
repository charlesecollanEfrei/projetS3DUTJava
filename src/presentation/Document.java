package presentation;

public class Document {
	
	protected String documentName;
	
	/**
	 * Basic constructor for the Document class, set the name of the document as ""
	 */
	public Document() {
		documentName="";
	}
	
	/**
	 * Constructor for the Document class, with the name of the document as parameter
	 * @param documentURI
	 */
	public Document(String documentURI) {
		this.documentName = documentURI;
	}
	
	/**
	 * Setter for the documentName attribute
	 * @param number
	 */
	public void setDocumentName(String number) {
		this.documentName = number;
	}
	
	/**
	 * Getter for the documentName attribute
	 * @return documentName
	 */
	public String getDocumentName() {
		return documentName;
	}

}
