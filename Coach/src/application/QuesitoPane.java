package application;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class QuesitoPane extends GridPane {

	private static final int CONSEGNA = 0;
	private static final int TESTO = 1;
	private static final int STUDIO = 2;
	private Element quesito;

	private Label okLabel, noLabel;
	private Coach coach;

	public QuesitoPane(Element quesito, Coach coach) {
		super();
		this.quesito = quesito;
		this.coach = coach;

		okLabel = new Label("OK!");
		noLabel = new Label("No!");

		NodeList nodeList = quesito.getChildNodes();

		Element consegna = (Element) nodeList.item(CONSEGNA);
		Element testo = (Element) nodeList.item(TESTO);
		Element studio = (Element) nodeList.item(STUDIO);

		String consegnaStr = consegna.getTextContent();
		String testo1 = testo.getChildNodes().item(0).getTextContent();
		String testoNascosto =  testo.getChildNodes().item(1).getFirstChild().getTextContent();
		String testo2 = testo.getChildNodes().item(2).getTextContent();
		String studioStr = studio.getTextContent();

		Label consegnaLabel = new Label(consegnaStr);
		add(consegnaLabel, 0, 0);

		HBox hBox =  new HBox(30);
		hBox.getStyleClass().add("hbox");
		

		Label testo1Label = new Label(testo1);
		hBox.getChildren().add(testo1Label);


		TextField textField = new TextField();
		hBox.getChildren().add(textField);

		textField.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {



				if(textField.getText().equalsIgnoreCase(testoNascosto)) coach.quesitoSuperato();


			}
		});



		Label testo2Label = new Label(testo2);
		hBox.getChildren().add(testo2Label);

		setVgap(20);

		add(hBox, 0, 1);

		Button button = new Button("studio");

		add(button, 0, 2);

		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {


				QuesitoPane.this.add(new Label(studioStr), 0, 3);
				button.removeEventHandler(ActionEvent.ACTION, this);

			}
		});


	}

}
