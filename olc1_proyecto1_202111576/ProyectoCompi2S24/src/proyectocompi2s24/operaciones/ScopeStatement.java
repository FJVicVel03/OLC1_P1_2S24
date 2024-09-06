package proyectocompi2s24.operaciones;

import proyectocompi2s24.contracts.IStatement;
import proyectocompi2s24.tsimbolos.TSimbolos;

import javax.swing.*;
import java.util.LinkedList;

public class ScopeStatement extends IStatement {
    private final String name;
    private final LinkedList<IStatement> statements;

    public ScopeStatement(String name, LinkedList<IStatement> statements) {
        this.name = name;
        this.statements = statements;
    }

    @Override
    public void execute(TSimbolos ts, JTextArea consola) {
        ts.pushScope();
        for (IStatement statement : this.statements) {
            statement.execute(ts, consola);
        }

        //Cerramos el scope
        ts.popScope();


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
    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();
        resultado.append("Scope: ").append(this.name).append("\n");

        for (IStatement statement : statements) {
            resultado.append(statement.toString()).append("\n");  // Llamada recursiva a toString() en cada statement
        }

        resultado.append("Fin Scope: ").append(this.name).append("\n");
        return resultado.toString();
    }


}
