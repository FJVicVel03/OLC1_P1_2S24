package proyectocompi2s24.operaciones;

import proyectocompi2s24.contracts.IOperation;
import proyectocompi2s24.tsimbolos.TSimbolos;

import java.util.Set;

public class SetReference extends IOperation {
    public final String id;

    public SetReference(String id) {
        this.id = id;
    }

    @Override
    public Set<String> eval(TSimbolos ts) {
        if(!ts.contiene(id))
        {
            System.out.println("Error: El conjunto " + this.id + " no ha sido definido");
            return null;
        }
        return ts.get(id);
    }

    @Override
    public void evaluar(Set<Integer> elementos) {
        // No hace nada
    }

    @Override
    public String grafo(){
        StringBuilder str = new StringBuilder();
        str.append("O_").append(id);
        str.append("[label=\"Referencia a conjunto: ").append(id).append("\"];\n");

        return str.toString();
    }
}
