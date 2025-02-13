package reports;

import com.afro.concursofx.model.DDBBQuery;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;;

public class ReportGenerating {
    public void generateReport(Connection conn) {
        try {

            // Define the output file path
            String filePath = "REPORTS/reporte_resultados.pdf";

            // Crea un documento PDF y un PdfDocument asociado
            PdfWriter writer = new PdfWriter(Files.newOutputStream(Paths.get(filePath)));
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Título del reporte
            Paragraph title = new Paragraph("REPORTE DE TODOS LOS RESULTADOS DE NUESTRA APLICACION")
                    .setFontSize(16);
            title.setTextAlignment(TextAlignment.CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));  // Salto de línea

            // Crear la tabla con 2 columnas
            Table table = new Table(2); // 2 columnas

            // Establecer los anchos de las columnas en porcentaje
            table.setWidth(UnitValue.createPercentValue(100)); // Hace que la tabla ocupe el 100% del ancho disponible

            // Agregar los encabezados de la tabla
            table.addCell(new Cell().add(new Paragraph("USUARIO")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("PUNTOS")).setTextAlignment(TextAlignment.CENTER));

            // Obtener los datos de la base de datos
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(DDBBQuery.QUERY_SELECT_ALL_RESULTADO.get());

            // Agregar los datos de la base de datos a la tabla
            while (rs.next()) {
                table.addCell(new Cell().add(new Paragraph(rs.getString("USUARIO"))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(rs.getString("VALOR"))).setTextAlignment(TextAlignment.LEFT));
            }

            // Añadir la tabla al documento PDF
            document.add(table);

            // Cerrar el documento
            document.close();

            System.out.println("Reporte generado exitosamente.");

            // Obtener la ruta absoluta del archivo
            String absolutePath = Paths.get(filePath).toAbsolutePath().toString();
            System.out.println("El reporte se ha generado en: " + absolutePath);

            // Abrir la carpeta que contiene el archivo PDF
            File folder = new File(absolutePath).getParentFile();
            if (folder.exists() && folder.isDirectory()) {
                Desktop.getDesktop().open(folder);  // Abre la carpeta en el explorador de archivos
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // Método para establecer la conexión con la base de datos SQLite
    public static Connection connect() {
        try {
            // Asegúrate de tener el driver JDBC de SQLite en tu proyecto maven
            //  String url = "jdbc:sqlite:data/bbdd_prueba.db";
            Connection conn = DriverManager.getConnection(DDBBQuery.URL_DATABASE.get());
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // Conectar a la base de datos y generar el reporte
        Connection conn = connect();
        if (conn != null) {
            ReportGenerating generator = new ReportGenerating();
            generator.generateReport(conn);
        } else {
            System.out.println("Error al conectar a la base de datos.");
        }
    }
}
