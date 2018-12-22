public class PersonNode {
    private String name;
    private String email;

    private PersonNode next;
    private PersonNode previous;

    public PersonNode(String name, String email) {
        this.name = name;
        this.email = email;
        this.next = null;
        this.previous = null;
    }

    public PersonNode(String strRepresentation) {
        this.name = strRepresentation.split(":")[0];
        this.email = strRepresentation.split(":")[1];
        this.next = null;
        this.previous = null;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public PersonNode getNext() {
        return this.next;
    }

    public void setNext(PersonNode next) {
        this.next = next;
    }

    public PersonNode getPrevious() {
        return this.previous;
    }

    public void setPrevious(PersonNode previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(this.getName()).append(':').append(this.getEmail()).toString();
    }
}
