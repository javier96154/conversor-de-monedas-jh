import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Busqueda {
    String api = "5c3f68f00db956e65887adf8";
    String direccion = "https://v6.exchangerate-api.com/v6/" + api + "/latest/CLP";
    HttpClient client = HttpClient.newHttpClient();
    JsonObject conversionRates;
    double usdValue;
    double arsValue;

    public Busqueda() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        // Parse JSON to get conversion rates using Gson
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        conversionRates = jsonObject.getAsJsonObject("conversion_rates");
        usdValue = conversionRates.get("USD").getAsDouble();
        arsValue = conversionRates.get("ARS").getAsDouble();
    }

    public double getUsdValue() {
        return usdValue;
    }

    public double getArsValue() {
        return arsValue;
    }

    public double getOtherValue(String symbology) {
        if (conversionRates.has(symbology)) {
            return conversionRates.get(symbology).getAsDouble();
        } else {
            throw new IllegalArgumentException("Símbolo de moneda no encontrado: " + symbology);
        }
    }

    public static void main(String[] args) {
        try {
            Busqueda busqueda = new Busqueda();
            Scanner teclado = new Scanner(System.in);
            System.out.println("Ingrese el símbolo de la moneda a consultar: ");
            String simbolo = teclado.nextLine().toUpperCase();
            System.out.println("El valor de " + simbolo + " es: " + busqueda.getOtherValue(simbolo));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
