package com.worksap.stm2016.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.worksap.stm2016.audit.CurrentUser;
import com.worksap.stm2016.domain.recruitment.JobApplication;
import org.springframework.security.core.Authentication;

import java.io.*;

public class FileUtil {
    public static void htmlToPdfFile(String path, String html) throws IOException, DocumentException {
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
