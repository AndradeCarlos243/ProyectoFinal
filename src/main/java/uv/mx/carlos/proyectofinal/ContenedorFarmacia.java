package uv.mx.carlos.proyectofinal;

import accesodatos.casos_uso.Productos;
import accesodatos.casos_uso.Ventas;
import accesodatos.modelo.Producto;
import accesodatos.modelo.Venta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.util.List;

public class ContenedorFarmacia extends BorderPane {

    private TableView tablaProductos;
    private TableView tablaVentas;
    private TabPane panelMostrar;
    private Tab tProductos = null;
    private Tab tVentas = null;
    private Tab tProductosTiles = null;
    private Productos productosHelper;
    private Ventas ventasHelper;
    private List<Producto> listaProductos = null;
    private List<Venta> listaVentas = null;
    private ObservableList<Producto> oListaProductos = null;
    private ObservableList<Venta> oListaVentas = null;
    private int nClicks=0;
    private TilePane tablaProductosT=null;

    private Font tipoLetraMarca;
    private Font tipoLetraNombre;
    private Producto productoSeleccionadoT;

    private MenuBar barraMenus;

    private Menu menuProducto;
    private Menu menuVenta;
    private Menu menuOpciones;

    //Producto
    private MenuItem opNuevo;
    private MenuItem opModificar;
    private MenuItem opEliminar;


    //Venta
    private MenuItem opNuevo2;
    private MenuItem opEliminar2;


    //Opciones
    private MenuItem opChat;
    private MenuItem opSalir;


    @FXML
    private void initialize() throws Exception{
        inicializarComponentes();
        inicializarComponentes2();
        crearMenu();
        cargarProductos();
        cargarVentas();
    }


    ContenedorFarmacia(){
       getStyleClass().add("panelPrincipal");
        inicializarComponentes();
        inicializarComponentes2();
        inicializarComponentes3();
        crearMenu();
        cargarProductos();
        cargarProductosTiles();
        cargarVentas();
    }

    private void cargarProductosTiles() {
        try{
            productosHelper = new Productos();
            listaProductos = productosHelper.Lista();
            oListaProductos = FXCollections.observableList(listaProductos);
            for(Producto producto: oListaProductos){
                String nombre = producto.getNombre();
                File foto = new File("./Fotos/"+nombre+".jpg");
                if (foto.exists()){

                }else
                    foto = new File("./Fotos/defecto.jpg");

                Image imagen = new Image(foto.toURI().toString());
                Circle circulo = new Circle();
                circulo.setRadius(75);
                circulo.setFill(new ImagePattern(imagen));

                Label etiNombre = new Label(nombre);
                Label etiMarca = new Label(producto.getMarca());

                VBox contenedorProducto = new VBox();
                contenedorProducto.setAlignment(Pos.TOP_CENTER);
                contenedorProducto.getChildren().addAll(circulo, etiMarca, etiNombre);

                Hyperlink ligaProducto = new Hyperlink();
                //Esta forma tiene un metodo para cada elemento de hiperlink, en vez de uno solo con la tabla
                ligaProducto.setOnMouseClicked(evtm -> {
                    productoSeleccionadoT = (Producto)producto;
                    if (productoSeleccionadoT != null){
                        opModificar.setDisable(false);
                        opEliminar.setDisable(false);
                    }
                    if (evtm.getClickCount() == 2){
                        if(productoSeleccionadoT != null){
                            crearVentanaModificar(productoSeleccionadoT);
                            Window window = getScene().getWindow();
                            if (window instanceof Stage){
                                ((Stage) window).close();
                            }
                        }
                    }else if(evtm.isAltDown()){
                        if(productoSeleccionadoT != null){
                            crearDialogoEliminar(productoSeleccionadoT);
                            Window window = getScene().getWindow();
                            if (window instanceof Stage){
                                ((Stage) window).close();
                            }
                        }
                    }
                });
                ligaProducto.setGraphic(contenedorProducto);
                tablaProductosT.getChildren().add(ligaProducto);
            }
        }catch (Exception e){

        }
    }

    private void inicializarComponentes3() {
        tipoLetraMarca = Font.font("Arial", FontWeight.BOLD, 14);
        tablaProductosT = new TilePane();
        tablaProductosT.setHgap(12);
        tablaProductosT.setVgap(12);
        tablaProductosT.setPadding(new Insets(10));
        tablaProductosT.setPrefColumns(3);
        setCenter(new ScrollPane(tablaProductosT));

        tProductosTiles.setContent(tablaProductosT);
    }

