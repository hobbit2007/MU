package action;

import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import application.conn_connector;
import data.FxDatePickerConverter;
import db._query;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import share_class.s_class;

public class UpdRec_WP_Controller {
	
	@FXML
	private ComboBox<String> shop_tsk, lm_tsk, os_tsk, equip_tsk, oft_tsk, otv_tsk, group_tsk;
	
	@FXML
	TextField numpm_tsk;
	
	@FXML
	TextArea description_tsk;
	
	@FXML
	DatePicker edate_tsk;
	
	@FXML
	Button explorer, pm_ap;
	
	@FXML
	Label err_msg, lbl_create_tsk_ap_upd, lbl_num_pm, lbl_type_ap, lbl_desc_ap, lbl_dd_ap, lbl_shop_ap, lbl_group_ap, lbl_lm_ap, lbl_os_ap, lbl_equip_ap, 
			lbl_oft_ap, lbl_oft_ap1, lbl_otv_ap, lbl_otv_ap1, lbl_tsk_maker_ap;
	
	@FXML
	JFXButton add_tsk_upd, cancel_tsk;
	
	@FXML
	TextField tsk_maker_ap;
	
	_query qr = new _query();
	s_class sclass = new s_class();
	apwr_controller pic = new apwr_controller();
	FxDatePickerConverter fx_dp = new FxDatePickerConverter();
	
	private Stage stage;
	Tooltip tip;
	String type_tsk;
	
	@SuppressWarnings("static-access")
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
		
		add_tsk_upd.setDisable(true);
		if(conn_connector.USER_ROLE.equals("Technics"))
			add_tsk_upd.setDisable(true);
		
