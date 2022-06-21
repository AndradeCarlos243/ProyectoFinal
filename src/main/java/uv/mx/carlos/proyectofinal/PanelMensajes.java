package uv.mx.carlos.proyectofinal;

import accesodatos.modelo.Conexion;
import javafx.application.Platform;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PanelMensajes extends TabPane {
    private Tab tabMensajesUsuarios;
    private Tab tabMensajesServidor;
    private TextArea txtMensajesUsuarios;
    private TextArea txtMensajesServidor;
    private Conexion conexion;

    public PanelMensajes(Conexion conexion) {
        this.conexion = conexion;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        tabMensajesUsuarios = new Tab("Mensajes de los usuarios");
        tabMensajesServidor = new Tab("Información del servidor");
        txtMensajesUsuarios = new TextArea();
        txtMensajesUsuarios.setEditable(false);
        txtMensajesServidor = new TextArea();
        txtMensajesServidor.setEditable(false);
        tabMensajesUsuarios.setContent(txtMensajesUsuarios);
        tabMensajesServidor.setContent(txtMensajesServidor);
        getTabs().addAll(tabMensajesUsuarios, tabMensajesServidor);
        habilitar(false);
    }

    public void habilitar(boolean hab) {
        tabMensajesUsuarios.setDisable(!hab);
        tabMensajesServidor.setDisable(!hab);
        if (hab == true) {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(() -> {
                try {
                    while(true) {
                        String mensajeC = conexion.recibirMensaje();
                        if (mensajeC.startsWith("00"))
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    String contenido = txtMensajesServidor.getText() + mensajeC + "\n";
                                    txtMensajesServidor.setText(contenido);
                                }
                            });
                        else {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    String contenido = txtMensajesUsuarios.getText() + mensajeC + "\n";
                                    txtMensajesUsuarios.setText(contenido);
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            });
        }
    }
}
