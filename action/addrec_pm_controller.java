package action;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import application.conn_connector;
import data.FxDatePickerConverter;
import db._query;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import share_class.s_class;

public class addrec_pm_controller {
	@FXML
	ComboBox<String> ninst_pm, shop_pm, lm_pm, os_pm, equip_pm, group_pm, ool_pm, otv_pm, group_eq, list_otv_isp_pm;
	
	@FXML
	JFXButton confirm_pm, cancel_pm;
	
	@FXML
	Label err_msg, lbl_head_pm, lbl_ninst_pm, lbl_shop_pm, lbl_lm_pm, lbl_os_pm, lbl_equip_pm, lbl_group_pm, lbl_ool_pm, lbl_otv_pm, lbl_group_eq, lbl_otv_isp, lbl_otv_isp1;
	
	private Stage stage;	
	_query qr = new _query();
	s_class sclass = new s_class();
	pm_controller pc = new pm_controller();
	FxDatePickerConverter fx_dp = new FxDatePickerConverter();
	Tooltip tip;
	LocalDate new_date;
	static String _last_id;
		
	@SuppressWarnings("static-access")
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
		
		//�������������� ������ �����������
		ninst_pm.setItems(qr._select_instr_pm());
		ninst_pm.requestFocus();
		
		FilteredList<String> filteredItems = new FilteredList<String>(qr._select_instr_pm(), p -> true);
		
		ninst_pm.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            final TextField editor = ninst_pm.getEditor();
            final String selected = ninst_pm.getSelectionModel().getSelectedItem();
            
            editor.requestFocus();
                      
