package procesamiento;

import java.io.FileOutputStream;
import java.io.IOException;

import java.io.FileOutputStream;
import java.io.IOException;

import java.io.FileOutputStream;
import java.io.IOException;

import java.io.FileOutputStream;
import java.io.IOException;

public class GeneradorPdf {

    public static void generatePdf(String data, String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            // PDF header
            fos.write("%PDF-1.7\n".getBytes());

            // PDF body
            String content = "1 0 obj\n" +
                             "<< /Type /Catalog /Pages 2 0 R >>\n" +
                             "endobj\n" +
                             "2 0 obj\n" +
                             "<< /Type /Pages /Kids [3 0 R] /Count 1 >>\n" +
                             "endobj\n" +
                             "3 0 obj\n" +
                             "<< /Type /Page /Parent 2 0 R /Resources << /Font << /F1 4 0 R >> >> /MediaBox [0 0 595 842] /Contents 5 0 R >>\n" +
                             "endobj\n" +
                             "4 0 obj\n" +
                             "<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica >>\n" +
                             "endobj\n" +
                             "5 0 obj\n" +
                             "<< /Length 44 >>\n" +
                             "stream\n" +
                             "BT\n" +
                             "/F1 12 Tf\n" +
                             "72 720 Td\n" +
                             "(" + data + ") Tj\n" +
                             "ET\n" +
                             "endstream\n" +
                             "endobj\n";

            fos.write(content.getBytes());

            // PDF cross-reference table
            String xref = "xref\n" +
                          "0 6\n" +
                          "0000000000 65535 f \n" +
                          "0000000017 00000 n \n" +
                          "0000000077 00000 n \n" +
                          "0000000174 00000 n \n" +
                          "0000000296 00000 n \n" +
                          "0000000388 00000 n \n";

            fos.write(xref.getBytes());

            // PDF trailer
            String trailer = "trailer\n" +
                             "<< /Size 6 /Root 1 0 R >>\n" +
                             "startxref\n" +
                             "473\n" +
                             "%%EOF";

            fos.write(trailer.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        generatePdf("Total a pagar: 1020.000 pesos", "./data/Factura.pdf");
        System.out.println("PDF generated successfully.");
    }
}

