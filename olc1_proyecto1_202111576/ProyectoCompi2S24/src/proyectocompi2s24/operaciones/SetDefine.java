package proyectocompi2s24.operaciones;

import proyectocompi2s24.contracts.IStatement;
import proyectocompi2s24.tsimbolos.TSimbolos;

import javax.swing.*;
import java.util.Set;

public class SetDefine extends IStatement {
    private final String name;
    private final Set<String> elements;

    public SetDefine(String name, Set<String> elements) {
        this.name = name;
        this.elements = elements;
    }


    @Override
    public String grafo(){
        StringBuilder str = new StringBuilder();
        str.append("S_").append(id);
        str.append("[label=\"Definir conjunto: \"];\n").append(name).append("\"];\n");

        for (String element : elements) {
            str.append("S_").append(id).append(" -> ").append("S_").append(id).append("_E_").append(element).append(";\n");

            str.append("S_").append(id).append("_E_").append(element).append("[label=\" Elemento: ").append(element).append("\"];\n");
        }

        return str.toString();
    }
    @Override
    public String toString() {
        return "Conjunto " + this.name + " contiene los elementos: " + this.elements;
    }

    @Override
    public void execute(TSimbolos tabla, JTextArea consola) {
        tabla.add(name, elements);
        System.out.println("Set " + name + " Contiene los elementos: " + elements);
    }
}
