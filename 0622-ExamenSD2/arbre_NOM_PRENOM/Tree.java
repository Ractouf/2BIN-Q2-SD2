import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class Tree implements Iterable<Tree> {

	private int value;

	private Tree parent;

	private Tree[] children;

	// *******************************************************
	// CONSTRUCTEURS
	// *******************************************************
	public Tree(int v, Tree[] chd) {
		value = v;
		children = chd;

		for (Tree child : chd) {
			child.parent = this;
		}
	}

	public Tree(int v) {
		this(v, new Tree[0]);
	}

	// *******************************************************
	// GETTERS
	// *******************************************************
	public int getValue() {
		return value;
	}

	public Tree getParent() {
		return parent;
	}

	public Tree[] getChildren() {
		return children;
	}

	// *******************************************************
	// ITERATEUR
	// *******************************************************
	public Iterator<Tree> iterator() {
		return Arrays.asList(children).iterator();
	}

	public int nbrChildren() {
		return children.length;
	}

	public boolean isLeaf() {
		return children.length == 0;
	}

	// renvoie vrai si l'entier est contenu dans l'arbre, faux sinon
	public boolean contains(int x) {
		return contains(this, x);
	}

	private boolean contains(Tree t, int x) {
		if (t.getValue() == x) {
			return true;
		}

		for (Tree c : t.getChildren()) {
			if (contains(c, x)) return true;
		}

		return false;
	}

	// renvoie une map dont les clés sont les entiers présents dans l'arbre 
	// et les valeurs sont le nombre de fois qu'apparaissent ces entiers dans l'arbre
	public HashMap<Integer,Integer> toMap(){
		HashMap<Integer, Integer> map = new HashMap<>();

		return toMap(this, map);
	}

	private HashMap<Integer,Integer> toMap(Tree t, HashMap<Integer, Integer> map) {
		if (!map.containsKey(t.getValue()))
			map.put(t.getValue(), 0);

		map.put(t.getValue(), map.get(t.getValue()) + 1);

		for (Tree c : t.getChildren()) {
			toMap(c, map);
		}

		return map;
	}

	public static void main(String[] args) {
		Tree l6 = new Tree(6);
		Tree l1 = new Tree(1);
		Tree t9 = new Tree(9, new Tree[] { l6, l1 });
		Tree l3 = new Tree(3);
		Tree l8 = new Tree(8);
		Tree t8 = new Tree(8, new Tree[] { l3, l8 });
		Tree l4 = new Tree(4);
		Tree t4 = new Tree(4, new Tree[] { t8, t9, l4});
		System.out.println(t4.contains(6));
		System.out.println(t4.toMap());
	}
}

