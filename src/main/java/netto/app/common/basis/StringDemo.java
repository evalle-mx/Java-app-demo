package netto.app.common.basis;

public class StringDemo {

	
	protected static void testEmpty(String st) {
		boolean esVacio = st.isEmpty();
		System.out.println("esVacio? "+ esVacio);
	}
	
	
	public static void main(String[] args) {
		testEmpty(null);
	}
	
}
