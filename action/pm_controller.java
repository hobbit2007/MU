package action;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import application.conn_connector;
import data.FxDatePickerConverter;
import db._query;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import share_class.s_class;

public class pm_controller {
	
	@FXML
	TableView<hmmr_pm_model> table_pm;
	
	@FXML
	TableColumn<hmmr_pm_model, String> col_id_pm, col_ninst_pm, col_group_pm, col_ool_pm, col_otv_pm, col_days_exp; //col_group_eq, col_lm_pm, col_os_pm, col_equip_pm, col_pmn_pm, col_pmc_pm, col_pmtype_pm, 
	
	@FXML
	JFXButton add_ap_pm, add_pm, upd_pm, del_pm, close_pm, upd_table_pm;
	
	public static String _id_pm, _ninst_pm_upd, _eq_id_upd,_group_pm_upd, _ool_pm_upd, _otv, _days_exp_upd;// _group_eq_upd, _lm_pm_upd, _os_pm_upd, _equip_pm_upd, _pmn_pm_upd, _pmc_pm_upd, _pmtype_pm_upd, 
	
	@FXML
	ScrollPane sp_pm;
	
	@FXML
	Pane pane_pm;
	
	@FXML
	VBox vbox_pm;
	
	@FXML
	HBox hbox_pm;
	
	_query qr = new _query();
	private Stage stage = new Stage();
	s_class scl = new s_class();
	FxDatePickerConverter fx_dp = new FxDatePickerConverter();
	public static ObservableList<TableColumn<hmmr_pm_model, ?>> columns_pm;
	private String name_col = "������������";
	public static ObservableList<hmmr_pm_model> _table_update_pm = FXCollections.observableArrayList();
	TableColumn<hmmr_pm_model, String> col_eq_id = new TableColumn<hmmr_pm_model, String>(name_col);
	
