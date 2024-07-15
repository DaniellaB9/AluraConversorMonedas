package principal;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConversorApplication {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);
        int opcion;
        String[] monedas = {"USD", "BRL", "EUR"};
        String check1 = null, check2 = null;
        double monto;
        do {
            System.out.println("**********************************");
            System.out.println("Bienvenido al conversor de monedas");
            do {
                System.out.println("Elija la conversión desea realizar:");
                System.out.println("1. Dólar ==> Real Brasilero");
                System.out.println("2. Dólar ==> Euro");
                System.out.println("3. Real Brasilero ==> Dólar");
                System.out.println("4. Real Brasilero ==> Euro");
                System.out.println("5. Euro ==> Real Brasilero");
                System.out.println("6. Euro ==> Dólar");
                System.out.println("7. Salir");
                System.out.println("**********************************");
                opcion = lectura.nextInt();
                if (opcion <= 0 || opcion>7){
                    System.out.println("Elija una opcion válida");
                }
            } while (opcion <= 0 || opcion>7);

            if (opcion != 7){
                System.out.println("Ingrese la cantidad a convertir:");
                monto = lectura.nextDouble();
                switch (opcion){
                    case 1:
                        check1 = monedas[0];
                        check2 = monedas[1];
                        break;
                    case 2:
                        check1 = monedas[0];
                        check2 = monedas[2];
                        break;
                    case 3:
                        check1 = monedas[1];
                        check2 = monedas[0];
                        break;
                    case 4:
                        check1 = monedas[1];
                        check2 = monedas[2];
                        break;
                    case 5:
                        check1 = monedas[2];
                        check2 = monedas[1];
                        break;
                    case 6:
                        check1 = monedas[2];
                        check2 = monedas[0];
                        break;
                }
                String direccion = "https://v6.exchangerate-api.com/v6/6263ede35e8cd715f4c0971c/pair/"+
                        check1+"/"+check2+"/"+monto;
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(direccion))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();

                Gson gson = new Gson();
                InfoCambio infoCambio = gson.fromJson(json,InfoCambio.class);
                System.out.println("Del monto ingresado "+ monto + " "+infoCambio.monedaBase()+
                        " se obtiene como cambio: " + infoCambio.resultado() +
                        " "+infoCambio.monedaCambio());
            }
        }while (opcion != 7);
    }

}
