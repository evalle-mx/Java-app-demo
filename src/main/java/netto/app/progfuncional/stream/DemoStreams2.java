package netto.app.progfuncional.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;

public class DemoStreams2 {

	public static void main(String[] args) {
//		printRandomsUsingForEach(10);
		highest_Lower_Number();
	}
	
	/**
	 *  print N random numbers using forEach
	 * @param nRandoms
	 */
	public static void printRandomsUsingForEach(int nRandoms) {
		

		Random random = new Random();

		random.ints()
			.limit(nRandoms)
				.forEach(System.out::println);
	}
	
	/**
	 *  Get the highest number that exists on a list?
	 */
	public static void highest_Lower_Number() {
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

		IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

		System.out.println("Highest number in List : " + stats.getMax());
		System.out.println("Lowest number in List : " + stats.getMin());
	}
}
