package deque;

public class ArrayDeque<ValueType> {
    public ValueType[] array;
    public int count;
    private int positionFirst = 0;
    private int positionLast = 0;
    private int arraySize;

    public ArrayDeque() {
        array = (ValueType[]) new Object[8];
        count = 0;
        arraySize = 8;
    }
    public void addFirst(ValueType T) {
        if (array[positionFirst] != null) {
            positionFirst --;
            positionFirst = doesLoopBack(positionFirst);
        }
        array[positionFirst] = T;
        count ++;
    }
    public void addLast(ValueType T) {
        if (array[positionLast] != null) {
            positionLast ++;
        }
        positionLast = doesLoopBack(positionLast);
        array[positionLast] = T;
        count ++;
    }
    public boolean isEmpty() {
        if (count > 0) {
            return false;
        }
        return true;
    }
    public int size() {
        return count;
    }
    public ValueType removeFirst() {
        if (isEmpty() == true) {
            return null;
        }
        ValueType temp = array[positionFirst];
        array[positionFirst] = null;
        if (positionFirst == positionLast) {
            positionLast++;
            positionLast= doesLoopBack(positionLast);
        }
        positionFirst ++;
        positionFirst = doesLoopBack(positionFirst);
        count --;
        return temp;
    }
    public ValueType removeLast() {
        if (isEmpty() == true) {
            return null;
        }
        ValueType temp = array[positionLast];
        array[positionLast] = null;
        if (positionLast == positionFirst) {
            positionFirst--;
            positionFirst= doesLoopBack(positionFirst);
        }
        positionLast --;
        positionLast = doesLoopBack(positionLast);
        count --;
        return temp;
    }
    private int doesLoopBack(int position) {
        if (position > arraySize - 1) {
            position = 0;
            return position;
        }
        if (position < 0) {
            position = arraySize - 1;
            return position;
        }
        return position;
    }
    public ValueType get(int index) {
        ValueType temp = array[index];
        return temp;
    }
//    private void createSmallerArray() {
//
//    }
//    private void createLargerArray() {
//
//    }
//    public Iterator<T> iterator() {
//        //TODO
//         //After lecture 11
//    }
//    public boolean equals(Object o) {
//        //TODO
//         //After lecture 11
//    }
//    public static void main(String[] args) {
//        ArrayDeque arrays = new ArrayDeque();
//        for (int i = 0; i < arrays.arraySize / 2 ; i++) {
//        arrays.addLast(i);
//        }
//        for (int i = arrays.arraySize / 2; i < arrays.arraySize; i++) {
//            arrays.addFirst(i);
//        }
//        for (int i = 0; i < arrays.arraySize; i++) {
//            arrays.removeFirst();
//        }
//    }
}
