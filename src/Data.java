import java.util.Map;

public class Data {

    private String base_code;
    private Map<String, Double> conversion_rates;

    public Data() {}

    @Override
    public String toString() {
        return "Moneda base: " + base_code + "\nTasas de conversión: " + conversion_rates;
    }

    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }
}