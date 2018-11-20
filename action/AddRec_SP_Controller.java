package action;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXButton;

import application.conn_connector;
import db._query;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import share_class.s_class;

public class AddRec_SP_Controller {
	
	@FXML
	TextField txt_HMMR_Material_Num, txt_Manufacturer, txt_Model, txt_Article, txt_Risk_Breakage, txt_Delivery_Time, txt_Replacement_Model, txt_Identity_SP, txt_Coefficient, txt_Price, 
	txt_Kind, txt_SP_Part_Type, txt_SP_Sub_Part_Type, txt_Part_Characteristic_1, txt_Part_Characteristic_2, txt_Part_Characteristic_3, txt_Part_Characteristic_4, txt_Qty_S, 
	txt_Qty_W, txt_Qty_P, txt_Qty_A, txt_Key_No_Backup_Yes, txt_Key_No_Backup_No, txt_Key_Yes_Backup_Yes, txt_Key_Yes_Backup_No, txt_Qty_Interchangeability, txt_Qty_Identify_SP,
	txt_MIN, txt_BATCH;
	
	@FXML
	TextArea txt_SP_MU_Description_RUS, txt_SP_FD_Description, txt_SP_Supplier_Description;
	
	@FXML
	ComboBox<String> list_Manufacturer, list_Model, list_Single_Complex_Sub;
	
	@FXML
	JFXButton btn_add_rec, btn_cancel;
	
	@FXML
	Label lbl_title_sp;
	
	ObservableList<String> list_type = FXCollections.observableArrayList();
	ObservableList<String> list_Material_Num = FXCollections.observableArrayList();
	ObservableList<String> list_interchangeable_identify = FXCollections.observableArrayList();
		
	_query qr = new _query();
	s_class scl = new s_class();
	SP_Controller spc = new SP_Controller();
	Stage stage;
	
