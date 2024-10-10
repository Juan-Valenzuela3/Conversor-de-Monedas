import com.google.gson.Gson;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class ConversorApp {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);

        // Mostrar menú
        while (true) {
            System.out.println("*****************************************************");
            System.out.print(
                    """
                        Sea bienvenido al conversor de moneda =]
                        
                        1: Dolar (USD) → Peso argentino (ARS)
                        2: Peso argentino (ARS) → Dolar (USD)
                        3: Dolar (USD) → Real brasileño (BRL)
                        4: Real brasileño (BRL) → Dolar (USD)
                        5: Dolar (USD) → Peso colombiano (COP)
                        6: Peso colombiano (COP) → Dolar (USD)
                        7: Salir
                        
                        Elija una opción válida:
                    """
            );

            int opcion = lectura.nextInt();
            String monedaBase = "";
            String monedaDestino = "";

            if (opcion == 7) {
                System.out.println("Gracias por usar el conversor de monedas.");
                break;
            }

            // Definir la moneda base y la moneda destino de acuerdo con la opción seleccionada
            switch (opcion) {
                case 1 -> {
                    monedaBase = "USD";
                    monedaDestino = "ARS";
                }
                case 2 -> {
                    monedaBase = "ARS";
                    monedaDestino = "USD";
                }
                case 3 -> {
                    monedaBase = "USD";
                    monedaDestino = "BRL";
                }
                case 4 -> {
                    monedaBase = "BRL";
                    monedaDestino = "USD";
                }
                case 5 -> {
                    monedaBase = "USD";
                    monedaDestino = "COP";
                }
                case 6 -> {
                    monedaBase = "COP";
                    monedaDestino = "USD";
                }
                default -> {
                    System.out.println("Opción no válida. Intente de nuevo.");
                    continue;
                }
            }

            // Obtener los datos de la API para la moneda base seleccionada
            Api api = new Api();
            String jsonResponse = api.obtenerDatos(monedaBase);

            // Deserializar JSON en un objeto Data
            Gson gson = new Gson();
            Data data = gson.fromJson(jsonResponse, Data.class);

            // Mostrar solo la moneda base sin imprimir las tasas de conversión
            System.out.println("Moneda base: " + monedaBase);

            // Pedir al usuario la cantidad que quiere convertir
            System.out.print("Introduce la cantidad de " + monedaBase + ": ");
            double cantidad = lectura.nextDouble();

            // Obtener la tasa de conversión adecuada
            Map<String, Double> tasasConversion = data.getConversionRates();
            if (tasasConversion.containsKey(monedaDestino)) {
                double tasaConversion = tasasConversion.get(monedaDestino);
                double resultado = cantidad * tasaConversion;

                System.out.println(cantidad + " " + monedaBase + " son " + resultado + " " + monedaDestino);
            } else {
                System.out.println("No se pudo realizar la conversión. Verifica las monedas.");
            }
        }
    }
}
