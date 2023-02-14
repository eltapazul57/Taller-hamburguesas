package modelo;

import java.util.List;

public class Combo implements Producto
{
	
	
	private String nombreCombo;
	private int precioTotal;
	
	public Combo(String nombreCombo,int precioTotal)
	{
		this.nombreCombo = nombreCombo;
		this.precioTotal = precioTotal;
		
	}
	

	
	public String getNombre() {
		// TODO Auto-generated method stub
		return nombreCombo;
	}

	
	
	@Override
	public String getPrecio() {
		// TODO Auto-generated method stub
		return Integer.toString(precioTotal) ;
	}



	@Override
	public String getTextoFactura() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
