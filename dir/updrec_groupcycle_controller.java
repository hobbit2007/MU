package dir;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import action.apwr_controller;
import application.conn_connector;
import data.FxDatePickerConverter;
import db._query;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import share_class.s_class;

public class updrec_groupcycle_controller {
	@FXML
	Label lbl_title_upd, lbl_pm_group, lbl_pm_cycle, lbl_days_gc, lbl_start_date, lbl_duration;
	
	@FXML
	JFXButton add_rec, cancel_form;
	
	@FXML
	TextField txt_pm_group, txt_days_gc, txt_duration;
	
	@FXML
	ComboBox<String> list_pm_cycle;
	
	@FXML
	DatePicker d_start_date;
	
	_query qr = new _query();
	s_class scl = new s_class();
	group_cycle_controller gcc = new group_cycle_controller();
	FxDatePickerConverter fx_dp = new FxDatePickerConverter();
	
	LocalDate new_date;
	
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
		txt_duration.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					txt_duration.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 3) {
					
					txt_duration.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
				}
			}
		});
		txt_duration.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				scl._ToolTip("���������� ���� �� ����������( ��������: 7 )", txt_duration);
			}
		});
		txt_duration.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				scl._ToolTipHide();
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
		txt_duration.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				chk_btn();
			}
		});
		
		txt_pm_group.setText(scl.parser_str(qr._select_for_gc_str(gcc._id_gc), 0));
		list_pm_cycle.getSelectionModel().select(scl.parser_str(qr._select_for_gc_str(gcc._id_gc), 1));
		txt_days_gc.setText(scl.parser_str(qr._select_for_gc_str(gcc._id_gc), 2));
		d_start_date.setValue(fx_dp.fromString(scl.parser_str(qr._select_for_gc_str(gcc._id_gc), 3)));
		txt_duration.setText(scl.parser_str(qr._select_for_gc_str(gcc._id_gc), 4));
		//!!!!!!!!!!!����������� ��������� ����!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Alert alert = new Alert(AlertType.CONFIRMATION);
			    alert.setTitle("M&U - ��������!");
			    			    
			    alert.setHeaderText("�� �������� ����! �� ������� ��� ������ ����������� � PM Plan ������: "+txt_pm_group.getText()+" �� ����� ����?");
			    
			    Optional<ButtonType> option = alert.showAndWait();
			    if (option.get() == null) {
			      
			    } else if (option.get() == ButtonType.OK) {
			  	   try {
			  		   new_date = d_start_date.getValue();
			  		   new_pm_date(gcc._id_gc);
			  		   chk_btn();
			  	   } catch (Exception e1) {
					// TODO: handle exception
				}
			    } else if (option.get() == ButtonType.CANCEL) {
			       return;
			    } else {
			       //label.setText("-");
			    }
            } 
        }; 
        d_start_date.setOnAction(event);
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		add_rec.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				qr._update_for_gc(gcc._id_gc, txt_pm_group.getText(), list_pm_cycle.getValue(), txt_days_gc.getText(), d_start_date.getValue(), txt_duration.getText());
				qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - ������� ������ � = " + qr._select_last_id("hmmr_group_cycle") + " � ����������� ������-������");
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
		lbl_start_date.setText(lngBndl.getString("col_startdate_ps")+":");
		lbl_duration.setText(lngBndl.getString("lbl_duration")+":");
		
		add_rec.setText(lngBndl.getString("upd_ap"));
		cancel_form.setText(lngBndl.getString("cancel_tsk"));
	}
	
	@SuppressWarnings("static-access")
	private void new_pm_date(int _id_gc)
	{
		String Otv_for_task = null;
		
		//������� Pm_id ��� ������ ��� ������� �������� ����
		String pm_id = qr._select_pmid(txt_pm_group.getText());
		//������� ��� ������ �� PM Plan ������ ��� ������� �������� ����
		qr._update_new_date(txt_pm_group.getText());
		
		if(!txt_pm_group.getText().equals("0")) {
			if(!scl.parser_sql_str(qr._select_for_pmgroup(txt_pm_group.getText()), 0).equals(txt_pm_group.getText())) {
				try {
					String before_pars = qr._select_for_pmplan(txt_pm_group.getText()).get(0);
					String pereodic = scl.parser_sql_str(before_pars, 0);
					String b_date = fx_dp.toString(new_date);
					
						String e_date = scl.parser_sql_str(before_pars, 2);
						@SuppressWarnings("unused")
						String shop = scl.parser_sql_str(before_pars, 3);
						Otv_for_task = scl.parser_sql_str(before_pars, 4);
						
						int pm_group = Integer.parseInt(txt_pm_group.getText());
						
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
							qr._insert_pm_year(pm_id, pm_group, days, Otv_for_task);
							_count = _cnt + _count;
						}
					
					
				}
				catch (Exception e) {
					scl._AlertDialog("�� ������� �� ������ PM ���������������� ������: "+ txt_pm_group.getText() +" !", "������!");
				}
			}
			else
			{
				scl._AlertDialog("������ "+ txt_pm_group.getText() +" ��� ��������� � PM PLAN!", "������ ��� ����������");
			}
		}
		else
			scl._AlertDialog("������ 0 �� ����� ���� ��������� � PM PLAN! ������� ���������� ����� ������!", "������!");
	}
	
	private void chk_btn()
	{
		try {
			if(txt_pm_group.getText().length() != 0 && list_pm_cycle.getValue().length() != 0 && txt_duration.getText().length() != 0)
				add_rec.setDisable(false);
			else
				add_rec.setDisable(true);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
}
