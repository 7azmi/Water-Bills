
package WaterBills;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static WaterBills.User.count;
import static WaterBills.User.userName;

public class PDFWriter {
     //location
                public static final String DEST = "results/tables/nested_tables.pdf";
        
         public static void genaratePDF() throws FileNotFoundException, DocumentException, IOException{
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new PDFWriter().editPdf(DEST);
    }
         
    public void editPdf(String dest) throws IOException, DocumentException {
        //#Fonts 
        Font bold =FontFactory.getFont("c:/WINDOWS/Fonts/arial.ttf",BaseFont.IDENTITY_H,14,Font.BOLD);
        Font big =FontFactory.getFont("c:/WINDOWS/Fonts/arial.ttf",BaseFont.IDENTITY_H,26,Font.BOLD);
        //Set date Format
        LocalDate dateNow = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formattedDate = dateNow.format(formatter);
        Document document = new Document();
         
        try
      {
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        float[] columnWidths = {60f, 30f, 30f, 60f, 23f, 50f};
//wave 1
    //set Title and date        
        PdfPTable tableTitle = new PdfPTable(2);    tableTitle.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);    
        PdfPCell cell = new PdfPCell(new Paragraph(User.userName[0], big));  cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);   cell.setBorder(Rectangle.NO_BORDER);
        cell.setPadding(16);
        tableTitle.addCell(cell);
        cell = new PdfPCell(new Paragraph(formattedDate, bold)); cell.setRunDirection(PdfWriter.RUN_DIRECTION_LTR);   cell.setBorder(Rectangle.NO_BORDER);   
        tableTitle.addCell(cell);
        document.add(tableTitle);
    //set Data Table
        PdfPTable table = new PdfPTable(columnWidths);  table.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        buildNestedTables(table);
        document.add(table);
         // add a couple of blank lines
        document.add(new Paragraph("\n\n\n\n\n\n"));
//wave 2 (REPEAT loop)
        for (int i=1;i<userName.length;i++) {
            if(User.userName.length<2) break;
        BillsCounter.setName(i);
        BillsCounter.setUnitNumber();
        count();
        tableTitle = new PdfPTable(2);    tableTitle.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        document.add(tableTitle);
        cell = new PdfPCell(new Paragraph(User.userName[i], big));  cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);   cell.setBorder(Rectangle.NO_BORDER);
        cell.setPadding(16);
        tableTitle.addCell(cell);
        cell = new PdfPCell(new Paragraph(formattedDate, bold)); cell.setRunDirection(PdfWriter.RUN_DIRECTION_LTR);   cell.setBorder(Rectangle.NO_BORDER);   
        tableTitle.addCell(cell);
        document.add(tableTitle);
        table = new PdfPTable(columnWidths);    table.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        buildNestedTables(table);
        document.add(table);
         // add a couple of blank lines
        document.add(new Paragraph("\n\n\n\n\n\n"));
        }
        document.close();
      }
       catch (DocumentException e)
      {
         e.printStackTrace();
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }  
      }

    public void buildNestedTables(PdfPTable outerTable) throws DocumentException, IOException {
        //SET Nested tables && set Direction RTL
        PdfPTable read = new PdfPTable(1);        read.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            PdfPTable readLN = new PdfPTable(2);        readLN.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
                PdfPTable unitNumber = new PdfPTable(2);        unitNumber.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        PdfPTable diff = new PdfPTable(1);        diff.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
           PdfPTable diff2 = new PdfPTable(1);        diff2.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);

        PdfPTable average = new PdfPTable(1);        average.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            PdfPTable PartsOfMonth = new PdfPTable(3);        PartsOfMonth.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
                PdfPTable averageValue = new PdfPTable(1);        averageValue.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        PdfPTable value = new PdfPTable(1);           value.setRunDirection(PdfWriter.RUN_DIRECTION_RTL); 
        PdfPTable tax = new PdfPTable(1);        tax.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        PdfPTable total = new PdfPTable(1);        total.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        
        //set Fonts
        Font normal =FontFactory.getFont("c:/WINDOWS/Fonts/arial.ttf",BaseFont.IDENTITY_H,14);
        Font bold =FontFactory.getFont("c:/WINDOWS/Fonts/arial.ttf",BaseFont.IDENTITY_H,14,Font.BOLD);
        Font redBold =FontFactory.getFont("c:/WINDOWS/Fonts/arial.ttf",BaseFont.IDENTITY_H,14,Font.BOLD);
        redBold.setColor(BaseColor.RED);
        Font blueBold =FontFactory.getFont("c:/WINDOWS/Fonts/arial.ttf",BaseFont.IDENTITY_H,14,Font.BOLD);
        blueBold.setColor(BaseColor.BLUE);
        
        //To String
        String lastRead=Integer.toString(User.lastUnit);
        String newRead=Integer.toString(User.newUnit);
        String diffRead=Integer.toString(User.units);
        String avg1=Integer.toString(User.average1);
        String avg2=Integer.toString(User.average2);
        String avg3=Integer.toString(User.average3);
        String tAvg=Integer.toString(User.averageValue);
        String taxRead=Integer.toString(User.tax);
        String valueRead=Integer.toString(User.value);
        
        DecimalFormat df = new DecimalFormat("#,###");
        String totalRead = df.format(User.fValue); 
        
        
        //Read Minu
        PdfPCell cell=new PdfPCell(new Paragraph("قراءة العداد", bold));
        cell.setPadding(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setGrayFill(.9f);
        read.addCell(cell);
        cell=new PdfPCell(new Paragraph("السابقة", bold));
        cell.setPadding(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        readLN.addCell(cell);
        cell=new PdfPCell(new Paragraph("الحالية", bold));
        cell.setPadding(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        readLN.addCell(cell);
        cell=new PdfPCell(new Paragraph(lastRead, normal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        readLN.addCell(cell);
        cell=new PdfPCell(new Paragraph(newRead, normal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        readLN.addCell(cell);
        
        read.addCell(unitNumber);
        read.addCell(readLN);
        cell = new PdfPCell(read);
        outerTable.addCell(cell);
        
        //DIFF Minu
        cell=new PdfPCell(new Paragraph("الفارق", bold));
        cell.setPadding(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setGrayFill(.9f);
        diff.addCell(cell);
        cell=new PdfPCell(new Paragraph(diffRead, redBold));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        diff2.addCell(cell);
        
        diff.addCell(diff2);
        cell = new PdfPCell(diff);
        outerTable.addCell(cell);
        
        //AVERAGE Minu
        cell=new PdfPCell(new Paragraph("متوسط سعر الوحدة", bold));
        cell.setPadding(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setGrayFill(.9f);
        average.addCell(cell);
        cell=new PdfPCell(new Paragraph(avg1, normal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        PartsOfMonth.addCell(cell);
        cell=new PdfPCell(new Paragraph(avg2, normal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        PartsOfMonth.addCell(cell);
        cell=new PdfPCell(new Paragraph(avg3, normal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        PartsOfMonth.addCell(cell);
        cell=new PdfPCell(new Paragraph(tAvg, redBold));
        cell.setPadding(8);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        averageValue.addCell(cell);
        
        average.addCell(PartsOfMonth);
        average.addCell(averageValue);
        cell = new PdfPCell(average);
        outerTable.addCell(cell);
        
         //VALUE Minu
        cell=new PdfPCell(new Paragraph("القيمة", bold));
        cell.setPadding(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setGrayFill(.9f);
        value.addCell(cell);
        cell=new PdfPCell(new Paragraph(valueRead, normal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        value.addCell(cell);
        
        cell = new PdfPCell(value);
        outerTable.addCell(cell);
        
        //TAX Minu
        cell=new PdfPCell(new Paragraph("الضريبة", bold));
        cell.setPadding(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setGrayFill(.9f);
        tax.addCell(cell);
        cell=new PdfPCell(new Paragraph(taxRead, normal));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        tax.addCell(cell);
        
        cell = new PdfPCell(tax);
        outerTable.addCell(cell);
        
        //TOTAL Minu
        cell=new PdfPCell(new Paragraph("إجمالي الكل", bold));
        cell.setPadding(12);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setGrayFill(.9f);
        total.addCell(cell);
        cell=new PdfPCell(new Paragraph(totalRead+" ريال", blueBold));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        total.addCell(cell);
         
        cell = new PdfPCell(total);
        outerTable.addCell(cell);
        /////////////////////

    }
}
