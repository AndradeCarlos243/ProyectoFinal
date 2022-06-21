package uv.mx.carlos.proyectofinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class Principal extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ContenedorFarmacia farmacia = new ContenedorFarmacia();
        Scene scene = new Scene(farmacia, 1240, 720);
        scene.getStylesheets().add(getClass().getResource("estilos.css").toExternalForm());
        stage.getIcons().add(new Image(Principal.class.getResourceAsStream("Logo.png")));
        stage.setTitle("Farmacia FEI");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}