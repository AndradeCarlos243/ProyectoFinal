package uv.mx.carlos.proyectofinal;

import accesodatos.modelo.Conexion;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;

public class PanelConexion extends TitledPane {
    private String servidor;
    private int puerto;
    private String usuario;
    private Label etiServidor;
    private Label etiPuerto;
    private Label etiUsuario;
    private TextField txtServidor;
    private TextField txtPuerto;
    private TextField txtUsuario;
    private Button btnConectar;
    private Button btnDesconectar;
    private Button btnGraficos;
    private Conexion conexion;
    private PanelMensajes panelMensajes;
    private PanelMensaje panelMensaje;

    public PanelConexion(String servidorDefault, int puertoDefault, Conexion conexion, PanelMensajes panelMensajes, PanelMensaje panelMensaje) {
        servidor = servidorDefault;
        puerto = puertoDefault;
        this.conexion = conexion;
        this.panelMensaje = panelMensaje;
        this.panelMensajes = panelMensajes;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        HBox contenedor = new HBox();
        contenedor.setSpacing(10);
        contenedor.setPadding(new Insets(10));
        etiServidor = new Label("Servidor:");
        etiPuerto = new Label("Puerto:");
        etiUsuario = new Label("Usuario:");
        txtServidor = new TextField(servidor);
        txtPuerto = new TextField(puerto+"");
        txtPuerto.setMaxWidth(60);
        txtUsuario = new TextField("");
        btnConectar = new Button("Conectar");
        btnDesconectar = new Button("Desconectar");
        btnGraficos = new Button("Gráficas");
        btnConectar.setOnAction(evt -> {conectarServidor();});
        btnDesconectar.setOnAction(evt -> {desconectarServidor();});
        btnGraficos.setOnAction(evt -> {mostrarVenataGraficos();});
        contenedor.getChildren().addAll(etiServidor, txtServidor, etiPuerto, txtPuerto, etiUsuario, txtUsuario, btnConectar, btnDesconectar, btnGraficos);
        setContent(contenedor);
        setText("Datos de Conexión...");
        setCollapsible(false);
        habilitar(true);
    }

    private void mostrarVenataGraficos() {
        VentanaGraficos ventana = new VentanaGraficos(conexion.getListaVendedores());
        ventana.show();
    }

    private void desconectarServidor() {
        if (conexion.desconectar()) {
            habilitar(true);
            panelMensajes.habilitar(false);
            panelMensaje.habilitar(false);
        }
    }

    private void conectarServidor() {
        servidor = txtServidor.getText();
        puerto = Integer.parseInt(txtPuerto.getText());
        usuario = txtUsuario.getText();
        if (conexion.conectar(servidor, puerto)) {
            habilitar(false);
            conexion.enviarNombreUsuario(usuario);
            panelMensajes.habilitar(true);
            panelMensaje.habilitar(true);
        }
    }

    public void habilitar(boolean hab) {
        txtServidor.setDisable(!hab);
        txtPuerto.setDisable(!hab);
        txtUsuario.setDisable(!hab);
        btnConectar.setVisible(hab);
        btnDesconectar.setVisible(!hab);
    }
}
