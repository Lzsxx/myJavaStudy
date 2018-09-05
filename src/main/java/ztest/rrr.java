package ztest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;


public class rrr {

    public static void main(String[] args) {
        Integer[] arr = {9, 8, 7};
        Integer[] arrr = {1, 2, 3, 4};
        String[] str = {"1", "2", "3"};
//        System.out.println(arr.toString());
//        System.out.println(Arrays.toString(arr));
//        System.out.println(ArrayUtils.toString(arr));
        System.out.println(StringUtils.join(arrr, ",,,"));
        System.out.println(arrr.toString());
        arr = ArrayUtils.addAll(arr, arrr);
        System.out.println(Arrays.toString(arr));


    }
}
