package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pedido 
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
	
	
	
	public Pedido(String nombreCliente,String direccionCliente, int idPedido)
	{
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.idPedido = idPedido;
		this.numeroPedidos = numeroPedidos;
		this.productosMenu = new ArrayList<>();
		this.ingredienteAdicional = new ArrayList<>();
		
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
		for (ProductoMenu pm : productosMenu) //Le suma el precio total de los productos del menú la pedido
		{
			precioTotalPedido += Integer.parseInt(pm.getPrecio());
		}
		for (Ingrediente ing: ingredienteAdicional)
		{
			precioTotalPedido += ing.getCostoAdicional();
			
		}
		for (Combo comb : combosPedido)
		{
			precioTotalPedido += Integer.parseInt(comb.getPrecio());
		}
	}
	public void calcularIva()
	{
		iva = (int)(precioTotalPedido*0.19);
		precioConIva = iva+precioTotalPedido;
	}
	public void generarIdFactura()
	{
		idPedido = Integer.parseInt(UUID.randomUUID().toString());
	}
	public String getIdFactura()
	{
		String fact= "";
		fact=Integer.toString(idPedido);
		return fact;
	}
}
