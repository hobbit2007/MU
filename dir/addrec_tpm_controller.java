package dir;

import com.jfoenix.controls.JFXButton;

import action.apwr_controller;
import application.conn_connector;
import db._query;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import share_class.s_class;

public class addrec_tpm_controller
{
	@FXML
	JFXButton add_tpm_confirm, add_tpm_cancel;
	
	@FXML
	TextField type_tpm, desc_tpm;
	
	@FXML
	Label err_info_cycle;
	
	private Stage stage;
	private String _t_tpm, _d_tpm;
	s_class scl = new s_class();
	_query qr = new _query();
	type_pm_controller tpm = new type_pm_controller();
	
	public addrec_tpm_controller()
	{
		
	}
	
	@FXML
	public void initialize()
	{
		scl._style(add_tpm_confirm);
		scl._style(add_tpm_cancel);
		
		add_tpm_cancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				stage = (Stage) add_tpm_cancel.getScene().getWindow();
				stage.close();
			}
		});
		
		add_tpm_confirm.setOnAction(new EventHandler<ActionEvent>() {
			
			@SuppressWarnings("static-access")
			@Override
			public void handle(ActionEvent event) {
				
				if(type_tpm.getText().length() != 0 && desc_tpm.getText().length() != 0)
				{
					err_info_cycle.setVisible(false);
					
					_t_tpm = type_tpm.getText();
					_d_tpm = desc_tpm.getText();
									
					
					qr._insert_type_pm(_t_tpm, _d_tpm);
					
					qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + "������ ������ � = " + qr._select_last_id("hmmr_type_pm") + " � Type PM");
					
					tpm._table_update_typepm.addAll(qr._select_data_typepm());
					
					stage = (Stage) add_tpm_confirm.getScene().getWindow();
					stage.close();
				}
				else
					err_info_cycle.setVisible(true);
			}
		});
	}
}