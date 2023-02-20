package procesamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import modelo.Ingrediente;
import modelo.Pedido;
import modelo.ProductoMenu;
import modelo.Combo;


public class Restaurante 
{
	//Hash maps donde van a ir la información del menú
	//en la llave va el nombre de la cosa y en el valor un int con el valor
	private static Map <String, Ingrediente> mapaIngredientes = new LinkedHashMap<>();
	private static Map <String, ProductoMenu> mapaMenu = new LinkedHashMap<>();
	private static Map <String, Combo> mapaCombos= new LinkedHashMap<>();
	private static Map <String, Pedido> mapaPedidos = new LinkedHashMap<>();
	private static Map <String, String> mapaAgregarPedidos = new LinkedHashMap<>();
	
    public static void mostrarMenuCompleto() throws FileNotFoundException, IOException//muestra el menú en consola y los combos e ingredientes añañay mi farafafafafafafaf
    {
        BufferedReader brMenu = new BufferedReader(new FileReader("./data/menu.txt"));
		String linea = "";
		int numeroMenu = 1;
		String numeroParaMapa = "";
		System.out.println("Productos del menú");
        while((linea = brMenu.readLine()) != null)
            {	
        		numeroParaMapa = Integer.toString(numeroMenu);
        		String itemSinPyC =  linea.split(";")[0];
        		String itemDelMapa = mapaAgregarPedidos.get(numeroParaMapa);
        		if (itemDelMapa == null)
        		{	
        			mapaAgregarPedidos.put(numeroParaMapa,itemSinPyC);
        		}
                System.out.println((numeroMenu)+". " + linea);
                numeroMenu +=1;
            }
        System.out.println("______________");
        System.out.println("Combos");
        BufferedReader brCombos = new BufferedReader(new FileReader("./data/combos.txt"));
		String linea2 = "";
		while((linea2 = brCombos.readLine()) != null)
        {
			numeroParaMapa = Integer.toString(numeroMenu);
    		String itemSinPyC =  linea2.split(";")[0];
    		String itemDelMapa = mapaAgregarPedidos.get(numeroParaMapa);
    		if (itemDelMapa == null)
    		{
    			mapaAgregarPedidos.put(numeroParaMapa,itemSinPyC);
    		}
            System.out.println((numeroMenu)+". " + linea2);
            numeroMenu +=1;
        }
		System.out.println("______________");
        System.out.println("Ingredientes extras");
        BufferedReader brIngredientes = new BufferedReader(new FileReader("./data/ingredientes.txt"));
		String linea3 = "";
		while((linea3 = brIngredientes.readLine()) != null)
        {
			numeroParaMapa = Integer.toString(numeroMenu);
    		String itemSinPyC =  linea3.split(";")[0];
    		String itemDelMapa = mapaAgregarPedidos.get(numeroParaMapa);
    		if (itemDelMapa == null)
    		{
    			mapaAgregarPedidos.put(numeroParaMapa,itemSinPyC);
    		}
            System.out.println((numeroMenu)+". " + linea3);
            numeroMenu +=1;
        }
        
    }
    public static void cargarInfoRestaurante() throws FileNotFoundException, IOException
    {	
    	cargarIng();
    	cargarMenu();
    	cargarCombos();
    }
    
