package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import share_class.s_class;

public class _connect {

	private static final String url = "jdbc:mysql://192.168.100.245:3306/hmmr_mu";
    private static final String user = "hmmr";
    private static final String password = "Ro145437";
    public static Connection con;
                
	public _connect() {
		
	}
	/*****************************************
	 * ������������ � �� MySQL				 *
	 *****************************************/
	public void ConToDb()
	{
		try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
        	s_class._AlertDialog(e.getMessage(), "��������! ������ ����������� � ��.");
        }
	}
	
	public Connection ConToDb1()
	{
		try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
        	s_class._AlertDialog(e.getMessage(), "��������! ������ ����������� � ��.");
        }
		return con;
	}

}
