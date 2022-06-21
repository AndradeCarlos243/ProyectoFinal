package uv.mx.carlos.proyectofinal;

import accesodatos.modelo.Vendedor;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.util.List;

public class VentanaGraficos extends Stage {
    private List<Vendedor> listaVendedores;
    public VentanaGraficos(List<Vendedor> listaUsuarios) {
        this.listaVendedores = listaUsuarios;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        TabPane root = new TabPane();
        Tab tabGrafica1 = new Tab("Gráfica de barras");
        Tab tabGrafica2 = new Tab("Gráfica de pastel");
        Tab tabGrafica3 = new Tab("Gráfica de puntos");

        var grafica1 = crearGrafica1();
        var grafica2 = crearGrafica2();
        var grafica3 = crearGrafica3();
        tabGrafica1.setContent(grafica1);
        tabGrafica2.setContent(grafica2);
        tabGrafica3.setContent(grafica3);

        root.getTabs().addAll(tabGrafica1, tabGrafica2, tabGrafica3);
        Scene scene = new Scene(root, 600, 600);
        setTitle("Gráficos");
        setScene(scene);
    }

    private BarChart<String, Number> crearGrafica1() {
        CategoryAxis ejeX = new CategoryAxis();
        NumberAxis ejeY = new NumberAxis();
        ejeX.setLabel("Usuarios");
        ejeY.setLabel("No. Mensajes");
        BarChart<String, Number> grafica= new BarChart<String, Number>(ejeX, ejeY);
        grafica.setTitle("Número de mensajes por usuario");

        for(Vendedor vendedor : listaVendedores) {
            XYChart.Series serie = new XYChart.Series();
            serie.setName(vendedor.getNombre());
            serie.getData().add(new XYChart.Data(vendedor.getNombre(), vendedor.getNoMensajes()));
            grafica.getData().add(serie);
        }

        return grafica;
    }

    private PieChart crearGrafica2() {
        PieChart grafica= new PieChart();
        for(Vendedor vendedor : listaVendedores)
            grafica.getData().add(new PieChart.Data(vendedor.getNombre(), vendedor.getNoMensajes()));
        return grafica;
    }

    private ScatterChart<String, Number> crearGrafica3() {
        CategoryAxis ejeX = new CategoryAxis();
        NumberAxis ejeY = new NumberAxis();
        ejeX.setLabel("Usuarios");
        ejeY.setLabel("No. Mensajes");
        ScatterChart<String, Number> grafica=new ScatterChart<String, Number>(ejeX, ejeY);
        grafica.setTitle("Número de mensajes por usuario");
        XYChart.Series serie = new XYChart.Series();
        serie.setName("Usuarios");
        for(Vendedor vendedor : listaVendedores)
            serie.getData().add(new XYChart.Data(vendedor.getNombre(), vendedor.getNoMensajes()));

        grafica.getData().add(serie);
        return grafica;
    }
}
