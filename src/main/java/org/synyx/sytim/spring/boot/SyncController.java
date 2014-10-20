package org.synyx.sytim.spring.boot;

import org.apache.commons.logging.Log;
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

    Log logger;

    /**
     * @param projects
     *
     * @return JSON
     */
    @RequestMapping(value = "/sync", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    @ResponseBody
    public String sync(@RequestBody Projects projects) {

        logger.debug(projects.getProjects().length);

        SyncBu syncer = new SyncBu(projects, repo);
        syncer.saveProjects();
        logger.info("Saved " + projects.getProjects().length + " projects for ident: " + projects.getIdent());

        return "{\"response:ok\"}";
    }
}
