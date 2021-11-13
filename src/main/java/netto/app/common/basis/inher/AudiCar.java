package netto.app.common.basis.inher;
/**
 * Demonstrates Polymorphism or dynamic method dispatch, and override private methods
 * 
 * @author evalle
 *
 */
class Car {
	void run() {
		System.out.println("Car is running");
	}
	private static void honk() {
		System.out.println("Honk 1");
	}
}

public class AudiCar  extends Car {
	private static void honk() {
		System.out.println("Beep ");
	}
	void run () {
		System.out.println("Audi is running safely with 100km ");
	}
	
	int wheels = 4;
	
	public static void main(String[] args) {
		Car b= new AudiCar(); //UpCasting
		b.run();
//		b.honk(); //Not visible in Car
		
		AudiCar c = new AudiCar();
		c.run();
		c.honk();
		
	}
}
