package application;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Coach extends Application {

	private NodeList quesiti;
	private int nextQuesito;
	private BorderPane borderPane;

	@Override
	public void start(Stage primaryStage) {

		borderPane = new BorderPane();



		Scene scene = new Scene(borderPane);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);

		File fXmlFile = new File("C:\\Users\\2015_2016\\Desktop\\progetto coach\\prova2.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			doc = dBuilder.parse(fXmlFile);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		doc.getDocumentElement().normalize();

		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		quesiti = doc.getElementsByTagName("quesito");

		System.out.println("----------------------------");

		QuesitoPane pane = new QuesitoPane((Element) quesiti.item(nextQuesito), this);

		borderPane.setCenter(pane);

//		for (int temp = 0; temp < nList.getLength(); temp++) {
//
//			Node nNode = nList.item(temp);
//
//			System.out.println("\nCurrent Element :" + nNode.getNodeName());
//
//			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//
//				Element eElement = (Element) nNode;
//
//				System.out.println("Staff id : " + eElement.getAttribute("id"));
//				System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
//				System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
//				System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
//				System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
//
//			}



		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	void quesitoSuperato() {

		borderPane.getChildren().remove(quesiti.item(nextQuesito));
		nextQuesito++;
		QuesitoPane pane = new QuesitoPane((Element) quesiti.item(nextQuesito), this);
		borderPane.setCenter(pane);


	}
}
