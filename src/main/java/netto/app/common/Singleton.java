package netto.app.common;

public class Singleton {

	/**
	 * 0) unique instance
	 */
	private static Singleton single_instance = null;
	/** 
	 * a) private Constructor
	 */
	private Singleton() { }
	
	/**
	 * b) static method that has return type object of this singleton class.
	 * @return
	 */
	public static Singleton getInstance() {
		if(single_instance== null) { //Lazy initialization
			single_instance = new Singleton();
		}
		return single_instance;
	}
	
}
