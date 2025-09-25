package dbsetter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dpsetter.mapping.ACCSignalMapper;

import static org.junit.jupiter.api.Assertions.*;

class ACCSignalMapperTest {

    private ACCSignalMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ACCSignalMapper();
    }

    @Test
    void testFehlernummerMapping() {
        assertEquals("Status Alarmnummer", mapper.map("Fehlernummer"));
    }

    @Test
    void testSollwertLuftMapping() {
        assertEquals("Sollwert Volumenstrom", mapper.map("Sollwert Luft"));
    }

    @Test
    void testIstwertLuftMapping() {
        assertEquals("Istwert Volumenstrom", mapper.map("Istwert Luft"));
    }

    @Test
    void testIstwertWinkelMapping() {
        assertEquals("Klappenstellung", mapper.map("Istwert Winkel"));
    }

    @Test
    void testIstwertDruckMapping() {
        assertEquals("Istwert Druck", mapper.map("Istwert Druck"));
    }

    @Test
    void testStatusGLTAlarmMapping() {
        assertEquals("Status Fehlernummer", mapper.map("Status GLT Alarm"));
    }

    @Test
    void testBetriebsartMapping() {
        assertEquals("Betriebsart", mapper.map("Betriebsart"));
    }

    @Test
    void testFallbackWhenKeyNotFound() {
        String raw = "Unbekanntes Signal";
        assertEquals(raw, mapper.map(raw));
    }
}
