package netto.app.common;

import java.util.Random;

public class RandomDemos {

	
	public static void main(String[] args) {
		printRandomsUsingForEach(10);
	}
	
	/* print N random numbers using forEach */
	public static void printRandomsUsingForEach(int nRandoms) {
		

		Random random = new Random();

		random.ints()
			.limit(nRandoms)
				.forEach(System.out::println);
	}
	
	
}
