import java.util.*;

public class BST {

    public NodePV root;
    public int size;

    public static void main(String[] args) {
	    BST tree = new BST();
	    System.out.println(tree.checkPV());
    }

	//Implemente
    public boolean checkPV() {
        return checkPV(node);
    }

	//Implemente a partir das propriedades básicas de uma PV. Utilize o método blackHeight para auxiliar.
	private boolean checkPV(NodePV node) {
      if(node == null) return true;
      if(node.vermelho){
        if((node.left != null && node.left.vermelho) || (node.right != null && node.right.vermelho){
          return false;
        }
      }

      int left = blackHeight(node.left);
      int right = blackHeight(node.right);

      if(left != right || left == -1){
        return false;
      }
      return checkPV(node.left) && checkPV(node.right);
    }

    //Implemente
	public int blackHeight(NodePV node) {
    if(node.left == null && node.right == null) return 0;
    int left = blackHeight(node.left);
    int right = blackHeight(node.right);

    if(left == -1 || right == -1){
      return -1;
    }

    if(left != right){
      return -1;
    }

    if(!node.vermelho){
      return left + 1;
    }

    returnt left;
        
	}

	public boolean isEmpty() {
	    return this.root == null;
	}
    
    /**
     * Implementação iterativa da adição de um elemento em uma árvore binária de pequisa.
     * @param element o valor a ser adicionado na árvore.
     */
    public void add(int element, boolean vermelho) {
        this.size += 1;
        if (isEmpty())
            this.root = new NodePV(element, vermelho);
        else {
            
            NodePV aux = this.root;
            
            while (aux != null) {
                
                if (element < aux.value) {
                    if (aux.left == null) { 
                        NodePV newNode = new NodePV(element, vermelho);
                        aux.left = newNode;
                        newNode.parent = aux;
                        return;
                    }
                    
                    aux = aux.left;
                } else {
                    if (aux.right == null) { 
                        NodePV newNode = new NodePV(element, vermelho);
                        aux.right = newNode;
                        newNode.parent = aux;
                        return;
                    }
                    
                    aux = aux.right;
                }
            }
        }
        
    }

  void fixUpInsert(NodePV node){
    fixUpCase1(node);
  }

  private void fixUpCase1(NodePV node){
    if(node.parent == null){
      node.vermelho = false;
    } else {
      fixUpCase2(node);
    }
  }

  private void fixUpCase2(NodePV node){
    if(!node.parent.vermelho){

    } else {
      fixUpCase3(node);
    }
  }

  private void fixUpCase3(NodePV node){
    NodePV tio = getUncle(node);

    if(tio != null && tio.vermelho){
      node.parent.vermelho = false;
      tio.vermelho = false;
      node.parent.parent = true;
      fixUpCase1(node.parent.parent);
    } else {
      fixUpCase4(node);
    }
  }

  private void fixUpCase4(NodePV node){
    NodePV painho = node.parent;
    NodePV vovo = node.parent.parent;
    NodePV next = node;

    if(node == painho.right && painho == vovo.left){
      rotateLeft(painho);
      next = node.left;
    } else if(node == painho.left && painho == vovo.right){
      rotateRight(painho);
      next = node.right;
    }
    fixUpCase5(node);
  }

  private void fixUpCase5(NodePV node){
    NodePV painho = node.parent;
    NodePV vovo = node.parent.parent;

    painho.vermelho = false;
	avo.vermelho = true;

	if(node == painho.left){
		rotateRight(vovo);
	} else {
		rotateLeft(vovo);
	}
  }

  
    
    
}



class NodePV {
    
    int value;
    NodePV left;
    NodePV right;
    NodePV parent;
    boolean vermelho;
    
    NodePV(int v, boolean vermelho) {
        this.value = v;
        this.vermelho = vermelho;
    }

    public boolean hasOnlyLeftChild() {
        return (this.left != null && this.right == null);
    }
    
    public boolean hasOnlyRightChild() {
        return (this.left == null && this.right != null);
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
    
}
