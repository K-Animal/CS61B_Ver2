package deque;

public class LinkedListDeque<T> {
        private LinkedListDeque prevItem;
        private T value;
        private LinkedListDeque nextItem;
    public LinkedListDeque() {
        this.prevItem = this;
        this.value = null;
        this.nextItem = this;
    }
    private int count;

    public void addFirst(T T) {
        // Adds the first item to the list continuously right after the sentinel node every time.
        LinkedListDeque add = new LinkedListDeque();
        if (count < 1) {
            this.nextItem = add;
            this.prevItem = add;
            add.value = T;
            add.prevItem = this;
            add.nextItem = this;
        } else {
            add.value = T;
            add.nextItem = this.nextItem;
            add.prevItem = this;
            this.nextItem.prevItem = add;
            this.nextItem = add;
        }
        count++;
    }
    public void addLast(T T) {
        // Adds an item to the last spot i the list
        LinkedListDeque add = new LinkedListDeque();
        if (count < 1) {
            this.nextItem = add;
            this.prevItem = add;
            add.value = T;
            add.prevItem = this;
            add.nextItem = this;
        } else {
            // This changes from addFirst
            add.value = T;
            add.nextItem = this;
            add.prevItem = this.prevItem;
            this.prevItem.nextItem = add;
            this.prevItem = add;
        }
        count++;
    }
    public boolean isEmpty() {
        if (count == 0) {
            return true;
    }
        return false;
    }
    public int size() {
        return count;
    }
    public void printDeque() {
        // Print out the Deque from the first item to the last
        LinkedListDeque stepper = new LinkedListDeque();
        stepper.prevItem = null;
        stepper.nextItem = this.nextItem;
        while (stepper.nextItem != this) {
            System.out.println(stepper.nextItem.value);
            stepper.nextItem = stepper.nextItem.nextItem;
        }
    }
    public T removeFirst() {
        if (count == 0) {
            return null;
        }
        T firstValue = (T) this.nextItem.value;
        this.nextItem = this.nextItem.nextItem;
        this.nextItem.prevItem.prevItem = null;
        this.nextItem.prevItem.nextItem = null;
        this.nextItem.prevItem = this;
        count --;
        return firstValue;
    }
    public T removeLast() {
        if (count == 0) {
        return null;
    }
        LinkedListDeque stepper = new LinkedListDeque();
        stepper.prevItem = null;
        stepper.nextItem = this.prevItem.prevItem;
        T lastValue = (T) this.prevItem.value;
        this.prevItem.prevItem.nextItem = this;
        this.prevItem.prevItem = null;
        this.prevItem.nextItem = null;
        this.prevItem = stepper.nextItem;
        count --;
        stepper.nextItem = null;
        return lastValue;
    }
    public T get(int index) {
         if (count == 0 || index == 0 || index > count) {
            return null;
        }
         if (index <= count / 2) {
            // Forward implementation
            int n = 1;
            LinkedListDeque stepper = new LinkedListDeque();
            stepper.prevItem = null;
            stepper.nextItem = this.nextItem;
            while (n < index) {
                stepper.nextItem = stepper.nextItem.nextItem;
                n++;
            }
            return (T) stepper.nextItem.value;
        }
        // Backward implementation
        int n = 1;
        LinkedListDeque stepper = new LinkedListDeque();
        stepper.nextItem = null;
        stepper.prevItem = this.prevItem;
        while (n <= count - index) {
            stepper.prevItem = stepper.prevItem.prevItem;
            n++;
        }
        return (T) stepper.prevItem.value;
    }
    public T getRecursive(int index) {
        if (this.value == null){
            return null;
        }
        if (index == 0) {
            return this.value;
        }
        return getRecursive(index - 1);
    }

//    public Iterator<T> iterator() {
//        //TODO
//         //After lecture 11
//    }
//    public boolean equals(Object o) {
//        //TODO
//         //After lecture 11
//    }

//    public static void main(String[] args){
//        LinkedListDeque Sentinel = new LinkedListDeque("bob");
//        Sentinel.addFirst("Tom");
//
//    }
}
