package action;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import share_class.s_class;

public class ps_controller {
	
	@FXML 
	TableView<hmmr_ps_model> table_ps = new TableView<hmmr_ps_model>();
	
	@FXML
	TableColumn<hmmr_ps_model, String> col_num_ps, col_company_ps, col_plant_ps, col_shops_ps, col_groups_ps, col_lines_ps, col_oss_ps, col_equips_ps, col_shop_ps, col_line_ps, col_linerus_ps,
		col_os_ps, col_osrus_ps, col_equip_ps, col_description_ps, col_equiplabel_ps, col_stationlabel_ps, col_passport_ps, col_manual_ps, col_stsupplier_ps, col_location_ps, col_room_ps,
		col_coord_ps, col_alt_ps, col_cham_ps, col_trcu_ps, col_trcul_ps, col_hazardous_ps, col_keyequip_ps, col_type_ps, col_sn_ps, col_manuf_ps, col_mtc_ps, col_respons_ps,
		col_melec_ps, col_mair_ps, col_mwater_ps, col_mcwater_ps, col_mhwater_ps, col_rowater_ps, col_mgas_ps;
	
	@FXML
	JFXButton add_ps, upd_ps, del_ps, upd_table;
	
	@FXML
	javafx.scene.control.ScrollPane sp_ps;
	
	public static ObservableList<TableColumn<hmmr_ps_model, ?>> columns_ps;
	public static ObservableList<hmmr_ps_model> _table_update_ps = FXCollections.observableArrayList();
	
	_query qr = new _query();
	s_class scl = new s_class();
	
	public Stage stage = new Stage();
	
	public static String _id_ps, _num_ps, _company_ps, _plant_ps, _shops_ps, _groups_ps, _lines_ps, _oss_ps, _equips_ps, _shop_ps, _line_ps, _linerus_ps,
	_os_ps, _osrus_ps, _equip_ps, _description_ps, _subnumber_ps, _passport_ps, _manual_ps, _stsupplier_ps, _location_ps, _room_ps,
	_coord_ps, _alt_ps, _cham_ps, _trcu_ps, _trcul_ps, _hazardous_ps, _keyequip_ps, _type_ps, _sn_ps, _manuf_ps, _mtc_ps, _respons_ps,
	_melec_ps, _mair_ps, _mwater_ps, _mcwater_ps, _mhwater_ps, _rowater_ps, _mgas_ps;
	
