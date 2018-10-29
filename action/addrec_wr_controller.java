package action;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTimePicker;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import share_class.s_class;

public class addrec_wr_controller {
	
	@FXML
	TextArea shift_report_wr_add, req_action_wr_add;
	
	@FXML
	TextField actual_time_wr_add, actual_time1_wr_add1, actual_time1_wr_add2, actual_time1_wr_add3, actual_time1_wr_add, numap_wr_add;// wmb, wsb, wme, wse;
	
	@FXML
	DatePicker w_data_begin, w_data_begin1, w_data_begin2, w_data_begin3, w_data_begin15, w_data_end, w_data_end1, w_data_end2, w_data_end3, w_data_end15;
	
	@FXML 
	ComboBox<String> shop_wr_add, lm_wr_add, os_wr_add, equip_wr_add, record_type_wr_add, resp_wr_add, status_wr_add, group_wr_add, resp_wr_add1, resp_wr_add2, resp_wr_add3, list_at_wr;
	
	@FXML
	Label err_msg, lbl_add_rec_wr, shift_report_wr, req_action_wr, lbl_trt_wr, actual_time_wr, actual_time1_wr, 
	lbl_shop_ap, lbl_group_ap, lbl_lm_ap, lbl_os_ap, lbl_equip_ap, record_type_wr, resp_wr, status_wr, lbl_trt_wr1, lbl_trt_wr2, lbl_trt_wr3, actual_time1_wr1,
	actual_time1_wr2, actual_time1_wr3, resp_wr1, resp_wr2, resp_wr3, lbl_at_wr;
	
	@FXML
	JFXButton add_wr_add, cancel_wr_add, plus, plus1, plus2, minus1, minus2, minus3;
	
	@FXML
	JFXTimePicker b_picker, b_picker1, b_picker2, b_picker3, b_picker15, e_picker, e_picker1, e_picker2, e_picker3, e_picker15;
	
	@FXML
	Pane pane;
	
	_query qr = new _query();
	s_class sclass = new s_class();
	apwr_controller pic = new apwr_controller();
	FxDatePickerConverter fx_dp = new FxDatePickerConverter();
	FxDatePickerConverter fx_time = new FxDatePickerConverter("HH:mm");
	private Stage stage;
	Tooltip tip;
	double wt_rezult = 0;
	private String b_gdw, e_gdw, b_gtw, e_gtw;
	int OtId = 0;
		
