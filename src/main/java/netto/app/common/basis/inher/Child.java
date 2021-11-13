package netto.app.common.basis.inher;

class Parent {
	public static void foo() {System.out.println("I'm a foo in super");}
	public void bar() { System.out.println("I'm a bar in super");}
}
public class Child extends Parent {
	public static void foo() {System.out.println("I'm a foo in Child");}
	public void bar() { System.out.println("I'm a bar in Child");}
	
	public static void main(String[] args) {
		Parent par = new Child();
		Child child = new Child();
		
		par.foo(); //foo is static, takes Parent
		child.foo();
		
		par.bar(); //bar is not static, takes child
		child.bar();
	}

}
/*
'm a foo in super
I'm a foo in Child
I'm a bar in Child
I'm a bar in Child*/