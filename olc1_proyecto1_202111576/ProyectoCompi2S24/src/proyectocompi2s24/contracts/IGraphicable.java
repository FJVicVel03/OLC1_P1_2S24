package proyectocompi2s24.contracts;

import proyectocompi2s24.analisis.Global;

public abstract class IGraphicable {
    protected final int id;

    public IGraphicable() {
        this.id = Global.idContador++;
    }
    public abstract String grafo();
    public int getId() {
        return id;
    }
}
