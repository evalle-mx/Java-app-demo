package netto.app.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import netto.app.common.dto.Persona;

public class TheArrays {

	
	public static void main(String[] args) {
//		tickTackToe();
//		demoList1();
//		demoStack();
//		demoQueue();
//		demoBidirecction();
//		demoMap();
		demoMap2();
	}
	
	protected static void tickTackToe() {
		
		char[][] board = new char[3][3];
		
		//fill by Lines
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = '-';
			}
		}
		
		char[][] board2 = new char[][] {
			new char[] {'#','#','#'},
			new char[] {'#','#','#'},
			new char[] {'#','#','#'}
		};
		
		System.out.println(Arrays.toString(board2) );	//[[C@4aa298b7, [C@7d4991ad, [C@28d93b30]
		System.out.println(Arrays.deepToString(board2) );	//[[0, 0, 0], [0, 0, 0], [0, 0, 0]]
		System.out.println();
		StringBuilder sb = new StringBuilder();
		for(char[] b1 : board2) {
			sb.append(Arrays.toString(b1)).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public static void demoList1() {
		List<String> colors = Arrays.asList("red", "blue", "yellow");
		System.out.println(colors.size());
		System.out.println(colors.contains("blue"));
		
	}
	
	public static void demoStack() {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(6);  stack.add(12);  stack.push(8);
		System.out.println("Peek:" + stack.peek());
		System.out.println("stack.size(): " + stack.size());
		System.out.println("  Stack: " + stack);
		while(!stack.empty()) {
			System.out.println("  Pop:" + stack.pop());
			System.out.println("  stack.size(): " + stack.size());
			System.out.println("  Stack: " + stack);
		}
	}
	
//	static record MyPerson(String name, int age) {};  //Java 14
	public static void demoQueue() {
		Queue<Persona> supermarket = new LinkedList<Persona>();
		supermarket.add(new Persona("Alex", "", 21));
		supermarket.add(new Persona("Mariam", "", 18));
		supermarket.add(new Persona("Ali", "", 40));
		
		System.out.println(supermarket.peek());
		System.out.println("("+supermarket.size()+") "+supermarket);
		while(!supermarket.isEmpty()) {
			System.out.println("  Poll: " +supermarket.poll());
			System.out.println("  ("+supermarket.size()+") "+supermarket);
		}
	}
	
	public static void demoBidirecction() {
		LinkedList<Persona> lnkList= new LinkedList<Persona>();
		lnkList.add(new Persona("Maria", "", 21));
		lnkList.add(new Persona("Alex", "", 18));
		lnkList.add(new Persona("ZaRA", "", 40));
		
		lnkList.addLast( new Persona("ultima", "", 55) );
		lnkList.addFirst( new Persona("Prima", "", 19) );
		
		System.out.println("lnkList: "+lnkList);
		ListIterator<Persona> perIterator = lnkList.listIterator();
		while(perIterator.hasNext()) {
			System.out.println(perIterator.next());
		}
		System.out.println();
		while(perIterator.hasPrevious()) {
			System.out.println(perIterator.previous());
		}
	}
	//are you gonna be able to take me to Guelp tomorrow?
	
		public static void demoMap() {
			Map<String, Persona> map = new HashMap<String, Persona>();
			Persona p1 = new Persona("Maria", "", 10);
			map.put("P1", p1);
			map.put("P2", new Persona("Alondra", "", 20));
			map.put("P3", new Persona("Rocio", "", 12));
			map.put("PX", new Persona("Alicia", "", 5));
			
			System.out.println(map);
			System.out.println("Size: " + map.size());
			System.out.println("map.get(P1): "+ map.get("P1"));
			System.out.println("map.containsKey(\"P2\"): "+ map.containsKey("P2"));
			System.out.println("map.containsValue(p1): "+ map.containsValue(p1));
			System.out.println("map.keySet(): "+ map.keySet());
			System.out.println("map.values(): "+ map.values());
			System.out.println("map.entrySet(): "+ map.entrySet());
			
			map.entrySet().forEach(System.out::println );
			map.entrySet().forEach( p-> {
				System.out.print(p.getKey() + " => ");
				System.out.println(p.getValue().getNombre());
			});
			map.remove("PX");
			
			map.forEach((iKey, objPerson) -> {
				System.out.println(iKey + " - " + objPerson.getNombre() );
			});
			
			System.out.println(map.getOrDefault("P25", new Persona("Incognito","", 99)));
			//map.computeIfAbsent("P25", Persona -> {});
		}
		
		
		static class Comprador {
			String name;
			
			public Comprador(String name) {
				this.name = name;
			}
			
			@Override
			public String toString() {				
				return "Comprador{"+
						"name='"+name+'\''+
						'}';
			}
			//*** Equals and hashCode must be overriden to compare and get equals for Set and Map
			@Override
			public boolean equals(Object o) {
				if(this ==o)return true;
				if(o==null || getClass() != o.getClass())return false;
				Comprador comp = (Comprador)o;
				return Objects.equals(name, comp.name); //<= Custom equality criteria
			}
			
			@Override
			public int hashCode() {
				return Objects.hash(name); //<= Custom Hash calculation criteria
			}
			
			// ********* (java record has this by default)
		}
		
		static class Diamante {
			String name;
		
			public Diamante(String name) {
					this.name = name;
			}
				
			@Override
			public String toString() {				
				return "Diamante{"+
						"name='"+name+'\''+
						'}';
			}
		}
		
		public static void demoMap2() {
			Map<Comprador, Diamante> map = new HashMap<TheArrays.Comprador, TheArrays.Diamante>();
			
			map.put(new Comprador("Jamila"), new Diamante("PinkPanter"));
			System.out.println(map);
			
			System.out.println(map.get(new Comprador("Jamila"))); //Supposed to bring PinkPanter (if Key is equal)
			
			//Once overriden equals and hasCode:
			System.out.println(new Comprador("Pepe").hashCode());
			System.out.println(new Comprador("Pepe").hashCode());
			
			
		}
		
		/**
		 * Muestra como crear una lista a partir de otra
		 */
		protected static void extendList() {
			List<Integer> ls1 = new ArrayList<Integer>();
			
			ls1.add(2); ls1.add(4); ls1.add(6);
			System.out.println(ls1);
			List<Integer> ls2 = new ArrayList<Integer>();
			ls2.addAll(ls1);
			ls2.add(3); ls2.add(5);
			System.out.println(ls2);
		}
	
}
