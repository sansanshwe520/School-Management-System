package utility;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertSupport {
	
	private AlertSupport() {
		// TODO Auto-generated constructor stub
	}
	
	public static Alert getAlert(AlertType type,String title, String header,String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		return alert;
	}

}