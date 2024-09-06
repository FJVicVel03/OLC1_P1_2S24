package proyectocompi2s24.contracts;

import proyectocompi2s24.tsimbolos.TSimbolos;

import java.util.Set;

public abstract class IOperation extends IGraphicable{
    public abstract Set<String> eval(TSimbolos tabla);
    public abstract void evaluar(Set<Integer> elementos);

}