	@FXML
	public void initialize()
	{
	//	Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	//	Double screen_width = primaryScreenBounds.getWidth();
	//	Double screen_hight = primaryScreenBounds.getHeight(); 
		
		//sp_pm.setPrefWidth(screen_width - 230);
		//sp_pm.setPrefHeight(screen_hight - 50);
		
		//pane_pm.setPrefHeight(screen_hight - 50);
		//vbox_pm.setPrefHeight(screen_hight - 50);
		
	//	hbox_pm.setPrefHeight(screen_hight - 199);
		
		upd_pm.setDisable(true);
		del_pm.setDisable(true);
		add_ap_pm.setDisable(true);
		
		if(conn_connector.LANG_ID == 1)
			lang_fun("en", "EN");
		if(conn_connector.LANG_ID == 0)
			lang_fun("ru", "RU");
		if(conn_connector.LANG_ID == 2)
			lang_fun("zh", "CN");
		if(conn_connector.LANG_ID == -1)
			lang_fun("ru", "RU");
		
		scl._style(add_ap_pm);
		scl._style(add_pm);
		scl._style(upd_pm);
		scl._style(del_pm);
		scl._style(close_pm);
		scl._style(upd_table_pm);
		//Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		//Double screen_width = primaryScreenBounds.getWidth();
		//Double screen_hight = primaryScreenBounds.getHeight(); 
		
		//sp_pm.setPrefWidth(screen_width - 230);
		//sp_pm.setPrefHeight(screen_hight - 30);
				
		initData();
		
		columns_pm = table_pm.getColumns();	
		
		//������������� ����� ��� ������ �������
		if(conn_connector.USER_ROLE.equals("Technics") || conn_connector.USER_ROLE.equals("Engeneer"))
			del_pm.setDisable(true);
			
		col_id_pm.setCellValueFactory(CellData -> CellData.getValue().IdProperty());
		col_ninst_pm.setCellValueFactory(CellData -> CellData.getValue().num_instProperty());
//		col_eq_id.setCellValueFactory(CellData -> CellData.getValue().eq_idProperty());
		col_group_pm.setCellValueFactory(CellData -> CellData.getValue().Group_PMProperty());
//		col_lm_pm.setCellValueFactory(CellData -> CellData.getValue().L_MProperty());
//		col_os_pm.setCellValueFactory(CellData -> CellData.getValue().O_SProperty());
//		col_equip_pm.setCellValueFactory(CellData -> CellData.getValue().EquipProperty());
//		col_group_eq.setCellValueFactory(CellData -> CellData.getValue().Group_EQProperty());
		//col_supplier_pm.setCellValueFactory(CellData -> CellData.getValue().S_SProperty());
		//col_mtt_pm.setCellValueFactory(CellData -> CellData.getValue().MTTProperty());
//		col_pmn_pm.setCellValueFactory(CellData -> CellData.getValue().PMNProperty());
//		col_pmc_pm.setCellValueFactory(CellData -> CellData.getValue().PMCProperty());
//		col_pmtype_pm.setCellValueFactory(CellData -> CellData.getValue().TPOTProperty());
		col_ool_pm.setCellValueFactory(CellData -> CellData.getValue().OOLProperty());
		col_otv_pm.setCellValueFactory(CellData -> CellData.getValue().OtvProperty());
		col_days_exp.setCellValueFactory(CellData -> CellData.getValue().Days_ExpProperty());
		
		col_id_pm.setSortable(false);
		col_ninst_pm.setSortable(false);
//		col_eq_id.setSortable(false);
//		col_lm_pm.setSortable(false);
//		col_os_pm.setSortable(false);
//		col_equip_pm.setSortable(false);
		col_group_pm.setSortable(false);
//		col_group_eq.setSortable(false);
//		col_pmn_pm.setSortable(false);
//		col_pmc_pm.setSortable(false);
//		col_pmtype_pm.setSortable(false);
		col_ool_pm.setSortable(false);
		col_otv_pm.setSortable(false);
		col_days_exp.setSortable(false);
		
		table_pm.setEditable(true);
		final ObservableList<TableColumn<hmmr_pm_model, ?>> columns = table_pm.getColumns();
		col_eq_id.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<hmmr_pm_model, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<hmmr_pm_model, String> arg0) {
                        hmmr_pm_model data = arg0.getValue();
                        return new SimpleObjectProperty<String>(qr._select_fillpm_equip(data.geteq_id()));
                    }

                });
        
        columns.add(col_eq_id);
		
		//�������� ���� ���������� �� �������� ����� �� ������
		table_pm.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (event.getClickCount() == 2 ){
		               func_upd(table_pm.getSelectionModel().getSelectedItem().getId());
		           }
			}
		});  
		
	      //���� ����� ������ ������� ���������� ������ �������� � �������
	        table_pm.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// TODO Auto-generated method stub
					upd_pm.setDisable(false);
					if(!conn_connector.USER_ROLE.equals("Engeneer"))
						del_pm.setDisable(false);
					add_ap_pm.setDisable(false);
				}
			});
	        add_pm.setOnAction(new EventHandler<ActionEvent>() {
	    		
	    		@Override
	    		public void handle(ActionEvent event) {
	    			try {
	    				upd_pm.setDisable(true);
	    				del_pm.setDisable(true);
	    				add_ap_pm.setDisable(true);
	    				
	    				pm_add(stage);
	    			} catch (IOException e) {
	    				e.printStackTrace();
	    			}
	    		}
	    	});
	        upd_pm.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					upd_pm.setDisable(true);
					del_pm.setDisable(true);
					add_ap_pm.setDisable(true);
					hmmr_pm_model _ccl1 = table_pm.getSelectionModel().getSelectedItem();
					try {
					func_upd(_ccl1.getId());
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			});
	        del_pm.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					
					Alert alert = new Alert(AlertType.CONFIRMATION);
				    alert.setTitle("M&U - Delete Record Window");
				    hmmr_pm_model _ccl = table_pm.getSelectionModel().getSelectedItem();
				    
				    alert.setHeaderText("�� ������������� ������ ������� ������ � = " + _ccl.getId() + "?");
				    //alert.setContentText("C:/MyFile.txt");
				 
				    // option != null.
				    Optional<ButtonType> option = alert.showAndWait();
				    if (option.get() == null) {
				       //label.setText("No selection!");
				    } else if (option.get() == ButtonType.OK) {
				  	   _ccl = table_pm.getSelectionModel().getSelectedItem();
				  	   try {
				  	   func_del(_ccl.getId());
				  	   } catch (Exception e) {
						// TODO: handle exception
					}
				    } else if (option.get() == ButtonType.CANCEL) {
				       //label.setText("Cancelled!");
				    } else {
				       //label.setText("-");
				    }
				}
			});
	        close_pm.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					stage = (Stage) close_pm.getScene().getWindow();
					stage.close();
				}
			});
	        add_ap_pm.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					String Otv_for_task = null;
					hmmr_pm_model _ccl = table_pm.getSelectionModel().getSelectedItem();
					//String sql_rez = qr._select_for_pmplan(_ccl.getGroup_PM());
					for(int j = 0; j < qr._select_for_pmplan(_ccl.getGroup_PM()).size(); j++) {
						String before_pars = qr._select_for_pmplan(_ccl.getGroup_PM()).get(j);
						String pereodic = scl.parser_sql_str(before_pars, 0);
						String b_date = scl.parser_sql_str(before_pars, 1);
						String e_date = scl.parser_sql_str(before_pars, 2);
						@SuppressWarnings("unused")
						String shop = scl.parser_sql_str(before_pars, 3);
						Otv_for_task = scl.parser_sql_str(before_pars, 4);
						int pm_group = Integer.parseInt(_ccl.getGroup_PM());
						
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
							qr._insert_pm_year(_ccl.getId(), days, Otv_for_task, pm_group);
							_count = _cnt + _count;
						}
					}
					//LocalDate day256_2017 = LocalDate.ofYearDay(2018, 256);
					//System.out.println("DAY = "+ day_bdate + " MONTH = " + month_bdate + " YEAR = " + year_bdate);
					add_ap_pm.setDisable(true);
				}
			});
	        upd_table_pm.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					table_pm.setItems(qr._select_data_pm());
					columns_pm.get(0).setVisible(false);
				    columns_pm.get(0).setVisible(true);
				}
			});
	        _table_update_pm.addListener(new ListChangeListener<hmmr_pm_model>() {
			    @Override
				public void onChanged(Change<? extends hmmr_pm_model> c) {
					table_pm.setItems(qr._select_data_pm());
			    	table_pm.getColumns().get(0).setVisible(false);
			        table_pm.getColumns().get(0).setVisible(true);
				}
			});
	}
	
	private void initData()
	{
		table_pm.setItems(qr._select_data_pm());
	}
	
	private void func_upd(String str)
	{
		 
		String _sql_rez = qr._select_for_update_pm(str);
		_id_pm = str;
		_ninst_pm_upd = scl.parser_sql_str(_sql_rez, 0);
		_eq_id_upd = scl.parser_sql_str(_sql_rez, 1);
		_group_pm_upd = scl.parser_sql_str(_sql_rez, 2);
		_otv = scl.parser_sql_str(_sql_rez, 3);
		_days_exp_upd = scl.parser_sql_str(_sql_rez, 4);
		_ool_pm_upd = scl.parser_sql_str(_sql_rez, 5);
				
		try {
			pm_upd(stage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void func_del(String str)
	{
		qr._update_rec_pm_del(str);
		qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - ������ ������ � = " + str + " � ������� PM");
		_id_pm = str;
		_table_update_pm.addAll(qr._select_data_pm());
	}
	
	//�������� ���� ���������� ������ � PM
	protected void pm_add(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("add_rec_pm.fxml"));
		   
	    Scene scene = new Scene(root);
	    stage.setTitle("M&U - Add Record Window");
	    stage.setResizable(false);
	    //stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.show();
	}
	//�������� ���� ���������� ������
	protected void pm_upd(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("upd_rec_pm.fxml"));
				   
	    Scene scene = new Scene(root);
	    stage.setTitle("M&U - Update Record Window");
	    stage.setResizable(false);
	    //stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.show();
	}
	//��������� ��� �������, ����� ���� �������� � �������� ���� ����������. ��� ���� ������� �������� �������!
	@FXML
	public void logoutWindow() throws IOException {
	    stage.showAndWait();
	}
	
	private void lang_fun(String loc1, String loc2)
	{
		ResourceBundle lngBndl = ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
		 
		col_ninst_pm.setText(lngBndl.getString("col_ninst_pm"));
		//col_eq_id.setText(lngBndl.getString("col_shop_pm"));
		col_group_pm.setText(lngBndl.getString("col_group_pm"));
		col_ool_pm.setText(lngBndl.getString("col_ool_pm"));
		col_otv_pm.setText(lngBndl.getString("lbl_otv_ap"));
		col_days_exp.setText(lngBndl.getString("col_days_exp"));
		add_ap_pm.setText(lngBndl.getString("add_ap_pm"));
		add_pm.setText(lngBndl.getString("add_tsk"));
		upd_pm.setText(lngBndl.getString("upd_wr"));
		del_pm.setText(lngBndl.getString("del_inst"));
		close_pm.setText(lngBndl.getString("cancel_tsk"));
		upd_table_pm.setText(lngBndl.getString("upd_table_wr"));
	}
}