	@SuppressWarnings("static-access")
	@FXML
	public void initialize()
	{
		scl._style(btn_add_rec);
		scl._style(btn_cancel);
		
		btn_add_rec.setDisable(true);
		
		list_Manufacturer.setItems(qr._select_list_str("hmmr_sp_db", "Manufacturer"));
		list_Model.setItems(qr._select_list_str("hmmr_sp_db", "Model"));
		list_Material_Num.addAll(qr._select_list_str("hmmr_sp_db", "HMMR_Material_Num"));
		list_interchangeable_identify.addAll(qr._select_recArr("hmmr_sp_db", "SP_MU_Description_RUS", "Model", "del_rec"));
				
		list_type.add("Single");
		list_type.add("Complex");
		list_type.add("Sub-part");
		
		list_Single_Complex_Sub.setItems(list_type);
		
		
		if(!spc._flag_sp_window) {
			lbl_title_sp.setText("Обновление записи SP DB");
			
			String _sql_rez = qr._select_for_update_sp(spc._id_sp);
			txt_HMMR_Material_Num.setText(scl.parser_str_str_str(_sql_rez, 0));
			txt_Manufacturer.setText(scl.parser_str_str_str(_sql_rez, 1));
			txt_Model.setText(scl.parser_str_str_str(_sql_rez, 2));
			txt_Article.setText(scl.parser_str_str_str(_sql_rez, 3));
			list_Single_Complex_Sub.getSelectionModel().select(scl.parser_str_str_str(_sql_rez, 4));
			txt_SP_MU_Description_RUS.setText(scl.parser_str_str_str(_sql_rez, 5));
			txt_SP_FD_Description.setText(scl.parser_str_str_str(_sql_rez, 6));
			txt_SP_Supplier_Description.setText(scl.parser_str_str_str(_sql_rez, 7));
			txt_Price.setText(scl.parser_str_str_str(_sql_rez, 8));
			txt_Risk_Breakage.setText(scl.parser_str_str_str(_sql_rez, 9));
			txt_Delivery_Time.setText(scl.parser_str_str_str(_sql_rez, 10));
			txt_Replacement_Model.setText(scl.parser_str_str_str(_sql_rez, 11));
			txt_Identity_SP.setText(scl.parser_str_str_str(_sql_rez, 12));
			txt_Coefficient.setText(scl.parser_str_str_str(_sql_rez, 13));
			
		}
		else
			lbl_title_sp.setText("Добавление записи к SP DB");
		
		TextFields.bindAutoCompletion(txt_HMMR_Material_Num, list_Material_Num);
		TextFields.bindAutoCompletion(txt_Replacement_Model, list_interchangeable_identify);
		TextFields.bindAutoCompletion(txt_Identity_SP, list_interchangeable_identify);
		
		list_Manufacturer.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				txt_Manufacturer.setText(list_Manufacturer.getValue());
			}
		});
		list_Model.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				txt_Model.setText(list_Model.getValue());
			}
		});
		
		txt_HMMR_Material_Num.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_Manufacturer.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_Model.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_Article.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		list_Single_Complex_Sub.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		txt_SP_MU_Description_RUS.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_SP_FD_Description.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_SP_Supplier_Description.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_Risk_Breakage.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_Delivery_Time.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_Replacement_Model.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_Identity_SP.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_Coefficient.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		txt_Price.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		
		btn_add_rec.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				String Id_Intercangeable = "-", Id_Identify = "-";
				if(!txt_Replacement_Model.getText().equals("-")) {
					String Desc_interchangeable = scl.parser_str(txt_Replacement_Model.getText(), 0);
					String Model_Interchangeable = scl.parser_str(txt_Replacement_Model.getText(), 1);
					if(!Model_Interchangeable.equals("null"))
						Id_Intercangeable = qr._select_recStr("hmmr_sp_db", "id", "del_rec", "SP_MU_Description_RUS", Desc_interchangeable, "Model", Model_Interchangeable);
					else
						scl._AlertDialog("ВНИМАНИЕ!!! Модель для "+Desc_interchangeable+" не определена!\nПоиск не возможен!", "Внимание!");
				}
				if(!txt_Identity_SP.getText().equals("-")) {
					String Desc_Identify = scl.parser_str(txt_Identity_SP.getText(), 0);
					String Model_Identify = scl.parser_str(txt_Identity_SP.getText(), 1);
					if(!Model_Identify.equals("null"))
						Id_Identify = qr._select_recStr("hmmr_sp_db", "id", "del_rec", "SP_MU_Description_RUS", Desc_Identify, "Model", Model_Identify);
					else
						scl._AlertDialog("ВНИМАНИЕ!!! Модель для "+Desc_Identify+" не определена!\nПоиск не возможен!", "Внимание!");
				}
				if(spc._flag_sp_window) {
					qr._insert_sp(txt_HMMR_Material_Num.getText(), txt_Manufacturer.getText(), txt_Model.getText(), txt_Article.getText(), list_Single_Complex_Sub.getValue(), txt_SP_MU_Description_RUS.getText(), txt_SP_FD_Description.getText(), txt_SP_Supplier_Description.getText(), txt_Price.getText(), txt_Risk_Breakage.getText(), txt_Delivery_Time.getText(), Id_Intercangeable, Id_Identify, txt_Coefficient.getText());
					qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Создал запись № = " + qr._select_last_id("hmmr_sp_db") + " в SP DB");
				}
				else {
					qr._update_sp(spc._id_sp, txt_HMMR_Material_Num.getText(), txt_Manufacturer.getText(), txt_Model.getText(), txt_Article.getText(), list_Single_Complex_Sub.getValue(), txt_SP_MU_Description_RUS.getText(), txt_SP_FD_Description.getText(), txt_SP_Supplier_Description.getText(), txt_Price.getText(), txt_Risk_Breakage.getText(), txt_Delivery_Time.getText(), Id_Intercangeable, Id_Identify, txt_Coefficient.getText());
					qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Обновил запись № = " + qr._select_last_id("hmmr_sp_db") + " в SP DB");
				}
				
				spc._table_update_sp.addAll(qr._select_data_sp());
				
				stage = (Stage) btn_add_rec.getScene().getWindow();
				stage.close();
			}
		});
		btn_cancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				stage = (Stage) btn_cancel.getScene().getWindow();
				stage.close();
			}
		});
	}
	
	void chk_btn()
	{
		try {
			if(txt_HMMR_Material_Num.getText().length() != 0 && txt_Manufacturer.getText().length() != 0 && txt_Model.getText().length() != 0 && txt_Article.getText().length() != 0 && 
					list_Single_Complex_Sub.getValue().length() != 0 && txt_SP_MU_Description_RUS.getText().length() != 0 && txt_SP_FD_Description.getText().length() != 0 && 
					txt_SP_Supplier_Description.getText().length() != 0 && txt_Risk_Breakage.getText().length() != 0 && txt_Delivery_Time.getText().length() != 0 && 
					txt_Replacement_Model.getText().length() != 0 && txt_Identity_SP.getText().length() != 0 && txt_Coefficient.getText().length() != 0 && txt_Price.getText().length() != 0)
				btn_add_rec.setDisable(false);
			else
				btn_add_rec.setDisable(true);
		}
		catch (Exception e) {
			
		}
	}

}
