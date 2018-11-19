package action;

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

public class AddRec_PartS_Controller {
	
	@FXML
	ComboBox<String> num_parts, shop_parts, group_parts, line_parts, os_parts, equip_parts;
	
	@FXML
	TextField draw_parts, pos_draw_parts, knby_parts, knbn_parts, kyby_parts, kybn_parts;
	
	@FXML
	JFXButton btn_accept, btn_cancel;
	
	@FXML
	Label lbl_title_addparts;
	
	_query qr = new _query();
	s_class scl = new s_class();
	PartSpec_Controller psc = new PartSpec_Controller();
	Tooltip tip;
	Stage stage;
	
	@SuppressWarnings("static-access")
	public void initialize()
	{
		scl._style(btn_accept);
		scl._style(btn_cancel);
		
		btn_accept.setDisable(true);
		
		num_parts.setItems(qr._select_num());
		
		shop_parts.setItems(qr._select_shop_pm());
		shop_parts.setValue(apwr_controller.SHOP_NAME);
		
		if(shop_parts.getValue().toString().length() != 0)
			group_parts.setItems(qr._select_group_pm(scl.parser_str(shop_parts.getValue(), 0)));
		
		try {
			shop_parts.getSelectionModel().selectedItemProperty().addListener(new  ChangeListener<String>() {
	
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if(shop_parts.getValue().toString().length() != 0) {
						group_parts.valueProperty().set(null);
						line_parts.valueProperty().set(null);
						os_parts.valueProperty().set(null);
						equip_parts.valueProperty().set(null);
						
						group_parts.setItems(qr._select_group_pm(scl.parser_str(shop_parts.getValue(), 0)));
					}
				}
			});
		} catch (Exception e) {
			
		}
		
