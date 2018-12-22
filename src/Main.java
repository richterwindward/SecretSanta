public class Main {

    public static void main(String[] args) {
        PersonList list = new PersonList();
        list.add(new PersonNode("Richter", "richter@hotmail.com"));
        list.add(new PersonNode("Jack", "jack@gmail.com"));
        for (Object p:
             list) {
            System.out.println(((String)p));
        }
    }
}
