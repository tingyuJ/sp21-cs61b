package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
        size = 0;
    }

    public ArrayDeque(ArrayDeque other){
        items = (T[]) new Object[other.items.length];
        System.arraycopy(other.items,0, items, 0, other.items.length);
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;
    }

    public void addFirst(T item){
        items[nextFirst] = item;
        nextFirst--;
        size++;
        if(nextFirst == -1){ // reaches the start of the array
            nextFirst = items.length-1;
        }
        if(nextFirst == nextLast){
            //resize items
            System.out.println("resize");
            items = resize(items);
        }
    }

    public void addLast(T item){
        items[nextLast] = item;
        nextLast++;
        size++;
        if(nextLast == items.length){ // reaches the end of the array
            nextLast = 0;
        }
        if(nextLast == nextFirst){
            //resize items
            System.out.println("resize");
            items = resize(items);
        }
    }

    private T[] resize(T[] items){
        T[] newArr = (T[])new Object[items.length * 2];

        if (nextFirst == 0) {
            //[F&L, 1, 2, 3]
            System.arraycopy(items, 1, newArr, 1, size);
        } else if (nextFirst == items.length - 1) {
            //[1, 2, 3, F&L]
            System.arraycopy(items, 0, newArr, 1, size);
        } else {
            //[ 3, F&L, 1, 2]
            int rightSide = items.length - 1 - nextFirst;
            System.arraycopy(items, nextFirst + 1, newArr, 1, rightSide);
            System.arraycopy(items, 0, newArr, rightSide + 1, size - rightSide);
        }
        nextFirst = 0;
        nextLast = size + 1;
        return newArr;
    }

    public boolean isEmpty(){
        if(nextLast == 0){
            return nextFirst == items.length-1;
        }
        return nextLast == nextFirst+1;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        if(nextFirst > nextLast){
            for(int i = nextFirst+1; i < items.length; i++){
                System.out.print(items[i] + " ");
            }
            for(int i = 0; i < nextLast; i++){
                System.out.print(items[i] + " ");
            }
        } else{
            for(int i = nextFirst+1; i < nextLast; i++){
                System.out.print(items[i] + " ");
            }
        }
        System.out.println();
    }

    public T removeFirst(){
        if(size == 0){
            return null;
        }
        nextFirst++;
        if(nextFirst == items.length){
            nextFirst = 0;
        }
        T i = items[nextFirst];
        items[nextFirst] = null;
        size--;
        if (items.length >= 16 && size < items.length / 4){
            items = downsize(items);
        }
        return i;
    }

    public T removeLast(){
        if(size == 0){
            return null;
        }
        nextLast--;
        if(nextLast == -1){
            nextLast = items.length-1;
        }
        T i = items[nextLast];
        items[nextLast] = null;
        size--;
        if (items.length >= 16 && size < items.length / 4){
            items = downsize(items);
        }
        return i;
    }

    private T[] downsize(T[] items){
        T[] newArr = (T[])new Object[items.length / 2];

        if (nextLast > nextFirst) {
            //[ , , , F, 1, 2, 3, L, , ]
            System.arraycopy(items, nextFirst + 1, newArr, 1, size);
        }else {
            //[ 2, 3, L, , , , , , F, 1]
            int rightSide = items.length - 1 - nextFirst;
            System.arraycopy(items, nextFirst + 1, newArr, 1, rightSide);
            System.arraycopy(items, 0, newArr, rightSide + 1, size - rightSide);
        }
        nextFirst = 0;
        nextLast = size + 1;
        return newArr;
    }

    public T get(int index){
        if(index > size-1){
            return null;
        }
        var i = nextFirst+1+index;
        if(i > items.length-1){
            i = i - items.length;
        }
        return items[i];
    }

    public static void main(String[] args){
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for (int i = 0; i < 1000; i++) {
            ad.addLast(i);
        }
        for (int i = 0; i < 980; i++) {
            ad.removeLast();
        }
        var s= ad.size();
        var e = ad.isEmpty();
        ad.printDeque();

        var copy = new ArrayDeque<Integer>(ad);
        for (int i = 0; i < 10; i++) {
            copy.removeLast();
        }

        /*
        var a = ad.isEmpty();
        ad.addLast(5);
        ad.addLast(6);
        ad.addFirst(7);
        ad.addFirst(8);
//        var s4 = ad.size();
        var a8 = ad.removeFirst();
//        var s3 = ad.size();
        var a6 = ad.removeFirst();
        ad.printDeque();
//        var s2 = ad.size();
        ad.addLast(9);
        ad.addLast(10);
        ad.addLast(9);
        ad.addLast(10);
//        ad.addLast(9);
        var s7 = ad.size();
        var q1 = ad.get(3);
        var q2 = ad.get(5);
//        var q3 = ad.get(7);

        ad.printDeque();

//        ad.removeFirst();
//        ad.removeFirst();
//        ad.removeFirst();
//        ad.removeFirst();
        ad.printDeque();

        ad.addLast(9);
        ad.addLast(9);
        ad.addLast(9);
        ad.addLast(9);
        ad.addLast(9);
        ad.addLast(9);
        ad.addLast(9);
        ad.addLast(9);
        ad.addFirst(1);
        ad.addFirst(1);
        ad.addFirst(1);

        var s= ad.size();
        var e = ad.isEmpty();
         */

    }
}
