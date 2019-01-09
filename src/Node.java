/* from class lectures */
public class Node<E> {
    private E content;
    private E name;
    private Node<E> previous;
    private Node<E> next;

    public Node(E content, E name, Node<E> previous, Node<E> next){
        this.previous = previous;
        this.next = next;
        this.content = content;
        this.name = name;
    }

    public Node<E> getPrevious(){
        return previous;
    }

    public void setPrevious(Node<E> previous){
        this.previous = previous;
    }

    public Node<E> getNext(){
        return next;
    }

    public E getName(){
        return name;
    }

    public void setNext(Node<E> next){
        this.next = next;
    }

    public E getContent(){
        return content;
    }

    public void setContent(E content){
        this.content = content;
    }
}
