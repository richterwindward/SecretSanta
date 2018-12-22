import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PersonList implements Iterable<Object> {
    private PersonNode head;
    private int size;

    public PersonList() {
        this.head = null;
        this.size = 0;
    }

    public void add(PersonNode p) {
        this.size++;

        if(this.head == null) {
            this.head = p;
            p.setNext(null);
            return;
        }

        PersonNode current = this.head;

        while(current.getNext() != null)
            current = current.getNext();

        current.setNext(p);
    }

    public PersonNode get(int at) {
        PersonNode current = this.head;

        for (int i = 0; i < at; i++) {
            current = current.getNext();
        }

        return current;
    }

    public void remove(int at) {
        size--;

        if(at == 0) {
            this.head.getNext().setPrevious(null);
            this.head = this.head.getNext();
            return;
        }

        PersonNode current = this.head;
        for (int i = 0; i < at; i++) {
            current = current.getNext();
        }

        current.setNext(current.getNext());
        current.getNext().setPrevious(current.getPrevious());
    }

    public String pollAndRemove(int at) {
        size--;

        if(at == 0) {
            String ret = this.head.toString();
            this.head.getNext().setPrevious(null);
            this.head = this.head.getNext();
            return ret;
        }

        PersonNode current = this.head;
        for (int i = 0; i < at; i++) {
            current = current.getNext();
        }

        current.setNext(current.getNext());
        current.getNext().setPrevious(current.getPrevious());

        return current.toString();
    }

    public void shuffle() {
        for(int i = 0; i < this.size; i++) {
            this.add(new PersonNode(this.pollAndRemove((int) (Math.random() * (this.size - i)))));
        }
    }

    public void debug() {
        StringBuilder str = new StringBuilder("{");

        for(PersonNode current = this.head; current != null; current = current.getNext()) {
            str.append(current.getName());
            str.append(":");
            str.append(current.getEmail());
            str.append(" ");
        }

        System.out.println(str.append("}").toString());
    }

    public Iterator<Object> iterator() {
        return new PersonList.PLIterator(this.head);
    }

    private class PLIterator implements Iterator<Object> {
        private PersonNode next;

        public PLIterator(PersonNode head) {
            this.next = head;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Object next() throws NoSuchElementException {
            if(next == null) {
                throw new NoSuchElementException();
            }

            String stringRepresentation = next.toString();

            next = next.getNext();

            return stringRepresentation;
        }
    }
}
