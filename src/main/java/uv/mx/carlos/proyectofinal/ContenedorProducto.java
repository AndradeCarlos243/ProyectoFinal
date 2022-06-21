package uv.mx.carlos.proyectofinal;


import accesodatos.casos_uso.Productos;
import accesodatos.modelo.Producto;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;



public class ContenedorProducto extends GridPane {
    Label etiNombre;
    Label etiMarca;
    Label etiCantidad;
    Label etiPrecio;
    TextField txtNombre;
    TextField txtMarca;
    TextField txtCantidad;
    TextField txtPrecio;
    ImageView ivFoto;
    Button btnAsignarFoto;
    Button btnGuardar;
    String archivoFoto = "";
    boolean modificando=false;
    Producto producto;
    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("farmaciafei");
    //EntityManager em = emf.createEntityManager();
    Productos productosHelper;

    public ContenedorProducto(){
        inicializarComponentes();
        try{
            productosHelper = new Productos();
        }catch (Exception e){

        }
    }

    public ContenedorProducto(Producto producto){
        this.producto = producto;
        modificando = true;
        inicializarComponentes();
        llenarDatosProducto();
        try{
            productosHelper = new Productos();
        }catch (Exception e){

        }
    }


    private void inicializarComponentes(){
        this.setVgap(7);
        this.setHgap(7);
        this.setPadding(new Insets(7));
        //Etiquetas
            etiNombre = new Label ("Nombre:");
        etiMarca = new Label ("Marca:");
        etiCantidad = new Label ("Cantidad:");
        etiPrecio = new Label ("Precio:");


        //Campos de entrada
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre del producto");
        txtMarca = new TextField();
        txtMarca.setPromptText("Marca del fabricante");
        txtCantidad = new TextField();
        txtCantidad.setPromptText("Cantidad disponible");
        txtPrecio = new TextField();
        txtPrecio.setPromptText("Precio del producto");
        ivFoto = new ImageView();
        ivFoto.setFitWidth(116);
        ivFoto.setFitHeight(116);

        ScrollPane spane = new ScrollPane(ivFoto);
        spane.setMinHeight(120);
        spane.setMinWidth(120);
        spane.setMaxHeight(120);
        spane.setMaxWidth(120);
        spane.setStyle("-fx-border-color: black; -fx-border-width:1; -fx-border-style: solid");
        btnAsignarFoto = new Button("Subir Foto");
        btnAsignarFoto.setMinWidth(100);
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(evt ->{
            if(validarDatosProducto())
                if(guardarProducto()){
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Éxito");
                    alerta.setContentText("Guardado");
                    alerta.setHeaderText("Los datos del producto se han almacenado de manera correcta");
                    limpiarCampos();
                    ContenedorFarmacia farmacia = new ContenedorFarmacia();
                    Scene scene = new Scene(farmacia, 1240, 720);
                    Stage nVentana = new Stage();
                    scene.getStylesheets().add(getClass().getResource("estilos.css").toExternalForm());
                    nVentana.getIcons().add(new Image(Principal.class.getResourceAsStream("Logo.png")));
                    nVentana.setTitle("Farmacia FEI");
                    nVentana.setScene(scene);
                    nVentana.show();
                    Window window = getScene().getWindow();

                    if (window instanceof Stage){
                        ((Stage) window).close();
                    }
                    alerta.show();
                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error");
                    alerta.setContentText("Favor de verificar datos y la conexión al servidor");
                    alerta.setHeaderText("Hubo un error al almacenar la información");
                    alerta.show();
                }
        });
        btnGuardar.setMaxWidth(100);
        txtNombre.setMaxWidth(150);
        txtMarca.setMaxWidth(150);
        txtCantidad.setMaxWidth(150);
        txtPrecio.setMaxWidth(150);

        btnAsignarFoto.setOnAction(evt-> {
            cargarFoto();
        });

        //En la fila 1 estan las etiquetas y en la 2 los componentes
        this.add(spane, 0, 0, 1, 4); //Columna 0, fila 0, ocupando 1 columna y 3 filas
        this.add(etiNombre, 1, 0);
        this.add(txtNombre, 2, 0);

        this.add(etiMarca, 1,1);
        this.add(txtMarca, 2, 1);

        this.add(etiCantidad, 1,2);
        this.add(txtCantidad, 2, 2);

        this.add(etiPrecio, 1,3);
        this.add(txtPrecio, 2, 3);

        this.add(btnAsignarFoto, 0, 4);


        this.add(btnGuardar, 1,6,2, 1);
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtMarca.setText("");
        txtCantidad.setText("");
        txtPrecio.setText("");
    }

