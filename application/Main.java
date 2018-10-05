package application;
	

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	private Stage primaryStage;
	private AnchorPane rootLayout;
	public static Stage pStage;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("M&U - Authorize Window");
		try {
			InitConn();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void InitConn() throws IOException
	{
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("authorize.fxml"));
			rootLayout = (AnchorPane) loader.load();
						
			Scene scene = new Scene(rootLayout);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("Maintenance.png")));
			primaryStage.show();
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}
	
	@SuppressWarnings("unused")
	private void setPrimaryStage(Stage pStage) {
        Main.pStage = pStage;
    }
	public static Stage getPrimaryStage() {
        return pStage;
    }
	//Запускаем на выполнение любой файл, не только Excel!!!!!
	public void _run_excel(File path) throws IOException
	{
		getHostServices().showDocument(path.toURI().toURL().toExternalForm());
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
