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
        return false;
    }

	//Implemente a partir das propriedades básicas de uma PV. Utilize o método blackHeight para auxiliar.
	private boolean checkPV(NodePV node) {
        return false;
    }

    //Implemente
	public int blackHeight(NodePV node) {
        return 0;
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
