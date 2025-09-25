package dbsetter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dpsetter.mapping.ACSignalMapper;

import static org.junit.jupiter.api.Assertions.*;

class ACSignalMapperTest {

    private ACSignalMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ACSignalMapper();
    }

    @Test
    void testStatusEIN() {
        assertEquals("Normalbetrieb", mapper.map("Status EIN"));
    }

    @Test
    void testStatusAUS() {
        assertEquals("Aus", mapper.map("Status AUS"));
    }

    @Test
    void testStatusERHOEHT() {
        assertEquals("erhoehte Luftmenge", mapper.map("Status ERHOEHT"));
    }

    @Test
    void testStatusGESENKT() {
        assertEquals("reduzierte Luftmenge", mapper.map("Status GESENKT"));
    }

    @Test
    void testStatusALARM() {
        assertEquals("Stoerung VSR", mapper.map("Status ALARM"));
    }

    @Test
    void testFehlernummer() {
        assertEquals("Alarmnummer", mapper.map("Fehlernummer"));
    }

    @Test
    void testSollwertLuft() {
        assertEquals("Sollwert Volumenstrom", mapper.map("Sollw. Luft"));
    }

    @Test
    void testIstwertLuft() {
        assertEquals("Istwert Volumenstrom", mapper.map("Istw. Luft"));
    }

    @Test
    void testIstwertWinkel() {
        assertEquals("Klappenstellung", mapper.map("Istwert Winkel"));
    }

    @Test
    void testStatusBetriebsart() {
        assertEquals("Betriebsart", mapper.map("Status Betriebsart"));
    }

    @Test
    void testStatusGLTAlarm() {
        assertEquals("Status Stoermeldung", mapper.map("Status GLT Alarm"));
    }

    @Test
    void testFallback() {
        String raw = "Unbekanntes Signal";
        assertEquals(raw, mapper.map(raw));
    }
}