    public static void cargarIng() throws FileNotFoundException, IOException //se agregó el argumetno de archivo
    {
    	BufferedReader brIng = new BufferedReader(new FileReader("./data/ingredientes.txt"));
		String linea = "";
        System.out.println("Se emepezó a cargar los ingredientes");
        while((linea = brIng.readLine()) != null)
            {
                String[] partes = linea.split(";"); //separa la linea por el ; 
                String nombre = partes[0]; //Agarra solo la primera parte del txt
                int costoAdicional = Integer.parseInt(partes[1]); //Agarra el precio de lo adicional
                Ingrediente ingrediente = mapaIngredientes.get(nombre);
                if (ingrediente == null)
                {
                	ingrediente = new Ingrediente(nombre,costoAdicional);
                	
                	mapaIngredientes.put(nombre, ingrediente);
                }
            }
        System.out.println("Se cargaron los ingredientes\n");
    }
    public static void cargarMenu() throws FileNotFoundException, IOException //se agregó el argumetno de archivo
    {
    	BufferedReader brMenu = new BufferedReader(new FileReader("./data/menu.txt"));
		String linea = "";
        
        System.out.println("Se emepezó a cargar el menú");
        while((linea = brMenu.readLine()) != null)
        {	
        	String[] partes = linea.split(";"); //separa la linea por el ; 
            String nombre = partes[0]; //Agarra solo la primera parte del txt
            int precioBase = Integer.parseInt(partes[1]);
            ProductoMenu productMenu = mapaMenu.get(nombre);
            
            if (productMenu == null)
            {
            	productMenu = new ProductoMenu(nombre,precioBase);
            	mapaMenu.put(nombre, productMenu);
            }
        }
        System.out.println("Se cargó el menú \n");
    }
    public static void cargarCombos() throws FileNotFoundException, IOException //se agregó el argumetno de archivo
    {
    	BufferedReader brCombos = new BufferedReader(new FileReader("./data/combos.txt"));
		String linea = "";
        System.out.println("Se emepezaron a cargar los combos");
        while((linea = brCombos.readLine()) != null)
        {
        	String[] partes = linea.split(";");
        	String nombre = partes[0];
        	String descuento = partes[1];
        	String descuentoSinPorcentaje = descuento.replace("%", "");
        	int descuentoReal = Integer.parseInt(descuentoSinPorcentaje);
        	String item1 = partes[2];
        	String item2 = partes[3];
        	String item3 = partes[4];
        	ProductoMenu nombre1 = mapaMenu.get(item1);
        	ProductoMenu nombre2 = mapaMenu.get(item2);
        	ProductoMenu nombre3 = mapaMenu.get(item3);
        	double precioTotal = (Integer.parseInt(nombre1.getPrecio())+Integer.parseInt(nombre2.getPrecio())+Integer.parseInt(nombre3.getPrecio()));
        	int precioDescuento = ((int)precioTotal)*(int)(1-(descuentoReal/100));
        	Combo combo = mapaCombos.get(nombre);
        	if (combo == null)
        	{
        		combo = new Combo(nombre,precioDescuento);
        		mapaCombos.put(nombre, combo);	
        	}
        }
        System.out.println("Se cargaron los combos");        
    }
    public static String crearPedido()
    {
    	Scanner inputNombre = new Scanner(System.in);
    	System.out.println("Ingrese su nombre");
        String nombreCliente = inputNombre.nextLine();
        Scanner inputDireccion = new Scanner(System.in);
    	System.out.println("Ingrese su direccion");
        String direccionCliente = inputDireccion.nextLine();
        int idPedido = 0;
        Pedido pedido = new Pedido(nombreCliente, direccionCliente, idPedido);
        pedido.generarIdFactura();
        String facturaParaPoner = pedido.getIdFactura();
        mapaPedidos.put(facturaParaPoner, pedido);
        String facturaReturn = pedido.getIdFactura();
        System.out.println("El id de su pedido es: \n");
        System.out.println(facturaReturn);
        return facturaReturn;
    }
    public static Pedido agregarPedido(String facturaID)
    {
    	try {
			mostrarMenuCompleto();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Pedido pedidoParaAgregar = mapaPedidos.get(facturaID);
    	System.out.println(pedidoParaAgregar.getPrecioTotal());
    	Scanner inputPMenuAgregar = new Scanner(System.in);
    	System.out.println("Ingrese el número del producto para agregar");
    	String numeroAgregar= inputPMenuAgregar.nextLine();
    	int opcionAgregar = Integer.parseInt(numeroAgregar);
    	if ( 0<opcionAgregar || opcionAgregar<42) // || significa or
    	{
    		if (0<opcionAgregar || opcionAgregar<23)
    		{	
    			String nombreProductoParaAgregar = mapaAgregarPedidos.get(Integer.toString(opcionAgregar));
    			ProductoMenu productoParaAgregar = new ProductoMenu(nombreProductoParaAgregar,Integer.parseInt((mapaMenu.get(nombreProductoParaAgregar)).getPrecio()));
    			pedidoParaAgregar.agregarProductoMenu(productoParaAgregar);
    		}
    		else if (22<opcionAgregar||opcionAgregar<27)
    		{
    			String nombreComboParaAgregar = mapaAgregarPedidos.get(Integer.toString(opcionAgregar));
    			Combo comboParaAgregar = new Combo(nombreComboParaAgregar, Integer.parseInt((mapaCombos.get(nombreComboParaAgregar)).getPrecio()));
    			pedidoParaAgregar.agregarNuevoCombo(comboParaAgregar);
    			
    		}
    		else if (26<opcionAgregar || opcionAgregar <42)
    		{    			
    			String nombreIngredienteParaAgregar = mapaAgregarPedidos.get(Integer.toString(opcionAgregar));
    			Ingrediente ingredienteAgregar = new Ingrediente(nombreIngredienteParaAgregar, (mapaIngredientes.get(nombreIngredienteParaAgregar)).getCostoAdicional());
    			pedidoParaAgregar.agregarNuevoIngrediente(ingredienteAgregar);
    		}
    	}
    	System.out.println(pedidoParaAgregar.getPrecioTotal());
    	
    	return pedidoParaAgregar;
    }
    public static void cerrarPedido(Pedido pedidoParaCerrar) throws FileNotFoundException, UnsupportedEncodingException
    {	
    	String facturaParaGuardar = pedidoParaCerrar.getIdFactura();
    	pedidoParaCerrar.calcularPrecioTotal();
    	pedidoParaCerrar.calcularIva();
    	int precioTotal = pedidoParaCerrar.getPrecioTotal();
    	System.out.println("El precio total de su pedido fue: \n");
    	System.out.println(precioTotal);
    	PrintWriter writer = new PrintWriter("./facturas/"+facturaParaGuardar+".txt", "UTF-8");
		writer.println("----------FACTURA #"+String.valueOf(facturaParaGuardar)+"----------");
		writer.println("Los productos son: ");
		System.out.println(pedidoParaCerrar.getTextoFactura());
		writer.println(pedidoParaCerrar.getTextoFactura());
		writer.close();
    	
    }
 
    
}
