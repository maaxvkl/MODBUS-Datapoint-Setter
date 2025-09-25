package dbsetter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dpsetter.mapping.GCSignalMapper;

import static org.junit.jupiter.api.Assertions.*;

class GCSignalMapperTest {

    private GCSignalMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new GCSignalMapper();
    }

    @Test
    void testSetStatusErhoeht() {
        assertEquals("Schaltbefehl Raum erhoehte Luft", mapper.map("SetStatus ERHOEHT"));
    }

    @Test
    void testStatusEIN() {
        assertEquals("Raum Status Tag (Normalbetrieb)", mapper.map("Status EIN"));
    }

    @Test
    void testStatusAUS() {
        assertEquals("Raum Status Aus", mapper.map("Status AUS"));
    }

    @Test
    void testStatusERHOEHT() {
        assertEquals("Raum Status erhoehte Luftmenge", mapper.map("Status ERHOEHT"));
    }

    @Test
    void testStatusGESENKT() {
        assertEquals("Raum Status Nacht (red. Luft)", mapper.map("Status GESENKT"));
    }

    @Test
    void testStatusALARM() {
        assertEquals("Raum Sammelstoerung", mapper.map("Status ALARM"));
    }

    @Test
    void testSollwertRaumzuluft() {
        assertEquals("Raumzuluft Sollwert Volumenstrom", mapper.map("Sollwert Raumzuluft"));
    }

    @Test
    void testIstwertRaumzuluft() {
        assertEquals("Raumzuluft Istwert Volumenstrom", mapper.map("Istwert Raumzuluft"));
    }

    @Test
    void testSollwertRaumabluft() {
        assertEquals("Raumabluft Sollwert Volumenstrom", mapper.map("Sollwert Raumabluft"));
    }

    @Test
    void testIstwertRaumabluft() {
        assertEquals("Raumabluft Istwert Volumenstrom", mapper.map("Istwert Raumabluft"));
    }

    @Test
    void testStatusBetriebsart() {
        assertEquals("Betriebsart", mapper.map("Status Betriebsart"));
    }

    @Test
    void testSetStatusEIN() {
        assertEquals("Schaltbefehl Raum Tag (Normalbetrieb)", mapper.map("Set Status EIN"));
    }

    @Test
    void testSetStatusAUS() {
        assertEquals("Schaltbefehl Raum Aus", mapper.map("Set Status AUS"));
    }

    @Test
    void testSetStatusGESENKT() {
        assertEquals("Schaltbefehl Raum red. Luftmenge", mapper.map("Set Status GESENKT"));
    }

    @Test
    void testSetStatusBetriebsart() {
        assertEquals("Raum Schaltbefehl Betriebsart", mapper.map("Set Status Betriebsart"));
    }

    @Test
    void testSetStatusErhoehtWithSpace() {
        assertEquals("Schaltbefehl Raum Erh. Luftmenge", mapper.map("Set Status ERHOEHT"));
    }

    @Test
    void testStatusSammelalarm() {
        assertEquals("Raum Status Sammelalarm", mapper.map("Status Sammelalarm"));
    }

    @Test
    void testIstwertSummeAblGesamt() {
        assertEquals("Raum Status Sammelalarm", mapper.map("Istwert Summe Abl Gesamt"));
    }

    @Test
    void testSollwertSummeVerbrAbl() {
        assertEquals("Abluft Abzuege Summe Sollwert", mapper.map("Sollwert Summe Verbr Abl"));
    }

    @Test
    void testIstwertSummeVerbrAbl() {
        assertEquals("Abluft Abzuege Summe Istwert", mapper.map("Istwert Summe Verbr Abl"));
    }

    @Test
    void testSollwertSummeRaumAbl() {
        assertEquals("Raumabluft Summe Sollwert", mapper.map("Sollwert Summe Raum Abl"));
    }

    @Test
    void testIstwertSummeRaumAbl() {
        assertEquals("Raumabluft Summe Istwert", mapper.map("Istwert Summe Raum Abl"));
    }

    @Test
    void testSollwertSummeAblGesamtWithExtraSpace() {
        assertEquals("Raumabluft Summe Sollwert Gesamt", mapper.map("Sollwert Summe  Abl Gesamt"));
    }

    @Test
    void testIstwertSummeAblGesamtWithExtraSpace() {
        assertEquals("Raumabluft Summe Istwert Gesamt", mapper.map("Istwert Summe  Abl Gesamt"));
    }

    @Test
    void testFallback() {
        String raw = "Unbekanntes Signal";
        assertEquals(raw, mapper.map(raw));
    }
}

