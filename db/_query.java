package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

import action.Hmmr_CS_Model;
import action.Hmmr_PartS_Model;
import action.Hmmr_SP_Model;
import action.Hmmr_Stuff_Model;
import action.hmmr_ap_model;
import action.hmmr_inst_model;
import action.hmmr_pm_model;
import action.hmmr_pmplan_model;
import action.hmmr_ps_model;
import action.hmmr_wp_model;
import action.hmmr_wr_model;
import application.conn_connector;
import dir.Cycle;
import dir.Hmmr_OrderType_Model;
import dir.Hmmr_PartCharDir_Model;
import dir.Hmmr_PartChar_Model;
import dir.Hmmr_Part_Model;
import dir.hmmr_groupcycle_model;
import dir.hmmr_prior_model;
import dir.type_pm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import share_class.s_class;

public class _query
{
	private static Statement stmt, stmt1, stmt2, stmt3, stmt4, stmt5, stmt6, stmt7, stmt8, stmt9, stmt10, stmt11, stmt12, stmt14,stmt15,stmt16,stmt17,stmt18,stmt19;
    private static ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rs9, rs10, rs11, rs12, rs14, rs15, rs16, rs17, rs18,rs19;
    _connect cn = new _connect();
    
    public String id_user = null, passwd_user = null, role_user, total_rez = null, type_c, pereodic_c, weeks_c, hours_c, bdate_c, edate_c, type_tpm, name_tpm, desc_tpm, icon_tpm, total_rez_tpm, total_rez_inst,
    			  weeks_inst, hours_inst, pereodic_inst, total_rez_upd_inst;
    private String ninst_inst, ver_inst, mtt_inst, pmn_inst, tpm_inst, pmc1_inst, pmc2_inst, ool_inst, oop_inst, pos_inst, sinfo_inst, s_doc_inst, qspec_inst,
    			   pwt_inst, wt_inst, adm2_inst, adm3_inst, ofit1_inst, ofit2_inst, date_create, date_change, inst_pdf;
    private String total_rez_upd_pm, ninst_pm, eq_id, ool_pm, pm_resp, group_pm, pm_exec, shop_pm, groupeq_pm, lm_pm, os_pm, equip_pm;
    private String total_rez_data, pereodic_pmplan, dbegin, dend, shop_pmplan, otv_plan;
    private String pmnum_ap, type_ap, description_ap, due_date_ap, equip_ap, oft_ap, otv_ap, total_rez_upd_ap, icon, icon_at;
	private String shift_report, req_action, actual_time, actual_time1,actual_time2,actual_time3,actual_time4, data, equip, record_type, work_time, resp,resp2,resp3,resp4, status_wr, qty, ap_num, total_rez_upd_wr, actual_date,actual_date_2,actual_date_3,actual_date_4, actual_date1,actual_date2,actual_date3,actual_date4, user, hours1,hours1_2,hours1_3,hours1_4, min1, hours2,hours2_2,hours2_3,hours2_4, min2, user_number, activity_type; 
	private String ap_num_plan, total_apnum_rez;
	private String qty_chk = "null", total_qtychk_rez, wt_chk = "null", total_wtchk_rez;
	private String a_date, a_date1, a_hours1, a_hours2;
	private String _num_ps, _company_ps, _plant_ps, _shops_ps, _groups_ps, _lines_ps, _oss_ps, _equips_ps, _shop_ps, _line_ps, _linerus_ps,
					_os_ps, _osrus_ps, _equip_ps, _description_ps, _manual_ps, _stsupplier_ps, _location_ps,
					_coord_ps, _alt_ps, _cham_ps, _trcu_ps, _trcul_ps, _hazardous_ps, _keyequip_ps, _type_ps, _sn_ps, _manuf_ps, _mtc_ps, _respons_ps,
					_melec_ps, _mair_ps, _mwater_ps, _mcwater_ps, _mhwater_ps, _rowater_ps, _mgas_ps, total_rez_ps, _group_eng, _shop_rus,
					_group_rus, _equip_rus, _group_otv, _inv_num, _os_num, _start_date, _cost_centre;
	private String lst_id;
	private String data_pmplan, oft_pmplan, total_rez_pmplan,pm_group, total_rez_group;
	private String id_prior, name_prior, desc_prior, icon_prior, total_rez_prior;
	private String name_ot, desc_ot, total_rez_ot;
	private String del_rec = "0";
	public static boolean _flag_error = true;
	            
	public _query() {
		
	}
	/**
	 * �������� �����, ������ � ����� ������������ �� ������� users ��� �����������. ����� 
	 * ������������ ��� ���������� �� ���������� ����� ������� ����������
	 * ���������
	 * @param login - ����� ������������ ��������� �� ��� �����������
	 * @return - id - id ������������, passwd -������ ������������, role - 
	 * 			 ����� ������������ �� ������������ �������� � ���������.
	 */
	@SuppressWarnings("static-access")
	public String _check_login_passwd(String login)
	{
		try {
			String query = "select id, passwd, role from users where login = "+"'"+login+"'"+";";
			
			cn.ConToDb();
			stmt = _connect.con.createStatement();
			rs = stmt.executeQuery(query);
			//log.log(Level.INFO, "CHANNEL WAS FOUND");
	        while (rs.next()) {
	            id_user = rs.getString(1);
	            passwd_user = rs.getString(2);
	            role_user = rs.getString(3);
	        }
	        total_rez = id_user+","+passwd_user+","+role_user;

		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 61!");
        } finally {

        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
		return total_rez;
	}
	/**
	 * ��������� ������� ������� ��� �������� ���� ����������� -
	 * pm_cycle
	 * @return - ���������� ����� ������ ObservableList, �������
	 * 			 ������������ ��� ���������� ������� TableView. 
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<Cycle> _select_data_cycle()
	{
		ObservableList<Cycle> list = FXCollections.observableArrayList();
		
		try {
			String query = "select id,Type,Pereodic,Hours,begin_date,end_date from hmmr_pm_cycle where del_rec = 0;";
			
			cn.ConToDb();
			stmt1 = cn.con.createStatement();
			rs1 = stmt1.executeQuery(query);
						
	        while (rs1.next()) {
	        	Cycle cycl = new Cycle();
	        	if(rs1.getString(1) != null && rs1.getString(2) != null && rs1.getString(3) != null && rs1.getString(4) != null) {
	        		cycl.Id.set(rs1.getString(1));
	        		cycl.Type.set(rs1.getString(2));
	        		cycl.Pereodic.set(rs1.getString(3));
	        		cycl.Hours.set(rs1.getString(4));
	        		cycl.BeginDate.set(rs1.getString(5));
	        		cycl.EndDate.set(rs1.getString(6));
		            
		            list.add(cycl);
	        	}    
	        }

		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 97!");
        } finally {
            //close connection ,stmt and resultset here
        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt1.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs1.close(); } catch(SQLException se) { /*can't do anything */ }
        }

		return list;
	}
	/**
	 * ������ ��� ������� ������ ��������� �� ������� hmmr_pm_cycle, ���
	 * ����������� ������� � ��������� ������������� ��� �������������� 
	 * ���
	 * @return - ���������� ����� ������ ��������������� ���������.
	 */
	
	@SuppressWarnings({ "static-access"})
	public ObservableList<Integer> _select_data_moto()
	{
		ObservableList<Integer> list = FXCollections.observableArrayList();
		
		try {
			String query = "select Hours from hmmr_pm_cycle where del_rec = 0 AND Hours <> 0;";
			
			cn.ConToDb();
			stmt1 = cn.con.createStatement();
			rs1 = stmt1.executeQuery(query);
						
	        while (rs1.next()) {
	        	//Cycle cycl = new Cycle();
	        	if(rs1.getString(1) != null) {
	        		list.add(rs1.getInt(1));
	        	}    
	        }
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 142!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt1.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs1.close(); } catch(SQLException se) { /*can't do anything */ }
	       }

		return list;
	}
	/**
	 * ��������� ������� ������� ��� �������� ���� �����������
	 * Type_PM
	 * @return - ���������� ����� ������ ���� ObservableList
	 * 			� ��������� ��� ������� TableView. 
	 */
	
	@SuppressWarnings({ "static-access"})
	public ObservableList<type_pm> _select_data_typepm()
	{
		ObservableList<type_pm> list = FXCollections.observableArrayList();
		
		try {
			String query = "select hat.id, hot.Name, hat.Name, hat.Description, hat.Icon from hmmr_activity_type hat INNER JOIN hmmr_order_type hot ON hot.id = hat.ID_OT where hat.del_rec = 0;";
			
			cn.ConToDb();
			stmt3 = cn.con.createStatement();
			rs3 = stmt3.executeQuery(query);
						
	        while (rs3.next()) {
	        	type_pm tpm = new type_pm();
	        	if(rs3.getString(1) != null && rs3.getString(2) != null && rs3.getString(3) != null) {
	        		tpm.Id.set(rs3.getString(1));
	        		tpm.Type.set(rs3.getString(2));
	        		tpm.Name.set(rs3.getString(3));
	        		tpm.Desc.set(rs3.getString(4));
	        		tpm.Icon.set(rs3.getString(5));
	        				            
		            list.add(tpm);
	        	}    
	        }

		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 189!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt3.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs3.close(); } catch(SQLException se) { /*can't do anything */ }
	        }

		return list;
	}
	
	/**
	 * ��������� ������� ������� ��� �������� ���� ��������������
	 * PM Instruction
	 * @return - ���������� ����� ������ ���� ObservableList � 
	 * 			��������� ��� ������� TableView. 
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<hmmr_inst_model> _select_data_pminst()
	{
		ObservableList<hmmr_inst_model> list = FXCollections.observableArrayList();
		
		try {
			String query = "select id,num_instruction,Creation_date,Last_edition_date,Link_instruction_PDF,Version,Model_Type_Task,PM_name,Type_PM,PM_Cycle1,PM_Cycle2,ON_Line_OFF_Line,"
					+ "Power_ON_Power_OFF,Position,Src_Info,Src_Doc,Qty_Specialist,Prep_Work_Time,Work_Time,Admission_2,"
					+ "Admission_3,Outfit_1,Outfit_2 from pm_inst where del_rec = 0;";
			
			cn.ConToDb();
			stmt5 = cn.con.createStatement();
			rs5 = stmt5.executeQuery(query);
						
	        while (rs5.next()) {
	        	hmmr_inst_model him = new hmmr_inst_model();
	        	if(rs5.getString(1) != null) {
	        		him.Id.set(rs5.getString(1));
	        		him.num_instruction.set(rs5.getString(2));
	        		him.date_create.set(rs5.getString(3));
	        		him.date_change.set(rs5.getString(4));
	        		him.inst_pdf.set(rs5.getString(5));
	        		him.Version.set(rs5.getString(6));
	        		him.Model_Type_task.set(rs5.getString(7));
	        		him.PM_name.set(rs5.getString(8));
	        		him.Type_PM.set(rs5.getString(9));
	        		him.PM_Cycle1.set(rs5.getString(10));
	        		him.PM_Cycle2.set(rs5.getString(11));
	        		him.ON_Line_OFF_Line.set(rs5.getString(12));
	        		him.Power_ON_Power_OFF.set(rs5.getString(13));
	        		him.Position.set(rs5.getString(14));
	        		him.Src_info.set(rs5.getString(15));
	        		him.Src_Doc.set(rs5.getString(16));
	        		him.Qty_Specialist.set(rs5.getString(17));
	        		him.Prep_Work_time.set(rs5.getString(18));
	        		him.Work_Time.set(rs5.getString(19));
	        		him.Admission_2.set(rs5.getString(20));
	        		him.Admission_3.set(rs5.getString(21));
	        		him.Outfit_1.set(rs5.getString(22));
	        		him.Outfit_2.set(rs5.getString(23));
	        						        					            
		            list.add(him);
	        	}    
	        }
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 221!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt5.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs5.close(); } catch(SQLException se) { /*can't do anything */ }
	       }
		return list;
	}
	
	/**
	 * ��������� ������� ������� ��� �������� ���� ��������������
	 * PM
	 * @return - ���������� ����� ������ ���� ObservableList � 
	 * 			��������� ��� ������� TableView.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<hmmr_pm_model> _select_data_pm()
	{
		ObservableList<hmmr_pm_model> list = FXCollections.observableArrayList();
		
		try {
			String query = "select id,Instruction_num,Eq_ID,PM_Group,PM_Resp,OnOff_Line,PM_Executor from hmmr_pm where del_rec = 0;";
			
			cn.ConToDb();
			stmt11 = cn.con.createStatement();
			rs11 = stmt11.executeQuery(query);
						
	        while (rs11.next()) {
	        	hmmr_pm_model hpm = new hmmr_pm_model();
	        	if(rs11.getString(1) != null && rs11.getString(2) != null && rs11.getString(3) != null) {
	        		hpm.Id.set(rs11.getString(1));
	        		hpm.num_instruction.set(rs11.getString(2));
	        		hpm.eq_id.set(rs11.getString(3));
	        		hpm.Group_PM.set(rs11.getString(4));
	        		hpm.Otv.set(rs11.getString(5));
	        		hpm.OnOff_Line.set(rs11.getString(6));
	        		hpm.Otv_Isp.set(rs11.getString(7));
	        		
	        				            
		            list.add(hpm);
	        	}    
	        }
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 291!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt11.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs11.close(); } catch(SQLException se) { /*can't do anything */ }
	       }
		return list;
	}
///////////////////////////////////////////////////////////////////ACTION PLAN/////////////////////////////////////////////////////////	
	/**
	 * ��������� ������� ������� Action Plan ��� �������� ����
	 * APWR - ��������. ������ ������ ���������� ������ � ��� 
	 * ������ ���� ���� �� ���� �� ���� ��������� (��������,
	 * ������������� �� ������ ��� �����������) �������� � ���
	 * ��� ����������������� � �������. �. �. ������� ����������
	 * ������ �������� ������������ ��������� � ����. 
	 * @param oft - ����������� ��� ������������ �������������������
	 * 		� ���������.
	 * @return - ���������� ����� ������ ���� ObservableList � 
	 * 			��������� ��� ������� TableView.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<hmmr_ap_model> _select_data_ap(String oft)
	{
		ObservableList<hmmr_ap_model> list = FXCollections.observableArrayList();
		
		try {
			String query = "select id,PM_Num,Type,Description,Due_Date,Equipment,Instruction,Otv_For_Task,Otv,Tsk_maker,flag_otv,flag_oft,flag_tm,Icon,Icon_AT from hmmr_action_plan where (Otv_For_Task = "+"'"+oft+"'"+" OR Otv = "+"'"+oft+"'"+" OR Tsk_maker = "+"'"+oft+"'"+") AND del_rec = 0 ORDER BY FIELD(Icon, '1S', '2Q', '3P', '4M', '1') ASC;";
						
			cn.ConToDb();
			stmt12 = cn.con.createStatement();
			rs12 = stmt12.executeQuery(query);
						
	        while (rs12.next()) {
	        	hmmr_ap_model hpm = new hmmr_ap_model();
	        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
	        		hpm.Id.set("AP"+rs12.getString(1));
	        		hpm.PM_Num.set(rs12.getString(2));
	        		hpm.Type.set(rs12.getString(3));
	        		hpm.Desc.set(rs12.getString(4));
	        		hpm.Due_Date.set(rs12.getString(5));
	        		hpm.Equip.set(rs12.getString(6));
	        		hpm.inst_btn.set(rs12.getString(7));
	        		hpm.OFT.set(rs12.getString(8));
	        		hpm.OTV.set(rs12.getString(9));
	        		hpm.tsk_maker.set(rs12.getString(10));
	        		hpm.flag_otv.set(rs12.getString(11));
	        		hpm.flag_oft.set(rs12.getString(12));
	        		hpm.flag_tm.set(rs12.getString(13));
	        		hpm.icon.set(rs12.getString(14));
	        		hpm.icon_at.set(rs12.getString(15));
	        						        				            
		            list.add(hpm);
	        	}    
	        }
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 339!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
	       }
		return list;
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//*********************************** PLANT STRUCTURE **********************************************************************
	/**
	 * ��������� ������� ������� ��� �������� ���� ��������������
	 * Plant Structure
	 * @return - ���������� ����� ������ ���� ObservableList � 
	 * 			��������� ��� ������� TableView.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<hmmr_ps_model> _select_data_ps()
	{
		ObservableList<hmmr_ps_model> list = FXCollections.observableArrayList();
		
		try {
			String query = "select id, FL01_Company, FL02_Plant, FL03_Shop_s, FL04_Group_s, FL05_Line_s, FL06_Station_s, FL07_Equipment_s, FL03_Shop_ENG, FL04_Group_ENG, FL05_Line_ENG, FL06_Station_ENG, FL07_Equipment_ENG, FL03_Shop_RUS, FL04_Group_RUS, FL05_Line_RUS, FL06_Station_RUS, FL07_Equipment_RUS, Description_RUS, Equip_label, Station_Label, Equipment_Folder_Link, Resp_Planner_Group, Assets_Inventory_Number, Assets_OS1_Number, Assets_Start_up_Date, Cost_Center, Site_Location, Site_CHAMBER, Site_Coordinates, Site_Altitude, TR_CU, TR_CU_Link, Hazardous, Key_equipment, EQ_Integrator, EQ_Manufacture, EQ_Type, EQ_Serial_Number, EQ_Technical_Characteristic, Responsobility, M_Electric, M_Air, M_Water, M_Cold_Water, M_Hot_Water, M_RO_Water, M_Gas from hmmr_plant_structure where Status = 0;";
			
			cn.ConToDb();
			stmt12 = cn.con.createStatement();
			rs12 = stmt12.executeQuery(query);
						
	        while (rs12.next()) {
	        	hmmr_ps_model hpm = new hmmr_ps_model();
	        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
	        		hpm.Id.set(rs12.getString(1));
	        		hpm.Company.set(rs12.getString(2));
	        		hpm.Plant.set(rs12.getString(3));
	        		hpm.Shop_s.set(rs12.getString(4));
	        		hpm.Group_PM.set(rs12.getString(5));
	        		hpm.Line_Machine_s.set(rs12.getString(6));
	        		hpm.Operation_Station_s.set(rs12.getString(7));
	        		hpm.Equipment_s.set(rs12.getString(8));
	        		hpm.Shop.set(rs12.getString(9));
	        		hpm.FL04_Group_ENG.set(rs12.getString(10));
	        		hpm.Line_Machine.set(rs12.getString(11));
	        		hpm.Operation_Station.set(rs12.getString(12));
	        		hpm.Equipment.set(rs12.getString(13));
	        		hpm.FL03_Shop_RUS.set(rs12.getString(14));
	        		hpm.FL04_Group_RUS.set(rs12.getString(15));
	        		hpm.Line_Machine_RUS.set(rs12.getString(16));
	        		hpm.Operation_Station_RUS.set(rs12.getString(17));
	        		hpm.FL07_Equipment_RUS.set(rs12.getString(18));
	        		hpm.Description.set(rs12.getString(19));
	        		hpm.Equip_label.set(rs12.getString(20));
	        		hpm.Station_label.set(rs12.getString(21));
	        		hpm.manual.set(rs12.getString(22));
	        		hpm.RespPlannerGroup.set(rs12.getString(23));
	        		hpm.AssetsInvNum.set(rs12.getString(24));
	        		hpm.AssetsOsNum.set(rs12.getString(25));
	        		hpm.AssetsStartDate.set(rs12.getString(26));
	        		hpm.CostCenter.set(rs12.getString(27));
	        		hpm.Location.set(rs12.getString(28));
	        		hpm.CHAMBER.set(rs12.getString(29));
	        		hpm.Coordinates.set(rs12.getString(30));
	        		hpm.Altitude.set(rs12.getString(31));
	        		hpm.TR_CU.set(rs12.getString(32));
	        		hpm.TR_CU_Link.set(rs12.getString(33));
	        		hpm.Hazardous.set(rs12.getString(34));
	        		hpm.Key_equipment.set(rs12.getString(35));
	        		hpm.Station_Supplier.set(rs12.getString(36));
	        		hpm.Manuf.set(rs12.getString(37));
	        		hpm.Type.set(rs12.getString(38));
	        		hpm.S_N.set(rs12.getString(39));
	        		hpm.MTC.set(rs12.getString(40));
	        		hpm.Resp.set(rs12.getString(41));
	        		hpm.M_Electric.set(rs12.getString(42));
	        		hpm.M_Air.set(rs12.getString(43));
	        		hpm.M_Water.set(rs12.getString(44));
	        		hpm.M_Cold_water.set(rs12.getString(45));
	        		hpm.M_Hot_water.set(rs12.getString(46));
	        		hpm.RO_Water.set(rs12.getString(47));
	        		hpm.M_Gas.set(rs12.getString(48));
	        						        				            
		            list.add(hpm);
	        	}    
	        }
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 389!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
	       }
		return list;
	}
//******************************************************************************************************************************	
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	/**
	 * ��������� ������� ������� Work Recording ��� �������� ���� 
	 * �������������� APWR - ��������
	 * @return - ���������� ����� ������ ���� ObservableList � 
	 * 			��������� ��� ������� TableView.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<hmmr_wr_model> _select_data_wr()
	{
		ObservableList<hmmr_wr_model> list = FXCollections.observableArrayList();
	
		try {
			String query = "select id,Task_Description,Task_Report,CM_DownTime,WR_Work_Time,WR_Begin_Date,Equipment_Full,Record_Type,Task_Resp_ID,WR_Executor_Confirmed,WR_Host_Confirmed,ap_num from hmmr_work_recording;";
			
			cn.ConToDb();
			stmt16 = cn.con.createStatement();
			rs16 = stmt16.executeQuery(query);
						
	        while (rs16.next()) {
	        	hmmr_wr_model hpm = new hmmr_wr_model();
	        	if(rs16.getString(1) != null && rs16.getString(2) != null && rs16.getString(3) != null) {
	        		hpm.Id.set("WR"+rs16.getString(1));
	        		hpm.shift_report.set(rs16.getString(2));
	        		hpm.req_action.set(rs16.getString(3));
	        		hpm.actual_time.set(rs16.getString(4));
	        		hpm.actual_time1.set(rs16.getString(5));
	        		hpm.data.set(rs16.getString(6));
	        		hpm.equip.set(rs16.getString(7));
	        		hpm.record_type.set(rs16.getString(8));
	        		hpm.resp.set(rs16.getString(9));
	        		hpm.status.set(rs16.getString(10));
	        		hpm.qtyProperty().set(rs16.getBoolean(11));
	        		hpm.ap_num.set(rs16.getString(12));
	        						        				            
		            list.add(hpm);
	        	}    
	        }
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 467!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt16.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs16.close(); } catch(SQLException se) { /*can't do anything */ }
	       }
		return list;
	}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
	/**
	 * ��������� ������� ������� Work Recording ��� �������� ���� 
	 * �������������� APWR - ��������, ��������������� �� ����
	 * @param begin_data - ��������� ����
	 * @param last_data - �������� ����
	 * @return - ���������� ����� ������ ���� ObservableList 
	 * 			��������������� �� ���� � ��������� ��� 
	 * 			������� TableView.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<hmmr_wr_model> _select_data_wr(String begin_data, String last_data)
	{
		ObservableList<hmmr_wr_model> list = FXCollections.observableArrayList();
	
		try {
			String query = "select id,Task_Description,Task_Report,CM_DownTime,WR_Work_Time,WR_End_Date,Equipment_Full,Record_Type,Task_Resp_ID,WR_Executor_Confirmed,WR_Host_Confirmed,ap_num,user_number,WR_Resp_Confirmed,Activity_Type from hmmr_work_recording where WR_End_Date BETWEEN "+"'"+begin_data+"'"+" AND "+"'"+last_data+"'"+";";
			
			cn.ConToDb();
			stmt16 = cn.con.createStatement();
			rs16 = stmt16.executeQuery(query);
						
	        while (rs16.next()) {
	        	hmmr_wr_model hpm = new hmmr_wr_model();
	        	if(rs16.getString(1) != null && rs16.getString(2) != null && rs16.getString(3) != null) {
	        		hpm.Id.set("WR"+rs16.getString(1));
	        		hpm.shift_report.set(rs16.getString(2));
	        		hpm.req_action.set(rs16.getString(3));
	        		hpm.actual_time.set(rs16.getString(4));
	        		hpm.actual_time1.set(rs16.getString(5));
	        		hpm.data.set(rs16.getString(6));
	        		hpm.equip.set(rs16.getString(7));
	        		hpm.record_type.set(rs16.getString(8));
	        		hpm.resp.set(rs16.getString(9));
	        		hpm.status.set(rs16.getString(10));
	        		hpm.qtyProperty().set(rs16.getBoolean(11));
	        		hpm.ap_num.set(rs16.getString(12));
	        		hpm.user_id.set(rs16.getString(13));
	        		hpm.userProperty().set(rs16.getBoolean(14));
	        		hpm.icon_at.set(rs16.getString(15));
		            list.add(hpm);
	        	}    
	        }
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 519!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt16.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs16.close(); } catch(SQLException se) { /*can't do anything */ }
	       }
		return list;
	}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
	/**
	 * ��������� ������� ������� PM Plan ��� �������� ���� 
	 * �������������� PM Plan - ��������, ��������������� �� ����
	 * (������ ��� PM Plan ,��� ���������� �� ���� � ������ - 654)
	 * @param begin_data - ��������� ����
	 * @param last_data - �������� ����
	 * @return - ���������� ����� ������ ���� ObservableList 
	 * 			��������������� �� ���� � ��������� ��� 
	 * 			������� TableView.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<hmmr_pmplan_model> _select_data_pmplan(String begin_data, String last_data)
	{
		ObservableList<hmmr_pmplan_model> list = FXCollections.observableArrayList();
	
		try {
			String query = "select id,PM_ID,PM_Group,data,OFT from hmmr_pm_year where data BETWEEN "+"'"+begin_data+"'"+" AND "+"'"+last_data+"'"+" AND record_del = 0;";
			
			cn.ConToDb();
			stmt16 = cn.con.createStatement();
			rs16 = stmt16.executeQuery(query);
						
	        while (rs16.next()) {
	        	hmmr_pmplan_model hpm = new hmmr_pmplan_model();
	        	if(rs16.getString(1) != null) {
	        		hpm.Id.set(rs16.getString(1));
	        		hpm.num_pm.set(rs16.getString(2));
	        		hpm.pm_group.set(rs16.getString(3));
	        		hpm.dd.set(rs16.getString(4));
	        		hpm.resp.set(rs16.getString(5));
	        			        		       						        				            
		            list.add(hpm);
	        	}    
	        }
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 590!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt16.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs16.close(); } catch(SQLException se) { /*can't do anything */ }
	       }
		return list;
	}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
	/**
	 * ��������� ������� Work Recording �� ������ ������ �� Action Plan - 
	 * apnum, � ���������� ���� � Work Recording ��������� ������ ��
	 * ������, �������� ������������� ������ � ���� ������� � 
	 * Action Plan
	 * @param apnum - ����� ������ � Action Plan
	 * @return - ���������� ����� ������ ���� ObservableList 
	 * 			��������������� �� ���� � ��������� ��� 
	 * 			������� TableView.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<hmmr_wr_model> _select_sort_apnum_wr(String apnum)
	{
		ObservableList<hmmr_wr_model> list = FXCollections.observableArrayList();
		
		try {
			String query = "select id,Task_Description,Task_Report,CM_DownTime,WR_Work_Time,WR_End_Date,Equipment_Full,Record_Type,Task_Resp_ID,WR_Executor_Confirmed,WR_Host_Confirmed,ap_num,user_number,WR_Resp_Confirmed,Activity_Type from hmmr_work_recording WHERE ap_num = "+"'"+apnum+"'"+";";
			
			cn.ConToDb();
			stmt16 = cn.con.createStatement();
			rs16 = stmt16.executeQuery(query);
						
	        while (rs16.next()) {
	        	hmmr_wr_model hpm = new hmmr_wr_model();
	        	if(rs16.getString(1) != null && rs16.getString(2) != null && rs16.getString(3) != null) {
	        		hpm.Id.set("WR"+rs16.getString(1));
	        		hpm.shift_report.set(rs16.getString(2));
	        		hpm.req_action.set(rs16.getString(3));
	        		hpm.actual_time.set(rs16.getString(4));
	        		hpm.actual_time1.set(rs16.getString(5));
	        		hpm.data.set(rs16.getString(6));
	        		hpm.equip.set(rs16.getString(7));
	        		hpm.record_type.set(rs16.getString(8));
	        		hpm.resp.set(rs16.getString(9));
	        		hpm.status.set(rs16.getString(10));
	        		hpm.qtyProperty().set(rs16.getBoolean(11));
	        		hpm.ap_num.set(rs16.getString(12));
	        		hpm.user_id.set(rs16.getString(13));
	        		hpm.userProperty().set(rs16.getBoolean(14));
	        		hpm.icon_at.set(rs16.getString(15));
	        						        				            
		            list.add(hpm);
	        	}    
	        }
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 618!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt16.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs16.close(); } catch(SQLException se) { /*can't do anything */ }
	       }
		return list;
	}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
	/**
	 * ��������� ������� Work Recording �� �������� ���� - 
	 * WSH, � ���������� ���� � Work Recording ��������� ������ ��
	 * ������, �������� ������������� ������� �������� ������ 
	 * ����� ����
	 * @param shop - �������� ����
	 * @return - ���������� ����� ������ ���� ObservableList 
	 * 			��������������� �� ���� � ��������� ��� 
	 * 			������� TableView.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<hmmr_wr_model> _select_sort_shop_wr(String begin_data, String last_data, String shop)
	{
		ObservableList<hmmr_wr_model> list = FXCollections.observableArrayList();
		
		try {
			String query = "select id,Task_Description,Task_Report,CM_DownTime,WR_Work_Time,WR_End_Date,Equipment_Full,Record_Type,Task_Resp_ID,WR_Executor_Confirmed,WR_Host_Confirmed,ap_num,user_number,WR_Resp_Confirmed,Activity_Type from hmmr_work_recording WHERE WR_End_Date BETWEEN "+"'"+begin_data+"'"+" AND "+"'"+last_data+"'"+" AND FL_WSH = "+"'"+shop+"'"+";";
			
			cn.ConToDb();
			stmt16 = cn.con.createStatement();
			rs16 = stmt16.executeQuery(query);
						
	        while (rs16.next()) {
	        	hmmr_wr_model hpm = new hmmr_wr_model();
	        	if(rs16.getString(1) != null && rs16.getString(2) != null && rs16.getString(3) != null) {
	        		hpm.Id.set("WR"+rs16.getString(1));
	        		hpm.shift_report.set(rs16.getString(2));
	        		hpm.req_action.set(rs16.getString(3));
	        		hpm.actual_time.set(rs16.getString(4));
	        		hpm.actual_time1.set(rs16.getString(5));
	        		hpm.data.set(rs16.getString(6));
	        		hpm.equip.set(rs16.getString(7));
	        		hpm.record_type.set(rs16.getString(8));
	        		hpm.resp.set(rs16.getString(9));
	        		hpm.status.set(rs16.getString(10));
	        		hpm.qtyProperty().set(rs16.getBoolean(11));
	        		hpm.ap_num.set(rs16.getString(12));
	        		hpm.user_id.set(rs16.getString(13));
	        		hpm.userProperty().set(rs16.getBoolean(14));
	        		hpm.icon_at.set(rs16.getString(15));
	        						        				            
		            list.add(hpm);
	        	}    
	        }
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 673!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt16.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs16.close(); } catch(SQLException se) { /*can't do anything */ }
	       }
		return list;
	}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
	/**
	 * ��������� ������� Work Recording �� ID ������� - 
	 * Resp1, � ���������� ���� � Work Recording ��������� ������ ��
	 * ������, �������� ������������� ������� ������ ����� �������
	 * @param resp - ID �������
	 * @return - ���������� ����� ������ ���� ObservableList 
	 * 			��������������� �� ���� � ��������� ��� 
	 * 			������� TableView.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<hmmr_wr_model> _select_sort_resp_wr(String begin_data, String last_data, String resp)
	{
		ObservableList<hmmr_wr_model> list = FXCollections.observableArrayList();
		
		try {
			String query = "select id,Task_Description,Task_Report,CM_DownTime,WR_Work_Time,WR_End_Date,Equipment_Full,Record_Type,Task_Resp_ID,WR_Executor_Confirmed,WR_Host_Confirmed,ap_num,user_number,WR_Resp_Confirmed,Activity_Type from hmmr_work_recording WHERE WR_End_Date BETWEEN "+"'"+begin_data+"'"+" AND "+"'"+last_data+"'"+" AND Task_Resp_ID = "+"'"+resp+"'"+";";
			
			cn.ConToDb();
			stmt16 = cn.con.createStatement();
			rs16 = stmt16.executeQuery(query);
						
	        while (rs16.next()) {
	        	hmmr_wr_model hpm = new hmmr_wr_model();
	        	if(rs16.getString(1) != null && rs16.getString(2) != null && rs16.getString(3) != null) {
	        		hpm.Id.set("WR"+rs16.getString(1));
	        		hpm.shift_report.set(rs16.getString(2));
	        		hpm.req_action.set(rs16.getString(3));
	        		hpm.actual_time.set(rs16.getString(4));
	        		hpm.actual_time1.set(rs16.getString(5));
	        		hpm.data.set(rs16.getString(6));
	        		hpm.equip.set(rs16.getString(7));
	        		hpm.record_type.set(rs16.getString(8));
	        		hpm.resp.set(rs16.getString(9));
	        		hpm.status.set(rs16.getString(10));
	        		hpm.qtyProperty().set(rs16.getBoolean(11));
	        		hpm.ap_num.set(rs16.getString(12));
	        		hpm.user_id.set(rs16.getString(13));
	        		hpm.userProperty().set(rs16.getBoolean(14));
	        		hpm.icon_at.set(rs16.getString(15));
	        						        				            
		            list.add(hpm);
	        	}    
	        }
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 728!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt16.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs16.close(); } catch(SQLException se) { /*can't do anything */ }
	       }
		return list;
	}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
	/**
	 * ���������� ������� ������� PM Plan ��� �������� ���� �������������� PM Plan - 
	 * ��������, ��� ���������� �� ���� (������ ��� PM Plan � ����������� �� ���� 
	 * � ������ - 555)
	 * @return - ���������� ����� ������ ���� ObservableList 
	 * 			��������������� �� ���� � ��������� ��� 
	 * 			������� TableView.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<hmmr_pmplan_model> _select_data_pmplan()
	{
		ObservableList<hmmr_pmplan_model> list = FXCollections.observableArrayList();
		
		try {
			String query = "select id,PM_ID,PM_Group,data,OFT from hmmr_pm_year where record_del = 0;";
			
			cn.ConToDb();
			stmt18 = cn.con.createStatement();
			rs18 = stmt18.executeQuery(query);
									
	        while (rs18.next()) {
	        	hmmr_pmplan_model hpm = new hmmr_pmplan_model();
	        	if(rs18.getString(1) != null) {
	        		hpm.Id.set(rs18.getString(1));
	        		hpm.num_pm.set(rs18.getString(2));
	        		hpm.pm_group.set(rs18.getString(3));
	        		hpm.dd.set(rs18.getString(4));
	        		hpm.resp.set(rs18.getString(5));
	        			        			        			        						        						        				            
		            list.add(hpm);
	        	}    
	        }
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 798!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt18.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs18.close(); } catch(SQLException se) { /*can't do anything */ }
	       }
		return list;
	}
	/**
	 * ��������� ComboBox ������� �� ���� PM �� ������� hmmr_type_pm.
	 * ������ ComboBox ������������ ��� �������� ������ ��� �������
	 * PM Instruction			
	 * @return - ���������� ����� ������ ���� ObservableList 
	 * 			��������������� �� ���� � ��������� ��� 
	 * 			ComboBox.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<String> _select_typepm_inst()
	{
		ObservableList<String> list = FXCollections.observableArrayList();
		
		try {
			String query = "select Name, Description from hmmr_activity_type WHERE ID_OT = 1 AND del_rec = 0;";
			
			cn.ConToDb();
			stmt6 = cn.con.createStatement();
			rs6 = stmt6.executeQuery(query);
						
	        while (rs6.next()) {
	        	if(rs6.getString(1) != null) {
	        		String tpm = rs6.getString(1) + " - " + rs6.getString(2);			        					            
		            list.add(tpm);
	        	}    
	        }

		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 849!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
	       }

		return list;
	}
	
	/**
	 * ��������� ComboBox ������� �� ����� PM �� ������� hmmr_pm_cycle.
	 * ������ ComboBox ������������ ��� �������� ������ ��� �������
	 * PM Instruction
	 * @return - ���������� ����� ������ ���� ObservableList 
	 * 			��������������� �� ���� � ��������� ��� 
	 * 			ComboBox.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<String> _select_cycle_inst()
	{
		ObservableList<String> list = FXCollections.observableArrayList();
		
		try {
			String query = "select Type from hmmr_pm_cycle where del_rec = 0;";
			
			cn.ConToDb();
			stmt7 = cn.con.createStatement();
			rs7 = stmt7.executeQuery(query);
						
	        while (rs7.next()) {
	        	if(rs7.getString(1) != null) {
	        		String cycl = rs7.getString(1);        
		            list.add(cycl);
		        	}    
	        }
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 883!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt7.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs7.close(); } catch(SQLException se) { /*can't do anything */ }
	       }

		return list;
	}
	
	/**
	 * ��������� ComboBox ������� �� ��������� �� ������� hmmr_mu_staff.
	 * ������ ComboBox ������������ ��� �������� ������ ��� �������
	 * PM Instruction
	 * @return - ���������� ����� ������ ���� ObservableList 
	 * 			��������������� �� ���� � ��������� ��� 
	 * 			ComboBox.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<String> _select_pos_inst()
	{
		ObservableList<String> list = FXCollections.observableArrayList();
		
		try {
			String query = "select Position from hmmr_mu_staff group by position;";
			
			cn.ConToDb();
			stmt8 = cn.con.createStatement();
			rs8 = stmt8.executeQuery(query);
						
	        while (rs8.next()) {
	        	if(rs8.getString(1) != null) {
	        		String cycl = rs8.getString(1);        
		            list.add(cycl);
		       	}    
		       }
	        }
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 902!");
		      } finally {
		          //close connection ,stmt and resultset here
		      	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt8.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs8.close(); } catch(SQLException se) { /*can't do anything */ }
		      }

		return list;
	}
	
	/**
	 * ������ ��� ���������� ComboBox ������� ��������� ����� - ��������
	 * ABA - ������� ������� ��� ������ � ������� Plant Structure
	 * @param str_s - �������� ����, ������� ����� �������� � ��������������
	 * 				 ������� �� ������� �� hmmr_plant_structure
	 * @param str - �������� ����, ������� ����� �������� � �������������� 
	 * 				������� �� ������� �� hmmr_plant_structure
	 * @param str_where - �������� ���� ������������� � ������� where 
	 * 					�� ������� �� hmmr_plant_structure
	 * @param str_znach - �������� ��� ����, ������� ���������� � where
	 * @return - ���������� ����� ������ ���� ObservableList 
	 * 			��������������� �� ���� � ��������� ��� 
	 * 			ComboBox.
	 */
	//********************************************************* PLANT STRUCTURE **********************************************************
	@SuppressWarnings({ "static-access"})
	public ObservableList<String> _select_shop_ps(String str_s, String str, String str_where, String str_znach)
	{
		ObservableList<String> list = FXCollections.observableArrayList();
		
		try {
			String query = "select "+str_s+", "+str+" from hmmr_plant_structure where "+str_where+" = "+"'"+str_znach+"'"+" group by "+str_s+", "+str+";";
			
			cn.ConToDb();
			stmt6 = cn.con.createStatement();
			rs6 = stmt6.executeQuery(query);
						
	        while (rs6.next()) {
	        	if(rs6.getString(1) != null) {
	        		String tpm = rs6.getString(1) + " - " + rs6.getString(2);			        					            
		            list.add(tpm);
	        	}    
	        }
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 946!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
	       }

		return list;
	}
