package com.devon.refferal.controller;

import java.util.List;

import com.devon.refferal.entites.Applications;
import com.devon.refferal.services.ApplicationServices;

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
public class ApplicationContoller {

    @Autowired
    private ApplicationServices applicationServices;
    
    @GetMapping("/applications")
    public ResponseEntity<List<Applications>> getapplications(){
        List<Applications> list = applicationServices.getAllApplications();
        if(list.size() <= 0)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }
    @PostMapping("/applicationscreate")
    public ResponseEntity<Applications> addApplication(@RequestBody Applications application)
    {
        try{
            this.applicationServices.addApplications(application);
            System.out.println(application);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (Exception e) {
           
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/applicationsdelete/{appId}")
    public ResponseEntity<Void> deletapplication(@PathVariable("appId")int appId){
        try {
            this.applicationServices.deleteApplications(appId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
      
    @PutMapping("/applicationsupdate/{appId}")
    public ResponseEntity<Applications> updateApplication(@RequestBody Applications applications, @PathVariable("appId") int appId)
    {
        try{
            this.applicationServices.updateApplications(applications, appId);
            return ResponseEntity.ok().body(applications);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }


    }

