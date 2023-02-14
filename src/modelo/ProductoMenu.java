package modelo;

public class ProductoMenu implements Producto
{
	private String nombre;
	private int precioBase;
	
	public ProductoMenu(String nombre, int precioBase)
	{
		this.nombre= nombre;
		this.precioBase = precioBase;		
	}
	public String getNombre()
	{
		return nombre;
	}
	
	@Override
	public String getPrecio() {
		// TODO Auto-generated method stub
		return Integer.toString(precioBase);
	}

}
