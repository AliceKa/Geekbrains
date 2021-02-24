package Level2.lesson5;


public class AppRun {
    static long time;

    public static void main(String[] args) {
        method1(100);
        method2(100);
    }

    public static void method1(int Size) {
        float[] arr = new float[Size];
        for (int i = 0; i < Size; i++) {
            arr[i] = 1;
        }
        time = System.currentTimeMillis();
        for (int i = 0; i < Size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - time);
    }

    public static void method2(int Size){
        float[] arr = new float[Size];
        float[] arr1 = new float[Size/2];
        float[] arr2 = new float[Size/2];

        for (int i = 0; i < Size; i++) {
            arr[i] = 1;
        }
        time = System.currentTimeMillis();

        System.arraycopy(arr, 0, arr1, 0, Size/2);
        System.arraycopy(arr, Size/2, arr2, 0, Size/2);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < Size/2; i++) {
                arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < Size/2; i++) {
                arr2[i] = (float)(arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, arr, 0, Size/2);
        System.arraycopy(arr2, 0, arr, Size/2, Size/2);

        System.out.println(System.currentTimeMillis() - time);
    }


}
