
public class MyHashMap<K,V> {
    private Node<K,V> [] table;
    private int size;
    private int threshold;
    private int capacity;
    static final int DEFAULT_CAPACITY =16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    public class Node<K,V> {
        private int h;
        private K key;
        private V value;
        private Node<K,V> next;

        public int hash(K key) {
            int h;
            if (key == null) return 0;
            else if (key.hashCode() > capacity -1 || key.hashCode() < 0) {
                h = Math.abs(key.hashCode() % capacity);
            }
            else {
                h = key.hashCode();
            }
            return h;
        }

        public final boolean hasNext() {
            return next != null;
        }


        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.h = hash(key);
            this.next = null;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        public void setKey(K key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return  key +
                    " : " + value;
        }
    }



    public V get(K key){
        Node<K,V> currentElement = table[hash(key)];
        int curBucketSize = bucketSize(currentElement);
        if(currentElement.key.equals(key)) {
            return table[hash(key)].value;
        }
        else {
            currentElement = table[hash(key)].next;
            for (int i = 1; i <= curBucketSize; i++) {
                if (currentElement.key.equals(key)){
                    return currentElement.value;
                }
                else {
                    currentElement = currentElement.next;
                }
            }
        }
        return null;
    }

    public V remove(K key){
        V needElement = get(key);
        Node<K,V> firstElement = table[hash(key)];
        int curBucketSize = bucketSize(firstElement);
        if(firstElement.key.equals(key)) {
            firstElement.setKey(null);
            firstElement.setValue(null);
            if (firstElement.hasNext()){
                table[hash(key)] = firstElement.next;
            }
        }
        else {
            Node<K,V> currentElement = table[hash(key)].next;
            Node<K,V> prevElement = firstElement;
            for (int i = 1; i < curBucketSize; i++) {
                if (currentElement.key.equals(key)){
                    currentElement.setKey(null);
                    currentElement.setValue(null);
                    firstElement.setNext(currentElement.next);
                    return needElement;
                }
                else {
                    prevElement = prevElement.next;
                    currentElement = currentElement.next;
                }
            }
        }

        return needElement;
    }

    public int hash(K key) {
        int h;
        if (key == null) return 0;
        else if (key.hashCode() > capacity -1 || key.hashCode() < 0) {
            h = Math.abs(key.hashCode() % capacity);
        }
        else {
            h = key.hashCode();
        }
        return h;
    }

    public MyHashMap(){
        this.table = new Node[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
    }

    public MyHashMap(int capacity){
        this.table = new Node[capacity];
        this.capacity = capacity;
    }

    public int calculateTreshold(){
        return (int) (this.capacity * DEFAULT_LOAD_FACTOR);
    }


    public void put(K key, V value) {
        Node<K,V> current = new Node<>(key,value);
        if(size++ >= calculateTreshold()){
            resize();
        }
        if (table[current.h] == null) {
            table[current.h] = current;
            current.next = null;
        }
        else if (table[current.h].key.equals(key)){
            table[current.h].value = value;
        }
        else {
            Node <K,V> checkedElement = table[current.h];
            for (int i = 1; i <
                    bucketSize(table[current.h]); i++){
                checkedElement  = checkedElement.next;
            }
//            while (checkedElement.hasNext()){
//                checkedElement  = checkedElement.next;
//            }
            checkedElement.next = current;
            current.next = null;
            }
        size++;
    }

    public void clear(){
        this.table = new Node[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public void resize(){
        int newCapacity = 2 * table.length;
        MyHashMap<K,V> newHashMap = new MyHashMap<>(newCapacity);
        for (Node<K,V> elements:this.table) {
            if (elements != null){
               newHashMap.put(elements.key, elements.value);
                Node<K,V> currentElement = elements;
                while (currentElement.hasNext()){
                    newHashMap.put(currentElement.next.key, currentElement.next.value);
                    currentElement = currentElement.next;
                }
            }
        }
        this.table = newHashMap.table;
        this.capacity = newHashMap.capacity;
    }


    public int bucketSize(Node<K,V> firstNode){
        int result = 0;
        Node<K,V> currentNode = firstNode;
        if (firstNode == null) {
            return result;
        }
        if (firstNode != null) {
            do {
                result += 1;
                currentNode = currentNode.next;
            }
            while (currentNode != null);
        }
        return result;
    }



    @Override
    public String toString() {
        if(this.size > 0) {
            String result = "{";
            for (Node<K,V> elements:table) {
                if (elements != null) {
                    result = result + elements.toString() + "; ";
                    Node<K,V> currentElement = elements;
                    while (currentElement.hasNext()) {
                        result = result + currentElement.next.toString() + "; ";
                        currentElement = currentElement.next;
                    }
                }
            }
            return result.substring(0,result.length()-2) + "}";
        }

        return "{}";

    }
}
