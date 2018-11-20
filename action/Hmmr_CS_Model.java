package action;

import javafx.beans.property.SimpleStringProperty;

public class Hmmr_CS_Model {
	
	public SimpleStringProperty Id = new SimpleStringProperty();
	public SimpleStringProperty HMMR_Material_Num_Complex = new SimpleStringProperty();
	public SimpleStringProperty HMMR_Material_Num_Sub = new SimpleStringProperty();

	public Hmmr_CS_Model()
	{
		
	}
	public Hmmr_CS_Model(String Id, String HMMR_Material_Num_Complex, String HMMR_Material_Num_Sub)
	{
		this.Id.set(Id);
		this.HMMR_Material_Num_Complex.set(HMMR_Material_Num_Complex);
		this.HMMR_Material_Num_Sub.set(HMMR_Material_Num_Sub);
	}
	public SimpleStringProperty getId() {
		return Id;
	}
	public void setId(SimpleStringProperty id) {
		Id = id;
	}
	public SimpleStringProperty getHMMR_Material_Num_Complex() {
		return HMMR_Material_Num_Complex;
	}
	public void setHMMR_Material_Num_Complex(SimpleStringProperty hMMR_Material_Num_Complex) {
		HMMR_Material_Num_Complex = hMMR_Material_Num_Complex;
	}
	public SimpleStringProperty getHMMR_Material_Num_Sub() {
		return HMMR_Material_Num_Sub;
	}
	public void setHMMR_Material_Num_Sub(SimpleStringProperty hMMR_Material_Num_Sub) {
		HMMR_Material_Num_Sub = hMMR_Material_Num_Sub;
	}
	
	public String getIdStr() {
		return Id.get();
	}
	public String getHMMR_Material_Num_ComplexStr() {
		return HMMR_Material_Num_Complex.get();
	}
	public String getHMMR_Material_Num_SubStr() {
		return HMMR_Material_Num_Sub.get();
	}
}
