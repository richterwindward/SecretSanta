/* mostly from class lectures */
import javax.print.URIException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.PrintWriter;

public class NodeList<E> implements Iterable<E> {
    private Node head;
    private int size;

    public NodeList() {
        this.head = new Node<E>(null, null, null, null);
        /* we cannot let the header's next and previous nodes be null or a NullPointerException will be thrown! */
        this.head.setNext(this.head);
        this.head.setPrevious(this.head);
        this.size = 0;
    }

    public NodeList(E[] array){
        for(E e : array){
            this.head.setPrevious(new Node<E>(e, null, this.head.getPrevious(), this.head));
            this.head.getPrevious().getPrevious().setNext(this.head.getPrevious());
        }
    }

    public E get(int index){
        if(index >= size || index < 0)
            return null;

        Node<E> current = this.head.getNext();

        for(int i = 0; i < index; i++){
            current = current.getNext();
        }

        return current.getContent();
    }

    public void addAfter(Node<E> node, E content, E name){
        Node<E> newNode = new Node<E>(content, name, node, node.getNext());
        node.getNext().setPrevious(newNode);
        node.setNext(newNode);
        this.size++;
    }

    public void addFirst(E content, E name){
        this.addAfter(this.head, content, name);
    }

    /* removes a node that contains the specified data */
    public void remove(E content){
        Node<E> currentNode = this.head.getNext();
        while(currentNode != this.head){
            if(currentNode.getContent() == content){
                currentNode.getPrevious().setNext(currentNode.getNext());
                currentNode.getNext().setPrevious(currentNode.getPrevious());
                currentNode.setNext(null);
                currentNode.setPrevious(null);
                this.size--;
                return;
            }
            currentNode = currentNode.getNext();
        }
    }

    /* removes a specific node, safer than removing based on content */
    public void remove(Node<E> node){
        Node<E> current = this.head.getNext();
        while(current != null){
            if(current == node){
                current.getPrevious().setNext(current.getNext());
                current.getNext().setPrevious(current.getPrevious());
                current.setNext(null);
                current.setPrevious(null);
                this.size--;
                return;
            }

            current = current.getNext();
        }
    }

    public void pairPeople() throws IOException, URISyntaxException {
        NodeList<E> shuffledList = new NodeList<E>();

        /* iterate until list is empty */
        while(this.size > 0){
            int selectedNode = (int) (Math.random() * this.size);
            Node<E> current = this.head.getNext();

            for(int i = 0; i < selectedNode; i++){
                current = current.getNext();
            }

            this.remove(current);

            shuffledList.addFirst(current.getContent(), current.getName());
        }

        /* overwrite self's properties with that of new list */
        this.head = shuffledList.head;
        this.size = shuffledList.size;

        /* https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it-in-java */

        PrintWriter writer = new PrintWriter("./assignments.txt", "UTF-8");
        Node<E> current = this.head.getNext();

        for(int i = 0; i < this.size - 1; i++){
            writer.println(current.getName() + " -> " + current.getNext().getName());
            /* StringBuilder is more efficient than string concatenation using + operator */
            /* there will be '+'s instead of a space in the email because of URI encoding standards. sorry. */
            StringBuilder msg = new StringBuilder();
            msg.append("Your assignment: ");
            msg.append(current.getNext().getName());
            msg.append(":");
            msg.append(current.getNext().getContent());
            Mailer.mail((String)current.getContent(), "Secret Santa Assignment!",msg.toString());
            current = current.getNext();
        }

        /* handle the odd person out */
        writer.println(current.getName() + " -> " + this.head.getNext().getName());

        StringBuilder msg = new StringBuilder();
        msg.append("Your assignment: ");
        msg.append(this.head.getNext().getName());
        msg.append(":");
        msg.append(this.head.getNext().getContent());
        Mailer.mail((String)current.getContent(), "Secret Santa Assignment!", msg.toString());
        writer.close();
    }

    public int size() {
        return this.size;
    }

    public Iterator<E> iterator() {
        return new NLIterator(this.head);
    }

    private class NLIterator implements Iterator<E> {
        private Node<E> next;

        public NLIterator(Node<E> head) {
            this.next = head.getNext();
        }

        @Override
        public boolean hasNext() {
            return next != head || next != null;
        }

        @Override
        public E next() throws NoSuchElementException {
            if(next == null || next == head) {
                throw new NoSuchElementException();
            }

            E content = next.getContent();

            next = next.getNext();

            return content;
        }
    }
}
