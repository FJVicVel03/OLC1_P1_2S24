package proyectocompi2s24.operaciones;

import proyectocompi2s24.contracts.IOperation;
import proyectocompi2s24.contracts.IStatement;
import proyectocompi2s24.graficos.ManejoGraficos;
import proyectocompi2s24.tsimbolos.TSimbolos;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SetOperation extends IStatement {
    private final String name;  // Nombre de la operación (puede ser null)
    private final IOperation op;
    private Set<String> resultado;      // Resultado de la operación
    private List<String> aplicado = new ArrayList<String>();
    private Set<String> resultadoOriginal;  // Guarda el resultado original
    private Set<String> resultadoSimplificado;  // Guarda el resultado simplificado
    private Set<Integer> resultadoRecibido;  // Guarda el resultado recibido

    // Constructor para operaciones con nombre
    public SetOperation(String name, IOperation op) {
        this.name = name;
        this.op = op;
    }

    // Constructor para operaciones sin nombre
    public SetOperation(IOperation op) {
        this(null, op);
    }

    public Set<String> eval(TSimbolos ts) {
        resultado = op.eval(ts);  // Ejecuta la operación y guarda el resultado
        return resultado;
    }

    @Override
    public void execute(TSimbolos ts, JTextArea consola) {
        Set<String> conjuntoA = null;
        Set<String> conjuntoB = null;
        String tipoOperacion = null;

        // Verificar el tipo de operación para obtener los conjuntos
        if (op instanceof Union) {
            conjuntoA = ((SetReference) ((Union) op).op1).eval(ts);  // Conjunto A para Unión
            conjuntoB = ((SetReference) ((Union) op).op2).eval(ts);  // Conjunto B para Unión
            tipoOperacion = "Union";
        } else if (op instanceof Interseccion) {
            conjuntoA = ((SetReference) ((Interseccion) op).op1).eval(ts);  // Conjunto A para Intersección
            conjuntoB = ((SetReference) ((Interseccion) op).op2).eval(ts);  // Conjunto B para Intersección
            tipoOperacion = "Interseccion";
        }



        resultadoOriginal = op.eval(ts);
        IOperation simplificadoOp = simplify(op);
        resultadoSimplificado = simplificadoOp.eval(ts);

        //aplicar filtro de buscar en la tabla de simbolos si existe ya el nombre de la operacion

        if (name != null) {
            ts.addOperation(name, simplificadoOp);
            ts.add(name, resultadoSimplificado);

            System.out.println("Operacion " + name + " ha sido definida.");
        }

        if (!aplicado.isEmpty()) {
            System.out.println("Aplicado: ");
            for (String ley : aplicado) {
                System.out.println("- " + ley);
            }
        } else {
            System.out.println("No se aplicaron leyes");
        }

        Set<String> result = this.op.eval(ts);
        consola.append("Resultado de la operacion: " + result + "\n");

        JFrame frame = new JFrame("Conjuntos");
        ManejoGraficos panel = new ManejoGraficos(conjuntoA, conjuntoB, result, tipoOperacion);
        frame.add(panel);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public String grafo() {
        StringBuilder str = new StringBuilder();
        str.append("S_").append(id);
        str.append("[label=\"Operacion de conjunto: ").append(name != null ? name : "").append("\"];\n");

        str.append("S_").append(id).append("->").append("O_").append(op.getId()).append("\n");
        str.append(op.grafo());

        return str.toString();
    }

    private IOperation simplify(IOperation operacion) {
        // Idempotencia de la union (A U A = A, A U A U A = A)
        if (operacion instanceof Union opUnion) {
            if (opUnion.op1 instanceof SetReference ref1 && opUnion.op2 instanceof SetReference ref2) {
                if (ref1.id.equals(ref2.id)) {
                    aplicado.add("Idempotencia de la union");
                    return opUnion.op1;
                }
            }
        } else if (operacion instanceof Interseccion opInterseccion) {
            if (opInterseccion.op1 instanceof SetReference ref1 && opInterseccion.op2 instanceof SetReference ref2) {
                if (ref1.id.equals(ref2.id)) {
                    aplicado.add("Idempotencia de la interseccion");
                    return opInterseccion.op1;
                }
            }
        }
        return operacion;
    }

}
