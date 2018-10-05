package action;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;

import application.Main;
import application.conn_connector;
import db._query;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import share_class.s_class;

public class pm_inst_controller
{
	@FXML
	TableView<hmmr_inst_model> table_inst;
	
	@FXML
	TableColumn<hmmr_inst_model, String> col_id_inst, col_ninst_inst, col_date_create_pi, col_date_change_pi, col_inst_pi, col_ver_inst, col_mt_inst, col_pmn_inst, col_pmt_inst, col_pmc1_inst, col_pmc2_inst, col_ool_inst,
										 col_oop_inst, col_pos_inst, col_sinfo_inst, col_sdoc_inst, col_qspec_inst, col_pty_inst, col_wt_inst, col_adm2_inst,
										 col_adm3_inst, col_of1_inst, col_of2_inst;
	
	@FXML
	ScrollPane sp_inst;
	
	@FXML
	JFXButton add_inst, upd_inst, del_inst, close_inst, upd_table_inst;
	
	@FXML
	HBox hbox_inst;
	
	@FXML
	Pane pane_inst;
	
	@FXML
	VBox vbox_inst;
	
	@FXML
	Label head_pm;
	
	public static String _id_inst, _ninst_inst, _ver_inst, _mt_inst, _pmname_inst, _sdoc_txt_inst, _qtyspec_inst, _ptw_inst, _wt_inst, _adm2_inst, _adm3_inst, _of1_inst, _of2_inst, 
	_typepm_inst, _cyclepm1_inst, _cyclepm2_inst, _pos_inst, _line_inst, _power_inst, _sinfo_inst, _date_create, _date_change, _inst_pdf;
	
	_query qr = new _query();
	s_class scl = new s_class();
	
	public Stage stage = new Stage();
	public static ObservableList<TableColumn<hmmr_inst_model, ?>> columns_inst;
	public static ObservableList<hmmr_inst_model> _table_update_inst = FXCollections.observableArrayList();
	public static Stage pStage;
	
	public pm_inst_controller()
	{
		
	}
	
