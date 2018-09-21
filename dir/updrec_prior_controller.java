package dir;

import java.io.File;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import action.apwr_controller;
import application.conn_connector;
import db._query;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import share_class.s_class;

public class updrec_prior_controller {

	@FXML
	JFXTextField t_id_prior, t_name_prior, t_icon_prior;
	
	@FXML
	Label lbl_title_prior, lbl_id_prior, lbl_name_prior, lbl_desc_prior, lbl_icon_prior;
	
	@FXML
	JFXButton b_add_prior, b_cancel_prior, b_exp_prior;
	
	@FXML
	TextArea t_desc_prior;
	
	_query qr = new _query();
	s_class scl = new s_class();
	prior_controller pic = new prior_controller();
	private Stage stage;
	
	@SuppressWarnings("static-access")
	@FXML
	public void initialize()
	{
		scl._style(b_add_prior);
		scl._style(b_cancel_prior);
		scl._style(b_exp_prior);
		
		b_add_prior.setDisable(true);
		//Проверяем заполненность полей
		t_id_prior.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				chk_btn();
			}
		});
		t_name_prior.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				chk_btn();
			}
		});
		t_desc_prior.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				chk_btn();
			}
		});
		t_icon_prior.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				chk_btn();
			}
		});
		
		b_exp_prior.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				FileChooser fc = new FileChooser();
			    fc.setTitle("Укажите путь к файлу:");
			    fc.getExtensionFilters().addAll(
			        new ExtensionFilter(
			            "JPG Files", 
			            "*.jpg"),
			        new ExtensionFilter(
			            "PNG Files", 
			            "*.png"));
			    
			    //showing the file chooser
			    File phil = 
			        fc.showOpenDialog(
			            pic.stage);
			    
			    // checking that a file was
			    // chosen by the user
			    if (phil != null) {
			    	 t_icon_prior.setText(phil.getPath());
			    	 chk_btn();
			    }
			}
		});
		b_exp_prior.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				scl._ToolTip("Внимание! Размеры изображения не должны превышать 30х30 px!", b_exp_prior);
			}
		});
		b_exp_prior.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				scl._ToolTipHide();
			}
		});
		t_icon_prior.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				scl._ToolTip("Внимание! Размеры изображения не должны превышать 30х30 px!", t_icon_prior);
			}
		});
		t_icon_prior.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				scl._ToolTipHide();
			}
		});
		
		t_id_prior.setText(pic._id_prior);
		t_name_prior.setText(pic._name_prior);
		t_desc_prior.setText(pic._desc_prior);
		t_icon_prior.setText(pic._icon_prior);
		
		b_add_prior.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				qr._update_rec_prior(pic._id_pr, t_id_prior.getText(), t_name_prior.getText(), t_desc_prior.getText(), t_icon_prior.getText().replace('\\', '/'));
				
				qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Обновил запись № = " + qr._select_last_id("hmmr_mu_prior") + " в Справочнике приоритетов");
				
				pic._table_update_prior.addAll(qr._select_data_prior());
				stage = (Stage) b_add_prior.getScene().getWindow();
				stage.close();
			}
		});
		b_cancel_prior.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				stage = (Stage) b_cancel_prior.getScene().getWindow();
				stage.close();
			}
		});
	}

	private void chk_btn()
	{
		try {
			if(t_id_prior.getText().length() != 0 && t_name_prior.getText().length() != 0 && t_desc_prior.getText().length() != 0 && t_icon_prior.getText().length() != 0)
				b_add_prior.setDisable(false);
			else
				b_add_prior.setDisable(true);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
	}
}
