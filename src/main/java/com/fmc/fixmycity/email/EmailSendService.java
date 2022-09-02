package com.fmc.fixmycity.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;

@Service
public class EmailSendService {

    @Autowired
    private JavaMailSender mailSender;

    Random rand = new Random();

    private final HashSet<Otp> otpDB = new HashSet<>();

    public void sendSimpleEmail(String toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("gauravshukla156@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);

        System.out.println("Mail sent Successfully to :" + toEmail);
    }

    public int generateOtp(){
        return rand.nextInt(9999);
    }

    public void sendOtp(String email) {
        int newOtp = generateOtp();
        sendSimpleEmail(email, "OTP for account verification", "OTP is : "+ newOtp);
        Otp otp = new Otp();
        otp.setEmail(email);
        otp.setOtp(newOtp);
        otpDB.add(otp);

        System.out.println("OTP : "+otp+" sent successfully");
    }

    public String verifyOtp(Otp givenOtp) {
        int actualOtp = 0;
        for (Otp o : otpDB) {
            if (o.getEmail().equals(givenOtp.getEmail())) {
                actualOtp = o.getOtp();
            }
        }
        String gOtp = String.valueOf(givenOtp.getOtp());
        if (givenOtp.getOtp() == actualOtp || gOtp.equals(givenOtp.getOtp())) {
            System.out.println("OTP verified");
            otpDB.remove(givenOtp);
            return "verified";
        }
        else{
            System.out.println("Wrong OTP");
            return "failed";
        }
    }
}
