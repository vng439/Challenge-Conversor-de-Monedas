import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BuscadorMonedas buscador = new BuscadorMonedas();

        List<String> monedasPermitidas = List.of("ARS", "USD", "BRL", "CLP");

        int opcion = 0;

        while (opcion != 3) {

            System.out.println("\n*********************************");
            System.out.println(" Conversor de Monedas ");
            System.out.println("*********************************");
            System.out.println("1) Convertir moneda");
            System.out.println("2) Ver monedas disponibles");
            System.out.println("3) Salir");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {

                case 1 -> {
                    System.out.print("Ingrese moneda origen: ");
                    String origen = scanner.nextLine().toUpperCase();

                    System.out.print("Ingrese moneda destino: ");
                    String destino = scanner.nextLine().toUpperCase();

                    if (!monedasPermitidas.contains(origen) ||
                            !monedasPermitidas.contains(destino)) {
                        System.out.println("Moneda no permitida.");
                        break;
                    }

                    System.out.print("Ingrese monto a convertir: ");
                    double monto = Double.parseDouble(scanner.nextLine());

                    try {
                        Moneda moneda = buscador.buscaMoneda(origen);
                        double tasa = moneda.conversion_rates().get(destino);
                        double resultado = monto * tasa;

                        System.out.println(
                                "Al día de hoy, " + monto + " " + origen +
                                " equivale a " + resultado + " " + destino
                        );

                    } catch (RuntimeException e) {
                        System.out.println("Error en la conversión.");
                    }
                }

                case 2 -> {
                    System.out.println("Monedas disponibles:");
                    monedasPermitidas.forEach(System.out::println);
                }

                case 3 -> System.out.println("Saliendo de la aplicación...");

                default -> System.out.println("Opción inválida");
            }
        }
    }
}