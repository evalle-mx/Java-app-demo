package netto.app.common.dto;

public class Persona {

	private String nombre;
	private String apellidos;
	private int edad;
	private StringBuilder sb;

	public Persona() {
	}

	public Persona(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Persona(String nombre, String apellidos, int edad) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public void displayInfo() {
		sb = new StringBuilder(this.getNombre()).append(" ").append( this.getApellidos() ).append(", ").append(this.edad);
		System.out.println(sb.toString()); 
	}
	
	public String getNombreCompleto() {
		return new StringBuilder(this.getNombre()).append(" ").append( this.getApellidos() ).toString();
	}
	
	@Override
	public String toString() {
		//return getNombreCompleto();
		return new StringBuilder(this.getNombre()).append(" ").append( this.getApellidos() ).append(", ").append(this.edad).toString();
	}
}
