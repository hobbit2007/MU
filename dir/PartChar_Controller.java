package dir;

import com.jfoenix.controls.JFXButton;

import db._query;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import share_class.s_class;

public class PartChar_Controller {
	
	@FXML
	TableView<Hmmr_PartChar_Model> table_partchar = new TableView<>();
	
	@FXML
	TableColumn<Hmmr_PartChar_Model, String> col_id_pc, col_Part_Characteristic_Name, col_Part_Characteristic_Name_ENG, col_Part_Type, col_SP_KIND, col_Part_Sub_Type, 
		col_Part_Sub_Type_ENG, col_Part_Characteristic_Name_1, col_Part_Characteristic_Name_2, col_Part_Characteristic_Name_3, col_Part_Characteristic_Name_4;
	
	@FXML
	JFXButton btn_add_pc, btn_upd_pc, btn_del_pc, btn_upd_tbl_pc, btn_cancel_pc;
	
	_query qr = new _query();
	s_class scl = new s_class();
	
	@FXML
	public void initialize()
	{
		scl._style(btn_add_pc);
		scl._style(btn_upd_pc);
		scl._style(btn_del_pc);
		scl._style(btn_cancel_pc);
		scl._style(btn_upd_tbl_pc);
		
		btn_upd_pc.setDisable(true);
		btn_del_pc.setDisable(true);
		
		initData();
		
		col_id_pc.setCellValueFactory(CellData -> CellData.getValue().getId());
		col_Part_Characteristic_Name.setCellValueFactory(CellData -> CellData.getValue().getPart_Characteristic_Name());
		col_Part_Characteristic_Name_ENG.setCellValueFactory(CellData -> CellData.getValue().getPart_Characteristic_Name_ENG());
		col_Part_Type.setCellValueFactory(CellData -> CellData.getValue().getPart_Type());
		col_SP_KIND.setCellValueFactory(CellData -> CellData.getValue().getSP_KIND());
		col_Part_Sub_Type.setCellValueFactory(CellData -> CellData.getValue().getPart_Sub_Type());
		col_Part_Sub_Type_ENG.setCellValueFactory(CellData -> CellData.getValue().getPart_Sub_Type_ENG());
		col_Part_Characteristic_Name_1.setCellValueFactory(CellData -> CellData.getValue().getPart_Characteristic_Name_1());
		col_Part_Characteristic_Name_2.setCellValueFactory(CellData -> CellData.getValue().getPart_Characteristic_Name_2());
		col_Part_Characteristic_Name_3.setCellValueFactory(CellData -> CellData.getValue().getPart_Characteristic_Name_3());
		col_Part_Characteristic_Name_4.setCellValueFactory(CellData -> CellData.getValue().getPart_Characteristic_Name_4());
	}

	void initData()
	{
		table_partchar.setItems(qr._select_data_partchar());
	}
}
