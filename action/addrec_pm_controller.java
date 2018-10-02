package action;

import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import application.conn_connector;
import db._query;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import share_class.s_class;

public class addrec_pm_controller {
	@FXML
	ComboBox<String> ninst_pm, shop_pm, lm_pm, os_pm, equip_pm, group_pm, ool_pm, otv_pm;
	
	@FXML
	TextField dexp_pm, group_eq;
	
	@FXML
	JFXButton confirm_pm, cancel_pm;
	
	@FXML
	Label err_msg, lbl_head_pm, lbl_ninst_pm, lbl_shop_pm, lbl_lm_pm, lbl_os_pm, lbl_equip_pm, lbl_group_pm, lbl_ool_pm, lbl_otv_pm, lbl_dexp_pm, lbl_group_eq;
	
	private Stage stage;	
	_query qr = new _query();
	s_class sclass = new s_class();
	pm_controller pc = new pm_controller();
	Tooltip tip;
		
	@FXML
	public void initialize()
	{
		if(conn_connector.LANG_ID == 1)
			lang_fun("en", "EN");
		if(conn_connector.LANG_ID == 0)
			lang_fun("ru", "RU");
		if(conn_connector.LANG_ID == 2)
			lang_fun("zh", "CN");
		if(conn_connector.LANG_ID == -1)
			lang_fun("ru", "RU");
		
		//инициализируем данные комбобоксов
		ninst_pm.setItems(qr._select_instr_pm());
		confirm_pm.setDisable(true);
		
		sclass._style(confirm_pm);
		sclass._style(cancel_pm);
		
		group_eq.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					group_eq.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 5) {
					
					group_eq.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
			}
			}
		});
		
		ninst_pm.setOnMouseEntered(new EventHandler<Event>() {
		
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip = new Tooltip(ninst_pm.getValue());
				Point2D p = ninst_pm.localToScreen(ninst_pm.getLayoutBounds().getMaxX(), ninst_pm.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(ninst_pm, p.getX(), p.getY());
			}
		});
		ninst_pm.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip.hide();
			}
		});
		
		shop_pm.setItems(qr._select_shop_pm());
		shop_pm.setValue(apwr_controller.SHOP_NAME);
				
		if(shop_pm.getValue().toString().length() != 0)
			group_pm.setItems(qr._select_group_pm(sclass.parser_str(shop_pm.getValue(), 0)));
		try {
			shop_pm.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					group_pm.valueProperty().set(null);
					lm_pm.valueProperty().set(null);
					os_pm.valueProperty().set(null);
					equip_pm.valueProperty().set(null);
					if(shop_pm.getValue().toString().length() != 0)
						group_pm.setItems(qr._select_group_pm(sclass.parser_str(shop_pm.getValue(), 0)));
					chk_btn();
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		group_pm.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				lm_pm.valueProperty().set(null);
				os_pm.valueProperty().set(null);
				equip_pm.valueProperty().set(null);
				try {
					if(group_pm.getValue().toString().length() != 0)
						lm_pm.setItems(qr._select_lm_pm(sclass.parser_str(group_pm.getValue(), 0)));
					chk_btn();
					//if(lm_wr_add.getValue().toString().length() != 0)
					//	os_wr_add.setItems(qr._select_os_pm(sclass.parser_str(shop_wr_add.getValue(), 0), sclass.parser_str(lm_wr_add.getValue(), 0)));
					} catch (Exception e) {
						// TODO: handle exception
					}
				
			}
		});
		group_pm.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip = new Tooltip(group_pm.getValue());
				Point2D p = group_pm.localToScreen(group_pm.getLayoutBounds().getMaxX(), group_pm.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(group_pm, p.getX(), p.getY());
			}
		});
		group_pm.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip.hide();
			}
		});
		
		lm_pm.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					os_pm.valueProperty().set(null);
					equip_pm.valueProperty().set(null);
					if(lm_pm.getValue().toString().length() != 0)
						os_pm.setItems(qr._select_os_pm(sclass.parser_str(group_pm.getValue(), 0), sclass.parser_str(lm_pm.getValue(), 0)));
					chk_btn();
					} catch (Exception e) {
						// TODO: handle exception
					}
			}
		});
		lm_pm.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip = new Tooltip(lm_pm.getValue());
				Point2D p = lm_pm.localToScreen(lm_pm.getLayoutBounds().getMaxX(), lm_pm.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(lm_pm, p.getX(), p.getY());
			}
		});
		lm_pm.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip.hide();
			}
		});
			
		
		os_pm.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					equip_pm.valueProperty().set(null);
					if(os_pm.getValue().toString().length() != 0)
						equip_pm.setItems(qr._select_equip_pm(sclass.parser_str(os_pm.getValue(), 0), sclass.parser_str(group_pm.getValue(), 0), sclass.parser_str(lm_pm.getValue(), 0)));
					chk_btn();
					
					} catch (Exception e) {
					}
			}
		});
		os_pm.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip = new Tooltip(os_pm.getValue());
				Point2D p = os_pm.localToScreen(os_pm.getLayoutBounds().getMaxX(), os_pm.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(os_pm, p.getX(), p.getY());
			}
		});
		os_pm.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip.hide();
			}
		});
		
		equip_pm.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					try {
				//	if(equip_pm.getValue().toString().length() != 0)
				//		equip_pm.setItems(qr._select_group_pm(sclass.parser_str(shop_pm.getValue(), 0), sclass.parser_str(lm_pm.getValue(), 0)));
						otv_pm.setItems(qr._select_fio_for_ap(1, sclass.parser_str(shop_pm.getValue(), 0)));
					chk_btn();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			});
		equip_pm.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip = new Tooltip(equip_pm.getValue());
				Point2D p = equip_pm.localToScreen(equip_pm.getLayoutBounds().getMaxX(), equip_pm.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(equip_pm, p.getX(), p.getY());
			}
		});
		equip_pm.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip.hide();
			}
		});
		
		ninst_pm.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				try {
					chk_btn();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		String line1 = new String("ON");
		String line2 = new String("OFF");
		ool_pm.getItems().addAll(line1, line2);
		
		ool_pm.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				chk_btn();
			}
		});
		
		confirm_pm.setOnAction(new EventHandler<ActionEvent>() {
			
			@SuppressWarnings("static-access")
			@Override
			public void handle(ActionEvent event) {
				
				String eq_id_total = qr._select_data_filter_ps_id(sclass.parser_str(shop_pm.getValue(), 0), sclass.parser_str(group_pm.getValue(), 0), sclass.parser_str(lm_pm.getValue(), 0), sclass.parser_str(os_pm.getValue(), 0), sclass.parser_str(equip_pm.getValue(), 0));
				qr._insert_pm(sclass.parser_str(ninst_pm.getValue(), 0), eq_id_total, sclass.parser_str(group_eq.getText(), 0), sclass.parser_str(otv_pm.getValue(), 0), dexp_pm.getText(), sclass.parser_str(ool_pm.getValue(), 0));
			
				qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Создал запись № = " + qr._select_last_id("hmmr_pm") + " в таблице PM");
					
				pc._table_update_pm.addAll(qr._select_data_pm());
					
				stage = (Stage) confirm_pm.getScene().getWindow();
				stage.close();
				}
			});
		cancel_pm.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				stage = (Stage) confirm_pm.getScene().getWindow();
				stage.close();
			}
		});
		group_eq.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				chk_btn();
			}
		});
		dexp_pm.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				chk_btn();
			}
		});
		}
	
	private void lang_fun(String loc1, String loc2)
	{
		ResourceBundle lngBndl = ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
		
		lbl_ninst_pm.setText(lngBndl.getString("col_ninst_inst")+":");
		lbl_shop_pm.setText(lngBndl.getString("lbl_shop_ap"));
		lbl_group_pm.setText(lngBndl.getString("lbl_group_ap"));
		lbl_lm_pm.setText(lngBndl.getString("lbl_lm_ap"));
		lbl_os_pm.setText(lngBndl.getString("lbl_os_ap"));
		lbl_equip_pm.setText(lngBndl.getString("lbl_equip_ap"));
		lbl_ool_pm.setText(lngBndl.getString("col_ool_pm")+":");
		lbl_otv_pm.setText(lngBndl.getString("lbl_otv_ap")+":");
		lbl_dexp_pm.setText(lngBndl.getString("lbl_dexp_pm")+":");
		lbl_group_eq.setText(lngBndl.getString("col_group_pm")+":");
		lbl_head_pm.setText(lngBndl.getString("lbl_head_pm"));
		confirm_pm.setText(lngBndl.getString("lbl_apply"));
		cancel_pm.setText(lngBndl.getString("cancel_tsk"));
	}
	
		private void chk_btn()
		{
			try {
			if(ninst_pm.getValue().length() != 0 && shop_pm.getValue().length() != 0 && group_pm.getValue().length() != 0 && lm_pm.getValue().length() != 0 &&
					os_pm.getValue().length() != 0 && equip_pm.getValue().length() != 0 && group_eq.getText().length() != 0 &&  
					ool_pm.getValue().length() != 0 && dexp_pm.getText().length() != 0 && otv_pm.getValue().length() != 0)// && dexp_pm.getText().length() != 0
				confirm_pm.setDisable(false);
			else
				confirm_pm.setDisable(true);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
}
