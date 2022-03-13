package deque;

public class LinkedListDeque <ValueType> {
        public int count;
        public LinkedListDeque prevItem;
        public ValueType value;
        public LinkedListDeque nextItem;
        public LinkedListDeque ( ValueType T) {
            this.prevItem = this;
            this.value = T;
            this.nextItem = this;
        }
    public void addFirst(ValueType T) {
        // Adds the first item to the list continuously right after the sentinel node every time.
        LinkedListDeque add = new LinkedListDeque(T);
        if (count < 1) {
            this.nextItem = add;
            this.prevItem = add;
            add.prevItem = this;
            add.nextItem = this;
        } else {
            add.nextItem = this.nextItem;
            add.prevItem = this;
            this.nextItem.prevItem = add;
            this.nextItem = add;
        }
        count++;
    }
    public void addLast(ValueType T) {
        // Adds an item to the last spot i the list
        LinkedListDeque add = new LinkedListDeque(T);
        if (count < 1) {
            this.nextItem = add;
            this.prevItem = add;
            add.prevItem = this;
            add.nextItem = this;
        } else {
            // This changes from addFirst
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
        LinkedListDeque stepper = new LinkedListDeque(null);
        stepper.prevItem = null;
        stepper.nextItem = this.nextItem;
        while (stepper.nextItem != this) {
            System.out.println(stepper.nextItem.value);
            stepper.nextItem = stepper.nextItem.nextItem;
        }
    }
    public ValueType removeFirst() {
        if (count == 0) {
            return null;
        }
        ValueType firstValue = (ValueType) this.nextItem.value;
        this.nextItem = this.nextItem.nextItem;
        this.nextItem.prevItem.prevItem = null;
        this.nextItem.prevItem.nextItem = null;
        this.nextItem.prevItem = this;
        count --;
        return firstValue;
    }
    public ValueType removeLast() {
        if (count == 0) {
        return null;
    }
        LinkedListDeque stepper = new LinkedListDeque(null);
        stepper.prevItem = null;
        stepper.nextItem = this.prevItem.prevItem;
        ValueType lastValue = (ValueType) this.prevItem.value;
        this.prevItem.prevItem.nextItem = this;
        this.prevItem.prevItem = null;
        this.prevItem.nextItem = null;
        this.prevItem = stepper.nextItem;
        count --;
        stepper.nextItem = null;
        return lastValue;
    }
    public ValueType get(int index) {
        //TODO
        // Try and do it at least a little smartly so you're working backwards or forwards on the list
         if (count == 0) {
            return null;
        }
        // Forward implementation first
        LinkedListDeque stepper = new LinkedListDeque(null);
        stepper.prevItem = null;
        stepper.nextItem = this.nextItem;
        while (stepper.nextItem != this) {
            System.out.println(stepper.nextItem.value);
            stepper.nextItem = stepper.nextItem.nextItem;
        }
    }

    }
//    public Iterator<T> iterator() {
//        //TODO
//         //After lecture 11
//    }
//    public boolean equals(Object o) {
//        //TODO
//         //After lecture 11
//    }
    public static void main(String[] args) {
        LinkedListDeque<String> sentinel = new LinkedListDeque<> ("Dummy Value");
        sentinel.addFirst("item 1");
        System.out.println(sentinel.removeFirst());
        System.out.println(sentinel.removeFirst());
        sentinel.addFirst("item 1");
        sentinel.addFirst("item 2");
        sentinel.addFirst("item 3");
        sentinel.addLast("item 4");
        sentinel.printDeque();
        System.out.println("");
        System.out.println(sentinel.removeLast());
//        System.out.println(sentinel.removeFirst());
//        System.out.println("");
//        sentinel.printDeque();
//        System.out.println(sentinel.size());
    }
}
