package netto.app.common.dto;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * Clase Bolsa nos permitirá almacenar objetos de varios tipos
 * Esta clase tendrá un límite de objetos a almacenar 
 */
public class Bolsa implements Iterable {
	
	private ArrayList lista = new ArrayList();
	private int tope;
	
	public Bolsa(int tope) {
		super();
		this.tope = tope;
	}
	
	public void add(Object objeto) {
		if(lista.size()< tope) {
			lista.add(objeto);
			System.out.println("added");
		}
		else {
			throw new RuntimeException("No caben mas");
		}
	}

	@Override
	public Iterator iterator() {		
		return lista.iterator();
	}

}
