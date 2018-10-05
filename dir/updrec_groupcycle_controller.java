package dir;

import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import action.apwr_controller;
import application.conn_connector;
import db._query;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import share_class.s_class;

public class updrec_groupcycle_controller {
	@FXML
	Label lbl_title_upd, lbl_pm_group, lbl_pm_cycle;
	
	@FXML
	JFXButton add_rec, cancel_form;
	
	@FXML
	TextField txt_pm_group;
	
	@FXML
	ComboBox<String> list_pm_cycle;
	
	_query qr = new _query();
	s_class scl = new s_class();
	group_cycle_controller gcc = new group_cycle_controller();
	
	private Stage stage;
	
	@SuppressWarnings("static-access")
	@FXML
	public void initialize()
	{
		scl._style(add_rec);
		scl._style(cancel_form);
		
		if(conn_connector.LANG_ID == 1)
			lang_fun("en", "EN");
		if(conn_connector.LANG_ID == 0)
			lang_fun("ru", "RU");
		if(conn_connector.LANG_ID == 2)
			lang_fun("zh", "CN");
		if(conn_connector.LANG_ID == -1)
			lang_fun("ru", "RU");
		
		add_rec.setDisable(true);
		
		txt_pm_group.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					txt_pm_group.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 5) {
					
					txt_pm_group.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
				}
			}
		});
		
		list_pm_cycle.setItems(qr._select_cycle_inst());
		
		txt_pm_group.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				chk_btn();
			}
		});
		/*list_pm_cycle.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				chk_btn();
			}
		});*/
		list_pm_cycle.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				chk_btn();
			}
		});
		
		txt_pm_group.setText(scl.parser_str(qr._select_for_gc_str(gcc._id_gc), 0));
		list_pm_cycle.getSelectionModel().select(scl.parser_str(qr._select_for_gc_str(gcc._id_gc), 1));
		
		add_rec.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				qr._update_for_gc(gcc._id_gc, txt_pm_group.getText(), list_pm_cycle.getValue());
				qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Обновил запись № = " + qr._select_last_id("hmmr_group_cycle") + " в справочнике Группа-Период");
				gcc._table_update_gc.addAll(qr._select_for_gc());
				stage = (Stage) add_rec.getScene().getWindow();
				stage.close();
			}
		});
		cancel_form.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				stage = (Stage) cancel_form.getScene().getWindow();
				stage.close();
			}
		});
	}
	
	private void lang_fun(String loc1, String loc2)
	{
		ResourceBundle lngBndl = ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
		 
		lbl_title_upd.setText(lngBndl.getString("lbl_title_upd"));
		lbl_pm_group.setText(lngBndl.getString("lbl_group")+":");
		lbl_pm_cycle.setText(lngBndl.getString("lbl_pm_cycle")+":");
		
		add_rec.setText(lngBndl.getString("upd_ap"));
		cancel_form.setText(lngBndl.getString("cancel_tsk"));
	}
	
	private void chk_btn()
	{
		try {
			if(txt_pm_group.getText().length() != 0 && list_pm_cycle.getValue().length() != 0)
				add_rec.setDisable(false);
			else
				add_rec.setDisable(true);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
}
