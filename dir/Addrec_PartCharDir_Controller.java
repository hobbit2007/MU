package dir;

import com.jfoenix.controls.JFXButton;

import action.apwr_controller;
import application.conn_connector;
import db._query;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import share_class.s_class;

public class Addrec_PartCharDir_Controller {
	
	@FXML
	TextField txt_part_type, txt_part_type_eng;
	
	@FXML
	JFXButton btn_add_part, btn_cancel_part;
	
	@FXML
	Label title_part_add, lbl_part_type, lbl_part_type_eng;
	
	_query qr = new _query();
	s_class scl = new s_class();
	apwr_controller pic = new apwr_controller();
	PartCharDir_Controller pc = new PartCharDir_Controller();
	Stage stage;
	
	@SuppressWarnings("static-access")
	@FXML
	public void initialize()
	{
		scl._style(btn_add_part);
		scl._style(btn_cancel_part);
		
		btn_add_part.setDisable(true);
		
		if(!pc._flag_window)
		{
			title_part_add.setText("���������� ������ Part Char");
			
			String _sql_rez = qr._select_for_update_partchardir(pc._id_part);
			
			txt_part_type.setText(scl.parser_str_str_str(_sql_rez, 0));
			txt_part_type_eng.setText(scl.parser_str_str_str(_sql_rez, 1));
		}
		else
			title_part_add.setText("���������� ������ � Part Char");
		
		txt_part_type.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_part_type_eng.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		btn_add_part.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				if(pc._flag_window)
				{
					qr._insert_partchartdir(txt_part_type.getText(), txt_part_type_eng.getText());
					qr._insert_history(conn_connector.USER_ID, pic.USER_S + " - ������� ������ � = " + qr._select_last_id("hmmr_mu_part") + " � ������� Part Char Dir");
				}
				else
				{
					qr._update_field_partchardir(pc._id_part, txt_part_type.getText(), txt_part_type_eng.getText());
					qr._insert_history(conn_connector.USER_ID, pic.USER_S + " - � ������ � = " + qr._select_last_id("hmmr_mu_part") + " � ������� Part Char Dir");
				}
				pc._table_update_part.addAll(qr._select_data_partchardir());
				
				stage = (Stage) btn_add_part.getScene().getWindow();
				stage.close();
			}
		});
		btn_cancel_part.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				stage = (Stage) btn_cancel_part.getScene().getWindow();
				stage.close();
			}
		});
	}
	
	private void chk_btn()
	{
		try {
			if(txt_part_type.getText().length() != 0 && txt_part_type_eng.getText().length() != 0)
			{
				btn_add_part.setDisable(false);
			}
			else
				btn_add_part.setDisable(true);
			}
			catch (Exception e) {
				
			}
	}

}
