import java.util.ArrayDeque;

public class Trees {
	public static int nbrLeaves(Tree t) {
		int r;
		if (t.isLeaf()) {
			r = 1;
		} else {
			r = 0;
			for (Tree child : t) {
				r += nbrLeaves(child);
			}
		}
		return r;
	}

	public static Tree[] flattenLeaves(Tree t) {
		int nl = nbrLeaves(t);
		Tree[] r = new Tree[nl];
		flattenLeaves(t, r, 0);
		return r;
	}

	private static int flattenLeaves(Tree t, Tree[] a, int idx) {
		int r;
		if (t.isLeaf()) {
			a[idx] = t;
			r = 1;
		} else {
			r = 0;
			for (Tree child : t) {
				r += flattenLeaves(child, a, idx + r);
			}
		}
		return r;
	}

	public static void pathV1(Tree t) {
		System.out.println(t.getValue());
		if (t.getParent() != null) {
			pathV1(t.getParent());
		}
	}

	public static void pathV2(Tree t) {
		while (t != null) {
			System.out.println(t.getValue());
			t = t.getParent();
		}
	}

	// 1.1)
	public static int nbrNode(Tree t) {
		int r = 1;

		for (Tree child : t)
			r += nbrNode(child);

		return r;
	}

	// 1.2)
	/*public static int min(Tree t) {
		int min = t.getValue();

		for (Tree child : t) {
			min = Math.min(min(child), min);
		}
		return min;
	}*/

	public static int min(Tree t) {
		return min(t, t.getValue());
	}

	private static int min(Tree t, int min) {
		if (t.getValue() < min)
			min = t.getValue();

		for (Tree child : t)
			min = min(child, min);

		return min;
	}

	// 1.3)
	public static int sum(Tree t) {
		int sum = t.getValue();

		for (Tree child : t) {
			sum += sum(child);
		}

		return sum;
	}

	// 1.4)
	public static boolean equals(Tree t1, Tree t2) {
		if (t1.getValue() != t2.getValue())
			return false;

		for (int i = 0; i < t1.nbrChildren(); i++) {
			if (!equals(t1.getChildren()[i], t2.getChildren()[i])) return false;
		}

		return true;
	}

	// 1.5)
	public static int depth(Tree n) {
		if (n.getParent() != null) {
			return depth(n.getParent()) + 1;
		}
		return 0;
	}

	// 1.6)
	public static boolean sameOne(Tree n1, Tree n2) {
		if (n1.getParent() != null)
			return sameOne(n1.getParent(), n2);

		if (n2.getParent() != null)
			return sameOne(n1, n2.getParent());

		return n1.getValue() == n2.getValue();
	}

	// 1.7)
	public static void dfsPrint(Tree t) {
		System.out.println("Arbre: " + t.getValue() + " de profondeur: " + depth(t));

		for (Tree c : t)
			dfsPrint(c);
	}

	// 1.8)
	public static void bfsPrint(Tree t) {
		ArrayDeque<Tree> deque = new ArrayDeque<>();
		deque.add(t);

		bfsPrint(deque);
	}

	private static void bfsPrint(ArrayDeque<Tree> deque) {
		if (!deque.isEmpty()) {
			Tree current = deque.removeFirst();

			System.out.println("Arbre: " + current.getValue() + " de profondeur: " + depth(current));

			for (Tree c : current) {
				deque.add(c);
			}

			bfsPrint(deque);
		}
	}

	// 2.1)
	static void printPathV1(Tree node) {
		if (node.getParent() != null) {
			printPathV1(node.getParent());
		}

		System.out.println(node.getValue());
	}

	// 2.2)
	static void printPathV2(Tree node) {
		while (node != null) {
			System.out.println(node.getValue());
			node = node.getParent();
		}
	}

	// 2.3
	static void printPathV3(Tree t, int v) {
		if (t.getValue() == v) {
		 printPathV1(t);
		 return;
		}

		for (Tree c : t) {
			printPathV3(c, v);
		}
	}

	// 3.1
	public static int[][] toArray(Tree t) {
		int[][] tab = new int[3][nbrNode(t)];

		return toArray(t, tab, 0);
	}

	private static int[][] toArray(Tree t, int[][] tab, int index) {
		tab[0][index] = t.getValue();

		if (t.isLeaf()) {
			tab[1][index] = -1;
			tab[2][index] = -1;
		} else if (t.getChildren().length == 1) {
			tab[1][index] = index + 1;
			tab[2][index] = -1;

			toArray(t.getChildren()[0], tab, index + 1);
		} else {
			tab[1][index] = index + 1;
			toArray(t.getChildren()[0], tab, index + 1);

			tab[2][index] = index + nbrNode(t.getChildren()[0]) + 1;
			toArray(t.getChildren()[1], tab, index + nbrNode(t.getChildren()[0]) + 1);
		}

		return tab;
	}

	// 3.2
	public static Tree toTree(int[][] t) {
		return null;
	}

	// 4
	public static Tree lca(Tree n1, Tree n2) {
		return null;
	}
}