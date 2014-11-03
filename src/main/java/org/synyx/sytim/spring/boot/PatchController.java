/*
 * 02.11.2014
 */
package org.synyx.sytim.spring.boot;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.synyx.sytim.spring.boot.domain.Project;
import org.synyx.sytim.spring.boot.repo.ProjectRepository;

/**
 * Instruments Spring sync JSONPatch Controller.
 * 
 * @author Joachim Arrasz synyx GmbH & Co. KG
 */
@RestController
public class PatchController {
    
    
    @Autowired
    ProjectRepository repo;
    
    @RequestMapping(value = "/data", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    @ResponseBody
    public String getDataForIdent(@RequestParam String identJSON) {
        
        
        return convertReturnValue(repo.findByIdent(identJSON));
    }

    private String convertReturnValue(List<Project> findByIdent) {
        //TODO Jackson serialisation.
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
