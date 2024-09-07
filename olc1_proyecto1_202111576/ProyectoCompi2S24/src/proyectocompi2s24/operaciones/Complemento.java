package proyectocompi2s24.operaciones;

import proyectocompi2s24.contracts.IOperation;
import proyectocompi2s24.tsimbolos.TSimbolos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Complemento extends IOperation {
    public final IOperation op1;

    public Complemento(IOperation op1) {
        this.op1 = op1;
    }

    @Override
    public Set<String> eval(TSimbolos ts) {
        //Definir un array list  por los símbolos entre ! (33 en código ascii)
        // hasta el ~ (126 en código ascii)
        ArrayList<String> simbolos = new ArrayList<>();
        for (char c = 33; c <= 38; c++) {
            simbolos.add(Character.toString(c));
        }
        Set<String> resultado = new HashSet<>(simbolos);
        resultado.removeAll(op1.eval(ts));
        return resultado;
    }
    @Override
    public void evaluar(Set<Integer> elementos) {

    }
    @Override
    public String grafo() {
        StringBuilder str = new StringBuilder();
        str.append("O_").append(id);
        str.append("[label=\"Complemento\"];\n");

        str.append("O_").append(id).append("->").append("O_").append(op1.getId()).append(";\n");
        str.append(op1.grafo());

        return str.toString();
    }
}
