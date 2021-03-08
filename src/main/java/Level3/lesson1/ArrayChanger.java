package Level3.lesson1;

import java.util.Arrays;
import java.util.List;

public class ArrayChanger <T> {
    private T[] nums;

    public ArrayChanger(T... nums) {
        this.nums = nums;
    }

    public void changePlaces(int a, int b) {
        T saver = nums[a];
        nums[a] = nums[b];
        nums[b] = saver;
    }

    public void getNums() {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]+" ");
        }
        System.out.println();
    }

    public void toArrayList() {
        List<T> ArrayList = Arrays.asList(nums);
        System.out.print("ArrayList: ");
        System.out.println(ArrayList);
        System.out.println("After conversion type: " + ArrayList.getClass().getName());
    }

    public void showType() {
        System.out.println("Before conversion type: " + nums.getClass().getName());
    }


    public static void main(String[] args) {
        ArrayChanger<Integer> intArray = new ArrayChanger<>(1,2,3,4);
        System.out.print("Initial array: ");
        intArray.getNums();
        intArray.showType();

        intArray.changePlaces(0,1);
        System.out.print("Array after changing positions: ");
        intArray.getNums();

        intArray.toArrayList();
    }
}
