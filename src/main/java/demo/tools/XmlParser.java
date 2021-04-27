package demo.tools;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlParser implements DataParser {

	@Override
	public Optional<BigDecimal> getRate(String content) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(content)));
			Element root = document.getDocumentElement();
			String item = root.getChildNodes()
					.item(3)
					.getFirstChild()
					.getLastChild()
					.getTextContent();
			return Optional.ofNullable(new BigDecimal(item));
		} catch (IOException | SAXException | ParserConfigurationException e) {
			return Optional.empty();
		}
	}
}