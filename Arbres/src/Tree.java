import java.util.Arrays;
import java.util.Iterator;

public class Tree implements Iterable<Tree> {
	private int value;
	private Tree parent;
	private Tree[] children;

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

	public int getValue() {
		return value;
	}
	public Tree getParent() {
		return parent;
	}
	public Tree[] getChildren() {
		return children;
	}

	public Iterator<Tree> iterator() {
		return Arrays.asList(children).iterator();
	}

	public int nbrChildren() {
		return children.length;
	}

	public boolean isLeaf() {
		return children.length == 0;
	}
}
