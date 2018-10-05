package dir;

import javafx.beans.property.SimpleStringProperty;

public class hmmr_groupcycle_model {
	
	public SimpleStringProperty group_pm = new SimpleStringProperty();
	public SimpleStringProperty cycle_pm = new SimpleStringProperty();
	public SimpleStringProperty Id = new SimpleStringProperty();
	
	public hmmr_groupcycle_model()
	{
		
	}
	
	public hmmr_groupcycle_model(String Id, String gruop_pm, String cycle_pm)
	{
		this.Id.set(Id);
		this.group_pm.set(gruop_pm);
		this.cycle_pm.set(cycle_pm);
	}
	
	public SimpleStringProperty getId_() {
		return Id;
	}

	public void setId(SimpleStringProperty Id) {
		this.Id = Id;
	}

	public SimpleStringProperty getGroup_pm() {
		return group_pm;
	}

	public void setGroup_pm(SimpleStringProperty group_pm) {
		this.group_pm = group_pm;
	}

	public SimpleStringProperty getCycle_pm() {
		return cycle_pm;
	}

	public void setCycle_pm(SimpleStringProperty cycle_pm) {
		this.cycle_pm = cycle_pm;
	}
	
	public String getId()
	{
		return Id.get();
	}
	
	public String getGroupStr()
	{
		return group_pm.get();
	}
	
	public String getCycleStr()
	{
		return cycle_pm.get();
	}

}
