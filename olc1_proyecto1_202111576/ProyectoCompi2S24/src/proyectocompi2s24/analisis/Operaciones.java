package proyectocompi2s24.analisis;

public class Operaciones {
    public Operaciones() {
    }
    public static double Neg(double op1){
        return -op1;
    }
    public static double Suma(double op1, double op2){
        return op1 + op2;
    }
    public static double Resta(double op1, double op2){
        return op1 - op2;
    }
    public static double Mult(double op1, double op2){
        return op1 * op2;
    }
    public static double Div(double op1, double op2){
        if(op2 == 0){
            System.out.println("Error: Division por 0");
            return 0;
        }else{
            return op1 / op2;
        }
    }
    public static double CastValue(String op1){
        return Double.parseDouble(op1);
    }
}
