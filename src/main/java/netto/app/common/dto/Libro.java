package netto.app.common.dto;

public class Libro {

	private String titulo;
	private int paginas;
	private String categoria;
	
	public Libro() { }
	public Libro(String titulo, int paginas) {
		this.titulo= titulo;
		this.paginas=paginas;
	}
	public Libro(String titulo, String categoria, int paginas) {
		this.titulo= titulo;
		this.categoria = categoria;
		this.paginas=paginas;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String title) {
		this.titulo = title;
	}
	public int getPaginas() {
		return paginas;
	}
	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