//******************************************************************************************************************************
	
	//*********************************************************** PLANT STRUCTURE **********************************************
	/**
	 * ������ ��� ���������� ComboBox ���������, ������������� 
	 * ������ ����������� �������, ������ � ����� ���������� ���
	 * ������ � ������� Plant Structure
	 * @param str - �������� ���� ������� ����� �������� � 
	 * 				�������������� ������� �� ������� �� 
	 * 				hmmr_plant_structure
	 * @param str_where - �������� ���� ������������� � ������� where 
	 * 					�� ������� �� hmmr_plant_structure
	 * @param str_znach - �������� ��� ����, ������� ���������� � where
	 * @return - ���������� ����� ������ ���� ObservableList 
	 * 			��������������� �� ���� � ��������� ��� 
	 * 			ComboBox.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<String> _select_shop_ps(String str, String str_where, String str_znach)
	{
		ObservableList<String> list = FXCollections.observableArrayList();
		
		try {
			String query = "select "+str+" from hmmr_plant_structure where "+str_where+" = "+"'"+str_znach+"'"+" group by "+str+";";
			
			cn.ConToDb();
			stmt6 = cn.con.createStatement();
			rs6 = stmt6.executeQuery(query);
						
	        while (rs6.next()) {
	        	if(rs6.getString(1) != null) {
	        		String tpm = rs6.getString(1);			        					            
		            list.add(tpm);
	        	}    
	        }

		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 991!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	           try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
	           try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
	       }

		return list;
	}
//******************************************************************************************************************************
	
//*********************************** PLANT STRUCTURE **************************************************************************	
	/**
	 * !!!!!!!!!!!!!!!!!!!!������ �� ����������!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * ������ ��� ���������� ComboBox ���������, ������������� 
	 * ������ ����������� �������, ������ � ����� ���������� ���
	 * ������ � ������� Plant Structure
	 * @param str - �������� ���� ������� ����� �������� � 
	 * 				�������������� ������� �� ������� �� 
	 * 				hmmr_plant_structure
	 * @param str_where - �������� ���� ������������� � ������� where 
	 * 					�� ������� �� hmmr_plant_structure
	 * @param str_znach - �������� ��� ����, ������� ���������� � where
	 * @return - ���������� ����� ������ ���� ObservableList 
	 * 			��������������� �� ���� � ��������� ��� 
	 * 			ComboBox.
	 */
	@SuppressWarnings({ "static-access"})
	public String _select_shop_ps_str(String str, String str_where, String str_znach)
	{
		String list = "null";
		
		try {
			String query = "select "+str+" from hmmr_plant_structure where "+str_where+" = "+"'"+str_znach+"'"+" group by "+str+";";
			
			cn.ConToDb();
			stmt6 = cn.con.createStatement();
			rs6 = stmt6.executeQuery(query);
						
	        while (rs6.next()) {
	        	if(rs6.getString(1) != null) {
	        		list = rs6.getString(1);			        					            
		        }    
	        }

		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1037!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
	       }

		return list;
	}
//******************************************************************************************************************************	
//********************************************************* PLANT STRUCTURE **********************************************************
/*		@SuppressWarnings({ "static-access"})
		public ObservableList<String> _select_shop_ps(String str_s, String str_g, String str_l, String str_os, String str_eq, String str_where, String str_znach)
		{
			ObservableList<String> list = FXCollections.observableArrayList();
			
			try {
				String query = "select "+str_s+","+str_g+","+str_l+","+str_os+","+str_eq+" from hmmr_plant_structure where "+str_where+" = "+"'"+str_znach+"'"+" group by "+str_s+", "+str_g+", "+str_l+", "+str_os+", "+str_eq+";";
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	if(rs6.getString(1) != null) {
		        		String tpm = rs6.getString(1) + "." + rs6.getString(2) + "." + rs6.getString(3) + "." + rs6.getString(4) + "." + rs6.getString(5);			        					            
			            list.add(tpm);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1097!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) {}
		        try { stmt6.close(); } catch(SQLException se) {}
		        try { rs6.close(); } catch(SQLException se) {}
		       }

			return list;
		}*/
	/**
	 * �������� ��������, ����� ������ ����� radiobutton ����������
	 * �10 - 10000 �����, �20 - 20000 ����� � �. �.
	 * @return - ���������� ���������� ��������
	 */
	@SuppressWarnings({ "static-access"})
	public String _select_rbtn()
	{
		String list = "null";
		
		try {
			String query = "select chk_sel from hmmr_pm_cycle where id = 1;";
			
			cn.ConToDb();
			stmt6 = cn.con.createStatement();
			rs6 = stmt6.executeQuery(query);
						
	        while (rs6.next()) {
	        	if(rs6.getString(1) != null) {
	        		list = rs6.getString(1);			        					            
		        }    
	        }

		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1072!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
	       }

		return list;
	}
//********************************************** PLANT STRUCTURE ***************************************************************	
	/**
	 * ������ ��� ���������� ComboBox ���������, ������������� 
	 * ������ ����������� �������, ������ � ����� ���������� ���
	 * ������ � ������� Plant Structure
	 * @param str - ��� ����, �������� �������� ���� �������� � ComboBox
	 * @return - ���������� ���������� ��������
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<String> _select_shop_ps(String str)
	{
		ObservableList<String> list = FXCollections.observableArrayList();
		
		try {
			String query = "select "+str+" from hmmr_plant_structure group by "+str+";";
			
			cn.ConToDb();
			stmt6 = cn.con.createStatement();
			rs6 = stmt6.executeQuery(query);
						
	        while (rs6.next()) {
	        	if(rs6.getString(1) != null) {
	        		String tpm = rs6.getString(1);			        					            
		            list.add(tpm);
	        	}    
	        }

		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1109!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
	       }

		return list;
	}
//***************************************************************************************************************************
	
//*************************************** PLANT STRUCTURE *******************************************************************	
	/**!!!!!!!!������ �� �����������!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * ������ ��� ���������� ComboBox ���������, ������������� 
	 * ������ ����������� �������, ������ � ����� ���������� ���
	 * ������ � ������� Plant Structure
	 * @param str - ��� ����, �������� �������� ���� �������� � ComboBox
	 * @return - ���������� ���������� ��������
	 */			
	@SuppressWarnings({ "static-access"})
	public String _select_shop_ps_str(String str)
	{
		String list = "null";
		
		try {
			String query = "select "+str+" from hmmr_plant_structure group by "+str+";";
			
			cn.ConToDb();
			stmt6 = cn.con.createStatement();
			rs6 = stmt6.executeQuery(query);
			
	        while (rs6.next()) {
	        	//typepm_model_inst tpm = new typepm_model_inst();
	        	if(rs6.getString(1) != null) {
	        		
	        		list = rs6.getString(1);			        					            
		        }    
	        }

		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1147!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
	       }

		return list;
	}
//**************************************************************************************************************************
	
