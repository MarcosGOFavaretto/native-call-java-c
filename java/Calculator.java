public class Calculator {

	// Loding our library.
	static {
		System.load("D:\\Users\\T-Gamer\\Documents\\MARCOS\\ALURA\\Nova pasta\\native-call-java-c\\c\\libcalculator.dll");
	}

	// Declaring methods to be implemented in C.
	public static native int add(int num1, int num2);
	public static native int subtract(int num1, int num2);
	public static native int multiply(int num1, int num2);
	public static native int divide(int num1, int num2);

	public static void main(String[] args) {

		// Obtaining numbers from provided arguments.
		int num1 = Integer.valueOf(args[0]);
		int num2 = Integer.valueOf(args[0]);

		// Calculating and displaing the results.
		System.out.println("RESULTS FOR " + num1 + " AND " + num2);
		System.out.println("Sum: " + add(num1, num2));
		System.out.println("Subtract: " + subtract(num1, num2));
		System.out.println("Multiply: " + multiply(num1, num2));
		System.out.println("Divide: " + divide(num1, num2));
	}

}