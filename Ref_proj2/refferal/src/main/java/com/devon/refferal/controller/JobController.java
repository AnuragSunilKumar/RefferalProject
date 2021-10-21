package com.devon.refferal.controller;

import java.util.List;
import java.util.Optional;

import com.devon.refferal.entites.Jobs;
import com.devon.refferal.services.JobServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    @Autowired
    private JobServices jobService;

    // get all jobs handler
    @GetMapping("/jobs")
    public ResponseEntity<List<Jobs>> getjobs() {

        List<Jobs> list = jobService.getAllJobs();
        if (list.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    // get single Jobs handler
    @GetMapping("/jobs/{id}")
    public ResponseEntity<Jobs> getjob(@PathVariable("id") int id) {
        Jobs Jobs = jobService.getJobById(id);
        if (Jobs == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(Jobs));
    }

    // new Jobs handler
    @PostMapping("/jobscreate")
    public ResponseEntity<Jobs> addJob(@RequestBody Jobs Jobs) {
       
        try {
           this.jobService.addjob(Jobs);
            System.out.println(Jobs);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
           
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    // delete Jobs handler

    @DeleteMapping("/jobsdelete/{jobId}")
    public ResponseEntity<Void> deletejob(@PathVariable("jobId") int jobId) {
        try {
            this.jobService.deleteJobs(jobId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    // update Jobs handler
    @PutMapping("/jobsupdate/{jobId}")
    public ResponseEntity<Jobs> updatejob(@RequestBody Jobs jobs, @PathVariable("jobId") int jobId) {
        try {
            this.jobService.updateJob(jobs, jobId);
            return ResponseEntity.ok().body(jobs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

       
    }

}


