package com.fmc.fixmycity.user.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @RequestMapping(method = RequestMethod.POST, value = "/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String createReport(@RequestBody Report report) throws ExecutionException, InterruptedException {
        return reportService.createReport(report);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Report getReport(@RequestParam String feedbackID) throws ExecutionException, InterruptedException {
        return reportService.getReport(feedbackID);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Report> getReports() throws ExecutionException, InterruptedException {
        return reportService.getReports();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/email", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Report> getReportsByEmail(@RequestParam String email) throws ExecutionException, InterruptedException {
        return reportService.getReportsByEmail(email);
    }
}
