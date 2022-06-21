package uv.mx.carlos.proyectofinal;

import accesodatos.casos_uso.Productos;
import accesodatos.casos_uso.Sucursales;
import accesodatos.casos_uso.Vendedores;
import accesodatos.casos_uso.Ventas;
import accesodatos.modelo.Producto;
import accesodatos.modelo.Sucursal;
import accesodatos.modelo.Vendedor;
import accesodatos.modelo.Venta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Version;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class ContenedorVenta extends GridPane {
    private TableView tablaProductos;
    private TableView tablaDeVenta;
    private Label etiProductos;
    private Label etiProductosS;
    private Label etiVendedor;
    private Label etiSucursal;
    private Label etiTotal;
    private Label etiDescuento;
    private Label etiTotalConDesc;
    private TextField txtTotal;
    private TextField txtDescuento;
    private TextField txtTotalConDesc;
    private Button btnGuardar;
    private boolean modificando = false;
    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("farmaciafei");
    //EntityManager em = emf.createEntityManager();
    private Venta venta;
    private Ventas ventasHelper;
    private Vendedores vendedoresHelper;
    private Sucursales sucursalesHelper;
    private Productos productosHelper;
    private List<Producto> listaProductos = null;
    private ObservableList<Producto> oListaProductos = null;
    private List<Producto> listaProductosVenta = null;
    private ObservableList<Producto> oListaProductosVenta = null;
    private List<Vendedor> listaVendedores = null;
    private ObservableList<Vendedor> oListaVendedores = null;
    private List<Sucursal> listaSucursales = null;
    private ObservableList<Sucursal> oListaSucursales = null;
    private ChoiceBox<String> cBvendedores;
    private ChoiceBox<String> cBsucursales;
    private List<Vendedor> listaNombreVend = null;
    private List<Sucursal> listaNombreSuc = null;
    private Double total = 0.0;
    private Double descuento = 0.0;
    private Double totalConDesc = 0.0;


    public ContenedorVenta() {
        inicializarComponentes();
        try {
            ventasHelper = new Ventas();
            cargarProductos();
        } catch (Exception e) {

        }
    }

    public ContenedorVenta(Venta venta) {
        this.venta = venta;
        modificando = true;
        inicializarComponentes();
        try {
            cargarProductos();
            ventasHelper = new Ventas();
        } catch (Exception e) {

        }
    }


    private void inicializarComponentes() {
        //Asignacion de paddding y tamano
        this.setVgap(7);
        this.setHgap(7);
        this.setPadding(new Insets(7));

        //Creacion de las columnas tabla 1
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

        //Creacion de las columnas tabla 2
        TableColumn<Producto, Integer> colId2 = new TableColumn("Id");
        TableColumn<Producto, String> colNombre2 = new TableColumn("Nombre");
        TableColumn<Producto, String> colMarca2 = new TableColumn("Marca");
        TableColumn<Producto, Integer> colCantidad2 = new TableColumn("Cantidad");
        TableColumn<Producto, Double> colPrecio2 = new TableColumn("Precio");

        colId2.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre2.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colMarca2.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colCantidad2.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colPrecio2.setCellValueFactory(new PropertyValueFactory<>("precio"));

        //ComboBox
        cBvendedores = new ChoiceBox<String>();
        cBvendedores.setMaxWidth(150);
        cBsucursales = new ChoiceBox<String>();
        cBsucursales.setMaxWidth(150);

        //DefinicionTablas
        tablaProductos = new TableView();
        tablaDeVenta = new TableView();
        tablaDeVenta.getColumns().addAll(colId, colNombre, colMarca, colCantidad, colPrecio);
        tablaProductos.getColumns().addAll(colId2, colNombre2, colMarca2, colCantidad2, colPrecio2);

        this.add(tablaProductos, 0,2,5,7);
        this.add(tablaDeVenta, 6,2,5,7);

        //Etiquetas
        etiProductos = new Label("Productos: ");
        etiProductosS = new Label("Productos seleccionados: ");
        etiVendedor = new Label("Vendedor:");
        etiSucursal = new Label("Sucursal:");
        etiTotal = new Label("Total:");
        etiDescuento = new Label("Descuento:");
        etiTotalConDesc = new Label("Total con descuento:");

        //Campos de entrada
        txtTotal = new TextField();
        txtDescuento = new TextField();
        txtTotalConDesc = new TextField();
        txtTotal.setPromptText("Total");
        txtDescuento.setPromptText("Descuento");
        txtTotalConDesc.setPromptText("Total con descuento");

        //botones
        btnGuardar = new Button("Guardar");

        //Tamanos
        btnGuardar.setMaxWidth(100);
        txtTotal.setMaxWidth(150);
        txtDescuento.setMaxWidth(150);
        txtTotalConDesc.setMaxWidth(150);


        //En la fila 1 estan las etiquetas y en la 2 los componentes
        this.add(etiProductos, 1, 1);
        this.add(etiProductosS, 6, 1);

        this.add(etiVendedor, 1, 9);
        this.add(cBvendedores, 2, 9);

        this.add(etiSucursal, 1, 10);
        this.add(cBsucursales, 2, 10);

        this.add(etiTotal, 1, 11);
        this.add(txtTotal, 2, 11);

        this.add(etiDescuento, 1, 12);
        this.add(txtDescuento, 2, 12);

        this.add(etiTotalConDesc, 1, 13);
        this.add(txtTotalConDesc, 2, 13);

        this.add(btnGuardar, 1, 17, 2, 1);

        //Eventos

        tablaProductos.setOnMouseClicked(evtm ->{
            Producto productoSeleccionado = (Producto) tablaProductos.getSelectionModel().getSelectedItem();
            if (evtm.getClickCount() == 2) {
                if (productoSeleccionado != null) {
                    if (listaProductosVenta.add(productoSeleccionado)){
                        total = total + productoSeleccionado.getPrecio();
                        txtTotal.setText(String.valueOf(total));
                        if(txtDescuento.getText() != ""){
                            descuento = Double.parseDouble(txtDescuento.getText());
                            if(descuento != 0){
                                total = Double.parseDouble(txtTotal.getText());
                                Double desc = descuento/100;
                                Double resta = total * desc;
                                totalConDesc = total - resta;
                                txtTotalConDesc.setText(String.valueOf(totalConDesc));
                            }
                        }
                    }
                    tablaDeVenta.refresh();
                }
            }
        });

        tablaDeVenta.setOnMouseClicked(evtm ->{
            Producto productoSeleccionado2 = (Producto) tablaDeVenta.getSelectionModel().getSelectedItem();
            if (evtm.getClickCount() == 2) {
                if (productoSeleccionado2 != null) {
                    if(listaProductosVenta.remove(productoSeleccionado2)){
                        total = total - productoSeleccionado2.getPrecio();
                        txtTotal.setText(String.valueOf(total));
                        if(txtDescuento.getText() != ""){
                            descuento = Double.parseDouble(txtDescuento.getText());
                            if(descuento != 0){
                                total = Double.parseDouble(txtTotal.getText());
                                Double desc = descuento/100;
                                Double resta = total * desc;
                                totalConDesc = total - resta;
                                txtTotalConDesc.setText(String.valueOf(totalConDesc));
                            }
                        }
                    }
                    tablaDeVenta.refresh();
                }
            }
        });

        btnGuardar.setOnAction(evt -> {
            if (validarDatosProducto())
                if (guardarVenta()) {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Éxito");
                    alerta.setContentText("Guardado");
                    alerta.setHeaderText("Los datos de la venta se han almacenado de manera correcta");
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

                    if (window instanceof Stage) {
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

        txtDescuento.setOnKeyPressed(evt->{
            if(txtDescuento.getText() != ""){
                descuento = Double.parseDouble(txtDescuento.getText());
                if(descuento != 0){
                    total = Double.parseDouble(txtTotal.getText());
                    Double desc = descuento/100;
                    Double resta = total * desc;
                    totalConDesc = total - resta;
                    txtTotalConDesc.setText(String.valueOf(totalConDesc));
                }
            }
        });


    }

    private void limpiarCampos() {
        txtTotal.setText("");
        txtDescuento.setText("");
        txtTotalConDesc.setText("");
    }


    private boolean validarDatosProducto() {
        boolean valido = true;
        String mensajes = "";
        if (txtTotal.getText() == null || txtTotal.getText().trim().length() == 0) {
            valido = false;
            mensajes += "* No hay nada seleccionado\n";
        }
        if (txtTotalConDesc.getText() == null || txtTotalConDesc.getText().trim().length() == 0) {
            valido = false;
            mensajes += "* No hay nada seleccionado\n";
        }
        if (!valido) {
            Alert alertaSalir = new Alert(Alert.AlertType.ERROR);
            alertaSalir.setTitle("Error");
            alertaSalir.setContentText(mensajes);
            alertaSalir.setHeaderText("Hay errores en los datos, favor de corregir");
            alertaSalir.show();
        }
        return valido;
    }


    private boolean guardarVenta() {
        try {
            if (!modificando) {
                venta = new Venta();
            }
            String productos = new String();
            for(Producto producto : listaProductos) {
                if(listaProductos.size() > 1) {
                    productos = productos + "" + producto.toString() + " , ";
                }else {
                    productos = producto.toString();
                }
            }
            venta.setProductos(productos);
            venta.setVendedor(cBvendedores.getValue());
            venta.setSucursal(cBsucursales.getValue());
            venta.setTotal(Double.parseDouble(txtTotal.getText()));
            venta.setDescuento(Double.parseDouble(txtDescuento.getText()));
            venta.setTotalConDescuento(Double.parseDouble(txtTotalConDesc.getText()));
            if (!modificando) {
                System.out.println(venta);
                return ventasHelper.insertar(venta);
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return false;
    }
    private void cargarProductos() {
        try{
            productosHelper = new Productos();
            vendedoresHelper = new Vendedores();
            sucursalesHelper = new Sucursales();
            listaProductos = productosHelper.Lista();
            listaProductosVenta = new ArrayList<Producto>();
            listaSucursales = sucursalesHelper.Lista();
            listaVendedores = vendedoresHelper.Lista();
            oListaSucursales = FXCollections.observableList(listaSucursales);
            oListaVendedores = FXCollections.observableList(listaVendedores);
            oListaProductos = FXCollections.observableList(listaProductos);
            tablaProductos.setItems(oListaProductos);
            oListaProductosVenta = FXCollections.observableList(listaProductosVenta);
            tablaDeVenta.setItems(oListaProductosVenta);
            List<String> nombres = new ArrayList<String>();
            List<String> sucursales = new ArrayList<String>();
            for (Vendedor vendedor:oListaVendedores
                 ) {
                nombres.add(vendedor.getNombre());
            }
            for (Sucursal sucursal:oListaSucursales
            ) {
                sucursales.add(sucursal.getNombre());
            }
            ObservableList<String> oNombres = FXCollections.observableList(nombres);
            ObservableList<String> oSucursales = FXCollections.observableList(sucursales);
            cBvendedores.setItems(oNombres);
            cBsucursales.setItems(oSucursales);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

