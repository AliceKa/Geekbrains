package ru.geekbrains.lesson2;

import java.lang.reflect.Array;
import java.nio.channels.Channel;
import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) {

        // Task1
        System.out.println("Task 1");
        int[] arr = {1,1,0,0,1,0,1};
        System.out.println(Arrays.toString(arr));
        for (int i=0; i <arr.length; i++) {
            arr[i] = 1 - arr[i];
        }
        System.out.println(Arrays.toString(arr));
        // Task2
        System.out.println("Task 2");
        int[] arr1 = new int[8];
        int toCount = 0;
        for (int i=0; i < arr1.length; i++) {
            arr1[i] = toCount;
            toCount+=3;
        }
        System.out.println(Arrays.toString(arr1));

        // Task3
        System.out.println("Task 3");
        int[] arr2 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i<arr2.length; i++) {
            if (arr2[i] < 6) {
                arr2[i] *=2;
            }
        }
        System.out.println(Arrays.toString(arr2));

        // Task4
        System.out.println("Task 4");
        int[][] arr3 = new int[5][5];
        for (int i= 0; i< arr3.length; i++){
            for (int j=0; j < arr3[i].length; j++){
                if(i == j || i == arr3[i].length-j-1) {
                    arr3[i][j] = 1;
                }
            }
            System.out.println(Arrays.toString(arr3[i]));
        }

        // Task5
        System.out.println("Task 5");
        int[] arr4 = {10, 32, 5, 8, 11};
        int maxValue = 0;
        int minValue = arr4[0];
        for (int i = 0; i < arr4.length; i++){
            if (arr4[i] > maxValue){
                maxValue = arr4[i];
            }
            else if (arr4[i] < minValue) {
                minValue = arr4[i];
            }
        }
        System.out.println("Max value is " + maxValue);
        System.out.println("Min value is " + minValue);

        // Task6
        System.out.println("Task 6");
        int[] arr5 = {1, 1, 1, 0, 2, 1};
        System.out.println(leftRightSum(arr5));

        // Task7
        System.out.println("Task 7");
        int[] arr6 = {1,2,3,4,5,6};
        changeNPosition(arr6, 2);

    }

    public static boolean leftRightSum(int[] arr) {
        int result = 0;
        for (int i = 1; i < arr.length-1; i++){
            int sumaLeft = 0;
            int sumaRight = 0;

            for (int j =0; j<i;j++){
                sumaLeft+=arr[j];
            }
            for (int k=i+1; k<arr.length; k++){
                sumaRight+=arr[k];
            }
            if(sumaLeft == sumaRight) {
                result = 1;
                break;
            }
        }
        return (result==1);
    }
    public static void changeNPosition(int[] arr, int n) {
        System.out.println(Arrays.toString(arr));
        int initial_len = arr.length;
        int[] arr_new = new int[initial_len];
        if (n >= 0) {
            for (int i = 0; n+i < initial_len; i++) {
                arr_new[n+i] = arr[i];
            }
            for (int k = 0; k < n; k++) {
                arr_new[k] = arr[initial_len-n+k];
            }
    }
        else if (n < 0) {
            for (int i = 0; i < -n; i++) {
                arr_new[initial_len + n + i] = arr[i];

            }
            for (int k = 0; k < initial_len + n; k++) {
                arr_new[k] = arr[k-n];
            }
        }
        System.out.println(Arrays.toString(arr_new));

        }

}
