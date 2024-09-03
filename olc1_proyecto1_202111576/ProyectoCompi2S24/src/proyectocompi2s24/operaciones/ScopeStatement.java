package proyectocompi2s24.operaciones;

import proyectocompi2s24.contracts.IStatement;
import proyectocompi2s24.tsimbolos.TSimbolos;

import java.util.LinkedList;

public class ScopeStatement extends IStatement {
    private final String name;
    private final LinkedList<IStatement> statements;

    public ScopeStatement(String name, LinkedList<IStatement> statements) {
        this.name = name;
        this.statements = statements;
    }

    @Override
    public void execute(TSimbolos ts) {
        ts.pushScope();
        System.out.println("Scope: " + this.name);
        for (IStatement statement : this.statements) {
            statement.execute(ts);
        }

        //Cerramos el scope
        ts.popScope();
        System.out.println("Fin Scope: " + this.name);


    }

    @Override
    public String grafo() {
        StringBuilder str = new StringBuilder();
        str.append("S_").append(id).append("[label=\"Scope\"").append(name).append("\"];\n");
        for (IStatement statement : statements) {
            str.append("S_").append(id).append(" -> ").append("S_").append(statement.getId()).append(";\n");
            str.append(statement.grafo());
        }
        return str.toString();
    }
}
