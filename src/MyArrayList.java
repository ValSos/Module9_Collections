import java.util.Arrays;
import java.util.Objects;

public class MyArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public MyArrayList(){
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException();
        } else {
            elementData = new Object[initialCapacity];
        }
    }

    public void add(Object value){
        resize();
        elementData[size] = value;
        size++;
    }

    private void resize(){
        if(elementData.length == size) {
            Object[] bigElementData = new Object[size * 2];
            System.arraycopy(elementData, 0, bigElementData, 0, size);
            elementData = bigElementData;
        }
    }

    public Object get(int index){
        Objects.checkIndex(index,size);
        return (E) elementData[index];
    }

    public void clear(){
        size = 0;
        Object[] clearElementData = new Object[DEFAULT_CAPACITY];
        elementData = clearElementData;
    }


    public E remove(int index){
        Objects.checkIndex(index,size);
        E removedElement = (E) elementData[index];
        System.arraycopy(elementData, index+1, elementData, index, size - index -1);
        size--;
        return removedElement;
    }

    public int size(){
        return size;
    }

    @Override
    public String toString(){
        Object [] result = new Object[size];
        for (int i = 0; i < size; i++){
            if (elementData[i] != null){
                result[i] = elementData[i];
            }
        }
        return Arrays.toString(result);
    }


}
