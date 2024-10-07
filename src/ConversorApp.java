import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class ConversorApp {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);

        // Pedir moneda al usuario
        System.out.println("*****************************************************");
        System.out.print(
        """
            Sea bienvenido al conversor de moneda =]
            
            1: Dolar → Peso argentino
            2: Peso argentino → Dolar
            3: Dolar → Real brasileño
            4: Real brasileño → Dolar
            5: Dolar → Peso colombiano
            6: Peso colombiano → Dolar
            7: Salir
            
            Eliga una opción válida:
        """
        );

        System.out.println("*****************************************************");

        var moneda = lectura.nextLine();

        // Crear una instancia de Api para obtener datos
        Api api = new Api();
        String jsonResponse = api.obtenerDatos(moneda);

        // Imprimir el JSON para verificar la respuesta
        System.out.println("Respuesta JSON:\n" + jsonResponse);

        // Deserializar JSON en un objeto ConversorApp
        Gson gson = new Gson();
        Data conversor = gson.fromJson(jsonResponse, Data.class);

        // Imprimir el contenido deserializado
        conversor.imprimirContenido();
    }
}
