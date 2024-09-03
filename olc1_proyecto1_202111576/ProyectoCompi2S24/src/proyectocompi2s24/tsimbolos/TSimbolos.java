package proyectocompi2s24.tsimbolos;

import java.util.*;

public class TSimbolos {
    private Stack<Map<String, Set<Integer>>> scopes;
    private List<Map<String, Set<Integer>>> allScopes;

    public TSimbolos()
    {
        this.scopes = new Stack<>();
        this.allScopes = new ArrayList<>();
        //Creamos el scope raiz
        this.pushScope();
    }

    public void pushScope() {
        this.scopes.push(new HashMap<>());
    }
    public void popScope() {
        if(!this.scopes.isEmpty())
        {
            Map<String, Set<Integer>> scopeActual = this.scopes.pop();
            // Guarda una copia del scope en la lista de todos los scopes
            this.allScopes.add(new HashMap<>(scopeActual));
        }
    }

    public void add(String id, Set<Integer> elementos)
    {
        if(!this.scopes.isEmpty())
        {
           this.scopes.peek().put(id, elementos);
        }
    }
    public Set<Integer> get(String id)
    {
        for(int i = this.scopes.size() - 1; i >= 0; i--)
        {
            if(this.scopes.get(i).containsKey(id))
            {
                return this.scopes.get(i).get(id);
            }
        }
        return null;
    }

    public boolean contiene(String id)
    {
        for(int i = this.scopes.size() - 1; i >= 0; i--)
        {
            if(this.scopes.get(i).containsKey(id))
            {
                return true;
            }
        }
        return false;
    }

    // Método para imprimir todas las tablas de símbolos creadas
    public void imprimirTablas()
    {
        for(int i = 0; i < this.allScopes.size(); i++)
        {
            System.out.println("Scope " + i);
            for (Map.Entry<String, Set<Integer>> entry : this.allScopes.get(i).entrySet())
            {
                System.out.println("ID: " + entry.getKey() + " Elementos: " + entry.getValue());
            }
        }
    }

}
