package nofxml;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

public class NotepadController {

	TextArea textArea;

	public void handleNew(ActionEvent event) {
		textArea.appendText("ActionEvent : New\n");
	}

	public void handleOpen(ActionEvent event) {
		textArea.appendText("ActionEvent : Open\n");
	}

	public void handleSave(ActionEvent event) {
		textArea.appendText("ActionEvent : Save\n");
	}

	public void handleExit() {
		System.out.println(">>> program exit");
		Platform.exit();
	}
}