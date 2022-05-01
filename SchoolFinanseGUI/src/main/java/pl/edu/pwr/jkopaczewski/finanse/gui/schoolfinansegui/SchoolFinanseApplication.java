package pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SchoolFinanseApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SchoolFinanseApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 460);
        stage.setTitle("School Finanse");
        stage.setIconified(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(); }
}