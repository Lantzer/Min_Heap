import java.io.*;

public class Richest {
    /*
    Load 10,000 into array, min_heap array, load 1 new data element in and compare to min_heap[1]
        if new element <= min_heap[1]
            load next element and repeat
        else if new element > min_heap[1]
            replace min_heap[1] with new element
            min_heapify min_heap[1]

        after all data from file is run through
            min_heap based sort on array and print data in descending order.

    To implement:
        Array will start at index 1 for simplicity

        Parent -> P(A[i])= A[floor(i/2)],   if floor(i/2) >= 1 , if == 0 no parent ( 1/2 = 0)
        Left -> L(A[i]) = A[2i],    if 2i <= A.heap_size,  if 2i > a.heap_size out of bounds
        Right -> R(A[i]) = A[2i+1],     if 2i+1 <= A.heap_size,   if 2i+1 > a.heap_size out of bounds

        min_heap

        min_heap based sort


     */
    public static void main(String[] args) {
        int[] A = new int[11]; //10001 because 1 base indexing
        int heap_size = A.length-1;
        File file = new File(args[0]);
        FileReader fileReader;
        int i;
        String line;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            for (i = 1; i < 11; i++) {
                line = bufferedReader.readLine();
                A[i] = Integer.parseInt(line);
                //System.out.println("Index: " + i + " " + A[i]);
            }
            build_min_heap(A,heap_size);
            for(int j = 1; j < 11; j++){
                System.out.println(A[j]);
            }
            while ((line = bufferedReader.readLine()) != null) {
                int nextIn = Integer.parseInt(line);
                if (nextIn > A[1]){
                    A[1] = nextIn;  //inserts nextIn
                    min_heapify(A,1,heap_size);
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

    }
    

    public static void min_heapify(int[] A, int i, int heap_size) {
        int left = L(A, i, heap_size);
        int right = R(A, i, heap_size);
        int smallest_index = i;
        boolean changed = false;

        //left is less than i
        if (left != -1 && A[left] < A[i]) {
            smallest_index = left;
            changed = true;
        }
        //right is less than i
        if (right != -1 && A[right] < A[smallest_index]) {
            smallest_index = right;
            changed = true;
        }
        //when left or right is the same value as a[i], i stays the same
        //if left or right was smaller than i, swap them and min_heap left or right
        if (changed) {
            swap(A, i, smallest_index);
            min_heapify(A, smallest_index, heap_size);

        }
    }


    public static void swap(int[] A, int i, int swap_index) {
        int temp = A[i];
        A[i] = A[swap_index];
        A[swap_index] = temp;
    }

    public static void build_min_heap(int[] A, int heap_size) {
        for (int i = A.length / 2; i >= 1; i--) {
            min_heapify(A, i, heap_size);
        }
    }

    //Returns parent index, or 0 if no parent
    public static int P(int[] A, int i) {
        if (i / 2 >= 1) {
            return i / 2;
        } else {
            return 0;
        }
    }

    //Returns i's Left child index, if it is out of bounds return -1;
    public static int L(int[] A, int i, int heap_size) {
        if (2 * i <= heap_size) {
            return 2 * i;
        } else {
            return -1;
        }
    }

    //Returns i's Right child index, if it is out of bounds returns -1
    public static int R(int[] A, int i, int heap_size) {
        if (2 * i + 1 <= heap_size) {
            return 2 * i + 1;
        } else {
            return -1;
        }
    }
}