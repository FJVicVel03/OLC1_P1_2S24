package proyectocompi2s24.errores;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Errores {

    public String tipo;
    public String desc;
    public int linea;
    public int col;
    public static LinkedList<Errores> errorList = new LinkedList<>();

    public Errores(String tipo, String desc, int linea, int col){
        this.tipo = tipo;
        this.desc = desc;
        this.linea = linea;
        this.col = col;
    }

    @Override
    public String toString(){
        return "Error " + tipo + " en la linea " + linea + " y columna " + col + ": " + desc;
    }

    public static void generarReportePDF() {
        StringBuilder pdfContent = new StringBuilder();
        pdfContent.append("<!DOCTYPE html>\n");
        pdfContent.append("<html>\n");
        pdfContent.append("<head>\n");
        pdfContent.append("<title>Reporte de Errores</title>\n");
        pdfContent.append("<style>\n");
        pdfContent.append("table {\n");
        pdfContent.append("    font-family: Arial, sans-serif;\n");
        pdfContent.append("    border-collapse: collapse;\n");
        pdfContent.append("    width: 100%;\n");
        pdfContent.append("}\n");
        pdfContent.append("th, td {\n");
        pdfContent.append("    border: 1px solid #dddddd;\n");
        pdfContent.append("    text-align: left;\n");
        pdfContent.append("    padding: 8px;\n");
        pdfContent.append("}\n");
        pdfContent.append("tr:nth-child(even) {\n");
        pdfContent.append("    background-color: #f2f2f2;\n");
        pdfContent.append("}\n");
        pdfContent.append("</style>\n");
        pdfContent.append("</head>\n");
        pdfContent.append("<body>\n");
        pdfContent.append("<h1>Reporte de Errores</h1>\n");
        pdfContent.append("<table>\n");
        pdfContent.append("<tr>\n");
        pdfContent.append("<th>Tipo</th>\n");
        pdfContent.append("<th>Descripción</th>\n");
        pdfContent.append("<th>Línea</th>\n");
        pdfContent.append("<th>Columna</th>\n");
        pdfContent.append("</tr>\n");

        for (Errores error : errorList) {
            pdfContent.append("<tr>\n");
            pdfContent.append("<td>").append(error.tipo).append("</td>\n");
            pdfContent.append("<td>").append(error.desc).append("</td>\n");
            pdfContent.append("<td>").append(error.linea).append("</td>\n");
            pdfContent.append("<td>").append(error.col).append("</td>\n");
            pdfContent.append("</tr>\n");
        }

        pdfContent.append("</table>\n");
        pdfContent.append("</body>\n");
        pdfContent.append("</html>\n");

        String rutaArchivo = "C:\\Users\\SuperUser\\Desktop\\Repositorio Local\\2S2024\\olc1_proyecto1_202111576\\reporte_errores.html";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            writer.write(pdfContent.toString());
            System.out.println("Se ha generado el reporte de errores correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo de errores: " + e.getMessage());
        }
    }
}