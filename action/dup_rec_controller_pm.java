package action;

import com.jfoenix.controls.JFXButton;

import db._query;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import share_class.s_class;

public class dup_rec_controller_pm {
	
	@FXML
	TextField num_rec_ps;
	
	@FXML
	JFXButton dup_rec_add, dup_cancel;
	
	s_class scl = new s_class();
	_query qr = new _query();
	ps_controller ps = new ps_controller();
	pm_controller pc = new pm_controller();
	Stage stage;
	
	public static int _qty_rec = 0;
	@FXML
	public void initialize()
	{
		scl._style(dup_rec_add);
		scl._style(dup_cancel);
		
		dup_rec_add.setDisable(true);
		
		num_rec_ps.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					num_rec_ps.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 5) {
					
					num_rec_ps.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
			}
			}
		});
		dup_rec_add.setOnAction(new EventHandler<ActionEvent>() {
			
			@SuppressWarnings("static-access")
			@Override
			public void handle(ActionEvent arg0) {
				try {
					if(num_rec_ps.getText().length() != 0)
						for(int i = 0; i < Integer.parseInt(num_rec_ps.getText()); i++)
						{
							qr._insert_pm_dup(pm_controller._Id_Dup_Pm);
						}
				}
				catch (Exception e) {
					
				}
				pc._table_update_pm.addAll(qr._select_data_pm());
				stage = (Stage) dup_rec_add.getScene().getWindow();
				stage.close();
			}
		});
		dup_cancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				stage = (Stage) dup_cancel.getScene().getWindow();
				stage.close();
			}
		});
		num_rec_ps.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				if(num_rec_ps.getText().length() != 0)
					dup_rec_add.setDisable(false);
			}
		});
	}

}
