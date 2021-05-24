package com.assignment3.contract;

import com.assignment3.Constants;
import com.assignment3.rental.model.Rental;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.*;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class Contract {

    public static void export(Rental rental){
        createContract(rental);
    }

    private static void createContract(Rental rental) {
        try {
            Document document = new Document();

            OutputStream outputStream =
                    new FileOutputStream(new File("contract.pdf"));

            PdfWriter.getInstance(document, outputStream);

            document.open();

            LocalDate now = LocalDate.now();

            document.add(new Paragraph("                                                            Contract"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("The following car will be rented: " + rental.getCar().getManufacturer() + " "
                + rental.getCar().getModel() + ", year " + rental.getCar().getYear()));
            document.add(new Paragraph("Starting date: " + rental.getFromDate().format(Constants.formatter)
                + ", Ending date: " + rental.getUntilDate().format(Constants.formatter)));
            document.add(new Paragraph("Price: " + rental.getPrice() + "$"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Date: " + now.format(Constants.formatter)));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("                                                                                                        Signature: "));

            document.close();
            outputStream.close();

            System.out.println("Pdf created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.COURIER, 12);
            contentStream.beginText();
            contentStream.showText("This is the contract, it will contain information like: car details - " +
                    rental.getCar().getManufacturer() + rental.getCar().getModel() + ", client - " +
                    rental.getCustomer().getName() + ", CNP - " + rental.getCustomer().getName() +
                    "rents the car from " + rental.getFromDate().format(Constants.formatter) +
                    " until " + rental.getUntilDate().format(Constants.formatter));
            contentStream.endText();
            contentStream.close();

            document.save("contract.pdf");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public static void delete() {
        File file = new File(Constants.contractName);
        file.delete();
    }
}
