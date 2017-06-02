package application;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class Main extends Application {
	private Document doc;

	@Override
	public void start(Stage primaryStage) {
		
			primaryStage.setWidth(600);

			doc = createDocument();
			Element rootElement = doc.createElement("allenamento");
			doc.appendChild(rootElement);

			BorderPane root = new BorderPane();

			CompletamentoPane pane = new CompletamentoPane();
			root.setCenter(pane);

			Button next = new Button("Next");

			HBox box = new HBox();
			box.getChildren().add(next);

			Button save = new Button("Save");
			box.getChildren().add(save);

			root.setBottom(box);

			next.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					Element quesito = doc.createElement("quesito");
					rootElement.appendChild(quesito);

					Element consegna = doc.createElement("consegna");
					quesito.appendChild(consegna);
					consegna.appendChild(doc.createTextNode(pane.getConsegna()));

					Element testo = doc.createElement("testo");
					quesito.appendChild(testo);

					testo.appendChild(doc.createTextNode(pane.getTesto1()));

					Element nascosto = doc.createElement("nascosto");
					testo.appendChild(nascosto);
					nascosto.appendChild(doc.createTextNode(pane.getNascosto()));

					testo.appendChild(doc.createTextNode(pane.getTesto2()));

					Element studio = doc.createElement("studio");
					quesito.appendChild(studio);
					studio.appendChild(doc.createTextNode(pane.getStudio()));

					Element note = doc.createElement("note");
					quesito.appendChild(note);
					note.appendChild(doc.createTextNode(pane.getNote()));

					System.out.println("aggiunto nodo");

				}
			});

			save.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					FileChooser fileChooser = new FileChooser();
		            fileChooser.setTitle("Save");

		            File file = fileChooser.showSaveDialog(primaryStage);
		            System.out.println(file);

		            if (file != null) {

		            	TransformerFactory transformerFactory = TransformerFactory.newInstance();
		        		Transformer transformer = null;
						try {
							transformer = transformerFactory.newTransformer();
						} catch (TransformerConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        		DOMSource source = new DOMSource(doc);
		        		StreamResult result = new StreamResult(file);

		        		// Output to console for testing
		        		// StreamResult result = new StreamResult(System.out);

		        		try {
							transformer.transform(source, result);
						} catch (TransformerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}


		            }

				}
			});


			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

	}

	private Document createDocument() {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return docBuilder.newDocument();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
