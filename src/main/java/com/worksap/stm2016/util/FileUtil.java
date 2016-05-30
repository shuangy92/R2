package com.worksap.stm2016.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
//import com.pdfcrowd.Client;
//import com.pdfcrowd.PdfcrowdError;
import com.worksap.stm2016.audit.CurrentUser;
import com.worksap.stm2016.domain.recruitment.JobApplication;
import org.springframework.security.core.Authentication;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class FileUtil {
    public static void htmlToPdfFile(String path, String html) throws IOException, DocumentException {
       // try
        //{

        try {
            OutputStream file = new FileOutputStream(new File(path));
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
            InputStream is = new ByteArrayInputStream(html.getBytes());
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
            document.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

            /*FileOutputStream fileStream;
            Client client = new Client("peps", "882a43faa3800c66a7e731709e99295b");

            fileStream = new FileOutputStream(path);
            client.convertHtml(html, fileStream);
            fileStream.close();*/
        //}
       /* catch(PdfcrowdError why) {
            System.err.println(why.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }*/
    }

    public static String parseHtmlWithJobApplication(String html, JobApplication jobApplication, Authentication authentication) {
        html = html.replace("$[applicant_name]", jobApplication.getApplicant().getName());
        html = html.replace("$[job_title]", jobApplication.getJobPost().getJob().getTitle());
        html = html.replace("$[department]", jobApplication.getJobPost().getJob().getDepartment().getName());
        html = html.replace("$[location]", jobApplication.getJobPost().getJob().getDepartment().getLocation());
        html = html.replace("$[start_date]", jobApplication.getJobPost().getStartDate().toString());
        html = html.replace("$[end_date]", jobApplication.getJobPost().getEndDate().toString());
        html = html.replace("$[salary]", jobApplication.getJobPost().getSalary());
        html = html.replace("$[pay_rate]", jobApplication.getJobPost().getPayRate().toString());
        html = html.replace("$[my_name]", ((CurrentUser) authentication.getPrincipal()).getUser().getName());

        return html;
    }
}
