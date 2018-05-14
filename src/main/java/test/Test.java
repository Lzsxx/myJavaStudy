package test;

import com.sun.org.apache.bcel.internal.generic.SWAP;
import jdk.nashorn.internal.ir.IfNode;

public class Test {









    public static void swap(int[] arr, int a, int b){
        if (a == b) {
            return;
        }
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }
}
