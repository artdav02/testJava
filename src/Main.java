// Write a Java method maxHeight() in class Node to calculate the height of the tree with root this. Height is defined as follows: height of the root is zero; height of a child is equal to height of its parent plus one; height of a tree is maximum of heights of all nodes in the tree. Do not forget to test the tree that consists of a single root node (height 0).
import java.util.*;

public class Node {

    private String name;
    private Node   firstChild;
    private Node   nextSibling;

    Node (String n, Node d, Node r) {
        setName (n);
        setFirstChild (d);
        setNextSibling (r);
    }

    public void   setName (String n)        { name = n; }
    public String getName()                 { return name; }
    public void   setFirstChild (Node d)    { firstChild = d; }
    public Node   getFirstChild()           { return firstChild; }
    public void   setNextSibling (Node r)   { nextSibling = r; }
    public Node   getNextSibling()          { return nextSibling; }

    @Override
    public String toString() {
        return leftParentheticRepresentation();
    }

    public String leftParentheticRepresentation() {
        StringBuffer b = new StringBuffer();
        b.append (getName());
        if (getFirstChild() != null) {
            b.append ("(");
            b.append (getFirstChild().leftParentheticRepresentation());
            Node right = getFirstChild().getNextSibling();
            while (right != null) {
                b.append (",");
                b.append (right.leftParentheticRepresentation());
                right = right.getNextSibling();
            }
            b.append (")");
        }
        return b.toString();
    }

    public static Node parseTree (String s) {
        if (s == null) return null;
        if (s.length() == 0) return null;
        Node root = null;
        Node curr = null;
        Node last = null;
        int state = 0; // begin
        Stack<Node> stk = new Stack<Node>();
        StringTokenizer tok = new StringTokenizer (s, "(),", true);
        while (tok.hasMoreTokens()) {
            String w = tok.nextToken().trim();
            if (w.equals ("(")) {
                state = 1; // from up
            } else if (w.equals (",")) {
                state = 2; // from left
            } else if (w.equals (")")) {
                state = 3; // from down
                stk.pop();
            } else {
                curr = new Node (w, null, null);
                switch (state) {
                    case 0: {
                        root = curr;
                        break;
                    }
                    case 1: {
                        last = stk.peek();
                        last.setFirstChild (curr);
                        break;
                    }
                    case 2: {
                        last = stk.pop();
                        last.setNextSibling (curr);
                        break;
                    }
                    default: {
                    }
                } // switch
                stk.push (curr);
            }
        } // next w
        return root;
    }

    public int maxHeight() {
        int maxChildHeight = 0;
        if (firstChild != null) {
            maxChildHeight = firstChild.maxHeight() + 1;
            Node sibling = firstChild.nextSibling;
            while (sibling != null) {
                maxChildHeight = Math.max(maxChildHeight, sibling.maxHeight() + 1);
                sibling = sibling.nextSibling;
            }
        }
        return maxChildHeight;
    }


    public static void main (String[] param) {
        Node v = Node.parseTree ("A(B,C(D,F(K,L,M,N(O)),P))");
        System.out.println (v);
        int n = v.maxHeight();
        System.out.println ("Maximum number of levels: " + n); // 4
        // TODO!!! Your tests here!
        Node singleNode = new Node("Z", null, null);
        System.out.println(singleNode);
        int singleNodeHeight = singleNode.maxHeight();
        System.out.println("Height of a single node: " + singleNodeHeight); // 0
    }
}
