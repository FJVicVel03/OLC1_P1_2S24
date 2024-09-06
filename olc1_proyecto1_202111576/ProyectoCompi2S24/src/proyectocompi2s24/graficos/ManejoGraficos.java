package proyectocompi2s24.graficos;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class ManejoGraficos extends JPanel {

    private Set<String> conjuntoA;
    private Set<String> conjuntoB;
    private Set<String> resultadoConjunto;
    private String tipoOperacion;

    public ManejoGraficos(Set<String> conjuntoA, Set<String> conjuntoB, Set<String> resultadoConjunto, String tipoOperacion) {
        this.conjuntoA = conjuntoA;
        this.conjuntoB = conjuntoB;
        this.resultadoConjunto = resultadoConjunto;
        this.tipoOperacion = tipoOperacion;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Colores para conjuntos
        g2d.setStroke(new BasicStroke(2));
        Color colorConjuntoA = Color.RED;
        Color colorConjuntoB = Color.BLUE;
        Color colorInterseccion = new Color(255, 0, 255, 150);  // Magenta semitransparente para intersección

        // Coordenadas para dibujar los conjuntos
        int xA = 100, yA = 100, width = 150, height = 150;
        int xB = 180, yB = 100;  // Conjunto B estará a la derecha de A

        // Dibujar información de los conjuntos
        g2d.setColor(Color.GREEN);
        g2d.drawString("Conjunto A: " + conjuntoA.toString(), 80, 270);
        g2d.drawString("Conjunto B: " + conjuntoB.toString(), 240, 270);
        g2d.setColor(Color.MAGENTA);
        g2d.drawString("Resultado: " + resultadoConjunto.toString(), 190, 300);

        // Encontrar la intersección entre los dos conjuntos
        Set<String> interseccion = new HashSet<>(conjuntoA);
        interseccion.retainAll(conjuntoB);  // Quedarse con los elementos comunes

        // Diferenciar entre Unión e Intersección
        if (tipoOperacion.equals("Union")) {
            // Dibujar conjunto A con transparencia
            g2d.setColor(new Color(255, 0, 0, 100));  // Rojo semitransparente
            g2d.fillOval(xA, yA, width, height);  // Pintar conjunto A

            // Dibujar conjunto B con transparencia
            g2d.setColor(new Color(0, 0, 255, 100));  // Azul semitransparente
            g2d.fillOval(xB, yB, width, height);  // Pintar conjunto B

            // Dibujar la intersección con color especial y transparencia
            g2d.setColor(colorInterseccion);
            g2d.fillOval(140, 100, 100, 150);  // Pintar el área de la intersección

        } else if (tipoOperacion.equals("Interseccion")) {
            // Solo dibujar los bordes de los conjuntos A y B
            g2d.setColor(colorConjuntoA);
            g2d.drawOval(xA, yA, width, height);  // Dibujar borde del conjunto A

            g2d.setColor(colorConjuntoB);
            g2d.drawOval(xB, yB, width, height);  // Dibujar borde del conjunto B

            // Colorear solo la intersección sin pintar el resto
            g2d.setColor(colorInterseccion);
            g2d.fillOval(175, 115, 75, 120);  // Pintar solo la intersección
        }

        // Ajustar las posiciones para los elementos de conjuntoA que no están en la intersección
        int offsetA = 0;
        for (String elemento : conjuntoA) {
            if (!interseccion.contains(elemento)) {
                g2d.setColor(Color.BLACK);  // Los elementos se dibujan en negro
                // Dibujar más a la izquierda para conjuntoA
                g2d.drawString(elemento, 120, 140 + offsetA);
                offsetA += 20;
            }
        }

        // Ajustar las posiciones para los elementos de conjuntoB que no están en la intersección
        int offsetB = 0;
        for (String elemento : conjuntoB) {
            if (!interseccion.contains(elemento)) {
                g2d.setColor(Color.BLACK);  // Los elementos se dibujan en negro
                // Dibujar más a la derecha para conjuntoB
                g2d.drawString(elemento, 300, 140 + offsetB);
                offsetB += 20;
            }
        }

        // Dibujar los elementos en la intersección (centrado entre los dos conjuntos)
        int offsetInterseccion = 0;
        for (String elemento : interseccion) {
            g2d.setColor(Color.BLACK);  // Los elementos de la intersección se dibujan en negro
            // Colocar en la intersección (entre los dos conjuntos)
            g2d.drawString(elemento, 200, 140 + offsetInterseccion);
            offsetInterseccion += 20;
        }
    }
}
