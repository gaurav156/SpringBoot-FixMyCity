package com.fmc.fixmycity.user.report;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class ReportService {
    public String createReport(Report report) throws ExecutionException, InterruptedException {
        report.setReportID(generateReportID());

        Date date = new Date();
        Calendar now = Calendar.getInstance();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        SimpleDateFormat formatDate = new SimpleDateFormat("h:mm a");
        String time = formatDate.format(new Date()).toString();

        report.setDay(day);
        report.setMonth(month);
        report.setYear(year);
        report.setHour(hour);
        report.setMinute(minute);
        report.setSecond(second);
        report.setTime(time);
        report.setDate(new Date());

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("reports").document(report.getReportID()).set(report);

        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Report getReport(String reportID) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("reports").document(reportID);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        Report report;
        if(document.exists()){
            report = document.toObject(Report.class);
            return report;
        }
        else{
            return null;
        }
    }

    public List<Report> getReports() throws ExecutionException, InterruptedException {
        List<Report> reports = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("reports").get().get().forEach(d -> reports.add(d.toObject(Report.class)));
        return Collections.unmodifiableList(reports);
    }

    public List<Report> getReportsByEmail(String email) throws ExecutionException, InterruptedException {
        List<Report> reports = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("reports").whereEqualTo("email", email).get().get().forEach(d -> reports.add(d.toObject(Report.class)));
        return Collections.unmodifiableList(reports);
    }

    public String generateReportID() throws ExecutionException, InterruptedException {
        int ind = getReports().size();
        int id = ind;
        if(ind > 0) {
            id = Integer.parseInt(getReports().get(ind - 1).getReportID()) +1;
        }
        if(id < 10){
            id= 10;
        }
        while (getReport(String.valueOf(id)) != null){
            id++;
        }
        return String.valueOf(id);
    }
}
