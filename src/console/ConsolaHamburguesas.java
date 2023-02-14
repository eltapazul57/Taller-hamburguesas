package console;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import procesamiento.Restaurante;



public class ConsolaHamburguesas 
{
    public void ejecutarAplicacion()
    {
    	ejecutarCargarDatos();
        System.out.println("Restaurante de hamburguesas\n");
        boolean continuar = true;
        while (continuar)
        {
            try
            {
                mostrarOpciones();
                Scanner input = new Scanner(System.in);
                System.out.println("Seleccione una opción\n");
                String opcion = input.nextLine();
                int opcionseleccionada = Integer.parseInt(opcion);
                if (opcionseleccionada == 1)
                {
                	ejecutarMostrarMenu();
                }
                else if (opcionseleccionada ==2)
                {
                	ejecutarNuevoPedido();
                }
                else if (opcionseleccionada ==6)
                {
                    System.out.println("Saliendo de la aplicación");
                    continuar = false;
                }
            }
            catch (NumberFormatException e)
            {
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
        }
    }
    private String input(String string) 
    {
        return null;
    }
    public void mostrarOpciones()
    {
        System.out.println("\nOpciones\n");
        System.out.println("1. Mostrar el menú");
        System.out.println("2. Iniciar un nuevo pedido");
        System.out.println("3. Agregar un elemento a un nuevo pedido");
        System.out.println("4. Cerrar un pedido y guardar la factura");
        System.out.println("5. Consultar la información de un pedido dado su ID");
        System.out.println("6. Salir de la aplicación");
    }
    private void ejecutarMostrarMenu() //para cargar el menú
    {
        System.out.println("Cargando el menú");
        try
        {
            Restaurante.mostrarMenu();
        }
        catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo no se encontró.");
		}
        catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}
    }
    
    private void ejecutarCargarDatos()// carga los datos de los 3 archivos en un hashmap para poder acceder a los precios
    {
    	try
        {
            Restaurante.cargarInfoRestaurante();
        }
        catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo no se encontró.");
		}
        catch (IOException e)
		{
			System.out.println("Aqui pasó algo malo");
			System.out.println(e.getMessage());
		}
    }
    private void ejecutarNuevoPedido()
    {
    	Restaurante.crearPedido(); 
    }
   

    public static void main(String[] args)
    {
        ConsolaHamburguesas consola = new ConsolaHamburguesas();
        consola.ejecutarAplicacion();
    }











}













