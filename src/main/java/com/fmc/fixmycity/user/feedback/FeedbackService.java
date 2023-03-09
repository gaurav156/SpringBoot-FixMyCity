package com.fmc.fixmycity.user.feedback;

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
public class FeedbackService {
    public String createFeedback(Feedback feedback) throws ExecutionException, InterruptedException {
        feedback.setFeedbackID(generateFeedbackID());

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

        feedback.setDay(day);
        feedback.setMonth(month);
        feedback.setYear(year);
        feedback.setHour(hour);
        feedback.setMinute(minute);
        feedback.setSecond(second);
        feedback.setTime(time);
        feedback.setDate(new Date());

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("feedbacks").document(feedback.getFeedbackID()).set(feedback);

        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Feedback getFeedback(String feedbackID) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("feedbacks").document(feedbackID);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        Feedback feedback;
        if(document.exists()){
            feedback = document.toObject(Feedback.class);
            return feedback;
        }
        else{
            return null;
        }
    }

    public List<Feedback> getFeedbacks() throws ExecutionException, InterruptedException {
        List<Feedback> feedbacks = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("feedbacks").get().get().forEach(d -> feedbacks.add(d.toObject(Feedback.class)));
        return Collections.unmodifiableList(feedbacks);
    }

    public List<Feedback> getFeedbacksByEmail(String email) throws ExecutionException, InterruptedException {
        List<Feedback> feedbacks = new ArrayList<>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        dbFirestore.collection("feedbacks").whereEqualTo("email", email).get().get().forEach(d -> feedbacks.add(d.toObject(Feedback.class)));
        return Collections.unmodifiableList(feedbacks);
    }

    public String generateFeedbackID() throws ExecutionException, InterruptedException {
        int ind = getFeedbacks().size();
        int id = ind;
        if(ind > 0) {
            id = Integer.parseInt(getFeedbacks().get(ind - 1).getFeedbackID()) +1;
        }
        if(id < 10){
            id= 10;
        }
        while (getFeedback(String.valueOf(id)) != null){
            id++;
        }
        return String.valueOf(id);
    }
}
