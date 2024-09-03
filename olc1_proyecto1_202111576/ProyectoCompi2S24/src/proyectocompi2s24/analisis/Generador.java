package proyectocompi2s24.analisis;

public class Generador {
    public static void main(String[] args) {
        generarCompilador();
    }

    public static void generarCompilador(){
        try{
            String ruta = "src/proyectocompi2s24/analisis/";
            String Flex[] = {ruta + "lexico.jflex", "-d", ruta};
            jflex.Main.generate(Flex);
            String Cup[] = {"-destdir", ruta, "-parser", "parser", ruta
                    + "sintactico.cup"};
            java_cup.Main.main(Cup);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
