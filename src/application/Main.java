package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import webPackUrl.WebPackUrl;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;




public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
        	Parent root = FXMLLoader.load(getClass().getResource("/resource/MyScene.fxml"));
            stage.setTitle("My Application");
            stage.setScene(new Scene(root));
//            stage.setScene(scene);
            System.out.println(root.toString());
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}