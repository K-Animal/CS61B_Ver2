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
        //TODO
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
/*    public void addLast(int T) {
        //TODO
    }
    public boolean isEmpty() {
        //TODO
    }
    public int size() {
        //TODO
    }
    public void printDeque() {
        //TODO
    }
    public T removeFirst() {
        //TODO
    }
    public T removeLast() {
        //TODO
    }
    public T get(int index) {
        //TODO
    }
    public Iterator<T> iterator() {
        //TODO
        *//* After lecture 11*//*
    }
    public boolean equals(Object o) {
        //TODO
        *//* After lecture 11*//*
    }*/
    public static void main(String[] args) {
        LinkedListDeque<String> sentinel = new LinkedListDeque<> ("Dummy Value");
        sentinel.addFirst("item 1");
        sentinel.addFirst("item 2");
        sentinel.addFirst("item 3");
        System.out.println(sentinel.value);
    }
}
