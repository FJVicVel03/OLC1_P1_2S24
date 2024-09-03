package proyectocompi2s24.ttokens;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class TablaTokens {
    private String tipo;
    private boolean booleano;
    private int linea;
    private int columna;
    private String lexema;
    public static LinkedList<TablaTokens> tokenList = new LinkedList<>();

    public TablaTokens(String tipo, boolean booleano, int linea, int columna, String lexema) {
        this.tipo = tipo;
        this.booleano = booleano;
        this.linea = linea;
        this.columna = columna;
        this.lexema = lexema;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isBooleano() {
        return booleano;
    }

    public void setBooleano(boolean booleano) {
        this.booleano = booleano;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    @Override
    public String toString() {
        return "Tipo: " + tipo + " Booleano: " + booleano + " Linea: " + linea + " Columna: " + columna + " Lexema: " + lexema;
    }

    public static void generarReporteHTML() {
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<!DOCTYPE html>\n");
        htmlContent.append("<html>\n");
        htmlContent.append("<head>\n");
        htmlContent.append("<title>Reporte de Tokens</title>\n");
        htmlContent.append("<style>\n");
        htmlContent.append("table {\n");
        htmlContent.append("    font-family: Arial, sans-serif;\n");
        htmlContent.append("    border-collapse: collapse;\n");
        htmlContent.append("    width: 100%;\n");
        htmlContent.append("}\n");
        htmlContent.append("th, td {\n");
        htmlContent.append("    border: 1px solid #dddddd;\n");
        htmlContent.append("    text-align: left;\n");
        htmlContent.append("    padding: 8px;\n");
        htmlContent.append("}\n");
        htmlContent.append("tr:nth-child(even) {\n");
        htmlContent.append("    background-color: #f2f2f2;\n");
        htmlContent.append("}\n");
        htmlContent.append("</style>\n");
        htmlContent.append("</head>\n");
        htmlContent.append("<body>\n");
        htmlContent.append("<h1>Reporte de Tokens</h1>\n");
        htmlContent.append("<table>\n");
        htmlContent.append("<tr>\n");
        htmlContent.append("<th>Tipo</th>\n");
        htmlContent.append("<th>Lexema</th>\n");
        htmlContent.append("<th>Línea</th>\n");
        htmlContent.append("<th>Columna</th>\n");
        htmlContent.append("</tr>\n");

        for (TablaTokens token : tokenList) {
            htmlContent.append("<tr>\n");
            htmlContent.append("<td>").append(token.getTipo()).append("</td>\n");
            htmlContent.append("<td>").append(token.getLexema()).append("</td>\n");
            htmlContent.append("<td>").append(token.getLinea()).append("</td>\n");
            htmlContent.append("<td>").append(token.getColumna()).append("</td>\n");
            htmlContent.append("</tr>\n");
        }

        htmlContent.append("</table>\n");
        htmlContent.append("</body>\n");
        htmlContent.append("</html>\n");

        String rutaArchivo = "C:\\Users\\SuperUser\\Desktop\\Repositorio Local\\2S2024\\olc1_proyecto1_202111576\\reporte_tokens.html";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            writer.write(htmlContent.toString());
            System.out.println("Se ha generado el reporte HTML correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo HTML: " + e.getMessage());
        }
    }
}