public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        class Node {
            private String name;
            private Node firstChild;
            private Node nextSibling;

            Node(String n, Node d, Node r) {
                name = n;
                firstChild = d;
                nextSibling = r;
            }
        }

    }
}