	@FXML
	public void initialize()
	{
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		Double screen_width = primaryScreenBounds.getWidth();
		Double screen_hight = primaryScreenBounds.getHeight(); 
		
		sp_ps.setPrefWidth(screen_width - 530);
		sp_ps.setPrefHeight(screen_hight - 30);
		
		table_ps.setPrefHeight(screen_hight - 230);
		
		add_ps.setLayoutY(screen_hight - 110);
		upd_ps.setLayoutY(screen_hight - 110);
		del_ps.setLayoutY(screen_hight - 110);
		upd_table.setLayoutY(screen_hight - 110);
		
		upd_ps.setDisable(true);
		del_ps.setDisable(true);
		
		if(conn_connector.USER_ROLE.equals("Technics") || conn_connector.USER_ROLE.equals("Engeneer"))
			del_ps.setDisable(true);
		
		if(conn_connector.LANG_ID == 1)
			lang_fun("en", "EN");
		if(conn_connector.LANG_ID == 0)
			lang_fun("ru", "RU");
		if(conn_connector.LANG_ID == 2)
			lang_fun("zh", "CN");
		if(conn_connector.LANG_ID == -1)
			lang_fun("ru", "RU");
		
		scl._style(add_ps);
		scl._style(upd_ps);
		scl._style(del_ps);
		scl._style(upd_table);
		
		initData();
		
		col_num_ps.setCellValueFactory(CellData -> CellData.getValue().IdProperty());
		col_company_ps.setCellValueFactory(CellData -> CellData.getValue().CompanyProperty());
		col_plant_ps.setCellValueFactory(CellData -> CellData.getValue().PlantProperty());
		col_shops_ps.setCellValueFactory(CellData -> CellData.getValue().Shop_sProperty());
		col_groups_ps.setCellValueFactory(CellData -> CellData.getValue().Group_PMProperty());
		col_lines_ps.setCellValueFactory(CellData -> CellData.getValue().Line_Machine_sProperty());
		col_oss_ps.setCellValueFactory(CellData -> CellData.getValue().Operation_Station_sProperty());
		col_equips_ps.setCellValueFactory(CellData -> CellData.getValue().Equipment_sProperty());
		col_shop_ps.setCellValueFactory(CellData -> CellData.getValue().ShopProperty());
		col_line_ps.setCellValueFactory(CellData -> CellData.getValue().Line_MachineProperty());
		col_linerus_ps.setCellValueFactory(CellData -> CellData.getValue().Line_Machine_RUSProperty());
		col_os_ps.setCellValueFactory(CellData -> CellData.getValue().Operation_StationProperty());
		col_osrus_ps.setCellValueFactory(CellData -> CellData.getValue().Operation_Station_RUSProperty());
		col_equip_ps.setCellValueFactory(CellData -> CellData.getValue().EquipmentProperty());
		col_description_ps.setCellValueFactory(CellData -> CellData.getValue().DescriptionProperty());
		col_equiplabel_ps.setCellValueFactory(CellData -> CellData.getValue().Equip_labelProperty());
		col_stationlabel_ps.setCellValueFactory(CellData -> CellData.getValue().Station_labelProperty());
		col_passport_ps.setCellValueFactory(CellData -> CellData.getValue().passportProperty());
		col_manual_ps.setCellValueFactory(CellData -> CellData.getValue().manualProperty());
		col_stsupplier_ps.setCellValueFactory(CellData -> CellData.getValue().Station_SupplierProperty());
		col_location_ps.setCellValueFactory(CellData -> CellData.getValue().LocationProperty());
		col_room_ps.setCellValueFactory(CellData -> CellData.getValue().Room_categoryProperty());
		col_coord_ps.setCellValueFactory(CellData -> CellData.getValue().CoordinatesProperty());
		col_alt_ps.setCellValueFactory(CellData -> CellData.getValue().AltitudeProperty());
		col_cham_ps.setCellValueFactory(CellData -> CellData.getValue().CHAMBERProperty());
		col_trcu_ps.setCellValueFactory(CellData -> CellData.getValue().TR_CUProperty());
		col_trcul_ps.setCellValueFactory(CellData -> CellData.getValue().TR_CU_LinkProperty());
		col_hazardous_ps.setCellValueFactory(CellData -> CellData.getValue().HazardousProperty());
		col_keyequip_ps.setCellValueFactory(CellData -> CellData.getValue().Key_equipmentProperty());
		col_type_ps.setCellValueFactory(CellData -> CellData.getValue().TypetProperty());
		col_sn_ps.setCellValueFactory(CellData -> CellData.getValue().S_NProperty());
		col_manuf_ps.setCellValueFactory(CellData -> CellData.getValue().ManufProperty());
		col_mtc_ps.setCellValueFactory(CellData -> CellData.getValue().MTCProperty());
		col_respons_ps.setCellValueFactory(CellData -> CellData.getValue().RespProperty());
		col_melec_ps.setCellValueFactory(CellData -> CellData.getValue().M_ElectricProperty());
		col_mair_ps.setCellValueFactory(CellData -> CellData.getValue().M_AirProperty());
		col_mwater_ps.setCellValueFactory(CellData -> CellData.getValue().M_WaterProperty());
		col_mcwater_ps.setCellValueFactory(CellData -> CellData.getValue().M_Cold_waterProperty());
		col_mhwater_ps.setCellValueFactory(CellData -> CellData.getValue().M_Hot_waterProperty());
		col_rowater_ps.setCellValueFactory(CellData -> CellData.getValue().RO_WaterProperty());
		col_mgas_ps.setCellValueFactory(CellData -> CellData.getValue().M_GasProperty());
		
		//Разрешаем ходить по ячейкам, а не целой строкой
        table_ps.getSelectionModel().setCellSelectionEnabled(true);
        
        table_ps.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);;
		
		add_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					addps_start(stage);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		columns_ps = table_ps.getColumns();	
		upd_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				hmmr_ps_model _ccl1 = table_ps.getSelectionModel().getSelectedItem();
				
