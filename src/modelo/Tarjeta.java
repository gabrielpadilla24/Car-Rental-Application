package modelo;



public class Tarjeta {
	private String numero;
	private String nombreTitular;
	private String fechaVencimiento;
	private Boolean bloqueada;
	
	public Tarjeta (String elnumero,String nombreTitular,String fechaVencimiento){
		this.numero = elnumero;
		this.nombreTitular = nombreTitular;
		this.fechaVencimiento = fechaVencimiento;
		this.bloqueada = true;
		
	
	}

}
