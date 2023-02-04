import java.util.Objects;

public class MyLinkedList<E> {
    private Node<E> last;
    private Node<E> first;

    private int size;

    public MyLinkedList() {
        last = new Node<E>(null, first, null);
        first = new Node<E>(null, null, last);
    }
    public void add(E value){
        Node<E> current = last;
        current.setElement(value);
        last = new Node<E>(null, current, null);
        current.setNextElement(last);
        size++;
        if (size == 1){
            first = new Node<E>(null, null, current);
        }
    }


    public E get(int index) {
        Objects.checkIndex(index,size);
        Node<E> target = first.getNextElement();
        for(int i=0; i<index; i++){
            target = target.getNextElement();
        }
        return target.getElement();

    }



    public int size(){
        return size;
    }

    public void remove(int index) {
        Objects.checkIndex(index,size);
        E removedElement = get(index);
        if(index == 0) {
            Node<E> prevTarget = first;
            prevTarget.nextElement = prevTarget.nextElement.nextElement;
        }
        else {
            Node<E> prevTarget = getNext(index -1);
            Node<E> nextTarget = getNext(index);
            prevTarget.nextElement = prevTarget.nextElement.nextElement;
            nextTarget.prevElement = nextTarget.prevElement.prevElement;
        }
        size--;

    }

    public void clear(){
        Node<E> target = first.getNextElement();
        for(int i = 0; i < size; i++){
            target.getNextElement().setPrevElement(target.getPrevElement());
            target = target.getNextElement();
        }
        size = 0;
    }

    public Node<E> getNext(int index){
        if(index == 0) {
            return first.nextElement;
        }
        else {
            Node<E> current = first;
            for (int i=0; i<= index; i++) {
                current = current.nextElement;
            }
            return current;
        }

    }


    @Override
    public String toString(){
        Node<E> start = first;
        String result ="";
        for (int i =0; i<size; i++){
            start = start.getNextElement();
            result = result + (String) start.getElement();
            if(i != size -1){
                result = result + ",";
            }
        }
        return result;
    }

    private class Node<E>{
        E element;
        Node<E> nextElement;
        Node<E> prevElement;


        public Node(E element, Node<E> prevElement, Node<E> nextElement){
            this.element = element;
            this.prevElement = prevElement;
            this.nextElement = nextElement;
        }



        public E getElement() {
            return element;
        }
        public Node<E> getNextElement() {
            return nextElement;
        }
        public Node<E> getPrevElement() {
            return prevElement;
        }
        public void setElement(E element) {
            this.element = element;
        }
        public void setNextElement(Node<E> nextElement) {
            this.nextElement = nextElement;
        }
        public void setPrevElement(Node<E> prevElement) {
            this.prevElement = prevElement;
        }
    }


}
