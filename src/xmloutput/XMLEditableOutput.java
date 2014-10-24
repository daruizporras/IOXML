package xmloutput;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLEditableOutput {
	private DocumentBuilderFactory _documentBuilderFactory;
	private DocumentBuilder _documentBuilder;
	private Document _documentOutput;
	private Element _root,_declarative,_conections;
	private String _path;
	private Transformer _transformer;
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
	
	public XMLEditableOutput(String path){
		// TODO Auto-generated constructor stub
		_path = path;
		try {
			createDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			_transformer = TransformerFactory.newInstance().newTransformer();
			configure();
		} catch (TransformerConfigurationException
				| TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createDocument() throws ParserConfigurationException {
		_documentBuilderFactory = DocumentBuilderFactory.newInstance();
		_documentBuilder = _documentBuilderFactory.newDocumentBuilder();
		_documentOutput = _documentBuilder.newDocument();
		_root = _documentOutput.createElement(TAG_ROOT);
		_declarative = _documentOutput.createElement(TAG_DECLARATIVE);
		_conections = _documentOutput.createElement(TAG_CONECTION);
		_root.appendChild(_declarative);
		_root.appendChild(_conections);
	}
	
	
	public void configure(){
		_transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		_transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		_transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		_transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "definition.dtd");
		_transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	}
	
	public boolean save(){
		_documentOutput.appendChild(_root);
		try {
			_transformer.transform(new DOMSource(_documentOutput), 
					new StreamResult(new FileOutputStream(_path)));
			return true;
		} catch (FileNotFoundException | TransformerException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	public void createGate(String pId, int x , int y, int pNumOfGates){
		Element e = _documentOutput.createElement(TAG_GATE);
		e.setAttribute(ATTRIBUTE_SERIAL,pId);
		e.setAttribute(ATTRIBUTE_TYPE, "a");
		e.setAttribute(ATTRIBUTE_XPOSITION, String.valueOf(x));
		e.setAttribute(ATTRIBUTE_YPOSITION, String.valueOf(y));
		e.setAttribute(ATTRIBUTE_NUM_OF_INPUTS, String.valueOf(pNumOfGates));
		_declarative.appendChild(e);
		
	}
	
	public void addConnection(String pfrom,String pTo, int pNumOfGate){
		Element e = _documentOutput.createElement(TAG_CONECTOR);
		e.setAttribute(ATTRIBUTE_FROM, pfrom);
		e.setAttribute(ATTRIBUTE_TO, pTo);
		e.setAttribute(ATTRIBUTE_INPUT, String.valueOf(pNumOfGate));
		_conections.appendChild(e);
	}

}
