package ex3;

public class Test {
	public static void main(String[] args) {
		int[] pointures={42,44,42};
		LocationPatins l = new LocationPatins(pointures);
		System.out.println(l.attribuerCasierAvecPatins(42));
		System.out.println(l.attribuerCasierAvecPatins(42));
		System.out.println(l.attribuerCasierAvecPatins(44));
		System.out.println(l.attribuerCasierAvecPatins(44));
		System.out.println(l.attribuerCasierAvecPatins(42));
		System.out.println(l.libererCasier(0));
		System.out.println(l.libererCasier(1));
		System.out.println(l.attribuerCasierAvecPatins(42));
		System.out.println(l.attribuerCasierAvecPatins(42));
		System.out.println(l.attribuerCasierAvecPatins(44));

	}
}
