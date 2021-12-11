package netto.app.common;
/* https://www.geeksforgeeks.org/singleton-class-java/ */
public class SingletonDemo {
	
	public static void main(String[] args) {
		Singleton x = Singleton.getInstance();
		
		Singleton y = Singleton.getInstance();
		
		Singleton z = Singleton.getInstance();
		
		// Printing the hash code for above variable as declared
        System.out.println("Hashcode of x is " + x.hashCode() + ", memory address: "+ x.toString().substring(x.toString().indexOf("@"))); 
        System.out.println("Hashcode of y is " + y.hashCode() + ", memory address: "+ y.toString().substring(y.toString().indexOf("@")));
        System.out.println("Hashcode of z is " + z.hashCode() + ", memory address: "+ z.toString().substring(z.toString().indexOf("@")));
        
        // check if is the same object in the memory (HEAP)
        if (x == y && y == z) {
        	System.out.println("3 objects point to the SAME MEMORY LOCATION on the heap i.e, to the same object");
        }
        else {
            System.out.println("Three objects DO NOT point to the same memory location on the heap");
        }
	}

}
