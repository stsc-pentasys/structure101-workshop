package workshop.structure101.persistence.memory;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class DataGeneratorTest {

    private static final List<String> STRINGS = Arrays.asList("One", "Two", "Three");

    private final DataGenerator underTest = new DataGenerator(12345678L);

    @Test
    public void alwaysReturnsSameListElement() throws Exception {
        String result = underTest.fromList(STRINGS);
        assertThat(result).isEqualTo("Three");
    }

    @Test
    public void alwaysReturnsTheSameEnumElement() throws Exception {
        TestOnly result = underTest.fromEnum(TestOnly.class);
        assertThat(result).isSameAs(TestOnly.THREE);
    }

    private enum TestOnly {
        ONE, TWO, THREE
    }
}