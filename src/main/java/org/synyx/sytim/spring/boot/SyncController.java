package org.synyx.sytim.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.synyx.sytim.spring.boot.bu.SyncBu;
import org.synyx.sytim.spring.boot.repo.ProjectRepository;

@RestController
public class SyncController {

    @Autowired
    ProjectRepository repo;

    /**
     * @param projects
     *
     * @return JSON
     */
    @RequestMapping(value = "/sync", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    @ResponseBody
    public String sync(@RequestBody Projects projects) {

        System.out.println(projects.getProjects().length);

        SyncBu syncer = new SyncBu(projects, repo);
        syncer.saveProjects();

        return "{\"response:ok\"}";
    }
}
