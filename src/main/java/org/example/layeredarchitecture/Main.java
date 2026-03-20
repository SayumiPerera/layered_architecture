//package org.example.layeredarchitecture;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.util.Objects;
//
//public class Main extends Application {
//
//    private static Scene scene;
//
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("IJSE");
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }
//
//    public static Parent loadFXML(String fxml) throws IOException {
//        return FXMLLoader.load(
//                Objects.requireNonNull(Main.class.getResource("/" + fxml + "layout.fxml"))
//        );
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//}
//
//


package org.example.layeredarchitecture;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"));
        stage.setTitle("IJSE");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        return FXMLLoader.load(
                Objects.requireNonNull(Main.class.getResource("/" + fxml + ".fxml"))
        );
    }

    public static void main(String[] args) {
        launch();
    }
}