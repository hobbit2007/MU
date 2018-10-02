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

public class updrec_pm_controller {
	@FXML
	ComboBox<String> ninst_pm_upd, ool_pm_upd, otv_pm_upd; //shop_pm_upd, group_pm_upd, lm_pm_upd, os_pm_upd, equip_pm_upd, pmname_pm_upd, pmcycle_pm_upd, pmtype_pm_upd, 
	
	@FXML
	TextField dexp_pm_upd, group_eq_upd, equip_pm_upd, num_pm_upd;
	
	@FXML
	JFXButton confirm_pm_upd, cancel_pm_upd;
	
	@FXML
	Label lbl_head_pm, lbl_ninst_pm, lbl_ool_pm, lbl_otv_pm, lbl_dexp_pm, lbl_group_eq;//lbl_shop_pm, lbl_lm_pm, lbl_os_pm, lbl_equip_pm, lbl_group_pm, lbl_pmname_pm, lbl_pmcycle_pm, lbl_pmtype_pm, 
	
	private Stage stage;	
	_query qr = new _query();
	s_class sclass = new s_class();
	pm_controller pc = new pm_controller();
	Tooltip tip;
		
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
		
		sclass._style(confirm_pm_upd);
		sclass._style(cancel_pm_upd);
		
		group_eq_upd.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(!newValue.isEmpty()) {
		
				if (!newValue.matches("\\d*|#|\\*")) {
					group_eq_upd.setText(newValue.replaceAll("[^\\d|#|\\*]", ""));
		        }
				if(newValue.length() > 5) {
					
					group_eq_upd.setText(newValue.replaceAll("[0-9]", ""));
	            	
				}
			}
			}
		});
		
		confirm_pm_upd.setDisable(true);
		//�������������� ������ �����������
				ninst_pm_upd.setItems(qr._select_instr_pm());
				
				ninst_pm_upd.setOnMouseEntered(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						// TODO Auto-generated method stub
						tip = new Tooltip(ninst_pm_upd.getValue());
						Point2D p = ninst_pm_upd.localToScreen(ninst_pm_upd.getLayoutBounds().getMaxX(), ninst_pm_upd.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
				        tip.show(ninst_pm_upd, p.getX(), p.getY());
					}
				});
				ninst_pm_upd.setOnMouseExited(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						// TODO Auto-generated method stub
						tip.hide();
					}
				});
				
				/*shop_pm_upd.setItems(qr._select_shop_pm());
				try {
					shop_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
						@Override
						public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
							group_pm_upd.valueProperty().set(null);
							lm_pm_upd.valueProperty().set(null);
							os_pm_upd.valueProperty().set(null);
							equip_pm_upd.valueProperty().set(null);
							if(shop_pm_upd.getValue().toString().length() != 0)
								group_pm_upd.setItems(qr._select_group_pm(sclass.parser_str(shop_pm_upd.getValue(), 0)));
							chk_btn();
						}
					});
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				group_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						lm_pm_upd.valueProperty().set(null);
						os_pm_upd.valueProperty().set(null);
						equip_pm_upd.valueProperty().set(null);
						try {
							if(group_pm_upd.getValue().toString().length() != 0)
								lm_pm_upd.setItems(qr._select_lm_pm(sclass.parser_str(group_pm_upd.getValue(), 0)));
							chk_btn();
							//if(lm_wr_add.getValue().toString().length() != 0)
							//	os_wr_add.setItems(qr._select_os_pm(sclass.parser_str(shop_wr_add.getValue(), 0), sclass.parser_str(lm_wr_add.getValue(), 0)));
							} catch (Exception e) {
								// TODO: handle exception
							}
						
					}
				});
				group_pm_upd.setOnMouseEntered(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						// TODO Auto-generated method stub
						tip = new Tooltip(group_pm_upd.getValue());
						Point2D p = group_pm_upd.localToScreen(group_pm_upd.getLayoutBounds().getMaxX(), group_pm_upd.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
				        tip.show(group_pm_upd, p.getX(), p.getY());
					}
				});
				group_pm_upd.setOnMouseExited(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						// TODO Auto-generated method stub
						tip.hide();
					}
				});
				
				lm_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						try {
							if(lm_pm_upd.getValue().toString().length() != 0)
								os_pm_upd.setItems(qr._select_os_pm(sclass.parser_str(group_pm_upd.getValue(), 0), sclass.parser_str(lm_pm_upd.getValue(), 0)));
							chk_btn();
							} catch (Exception e) {
								// TODO: handle exception
							}
					}
				});
				lm_pm_upd.setOnMouseEntered(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						// TODO Auto-generated method stub
						tip = new Tooltip(lm_pm_upd.getValue());
						Point2D p = lm_pm_upd.localToScreen(lm_pm_upd.getLayoutBounds().getMaxX(), lm_pm_upd.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
				        tip.show(lm_pm_upd, p.getX(), p.getY());
					}
				});
				lm_pm_upd.setOnMouseExited(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						// TODO Auto-generated method stub
						tip.hide();
					}
				});
					
				
				os_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						try {
							if(os_pm_upd.getValue().toString().length() != 0)
								equip_pm_upd.setItems(qr._select_equip_pm(sclass.parser_str(os_pm_upd.getValue(), 0), sclass.parser_str(group_pm_upd.getValue(), 0), sclass.parser_str(lm_pm_upd.getValue(), 0)));
							chk_btn();
							} catch (Exception e) {
							}
					}
				});
				os_pm_upd.setOnMouseEntered(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						// TODO Auto-generated method stub
						tip = new Tooltip(os_pm_upd.getValue());
						Point2D p = os_pm_upd.localToScreen(os_pm_upd.getLayoutBounds().getMaxX(), os_pm_upd.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
				        tip.show(os_pm_upd, p.getX(), p.getY());
					}
				});
				os_pm_upd.setOnMouseExited(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						// TODO Auto-generated method stub
						tip.hide();
					}
				});
				
				equip_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
						@Override
						public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
							otv_pm_upd.setItems(qr._select_fio_for_ap(1, sclass.parser_str(shop_pm_upd.getValue(), 0)));
							chk_btn();
						}
					});
				equip_pm_upd.setOnMouseEntered(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						// TODO Auto-generated method stub
						tip = new Tooltip(equip_pm_upd.getValue());
						Point2D p = equip_pm_upd.localToScreen(equip_pm_upd.getLayoutBounds().getMaxX(), equip_pm_upd.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
				        tip.show(equip_pm_upd, p.getX(), p.getY());
					}
				});
				equip_pm_upd.setOnMouseExited(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						// TODO Auto-generated method stub
						tip.hide();
					}
				});*/
				/*ninst_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						// TODO Auto-generated method stub
						try {
						if(ninst_pm_upd.getValue().toString().length() != 0)
							mtt_pm_upd.setItems(qr._select_mtt_pm(ninst_pm_upd.getValue()));
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				});
				mtt_pm_upd.setOnMouseEntered(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						// TODO Auto-generated method stub
						tip = new Tooltip(mtt_pm_upd.getValue());
						Point2D p = mtt_pm_upd.localToScreen(mtt_pm_upd.getLayoutBounds().getMaxX(), mtt_pm_upd.getLayoutBounds().getMaxY()); //I position the tooltip at bottom right of the node (see below for explanation)
				        tip.show(mtt_pm_upd, p.getX(), p.getY());
					}
				});
				mtt_pm_upd.setOnMouseExited(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						// TODO Auto-generated method stub
						tip.hide();
					}
				});*/
				
				String line1 = new String("ON");
				String line2 = new String("OFF");
				otv_pm_upd.setItems(qr._select_fio_for_ap(1, apwr_controller.SHOP_NAME));
				ool_pm_upd.getItems().addAll(line1, line2);
				ninst_pm_upd.getSelectionModel().select(pc._ninst_pm_upd);
				//shop_pm_upd.getSelectionModel().select(pc._eq_id_upd);
				//group_pm_upd.getSelectionModel().select(pc._group_pm_upd);
				ool_pm_upd.getSelectionModel().select(pc._ool_pm_upd);
				otv_pm_upd.getSelectionModel().select(pc._otv);
				dexp_pm_upd.setText(pc._days_exp_upd);
				num_pm_upd.setText(pc._id_pm);
				equip_pm_upd.setText(qr._select_fillpm_equip(pc._eq_id_upd));
				group_eq_upd.setText(pc._group_pm_upd);
				
				/*pmname_pm_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						chk_btn();
					}
				});
				pmcycle_pm_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						chk_btn();
					}
				});
				pmtype_pm_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						chk_btn();
					}
				});*/
				ool_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
						// TODO Auto-generated method stub
						chk_btn();
					}
				});
				otv_pm_upd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
						// TODO Auto-generated method stub
						chk_btn();
					}
				});
				group_eq_upd.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event arg0) {
						// TODO Auto-generated method stub
						chk_btn();
					}
				});
				dexp_pm_upd.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event arg0) {
						// TODO Auto-generated method stub
						chk_btn();
					}
				});
				
				cancel_pm_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						stage = (Stage) cancel_pm_upd.getScene().getWindow();
						stage.close();
					}
				});
				confirm_pm_upd.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						qr._update_rec_pm(pc._id_pm, sclass.parser_str(ninst_pm_upd.getValue(), 0), sclass.parser_str(group_eq_upd.getText(), 0), sclass.parser_str(otv_pm_upd.getValue(), 0), dexp_pm_upd.getText(), sclass.parser_str(ool_pm_upd.getValue(), 0));
							
						qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - �������� ������ � = " + pc._id_pm + " � ������� PM");
							
						pc._table_update_pm.addAll(qr._select_data_pm());
							
						stage = (Stage) confirm_pm_upd.getScene().getWindow();
						stage.close();
					}
				});
				group_eq_upd.setOnKeyReleased(new EventHandler<Event>() {

					@Override
					public void handle(Event event) {
						// TODO Auto-generated method stub
						chk_btn();
					}
				});
				dexp_pm_upd.setOnKeyReleased(new EventHandler<Event>() {

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
		//lbl_shop_pm.setText(lngBndl.getString("lbl_shop_ap"));
		//lbl_group_pm.setText(lngBndl.getString("lbl_group_ap"));
		//lbl_lm_pm.setText(lngBndl.getString("lbl_lm_ap"));
		//lbl_os_pm.setText(lngBndl.getString("lbl_os_ap"));
		//lbl_equip_pm.setText(lngBndl.getString("lbl_equip_ap"));
		//lbl_pmname_pm.setText(lngBndl.getString("col_group_eq")+":");
		//lbl_pmcycle_pm.setText(lngBndl.getString("col_pmc_pm")+":");
		//lbl_pmtype_pm.setText(lngBndl.getString("col_pmtype_pm")+":");
		lbl_ool_pm.setText(lngBndl.getString("col_ool_pm")+":");
		lbl_otv_pm.setText(lngBndl.getString("lbl_otv_ap")+":");
		lbl_dexp_pm.setText(lngBndl.getString("lbl_dexp_pm")+":");
		lbl_group_eq.setText(lngBndl.getString("col_group_pm")+":");
		lbl_head_pm.setText(lngBndl.getString("lbl_head_pm_upd"));
		confirm_pm_upd.setText(lngBndl.getString("lbl_apply"));
		cancel_pm_upd.setText(lngBndl.getString("cancel_tsk"));
	}
	private void chk_btn()
	{
		try {
			if(ninst_pm_upd.getValue().length() != 0 && group_eq_upd.getText().length() != 0 &&  
					ool_pm_upd.getValue().length() != 0 && otv_pm_upd.getValue().length() != 0 && dexp_pm_upd.getText().length() != 0)
				confirm_pm_upd.setDisable(false);
			else
				confirm_pm_upd.setDisable(true);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
	}
}