    private void cargarProductos() {
        try{
            productosHelper = new Productos();
            listaProductos = productosHelper.Lista();
            oListaProductos = FXCollections.observableList(listaProductos);
            tablaProductos.setItems(oListaProductos);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void cargarVentas() {
        try{
            ventasHelper = new Ventas();
            listaVentas = ventasHelper.Lista();
            oListaVentas = FXCollections.observableList(listaVentas);
            tablaVentas.setItems(oListaVentas);
            System.out.println(listaVentas);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void inicializarComponentes() {
        panelMostrar = new TabPane();
        tProductosTiles = new Tab("Productos tiles");
        tProductos = new Tab("Productos");
        tVentas = new Tab("Ventas");
        panelMostrar.getTabs().addAll(tProductosTiles, tProductos, tVentas);

        TableColumn<Producto, Integer> colId = new TableColumn("Id");
        TableColumn<Producto, String> colNombre = new TableColumn("Nombre");
        TableColumn<Producto, String> colMarca = new TableColumn("Marca");
        TableColumn<Producto, Integer> colCantidad = new TableColumn("Cantidad");
        TableColumn<Producto, Double> colPrecio = new TableColumn("Precio");


        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));


        tablaProductos = new TableView();
        tablaProductos.getColumns().addAll(colId, colNombre, colMarca, colCantidad, colPrecio);
        tablaProductos.setOnMouseClicked(evtm ->{
            Producto productoSeleccionado = (Producto) tablaProductos.getSelectionModel().getSelectedItem();
            if (productoSeleccionado != null){
                opModificar.setDisable(false);
                opEliminar.setDisable(false);
            }
            if (evtm.getClickCount() == 2) {
                if (productoSeleccionado != null) {
                    crearVentanaModificar(productoSeleccionado);
                    Window window = getScene().getWindow();

                    if (window instanceof Stage){
                        ((Stage) window).close();
                    }
                }
            }
        });
        tProductos.setContent(tablaProductos);

    }

    private void inicializarComponentes2() {

        TableColumn<Venta, Integer> colId = new TableColumn("Id");
        TableColumn<Venta, String> colProductos = new TableColumn("Productos");
        TableColumn<Venta, String> colVendedor = new TableColumn("Vendedor");
        TableColumn<Venta, String> colSucursal = new TableColumn("Sucursal");
        TableColumn<Venta, Double> colTotal = new TableColumn("Total");
        TableColumn<Venta, Double> colDescuento = new TableColumn("Descuento");
        TableColumn<Venta, Double> coltotalDesc = new TableColumn("Total con descuento");


        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductos.setCellValueFactory(new PropertyValueFactory<>("productos"));
        colProductos.setPrefWidth(550);
        colProductos.setResizable(true);
        colVendedor.setCellValueFactory(new PropertyValueFactory<>("vendedor"));
        colVendedor.setResizable(true);
        colSucursal.setCellValueFactory(new PropertyValueFactory<>("sucursal"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colDescuento.setCellValueFactory(new PropertyValueFactory<>("descuento"));
        coltotalDesc.setCellValueFactory(new PropertyValueFactory<>("totalConDescuento"));


        tablaVentas = new TableView();
        tablaVentas.getColumns().addAll(colId, colProductos, colVendedor,  colSucursal, colTotal, colDescuento, coltotalDesc);
        tablaVentas.setOnMouseClicked(evtm ->{
            Venta ventaSeleccionada = (Venta) tablaVentas.getSelectionModel().getSelectedItem();
            if (ventaSeleccionada != null){
                opEliminar2.setDisable(false);
            }
        });
        tVentas.setContent(tablaVentas);

    }

    private void crearMenu(){
        barraMenus = new MenuBar();

        menuProducto = new Menu("Producto");
        menuVenta = new Menu("Venta");
        menuOpciones = new Menu("Opciones");

        //Producto
        opNuevo = new MenuItem("Nuevo");
        opModificar = new MenuItem("Modificar");
        opEliminar = new MenuItem("Eliminar");


        //Venta
        opNuevo2 = new MenuItem("Nuevo");
        opEliminar2 = new MenuItem("Eliminar");


        //Opciones
        opChat = new MenuItem("Chat entre farmacias");
        opSalir = new MenuItem("Salir");

        //Funciones
        opSalir.setOnAction(evt->{
            manejoSalir();
        });
        opNuevo.setOnAction(evt->{
            crearVentanaAgregar();
            Window window = getScene().getWindow();

            if (window instanceof Stage){
                ((Stage) window).close();
            }

        });
        opModificar.setOnAction(evt ->{
            Producto productoSeleccionado = (Producto) tablaProductos.getSelectionModel().getSelectedItem();
            if(productoSeleccionado != null){
                crearVentanaModificar(productoSeleccionado);
                Window window = getScene().getWindow();

                if (window instanceof Stage){
                    ((Stage) window).close();
                }
            }
            opModificar.setDisable(true);
            opEliminar.setDisable(true);
        });
        opModificar.setDisable(true);
        opEliminar.setOnAction(evt ->{
            Producto productoSeleccionado = (Producto) tablaProductos.getSelectionModel().getSelectedItem();
            if(crearDialogoEliminar(productoSeleccionado)){
                if (productosHelper.eliminar(productoSeleccionado)){
                    oListaProductos.remove(productoSeleccionado);
                }
            }
            opModificar.setDisable(true);
            opEliminar.setDisable(true);
        });
        opEliminar.setDisable(true);


        opNuevo2.setOnAction(evt->{
            crearVentanaAgregar2();
            Window window = getScene().getWindow();

            if (window instanceof Stage){
                ((Stage) window).close();
            }

        });
        opEliminar2.setOnAction(evt ->{
            Venta ventaSeleccionada = (Venta) tablaVentas.getSelectionModel().getSelectedItem();
            if(crearDialogoEliminar2(ventaSeleccionada)){
                if (ventasHelper.eliminar(ventaSeleccionada)){
                    oListaVentas.remove(ventaSeleccionada);
                }
            }
            opEliminar2.setDisable(true);
        });
        opEliminar2.setDisable(true);

        opChat.setOnAction(evt ->{
            ContenedorPrincipalChat root = new ContenedorPrincipalChat();
            Scene scene = new Scene(root, 800, 800);
            Stage stage = new Stage();
            stage.setTitle("Cliente Chat farmaciafei");
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(evt2->{
                ContenedorFarmacia farmacia = new ContenedorFarmacia();
                Scene escene = new Scene(farmacia, 1240, 720);
                Stage nVentana = new Stage();
                escene.getStylesheets().add(getClass().getResource("estilos.css").toExternalForm());
                nVentana.getIcons().add(new Image(Principal.class.getResourceAsStream("Logo.png")));
                nVentana.setTitle("Farmacia FEI");
                nVentana.setScene(escene);
                nVentana.show();
                Window window = getScene().getWindow();

                if (window instanceof Stage) {
                    ((Stage) window).close();
                }
            });

        });

        //ShortCuts
        opSalir.setAccelerator(new KeyCodeCombination(KeyCode.S,KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN));


        //Asignacion menus
        menuOpciones.getItems().addAll(opChat,opSalir);
        menuProducto.getItems().addAll(opNuevo, opModificar, opEliminar);
        menuVenta.getItems().addAll(opNuevo2, opEliminar2);
        barraMenus.getMenus().addAll(menuProducto, menuVenta, menuOpciones);
        setTop(barraMenus);
        setCenter(panelMostrar);

        //Asignacion estilos
        barraMenus.getStyleClass().add("barraMenus");
        opNuevo.getStyleClass().add("botonMenu");
        opNuevo2.getStyleClass().add("botonMenu");
        opModificar.getStyleClass().add("botonMenuI");
        opEliminar.getStyleClass().add("botonMenu");
        opEliminar2.getStyleClass().add("botonMenuI");
        opChat.getStyleClass().add("botonMenu");
        opSalir.getStyleClass().add("botonMenuI");

        //Asignacion iconos
        menuProducto.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icoProductos.png"))));
        menuVenta.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icoVentas.png"))));
        menuOpciones.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icoOpciones.png"))));
        opNuevo.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icoNuevo.png"))));
        opNuevo2.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icoNuevo.png"))));
        opModificar.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icoEditar.png"))));
        opEliminar.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icoBorrar.png"))));
        opEliminar2.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icoBorrar.png"))));
        opSalir.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icoSalir.png"))));
        tProductos.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icoProductos.png"))));
        tProductosTiles.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icoProductos.png"))));
        tVentas.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icoVentas.png"))));
        opChat.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("icoChat.png"))));
    }

    private void manejoSalir(){
        Alert alertaSalir = new Alert(Alert.AlertType.CONFIRMATION);
        alertaSalir.setTitle("Salir");
        alertaSalir.setHeaderText("Estás seguro de cerrar el punto de venta?");
        alertaSalir.setContentText("Recuerda guardar todo antes de salir");
        ButtonType respuesta = alertaSalir.showAndWait().orElse(ButtonType.OK);
        if (ButtonType.OK.equals(respuesta));
            System.exit(0);
    }
    private void crearVentanaModificar(Producto productoSeleccionado){
        ContenedorProducto rootVentana = new ContenedorProducto(productoSeleccionado);
        Scene escena = new Scene(rootVentana, 360, 230);
        Stage ventana = new Stage();
        ventana.setResizable(false);
        ventana.setScene(escena);
        ventana.setTitle("Modificar "+productoSeleccionado.getNombre());
        ventana.show();
        ventana.setOnCloseRequest(evt->{
            ContenedorFarmacia farmacia = new ContenedorFarmacia();
            Scene scene = new Scene(farmacia, 1240, 720);
            Stage nVentana = new Stage();
            scene.getStylesheets().add(getClass().getResource("estilos.css").toExternalForm());
            nVentana.getIcons().add(new Image(Principal.class.getResourceAsStream("Logo.png")));
            nVentana.setTitle("Farmacia FEI");
            nVentana.setScene(scene);
            nVentana.show();
            Window window = getScene().getWindow();

            if (window instanceof Stage) {
                ((Stage) window).close();
            }
        });
    }

    private boolean crearDialogoEliminar(Producto productoSelecionado){
        Alert alertaEliminar = new Alert(Alert.AlertType.CONFIRMATION);
        alertaEliminar.setTitle("Advertencia");
        alertaEliminar.setContentText("¿Estas seguro que quieres eliminar el producto "+productoSelecionado.getNombre()+"?");
        ButtonType respuesta = alertaEliminar.showAndWait().orElse(ButtonType.OK);
        ContenedorFarmacia farmacia = new ContenedorFarmacia();
        Scene scene = new Scene(farmacia, 1240, 720);
        Stage nVentana = new Stage();
        scene.getStylesheets().add(getClass().getResource("estilos.css").toExternalForm());
        nVentana.getIcons().add(new Image(Principal.class.getResourceAsStream("Logo.png")));
        nVentana.setTitle("Farmacia FEI");
        nVentana.setScene(scene);
        nVentana.show();
        Window window = getScene().getWindow();

        if (window instanceof Stage) {
            ((Stage) window).close();
        }
        return respuesta.equals(ButtonType.OK);
    }

    private void crearVentanaAgregar() {

        ContenedorProducto rootVentana = new ContenedorProducto();
        Scene escena = new Scene(rootVentana, 360, 230);
        Stage ventana = new Stage();
        ventana.setResizable(false);
        ventana.setScene(escena);
        ventana.setTitle("Agregar producto");
        ventana.show();
        ventana.setOnCloseRequest(evt->{
            ContenedorFarmacia farmacia = new ContenedorFarmacia();
            Scene scene = new Scene(farmacia, 1240, 720);
            Stage nVentana = new Stage();
            scene.getStylesheets().add(getClass().getResource("estilos.css").toExternalForm());
            nVentana.getIcons().add(new Image(Principal.class.getResourceAsStream("Logo.png")));
            nVentana.setTitle("Farmacia FEI");
            nVentana.setScene(scene);
            nVentana.show();
            Window window = getScene().getWindow();

            if (window instanceof Stage) {
                ((Stage) window).close();
            }
        });
    }


    private boolean crearDialogoEliminar2(Venta ventaSeleccionada){
        Alert alertaEliminar = new Alert(Alert.AlertType.CONFIRMATION);
        alertaEliminar.setTitle("Advertencia");
        alertaEliminar.setContentText("¿Estas seguro que quieres eliminar la venta "+ventaSeleccionada.getId()+"?");
        ButtonType respuesta = alertaEliminar.showAndWait().orElse(ButtonType.OK);
        return ButtonType.OK.equals(respuesta);
    }

    private void crearVentanaAgregar2() {

        ContenedorVenta rootVentana = new ContenedorVenta();
        Scene escena = new Scene(rootVentana, 1000, 600);
        Stage ventana = new Stage();
        ventana.setResizable(false);
        ventana.setScene(escena);
        ventana.setTitle("Agregar Venta");
        ventana.show();
        ventana.setOnCloseRequest(evt->{
            ContenedorFarmacia farmacia = new ContenedorFarmacia();
            Scene scene = new Scene(farmacia, 1240, 720);
            Stage nVentana = new Stage();
            scene.getStylesheets().add(getClass().getResource("estilos.css").toExternalForm());
            nVentana.getIcons().add(new Image(Principal.class.getResourceAsStream("Logo.png")));
            nVentana.setTitle("Farmacia FEI");
            nVentana.setScene(scene);
            nVentana.show();
            Window window = getScene().getWindow();

            if (window instanceof Stage) {
                ((Stage) window).close();
            }
        });

    }
}
