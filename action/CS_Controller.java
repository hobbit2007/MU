package action;

import com.jfoenix.controls.JFXButton;

import db._query;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import share_class.s_class;

public class CS_Controller {
	
	@FXML
	TableView<Hmmr_CS_Model> table_cs = new TableView<>();
	
	@FXML
	TableColumn<Hmmr_CS_Model, String> col_id_cs, col_comp_id, col_sub_id;
	
	@FXML
	JFXButton btn_add_rec, btn_upd_rec, btn_del_rec, btn_upd_tbl, btn_cancel;
	
	@FXML
	Label lbl_title_cs;
	
	_query qr = new _query();
	s_class scl = new s_class();
	
	String name_complex = "Название материала (complex)";
	private String name_subpart = "Название материала (sub-part)";
	TableColumn<Hmmr_CS_Model, String> col_complex_id = new TableColumn<Hmmr_CS_Model, String>(name_complex);
	TableColumn<Hmmr_CS_Model, String> col_subpart_id = new TableColumn<Hmmr_CS_Model, String>(name_subpart);
	
	public void initialize()
	{
		scl._style(btn_add_rec);
		scl._style(btn_upd_rec);
		scl._style(btn_del_rec);
		scl._style(btn_upd_tbl);
		scl._style(btn_cancel);
		
		btn_upd_rec.setDisable(true);
		btn_del_rec.setDisable(true);
		
		table_cs.setEditable(true);
		
		initData();
		
		col_id_cs.setCellValueFactory(CellData -> CellData.getValue().getId());
		col_comp_id.setCellValueFactory(CellData -> CellData.getValue().getHMMR_Material_Num_Complex());
		col_sub_id.setCellValueFactory(CellData -> CellData.getValue().getHMMR_Material_Num_Sub());
		
		col_complex_id.setPrefWidth(350.0);
		col_subpart_id.setPrefWidth(350.0);
		
		final ObservableList<TableColumn<Hmmr_CS_Model, ?>> columns_complex = table_cs.getColumns();
		col_complex_id.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Hmmr_CS_Model, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Hmmr_CS_Model, String> arg0) {
                        Hmmr_CS_Model data = arg0.getValue();
                        return new SimpleObjectProperty<String>(qr._select_fillcs(data.getHMMR_Material_Num_ComplexStr()));
                    }

                });
        
        columns_complex.add(col_complex_id);
        
        final ObservableList<TableColumn<Hmmr_CS_Model, ?>> columns_subpart = table_cs.getColumns();
		col_subpart_id.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Hmmr_CS_Model, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Hmmr_CS_Model, String> arg0) {
                        Hmmr_CS_Model data = arg0.getValue();
                        return new SimpleObjectProperty<String>(qr._select_fillcs(data.getHMMR_Material_Num_SubStr()));
                    }

                });
        
        columns_subpart.add(col_subpart_id);
		
	}
	
	void initData()
	{
		table_cs.setItems(qr._select_data_cs());
	}

}
