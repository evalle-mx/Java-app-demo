package netto.app.progfuncional.stream;


import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

import netto.app.common.dto.TourRating;

public class TourRatingMap {
	
	
	static List<TourRating> lsTourRatings;
	
	public static void main(String[] args) {
		setLista();
		
		calculaPromedio();
	}
	
	protected static void calculaPromedio() {
		OptionalDouble promedio;
		
		lsTourRatings.stream()
		.forEach( r -> System.out.println(r));
		
		promedio = lsTourRatings.stream()
				.mapToInt(TourRating::getScore)
				.average();
		
		System.out.println("promedio: "+promedio);
	}
	
	
	private static void setLista() {
		
		TourRating t1 = new TourRating("T1", 5, "Excelent");
		TourRating t2 = new TourRating("T1", 4, "Good");
		TourRating t3 = new TourRating("T1", 5, "The better");
		TourRating t4 = new TourRating("T1", 3, "Not bad");
		TourRating t5 = new TourRating("T1", 3, "I had a good time");
		TourRating t6 = new TourRating("T1", 1, "horrible");
		TourRating t7 = new TourRating("T1", 5, "perfect");
		
		lsTourRatings = Arrays.asList(t1, t2, t3, t4, t5, t6, t7 );
		
	}
	

}
