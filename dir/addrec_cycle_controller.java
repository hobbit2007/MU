package dir;

import java.time.LocalDate;

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
import javafx.geometry.Point2D;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import share_class.s_class;

public class addrec_cycle_controller
{
	@FXML
	JFXButton add_cycle_confirm, add_cycle_cancel;
	
	@FXML
	TextField type_cycle, pereodic_cycle, moto_cycle;
	
	@FXML
	DatePicker bdate_cycle, edate_cycl;
	
	@FXML
	Label err_info_cycle;
	
	private Stage stage;
	private String _tc, _pc, _hc, _dc, _edc;
	s_class scl = new s_class();
	_query qr = new _query();
	CycleController cl = new CycleController();
	Tooltip tip;
	
	public addrec_cycle_controller()
	{
		
	}
	
	@FXML
	public void initialize()
	{
		bdate_cycle.setValue(LocalDate.now());
		edate_cycl.setValue(LocalDate.now().plusDays(365));
		
		scl._style(add_cycle_confirm);
		scl._style(add_cycle_cancel);
		
		//��������� ������� ������ ����� � �� ������ ����
		pereodic_cycle.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
		            pereodic_cycle.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 5) {
					
	            	pereodic_cycle.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
			}
			}
		});
		moto_cycle.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
		            moto_cycle.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 5) {
					
	            	moto_cycle.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
			}
			}
		});
		
		pereodic_cycle.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip("���������� ������� ������������� ��� � ����. ��������: ����������� - 7 ����,\n "
			      		+ " ��� � ��� ������ - 14 ����, ��� � ������� - 182.5 ����, ��� � ��� 365 ���� � �.�.");
				Point2D p = pereodic_cycle.localToScreen(pereodic_cycle.getLayoutBounds().getMaxX(), pereodic_cycle.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(pereodic_cycle, p.getX(), p.getY());
			}
		});
		pereodic_cycle.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		edate_cycl.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip = new Tooltip("��� �������� ������ ���������� ���, ������� ���������� ��������� � �������,\n " 
						+ " ��������: ���� ������ - 01.08.2018, ���� ��������� - 01.09.2018 � � ������� ������������� �������� - 7 ���� \n " 
						+ " (�.�. �����������), ����� ����� ������� ���� ��� - 01.08.2018; 08.08.2018; 15.08.2018; 23.08.2018 � 30.08.2018");
				Point2D p = edate_cycl.localToScreen(edate_cycl.getLayoutBounds().getMaxX(), edate_cycl.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
		        tip.show(edate_cycl, p.getX(), p.getY());
			}
		});
		edate_cycl.setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tip.hide();
			}
		});
		
		add_cycle_cancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				stage = (Stage) add_cycle_cancel.getScene().getWindow();
				stage.close();
			}
		});
		
		add_cycle_confirm.setOnAction(new EventHandler<ActionEvent>() {
			
			@SuppressWarnings("static-access")
			@Override
			public void handle(ActionEvent event) {
				
				if(type_cycle.getText().length() != 0 && pereodic_cycle.getText().length() != 0 && moto_cycle.getText().length() != 0 && bdate_cycle.getValue().toString().length() != 0 && edate_cycl.getValue().toString().length() != 0)
				{
					err_info_cycle.setVisible(false);
					
					_tc = type_cycle.getText();
					_pc = pereodic_cycle.getText();
					_hc = moto_cycle.getText();
					_dc = bdate_cycle.getValue().toString();
					_edc = edate_cycl.getValue().toString();	
					
					qr._insert_pm_cycle(_tc, _pc, _hc, _dc, _edc);
					
					qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + "������ ������ � = " + qr._select_last_id("hmmr_pm_cycle") + " � PM Cycle");
					
					cl._table_update_cycle.addAll(qr._select_data_cycle());
					
					stage = (Stage) add_cycle_confirm.getScene().getWindow();
					stage.close();
				}
				else
					err_info_cycle.setVisible(true);
			}
		});
	}
}