		shop_tsk.setItems(qr._select_shop_pm());
		try {
			shop_tsk.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					if(shop_tsk.getValue().toString().length() != 0) {
						group_tsk.valueProperty().set(null);
						lm_tsk.valueProperty().set(null);
						os_tsk.valueProperty().set(null);
						equip_tsk.valueProperty().set(null);
						group_tsk.setItems(qr._select_group_pm(sclass.parser_str(shop_tsk.getValue(), 0)));
						add_tsk_upd.setDisable(true);
					}
						chk_btn();
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		group_tsk.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				try {
					if(group_tsk.getValue().toString().length() != 0) {
						lm_tsk.valueProperty().set(null);
						os_tsk.valueProperty().set(null);
						equip_tsk.valueProperty().set(null);
						add_tsk_upd.setDisable(true);
						lm_tsk.setItems(qr._select_lm_pm(sclass.parser_str(group_tsk.getValue(), 0)));
					}
					//if(lm_wr_add.getValue().toString().length() != 0)
					//	os_wr_add.setItems(qr._select_os_pm(sclass.parser_str(shop_wr_add.getValue(), 0), sclass.parser_str(lm_wr_add.getValue(), 0)));
					} catch (Exception e) {
						// TODO: handle exception
					}
				chk_btn();
				}
		});
		group_tsk.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip = new Tooltip(group_tsk.getValue());
				Point2D p = group_tsk.localToScreen(group_tsk.getLayoutBounds().getMaxX(), group_tsk.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(group_tsk, p.getX(), p.getY());
			}
		});
		group_tsk.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip.hide();
			}
		});

		
		lm_tsk.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					if(lm_tsk.getValue().toString().length() != 0) {
						os_tsk.valueProperty().set(null);
						equip_tsk.valueProperty().set(null);
						add_tsk_upd.setDisable(true);
						os_tsk.setItems(qr._select_os_pm(sclass.parser_str(group_tsk.getValue(), 0), sclass.parser_str(lm_tsk.getValue(), 0)));
					}
					} catch (Exception e) {
						// TODO: handle exception
					}
				chk_btn();
				}
		});
		lm_tsk.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip = new Tooltip(lm_tsk.getValue());
				Point2D p = lm_tsk.localToScreen(lm_tsk.getLayoutBounds().getMaxX(), lm_tsk.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(lm_tsk, p.getX(), p.getY());
			}
		});
		lm_tsk.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip.hide();
			}
		});
			
		
		os_tsk.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					if(os_tsk.getValue().toString().length() != 0) {
						equip_tsk.valueProperty().set(null);
						add_tsk_upd.setDisable(true);
						equip_tsk.setItems(qr._select_equip_pm(sclass.parser_str(os_tsk.getValue(), 0), sclass.parser_str(group_tsk.getValue(), 0), sclass.parser_str(lm_tsk.getValue(), 0)));
					}
					} catch (Exception e) {
					}
				chk_btn();
				}
		});
		os_tsk.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip = new Tooltip(os_tsk.getValue());
				Point2D p = os_tsk.localToScreen(os_tsk.getLayoutBounds().getMaxX(), os_tsk.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(os_tsk, p.getX(), p.getY());
			}
		});
		os_tsk.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip.hide();
			}
		});
		
		
		equip_tsk.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip = new Tooltip(equip_tsk.getValue());
				Point2D p = equip_tsk.localToScreen(equip_tsk.getLayoutBounds().getMaxX(), equip_tsk.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(equip_tsk, p.getX(), p.getY());
			}
		});
		equip_tsk.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip.hide();
			}
		});
		
		equip_tsk.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					//if(oft_tsk.getValue().toString().length() != 0)
						oft_tsk.setItems(qr._select_fio_for_ap(1, sclass.parser_str(shop_tsk.getValue(), 0)));
					} catch (Exception e) {
					}
				chk_btn();
				}
		});
		oft_tsk.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip = new Tooltip(oft_tsk.getValue());
				Point2D p = oft_tsk.localToScreen(oft_tsk.getLayoutBounds().getMaxX(), oft_tsk.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(oft_tsk, p.getX(), p.getY());
			}
		});
		oft_tsk.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip.hide();
			}
		});
		
		oft_tsk.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					//if(oft_tsk.getValue().toString().length() != 0)
						otv_tsk.setItems(qr._select_fio_for_ap(2, sclass.parser_str(shop_tsk.getValue(), 0)));
					} catch (Exception e) {
					}
				chk_btn();
			}
		});
		
		otv_tsk.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip = new Tooltip(otv_tsk.getValue());
				Point2D p = otv_tsk.localToScreen(otv_tsk.getLayoutBounds().getMaxX(), otv_tsk.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(otv_tsk, p.getX(), p.getY());
			}
		});
		otv_tsk.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip.hide();
			}
		});
		
		description_tsk.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				chk_btn();
			}
		});
		
		pm_ap.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip = new Tooltip(pm_ap.getText());
				Point2D p = pm_ap.localToScreen(pm_ap.getLayoutBounds().getMaxX(), pm_ap.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(pm_ap, p.getX(), p.getY());
			}
		});
		pm_ap.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				tip.hide();
			}
		});
		numpm_tsk.setText(pic._pmnum_wp);
		description_tsk.setText(pic._description_wp);
		type_tsk = pic._type_wp;
		shop_tsk.getSelectionModel().select(sclass.parser_str_str(pic._equip_wp, 0));
		group_tsk.getSelectionModel().select(sclass.parser_str_str(pic._equip_wp, 1));
		lm_tsk.getSelectionModel().select(sclass.parser_str_str(pic._equip_wp, 2));
		os_tsk.getSelectionModel().select(sclass.parser_str_str(pic._equip_wp, 3));
		equip_tsk.getSelectionModel().select(sclass.parser_str_str(pic._equip_wp, 4));
		tsk_maker_ap.setText(qr._select_tm_wp(pic._id_wp));
		oft_tsk.getSelectionModel().select(pic._oft_wp);
		otv_tsk.getSelectionModel().select(pic._otv_wp);
		edate_tsk.setValue(fx_dp.fromString(pic._due_date_wp));
				
		sclass._style(add_tsk_upd);
		
		//!!!!!!!!!!!����������� ��������� ����!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
		    public void handle(ActionEvent e) 
		    { 
		       	chk_btn();
			} 
		}; 
		edate_tsk.setOnAction(event);
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		add_tsk_upd.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
			//	if(type_tsk.length() != 0 && shop_tsk.getValue().length() != 0 && lm_tsk.getValue().length() != 0 && os_tsk.getValue().length() != 0 &&
			//			equip_tsk.getValue().length() != 0 && oft_tsk.getValue().length() != 0 && otv_tsk.getValue().length() != 0 && numpm_tsk.getText().length() != 0 &&
			//			description_tsk.getText().length() != 0 && edate_tsk.getValue().toString().length() != 0)
			//	{
				//	err_msg.setVisible(false);
					qr._update_rec_ap(pic._id_ap, numpm_tsk.getText(), type_tsk, description_tsk.getText(), edate_tsk.getValue(), sclass.parser_str(shop_tsk.getValue(), 0)+"."+group_tsk.getValue()+"."+sclass.parser_str(lm_tsk.getValue(), 0)+"."+sclass.parser_str(os_tsk.getValue(), 0)+"."+sclass.parser_str(equip_tsk.getValue(), 0), sclass.parser_str(oft_tsk.getValue(), 0), sclass.parser_str(otv_tsk.getValue(), 0), sclass.parser_str(shop_tsk.getValue(), 0));
					//pic.refreshTable_ap(apwr_controller.columns_ap);
					 
					qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - ������� ������ � = " + pic._id_ap + " � ������� Work Plan");
					//pic.refreshTable(pic.table_ap, pic.columns_ap, pic.row);
					pic._table_update.addAll(qr._select_data_ap(pic.USER_S));
					stage = (Stage) add_tsk_upd.getScene().getWindow();
					stage.close();
			//	}
				//else
				//	err_msg.setVisible(true);
			}
		});
		
		sclass._style(cancel_tsk);
		
		cancel_tsk.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				stage = (Stage) cancel_tsk.getScene().getWindow();
				stage.close();
			}
		});
		
	}
	
	private void lang_fun(String loc1, String loc2)
	{
		ResourceBundle lngBndl = ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
		
		lbl_create_tsk_ap_upd.setText(lngBndl.getString("lbl_create_tsk_ap_upd"));
		lbl_num_pm.setText(lngBndl.getString("lbl_num_pm"));
		lbl_type_ap.setText(lngBndl.getString("lbl_type_ap")+":");
		lbl_desc_ap.setText(lngBndl.getString("lbl_desc_ap"));
		lbl_dd_ap.setText(lngBndl.getString("lbl_dd_ap"));
		lbl_shop_ap.setText(lngBndl.getString("lbl_shop_ap"));
		lbl_group_ap.setText(lngBndl.getString("lbl_group_ap"));
		lbl_lm_ap.setText(lngBndl.getString("lbl_lm_ap"));
		lbl_os_ap.setText(lngBndl.getString("lbl_os_ap"));
		lbl_equip_ap.setText(lngBndl.getString("lbl_equip_ap"));
		lbl_oft_ap.setText(lngBndl.getString("lbl_oft_ap"));
		lbl_oft_ap1.setText(lngBndl.getString("lbl_oft_ap1"));
		lbl_otv_ap.setText(lngBndl.getString("lbl_otv_ap"));
		lbl_otv_ap1.setText(lngBndl.getString("lbl_otv_ap1"));
		lbl_tsk_maker_ap.setText(lngBndl.getString("lbl_tsk_maker_ap"));
		add_tsk_upd.setText(lngBndl.getString("lbl_apply"));
		cancel_tsk.setText(lngBndl.getString("cancel_tsk"));
	}
	
	private void chk_btn()
	{
		try {
			if(shop_tsk.getValue().length() != 0 && 
					lm_tsk.getValue().length() != 0 && 
					os_tsk.getValue().length() != 0 && 
					equip_tsk.getValue().length() != 0 &&
					oft_tsk.getValue().length() != 0 &&
					group_tsk.getValue().length() != 0 && 
					description_tsk.getText().length() != 0 &&
					edate_tsk.getValue().toString().length() != 0) //otv_tsk.getValue().length() != 0 && 
			{
				if(!conn_connector.USER_ROLE.equals("Technics"))
					add_tsk_upd.setDisable(false);
			}
			else
				add_tsk_upd.setDisable(true);
			}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
}
