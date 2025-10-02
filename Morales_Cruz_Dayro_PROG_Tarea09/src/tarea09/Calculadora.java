package tarea09;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * La típica calculadora básica para realizar cálculos artitméticos combinados.
 * Permite sumar, restar, multiplicar y dividir.
 *
 * @author profesorado y Dayro Morales Cruz
 */
public class Calculadora extends Application {

    // Se muestra la entrada y el resultado
    private TextField display;

    @Override
    public void start(Stage escenario) {
        // Configuración del escenario
        escenario.setTitle("Mi calculadora");
        escenario.setResizable(false);
        // Se carga la imagen de icono
        Image icono = new Image(getClass().getResourceAsStream("/tarea09/logoCalcu.png"));
        escenario.getIcons().add(icono);

        // Crea el panel principal y asigna márgenes
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Crea el campo de texto (display)
        display = new TextField();
        display.setEditable(false);
        display.setPrefHeight(50);
        display.getStyleClass().add("text-field");

        // Posiciona el display en la parte superior del BorderPane
        root.setTop(display);

        // Crea un VBox para organizar las filas de botones
        VBox vbox = new VBox(5);
        vbox.setAlignment(Pos.CENTER);

        // Define las etiquetas de los botones y su orden
        String[][] botones = {
            {"7", "8", "9", "/", "("},
            {"4", "5", "6", "*", ")"},
            {"1", "2", "3", "-", "."},
            {"0", "C", "<", "+", "="}
        };

        // Recorrer la matriz y crear los HBox para cada fila de botones
        for (int i = 0; i < botones.length; i++) {
            HBox hboxFila = new HBox(5); // Crear una fila de botones
            hboxFila.setAlignment(Pos.CENTER);

            for (int j = 0; j < botones[i].length; j++) {
                String etiqueta = botones[i][j];
                Button boton = new Button(etiqueta);
                boton.setPrefSize(60, 60);

                // Asignar clases CSS según el botón
                switch (etiqueta) {
                    case "C", "<" ->
                        boton.getStyleClass().add("limpiar");
                    case "=" ->
                        boton.getStyleClass().add("igual");
                    case "+", "-", "*", "/", "(", ")", "." ->
                        boton.getStyleClass().add("operador");
                    default -> {
                    }
                }

                // Asignar el manejador de eventos para cada botón
                boton.setOnAction(e -> procesoDeEntrada(etiqueta));
                hboxFila.getChildren().add(boton); // Añadir el botón a la fila
            }

            vbox.getChildren().add(hboxFila); // Añadir la fila de botones
        }

        // Coloca el VBox en el centro del BorderPane
        root.setCenter(vbox);

        // Crea la escena, asigna el CSS y muestra el escenario
        Scene escena = new Scene(root, 280, 400);
        escena.getStylesheets().add(getClass().getResource("calculadora.css").toExternalForm());
        escenario.setScene(escena);
        escenario.show();
    }

    /**
     * Maneja la entrada de datos en la calculadora.
     *
     * @param entrada Texto recogido del botón pulsado.
     */
    public void procesoDeEntrada(String entrada) {
        String actual = display.getText();
        switch (entrada) {
            case "C" -> // Borra todo el contenido
                display.setText("");
            case "<" -> {
                // Borra el último carácter siempre que haya uno
                if (!actual.isEmpty()) {
                    display.setText(actual.substring(0, actual.length() - 1));
                }
            }
            case "=" -> {
                // Se evalúa la expresión y se recogen excepciones
                try {
                    // Validar que la expresión contiene sólo los caracteres permitidos
                    if (!actual.matches("[0-9+\\-*/(). ]+")) {
                        display.setText("Expresión no válida");
                        return;
                    }
                    Expression expression = new ExpressionBuilder(actual).build();
                    double resultado = expression.evaluate();
                    display.setText(String.valueOf(resultado));
                } catch (IllegalArgumentException | ArithmeticException ex) {
                    display.setText("Error en la expresión");
                }
            }
            case "(" -> {
                // Si el último carácter es un dígito, se inserta de manera explícita un "*"
                if (!actual.isEmpty() && Character.isDigit(actual.charAt(actual.length() - 1))) {
                    display.setText(actual + "*" + entrada);
                } else {
                    display.setText(actual + entrada);
                }
            }

            case "+", "-", "*", "/" -> {
                // Solo se añade el operador si el último carácter es dígito o ")"
                if (!actual.isEmpty()) {
                    char ultimo = actual.charAt(actual.length() - 1);
                    if (Character.isDigit(ultimo) || ultimo == ')') {
                        display.setText(actual + entrada);
                    }
                }
            }
            case "." -> {
                // Verifica que en el número actual no exista ya un punto decimal
                String[] partes = actual.split("[+\\-*/()]");
                String ultimoNumero = partes.length > 0 ? partes[partes.length - 1] : "";
                if (!ultimoNumero.contains(".")) {
                    display.setText(actual + entrada);
                }
            }
            default -> // Asumir que es un dígito
                display.setText(actual + entrada);
        }
    }

    /* 
            // Posible case para cerrar el paréntesis en caso de no haber para evitar el error
            case ")" -> {
                // Agrega ")" sólo si hay un paréntesis abierto sin cerrar y el carácter anterior es un dígito o ")"
                if (!actual.isEmpty() && (Character.isDigit(actual.charAt(actual.length() - 1)) || actual.charAt(actual.length() - 1) == ')')) {
                    int open = 0, close = 0;
                    for (char c : actual.toCharArray()) {
                        if (c == '(') {
                            open++;
                        }
                        if (c == ')') {
                            close++;
                        }
                    }
                    if (open > close) {
                        display.setText(actual + entrada);
                    }
                }
            }*/
    /**
     * Programa principal, lanza la aplicación.
     *
     * @param args Argumentos o parámetros (no hay en este caso)
     */
    public static void main(String[] args) {
        launch(args);
    }
}
