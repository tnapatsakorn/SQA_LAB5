package ShippingTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import sqa.main.ShippingVehicle;

public class ShippingVehicleTesting {

    ShippingVehicle shippingVehicle = new ShippingVehicle();

    @ParameterizedTest
    @CsvSource({
        "5, 5, 5, 5, 5, 0",           
        "10, 0, 0, 0, 0, 0",     
        "0, 0, 100, -1, -1, -1",    
        "0, 0, 101, -1, -1, -1",       
        "1, 1, 1, 1, 1, 1",           
        "0, 0, 0, -1, -1, -1",      
        "1, 1, 200, -1, -1, -1",            
        "5, 0, 0, 0, 0, 5"         
    })
    void testCalculate(int small, int medium, int large, int expectedLarge, int expectedMedium, int expectedSmall) {
        List<Integer> expected;
        if (expectedLarge == -1) {
            expected = List.of(ShippingVehicle.CANNOT_SHIP_ITEM);
        } else {
            expected = Arrays.asList(expectedLarge, expectedMedium, expectedSmall);
        }
        List<Integer> actual = shippingVehicle.calculate(small, medium, large);
        assertEquals(expected, actual);
    }
}
