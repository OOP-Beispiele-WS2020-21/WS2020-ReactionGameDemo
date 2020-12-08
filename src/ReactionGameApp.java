import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.events.*;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.graphics.Label;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

/**
 * Dieses Spiel erlaubt es Nutzer*innen, ihre persönliche Reaktionszeit zu messen:
 * <p>
 * - ein Kreis wird vom oberen Bildschirmrand senkrecht nach unten bewegt
 * - in der Mitte der Zeichenfläche wird ein Ziel angezeigt
 * - die Bewegung des Kreises wird gestoppt, sobald die Leertaste gedrückt wird
 * - Nach dem Drücken der Leertaste wird die verbleibende Distanz zwischen Ziel und Mittelpunkt
 * bestimmt und für die Nutzer*innen angezeigt
 */

public class ReactionGameApp extends GraphicsApp {

    // Viele Konstanten, die der Parametrisierung der Animation und des Spielablaufs dienen
    private static final int CANVAS_WIDTH = 500;
    private static final int CANVAS_HEIGHT = 500;
    private static final Color CANVAS_BACKGROUND = Colors.WHITE;
    private static final int CIRCLE_RADIUS = 25;
    private static final Color CIRCLE_COLOR = Colors.RED;
    private static final int CIRCLE_SPEED = 5;
    private static final int TARGET_RADIUS = CIRCLE_RADIUS * 2;
    private static final int TARGET_BORDER_WEIGHT = TARGET_RADIUS / 10;

    // Mit diesem Kreis wird das Ziel im Zentrum der Zeichenfläche repräsentiert
    private Circle target;
    // Mit diesem Kreis wird der sich nach unten bewegende Kreis repräsentiert
    private StoppableCircle stoppableCircle;
    // In diesem Label werden Textinformationen für die Nutzer*innen angezeigt
    private Label hint;

    @Override
    public void initialize() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        initializeTarget();
        initializeHintLabel();
        stoppableCircle = new StoppableCircle(CANVAS_WIDTH / 2, -CIRCLE_RADIUS, CIRCLE_RADIUS, CIRCLE_COLOR);
    }

    private void initializeTarget() {
        target = new Circle(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2, TARGET_RADIUS, CANVAS_BACKGROUND);
        target.setBorder(CIRCLE_COLOR, TARGET_BORDER_WEIGHT);
    }

    private void initializeHintLabel() {
        hint = new Label(CIRCLE_RADIUS, CIRCLE_RADIUS, "Drücke auf die Leertaste, wenn der Kreis den Mittelpunkt erreicht hat", CIRCLE_COLOR);
        hint.setFont("Arial");
        hint.setFontSize(14);
    }

    @Override
    public void draw() {
        drawBackground(CANVAS_BACKGROUND);
        stoppableCircle.move(0, CIRCLE_SPEED);
        // Der Kreis soll sich "über" dem Ziel und dem Label bewegen, die Reihenfolge der draw-Aufrufe ist relevant!
        hint.draw();
        target.draw();
        stoppableCircle.draw();
    }

    /**
     * Diese Methode stoppt die Bewegung des Kreises, berechnet die Distanz zwischen Kreis und Mittelpunkt des Ziels
     * und gibt diesen Wert (in Pixeln) auf dem Bildschirm aus.
     */
    private void stopCircleAndShowResults() {
        stoppableCircle.stop();
        int distance = (int) target.distanceTo(stoppableCircle);
        hint.setText("Der Kreis ist noch ~ " + distance + " Pixel vom Mittelpunkt des Ziels entfernt!");
    }

    /**
     * Diese Methode wird automatisch aufgerufen, wenn eine Taste auf der Tastatur gedrückt wurde. Falls es sich
     * um die Leertaste handelt, wird die Kreisbewegung gestoppt und die verbleibende Distanz angezeigt.
     */
    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        // Über den übergebenen Key-Code prüfen wir, ob die Leertaste gedrückt wurde ...
        if (event.getKeyCode() == KeyPressedEvent.VK_SPACE) {
            // ... und leiten in diesem Fall das Spielende ein.
            stopCircleAndShowResults();
        }
    }


    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}
