package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

import action.hmmr_ap_model;
import action.hmmr_inst_model;
import action.hmmr_pm_model;
import action.hmmr_pmplan_model;
import action.hmmr_ps_model;
import action.hmmr_wr_model;
import application.conn_connector;
import dir.Cycle;
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
    
    public String id_user = null, passwd_user = null, role_user, total_rez = null, type_c, pereodic_c, weeks_c, hours_c, bdate_c, edate_c, type_tpm, desc_tpm, total_rez_tpm, total_rez_inst,
    			  weeks_inst, hours_inst, pereodic_inst, total_rez_upd_inst;
    private String ninst_inst, ver_inst, mtt_inst, pmn_inst, tpm_inst, pmc1_inst, pmc2_inst, ool_inst, oop_inst, pos_inst, sinfo_inst, s_doc_inst, qspec_inst,
    			   pwt_inst, wt_inst, adm2_inst, adm3_inst, ofit1_inst, ofit2_inst, date_create, date_change, inst_pdf;
    private String total_rez_upd_pm, ninst_pm, eq_id, ool_pm, pm_resp, dexp_pm, group_pm;
    private String total_rez_data, pereodic_pmplan, dbegin, dend, shop_pmplan, otv_plan;
    private String pmnum_ap, type_ap, description_ap, due_date_ap, equip_ap, oft_ap, otv_ap, total_rez_upd_ap, icon;
	private String shift_report, req_action, actual_time, actual_time1,actual_time2,actual_time3,actual_time4, data, equip, record_type, work_time, resp,resp2,resp3,resp4, status_wr, qty, ap_num, total_rez_upd_wr, actual_date,actual_date_2,actual_date_3,actual_date_4, actual_date1,actual_date2,actual_date3,actual_date4, user, hours1,hours1_2,hours1_3,hours1_4, min1, hours2,hours2_2,hours2_3,hours2_4, min2, user_number; 
	private String ap_num_plan, total_apnum_rez;
	private String qty_chk = "null", total_qtychk_rez, wt_chk = "null", total_wtchk_rez;
	private String a_date, a_date1, a_hours1, a_hours2;
	private String _num_ps, _company_ps, _plant_ps, _shops_ps, _groups_ps, _lines_ps, _oss_ps, _equips_ps, _shop_ps, _line_ps, _linerus_ps,
					_os_ps, _osrus_ps, _equip_ps, _description_ps, _manual_ps, _stsupplier_ps, _location_ps,
					_coord_ps, _alt_ps, _cham_ps, _trcu_ps, _trcul_ps, _hazardous_ps, _keyequip_ps, _type_ps, _sn_ps, _manuf_ps, _mtc_ps, _respons_ps,
					_melec_ps, _mair_ps, _mwater_ps, _mcwater_ps, _mhwater_ps, _rowater_ps, _mgas_ps, total_rez_ps, _group_eng, _shop_rus,
					_group_rus, _equip_rus, _group_otv, _inv_num, _os_num, _start_date, _cost_centre;
	private String lst_id;
	private String numpm_pmplan, data_pmplan, oft_pmplan, total_rez_pmplan,pm_group;
	private String id_prior, name_prior, desc_prior, icon_prior, total_rez_prior;
	private String del_rec = "0";

            
	public _query() {
		
	}
	/**
	 * Получаем логин, пароль и права пользователя из таблицы users при авторизации. Будем 
	 * использовать эту информацию на протяжении всего времени выполнения
	 * программы
	 * @param login - Логин пользователя введенный им при регистрации
	 * @return - id - id пользователя, passwd -пароль пользователя, role - 
	 * 			 права пользователя на определенные действия в программе.
	 */
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 61!");
        } finally {

        	try { _connect.con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
		return total_rez;
	}
	/**
	 * Заполняем таблицу данными при открытии окна справочника -
	 * pm_cycle
	 * @return - возвращает набор данных ObservableList, которые
	 * 			 используются для заполнения таблицы TableView. 
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 97!");
        } finally {
            //close connection ,stmt and resultset here
        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt1.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs1.close(); } catch(SQLException se) { /*can't do anything */ }
        }

		return list;
	}
	/**
	 * Запрос для выборки только моточасов из таблицы hmmr_pm_cycle, для
	 * дальнейшего расчета в программе переодичности для прогрессивного 
	 * ППР
	 * @return - Возвращает набор данных соответствующий моточасам.
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 142!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt1.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs1.close(); } catch(SQLException se) { /*can't do anything */ }
	       }

		return list;
	}
	/**
	 * Заполняем данными таблицу при открытии окна справочника
	 * Type_PM
	 * @return - Возвращает набор данных типа ObservableList
	 * 			и заполняет ими таблицу TableView. 
	 */
	
	@SuppressWarnings({ "static-access"})
	public ObservableList<type_pm> _select_data_typepm()
	{
		ObservableList<type_pm> list = FXCollections.observableArrayList();
		
		try {
			String query = "select id,Type_PM,Description from hmmr_type_pm where del_rec = 0;";
			
			cn.ConToDb();
			stmt3 = cn.con.createStatement();
			rs3 = stmt3.executeQuery(query);
						
	        while (rs3.next()) {
	        	type_pm tpm = new type_pm();
	        	if(rs3.getString(1) != null && rs3.getString(2) != null && rs3.getString(3) != null) {
	        		tpm.Id.set(rs3.getString(1));
	        		tpm.Type.set(rs3.getString(2));
	        		tpm.Desc.set(rs3.getString(3));
	        					            
		            list.add(tpm);
	        	}    
	        }

		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 179!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt3.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs3.close(); } catch(SQLException se) { /*can't do anything */ }
	        }

		return list;
	}
	
	/**
	 * Заполняем данными таблицу при открытии окна редактирования
	 * PM Instruction
	 * @return - Возвращает набор данных типа ObservableList и 
	 * 			заполняет ими таблицу TableView. 
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 221!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt5.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs5.close(); } catch(SQLException se) { /*can't do anything */ }
	       }
		return list;
	}
	
	/**
	 * Заполняем данными таблицу при открытии окна редактирования
	 * PM
	 * @return - Возвращает набор данных типа ObservableList и 
	 * 			заполняет ими таблицу TableView.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<hmmr_pm_model> _select_data_pm()
	{
		ObservableList<hmmr_pm_model> list = FXCollections.observableArrayList();
		
		try {
			String query = "select id,Instruction_num,Eq_ID,PM_Group,PM_Resp,Date_Beforehand,OnOff_Line from hmmr_pm where del_rec = 0;";
			
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
	        		hpm.Days_Exp.set(rs11.getString(6));
	        		hpm.OnOff_Line.set(rs11.getString(7));
	        		
	        				            
		            list.add(hpm);
	        	}    
	        }
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 280!");
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
	 * Заполняем данными таблицу Action Plan при открытии окна
	 * APWR - Редактор. Причем данные выбираются только в том 
	 * случае если хотя бы один из трех возможных (Владелец,
	 * Ответственный за задачу или Исполнитель) совпадет с тем
	 * кто зарегистрировался в системе. Т. е. таблица заполнится
	 * только задачами относящимися конкретно к нему. 
	 * @param oft - Сокращенное имя пользователя зарегистрировшегося
	 * 		в программе.
	 * @return - Возвращает набор данных типа ObservableList и 
	 * 			заполняет ими таблицу TableView.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<hmmr_ap_model> _select_data_ap(String oft)
	{
		ObservableList<hmmr_ap_model> list = FXCollections.observableArrayList();
		
		try {
			String query = "select id,PM_Num,Type,Description,Due_Date,Equipment,Instruction,Otv_For_Task,Otv,Tsk_maker,flag_otv,flag_oft,flag_tm,Icon from hmmr_action_plan where (Otv_For_Task = "+"'"+oft+"'"+" OR Otv = "+"'"+oft+"'"+" OR Tsk_maker = "+"'"+oft+"'"+") AND del_rec = 0 ORDER BY FIELD(Icon, '1S', '2Q', '3P', '4M', '1') ASC;";
						
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 339!");
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
	 * Заполняем данными таблицу при открытии окна редактирования
	 * Plant Structure
	 * @return - Возвращает набор данных типа ObservableList и 
	 * 			заполняет ими таблицу TableView.
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 389!");
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
	 * Заполняем данными таблицу Work Recording при открытии окна 
	 * редактирования APWR - редактор
	 * @return - Возвращает набор данных типа ObservableList и 
	 * 			заполняет ими таблицу TableView.
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 467!");
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
	 * Заполняем данными таблицу Work Recording при открытии окна 
	 * редактирования APWR - редактор, отсортированных по дате
	 * @param begin_data - Начальная дата
	 * @param last_data - Конечная дата
	 * @return - Возвращает набор данных типа ObservableList 
	 * 			отсортированных по дате и заполняет ими 
	 * 			таблицу TableView.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<hmmr_wr_model> _select_data_wr(String begin_data, String last_data)
	{
		ObservableList<hmmr_wr_model> list = FXCollections.observableArrayList();
	
		try {
			String query = "select id,Task_Description,Task_Report,CM_DownTime,WR_Work_Time,WR_End_Date,Equipment_Full,Record_Type,Task_Resp_ID,WR_Executor_Confirmed,WR_Host_Confirmed,ap_num,user_number,WR_Resp_Confirmed from hmmr_work_recording where WR_End_Date BETWEEN "+"'"+begin_data+"'"+" AND "+"'"+last_data+"'"+";";
			
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
		            list.add(hpm);
	        	}    
	        }
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 519!");
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
	 * Заполняем данными таблицу PM Plan при открытии окна 
	 * редактирования PM Plan - редактор, отсортированных по дате
	 * (Запрос для PM Plan ,без сортировки по дате в строке - 654)
	 * @param begin_data - Начальная дата
	 * @param last_data - Конечная дата
	 * @return - Возвращает набор данных типа ObservableList 
	 * 			отсортированных по дате и заполняет ими 
	 * 			таблицу TableView.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<hmmr_pmplan_model> _select_data_pmplan(String begin_data, String last_data)
	{
		ObservableList<hmmr_pmplan_model> list = FXCollections.observableArrayList();
	
		try {
			String query = "select id,PM,data,OFT,PM_Group from hmmr_pm_year where data BETWEEN "+"'"+begin_data+"'"+" AND "+"'"+last_data+"'"+" AND record_del = 0;";
			
			cn.ConToDb();
			stmt16 = cn.con.createStatement();
			rs16 = stmt16.executeQuery(query);
						
	        while (rs16.next()) {
	        	hmmr_pmplan_model hpm = new hmmr_pmplan_model();
	        	if(rs16.getString(1) != null) {
	        		hpm.Id.set(rs16.getString(1));
	        		hpm.num_pm.set(rs16.getString(2));
	        		hpm.dd.set(rs16.getString(3));
	        		hpm.resp.set(rs16.getString(4));
	        		hpm.pm_group.set(rs16.getString(5));
	        						        				            
		            list.add(hpm);
	        	}    
	        }
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 586!");
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
	 * Сортируем таблицу Work Recording по номеру задачи из Action Plan - 
	 * apnum, в результате чего в Work Recording выведутся только те
	 * записи, кототрые соответствуют задаче с этим номером в 
	 * Action Plan
	 * @param apnum - Номер задачи в Action Plan
	 * @return - Возвращает набор данных типа ObservableList 
	 * 			отсортированных по дате и заполняет ими 
	 * 			таблицу TableView.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<hmmr_wr_model> _select_sort_apnum_wr(String apnum)
	{
		ObservableList<hmmr_wr_model> list = FXCollections.observableArrayList();
		
		try {
			String query = "select id,Task_Description,Task_Report,CM_DownTime,WR_Work_Time,WR_End_Date,Equipment_Full,Record_Type,Task_Resp_ID,WR_Executor_Confirmed,WR_Host_Confirmed,ap_num,user_number,WR_Resp_Confirmed from hmmr_work_recording WHERE ap_num = "+"'"+apnum+"'"+";";
			
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
	        						        				            
		            list.add(hpm);
	        	}    
	        }
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 618!");
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
	 * Сортируем таблицу Work Recording по названию цеха - 
	 * WSH, в результате чего в Work Recording выведутся только те
	 * записи, кототрые соответствуют записям техников только 
	 * этого цеха
	 * @param shop - Название цеха
	 * @return - Возвращает набор данных типа ObservableList 
	 * 			отсортированных по дате и заполняет ими 
	 * 			таблицу TableView.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<hmmr_wr_model> _select_sort_shop_wr(String begin_data, String last_data, String shop)
	{
		ObservableList<hmmr_wr_model> list = FXCollections.observableArrayList();
		
		try {
			String query = "select id,Task_Description,Task_Report,CM_DownTime,WR_Work_Time,WR_End_Date,Equipment_Full,Record_Type,Task_Resp_ID,WR_Executor_Confirmed,WR_Host_Confirmed,ap_num,user_number,WR_Resp_Confirmed from hmmr_work_recording WHERE WR_End_Date BETWEEN "+"'"+begin_data+"'"+" AND "+"'"+last_data+"'"+" AND FL_WSH = "+"'"+shop+"'"+";";
			
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
	        						        				            
		            list.add(hpm);
	        	}    
	        }
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 673!");
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
	 * Сортируем таблицу Work Recording по ID техника - 
	 * Resp1, в результате чего в Work Recording выведутся только те
	 * записи, кототрые соответствуют записям только этого техника
	 * @param resp - ID техника
	 * @return - Возвращает набор данных типа ObservableList 
	 * 			отсортированных по дате и заполняет ими 
	 * 			таблицу TableView.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<hmmr_wr_model> _select_sort_resp_wr(String begin_data, String last_data, String resp)
	{
		ObservableList<hmmr_wr_model> list = FXCollections.observableArrayList();
		
		try {
			String query = "select id,Task_Description,Task_Report,CM_DownTime,WR_Work_Time,WR_End_Date,Equipment_Full,Record_Type,Task_Resp_ID,WR_Executor_Confirmed,WR_Host_Confirmed,ap_num,user_number,WR_Resp_Confirmed from hmmr_work_recording WHERE WR_End_Date BETWEEN "+"'"+begin_data+"'"+" AND "+"'"+last_data+"'"+" AND Task_Resp_ID = "+"'"+resp+"'"+";";
			
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
	        						        				            
		            list.add(hpm);
	        	}    
	        }
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 728!");
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
	 * Заполнение данными таблицы PM Plan при открытии окна редактирования PM Plan - 
	 * редактор, без сортировки по дате (Запрос для PM Plan с сортировкой по дате 
	 * в строке - 555)
	 * @return - Возвращает набор данных типа ObservableList 
	 * 			отсортированных по дате и заполняет ими 
	 * 			таблицу TableView.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<hmmr_pmplan_model> _select_data_pmplan()
	{
		ObservableList<hmmr_pmplan_model> list = FXCollections.observableArrayList();
		
		try {
			String query = "select id,PM,data,OFT,PM_Group from hmmr_pm_year where record_del = 0;";
			
			cn.ConToDb();
			stmt18 = cn.con.createStatement();
			rs18 = stmt18.executeQuery(query);
									
	        while (rs18.next()) {
	        	hmmr_pmplan_model hpm = new hmmr_pmplan_model();
	        	if(rs18.getString(1) != null) {
	        		hpm.Id.set(rs18.getString(1));
	        		hpm.num_pm.set(rs18.getString(2));
	        		hpm.dd.set(rs18.getString(3));
	        		hpm.resp.set(rs18.getString(4));
	        		hpm.pm_group.set(rs18.getString(5));
	        						        						        				            
		            list.add(hpm);
	        	}    
	        }
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 798!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt18.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs18.close(); } catch(SQLException se) { /*can't do anything */ }
	       }
		return list;
	}
	/**
	 * Заполняем ComboBox данными по типу PM из таблицы hmmr_type_pm.
	 * Данный ComboBox используется при создании записи для таблицы
	 * PM Instruction			
	 * @return - Возвращает набор данных типа ObservableList 
	 * 			отсортированных по дате и заполняет ими 
	 * 			ComboBox.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<String> _select_typepm_inst()
	{
		ObservableList<String> list = FXCollections.observableArrayList();
		
		try {
			String query = "select Type_PM from hmmr_type_pm;";
			
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 823!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
	       }

		return list;
	}
	
	/**
	 * Заполняем ComboBox данными по циклу PM из таблицы hmmr_pm_cycle.
	 * Данный ComboBox используется при создании записи для таблицы
	 * PM Instruction
	 * @return - Возвращает набор данных типа ObservableList 
	 * 			отсортированных по дате и заполняет ими 
	 * 			ComboBox.
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<String> _select_cycle_inst()
	{
		ObservableList<String> list = FXCollections.observableArrayList();
		
		try {
			String query = "select Type from hmmr_pm_cycle;";
			
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 863!");
	       } finally {
	           //close connection ,stmt and resultset here
	       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { stmt7.close(); } catch(SQLException se) { /*can't do anything */ }
	        try { rs7.close(); } catch(SQLException se) { /*can't do anything */ }
	       }

		return list;
	}
	
	/**
	 * Заполняем ComboBox данными по должности из таблицы hmmr_mu_staff.
	 * Данный ComboBox используется при создании записи для таблицы
	 * PM Instruction
	 * @return - Возвращает набор данных типа ObservableList 
	 * 			отсортированных по дате и заполняет ими 
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 902!");
		      } finally {
		          //close connection ,stmt and resultset here
		      	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt8.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs8.close(); } catch(SQLException se) { /*can't do anything */ }
		      }

		return list;
	}
	
	/**
	 * Запрос для заполнения ComboBox двойным значением через - например
	 * ABA - Баранов Алексей для записи в таблицу Plant Structure
	 * @param str_s - Название поля, которое хотим получить в результирующей
	 * 				 выборке из таблицы БД hmmr_plant_structure
	 * @param str - Название поля, которое хотим получить в результирующей 
	 * 				выборке из таблицы БД hmmr_plant_structure
	 * @param str_where - Название поля используемого в условии where 
	 * 					из таблицы БД hmmr_plant_structure
	 * @param str_znach - Значение для поля, которое используем в where
	 * @return - Возвращает набор данных типа ObservableList 
	 * 			отсортированных по дате и заполняет ими 
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 946!");
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
	 * Запрос для заполнения ComboBox значением, перегруженный 
	 * аналог предыдущего запроса, только с одним параметром для
	 * записи в таблицу Plant Structure
	 * @param str - Название поля которое хотим получить в 
	 * 				результирующей выборке из таблицы БД 
	 * 				hmmr_plant_structure
	 * @param str_where - Название поля используемого в условии where 
	 * 					из таблицы БД hmmr_plant_structure
	 * @param str_znach - Значение для поля, которое используем в where
	 * @return - Возвращает набор данных типа ObservableList 
	 * 			отсортированных по дате и заполняет ими 
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 991!");
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
	 * !!!!!!!!!!!!!!!!!!!!Похожа на предыдущую!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * Запрос для заполнения ComboBox значением, перегруженный 
	 * аналог предыдущего запроса, только с одним параметром для
	 * записи в таблицу Plant Structure
	 * @param str - Название поля которое хотим получить в 
	 * 				результирующей выборке из таблицы БД 
	 * 				hmmr_plant_structure
	 * @param str_where - Название поля используемого в условии where 
	 * 					из таблицы БД hmmr_plant_structure
	 * @param str_znach - Значение для поля, которое используем в where
	 * @return - Возвращает набор данных типа ObservableList 
	 * 			отсортированных по дате и заполняет ими 
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1037!");
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1097!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) {}
		        try { stmt6.close(); } catch(SQLException se) {}
		        try { rs6.close(); } catch(SQLException se) {}
		       }

			return list;
		}*/
	/**
	 * Получаем значение, чтобы узнать какой radiobutton установлен
	 * К10 - 10000 машин, К20 - 20000 машин и т. д.
	 * @return - Возвращаем полученное значение
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1072!");
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
	 * Запрос для заполнения ComboBox значением, перегруженный 
	 * аналог предыдущего запроса, только с одним параметром для
	 * записи в таблицу Plant Structure
	 * @param str - Имя поля, значение которого надо вставить в ComboBox
	 * @return - Возвращаем полученное значение
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1109!");
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
	/**!!!!!!!!Похожа на предыдущуюю!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * Запрос для заполнения ComboBox значением, перегруженный 
	 * аналог предыдущего запроса, только с одним параметром для
	 * записи в таблицу Plant Structure
	 * @param str - Имя поля, значение которого надо вставить в ComboBox
	 * @return - Возвращаем полученное значение
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1147!");
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
	 * Заполняем значениями ComboBox Shop
	 * @return - Возвращаем полученный набор данных
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<String> _select_shop_pm()
	{
		ObservableList<String> list = FXCollections.observableArrayList();
		
		try {
			String query = "select FL03_Shop_s, FL03_Shop_ENG from hmmr_plant_structure group by FL03_Shop_s, FL03_Shop_ENG;";
			
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1183");
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
	 * Заполняем значениями ComboBox Line Machine
	 * @param group - параметр использующийся в сортировке по полю Group_PM
	 * @return - Возвращаем полученный набор данных
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<String> _select_lm_pm(String group)
	{
		ObservableList<String> list = FXCollections.observableArrayList();
		
		try {
			String query = "select FL05_Line_s, FL05_Line_ENG from hmmr_plant_structure where FL04_Group_s ="+"'"+group+"'"+" group by FL05_Line_s, FL05_Line_ENG;";
			
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1219!");
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
	 * Заполняем значениями ComboBox Group
	 * @param shop - параметр использующийся в сортировке по полю Shop_s
	 * @return - - Возвращаем полученный набор данных
	 */
	@SuppressWarnings({ "static-access"})
	public ObservableList<String> _select_group_pm(String shop)
	{
		ObservableList<String> list = FXCollections.observableArrayList();
		
		try {
			String query = "select FL04_Group_s from hmmr_plant_structure where FL03_Shop_s ="+"'"+shop+"'"+" group by FL04_Group_s;";
			
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1255!");
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
				//Заполняем ComboBox Operation Station для таблицы PM
				@SuppressWarnings({ "static-access"})
				public ObservableList<String> _select_os_pm(String group, String lm)
				{
					ObservableList<String> list = FXCollections.observableArrayList();
					
					try {
						String query = "select FL06_Station_s, FL06_Station_ENG from hmmr_plant_structure where FL04_Group_s ="+"'"+group+"'"+" AND FL05_Line_s = "+"'"+lm+"'"+" group by FL06_Station_s, FL06_Station_ENG;";
						
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1290!");
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
				//Заполняем ComboBox Equipment для таблицы PM
				@SuppressWarnings({ "static-access"})
				public ObservableList<String> _select_equip_pm(String os, String grp, String lm)
				{
					ObservableList<String> list = FXCollections.observableArrayList();
					
					try {
						String query = "select FL07_Equipment_s, FL07_Equipment_ENG from hmmr_plant_structure where FL06_Station_s ="+"'"+os+"'"+" AND FL04_Group_s = "+"'"+grp+"'"+" AND FL05_Line_s = "+"'"+lm+"'"+" group by FL07_Equipment_s, FL07_Equipment_ENG;";
						
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1324!");
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
				//Заполняем ComboBox Group для таблицы PM
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1358!");
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
				//Заполняем ComboBox Model_Type_Task для таблицы PM
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1393!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
//					System.out.println("ARRAYLIST: "+list);
					return list;
				}
				//Заполняем ComboBox num_instruction для таблицы PM
				@SuppressWarnings({ "static-access"})
				public ObservableList<String> _select_instr_pm()
				{
					ObservableList<String> list = FXCollections.observableArrayList();
					
					try {
						String query = "select num_instruction from pm_inst where del_rec = 0 group by num_instruction;";
						
						cn.ConToDb();
						stmt6 = cn.con.createStatement();
						rs6 = stmt6.executeQuery(query);
									
				        while (rs6.next()) {
				        	//typepm_model_inst tpm = new typepm_model_inst();
				        	if(rs6.getString(1) != null) {
				        		//tpm.settypepm(rs6.getString(1));
				        		String instr = rs6.getString(1);			        					            
					            list.add(instr);
				        	}    
				        }
//				        System.out.println("SELECT WORKED DATA: "+list);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1427!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
//					System.out.println("ARRAYLIST: "+list);
					return list;
				}
				//Заполняем ComboBox PM_Name для таблицы PM
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1461!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
//					System.out.println("ARRAYLIST: "+list);
					return list;
				}
				
				//получаем дату окончания и время за которое надо предупредить пользователя, т.е. вставить информацию в Action Plan
				@SuppressWarnings({ "static-access"})
				public ObservableList<String> _select_pmplan()
				{
					ObservableList<String> list = FXCollections.observableArrayList();
					try {
						String query = "select hpy.data, hp.Date_Beforehand from hmmr_pm_year hpy INNER JOIN hmmr_pm hp ON hpy.PM = hp.id;";
						
						cn.ConToDb();
						stmt6 = cn.con.createStatement();
						rs6 = stmt6.executeQuery(query);
									
				        while (rs6.next()) {
				        	//typepm_model_inst tpm = new typepm_model_inst();
				        	if(rs6.getString(1) != null) {
				        		//tpm.settypepm(rs6.getString(1));
				        		String pmn = rs6.getString(1)+" - "+ rs6.getString(2);			        					            
					            list.add(pmn);
				        	}    
				        }
//				        System.out.println("SELECT WORKED DATA: "+list);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1571!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
//					System.out.println("ARRAYLIST: "+list);
					return list;
				}
				
				//Получаем все необходимые поля для инсерта в Action Plan
						@SuppressWarnings({ "static-access"})
				public ObservableList<String> _select_getfield_for_ap()
				{
					ObservableList<String> list = FXCollections.observableArrayList();
					try {
						//String query = "select hpy.data, hpy.OFT, pi.Src_Doc, hp.Shop, hp.Line_Machine, hp.Operation_Station, hp.Equipment, hp.id, hp.PM_Name, hpy.record_del, hpy.id, hp.Group_PM from hmmr_pm_year hpy INNER JOIN hmmr_pm hp ON hpy.PM = hp.id INNER JOIN pm_inst pi ON hp.num_instruction = pi.num_instruction;";
						String query = "select hpy.data, hpy.OFT, pi.Src_Doc, hp.id, hpy.record_del, hpy.id, hps.FL03_Shop_s, hps.FL04_Group_s, hps.FL05_Line_s, hps.FL06_Station_s, hps.FL07_Equipment_s from hmmr_pm_year hpy INNER JOIN hmmr_pm hp ON hpy.PM = hp.id INNER JOIN hmmr_plant_structure hps ON hps.id = hp.Eq_ID INNER JOIN pm_inst pi ON hp.Instruction_num = pi.num_instruction;";
						
						cn.ConToDb();
						stmt6 = cn.con.createStatement();
						rs6 = stmt6.executeQuery(query);
									
				        while (rs6.next()) {
				        	//typepm_model_inst tpm = new typepm_model_inst();
				        	if(rs6.getString(1) != null) {
				        		//tpm.settypepm(rs6.getString(1));
				        		String pmn = rs6.getString(1)+" - "+ rs6.getString(2)+" - "+ rs6.getString(3)+" - "+ rs6.getString(4)+" - "+ rs6.getString(5)+" - "+ rs6.getString(6)+" - "+ rs6.getString(7)+" - "+ rs6.getString(8)+" - "+ rs6.getString(9)+" - "+ rs6.getString(10)+" - "+ rs6.getString(11)+" - "+ rs6.getString(12);			        					            
					            list.add(pmn);
				        	}    
				        }
//				        System.out.println("SELECT WORKED DATA: "+list);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1529!");
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
				//Получаем путь к инструкции
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1563!");
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
				//Получаем ФИО
				//для разных комбобоксов свой запрос, для ответственных - сортируем так чтоб на первом месте шли инженеры
				//для исполнителей так - что на первом месте идут техники принадлежащее цеху, затем другие техники, затем инженера и т.д.
				//otv = 1 - ответственные, otv = 2 - исполнители, shop - цех
				@SuppressWarnings({ "static-access"})
				public ObservableList<String> _select_fio_for_ap(int otv, String shop)
				{
					ObservableList<String> list = FXCollections.observableArrayList();
					String ln = null;
					String query = "null";
					try {
						if(otv == 1)
							query = "select ID, L_Name_RUS, F_Name_RUS, Otchestvo from hmmr_mu_staff where user_del = 0 ORDER BY FIELD(Team, 'E', 'T', 'SL', 'SS', 'TL', 'GL', 'MS', 'D') ASC, L_Name_RUS ASC;";
						else
							query ="select ID, L_Name_RUS, F_Name_RUS, Otchestvo from hmmr_mu_staff where user_del = 0 ORDER BY IF(Group_S="+"'"+shop+"'"+",Team = 'T',0) DESC, FIELD(Team, 'T', 'E', 'SL', 'SS', 'TL', 'GL', 'MS', 'D') ASC, L_Name_RUS ASC;";
						
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1603!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
//					System.out.println("ARRAYLIST: "+list);
					return list;
				}
				
				//Получаем признак устанвлена галочка в work recording или нет
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
				
				//Получаем данные по пользователю
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1677!");
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
				//получаем id пользователя для сравнения с его id полученном при входе  в программу
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1711!");
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
				//Показываем все задачи по цеху, можно перегрузить нижнюю функцию или наоборот
				@SuppressWarnings({ "static-access"})
				public ObservableList<hmmr_ap_model> _select_data_all_shop(String shop)
				{
					ObservableList<hmmr_ap_model> list = FXCollections.observableArrayList();
					
					try {
						String query = "select id,PM_Num,Type,Description,Due_Date,Equipment,Instruction,Otv_For_Task,Otv,Tsk_maker,flag_otv,flag_oft,flag_tm,Icon from hmmr_action_plan where shop = "+"'"+shop+"'"+" AND del_rec = 0 ORDER BY FIELD(Icon, '1S', '2Q', '3P', '4M', '1') ASC;";
						
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1745!");
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
				//Показываем все задачи по цеху
				@SuppressWarnings({ "static-access"})
				public ObservableList<hmmr_ap_model> _select_data_ap_shop(String shop, String oft)
				{
					ObservableList<hmmr_ap_model> list = FXCollections.observableArrayList();
					
					try {
						String query = "select id,PM_Num,Type,Description,Due_Date,Equipment,Instruction,Otv_For_Task,Otv,Tsk_maker,flag_otv,flag_oft,flag_tm,Icon from hmmr_action_plan where del_rec = 0 AND (shop = "+"'"+shop+"'"+" OR Otv_For_Task = "+"'"+oft+"'"+" OR Otv = "+"'"+oft+"'"+" OR Tsk_maker = "+"'"+oft+"'"+");";
						
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1790!");
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
				//Показываем все выполненные задачи по цеху
				@SuppressWarnings({ "static-access"})
				public ObservableList<hmmr_ap_model> _select_data_exectsk(String shop)
				{
					ObservableList<hmmr_ap_model> list = FXCollections.observableArrayList();
					
					try {
						//String query = "select id,PM_Num,Type,Description,Due_Date,Equipment,Instruction,Otv_For_Task,Otv,Tsk_maker,flag_otv,flag_oft,flag_tm from hmmr_action_plan where shop = "+"'"+shop+"'"+" AND flag_otv = 2 AND flag_oft = 2 AND flag_tm = 2;";
						//String query = "select hap.id,hap.PM_Num,hap.Type,hap.Description,hap.Due_Date,hap.Equipment,hap.Instruction,hap.Otv_For_Task,hap.Otv,hap.Tsk_maker,hap.flag_otv,hap.flag_oft,hap.flag_tm from hmmr_action_plan hap INNER JOIN hmmr_mu_staff hms ON hap.Otv = hms.ID AND hms.Group_S="+"'"+shop+"'"+"  AND hap.flag_otv = 2 AND hap.flag_oft = 2 AND hap.flag_tm = 2;";
						String query = "select hap.id,hap.PM_Num,hap.Type,hap.Description,hap.Due_Date,hap.Equipment,hap.Instruction,hap.Otv_For_Task,hap.Otv,hap.Tsk_maker,hap.flag_otv,hap.flag_oft,hap.flag_tm,hap.Icon from hmmr_action_plan hap INNER JOIN hmmr_mu_staff hms ON hap.Otv = hms.ID AND hap.flag_otv = 2 AND hap.flag_oft = 2 AND hap.flag_tm = 2 AND if( "+"'"+shop+"'"+"='S' || "+"'"+shop+"'"+"='W', hms.Group_S='S,W', hms.Group_S="+"'"+shop+"'"+");"; 
						
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1835!");
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
				//Временный запрос для цеха S,W пока он еще не разделен
				//Показываем все задачи по цеху
				@SuppressWarnings({ "static-access"})
				public ObservableList<hmmr_ap_model> _select_data_ap_sw(String oft)
				{
					ObservableList<hmmr_ap_model> list = FXCollections.observableArrayList();
					
					try {
						String query = "select id,PM_Num,Type,Description,Due_Date,Equipment,Instruction,Otv_For_Task,Otv,Tsk_maker,flag_otv,flag_oft,flag_tm,Icon from hmmr_action_plan where del_rec = 0 AND (shop = 'S' OR shop = 'W') OR (Otv_For_Task = "+"'"+oft+"'"+" OR Otv = "+"'"+oft+"'"+" OR Tsk_maker = "+"'"+oft+"'"+");";
						
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1881!");
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1922!");
		}
    	finally {
            //close connection ,stmt and resultset here
        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
	}
//////////////////////////////////////////////////////////////ACTION PLAN////////////////////////////////////////////////////////////////////////////////	
	//Вставляем запись в Action Plan
	@SuppressWarnings("static-access")
	public void _insert_ap(String id_pm, String type, String pmname, String due_date, String equip, String instruct, String otf, String userid, String shop)
	{
		String query = "INSERT INTO hmmr_action_plan (PM_Num, Type, Description, Due_Date, Equipment, Instruction, Tsk_maker, Otv_For_Task, user_id, shop) VALUES ("+"'"+id_pm+"'"+","+ "'"+type+"'"+","+"'"+pmname+"'"+","+"'"+due_date+"'"+","+"'"+equip+"'"+","+"'"+instruct+"'"+","+"'"+otf+"'"+","+"'"+otf+"'"+","+"'"+userid+"'"+","+"'"+shop+"'"+");";
		
		try {
			cn.ConToDb();
			stmt = cn.con.createStatement();
			stmt.executeUpdate(query);
			//log.log(Level.INFO, "ADD STRING TO DB");
			//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
		} catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1944!");
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
	//Вставляем запись в Action Plan
		@SuppressWarnings("static-access")
		public void _insert_ap1(String type, String pmname, LocalDate due_date, String equip, String tsk_maker, String otf, String otv, String userid, String shop, String icon)
		{
			String query = "INSERT INTO hmmr_action_plan (Type, Description, Due_Date, Equipment, Tsk_maker, Otv_For_Task, Otv, user_id, shop, Icon) VALUES ("+ "'"+type+"'"+","+"'"+pmname+"'"+","+"'"+due_date+"'"+","+"'"+equip+"'"+","+"'"+tsk_maker+"'"+","+"'"+otf+"'"+","+"'"+otv+"'"+","+"'"+userid+"'"+","+"'"+shop+"'"+","+"'"+icon+"'"+");";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1967!");
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
		//Вставляем запись в Work Recording
				@SuppressWarnings("static-access")
				public void _insert_wr(String ap_num, String userid, String WSH, String Group_EQ, String Line, String Station, String Equip, String equip, String record_type, Double wt, String resp, String resp1, String resp2, String resp3, String status_wr, String shift_report, String req_action, LocalDate actual_date, LocalDate actual_date_2, LocalDate actual_date_3, LocalDate actual_date_4, String actual_time, LocalDate actual_date1, LocalDate actual_date2, LocalDate actual_date3, LocalDate actual_date4, String actual_time1, String actual_time2, String actual_time3, String actual_time4, LocalTime hours1, LocalTime hours1_2, LocalTime hours1_3, LocalTime hours1_4, LocalTime hours2, LocalTime hours2_2, LocalTime hours2_3, LocalTime hours2_4, String b_gdw, String e_gdw, String b_gtw, String e_gtw)
				{
					String query = "INSERT INTO hmmr_work_recording (ap_num, user_number, FL_WSH, FL_Group, FL_Line, FL_Station, FL_Equipment, Equipment_Full, Record_Type, CM_Work_Time, Task_Resp_ID, _Resp2, _Resp3, _Resp4, WR_Executor_Confirmed, Task_Description, Task_Report, WR_Begin_Date, _Actual_Date_2, _Actual_Date_3, _Actual_Date_4, CM_DownTime, WR_End_Date, _Actual_Date2, _Actual_Date3, _Actual_Date4, WR_Work_Time, _Actual_Time2, _Actual_Time3, _Actual_Time4, WR_Work_Time_Begin, _Hours1_2, _Hours1_3, _Hours1_4, WR_Work_Time_End, _Hours2_2, _Hours2_3, _Hours2_4, CM_Date_Begin, CM_Date_End, CM_Time_Begin, CM_Time_End) VALUES ("+"'"+ap_num+"'"+","+ "'"+userid+"'"+","+"'"+WSH+"'"+","+"'"+Group_EQ+"'"+","+"'"+Line+"'"+","+"'"+Station+"'"+","+"'"+Equip+"'"+","+"'"+equip+"'"+","+"'"+record_type+"'"+","+"'"+wt+"'"+","+"'"+resp+"'"+","+"'"+resp1+"'"+","+"'"+resp2+"'"+","+"'"+resp3+"'"+","+"'"+status_wr+"'"+","+"'"+shift_report+"'"+","+"'"+req_action+"'"+","+"'"+actual_date+"'"+","+"'"+actual_date_2+"'"+","+"'"+actual_date_3+"'"+","+"'"+actual_date_4+"'"+","+"'"+actual_time+"'"+","+"'"+actual_date1+"'"+","+"'"+actual_date2+"'"+","+"'"+actual_date3+"'"+","+"'"+actual_date4+"'"+","+"'"+actual_time1+"'"+","+"'"+actual_time2+"'"+","+"'"+actual_time3+"'"+","+"'"+actual_time4+"'"+","+"'"+hours1+"'"+","+"'"+hours1_2+"'"+","+"'"+hours1_3+"'"+","+"'"+hours1_4+"'"+","+"'"+hours2+"'"+","+"'"+hours2_2+"'"+","+"'"+hours2_3+"'"+","+"'"+hours2_4+"'"+","+"'"+b_gdw+"'"+","+"'"+e_gdw+"'"+","+"'"+b_gtw+"'"+","+"'"+e_gtw+"'"+");";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "ADD STRING TO DB");
						//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
					} catch (SQLException e) {
						//e.printStackTrace();
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 1990!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!				
	//Вставляем строку в таблицу для справочника Type_PM 
	@SuppressWarnings("static-access")
	public void _insert_type_pm(String type, String desc)
	{
		String query = "INSERT INTO hmmr_type_pm (Type_PM, Description) VALUES ("+"'"+type+"'"+","+ "'"+desc+"'"+");";
		
		try {
			cn.ConToDb();
			stmt = cn.con.createStatement();
			stmt.executeUpdate(query);
			//log.log(Level.INFO, "ADD STRING TO DB");
			//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
		} catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2013!");
		}
    	finally {
            //close connection ,stmt and resultset here
        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
	}
	
	//Вставляем строку в таблицу hmmr_pm_year 
		@SuppressWarnings("static-access")
		public void _insert_pm_year(String npm, LocalDate data, String OFT, int pm_group)
		{
			String query = "INSERT INTO hmmr_pm_year (PM, data, OFT, PM_Group) VALUES ("+"'"+npm+"'"+","+ "'"+data+"'"+","+"'"+OFT+"'"+","+pm_group+");";
			
			try {
				cn.ConToDb();
				stmt = cn.con.createStatement();
				stmt.executeUpdate(query);
				//log.log(Level.INFO, "ADD STRING TO DB");
				//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
			} catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2036!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		
	//Вставляем в таблицу history
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2059!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}	
	
	//Вставляем строку в таблицу PM Instruction 
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2084!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		
		/**
		 * Вставляем строку в Plant Structure 
		 * @param user_id - id пользователя
		 * @param FL01_Company - старое название Company
		 * @param FL02_Plant - старое название Plant
		 * @param FL03_Shop_s - старое название Shop_s
		 * @param FL04_Group_s - старое название Group_PM
		 * @param FL05_Line_s - старое название Line_Macine_s
		 * @param FL06_Station_s - старое название Operation_Station_s
		 * @param FL07_Equipment_s - старое название Equipmant_s
		 * @param FL03_Shop_ENG - старое название Shop
		 * @param FL04_Group_ENG - новое поле
		 * @param FL05_Line_ENG - старое название Line_Machine
		 * @param FL06_Station_ENG - старое название Operation_Station
		 * @param FL07_Equipment_ENG - старое название Equipmant
		 * @param FL03_Shop_RUS - новое поле
		 * @param FL04_Group_RUS - новое поле
		 * @param FL05_Line_RUS - старое название Line_Machine_RUS
		 * @param FL06_Station_RUS - старое название Operation_Station_RUS
		 * @param FL07_Equipment_RUS - новое поле
		 * @param Description_RUS - старое название Description
		 * @param Equip_label - старое название Equip_label
		 * @param Station_Label - старое название Station_label
		 * @param Equipment_Folder_Link - старое название manual
		 * @param Resp_Planner_Group - новое поле Группа ответственных(Берем из справочника)
		 * @param Assets_Inventory_Number - новое поле Инвентарный номер
		 * @param Assets_OS1_Number - новое поле № Основных средств
		 * @param Assets_Start_up_Date - новое поле Дата запуска в эксплуатацию
		 * @param Cost_Center - новое поле Куда списали
		 * @param Site_Location - старое название Location
		 * @param Site_CHAMBER - старое название CHAMBER
		 * @param Site_Coordinates - старое название Coordinates
		 * @param Site_Altitude - старое название Altitude
		 * @param TR_CU - не поменялось
		 * @param TR_CU_Link - не поменялось
		 * @param Hazardous - не поменялось
		 * @param Key_equipment - не поменялось
		 * @param EQ_Integrator - старое название Station Supplier
		 * @param EQ_Manufacture - старое название Manuf
		 * @param EQ_Type - старое название Type
		 * @param EQ_Serial_Number - старое название S_N
		 * @param EQ_Technical_Characteristic - старое название Main_Technical_Characteristic
		 * @param Responsobility - не поменялось
		 * @param M_Electric - не поменялось
		 * @param M_Air - не поменялось
		 * @param M_Water - не поменялось
		 * @param M_Cold_Water - не поменялось
		 * @param M_Hot_Water - не поменялось
		 * @param M_RO_Water - старое название RO_Water
		 * @param M_Gas - не поменялось
		 * @param Status - старое название del_rec
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2116!");
			}
		   	finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
//*****************************************************************************************************************************		
		//Вставляем строку в таблицу PM 
				@SuppressWarnings("static-access")
				public void _insert_pm(String ninst_pm, String eq_id, String group_pm, String otv_pm, String dexp_pm, String ool_pm)
				{
					String query = "INSERT INTO hmmr_pm (user_id, Instruction_num, Eq_ID, PM_Group, PM_Resp, Date_Beforehand, OnOff_Line) "
							+ "VALUES ("+"'"+conn_connector.USER_ID+"'"+","+"'"+ninst_pm+"'"+","+"'"+eq_id+"'"+","+"'"+group_pm+"'"+","+"'"+otv_pm+"'"+","+"'"+dexp_pm+"'"+","+"'"+ool_pm+"'"+");";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "ADD STRING TO DB");
						//mgr.logger.log(Level.INFO, "ADD STRING TO DB");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2305!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
	
	//для формы обновления справочника pm_cycle
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2181!");
        } finally {
            //close connection ,stmt and resultset here
        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt2.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs2.close(); } catch(SQLException se) { /*can't do anything */ }
        }
		return total_rez;
	}
	
	//для формы обновления справочника Type_PM
		@SuppressWarnings("static-access")
		public String _select_for_update_typepm(String id)
		{
			try {
				String query = "select Type_PM, Description from hmmr_type_pm where id = "+"'"+id+"'"+";";
				
				cn.ConToDb();
				stmt4 = cn.con.createStatement();
				rs4 = stmt4.executeQuery(query);
				//log.log(Level.INFO, "CHANNEL WAS FOUND");
		        while (rs4.next()) {
		            type_tpm = rs4.getString(1);
		            desc_tpm = rs4.getString(2);
		        }
		        total_rez_tpm = type_tpm+","+desc_tpm;
//		        System.out.println("SELECT WORKED: "+total_rez);
			}
			catch (SQLException e) {
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2213!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt4.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs4.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return total_rez_tpm;
		}
		
		//получаем дни и цвет из таблицы Color
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2244!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt4.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs4.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
					return _color;
				}
		
		//для формы обновления таблицы PM Instruction
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
				        total_rez_upd_inst = ninst_inst+","+date_create+","+date_change+","+inst_pdf+","+ver_inst+","+mtt_inst+","+pmn_inst+","+tpm_inst+","+pmc1_inst+","+pmc2_inst+","+ool_inst+","+oop_inst+","+pos_inst+","+
				                             sinfo_inst+","+s_doc_inst+","+qspec_inst+","+pwt_inst+","+wt_inst+","+adm2_inst+","+adm3_inst+","+ofit1_inst+","+
				        		             ofit2_inst;
//				        System.out.println("SELECT WORKED: "+total_rez);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2274!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt10.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs10.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
					return total_rez_upd_inst;
				}
				
				//для формы обновления таблицы PM
				@SuppressWarnings("static-access")
				public String _select_for_update_pm(String id)
				{
					try {
						String query = "select Instruction_num,Eq_ID,PM_Group,PM_Resp,Date_Beforehand,OnOff_Line from hmmr_pm where id = "+"'"+id+"'"+";";
						
						cn.ConToDb();
						stmt9 = cn.con.createStatement();
						rs9 = stmt9.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs9.next()) {
				        	ninst_pm = rs9.getString(1);
				        	eq_id = rs9.getString(2);
				        	group_pm = rs9.getString(3);
				        	pm_resp = rs9.getString(4);
				        	dexp_pm = rs9.getString(5);
				        	ool_pm = rs9.getString(6); 
				        }
				        total_rez_upd_pm = ninst_pm+","+eq_id+","+group_pm+","+pm_resp+","+dexp_pm+","+ool_pm;
//				        System.out.println("SELECT WORKED: "+total_rez);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2322!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt9.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs9.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
					return total_rez_upd_pm;
				}
//////////////////////////////////////////////////////////////////////////////ACTION PLAN///////////////////////////////////////////////////////////////////////				
				//для формы обновления таблицы AP
				@SuppressWarnings("static-access")
				public String _select_for_update_ap(String id)
				{
					try {
						String query = "select PM_Num,Type,Description,Due_Date,Equipment,Otv_For_Task,Otv,Icon from hmmr_action_plan where id = "+"'"+id+"'"+";";
						
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
				        }
				        total_rez_upd_ap = pmnum_ap+";"+type_ap+";"+description_ap+";"+due_date_ap+";"+equip_ap+";"+oft_ap+";"+otv_ap+";"+icon;
//				        System.out.println("SELECT WORKED: "+total_rez);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2365!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt15.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs15.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
					return total_rez_upd_ap;
				}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
				//для формы обновления таблицы PM Plan
				@SuppressWarnings("static-access")
				public String _select_for_update_pmplan(String id)
				{
					try {
						String query = "select PM, data, OFT, PM_Group from hmmr_pm_year where id = "+"'"+id+"'"+" AND record_del = 0;";
						
						cn.ConToDb();
						stmt15 = cn.con.createStatement();
						rs15 = stmt15.executeQuery(query);
						//log.log(Level.INFO, "CHANNEL WAS FOUND");
				        while (rs15.next()) {
				        	numpm_pmplan = rs15.getString(1);
				        	data_pmplan = rs15.getString(2);
				        	oft_pmplan = rs15.getString(3);
				        	pm_group = rs15.getString(4);
				        }
				        total_rez_pmplan = numpm_pmplan+";"+data_pmplan+";"+oft_pmplan+";"+pm_group;
//				        System.out.println("SELECT WORKED: "+total_rez);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2399!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt15.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs15.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
					return total_rez_pmplan;
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!				
				//для формы обновления таблицы WR
				@SuppressWarnings("static-access")
				public String _select_for_update_wr(String id)
				{

					try {
						String query = "select ap_num,WR_End_Date,Equipment_Full,Record_Type,CM_Work_Time,Task_Resp_ID,_Resp2,_Resp3,_Resp4,WR_Executor_Confirmed,Task_Description,Task_Report,WR_Host_Confirmed,WR_Begin_Date,_Actual_Date_2,_Actual_Date_3,_Actual_Date_4,CM_DownTime,WR_End_Date,_Actual_Date2,_Actual_Date3,_Actual_Date4,WR_Work_Time,_Actual_Time2,_Actual_Time3,_Actual_Time4,WR_Resp_Confirmed,WR_Work_Time_Begin,_Hours1_2,_Hours1_3,_Hours1_4,_Min1,WR_Work_Time_End,_Hours2_2,_Hours2_3,_Hours2_4,_Min2,user_number from hmmr_work_recording where id = "+"'"+id+"'"+";";
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
				        }
				        total_rez_upd_wr = shift_report+";"+req_action+";"+actual_time+";"+actual_time1+";"+actual_time2+";"+actual_time3+";"+actual_time4+";"+data+";"+equip+";"+record_type+";"+resp+";"+resp2+";"+resp3+";"+resp4+";"+status_wr+";"+qty+";"+ap_num+";"+work_time+";"+actual_date+";"+actual_date_2+";"+actual_date_3+";"+actual_date_4+";"+actual_date1+";"+actual_date2+";"+actual_date3+";"+actual_date4+";"+user+";"+hours1+";"+hours1_2+";"+hours1_3+";"+hours1_4+";"+min1+";"+hours2+";"+hours2_2+";"+hours2_3+";"+hours2_4+";"+min2+";"+user_number;
//				        System.out.println("SELECT WORKED: "+total_rez);
					}
					catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2430!");
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
				//для формы обновления таблицы PS
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2494!");
			        } finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt17.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs17.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
					return total_rez_ps;
				}
//*****************************************************************************************************************************				
				//Получаем begin_date и end_date для расчета кол-ва записей и заполнения таблицы hmmr_pm_year
				//SELECT p.id, p.PM_Cycle, ps.begin_date, ps.end_date from hmmr_pm p INNER JOIN hmmr_pm_cycle ps ON ps.Type = p.PM_Cycle where p.id = 3426
				@SuppressWarnings("static-access")
				public ObservableList<String> _select_for_pmplan(String group)
				{
					ObservableList<String> list = FXCollections.observableArrayList();
					try {
						String query = "select ps.Pereodic, ps.begin_date, ps.end_date, hps.FL03_Shop_s, p.PM_Resp from hmmr_pm p INNER JOIN pm_inst pi ON pi.num_instruction = p.Instruction_num INNER JOIN hmmr_pm_cycle ps ON ps.Type = pi.PM_Cycle1 INNER JOIN hmmr_plant_structure hps ON hps.id = p.Eq_ID where p.PM_Group = "+"'"+group+"'"+";";
						
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2562!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return list;
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!				
				//Получаем номер записи в action plan
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2593!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return total_apnum_rez;
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
				
////////////////////////////////////////////////////////////////////ACTION PLAN////////////////////////////////////////////////////////////////////////				
				//Получаем userid в action plan
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2622!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return userid;
				}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
				//Получаем userid в по id сотрудника
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2651!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return userid;
				}
/////////////////////////////////////////////////////////////////////////ACTION PLAN/////////////////////////////////////////////////////////////////////////				
				//Получаем сокращенное имя ответственного за задачу в action plan
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2680!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return userid;
				}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
///////////////////////////////////////////////////////////////ACTION PLAN//////////////////////////////////////////////////////////////////////////////////
				//Получаем сокращенное имя хозяина задачи в action plan
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2709!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return tm;
				}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
				
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK_RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!				
				//Проверяем статус задачи в WR
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2738!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return status;
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
				
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//Проверяем все ли записи для одной задачи из АР подтверджены
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2766!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return total_qtychk_rez;
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//Проверяем заполнение Work_time для одной задачи, если заполнен, то всем остальным кто делал какие-то работы по этой задачи WT ставить не надо
				//так как он один на всю задачу только для CM
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2800!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return total_wtchk_rez;
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!				
				//Узнаем id последней вставленной записи
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2875!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return lst_id;
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!				
				//Узнаем зпалнены ли поля RESP2,RESP3,RESP4
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2859!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return lst_id;
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
				
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//Берем значение из нужного нам поля поля
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2885!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return lst_id;
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!	
				
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//Проверяем ввел ли кто-то общее время ремонта, если да, то дизеблим всю строку с возможностью ввода общего времени ремонта
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2911!");
			        } finally {

			        	try { cn.con.close(); } catch(SQLException se) { }
			            try { stmt9.close(); } catch(SQLException se) {  }
			            try { rs9.close(); } catch(SQLException se) {  }
			        }
					return total_wtchk_rez;
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!		
		//для формы добавления в таблицу PM_Instruction заполняем автоматом 3 текстовых поля: Период выполнения, кол-во моточасов, кол-во недель
		// !!!!!!!!!!!!!!!!!!!! ПОКА НЕ НУЖНА!! УСТАРЕЛА!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 		
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
	//Обновляем запись в PM_CYCLE
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2976!");
		}
    	finally {
            //close connection ,stmt and resultset here
        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
	}
	
	//Обновляем столбец Pereodic в PM_CYCLE в соответствии с количеством машин в год для прогрессивных ППР 
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 2998!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		
		//Обновляем столбец chk_sel в PM_CYCLE чтобы узнать при повторном запуске программы, какой радиобаттон мы сделали активным 
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3020!");
			}
		   	finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
	
	//Обновляем запись в hmmr_pm_year, если запись добавили в Action Plan
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3042!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!WORK RECORDING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!		
		//Обновляем запись в hmmr_work_recording, если задачу подтвердил хозяин
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3064!");
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
				//Обновляем запись в hmmr_work_recording, если задачу подтвердил ответственный за задачу
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3086!");
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
				//Обновляем запись в hmmr_action_plan, если задачу подтвердил ответственный за задачу для поля исполнитель
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3108!");
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
				//Обновляем запись в hmmr_action_plan, с целью сделать желтым цветом исполнителя если все записи в WR по задаче подтверждены
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3130!");
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
				//Обновляем запись в hmmr_work_recording, если в одну запись мы добавили несколько исполнителей
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3152!");
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
				//Удаляем запись в hmmr_action_plan, если она всеми подтверждена
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3174!");
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
	//апдейтим запись в таблице справочнике Type_PM
	public void _update_rec_tpm(String type, String desc, String id)
	{
		String query = "UPDATE hmmr_type_pm SET Type_PM = "+"'"+type+"'"+", Description = "+"'"+desc+"'"+" WHERE id = "+"'"+id+"'"+";";
		
		try {
			cn.ConToDb();
			stmt = cn.con.createStatement();
			stmt.executeUpdate(query);
			//log.log(Level.INFO, "STATUS RING WAS UPDATED");
		} catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3196!");
		}
    	finally {
            //close connection ,stmt and resultset here
        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
	}
	
	//апдейтим запись в таблице PM Instruction
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3220!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		
		//апдейтим запись в таблице PM
				@SuppressWarnings("static-access")
				public void _update_rec_pm(String id, String num_instruction, String Group_EQ, String Otv, String Date_Expire, String OnOff_Line)
				{
					String query = "UPDATE hmmr_pm SET Instruction_num = "+"'"+num_instruction+"'"+",PM_Group = "+"'"+Group_EQ+"'"+",PM_Resp = "+"'"+Otv+"'"+",Date_Beforehand = "+"'"+Date_Expire+"'"+",OnOff_Line = "+"'"+OnOff_Line+"'"+" where id = "+"'"+id+"'"+";"; //"UPDATE pm_inst SET Type_PM = "+"'"+type+"'"+", Description = "+"'"+desc+"'"+" WHERE id = "+"'"+id+"'"+";";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3416!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
//////////////////////////////////////////////////////////////////////ACTION PLAN/////////////////////////////////////////////////////////////////////////////////				
				//апдейтим запись в таблице AP
				@SuppressWarnings("static-access")
				public void _update_rec_ap(String id, String PM_Num, String Type, String Description, LocalDate Due_Date, String Equipment, String Otv_For_Task, String Otv, String shop, String icon)
				{
					String query = "UPDATE hmmr_action_plan SET PM_Num = "+"'"+PM_Num+"'"+",Type = "+"'"+Type+"'"+",Description = "+"'"+Description+"'"+",Due_Date = "+"'"+Due_Date+"'"+",Equipment = "+"'"+Equipment+"'"+",Otv_For_Task = "+"'"+Otv_For_Task+"'"+",Otv = "+"'"+Otv+"'"+",shop = "+"'"+shop+"'"+",Icon = "+"'"+icon+"'"+" where id = "+"'"+id+"'"+";"; //"UPDATE pm_inst SET Type_PM = "+"'"+type+"'"+", Description = "+"'"+desc+"'"+" WHERE id = "+"'"+id+"'"+";";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3266!");
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
				//апдейтим запись в таблице WR
				@SuppressWarnings("static-access")
				public void _update_rec_wr(String id, String ap_num, String WSH, String Group_EQ, String Line, String Station, String Equip, String Equipment, String Record_Type, String Resp1,String Resp2,String Resp3,String Resp4, String Status, String Shift_Report, String Required_Action, LocalDate Actual_Date,LocalDate Actual_Date_2,LocalDate Actual_Date_3,LocalDate Actual_Date_4, String Actual_Time, LocalDate Actual_Date1,LocalDate Actual_Date2,LocalDate Actual_Date3,LocalDate Actual_Date4, String Actual_Time1,String Actual_Time2,String Actual_Time3,String Actual_Time4, LocalTime Hours1,LocalTime Hours1_2,LocalTime Hours1_3,LocalTime Hours1_4, LocalTime Hours2,LocalTime Hours2_2,LocalTime Hours2_3,LocalTime Hours2_4)
				{
					String query = "UPDATE hmmr_work_recording SET ap_num = "+"'"+ap_num+"'"+",user_number = "+"'"+conn_connector.USER_ID+"'"+",FL_WSH = "+"'"+WSH+"'"+",FL_Group = "+"'"+Group_EQ+"'"+",FL_Line = "+"'"+Line+"'"+",FL_Station = "+"'"+Station+"'"+",FL_Equipment = "+"'"+Equip+"'"+",Equipment_Full = "+"'"+Equipment+"'"+",Record_Type = "+"'"+Record_Type+"'"+",Task_Resp_ID = "+"'"+Resp1+"'"+",_Resp2 = "+"'"+Resp2+"'"+",_Resp3 = "+"'"+Resp3+"'"+",_Resp4 = "+"'"+Resp4+"'"+",WR_Executor_Confirmed = "+"'"+Status+"'"+",Task_Description = "+"'"+Shift_Report+"'"+",Task_Report = "+"'"+Required_Action+"'"+",WR_Begin_Date = "+"'"+Actual_Date+"'"+",_Actual_Date_2 = "+"'"+Actual_Date_2+"'"+",_Actual_Date_3 = "+"'"+Actual_Date_3+"'"+",_Actual_Date_4 = "+"'"+Actual_Date_4+"'"+",CM_DownTime = "+"'"+Actual_Time+"'"+",WR_End_Date = "+"'"+Actual_Date1+"'"+",_Actual_Date2 = "+"'"+Actual_Date2+"'"+",_Actual_Date3 = "+"'"+Actual_Date3+"'"+",_Actual_Date4 = "+"'"+Actual_Date4+"'"+",WR_Work_Time = "+"'"+Actual_Time1+"'"+",_Actual_Time2 = "+"'"+Actual_Time2+"'"+",_Actual_Time3 = "+"'"+Actual_Time3+"'"+",_Actual_Time4 = "+"'"+Actual_Time4+"'"+",WR_Work_Time_Begin = "+"'"+Hours1+"'"+",_Hours1_2 = "+"'"+Hours1_2+"'"+",_Hours1_3 = "+"'"+Hours1_3+"'"+",_Hours1_4 = "+"'"+Hours1_4+"'"+",WR_Work_Time_End = "+"'"+Hours2+"'"+",_Hours2_2 = "+"'"+Hours2_2+"'"+",_Hours2_3 = "+"'"+Hours2_3+"'"+",_Hours2_4 = "+"'"+Hours2_4+"'"+" where id = "+"'"+id+"'"+";"; //"UPDATE pm_inst SET Type_PM = "+"'"+type+"'"+", Description = "+"'"+desc+"'"+" WHERE id = "+"'"+id+"'"+";";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3288!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!				
				//апдейтим запись в таблице PM Plan
				@SuppressWarnings("static-access")
				public void _update_rec_pmplan(String id, LocalDate Date, String oft)
				{
					String query = "UPDATE hmmr_pm_year SET data = "+"'"+Date+"'"+",OFT = "+"'"+oft+"'"+" where id = "+"'"+id+"'"+";"; //"UPDATE pm_inst SET Type_PM = "+"'"+type+"'"+", Description = "+"'"+desc+"'"+" WHERE id = "+"'"+id+"'"+";";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3310!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
//****************************************************** PLANT STRUCTURE ********************************************************				
				//апдейтим запись в таблице PS
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
					",Station_Label = "+"'"+Station_Label+"'"+",Equipment_Folder_Link = "+"'"+Equipment_Folder_Link+"'"+",Resp_Planner_Group = "+"'"+Resp_Planner_Group+"'"+",Assets_Inventory_Number = "+"'"+Assets_Inventory_Number+"'"+",Assets_OS1_Number = "+"'"+Assets_OS1_Number+"'"+",Assets_Start_up_Date = "+"'"+Assets_Start_up_Date+"'"+",Cost_Center = "+"'"+Cost_Center+"'"+",EQ_Integrator = "+"'"+EQ_Integrator+"'"+
					",Site_Location = "+"'"+Site_Location+"'"+",Site_Coordinates = "+"'"+Site_Coordinates+"'"+",Site_Altitude = "+"'"+Site_Altitude+"'"+
					",Site_CHAMBER = "+"'"+Site_CHAMBER+"'"+",TR_CU = "+"'"+TR_CU+"'"+",TR_CU_Link = "+"'"+TR_CU_Link+"'"+",Hazardous = "+"'"+Hazardous+"'"+
					",Key_equipment = "+"'"+Key_equipment+"'"+",EQ_Type = "+"'"+EQ_Type+"'"+",EQ_Serial_Number = "+"'"+EQ_Serial_Number+"'"+",EQ_Manufacture = "+"'"+EQ_Manufacture+"'"+",EQ_Technical_Characteristic = "+"'"+EQ_Technical_Characteristic+"'"+
					",Responsobility = "+"'"+Responsobility+"'"+",M_Electric = "+"'"+M_Electric+"'"+",M_Air = "+"'"+M_Air+"'"+",M_Water = "+"'"+M_Water+"'"+
					",M_Cold_Water = "+"'"+M_Cold_Water+"'"+",M_Hot_Water = "+"'"+M_Hot_Water+"'"+",M_RO_Water = "+"'"+M_RO_Water+"'"+",M_Gas = "+"'"+M_Gas+"'"+" where id = "+"'"+id+"'"+";"; //"UPDATE pm_inst SET Type_PM = "+"'"+type+"'"+", Description = "+"'"+desc+"'"+" WHERE id = "+"'"+id+"'"+";";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3337!");
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
				//апдейтим запись в таблице PS, если делаем удаление записи
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3359!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
//*******************************************************************************************************************************				
				//апдейтим запись в таблице PM, если делаем удаление записи
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3381");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
				
				//апдейтим запись в таблице PM_INST, если делаем удаление записи
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3403!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
	
				//апдейтим запись в таблице PM_Cycle, если делаем удаление записи
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3425!");
					}
			    	finally {
			            //close connection ,stmt and resultset here
			        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
			            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
			        }
				}
				
				//апдейтим запись в таблице Type_PM, если делаем удаление записи
				@SuppressWarnings("static-access")
				public void _update_rec_typepm_del(String Id)
				{
					String query = "UPDATE hmmr_type_pm SET del_rec = 1 where id = "+"'"+Id+"'"+";"; //"UPDATE pm_inst SET Type_PM = "+"'"+type+"'"+", Description = "+"'"+desc+"'"+" WHERE id = "+"'"+id+"'"+";";
					
					try {
						cn.ConToDb();
						stmt = cn.con.createStatement();
						stmt.executeUpdate(query);
						//log.log(Level.INFO, "STATUS RING WAS UPDATED");
					} catch (SQLException e) {
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3447!");
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3469!");
        } finally {
            //close connection ,stmt and resultset here
        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
	}
	
	@SuppressWarnings("static-access")
	public void _delete_rec_tpm(String id)
	{
		try {
			String query = "delete from hmmr_type_pm where id = "+"'"+id+"'"+";";
			
			cn.ConToDb();
			stmt = cn.con.createStatement();
			stmt.executeUpdate(query);
		}
		catch (SQLException e) {
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3489!");
        } finally {
            //close connection ,stmt and resultset here
        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
	}
	//Удаляем запись из таблицы PM Instruction
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
			s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3509!");
        } finally {
            //close connection ,stmt and resultset here
        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
	}
	
	//Удаляем запись из таблицы PM
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3530!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		/**
		 *Заполняем данными таблицу, для справочника приоритев 
		 * @return - возвращаем набор необходимых данных
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3558!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt19.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs19.close(); } catch(SQLException se) { /*can't do anything */ }
	        }

			return list;
		}
		/**
		 * Вставляем запись в БД для таблицы справочника приоритетов
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3594!");
			}
		   	finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
		/**
		 * Запрос необходим при вызове окна обновления справочника приоритетов,
		 * чтобы были заполнены все поля в окне обновления
		 * @param id - id записи для которой вызывается окно обновления
		 * @return - возвращаем набор данных
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3623!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt10.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs10.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return total_rez_prior;
		}
		/**
		 * Делаем апдейт записи в БД в таблицу hmmr_mu_prior
		 * @param Id - id записи которую апдейтим
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3656!");
			}
	    	finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
		}
		/**
		 * Удаляем запись из таблицы hmmr_mu_prior, путем апдейта значения поля del_rec
		 * с 0 на 1
		 * @param id - id записи, которую удаляем
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3678!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
		}
		/**
		 * Получаем признак удаления записи из hmmr_action_plan, если del_rec = 1 - запись удалена и
		 * значит задача выполнена. Нужна чтобы закрашивать ячейки в зеленый цвет для выполненных задач.
		 * @param id - id записи, которую проверяем на признак удаления
		 * @return - возвращаем набор данных
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3754!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt10.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs10.close(); } catch(SQLException se) { /*can't do anything */ }
	        }
			return del_rec;
		}
		/**
		 * Заполняем значениями ComboBox приоритет
		 * @return - Возвращаем полученный набор данных
		 */
		@SuppressWarnings({ "static-access"})
		public ObservableList<String> _select_prior()
		{
			ObservableList<String> list = FXCollections.observableArrayList();
			
			try {
				String query = "select ID_TSK, Name_Prior from hmmr_mu_prior group by ID_TSK;";
				
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3786");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
		       }

			return list;
		}	
		/**
		 * Получаем путь к картинке приоритета
		 * @param id - ID_TSK приоритета
		 * @return - Возвращаем полученный набор данных
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3827");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt6.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs6.close(); } catch(SQLException se) { /*can't do anything */ }
		       }

			return list;
		}	
		/**
		 * Получаем Полное описание к картинке приоритета, чтобы вывести его в виде подсказки
		 * при наведении мышки на иконку приоритета в таблице Action Plan
		 * @param id - id в AP чтобы получить имя приоритета, а по нему и полное описание
		 * @return - Возвращаем полученный набор данных
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3827");
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
		 * Заполняем данными таблицу при применениии фильтра цех в
		 * Plant Structure
		 * @return - Возвращает набор данных типа ObservableList и 
		 * 			заполняет ими таблицу TableView.
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3900!");
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
		 * Заполняем данными таблицу при применениии фильтра цех и группа в
		 * Plant Structure
		 * @return - Возвращает набор данных типа ObservableList и 
		 * 			заполняет ими таблицу TableView.
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 3978!");
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
		 * Заполняем данными таблицу при применениии фильтра цех, группа и линия в
		 * Plant Structure
		 * @return - Возвращает набор данных типа ObservableList и 
		 * 			заполняет ими таблицу TableView.
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 4056!");
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
		 * Заполняем данными таблицу при применениии фильтра цех, группа, линия и станция в
		 * Plant Structure
		 * @return - Возвращает набор данных типа ObservableList и 
		 * 			заполняет ими таблицу TableView.
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 4134!");
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
		 * Заполняем данными таблицу при применениии фильтра цех, группа, линия, станция и оборудование в
		 * Plant Structure
		 * @return - Возвращает набор данных типа ObservableList и 
		 * 			заполняет ими таблицу TableView.
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 4212!");
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
				 * Получаем id из Plant Structure
				 * @return - Возвращает id
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
						s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 4479!");
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
		 * Вставляем последню запись в таблицу Plant Structure
		 * @param id - id строки которую надо продублировать
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 4285!");
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
		 * Получаем id предыдущей записи для таблицы
		 * @param id - текущее id для данной таблицы
		 * @param table - таблица для которой необходимо получить предыдущее id
		 * @return - возвращаем id предыдущей записи
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 4316!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt19.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs19.close(); } catch(SQLException se) { /*can't do anything */ }
	        }

			return list;
		}
		/**
		 * Получаем id следующей записи для таблицы
		 * @param id - текущее id для данной таблицы
		 * @param table - таблица для которой необходимо получить следующее id
		 * @return - возвращаем id слудующей записи
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 4360!");
	        } finally {
	            //close connection ,stmt and resultset here
	        	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { stmt19.close(); } catch(SQLException se) { /*can't do anything */ }
	            try { rs19.close(); } catch(SQLException se) { /*can't do anything */ }
	        }

			return list;
		}
		/**
		 * Получаем сокращенные названия цех.группа.линия.станция.оборудование
		 * @param id - id записи в PS
		 * @return - возвращаем данные
		 */
		@SuppressWarnings({ "static-access"})
		public String _select_fillpm_equip(String id)
		{
			String list = "null";
			
			try {
				String query = "select concat(hps.FL03_Shop_s,'.',hps.FL04_Group_s,'.',hps.FL05_Line_s,'.',hps.FL06_Station_s,'.',hps.FL07_Equipment_s) from hmmr_pm hpm INNER JOIN hmmr_plant_structure hps ON del_rec = 0 AND hps.id = "+"'"+id+"'"+";";
				
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
				s_class._AlertDialog(e.getMessage()+", "+ " ошибка в строке № 4589!");
		       } finally {
		           //close connection ,stmt and resultset here
		       	try { cn.con.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { stmt11.close(); } catch(SQLException se) { /*can't do anything */ }
		        try { rs11.close(); } catch(SQLException se) { /*can't do anything */ }
		       }
			return list;
		}
}