		group_parts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				try {
					if(group_parts.getValue().toString().length() != 0) {
						line_parts.valueProperty().set(null);
						os_parts.valueProperty().set(null);
						equip_parts.valueProperty().set(null);
						
						line_parts.setItems(qr._select_lm_pm(scl.parser_str(group_parts.getValue(), 0)));
					}
					
					} catch (Exception e) {
						
					}
			}
		});
		group_parts.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(group_parts.getValue());
				Point2D p = group_parts.localToScreen(group_parts.getLayoutBounds().getMaxX(), group_parts.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(group_parts, p.getX(), p.getY());
			}
		});
		group_parts.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		line_parts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					if(line_parts.getValue().toString().length() != 0) {
						os_parts.valueProperty().set(null);
						equip_parts.valueProperty().set(null);
						
						os_parts.setItems(qr._select_os_pm(scl.parser_str(group_parts.getValue(), 0), scl.parser_str(line_parts.getValue(), 0)));
					}
					} catch (Exception e) {
						
					}
			}
		});
		line_parts.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(line_parts.getValue());
				Point2D p = line_parts.localToScreen(line_parts.getLayoutBounds().getMaxX(), line_parts.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(line_parts, p.getX(), p.getY());
			}
		});
		line_parts.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
			
		
		os_parts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					if(os_parts.getValue().toString().length() != 0) {
						equip_parts.valueProperty().set(null);
						
						equip_parts.setItems(qr._select_equip_pm(scl.parser_str(os_parts.getValue(), 0), scl.parser_str(group_parts.getValue(), 0), scl.parser_str(line_parts.getValue(), 0)));
					}
					} catch (Exception e) {
					}
			}
		});
		os_parts.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(os_parts.getValue());
				Point2D p = os_parts.localToScreen(os_parts.getLayoutBounds().getMaxX(), os_parts.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(os_parts, p.getX(), p.getY());
			}
		});
		os_parts.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		equip_parts.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(equip_parts.getValue());
				Point2D p = equip_parts.localToScreen(equip_parts.getLayoutBounds().getMaxX(), equip_parts.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(equip_parts, p.getX(), p.getY());
			}
		});
		equip_parts.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		if(!psc._flag_window_parts)
		{
			String _sql_rez = qr._select_for_update_parts(psc._id_parts);
			
			num_parts.getSelectionModel().select(scl.parser_str_str_str(_sql_rez, 0));
			
			shop_parts.getSelectionModel().select(scl.parser_str_str(scl.parser_str_str_str(_sql_rez, 1), 0));
			group_parts.getSelectionModel().select(scl.parser_str_str(scl.parser_str_str_str(_sql_rez, 1), 1));
			line_parts.getSelectionModel().select(scl.parser_str_str(scl.parser_str_str_str(_sql_rez, 1), 2));
			os_parts.getSelectionModel().select(scl.parser_str_str(scl.parser_str_str_str(_sql_rez, 1), 3));
			equip_parts.getSelectionModel().select(scl.parser_str_str(scl.parser_str_str_str(_sql_rez, 1), 4));
			draw_parts.setText(scl.parser_str_str_str(_sql_rez, 2));
			pos_draw_parts.setText(scl.parser_str_str_str(_sql_rez, 3));
			knby_parts.setText(scl.parser_str_str_str(_sql_rez, 4));
			knbn_parts.setText(scl.parser_str_str_str(_sql_rez, 5));
			kyby_parts.setText(scl.parser_str_str_str(_sql_rez, 6));
			kybn_parts.setText(scl.parser_str_str_str(_sql_rez, 7));
			
			lbl_title_addparts.setText("Обновить запись Parts Specification");
		}
		else
			lbl_title_addparts.setText("Добавить запись к Parts Specification");
		
		shop_parts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		group_parts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		line_parts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		os_parts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		equip_parts.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		draw_parts.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		pos_draw_parts.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		knby_parts.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		knbn_parts.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		kyby_parts.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		kybn_parts.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				chk_btn();
			}
		});
		
		btn_accept.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				if(psc._flag_window_parts) {
					qr._insert_parts(scl.parser_str(num_parts.getValue(), 0), scl.parser_str(shop_parts.getValue(), 0)+"."+scl.parser_str(group_parts.getValue(), 0)+"."+scl.parser_str(line_parts.getValue(), 0)+"."+scl.parser_str(os_parts.getValue(), 0)+"."+scl.parser_str(equip_parts.getValue(), 0), draw_parts.getText(), pos_draw_parts.getText(), knby_parts.getText(), knbn_parts.getText(), kyby_parts.getText(), kybn_parts.getText());
					qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Создал запись № = " + qr._select_last_id("hmmr_parts_spec") + " в Parts Specification");
				}
				else
				{
					qr._update_parts(psc._id_parts, scl.parser_str(num_parts.getValue(), 0), scl.parser_str(shop_parts.getValue(), 0)+"."+scl.parser_str(group_parts.getValue(), 0)+"."+scl.parser_str(line_parts.getValue(), 0)+"."+scl.parser_str(os_parts.getValue(), 0)+"."+scl.parser_str(equip_parts.getValue(), 0), draw_parts.getText(), pos_draw_parts.getText(), knby_parts.getText(), knbn_parts.getText(), kyby_parts.getText(), kybn_parts.getText());
					qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Обновил запись № = " + qr._select_last_id("hmmr_parts_spec") + " в Parts Specification");
				}
					psc._table_update_parts.addAll(qr._select_data_parts());
					
					stage = (Stage) btn_accept.getScene().getWindow();
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
			if(num_parts.getValue().length() != 0 && shop_parts.getValue().length() != 0 && group_parts.getValue().length() != 0 && line_parts.getValue().length() != 0 && 
					os_parts.getValue().length() != 0 && equip_parts.getValue().length() != 0 && draw_parts.getText().length() != 0 && pos_draw_parts.getText().length() != 0 && 
					knby_parts.getText().length() != 0 && knbn_parts.getText().length() != 0 && kyby_parts.getText().length() != 0 && kybn_parts.getText().length() != 0)
				btn_accept.setDisable(false);
			else
				btn_accept.setDisable(true);
		}
		catch (Exception e) {
			
		}
	}

}
