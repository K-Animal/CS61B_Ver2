package deque;

public class ArrayDeque<ValueType> {
    public ValueType[] array;
    public int count;
    private int positionFirst = 0;
    private int positionLast = 0;
    private int arraySize = 0;

    public ArrayDeque() {
        array = (ValueType[]) new Object[8];
        count = 0;
        arraySize = 8;
    }
    public void addFirst(ValueType T) {
        if (array[positionFirst] != null) {
            if (positionFirst - 1 >= 0) {
                positionFirst --;
            } else {
                positionFirst = arraySize - 1;
            }
        }

        if (positionFirst - 1 >= -1) {
            array[positionFirst] = T;
            positionFirst --;
        } else {
            positionFirst = arraySize - 1;
            array[positionFirst] = T;
            positionFirst --;
        }
        count ++;
    }
    public void addLast(ValueType T) {
        if (positionLast == arraySize && array[positionFirst] == null) {
            positionLast = 0;
        }
        array[positionLast] = T;
        positionLast ++;
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
        // TODO BROKEN WHEN CYCLING BACK AROUND
        // TODO Maybe it would be nice to have a setter function that performs all the required checks
        if (array[positionFirst] != null) {
            if (positionLast == positionFirst) {
                positionLast ++;
            }
            ValueType temp = array[positionFirst];
            array[positionFirst] = null;
            positionFirst ++;
            return temp;
        }
        if (positionFirst + 1 < arraySize) {
            positionFirst ++;
            if (positionFirst + 1 > positionLast) {
                positionLast = positionFirst + 1;
            }
            ValueType temp = array[positionFirst];
            array[positionFirst] = null;
            return temp;
        } else {
            if (positionLast == 0) {
                positionLast = 1;
            }
            positionFirst = 0;
            ValueType temp = array[positionFirst];
            array[positionFirst] = null;
            return temp;
        }
    }
//    public ValueType removeLast() {
//
//    }
//    public ValueType get(int index) {
//
//    }
//
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

    public static void main(String[] args) {
        ArrayDeque arrays = new ArrayDeque();
        for (int i = 0; i < arrays.arraySize / 2; i++) {
        arrays.addLast(i);
        }
        for (int i = arrays.arraySize / 2; i < arrays.arraySize; i++) {
            arrays.addFirst(i);
        }
        for (int i = 0; i < arrays.arraySize; i++) {
            arrays.removeFirst();
        }
    }
}
