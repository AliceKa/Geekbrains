import Level3.lesson6.ArrayCheck;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayCheckTest {

    public static ArrayCheck result;

    @BeforeAll
    public static void init() {
        result = new ArrayCheck();
    }

    @Test
    public void array1() {
        int[] arrResult = result.array1(new int[]{0, 1, 2, 4, 5, 6, 4, 7, 8, 8});
        assertArrayEquals(new int[]{7,8,8}, arrResult);

        int[] arrResult1 = result.array1(new int[]{0, 1, 2, 4, 5, 6});
        assertArrayEquals(new int[]{5, 6}, arrResult1);

        assertThrows(RuntimeException.class,()-> {result.array1(new int[]{0, 1, 3, 7, 9});});
        assertThrows(RuntimeException.class,()-> {result.array1(new int[]{0, 1, 2, 2, 2});});
    }

    @Test
    public void array2() {
        boolean arrResult = result.array2(new int[]{0, 1, 2, 3});
        assertEquals(true, arrResult);

        boolean arrResult1 = result.array2(new int[]{4, 1, 0, 0});
        assertEquals(true, arrResult1);

        boolean arrResult2 = result.array2(new int[]{2, 0, 0, 0});
        assertEquals(false, arrResult2);

    }
}
