import java.util.Objects;

public class MyStack <K>{
    private Node<K> last;
    private Node<K> first;
    private int size;

    public MyStack() {
        last = new Node<K>(null, first, null);
        first = new Node<K>(null, null, last);
    }

    public void push(K value){
        Node<K> current = last;
        current.setElement(value);
        last = new Node<K>(null, current, null);
        current.setNextElement(last);
        size++;
        if (size == 1){
            first = new Node<K>(null, null, current);
        }
    }

    public void clear(){
        MyStack<K> clearStack = new MyStack<>();
        this.first = clearStack.first;
        this.last = clearStack.last;
        size = 0;
    }

    public K peek(){
        return last.getPrevElement().getElement();
    }

    public K pop(){
        K target = last.getPrevElement().getElement();
        Node<K> targetNode = last.getPrevElement();
        Node<K> prevTarget = targetNode.getPrevElement();
        last.setPrevElement(prevTarget);
        size--;
        return target;
    }

    public K get(int index) {
        Objects.checkIndex(index,size);
        Node<K> target = first.getNextElement();
        for(int i=0; i<index; i++){
            target = target.getNextElement();
        }
        return target.getElement();

    }

    public Node<K> getNext(int index){
        if(index == 0) {
            return first.nextElement;
        }
        else {
            Node<K> current = first;
            for (int i=0; i<= index; i++) {
                current = current.nextElement;
            }
            return current;
        }

    }

    public void remove(int index){
        Objects.checkIndex(index,size);
        K removedElement = get(index);

        if(index == 0) {
            Node<K> prevTarget = first;
            prevTarget.nextElement = prevTarget.nextElement.nextElement;
        }
        else {
            Node<K> prevTarget = getNext(index -1);
            Node<K> nextTarget = getNext(index);
            prevTarget.nextElement = prevTarget.nextElement.nextElement;
            nextTarget.prevElement = nextTarget.prevElement.prevElement;
        }

        size--;
    }

    public int size(){
        return size;
    }

    @Override
    public String toString(){
        Node<K> start = first;
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

    private class Node<K>{
        K element;
        Node<K> nextElement;
        Node<K> prevElement;


        public Node(K element, Node<K> prevElement, Node<K> nextElement){
            this.element = element;
            this.prevElement = prevElement;
            this.nextElement = nextElement;
        }


        public K getElement() {
            return element;
        }
        public Node<K> getNextElement() {
            return nextElement;
        }
        public Node<K> getPrevElement() {
            return prevElement;
        }
        public void setElement(K element) {
            this.element = element;
        }
        public void setNextElement(Node<K> nextElement) {
            this.nextElement = nextElement;
        }
        public void setPrevElement(Node<K> prevElement) {
            this.prevElement = prevElement;
        }
    }


}
