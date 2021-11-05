package netto.app.progfuncional;
/* https://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/ */
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TransformObjects {

	public static void main(String[] args) {
		demo();
	}
	
	protected static void demo() {
		List<Foo> foos = new ArrayList<>();

		// create 4 foos
		IntStream
		    .range(1, 4)
		    .forEach(i -> foos.add(new Foo("Foo" + i)));
		// create 4 bars in each foo
		foos.forEach(f ->
	    	IntStream
	        	.range(1, 4)
	        	.forEach(i -> f.bars.add(new Bar("Bar" + i + " dentro de " + f.name)))
	      );
		
		foos.stream()
	    .flatMap(f -> f.bars.stream())
	    .forEach(b -> System.out.println(b.name));
		
		System.out.println("------");
		/*  Iinto a single pipeline of stream operations: */ 
		IntStream.range(1, 4)
	    	.mapToObj(i -> new Foo("Foo" + i))
	    	.peek(f -> IntStream.range(1, 4)  //peek return a stream
	    			.mapToObj(i -> new Bar("Bar" + i + " <= " + f.name))
	    			.forEach(f.bars::add))
	    	.flatMap(f -> f.bars.stream())
	    	.forEach(b -> System.out.println(b.name));
	}
	
	static class Foo {
	    String name;
	    List<Bar> bars = new ArrayList<Bar>();

	    Foo(String name) {
	        this.name = name;
	    }
	}

	static class Bar {
	    String name;

	    Bar(String name) {
	        this.name = name;
	    }
	}
}
