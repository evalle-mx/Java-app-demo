package netto.app.common.dto;

import java.util.ArrayList;
import java.util.Iterator;

public class BolsaGen<T> implements Iterable<T>{
	private ArrayList<T> lista = new ArrayList<T>();
	
	private int tope;
	
	public BolsaGen(int tope) {
		super();
		this.tope = tope;
	}
	
	public void add(T objeto) {
		if(lista.size()< tope) {
			lista.add(objeto);
		}
		else {
			throw new RuntimeException("No caben mas");
		}
	}

	@Override
	public Iterator<T> iterator() {
		return this.lista.iterator();
	}

}
