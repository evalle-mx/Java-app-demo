package netto.app.util;

public class StringConverter {
	
	private static final String snakedString = "geeks_for_geeks";
	private static StringBuilder sb;
	
	public static void main(String[] args) {
		snakeToCamel();
	}
	
	/**
	 * Method 1: Using Traversal
	 * <ul>
	 * <li>The idea is to first capitalize the first letter of the string.</li>
	 * <li>Then convert the string to string builder.</li>
	 * <li>Then traverse the string character by character from the first</li> 
	 * <li>index to the last index and check if the character is underscored then delete the character and capitalize the next character of the underscore.</li>
	 * <li>Print the modified string.</li> </ul>
	 */
	protected static void snakeToCamel1() {
		//Capitalize 1st letter and copy the rest of the string
		String str = snakedString.substring(0,1).toUpperCase()
				 + snakedString.substring(1);
		System.out.println("str:"+str);
        sb = new StringBuilder(str);
		
		for (int i = 0; i < sb.length(); i++) {
			  
            // Check char is underscore
            if (sb.charAt(i) == '_') {
            	sb.deleteCharAt(i);
            	sb.replace(
                    i, i + 1,
                    String.valueOf(
                        Character.toUpperCase(
                        		sb.charAt(i))));
            }
        }
		
		System.out.println(sb.toString());
	}
	
	/**
	 * Method 2: Using String.replaceFirst() method
	 * <ul><li>The idea is to use String.replaceFirst() method to convert the given string from snake case to camel case.</li>
	 * <li>First, capitalize the first letter of the string.</li>
	 * <li>Run a while loop if the string contains underscore (_).</li>
	 * <li>Replace the first occurrence of a letter that present after the underscore to the capitalized form of the next letter of the underscore.</li>
	 * <li>Print the modified string.</li></ul>
	 */
	protected static void snakeToCamel() {
		String str = snakedString.substring(0, 1).toUpperCase() + snakedString.substring(1);
		
		while(str.contains("_")) {
			// Replace 1st letter after underscore, to capitalize form of next letter of underscore
            str = str.replaceFirst(
                          "_[a-z]",
                          String.valueOf(
                              Character.toUpperCase(
                                  str.charAt(
                                      str.indexOf("_") + 1))));
		}
		System.out.println(str);
	}

}
