import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
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

    public static void main(String[] args) {
        String s="Bonjour! Au revoir!";
        Map<Character, Integer> freq = computeFreq(s);
        Node root = buildTree(freq);
        Map<Character, String> code= buildCode(root);
        System.out.println(code);
    }

    // renvoie une map qui a comme clé les lettres de la chaine de
    // caractère donnée en paramètre et comme valeur la fréquence de
    // ces lettres
    public static Map<Character, Integer> computeFreq(String s) {
		Map<Character, Integer> count = new HashMap<>();

		for (Character currentChar : s.toCharArray()) {
			if (!count.containsKey(currentChar)) count.put(currentChar, 0);

            count.put(currentChar, count.get(currentChar) + 1);
		}

        return count;
    }

    // renvoie l'arbre de Huffman obtenu à partir de la map des fréquences des lettres
    public static Node buildTree(Map<Character, Integer> freq) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.freq));

        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            priorityQueue.add(new Node(entry.getKey(), entry.getValue(), null, null));
        }

        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            priorityQueue.add(new Node('\0', left.freq + right.freq, left, right));
        }

        return priorityQueue.poll();
    }

    // renvoie une map qui associe chaque lettre à son code (suite de 0 et de 1).
    // Ce code est obtenu en parcourant l'arbre de Huffman donné en paramètre
    public static Map<Character, String> buildCode(Node root) {
        Map<Character, String> map = new HashMap<>();
        buildCode(root, map, new StringBuilder());
        return map;
    }

    private static void buildCode(Node root, Map<Character, String> map, StringBuilder code) {
        if (root.isLeaf()) {
            map.put(root.ch, code.toString());
            return;
        }

        buildCode(root.left, map, code.append("0"));
        buildCode(root.right, map, code.append("1"));
    }

    // encode la chaine de caractère prise en paramètre en une chaine de
    // bit (0 et 1) en utilisant la map des codes codeMap
    public static String compress(String s, Map<Character, String> codeMap) {
        return null;
    }

    // Cette méthode décode une chaine de 0 et de 1 codé à l'aide de l'algorithme de Huffman
    // En paramètre, en plus de la chaine à décoder, est spécifié la racine de l'arbre de
    // Huffman
    public static String expand(Node root, String t) {
        return null;
    }
}
