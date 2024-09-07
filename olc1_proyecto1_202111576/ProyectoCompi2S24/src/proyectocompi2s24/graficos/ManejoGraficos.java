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
        Color colorDiferencia = new Color(150, 150, 150, 150);  // Gris semitransparente para la diferencia A - B
        Color colorInterseccion = new Color(255, 0, 255, 150);  // Magenta semitransparente para intersección

        // Coordenadas para dibujar los conjuntos
        int xA = 100, yA = 100, width = 150, height = 150;
        int xB = 180, yB = 100;  // Conjunto B estará a la derecha de A

        // Diferenciar entre Unión, Intersección, Complemento y Diferencia
        if (tipoOperacion.equals("Diferencia")) {
            // Dibujar conjunto A (círculo) con borde rojo
            g2d.setColor(colorConjuntoA);
            g2d.drawOval(xA, yA, width, height);  // Dibujar borde del conjunto A

            // Dibujar conjunto B (círculo) con borde azul
            g2d.setColor(colorConjuntoB);
            g2d.drawOval(xB, yB, width, height);  // Dibujar borde del conjunto B

            // Resaltar solo la diferencia A - B (parte de A que no está en B)
            g2d.setColor(colorDiferencia);
            g2d.fillArc(xA, yA, width, height, 90, 180);  // Pintar solo el área de A que no está en B

        } else if (tipoOperacion.equals("Union")) {
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

        } else if (tipoOperacion.equals("Complemento")) {
            // Dibujar el universo (rectángulo grande)
            g2d.setColor(Color.GRAY);
            g2d.fillRect(50, 50, 400, 250);  // Pintar el fondo del universo

            // Dibujar conjunto A (círculo) con borde rojo y sin relleno
            g2d.setColor(colorConjuntoA);
            g2d.drawOval(xA, yA, width, height);  // Dibujar el borde del conjunto A

            // Dibujar el área fuera del conjunto A (complemento) con color blanco
            g2d.setColor(Color.WHITE);
            g2d.fillOval(xA, yA, width, height);  // Pintar el área dentro del conjunto A con blanco
        }

        // Dibujar los elementos dentro de los conjuntos, si no es Complemento
        if (!tipoOperacion.equals("Complemento")) {
            int offsetA = 0;
            int stepA = height / (conjuntoA.size() + 1);  // Espaciado uniforme para los elementos de A
            Set<String> interseccion = new HashSet<>(conjuntoA);
            interseccion.retainAll(conjuntoB);  // Encontrar los elementos comunes para Intersección

            for (String elemento : conjuntoA) {
                if (!interseccion.contains(elemento)) {
                    g2d.setColor(Color.BLACK);  // Los elementos se dibujan en negro
                    g2d.drawString(elemento, 120, 140 + offsetA);  // Dibujar elementos de conjunto A
                    offsetA += stepA;
                }
            }

            int offsetB = 0;
            int stepB = height / (conjuntoB.size() + 1);  // Espaciado uniforme para los elementos de B
            for (String elemento : conjuntoB) {
                if (!interseccion.contains(elemento)) {
                    g2d.setColor(Color.BLACK);  // Los elementos se dibujan en negro
                    g2d.drawString(elemento, 300, 140 + offsetB);  // Dibujar elementos de conjunto B
                    offsetB += stepB;
                }
            }

            int offsetInterseccion = 0;
            int stepInterseccion = height / (interseccion.size() + 1);  // Espaciado uniforme para la intersección
            for (String elemento : interseccion) {
                g2d.setColor(Color.BLACK);  // Los elementos de la intersección se dibujan en negro
                g2d.drawString(elemento, 200, 140 + offsetInterseccion);  // Colocar en la intersección
                offsetInterseccion += stepInterseccion;
            }
        }
    }
}
