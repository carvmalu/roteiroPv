import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;


public class BSTTest {

	@Test
    public void testEmptyTree() {
        BST tree = new BST();
        assertTrue(tree.checkPV(), "Árvore vazia deve ser PV válida");
    }

    @Test
    public void testSingleBlackNode() {
        BST tree = new BST();
        tree.add(10, false); // false = preto
        assertTrue(tree.checkPV(), "Árvore com um nó preto é PV válida");
    }

    @Test
    public void testSingleRedNode() {
        BST tree = new BST();
        tree.add(10, true); // true = vermelho
        assertFalse(tree.checkPV(), "Root vermelho não é PV válida");
    }

    @Test
    public void testValidRedBlackTree() {
        BST tree = new BST();
        tree.add(10, false); // root preta
        tree.add(5, true);
        tree.add(15, true);
        tree.add(1, false);
        tree.add(7, false);
        tree.add(12, false);
        tree.add(20, false);

        assertTrue(tree.checkPV());
    }

    @Test
    public void testConsecutiveRedNodes() {
        BST tree = new BST();
        tree.add(10, false); 
        tree.add(5, true);
        tree.add(1, true); 

        assertFalse(tree.checkPV());
    }



    @Test
    public void testUnequalBlackHeight() {
        BST tree = new BST();
        tree.add(10, false);
        tree.add(5, false);
        tree.add(15, false);
        tree.add(1, false); 

        int expected = 3;
        
        assertEquals(expected, tree.blackHeight(tree.root));

    }

    @Test
    public void testBalancedBlackHeight() {
        BST tree = new BST();
        tree.add(10, false);
        tree.add(5, true);
        tree.add(15, true);
        tree.add(1, false);
        tree.add(7, false);
        tree.add(12, false);
        tree.add(20, false);

        int expected = 2;
        assertEquals(expected, tree.blackHeight(tree.root));
    }

}

