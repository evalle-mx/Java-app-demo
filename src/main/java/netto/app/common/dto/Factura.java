package netto.app.common.dto;

public class Factura {
	String concepto;
	int importe;
	
	public Factura(String concepto, Integer importe) {
		this.concepto = concepto;
		this.importe = importe.intValue();
	}
	
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public int getImporte() {
		return importe;
	}
	public void setImporte(int importe) {
		this.importe = importe;
	}
}
