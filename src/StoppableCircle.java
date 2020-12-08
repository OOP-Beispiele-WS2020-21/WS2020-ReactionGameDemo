import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.graphics.Circle;

/**
 * Diese Klasse repräsentiert einen Kreis, dessen Bewegung durch Aufruf einer
 * öffentlichen Methode gestoppt werden kann. Es handelt sich um eine Spezialisierung
 * der Circle-Klasse, von der diese Klasse erbt.
 */

public class StoppableCircle extends Circle {

    // Instanzvariable um festzuhalen, ob der Kreis aktuell gestoppt wurde
    private boolean stopped;

    public StoppableCircle(float x, float y, float radius, Color color) {
        // Aufruf des geerbten Konstruktors aus der Circle-Klasse
        super(x, y, radius, color);
        // Initialisieren der Instanzvariable mit dem Wert false
        stopped = false;
    }

    /**
     * Der Aufruf dieser Methode verhindert zukünftige Bewegungen des Kreises
     */
    public void stop() {
        stopped = true;
    }

    /**
     * Die geerbte move-Methode wird überschrieben und angepasst um sicherzustellen, dass
     * eine Bewegung des Kreises nur solange statt findet, bis die Variable stopped durch
     * Aufruf der Methode stop den Wert true angenommen hat.
     */
    @Override
    public void move(float dx, float dy) {
        // Wenn der Kreis "gestoppt wurde" ...
        if (stopped) {
            // ... verlassen wir die Methode
            return;
        }
        // ... andernfalls lassen wir die Bewegung durch Aufruf der ursprünglichen Methode zu
        super.move(dx, dy);
    }
}
