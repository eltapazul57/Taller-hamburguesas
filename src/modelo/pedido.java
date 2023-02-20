package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Pedido implements Producto
{
	private String nombreCliente;
	private String direccionCliente;
	private int idPedido;
	private int numeroPedidos=0;
	private int iva = 0;
	private int precioTotalPedido = 0;
	private int precioConIva = 0;
	private List <ProductoMenu> productosMenu;
	private List<Ingrediente> ingredienteAdicional;
	private List<Combo> combosPedido;
	private List<String> pedidosTotales;
	
	
	public Pedido(String nombreCliente,String direccionCliente, int idPedido)
	{
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.idPedido = idPedido;
		this.numeroPedidos = numeroPedidos;
		this.productosMenu = new ArrayList<>();
		this.combosPedido = new ArrayList<>();
		this.ingredienteAdicional = new ArrayList<>();
		this.pedidosTotales = new ArrayList<>();
		
	}
	public String getNombreCliente()
	{
		return nombreCliente;
	}
	public String getDireccion()
	{
		return direccionCliente;
	}
	public int getIdPedido()
	{
		return idPedido;
	}
	public int getNumeroPedidos()
	{
		return numeroPedidos;
	}
	public void agregarProductoMenu(ProductoMenu nuevoProducto)
	{
		productosMenu.add(nuevoProducto);
		
	}
	public void agregarPedidosTotales(String nombreProducto)
	{
		pedidosTotales.add(nombreProducto);
	}
	public void agregarNuevoCombo(Combo nuevoCombo)
	{
		combosPedido.add(nuevoCombo);
	}
	public void agregarNuevoIngrediente(Ingrediente nuevoIngrediente)
	{
		ingredienteAdicional.add(nuevoIngrediente);
	}
	public void calcularPrecioTotal()
	{
		if (productosMenu != null)
			
		{
			for (ProductoMenu pm : productosMenu) //Le suma el precio total de los productos del menú la pedido
			{	

				precioTotalPedido += Integer.parseInt(pm.getPrecio());
			}
		}
		else
		{
			;
		}
		if (ingredienteAdicional != null)
		{
			for (Ingrediente ing: ingredienteAdicional)
			{
				precioTotalPedido += ing.getCostoAdicional();	
			}
		}
		else
		{
			;
		}
		if (combosPedido != null)
		{
			for (Combo comb : combosPedido)
			{
				precioTotalPedido += Integer.parseInt(comb.getPrecio());
			}
		}
	}
	public void calcularIva()
	{
		iva = (int)(precioTotalPedido*0.19);
		precioConIva = iva+precioTotalPedido;
	}
	public void generarIdFactura()
	{
		Random random = new Random();
		idPedido = random.nextInt(900) + 100;
	}
	public String getIdFactura()
	{
		String fact= "";
		fact=Integer.toString(idPedido);
		return fact;
	}
	public int getPrecioTotal()
	{
		return precioConIva;
	}
	@Override
	public String getPrecio() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getTextoFactura(){
		// TODO Auto-generated method stub
		String textoFactura = "";
		if (productosMenu != null)
		{
			
			for (ProductoMenu pm : productosMenu) //Le suma el precio total de los productos del menú la pedido
			{	
				textoFactura += (pm.getNombre() +" " + pm.getPrecio());
				textoFactura += "\n";
				
			}
		}
		else
		{
			;
		}
		if (ingredienteAdicional != null)
		{
			for (Ingrediente ing: ingredienteAdicional)
			{
				textoFactura = (ing.getNombre() +" " + ing.getCostoAdicional());
				textoFactura += "\n";	
			}
		}
		else
		{
			;
		}
		if (combosPedido != null)
		{
			for (Combo comb : combosPedido)
			{
				textoFactura = (comb.getNombre() + " " +comb.getPrecio());
				textoFactura += "\n";
			}
		}
		return textoFactura;
		
	}
}

