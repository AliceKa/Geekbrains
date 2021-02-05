package Level2.lesson2;


public class Main {
    public static void main(String[] args){
        String[][] m  = new String[1][2];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j] = "1";
            }
        }
        m[0][0] = "k";
        check(m);


    }

    public static void check(String[][] matrix)  {

        try {
            if (rowsColumns(matrix) != 4) {
                throw new MyArraySizeException();
            }
        } catch (Exception e) {
            System.out.println("Error type 1: Wrong Array Size");
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                try {
                    if (!isNumeric(matrix[i][j])) {
                        throw new MyArrayDataException();
                    }
                } catch (Exception e) {
                    System.out.println("Error type 2: Wrong format in cell [" + i + "," + j + "]");
                }

            }
        }
    }
    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }

    public static int rowsColumns(String[][] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                sum ++;
            }
        }
        return sum;
    }

}
