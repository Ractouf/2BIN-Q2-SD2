import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Huffman {
  private static class Node {
    private char ch;
    private int freq;
    private final Node left, right;

    public Node(char ch, int freq, Node left, Node right) {
      this.ch = ch;
      this.freq = freq;
      this.left = left;
      this.right = right;
    }
    public boolean isLeaf() {
      return left == null && right == null;
    }
  }

  public static void main(String[] args) throws IOException {
    String s=HuffmanReadFile.loadFile(new File("11-0.txt"));
    Map<Character, Integer> freq = computeFreq(s);
    Node root = buildTree(freq);
    Map<Character, String> code= buildCode(root);
    String compress = compress(s, code);
    HuffmanWriteFile.write("fichier_compresse",compress);
    String texteOriginal =
        expand(root,HuffmanReadFile.read("fichier_compresse"));
    System.out.println(texteOriginal);
  }

  // renvoie une map qui a comme cl� les lettres de la chaine de
  // caract�re donn�e en param�tre et comme valeur la fr�quence de
  // ces lettres
  public static Map<Character, Integer> computeFreq(String s) {
    Map<Character, Integer> count = new HashMap<>();

    for (Character currentChar : s.toCharArray()) {
      if (!count.containsKey(currentChar))
        count.put(currentChar, 0);

      count.put(currentChar, count.get(currentChar) + 1);
    }

    return count;
  }

  // renvoie l'arbre de Huffman obtenu � partir de la map des fr�quences des lettres
  public static Node buildTree(Map<Character, Integer> freq) {
    PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparing(a -> a.freq));

    for (Entry<Character, Integer> entry : freq.entrySet()) {
      queue.add(new Node(entry.getKey(), entry.getValue(), null, null));
    }

    while (queue.size() > 1) {
      Node left = queue.poll();
      Node right = queue.poll();

      queue.add(new Node('\0', left.freq + right.freq, left, right));
    }

    return queue.poll();
  }

  // renvoie une map qui associe chaque lettre � son code (suite de 0 et de 1).
  // Ce code est obtenu en parcourant l'arbre de Huffman donn� en param�tre
  public static Map<Character, String> buildCode(Node root) {
    Map<Character, String> map = new HashMap<>();

    buildCode(root, map, "");

    return map;
  }

  private static void buildCode(Node root, Map<Character, String> map, String code) {
    if (root.isLeaf()) {
      map.put(root.ch, code);
    } else {
      buildCode(root.left, map, code + '0');
      buildCode(root.right, map, code + '1');
    }
  }

  // encode la chaine de caract�re prise en param�tre en une chaine de
  // bit (0 et 1) en utilisant la map des codes codeMap
  public static String compress(String s, Map<Character, String> codeMap) {
    StringBuilder compressed = new StringBuilder();

    for (Character c : s.toCharArray()) {
      compressed.append(codeMap.get(c));
    }

    return String.valueOf(compressed);
  }

  // Cette m�thode d�code une chaine de 0 et de 1 cod� � l'aide de l'algorithme de Huffman
  // En param�tre, en plus de la chaine � d�coder, est sp�cifi� la racine de l'arbre de
  // Huffman
  public static String expand(Node root, String t) {
    StringBuilder decode = new StringBuilder();

    Node current = root;
    int index = 0;
    int nbChar = 0;

    while (nbChar < root.freq) {
      if (t.charAt(index) == '0') {
        current = current.left;
      } else {
        current = current.right;
      }

      if (current.isLeaf()) {
        decode.append(current.ch);
        current = root;

        nbChar++;
      }

      index ++;
    }

    return String.valueOf(decode);
  }
}
