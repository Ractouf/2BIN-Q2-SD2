// Cette classe repr�sente un joueur automatique.
// Elle maintient � jour un arbre des meilleurs coups.
public class AutomaticPlayer extends SimpleSpectator implements Player {

  // N�ud courant de jeu. C'est le n�ud qui correspond � l'�tat courant du jeu.
  private Tree currentNode;

  /* En plus du contrat de Spectator, cette m�thode :
   1) Initialise l'arbre du jeu, et
   2) calcule les valeurs Minimax pour chaque n�ud de l'arbre.*/
  @Override
  public void start(State state) {
    currentNode = new Tree(state);

    this.currentNode.computeMinimaxValues();
  }

  /* En plus du contrat de Spectator, cette m�thode maintient currentNode,
   c.-�-d. le n�ud qui correspond � l'�tat courant du jeu.*/
  @Override
  public void play(boolean isLeftMove, State state) {
    super.play(isLeftMove, state);

    if (isLeftMove) {
      currentNode = currentNode.getLeftChild();
    } else {
      currentNode = currentNode.getRightChild();
    }
  }

  /* Cette m�thode est appel�e pour connaitre le coup de ce joueur :
   1) Elle renvoie vrai si ce joueur joue la barre de gauche, et
   2) Elle renvoie faux si ce joueur joue la barre de droite.*/
  @Override
  public boolean nextPlay() {
    return currentNode.getMinimaxValue().isLeftMove();
  }
}
