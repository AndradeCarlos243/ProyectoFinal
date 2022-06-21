package uv.mx.carlos.proyectofinal;

import accesodatos.modelo.Conexion;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;

public class PanelMensaje extends TitledPane {
    private Label etiMensaje;
    private TextField txtMensaje;
    private Button btnEnviarMensaje;
    private Conexion conexion;

    public PanelMensaje(Conexion conexion) {
        this.conexion = conexion;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        HBox contenedor = new HBox();
        contenedor.setPadding(new Insets(10));
        contenedor.setSpacing(10);
        etiMensaje = new Label("Mensaje:");
        txtMensaje = new TextField("");
        txtMensaje.setPromptText("Escriba el mensaje a enviar al chat");
        txtMensaje.setMinWidth(500);
        btnEnviarMensaje = new Button("Enviar mensaje!");
        btnEnviarMensaje.setOnAction(evt -> {enviarMensaje();});
        contenedor.getChildren().addAll(etiMensaje, txtMensaje, btnEnviarMensaje);
        setContent(contenedor);
        setCollapsible(false);
        setText("Mensaje a enviar...");
        habilitar(false);
    }

    private void enviarMensaje() {
        String mensaje = txtMensaje.getText();
        if (conexion.enviarMensaje(mensaje)) {
            txtMensaje.setText("");
            txtMensaje.requestFocus();
        }
    }

    public void habilitar(boolean hab) {
        txtMensaje.setDisable(!hab);
        btnEnviarMensaje.setDisable(!hab);
    }
}
