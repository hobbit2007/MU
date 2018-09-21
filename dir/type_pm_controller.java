package dir;

import java.io.IOException;
import java.util.Optional;
import com.jfoenix.controls.JFXButton;
import action.apwr_controller;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import share_class.s_class;

public class type_pm_controller
{
	@FXML
	TableView<type_pm> table_tpm;
	
	@FXML
	TableColumn<type_pm, String> col_id_tpm;
	
	@FXML
	TableColumn<type_pm, String> col_type_tpm;
	
	@FXML
	TableColumn<type_pm, String> col_desc_tpm;
	
	@FXML
	JFXButton add_tpm, upd_tpm, del_tpm, cancel_tpm, upd_table_type;
	
	_query qr = new _query();
	private Stage stage = new Stage();
	s_class scl = new s_class();
	
	public static ObservableList<TableColumn<type_pm, ?>> columns_type;
	public static ObservableList<type_pm> _table_update_typepm = FXCollections.observableArrayList();
		
	public static String _id_tpm, _type_tpm, _desc_tpm;
		
	@FXML
	public void initialize() {
		scl._style(add_tpm);
		scl._style(upd_tpm);
		scl._style(del_tpm);
		scl._style(cancel_tpm);
		
       	initData();
       	
       	columns_type = table_tpm.getColumns();
       	
       	table_tpm.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
        
       	col_id_tpm.setCellValueFactory(CellData -> CellData.getValue().IdProperty());
       	col_type_tpm.setCellValueFactory(CellData -> CellData.getValue().TypeProperty());
       	col_desc_tpm.setCellValueFactory(CellData -> CellData.getValue().DescProperty());
       	
       	col_id_tpm.setSortable(false);
       	col_type_tpm.setSortable(false);
       	col_desc_tpm.setSortable(false);
       	
       	table_tpm.setEditable(true);
       	
       	add_tpm.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					//_flag = false;
					typepm_add(stage);
					//_flag = true;
					//t = new Thread(update_table());
	        		//t.setDaemon(true);
	        		//t.start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
      cancel_tpm.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			stage = (Stage) cancel_tpm.getScene().getWindow();
			stage.close();
		}
	}); 
      upd_tpm.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			upd_tpm.setDisable(true);
			del_tpm.setDisable(true);
			type_pm _ccl1 = table_tpm.getSelectionModel().getSelectedItem();
			try {
			func_upd(_ccl1.getId());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	});
      
      del_tpm.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
		    alert.setTitle("M&U - Delete Record Window");
		    type_pm _ccl = table_tpm.getSelectionModel().getSelectedItem();
		    
		    alert.setHeaderText("Вы действительно хотите удалить запись № = " + _ccl.getId() + "?");
		    //alert.setContentText("C:/MyFile.txt");
		 
		    // option != null.
		    Optional<ButtonType> option = alert.showAndWait();
		    if (option.get() == null) {
		       //label.setText("No selection!");
		    } else if (option.get() == ButtonType.OK) {
		  	   _ccl = table_tpm.getSelectionModel().getSelectedItem();
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
    
    //Вызываем окно обновления по двойному клику на строке
    table_tpm.setOnMousePressed(new EventHandler<MouseEvent>() {
    	@Override
    	public void handle(MouseEvent event) {
    		// TODO Auto-generated method stub
    		if (event.getClickCount() == 2 ){
                   func_upd(table_tpm.getSelectionModel().getSelectedItem().getId());
               }
    	}
    });  
    
      //Клик мышью внутри таблицы останавливает поток и активирует кнопки Обновить и Удалить
        table_tpm.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				upd_tpm.setDisable(false);
				if(!conn_connector.USER_ROLE.equals("Technics") || !conn_connector.USER_ROLE.equals("Engeneer"))
					del_tpm.setDisable(false);
			}
		});
        
        upd_table_type.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				table_tpm.setItems(qr._select_data_typepm());
				columns_type.get(0).setVisible(false);
			    columns_type.get(0).setVisible(true);
			}
		});
        
        _table_update_typepm.addListener(new ListChangeListener<type_pm>() {
		    @Override
			public void onChanged(Change<? extends type_pm> c) {
				table_tpm.setItems(qr._select_data_typepm());
		    	table_tpm.getColumns().get(0).setVisible(false);
		        table_tpm.getColumns().get(0).setVisible(true);
			}
		});
    }
	
	//Вызываем окно добавления записи к справочнику PM_Cycle
	protected void typepm_add(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("add_rec_typepm.fxml"));
		   
	    Scene scene = new Scene(root);
	    stage.setTitle("M&U - Add Record Window");
	    stage.setResizable(false);
	    //stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.show();
	}
	//Вызываем окно обновления записи
	protected void typepm_upd(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("upd_rec_typepm.fxml"));
		   
	    Scene scene = new Scene(root);
	    stage.setTitle("M&U - Update Record Window");
	    stage.setResizable(false);
	    //stage.initModality(Modality.APPLICATION_MODAL);
	    stage.setScene(scene);
	    stage.show();
	}

	private void initData()
	{
		table_tpm.setItems(qr._select_data_typepm());
	}
	
	private String parser_sql_str(String str, int count)
	{
		String[] p_str = null;
		for(int i = 0; i < str.length(); i++)
		{
			p_str = str.split(","); 
		}
		return p_str[count];
	}
	
	private void func_upd(String str)
	{
		String _sql_rez = qr._select_for_update_typepm(str);
		_id_tpm = str;
		_type_tpm = parser_sql_str(_sql_rez, 0);
		_desc_tpm = parser_sql_str(_sql_rez, 1);
		try {
			typepm_upd(stage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void func_del(String str)
	{
		qr._update_rec_typepm_del(str);
		qr._insert_history(conn_connector.USER_ID, apwr_controller.USER_S + " - Удалил запись № = " + str + " в таблице Type PM");
		_id_tpm = str;
	}
}