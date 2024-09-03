package proyectocompi2s24.operaciones;

import proyectocompi2s24.contracts.IOperation;
import proyectocompi2s24.contracts.IStatement;
import proyectocompi2s24.tsimbolos.TSimbolos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SetOperation extends IStatement {
    private final IOperation op;
    private List<String> aplicado = new ArrayList<String>();

    public SetOperation(IOperation op) {
        this.op = op;
    }

    @Override
    public void execute(TSimbolos ts) {
        Set<Integer> resultadoOriginal = op.eval(ts);

        //Simplificando operacion
        IOperation simplificadoOp = simplify(op);
        Set<Integer> resultadoSimplificado = simplificadoOp.eval(ts);

        //Resultados a imprimir
        System.out.println("Resultado de la operacion original: " + resultadoOriginal);
        System.out.println("Resultado de la operacion simplificada: " + resultadoSimplificado);

        //Imprimiendo aplicados (leyes)
        if(!aplicado.isEmpty())
        {
            System.out.println("Aplicado: ");
            for (String ley : aplicado) {
                System.out.println("- "+ ley);
            }
        } else{
            System.out.println("No se aplicaron leyes");
        }
    }
    @Override
    public String grafo(){
        StringBuilder str = new StringBuilder();
        str.append("S_").append(id);
        str.append("[label=\"Operacion de conjunto: \"];\n");

        str.append("S_").append(id).append("->").append("O_").append(op.getId()).append("\n");
        str.append(op.grafo());

        return str.toString();
    }

    private IOperation simplify(IOperation operacion){
        //Idempotencia de la union (A U A = A, A U A U A = A)
        if(operacion instanceof Union opUnion){
            if(opUnion.op1 instanceof SetReference ref1 && opUnion.op2 instanceof SetReference ref2){
                if(ref1.id.equals(ref2.id)){
                    aplicado.add("Idempotencia de la union");
                    return opUnion.op1;
                }
            }
        }
        return operacion;
    }
}
