package proyectocompi2s24.operaciones;

import proyectocompi2s24.contracts.IStatement;
import proyectocompi2s24.tsimbolos.TSimbolos;

import java.util.Set;

public class SetDefine extends IStatement {
    private final String name;
    private final Set<Integer> elements;

    public SetDefine(String name, Set<Integer> elements) {
        this.name = name;
        this.elements = elements;
    }

    @Override
    public void execute(TSimbolos ts) {
        ts.add(name, elements);
        System.out.println("Set " + name + " Contiene los elementos: " + elements);
    }
    @Override
    public String grafo(){
        StringBuilder str = new StringBuilder();
        str.append("S_").append(id);
        str.append("[label=\"Definir conjunto: \"];\n").append(name).append("\"];\n");

        for (Integer element : elements) {
            str.append("S_").append(id).append(" -> ").append("S_").append(id).append("_E_").append(element).append(";\n");

            str.append("S_").append(id).append("_E_").append(element).append("[label=\" Elemento: ").append(element).append("\"];\n");
        }

        return str.toString();
    }

}
