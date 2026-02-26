import java.util.*;

public class Pv {

    public NodePV root;
    public int size;

    public static void main(String[] args) {
	    BST tree = new BST();
	    System.out.println(tree.checkPV());
    }

	//Implemente
    public boolean checkPV() {
        return checkPV(this.root);
    }

	//Implemente a partir das propriedades básicas de uma PV. Utilize o método blackHeight para auxiliar.
	private boolean checkPV(NodePV node) {
        if(node == null) return true;

        if(node.vermelho){
            if((node.left != null && node.left.vermelho) || (node.right != null && node.right.vermelho)){
                return false;
            }
        }
        int left = blackHeight(node.left);
        int right = blackHeight(node.right);

        if(left != right){
            return false;
        }
        return checkPV(node.left) && checkPV(node.right);
    }

    //Implemente
	public int blackHeight(NodePV node) {
        if(node == null) return 0;


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

        return left;

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
            node.vermelho = false; // raiz tem q ser preta
        } else  {
            fixUpCase2(node);
        }
    }

    private void fixUpCase2(NodePV node){
        if(!node.parent.vermelho){
            // tá tudo certo não acontece nada
        } else {
            fixUpCase3(node);
        }
    }

    private void fixUpCase3(NodePV node){
        NodePV uncle = getUncle(node);

        if(uncle != null && uncle.vermelho){
            node.parent.vermelho = false; // pai vira preto
            uncle.vermelho = false; // tio vira preto
            node.parent.parent.vermelho = true; // avo vira vermelho
            fixUpCase1(node.parent.parent);
        } else {
            fixUpCase4(node);
        }
    }

    private void fixUpCase4(NodePV node){
        NodePV parent = node.parent;
        NodePV vovo = node.parent.parent;
        NodePV next = node;

        // nó é filho direito e pai é filho esquerdo -> rotacao a esquerda no pai
        if(node == parent.right && parent == vovo.left){
            rotateLeft(parent);
            next = node.left;
        // nó é filho esquerdo e pai é filho direito -> rotacao a direita do pai 
        } else if(node == parent.left && parent == vovo.right){
            rotateRight(parent);
            next = node.right;
        }

        fixUpCase5(node);
    }

    private void fixUpCase5(NodePV node){
        NodePV painho = node.parent;
        NodePV vovo = painho.parent;

        painho.vermelho = false; // pai vira preto
        vovo.vermelho = true; // avo vira vermelho

        // nó é filho esquerdp -> rotadacao á direita do nó
        if(node == painho.left){
            rotateRight(vovo);
        } else {
            rotateLeft(vovo);
        }
    }

    private NodePV getUncle(NodePV node){
        NodePV vovo = node.parent.parent;
        if(vovo == null) return null;

        if(node.parent == vovo.left){
            return vovo.right; // tio é filho direito
        } else {
            return vovo.left; // tio é filho esquerdo
        }
    }

    private void rotateLeft(NodePV node){
        NodePV newRoot = node.right;
        newRoot.parent = node.parent;

        node.right = newRoot.left;
        if(node.right != null){
            node.right.parent = node;
        }

        newRoot.left = node;
        node.parent = newRoot;
        if(newRoot.parent != null){
            if(newRoot.parent.right == node){
                newRoot.parent.right = newRoot;
            } else {
                newRoot.parent.left = newRoot;
            }
        } else {
            this.root = newRoot;
        }
    }

    private void rotateRight(NodePV node){
        NodePV newRoot = node.left;
        newRoot.parent = node.parent;

        node.left = newRoot.right;
        if(node.left != null){
            node.left.parent = node;
        }

        newRoot.right = node;
        node.parent = newRoot;
        if(newRoot.parent != null){
            if(newRoot.parent.left == node){
                newRoot.parent.left = newRoot;
            } else {
                newRoot.parent.right = newRoot;
            }
        } else {
            this.root = newRoot;
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

