package proyectocompi2s24.contracts;

import proyectocompi2s24.tsimbolos.TSimbolos;

import javax.swing.*;

public abstract class IStatement extends IGraphicable{

    public abstract void execute(TSimbolos tabla, JTextArea consola);
}

