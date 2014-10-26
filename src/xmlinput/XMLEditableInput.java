package xmlinput;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XMLEditableInput {
	private DocumentBuilderFactory _documentBuilderFactory;
	private DocumentBuilder _documentBuilder;
	private Document _documentOutput;
	private Element _root,_declarative,_conections;
	private String _path;
	public static final String TAG_ROOT = "circuit";
	public static final String TAG_DECLARATIVE = "declarative";
	public static final String TAG_GATE = "logicgate";
	public static final String TAG_CONECTION = "conection";
	public static final String TAG_CONECTOR = "conector";
	public static final String ATTRIBUTE_SERIAL = "serial";
	public static final String ATTRIBUTE_TYPE = "type";
	public static final String ATTRIBUTE_XPOSITION = "xposition";
	public static final String ATTRIBUTE_YPOSITION = "yposition";
	public static final String ATTRIBUTE_NUM_OF_INPUTS = "numofinputs";
	public static final String ATTRIBUTE_FROM = "from";
	public static final String ATTRIBUTE_TO = "to";
	public static final String ATTRIBUTE_INPUT = "input";
	
	public XMLEditableInput(String path){
		// TODO Auto-generated constructor stub
		_path = path;
		try {
			openDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void openDocument() throws ParserConfigurationException, SAXException, IOException {
		_documentBuilderFactory = DocumentBuilderFactory.newInstance();
		_documentBuilderFactory.setValidating(true);
		_documentBuilderFactory.setNamespaceAware(true);
		_documentBuilder = _documentBuilderFactory.newDocumentBuilder();
		_documentOutput = _documentBuilder.parse(_path);
		_root = _documentOutput.createElement(TAG_ROOT);
		_declarative = _documentOutput.createElement(TAG_DECLARATIVE);
		_conections = _documentOutput.createElement(TAG_CONECTION);
		_root.appendChild(_declarative);
		_root.appendChild(_conections);
	}
	
	public static void main(String[] args) {
		XMLEditableInput xml = new XMLEditableInput("C:\\Users\\User\\Desktop\\prub.xml");
		System.out.println("I am working!");	
	}
}