	@FXML
	public void initialize()
	{
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		Double screen_width = primaryScreenBounds.getWidth();
		Double screen_hight = primaryScreenBounds.getHeight(); 
		
		sp_inst.setPrefWidth(screen_width - 230);
		sp_inst.setPrefHeight(screen_hight - 50);
		
		pane_inst.setPrefHeight(screen_hight - 50);
		vbox_inst.setPrefHeight(screen_hight - 50);
		
		hbox_inst.setPrefHeight(screen_hight - 199);
		//������������� ����� ��� ������ �������
		if(conn_connector.USER_ROLE.equals("Technics") || conn_connector.USER_ROLE.equals("Engeneer"))
			del_inst.setDisable(true);
		
		if(conn_connector.LANG_ID == 1)
			lang_fun("en", "EN");
		if(conn_connector.LANG_ID == 0)
			lang_fun("ru", "RU");
		if(conn_connector.LANG_ID == 2)
			lang_fun("zh", "CN");
		if(conn_connector.LANG_ID == -1)
			lang_fun("ru", "RU");
		
		scl._style(add_inst);
		scl._style(upd_inst);
		scl._style(del_inst);
		scl._style(close_inst);
		scl._style(upd_table_inst);
		
		initData();
		
		columns_inst = table_inst.getColumns();
		
		upd_inst.setDisable(true);
		del_inst.setDisable(true);
				
		col_id_inst.setCellValueFactory(CellData -> CellData.getValue().IdProperty());
		col_ninst_inst.setCellValueFactory(CellData -> CellData.getValue().num_instProperty());
		col_date_create_pi.setCellValueFactory(CellData -> CellData.getValue().date_createProperty());
		col_date_change_pi.setCellValueFactory(CellData -> CellData.getValue().date_changeProperty());
		col_inst_pi.setCellValueFactory(CellData -> CellData.getValue().inst_pdfProperty());
		col_ver_inst.setCellValueFactory(CellData -> CellData.getValue().VerProperty());
		col_mt_inst.setCellValueFactory(CellData -> CellData.getValue().MTTProperty());
		col_pmn_inst.setCellValueFactory(CellData -> CellData.getValue().PM_nameProperty());
		col_pmt_inst.setCellValueFactory(CellData -> CellData.getValue().type_PMProperty());
		col_pmc1_inst.setCellValueFactory(CellData -> CellData.getValue().PM_Cycle1Property());
		col_pmc2_inst.setCellValueFactory(CellData -> CellData.getValue().PM_Cycle2Property());
		col_ool_inst.setCellValueFactory(CellData -> CellData.getValue().OlOlProperty());
		col_oop_inst.setCellValueFactory(CellData -> CellData.getValue().PoPoProperty());
		col_pos_inst.setCellValueFactory(CellData -> CellData.getValue().PosProperty());
		col_sinfo_inst.setCellValueFactory(CellData -> CellData.getValue().S_infoProperty());
		col_sdoc_inst.setCellValueFactory(CellData -> CellData.getValue().S_docProperty());
		col_qspec_inst.setCellValueFactory(CellData -> CellData.getValue().Qty_sProperty());
		col_pty_inst.setCellValueFactory(CellData -> CellData.getValue().PWTProperty());
		col_wt_inst.setCellValueFactory(CellData -> CellData.getValue().WTProperty());
		col_adm2_inst.setCellValueFactory(CellData -> CellData.getValue().Adm_2Property());
		col_adm3_inst.setCellValueFactory(CellData -> CellData.getValue().Adm_3Property());
		col_of1_inst.setCellValueFactory(CellData -> CellData.getValue().OF_1Property());
		col_of2_inst.setCellValueFactory(CellData -> CellData.getValue().OF_2Property());
				
		col_id_inst.setSortable(false);
		col_ninst_inst.setSortable(false);
		col_ver_inst.setSortable(false);
		col_mt_inst.setSortable(false);
		col_pmn_inst.setSortable(false);
		col_pmt_inst.setSortable(false);
		col_pmc1_inst.setSortable(false);
		col_pmc2_inst.setSortable(false);
		col_ool_inst.setSortable(false);
		col_oop_inst.setSortable(false);
		col_pos_inst.setSortable(false);
		col_sinfo_inst.setSortable(false);
		col_sdoc_inst.setSortable(false);
		col_qspec_inst.setSortable(false);
		col_pty_inst.setSortable(false);
		col_wt_inst.setSortable(false);
		col_adm2_inst.setSortable(false);
		col_adm3_inst.setSortable(false);
		col_of1_inst.setSortable(false);
		col_of2_inst.setSortable(false);
		
		table_inst.setEditable(true);
		
		Label per = new Label("PM Cycle1");
	      per.setTooltip(new Tooltip("����� ��� �� ������������. ���� � ������������ 1 ������� ���������� ���, �� � PM Cycle1 � PM Cycle2 \n"
					+ " ������ ���������� ��������, ��������: ��1 � ��1, ���� � ������������ 2 �������� ���������� ���, �� �\n"
					+ " PM Cycle1 � PM Cycle2 ������ ������ ��������, ��������: ��1 � ��1�"));
	      col_pmc1_inst.setGraphic(per);
	      Label per1 = new Label("PM Cycle2");
	      per1.setTooltip(new Tooltip("����� ��� �� ������������. ���� � ������������ 1 ������� ���������� ���, �� � PM Cycle1 � PM Cycle2 \n"
					+ " ������ ���������� ��������, ��������: ��1 � ��1, ���� � ������������ 2 �������� ���������� ���, �� �\n"
					+ " PM Cycle1 � PM Cycle2 ������ ������ ��������, ��������: ��1 � ��1�"));
	      col_pmc2_inst.setGraphic(per1);
		
		//�������� ���� ���������� �� �������� ����� �� ������
		table_inst.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (event.getClickCount() == 2 ){
		               func_upd(table_inst.getSelectionModel().getSelectedItem().getId());
		           }
			}
		});  
		
		//���� ����� ������ ������� ������������� ����� � ���������� ������ �������� � �������
	    table_inst.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
			// TODO Auto-generated method stub
				upd_inst.setDisable(false);
				//������������� ����� ��� ������ �������
				if(!conn_connector.USER_ROLE.equals("Engeneer"))
					del_inst.setDisable(false);
			}
		});
		
		add_inst.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					upd_inst.setDisable(true);
					del_inst.setDisable(true);
					
					pminst_add(stage);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		upd_inst.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				upd_inst.setDisable(true);
				del_inst.setDisable(true);
				hmmr_inst_model _ccl1 = table_inst.getSelectionModel().getSelectedItem();
				try {
				func_upd(_ccl1.getId());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});

		close_inst.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				stage = (Stage) close_inst.getScene().getWindow();
				stage.close();
			}
		});
		
		del_inst.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
			    alert.setTitle("M&U - Delete Record Window");
			    hmmr_inst_model _ccl = table_inst.getSelectionModel().getSelectedItem();
			    
			    alert.setHeaderText("�� ������������� ������ ������� ������ � = " + _ccl.getId() + "?");
			    //alert.setContentText("C:/MyFile.txt");
			 
			    // option != null.
			    Optional<ButtonType> option = alert.showAndWait();
			    if (option.get() == null) {
			       //label.setText("No selection!");
			    } else if (option.get() == ButtonType.OK) {
			  	   _ccl = table_inst.getSelectionModel().getSelectedItem();
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
		upd_table_inst.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				table_inst.setItems(qr._select_data_pminst());
				columns_inst.get(0).setVisible(false);
			    columns_inst.get(0).setVisible(true);
			}
		});
		 _table_update_inst.addListener(new ListChangeListener<hmmr_inst_model>() {
			    @Override
				public void onChanged(Change<? extends hmmr_inst_model> c) {
					table_inst.setItems(qr._select_data_pminst());
			    	table_inst.getColumns().get(0).setVisible(false);
			        table_inst.getColumns().get(0).setVisible(true);
				}
			});
	}
	
	private void initData()
	{
		table_inst.setItems(qr._select_data_pminst());
	}
	
	//�������� ���� ���������� ������ � PM_Instruction
		protected void pminst_add(Stage stage) throws IOException {
			setPrimaryStage(stage);
			Parent root = FXMLLoader.load(getClass().getResource("add_rec_inst.fxml"));
			   
		    Scene scene = new Scene(root);
		    stage.setTitle("M&U - Add Record Window");
		    stage.setResizable(false);
		    //stage.initModality(Modality.APPLICATION_MODAL);
		    stage.setScene(scene);
		    stage.show();
		}
		//�������� ���� ���������� ������
		protected void typepm_upd(Stage stage) throws IOException {
			Parent root = FXMLLoader.load(getClass().getResource("upd_rec_inst.fxml"));
			   
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
				
		private void func_upd(String str)
		{
			String _sql_rez = qr._select_for_update_pminst(str);
			_id_inst = str;
			_ninst_inst = scl.parser_sql_str(_sql_rez, 0);
			_date_create = scl.parser_sql_str(_sql_rez, 1);
			_date_change = scl.parser_sql_str(_sql_rez, 2);
			_inst_pdf = scl.parser_sql_str(_sql_rez, 3);
			_ver_inst = scl.parser_sql_str(_sql_rez, 4);
			_mt_inst = scl.parser_sql_str(_sql_rez, 5);
			_pmname_inst = scl.parser_sql_str(_sql_rez, 6); 
			_typepm_inst = scl.parser_sql_str(_sql_rez, 7);
			_cyclepm1_inst = scl.parser_sql_str(_sql_rez, 8);
			_cyclepm2_inst = scl.parser_sql_str(_sql_rez, 9);
			_line_inst = scl.parser_sql_str(_sql_rez, 10);
			_power_inst = scl.parser_sql_str(_sql_rez, 11);
			_pos_inst = scl.parser_sql_str(_sql_rez, 12);
			_sinfo_inst = scl.parser_sql_str(_sql_rez, 13);
			_sdoc_txt_inst = scl.parser_sql_str(_sql_rez, 14);
			_qtyspec_inst = scl.parser_sql_str(_sql_rez, 15);
			_ptw_inst = scl.parser_sql_str(_sql_rez, 16);
			_wt_inst = scl.parser_sql_str(_sql_rez, 17);
			_adm2_inst = scl.parser_sql_str(_sql_rez, 18);
			_adm3_inst = scl.parser_sql_str(_sql_rez, 19);
			_of1_inst = scl.parser_sql_str(_sql_rez, 20);
			_of2_inst = scl.parser_sql_str(_sql_rez, 21);
			
			try {
				typepm_upd(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private void func_del(String str)
		{
			qr._update_rec_pminst_del(str);
			qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - ������ ������ � = " + str + " � ������� PM Instruction");
			_id_inst = str;
			_table_update_inst.addAll(qr._select_data_pminst());
		}
		
		private void lang_fun(String loc1, String loc2)
		{
			ResourceBundle lngBndl = ResourceBundle
		            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
			
			col_ninst_inst.setText(lngBndl.getString("col_ninst_inst"));
			col_ver_inst.setText(lngBndl.getString("col_ver_inst"));
			col_mt_inst.setText(lngBndl.getString("col_mt_inst"));
			col_pmn_inst.setText(lngBndl.getString("col_pmn_inst"));
			col_pmt_inst.setText(lngBndl.getString("col_pmt_inst"));
			col_pmc1_inst.setText(lngBndl.getString("col_pmc1_inst"));
			col_pmc2_inst.setText(lngBndl.getString("col_pmc2_inst"));
			col_ool_inst.setText(lngBndl.getString("col_ool_inst"));
			col_oop_inst.setText(lngBndl.getString("col_oop_inst"));
			col_pos_inst.setText(lngBndl.getString("col_pos_inst"));
			col_sinfo_inst.setText(lngBndl.getString("col_sinfo_inst"));
			col_sdoc_inst.setText(lngBndl.getString("col_sdoc_inst"));
			
			col_qspec_inst.setText(lngBndl.getString("col_qspec_inst"));
			col_pty_inst.setText(lngBndl.getString("col_pty_inst"));
			col_wt_inst.setText(lngBndl.getString("col_wt_inst"));
			col_adm2_inst.setText(lngBndl.getString("col_adm2_inst"));
			col_adm3_inst.setText(lngBndl.getString("col_adm3_inst"));
			col_of1_inst.setText(lngBndl.getString("col_of1_inst"));
			col_of2_inst.setText(lngBndl.getString("col_of2_inst"));
			col_date_create_pi.setText(lngBndl.getString("col_date_create_pi"));
			col_date_change_pi.setText(lngBndl.getString("col_date_change_pi"));
			col_inst_pi.setText(lngBndl.getString("inst_l")+ " PDF");
			add_inst.setText(lngBndl.getString("add_tsk"));
			upd_inst.setText(lngBndl.getString("upd_wr"));
			del_inst.setText(lngBndl.getString("del_inst"));
			close_inst.setText(lngBndl.getString("cancel_tsk"));
			head_pm.setText(lngBndl.getString("head_pm"));
			upd_table_inst.setText(lngBndl.getString("upd_table_wr"));
		}
		
		@SuppressWarnings("unused")
		private void setPrimaryStage(Stage pStage) {
	        Main.pStage = pStage;
	    }
		public static Stage getPrimaryStage() {
	        return pStage;
	    }
		
		//��������� ����� ��� ��������� ���������� ������ � �������
		/*		@SuppressWarnings("rawtypes")
				public Task update_table()
				{
					Task task = new Task<Void>() {
						
						@Override public Void call() throws InterruptedException {
							while(_flag)
							{
								Platform.runLater(new Runnable() {
							        @Override
							        public void run() {
							        	table_inst.setItems(qr._select_data_pminst());
										table_inst.getColumns().get(0).setVisible(false);
								        table_inst.getColumns().get(0).setVisible(true);
							        }
							      });
						    	
						        TimeUnit.SECONDS.sleep(1);
							}
							return null;
						}
					};
				return task;
				}*/
}