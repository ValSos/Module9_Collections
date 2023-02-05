public class MyQueue<T> {

    private Node<T> last;
    private Node<T> first;

    private int size;

    public MyQueue() {
        last = new Node<T>(null, first, null);
        first = new Node<T>(null, null, last);
    }

    public void add(T value){
        Node<T> current = last;
        current.setElement(value);
        last = new Node<T>(null, current, null);
        current.setNextElement(last);
        size++;
        if (size == 1){
            first = new Node<T>(null, null, current);
        }
    }

    public int size(){
        return size;
    }

    public void clear(){
        MyQueue<T> clearQueue = new MyQueue<>();
        this.first = clearQueue.first;
        this.last = clearQueue.last;
        size = 0;
    }

    public T peek(){
        return first.getNextElement().getElement();
    }

    public T poll(){
        T target = first.getNextElement().getElement();
        Node<T> targetNode = first.getNextElement();
        Node<T> nextTarget = targetNode.nextElement;
        first.setNextElement(nextTarget);
        size--;
        return target;
    }


    @Override
    public String toString(){
        Node<T> start = first;
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


    private class Node<T>{
        T element;
        Node<T> nextElement;
        Node<T> prevElement;


        public Node(T element, Node<T> prevElement, Node<T> nextElement){
            this.element = element;
            this.prevElement = prevElement;
            this.nextElement = nextElement;
        }



        public T getElement() {
            return element;
        }
        public Node<T> getNextElement() {
            return nextElement;
        }
        public Node<T> getPrevElement() {
            return prevElement;
        }
        public void setElement(T element) {
            this.element = element;
        }
        public void setNextElement(Node<T> nextElement) {
            this.nextElement = nextElement;
        }
        public void setPrevElement(Node<T> prevElement) {
            this.prevElement = prevElement;
        }
    }


}
