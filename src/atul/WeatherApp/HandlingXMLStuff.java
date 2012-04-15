package atul.WeatherApp;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlingXMLStuff extends DefaultHandler {
	XMLDataCollected info = new XMLDataCollected();
	public String getInfo(){
		return info.dataToString();
	}
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

	if(localName.equals("city")){
		String city = attributes.getValue("data");
		info.setCity(city);
	}else if(localName.equals("temp_c")){
		int temp = 0;
		if(attributes.getValue("data")!= null || attributes.getValue("data")=="")
			 temp = Integer.parseInt(attributes.getValue("data"));
		info.setTemp(temp);
	}

		
	
	}

}
