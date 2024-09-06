package proyectocompi2s24.operaciones;

import proyectocompi2s24.contracts.IOperation;
import proyectocompi2s24.tsimbolos.TSimbolos;

import java.util.HashSet;
import java.util.Set;

public class Interseccion extends IOperation {
    public final IOperation op1, op2;

    public Interseccion(IOperation op1, IOperation op2) {
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public Set<String> eval(TSimbolos ts) {
        Set<String> resultado = new HashSet<>(op1.eval(ts));
        resultado.retainAll(op2.eval(ts));
        return resultado;
    }
    @Override
    public void evaluar(Set<Integer> elementos) {

    }
    @Override
    public String grafo() {
        StringBuilder str = new StringBuilder();
        str.append("O_").append(id);
        str.append("[label=\"Interseccion\"];\n");

        str.append("O_").append(id).append("->").append("O_").append(op1.getId()).append(";\n");
        str.append(op1.grafo());

        str.append("O_").append(id).append("->").append("O_").append(op2.getId()).append(";\n");
        str.append(op2.grafo());

        return str.toString();
    }

}
