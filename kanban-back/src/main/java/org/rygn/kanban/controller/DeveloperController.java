package org.rygn.kanban.controller;

import org.rygn.kanban.domain.Developer;
import org.rygn.kanban.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="*", allowedHeaders = "*",maxAge = 3600, methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.OPTIONS,RequestMethod.PATCH})
public class DeveloperController {
    @Autowired
    private DeveloperService developerService;

    @GetMapping("/developers")
    List<Developer> findAllDevelopers(){
        return this.developerService.findAllDevelopers();
    }


}
