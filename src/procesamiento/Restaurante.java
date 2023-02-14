package procesamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import modelo.Ingrediente;
import modelo.ProductoMenu;
import modelo.Combo;

public class Restaurante 
{
	//Hash maps donde van a ir la información del menú
	//en la llave va el nombre de la cosa y en el valor un int con el valor
	private static Map <String, Ingrediente> mapaIngredientes = new LinkedHashMap<>();
	private static Map <String, ProductoMenu> mapaMenu = new LinkedHashMap<>();
	private static Map <String, Combo> mapaCombos= new LinkedHashMap<>();
	
	
    public static void mostrarMenu() throws FileNotFoundException, IOException//muestra el menú en consol
    {
        BufferedReader br = new BufferedReader(new FileReader("./data/menu.txt"));
		String linea = "";
        while((linea = br.readLine()) != null)
            {
                System.out.println(linea);
            }
    }
    public static void cargarInfoRestaurante() throws FileNotFoundException, IOException
    {	
    	
    	cargarIng();
    	cargarMenu();
    	cargarCombos();
    }
    
    public static void cargarIng() throws FileNotFoundException, IOException
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
    public static void cargarMenu() throws FileNotFoundException, IOException
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
            //System.out.println(productMenu.getNombre());
            //System.out.println(productMenu.getPrecio());
        }
        System.out.println("Se cargó el menú \n");
    }
    public static void cargarCombos() throws FileNotFoundException, IOException
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
 
    
}
