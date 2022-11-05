package com.mygdx.game;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;

public class CardReader {

	File inputFile;
	DocumentBuilderFactory dbFactory;
	DocumentBuilder dBuilder;
	Document doc;
	XPathFactory factory;
	XPath xpath;
	XPathExpression expr;

	NodeList nList = new NodeList() {
		@Override
		public Node item(int index) {
			return null;
		}

		@Override
		public int getLength() {
			return 0;
		}
	};

	public CardReader() {
		inputFile = new File("cards.xml");
		try {
			dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			nList = doc.getElementsByTagName("card");


			//	doc.getDocumentElement().normalize();
		}catch(Exception e) {
			e.getMessage();
		}
	}

	public Card readCard(int id) {

		String name = "";
		String description = "";
		int manaCost = 0;
		int damage = 0;
		int heal = 0;
		boolean block = false;


		for(int i = 0; i < nList.getLength(); i++) {
			Node node = nList.item(i);
			Element e = (Element)node;
			if(Integer.parseInt(e.getAttribute("id")) == id) {
				name = (e.getElementsByTagName("name").item(0).getTextContent());
				description = (e.getElementsByTagName("description").item(0).getTextContent());
				block = Boolean.parseBoolean(e.getElementsByTagName("block").item(0).getTextContent());
				damage = Integer.parseInt(e.getElementsByTagName("damage").item(0).getTextContent());
				heal = Integer.parseInt(e.getElementsByTagName("heal").item(0).getTextContent());
				manaCost = Integer.parseInt(e.getElementsByTagName("manaCost").item(0).getTextContent());
			}
		}

		return new Card(id, name, description, block, damage, heal, manaCost);


	}

}




	

