package action;

import com.jfoenix.controls.JFXButton;

import db._query;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import share_class.s_class;

public class SP_Controller {
	
	@FXML
	TableView<Hmmr_SP_Model> table_sp = new TableView<>();
	
	@FXML
	TableColumn<Hmmr_SP_Model, String> col_id_sp, col_HMMR_Material_Num, col_Manufacturer, col_Model, col_Article, col_Single_Complex_Sub, col_SP_MU_Description_RUS,
	col_SP_FD_Description, col_SP_Supplier_Description, col_Kind, col_SP_Part_Type, col_SP_Sub_Part_Type, col_Part_Characteristic_1, col_Part_Characteristic_2,
	col_Part_Characteristic_3, col_Part_Characteristic_4, col_Qty_S, col_Qty_W, col_Qty_P, col_Qty_A, col_Price, col_Key_No_Backup_Yes, col_Key_No_Backup_No,
	col_Key_Yes_Backup_Yes, col_Key_Yes_Backup_No, col_Risk_Breakage, col_Delivery_Time, col_Replacement_Model, col_Qty_Interchangeability, col_Identity_SP, 
	col_Qty_Identify_SP, col_Coefficient, col_MIN, col_BATCH;
	
	@FXML
	JFXButton btn_add_sp, btn_upd_sp, btn_del_sp, btn_upd_tbl_sp, btn_cancel_sp;
	
	@FXML
	Label lbl_title_sp;
	
	@FXML
	ScrollPane sp_sp;
	
	@FXML
	AnchorPane ap_sp;
	
	@FXML
	VBox vb_sp;
	
	@FXML
	HBox hb_sp1, hb_sp2, hb_sp3;
	
	_query qr = new _query();
	s_class scl = new s_class();
	
	@FXML
	public void initialize()
	{
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		Double screen_width = primaryScreenBounds.getWidth();
		Double screen_hight = primaryScreenBounds.getHeight(); 
		
		sp_sp.setPrefWidth(screen_width);
		sp_sp.setPrefHeight(screen_hight);
		ap_sp.setPrefWidth(screen_width - 20);
		ap_sp.setPrefHeight(screen_hight - 20);
		vb_sp.setPrefWidth(screen_width - 20);
		vb_sp.setPrefHeight(screen_hight - 20);
		hb_sp1.setPrefWidth(screen_width - 20);
		hb_sp1.setPrefHeight(30.0);
		hb_sp2.setPrefWidth(screen_width - 20);
		hb_sp2.setPrefHeight(screen_hight - 150);
		hb_sp3.setPrefWidth(screen_width - 20);
		hb_sp3.setPrefHeight(70.0);
		
		table_sp.setPrefWidth(screen_width-70);
		table_sp.setPrefHeight(screen_hight-170);
		
		scl._style(btn_add_sp);
		scl._style(btn_upd_sp);
		scl._style(btn_del_sp);
		scl._style(btn_upd_tbl_sp);
		scl._style(btn_cancel_sp);
		
		initData();
		
		col_id_sp.setCellValueFactory(CellData -> CellData.getValue().getId());
		col_HMMR_Material_Num.setCellValueFactory(CellData -> CellData.getValue().getHMMR_Material_Num());
		col_Manufacturer.setCellValueFactory(CellData -> CellData.getValue().getManufacturer());
		col_Model.setCellValueFactory(CellData -> CellData.getValue().getModel());
		col_Article.setCellValueFactory(CellData -> CellData.getValue().getArticle());
		col_Single_Complex_Sub.setCellValueFactory(CellData -> CellData.getValue().getSingle_Complex_Sub());
		col_SP_MU_Description_RUS.setCellValueFactory(CellData -> CellData.getValue().getSP_MU_Description_RUS());
		col_SP_FD_Description.setCellValueFactory(CellData -> CellData.getValue().getSP_FD_Description());
		col_SP_Supplier_Description.setCellValueFactory(CellData -> CellData.getValue().getSP_Supplier_Description());
		col_Kind.setCellValueFactory(CellData -> CellData.getValue().getKind());
		col_SP_Part_Type.setCellValueFactory(CellData -> CellData.getValue().getSP_Part_Type());
		col_SP_Sub_Part_Type.setCellValueFactory(CellData -> CellData.getValue().getSP_Sub_Part_Type());
		col_Part_Characteristic_1.setCellValueFactory(CellData -> CellData.getValue().getPart_Characteristic_1());
		col_Part_Characteristic_2.setCellValueFactory(CellData -> CellData.getValue().getPart_Characteristic_2());
		col_Part_Characteristic_3.setCellValueFactory(CellData -> CellData.getValue().getPart_Characteristic_3());
		col_Part_Characteristic_4.setCellValueFactory(CellData -> CellData.getValue().getPart_Characteristic_4());
		col_Qty_S.setCellValueFactory(CellData -> CellData.getValue().getQty_S());
		col_Qty_W.setCellValueFactory(CellData -> CellData.getValue().getQty_W());
		col_Qty_P.setCellValueFactory(CellData -> CellData.getValue().getQty_P());
		col_Qty_A.setCellValueFactory(CellData -> CellData.getValue().getQty_A());
		col_Price.setCellValueFactory(CellData -> CellData.getValue().getPrice());
		col_Key_No_Backup_Yes.setCellValueFactory(CellData -> CellData.getValue().getKey_No_Backup_Yes());
		col_Key_No_Backup_No.setCellValueFactory(CellData -> CellData.getValue().getKey_No_Backup_No());
		col_Key_Yes_Backup_Yes.setCellValueFactory(CellData -> CellData.getValue().getKey_Yes_Backup_Yes());
		col_Key_Yes_Backup_No.setCellValueFactory(CellData -> CellData.getValue().getKey_Yes_Backup_No());
		col_Risk_Breakage.setCellValueFactory(CellData -> CellData.getValue().getRisk_Breakage());
		col_Delivery_Time.setCellValueFactory(CellData -> CellData.getValue().getDelivery_Time());
		col_Replacement_Model.setCellValueFactory(CellData -> CellData.getValue().getReplacement_Model());
		col_Qty_Interchangeability.setCellValueFactory(CellData -> CellData.getValue().getQty_Interchangeability());
		col_Identity_SP.setCellValueFactory(CellData -> CellData.getValue().getIdentity_SP());
		col_Qty_Identify_SP.setCellValueFactory(CellData -> CellData.getValue().getQty_Identify_SP());
		col_Coefficient.setCellValueFactory(CellData -> CellData.getValue().getCoefficient());
		col_MIN.setCellValueFactory(CellData -> CellData.getValue().getMIN());
		col_BATCH.setCellValueFactory(CellData -> CellData.getValue().getBATCH());
	}
	
	void initData()
	{
		table_sp.setItems(qr._select_data_sp());
	}

}
