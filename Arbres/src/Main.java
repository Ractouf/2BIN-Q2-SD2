public class Main {
  public static void main(String[] args){
    Tree l1 = new Tree(7);
    Tree l3 = new Tree(3);
    Tree l5 = new Tree(5);
    Tree l7 = new Tree(1);

    Tree t2 = new Tree(2, new Tree[]{l1, l3});
    Tree t6 = new Tree(6, new Tree[]{l7});

    Tree t4 = new Tree(4, new Tree[]{t2, l5, t6});

    Tree l1_2 = new Tree(7);
    Tree l3_2 = new Tree(3);
    Tree l7_2 = new Tree(1);

    Tree t2_2 = new Tree(2, new Tree[]{l1_2, l3_2});
    Tree t6_2 = new Tree(6, new Tree[]{l7_2});

    Tree t4_2 = new Tree(4, new Tree[]{t2_2, t6_2});

    System.out.println(Trees.nbrLeaves(t4));


    Tree[] ls = Trees.flattenLeaves(t4);
    System.out.println("Les " + ls.length + " feuilles");
    int i = 0;
    while(i != ls.length) {
      System.out.println(ls[i].getValue());
      i++;
    }

    System.out.println("Path V1");
    Trees.pathV1(l7);

    System.out.println("Path V2");
    Trees.pathV2(l7);

    System.out.println("1.1");
    System.out.println(Trees.nbrNode(t4));

    System.out.println("1.2");
    System.out.println(Trees.min(t4));

    System.out.println("1.3");
    System.out.println(Trees.sum(t4));

    System.out.println("1.4");
    System.out.println("t4 == t2 " + Trees.equals(t4, t2));
    System.out.println("t4 == t4 " + Trees.equals(t4, t4));

    System.out.println("1.5");
    System.out.println(Trees.depth(l7));

    System.out.println("1.6");
    System.out.println("l7 == l7 " + Trees.sameOne(l7, l7));
    System.out.println("t4 == t6_2 " + Trees.sameOne(t4, t6_2));

    System.out.println("1.7");
    Trees.dfsPrint(t4);

    System.out.println("1.8");
    Trees.bfsPrint(t4);

    System.out.println("2.1");
    Trees.printPathV1(l7);

    System.out.println("2.2");
    Trees.printPathV2(l7);

    System.out.println("2.2");
    Trees.printPathV3(t4, 7);

    System.out.println("3.1");
    int[][] tab = Trees.toArray(t4_2);
    for (int j = 0; j < tab.length; j++) {
      for (int k = 0; k < tab[j].length; k++) {
        System.out.print("\t " + tab[j][k]);
      }
      System.out.println();
    }

  }
}