//******************************************** PLANT STRUCTURE **************************************************************	
	/**
	 * ��������� ���������� ComboBox Shop
	 * @return - ���������� ���������� ����� ������
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<String> _select_shop_pm()
	{
		ObservableList<String> list = FXCollections.observableArrayList();
		
		try {
			String query = "select FL03_Shop_s, FL03_Shop_ENG from hmmr_plant_structure where status = 0 group by FL03_Shop_s, FL03_Shop_ENG;";
			
			cn.ConToDb();
			stmt6 = cn.con.createStatement();
			rs6 = stmt6.executeQuery(query);
						
	        while (rs6.next()) {
	        	if(rs6.getString(1) != null) {
	        		String tpm = rs6.getString(1) + " - " + rs6.getString(2);			        					            
		            list.add(tpm);
	        	}    
	        }

		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1183");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
	       }

		return list;
	}	
//*****************************************************************************************************************************
	
//*************************************************** PLANT STRUCTURE *********************************************************	
	/**
	 * ��������� ���������� ComboBox Line Machine
	 * @param group - �������� �������������� � ���������� �� ���� Group_PM
	 * @return - ���������� ���������� ����� ������
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<String> _select_lm_pm(String group)
	{
		ObservableList<String> list = FXCollections.observableArrayList();
		
		try {
			String query = "select FL05_Line_s, FL05_Line_ENG from hmmr_plant_structure where FL04_Group_s ="+"'"+group+"'"+" AND status = 0 group by FL05_Line_s, FL05_Line_ENG;";
			
			cn.ConToDb();
			stmt6 = cn.con.createStatement();
			rs6 = stmt6.executeQuery(query);
						
	        while (rs6.next()) {
	        	if(rs6.getString(1) != null) {
	        		String lms = rs6.getString(1) +" - "+ rs6.getString(2);			        					            
		            list.add(lms);
	        	}    
	        }

		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1219!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
	       }

		return list;
	}
// ***************************************************************************************************************************
	
//************************************************ PLANT STRUCTURE *************************************************************************	
	/**
	 * ��������� ���������� ComboBox Group
	 * @param shop - �������� �������������� � ���������� �� ���� Shop_s
	 * @return - - ���������� ���������� ����� ������
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<String> _select_group_pm(String shop)
	{
		ObservableList<String> list = FXCollections.observableArrayList();
		
		try {
			String query = "select FL04_Group_s from hmmr_plant_structure where FL03_Shop_s ="+"'"+shop+"'"+" AND status = 0 group by FL04_Group_s;";
			
			cn.ConToDb();
			stmt6 = cn.con.createStatement();
			rs6 = stmt6.executeQuery(query);
					
	        while (rs6.next()) {
	        	//typepm_model_inst tpm = new typepm_model_inst();
	        	if(rs6.getString(1) != null) {
	        		//tpm.settypepm(rs6.getString(1));
	        		String group = rs6.getString(1);			        					            
		            list.add(group);
	        	}    
	        }
//				        System.out.println("SELECT WORKED DATA: "+list);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1255!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
//					System.out.println("ARRAYLIST: "+list);
					return list;
				}
//****************************************************************************************************************************
	
//************************************************ PLANT STRUCTURE ***********************************************************	
				//��������� ComboBox Operation Station ��� ������� PM
				@SuppressWarnings({ "static-access"})
				public ObservableList<String> _select_os_pm(String group, String lm)
				{
					ObservableList<String> list = FXCollections.observableArrayList();
					
					try {
						String query = "select FL06_Station_s, FL06_Station_ENG from hmmr_plant_structure where FL04_Group_s ="+"'"+group+"'"+" AND FL05_Line_s = "+"'"+lm+"'"+" AND status = 0 group by FL06_Station_s, FL06_Station_ENG;";
						
						cn.ConToDb();
						stmt6 = cn.con.createStatement();
						rs6 = stmt6.executeQuery(query);
									
				        while (rs6.next()) {
				        	//typepm_model_inst tpm = new typepm_model_inst();
				        	if(rs6.getString(1) != null) {
				        		//tpm.settypepm(rs6.getString(1));
				        		String os = rs6.getString(1) +" - "+ rs6.getString(2);			        					            
					            list.add(os);
				        	}    
				        }
//				        System.out.println("SELECT WORKED DATA: "+list);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1290!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
//					System.out.println("ARRAYLIST: "+list);
					return list;
				}
//*****************************************************************************************************************************				
				//��������� ComboBox Equipment ��� ������� PM
				@SuppressWarnings({ "static-access"})
				public ObservableList<String> _select_equip_pm(String os, String grp, String lm)
				{
					ObservableList<String> list = FXCollections.observableArrayList();
					
					try {
						String query = "select FL07_Equipment_s, FL07_Equipment_ENG from hmmr_plant_structure where FL06_Station_s ="+"'"+os+"'"+" AND FL04_Group_s = "+"'"+grp+"'"+" AND FL05_Line_s = "+"'"+lm+"'"+" AND status = 0 group by FL07_Equipment_s, FL07_Equipment_ENG;";
						
						cn.ConToDb();
						stmt6 = cn.con.createStatement();
						rs6 = stmt6.executeQuery(query);
									
				        while (rs6.next()) {
				        	//typepm_model_inst tpm = new typepm_model_inst();
				        	if(rs6.getString(1) != null) {
				        		//tpm.settypepm(rs6.getString(1));
				        		String equip = rs6.getString(1) +" - "+ rs6.getString(2);			        					            
					            list.add(equip);
				        	}    
				        }
//				        System.out.println("SELECT WORKED DATA: "+list);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1324!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
//					System.out.println("ARRAYLIST: "+list);
					return list;
				}
//*************************************************************************************************************************
				
//************************************************** PLANT STRUCTURE *******************************************************				
				//��������� ComboBox Group ��� ������� PM
				@SuppressWarnings({ "static-access"})
				public ObservableList<String> _select_group_pm(String shop, String lm)
				{
					ObservableList<String> list = FXCollections.observableArrayList();
					
					try {
						String query = "select FL04_Group_s from hmmr_plant_structure where FL03_Shop_s ="+"'"+shop+"'"+" AND FL05_Line_s = "+"'"+lm+"'"+" group by FL04_Group_s;";
						
						cn.ConToDb();
						stmt6 = cn.con.createStatement();
						rs6 = stmt6.executeQuery(query);
									
				        while (rs6.next()) {
				        	//typepm_model_inst tpm = new typepm_model_inst();
				        	if(rs6.getString(1) != null) {
				        		//tpm.settypepm(rs6.getString(1));
				        		String grp = rs6.getString(1);// +" - "+ rs6.getString(2);			        					            
					            list.add(grp);
				        	}    
				        }
//				        System.out.println("SELECT WORKED DATA: "+list);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1358!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
//					System.out.println("ARRAYLIST: "+list);
					return list;
				}	
//****************************************************************************************************************************				
				//��������� ComboBox Model_Type_Task ��� ������� PM
				@SuppressWarnings({ "static-access"})
				public ObservableList<String> _select_mtt_pm(String instr)
				{
					ObservableList<String> list = FXCollections.observableArrayList();
					
					try {
						String query = "select Model_Type_Task from pm_inst where num_instruction ="+"'"+instr+"'"+" group by Model_Type_Task;";
						
						cn.ConToDb();
						stmt6 = cn.con.createStatement();
						rs6 = stmt6.executeQuery(query);
									
				        while (rs6.next()) {
				        	//typepm_model_inst tpm = new typepm_model_inst();
				        	if(rs6.getString(1) != null) {
				        		//tpm.settypepm(rs6.getString(1));
				        		String grp = rs6.getString(1);// +" - "+ rs6.getString(2);			        					            
					            list.add(grp);
				        	}    
				        }
//				        System.out.println("SELECT WORKED DATA: "+list);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1393!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
//					System.out.println("ARRAYLIST: "+list);
					return list;
				}
				//��������� ComboBox num_instruction ��� ������� PM
				@SuppressWarnings({ "static-access"})
				public ObservableList<String> _select_instr_pm()
				{
					ObservableList<String> list = FXCollections.observableArrayList();
					
					try {
						String query = "select num_instruction, PM_name from pm_inst where del_rec = 0 group by num_instruction;";
						
						cn.ConToDb();
						stmt6 = cn.con.createStatement();
						rs6 = stmt6.executeQuery(query);
									
				        while (rs6.next()) {
				        	//typepm_model_inst tpm = new typepm_model_inst();
				        	if(rs6.getString(1) != null) {
				        		//tpm.settypepm(rs6.getString(1));
				        		String instr = rs6.getString(1) + " - " + rs6.getString(2);			        					            
					            list.add(instr);
				        	}    
				        }
//				        System.out.println("SELECT WORKED DATA: "+list);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1517!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
//					System.out.println("ARRAYLIST: "+list);
					return list;
				}
				//��������� ComboBox PM_Name ��� ������� PM
				@SuppressWarnings({ "static-access"})
				public ObservableList<String> _select_pmn_pm(String instr)
				{
					ObservableList<String> list = FXCollections.observableArrayList();
					
					try {
						String query = "select PM_name from pm_inst where num_instruction ="+"'"+instr+"'"+" group by PM_name;";
						
						cn.ConToDb();
						stmt6 = cn.con.createStatement();
						rs6 = stmt6.executeQuery(query);
									
				        while (rs6.next()) {
				        	//typepm_model_inst tpm = new typepm_model_inst();
				        	if(rs6.getString(1) != null) {
				        		//tpm.settypepm(rs6.getString(1));
				        		String pmn = rs6.getString(1);// +" - "+ rs6.getString(2);			        					            
					            list.add(pmn);
				        	}    
				        }
//				        System.out.println("SELECT WORKED DATA: "+list);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1461!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
//					System.out.println("ARRAYLIST: "+list);
					return list;
				}
				
				//�������� ���� ��������� � ����� �� ������� ���� ������������ ������������, �.�. �������� ���������� � Action Plan
				@SuppressWarnings({ "static-access"})
				public ObservableList<String> _select_pmplan()
				{
					ObservableList<String> list = FXCollections.observableArrayList();
					try {
						//String query = "select hpy.data, hp.Date_Beforehand from hmmr_pm_year hpy INNER JOIN hmmr_pm hp ON hpy.PM_ID = hp.id where hpy.record_del = 0;";// GROUP BY hpy.data
						String query = "select hpy.data, hgc.Date_Beforehand, hgc.PM_Duration from hmmr_pm_year hpy INNER JOIN hmmr_group_cycle hgc ON hpy.PM_Group = hgc.PM_Group where hpy.record_del = 0;";
						cn.ConToDb();
						stmt6 = cn.con.createStatement();
						rs6 = stmt6.executeQuery(query);
									
				        while (rs6.next()) {
				        	//typepm_model_inst tpm = new typepm_model_inst();
				        	if(rs6.getString(1) != null) {
				        		//tpm.settypepm(rs6.getString(1));
				        		String pmn = rs6.getString(1)+" - "+ rs6.getString(2)+" - "+ rs6.getString(3);			        					            
					            list.add(pmn);
				        	}    
				        }
//				        System.out.println("SELECT WORKED DATA: "+list);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1607!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
//					System.out.println("ARRAYLIST: "+list);
					return list;
				}
				
				//�������� ��� ����������� ���� ��� ������� � Action Plan
				@SuppressWarnings({ "static-access"})
				public ObservableList<String> _select_getfield_for_ap(String data)
				{
					ObservableList<String> list = FXCollections.observableArrayList();
					try {
						//String query = "select hpy.data, hpy.OFT, pi.Src_Doc, hp.Shop, hp.Line_Machine, hp.Operation_Station, hp.Equipment, hp.id, hp.PM_Name, hpy.record_del, hpy.id, hp.Group_PM from hmmr_pm_year hpy INNER JOIN hmmr_pm hp ON hpy.PM = hp.id INNER JOIN pm_inst pi ON hp.num_instruction = pi.num_instruction;";
						String query = "select hpy.data, hpy.OFT, pi.Link_instruction_PDF, hp.id, hp.PM_Group, pi.PM_name, hps.FL03_Shop_s, hps.FL04_Group_s, hps.FL05_Line_s, hps.FL06_Station_s, hps.FL07_Equipment_s, hpy.id, hp.PM_Executor, pi.Type_PM from hmmr_pm_year hpy INNER JOIN hmmr_pm hp ON hp.PM_Group = hpy.PM_Group INNER JOIN hmmr_plant_structure hps ON hps.id = hp.Eq_ID INNER JOIN pm_inst pi ON hp.Instruction_num = pi.num_instruction INNER JOIN hmmr_group_cycle hgc ON hgc.PM_Group = hpy.PM_Group where hpy.record_del = 0 AND hgc.PM_Duration <> 0 AND hgc.Date_Beforehand <> 0 AND hpy.data = "+"'"+data+"'"+" GROUP BY hp.id;";
						
						cn.ConToDb();
						stmt6 = cn.con.createStatement();
						rs6 = stmt6.executeQuery(query);
									
				        while (rs6.next()) {
				        	//typepm_model_inst tpm = new typepm_model_inst();
				        	if(rs6.getString(1) != null) {
				        		//tpm.settypepm(rs6.getString(1));
				        		String pmn = rs6.getString(1)+" - "+ rs6.getString(2)+" - "+ rs6.getString(3)+" - "+ rs6.getString(4)+" - "+ rs6.getString(5)+" - "+ rs6.getString(6)+" - "+ rs6.getString(7)+" - "+ rs6.getString(8)+" - "+ rs6.getString(9)+" - "+ rs6.getString(10)+" - "+ rs6.getString(11)+" - "+ rs6.getString(12)+" - "+ rs6.getString(13)+" - "+ rs6.getString(14);			        					            
					            list.add(pmn);
				        	}    
				        }
//				        System.out.println("SELECT WORKED DATA: "+list);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1625!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
//					System.out.println("ARRAYLIST: "+list);
					return list;
				}	
/////////////////////////////////////////////////////////ACTION PLAN///////////////////////////////////////////////////////////////////////////////////						
				//�������� ���� � ����������
				@SuppressWarnings({ "static-access"})
				public String _select_inst_for_ap(String id)
				{
					String pmn = null;
					try {
						String query = "select Instruction from hmmr_action_plan WHERE id = "+"'"+id.substring(2)+"'"+";";
						
						
						cn.ConToDb();
						stmt6 = cn.con.createStatement();
						rs6 = stmt6.executeQuery(query);
									
				        while (rs6.next()) {
				        	//typepm_model_inst tpm = new typepm_model_inst();
				        	if(rs6.getString(1) != null) {
				        		//tpm.settypepm(rs6.getString(1));
				        		pmn = rs6.getString(1);			        					            
					        }    
				        }
//				        System.out.println("SELECT WORKED DATA: "+list);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1658!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
//					System.out.println("ARRAYLIST: "+list);
					return pmn;
				}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
				//�������� ���
				//��� ������ ����������� ���� ������, ��� ������������� - ��������� ��� ���� �� ������ ����� ��� ��������
				//��� ������������ ��� - ��� �� ������ ����� ���� ������� ������������� ����, ����� ������ �������, ����� �������� � �.�.
				//otv = 1 - �������������, otv = 2 - �����������, shop - ���
				@SuppressWarnings({ "static-access"})
				public ObservableList<String> _select_fio_for_ap(int otv, String shop)
				{
					ObservableList<String> list = FXCollections.observableArrayList();
					String ln = null;
					String query = "null";
					try {
						if(otv == 1)
							query = "select ID, L_Name_RUS, F_Name_RUS, Otchestvo from hmmr_mu_staff where user_del = 0 ORDER BY IF(Group_S="+"'"+shop+"'"+",Team = 'E',0) DESC, FIELD(Team, 'E', 'T', 'SL', 'SS', 'TL', 'GL', 'MS', 'HOD', 'HOS', 'ECh') ASC, L_Name_RUS ASC;";
						else
							query ="select ID, L_Name_RUS, F_Name_RUS, Otchestvo from hmmr_mu_staff where user_del = 0 ORDER BY IF(Group_S="+"'"+shop+"'"+",Team = 'T',0) DESC, FIELD(Team, 'T', 'E', 'SL', 'SS', 'TL', 'GL', 'MS', 'HOD', 'HOS', 'ECh') ASC, L_Name_RUS ASC;";
						
						cn.ConToDb();
						stmt6 = cn.con.createStatement();
						rs6 = stmt6.executeQuery(query);
									
				        while (rs6.next()) {
				        	//typepm_model_inst tpm = new typepm_model_inst();
				        	if(rs6.getString(1) != null) {
				        		//tpm.settypepm(rs6.getString(1));
				        		ln = rs6.getString(1)+" - "+rs6.getString(2) + " " + rs6.getString(3) + " " + rs6.getString(4);
				        		
				        		list.add(ln);
					        }    
				        }
//				        System.out.println("SELECT WORKED DATA: "+list);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1694!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
//					System.out.println("ARRAYLIST: "+list);
					return list;
				}
				
				//�������� ������� ���������� ������� � work recording ��� ���
/*				@SuppressWarnings({ "static-access"})
				public ObservableList<String> _select_check_for_wr()
				{
					ObservableList<String> list = FXCollections.observableArrayList();
					String ln = null;
					try {
						String query = "select Qty from hmmr_work_recording;";
						
						
						cn.ConToDb();
						stmt6 = cn.con.createStatement();
						rs6 = stmt6.executeQuery(query);
									
				        while (rs6.next()) {
				        	//typepm_model_inst tpm = new typepm_model_inst();
				        	if(rs6.getString(1) != null) {
				        		//tpm.settypepm(rs6.getString(1));
				        		ln = rs6.getString(1);
				        		
				        		list.add(ln);
					        }    
				        }
				        System.out.println("SELECT WORKED DATA: "+list);
					}
					catch (SQLException sqlEx) {
						sqlEx.printStackTrace();
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) {  }
			            try { stmt6.close(); } catch(SQLException se) {  }
			            try { rs6.close(); } catch(SQLException se) {  }
			        }
					System.out.println("ARRAYLIST: "+list);
					return list;
				}*/
				
				//�������� ������ �� ������������
				@SuppressWarnings({ "static-access"})
				public String _select_user(String id)
				{
					String pmn = null;
					try {
						String query = "select STAFF_ID, ID, First_Name, Last_Name, Sec, Group_S, Position from hmmr_mu_staff WHERE user_id = "+"'"+id+"'"+";";
						
						
						cn.ConToDb();
						stmt14 = cn.con.createStatement();
						rs14 = stmt14.executeQuery(query);
									
				        while (rs14.next()) {
				        	//typepm_model_inst tpm = new typepm_model_inst();
				        	if(rs14.getString(1) != null) {
				        		//tpm.settypepm(rs6.getString(1));
				        		pmn = rs14.getString(1)+" - "+rs14.getString(2)+" - "+rs14.getString(3)+" - "+rs14.getString(4)+" - "+rs14.getString(5)+" - "+rs14.getString(6)+" - "+rs14.getString(7);			        					            
					        }    
				        }
//				        System.out.println("SELECT WORKED DATA: "+list);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1677!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt14.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs14.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
//					System.out.println("ARRAYLIST: "+list);
					return pmn;
				}
//////////////////////////////////////////////////////////////ACTION PLAN/////////////////////////////////////////////////////////////////////////////				
				//�������� id ������������ ��� ��������� � ��� id ���������� ��� �����  � ���������
				@SuppressWarnings({ "static-access"})
				public String _select_chk_userid(String apnum)
				{
					String list = "null";
					try {
						String query = "select user_id from hmmr_action_plan where (Type = TSK or Type = CM) AND id = "+"'"+apnum+"'"+";";
						
						cn.ConToDb();
						stmt6 = cn.con.createStatement();
						rs6 = stmt6.executeQuery(query);
									
				        while (rs6.next()) {
				        	//typepm_model_inst tpm = new typepm_model_inst();
				        	if(rs6.getString(1) != null) {
				        		//tpm.settypepm(rs6.getString(1));
				        		list = rs6.getString(1);			        					            
					        }    
				        }
//				        System.out.println("SELECT WORKED DATA: "+list);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1711!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
//					System.out.println("ARRAYLIST: "+list);
					return list;
				}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
////////////////////////////////////////////////////////////////////////ACTION PLAN//////////////////////////////////////////////////////////////////////				
				//���������� ��� ������ �� ����, ����� ����������� ������ ������� ��� ��������
				@SuppressWarnings({ "static-access"})
				public ObservableList<hmmr_ap_model> _select_data_all_shop(String shop)
				{
					ObservableList<hmmr_ap_model> list = FXCollections.observableArrayList();
					
					try {
						String query = "select hap.id,hap.PM_Num,hap.Type,hap.Description,hap.Due_Date,hap.Equipment,hap.Instruction,hap.Otv_For_Task,hap.Otv,hap.Tsk_maker,hap.flag_otv,hap.flag_oft,hap.flag_tm,hap.Icon,hap.Icon_AT from hmmr_action_plan hap INNER JOIN hmmr_mu_staff hms ON hap.Otv = hms.ID where del_rec = 0 AND if( "+"'"+shop+"'"+"='S' || "+"'"+shop+"'"+"='W', hms.Group_S='S,W', hms.Group_S="+"'"+shop+"'"+") ORDER BY FIELD(Icon, '1S', '2Q', '3P', '4M', '1') ASC;";//shop = "+"'"+shop+"'"+" AND 
						
						cn.ConToDb();
						stmt12 = cn.con.createStatement();
						rs12 = stmt12.executeQuery(query);
									
				        while (rs12.next()) {
				        	hmmr_ap_model hpm = new hmmr_ap_model();
				        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
				        		hpm.Id.set("AP"+rs12.getString(1));
				        		hpm.PM_Num.set(rs12.getString(2));
				        		hpm.Type.set(rs12.getString(3));
				        		hpm.Desc.set(rs12.getString(4));
				        		hpm.Due_Date.set(rs12.getString(5));
				        		hpm.Equip.set(rs12.getString(6));
				        		hpm.inst_btn.set(rs12.getString(7));
				        		hpm.OFT.set(rs12.getString(8));
				        		hpm.OTV.set(rs12.getString(9));
				        		hpm.tsk_maker.set(rs12.getString(10));
				        		hpm.flag_otv.set(rs12.getString(11));
				        		hpm.flag_oft.set(rs12.getString(12));
				        		hpm.flag_tm.set(rs12.getString(13));
				        		hpm.icon.set(rs12.getString(14));
				        		hpm.icon_at.set(rs12.getString(15));
				        						        				            
					            list.add(hpm);
				        	}    
				        }
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1829!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
					return list;
				}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		

/////////////////////////////////////////////////////////////////////ACTION PLAN//////////////////////////////////////////////////////////////////////////////
				//���������� ��� ������ �� ����
				@SuppressWarnings({ "static-access"})
				public ObservableList<hmmr_ap_model> _select_data_ap_shop(String shop, String oft)
				{
					ObservableList<hmmr_ap_model> list = FXCollections.observableArrayList();
					
					try {
						//String query = "select id,PM_Num,Type,Description,Due_Date,Equipment,Instruction,Otv_For_Task,Otv,Tsk_maker,flag_otv,flag_oft,flag_tm,Icon from hmmr_action_plan where del_rec = 0 AND (shop = "+"'"+shop+"'"+" OR Otv_For_Task = "+"'"+oft+"'"+" OR Otv = "+"'"+oft+"'"+" OR Tsk_maker = "+"'"+oft+"'"+");";
						String query = "select hap.id,hap.PM_Num,hap.Type,hap.Description,hap.Due_Date,hap.Equipment,hap.Instruction,hap.Otv_For_Task,hap.Otv,hap.Tsk_maker,hap.flag_otv,hap.flag_oft,hap.flag_tm,hap.Icon,hap.Icon_AT from hmmr_action_plan hap INNER JOIN hmmr_mu_staff hms ON hap.Otv = hms.ID where del_rec = 0 AND (if( "+"'"+shop+"'"+"='S' || "+"'"+shop+"'"+"='W', hms.Group_S='S,W', hms.Group_S="+"'"+shop+"'"+") OR Otv_For_Task = "+"'"+oft+"'"+" OR Otv = "+"'"+oft+"'"+" OR Tsk_maker = "+"'"+oft+"'"+") ORDER BY FIELD(Icon, '1S', '2Q', '3P', '4M', '1') ASC;";
						
						cn.ConToDb();
						stmt12 = cn.con.createStatement();
						rs12 = stmt12.executeQuery(query);
									
				        while (rs12.next()) {
				        	hmmr_ap_model hpm = new hmmr_ap_model();
				        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
				        		hpm.Id.set("AP"+rs12.getString(1));
				        		hpm.PM_Num.set(rs12.getString(2));
				        		hpm.Type.set(rs12.getString(3));
				        		hpm.Desc.set(rs12.getString(4));
				        		hpm.Due_Date.set(rs12.getString(5));
				        		hpm.Equip.set(rs12.getString(6));
				        		hpm.inst_btn.set(rs12.getString(7));
				        		hpm.OFT.set(rs12.getString(8));
				        		hpm.OTV.set(rs12.getString(9));
				        		hpm.tsk_maker.set(rs12.getString(10));
				        		hpm.flag_otv.set(rs12.getString(11));
				        		hpm.flag_oft.set(rs12.getString(12));
				        		hpm.flag_tm.set(rs12.getString(13));
				        		hpm.icon.set(rs12.getString(14));
				        		hpm.icon_at.set(rs12.getString(15));
				        						        				            
					            list.add(hpm);
				        	}    
				        }
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1790!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
					return list;
				}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////ACTION PLAN//////////////////////////////////////////////////////////////////////////////////////				
				//���������� ��� ����������� ������ �� ����
				@SuppressWarnings({ "static-access"})
				public ObservableList<hmmr_ap_model> _select_data_exectsk(String shop)
				{
					ObservableList<hmmr_ap_model> list = FXCollections.observableArrayList();
					
					try {
						//String query = "select id,PM_Num,Type,Description,Due_Date,Equipment,Instruction,Otv_For_Task,Otv,Tsk_maker,flag_otv,flag_oft,flag_tm from hmmr_action_plan where shop = "+"'"+shop+"'"+" AND flag_otv = 2 AND flag_oft = 2 AND flag_tm = 2;";
						//String query = "select hap.id,hap.PM_Num,hap.Type,hap.Description,hap.Due_Date,hap.Equipment,hap.Instruction,hap.Otv_For_Task,hap.Otv,hap.Tsk_maker,hap.flag_otv,hap.flag_oft,hap.flag_tm from hmmr_action_plan hap INNER JOIN hmmr_mu_staff hms ON hap.Otv = hms.ID AND hms.Group_S="+"'"+shop+"'"+"  AND hap.flag_otv = 2 AND hap.flag_oft = 2 AND hap.flag_tm = 2;";
						String query = "select hap.id,hap.PM_Num,hap.Type,hap.Description,hap.Due_Date,hap.Equipment,hap.Instruction,hap.Otv_For_Task,hap.Otv,hap.Tsk_maker,hap.flag_otv,hap.flag_oft,hap.flag_tm,hap.Icon,hap.Icon_AT from hmmr_action_plan hap INNER JOIN hmmr_mu_staff hms ON hap.Otv = hms.ID AND hap.flag_otv = 2 AND hap.flag_oft = 2 AND hap.flag_tm = 2 AND if( "+"'"+shop+"'"+"='S' || "+"'"+shop+"'"+"='W', hms.Group_S='S,W', hms.Group_S="+"'"+shop+"'"+");"; 
						
						cn.ConToDb();
						stmt12 = cn.con.createStatement();
						rs12 = stmt12.executeQuery(query);
									
				        while (rs12.next()) {
				        	hmmr_ap_model hpm = new hmmr_ap_model();
				        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
				        		hpm.Id.set("AP"+rs12.getString(1));
				        		hpm.PM_Num.set(rs12.getString(2));
				        		hpm.Type.set(rs12.getString(3));
				        		hpm.Desc.set(rs12.getString(4));
				        		hpm.Due_Date.set(rs12.getString(5));
				        		hpm.Equip.set(rs12.getString(6));
				        		hpm.inst_btn.set(rs12.getString(7));
				        		hpm.OFT.set(rs12.getString(8));
				        		hpm.OTV.set(rs12.getString(9));
				        		hpm.tsk_maker.set(rs12.getString(10));
				        		hpm.flag_otv.set(rs12.getString(11));
				        		hpm.flag_oft.set(rs12.getString(12));
				        		hpm.flag_tm.set(rs12.getString(13));
				        		hpm.icon.set(rs12.getString(14));
				        		hpm.icon_at.set(rs12.getString(15));
				        						        				            
					            list.add(hpm);
				        	}    
				        }
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1835!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
					return list;
				}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
				
////////////////////////////////////////////////////////////////////////////////ACTION PLAN///////////////////////////////////////////////////////////////				
				//��������� ������ ��� ���� S,W ���� �� ��� �� �������� !!!!!!!!������ ��� �� ������������ � 2018-10-19!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//���������� ��� ������ �� ����
				@SuppressWarnings({ "static-access"})
				public ObservableList<hmmr_ap_model> _select_data_ap_sw(String oft)
				{
					ObservableList<hmmr_ap_model> list = FXCollections.observableArrayList();
					
					try {
						String query = "select id,PM_Num,Type,Description,Due_Date,Equipment,Instruction,Otv_For_Task,Otv,Tsk_maker,flag_otv,flag_oft,flag_tm,Icon from hmmr_action_plan where del_rec = 0 AND ((shop = 'S' OR shop = 'W') OR (Otv_For_Task = "+"'"+oft+"'"+" OR Otv = "+"'"+oft+"'"+" OR Tsk_maker = "+"'"+oft+"'"+")) ORDER BY FIELD(Icon, '1S', '2Q', '3P', '4M', '1') ASC;";
						
						cn.ConToDb();
						stmt12 = cn.con.createStatement();
						rs12 = stmt12.executeQuery(query);
									
				        while (rs12.next()) {
				        	hmmr_ap_model hpm = new hmmr_ap_model();
				        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
				        		hpm.Id.set("AP"+rs12.getString(1));
				        		hpm.PM_Num.set(rs12.getString(2));
				        		hpm.Type.set(rs12.getString(3));
				        		hpm.Desc.set(rs12.getString(4));
				        		hpm.Due_Date.set(rs12.getString(5));
				        		hpm.Equip.set(rs12.getString(6));
				        		hpm.inst_btn.set(rs12.getString(7));
				        		hpm.OFT.set(rs12.getString(8));
				        		hpm.OTV.set(rs12.getString(9));
				        		hpm.tsk_maker.set(rs12.getString(10));
				        		hpm.flag_otv.set(rs12.getString(11));
				        		hpm.flag_oft.set(rs12.getString(12));
				        		hpm.flag_tm.set(rs12.getString(13));
				        		hpm.icon.set(rs12.getString(14));
				        						        				            
					            list.add(hpm);
				        	}    
				        }
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1970!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
					return list;
				}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
	@SuppressWarnings("static-access")
	public void _insert_pm_cycle(String type, String pereodic, String hours, String bdate, String edate)
	{
		String query = "INSERT INTO hmmr_pm_cycle (Type, Pereodic, Hours, begin_date, end_date) VALUES ("+"'"+type+"'"+","+ "'"+pereodic+"'"+","+"'"+hours+"'"+","+"'"+bdate+"'"+","+"'"+edate+"'"+");";
		
		try {
			cn.ConToDb();
			stmt = cn.con.createStatement();
			stmt.executeUpdate(query);
			//log.log(Level.INFO, "ADD STRING TO DB");
			//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
		} catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1922!");
		}
    	finally {
            //close connection ,stmt and resultset here
        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
	}
//////////////////////////////////////////////////////////////ACTION PLAN////////////////////////////////////////////////////////////////////////////////	
	//��������� ������ � Action Plan
	@SuppressWarnings("static-access")
	public void _insert_ap(String id_pm, String type, String pmname, LocalDate due_date, String equip, String instruct, String otf, String userid, String shop, String icon, String otv, String icon_at)
	{
		String query = "INSERT INTO hmmr_action_plan (PM_Num, Type, Description, Due_Date, Equipment, Instruction, Tsk_maker, Otv_For_Task, user_id, shop, Icon, Otv, Icon_AT) VALUES ("+"'"+id_pm+"'"+","+ "'"+type+"'"+","+"'"+pmname+"'"+","+"'"+due_date+"'"+","+"'"+equip+"'"+","+"'"+instruct+"'"+","+"'"+otf+"'"+","+"'"+otf+"'"+","+"'"+userid+"'"+","+"'"+shop+"'"+","+"'"+icon+"'"+","+"'"+otv+"'"+","+"'"+icon_at+"'"+");";
		
		try {
			cn.ConToDb();
			stmt = cn.con.createStatement();
			stmt.executeUpdate(query);
			//log.log(Level.INFO, "ADD STRING TO DB");
			//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
		} catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1944!");
		}
    	finally {
            //close connection ,stmt and resultset here
        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
////////////////////////////////////////////////////////////////////ACTION PLAN/////////////////////////////////////////////////////////////////////////////////
	//��������� ������ � Action Plan
		@SuppressWarnings("static-access")
		public void _insert_ap1(String type, String pmname, LocalDate due_date, String equip, String tsk_maker, String otf, String otv, String userid, String shop, String icon, String icon_at)
		{
			String query = "INSERT INTO hmmr_action_plan (Type, Description, Due_Date, Equipment, Tsk_maker, Otv_For_Task, Otv, user_id, shop, Icon, Icon_AT) VALUES ("+ "'"+type+"'"+","+"'"+pmname+"'"+","+"'"+due_date+"'"+","+"'"+equip+"'"+","+"'"+tsk_maker+"'"+","+"'"+otf+"'"+","+"'"+otv+"'"+","+"'"+userid+"'"+","+"'"+shop+"'"+","+"'"+icon+"'"+","+"'"+icon_at+"'"+");";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2065!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!		
		//��������� ������ � Work Recording
				@SuppressWarnings("static-access")
				public void _insert_wr(String ap_num, String userid, String WSH, String Group_EQ, String Line, String Station, String Equip, String equip, String record_type, Double wt, String resp, String resp1, String resp2, String resp3, String status_wr, String shift_report, String req_action, LocalDate actual_date, LocalDate actual_date_2, LocalDate actual_date_3, LocalDate actual_date_4, String actual_time, LocalDate actual_date1, LocalDate actual_date2, LocalDate actual_date3, LocalDate actual_date4, String actual_time1, String actual_time2, String actual_time3, String actual_time4, LocalTime hours1, LocalTime hours1_2, LocalTime hours1_3, LocalTime hours1_4, LocalTime hours2, LocalTime hours2_2, LocalTime hours2_3, LocalTime hours2_4, String b_gdw, String e_gdw, String b_gtw, String e_gtw, String icon_at, String  _Resp4, String _Resp5, String _Resp6, String _Resp7, String _Resp8, LocalDate _Actual_Date_5, LocalDate _Actual_Date_6, LocalDate _Actual_Date_7, LocalDate _Actual_Date_8, LocalDate _Actual_Date_9, LocalDate _Actual_Date5, LocalDate _Actual_Date6, LocalDate _Actual_Date7, LocalDate _Actual_Date8, LocalDate _Actual_Date9, String _Actual_Time5, String _Actual_Time6, String _Actual_Time7, String _Actual_Time8, String _Actual_Time9, LocalTime _Hours1_5, LocalTime _Hours1_6, LocalTime _Hours1_7, LocalTime _Hours1_8, LocalTime _Hours1_9, LocalTime _Hours2_5, LocalTime _Hours2_6, LocalTime _Hours2_7, LocalTime _Hours2_8, LocalTime _Hours2_9)
				{
					String query = "INSERT INTO hmmr_work_recording (ap_num, user_number, FL_WSH, FL_Group, FL_Line, FL_Station, FL_Equipment, Equipment_Full, Record_Type, CM_Work_Time, Task_Resp_ID, _Resp2, _Resp3, _Resp4, WR_Executor_Confirmed, Task_Description, Task_Report, WR_Begin_Date, _Actual_Date_2, _Actual_Date_3, _Actual_Date_4, CM_DownTime, WR_End_Date, _Actual_Date2, _Actual_Date3, _Actual_Date4, WR_Work_Time, _Actual_Time2, _Actual_Time3, _Actual_Time4, WR_Work_Time_Begin, _Hours1_2, _Hours1_3, _Hours1_4, WR_Work_Time_End, _Hours2_2, _Hours2_3, _Hours2_4, CM_Date_Begin, CM_Date_End, CM_Time_Begin, CM_Time_End, Activity_Type, _Resp5, _Resp6, _Resp7, _Resp8, _Resp9, _Actual_Date_5, _Actual_Date_6, _Actual_Date_7, _Actual_Date_8, _Actual_Date_9, _Actual_Date5, _Actual_Date6, _Actual_Date7, _Actual_Date8, _Actual_Date9, _Actual_Time5, _Actual_Time6, _Actual_Time7, _Actual_Time8, _Actual_Time9, _Hours1_5, _Hours1_6, _Hours1_7, _Hours1_8, _Hours1_9, _Hours2_5, _Hours2_6, _Hours2_7, _Hours2_8, _Hours2_9) VALUES ("+"'"+ap_num+"'"+","+ "'"+userid+"'"+","+"'"+WSH+"'"+","+"'"+Group_EQ+"'"+","+"'"+Line+"'"+","+"'"+Station+"'"+","+"'"+Equip+"'"+","+"'"+equip+"'"+","+"'"+record_type+"'"+","+"'"+wt+"'"+","+"'"+resp+"'"+","+"'"+resp1+"'"+","+"'"+resp2+"'"+","+"'"+resp3+"'"+","+"'"+status_wr+"'"+","+"'"+shift_report+"'"+","+"'"+req_action+"'"+","+"'"+actual_date+"'"+","+"'"+actual_date_2+"'"+","+"'"+actual_date_3+"'"+","+"'"+actual_date_4+"'"+","+"'"+actual_time+"'"+","+"'"+actual_date1+"'"+","+"'"+actual_date2+"'"+","+"'"+actual_date3+"'"+","+"'"+actual_date4+"'"+","+"'"+actual_time1+"'"+","+"'"+actual_time2+"'"+","+"'"+actual_time3+"'"+","+"'"+actual_time4+"'"+","+"'"+hours1+"'"+","+"'"+hours1_2+"'"+","+"'"+hours1_3+"'"+","+"'"+hours1_4+"'"+","+"'"+hours2+"'"+","+"'"+hours2_2+"'"+","+"'"+hours2_3+"'"+","+"'"+hours2_4+"'"+","+"'"+b_gdw+"'"+","+"'"+e_gdw+"'"+","+"'"+b_gtw+"'"+","+"'"+e_gtw+"'"+","+"'"+icon_at+"'"+","+"'"+_Resp4+"'"+","+"'"+_Resp5+"'"+","+"'"+_Resp6+"'"+","+"'"+_Resp7+"'"+","+"'"+_Resp8+"'"+","+"'"+_Actual_Date_5+"'"+","+"'"+_Actual_Date_6+"'"+","+"'"+_Actual_Date_7+"'"+","+"'"+_Actual_Date_8+"'"+","+"'"+_Actual_Date_9+"'"+","+"'"+_Actual_Date5+"'"+","+"'"+_Actual_Date6+"'"+","+"'"+_Actual_Date7+"'"+","+"'"+_Actual_Date8+"'"+","+"'"+_Actual_Date9+"'"+","+"'"+_Actual_Time5+"'"+","+"'"+_Actual_Time6+"'"+","+"'"+_Actual_Time7+"'"+","+"'"+_Actual_Time8+"'"+","+"'"+_Actual_Time9+"'"+","+"'"+_Hours1_5+"'"+","+"'"+_Hours1_6+"'"+","+"'"+_Hours1_7+"'"+","+"'"+_Hours1_8+"'"+","+"'"+_Hours1_9+"'"+","+"'"+_Hours2_5+"'"+","+"'"+_Hours2_6+"'"+","+"'"+_Hours2_7+"'"+","+"'"+_Hours2_8+"'"+","+"'"+_Hours2_9+"'"+");";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "ADD STRING TO DB");
						//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
					} catch (SQLException e) {
						//e.printStackTrace();
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1990!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!				
	//��������� ������ � ������� ��� ����������� Type_PM 
	@SuppressWarnings("static-access")
	public void _insert_type_pm(String type, String name, String desc, String icon)
	{
		String query = "INSERT INTO hmmr_activity_type (ID_OT, Name, Description, Icon) VALUES ("+"'"+type+"'"+","+ "'"+name+"'"+","+ "'"+desc+"'"+","+ "'"+icon+"'"+");";
		
		try {
			cn.ConToDb();
			stmt = cn.con.createStatement();
			stmt.executeUpdate(query);
			//log.log(Level.INFO, "ADD STRING TO DB");
			//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
		} catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2118!");
		}
    	finally {
            //close connection ,stmt and resultset here
        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
	}
	
	//��������� ������ � ������� hmmr_pm_year 
		@SuppressWarnings("static-access")
		public void _insert_pm_year(String pm_id, int pm_group, LocalDate data, String OFT) //, String pm_startdate, String pm_duration
		{
			String query = "INSERT INTO hmmr_pm_year (PM_ID,PM_Group, data, OFT) VALUES ("+"'"+pm_id+"'"+","+"'"+pm_group+"'"+","+ "'"+data+"'"+","+"'"+OFT+"'"+");";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2140!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		
	//��������� � ������� history
		@SuppressWarnings("static-access")
		public void _insert_history(String userid, String action)
		{
			String query = "INSERT INTO hmmr_history (user_id, action) VALUES ("+"'"+userid+"'"+","+ "'"+action+"'"+");";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2059!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}	
	
	//��������� ������ � ������� PM Instruction 
		@SuppressWarnings("static-access")
		public void _insert_pm_inst(String ninst_inst, LocalDate date_create, LocalDate date_change, String inst_pdf, String ver_inst, String mt_inst, String pmname_inst, String sdoc_txt_inst, String qtyspec_inst, String ptw_inst,
				                    String wt_inst, String adm2_inst, String adm3_inst, String of1_inst, String of2_inst,
				                    String typepm_inst, String cyclepm1_inst, String cyclepm2_inst, String pos_inst, String line_inst, String power_inst, String sinfo_inst)
		{
			String query = "INSERT INTO pm_inst (num_instruction,Creation_date,Last_edition_date,Link_instruction_PDF,Version,Model_Type_Task,PM_name,Type_PM,PM_Cycle1,PM_Cycle2,ON_Line_OFF_Line,Power_ON_Power_OFF,"
					+ "Position,Src_Info,Src_Doc,Qty_Specialist,Prep_Work_Time,Work_Time,Admission_2,Admission_3,Outfit_1,Outfit_2) "
					+ "VALUES ("+"'"+ninst_inst+"'"+","+"'"+date_create+"'"+","+"'"+date_change+"'"+","+"'"+inst_pdf+"'"+","+"'"+ver_inst+"'"+","+"'"+mt_inst+"'"+","+"'"+pmname_inst+"'"+","+"'"+typepm_inst+"'"+","
					+ ""+"'"+cyclepm1_inst+"'"+","+"'"+cyclepm2_inst+"'"+","+"'"+line_inst+"'"+","+"'"+power_inst+"'"+","+"'"+pos_inst+"'"+","
					+ ""+"'"+sinfo_inst+"'"+","+"'"+sdoc_txt_inst+"'"+","+"'"+qtyspec_inst+"'"+","+"'"+ptw_inst+"'"+","+"'"+wt_inst+"'"+","
					+ ""+"'"+adm2_inst+"'"+","+"'"+adm3_inst+"'"+","+"'"+of1_inst+"'"+","+"'"+of2_inst+"'"+");";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2084!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		
		/**
		 * ��������� ������ � Plant Structure 
		 * @param user_id - id ������������
		 * @param FL01_Company - ������ �������� Company
		 * @param FL02_Plant - ������ �������� Plant
		 * @param FL03_Shop_s - ������ �������� Shop_s
		 * @param FL04_Group_s - ������ �������� Group_PM
		 * @param FL05_Line_s - ������ �������� Line_Macine_s
		 * @param FL06_Station_s - ������ �������� Operation_Station_s
		 * @param FL07_Equipment_s - ������ �������� Equipmant_s
		 * @param FL03_Shop_ENG - ������ �������� Shop
		 * @param FL04_Group_ENG - ����� ����
		 * @param FL05_Line_ENG - ������ �������� Line_Machine
		 * @param FL06_Station_ENG - ������ �������� Operation_Station
		 * @param FL07_Equipment_ENG - ������ �������� Equipmant
		 * @param FL03_Shop_RUS - ����� ����
		 * @param FL04_Group_RUS - ����� ����
		 * @param FL05_Line_RUS - ������ �������� Line_Machine_RUS
		 * @param FL06_Station_RUS - ������ �������� Operation_Station_RUS
		 * @param FL07_Equipment_RUS - ����� ����
		 * @param Description_RUS - ������ �������� Description
		 * @param Equip_label - ������ �������� Equip_label
		 * @param Station_Label - ������ �������� Station_label
		 * @param Equipment_Folder_Link - ������ �������� manual
		 * @param Resp_Planner_Group - ����� ���� ������ �������������(����� �� �����������)
		 * @param Assets_Inventory_Number - ����� ���� ����������� �����
		 * @param Assets_OS1_Number - ����� ���� � �������� �������
		 * @param Assets_Start_up_Date - ����� ���� ���� ������� � ������������
		 * @param Cost_Center - ����� ���� ���� �������
		 * @param Site_Location - ������ �������� Location
		 * @param Site_CHAMBER - ������ �������� CHAMBER
		 * @param Site_Coordinates - ������ �������� Coordinates
		 * @param Site_Altitude - ������ �������� Altitude
		 * @param TR_CU - �� ����������
		 * @param TR_CU_Link - �� ����������
		 * @param Hazardous - �� ����������
		 * @param Key_equipment - �� ����������
		 * @param EQ_Integrator - ������ �������� Station Supplier
		 * @param EQ_Manufacture - ������ �������� Manuf
		 * @param EQ_Type - ������ �������� Type
		 * @param EQ_Serial_Number - ������ �������� S_N
		 * @param EQ_Technical_Characteristic - ������ �������� Main_Technical_Characteristic
		 * @param Responsobility - �� ����������
		 * @param M_Electric - �� ����������
		 * @param M_Air - �� ����������
		 * @param M_Water - �� ����������
		 * @param M_Cold_Water - �� ����������
		 * @param M_Hot_Water - �� ����������
		 * @param M_RO_Water - ������ �������� RO_Water
		 * @param M_Gas - �� ����������
		 * @param Status - ������ �������� del_rec
		 */
		//*************************************************** PLANT STRUCTURE ******************************************************
		@SuppressWarnings("static-access")
		public void _insert_ps(String user_id, String FL01_Company, String FL02_Plant, String FL03_Shop_s,
				String FL04_Group_s, String FL05_Line_s, String FL06_Station_s, String FL07_Equipment_s, 
				String FL03_Shop_ENG, String FL04_Group_ENG, String FL05_Line_ENG, String FL06_Station_ENG, 
				String FL07_Equipment_ENG, String FL03_Shop_RUS, String FL04_Group_RUS, String FL05_Line_RUS, 
				String FL06_Station_RUS, String FL07_Equipment_RUS, String Description_RUS, String Equip_label, 
				String Station_Label, String Equipment_Folder_Link, String Resp_Planner_Group, 
				String Assets_Inventory_Number, String Assets_OS1_Number, LocalDate Assets_Start_up_Date, 
				String Cost_Center, String Site_Location, String Site_CHAMBER, String Site_Coordinates, 
				String Site_Altitude, String TR_CU, String TR_CU_Link, String Hazardous, String Key_equipment, 
				String EQ_Integrator, String EQ_Manufacture, String EQ_Type, String EQ_Serial_Number, 
				String EQ_Technical_Characteristic, String Responsobility, String M_Electric, String M_Air, 
				String M_Water, String M_Cold_Water, String M_Hot_Water, String M_RO_Water, String M_Gas)
		{
			String query = "INSERT INTO hmmr_plant_structure (user_id, FL01_Company, FL02_Plant, FL03_Shop_s, FL04_Group_s, "
					+ "FL05_Line_s, FL06_Station_s, FL07_Equipment_s, FL03_Shop_ENG, FL04_Group_ENG, FL05_Line_ENG, "
					+ "FL06_Station_ENG, FL07_Equipment_ENG, FL03_Shop_RUS, FL04_Group_RUS, FL05_Line_RUS, FL06_Station_RUS, "
					+ "FL07_Equipment_RUS, Description_RUS, Equip_label, Station_Label, Equipment_Folder_Link, "
					+ "Resp_Planner_Group, Assets_Inventory_Number, Assets_OS1_Number, Assets_Start_up_Date, Cost_Center, "
					+ "Site_Location, Site_CHAMBER, Site_Coordinates, Site_Altitude, TR_CU, TR_CU_Link, Hazardous, "
					+ "Key_equipment, EQ_Integrator, EQ_Manufacture, EQ_Type, EQ_Serial_Number, EQ_Technical_Characteristic, "
					+ "Responsobility, M_Electric, M_Air, M_Water, M_Cold_Water, M_Hot_Water, M_RO_Water, M_Gas) "
					+ "VALUES ("+"'"+conn_connector.USER_ID+"'"+","+"'"+FL01_Company+"'"+","+"'"+FL02_Plant+"'"+","+"'"+FL03_Shop_s+"'"+","+"'"+FL04_Group_s+"'"+","+"'"+FL05_Line_s+"'"+","
					+ ""+"'"+FL06_Station_s+"'"+","+"'"+FL07_Equipment_s+"'"+","+"'"+FL03_Shop_ENG+"'"+","+"'"+FL04_Group_ENG+"'"+","+"'"+FL05_Line_ENG+"'"+","
					+ ""+"'"+FL06_Station_ENG+"'"+","+"'"+FL07_Equipment_ENG+"'"+","+"'"+FL03_Shop_RUS+"'"+","+"'"+FL04_Group_RUS+"'"+","+"'"+FL05_Line_RUS+"'"+","
					+ ""+"'"+FL06_Station_RUS+"'"+","+"'"+FL07_Equipment_RUS+"'"+","+"'"+Description_RUS+"'"+","+"'"+Equip_label+"'"+","
					+ ""+"'"+Station_Label+"'"+","+"'"+Equipment_Folder_Link+"'"+","+"'"+Resp_Planner_Group+"'"+","+"'"+Assets_Inventory_Number+"'"+","
					+ ""+"'"+Assets_OS1_Number+"'"+","+"'"+Assets_Start_up_Date+"'"+","+"'"+Cost_Center+"'"+","+"'"+Site_Location+"'"+","
					+ ""+"'"+Site_CHAMBER+"'"+","+"'"+Site_Coordinates+"'"+","+"'"+Site_Altitude+"'"+","+"'"+TR_CU+"'"+","
					+ ""+"'"+TR_CU_Link+"'"+","+"'"+Hazardous+"'"+","+"'"+Key_equipment+"'"+","+"'"+EQ_Integrator+"'"+","
					+ ""+"'"+EQ_Manufacture+"'"+","+"'"+EQ_Type+"'"+","+"'"+EQ_Serial_Number+"'"+","+"'"+EQ_Technical_Characteristic+"'"+","
					+ ""+"'"+Responsobility+"'"+","+"'"+M_Electric+"'"+","+"'"+M_Air+"'"+","+"'"+M_Water+"'"+","+"'"+M_Cold_Water+"'"+","+"'"+M_Hot_Water+"'"+","+"'"+M_RO_Water+"'"+","+"'"+M_Gas+"'"+");";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2116!");
			}
		   	finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
//*****************************************************************************************************************************		
		//��������� ������ � ������� PM 
				@SuppressWarnings("static-access")
				public void _insert_pm(String ninst_pm, String eq_id, String group_pm, String otv_pm, String ool_pm, String pm_exec)
				{
					String query = "INSERT INTO hmmr_pm (user_id, Instruction_num, Eq_ID, PM_Group, PM_Resp, OnOff_Line, PM_Executor) "
							+ "VALUES ("+"'"+conn_connector.USER_ID+"'"+","+"'"+ninst_pm+"'"+","+"'"+eq_id+"'"+","+"'"+group_pm+"'"+","+"'"+otv_pm+"'"+","+"'"+ool_pm+"'"+","+"'"+pm_exec+"'"+");";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "ADD STRING TO DB");
						//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2315!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
	
	//��� ����� ���������� ����������� pm_cycle
	@SuppressWarnings("static-access")
	public String _select_for_update_cycle(String id)
	{
		try {
			String query = "select Type, Pereodic, Hours, begin_date, end_date from hmmr_pm_cycle where id = "+"'"+id+"'"+";";
			
			cn.ConToDb();
			stmt2 = cn.con.createStatement();
			rs2 = stmt2.executeQuery(query);
			//log.log(Level.INFO, "CHANNEL WAS FOUND");
	        while (rs2.next()) {
	            type_c = rs2.getString(1);
	            pereodic_c = rs2.getString(2);
	            hours_c = rs2.getString(3);
	            bdate_c = rs2.getString(4);
	            edate_c = rs2.getString(5);
	        }
	        total_rez = type_c+","+pereodic_c+","+hours_c+","+bdate_c+","+edate_c;
//	        System.out.println("SELECT WORKED: "+total_rez);
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2181!");
        } finally {
            //close connection ,stmt and resultset here
        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt2.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs2.close(); } catch(SQLException se) { /*can't do anything */ }
        }
		return total_rez;
	}
	
	//��� ����� ���������� ����������� Type_PM
		@SuppressWarnings("static-access")
		public String _select_for_update_typepm(String id)
		{
			try {
				String query = "select ID_OT, Name, Description, Icon from hmmr_activity_type where id = "+"'"+id+"'"+";";
				
				cn.ConToDb();
				stmt4 = cn.con.createStatement();
				rs4 = stmt4.executeQuery(query);
				//log.log(Level.INFO, "CHANNEL WAS FOUND");
		        while (rs4.next()) {
		            type_tpm = rs4.getString(1);
		            name_tpm = rs4.getString(2);
		            desc_tpm = rs4.getString(3);
		            icon_tpm = rs4.getString(4);
		        }
		        total_rez_tpm = type_tpm+","+name_tpm+","+desc_tpm+","+icon_tpm;
//		        System.out.println("SELECT WORKED: "+total_rez);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2377!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt4.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs4.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return total_rez_tpm;
		}
		
		//�������� ��� � ���� �� ������� Color
				@SuppressWarnings("static-access")
				public ObservableList<String> _select_data_color()
				{
					ObservableList<String> _color = FXCollections.observableArrayList();
					String _info;
					try {
						String query = "select days, colors from hmmr_colors;";
						
						cn.ConToDb();
						stmt4 = cn.con.createStatement();
						rs4 = stmt4.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs4.next()) {
				            _info = rs4.getString(1)+","+rs4.getString(2);
				        				            
				            _color.add(_info);
				        }
				        
//				        System.out.println("SELECT WORKED: "+total_rez);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2244!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt4.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs4.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
					return _color;
				}
		
		//��� ����� ���������� ������� PM Instruction
				@SuppressWarnings("static-access")
				public String _select_for_update_pminst(String id)
				{
					try {
						String query = "select num_instruction,Creation_date,Last_edition_date,Link_instruction_PDF,Version,Model_Type_Task,PM_name,Type_PM,PM_Cycle1,PM_Cycle2,ON_Line_OFF_Line,Power_ON_Power_OFF,Position,Src_Info,Src_Doc,Qty_Specialist,Prep_Work_Time,Work_Time,Admission_2,Admission_3,Outfit_1,Outfit_2 from pm_inst where id = "+"'"+id+"'"+";";
						
						cn.ConToDb();
						stmt10 = cn.con.createStatement();
						rs10 = stmt10.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs10.next()) {
				            ninst_inst = rs10.getString(1);
				            date_create = rs10.getString(2);
				            date_change = rs10.getString(3);
				            inst_pdf = rs10.getString(4);
				            ver_inst = rs10.getString(5);
				            mtt_inst = rs10.getString(6);
				            pmn_inst = rs10.getString(7);
				            tpm_inst = rs10.getString(8);
				            pmc1_inst = rs10.getString(9);
				            pmc2_inst = rs10.getString(10);
				            ool_inst = rs10.getString(11); 
				            oop_inst = rs10.getString(12); 
				            pos_inst = rs10.getString(13); 
				            sinfo_inst = rs10.getString(14); 
				            s_doc_inst = rs10.getString(15); 
				            qspec_inst = rs10.getString(16);
			    			pwt_inst = rs10.getString(17);
			    			wt_inst = rs10.getString(18); 
			    			adm2_inst = rs10.getString(19); 
			    			adm3_inst = rs10.getString(20); 
			    			ofit1_inst = rs10.getString(21); 
			    			ofit2_inst = rs10.getString(22); 
			    		}
				        total_rez_upd_inst = ninst_inst+";"+date_create+";"+date_change+";"+inst_pdf+";"+ver_inst+";"+mtt_inst+";"+pmn_inst+";"+tpm_inst+";"+pmc1_inst+";"+pmc2_inst+";"+ool_inst+";"+oop_inst+";"+pos_inst+";"+
				                             sinfo_inst+";"+s_doc_inst+";"+qspec_inst+";"+pwt_inst+";"+wt_inst+";"+adm2_inst+";"+adm3_inst+";"+ofit1_inst+";"+
				        		             ofit2_inst;
//				        System.out.println("SELECT WORKED: "+total_rez);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2274!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt10.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs10.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
					return total_rez_upd_inst;
				}
				
				//��� ����� ���������� ������� PM
				@SuppressWarnings("static-access")
				public String _select_for_update_pm(String id)
				{
					try {
						//String query = "select Instruction_num,Eq_ID,PM_Group,PM_Resp,OnOff_Line,PM_Executor from hmmr_pm where id = "+"'"+id+"'"+";";
						String query = "select hp.Instruction_num,hp.Eq_ID,hp.PM_Group,hp.PM_Resp,hp.OnOff_Line,hp.PM_Executor,hps.FL03_Shop_s,hps.FL04_Group_s,hps.FL05_Line_s,hps.FL06_Station_s,hps.FL07_Equipment_s from hmmr_pm hp INNER JOIN hmmr_plant_structure hps ON hps.id = hp.Eq_ID where hp.id = "+"'"+id+"'"+";";
						cn.ConToDb();
						stmt9 = cn.con.createStatement();
						rs9 = stmt9.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs9.next()) {
				        	ninst_pm = rs9.getString(1);
				        	eq_id = rs9.getString(2);
				        	group_pm = rs9.getString(3);
				        	pm_resp = rs9.getString(4);
				        	ool_pm = rs9.getString(5); 
				        	pm_exec = rs9.getString(6);
				        	shop_pm = rs9.getString(7);
				        	groupeq_pm = rs9.getString(8);
				        	lm_pm = rs9.getString(9);
				        	os_pm = rs9.getString(10);
				        	equip_pm = rs9.getString(11);
				        }
				        total_rez_upd_pm = ninst_pm+","+eq_id+","+group_pm+","+pm_resp+","+ool_pm+","+pm_exec+","+shop_pm+","+groupeq_pm+","+lm_pm+","+os_pm+","+equip_pm;
//				        System.out.println("SELECT WORKED: "+total_rez);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2484!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt9.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs9.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
					return total_rez_upd_pm;
				}
//////////////////////////////////////////////////////////////////////////////ACTION PLAN///////////////////////////////////////////////////////////////////////				
				//��� ����� ���������� ������� AP
				@SuppressWarnings("static-access")
				public String _select_for_update_ap(String id)
				{
					try {
						String query = "select PM_Num,Type,Description,Due_Date,Equipment,Otv_For_Task,Otv,Icon,Icon_AT from hmmr_action_plan where id = "+"'"+id+"'"+";";
						
						cn.ConToDb();
						stmt15 = cn.con.createStatement();
						rs15 = stmt15.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs15.next()) {
				        	pmnum_ap = rs15.getString(1);
				        	type_ap = rs15.getString(2);
				        	description_ap = rs15.getString(3);
				        	due_date_ap = rs15.getString(4);
				        	equip_ap = rs15.getString(5);
				        	oft_ap = rs15.getString(6); 
				        	otv_ap = rs15.getString(7); 
				        	icon = rs15.getString(8);
				        	icon_at = rs15.getString(9);
				        }
				        total_rez_upd_ap = pmnum_ap+";"+type_ap+";"+description_ap+";"+due_date_ap+";"+equip_ap+";"+oft_ap+";"+otv_ap+";"+icon+";"+icon_at;
//				        System.out.println("SELECT WORKED: "+total_rez);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2365!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt15.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs15.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
					return total_rez_upd_ap;
				}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
				//��� ����� ���������� ������� PM Plan
				@SuppressWarnings("static-access")
				public String _select_for_update_pmplan(String id)
				{
					try {
						String query = "select PM_Group, data, OFT from hmmr_pm_year where id = "+"'"+id+"'"+" AND record_del = 0;";
						
						cn.ConToDb();
						stmt15 = cn.con.createStatement();
						rs15 = stmt15.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs15.next()) {
				        	pm_group = rs15.getString(1);
				        	data_pmplan = rs15.getString(2);
				        	oft_pmplan = rs15.getString(3);
				        }
				        total_rez_pmplan = pm_group+";"+data_pmplan+";"+oft_pmplan;
//				        System.out.println("SELECT WORKED: "+total_rez);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2546!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt15.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs15.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
					return total_rez_pmplan;
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!				
				//��� ����� ���������� ������� WR
				@SuppressWarnings("static-access")
				public String _select_for_update_wr(String id)
				{

					try {
						String query = "select ap_num,WR_End_Date,Equipment_Full,Record_Type,CM_Work_Time,Task_Resp_ID,_Resp2,_Resp3,_Resp4,WR_Executor_Confirmed,Task_Description,Task_Report,WR_Host_Confirmed,WR_Begin_Date,_Actual_Date_2,_Actual_Date_3,_Actual_Date_4,CM_DownTime,WR_End_Date,_Actual_Date2,_Actual_Date3,_Actual_Date4,WR_Work_Time,_Actual_Time2,_Actual_Time3,_Actual_Time4,WR_Resp_Confirmed,WR_Work_Time_Begin,_Hours1_2,_Hours1_3,_Hours1_4,_Min1,WR_Work_Time_End,_Hours2_2,_Hours2_3,_Hours2_4,_Min2,user_number,Activity_Type from hmmr_work_recording where id = "+"'"+id+"'"+";";
						cn.ConToDb();
						stmt15 = cn.con.createStatement();
						rs15 = stmt15.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs15.next()) {
				        	ap_num = rs15.getString(1);
				        	data = rs15.getString(2);
				        	equip = rs15.getString(3);
				        	record_type = rs15.getString(4);
				        	work_time = rs15.getString(5);
				        	resp = rs15.getString(6);
				        	resp2 = rs15.getString(7);
				        	resp3 = rs15.getString(8);
				        	resp4 = rs15.getString(9);
				        	status_wr = rs15.getString(10);
				        	shift_report = rs15.getString(11);
				        	req_action = rs15.getString(12);
				        	qty = rs15.getString(13);
				        	actual_date = rs15.getString(14);
				        	actual_date_2 = rs15.getString(15);
				        	actual_date_3 = rs15.getString(16);
				        	actual_date_4 = rs15.getString(17);
				        	actual_time = rs15.getString(18);
				        	actual_date1 = rs15.getString(19);
				        	actual_date2 = rs15.getString(20);
				        	actual_date3 = rs15.getString(21);
				        	actual_date4 = rs15.getString(22);
				        	actual_time1 = rs15.getString(23);
				        	actual_time2 = rs15.getString(24);
				        	actual_time3 = rs15.getString(25);
				        	actual_time4 = rs15.getString(26);
				        	user = rs15.getString(27);
				        	hours1 = rs15.getString(28);
				        	hours1_2 = rs15.getString(29);
				        	hours1_3 = rs15.getString(30);
				        	hours1_4 = rs15.getString(31);
				        	min1 = rs15.getString(32);
				        	hours2 = rs15.getString(33);
				        	hours2_2 = rs15.getString(34);
				        	hours2_3 = rs15.getString(35);
				        	hours2_4 = rs15.getString(36);
				        	min2 = rs15.getString(37);
				        	user_number = rs15.getString(38);
				        	activity_type = rs15.getString(39);
				        }
				        total_rez_upd_wr = shift_report+";"+req_action+";"+actual_time+";"+actual_time1+";"+actual_time2+";"+actual_time3+";"+actual_time4+";"+data+";"+equip+";"+record_type+";"+resp+";"+resp2+";"+resp3+";"+resp4+";"+status_wr+";"+qty+";"+ap_num+";"+work_time+";"+actual_date+";"+actual_date_2+";"+actual_date_3+";"+actual_date_4+";"+actual_date1+";"+actual_date2+";"+actual_date3+";"+actual_date4+";"+user+";"+hours1+";"+hours1_2+";"+hours1_3+";"+hours1_4+";"+min1+";"+hours2+";"+hours2_2+";"+hours2_3+";"+hours2_4+";"+min2+";"+user_number+";"+activity_type;
//				        System.out.println("SELECT WORKED: "+total_rez);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2430!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt15.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs15.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
					return total_rez_upd_wr;
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				
//*************************************************** PLANT STRUCTURE ***********************************************************				
				//��� ����� ���������� ������� PS
				@SuppressWarnings("static-access")
				public String _select_for_update_ps(String id)
				{

					try {
						String query = "select id,FL01_Company, FL02_Plant, FL03_Shop_s, FL04_Group_s, FL05_Line_s, FL06_Station_s, FL07_Equipment_s, FL03_Shop_ENG, FL04_Group_ENG, FL05_Line_ENG, FL06_Station_ENG, FL07_Equipment_ENG, FL03_Shop_RUS, FL04_Group_RUS, FL05_Line_RUS, FL06_Station_RUS, FL07_Equipment_RUS, Description_RUS, Equipment_Folder_Link, Resp_Planner_Group, Assets_Inventory_Number, Assets_OS1_Number, Assets_Start_up_Date, Cost_Center, Site_Location, Site_CHAMBER, Site_Coordinates, Site_Altitude, TR_CU, TR_CU_Link, Hazardous, Key_equipment, EQ_Integrator, EQ_Manufacture, EQ_Type, EQ_Serial_Number, EQ_Technical_Characteristic, Responsobility, M_Electric, M_Air, M_Water, M_Cold_Water, M_Hot_Water, M_RO_Water, M_Gas from hmmr_plant_structure where id = "+"'"+id+"'"+";";
						cn.ConToDb();
						stmt17 = cn.con.createStatement();
						rs17 = stmt17.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
						
				        while (rs17.next()) {
				        	_num_ps = rs17.getString(1);
				        	_company_ps = rs17.getString(2);
				        	_plant_ps = rs17.getString(3);
				        	_shops_ps = rs17.getString(4);
				        	_groups_ps = rs17.getString(5);
				        	_lines_ps = rs17.getString(6);
				        	_oss_ps = rs17.getString(7);
				        	_equips_ps = rs17.getString(8);
				        	_shop_ps = rs17.getString(9);
				        	_group_eng = rs17.getString(10);
				        	_line_ps = rs17.getString(11);
				        	_os_ps = rs17.getString(12);
				        	_equip_ps = rs17.getString(13);
				        	_shop_rus = rs17.getString(14);
				        	_group_rus = rs17.getString(15);
				        	_linerus_ps = rs17.getString(16);
				        	_osrus_ps = rs17.getString(17);
				        	_equip_rus = rs17.getString(18);
				        	_description_ps = rs17.getString(19);
				        	_manual_ps = rs17.getString(20);
				        	_group_otv = rs17.getString(21);
				        	_inv_num = rs17.getString(22);
				        	_os_num = rs17.getString(23);
				        	_start_date = rs17.getString(24);
				        	_cost_centre = rs17.getString(25);
				        	_location_ps = rs17.getString(26);
				        	_cham_ps = rs17.getString(27);
				        	_coord_ps = rs17.getString(28);
				        	_alt_ps = rs17.getString(29);
				        	_trcu_ps = rs17.getString(30);
				        	_trcul_ps = rs17.getString(31);
				        	_hazardous_ps = rs17.getString(32);
				        	_keyequip_ps = rs17.getString(33);
				        	_stsupplier_ps = rs17.getString(34);
				        	_manuf_ps = rs17.getString(35);
				        	_type_ps = rs17.getString(36);
				        	_sn_ps = rs17.getString(37);
				        	_mtc_ps = rs17.getString(38);
				        	_respons_ps = rs17.getString(39);
				        	_melec_ps = rs17.getString(40);
				        	_mair_ps = rs17.getString(41);
				        	_mwater_ps = rs17.getString(42);
				        	_mcwater_ps = rs17.getString(43);
				        	_mhwater_ps = rs17.getString(44);
				        	_rowater_ps = rs17.getString(45);
				        	_mgas_ps = rs17.getString(46);
				        }
						total_rez_ps = _num_ps+";"+_company_ps+";"+_plant_ps+";"+_shops_ps+";"+_groups_ps+";"+_lines_ps+";"+_oss_ps+";"+_equips_ps+";"+_shop_ps+";"+_group_eng+";"+_line_ps+";"+_os_ps+";"+_equip_ps+";"+_shop_rus+";"+_group_rus+";"+_linerus_ps+";"+_osrus_ps+";"+_equip_rus+";"+_description_ps+";"+_manual_ps+";"+_group_otv+";"+_inv_num+";"+_os_num+";"+_start_date+";"+_cost_centre+";"+_location_ps+";"+_cham_ps+";"+_coord_ps+";"+_alt_ps+";"+_trcu_ps+";"+_trcul_ps+";"+_hazardous_ps+";"+_keyequip_ps+";"+_stsupplier_ps+";"+_manuf_ps+";"+_type_ps+";"+_sn_ps+";"+_mtc_ps+";"+_respons_ps+";"+_melec_ps+";"+_mair_ps+";"+_mwater_ps+";"+_mcwater_ps+";"+_mhwater_ps+";"+_rowater_ps+";"+_mgas_ps;
//				        System.out.println("SELECT WORKED: "+total_rez);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2494!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt17.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs17.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
					return total_rez_ps;
				}
//*****************************************************************************************************************************				
				//�������� begin_date � end_date ��� ������� ���-�� ������� � ���������� ������� hmmr_pm_year
				//SELECT p.id, p.PM_Cycle, ps.begin_date, ps.end_date from hmmr_pm p INNER JOIN hmmr_pm_cycle ps ON ps.Type = p.PM_Cycle where p.id = 3426
				@SuppressWarnings("static-access")
				public ObservableList<String> _select_for_pmplan(String group)
				{
					ObservableList<String> list = FXCollections.observableArrayList();
					try {
						String query = "select ps.Pereodic, hgc.PM_StartDate, ps.end_date, hps.FL03_Shop_s, p.PM_Resp from hmmr_pm p INNER JOIN hmmr_group_cycle hgc ON hgc.PM_Group = p.PM_Group INNER JOIN hmmr_pm_cycle ps ON ps.Type = hgc.PM_Cycle INNER JOIN hmmr_plant_structure hps ON hps.id = p.Eq_ID where hps.Status = 0 AND p.PM_Group = "+"'"+group+"'"+";";
						
						cn.ConToDb();
						stmt9 = cn.con.createStatement();
						rs9 = stmt9.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs9.next()) {
				        	pereodic_pmplan = rs9.getString(1);
				        	dbegin = rs9.getString(2);
				            dend = rs9.getString(3);
				            shop_pmplan = rs9.getString(4);
				            otv_plan = rs9.getString(5);
				            				            
				            total_rez_data = pereodic_pmplan+","+dbegin+","+dend+","+shop_pmplan+","+otv_plan;
				            list.add(total_rez_data);
				            
				        }
				        //total_rez_data = pereodic_pmplan+","+dbegin+","+dend+","+shop_pmplan+","+otv_plan;

					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2725!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return list;
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!				
				//�������� ����� ������ � action plan
				@SuppressWarnings("static-access")
				public String _select_apnum(String id)
				{
					try {
						String query = "select ap_num from hmmr_work_recording where id = "+"'"+id+"'"+";";
						
						cn.ConToDb();
						stmt9 = cn.con.createStatement();
						rs9 = stmt9.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs9.next()) {
				        	ap_num_plan = rs9.getString(1);
				        }
				        total_apnum_rez = ap_num_plan;

					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2593!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return total_apnum_rez;
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
				
////////////////////////////////////////////////////////////////////ACTION PLAN////////////////////////////////////////////////////////////////////////				
				//�������� userid � action plan
				@SuppressWarnings("static-access")
				public String _select_userid(String id)
				{
					String userid = "null";
					try {
						String query = "select user_id from hmmr_action_plan where id = "+"'"+id+"'"+";";
						
						cn.ConToDb();
						stmt9 = cn.con.createStatement();
						rs9 = stmt9.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs9.next()) {
				        	userid = rs9.getString(1);
				        }
				        

					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2622!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return userid;
				}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
				//�������� userid � �� id ����������
				@SuppressWarnings("static-access")
				public String _select_userid_(String ID)
				{
					String userid = "null";
					try {
						String query = "select user_id from hmmr_mu_staff where ID = "+"'"+ID+"'"+";";
						
						cn.ConToDb();
						stmt9 = cn.con.createStatement();
						rs9 = stmt9.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs9.next()) {
				        	userid = rs9.getString(1);
				        }
				        

					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2651!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return userid;
				}
/////////////////////////////////////////////////////////////////////////ACTION PLAN/////////////////////////////////////////////////////////////////////////				
				//�������� ����������� ��� �������������� �� ������ � action plan
				@SuppressWarnings("static-access")
				public String _select_oft(String id)
				{
					String userid = "null";
					try {
						String query = "select Otv_For_Task from hmmr_action_plan where id = "+"'"+id+"'"+";";
						
						cn.ConToDb();
						stmt9 = cn.con.createStatement();
						rs9 = stmt9.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs9.next()) {
				        	userid = rs9.getString(1);
				        }
				        

					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2680!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return userid;
				}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
///////////////////////////////////////////////////////////////ACTION PLAN//////////////////////////////////////////////////////////////////////////////////
				//�������� ����������� ��� ������� ������ � action plan
				@SuppressWarnings("static-access")
				public String _select_tm(String id)
				{
					String tm = "null";
					try {
						String query = "select Tsk_maker from hmmr_action_plan where id = "+"'"+id+"'"+";";
						
						cn.ConToDb();
						stmt9 = cn.con.createStatement();
						rs9 = stmt9.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs9.next()) {
				        	tm = rs9.getString(1);
				        }
				        

					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2709!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return tm;
				}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
				
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK_RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!				
				//��������� ������ ������ � WR
				@SuppressWarnings("static-access")
				public String _select_status(String id)
				{
					String status = "null";
					try {
						String query = "select WR_Executor_Confirmed from hmmr_work_recording where id = "+"'"+id+"'"+";";
						
						cn.ConToDb();
						stmt9 = cn.con.createStatement();
						rs9 = stmt9.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs9.next()) {
				        	status = rs9.getString(1);
				        }
				        

					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2738!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return status;
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
				
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//��������� ��� �� ������ ��� ����� ������ �� �� ������������
				@SuppressWarnings("static-access")
				public String _select_confirm(String id)
				{
					try {
						String query = "select WR_Host_Confirmed from hmmr_work_recording where ap_num = "+"'"+id+"'"+" AND WR_Host_Confirmed = 0;";
						
						cn.ConToDb();
						stmt9 = cn.con.createStatement();
						rs9 = stmt9.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs9.next()) {
				        	qty_chk = rs9.getString(1);
				        }
				       
				        if(qty_chk.equals("0"))
				        	total_qtychk_rez = "NO";
				        else
				        	total_qtychk_rez = "YES";
				        
				        qty_chk = "null";
				     }
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2766!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return total_qtychk_rez;
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//��������� ���������� Work_time ��� ����� ������, ���� ��������, �� ���� ��������� ��� ����� �����-�� ������ �� ���� ������ WT ������� �� ����
				//��� ��� �� ���� �� ��� ������ ������ ��� CM
				@SuppressWarnings("static-access")
				public String _select_confirm_wt(String id)
				{
					try {
						String query = "select CM_Work_Time from hmmr_work_recording where ap_num = "+"'"+id+"'"+" AND CM_Work_Time <> 0;";
						
						cn.ConToDb();
						stmt9 = cn.con.createStatement();
						rs9 = stmt9.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs9.next()) {
				        	wt_chk = rs9.getString(1);
				        }
				       
				        if(!wt_chk.equals("0") && wt_chk != null && !wt_chk.equals("null"))
				        	total_wtchk_rez = "YES";
				        else
				        	total_wtchk_rez = "NO";
				        
				        wt_chk = "null";
				     }
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2800!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return total_wtchk_rez;
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!				
				//������ id ��������� ����������� ������
				@SuppressWarnings("static-access")
				public String _select_last_id(String table_name)
				{
					try {
						String query = "SELECT id FROM "+table_name+" ORDER BY id DESC LIMIT 1;";
						
						cn.ConToDb();
						stmt9 = cn.con.createStatement();
						rs9 = stmt9.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs9.next()) {
				        	lst_id = rs9.getString(1);
				        }
				     }
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2875!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return lst_id;
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!				
				//������ �������� �� ���� RESP2,RESP3,RESP4
				@SuppressWarnings("static-access")
				public String _select_resp(String id, String resp)
				{
					try {
						String query = "SELECT "+resp+" FROM hmmr_work_recording where id = "+"'"+id+"'"+";";
						
						cn.ConToDb();
						stmt9 = cn.con.createStatement();
						rs9 = stmt9.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs9.next()) {
				        	lst_id = rs9.getString(1);
				        }
				     }
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2859!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return lst_id;
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
				
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//����� �������� �� ������� ��� ���� ����
				@SuppressWarnings("static-access")
				public String _select_b_hours(String id, String hours)
				{
					try {
						String query = "SELECT "+hours+" FROM hmmr_work_recording where id = "+"'"+id+"'"+";";
						
						cn.ConToDb();
						stmt9 = cn.con.createStatement();
						rs9 = stmt9.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs9.next()) {
				        	lst_id = rs9.getString(1);
				        }
				     }
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2885!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return lst_id;
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
				
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//��������� ���� �� ���-�� ����� ����� �������, ���� ��, �� �������� ��� ������ � ������������ ����� ������ ������� �������
				@SuppressWarnings("static-access")
				public String _select_confirm_wt_data(String id)
				{
					try {
						String query = "select CM_Date_Begin, CM_Date_End, CM_Time_Begin, CM_Time_End from hmmr_work_recording where ap_num = "+"'"+id+"'"+" AND CM_Work_Time <> 0;";
						
						cn.ConToDb();
						stmt9 = cn.con.createStatement();
						rs9 = stmt9.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs9.next()) {
				        	a_date = rs9.getString(1);
				        	a_date1 = rs9.getString(2);
				        	a_hours1 = rs9.getString(3);
				        	a_hours2 = rs9.getString(4);
				        }
				       
				        if(!a_date.equals("0") && a_date != null && !a_date.equals("null"))
				        	total_wtchk_rez = a_date+","+a_date1+","+a_hours1+","+a_hours2;
				        else
				        	total_wtchk_rez = "NO";
				        
				        wt_chk = "null";
				     }
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2911!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return total_wtchk_rez;
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!		
		//��� ����� ���������� � ������� PM_Instruction ��������� ��������� 3 ��������� ����: ������ ����������, ���-�� ���������, ���-�� ������
		// !!!!!!!!!!!!!!!!!!!! ���� �� �����!! ��������!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 		
/*				@SuppressWarnings("static-access")
				public String _select_for_addform_inst(String type)
				{
					try {
						String query = "select Pereodic,Weeks,Hours from hmmr_pm_cycle where Type = "+"'"+type+"'"+";";
						
						cn.ConToDb();
						stmt9 = cn.con.createStatement();
						rs9 = stmt9.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs9.next()) {
				        	pereodic_inst = rs9.getString(1);
				            weeks_inst = rs9.getString(2);
				            hours_inst = rs9.getString(3);
				        }
				        total_rez_inst = pereodic_inst+","+weeks_inst+","+hours_inst;

					}
					catch (SQLException sqlEx) {
						sqlEx.printStackTrace();
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return total_rez_inst;
				}*/
	//��������� ������ � PM_CYCLE
	@SuppressWarnings("static-access")
	public void _update_rec_cycle(String type, String pereodic, String hours, String bdate, String edate, String id)
	{
		String query = "UPDATE hmmr_pm_cycle SET Type = "+"'"+type+"'"+", Pereodic = "+"'"+pereodic+"'"+", Hours = "+"'"+hours+"'"+", begin_date = "+"'"+bdate+"'"+", end_date = "+"'"+edate+"'"+" WHERE id = "+"'"+id+"'"+";";
		
		try {
			cn.ConToDb();
			stmt = cn.con.createStatement();
			stmt.executeUpdate(query);
			//log.log(Level.INFO, "STATUS RING WAS UPDATED");
		} catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2976!");
		}
    	finally {
            //close connection ,stmt and resultset here
        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
	}
	
	//��������� ������� Pereodic � PM_CYCLE � ������������ � ����������� ����� � ��� ��� ������������� ��� 
		@SuppressWarnings("static-access")
		public void _update_rec_cycle(String rez, String hours)
		{
			String query = "UPDATE hmmr_pm_cycle SET Pereodic = "+"'"+rez+"'"+" WHERE Hours = "+"'"+hours+"'"+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 2998!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		
		//��������� ������� chk_sel � PM_CYCLE ����� ������ ��� ��������� ������� ���������, ����� ����������� �� ������� �������� 
		@SuppressWarnings("static-access")
		public void _update_rbtn(String rbtn)
		{
			String query = "UPDATE hmmr_pm_cycle SET chk_sel = "+"'"+rbtn+"'"+" WHERE id = 1;";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3020!");
			}
		   	finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
	
	//��������� ������ � hmmr_pm_year, ���� ������ �������� � Action Plan
		@SuppressWarnings("static-access")
		public void _update_hpy_record(String id, String type)
		{
			String query = "UPDATE hmmr_pm_year SET record_del = "+"'"+type+"'"+" WHERE id = "+"'"+id+"'"+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3042!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!		
		//��������� ������ � hmmr_work_recording, ���� ������ ���������� ������
				@SuppressWarnings("static-access")
				public void _update_qty_wr(String val, String id)
				{
					String query = "UPDATE hmmr_work_recording SET WR_Host_Confirmed = "+"'"+val+"'"+",WR_Executor_Confirmed = 'Done' WHERE id = "+"'"+id+"'"+";";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3064!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//��������� ������ � hmmr_work_recording, ���� ������ ���������� ������������� �� ������
				@SuppressWarnings("static-access")
				public void _update_oft_wr(String val, String id)
				{
					String query = "UPDATE hmmr_work_recording SET WR_Resp_Confirmed = "+"'"+val+"'"+",WR_Executor_Confirmed = 'Done' WHERE id = "+"'"+id+"'"+";";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3086!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				
/////////////////////////////////////////////////////////////////ACTION PLAN/////////////////////////////////////////////////////////////////////////////////////				
				//��������� ������ � hmmr_action_plan, ���� ������ ���������� ������������� �� ������ ��� ���� �����������
				@SuppressWarnings("static-access")
				public void _update_otv_ap(String id, String field, String val)
				{
					String query = "UPDATE hmmr_action_plan SET "+field+" = "+"'"+val+"'"+" WHERE id = "+"'"+id+"'"+";";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3108!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
//////////////////////////////////////////////////////////////////////ACTION PLAN////////////////////////////////////////////////////////////////////////////////				
				//��������� ������ � hmmr_action_plan, � ����� ������� ������ ������ ����������� ���� ��� ������ � WR �� ������ ������������
				@SuppressWarnings("static-access")
				public void _update_delrec_ap(String id)
				{
					String query = "UPDATE hmmr_action_plan SET flag_otv = 2, flag_oft = 1 WHERE id = "+"'"+id+"'"+";";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3130!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
				
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!				
				//��������� ������ � hmmr_work_recording, ���� � ���� ������ �� �������� ��������� ������������
				@SuppressWarnings("static-access")
				public void _update_r_wr(String id, String field, String val)
				{
					String query = "UPDATE hmmr_work_recording SET "+field+" = "+"'"+val+"'"+" WHERE id = "+"'"+id+"'"+";";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3152!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
				
///////////////////////////////////////////////////////////////////ACTION PLAN/////////////////////////////////////////////////////////////////////////////////				
				//������� ������ � hmmr_action_plan, ���� ��� ����� ������������
				@SuppressWarnings("static-access")
				public void _update_deleterec_ap(String id)
				{
					String query = "UPDATE hmmr_action_plan SET del_rec = 1 WHERE id = "+"'"+id+"'"+";";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3174!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	@SuppressWarnings("static-access")
	//�������� ������ � ������� ����������� Type_PM
	public void _update_rec_tpm(String name, String desc, String type, String icon, String id)
	{
		String query = "UPDATE hmmr_activity_type SET Name = "+"'"+name+"'"+", Description = "+"'"+desc+"'"+", ID_OT = "+"'"+type+"'"+", Icon = "+"'"+icon+"'"+" WHERE id = "+"'"+id+"'"+";";
		
		try {
			cn.ConToDb();
			stmt = cn.con.createStatement();
			stmt.executeUpdate(query);
			//log.log(Level.INFO, "STATUS RING WAS UPDATED");
		} catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3395!");
		}
    	finally {
            //close connection ,stmt and resultset here
        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
	}
	
	//�������� ������ � ������� PM Instruction
		@SuppressWarnings("static-access")
		public void _update_rec_inst(String id, String ninst_inst, LocalDate date_create, LocalDate date_change, String inst_pdf, String ver_inst, String mt_inst, String pmname_inst, String sdoc_txt_inst, String qtyspec_inst, String ptw_inst,
                String wt_inst, String adm2_inst, String adm3_inst, String of1_inst, String of2_inst, String typepm_inst, String cyclepm1_inst, String cyclepm2_inst, 
                String pos_inst, String line_inst, String power_inst, String sinfo_inst)
		{
			String query = "UPDATE pm_inst SET num_instruction = "+"'"+ninst_inst+"'"+",Creation_date = "+"'"+date_create+"'"+",Last_edition_date = "+"'"+date_change+"'"+",Link_instruction_PDF = "+"'"+inst_pdf+"'"+",Version = "+"'"+ver_inst+"'"+",Model_Type_Task = "+"'"+mt_inst+"'"+",PM_name = "+"'"+pmname_inst+"'"+",Type_PM = "+"'"+typepm_inst+"'"+",PM_Cycle1 = "+"'"+cyclepm1_inst+"'"+",PM_Cycle2 = "+"'"+cyclepm2_inst+"'"+",ON_Line_OFF_Line = "+"'"+line_inst+"'"+",Power_ON_Power_OFF = "+"'"+power_inst+"'"+",Position = "+"'"+pos_inst+"'"+",Src_Info = "+"'"+sinfo_inst+"'"+",Src_Doc = "+"'"+sdoc_txt_inst+"'"+",Qty_Specialist = "+"'"+qtyspec_inst+"'"+",Prep_Work_Time = "+"'"+ptw_inst+"'"+",Work_Time = "+"'"+wt_inst+"'"+",Admission_2 = "+"'"+adm2_inst+"'"+",Admission_3 = "+"'"+adm3_inst+"'"+",Outfit_1 = "+"'"+of1_inst+"'"+",Outfit_2 = "+"'"+of2_inst+"'"+" where id = "+"'"+id+"'"+";"; //"UPDATE pm_inst SET Type_PM = "+"'"+type+"'"+", Description = "+"'"+desc+"'"+" WHERE id = "+"'"+id+"'"+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3220!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		
		//�������� ������ � ������� PM
				@SuppressWarnings("static-access")
				public void _update_rec_pm(String id, String num_instruction, String Eq_ID, String Group_EQ, String Otv, String OnOff_Line, String pm_exec)
				{
					String query = "UPDATE hmmr_pm SET Instruction_num = "+"'"+num_instruction+"'"+",Eq_ID = "+"'"+Eq_ID+"'"+",PM_Group = "+"'"+Group_EQ+"'"+",PM_Resp = "+"'"+Otv+"'"+",OnOff_Line = "+"'"+OnOff_Line+"'"+" ,PM_Executor = "+"'"+pm_exec+"'"+" where id = "+"'"+id+"'"+";"; //"UPDATE pm_inst SET Type_PM = "+"'"+type+"'"+", Description = "+"'"+desc+"'"+" WHERE id = "+"'"+id+"'"+";";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3416!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
//////////////////////////////////////////////////////////////////////ACTION PLAN/////////////////////////////////////////////////////////////////////////////////				
				//�������� ������ � ������� AP
				@SuppressWarnings("static-access")
				public void _update_rec_ap(String id, String PM_Num, String Type, String Description, LocalDate Due_Date, String Equipment, String Otv_For_Task, String Otv, String shop, String icon, String icon_at)
				{
					String query = "UPDATE hmmr_action_plan SET PM_Num = "+"'"+PM_Num+"'"+",Type = "+"'"+Type+"'"+",Description = "+"'"+Description+"'"+",Due_Date = "+"'"+Due_Date+"'"+",Equipment = "+"'"+Equipment+"'"+",Otv_For_Task = "+"'"+Otv_For_Task+"'"+",Otv = "+"'"+Otv+"'"+",shop = "+"'"+shop+"'"+",Icon = "+"'"+icon+"'"+",Icon_AT = "+"'"+icon_at+"'"+" where id = "+"'"+id+"'"+";"; //"UPDATE pm_inst SET Type_PM = "+"'"+type+"'"+", Description = "+"'"+desc+"'"+" WHERE id = "+"'"+id+"'"+";";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3465!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
				
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!				
				//�������� ������ � ������� WR
				@SuppressWarnings("static-access")
				public void _update_rec_wr(String id, String ap_num, String WSH, String Group_EQ, String Line, String Station, String Equip, String Equipment, String Record_Type, String Resp1,String Resp2,String Resp3,String Resp4, String Status, String Shift_Report, String Required_Action, LocalDate Actual_Date,LocalDate Actual_Date_2,LocalDate Actual_Date_3,LocalDate Actual_Date_4, String Actual_Time, LocalDate Actual_Date1,LocalDate Actual_Date2,LocalDate Actual_Date3,LocalDate Actual_Date4, String Actual_Time1,String Actual_Time2,String Actual_Time3,String Actual_Time4, LocalTime Hours1,LocalTime Hours1_2,LocalTime Hours1_3,LocalTime Hours1_4, LocalTime Hours2,LocalTime Hours2_2,LocalTime Hours2_3,LocalTime Hours2_4, String Activity_Type, String  _Resp4, String _Resp5, String _Resp6, String _Resp7, String _Resp8, LocalDate _Actual_Date_5, LocalDate _Actual_Date_6, LocalDate _Actual_Date_7, LocalDate _Actual_Date_8, LocalDate _Actual_Date_9, LocalDate _Actual_Date5, LocalDate _Actual_Date6, LocalDate _Actual_Date7, LocalDate _Actual_Date8, LocalDate _Actual_Date9, String _Actual_Time5, String _Actual_Time6, String _Actual_Time7, String _Actual_Time8, String _Actual_Time9, LocalTime _Hours1_5, LocalTime _Hours1_6, LocalTime _Hours1_7, LocalTime _Hours1_8, LocalTime _Hours1_9, LocalTime _Hours2_5, LocalTime _Hours2_6, LocalTime _Hours2_7, LocalTime _Hours2_8, LocalTime _Hours2_9)
				{
					String query = "UPDATE hmmr_work_recording SET ap_num = "+"'"+ap_num+"'"+",user_number = "+"'"+conn_connector.USER_ID+"'"+",FL_WSH = "+"'"+WSH+"'"+",FL_Group = "+"'"+Group_EQ+"'"+",FL_Line = "+"'"+Line+"'"+",FL_Station = "+"'"+Station+"'"+",FL_Equipment = "+"'"+Equip+"'"+",Equipment_Full = "+"'"+Equipment+"'"+",Record_Type = "+"'"+Record_Type+"'"+",Task_Resp_ID = "+"'"+Resp1+"'"+",_Resp2 = "+"'"+Resp2+"'"+",_Resp3 = "+"'"+Resp3+"'"+",_Resp4 = "+"'"+Resp4+"'"+",WR_Executor_Confirmed = "+"'"+Status+"'"+",Task_Description = "+"'"+Shift_Report+"'"+",Task_Report = "+"'"+Required_Action+"'"+",WR_Begin_Date = "+"'"+Actual_Date+"'"+",_Actual_Date_2 = "+"'"+Actual_Date_2+"'"+",_Actual_Date_3 = "+"'"+Actual_Date_3+"'"+",_Actual_Date_4 = "+"'"+Actual_Date_4+"'"+",CM_DownTime = "+"'"+Actual_Time+"'"+",WR_End_Date = "+"'"+Actual_Date1+"'"+",_Actual_Date2 = "+"'"+Actual_Date2+"'"+",_Actual_Date3 = "+"'"+Actual_Date3+"'"+",_Actual_Date4 = "+"'"+Actual_Date4+"'"+",WR_Work_Time = "+"'"+Actual_Time1+"'"+",_Actual_Time2 = "+"'"+Actual_Time2+"'"+",_Actual_Time3 = "+"'"+Actual_Time3+"'"+",_Actual_Time4 = "+"'"+Actual_Time4+"'"+",WR_Work_Time_Begin = "+"'"+Hours1+"'"+",_Hours1_2 = "+"'"+Hours1_2+"'"+",_Hours1_3 = "+"'"+Hours1_3+"'"+",_Hours1_4 = "+"'"+Hours1_4+"'"+",WR_Work_Time_End = "+"'"+Hours2+"'"+",_Hours2_2 = "+"'"+Hours2_2+"'"+",_Hours2_3 = "+"'"+Hours2_3+"'"+",_Hours2_4 = "+"'"+Hours2_4+"'"+",Activity_Type = "+"'"+Activity_Type+"'"+",_Resp4 = "+"'"+_Resp4+"'"+",_Resp5 = "+"'"+_Resp5+"'"+",_Resp6 = "+"'"+_Resp6+"'"+",_Resp7 = "+"'"+_Resp7+"'"+",_Resp8 = "+"'"+_Resp8+"'"+",_Actual_Date_5 = "+"'"+_Actual_Date_5+"'"+",_Actual_Date_6 = "+"'"+_Actual_Date_6+"'"+",_Actual_Date_7 = "+"'"+_Actual_Date_7+"'"+",_Actual_Date_8 = "+"'"+_Actual_Date_8+"'"+",_Actual_Date_9 = "+"'"+_Actual_Date_9+"'"+",_Actual_Date5 = "+"'"+_Actual_Date5+"'"+",_Actual_Date6 = "+"'"+_Actual_Date6+"'"+",_Actual_Date7 = "+"'"+_Actual_Date7+"'"+",_Actual_Date8 = "+"'"+_Actual_Date8+"'"+",_Actual_Date9 = "+"'"+_Actual_Date9+"'"+",_Actual_Time5 = "+"'"+_Actual_Time5+"'"+",_Actual_Time6 = "+"'"+_Actual_Time6+"'"+",_Actual_Time7 = "+"'"+_Actual_Time7+"'"+",_Actual_Time8 = "+"'"+_Actual_Time8+"'"+",_Actual_Time9 = "+"'"+_Actual_Time9+"'"+",_Hours1_5 = "+"'"+_Hours1_5+"'"+",_Hours1_6 = "+"'"+_Hours1_6+"'"+",_Hours1_7 = "+"'"+_Hours1_7+"'"+",_Hours1_8 = "+"'"+_Hours1_8+"'"+",_Hours1_9 = "+"'"+_Hours1_9+"'"+",_Hours2_5 = "+"'"+_Hours2_5+"'"+",_Hours2_6 = "+"'"+_Hours2_6+"'"+",_Hours2_7 = "+"'"+_Hours2_7+"'"+",_Hours2_8 = "+"'"+_Hours2_8+"'"+",_Hours2_9 = "+"'"+_Hours2_9+"'"+" where id = "+"'"+id+"'"+";"; //"UPDATE pm_inst SET Type_PM = "+"'"+type+"'"+", Description = "+"'"+desc+"'"+" WHERE id = "+"'"+id+"'"+";";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3288!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!				
				//�������� ������ � ������� PM Plan
				@SuppressWarnings("static-access")
				public void _update_rec_pmplan(String id, LocalDate Date, String oft) //, String pm_startdate, String pm_duration
				{
					String query = "UPDATE hmmr_pm_year SET data = "+"'"+Date+"'"+",OFT = "+"'"+oft+"'"+" where id = "+"'"+id+"'"+";"; //,PM_StartDate = "+"'"+pm_startdate+"'"+",PM_Duration = "+"'"+pm_duration+"'"+"
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3310!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
//****************************************************** PLANT STRUCTURE ********************************************************				
				//�������� ������ � ������� PS
				@SuppressWarnings("static-access")
				public void _update_rec_ps(String id, String FL01_Company, String FL02_Plant, String FL03_Shop_s,
						String FL04_Group_s, String FL05_Line_s, String FL06_Station_s, String FL07_Equipment_s, 
						String FL03_Shop_ENG, String FL04_Group_ENG, String FL05_Line_ENG, String FL06_Station_ENG, 
						String FL07_Equipment_ENG, String FL03_Shop_RUS, String FL04_Group_RUS, String FL05_Line_RUS, 
						String FL06_Station_RUS, String FL07_Equipment_RUS, String Description_RUS, String Equip_label, 
						String Station_Label, String Equipment_Folder_Link, String Resp_Planner_Group, 
						String Assets_Inventory_Number, String Assets_OS1_Number, LocalDate Assets_Start_up_Date, 
						String Cost_Center, String Site_Location, String Site_CHAMBER, String Site_Coordinates, 
						String Site_Altitude, String TR_CU, String TR_CU_Link, String Hazardous, String Key_equipment, 
						String EQ_Integrator, String EQ_Manufacture, String EQ_Type, String EQ_Serial_Number, 
						String EQ_Technical_Characteristic, String Responsobility, String M_Electric, String M_Air, 
						String M_Water, String M_Cold_Water, String M_Hot_Water, String M_RO_Water, String M_Gas)
				{
					
					String query = "UPDATE hmmr_plant_structure SET user_id = "+"'"+conn_connector.USER_ID+"'"+",FL01_Company = "+"'"+FL01_Company+"'"+
					",FL02_Plant = "+"'"+FL02_Plant+"'"+",FL03_Shop_s = "+"'"+FL03_Shop_s+"'"+",FL04_Group_s = "+"'"+FL04_Group_s+"'"+",FL05_Line_s = "+"'"+FL05_Line_s+"'"+
					",FL06_Station_s = "+"'"+FL06_Station_s+"'"+",FL07_Equipment_s = "+"'"+FL07_Equipment_s+"'"+",FL03_Shop_ENG = "+"'"+FL03_Shop_ENG+"'"+",FL04_Group_ENG = "+"'"+FL04_Group_ENG+"'"+",FL05_Line_ENG = "+"'"+FL05_Line_ENG+"'"+
					",FL06_Station_ENG = "+"'"+FL06_Station_ENG+"'"+",FL07_Equipment_ENG = "+"'"+FL07_Equipment_ENG+"'"+",FL03_Shop_RUS = "+"'"+FL03_Shop_RUS+"'"+",FL04_Group_RUS = "+"'"+FL04_Group_RUS+"'"+",FL05_Line_RUS = "+"'"+FL05_Line_RUS+"'"+",FL06_Station_RUS = "+"'"+FL06_Station_RUS+"'"+",FL07_Equipment_RUS = "+"'"+FL07_Equipment_RUS+"'"+
					",Description_RUS = "+"'"+Description_RUS+"'"+",Equip_label = "+"'"+Equip_label+"'"+
					",Station_Label = "+"'"+Station_Label+"'"+",Equipment_Folder_Link = "+"'"+Equipment_Folder_Link+"'"+",Resp_Planner_Group = "+"'"+Resp_Planner_Group+"'"+",Assets_Inventory_Number = "+"'"+Assets_Inventory_Number+"'"+",Assets_OS1_Number = "+"'"+Assets_OS1_Number+"'"+",Assets_Start_up_Date = "+"'"+Assets_Start_up_Date+"'"+",Cost_Center = "+"'"+Cost_Center+"'"+
					",Site_Location = "+"'"+Site_Location+"'"+",Site_CHAMBER = "+"'"+Site_CHAMBER+"'"+",Site_Coordinates = "+"'"+Site_Coordinates+"'"+",Site_Altitude = "+"'"+Site_Altitude+"'"+
					",TR_CU = "+"'"+TR_CU+"'"+",TR_CU_Link = "+"'"+TR_CU_Link+"'"+",Hazardous = "+"'"+Hazardous+"'"+
					",Key_equipment = "+"'"+Key_equipment+"'"+",EQ_Integrator = "+"'"+EQ_Integrator+"'"+",EQ_Manufacture = "+"'"+EQ_Manufacture+"'"+",EQ_Type = "+"'"+EQ_Type+"'"+",EQ_Serial_Number = "+"'"+EQ_Serial_Number+"'"+",EQ_Technical_Characteristic = "+"'"+EQ_Technical_Characteristic+"'"+
					",Responsobility = "+"'"+Responsobility+"'"+",M_Electric = "+"'"+M_Electric+"'"+",M_Air = "+"'"+M_Air+"'"+",M_Water = "+"'"+M_Water+"'"+
					",M_Cold_Water = "+"'"+M_Cold_Water+"'"+",M_Hot_Water = "+"'"+M_Hot_Water+"'"+",M_RO_Water = "+"'"+M_RO_Water+"'"+",M_Gas = "+"'"+M_Gas+"'"+" where id = "+"'"+id+"'"+";"; //"UPDATE pm_inst SET Type_PM = "+"'"+type+"'"+", Description = "+"'"+desc+"'"+" WHERE id = "+"'"+id+"'"+";";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3337!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
//*******************************************************************************************************************************
				
//********************************************** PLANT STRUCTURE ****************************************************************				
				//�������� ������ � ������� PS, ���� ������ �������� ������
				@SuppressWarnings("static-access")
				public void _update_rec_ps_del(String Id)
				{
					String query = "UPDATE hmmr_plant_structure SET Status = 1 where id = "+"'"+Id+"'"+";"; //"UPDATE pm_inst SET Type_PM = "+"'"+type+"'"+", Description = "+"'"+desc+"'"+" WHERE id = "+"'"+id+"'"+";";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3359!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
//*******************************************************************************************************************************				
				//�������� ������ � ������� PM, ���� ������ �������� ������
				@SuppressWarnings("static-access")
				public void _update_rec_pm_del(String Id)
				{
					String query = "UPDATE hmmr_pm SET del_rec = 1 where id = "+"'"+Id+"'"+";"; //"UPDATE pm_inst SET Type_PM = "+"'"+type+"'"+", Description = "+"'"+desc+"'"+" WHERE id = "+"'"+id+"'"+";";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3381");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
				
				//�������� ������ � ������� PM_INST, ���� ������ �������� ������
				@SuppressWarnings("static-access")
				public void _update_rec_pminst_del(String Id)
				{
					String query = "UPDATE pm_inst SET del_rec = 1 where id = "+"'"+Id+"'"+";"; //"UPDATE pm_inst SET Type_PM = "+"'"+type+"'"+", Description = "+"'"+desc+"'"+" WHERE id = "+"'"+id+"'"+";";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3403!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
	
				//�������� ������ � ������� PM_Cycle, ���� ������ �������� ������
				@SuppressWarnings("static-access")
				public void _update_rec_pmcycle_del(String Id)
				{
					String query = "UPDATE hmmr_pm_cycle SET del_rec = 1 where id = "+"'"+Id+"'"+";"; //"UPDATE pm_inst SET Type_PM = "+"'"+type+"'"+", Description = "+"'"+desc+"'"+" WHERE id = "+"'"+id+"'"+";";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3425!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
				
				//�������� ������ � ������� Type_PM, ���� ������ �������� ������
				@SuppressWarnings("static-access")
				public void _update_rec_typepm_del(String Id)
				{
					String query = "UPDATE hmmr_activity_type SET del_rec = 1 where id = "+"'"+Id+"'"+";"; //"UPDATE pm_inst SET Type_PM = "+"'"+type+"'"+", Description = "+"'"+desc+"'"+" WHERE id = "+"'"+id+"'"+";";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3447!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
				
	@SuppressWarnings("static-access")
	public void _delete_rec_cycle(String id)
	{
		try {
			String query = "delete from hmmr_pm_cycle where id = "+"'"+id+"'"+";";
			
			cn.ConToDb();
			stmt = cn.con.createStatement();
			stmt.executeUpdate(query);
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3469!");
        } finally {
            //close connection ,stmt and resultset here
        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
	}
	
	/*@SuppressWarnings("static-access")
	public void _delete_rec_tpm(String id)
	{
		try {
			String query = "delete from hmmr_type_pm where id = "+"'"+id+"'"+";";
			
			cn.ConToDb();
			stmt = cn.con.createStatement();
			stmt.executeUpdate(query);
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3489!");
        } finally {
            //close connection ,stmt and resultset here
        	try { cn.con.close(); } catch(SQLException se) { }
            try { stmt.close(); } catch(SQLException se) {  }
            try { rs.close(); } catch(SQLException se) {  }
        }
	}*/
	//������� ������ �� ������� PM Instruction
	@SuppressWarnings("static-access")
	public void _delete_rec_inst(String id)
	{
		try {
			String query = "delete from pm_inst where id = "+"'"+id+"'"+";";
			
			cn.ConToDb();
			stmt = cn.con.createStatement();
			stmt.executeUpdate(query);
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3509!");
        } finally {
            //close connection ,stmt and resultset here
        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
	}
	
	//������� ������ �� ������� PM
		@SuppressWarnings("static-access")
		public void _delete_rec_pm(String id)
		{
			try {
				String query = "delete from hmmr_pm where id = "+"'"+id+"'"+";";
				
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3530!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		/**
		 *��������� ������� �������, ��� ����������� ��������� 
		 * @return - ���������� ����� ����������� ������
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<hmmr_prior_model> _select_data_prior()
		{
			ObservableList<hmmr_prior_model> list = FXCollections.observableArrayList();
			
			try {
				String query = "select ID_TSK, Name_Prior, Description, Icon, id from hmmr_mu_prior where del_rec = 0;";
				
				cn.ConToDb();
				stmt19 = cn.con.createStatement();
				rs19 = stmt19.executeQuery(query);
							
		        while (rs19.next()) {
		        	hmmr_prior_model hpm = new hmmr_prior_model();
		        	if(rs19.getString(1) != null && rs19.getString(2) != null && rs19.getString(3) != null) {
		        		hpm.ID_TSK.set(rs19.getString(1));
		        		hpm.Name_Prior.set(rs19.getString(2));
		        		hpm.Description.set(rs19.getString(3));
		        		hpm.Icon.set(rs19.getString(4));
		        		hpm.Id.set(rs19.getString(5));
		        				            
			            list.add(hpm);
		        	}    
		        }

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3558!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt19.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs19.close(); } catch(SQLException se) { /*can't do anything */ }
	        }

			return list;
		}
		/**
		 * ��������� ������ � �� ��� ������� ����������� �����������
		 */
		@SuppressWarnings("static-access")
		public void _insert_prior(String id_prior, String name, String desc, String icon)
		{
			String query = "INSERT INTO hmmr_mu_prior (ID_TSK, Name_Prior, Description, Icon) VALUES ("+"'"+id_prior+"'"+","+ "'"+name+"'"+","+ "'"+desc+"'"+","+ "'"+icon+"'"+");";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3594!");
			}
		   	finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
		/**
		 * ������ ��������� ��� ������ ���� ���������� ����������� �����������,
		 * ����� ���� ��������� ��� ���� � ���� ����������
		 * @param id - id ������ ��� ������� ���������� ���� ����������
		 * @return - ���������� ����� ������
		 */
		@SuppressWarnings("static-access")
		public String _select_for_update_prior(String id)
		{
			try {
				String query = "select ID_TSK, Name_Prior, Description, Icon from hmmr_mu_prior where id = "+"'"+id+"'"+";";
				
				cn.ConToDb();
				stmt10 = cn.con.createStatement();
				rs10 = stmt10.executeQuery(query);
				
		        while (rs10.next()) {
		            id_prior = rs10.getString(1);
		            name_prior = rs10.getString(2);
		            desc_prior = rs10.getString(3);
		            icon_prior = rs10.getString(4);
		       	}
		        total_rez_prior = id_prior+","+name_prior+","+desc_prior+","+icon_prior;

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3623!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt10.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs10.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return total_rez_prior;
		}
		/**
		 * ������ ������ ������ � �� � ������� hmmr_mu_prior
		 * @param Id - id ������ ������� ��������
		 */
		@SuppressWarnings("static-access")
		public void _update_rec_prior(String Id, String id_prior, String name_prior, String desc_prior, String icon_prior)
		{
			String query = "UPDATE hmmr_mu_prior SET ID_TSK = "+"'"+id_prior+"'"+", Name_Prior = "+"'"+name_prior+"'"+", Description = "+"'"+desc_prior+"'"+", Icon = "+"'"+icon_prior+"'"+" where id = "+"'"+Id+"'"+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3656!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		/**
		 * ������� ������ �� ������� hmmr_mu_prior, ����� ������� �������� ���� del_rec
		 * � 0 �� 1
		 * @param id - id ������, ������� �������
		 */
		@SuppressWarnings("static-access")
		public void _delete_rec_prior(String id)
		{
			try {
				String query = "update hmmr_mu_prior set del_rec = 1 where id = "+"'"+id+"'"+";";
				
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3678!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
		/**
		 * �������� ������� �������� ������ �� hmmr_action_plan, ���� del_rec = 1 - ������ ������� �
		 * ������ ������ ���������. ����� ����� ����������� ������ � ������� ���� ��� ����������� �����.
		 * @param id - id ������, ������� ��������� �� ������� ��������
		 * @return - ���������� ����� ������
		 */
		@SuppressWarnings("static-access")
		public String _select_for_exec_task(int id)
		{
			try {
				String query = "select del_rec from hmmr_action_plan where id = "+id+";";
				
				cn.ConToDb();
				stmt10 = cn.con.createStatement();
				rs10 = stmt10.executeQuery(query);
				
		        while (rs10.next()) {
		        	if(rs10.getString(1) != null)
		        		del_rec = rs10.getString(1);
		        }
		    }
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3754!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt10.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs10.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return del_rec;
		}
		/**
		 * ��������� ���������� ComboBox ���������
		 * @return - ���������� ���������� ����� ������
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<String> _select_prior()
		{
			ObservableList<String> list = FXCollections.observableArrayList();
			
			try {
				String query = "select ID_TSK, Name_Prior from hmmr_mu_prior where del_rec = 0 group by ID_TSK;";
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	if(rs6.getString(1) != null) {
		        		String tpm = rs6.getString(1) + " - " + rs6.getString(2);			        					            
			            list.add(tpm);
		        	}    
		        }

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3786");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
		       }

			return list;
		}	
		/**
		 * �������� ���� � �������� ����������
		 * @param id - ID_TSK ����������
		 * @return - ���������� ���������� ����� ������
		 */
		@SuppressWarnings({ "static-access"})
		public String _select_prior_img(String id)
		{
			String list = "null";
			
			try {
				String query = "select Icon from hmmr_mu_prior where ID_TSK = "+"'"+id+"'"+";";
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	if(rs6.getString(1) != null) {
		        		list = rs6.getString(1);			        					            
			        }    
		        }

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3827");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
		       }

			return list;
		}	
		/**
		 * �������� ������ �������� � �������� ����������, ����� ������� ��� � ���� ���������
		 * ��� ��������� ����� �� ������ ���������� � ������� Action Plan
		 * @param id - id � AP ����� �������� ��� ����������, � �� ���� � ������ ��������
		 * @return - ���������� ���������� ����� ������
		 */
		@SuppressWarnings({ "static-access"})
		public String _select_prior_desc(String id)
		{
			String list = "null";
			
			try {
				String query = "select hmp.Description from hmmr_action_plan hap INNER JOIN hmmr_mu_prior hmp ON hap.Icon = hmp.ID_TSK AND hap.id = "+"'"+id+"'"+";"; // AND ID_TSK = "+"'"+id+"'"+
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	if(rs6.getString(1) != null) {
		        		list = rs6.getString(1);			        					            
			        }    
		        }

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 4027");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
		       }

			return list;
		}
//******************************************************** PLANT STRUCTURE *******************************************************		
		/**
		 * ��������� ������� ������� ��� ����������� ������� ��� �
		 * Plant Structure
		 * @return - ���������� ����� ������ ���� ObservableList � 
		 * 			��������� ��� ������� TableView.
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<hmmr_ps_model> _select_data_filter_ps(String shop)
		{
			ObservableList<hmmr_ps_model> list = FXCollections.observableArrayList();
			
			try {
				//String query = "select id,Company,Plant,Shop_s,Group_PM,Line_Machine_s,Operation_Station_s,Equipment_s,Shop,Line_Machine,Line_Machine_RUS,Operation_Station,Operation_Station_RUS,Equipment,Description,Sub_Number,Equip_label,Station_Label,passport,manual,Station_Supplier,Location,Room_category,Coordinates,Altitude,CHAMBER,TR_CU,TR_CU_Link,Hazardous,Key_equipment,Type,S_N,Manuf,Main_Technical_Characteristic,Responsobility,M_Electric,M_Air,M_Water,M_Cold_water,M_Hot_water,RO_Water,M_Gas from hmmr_plant_structure where del_rec = 0 AND Shop_s = "+"'"+shop+"'"+";";
				String query = "select id, FL01_Company, FL02_Plant, FL03_Shop_s, FL04_Group_s, FL05_Line_s, FL06_Station_s, FL07_Equipment_s, FL03_Shop_ENG, FL04_Group_ENG, FL05_Line_ENG, FL06_Station_ENG, FL07_Equipment_ENG, FL03_Shop_RUS, FL04_Group_RUS, FL05_Line_RUS, FL06_Station_RUS, FL07_Equipment_RUS, Description_RUS, Equip_label, Station_Label, Equipment_Folder_Link, Resp_Planner_Group, Assets_Inventory_Number, Assets_OS1_Number, Assets_Start_up_Date, Cost_Center, Site_Location, Site_CHAMBER, Site_Coordinates, Site_Altitude, TR_CU, TR_CU_Link, Hazardous, Key_equipment, EQ_Integrator, EQ_Manufacture, EQ_Type, EQ_Serial_Number, EQ_Technical_Characteristic, Responsobility, M_Electric, M_Air, M_Water, M_Cold_Water, M_Hot_Water, M_RO_Water, M_Gas from hmmr_plant_structure where Status = 0 AND FL03_Shop_s = "+"'"+shop+"'"+";";
				
				cn.ConToDb();
				stmt12 = cn.con.createStatement();
				rs12 = stmt12.executeQuery(query);
							
		        while (rs12.next()) {
		        	hmmr_ps_model hpm = new hmmr_ps_model();
		        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
		        		hpm.Id.set(rs12.getString(1));
		        		hpm.Company.set(rs12.getString(2));
		        		hpm.Plant.set(rs12.getString(3));
		        		hpm.Shop_s.set(rs12.getString(4));
		        		hpm.Group_PM.set(rs12.getString(5));
		        		hpm.Line_Machine_s.set(rs12.getString(6));
		        		hpm.Operation_Station_s.set(rs12.getString(7));
		        		hpm.Equipment_s.set(rs12.getString(8));
		        		hpm.Shop.set(rs12.getString(9));
		        		hpm.FL04_Group_ENG.set(rs12.getString(10));
		        		hpm.Line_Machine.set(rs12.getString(11));
		        		hpm.Operation_Station.set(rs12.getString(12));
		        		hpm.Equipment.set(rs12.getString(13));
		        		hpm.FL03_Shop_RUS.set(rs12.getString(14));
		        		hpm.FL04_Group_RUS.set(rs12.getString(15));
		        		hpm.Line_Machine_RUS.set(rs12.getString(16));
		        		hpm.Operation_Station_RUS.set(rs12.getString(17));
		        		hpm.FL07_Equipment_RUSProperty().set(rs12.getString(18));
		        		hpm.Description.set(rs12.getString(19));
		        		hpm.Equip_label.set(rs12.getString(20));
		        		hpm.Station_label.set(rs12.getString(21));
		        		hpm.manual.set(rs12.getString(22));
		        		hpm.RespPlannerGroup.set(rs12.getString(23));
		        		hpm.AssetsInvNum.set(rs12.getString(24));
		        		hpm.AssetsOsNum.set(rs12.getString(25));
		        		hpm.AssetsStartDate.set(rs12.getString(26));
		        		hpm.CostCenter.set(rs12.getString(27));
		        		hpm.Location.set(rs12.getString(28));
		        		hpm.CHAMBER.set(rs12.getString(29));
		        		hpm.Coordinates.set(rs12.getString(30));
		        		hpm.Altitude.set(rs12.getString(31));
		        		hpm.TR_CU.set(rs12.getString(32));
		        		hpm.TR_CU_Link.set(rs12.getString(33));
		        		hpm.Hazardous.set(rs12.getString(34));
		        		hpm.Key_equipment.set(rs12.getString(35));
		        		hpm.Station_Supplier.set(rs12.getString(36));
		        		hpm.Manuf.set(rs12.getString(37));
		        		hpm.Type.set(rs12.getString(38));
		        		hpm.S_N.set(rs12.getString(39));
		        		hpm.MTC.set(rs12.getString(40));
		        		hpm.Resp.set(rs12.getString(41));
		        		hpm.M_Electric.set(rs12.getString(42));
		        		hpm.M_Air.set(rs12.getString(43));
		        		hpm.M_Water.set(rs12.getString(44));
		        		hpm.M_Cold_water.set(rs12.getString(45));
		        		hpm.M_Hot_water.set(rs12.getString(46));
		        		hpm.RO_Water.set(rs12.getString(47));
		        		hpm.M_Gas.set(rs12.getString(48));
		        						        				            
			            list.add(hpm);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3900!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
//*******************************************************************************************************************************
		
//*********************************************** PLANT STRUCTURE ***************************************************************		
		/**
		 * ��������� ������� ������� ��� ����������� ������� ��� � ������ �
		 * Plant Structure
		 * @return - ���������� ����� ������ ���� ObservableList � 
		 * 			��������� ��� ������� TableView.
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<hmmr_ps_model> _select_data_filter_ps(String shop, String eq_group)
		{
			ObservableList<hmmr_ps_model> list = FXCollections.observableArrayList();
			
			try {
				//String query = "select id,Company,Plant,Shop_s,Group_PM,Line_Machine_s,Operation_Station_s,Equipment_s,Shop,Line_Machine,Line_Machine_RUS,Operation_Station,Operation_Station_RUS,Equipment,Description,Sub_Number,Equip_label,Station_Label,passport,manual,Station_Supplier,Location,Room_category,Coordinates,Altitude,CHAMBER,TR_CU,TR_CU_Link,Hazardous,Key_equipment,Type,S_N,Manuf,Main_Technical_Characteristic,Responsobility,M_Electric,M_Air,M_Water,M_Cold_water,M_Hot_water,RO_Water,M_Gas from hmmr_plant_structure where del_rec = 0 AND Shop_s = "+"'"+shop+"'"+" AND Group_PM = "+"'"+eq_group+"'"+";";
				String query = "select id, FL01_Company, FL02_Plant, FL03_Shop_s, FL04_Group_s, FL05_Line_s, FL06_Station_s, FL07_Equipment_s, FL03_Shop_ENG, FL04_Group_ENG, FL05_Line_ENG, FL06_Station_ENG, FL07_Equipment_ENG, FL03_Shop_RUS, FL04_Group_RUS, FL05_Line_RUS, FL06_Station_RUS, FL07_Equipment_RUS, Description_RUS, Equip_label, Station_Label, Equipment_Folder_Link, Resp_Planner_Group, Assets_Inventory_Number, Assets_OS1_Number, Assets_Start_up_Date, Cost_Center, Site_Location, Site_CHAMBER, Site_Coordinates, Site_Altitude, TR_CU, TR_CU_Link, Hazardous, Key_equipment, EQ_Integrator, EQ_Manufacture, EQ_Type, EQ_Serial_Number, EQ_Technical_Characteristic, Responsobility, M_Electric, M_Air, M_Water, M_Cold_Water, M_Hot_Water, M_RO_Water, M_Gas from hmmr_plant_structure where Status = 0 AND FL03_Shop_s = "+"'"+shop+"'"+" AND FL04_Group_s = "+"'"+eq_group+"'"+";";
				
				cn.ConToDb();
				stmt12 = cn.con.createStatement();
				rs12 = stmt12.executeQuery(query);
							
		        while (rs12.next()) {
		        	hmmr_ps_model hpm = new hmmr_ps_model();
		        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
		        		hpm.Id.set(rs12.getString(1));
		        		hpm.Company.set(rs12.getString(2));
		        		hpm.Plant.set(rs12.getString(3));
		        		hpm.Shop_s.set(rs12.getString(4));
		        		hpm.Group_PM.set(rs12.getString(5));
		        		hpm.Line_Machine_s.set(rs12.getString(6));
		        		hpm.Operation_Station_s.set(rs12.getString(7));
		        		hpm.Equipment_s.set(rs12.getString(8));
		        		hpm.Shop.set(rs12.getString(9));
		        		hpm.FL04_Group_ENG.set(rs12.getString(10));
		        		hpm.Line_Machine.set(rs12.getString(11));
		        		hpm.Operation_Station.set(rs12.getString(12));
		        		hpm.Equipment.set(rs12.getString(13));
		        		hpm.FL03_Shop_RUS.set(rs12.getString(14));
		        		hpm.FL04_Group_RUS.set(rs12.getString(15));
		        		hpm.Line_Machine_RUS.set(rs12.getString(16));
		        		hpm.Operation_Station_RUS.set(rs12.getString(17));
		        		hpm.FL07_Equipment_RUSProperty().set(rs12.getString(18));
		        		hpm.Description.set(rs12.getString(19));
		        		hpm.Equip_label.set(rs12.getString(20));
		        		hpm.Station_label.set(rs12.getString(21));
		        		hpm.manual.set(rs12.getString(22));
		        		hpm.RespPlannerGroup.set(rs12.getString(23));
		        		hpm.AssetsInvNum.set(rs12.getString(24));
		        		hpm.AssetsOsNum.set(rs12.getString(25));
		        		hpm.AssetsStartDate.set(rs12.getString(26));
		        		hpm.CostCenter.set(rs12.getString(27));
		        		hpm.Location.set(rs12.getString(28));
		        		hpm.CHAMBER.set(rs12.getString(29));
		        		hpm.Coordinates.set(rs12.getString(30));
		        		hpm.Altitude.set(rs12.getString(31));
		        		hpm.TR_CU.set(rs12.getString(32));
		        		hpm.TR_CU_Link.set(rs12.getString(33));
		        		hpm.Hazardous.set(rs12.getString(34));
		        		hpm.Key_equipment.set(rs12.getString(35));
		        		hpm.Station_Supplier.set(rs12.getString(36));
		        		hpm.Manuf.set(rs12.getString(37));
		        		hpm.Type.set(rs12.getString(38));
		        		hpm.S_N.set(rs12.getString(39));
		        		hpm.MTC.set(rs12.getString(40));
		        		hpm.Resp.set(rs12.getString(41));
		        		hpm.M_Electric.set(rs12.getString(42));
		        		hpm.M_Air.set(rs12.getString(43));
		        		hpm.M_Water.set(rs12.getString(44));
		        		hpm.M_Cold_water.set(rs12.getString(45));
		        		hpm.M_Hot_water.set(rs12.getString(46));
		        		hpm.RO_Water.set(rs12.getString(47));
		        		hpm.M_Gas.set(rs12.getString(48));
		        						        				            
			            list.add(hpm);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 3978!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
//*******************************************************************************************************************************
		
//***************************************** PLANT STRUCTURE *********************************************************************		
		/**
		 * ��������� ������� ������� ��� ����������� ������� ���, ������ � ����� �
		 * Plant Structure
		 * @return - ���������� ����� ������ ���� ObservableList � 
		 * 			��������� ��� ������� TableView.
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<hmmr_ps_model> _select_data_filter_ps(String shop, String eq_group, String line)
		{
			ObservableList<hmmr_ps_model> list = FXCollections.observableArrayList();
			
			try {
				//String query = "select id,Company,Plant,Shop_s,Group_PM,Line_Machine_s,Operation_Station_s,Equipment_s,Shop,Line_Machine,Line_Machine_RUS,Operation_Station,Operation_Station_RUS,Equipment,Description,Sub_Number,Equip_label,Station_Label,passport,manual,Station_Supplier,Location,Room_category,Coordinates,Altitude,CHAMBER,TR_CU,TR_CU_Link,Hazardous,Key_equipment,Type,S_N,Manuf,Main_Technical_Characteristic,Responsobility,M_Electric,M_Air,M_Water,M_Cold_water,M_Hot_water,RO_Water,M_Gas from hmmr_plant_structure where del_rec = 0 AND Shop_s = "+"'"+shop+"'"+" AND Group_PM = "+"'"+eq_group+"'"+" AND Line_Machine_s = "+"'"+line+"'"+";";
				String query = "select id, FL01_Company, FL02_Plant, FL03_Shop_s, FL04_Group_s, FL05_Line_s, FL06_Station_s, FL07_Equipment_s, FL03_Shop_ENG, FL04_Group_ENG, FL05_Line_ENG, FL06_Station_ENG, FL07_Equipment_ENG, FL03_Shop_RUS, FL04_Group_RUS, FL05_Line_RUS, FL06_Station_RUS, FL07_Equipment_RUS, Description_RUS, Equip_label, Station_Label, Equipment_Folder_Link, Resp_Planner_Group, Assets_Inventory_Number, Assets_OS1_Number, Assets_Start_up_Date, Cost_Center, Site_Location, Site_CHAMBER, Site_Coordinates, Site_Altitude, TR_CU, TR_CU_Link, Hazardous, Key_equipment, EQ_Integrator, EQ_Manufacture, EQ_Type, EQ_Serial_Number, EQ_Technical_Characteristic, Responsobility, M_Electric, M_Air, M_Water, M_Cold_Water, M_Hot_Water, M_RO_Water, M_Gas from hmmr_plant_structure where Status = 0 AND FL03_Shop_s = "+"'"+shop+"'"+" AND FL04_Group_s = "+"'"+eq_group+"'"+" AND FL05_Line_s = "+"'"+line+"'"+";";
				
				cn.ConToDb();
				stmt12 = cn.con.createStatement();
				rs12 = stmt12.executeQuery(query);
							
		        while (rs12.next()) {
		        	hmmr_ps_model hpm = new hmmr_ps_model();
		        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
		        		hpm.Id.set(rs12.getString(1));
		        		hpm.Company.set(rs12.getString(2));
		        		hpm.Plant.set(rs12.getString(3));
		        		hpm.Shop_s.set(rs12.getString(4));
		        		hpm.Group_PM.set(rs12.getString(5));
		        		hpm.Line_Machine_s.set(rs12.getString(6));
		        		hpm.Operation_Station_s.set(rs12.getString(7));
		        		hpm.Equipment_s.set(rs12.getString(8));
		        		hpm.Shop.set(rs12.getString(9));
		        		hpm.FL04_Group_ENG.set(rs12.getString(10));
		        		hpm.Line_Machine.set(rs12.getString(11));
		        		hpm.Operation_Station.set(rs12.getString(12));
		        		hpm.Equipment.set(rs12.getString(13));
		        		hpm.FL03_Shop_RUS.set(rs12.getString(14));
		        		hpm.FL04_Group_RUS.set(rs12.getString(15));
		        		hpm.Line_Machine_RUS.set(rs12.getString(16));
		        		hpm.Operation_Station_RUS.set(rs12.getString(17));
		        		hpm.FL07_Equipment_RUSProperty().set(rs12.getString(18));
		        		hpm.Description.set(rs12.getString(19));
		        		hpm.Equip_label.set(rs12.getString(20));
		        		hpm.Station_label.set(rs12.getString(21));
		        		hpm.manual.set(rs12.getString(22));
		        		hpm.RespPlannerGroup.set(rs12.getString(23));
		        		hpm.AssetsInvNum.set(rs12.getString(24));
		        		hpm.AssetsOsNum.set(rs12.getString(25));
		        		hpm.AssetsStartDate.set(rs12.getString(26));
		        		hpm.CostCenter.set(rs12.getString(27));
		        		hpm.Location.set(rs12.getString(28));
		        		hpm.CHAMBER.set(rs12.getString(29));
		        		hpm.Coordinates.set(rs12.getString(30));
		        		hpm.Altitude.set(rs12.getString(31));
		        		hpm.TR_CU.set(rs12.getString(32));
		        		hpm.TR_CU_Link.set(rs12.getString(33));
		        		hpm.Hazardous.set(rs12.getString(34));
		        		hpm.Key_equipment.set(rs12.getString(35));
		        		hpm.Station_Supplier.set(rs12.getString(36));
		        		hpm.Manuf.set(rs12.getString(37));
		        		hpm.Type.set(rs12.getString(38));
		        		hpm.S_N.set(rs12.getString(39));
		        		hpm.MTC.set(rs12.getString(40));
		        		hpm.Resp.set(rs12.getString(41));
		        		hpm.M_Electric.set(rs12.getString(42));
		        		hpm.M_Air.set(rs12.getString(43));
		        		hpm.M_Water.set(rs12.getString(44));
		        		hpm.M_Cold_water.set(rs12.getString(45));
		        		hpm.M_Hot_water.set(rs12.getString(46));
		        		hpm.RO_Water.set(rs12.getString(47));
		        		hpm.M_Gas.set(rs12.getString(48));
		        						        				            
			            list.add(hpm);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 4056!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
//*******************************************************************************************************************************
		
//********************************************** PLANT STRUCTURE ****************************************************************		
		/**
		 * ��������� ������� ������� ��� ����������� ������� ���, ������, ����� � ������� �
		 * Plant Structure
		 * @return - ���������� ����� ������ ���� ObservableList � 
		 * 			��������� ��� ������� TableView.
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<hmmr_ps_model> _select_data_filter_ps(String shop, String eq_group, String line, String os)
		{
			ObservableList<hmmr_ps_model> list = FXCollections.observableArrayList();
			
			try {
				//String query = "select id,Company,Plant,Shop_s,Group_PM,Line_Machine_s,Operation_Station_s,Equipment_s,Shop,Line_Machine,Line_Machine_RUS,Operation_Station,Operation_Station_RUS,Equipment,Description,Sub_Number,Equip_label,Station_Label,passport,manual,Station_Supplier,Location,Room_category,Coordinates,Altitude,CHAMBER,TR_CU,TR_CU_Link,Hazardous,Key_equipment,Type,S_N,Manuf,Main_Technical_Characteristic,Responsobility,M_Electric,M_Air,M_Water,M_Cold_water,M_Hot_water,RO_Water,M_Gas from hmmr_plant_structure where del_rec = 0 AND Shop_s = "+"'"+shop+"'"+" AND Group_PM = "+"'"+eq_group+"'"+" AND Line_Machine_s = "+"'"+line+"'"+" AND Operation_Station_s = "+"'"+os+"'"+";";
				String query = "select id, FL01_Company, FL02_Plant, FL03_Shop_s, FL04_Group_s, FL05_Line_s, FL06_Station_s, FL07_Equipment_s, FL03_Shop_ENG, FL04_Group_ENG, FL05_Line_ENG, FL06_Station_ENG, FL07_Equipment_ENG, FL03_Shop_RUS, FL04_Group_RUS, FL05_Line_RUS, FL06_Station_RUS, FL07_Equipment_RUS, Description_RUS, Equip_label, Station_Label, Equipment_Folder_Link, Resp_Planner_Group, Assets_Inventory_Number, Assets_OS1_Number, Assets_Start_up_Date, Cost_Center, Site_Location, Site_CHAMBER, Site_Coordinates, Site_Altitude, TR_CU, TR_CU_Link, Hazardous, Key_equipment, EQ_Integrator, EQ_Manufacture, EQ_Type, EQ_Serial_Number, EQ_Technical_Characteristic, Responsobility, M_Electric, M_Air, M_Water, M_Cold_Water, M_Hot_Water, M_RO_Water, M_Gas from hmmr_plant_structure where Status = 0 AND FL03_Shop_s = "+"'"+shop+"'"+" AND FL04_Group_s = "+"'"+eq_group+"'"+" AND FL05_Line_s = "+"'"+line+"'"+" AND FL06_Station_s = "+"'"+os+"'"+";";
				
				cn.ConToDb();
				stmt12 = cn.con.createStatement();
				rs12 = stmt12.executeQuery(query);
							
		        while (rs12.next()) {
		        	hmmr_ps_model hpm = new hmmr_ps_model();
		        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
		        		hpm.Id.set(rs12.getString(1));
		        		hpm.Company.set(rs12.getString(2));
		        		hpm.Plant.set(rs12.getString(3));
		        		hpm.Shop_s.set(rs12.getString(4));
		        		hpm.Group_PM.set(rs12.getString(5));
		        		hpm.Line_Machine_s.set(rs12.getString(6));
		        		hpm.Operation_Station_s.set(rs12.getString(7));
		        		hpm.Equipment_s.set(rs12.getString(8));
		        		hpm.Shop.set(rs12.getString(9));
		        		hpm.FL04_Group_ENG.set(rs12.getString(10));
		        		hpm.Line_Machine.set(rs12.getString(11));
		        		hpm.Operation_Station.set(rs12.getString(12));
		        		hpm.Equipment.set(rs12.getString(13));
		        		hpm.FL03_Shop_RUS.set(rs12.getString(14));
		        		hpm.FL04_Group_RUS.set(rs12.getString(15));
		        		hpm.Line_Machine_RUS.set(rs12.getString(16));
		        		hpm.Operation_Station_RUS.set(rs12.getString(17));
		        		hpm.FL07_Equipment_RUSProperty().set(rs12.getString(18));
		        		hpm.Description.set(rs12.getString(19));
		        		hpm.Equip_label.set(rs12.getString(20));
		        		hpm.Station_label.set(rs12.getString(21));
		        		hpm.manual.set(rs12.getString(22));
		        		hpm.RespPlannerGroup.set(rs12.getString(23));
		        		hpm.AssetsInvNum.set(rs12.getString(24));
		        		hpm.AssetsOsNum.set(rs12.getString(25));
		        		hpm.AssetsStartDate.set(rs12.getString(26));
		        		hpm.CostCenter.set(rs12.getString(27));
		        		hpm.Location.set(rs12.getString(28));
		        		hpm.CHAMBER.set(rs12.getString(29));
		        		hpm.Coordinates.set(rs12.getString(30));
		        		hpm.Altitude.set(rs12.getString(31));
		        		hpm.TR_CU.set(rs12.getString(32));
		        		hpm.TR_CU_Link.set(rs12.getString(33));
		        		hpm.Hazardous.set(rs12.getString(34));
		        		hpm.Key_equipment.set(rs12.getString(35));
		        		hpm.Station_Supplier.set(rs12.getString(36));
		        		hpm.Manuf.set(rs12.getString(37));
		        		hpm.Type.set(rs12.getString(38));
		        		hpm.S_N.set(rs12.getString(39));
		        		hpm.MTC.set(rs12.getString(40));
		        		hpm.Resp.set(rs12.getString(41));
		        		hpm.M_Electric.set(rs12.getString(42));
		        		hpm.M_Air.set(rs12.getString(43));
		        		hpm.M_Water.set(rs12.getString(44));
		        		hpm.M_Cold_water.set(rs12.getString(45));
		        		hpm.M_Hot_water.set(rs12.getString(46));
		        		hpm.RO_Water.set(rs12.getString(47));
		        		hpm.M_Gas.set(rs12.getString(48));
		        						        				            
			            list.add(hpm);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 4134!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
//********************************************************************************************************************************
		
//********************************************** PLANT STRUCTURE *****************************************************************		
		/**
		 * ��������� ������� ������� ��� ����������� ������� ���, ������, �����, ������� � ������������ �
		 * Plant Structure
		 * @return - ���������� ����� ������ ���� ObservableList � 
		 * 			��������� ��� ������� TableView.
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<hmmr_ps_model> _select_data_filter_ps(String shop, String eq_group, String line, String os, String equip)
		{
			ObservableList<hmmr_ps_model> list = FXCollections.observableArrayList();
			
			try {
				//String query = "select id,Company,Plant,Shop_s,Group_PM,Line_Machine_s,Operation_Station_s,Equipment_s,Shop,Line_Machine,Line_Machine_RUS,Operation_Station,Operation_Station_RUS,Equipment,Description,Sub_Number,Equip_label,Station_Label,passport,manual,Station_Supplier,Location,Room_category,Coordinates,Altitude,CHAMBER,TR_CU,TR_CU_Link,Hazardous,Key_equipment,Type,S_N,Manuf,Main_Technical_Characteristic,Responsobility,M_Electric,M_Air,M_Water,M_Cold_water,M_Hot_water,RO_Water,M_Gas from hmmr_plant_structure where del_rec = 0 AND Shop_s = "+"'"+shop+"'"+" AND Group_PM = "+"'"+eq_group+"'"+" AND Line_Machine_s = "+"'"+line+"'"+" AND Operation_Station_s = "+"'"+os+"'"+" AND Equipment_s = "+"'"+equip+"'"+";";
				String query = "select id, FL01_Company, FL02_Plant, FL03_Shop_s, FL04_Group_s, FL05_Line_s, FL06_Station_s, FL07_Equipment_s, FL03_Shop_ENG, FL04_Group_ENG, FL05_Line_ENG, FL06_Station_ENG, FL07_Equipment_ENG, FL03_Shop_RUS, FL04_Group_RUS, FL05_Line_RUS, FL06_Station_RUS, FL07_Equipment_RUS, Description_RUS, Equip_label, Station_Label, Equipment_Folder_Link, Resp_Planner_Group, Assets_Inventory_Number, Assets_OS1_Number, Assets_Start_up_Date, Cost_Center, Site_Location, Site_CHAMBER, Site_Coordinates, Site_Altitude, TR_CU, TR_CU_Link, Hazardous, Key_equipment, EQ_Integrator, EQ_Manufacture, EQ_Type, EQ_Serial_Number, EQ_Technical_Characteristic, Responsobility, M_Electric, M_Air, M_Water, M_Cold_Water, M_Hot_Water, M_RO_Water, M_Gas from hmmr_plant_structure where Status = 0 AND FL03_Shop_s = "+"'"+shop+"'"+" AND FL04_Group_s = "+"'"+eq_group+"'"+" AND FL05_Line_s = "+"'"+line+"'"+" AND FL06_Station_s = "+"'"+os+"'"+" AND FL07_Equipment_s = "+"'"+equip+"'"+";";
				
				cn.ConToDb();
				stmt12 = cn.con.createStatement();
				rs12 = stmt12.executeQuery(query);
							
		        while (rs12.next()) {
		        	hmmr_ps_model hpm = new hmmr_ps_model();
		        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
		        		hpm.Id.set(rs12.getString(1));
		        		hpm.Company.set(rs12.getString(2));
		        		hpm.Plant.set(rs12.getString(3));
		        		hpm.Shop_s.set(rs12.getString(4));
		        		hpm.Group_PM.set(rs12.getString(5));
		        		hpm.Line_Machine_s.set(rs12.getString(6));
		        		hpm.Operation_Station_s.set(rs12.getString(7));
		        		hpm.Equipment_s.set(rs12.getString(8));
		        		hpm.Shop.set(rs12.getString(9));
		        		hpm.FL04_Group_ENG.set(rs12.getString(10));
		        		hpm.Line_Machine.set(rs12.getString(11));
		        		hpm.Operation_Station.set(rs12.getString(12));
		        		hpm.Equipment.set(rs12.getString(13));
		        		hpm.FL03_Shop_RUS.set(rs12.getString(14));
		        		hpm.FL04_Group_RUS.set(rs12.getString(15));
		        		hpm.Line_Machine_RUS.set(rs12.getString(16));
		        		hpm.Operation_Station_RUS.set(rs12.getString(17));
		        		hpm.FL07_Equipment_RUSProperty().set(rs12.getString(18));
		        		hpm.Description.set(rs12.getString(19));
		        		hpm.Equip_label.set(rs12.getString(20));
		        		hpm.Station_label.set(rs12.getString(21));
		        		hpm.manual.set(rs12.getString(22));
		        		hpm.RespPlannerGroup.set(rs12.getString(23));
		        		hpm.AssetsInvNum.set(rs12.getString(24));
		        		hpm.AssetsOsNum.set(rs12.getString(25));
		        		hpm.AssetsStartDate.set(rs12.getString(26));
		        		hpm.CostCenter.set(rs12.getString(27));
		        		hpm.Location.set(rs12.getString(28));
		        		hpm.CHAMBER.set(rs12.getString(29));
		        		hpm.Coordinates.set(rs12.getString(30));
		        		hpm.Altitude.set(rs12.getString(31));
		        		hpm.TR_CU.set(rs12.getString(32));
		        		hpm.TR_CU_Link.set(rs12.getString(33));
		        		hpm.Hazardous.set(rs12.getString(34));
		        		hpm.Key_equipment.set(rs12.getString(35));
		        		hpm.Station_Supplier.set(rs12.getString(36));
		        		hpm.Manuf.set(rs12.getString(37));
		        		hpm.Type.set(rs12.getString(38));
		        		hpm.S_N.set(rs12.getString(39));
		        		hpm.MTC.set(rs12.getString(40));
		        		hpm.Resp.set(rs12.getString(41));
		        		hpm.M_Electric.set(rs12.getString(42));
		        		hpm.M_Air.set(rs12.getString(43));
		        		hpm.M_Water.set(rs12.getString(44));
		        		hpm.M_Cold_water.set(rs12.getString(45));
		        		hpm.M_Hot_water.set(rs12.getString(46));
		        		hpm.RO_Water.set(rs12.getString(47));
		        		hpm.M_Gas.set(rs12.getString(48));
		        						        				            
			            list.add(hpm);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 4212!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
//******************************************************************************************************************************
		
		//********************************************** PLANT STRUCTURE *****************************************************************		
				/**
				 * �������� id �� Plant Structure
				 * @return - ���������� id
				 */
				@SuppressWarnings({ "static-access"})
				public String _select_data_filter_ps_id(String shop, String eq_group, String line, String os, String equip)
				{
					String list = "null";
					
					try {
						String query = "select id from hmmr_plant_structure where Status = 0 AND FL03_Shop_s = "+"'"+shop+"'"+" AND FL04_Group_s = "+"'"+eq_group+"'"+" AND FL05_Line_s = "+"'"+line+"'"+" AND FL06_Station_s = "+"'"+os+"'"+" AND FL07_Equipment_s = "+"'"+equip+"'"+";";
						
						cn.ConToDb();
						stmt12 = cn.con.createStatement();
						rs12 = stmt12.executeQuery(query);
									
				        while (rs12.next()) {
				        	if(rs12.getString(1) != null) {
				        		list = rs12.getString(1);
				        	}    
				        }
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 4479!");
				       } finally {
				           //close connection ,stmt and resultset here
				       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
				        try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
				        try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
				       }
					return list;
				}
		//******************************************************************************************************************************		
		
//***************************************************** PLANT STRUCTURE ********************************************************		
		/**
		 * ��������� ������ � ������� Plant Structure
		 * @param id - id ������ ������� ���� ��������������
		 */
		@SuppressWarnings("static-access")
		public void _insert_last_ps(int id)
		{
			String query = "INSERT INTO hmmr_plant_structure (user_id,FL01_Company, FL02_Plant, FL03_Shop_s, FL04_Group_s, FL05_Line_s, FL06_Station_s, FL07_Equipment_s, "
					+ "FL03_Shop_ENG, FL04_Group_ENG, FL05_Line_ENG, FL06_Station_ENG, FL07_Equipment_ENG, FL03_Shop_RUS, FL04_Group_RUS, FL05_Line_RUS, FL06_Station_RUS, "
					+ "FL07_Equipment_RUS, Description_RUS, Equip_label, Station_Label, Equipment_Folder_Link, Resp_Planner_Group, Assets_Inventory_Number, Assets_OS1_Number, "
					+ "Assets_Start_up_Date, Cost_Center, Site_Location, Site_CHAMBER, Site_Coordinates, Site_Altitude, TR_CU, TR_CU_Link, Hazardous, Key_equipment, EQ_Integrator, "
					+ "EQ_Manufacture, EQ_Type, EQ_Serial_Number, EQ_Technical_Characteristic, Responsobility, M_Electric, M_Air, M_Water, M_Cold_Water, M_Hot_Water, M_RO_Water, "
					+ "M_Gas) "
					+ "SELECT user_id,FL01_Company, FL02_Plant, FL03_Shop_s, FL04_Group_s, FL05_Line_s, FL06_Station_s, FL07_Equipment_s, FL03_Shop_ENG, FL04_Group_ENG, "
					+ "FL05_Line_ENG, FL06_Station_ENG, FL07_Equipment_ENG, FL03_Shop_RUS, FL04_Group_RUS, FL05_Line_RUS, FL06_Station_RUS, FL07_Equipment_RUS, Description_RUS, "
					+ "Equip_label, Station_Label, Equipment_Folder_Link, Resp_Planner_Group, Assets_Inventory_Number, Assets_OS1_Number, Assets_Start_up_Date, Cost_Center, "
					+ "Site_Location, Site_CHAMBER, Site_Coordinates, Site_Altitude, TR_CU, TR_CU_Link, Hazardous, Key_equipment, EQ_Integrator, EQ_Manufacture, EQ_Type, "
					+ "EQ_Serial_Number, EQ_Technical_Characteristic, Responsobility, M_Electric, M_Air, M_Water, M_Cold_Water, M_Hot_Water, M_RO_Water, "
					+ "M_Gas FROM hmmr_plant_structure WHERE id = "+id+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 4285!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
//********************************************************************************************************************************		
		/**
		 * �������� id ���������� ������ ��� �������
		 * @param id - ������� id ��� ������ �������
		 * @param table - ������� ��� ������� ���������� �������� ���������� id
		 * @return - ���������� id ���������� ������
		 */
		@SuppressWarnings({ "static-access"})
		public String _select_data_prew(String id, String table)
		{
			String list = "null";
			
			try {
				String query = "SELECT id FROM "+table+" WHERE Status = 0 AND id < "+"'"+id+"'"+" ORDER BY id DESC LIMIT 1;";
				
				cn.ConToDb();
				stmt19 = cn.con.createStatement();
				rs19 = stmt19.executeQuery(query);
							
		        while (rs19.next()) {
		        	
		        	if(rs19.getString(1) != null) {
		        		         
			            list = rs19.getString(1);
		        	}    
		        }

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 4316!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt19.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs19.close(); } catch(SQLException se) { /*can't do anything */ }
	        }

			return list;
		}
		/**
		 * �������� id ��������� ������ ��� �������
		 * @param id - ������� id ��� ������ �������
		 * @param table - ������� ��� ������� ���������� �������� ��������� id
		 * @return - ���������� id ��������� ������
		 */
		@SuppressWarnings({ "static-access"})
		public String _select_data_next(String id, String table)
		{
			String list = "null";
			
			try {
				String query = "SELECT id FROM "+table+" WHERE Status = 0 AND id > "+"'"+id+"'"+" ORDER BY id LIMIT 1;";
				
				cn.ConToDb();
				stmt19 = cn.con.createStatement();
				rs19 = stmt19.executeQuery(query);
							
		        while (rs19.next()) {
		        	
		        	if(rs19.getString(1) != null) {
		        		         
			            list = rs19.getString(1);
		        	}    
		        }

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 4360!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt19.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs19.close(); } catch(SQLException se) { /*can't do anything */ }
	        }

			return list;
		}
		/**
		 * �������� ����������� �������� ���.������.�����.�������.������������
		 * @param id - id ������ � PS
		 * @param tbl_name - ��� �������
		 * @return - ���������� ������
		 */
		@SuppressWarnings({ "static-access"})
		public String _select_fillpm_equip(String id, String tbl_name)
		{
			String list = "null";
			
			try {
				String query = "select concat(hps.FL03_Shop_s,'.',hps.FL04_Group_s,'.',hps.FL05_Line_s,'.',hps.FL06_Station_s,'.',hps.FL07_Equipment_s) from "+tbl_name+" hpm INNER JOIN hmmr_plant_structure hps ON del_rec = 0 AND hps.id = "+"'"+id+"'"+";";
				
				cn.ConToDb();
				stmt11 = cn.con.createStatement();
				rs11 = stmt11.executeQuery(query);
							
		        while (rs11.next()) {
		        	if(rs11.getString(1) != null) {
		        		list = rs11.getString(1);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 4589!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt11.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs11.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
		//�������� ����� ������, ����� ������ ��������� ��� ��� � PM PLAN ��� ���� ����������
		/**
		 * ��������� ����� ������, ����� ������ ��������� ��� ��� � PM PLAN ��� ���� �� ���� ��������
		 * @param group - ����� ������, ������� ��������� �� ������� � PM PLAN
		 * @return - ���������� null - ���� ���, ���� �����, ���� ��� ����
		 */
		@SuppressWarnings("static-access")
		public String _select_for_pmgroup(String group)
		{
			try {
				String query = "select PM_Group from hmmr_pm_year where PM_Group = "+"'"+group+"'"+" AND record_del = 0;";
				
				cn.ConToDb();
				stmt15 = cn.con.createStatement();
				rs15 = stmt15.executeQuery(query);
				//log.log(Level.INFO, "CHANNEL WAS FOUND");
		        while (rs15.next()) {
		        	pm_group = rs15.getString(1);
		        }
		        total_rez_group = pm_group;
		        if(total_rez_group == null)
		        	total_rez_group = "0";
//		        System.out.println("SELECT WORKED: "+total_rez);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 4683!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt15.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs15.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return total_rez_group;
		}
		/**
		 * �������� ������ ��� ���������� ����� ������� Group-Cycle
		 * @return - ���������� ����� ������
		 */
		@SuppressWarnings("static-access")
		public ObservableList<hmmr_groupcycle_model> _select_for_gc()
		{
			ObservableList<hmmr_groupcycle_model> list = FXCollections.observableArrayList();
			try {
				String query = "select id, PM_Group, PM_Cycle, PM_StartDate, PM_Duration, Date_Beforehand from hmmr_group_cycle where del_rec = 0;";
				
				cn.ConToDb();
				stmt15 = cn.con.createStatement();
				rs15 = stmt15.executeQuery(query);
				//log.log(Level.INFO, "CHANNEL WAS FOUND");
		        while (rs15.next()) {
		        	hmmr_groupcycle_model hgm = new hmmr_groupcycle_model();
		        	hgm.Id.set(rs15.getString(1));
		        	hgm.group_pm.set(rs15.getString(2));
		        	hgm.cycle_pm.set(rs15.getString(3));
		        	hgm.pm_startdate.set(rs15.getString(4));
		        	hgm.pm_duration.set(rs15.getString(5));
		        	hgm.days.set(rs15.getString(6));
		        	list.add(hgm);
		        }
		    }
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 4704!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt15.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs15.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return list;
		}
		/**
		 * ��������� �������� � �� � ������� hmmr_group_cycle
		 * @param pm_group - ������ ���
		 * @param pm_cycle - ������������� ���������� ���
		 * @param date_start - ���� ������ ���
		 * @param duration - ����������������� ���
		 */
		@SuppressWarnings("static-access")
		public void _insert_gc(String pm_group, String pm_cycle, LocalDate date_start, String duration, String days_gc)
		{
			String query = "INSERT INTO hmmr_group_cycle (PM_Group, PM_Cycle, PM_StartDate, PM_Duration, Date_Beforehand) "
					+ "VALUES ("+"'"+pm_group+"'"+","+"'"+pm_cycle+"'"+","+"'"+date_start+"'"+","+"'"+duration+"'"+","+"'"+days_gc+"'"+");";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				s_class._AlertDialog("������ � ������� "+pm_group+" ��� ���������� � �����������!", "������ � ������ � 4749!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		/**
		 * �������� ������ ��� ���������� ����� ������� ������� Group-Cycle
		 * @return - ���������� ����� ������
		 */
		@SuppressWarnings("static-access")
		public String _select_for_gc_str(int id)
		{
			String list = "null";
			try {
				String query = "select PM_Group, PM_Cycle, Date_Beforehand, PM_StartDate, PM_Duration from hmmr_group_cycle where del_rec = 0 and id = "+id+";";
				
				cn.ConToDb();
				stmt15 = cn.con.createStatement();
				rs15 = stmt15.executeQuery(query);
				//log.log(Level.INFO, "CHANNEL WAS FOUND");
		        while (rs15.next()) {
		        	list = rs15.getString(1) + " - " + rs15.getString(2) + " - " + rs15.getString(3) + " - " + rs15.getString(4) + " - " + rs15.getString(5);
		        }
		    }
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 4704!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt15.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs15.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return list;
		}
		/**
		 * ��������� ������ � �� ��� ������� hmmr_group_cycle
		 * @param id - id ������
		 * @param pm_group - ����� PM ������
		 * @param pm_cycle - ������ ���������� ��� 
		 */
		@SuppressWarnings("static-access")
		public void _update_for_gc(int id, String pm_group, String pm_cycle, String days_gc, LocalDate start_date, String duration)
		{
			try {
				String query = "update hmmr_group_cycle set PM_Group = "+"'"+pm_group+"'"+", PM_Cycle = "+"'"+pm_cycle+"'"+", Date_Beforehand = "+"'"+days_gc+"'"+", PM_StartDate = "+"'"+start_date+"'"+", PM_Duration = "+"'"+duration+"'"+" where id = "+id+";";
				
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 4807!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
		/**
		 * ������� ������ � �� �� ������� hmmr_group_cycle
		 * @param id - id ������ ������� �������
		 */
		@SuppressWarnings("static-access")
		public void _delete_for_gc(String id)
		{
			try {
				String query = "delete from hmmr_group_cycle where id = "+"'"+id+"'"+";";
				
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 4818!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
		@SuppressWarnings({ "static-access"})
		public ObservableList<String> _select_pm_group(String num_inst)
		{
			ObservableList<String> list = FXCollections.observableArrayList();
			
			try {
				String query = "SELECT hgc.PM_Group FROM hmmr_pm pm, pm_inst pi INNER JOIN hmmr_group_cycle hgc ON hgc.PM_Cycle = pi.PM_Cycle1 WHERE pi.num_instruction = "+"'"+num_inst+"'"+" GROUP BY hgc.PM_Group;";
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	if(rs6.getString(1) != null) {
		        		String tpm = rs6.getString(1);			        					            
			            list.add(tpm);
		        	}    
		        }

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 4839!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
		       }

			return list;
		}
		
		/**
		 *��������� ������� ������� ��, ��� ����������� order type 
		 * @return - ���������� ����� ����������� ������
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<Hmmr_OrderType_Model> _select_data_ot()
		{
			ObservableList<Hmmr_OrderType_Model> list = FXCollections.observableArrayList();
			
			try {
				String query = "select id, Name, Description from hmmr_order_type where del_rec = 0;";
				
				cn.ConToDb();
				stmt19 = cn.con.createStatement();
				rs19 = stmt19.executeQuery(query);
							
		        while (rs19.next()) {
		        	Hmmr_OrderType_Model hot = new Hmmr_OrderType_Model();
		        	if(rs19.getString(1) != null && rs19.getString(2) != null && rs19.getString(3) != null) {
		        		hot.Id.set(rs19.getString(1));
		        		hot.Name.set(rs19.getString(2));
		        		hot.Desc.set(rs19.getString(3));
		        				        				            
			            list.add(hot);
		        	}    
		        }

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 4874!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt19.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs19.close(); } catch(SQLException se) { /*can't do anything */ }
	        }

			return list;
		}
		/**
		 * ������ ��������� ��� ������ ���� ���������� ����������� order type,
		 * ����� ���� ��������� ��� ���� � ���� ����������
		 * @param id - id ������ ��� ������� ���������� ���� ����������
		 * @return - ���������� ����� ������
		 */
		@SuppressWarnings("static-access")
		public String _select_for_update_ot(String id)
		{
			try {
				String query = "select Name, Description from hmmr_order_type where id = "+"'"+id+"'"+";";
				
				cn.ConToDb();
				stmt10 = cn.con.createStatement();
				rs10 = stmt10.executeQuery(query);
				
		        while (rs10.next()) {
		            name_ot = rs10.getString(1);
		            desc_ot = rs10.getString(2);
		        }
		        total_rez_ot = name_ot+","+desc_ot;

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 4914!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt10.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs10.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return total_rez_ot;
		}
		/**
		 * ������� ������ �� ������� hmmr_order_type, ����� ������� �������� ���� del_rec
		 * � 0 �� 1
		 * @param id - id ������, ������� �������
		 */
		@SuppressWarnings("static-access")
		public void _delete_rec_ot(String id)
		{
			try {
				String query = "update hmmr_order_type set del_rec = 1 where id = "+"'"+id+"'"+";";
				
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 4946!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
		/**
		 * ��������� ������ � �� ��� ������� ����������� Order Type
		 */
		@SuppressWarnings("static-access")
		public void _insert_ot(String name, String desc)
		{
			String query = "INSERT INTO hmmr_order_type (Name, Description) VALUES ("+ "'"+name+"'"+","+ "'"+desc+"'"+");";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 4969!");
			}
		   	finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
		/**
		 * �������� ������ � �� � ������� hmmr_order_type
		 * @param id - id ������ ��� �������
		 * @param name - ��� ������
		 * @param desc - ��������
		 */
		@SuppressWarnings("static-access")
		public void _update_rec_ot(String id, String name, String desc)
		{
			try {
				String query = "update hmmr_order_type set Name = "+"'"+name+"'"+",Description = "+"'"+desc+"'"+" where id = "+"'"+id+"'"+";";
				
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5003!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
		/**
		 * ������� ������ �� PM Plan ��� ������ ��� ������� �� �������� ���� ������ � ����������� ������-������
		 * @param group - ����� ������ ��� ������� �������� ���� ������
		 */
		@SuppressWarnings("static-access")
		public void _update_new_date(String group)
		{
			String query = "UPDATE hmmr_pm_year SET record_del = 1 WHERE PM_Group = "+"'"+group+"'"+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5029!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		@SuppressWarnings("static-access")
		public String _select_pmid(String group)
		{
			String pm_id = "UNKNOWN";
			try {
				String query = "select PM_ID from hmmr_pm_year where PM_Group = "+"'"+group+"'"+";";
				
				cn.ConToDb();
				stmt15 = cn.con.createStatement();
				rs15 = stmt15.executeQuery(query);
				//log.log(Level.INFO, "CHANNEL WAS FOUND");
		        while (rs15.next()) {
		        	pm_id = rs15.getString(1);
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5051!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt15.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs15.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return pm_id;
		}
		/**
		 * ��������� ������ � Work Plan
		 * @param id_pm - ����� PM
		 * @param type - ��� �� ����������� Order Types
		 * @param pmname - ��� PM
		 * @param due_date - ���� ����� ������ ����� ���������� � Work Order
		 * @param equip - ������������
		 * @param instruct - ���� � ����������
		 * @param otf - ������������� Group Leader ��� Team Leader
		 * @param userid - id ������������
		 * @param shop - ���
		 * @param icon - ������ ����������
		 * @param otv - ������������� �����������
		 */
		@SuppressWarnings("static-access")
		public void _insert_wp(String id_pm, String type, String pmname, String due_date, String equip, String instruct, String otf, String userid, String shop, String icon, String otv, String icon_at)
		{
			String query = "INSERT INTO hmmr_work_plan (PM_Num, Type, Description, Due_Date, Equipment, Instruction, Tsk_maker, Otv_For_Task, user_id, shop, Icon, Otv, Icon_AT) VALUES ("+"'"+id_pm+"'"+","+ "'"+type+"'"+","+"'"+pmname+"'"+","+"'"+due_date+"'"+","+"'"+equip+"'"+","+"'"+instruct+"'"+","+"'"+otf+"'"+","+"'"+otf+"'"+","+"'"+userid+"'"+","+"'"+shop+"'"+","+"'"+icon+"'"+","+"'"+otv+"'"+","+"'"+icon_at+"'"+");";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5092!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		
		/**
		 * ��������� ������� ������� Work Plan ��� �������� ����
		 * APWR - ��������. ������ ������ ���������� ������ � ��� 
		 * ������ ���� ���� �� ���� �� ���� ��������� (��������,
		 * ������������� �� ������ ��� �����������) �������� � ���
		 * ��� ����������������� � �������. �. �. ������� ����������
		 * ������ �������� ������������ ��������� � ����. ���� ���������
		 * ���������� �� �������� ����������, �.�. �� ���� Icon 
		 * @param oft - ����������� ��� ������������ �������������������
		 * 		� ���������.
		 * @return - ���������� ����� ������ ���� ObservableList � 
		 * 			��������� ��� ������� TableView.
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<hmmr_wp_model> _select_data_wp(String oft)
		{
			ObservableList<hmmr_wp_model> list = FXCollections.observableArrayList();
			
			try {
				String query = "select id,PM_Num,Type,Description,Due_Date,Equipment,Instruction,Otv_For_Task,Otv,Tsk_maker,flag_otv,flag_oft,flag_tm,Icon from hmmr_work_plan where (Otv_For_Task = "+"'"+oft+"'"+" OR Otv = "+"'"+oft+"'"+" OR Tsk_maker = "+"'"+oft+"'"+") AND del_rec = 0;";
							
				cn.ConToDb();
				stmt12 = cn.con.createStatement();
				rs12 = stmt12.executeQuery(query);
							
		        while (rs12.next()) {
		        	hmmr_wp_model hpm = new hmmr_wp_model();
		        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
		        		hpm.Id.set("WP"+rs12.getString(1));
		        		hpm.PM_Num.set(rs12.getString(2));
		        		hpm.Type.set(rs12.getString(3));
		        		hpm.Desc.set(rs12.getString(4));
		        		hpm.Due_Date.set(rs12.getString(5));
		        		hpm.Equip.set(rs12.getString(6));
		        		hpm.inst_btn.set(rs12.getString(7));
		        		hpm.OFT.set(rs12.getString(8));
		        		hpm.OTV.set(rs12.getString(9));
		        		hpm.tsk_maker.set(rs12.getString(10));
		        		hpm.flag_otv.set(rs12.getString(11));
		        		hpm.flag_oft.set(rs12.getString(12));
		        		hpm.flag_tm.set(rs12.getString(13));
		        		hpm.icon.set(rs12.getString(14));
		        						        				            
			            list.add(hpm);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5120!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
		
		/**
		 * �������� ���� � ����������
		 * @param id - id ������ ��� ������� ����� �������� ����������
		 * @return - ���� � ����������
		 */
		@SuppressWarnings({ "static-access"})
		public String _select_inst_for_wp(String id)
		{
			String pmn = null;
			try {
				String query = "select Instruction from hmmr_work_plan WHERE id = "+"'"+id.substring(2)+"'"+";";
				
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	//typepm_model_inst tpm = new typepm_model_inst();
		        	if(rs6.getString(1) != null) {
		        		//tpm.settypepm(rs6.getString(1));
		        		pmn = rs6.getString(1);			        					            
			        }    
		        }
//		        System.out.println("SELECT WORKED DATA: "+list);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5160!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
//			System.out.println("ARRAYLIST: "+list);
			return pmn;
		}
		/**
		 * �������� ������ ���� ����� �� ������� hmmr_work_plan, ��������������� ��������� ����
		 * @return - ���������� ������ �����
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<String> _select_date_wp(String date)
		{
			ObservableList<String> list = FXCollections.observableArrayList();
			
			try {
				String query = "select id,PM_Num,Type,Description,Due_Date,Equipment,Instruction,Otv_For_Task,Otv,Tsk_maker,flag_otv,flag_oft,flag_tm,Icon,shop,Icon_AT from hmmr_work_plan where Due_Date = "+"'"+date+"'"+" AND del_rec = 0;";
							
				cn.ConToDb();
				stmt12 = cn.con.createStatement();
				rs12 = stmt12.executeQuery(query);
							
		        while (rs12.next()) {
		        	
		        	if(rs12.getString(1) != null) {
		        		String hpm = rs12.getString(1) + " - " + rs12.getString(2) + " - " + rs12.getString(3) + " - " + rs12.getString(4) + " - " + rs12.getString(5) + " - " + rs12.getString(6) + " - " + rs12.getString(7) + " - " + rs12.getString(8) + " - " + rs12.getString(9) + " - " + rs12.getString(10) + " - " + rs12.getString(11) + " - " + rs12.getString(12) + " - " + rs12.getString(13) + " - " + rs12.getString(14) + " - " + rs12.getString(15) + " - " + rs12.getString(16);		        						        				            
			            list.add(hpm);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5207!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
		/**
		 * ������� ������ �� Work Plan ���� ������� ���� ������� � ����� � Work Plan
		 * @param id - id ������ ������� �������
		 */
		@SuppressWarnings("static-access")
		public void _update_wp_record(String id)
		{
			String query = "UPDATE hmmr_work_plan SET del_rec = 1 WHERE id = "+"'"+id+"'"+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5238!");
			}
		   	finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
		
		/**
		 * �������� ������ ��� ������� � Work Order ��� ����������� ��� �� ������� ����
		 * @param data - ����
		 * @return - ���������� ����� ������
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<String> _select_dly_wo(String data)
		{
			ObservableList<String> list = FXCollections.observableArrayList();
			try {
				
				String query = "select hpy.data, hpy.OFT, pi.Link_instruction_PDF, hp.id, hp.PM_Group, pi.PM_name, hps.FL03_Shop_s, hps.FL04_Group_s, hps.FL05_Line_s, hps.FL06_Station_s, hps.FL07_Equipment_s, hpy.id, hp.PM_Executor, pi.Type_PM from hmmr_pm_year hpy INNER JOIN hmmr_pm hp ON hp.PM_Group = hpy.PM_Group INNER JOIN hmmr_plant_structure hps ON hps.id = hp.Eq_ID INNER JOIN pm_inst pi ON hp.Instruction_num = pi.num_instruction INNER JOIN hmmr_group_cycle hgc ON hgc.PM_Group = hpy.PM_Group where hpy.record_del = 0 AND hgc.PM_Duration = 0 AND hgc.Date_Beforehand = 0 AND hpy.data = "+"'"+data+"'"+" GROUP BY hp.id;";
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	//typepm_model_inst tpm = new typepm_model_inst();
		        	if(rs6.getString(1) != null) {
		        		//tpm.settypepm(rs6.getString(1));
		        		String pmn = rs6.getString(1)+" - "+ rs6.getString(2)+" - "+ rs6.getString(3)+" - "+ rs6.getString(4)+" - "+ rs6.getString(5)+" - "+ rs6.getString(6)+" - "+ rs6.getString(7)+" - "+ rs6.getString(8)+" - "+ rs6.getString(9)+" - "+ rs6.getString(10)+" - "+ rs6.getString(11)+" - "+ rs6.getString(12)+" - "+ rs6.getString(13)+" - "+ rs6.getString(14);			        					            
			            list.add(pmn);
		        	}    
		        }
//		        System.out.println("SELECT WORKED DATA: "+list);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5267!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
//			System.out.println("ARRAYLIST: "+list);
			return list;
		}	
		/**
		 * �������� ������ ��� ����� ���������� ������ � Work Plan
		 * @param id - id ������ � Work Plan, ������� ���������� ��������
		 * @return
		 */
		@SuppressWarnings("static-access")
		public String _select_for_update_wp(String id)
		{
			try {
				String query = "select PM_Num,Type,Description,Due_Date,Equipment,Otv_For_Task,Otv from hmmr_work_plan where id = "+"'"+id+"'"+";";
				
				cn.ConToDb();
				stmt15 = cn.con.createStatement();
				rs15 = stmt15.executeQuery(query);
				//log.log(Level.INFO, "CHANNEL WAS FOUND");
		        while (rs15.next()) {
		        	pmnum_ap = rs15.getString(1);
		        	type_ap = rs15.getString(2);
		        	description_ap = rs15.getString(3);
		        	due_date_ap = rs15.getString(4);
		        	equip_ap = rs15.getString(5);
		        	oft_ap = rs15.getString(6); 
		        	otv_ap = rs15.getString(7); 
		        }
		        total_rez_upd_ap = pmnum_ap+";"+type_ap+";"+description_ap+";"+due_date_ap+";"+equip_ap+";"+oft_ap+";"+otv_ap;
//		        System.out.println("SELECT WORKED: "+total_rez);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5303!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt15.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs15.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return total_rez_upd_ap;
		}
		
		/**
		 * �������� ����������� ��� �������������� �� PM
		 * @param id - id ������ � ������� ���������� ������ �������������� �� PM
		 * @return - ���������� ����������� ��� �������������� �� PM
		 */
		@SuppressWarnings("static-access")
		public String _select_tm_wp(String id)
		{
			String tm = "null";
			try {
				String query = "select Tsk_maker from hmmr_work_plan where id = "+"'"+id+"'"+";";
				
				cn.ConToDb();
				stmt9 = cn.con.createStatement();
				rs9 = stmt9.executeQuery(query);
				//log.log(Level.INFO, "CHANNEL WAS FOUND");
		        while (rs9.next()) {
		        	tm = rs9.getString(1);
		        }
		        

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5342!");
	        } finally {

	        	try { cn.con.close(); } catch(SQLException se) { }
	            try { stmt9.close(); } catch(SQLException se) {  }
	            try { rs9.close(); } catch(SQLException se) {  }
	        }
			return tm;
		}
		
		/**
		 * �������� ������ � �� � ������� hmmr_work_plan
		 * @param id - id ������ ��� �������
		 * @param PM_Num - ����� PM
		 * @param Type - ���, � ���� ������ ������ PM
		 * @param Description - ��� PM
		 * @param Due_Date - ���� �� ������� ���������� ��������� PM
		 * @param Equipment - ������������
		 * @param Otv_For_Task - ������������� �� PM
		 * @param Otv - ������������� ����������� PM
		 * @param shop - ���
		 */
		@SuppressWarnings("static-access")
		public void _update_rec_ap(String id, String PM_Num, String Type, String Description, LocalDate Due_Date, String Equipment, String Otv_For_Task, String Otv, String shop)
		{
			String query = "UPDATE hmmr_work_plan SET PM_Num = "+"'"+PM_Num+"'"+",Type = "+"'"+Type+"'"+",Description = "+"'"+Description+"'"+",Due_Date = "+"'"+Due_Date+"'"+",Equipment = "+"'"+Equipment+"'"+",Otv_For_Task = "+"'"+Otv_For_Task+"'"+",Otv = "+"'"+Otv+"'"+",shop = "+"'"+shop+"'"+" where id = "+"'"+id+"'"+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5380!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		/**
		 * ������ ��� ���������� ComboBox ���������, ������ � ����� ���������� ���
		 * ������ � ������� PM Instruction
		 * @param str - ��� ����, �������� �������� ���� �������� � ComboBox
		 * @return - ���������� ���������� ��������
		 */			
		@SuppressWarnings({ "static-access"})
		public ObservableList<String> _select_pm_str(String str)
		{
			ObservableList<String> list = FXCollections.observableArrayList();
			
			try {
				String query = "select "+str+" from pm_inst group by "+str+";";
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	if(rs6.getString(1) != null) {
		        		String tpm = rs6.getString(1);			        					            
			            list.add(tpm);
		        	}    
		        }

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 1109!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
		       }

			return list;
		}
		/**
		 * ���������� ��� ������ �� ���� ��� �����������
		 * @param shop - ��� �� �������� ���������� �������� ������ ��� �����������
		 * @return - ���������� � TableView ���������� ����� ������
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<hmmr_ap_model> _select_data_without_otv(String shop)
		{
			ObservableList<hmmr_ap_model> list = FXCollections.observableArrayList();
			
			try {
				
				String query = "select hap.id,hap.PM_Num,hap.Type,hap.Description,hap.Due_Date,hap.Equipment,hap.Instruction,hap.Otv_For_Task,hap.Otv,hap.Tsk_maker,hap.flag_otv,hap.flag_oft,hap.flag_tm,hap.Icon from hmmr_action_plan hap INNER JOIN hmmr_mu_staff hms ON hap.Tsk_maker = hms.ID WHERE hap.Otv = 'need select' AND del_rec = 0 AND if( "+"'"+shop+"'"+"='S' || "+"'"+shop+"'"+"='W', hms.Group_S='S,W', hms.Group_S="+"'"+shop+"'"+");"; 
				
				cn.ConToDb();
				stmt12 = cn.con.createStatement();
				rs12 = stmt12.executeQuery(query);
							
		        while (rs12.next()) {
		        	hmmr_ap_model hpm = new hmmr_ap_model();
		        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
		        		hpm.Id.set("AP"+rs12.getString(1));
		        		hpm.PM_Num.set(rs12.getString(2));
		        		hpm.Type.set(rs12.getString(3));
		        		hpm.Desc.set(rs12.getString(4));
		        		hpm.Due_Date.set(rs12.getString(5));
		        		hpm.Equip.set(rs12.getString(6));
		        		hpm.inst_btn.set(rs12.getString(7));
		        		hpm.OFT.set(rs12.getString(8));
		        		hpm.OTV.set(rs12.getString(9));
		        		hpm.tsk_maker.set(rs12.getString(10));
		        		hpm.flag_otv.set(rs12.getString(11));
		        		hpm.flag_oft.set(rs12.getString(12));
		        		hpm.flag_tm.set(rs12.getString(13));
		        		hpm.icon.set(rs12.getString(14));
		        						        				            
			            list.add(hpm);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5446!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return list;
		}
		/**
		 * ��������� ������� ������� ����� staff ��������
		 * @param id_num - id ������ ���������
		 * @param STAFF_ID - id ����������
		 * @param ID - ����������� id ����������
		 * @param L_Name_RUS - �������
		 * @param F_Name_RUS - ���
		 * @param Otchestvo - ��������
		 * @param DoB - ���� ��������
		 * @param Sec - ������
		 * @param Group_S - ����� ���
		 * @param Team - ����� ������� (GL, TL, �������� � �.�.) ���������� (GL, TL, E, T)
		 * @param Position_RUS - ���������
		 * @param GWM_ID - ��������� �����
		 * @param Date_of_Start - ���� ������ ������
		 * @param Cell_1 - �������
		 * @param Address - �����
		 * @param Avto - � ������
		 * @param Shoes_size - ������ �����
		 * @param Clothes_size - ������ ������
		 * @return - ���������� ����� ������
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<Hmmr_Stuff_Model> _select_data_staff() //String id_num, String STAFF_ID, String ID, String L_Name_RUS, String F_Name_RUS, String Otchestvo, String DoB, String Sec, String Group_S, String Team, String Position_RUS, String GWM_ID, String Date_of_Start, String Cell_1, String Address, String Avto, String Shoes_size, String Clothes_size
		{
			ObservableList<Hmmr_Stuff_Model> list = FXCollections.observableArrayList();
			
			try {
				
				String query = "SELECT hms.id_num, hms.STAFF_ID, hms.ID, hms.L_Name_RUS, hms.F_Name_RUS, hms.Otchestvo, hms.DoB, hms.Sec, hms.Group_S, hms.Team, hms.Position_RUS, hms.GWM_ID, hms.Date_of_Start, hms.Cell_1, hms.Address, hms.Avto, hms.Shoes_size, hms.Clothes_size, usr.login FROM hmmr_mu_staff hms INNER JOIN users usr ON usr.id = hms.user_id where hms.user_del = 0 ORDER BY id_num ASC;"; 
				
				cn.ConToDb();
				stmt12 = cn.con.createStatement();
				rs12 = stmt12.executeQuery(query);
							
		        while (rs12.next()) {
		        	Hmmr_Stuff_Model hsm = new Hmmr_Stuff_Model();
		        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
		        		hsm.id.set(rs12.getString(1));
		        		hsm.StaffId.set(rs12.getString(2));
		        		hsm.ID.set(rs12.getString(3));
		        		hsm.L_Name_Rus.set(rs12.getString(4));
		        		hsm.F_Name_Rus.set(rs12.getString(5));
		        		hsm.Otchestvo.set(rs12.getString(6));
		        		hsm.DoB.set(rs12.getString(7));
		        		hsm.Sec.set(rs12.getString(8));
		        		hsm.Group_S.set(rs12.getString(9));
		        		hsm.Team.set(rs12.getString(10));
		        		hsm.Position_RUS.set(rs12.getString(11));
		        		hsm.GWM_ID.set(rs12.getString(12));
		        		hsm.Date_of_Start.set(rs12.getString(13));
		        		hsm.Cell_1.set(rs12.getString(14));
		        		hsm.Address.set(rs12.getString(15));
		        		hsm.Avto.set(rs12.getString(16));
		        		hsm.Shoes_Size.set(rs12.getString(17));
		        		hsm.Clothes_Size.set(rs12.getString(18));
		        		hsm.Login.set(rs12.getString(19));			        				            
			            list.add(hsm);
			        }    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5515!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return list;
		}
		/**
		 * ������ ��� ���������� ComboBox ���������, ������ � ����� ���������� ���
		 * ������ � ����� ������� ���� ���� ����� ��� �������
		 * @param tbl_name - ��� ������� �� ������� ����� ������ ��� ���������� ComboBox
		 * @param str - ��� ����, �������� �������� ���� �������� � ComboBox
		 * @return - ���������� ���������� ��������
		 */			
		@SuppressWarnings({ "static-access"})
		public ObservableList<String> _select_list_str(String tbl_name, String str)
		{
			ObservableList<String> list = FXCollections.observableArrayList();
			
			try {
				String query = "select "+str+" from "+tbl_name+" group by "+str+";";
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	if(rs6.getString(1) != null) {
		        		String tpm = rs6.getString(1);			        					            
			            list.add(tpm);
		        	}    
		        }

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5570!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
		       }

			return list;
		}
		/**
		 * ��������� ������ � �� � ������� hmmr_mu_staff
		 * @param STAFF_ID
		 * @param ID
		 * @param user_id
		 * @param L_Name_RUS
		 * @param F_Name_RUS
		 * @param Otchestvo
		 * @param First_Name
		 * @param Last_Name
		 * @param DoB
		 * @param Sec
		 * @param Group_S
		 * @param Team
		 * @param WorkShift
		 * @param Position
		 * @param Position_RUS
		 * @param GWM_ID
		 * @param Date_of_Start
		 * @param E_Mail
		 * @param Skype
		 * @param Cell_1
		 * @param Cell_2
		 * @param Address
		 * @param Avto
		 * @param Shoes_size
		 * @param Clothes_size
		 * @param Quit_Date
		 */
		@SuppressWarnings("static-access")
		public void _insert_staff(String STAFF_ID, String ID, String user_id, String L_Name_RUS, String F_Name_RUS, String Otchestvo, String First_Name, String Last_Name, LocalDate DoB, 
				String Sec, String Group_S, String Team, String WorkShift, String Position, String Position_RUS, String GWM_ID, LocalDate Date_of_Start, String E_Mail, 
				String Skype, String Cell_1, String Cell_2, String Address, String Avto, String Shoes_size, String Clothes_size, LocalDate Quit_Date)
		{
			String query = "INSERT INTO hmmr_mu_staff (STAFF_ID, ID, user_id, L_Name_RUS, F_Name_RUS, Otchestvo, First_Name, Last_Name, DoB, Sec, Group_S, Team, WorkShift, Position,"
					+ "Position_RUS, GWM_ID, Date_of_Start, E_Mail, Skype, Cell_1, Cell_2, Address, Avto, Shoes_size, Clothes_size, Quit_Date) "
					+ "VALUES ("+"'"+STAFF_ID+"'"+","+"'"+ID+"'"+","+"'"+user_id+"'"+","+"'"+L_Name_RUS+"'"+","+"'"+F_Name_RUS+"'"+","+"'"+Otchestvo+"'"+","
					+ ""+"'"+First_Name+"'"+","+"'"+Last_Name+"'"+","+"'"+DoB+"'"+","+"'"+Sec+"'"+","+"'"+Group_S+"'"+","
					+ ""+"'"+Team+"'"+","+"'"+WorkShift+"'"+","+"'"+Position+"'"+","+"'"+Position_RUS+"'"+","+"'"+GWM_ID+"'"+","
					+ ""+"'"+Date_of_Start+"'"+","+"'"+E_Mail+"'"+","+"'"+Skype+"'"+","+"'"+Cell_1+"'"+","
					+ ""+"'"+Cell_2+"'"+","+"'"+Address+"'"+","+"'"+Avto+"'"+","+"'"+Shoes_size+"'"+","
					+ ""+"'"+Clothes_size+"'"+","+"'"+Quit_Date+"'"+");";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				_flag_error = true;
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				_flag_error = false;
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5629!");
			}
		   	finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
		/**
		 * ��������� ������ � �� � ������� users
		 * @param first_name
		 * @param last_name
		 * @param login
		 * @param passwd
		 * @param create_date
		 * @param last_login
		 * @param role
		 */
		@SuppressWarnings("static-access")
		public void _insert_users(int id, String first_name, String last_name, String login, String passwd, LocalDate create_date, String role)
		{
			String query = "INSERT INTO users (id, first_name, last_name, login, passwd, create_date, role) "
					+ "VALUES ("+id+","+"'"+first_name+"'"+","+"'"+last_name+"'"+","+"'"+login+"'"+","+"'"+passwd+"'"+","+"'"+create_date+"'"+","+ ""+"'"+role+"'"+");";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5667!");
			}
		   	finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
		/**
		 * �������� ������ ��� ������� � ����������� �� � ����� ������� ��� STAFF
		 * @param id - id ������ ������� ���������� ���������
		 * @return - ���������� ����� ������
		 */
		@SuppressWarnings("static-access")
		public String _select_for_staff_str(int id)
		{
			String list = "null";
			try {
				String query = "SELECT hms.STAFF_ID, hms.ID, hms.L_Name_RUS, hms.F_Name_RUS, hms.Otchestvo, hms.DoB, hms.Sec, hms.Group_S, hms.Team, hms.Position_RUS, hms.GWM_ID, hms.Date_of_Start, hms.Cell_1, hms.Address, hms.Avto, hms.Shoes_size, hms.Clothes_size, usr.login, hms.Position, hms.WorkShift, hms.Last_Name, hms.First_Name, hms.E_Mail, hms.Skype, hms.Cell_2, hms.Quit_Date, usr.role, usr.passwd, usr.id FROM hmmr_mu_staff hms INNER JOIN users usr ON usr.id = hms.user_id where hms.user_del = 0 and hms.id_num = "+id+" ORDER BY hms.id_num ASC;";
				
				cn.ConToDb();
				stmt15 = cn.con.createStatement();
				rs15 = stmt15.executeQuery(query);
				//log.log(Level.INFO, "CHANNEL WAS FOUND");
		        while (rs15.next()) {
		        	list = rs15.getString(1) + " - " + rs15.getString(2) + " - " + rs15.getString(3) + " - " + rs15.getString(4) + " - " + rs15.getString(5) + " - " + rs15.getString(6) + " - " + rs15.getString(7) + " - " + rs15.getString(8) + " - " + rs15.getString(9) + " - " + rs15.getString(10) + " - " + rs15.getString(11) + " - " + rs15.getString(12) + " - " + rs15.getString(13) + " - " + rs15.getString(14) + " - " + rs15.getString(15) + " - " + rs15.getString(16) + " - " + rs15.getString(17) + " - " + rs15.getString(18) + " - " + rs15.getString(19) + " - " + rs15.getString(20) + " - " + rs15.getString(21) + " - " + rs15.getString(22) + " - " + rs15.getString(23) + " - " + rs15.getString(24) + " - " + rs15.getString(25) + " - " + rs15.getString(26) + " - " + rs15.getString(27) + " - " + rs15.getString(28) + " - " + rs15.getString(29);
		        }
		    }
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5696!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt15.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs15.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return list;
		}
		/**
		 * �������� ������ � �� � ������� hmmr_mu_staff
		 * @param id_num
		 * @param STAFF_ID
		 * @param ID
		 * @param L_Name_RUS
		 * @param F_Name_RUS
		 * @param Otchestvo
		 * @param First_Name
		 * @param Last_Name
		 * @param DoB
		 * @param Sec
		 * @param Group_S
		 * @param Team
		 * @param WorkShift
		 * @param Position
		 * @param Position_RUS
		 * @param GWM_ID
		 * @param Date_of_Start
		 * @param E_Mail
		 * @param Skype
		 * @param Cell_1
		 * @param Cell_2
		 * @param Address
		 * @param Avto
		 * @param Shoes_size
		 * @param Clothes_size
		 * @param Quit_Date
		 */
		@SuppressWarnings("static-access")
		public void _update_rec_staff(int id_num, String STAFF_ID, String ID, String L_Name_RUS, String F_Name_RUS, String Otchestvo, String First_Name, String Last_Name, LocalDate DoB, String Sec, String Group_S, String Team, String WorkShift, String Position, String Position_RUS, String GWM_ID, LocalDate Date_of_Start, String E_Mail, String Skype, String Cell_1, String Cell_2, String Address, String Avto, String Shoes_size, String Clothes_size, LocalDate Quit_Date)
		{
			String query = "UPDATE hmmr_mu_staff SET STAFF_ID = "+"'"+STAFF_ID+"'"+",ID = "+"'"+ID+"'"+",L_Name_RUS = "+"'"+L_Name_RUS+"'"+",F_Name_RUS = "+"'"+F_Name_RUS+"'"+",Otchestvo = "+"'"+Otchestvo+"'"+",First_Name = "+"'"+First_Name+"'"+",Last_Name = "+"'"+Last_Name+"'"+",DoB = "+"'"+DoB+"'"+",Sec = "+"'"+Sec+"'"+",Group_S = "+"'"+Group_S+"'"+",Team = "+"'"+Team+"'"+",WorkShift = "+"'"+WorkShift+"'"+",Position = "+"'"+Position+"'"+",Position_RUS = "+"'"+Position_RUS+"'"+",GWM_ID = "+"'"+GWM_ID+"'"+",Date_of_Start = "+"'"+Date_of_Start+"'"+",E_Mail = "+"'"+E_Mail+"'"+",Skype = "+"'"+Skype+"'"+",Cell_1 = "+"'"+Cell_1+"'"+",Cell_2 = "+"'"+Cell_2+"'"+",Address = "+"'"+Address+"'"+",Avto = "+"'"+Avto+"'"+",Shoes_size = "+"'"+Shoes_size+"'"+",Clothes_size = "+"'"+Clothes_size+"'"+",Quit_Date = "+"'"+Quit_Date+"'"+" where id_num = "+id_num+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5748!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		/**
		 * �������� ������ � �� � ������� users
		 * @param id
		 * @param first_name
		 * @param last_name
		 * @param login
		 * @param passwd
		 * @param role
		 */
		@SuppressWarnings("static-access")
		public void _update_rec_users(String id, String first_name, String last_name, String login, String passwd, String role)
		{
			String query = "UPDATE users SET first_name = "+"'"+first_name+"'"+",last_name = "+"'"+last_name+"'"+",login = "+"'"+login+"'"+",passwd = "+"'"+passwd+"'"+",role = "+"'"+role+"'"+" where id = "+"'"+id+"'"+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5777!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		/**
		 * ������� ������ �� �� � ����� ������� 
		 * @param Id - id ������ ������� �������
		 * @param tbl_name - ��� ������� �� ������� ������� ������
		 * @param field_name - ��� ���� � ������� ������ ������� 1 - ���������� ��� ������ ������ �������
		 * @param id_name - ��� ���� � ������� ������� id(��������� ���������������� ����)
		 */
		@SuppressWarnings("static-access")
		public void _update_rec_del(String Id, String tbl_name, String field_name, String id_name)
		{
			String query = "UPDATE "+tbl_name+" SET "+field_name+" = 1 where "+id_name+" = "+"'"+Id+"'"+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5802");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		/**
		 * �������� ���� �������� �� ����� ������� � �� �����
		 * @param tbl_name - ��� ������� � ��
		 * @param str - ��� ���� �������� �������� ���������� ��������
		 * @param del_name - ��� ���� � ��������� ��������
		 * @param id_name - ��� ���� � Id (��������� � ���������������) ��� ������ ����������
		 * @param id - �������� id
		 * @return
		 */
		@SuppressWarnings({ "static-access"})
		public String _select_rec(String tbl_name, String str, String del_name, String id_name, String id)
		{
			String list = "NULL";
			
			try {
				String query = "select "+str+" from "+tbl_name+" where "+del_name+" = 0 AND "+id_name+" = "+id+";";
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	if(rs6.getString(1) != null) {
		        		list = rs6.getString(1);			        					            
			        }    
		        }

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5836!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
		       }

			return list;
		}
		/**
		 * �������� ���� �������� �� ����� ������� � �� �����
		 * @param tbl_name - ��� ������� � ��
		 * @param str - ��� ���� �������� �������� ���������� ��������
		 * @param del_name - ��� ���� � ��������� ��������
		 * @param id_name - ��� ���� � Id (��������� � ���������������) ��� ������ ����������
		 * @param id - ��������
		 * @return
		 */
		@SuppressWarnings({ "static-access"})
		public String _select_recStr(String tbl_name, String str, String del_name, String id_name, String id)
		{
			String list = "NULL";
			
			try {
				String query = "select "+str+" from "+tbl_name+" where "+del_name+" = 0 AND "+id_name+" = "+"'"+id+"'"+";";
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	if(rs6.getString(1) != null) {
		        		list = rs6.getString(1);			        					            
			        }    
		        }

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5836!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
		       }

			return list;
		}
		/**
		 * �������� ��� �������� �� ����� ������� � �� ����� ������
		 * @param tbl_name - ��� ������� � ��
		 * @param str - ��� ���� �������� �������� ���������� ��������
		 * @param str1 - ��� ������� ���� �������� �������� ���������� ��������
		 * @param del_name - ��� ���� � ��������� ��������
		 * @param id_name - ��� ���� � Id (��������� � ���������������) ��� ������ ����������
		 * @param id - �������� id
		 * @return
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<String> _select_recArr(String tbl_name, String str, String str1, String del_name, String id_name, int id)
		{
			ObservableList<String> list = FXCollections.observableArrayList();
			
			try {
				String query = "select "+str+","+str1+" from "+tbl_name+" where "+del_name+" = 0 AND "+id_name+" = "+id+";";
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	if(rs6.getString(1) != null) {
		        		list.add(rs6.getString(1) + " - " + rs6.getString(2));			        					            
			        }    
		        }

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5927!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
		       }

			return list;
		}
		/**
		 * �������� ��� �������� ����� �� ����� ������� � �� ����� ������
		 * @param tbl_name - ��� ������� � ��
		 * @param str - ��� ���� �������� �������� ���������� ��������
		 * @param str1 - ��� ������� ���� �������� �������� ���������� ��������
		 * @param del_name - ��� ���� � ��������� ��������
		 * @return
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<String> _select_recArr(String tbl_name, String str, String str1, String del_name)
		{
			ObservableList<String> list = FXCollections.observableArrayList();
			
			try {
				String query = "select "+str+","+str1+" from "+tbl_name+" where "+del_name+" = 0;";
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	if(rs6.getString(1) != null) {
		        		list.add(rs6.getString(1) + " - " + rs6.getString(2));			        					            
			        }    
		        }

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5927!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
		       }

			return list;
		}
		/**
		 * �������� ������ �������� � �������� ���� �����, ����� ������� ��� � ���� ���������
		 * ��� ��������� ����� �� ������ ���� ����� � ������� Action Plan
		 * @param id - id � AP ����� �������� ��� ���� �����, � �� ���� � ������ ��������
		 * @return - ���������� ���������� ����� ������
		 */
		@SuppressWarnings({ "static-access"})
		public String _select_at_desc(String id)
		{
			String list = "null";
			
			try {
				String query = "select hat.Description from hmmr_action_plan hap INNER JOIN hmmr_activity_type hat ON hap.Icon_AT = hat.Name AND hap.id = "+"'"+id+"'"+";"; // AND ID_TSK = "+"'"+id+"'"+
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	if(rs6.getString(1) != null) {
		        		list = rs6.getString(1);			        					            
			        }    
		        }

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5963");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
		       }

			return list;
		}
		/**
		 * �������� ���� �������� � WO 
		 * @param icon_at - �������� activity type ����������
		 */
		@SuppressWarnings("static-access")
		public void _update_rec_ap_iconat(String icon_at, String id)
		{
			String query = "UPDATE hmmr_action_plan SET Icon_AT = "+"'"+icon_at+"'"+" WHERE id = "+id+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 5994!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		/**
		 * �������� ������ �������� � �������� ���� �����, ����� ������� ��� � ���� ���������
		 * ��� ��������� ����� �� ������ ���� ����� � ������� Work Recording
		 * @param id - id � AP ����� �������� ��� ���� �����, � �� ���� � ������ ��������
		 * @return - ���������� ���������� ����� ������
		 */
		@SuppressWarnings({ "static-access"})
		public String _select_at_desc_wr(String id)
		{
			String list = "null";
			
			try {
				String query = "select hat.Description from hmmr_work_recording hwr INNER JOIN hmmr_activity_type hat ON hwr.Activity_Type = hat.Name AND hwr.id = "+"'"+id+"'"+";"; // AND ID_TSK = "+"'"+id+"'"+
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	if(rs6.getString(1) != null) {
		        		list = rs6.getString(1);			        					            
			        }    
		        }

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6023");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
		       }

			return list;
		}
		/**
		 * ������� ������ � �� �� ������� hmmr_group_cycle
		 * @param id - id ������ ������� �������
		 */
		@SuppressWarnings("static-access")
		public void _delete_for_pmplan(String group)
		{
			try {
				String query = "update hmmr_pm_year set record_del = 1 where PM_Group = "+"'"+group+"'"+";";
				
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6055!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
		/**
		 * �������� ��� �������� �� ����� ������� � �� ������ ������
		 * @param tbl_name - ��� ������� � ��
		 * @param str - ��� ���� �������� �������� ���������� ��������
		 * @param str1 - ��� ������� ���� �������� �������� ���������� ��������
		 * @param del_name - ��� ���� � ��������� ��������
		 * @param id_name - ��� ���� � Id (��������� � ���������������) ��� ������ ����������
		 * @param id - �������� id
		 * @return
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<String> _select_recArrStr(String tbl_name, String str, String del_name, String id_name, String id)
		{
			ObservableList<String> list = FXCollections.observableArrayList();
			
			try {
				String query = "select "+str+" from "+tbl_name+" where "+del_name+" = 0 AND "+id_name+" = "+"'"+id+"'"+" GROUP BY "+str+";";
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	if(rs6.getString(1) != null) {
		        		list.add(rs6.getString(1));			        					            
			        }    
		        }

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6086!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
		       }

			return list;
		}
		/**
		 * �������� ��� ������� hmmr_action_plan �� ���� Staus
		 * 0 - ������ �� ������������
		 * 1 - ������ ���������� ������ � WR, �� �� ��� �� ���������� ������������� �� ������
		 * 3 - ������������� �� ������ ���������� ������ � WR, �� ��� �� ���������� ������ � WO
		 * 5 - ������ � WR � ������ � WO ������������ ������������� �� ������, �� ������ ��� �� ������������ � WO �� ����������
		 * 6 - ������ ����� ������������
		 */
		@SuppressWarnings("static-access")
		public void _update_calc_field(String id)
		{
			try {
				String query = "UPDATE hmmr_action_plan SET Status = flag_otv+flag_oft+flag_tm where id = "+id+";";
				
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6127!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
		/**
		 * ��������� ������� ����� ����������� ������ Part Type �� ��
		 * @return - ���������� ����� ������ ��� ���������� �������
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<Hmmr_Part_Model> _select_data_part()
		{
			ObservableList<Hmmr_Part_Model> list = FXCollections.observableArrayList();
			
			try {
				String query = "select id, Part_Type, Part_Type_Eng from hmmr_mu_part where del_rec = 0;";
							
				cn.ConToDb();
				stmt12 = cn.con.createStatement();
				rs12 = stmt12.executeQuery(query);
							
		        while (rs12.next()) {
		        	Hmmr_Part_Model hpm = new Hmmr_Part_Model();
		        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
		        		hpm.Id.set(rs12.getString(1));
		        		hpm.Part_Type.set(rs12.getString(2));
		        		hpm.Part_Type_Eng.set(rs12.getString(3));
		        				        						        				            
			            list.add(hpm);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6149!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
		/**
		 * �������� ���� �������� �� ����� ������� � �� ����� + ����������
		 * @param tbl_name - ��� ������� � ��
		 * @param str - ��� ���� �������� �������� ���������� ��������
		 * @param del_name - ��� ���� � ��������� ��������
		 * @param id_name - ��� ���� � Id (��������� � ���������������) ��� ������ ����������
		 * @param id - ��������
		 * @return
		 */
		@SuppressWarnings({ "static-access"})
		public String _select_recStrSort(String tbl_name, String str, String del_name, String id_name, String id)
		{
			String list = "NULL";
			
			try {
				String query = "select "+str+" from "+tbl_name+" where "+del_name+" = 0 AND "+id_name+" = "+"'"+id+"'"+" GROUP BY "+"'"+str+"'"+";";
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	if(rs6.getString(1) != null) {
		        		list = rs6.getString(1);			        					            
		        	}    
		       }

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6195!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
		       }

			return list;
		}
		/**
		 * ��������� ������ � �� ��� ������� hmmr_mu_part
		 * @param Part_Type - �������� ����� ������������
		 * @param Part_Type_Eng - �������� ����� ������������ �� ����������
		 */
		@SuppressWarnings("static-access")
		public void _insert_part(String Part_Type, String Part_Type_Eng)
		{
			String query = "INSERT INTO hmmr_mu_part (Part_Type, Part_Type_Eng) "
					+ "VALUES ("+"'"+Part_Type+"'"+","+"'"+Part_Type_Eng+"'"+");";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6227!");
			}
		   	finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
		/**
		 * �������� �������� �����, ����� �������� �� � ��������������� �������� ��� ����� ���������� ����������� Part Type
		 * @param id - id ������ ��� ����������
		 * @return - ���������� ����� ������
		 */
		@SuppressWarnings("static-access")
		public String _select_for_update_part(String id)
		{
			String total_rez_upd_part = "NULL";
			try {
				String query = "select Part_Type, Part_Type_Eng from hmmr_mu_part where id = "+"'"+id+"'"+";";
				
				String part_type = "NULL", part_type_eng = "NULL"; 
				
				cn.ConToDb();
				stmt9 = cn.con.createStatement();
				rs9 = stmt9.executeQuery(query);
				//log.log(Level.INFO, "CHANNEL WAS FOUND");
		        while (rs9.next()) {
		        	part_type = rs9.getString(1);
		        	part_type_eng = rs9.getString(2);
		        	
		        }
		        total_rez_upd_part = part_type+","+part_type_eng;
//		        System.out.println("SELECT WORKED: "+total_rez);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6250!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt9.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs9.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return total_rez_upd_part;
		}
		/**
		 * �������� ������ � �� ��� ������� hmmr_mu_part
		 * @param id - id ������ ��� �������
		 * @param Part_Type
		 * @param Part_Type_Eng
		 */
		@SuppressWarnings("static-access")
		public void _update_field_part(String id, String Part_Type, String Part_Type_Eng)
		{
			try {
				String query = "UPDATE hmmr_mu_part SET Part_Type = "+"'"+Part_Type+"'"+",Part_Type_Eng = "+"'"+Part_Type_Eng+"'"+" where id = "+id+";";
				
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6292!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
		@SuppressWarnings("static-access")
		public void _update_part_del(String Id)
		{
			String query = "UPDATE hmmr_mu_part SET del_rec = 1 where id = "+"'"+Id+"'"+";"; //"UPDATE pm_inst SET Type_PM = "+"'"+type+"'"+", Description = "+"'"+desc+"'"+" WHERE id = "+"'"+id+"'"+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6310");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		/**
		 * ��������� ������� ����� ����������� ������ Part Char �� ��
		 * @return - ���������� ����� ������ ��� ���������� �������
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<Hmmr_PartChar_Model> _select_data_partchar()
		{
			ObservableList<Hmmr_PartChar_Model> list = FXCollections.observableArrayList();
			
			try {
				String query = "select id, Part_Type, SP_KIND, Part_Sub_Type, Part_Sub_Type_ENG, Part_Characteristic_Name_1, Part_Characteristic_Name_2, Part_Characteristic_Name_3, Part_Characteristic_Name_4 from hmmr_part_characteristic where del_rec = 0;";
							
				cn.ConToDb();
				stmt12 = cn.con.createStatement();
				rs12 = stmt12.executeQuery(query);
							
		        while (rs12.next()) {
		        	Hmmr_PartChar_Model hpm = new Hmmr_PartChar_Model();
		        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
		        		hpm.Id.set(rs12.getString(1));
		        		hpm.Part_Type.set(rs12.getString(2));
		        		hpm.SP_KIND.set(rs12.getString(3));
		        		hpm.Part_Sub_Type.set(rs12.getString(4));
		        		hpm.Part_Sub_Type_ENG.set(rs12.getString(5));
		        		hpm.Part_Characteristic_Name_1.set(rs12.getString(6));
		        		hpm.Part_Characteristic_Name_2.set(rs12.getString(7));
		        		hpm.Part_Characteristic_Name_3.set(rs12.getString(8));
		        		hpm.Part_Characteristic_Name_4.set(rs12.getString(9));
		        				        						        				            
			            list.add(hpm);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6380!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
		@SuppressWarnings("static-access")
		public void _update_rec_users(String id, String first_name, String last_name, String login, String role)
		{
			String query = "UPDATE users SET first_name = "+"'"+first_name+"'"+",last_name = "+"'"+last_name+"'"+",login = "+"'"+login+"'"+",role = "+"'"+role+"'"+" where id = "+"'"+id+"'"+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6416!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		/**
		 * ��������� ������ � ������� hmmr_pm
		 * @param id - id ������ ������� ���� ��������������
		 */
		@SuppressWarnings("static-access")
		public void _insert_pm_dup(int id)
		{
			String query = "INSERT INTO hmmr_pm (user_id, Instruction_num, Eq_ID, PM_Group, PM_Resp, OnOff_Line, PM_Executor) "
					+ "SELECT user_id, Instruction_num, Eq_ID, PM_Group, PM_Resp, OnOff_Line, PM_Executor FROM hmmr_pm WHERE id = "+id+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6440!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		
		/**
		 * ��������� ������ � ������� hmmr_part_characteristic
		 * @param txt_pst
		 * @param txt_pste
		 * @param txt_pcn1
		 * @param txt_pcn2
		 * @param txt_pcn3
		 * @param txt_pcn4
		 * @param list_pt
		 * @param list_spk
		 */
		@SuppressWarnings("static-access")
		public void _insert_partchar(String list_pt, String list_spk, String txt_pst, String txt_pste, String txt_pcn1, String txt_pcn2, String txt_pcn3, String txt_pcn4)
		{
			String query = "INSERT INTO hmmr_part_characteristic(Part_Type, SP_KIND, Part_Sub_Type, Part_Sub_Type_ENG, Part_Characteristic_Name_1, Part_Characteristic_Name_2, Part_Characteristic_Name_3, Part_Characteristic_Name_4) VALUES ("+"'"+list_pt+"'"+","+"'"+list_spk+"'"+","+"'"+txt_pst+"'"+","+"'"+txt_pste+"'"+","+"'"+txt_pcn1+"'"+","+"'"+txt_pcn2+"'"+","+"'"+txt_pcn3+"'"+","+"'"+txt_pcn4+"'"+");";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6474!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		/**
		 * �������� �������� �����, ����� �������� �� � ��������������� �������� ��� ����� ���������� ����������� Part Char
		 * @param id - id ������ ��� ����������
		 * @return - ���������� ����� ������
		 */
		@SuppressWarnings("static-access")
		public String _select_for_update_partchar(String id)
		{
			String total_rez_upd_part = "NULL";
			try {
				String query = "select Part_Type, SP_KIND, Part_Sub_Type, Part_Sub_Type_ENG, Part_Characteristic_Name_1, Part_Characteristic_Name_2, Part_Characteristic_Name_3, Part_Characteristic_Name_4 from hmmr_part_characteristic where id = "+"'"+id+"'"+";";
				
				String Part_Type = "NULL", SP_KIND = "NULL", Part_Sub_Type = "NULL", Part_Sub_Type_ENG = "NULL", Part_Characteristic_Name_1 = "NULL", Part_Characteristic_Name_2 = "NULL", Part_Characteristic_Name_3 = "NULL", Part_Characteristic_Name_4 = "NULL"; 
				
				cn.ConToDb();
				stmt9 = cn.con.createStatement();
				rs9 = stmt9.executeQuery(query);
				//log.log(Level.INFO, "CHANNEL WAS FOUND");
		        while (rs9.next()) {
		        	Part_Type = rs9.getString(1);
		        	SP_KIND = rs9.getString(2);
		        	Part_Sub_Type = rs9.getString(3);
		        	Part_Sub_Type_ENG = rs9.getString(4);
		        	Part_Characteristic_Name_1 = rs9.getString(5);
		        	Part_Characteristic_Name_2 = rs9.getString(6);
		        	Part_Characteristic_Name_3 = rs9.getString(7);
		        	Part_Characteristic_Name_4 = rs9.getString(8);
		        	
		        }
		        total_rez_upd_part = Part_Type+";"+SP_KIND+";"+Part_Sub_Type+";"+Part_Sub_Type_ENG+";"+Part_Characteristic_Name_1+";"+Part_Characteristic_Name_2+";"+Part_Characteristic_Name_3+";"+Part_Characteristic_Name_4;
//		        System.out.println("SELECT WORKED: "+total_rez);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6502!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt9.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs9.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return total_rez_upd_part;
		}
		/**
		 * �������� ������ � hmmr_part_characteristic
		 * @param id
		 * @param first_name
		 * @param last_name
		 * @param login
		 * @param role
		 */
		@SuppressWarnings("static-access")
		public void _update_partchar(String id, String list_pt, String list_spk, String txt_pst, String txt_pste, String txt_pcn1, String txt_pcn2, String txt_pcn3, String txt_pcn4)
		{
			String query = "UPDATE hmmr_part_characteristic SET Part_Type = "+"'"+list_pt+"'"+",SP_KIND = "+"'"+list_spk+"'"+",Part_Sub_Type = "+"'"+txt_pst+"'"+",Part_Sub_Type_ENG = "+"'"+txt_pste+"'"+",Part_Characteristic_Name_1 = "+"'"+txt_pcn1+"'"+",Part_Characteristic_Name_2 = "+"'"+txt_pcn2+"'"+",Part_Characteristic_Name_3 = "+"'"+txt_pcn3+"'"+",Part_Characteristic_Name_4 = "+"'"+txt_pcn4+"'"+" where id = "+"'"+id+"'"+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6545!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		/**
		 * ��������� ������� ������� ��� �������� ���� �� ���� ��������->�����->SP - ��������
		 * @return - ���������� ����� ������
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<Hmmr_SP_Model> _select_data_sp()
		{
			ObservableList<Hmmr_SP_Model> list = FXCollections.observableArrayList();
			
			try {
				//String query = "SELECT id, HMMR_Material_Num, Manufacturer, Model, Article, Single_Complex_Sub, SP_MU_Description_RUS, SP_FD_Description, SP_Supplier_Description, Qty_S, Qty_W, Qty_P, Qty_A, Price, Key_No_Backup_Yes, Key_No_Backup_No, Key_Yes_Backup_Yes, Key_Yes_Backup_No, Risk_Breakage, Delivery_Time, Replacement_Model, Qty_Interchangeability, Qty_Identify_SP, Identity_SP, Coefficient, MIN, BATCH FROM hmmr_sp_db hsd INNER JOIN hmmr_parts_spec hpc ON hpc.ID_HMMR = hsd.id WHERE hsd.del_rec = 0;"; //Kind, SP_Part_Type, SP_Sub_Part_Type, Part_Characteristic_1, Part_Characteristic_2, Part_Characteristic_3, Part_Characteristic_4, 
				//String query = "SELECT hsd.id, hsd.HMMR_Material_Num, hsd.Manufacturer, hsd.Model, hsd.Article, hsd.Single_Complex_Sub, hsd.SP_MU_Description_RUS, hsd.SP_FD_Description, hsd.SP_Supplier_Description, hsd.Qty_S, hsd.Qty_W, hsd.Qty_P, hsd.Qty_A, hsd.Price, hps.Key_No_Backup_Yes, hps.Key_No_Backup_No, hps.Key_Yes_Backup_Yes, hps.Key_Yes_Backup_No, hsd.Risk_Breakage, hsd.Delivery_Time, hsd.Replacement_Model, hsd.Qty_Interchangeability, hsd.Qty_Identify_SP, hsd.Identity_SP, hsd.Coefficient, hsd.MIN, hsd.BATCH FROM hmmr_sp_db hsd INNER JOIN hmmr_parts_spec hps ON hps.ID_HMMR = hsd.id WHERE hsd.del_rec = 0 GROUP BY hps.ID_HMMR";
				String query = "SELECT id, HMMR_Material_Num, Manufacturer, Model, Article, Single_Complex_Sub, SP_MU_Description_RUS, SP_FD_Description, SP_Supplier_Description, Qty_S, Qty_W, Qty_P, Qty_A, Price, Key_No_Backup_Yes, Key_No_Backup_No, Key_Yes_Backup_Yes, Key_Yes_Backup_No, Risk_Breakage, Delivery_Time, Replacement_Model, Qty_Interchangeability, Qty_Identify_SP, Identity_SP, Coefficient, MIN, BATCH FROM hmmr_sp_db WHERE del_rec = 0";
				//String query = "SELECT hsd.id, hsd.HMMR_Material_Num, hsd.Manufacturer, hsd.Model, hsd.Article, hsd.Single_Complex_Sub, hsd.SP_MU_Description_RUS, hsd.SP_FD_Description, hsd.SP_Supplier_Description, hsd.Qty_S, hsd.Qty_W, hsd.Qty_P, hsd.Qty_A, hsd.Price, IF(hps.Key_No_Backup_Yes = 1,COUNT(hps.Key_No_Backup_Yes),0), IF(hps.Key_No_Backup_No = 1,COUNT(hps.Key_No_Backup_No), 0), IF(hps.Key_Yes_Backup_Yes = 1,COUNT(hps.Key_Yes_Backup_Yes),0), IF(hps.Key_Yes_Backup_No = 1,COUNT(hps.Key_Yes_Backup_No),0), hsd.Risk_Breakage, hsd.Delivery_Time, hsd.Replacement_Model, hsd.Qty_Interchangeability, hsd.Qty_Identify_SP, hsd.Identity_SP, hsd.Coefficient, hsd.MIN, hsd.BATCH FROM hmmr_sp_db hsd INNER JOIN hmmr_parts_spec hps ON hps.ID_HMMR = hsd.id WHERE hsd.del_rec = 0 GROUP BY hsd.HMMR_Material_Num";
				cn.ConToDb();
				stmt12 = cn.con.createStatement();
				rs12 = stmt12.executeQuery(query);
				 			
		        while (rs12.next()) {
		        	Hmmr_SP_Model hsm = new Hmmr_SP_Model();
		        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
		        		hsm.Id.set(rs12.getString(1));
		        		hsm.HMMR_Material_Num.set(rs12.getString(2));
		        		hsm.Manufacturer.set(rs12.getString(3));
		        		hsm.Model.set(rs12.getString(4));
		        		hsm.Article.set(rs12.getString(5));
		        		hsm.Single_Complex_Sub.set(rs12.getString(6));
		        		hsm.SP_MU_Description_RUS.set(rs12.getString(7));
		        		hsm.SP_FD_Description.set(rs12.getString(8));
		        		hsm.SP_Supplier_Description.set(rs12.getString(9));
//		        		hsm.Kind.set(rs12.getString(10));
//		        		hsm.SP_Part_Type.set(rs12.getString(11));
//		        		hsm.SP_Sub_Part_Type.set(rs12.getString(12));
//		        		hsm.Part_Characteristic_1.set(rs12.getString(13));
//		        		hsm.Part_Characteristic_2.set(rs12.getString(14));
//		        		hsm.Part_Characteristic_3.set(rs12.getString(15));
//		        		hsm.Part_Characteristic_4.set(rs12.getString(16));
		        		hsm.Qty_S.set(rs12.getString(10));
		        		hsm.Qty_W.set(rs12.getString(11));
		        		hsm.Qty_P.set(rs12.getString(12));
		        		hsm.Qty_A.set(rs12.getString(13));
		        		hsm.Price.set(rs12.getString(14));
		        		hsm.Key_No_Backup_Yes.set(rs12.getString(15));
		        		hsm.Key_No_Backup_No.set(rs12.getString(16));
		        		hsm.Key_Yes_Backup_Yes.set(rs12.getString(17));
		        		hsm.Key_Yes_Backup_No.set(rs12.getString(18));
		        		hsm.Risk_Breakage.set(rs12.getString(19));
		        		hsm.Delivery_Time.set(rs12.getString(20));
		        		hsm.Replacement_Model.set(rs12.getString(21));
		        		hsm.Qty_Interchangeability.set(rs12.getString(22));
		        		hsm.Qty_Identify_SP.set(rs12.getString(23));
		        		hsm.Identity_SP.set(rs12.getString(24));
		        		hsm.Coefficient.set(rs12.getString(25));
		        		hsm.MIN.set(rs12.getString(26));
		        		hsm.BATCH.set(rs12.getString(27));
		        				        						        				            
			            list.add(hsm);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6574!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
		/**
		 * ��������� ������� ����� ����������� ������ Part Char Dir �� ��
		 * @return - ���������� ����� ������ ��� ���������� �������
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<Hmmr_PartCharDir_Model> _select_data_partchardir()
		{
			ObservableList<Hmmr_PartCharDir_Model> list = FXCollections.observableArrayList();
			
			try {
				String query = "select id, Part_Char, Part_Char_Eng from hmmr_partchar_dir where del_rec = 0;";
							
				cn.ConToDb();
				stmt12 = cn.con.createStatement();
				rs12 = stmt12.executeQuery(query);
							
		        while (rs12.next()) {
		        	Hmmr_PartCharDir_Model hpm = new Hmmr_PartCharDir_Model();
		        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
		        		hpm.Id.set(rs12.getString(1));
		        		hpm.Part_Char.set(rs12.getString(2));
		        		hpm.Part_Char_Eng.set(rs12.getString(3));
		        				        						        				            
			            list.add(hpm);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6641!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
		/**
		 * ��������� ������ � �� ��� ������� hmmr_partchar_dir
		 * @param Part_Char - �������� ����� ������������
		 * @param Part_Char_Eng - �������� ����� ������������ �� ����������
		 */
		@SuppressWarnings("static-access")
		public void _insert_partchartdir(String Part_Char, String Part_Char_Eng)
		{
			String query = "INSERT INTO hmmr_partchar_dir (Part_Char, Part_Char_Eng) "
					+ "VALUES ("+"'"+Part_Char+"'"+","+"'"+Part_Char_Eng+"'"+");";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6676!");
			}
		   	finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
		/**
		 * �������� ������ � �� ��� ������� hmmr_partchar_dir
		 * @param id - id ������ ��� �������
		 * @param Part_Char
		 * @param Part_Char_Eng
		 */
		@SuppressWarnings("static-access")
		public void _update_field_partchardir(String id, String Part_Char, String Part_Char_Eng)
		{
			try {
				String query = "UPDATE hmmr_partchar_dir SET Part_Char = "+"'"+Part_Char+"'"+",Part_Char_Eng = "+"'"+Part_Char_Eng+"'"+" where id = "+id+";";
				
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6705!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
		@SuppressWarnings("static-access")
		public void _update_partchardir_del(String Id)
		{
			String query = "UPDATE hmmr_partchar_dir SET del_rec = 1 where id = "+"'"+Id+"'"+";"; //"UPDATE pm_inst SET Type_PM = "+"'"+type+"'"+", Description = "+"'"+desc+"'"+" WHERE id = "+"'"+id+"'"+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6723");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		/**
		 * �������� �������� �����, ����� �������� �� � ��������������� �������� ��� ����� ���������� ����������� Part Char Dir
		 * @param id - id ������ ��� ����������
		 * @return - ���������� ����� ������
		 */
		@SuppressWarnings("static-access")
		public String _select_for_update_partchardir(String id)
		{
			String total_rez_upd_part = "NULL";
			try {
				String query = "select Part_Char, Part_Char_Eng from hmmr_partchar_dir where id = "+"'"+id+"'"+";";
				
				String part_char = "NULL", part_char_eng = "NULL"; 
				
				cn.ConToDb();
				stmt9 = cn.con.createStatement();
				rs9 = stmt9.executeQuery(query);
				//log.log(Level.INFO, "CHANNEL WAS FOUND");
		        while (rs9.next()) {
		        	part_char = rs9.getString(1);
		        	part_char_eng = rs9.getString(2);
		        	
		        }
		        total_rez_upd_part = part_char+";"+part_char_eng;
//		        System.out.println("SELECT WORKED: "+total_rez);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6750!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt9.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs9.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return total_rez_upd_part;
		}
		@SuppressWarnings("static-access")
		public void _insert_sp(String HMMR_Material_Num, String Manufacturer, String Model, String Article, String Single_Complex_Sub, String SP_MU_Description_RUS, String SP_FD_Description, String SP_Supplier_Description, String ID_Pchar, String Price, String Risk_Breakage, String Delivery_Time, String Replacement_Model, String Identity_SP, String Coefficient)
		{
			String query = "INSERT INTO hmmr_sp_db (HMMR_Material_Num, Manufacturer, Model, Article, Single_Complex_Sub, SP_MU_Description_RUS, SP_FD_Description, SP_Supplier_Description, ID_Pchar, Price, Risk_Breakage, Delivery_Time, Replacement_Model, Identity_SP, Coefficient) "
					+ "VALUES ("+"'"+HMMR_Material_Num+"'"+","+"'"+Manufacturer+"'"+","+"'"+Model+"'"+","+"'"+Article+"'"+","+"'"+Single_Complex_Sub+"'"+","+"'"+SP_MU_Description_RUS+"'"+","+"'"+SP_FD_Description+"'"+","+"'"+SP_Supplier_Description+"'"+","+"'"+ID_Pchar+"'"+","+"'"+Price+"'"+","+"'"+Risk_Breakage+"'"+","+"'"+Delivery_Time+"'"+","+"'"+Replacement_Model+"'"+","+"'"+Identity_SP+"'"+","+"'"+Coefficient+"'"+");";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6779!");
			}
		   	finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
		/**
		 * �������� ���� �������� �� ����� ������� � �� �����
		 * @param tbl_name - ��� ������� � ��
		 * @param str - ��� ���� �������� �������� ���������� ��������
		 * @param del_name - ��� ���� � ��������� ��������
		 * @param id_name - ��� ���� � Id (��������� � ���������������) ��� ������ ����������
		 * @param id - ��������
		 * @param field - ��� ������������� ����
		 * @param field_value - �������� ��� ������������� ����
		 * @return - �������� ���������� ����
		 */
		@SuppressWarnings({ "static-access"})
		public String _select_recStr(String tbl_name, String str, String del_name, String id_name, String id, String field, String field_value)
		{
			String list = "NULL";
			
			try {
				String query = "select "+str+" from "+tbl_name+" where "+del_name+" = 0 AND "+id_name+" = "+"'"+id+"'"+" AND "+field+" = "+"'"+field_value+"'"+";";
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	if(rs6.getString(1) != null) {
		        		list = rs6.getString(1);			        					            
		        	}    
		       }

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6815!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
		       }

			return list;
		}
		/**
		 * �������� �������� �����, ����� �������� �� � ��������������� �������� ��� ����� ���������� SP DB
		 * @param id - id ������ ��� ����������
		 * @return - ���������� ����� ������
		 */
		@SuppressWarnings("static-access")
		public String _select_for_update_sp(String id)
		{
			String total_rez_upd_part = "NULL";
			try {
				String query = "select HMMR_Material_Num, Manufacturer, Model, Article, Single_Complex_Sub, SP_MU_Description_RUS, SP_FD_Description, SP_Supplier_Description, Price, Risk_Breakage, Delivery_Time, Replacement_Model, Identity_SP, Coefficient, ID_Pchar, Qty_S, Qty_W, Qty_P, Qty_A, Key_No_Backup_Yes, Key_No_Backup_No, Key_Yes_Backup_Yes, Key_Yes_Backup_No from hmmr_sp_db where id = "+"'"+id+"'"+";";
				
				String HMMR_Material_Num = "NULL", Manufacturer = "NULL", Model = "NULL", Article = "NULL", Single_Complex_Sub = "NULL", SP_MU_Description_RUS = "NULL", 
						SP_FD_Description = "NULL", SP_Supplier_Description = "NULL", Price = "NULL", Risk_Breakage = "NULL", Delivery_Time = "NULL", Replacement_Model = "NULL", 
						Identity_SP = "NULL", Coefficient = "NULL", ID_Pchar = "NULL", Qty_S = "NULL", Qty_W = "NULL", Qty_P = "NULL", Qty_A = "NULL", Key_No_Backup_Yes = "NULL", 
						Key_No_Backup_No = "NULL", 	Key_Yes_Backup_Yes = "NULL", Key_Yes_Backup_No = "NULL"; 
				
				cn.ConToDb();
				stmt9 = cn.con.createStatement();
				rs9 = stmt9.executeQuery(query);
				//log.log(Level.INFO, "CHANNEL WAS FOUND");
		        while (rs9.next()) {
		        	HMMR_Material_Num = rs9.getString(1);
		        	Manufacturer = rs9.getString(2);
		        	Model = rs9.getString(3);
		        	Article = rs9.getString(4);
		        	Single_Complex_Sub = rs9.getString(5);
		        	SP_MU_Description_RUS = rs9.getString(6);
		        	SP_FD_Description = rs9.getString(7);
		        	SP_Supplier_Description = rs9.getString(8);
		        	Price = rs9.getString(9);
		        	Risk_Breakage = rs9.getString(10);
		        	Delivery_Time = rs9.getString(11);
		        	Replacement_Model = rs9.getString(12);
		        	Identity_SP = rs9.getString(13);
		        	Coefficient = rs9.getString(14);
		        	ID_Pchar = rs9.getString(15);
		        	Qty_S = rs9.getString(16);
		        	Qty_W = rs9.getString(17);
		        	Qty_P = rs9.getString(18);
		        	Qty_A = rs9.getString(19);
		        	Key_No_Backup_Yes = rs9.getString(20);
		        	Key_No_Backup_No = rs9.getString(21);
		        	Key_Yes_Backup_Yes = rs9.getString(22);
		        	Key_Yes_Backup_No = rs9.getString(23);
		        }
		        total_rez_upd_part = HMMR_Material_Num+";"+Manufacturer+";"+Model+";"+Article+";"+Single_Complex_Sub+";"+SP_MU_Description_RUS+";"+SP_FD_Description+";"+SP_Supplier_Description+";"+Price+";"+Risk_Breakage+";"+Delivery_Time+";"+Replacement_Model+";"+Identity_SP+";"+Coefficient+";"+ID_Pchar+";"+Qty_S+";"+Qty_W+";"+Qty_P+";"+Qty_A+";"+Key_No_Backup_Yes+";"+Key_No_Backup_No+";"+Key_Yes_Backup_Yes+";"+Key_Yes_Backup_No;
//		        System.out.println("SELECT WORKED: "+total_rez);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6849!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt9.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs9.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return total_rez_upd_part;
		}
		/**
		 * �������� ������ � SP DB
		 * @param Id
		 */
		@SuppressWarnings("static-access")
		public void _update_sp(String Id, String HMMR_Material_Num, String Manufacturer, String Model, String Article, String Single_Complex_Sub, String SP_MU_Description_RUS, String SP_FD_Description, String SP_Supplier_Description, String Price, String Risk_Breakage, String Delivery_Time, String Replacement_Model, String Identity_SP, String Coefficient)
		{
			String query = "UPDATE hmmr_sp_db SET HMMR_Material_Num = "+"'"+HMMR_Material_Num+"'"+",Manufacturer = "+"'"+Manufacturer+"'"+",Model = "+"'"+Model+"'"+",Article = "+"'"+Article+"'"+",Single_Complex_Sub = "+"'"+Single_Complex_Sub+"'"+",SP_MU_Description_RUS = "+"'"+SP_MU_Description_RUS+"'"+",SP_FD_Description = "+"'"+SP_FD_Description+"'"+",SP_Supplier_Description = "+"'"+SP_Supplier_Description+"'"+",Price = "+"'"+Price+"'"+",Risk_Breakage = "+"'"+Risk_Breakage+"'"+",Delivery_Time = "+"'"+Delivery_Time+"'"+",Replacement_Model = "+"'"+Replacement_Model+"'"+",Identity_SP = "+"'"+Identity_SP+"'"+",Coefficient = "+"'"+Coefficient+"'"+" where id = "+"'"+Id+"'"+";"; //"UPDATE pm_inst SET Type_PM = "+"'"+type+"'"+", Description = "+"'"+desc+"'"+" WHERE id = "+"'"+id+"'"+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6905");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		@SuppressWarnings({ "static-access"})
		public ObservableList<Hmmr_PartS_Model> _select_data_parts()
		{
			ObservableList<Hmmr_PartS_Model> list = FXCollections.observableArrayList();
			
			try {
				//String query = "SELECT id, ID_HMMR, ID_EQUIP, Drawing, Position_On_Drawing, Key_No_Backup_Yes, Key_No_Backup_No, Key_Yes_Backup_Yes, Key_Yes_Backup_No FROM hmmr_parts_spec WHERE del_rec = 0;";
				String query = "SELECT hps.id, hsd.SP_MU_Description_RUS, concat(fl.FL03_Shop_s,'.',fl.FL04_Group_s,'.',fl.FL05_Line_s,'.',fl.FL06_Station_s,'.',fl.FL07_Equipment_s), hps.Drawing, hps.Position_On_Drawing, hps.Key_No_Backup_Yes, hps.Key_No_Backup_No, hps.Key_Yes_Backup_Yes, hps.Key_Yes_Backup_No FROM hmmr_parts_spec hps INNER JOIN hmmr_plant_structure fl ON fl.status = 0 AND fl.id = hps.ID_EQUIP INNER JOIN hmmr_sp_db hsd ON hsd.id = hps.ID_HMMR WHERE hps.del_rec = 0 GROUP BY hps.ID_EQUIP";			
				cn.ConToDb();
				stmt12 = cn.con.createStatement();
				rs12 = stmt12.executeQuery(query);
				 			
		        while (rs12.next()) {
		        	Hmmr_PartS_Model hpsm = new Hmmr_PartS_Model();
		        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
		        		hpsm.Id.set(rs12.getString(1));
		        		hpsm.HMMR_Material_Num.set(rs12.getString(2));
		        		hpsm.Equipment.set(rs12.getString(3));
		        		hpsm.Drawing.set(rs12.getString(4));
		        		hpsm.Position_On_Drawing.set(rs12.getString(5));
		        		hpsm.Key_No_Backup_Yes.set(rs12.getString(6));
		        		hpsm.Key_No_Backup_No.set(rs12.getString(7));
		        		hpsm.Key_Yes_Backup_Yes.set(rs12.getString(8));
		        		hpsm.Key_Yes_Backup_No.set(rs12.getString(9));
		        				        				        						        				            
			            list.add(hpsm);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6929!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
		/**
		 * ��������� ��������� ����� ��������� 
		 * @return
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<String> _select_num()
		{
			ObservableList<String> list = FXCollections.observableArrayList();
			
			try {
				String query = "select HMMR_Material_Num, SP_MU_Description_RUS from hmmr_sp_db where CHAR_LENGTH(HMMR_Material_Num) < 13 AND del_rec = 0 ORDER BY HMMR_Material_Num;";
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	//typepm_model_inst tpm = new typepm_model_inst();
		        	if(rs6.getString(1) != null) {
		        		//tpm.settypepm(rs6.getString(1));
		        		String instr = rs6.getString(1) + " - " + rs6.getString(2);			        					            
			            list.add(instr);
		        	}    
		        }
//		        System.out.println("SELECT WORKED DATA: "+list);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 6971!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
//			System.out.println("ARRAYLIST: "+list);
			return list;
		}
		@SuppressWarnings("static-access")
		public void _insert_parts( String ID_EQUIP, String Drawing, String Position_On_Drawing, String Key_No_Backup_Yes, String Key_No_Backup_No, String Key_Yes_Backup_Yes, String Key_Yes_Backup_No, String ID_HMMR)
		{
			String query = "INSERT INTO hmmr_parts_spec (ID_EQUIP, Drawing, Position_On_Drawing, Key_No_Backup_Yes, Key_No_Backup_No, Key_Yes_Backup_Yes, Key_Yes_Backup_No, ID_HMMR) "
					+ "VALUES ("+"'"+ID_EQUIP+"'"+","+"'"+Drawing+"'"+","+"'"+Position_On_Drawing+"'"+","+"'"+Key_No_Backup_Yes+"'"+","+"'"+Key_No_Backup_No+"'"+","+"'"+Key_Yes_Backup_Yes+"'"+","+"'"+Key_Yes_Backup_No+"'"+","+"'"+ID_HMMR+"'"+");";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7001!");
			}
		   	finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
		/**
		 * �������� ����� Parts Specification
		 * @param id - id ������ � Parts Specification
		 * @return - ���������� ����� ������
		 */
		@SuppressWarnings("static-access")
		public String _select_for_update_parts(String id)
		{
			String total_rez_upd_parts = "NULL";
			try {
				String query = "select ID_HMMR, ID_EQUIP, Drawing, Position_On_Drawing, Key_No_Backup_Yes, Key_No_Backup_No, Key_Yes_Backup_Yes, Key_Yes_Backup_No from hmmr_parts_spec where id = "+"'"+id+"'"+";";
				//String query = "SELECT hps.ID_HMMR, hps.ID_EQUIP, hps.Drawing, hps.Position_On_Drawing, hps.Key_No_Backup_Yes, hps.Key_No_Backup_No, hps.Key_Yes_Backup_Yes, hps.Key_Yes_Backup_No FROM hmmr_parts_spec hps INNER JOIN hmmr_plant_structure fl ON fl.status = 0 AND fl.id = hps.ID_EQUIP INNER JOIN hmmr_sp_db hsd ON hsd.id = hps.id WHERE hps.del_rec = 0";
				
				String HMMR_Material_Num = "NULL", Equipment = "NULL", Drawing = "NULL", Position_On_Drawing = "NULL", Key_No_Backup_Yes = "NULL", Key_No_Backup_No = "NULL", Key_Yes_Backup_Yes = "NULL", Key_Yes_Backup_No = "NULL"; 
				
				cn.ConToDb();
				stmt9 = cn.con.createStatement();
				rs9 = stmt9.executeQuery(query);
				//log.log(Level.INFO, "CHANNEL WAS FOUND");
		        while (rs9.next()) {
		        	HMMR_Material_Num = rs9.getString(1);
		        	Equipment = rs9.getString(2);
		        	Drawing = rs9.getString(3);
		        	Position_On_Drawing = rs9.getString(4);
		        	Key_No_Backup_Yes = rs9.getString(5);
		        	Key_No_Backup_No = rs9.getString(6);
		        	Key_Yes_Backup_Yes = rs9.getString(7);
		        	Key_Yes_Backup_No = rs9.getString(8);
		        	
		        }
		        total_rez_upd_parts = HMMR_Material_Num+";"+Equipment+";"+Drawing+";"+Position_On_Drawing+";"+Key_No_Backup_Yes+";"+Key_No_Backup_No+";"+Key_Yes_Backup_Yes+";"+Key_Yes_Backup_No;
//		        System.out.println("SELECT WORKED: "+total_rez);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7030!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt9.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs9.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return total_rez_upd_parts;
		}
		/**
		 * �������� ������ � Parts Specification
		 * @param Id
		 */
		@SuppressWarnings("static-access")
		public void _update_parts(String Id, String ID_EQUIP, String Drawing, String Position_On_Drawing, String Key_No_Backup_Yes, String Key_No_Backup_No, String Key_Yes_Backup_Yes, String Key_Yes_Backup_No, String ID_HMMR)
		{
			String query = "UPDATE hmmr_parts_spec SET ID_EQUIP = "+"'"+ID_EQUIP+"'"+",Drawing = "+"'"+Drawing+"'"+",Position_On_Drawing = "+"'"+Position_On_Drawing+"'"+",Key_No_Backup_Yes = "+"'"+Key_No_Backup_Yes+"'"+",Key_No_Backup_No = "+"'"+Key_No_Backup_No+"'"+",Key_Yes_Backup_Yes = "+"'"+Key_Yes_Backup_Yes+"'"+",Key_Yes_Backup_No = "+"'"+Key_Yes_Backup_No+"'"+",ID_HMMR = "+"'"+ID_HMMR+"'"+" where id = "+"'"+Id+"'"+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7070");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		/**
		 * ������� ������ �� ������� �� hmmr_parts_spec
		 * @param id
		 */
		@SuppressWarnings("static-access")
		public void _update_deleterec_parts(String id)
		{
			String query = "UPDATE hmmr_parts_spec SET del_rec = 1 WHERE id = "+"'"+id+"'"+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7094!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		/**
		 * �������� �������� ��������� �� id 
		 * @param id - id ������ � CS
		 * @return - ���������� ������
		 */
		@SuppressWarnings({ "static-access"})
		public String _select_fillcs(String field_name)
		{
			String list = "null";
			
			try {
				String query = "select hsd.SP_MU_Description_RUS from hmmr_sp_db hsd INNER JOIN hmmr_comp_spec hcs ON hcs.del_rec = 0 AND hsd.id = "+field_name+";"; // AND hsd.id = "+"'"+id+"'"+"
				
				cn.ConToDb();
				stmt11 = cn.con.createStatement();
				rs11 = stmt11.executeQuery(query);
							
		        while (rs11.next()) {
		        	if(rs11.getString(1) != null) {
		        		list = rs11.getString(1);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7122!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt11.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs11.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
		/**
		 * ��������� ������� ������� ����� CompSpec
		 * @return
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<Hmmr_CS_Model> _select_data_cs()
		{
			ObservableList<Hmmr_CS_Model> list = FXCollections.observableArrayList();
			
			try {
				//String query = "SELECT id, ID_HMMR_COMPLEX, ID_HMMR_SUB FROM hmmr_comp_spec WHERE del_rec = 0 GROUP BY id;";
				String query = "SELECT hcs.id, hsd.SP_MU_Description_RUS, hcs.ID_HMMR_SUB FROM hmmr_comp_spec hcs INNER JOIN hmmr_sp_db hsd ON hsd.del_rec = 0 AND hsd.id = hcs.ID_HMMR_COMPLEX";			
				cn.ConToDb();
				stmt12 = cn.con.createStatement();
				rs12 = stmt12.executeQuery(query);
				 			
		        while (rs12.next()) {
		        	Hmmr_CS_Model hcsm = new Hmmr_CS_Model();
		        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
		        		hcsm.Id.set(rs12.getString(1));
		        		hcsm.ID_HMMR_COMPLEX.set(rs12.getString(2));
		        		hcsm.ID_HMMR_SUB.set(rs12.getString(3));
		        			        				        				        						        				            
		        		list.add(hcsm);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7155!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
		/**
		 * ��������� ������� ������� ����� CompSpec � ����������� �� �������
		 * @param field - ��� ���� ��� ����������
		 * @param field1 - �������� �� �������� ���������
		 * @return
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<Hmmr_CS_Model> _select_data_cs_sort(String field, String field1)
		{
			ObservableList<Hmmr_CS_Model> list = FXCollections.observableArrayList();
			
			try {
				String query = "select id, ID_HMMR_COMPLEX, ID_HMMR_SUB from hmmr_comp_spec where del_rec = 0 AND "+field+" = "+"'"+field1+"'"+" GROUP BY id;";
							
				cn.ConToDb();
				stmt12 = cn.con.createStatement();
				rs12 = stmt12.executeQuery(query);
				 			
		        while (rs12.next()) {
		        	Hmmr_CS_Model hcsm = new Hmmr_CS_Model();
		        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
		        		hcsm.Id.set(rs12.getString(1));
		        		hcsm.ID_HMMR_COMPLEX.set(rs12.getString(2));
		        		hcsm.ID_HMMR_SUB.set(rs12.getString(3));
		        			        				        				        						        				            
		        		list.add(hcsm);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7193!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
		/**
		 * ��������� ������� ������� ����� CompSpec � ����������� �� ��������
		 * @param field1 - �������� �� �������� ���������
		 * @return
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<Hmmr_CS_Model> _select_data_cs_sort(String field1)
		{
			ObservableList<Hmmr_CS_Model> list = FXCollections.observableArrayList();
			
			try {
				String query = "select hcs.id, hcs.ID_HMMR_COMPLEX, hcs.ID_HMMR_SUB from hmmr_sp_db hsd INNER JOIN hmmr_comp_spec hcs ON hcs.del_rec = 0 AND hsd.SP_MU_Description_RUS = "+"'"+field1+"'"+" AND hsd.id = hcs.ID_HMMR_COMPLEX GROUP BY hcs.id;";
							
				cn.ConToDb();
				stmt12 = cn.con.createStatement();
				rs12 = stmt12.executeQuery(query);
				 			
		        while (rs12.next()) {
		        	Hmmr_CS_Model hcsm = new Hmmr_CS_Model();
		        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
		        		hcsm.Id.set(rs12.getString(1));
		        		hcsm.ID_HMMR_COMPLEX.set(rs12.getString(2));
		        		hcsm.ID_HMMR_SUB.set(rs12.getString(3));
		        			        				        				        						        				            
		        		list.add(hcsm);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7231!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
		@SuppressWarnings("static-access")
		public void _insert_cs(String HMMR_Material_Num_Complex, String HMMR_Material_Num_Sub)
		{
			String query = "INSERT INTO hmmr_comp_spec (ID_HMMR_COMPLEX, ID_HMMR_SUB) "
					+ "VALUES ("+"'"+HMMR_Material_Num_Complex+"'"+","+"'"+HMMR_Material_Num_Sub+"'"+");";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7261!");
			}
		   	finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
		/**
		 * �������� ������ � CS
		 * @param Id
		 */
		@SuppressWarnings("static-access")
		public void _update_cs(String Id, String HMMR_Material_Num_Complex, String HMMR_Material_Num_Sub)
		{
			String query = "UPDATE hmmr_comp_spec SET ID_HMMR_COMPLEX = "+"'"+HMMR_Material_Num_Complex+"'"+",ID_HMMR_SUB = "+"'"+HMMR_Material_Num_Sub+"'"+" where id = "+"'"+Id+"'"+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7287");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		
		/**
		 * ��������� ������� �������� ����� ��� �� �������� Components Specification
		 * @param id - id ������ � Parts Specification
		 * @return - ���������� ����� ������
		 */
		@SuppressWarnings("static-access")
		public String _select_for_update_cs(String id)
		{
			String total_rez_upd_�s = "NULL";
			try {
				String query = "select ID_HMMR_COMPLEX, ID_HMMR_SUB from hmmr_comp_spec where id = "+"'"+id+"'"+";";
								
				String HMMR_Material_Num_Complex = "NULL", SP_MU_Description_RUS = "NULL", ID_HMMR_COMPLEX = "NULL", ID_HMMR_SUB = "NULL"; 
				
				cn.ConToDb();
				stmt9 = cn.con.createStatement();
				rs9 = stmt9.executeQuery(query);
				
				//log.log(Level.INFO, "CHANNEL WAS FOUND");
		        while (rs9.next()) {
		        	ID_HMMR_COMPLEX = rs9.getString(1);
		        	ID_HMMR_SUB = rs9.getString(2);
		        			        	
		        }
		        String query1 = "select HMMR_Material_Num, SP_MU_Description_RUS from hmmr_sp_db where id = "+"'"+ID_HMMR_COMPLEX+"'"+";";
		        stmt10 = cn.con.createStatement();
		        rs10 = stmt10.executeQuery(query1);
		        while (rs10.next())
		        	HMMR_Material_Num_Complex = rs10.getString(1) + " - " + rs10.getString(2); 
		        
		        String query2 = "select HMMR_Material_Num, SP_MU_Description_RUS from hmmr_sp_db where id = "+"'"+ID_HMMR_SUB+"'"+";";
		        stmt11 = cn.con.createStatement();
		        rs11 = stmt11.executeQuery(query2);
		        while (rs11.next())
		        	SP_MU_Description_RUS = rs11.getString(1) + " - " + rs11.getString(2);; 
		        
		        total_rez_upd_�s = HMMR_Material_Num_Complex+";"+SP_MU_Description_RUS;
//		        System.out.println("SELECT WORKED: "+total_rez);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7315!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt9.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs9.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt10.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs10.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt11.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs11.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return total_rez_upd_�s;
		}
		/**
		 * ������� ������ �� ������� �� hmmr_comp_spec
		 * @param id
		 */
		@SuppressWarnings("static-access")
		public void _update_deleterec_cs(String id)
		{
			String query = "UPDATE hmmr_comp_spec SET del_rec = 1 WHERE id = "+"'"+id+"'"+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7365!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		/**
		 * �������� �������� id ������ �� ������� hmmr_part_characteristic 
		 * @param id
		 * @return
		 */
		@SuppressWarnings("static-access")
		public String _select_for_pchar(String field1, String field2)
		{
			String id = "NULL"; 
			try {
				String query = "select id from hmmr_part_characteristic where Part_Type = "+"'"+field1+"'"+" AND Part_Sub_Type = "+"'"+field2+"'"+";";
				
				cn.ConToDb();
				stmt9 = cn.con.createStatement();
				rs9 = stmt9.executeQuery(query);
				//log.log(Level.INFO, "CHANNEL WAS FOUND");
		        while (rs9.next()) {
		        	id = rs9.getString(1);
		        }
		       
//		        System.out.println("SELECT WORKED: "+total_rez);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7392!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt9.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs9.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return id;
		}
		
		@SuppressWarnings("static-access")
		public void _select_test(String id)
		{
			int i = 1;
			try {
				String query = "select HMMR_Material_Num, id from hmmr_sp_db;";//"+"'"+id+"'"+";"; where id = 1
				
				String HMMR_Material_Num = "NULL", rez = "NULL", id_id, field, field1; 
				
				cn.ConToDb();
				stmt9 = cn.con.createStatement();
				stmt11 = cn.con.createStatement();
				stmt12 = cn.con.createStatement();
				stmt14 = cn.con.createStatement();
				rs9 = stmt9.executeQuery(query);
				//log.log(Level.INFO, "CHANNEL WAS FOUND");
		        while (rs9.next()) {
		        	HMMR_Material_Num = rs9.getString(1);
		        	id_id = rs9.getString(2);
		        	String sub_num = HMMR_Material_Num.substring(0, 5);
		        	if(sub_num.equals("MTCSP")) {
		        		//String sub_num1 = HMMR_Material_Num.substring(6, HMMR_Material_Num.length());
		        		//rez = "MTCSP"+sub_num1;
		        		if(i < 10)
		        			rez = "MTCSP"+"0000000"+i;
		        		if(i >= 10 && i < 100)
		        			rez = "MTCSP"+"000000"+i;
		        		if(i >= 100 && i < 1000)
		        			rez = "MTCSP"+"00000"+i;
		        		if(i >= 1000 && i < 10000)
		        			rez = "MTCSP"+"0000"+i;
		        		if(i >= 10000 && i < 100000)
		        			rez = "MTCSP"+"000"+i;
		        		field = "ID_HMMR_SUB";
		        		field1 ="HMMR_Material_Num_Sub";
		        	}
		        	else {
		        		//String sub_num1 = HMMR_Material_Num.substring(4, HMMR_Material_Num.length());
		        		if(i < 10)
		        			rez = "MTC"+"000000"+i;
		        		if(i >= 10 && i < 100)
		        			rez = "MTC"+"00000"+i;
		        		if(i >= 100 && i < 1000)
		        			rez = "MTC"+"0000"+i;
		        		if(i >= 1000 && i < 10000)
		        			rez = "MTC"+"000"+i;
		        		if(i >= 10000 && i < 100000)
		        			rez = "MTC"+"00"+i;
		        		field = "ID_HMMR_COMPLEX";
		        		field1 = "HMMR_Material_Num_Complex";
		        	}
		        	String query1 = "UPDATE hmmr_sp_db SET HMMR_Material_Num = "+"'"+rez+"'"+" where id = "+"'"+id_id+"'"+";";
		        	stmt11.executeUpdate(query1);
		        	String query2 = "UPDATE hmmr_comp_spec SET "+field+" = "+"'"+id_id+"'"+" where "+field1+" = "+"'"+HMMR_Material_Num+"'"+";";
		        	stmt12.executeUpdate(query2);
		        	String query3 = "UPDATE hmmr_parts_spec SET ID_HMMR = "+"'"+id_id+"'"+" where HMMR_Material_Num = "+"'"+HMMR_Material_Num+"'"+";";
		        	stmt14.executeUpdate(query3);
		        	
		        	i = i + 1;
		        }
		        
//		        System.out.println("SELECT WORKED: "+total_rez);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7420!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt9.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt11.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt14.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs9.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			
		}
		@SuppressWarnings("static-access")
		public void _select_test1()
		{
			//int i = 1;
			try {
				String query = "select Equipment, id from hmmr_parts_spec3;";//"+"'"+id+"'"+";"; where id = 1
				
				String Equipment = "NULL", id_id; 
				
				cn.ConToDb();
				stmt9 = cn.con.createStatement();
				stmt11 = cn.con.createStatement();
				stmt12 = cn.con.createStatement();
				stmt14 = cn.con.createStatement();
				rs9 = stmt9.executeQuery(query);
				//log.log(Level.INFO, "CHANNEL WAS FOUND");
		        while (rs9.next()) {
		        	Equipment = rs9.getString(1);
		        	id_id = rs9.getString(2);
		        	
		        	String shop = s_class.parser_str_str(Equipment, 0);
		        	String line = s_class.parser_str_str(Equipment, 2);
		        	String os = s_class.parser_str_str(Equipment, 3);
		        	String equip = s_class.parser_str_str(Equipment, 4);
		        	
		        	String query1 = "UPDATE hmmr_parts_spec3 hp, hmmr_plant_structure hps SET hp.ID_EQUIP = hps.id WHERE hps.FL03_Shop_s = "+"'"+shop+"'"+" AND hps.FL05_Line_s = "+"'"+line+"'"+" AND hps.FL06_Station_s = "+"'"+os+"'"+" AND hps.FL07_Equipment_s = "+"'"+equip+"'"+" AND hp.id = "+"'"+id_id+"'"+";";
		        	stmt11.executeUpdate(query1);
		        }
		        
//		        System.out.println("SELECT WORKED: "+total_rez);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7496!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt9.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt11.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt14.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs9.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			
		}
		/**
		 * ��������� ��������� ����� ��������� 
		 * @return
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<String> _select_num_cs()
		{
			ObservableList<String> list = FXCollections.observableArrayList();
			
			try {
				String query = "select HMMR_Material_Num, SP_MU_Description_RUS from hmmr_sp_db where CHAR_LENGTH(HMMR_Material_Num) < 11 AND del_rec = 0 ORDER BY HMMR_Material_Num;";
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	//typepm_model_inst tpm = new typepm_model_inst();
		        	if(rs6.getString(1) != null) {
		        		//tpm.settypepm(rs6.getString(1));
		        		String instr = rs6.getString(1) + " - " + rs6.getString(2);			        					            
			            list.add(instr);
		        	}    
		        }
//		        System.out.println("SELECT WORKED DATA: "+list);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7545!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
//			System.out.println("ARRAYLIST: "+list);
			return list;
		}
		/**
		 * ��������� ��������� ����� ��������� 
		 * @return
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<String> _select_num_cs1()
		{
			ObservableList<String> list = FXCollections.observableArrayList();
			
			try {
				String query = "select HMMR_Material_Num, SP_MU_Description_RUS from hmmr_sp_db where CHAR_LENGTH(HMMR_Material_Num) = 13 AND del_rec = 0 ORDER BY HMMR_Material_Num;";
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	//typepm_model_inst tpm = new typepm_model_inst();
		        	if(rs6.getString(1) != null) {
		        		//tpm.settypepm(rs6.getString(1));
		        		String instr = rs6.getString(1) + " - " + rs6.getString(2);			        					            
			            list.add(instr);
		        	}    
		        }
//		        System.out.println("SELECT WORKED DATA: "+list);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7582!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
//			System.out.println("ARRAYLIST: "+list);
			return list;
		}
		/**
		 * �������� �������� id ������ �� ����� ������� 
		 * @param tbl_name - ��� �������
		 * @param field_name - ��� ���� �� �������� ����
		 * @param field_value - �������� ��� ����� ����
		 * @return - ���������� ���������� id
		 */
		@SuppressWarnings("static-access")
		public String _select_id(String tbl_name, String field_name, String field_value)
		{
			String id = "NULL"; 
			try {
				String query = "select id from "+tbl_name+" where "+field_name+" = "+"'"+field_value+"'"+" AND del_rec = 0;";
				
				cn.ConToDb();
				stmt9 = cn.con.createStatement();
				rs9 = stmt9.executeQuery(query);
				//log.log(Level.INFO, "CHANNEL WAS FOUND");
		        while (rs9.next()) {
		        	id = rs9.getString(1);
		        }
		       
//		        System.out.println("SELECT WORKED: "+total_rez);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7621!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt9.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs9.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return id;
		}
		/**
		 * ������ ��� ���������� ComboBox ���������, � ����� ����������� ��� group by
		 * ������ � ����� ������� ���� ���� ����� ��� �������
		 * @param tbl_name - ��� ������� �� ������� ����� ������ ��� ���������� ComboBox
		 * @param str - ��� ����, �������� �������� ���� �������� � ComboBox
		 * @return - ���������� ���������� ��������
		 */			
		@SuppressWarnings({ "static-access"})
		public ObservableList<String> _select_name(String tbl_name, String field_name, String field_name1)
		{
			ObservableList<String> list = FXCollections.observableArrayList();
			
			try {
				String query = "select "+field_name+", "+field_name1+" from "+tbl_name+";";
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	if(rs6.getString(1) != null) {
		        		String tpm = rs6.getString(1) + " - " + rs6.getString(2);			        					            
			            list.add(tpm);
		        	}    
		        }

			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7656!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
		       }

			return list;
		}
		/**
		 * �������� ����� ��������� �� id 
		 * @param id - id ������ � SD
		 * @return - ���������� ������
		 */
		@SuppressWarnings({ "static-access"})
		public String _select_matnum(String id, String field_name, String tbl_name)
		{
			String list = "null";
			
			try {
				String query = "select "+field_name+" from "+tbl_name+" where del_rec = 0 AND id = "+id+";"; // AND hsd.id = "+"'"+id+"'"+"
				
				cn.ConToDb();
				stmt11 = cn.con.createStatement();
				rs11 = stmt11.executeQuery(query);
							
		        while (rs11.next()) {
		        	if(rs11.getString(1) != null) {
		        		list = rs11.getString(1);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7692!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt11.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs11.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
		/**
		 * �������� id �� ������ ��������� 
		 * @param MatNum - MatNum ����� ��������� � SD
		 * @return - ���������� ������
		 */
		@SuppressWarnings({ "static-access"})
		public String _select_matnum_id(String MatNum)
		{
			String list = "null";
			
			try {
				String query = "select id from hmmr_sp_db where del_rec = 0 AND HMMR_Material_Num = "+"'"+MatNum+"'"+";"; // AND hsd.id = "+"'"+id+"'"+"
				
				cn.ConToDb();
				stmt11 = cn.con.createStatement();
				rs11 = stmt11.executeQuery(query);
							
		        while (rs11.next()) {
		        	if(rs11.getString(1) != null) {
		        		list = rs11.getString(1);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7725!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt11.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs11.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
		/**
		 * �������� ��������� � ������ ����� ��������� ������������ � MTC 
		 * @return - ���������� ������
		 */
		@SuppressWarnings({ "static-access"})
		public String _select_last_matnum(int size)
		{
			String list = "null";
			
			try {
				String query = "SELECT HMMR_Material_Num FROM `hmmr_sp_db` WHERE CHAR_LENGTH(HMMR_Material_Num) < "+size+" ORDER BY `HMMR_Material_Num`;"; // AND hsd.id = "+"'"+id+"'"+"
				
				cn.ConToDb();
				stmt11 = cn.con.createStatement();
				rs11 = stmt11.executeQuery(query);
							
		        while (rs11.next()) {
		        	if(rs11.getString(1) != null) {
		        		list = rs11.getString(1);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7757!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt11.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs11.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
		/**
		 * ������������ ���������� ����������� � ���������� �� ������������
		 * @return - ���������� ������
		 */
		@SuppressWarnings("static-access")
		public ObservableList<String> _select_qty_shop()
		{
			ObservableList<String> list = FXCollections.observableArrayList();
			
			try {
				String query = "SELECT cs.ID_HMMR_SUB, fl.FL03_Shop_s FROM hmmr_comp_spec cs JOIN hmmr_parts_spec ps ON ps.ID_HMMR = cs.ID_HMMR_COMPLEX JOIN hmmr_plant_structure fl ON fl.id = ps.ID_EQUIP WHERE cs.ID_HMMR_COMPLEX <> 0 AND cs.ID_HMMR_SUB <> 0 GROUP BY ps.ID_HMMR ORDER BY ps.ID_EQUIP ASC;";
				
				cn.ConToDb();
				stmt6 = cn.con.createStatement();
				rs6 = stmt6.executeQuery(query);
							
		        while (rs6.next()) {
		        	//typepm_model_inst tpm = new typepm_model_inst();
		        	if(rs6.getString(1) != null) {
		        		//tpm.settypepm(rs6.getString(1));
		        		String instr = rs6.getString(1) + ";" + rs6.getString(2);			        					            
			            list.add(instr);
		        	}    
		        }
//		        System.out.println("SELECT WORKED DATA: "+list);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7789!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
//			System.out.println("ARRAYLIST: "+list);
			return list;
		}
		/**
		 * �������� ������� �� hmmr_sp_db, �� ������ ���� ���������� �� �����
		 * @param id
		 */
		@SuppressWarnings("static-access")
		public void _update_qty_shop(String id, String Qty_S_Field, String Qty_S_Value)
		{
			String query = "UPDATE hmmr_sp_db SET "+Qty_S_Field+" = "+"'"+Qty_S_Value+"'"+" WHERE id = "+"'"+id+"'"+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7823!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			
		}
		
		/**
		 * ��������� ������ � ������� hmmr_pm
		 * @param id - id ������ ������� ���� ��������������
		 */
		@SuppressWarnings("static-access")
		public void _insert_psc_dup(String id)
		{
			String query = "INSERT INTO hmmr_parts_spec (ID_EQUIP, Drawing, Position_On_Drawing, Key_No_Backup_Yes, Key_No_Backup_No, Key_Yes_Backup_Yes, Key_Yes_Backup_No, ID_HMMR) "
					+ "SELECT ID_EQUIP, Drawing, Position_On_Drawing, Key_No_Backup_Yes, Key_No_Backup_No, Key_Yes_Backup_Yes, Key_Yes_Backup_No, ID_HMMR FROM hmmr_parts_spec WHERE id = "+"'"+id+"'"+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7849!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		/**
		 * �������� ������� �� hmmr_sp_db, �� ������ ���� ���������� �� �����
		 * @param id
		 */
		@SuppressWarnings("static-access")
		public void _update_key_backup()
		{
			//String query = "UPDATE hmmr_sp_db AS hsd, hmmr_parts_spec AS hps SET hsd.Key_No_Backup_Yes = hps.Key_No_Backup_Yes, hsd.Key_No_Backup_No = hps.Key_No_Backup_No, hsd.Key_Yes_Backup_Yes = hps.Key_Yes_Backup_Yes, hsd.Key_Yes_Backup_No = hps.Key_Yes_Backup_No WHERE hsd.id = hps.ID_HMMR;";
			String query = "UPDATE hmmr_sp_db hsd SET hsd.Key_No_Backup_Yes = (SELECT IF(hps1.Key_No_Backup_Yes = 1,COUNT(hps1.Key_No_Backup_Yes),0) FROM hmmr_parts_spec hps1 WHERE hps1.ID_HMMR = hsd.id AND hsd.del_rec = 0 GROUP BY hsd.HMMR_Material_Num), hsd.Key_No_Backup_No = (SELECT IF(hps1.Key_No_Backup_No = 1,COUNT(hps1.Key_No_Backup_No),0) FROM hmmr_parts_spec hps1 WHERE hps1.ID_HMMR = hsd.id AND hsd.del_rec = 0 GROUP BY hsd.HMMR_Material_Num), hsd.Key_Yes_Backup_Yes = (SELECT IF(hps1.Key_Yes_Backup_Yes = 1,COUNT(hps1.Key_Yes_Backup_Yes),0) FROM hmmr_parts_spec hps1 WHERE hps1.ID_HMMR = hsd.id AND hsd.del_rec = 0 GROUP BY hsd.HMMR_Material_Num), hsd.Key_Yes_Backup_No = (SELECT IF(hps1.Key_Yes_Backup_No = 1,COUNT(hps1.Key_Yes_Backup_No),0) FROM hmmr_parts_spec hps1 WHERE hps1.ID_HMMR = hsd.id AND hsd.del_rec = 0 GROUP BY hsd.HMMR_Material_Num)";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7877!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			
		}
		/**
		 * ��������� ������� ������� ��� �������� ���� �� ���� ��������->�����->SP - ��������
		 * @return - ���������� ����� ������
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<Hmmr_SP_Model> _select_data_sp_sort(String manuf)
		{
			ObservableList<Hmmr_SP_Model> list = FXCollections.observableArrayList();
			
			try {
				
				String query = "SELECT id, HMMR_Material_Num, Manufacturer, Model, Article, Single_Complex_Sub, SP_MU_Description_RUS, SP_FD_Description, SP_Supplier_Description, Qty_S, Qty_W, Qty_P, Qty_A, Price, Key_No_Backup_Yes, Key_No_Backup_No, Key_Yes_Backup_Yes, Key_Yes_Backup_No, Risk_Breakage, Delivery_Time, Replacement_Model, Qty_Interchangeability, Qty_Identify_SP, Identity_SP, Coefficient, MIN, BATCH FROM hmmr_sp_db WHERE del_rec = 0 AND Manufacturer = "+"'"+manuf+"'"+";";
				
				cn.ConToDb();
				stmt12 = cn.con.createStatement();
				rs12 = stmt12.executeQuery(query);
				 			
		        while (rs12.next()) {
		        	Hmmr_SP_Model hsm = new Hmmr_SP_Model();
		        	if(rs12.getString(1) != null && rs12.getString(2) != null && rs12.getString(3) != null) {
		        		hsm.Id.set(rs12.getString(1));
		        		hsm.HMMR_Material_Num.set(rs12.getString(2));
		        		hsm.Manufacturer.set(rs12.getString(3));
		        		hsm.Model.set(rs12.getString(4));
		        		hsm.Article.set(rs12.getString(5));
		        		hsm.Single_Complex_Sub.set(rs12.getString(6));
		        		hsm.SP_MU_Description_RUS.set(rs12.getString(7));
		        		hsm.SP_FD_Description.set(rs12.getString(8));
		        		hsm.SP_Supplier_Description.set(rs12.getString(9));
		        		hsm.Qty_S.set(rs12.getString(10));
		        		hsm.Qty_W.set(rs12.getString(11));
		        		hsm.Qty_P.set(rs12.getString(12));
		        		hsm.Qty_A.set(rs12.getString(13));
		        		hsm.Price.set(rs12.getString(14));
		        		hsm.Key_No_Backup_Yes.set(rs12.getString(15));
		        		hsm.Key_No_Backup_No.set(rs12.getString(16));
		        		hsm.Key_Yes_Backup_Yes.set(rs12.getString(17));
		        		hsm.Key_Yes_Backup_No.set(rs12.getString(18));
		        		hsm.Risk_Breakage.set(rs12.getString(19));
		        		hsm.Delivery_Time.set(rs12.getString(20));
		        		hsm.Replacement_Model.set(rs12.getString(21));
		        		hsm.Qty_Interchangeability.set(rs12.getString(22));
		        		hsm.Qty_Identify_SP.set(rs12.getString(23));
		        		hsm.Identity_SP.set(rs12.getString(24));
		        		hsm.Coefficient.set(rs12.getString(25));
		        		hsm.MIN.set(rs12.getString(26));
		        		hsm.BATCH.set(rs12.getString(27));
		        				        						        				            
			            list.add(hsm);
		        	}    
		        }
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7911!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs12.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
		/**
		 * �������� ������ � SP DB ����� ������� ������ ��� ������� ������ ��������� ������(MTC ��� MTCSP), �������� ����� ���� �� ��������� ���� ���������� ������� � ��������
		 * ���������� �����, ���� ��� ���-�� ���� ����� ��������� ��� �� �����
		 * @param Id
		 */
		@SuppressWarnings("static-access")
		public void _update_sp_ins(String HMMR_Material_Num, String Manufacturer, String Model, String Article, String Single_Complex_Sub, String SP_MU_Description_RUS, String SP_FD_Description, String SP_Supplier_Description, String ID_Pchar, String Price, String Risk_Breakage, String Delivery_Time, String Replacement_Model, String Identity_SP, String Coefficient)
		{
			String query = "UPDATE hmmr_sp_db SET Manufacturer = "+"'"+Manufacturer+"'"+",Model = "+"'"+Model+"'"+",Article = "+"'"+Article+"'"+",Single_Complex_Sub = "+"'"+Single_Complex_Sub+"'"+",SP_MU_Description_RUS = "+"'"+SP_MU_Description_RUS+"'"+",SP_FD_Description = "+"'"+SP_FD_Description+"'"+",SP_Supplier_Description = "+"'"+SP_Supplier_Description+"'"+",ID_Pchar = "+"'"+ID_Pchar+"'"+",Price = "+"'"+Price+"'"+",Risk_Breakage = "+"'"+Risk_Breakage+"'"+",Delivery_Time = "+"'"+Delivery_Time+"'"+",Replacement_Model = "+"'"+Replacement_Model+"'"+",Identity_SP = "+"'"+Identity_SP+"'"+",Coefficient = "+"'"+Coefficient+"'"+" where HMMR_Material_Num = "+"'"+HMMR_Material_Num+"'"+";"; //"UPDATE pm_inst SET Type_PM = "+"'"+type+"'"+", Description = "+"'"+desc+"'"+" WHERE id = "+"'"+id+"'"+";";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "STATUS RING WAS UPDATED");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 7970");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		/**
		 * �������� ����� ������ �� ���� � ���
		 */
		@SuppressWarnings("static-access")
		public void _update_week_year(int group)
		{
			try {
				cn.ConToDb();
				stmt9 = cn.con.createStatement();
				stmt11 = cn.con.createStatement();
				stmt12 = cn.con.createStatement();
				stmt14 = cn.con.createStatement();
				
				String query = "UPDATE `hmmr_pm_year` SET `Week_Num` = WEEK(`data`, 1) WHERE `PM_Group` = "+group+";";
				stmt9.executeUpdate(query);
				String query1 = "UPDATE `hmmr_pm_year` SET `Year_Num` = YEAR(`data`) WHERE `PM_Group` = "+group+";";
		        stmt11.executeUpdate(query1);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ������ � ������ � 8001!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt9.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt11.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt12.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt14.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs9.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			
		}
}