				upd_ps.setDisable(true);
				func_upd(_ccl1.getId());
			}
		});
		table_ps.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				upd_ps.setDisable(false);
				if(!conn_connector.USER_ROLE.equals("Technics") || !conn_connector.USER_ROLE.equals("Engeneer"))
					del_ps.setDisable(false);
			}
		});
		upd_table.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				table_ps.setItems(qr._select_data_ps());
				columns_ps.get(0).setVisible(false);
			    columns_ps.get(0).setVisible(true);
			}
		});
		del_ps.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Alert alert = new Alert(AlertType.CONFIRMATION);
			    alert.setTitle("M&U - Delete Record Window");
			    hmmr_ps_model _ccl = table_ps.getSelectionModel().getSelectedItem();
			    
			    alert.setHeaderText("Вы действительно хотите удалить запись № = " + _ccl.getId() + "?");
			    //alert.setContentText("C:/MyFile.txt");
			 
			    // option != null.
			    Optional<ButtonType> option = alert.showAndWait();
			    if (option.get() == null) {
			       //label.setText("No selection!");
			    } else if (option.get() == ButtonType.OK) {
			  	   _ccl = table_ps.getSelectionModel().getSelectedItem();
			  	   try {
			  	   qr._update_rec_ps_del(_ccl.getId());
			  	   qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Удалил запись № = " + _ccl.getId() + " в таблице Plant Structure");
			  	   _table_update_ps.addAll(qr._select_data_ps());
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
		_table_update_ps.addListener(new ListChangeListener<hmmr_ps_model>() {
		    @Override
			public void onChanged(Change<? extends hmmr_ps_model> c) {
				// TODO Auto-generated method stub
		    	
		    	table_ps.setItems(qr._select_data_ps());
		    	table_ps.getColumns().get(0).setVisible(false);
		        table_ps.getColumns().get(0).setVisible(true);
			}
		});
		//Вызываем окно обновления по двойному клику на строке
		table_ps.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if (event.getClickCount() == 2 ){
	                func_upd(table_ps.getSelectionModel().getSelectedItem().getId());
	            }
			}
		});
	}
	
	private void initData()
	{
		table_ps.setItems(qr._select_data_ps());
	}
	//Вызываем окно записи для PS
	protected void addps_start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("add_rec_ps.fxml"));
		   
	    Scene scene = new Scene(root);
	    stage.setTitle("M&U - Add Record Window");
	    stage.setResizable(true);
	    //stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public void refreshTable_ps(@SuppressWarnings("rawtypes") ObservableList col) {
		 table_ps.getColumns().removeAll(columns_ps);
		 table_ps.getColumns().addAll(columns_ps);
		 table_ps.setItems(qr._select_data_ps());
		 columns_ps.get(0).setVisible(false);
	     columns_ps.get(0).setVisible(true);
	 }
	
	private void func_upd(String id)
	{
		String _sql_rez = qr._select_for_update_ps(id);
		_id_ps = id;
		
		_num_ps = scl.parser_str_str_str(_sql_rez, 0);
		_company_ps = scl.parser_str_str_str(_sql_rez, 1);
		_plant_ps = scl.parser_str_str_str(_sql_rez, 2);
		_shops_ps = scl.parser_str_str_str(_sql_rez, 3);
		_groups_ps = scl.parser_str_str_str(_sql_rez, 4);
		_lines_ps = scl.parser_str_str_str(_sql_rez, 5);
		_oss_ps = scl.parser_str_str_str(_sql_rez, 6);
		_equips_ps = scl.parser_str_str_str(_sql_rez, 7);
		_shop_ps = scl.parser_str_str_str(_sql_rez, 8);
		_line_ps = scl.parser_str_str_str(_sql_rez, 9);
		_linerus_ps = scl.parser_str_str_str(_sql_rez, 10);
		_os_ps = scl.parser_str_str_str(_sql_rez, 11);
		_osrus_ps = scl.parser_str_str_str(_sql_rez, 12);
		_equip_ps = scl.parser_str_str_str(_sql_rez, 13);
		_description_ps = scl.parser_str_str_str(_sql_rez, 14);
		_subnumber_ps = scl.parser_str_str_str(_sql_rez, 15);
		_passport_ps = scl.parser_str_str_str(_sql_rez, 16);
		_manual_ps = scl.parser_str_str_str(_sql_rez, 17);
		_stsupplier_ps = scl.parser_str_str_str(_sql_rez, 18);
		_location_ps = scl.parser_str_str_str(_sql_rez, 19);
		_room_ps = scl.parser_str_str_str(_sql_rez, 20);
		_coord_ps = scl.parser_str_str_str(_sql_rez, 21);
		_alt_ps = scl.parser_str_str_str(_sql_rez, 22);
		_cham_ps = scl.parser_str_str_str(_sql_rez, 23);
		_trcu_ps = scl.parser_str_str_str(_sql_rez, 24);
		_trcul_ps = scl.parser_str_str_str(_sql_rez, 25);
		_hazardous_ps = scl.parser_str_str_str(_sql_rez, 26);
		_keyequip_ps = scl.parser_str_str_str(_sql_rez, 27);
		_type_ps = scl.parser_str_str_str(_sql_rez, 28);
		_sn_ps = scl.parser_str_str_str(_sql_rez, 29);
		_manuf_ps = scl.parser_str_str_str(_sql_rez, 30);
		_mtc_ps = scl.parser_str_str_str(_sql_rez, 31);
		_respons_ps = scl.parser_str_str_str(_sql_rez, 32);
		_melec_ps = scl.parser_str_str_str(_sql_rez, 33);
		_mair_ps = scl.parser_str_str_str(_sql_rez, 34);
		_mwater_ps = scl.parser_str_str_str(_sql_rez, 35);
		_mcwater_ps = scl.parser_str_str_str(_sql_rez, 36);
		_mhwater_ps = scl.parser_str_str_str(_sql_rez, 37);
		_rowater_ps = scl.parser_str_str_str(_sql_rez, 38);
		_mgas_ps = scl.parser_str_str_str(_sql_rez, 39);
		try {
			ps_upd(stage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Вызываем окно обновления записи
	protected void ps_upd(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("upd_rec_ps.fxml"));
				   
	    Scene scene = new Scene(root);
	    stage.setTitle("M&U - Update Record Window");
	    stage.setResizable(true);
	    //stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.show();
		}
	
	private void lang_fun(String loc1, String loc2)
	{
		ResourceBundle lngBndl = ResourceBundle
	            .getBundle("bundles.LangBundle", new Locale(loc1, loc2));
				
		col_company_ps.setText(lngBndl.getString("col_company_ps"));
		col_plant_ps.setText(lngBndl.getString("col_plant_ps"));
		col_shops_ps.setText(lngBndl.getString("col_shop_pm")+","+lngBndl.getString("lbl_short"));
		col_groups_ps.setText(lngBndl.getString("col_group_pm")+","+lngBndl.getString("lbl_short"));
		col_lines_ps.setText(lngBndl.getString("col_lm_pm")+","+lngBndl.getString("lbl_short"));
		col_oss_ps.setText(lngBndl.getString("col_os_pm")+","+lngBndl.getString("lbl_short"));
		col_equips_ps.setText(lngBndl.getString("col_equip_pm")+","+lngBndl.getString("lbl_short"));
		col_shop_ps.setText(lngBndl.getString("col_shop_pm"));
		col_line_ps.setText(lngBndl.getString("col_lm_pm"));
		col_linerus_ps.setText(lngBndl.getString("col_lm_pm")+","+lngBndl.getString("lbl_rus"));
		col_os_ps.setText(lngBndl.getString("col_os_pm"));
		col_osrus_ps.setText(lngBndl.getString("col_os_pm")+","+lngBndl.getString("lbl_rus"));
		col_equip_ps.setText(lngBndl.getString("col_equip_pm"));
		
		col_description_ps.setText(lngBndl.getString("desc_ap"));
		col_equiplabel_ps.setText(lngBndl.getString("col_equiplabel_ps"));
		col_stationlabel_ps.setText(lngBndl.getString("col_stationlabel_ps"));
		col_passport_ps.setText(lngBndl.getString("col_passport_ps"));
		col_manual_ps.setText(lngBndl.getString("col_sdoc_inst"));
		col_stsupplier_ps.setText(lngBndl.getString("col_stsupplier_ps"));
		col_location_ps.setText(lngBndl.getString("col_location_ps"));
		col_room_ps.setText(lngBndl.getString("col_room_ps"));
		col_coord_ps.setText(lngBndl.getString("col_coord_ps"));
		col_alt_ps.setText(lngBndl.getString("col_alt_ps"));
		col_cham_ps.setText(lngBndl.getString("col_cham_ps"));
		col_trcu_ps.setText(lngBndl.getString("col_trcu_ps"));
		col_trcul_ps.setText(lngBndl.getString("col_trcul_ps"));
		col_hazardous_ps.setText(lngBndl.getString("col_hazardous_ps"));
		col_keyequip_ps.setText(lngBndl.getString("col_keyequip_ps"));
		col_type_ps.setText(lngBndl.getString("lbl_type_ap"));
		col_sn_ps.setText(lngBndl.getString("col_sn_ps"));
		col_manuf_ps.setText(lngBndl.getString("col_manuf_ps"));
		col_mtc_ps.setText(lngBndl.getString("col_mtc_ps"));
		col_respons_ps.setText(lngBndl.getString("lbl_otv_ap"));
		col_melec_ps.setText(lngBndl.getString("col_melec_ps"));
		col_mair_ps.setText(lngBndl.getString("col_mair_ps"));
		col_mwater_ps.setText(lngBndl.getString("col_mwater_ps"));
		col_mcwater_ps.setText(lngBndl.getString("col_mcwater_ps"));
		col_mhwater_ps.setText(lngBndl.getString("col_mhwater_ps"));
		col_rowater_ps.setText(lngBndl.getString("col_rowater_ps"));
		col_mgas_ps.setText(lngBndl.getString("col_mgas_ps"));
		
		add_ps.setText(lngBndl.getString("add_tsk"));
		upd_ps.setText(lngBndl.getString("upd_wr"));
		del_ps.setText(lngBndl.getString("del_inst"));
		upd_table.setText(lngBndl.getString("upd_table_wr"));
	}
}
