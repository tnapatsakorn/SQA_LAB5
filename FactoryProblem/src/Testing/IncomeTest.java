package Testing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import sqa.main.Income;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class IncomeTest {

    @ParameterizedTest(name = "{index} => impeller={0}, motor={1}, cover={2} => expected={3}")
    @MethodSource("provide36InvalidTestCases")
    void testStrongRobust36Manual(int impeller, int motor, int cover, double expected) {
        Income income = new Income();
        double result = income.calculateIncome(impeller, motor, cover);
        assertEquals(expected, result, 0.001);
    }

    static Stream<Arguments> provide36InvalidTestCases() {
        return Stream.of(
            // ✅ VF1 = 1500, VF2 = 3000
            // ✅ VM1 = 400,  VM2 = 700
            // ✅ VC1 = 1000, VC2 = 2000

            // -- IVF1 < 1000
            Arguments.of(900, 400, 1000, -1.0),
            Arguments.of(900, 400, 2000, -1.0),
            Arguments.of(900, 700, 1000, -1.0),
            Arguments.of(900, 700, 2000, -1.0),
            Arguments.of(900, 400, 1000, -1.0),
            Arguments.of(900, 400, 2000, -1.0),

            // -- IVF2 > 5000
            Arguments.of(5100, 400, 1000, -1.0),
            Arguments.of(5100, 400, 2000, -1.0),
            Arguments.of(5100, 700, 1000, -1.0),
            Arguments.of(5100, 700, 2000, -1.0),
            Arguments.of(5100, 400, 1000, -1.0),
            Arguments.of(5100, 400, 2000, -1.0),

            // -- IVM1 < 300
            Arguments.of(1500, 250, 1000, -1.0),
            Arguments.of(1500, 250, 2000, -1.0),
            Arguments.of(3000, 250, 1000, -1.0),
            Arguments.of(3000, 250, 2000, -1.0),
            Arguments.of(1500, 250, 1000, -1.0),
            Arguments.of(1500, 250, 2000, -1.0),

            // -- IVM2 > 800
            Arguments.of(1500, 850, 1000, -1.0),
            Arguments.of(1500, 850, 2000, -1.0),
            Arguments.of(3000, 850, 1000, -1.0),
            Arguments.of(3000, 850, 2000, -1.0),
            Arguments.of(1500, 850, 1000, -1.0),
            Arguments.of(1500, 850, 2000, -1.0),

            // -- IVC1 < 500
            Arguments.of(1500, 400, 400, -1.0),
            Arguments.of(1500, 700, 400, -1.0),
            Arguments.of(3000, 400, 400, -1.0),
            Arguments.of(3000, 700, 400, -1.0),
            Arguments.of(1500, 400, 400, -1.0),
            Arguments.of(1500, 700, 400, -1.0),

            // -- IVC2 > 3000
            Arguments.of(1500, 400, 3100, -1.0),
            Arguments.of(1500, 700, 3100, -1.0),
            Arguments.of(3000, 400, 3100, -1.0),
            Arguments.of(3000, 700, 3100, -1.0),
            Arguments.of(1500, 400, 3100, -1.0),
            Arguments.of(1500, 700, 3100, -1.0)
        );
    }
}