            // This needs run on the GUI thread to avoid the error described
            // here: https://bugs.openjdk.java.net/browse/JDK-8081700.
            Platform.runLater(() -> {
                // If the no item in the list is selected or the selected item
                // isn't equal to the current input, we refilter the list.
                if (selected == null || !selected.equals(editor.getText())) {
                    filteredItems.setPredicate(item -> {
                        // We return true for any items that starts with the
                        // same letters as the input. We use toUpperCase to
                        // avoid case sensitivity.
                        if (item.toUpperCase().startsWith(newValue.toUpperCase())) {
                            return true;
                        } else {
                            return false;
                        }
                    });
                }
            });
        });
		
		if(!pc._num_inst_last.equals("NULL")) {
			ninst_pm.setValue(pc._num_inst_last);//pc._num_inst_last.substring(0, pc._num_inst_last.length() - 1)  
			ninst_pm.setItems(filteredItems);
			ninst_pm.getEditor().requestFocus();
			Platform.runLater(new Runnable() {
                @Override
                public void run() {
                	ninst_pm.getEditor().positionCaret(pc._num_inst_last.length());
                }
           });
	}
		confirm_pm.setDisable(true);
		
		sclass._style(confirm_pm);
		sclass._style(cancel_pm);
		
		ninst_pm.setOnMouseEntered(new EventHandler<Event>() {
		
			@Override
			public void handle(Event event) {
				tip = new Tooltip(ninst_pm.getValue());
				Point2D p = ninst_pm.localToScreen(ninst_pm.getLayoutBounds().getMaxX(), ninst_pm.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(ninst_pm, p.getX(), p.getY());
			}
		});
		ninst_pm.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		//��������� ComboBox ������ � ����������� �� �������������, �.� � ���� ComboBox ������� ������ �� ������ ������� ������������� ���������� ������� ���, � ������
		//�� ������ �� ������ ���������� �� ������� pm_inst
		ninst_pm.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				try {
					ninst_pm.getEditor().selectPositionCaret(pc._num_inst_last.length());
					group_eq.setItems(qr._select_pm_group(sclass.parser_str(ninst_pm.getValue(), 0)));
				}
				catch (Exception e) {
					
				}
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
						
					}
				
			}
		});
		group_pm.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(group_pm.getValue());
				Point2D p = group_pm.localToScreen(group_pm.getLayoutBounds().getMaxX(), group_pm.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(group_pm, p.getX(), p.getY());
			}
		});
		group_pm.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
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
						
					}
			}
		});
		lm_pm.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(lm_pm.getValue());
				Point2D p = lm_pm.localToScreen(lm_pm.getLayoutBounds().getMaxX(), lm_pm.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(lm_pm, p.getX(), p.getY());
			}
		});
		lm_pm.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
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
				tip = new Tooltip(os_pm.getValue());
				Point2D p = os_pm.localToScreen(os_pm.getLayoutBounds().getMaxX(), os_pm.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(os_pm, p.getX(), p.getY());
			}
		});
		os_pm.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
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
						
					}
				}
			});
		equip_pm.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip(equip_pm.getValue());
				Point2D p = equip_pm.localToScreen(equip_pm.getLayoutBounds().getMaxX(), equip_pm.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(equip_pm, p.getX(), p.getY());
			}
		});
		equip_pm.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		ninst_pm.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				try {
					chk_btn();
				} catch (Exception e) {
					
				}
			}
		});
		
		String line1 = new String("ON");
		String line2 = new String("OFF");
		ool_pm.getItems().addAll(line1, line2);
		
		ool_pm.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				chk_btn();
			}
		});
		list_otv_isp_pm.setItems(qr._select_fio_for_ap(2, sclass.parser_str(shop_pm.getValue(), 0)));
		
		confirm_pm.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				if(list_otv_isp_pm.getValue() == null)
					list_otv_isp_pm.setValue("need select");
				
				String eq_id_total = qr._select_data_filter_ps_id(sclass.parser_str(shop_pm.getValue(), 0), sclass.parser_str(group_pm.getValue(), 0), sclass.parser_str(lm_pm.getValue(), 0), sclass.parser_str(os_pm.getValue(), 0), sclass.parser_str(equip_pm.getValue(), 0));
				qr._insert_pm(sclass.parser_str(ninst_pm.getValue(), 0), eq_id_total, group_eq.getValue(), sclass.parser_str(otv_pm.getValue(), 0), sclass.parser_str(ool_pm.getValue(), 0), sclass.parser_str(list_otv_isp_pm.getValue(), 0));
				_last_id = qr._select_last_id("hmmr_pm");
				qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - ������ ������ � = " + _last_id + " � ������� PM");
					
				pc._table_update_pm.addAll(qr._select_data_pm());
				
				pc._num_inst_last = sclass.parser_str(ninst_pm.getValue(), 0);
				
				if(!qr._select_recStr("hmmr_group_cycle", "PM_StartDate", "del_rec", "PM_Group", group_eq.getValue()).equals("2018-10-10") && !sclass.parser_sql_str(qr._select_for_pmgroup(group_eq.getValue()), 0).equals(group_eq.getValue())) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
				    alert.setTitle("M&U - ��������!");
				    			    
				    alert.setHeaderText("�������� � PM Plan ������: "+group_eq.getValue()+"?\n��������!!! ���� ������ �����: "+qr._select_recStr("hmmr_group_cycle", "PM_StartDate", "del_rec", "PM_Group", group_eq.getValue())+"!\n���� �� �� ������� � ���� ������ ���������: 2018-10-10, ����� ������ �� ����� ��������� � PM Plan!");
				    
				    Optional<ButtonType> option = alert.showAndWait();
				    if (option.get() == null) {
				      
				    } else if (option.get() == ButtonType.OK) {
				  	   try {
				  		   new_date = fx_dp.fromString(qr._select_recStr("hmmr_group_cycle", "PM_StartDate", "del_rec", "PM_Group", group_eq.getValue()));
				  		   new_pm_date(fx_dp.toString(new_date));
				  		   chk_btn();
				  	   } catch (Exception e1) {
						
					}
				    } else if (option.get() == ButtonType.CANCEL) {
				    	stage = (Stage) confirm_pm.getScene().getWindow();
						stage.close();
				    }
			    }
					
				stage = (Stage) confirm_pm.getScene().getWindow();
				stage.close();
				}
			});
		cancel_pm.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				stage = (Stage) cancel_pm.getScene().getWindow();
				stage.close();
			}
		});
		group_eq.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				chk_btn();
			}
		});
		otv_pm.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
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
		lbl_otv_isp.setText(lngBndl.getString("lbl_otv_ap"));
		lbl_otv_isp1.setText(lngBndl.getString("lbl_otv_ap1"));
		lbl_group_eq.setText(lngBndl.getString("col_group_eq")+":");
		lbl_head_pm.setText(lngBndl.getString("lbl_head_pm"));
		confirm_pm.setText(lngBndl.getString("lbl_apply"));
		cancel_pm.setText(lngBndl.getString("cancel_tsk"));
	}
	
	private void new_pm_date(String chk_date)
	{
		String Otv_for_task = null;
		
		//������� Pm_id ��� ������ ��� ������� �������� ����
		//String pm_id = qr._select_pmid(group_eq.getValue());
		//������� ��� ������ �� PM Plan ������ ��� ������� �������� ����
		//qr._update_new_date(txt_pm_group.getText());
		
//		if(!qr._select_recStr("hmmr_group_cycle", "PM_StartDate", "del_rec", "PM_Group", group_eq.getValue()).equals("2018-10-10")) {
//			if(!sclass.parser_sql_str(qr._select_for_pmgroup(group_eq.getValue()), 0).equals(group_eq.getValue())) {
//				try {
		if(!chk_date.equals("2018-10-10")) {
			String before_pars = qr._select_for_pmplan(group_eq.getValue()).get(0);
			String pereodic = sclass.parser_sql_str(before_pars, 0);
			String b_date = fx_dp.toString(new_date);
					
			String e_date = sclass.parser_sql_str(before_pars, 2);
			@SuppressWarnings("unused")
			String shop = sclass.parser_sql_str(before_pars, 3);
			Otv_for_task = sclass.parser_sql_str(before_pars, 4);
						
			int pm_group = Integer.parseInt(group_eq.getValue());
						
			int _count = Integer.parseInt(pereodic);
			int _cnt = _count;
						
			int day_bdate = fx_dp.fromString(b_date).getDayOfMonth();
			int month_bdate = fx_dp.fromString(b_date).getMonthValue();
			int year_bdate = fx_dp.fromString(b_date).getYear();
						
			int day_edate = fx_dp.fromString(e_date).getDayOfMonth();
			int month_edate = fx_dp.fromString(e_date).getMonthValue();
			int year_edate = fx_dp.fromString(e_date).getYear();
						
			//������� ���������� ���� � ������� ������� ������ ����������� ���, � ����� ������� ������� ���� ������� ������� � ������� hmmr_pm_year
			int gen_day = Math.abs(day_edate - day_bdate);
			int gen_month = Math.abs(month_edate - month_bdate)*30;
			int gen_year = Math.abs(year_edate - year_bdate)*365;
						
			int _general = Math.round((gen_day + gen_month + gen_year)/_count);
						
			for (int i = 0; i < _general; i++) {
				LocalDate days = LocalDate.of(year_bdate, month_bdate, day_bdate).plusDays(_count);//����������� ���� ����� ������ ������ ���� ���������
				qr._insert_pm_year(_last_id, pm_group, days, Otv_for_task);
				_count = _cnt + _count;
			}
			qr._update_week_year(pm_group);
		}		
					
//				}
//				catch (Exception e) {
//					scl._AlertDialog("�� ������ ����� ���������� ��� ��� ����� ������������� ������ �����������!", "������!");
//				}
//			}
//			else
//			{
//				sclass._AlertDialog("������ "+ group_eq.getValue() +" ��� ��������� � PM PLAN!", "������ ��� ����������");
//			}
//		}
//		else
//			sclass._AlertDialog("������ 0 �� ����� ���� ��������� � PM PLAN! ������� ���������� ����� ������!", "������!");
	}
	
	private void chk_btn()
	{
		try {
		if(ninst_pm.getValue().length() != 0 && shop_pm.getValue().length() != 0 && group_pm.getValue().length() != 0 && lm_pm.getValue().length() != 0 && os_pm.getValue().length() != 0 && equip_pm.getValue().length() != 0 && group_eq.getValue().length() != 0 && ool_pm.getValue().length() != 0 && otv_pm.getValue().length() != 0)
			confirm_pm.setDisable(false);
		else
			confirm_pm.setDisable(true);
		}
		catch (Exception e) {
			
		}
	}
}
