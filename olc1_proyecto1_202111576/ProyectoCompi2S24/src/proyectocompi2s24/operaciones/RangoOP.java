package proyectocompi2s24.operaciones;

import java.util.HashSet;
import java.util.Set;

public class RangoOP {
    public String op1;
    public String op2;

    public RangoOP(String op1, String op2) {
        this.op1 = op1;
        this.op2 = op2;
    }

    public Set<String> generarRango() {
        Set<String> resultado = new HashSet<>();
        char el1 = op1.charAt(0);
        char el2 = op2.charAt(0);

        if (el1 < el2) {
            for (char i = el1; i <= el2; i++) {
                resultado.add(String.valueOf(i));
            }
        } else {
            for (char i = el1; i >= el2; i--) {
                resultado.add(String.valueOf(i));
            }
        }
        return resultado;
    }
}
