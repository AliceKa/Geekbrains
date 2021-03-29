package Level3.lesson6;

public class ArrayCheck {

    public int[] array1(int[] arr) {
        int newLength = arr.length - (findLast4(arr) + 1);
        int[] newArray = new int[newLength];
        for (int i = 0; i< newLength; i++) {
            newArray[i] = arr[findLast4(arr) + i + 1];
        }
        return newArray;
    }

    public int findLast4(int[] arr) {
        int lastPositionof4 = -1;
        for (int i = 0; i< arr.length; i++) {
            if (arr[i] == 4) lastPositionof4 = i;
        }
        if (lastPositionof4 == -1) throw new RuntimeException();
        return lastPositionof4;
    }
    public boolean array2(int[] arr) {
        for (int i = 0; i< arr.length; i++) {
            if (arr[i] == 1 || arr[i] == 4) {
                return true;
            }
        }
        return false;
    }


}