	@SuppressWarnings("static-access")
	@FXML
	public void initialize()
	{
		if(conn_connector.LANG_ID == 1) {
			lang_fun("en", "EN");
			w_data_begin.setPromptText("from:");
			w_data_end.setPromptText("to:");
		}
		if(conn_connector.LANG_ID == 0) {
			lang_fun("ru", "RU");
			w_data_begin.setPromptText("с:");
			w_data_end.setPromptText("по:");
		}
		if(conn_connector.LANG_ID == 2) {
			lang_fun("zh", "CN");
			w_data_begin.setPromptText("с:");
			w_data_end.setPromptText("по:");
		}
		if(conn_connector.LANG_ID == -1) {
			lang_fun("ru", "RU");
			w_data_begin.setPromptText("с:");
			w_data_end.setPromptText("по:");
		}
		
		//if(conn_connector.USER_ROLE.equals("Technics"))
		//	status_wr_add.setDisable(true);
		
		b_picker._24HourViewProperty();
		b_picker.setIs24HourView(true);
		
		e_picker._24HourViewProperty();
		e_picker.setIs24HourView(true);
		
		b_picker1._24HourViewProperty();
		b_picker1.setIs24HourView(true);
		
		e_picker1._24HourViewProperty();
		e_picker1.setIs24HourView(true);
		
		b_picker2._24HourViewProperty();
		b_picker2.setIs24HourView(true);
		
		e_picker2._24HourViewProperty();
		e_picker2.setIs24HourView(true);
		
		b_picker3._24HourViewProperty();
		b_picker3.setIs24HourView(true);
		
		e_picker3._24HourViewProperty();
		e_picker3.setIs24HourView(true);
		
		e_picker15._24HourViewProperty();
		e_picker15.setIs24HourView(true);
		
		add_wr_add.setDisable(true);
		
		actual_time_wr_add.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					actual_time_wr_add.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 4) {
					
					actual_time_wr_add.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
				}
			}
		});
		actual_time1_wr_add.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					actual_time1_wr_add.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 4) {
					
					actual_time1_wr_add.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
				}
			}
		});
		
		actual_time1_wr_add1.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					actual_time1_wr_add1.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 4) {
					
					actual_time1_wr_add1.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
				}
			}
		});
		actual_time1_wr_add2.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					actual_time1_wr_add2.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 4) {
					
					actual_time1_wr_add2.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
				}
			}
		});
		actual_time1_wr_add3.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					actual_time1_wr_add3.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 4) {
					
					actual_time1_wr_add3.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
				}
			}
		});
		
		list_at_wr.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				
					if(record_type_wr_add.getValue().equals("TSK"))
						OtId = 3;
					if(record_type_wr_add.getValue().equals("CM"))
						OtId = 2;
					list_at_wr.setItems(qr._select_recArr("hmmr_activity_type", "Name", "Description", "del_rec", "ID_OT", OtId));
				
			}
		});
		
		add_wr_add.setDisable(true);
		
		w_data_begin.setValue(LocalDate.now());
		w_data_end.setValue(LocalDate.now());
		b_picker.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now())));
		e_picker.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now().plusHours(1))));
		
		w_data_begin1.setValue(LocalDate.now());
		w_data_end1.setValue(LocalDate.now());
		b_picker1.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now())));
		e_picker1.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now().plusHours(1))));
		
		w_data_begin2.setValue(LocalDate.now());
		w_data_end2.setValue(LocalDate.now());
		b_picker2.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now())));
		e_picker2.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now().plusHours(1))));
		
		w_data_begin3.setValue(LocalDate.now());
		w_data_end3.setValue(LocalDate.now());
		b_picker3.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now())));
		e_picker3.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now().plusHours(1))));
		
		w_data_begin15.setValue(LocalDate.now());
		w_data_end15.setValue(LocalDate.now());
		b_picker15.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now())));
		e_picker15.setValue(LocalTime.parse(fx_time.toStringt(LocalTime.now().plusHours(1))));
		
		actual_time1_wr_add1.setText("0");
		actual_time1_wr_add2.setText("0");
		actual_time1_wr_add3.setText("0");
		
		resp_wr_add1.setValue("0");
		resp_wr_add2.setValue("0");
		resp_wr_add3.setValue("0");
		
		
		//инициализируем комбобоксы
		String line1 = new String("PM");
		String line2 = new String("TSK");
		String line3 = new String("CM");
		record_type_wr_add.getItems().addAll(line1, line2, line3);
		
		String line4 = new String("New WR");
		String line5 = new String("Confirmed WR");
		status_wr_add.getItems().addAll(line4, line5);
		status_wr_add.setValue(line5);
		
		shop_wr_add.setItems(qr._select_shop_pm());
		try {
			shop_wr_add.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					
					if(shop_wr_add.getValue().toString().length() != 0) {
						group_wr_add.valueProperty().set(null);
						lm_wr_add.valueProperty().set(null);
						os_wr_add.valueProperty().set(null);
						equip_wr_add.valueProperty().set(null);
						add_wr_add.setDisable(true);
						group_wr_add.setItems(qr._select_group_pm(sclass.parser_str(shop_wr_add.getValue(), 0)));
					}
						//lm_wr_add.setItems(qr._select_lm_pm(sclass.parser_str(shop_wr_add.getValue(), 0)));
					chk_btn();
				}
			});
		} catch (Exception e) {
			s_class._AlertDialog(e.getMessage() + "Ошибка в строке № 258 addrec_wr_controller", "Ошибка!");
		}
		
		group_wr_add.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				try {
					if(group_wr_add.getValue().toString().length() != 0) {
						lm_wr_add.valueProperty().set(null);
						os_wr_add.valueProperty().set(null);
						equip_wr_add.valueProperty().set(null);
						add_wr_add.setDisable(true);
						lm_wr_add.setItems(qr._select_lm_pm(sclass.parser_str(group_wr_add.getValue(), 0)));
					}
					//if(lm_wr_add.getValue().toString().length() != 0)
					//	os_wr_add.setItems(qr._select_os_pm(sclass.parser_str(shop_wr_add.getValue(), 0), sclass.parser_str(lm_wr_add.getValue(), 0)));
					} catch (Exception e) {
						
					}
				chk_btn();
			}
		});
		group_wr_add.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(group_wr_add.getValue());
				Point2D p = group_wr_add.localToScreen(group_wr_add.getLayoutBounds().getMaxX(), group_wr_add.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(group_wr_add, p.getX(), p.getY());
			}
		});
		group_wr_add.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		lm_wr_add.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				try {
					if(lm_wr_add.getValue().toString().length() != 0) {
						os_wr_add.valueProperty().set(null);
						equip_wr_add.valueProperty().set(null);
						add_wr_add.setDisable(true);
						os_wr_add.setItems(qr._select_os_pm(sclass.parser_str(group_wr_add.getValue(), 0), sclass.parser_str(lm_wr_add.getValue(), 0)));
					}
					} catch (Exception e) {
					}
				chk_btn();
			}
		});
		lm_wr_add.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(lm_wr_add.getValue());
				Point2D p = lm_wr_add.localToScreen(lm_wr_add.getLayoutBounds().getMaxX(), lm_wr_add.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(lm_wr_add, p.getX(), p.getY());
			}
		});
		lm_wr_add.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
			
		
		os_wr_add.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				try {
					if(os_wr_add.getValue().toString().length() != 0) {
						equip_wr_add.valueProperty().set(null);
						add_wr_add.setDisable(true);
						equip_wr_add.setItems(qr._select_equip_pm(sclass.parser_str(os_wr_add.getValue(), 0), sclass.parser_str(group_wr_add.getValue(), 0), sclass.parser_str(lm_wr_add.getValue(), 0)));
					}
					} catch (Exception e) {
					}
				chk_btn();
			}
		});
		os_wr_add.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(os_wr_add.getValue());
				Point2D p = os_wr_add.localToScreen(os_wr_add.getLayoutBounds().getMaxX(), os_wr_add.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(os_wr_add, p.getX(), p.getY());
			}
		});
		os_wr_add.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		
		equip_wr_add.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(equip_wr_add.getValue());
				Point2D p = equip_wr_add.localToScreen(equip_wr_add.getLayoutBounds().getMaxX(), equip_wr_add.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(equip_wr_add, p.getX(), p.getY());
			}
		});
		equip_wr_add.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		equip_wr_add.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					try {
					//if(oft_tsk.getValue().toString().length() != 0)
						resp_wr_add.setItems(qr._select_fio_for_ap(2, sclass.parser_str(shop_wr_add.getValue(), 0)));
						resp_wr_add1.setItems(qr._select_fio_for_ap(2, sclass.parser_str(shop_wr_add.getValue(), 0)));
						resp_wr_add2.setItems(qr._select_fio_for_ap(2, sclass.parser_str(shop_wr_add.getValue(), 0)));
						resp_wr_add3.setItems(qr._select_fio_for_ap(2, sclass.parser_str(shop_wr_add.getValue(), 0)));
					} catch (Exception e) {
					}
					chk_btn();
			}
		});
		resp_wr_add.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				chk_btn();
			}
		});
		resp_wr_add.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(resp_wr_add.getValue());
				Point2D p = resp_wr_add.localToScreen(resp_wr_add.getLayoutBounds().getMaxX(), resp_wr_add.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(resp_wr_add, p.getX(), p.getY());
			}
		});
		resp_wr_add.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		status_wr_add.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				chk_btn();
			}
		});
		if(!pic._idap_for_wr.equals("null")) {
			numap_wr_add.setText(pic._idap_for_wr);
			shift_report_wr_add.setText(pic._description_ap);
			record_type_wr_add.getSelectionModel().select(pic._type_ap);
			shop_wr_add.getSelectionModel().select(sclass.parser_str_str(pic._equip_ap, 0));
			group_wr_add.getSelectionModel().select(sclass.parser_str_str(pic._equip_ap, 1));
			lm_wr_add.getSelectionModel().select(sclass.parser_str_str(pic._equip_ap, 2));
			os_wr_add.getSelectionModel().select(sclass.parser_str_str(pic._equip_ap, 3));
			equip_wr_add.getSelectionModel().select(sclass.parser_str_str(pic._equip_ap, 4));
			resp_wr_add.getSelectionModel().select(pic._otv_ap);
			list_at_wr.getSelectionModel().select(pic._icon_at);
						
			if(record_type_wr_add.getValue().equals("PM") || record_type_wr_add.getValue().equals("TSK"))
				actual_time_wr_add.setText("0");
			
			if(record_type_wr_add.getValue().equals("CM"))
				chk_wt(pic._idap_for_wr.substring(2));
		}
		
		shift_report_wr_add.setOnKeyPressed(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				chk_btn();
			}
		});
		req_action_wr_add.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				chk_btn();
			}
		});
		actual_time_wr_add.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				chk_btn();
			}
		});
		actual_time1_wr_add.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				chk_btn();
			}
		});
		list_at_wr.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		
		sclass._style(add_wr_add);
		
		add_wr_add.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				/*if(shift_report_wr_add.getText().length() != 0 && req_action_wr_add.getText().length() != 0 && actual_time_wr_add.getText().length() != 0 &&
						actual_time1_wr_add.getText().length() != 0 && numap_wr_add.getText().length() != 0 && data_wr_add.getValue().toString().length() != 0 &&
						shop_wr_add.getValue().length() != 0 && group_wr_add.getValue().length() != 0 && lm_wr_add.getValue().length() != 0 && os_wr_add.getValue().length() != 0 &&
						equip_wr_add.getValue().length() != 0 && record_type_wr_add.getValue().length() != 0 && resp_wr_add.getValue().length() != 0 &&
						status_wr_add.getValue().length() != 0)
				{*/
				if(record_type_wr_add.getValue().equals("CM")) {
					if(!qr._select_confirm_wt(pic._idap_for_wr.substring(2)).equals("YES")) {
						
						b_gdw = fx_dp.toString(w_data_begin.getValue());
						e_gdw = fx_dp.toString(w_data_end.getValue());
						b_gtw = fx_time.toStringt(b_picker15.getValue());
						e_gtw = fx_time.toStringt(e_picker15.getValue());
						
						//Ввычисляем время сколько занял ремонт
						int d_b = w_data_begin.getValue().getDayOfYear();
						int d_e = w_data_end.getValue().getDayOfYear();
						
						int data_rezult = d_e - d_b;
						//Обрезаем секунды
						//int hours_b = Integer.parseInt(sclass.parser_double_dot(b_picker.getValue().toString(), 0));
						//int min_b = Integer.parseInt(sclass.parser_double_dot(b_picker.getValue().toString(), 1));
						//int hours_e = Integer.parseInt(sclass.parser_double_dot(e_picker.getValue().toString(), 0));
						//int min_e = Integer.parseInt(sclass.parser_double_dot(e_picker.getValue().toString(), 1));
						
						//int time_rezult = Math.abs(hours_e - hours_b)*60 + Math.abs(min_e - min_b);
						LocalTime test = b_picker15.getValue();
						LocalTime test2 = e_picker15.getValue();
						
						//long h_between = ChronoUnit.HOURS.between(test, test2);
						long m_between = ChronoUnit.MINUTES.between(test, test2);
						
						int time_rezult = Math.abs((int) m_between);
												
						//wt_rezult = data_rezult*24 + time_rezult/60.d;
						wt_rezult = data_rezult*24 + time_rezult/60.d;
					}
					else 
						wt_rezult = 0;
				}
				else
				{
					b_gdw = "0000-00-00";
					e_gdw = "0000-00-00";
					b_gtw = "00:00";
					e_gtw = "00:00";
				}
				
				if(!record_type_wr_add.getValue().equals("CM")) {
					list_at_wr.setValue(qr._select_rec("hmmr_action_plan", "Icon_AT", "del_rec", "id", numap_wr_add.getText().substring(2)));
				}
				else
					qr._update_rec_ap_iconat(sclass.parser_str(list_at_wr.getValue(), 0), numap_wr_add.getText().substring(2));
					
				qr._insert_wr(numap_wr_add.getText().substring(2), conn_connector.USER_ID, sclass.parser_str(shop_wr_add.getValue(), 0), group_wr_add.getValue(), sclass.parser_str(lm_wr_add.getValue(), 0), sclass.parser_str(os_wr_add.getValue(), 0), sclass.parser_str(equip_wr_add.getValue(), 0), sclass.parser_str(shop_wr_add.getValue(), 0)+"."+group_wr_add.getValue()+"."+sclass.parser_str(lm_wr_add.getValue(), 0)+"."+sclass.parser_str(os_wr_add.getValue(), 0)+"."+sclass.parser_str(equip_wr_add.getValue(), 0), record_type_wr_add.getValue(), wt_rezult, sclass.parser_str(resp_wr_add.getValue(), 0), sclass.parser_str(resp_wr_add1.getValue(), 0), sclass.parser_str(resp_wr_add2.getValue(), 0), sclass.parser_str(resp_wr_add3.getValue(), 0), status_wr_add.getValue(), shift_report_wr_add.getText(), req_action_wr_add.getText(), w_data_begin.getValue(), w_data_begin1.getValue(), w_data_begin2.getValue(), w_data_begin3.getValue(), actual_time_wr_add.getText(), w_data_end.getValue(), w_data_end1.getValue(), w_data_end2.getValue(), w_data_end3.getValue(), actual_time1_wr_add.getText(), actual_time1_wr_add1.getText(), actual_time1_wr_add2.getText(), actual_time1_wr_add3.getText(), b_picker.getValue(), b_picker1.getValue(), b_picker2.getValue(), b_picker3.getValue(), e_picker.getValue(), e_picker1.getValue(), e_picker2.getValue(), e_picker3.getValue(), b_gdw, e_gdw, b_gtw, e_gtw, sclass.parser_str(list_at_wr.getValue(), 0));
				//pic.refreshTable_wr(apwr_controller.columns_wr, apwr_controller.before_date, apwr_controller.after_date);
					
				qr._insert_history(conn_connector.USER_ID, pic.USER_S + " - Добавил запись № = " + qr._select_last_id("hmmr_work_recording") + " в таблице Work Recording");
				//Если мы создаем новую запись в WR по задаче из AP то меняем цвет для поля исполнитель на желтый, т.к. эта новая запись еще не проверенна
				qr._update_otv_ap(pic._idap_for_wr.substring(2), "flag_otv", "1");
				qr._update_otv_ap(pic._idap_for_wr.substring(2), "flag_oft", "0");
				qr._update_otv_ap(pic._idap_for_wr.substring(2), "flag_tm", "0");
					
				String id_wr = qr._select_last_id("hmmr_work_recording");
				//В зависимости от количества исполнителей указанных в записи WR создаем такое же количество записей в WR
				if(!qr._select_resp(id_wr, "_Resp2").equals("0"))
				{
					LocalDate b_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date_2"));
					LocalDate e_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date2"));
					LocalTime b_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours1_2"));
					LocalTime e_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours2_2"));
					String at = qr._select_b_hours(id_wr, "_Actual_Time2");
					//Если СМ то для всех остальных кроме первого техника время общего ремонта = 0
					wt_rezult = 0;
						
					qr._insert_wr(numap_wr_add.getText().substring(2), conn_connector.USER_ID, sclass.parser_str(shop_wr_add.getValue(), 0), group_wr_add.getValue(), sclass.parser_str(lm_wr_add.getValue(), 0), sclass.parser_str(os_wr_add.getValue(), 0), sclass.parser_str(equip_wr_add.getValue(), 0), sclass.parser_str(shop_wr_add.getValue(), 0)+"."+group_wr_add.getValue()+"."+sclass.parser_str(lm_wr_add.getValue(), 0)+"."+sclass.parser_str(os_wr_add.getValue(), 0)+"."+sclass.parser_str(equip_wr_add.getValue(), 0), record_type_wr_add.getValue(), wt_rezult, sclass.parser_str(qr._select_resp(id_wr, "_Resp2"), 0), "0", "0", "0", status_wr_add.getValue(), shift_report_wr_add.getText(), req_action_wr_add.getText(), b_data, w_data_begin.getValue(), w_data_begin2.getValue(), w_data_begin3.getValue(), actual_time_wr_add.getText(), e_data, w_data_end.getValue(), w_data_end2.getValue(), w_data_end3.getValue(), at, "0", "0", "0", b_time, b_picker.getValue(), b_picker2.getValue(), b_picker3.getValue(), e_time, e_picker.getValue(), e_picker2.getValue(), e_picker3.getValue(), b_gdw, e_gdw, b_gtw, e_gtw, sclass.parser_str(list_at_wr.getValue(), 0));
					qr._update_r_wr(id_wr, "_Resp2", "0");
					qr._update_r_wr(id_wr, "_Actual_Time2", "0");
				}
				if(!qr._select_resp(id_wr, "_Resp3").equals("0"))
				{
					LocalDate b_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date_3"));
					LocalDate e_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date3"));
					LocalTime b_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours1_3"));
					LocalTime e_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours2_3"));
					String at = qr._select_b_hours(id_wr, "_Actual_Time3");
					wt_rezult = 0;
						
					qr._insert_wr(numap_wr_add.getText().substring(2), conn_connector.USER_ID, sclass.parser_str(shop_wr_add.getValue(), 0), group_wr_add.getValue(), sclass.parser_str(lm_wr_add.getValue(), 0), sclass.parser_str(os_wr_add.getValue(), 0), sclass.parser_str(equip_wr_add.getValue(), 0), sclass.parser_str(shop_wr_add.getValue(), 0)+"."+group_wr_add.getValue()+"."+sclass.parser_str(lm_wr_add.getValue(), 0)+"."+sclass.parser_str(os_wr_add.getValue(), 0)+"."+sclass.parser_str(equip_wr_add.getValue(), 0), record_type_wr_add.getValue(), wt_rezult, sclass.parser_str(qr._select_resp(id_wr, "_Resp3"), 0), "0", "0", "0", status_wr_add.getValue(), shift_report_wr_add.getText(), req_action_wr_add.getText(), b_data, w_data_begin.getValue(), w_data_begin2.getValue(), w_data_begin3.getValue(), actual_time_wr_add.getText(), e_data, w_data_end.getValue(), w_data_end2.getValue(), w_data_end3.getValue(), at, "0", "0", "0", b_time, b_picker.getValue(), b_picker2.getValue(), b_picker3.getValue(), e_time, e_picker.getValue(), e_picker2.getValue(), e_picker3.getValue(), b_gdw, e_gdw, b_gtw, e_gtw, sclass.parser_str(list_at_wr.getValue(), 0));
					qr._update_r_wr(id_wr, "_Resp3", "0");
					qr._update_r_wr(id_wr, "_Actual_Time3", "0");
				}
				if(!qr._select_resp(id_wr, "_Resp4").equals("0"))
				{
					LocalDate b_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date_4"));
					LocalDate e_data = fx_dp.fromString(qr._select_b_hours(id_wr, "_Actual_Date4"));
					LocalTime b_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours1_4"));
					LocalTime e_time = fx_time.fromStringt(qr._select_b_hours(id_wr, "_Hours2_4"));
					String at = qr._select_b_hours(id_wr, "_Actual_Time4");
					wt_rezult = 0;
						
					qr._insert_wr(numap_wr_add.getText().substring(2), conn_connector.USER_ID, sclass.parser_str(shop_wr_add.getValue(), 0), group_wr_add.getValue(), sclass.parser_str(lm_wr_add.getValue(), 0), sclass.parser_str(os_wr_add.getValue(), 0), sclass.parser_str(equip_wr_add.getValue(), 0), sclass.parser_str(shop_wr_add.getValue(), 0)+"."+group_wr_add.getValue()+"."+sclass.parser_str(lm_wr_add.getValue(), 0)+"."+sclass.parser_str(os_wr_add.getValue(), 0)+"."+sclass.parser_str(equip_wr_add.getValue(), 0), record_type_wr_add.getValue(), wt_rezult, sclass.parser_str(qr._select_resp(id_wr, "_Resp4"), 0), "0", "0", "0", status_wr_add.getValue(), shift_report_wr_add.getText(), req_action_wr_add.getText(), b_data, w_data_begin.getValue(), w_data_begin2.getValue(), w_data_begin3.getValue(), actual_time_wr_add.getText(), e_data, w_data_end.getValue(), w_data_end2.getValue(), w_data_end3.getValue(), at, "0", "0", "0", b_time, b_picker.getValue(), b_picker2.getValue(), b_picker3.getValue(), e_time, e_picker.getValue(), e_picker2.getValue(), e_picker3.getValue(), b_gdw, e_gdw, b_gtw, e_gtw, sclass.parser_str(list_at_wr.getValue(), 0));
					qr._update_r_wr(id_wr, "_Resp4", "0");
					qr._update_r_wr(id_wr, "_Actual_Time4", "0");
				}
				//рефрешим таблицу AP
				pic._table_update_wr.addAll(qr._select_data_wr(apwr_controller.before_date, apwr_controller.after_date));
				pic._table_update.addAll(qr._select_data_ap(pic.USER_S));
				//Заполняем таблицу данными в зависимости от условия сортировкия, которое было выбранно
				if(pic.flag == 1)
					pic._table_update_wr.addAll(qr._select_sort_apnum_wr(id_wr.substring(2)));
				if(pic.flag == 2)
					pic._table_update_wr.addAll(qr._select_data_wr(apwr_controller.before_date, apwr_controller.after_date));
				if(pic.flag == 0)
					pic._table_update_wr.addAll(qr._select_data_wr(apwr_controller.before_date, apwr_controller.after_date));
				if(pic.flag == 3)
					pic._table_update_wr.addAll(qr._select_sort_shop_wr(apwr_controller.before_date, apwr_controller.after_date, pic.SORT_SHOP));
				if(pic.flag == 4)
					pic._table_update_wr.addAll(qr._select_sort_resp_wr(apwr_controller.before_date, apwr_controller.after_date, sclass.parser_str(pic.SORT_RESP, 0)));
									
				stage = (Stage) add_wr_add.getScene().getWindow();
				stage.close();
				//}
				//else
				//	err_msg.setVisible(true);
			}
		});
		
		sclass._style(cancel_wr_add);
		
		cancel_wr_add.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				stage = (Stage) add_wr_add.getScene().getWindow();
				stage.close();
			}
		});
		
		/*sclass._style(plus);
		sclass._style(plus1);
		sclass._style(plus2);
		sclass._style(minus1);
		sclass._style(minus2);
		sclass._style(minus3);*/
		
		plus.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				lbl_trt_wr1.setDisable(false);
				w_data_begin1.setDisable(false);
				b_picker1.setDisable(false);
				w_data_end1.setDisable(false);
				e_picker1.setDisable(false);
				resp_wr1.setDisable(false);
				resp_wr_add1.setDisable(false);
				actual_time1_wr1.setDisable(false);
				actual_time1_wr_add1.setDisable(true);
				plus1.setDisable(false);
				minus1.setDisable(false);
				plus.setDisable(true);
				
				w_data_begin1.setValue(w_data_begin.getValue());
				w_data_end1.setValue(w_data_end.getValue());
				b_picker1.setValue(b_picker.getValue());
				e_picker1.setValue(e_picker.getValue());
				
				actual_time1_wr_add1.setText(""+fix_time(w_data_begin1, w_data_end1, b_picker1, e_picker1));
			}
		});
		minus1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				lbl_trt_wr1.setDisable(true);
				w_data_begin1.setDisable(true);
				b_picker1.setDisable(true);
				w_data_end1.setDisable(true);
				e_picker1.setDisable(true);
				resp_wr1.setDisable(true);
				resp_wr_add1.setDisable(true);
				actual_time1_wr1.setDisable(true);
				actual_time1_wr_add1.setDisable(true);
				plus1.setDisable(true);
				minus1.setDisable(true);
				plus.setDisable(false);
			}
		});
		plus1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				lbl_trt_wr2.setDisable(false);
				w_data_begin2.setDisable(false);
				b_picker2.setDisable(false);
				w_data_end2.setDisable(false);
				e_picker2.setDisable(false);
				resp_wr2.setDisable(false);
				resp_wr_add2.setDisable(false);
				actual_time1_wr2.setDisable(false);
				actual_time1_wr_add2.setDisable(true);
				plus2.setDisable(false);
				minus2.setDisable(false);
				minus1.setDisable(true);
				plus.setDisable(true);
				plus1.setDisable(true);
				
				w_data_begin2.setValue(w_data_begin.getValue());
				w_data_end2.setValue(w_data_end.getValue());
				b_picker2.setValue(b_picker.getValue());
				e_picker2.setValue(e_picker.getValue());
				
				actual_time1_wr_add2.setText(""+fix_time(w_data_begin2, w_data_end2, b_picker2, e_picker2));
			}
		});
		minus2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				lbl_trt_wr2.setDisable(true);
				w_data_begin2.setDisable(true);
				b_picker2.setDisable(true);
				w_data_end2.setDisable(true);
				e_picker2.setDisable(true);
				resp_wr2.setDisable(true);
				resp_wr_add2.setDisable(true);
				actual_time1_wr2.setDisable(true);
				actual_time1_wr_add2.setDisable(true);
				plus1.setDisable(false);
				minus1.setDisable(false);
				plus.setDisable(false);
				plus2.setDisable(true);
				minus2.setDisable(true);
			}
		});
		plus2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				lbl_trt_wr3.setDisable(false);
				w_data_begin3.setDisable(false);
				b_picker3.setDisable(false);
				w_data_end3.setDisable(false);
				e_picker3.setDisable(false);
				resp_wr3.setDisable(false);
				resp_wr_add3.setDisable(false);
				actual_time1_wr3.setDisable(false);
				actual_time1_wr_add3.setDisable(true);
				plus2.setDisable(false);
				minus2.setDisable(true);
				minus3.setDisable(false);
				plus.setDisable(true);
				plus1.setDisable(true);
				plus2.setDisable(true);
				
				w_data_begin3.setValue(w_data_begin.getValue());
				w_data_end3.setValue(w_data_end.getValue());
				b_picker3.setValue(b_picker.getValue());
				e_picker3.setValue(e_picker.getValue());
				
				actual_time1_wr_add3.setText(""+fix_time(w_data_begin3, w_data_end3, b_picker3, e_picker3));
			}
		});
		minus3.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				lbl_trt_wr3.setDisable(true);
				w_data_begin3.setDisable(true);
				b_picker3.setDisable(true);
				w_data_end3.setDisable(true);
				e_picker3.setDisable(true);
				resp_wr3.setDisable(true);
				resp_wr_add3.setDisable(true);
				actual_time1_wr3.setDisable(true);
				actual_time1_wr_add3.setDisable(true);
				//plus1.setDisable(false);
				//minus1.setDisable(false);
				//plus.setDisable(false);
				plus2.setDisable(false);
				minus2.setDisable(false);
				minus3.setDisable(true);
			}
		});
		e_picker.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				actual_time1_wr_add.setText(""+ fix_time(w_data_begin, w_data_end, b_picker, e_picker));
				chk_btn();
			}
		});
		e_picker1.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				actual_time1_wr_add1.setText(""+ fix_time(w_data_begin1, w_data_end1, b_picker1, e_picker1));
			}
		});
		e_picker2.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				actual_time1_wr_add2.setText(""+ fix_time(w_data_begin2, w_data_end2, b_picker2, e_picker2));
			}
		});
		e_picker3.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				actual_time1_wr_add3.setText(""+ fix_time(w_data_begin3, w_data_end3, b_picker3, e_picker3));
			}
		});
		//Пересчитываем время работы техника, если кликнули не только по часикам, но и в любом месте панели
		pane.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				actual_time1_wr_add.setText(""+ fix_time(w_data_begin, w_data_end, b_picker, e_picker));
				actual_time1_wr_add1.setText(""+ fix_time(w_data_begin1, w_data_end1, b_picker1, e_picker1));
				actual_time1_wr_add2.setText(""+ fix_time(w_data_begin2, w_data_end2, b_picker2, e_picker2));
				actual_time1_wr_add3.setText(""+ fix_time(w_data_begin3, w_data_end3, b_picker3, e_picker3));
			}
		});
		//Пересчитываем время работы техника, если кликнули не только по часикам, но и при клике на один из ComboBox выбора исполнителя
		resp_wr_add.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				actual_time1_wr_add.setText(""+ fix_time(w_data_begin, w_data_end, b_picker, e_picker));
			}
		});
		resp_wr_add1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				actual_time1_wr_add1.setText(""+ fix_time(w_data_begin1, w_data_end1, b_picker1, e_picker1));
			}
		});
		resp_wr_add2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				actual_time1_wr_add2.setText(""+ fix_time(w_data_begin2, w_data_end2, b_picker2, e_picker2));
			}
		});
		resp_wr_add3.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				actual_time1_wr_add3.setText(""+ fix_time(w_data_begin3, w_data_end3, b_picker3, e_picker3));
			}
		});
		
		resp_wr_add1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
								
			}
		});
		//Если СМ то активируем поля нужные для заполнения СМ
		if(record_type_wr_add.getValue().equals("CM"))
		{
			actual_time_wr.setDisable(false);
			actual_time_wr_add.setDisable(false);
			lbl_trt_wr.setDisable(false);
			w_data_begin15.setDisable(false);
			b_picker15.setDisable(false);
			w_data_end15.setDisable(false);
			e_picker15.setDisable(false);
			//lbl_at_wr.setDisable(false);
			//list_at_wr.setDisable(false);
		}
	}
	//Проверяем ввел ли кто-то общее время ремонта, если да, то дизеблим всю строку с возможностью ввода общего времени ремонта
	
	private void chk_wt(String id)
	{
		if(qr._select_confirm_wt(id).equals("YES"))
		{
			w_data_begin.setDisable(true);
			w_data_end.setDisable(true);
			b_picker.setDisable(true);
			e_picker.setDisable(true);
						
			if(!qr._select_confirm_wt_data(id).equals("NO")) {
				w_data_begin.setValue(fx_dp.fromString(sclass.parser_sql_str(qr._select_confirm_wt_data(id), 0)));
				w_data_end.setValue(fx_dp.fromString(sclass.parser_sql_str(qr._select_confirm_wt_data(id), 1)));
				b_picker.setValue(fx_time.fromStringt(sclass.parser_sql_str(qr._select_confirm_wt_data(id), 2)));
				e_picker.setValue(fx_time.fromStringt(sclass.parser_sql_str(qr._select_confirm_wt_data(id), 3)));
				chk_btn();
			}
			chk_btn();
		}	
	}
	 
	private void lang_fun(String loc1, String loc2)
	{
		ResourceBundle lngBndl = ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
		
		lbl_add_rec_wr.setText(lngBndl.getString("lbl_add_rec_wr"));
		shift_report_wr.setText(lngBndl.getString("shift_report_wr")+":");
		req_action_wr.setText(lngBndl.getString("req_action_wr")+":");
		lbl_trt_wr.setText(lngBndl.getString("lbl_trt_wr"));
		lbl_shop_ap.setText(lngBndl.getString("lbl_shop_ap"));
		lbl_group_ap.setText(lngBndl.getString("lbl_group_ap"));
		lbl_lm_ap.setText(lngBndl.getString("lbl_lm_ap"));
		lbl_os_ap.setText(lngBndl.getString("lbl_os_ap"));
		lbl_equip_ap.setText(lngBndl.getString("lbl_equip_ap"));
		actual_time_wr.setText(lngBndl.getString("actual_time_wr")+":");
		actual_time1_wr.setText(lngBndl.getString("actual_time1_wr")+":");
		record_type_wr.setText(lngBndl.getString("record_type_wr")+":");
		resp_wr.setText(lngBndl.getString("resp_wr")+":");
		status_wr.setText(lngBndl.getString("status_wr")+":");
		add_wr_add.setText(lngBndl.getString("lbl_apply"));
		cancel_wr_add.setText(lngBndl.getString("cancel_tsk"));
		
		lbl_trt_wr1.setText(lngBndl.getString("lbl_trt_wr"));
		lbl_trt_wr2.setText(lngBndl.getString("lbl_trt_wr"));
		lbl_trt_wr3.setText(lngBndl.getString("lbl_trt_wr"));
		actual_time1_wr1.setText(lngBndl.getString("actual_time1_wr")+":");
		actual_time1_wr2.setText(lngBndl.getString("actual_time1_wr")+":");
		actual_time1_wr3.setText(lngBndl.getString("actual_time1_wr")+":");
		resp_wr1.setText(lngBndl.getString("resp_wr")+":");
		resp_wr2.setText(lngBndl.getString("resp_wr")+":");
		resp_wr3.setText(lngBndl.getString("resp_wr")+":");
	}
	private void chk_btn()
	{
		try {
		if(shift_report_wr_add.getText().length() != 0 && req_action_wr_add.getText().length() != 0 && actual_time_wr_add.getText().length() != 0 &&
				actual_time1_wr_add.getText().length() != 0 && numap_wr_add.getText().length() != 0 && 
				shop_wr_add.getValue().length() != 0 && group_wr_add.getValue().length() != 0 && lm_wr_add.getValue().length() != 0 && os_wr_add.getValue().length() != 0 &&
				equip_wr_add.getValue().length() != 0 && record_type_wr_add.getValue().length() != 0 && resp_wr_add.getValue().length() != 0 &&
				status_wr_add.getValue().length() != 0 && w_data_begin.getValue().toString().length() != 0 && b_picker.getValue().toString().length() != 0 &&
				w_data_end.getValue().toString().length() != 0 && e_picker.getValue().toString().length() != 0 )//&& list_at_wr.getValue().length() != 0
			add_wr_add.setDisable(false);
		else
			add_wr_add.setDisable(true);
		}
		catch (Exception e) {
			
		}
	}
	
	private int fix_time(DatePicker d1, DatePicker d2, JFXTimePicker t1, JFXTimePicker t2)
	{
		int wt_rezult1 = 0;
		int d_b = d1.getValue().getDayOfYear();
		int d_e = d2.getValue().getDayOfYear();
		
		int data_rezult = d_e - d_b;
		//Обрезаем секунды
		//int hours_b = Integer.parseInt(sclass.parser_double_dot(t1.getValue().toString(), 0));
		//int min_b = Integer.parseInt(sclass.parser_double_dot(t1.getValue().toString(), 1));
		//int hours_e = Integer.parseInt(sclass.parser_double_dot(t2.getValue().toString(), 0));
		//int min_e = Integer.parseInt(sclass.parser_double_dot(t2.getValue().toString(), 1));
		
		//int time_rezult = Math.abs(hours_e - hours_b)*60 + Math.abs(min_e - min_b);
			
		LocalTime test = t1.getValue();
		LocalTime test2 = t2.getValue();
		
		//long h_between = ChronoUnit.HOURS.between(test, test2);
		long m_between = ChronoUnit.MINUTES.between(test, test2);
		
		int time_rezult = Math.abs((int) m_between);
		
		wt_rezult1 = data_rezult*24*60 + time_rezult;
		//System.out.println("HOURS_B = "+hours_b+" MIN_B = "+min_b+" HOURS_E = "+hours_e+" MIN_E = "+min_e+" TIME REZUL = "+time_rezult+" WT REZULT = "+wt_rezult1);
		return wt_rezult1;
	}
}
