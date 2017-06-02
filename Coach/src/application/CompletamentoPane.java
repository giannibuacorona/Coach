package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class CompletamentoPane extends GridPane {

	private TextArea testo1;
	private TextArea nascosto;
	private TextArea testo2;
	private TextArea studio;
	private TextArea note;
	private TextArea consegna;

	public CompletamentoPane() {
		super();

		//consegna
		Label label = new Label("Consegna");
		add(label, 0, 0);
		consegna = new TextArea();
		add(consegna, 1, 0);

		//testo prima
		Label label2 = new Label("Testo prima");
		add(label2, 0, 1);
		testo1 = new TextArea();
		add(testo1, 1, 1);

		//nascosto
		Label label3 = new Label("Testo nascosto");
		add(label3, 0, 2);
		nascosto = new TextArea();
		add(nascosto, 1, 2);

		//testo dopo
		Label label4 = new Label("Testo dopo");
		add(label4, 0, 3);
		testo2 = new TextArea();
		add(testo2, 1, 3);

		//studio
		Label label5 = new Label("Studio");
		add(label5, 0, 4);
		studio = new TextArea();
		add(studio, 1, 4);

		//note
		Label label6 = new Label("Note");
		add(label6, 0, 5);
		note = new TextArea();
		add(note, 1, 5);



	}

	public String getTesto1() {

		return testo1.getText();
	}

	public String getTesto2() {

		return testo2.getText();
	}

	public String getConsegna() {

		return consegna.getText();
	}

	public String getNascosto() {

		return nascosto.getText();
	}

	public String getStudio() {

		return studio.getText();
	}

	public String getNote() {

		return note.getText();
	}



}
