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
        } else if (op instanceof Complemento) {
            conjuntoA = ((SetReference) ((Complemento) op).op1).eval(ts);  // Conjunto A para Complemento
            tipoOperacion = "Complemento";
        } else if (op instanceof Diferencia) {
            conjuntoA = ((SetReference) ((Diferencia) op).op1).eval(ts);  // Conjunto A para Diferencia
            conjuntoB = ((SetReference) ((Diferencia) op).op2).eval(ts);  // Conjunto B para Diferencia
            tipoOperacion = "Diferencia";
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
        // Ley del Doble Complemento: ^^(A) = A
        if (operacion instanceof Complemento opComplemento) {
            if (opComplemento.op1 instanceof Complemento ref1) {
                aplicado.add("Ley del Doble Complemento");
                return ref1.op1;  // Devuelve el conjunto original
            }
        }

        // Leyes de DeMorgan: ^(A U B) = ^A ∩ ^B y ^(A ∩ B) = ^A U ^B
        if (operacion instanceof Union opUnion) {
            if (opUnion.op1 instanceof Complemento && opUnion.op2 instanceof Complemento) {
                aplicado.add("Ley de DeMorgan (Unión)");
                return new Interseccion(
                        new Complemento(((Complemento) opUnion.op1).op1),
                        new Complemento(((Complemento) opUnion.op2).op1)
                );
            }
        }

        if (operacion instanceof Interseccion opInterseccion) {
            if (opInterseccion.op1 instanceof Complemento && opInterseccion.op2 instanceof Complemento) {
                aplicado.add("Ley de DeMorgan (Intersección)");
                return new Union(
                        new Complemento(((Complemento) opInterseccion.op1).op1),
                        new Complemento(((Complemento) opInterseccion.op2).op1)
                );
            }
        }

        // Propiedades conmutativas: A U B = B U A y A ∩ B = B ∩ A
        if (operacion instanceof Union || operacion instanceof Interseccion) {
            IOperation op1 = null;
            IOperation op2 = null;

            if (operacion instanceof Union op) {
                op1 = op.op1;
                op2 = op.op2;
            } else if (operacion instanceof Interseccion op) {
                op1 = op.op1;
                op2 = op.op2;
            }

            if (op1 instanceof SetReference ref1 && op2 instanceof SetReference ref2) {
                if (ref1.id.equals(ref2.id)) {
                    aplicado.add("Propiedad conmutativa");
                    return operacion;  // Sin cambios, la conmutatividad ya aplica
                }
            }
        }

        // Propiedades asociativas: (A U B) U C = A U (B U C) y (A ∩ B) ∩ C = A ∩ (B ∩ C)
        if (operacion instanceof Union opUnionA) {
            if (opUnionA.op1 instanceof Union) {
                aplicado.add("Propiedad asociativa (Unión)");
                return new Union(
                        ((Union) opUnionA.op1).op1,
                        new Union(((Union) opUnionA.op1).op2, opUnionA.op2)
                );
            }
        }

        if (operacion instanceof Interseccion opInterseccionA) {
            if (opInterseccionA.op1 instanceof Interseccion) {
                aplicado.add("Propiedad asociativa (Intersección)");
                return new Interseccion(
                        ((Interseccion) opInterseccionA.op1).op1,
                        new Interseccion(((Interseccion) opInterseccionA.op1).op2, opInterseccionA.op2)
                );
            }
        }

        // Propiedades distributivas: A U (B ∩ C) = (A U B) ∩ (A U C) y A ∩ (B U C) = (A ∩ B) U (A ∩ C)
        if (operacion instanceof Union opUnionB) {
            if (opUnionB.op2 instanceof Interseccion) {
                aplicado.add("Propiedad distributiva (Unión)");
                return new Interseccion(
                        new Union(opUnionB.op1, ((Interseccion) opUnionB.op2).op1),
                        new Union(opUnionB.op1, ((Interseccion) opUnionB.op2).op2)
                );
            }
        }

        if (operacion instanceof Interseccion opInterseccionB) {
            if (opInterseccionB.op2 instanceof Union) {
                aplicado.add("Propiedad distributiva (Intersección)");
                return new Union(
                        new Interseccion(opInterseccionB.op1, ((Union) opInterseccionB.op2).op1),
                        new Interseccion(opInterseccionB.op1, ((Union) opInterseccionB.op2).op2)
                );
            }
        }

        // Propiedades idempotentes: A U A = A y A ∩ A = A
        if (operacion instanceof Union opUnionC) {
            if (opUnionC.op1 instanceof SetReference ref1 && opUnionC.op2 instanceof SetReference ref2) {
                if (ref1.id.equals(ref2.id)) {
                    aplicado.add("Propiedad idempotente (Unión)");
                    return opUnionC.op1;  // Devuelve A
                }
            }
        }

        if (operacion instanceof Interseccion opInterseccionC) {
            if (opInterseccionC.op1 instanceof SetReference ref1 && opInterseccionC.op2 instanceof SetReference ref2) {
                if (ref1.id.equals(ref2.id)) {
                    aplicado.add("Propiedad idempotente (Intersección)");
                    return opInterseccionC.op1;  // Devuelve A
                }
            }
        }

        // Propiedades de absorción: A U (A ∩ B) = A y A ∩ (A U B) = A
        if (operacion instanceof Union opUnionAbsorcion) {
            if (opUnionAbsorcion.op2 instanceof Interseccion opInterseccionAbs) {
                if (opInterseccionAbs.op1 instanceof SetReference ref1 && opUnionAbsorcion.op1 instanceof SetReference ref2) {
                    if (ref1.id.equals(ref2.id)) {
                        aplicado.add("Propiedad de absorción (Unión)");
                        return opUnionAbsorcion.op1;  // Devuelve A
                    }
                }
            }
        }

        if (operacion instanceof Interseccion opInterseccionAbsorcion) {
            if (opInterseccionAbsorcion.op2 instanceof Union opUnionAbs) {
                if (opUnionAbs.op1 instanceof SetReference ref1 && opInterseccionAbsorcion.op1 instanceof SetReference ref2) {
                    if (ref1.id.equals(ref2.id)) {
                        aplicado.add("Propiedad de absorción (Intersección)");
                        return opInterseccionAbsorcion.op1;  // Devuelve A
                    }
                }
            }
        }

        return operacion;
    }

}
