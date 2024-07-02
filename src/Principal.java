import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Busqueda busqueda;

        try {
            busqueda = new Busqueda();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return;
        }

        double usdValue = busqueda.getUsdValue();
        double arsValue = busqueda.getArsValue();


        System.out.println("** Bienvenido al conversor de monedas de Javier **");
        System.out.println("\n** Menú de opciones **");
        System.out.println("1 - Dolar -> Peso Chileno");
        System.out.println("2 - Peso Chileno -> Dolar");
        System.out.println("3 - Peso Chileno -> Peso Argentino");
        System.out.println("4 - Peso Chileno -> Moneda a Consultar");
        System.out.println("5 - Moneda a Consultar -> Peso Chileno");
        System.out.println("9 - Salir");

        int menu = 0;

        while (menu != 9) {
            System.out.println("Ingresa una opción:");
            try {
                if (teclado.hasNextInt()) {
                    menu = teclado.nextInt();
                    teclado.nextLine(); // Consumir la nueva línea

                    switch (menu) {
                        case 1:
                            System.out.println("Ingrese dolares a convertir: ");
                            if (teclado.hasNextDouble()) {
                                double valor = teclado.nextDouble();
                                valor = valor / usdValue;
                                System.out.println("El valor en Pesos Chilenos es: " + valor);
                            } else {
                                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                                teclado.next();
                            }
                            teclado.nextLine();
                            break;

                        case 2:
                            System.out.println("Ingrese pesos chilenos a convertir: ");
                            if (teclado.hasNextDouble()) {
                                double valor = teclado.nextDouble();
                                valor = valor * usdValue;
                                System.out.println("El valor en Dólares es: " + valor);
                            } else {
                                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                                teclado.next();
                            }
                            teclado.nextLine();
                            break;

                        case 3:
                            System.out.println("Ingrese pesos chilenos a convertir: ");
                            if (teclado.hasNextDouble()) {
                                double valor = teclado.nextDouble();
                                valor = valor * arsValue;
                                System.out.println("El valor en peso argentino es: " + valor);
                            } else {
                                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                                teclado.next();
                            }
                            teclado.nextLine();
                            break;

                        case 4:
                            System.out.println("Ingrese el símbolo de la moneda a consultar a convertir peso chileno a esta: ");
                            String symbology = teclado.nextLine().toUpperCase();
                            double valorMoneda = busqueda.getOtherValue(symbology);
                            System.out.println("Ingrese pesos chilenos a convertir: ");
                            if (valorMoneda != -1) {
                                if (teclado.hasNextDouble()) {
                                    double valor = teclado.nextDouble();
                                    valor = valor * valorMoneda;
                                    System.out.println("El valor en "+ symbology +" es: " + valor);
                                } else {
                                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                                    teclado.next();
                                }
                                teclado.nextLine();
                                break;
                            } else {
                                System.out.println("Símbolo de moneda no encontrado.");
                            }
                            break;
                        case 5:
                            System.out.println("Ingrese el símbolo de la moneda a consultar a convertir a peso chileno: ");
                            String symbology1 = teclado.nextLine().toUpperCase();
                            double valorMoneda1 = busqueda.getOtherValue(symbology1);
                            System.out.println("Ingrese "+ symbology1 + " a convertir: ");
                            if (valorMoneda1 != -1) {
                                if (teclado.hasNextDouble()) {
                                    double valor = teclado.nextDouble();
                                    valor = valor / valorMoneda1;
                                    System.out.println("El valor en pesos chilenos es: " + valor);
                                } else {
                                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                                    teclado.next();
                                }
                                teclado.nextLine();
                                break;
                            } else {
                                System.out.println("Símbolo de moneda no encontrado.");
                            }
                            break;

                        case 9:
                            System.out.println("Saliendo del programa...");
                            break;

                        default:
                            System.out.println("Opción no válida, por favor intenta de nuevo.");
                    }
                } else {
                    System.out.println("Entrada inválida. Por favor, ingrese un número válido.");
                    teclado.next(); // limpiar la entrada inválida
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                teclado.next(); // limpiar la entrada inválida
            }
        }

        teclado.close();
    }
}