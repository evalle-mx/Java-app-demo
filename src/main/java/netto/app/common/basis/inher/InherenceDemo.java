package netto.app.common.basis.inher;

/**
 * This class demonstrates how inherence Works, by extends or implements
 * @author evalle
 *
 */
abstract class Shape {
	
//	abstract void draw() {
//		System.out.println("Drawing a Shape");
//	}
	abstract void draw();  /* Abstract methods do not specify a body */
}

interface Polygon {
	void getSides();
}

class Circle extends Shape {
	void draw() {
		System.out.println("Drawing a Circle");
	}
}

class Rectangle extends Shape {
	int sides = 4;
	void draw() {
		System.out.println("Drawing a Rectangle");
	}
}
class Octagon extends Shape implements Polygon {
	private int sides = 8;
	void draw() { /* If method draw is abstract, must be implemented */
		System.out.println("Drawing a Octagon");
	}

	@Override
	public void getSides() {
		System.out.println( "sides: " + this.sides);
	}
}

public class InherenceDemo {
	
	public static void main(String[] args) {
		Shape s;
//		s = new Shape();
//		s.draw();
		
		Circle c;
		c = new Circle();
		c.draw();
		

		Rectangle r;
		r = new Rectangle();
		r.draw();
		System.out.println( "sides: " + r.sides);
		
		Octagon o = new Octagon();
		o.draw();
		//System.out.println( "sides: " + o.sides); //Not visible
		o.getSides(); //Error, Shape has no method getSides
		
		Shape s2;
		s2 = new Circle();
		s2.draw();
		
		//Runtime Polymorphism
		s2 = new Rectangle();
		s2.draw();
//		System.out.println(s2.sides); //Error
		
		s = new Rectangle();
		s.draw();
		
		//s = new Polygon(); //Interface Cannot be instantiated
		//s.draw();
		
		s = new Octagon();
		s.draw();
//		System.out.println( "sides: " + s.sides);//Error Shape has no sides
//		s.getSides(); //Error, Shape has no method getSides
		
		
//		s = new Shape(); //If Shape is abstract, cannot be instantiated
//		s.draw();		
	}
	
	
	

}
