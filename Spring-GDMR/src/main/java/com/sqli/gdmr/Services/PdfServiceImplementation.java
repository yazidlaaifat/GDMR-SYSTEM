package com.sqli.gdmr.Services;


import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.sqli.gdmr.Models.Antecedant;
import com.sqli.gdmr.Models.DossierMedical;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class PdfServiceImplementation implements PdfService {

    @Override
    public byte[] generatePdf(DossierMedical dossierMedical, List<Antecedant> antecedants, String collaborateurName, String collaborateurLastName) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument, PageSize.A4);
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

            // Add logo at the top
            InputStream logoStream = getClass().getResourceAsStream("/logo.png");  // Ensure the logo is in src/main/resources
            if (logoStream != null) {
                Image logo = new Image(ImageDataFactory.create(logoStream.readAllBytes()))
                        .setWidth(200)  // Adjusted width
                        .setHeight(200) // Adjusted height
                        .setHorizontalAlignment(HorizontalAlignment.CENTER)
                        .setMarginBottom(20);
                document.add(logo);
            }

            // Top line
            LineSeparator topLine = new LineSeparator(new SolidLine());
            topLine.setStrokeColor(new DeviceRgb(110, 50, 130));
            document.add(topLine);

            // Title
            Paragraph title = new Paragraph("Medical Dossier")
                    .setFont(font)
                    .setFontSize(20)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(10)
                    .setMarginBottom(10)
                    .setStrokeColor(new DeviceRgb(110, 50, 130))
                    .setBackgroundColor(new DeviceRgb(110, 50, 130))
                    .setFontColor(new DeviceRgb(255, 255, 255));

            document.add(title);

            // Bottom line
            LineSeparator bottomLine = new LineSeparator(new SolidLine());
            bottomLine.setStrokeColor(new DeviceRgb(110, 50, 130));
            document.add(bottomLine.setMarginBottom(30));

            // Collaborator Name
            Paragraph patientName = new Paragraph("Collaborateur: " + collaborateurName + " " + collaborateurLastName)
                    .setFont(font)
                    .setFontSize(16)
                    .setBold()
                    .setMarginBottom(10);
            document.add(patientName);

            // Patient Information Frame
            Div patientInfoDiv = new Div()
                    .setBorder(new SolidBorder(new DeviceRgb(110, 50, 130), 8))
                    .setPadding(10)
                    .setMarginBottom(20);

            Paragraph patientInfoTitle = new Paragraph("Patient Information")
                    .setFont(font)
                    .setFontSize(16)
                    .setBold()
                    .setMarginBottom(10)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setUnderline(1f, -1.5f);
            patientInfoDiv.add(patientInfoTitle);

            patientInfoDiv.add(new Paragraph("Gender: " + dossierMedical.getSexe())
                    .setFont(font)
                    .setFontSize(12));
            patientInfoDiv.add(new Paragraph("Height: " + dossierMedical.getHeight())
                    .setFont(font)
                    .setFontSize(12));
            patientInfoDiv.add(new Paragraph("Weight: " + dossierMedical.getWeight())
                    .setFont(font)
                    .setFontSize(12));
            patientInfoDiv.add(new Paragraph("BloodType: " + dossierMedical.getGroupeSanguin())
                    .setFont(font)
                    .setFontSize(12));
            patientInfoDiv.add(new Paragraph("Allergies: " + dossierMedical.getAllergies())
                    .setFont(font)
                    .setFontSize(12));

            document.add(patientInfoDiv);

            // Antecedents Frame
            Div antecedentsDiv = new Div()
                    .setBorder(new SolidBorder(new DeviceRgb(110, 50, 130), 8))
                    .setPadding(10)
                    .setMarginBottom(20);

            Paragraph antecedentsTitle = new Paragraph("Medical History")
                    .setFont(font)
                    .setFontSize(16)
                    .setBold()
                    .setMarginBottom(10)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setUnderline(1f, -1.5f);
            antecedentsDiv.add(antecedentsTitle);

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            for (Antecedant antecedant : antecedants) {
                String formattedDate = formatter.format(antecedant.getDateAntecedant());
                antecedentsDiv.add(new Paragraph(formattedDate + " " + antecedant.getDescription())
                        .setFont(font)
                        .setFontSize(12)
                        .setMarginBottom(5));
            }

            document.add(antecedentsDiv);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toByteArray();
    }
}