    //Este se puede denominar como controlador del mvc
    private void llenarDatosProducto(){
        txtNombre.setText(String.valueOf(producto.getNombre()));
        txtMarca.setText(producto.getMarca());
        txtCantidad.setText(String.valueOf(producto.getCantidad()));
        txtPrecio.setText(String.valueOf(producto.getPrecio()));
        File foto = new File("./Fotos/"+producto.getNombre()+".jpg");
        System.out.println(foto);
        if (foto.exists()){
            Image imagen = new Image(foto.toURI().toString());
            ivFoto.setImage(imagen);
        }
    }

    private boolean validarDatosProducto() {
        boolean valido = true;
        String mensajes="";
        if(txtNombre.getText() == null || txtNombre.getText().trim().length()==0) {
            valido = false;
            mensajes += "* Nombre invalido\n";
        }
        if(txtMarca.getText() == null || txtMarca.getText().trim().length()==0) {
            valido = false;
            mensajes += "* Marca invalida\n";
        }
        if(txtCantidad.getText() == null || txtCantidad.getText().trim().length()==0) {
            valido = false;
            mensajes += "* Cantidad invalida\n";
        }
        if(!valido){
            Alert alertaSalir = new Alert(Alert.AlertType.ERROR);
            alertaSalir.setTitle("Error");
            alertaSalir.setContentText(mensajes);
            alertaSalir.setHeaderText("Hay errores en los datos, favor de corregir");
            alertaSalir.show();
        }
        return valido;
    }

    private void cargarFoto(){
        FileChooser dialogoArch = new FileChooser();
        dialogoArch.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imagenes JPEG","*.jpg; *.jpeg"),
                new FileChooser.ExtensionFilter("Imagenes PNG","*.png"));
        File archivo = dialogoArch.showOpenDialog(null);
        if(archivo != null){
            Image imagen = new Image(archivo.toURI().toString());
            archivoFoto = archivo.getAbsolutePath();
            ivFoto.setImage(imagen);
        }
    }

    private boolean guardarProducto(){
        try {
            if (!modificando){
                producto = new Producto();
            }
            producto.setNombre(txtNombre.getText());
            producto.setMarca(txtMarca.getText());
            producto.setCantidad(Integer.parseInt(txtCantidad.getText()));
            producto.setPrecio(Double.parseDouble(txtPrecio.getText()));
            if (archivoFoto.length()>0){

                //codigo que copia una imagen seleccionada a un directorio de proyecto
                File fImagenOriginal = new File(archivoFoto);

                File fImagenNueva = new File("./Fotos/"+producto.getNombre()+".jpg");
                long tam = fImagenOriginal.length();
                byte []arr = new byte[(int)tam];
                FileInputStream fis = new FileInputStream(fImagenOriginal);
                arr = fis.readAllBytes();
                FileOutputStream fos = new FileOutputStream(fImagenNueva);
                fos.write(arr);
                fis.close();
                fos.close();

            }
            if(!modificando){
                System.out.println(producto);
                return productosHelper.insertar(producto);
            }else{
                return productosHelper.actualizar(producto);
            }
        } catch (Exception e){
            System.out.println(e);
            return false;
        }


    }
}
