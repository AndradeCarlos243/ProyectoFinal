package uv.mx.carlos.proyectofinal;

import accesodatos.modelo.Conexion;
import javafx.scene.layout.BorderPane;

public class ContenedorPrincipalChat extends BorderPane {
    private PanelConexion panelConexion;
    private PanelMensajes panelMensajes;
    private PanelMensaje panelMensaje;
    private Conexion conexion;

    public ContenedorPrincipalChat() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        conexion = new Conexion();

        panelMensajes = new PanelMensajes(conexion);
        panelMensaje = new PanelMensaje(conexion);
        panelConexion = new PanelConexion("localhost",4800, conexion, panelMensajes, panelMensaje);
        setTop(panelConexion);
        setCenter(panelMensajes);
        setBottom(panelMensaje);
    }


}
