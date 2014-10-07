package my.com.myriadeas.marc;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadXML {
	public static void main(String argv[]) {

		try {

			File fXmlFile = new File("data/js/convert/convert_general20080912latest_XXX.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			
			Node root = doc.getDocumentElement();

			System.out.println("Root element :"
					+ root.getNodeName());

			NodeList nList = root.getChildNodes();

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node childNode = nList.item(temp);

				if (childNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) childNode;

					if (eElement.getNodeName().equals("script")) {
						//System.out.println(eElement.getTextContent());
					} else if (eElement.getNodeName().equals("tag-remover")) {
						String tag = eElement.getAttribute("tag");
						System.out.print("removeTag('" + tag + "'); ");
						System.out.println("/* <tag-remover tag='" + tag + "' /> */");
					} else if (eElement.getNodeName().equals("indi1-replacer")) {
						String from = eElement.getAttribute("from");
						String to = eElement.getAttribute("to");
						String tag = eElement.getAttribute("tag");
						System.out.print("replaceIndi1('" + tag + "', '" + from + "', '" + to + "'); ");
						System.out.println("/* <indi1-replacer from='" + from + "' to='" + to + "' tag='" + tag + "' /> */");
					} else if (eElement.getNodeName().equals("indi2-replacer")) {
						String from = eElement.getAttribute("from");
						String to = eElement.getAttribute("to");
						String tag = eElement.getAttribute("tag");
						System.out.print("replaceIndi2('" + tag + "', '" + from + "', '" + to + "');  ");
						System.out.println("/* <indi2-replacer from='" + from + "' to='" + to + "' tag='" + tag + "' /> */");
					} else if (eElement.getNodeName().equals("data-replacer")) {
						String from = eElement.getAttribute("from");
						String to = eElement.getAttribute("to");
						String tag = eElement.getAttribute("tag");
						if (from.startsWith("(\\x1F)")) {
							if (to.startsWith("$1")) {
								String fromSubfield = from.substring("(\\x1F)".length());
								String toSubfield = to.substring("$1".length());
								System.out.print("changeSubfield('" + tag + "', '" + fromSubfield + "', '" + toSubfield + "'); ");
							} else if (to.trim().length() == 0) {
								String fromSubfield = from.substring("(\\x1F)".length());
								System.out.print("removeSubfieldCode('" + tag + "', '" + fromSubfield + "'); ");
							} else {
								System.out.print("replaceData1('" + tag + "', '" + from + "', '" + to + "'); ");
							}
						} else {
							System.out.print("replaceData2('" + tag + "', '" + from + "', '" + to + "'); ");
						}
						System.out.println("/* <data-replacer from='" + from + "' to='" + to + "' tag='" + tag + "' /> */");
					} else {
						System.out.println(eElement.getNodeName() + " not handled.");
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String[] expandRegex(String source) {
		List<String> values = new ArrayList<String>();
		
		if (source.contains("[")) {
			int startPos = source.indexOf("[");
			int endPos = source.indexOf("]");
			String left = source.substring(0, startPos);
			String[] lefts = expandRegex(left);
			String right = source.substring(endPos + 1);
			String[] rights = expandRegex(right);
			for(String l : lefts) {
				for(int i=startPos + 1; i < endPos; i++) {
					char ch = source.charAt(i);
					for(String r : rights) {
						String value = l + ch + r;
						values.add(value);
					}
				}
			}
		} else {
			values.add(source);
		}
		
		return values.toArray(new String[0]);
	}
}
