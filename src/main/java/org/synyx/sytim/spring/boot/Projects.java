/*
 * 14.08.2014
 */
package org.synyx.sytim.spring.boot;

import java.util.List;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.synyx.sytim.spring.boot.domain.Project;

/**
 * @author Joachim Arrasz synyx GmbH & Co. KG
 */
public class Projects {

    private String ident;

    private List projects;

    public Projects(String json) {
    }

    public Projects() {
    }

    /**
     * @return the ident
     */
    public String getIdent() {

        return ident;
    }

    /**
     * @param ident the ident to set
     */
    public void setIdent(String ident) {

        this.ident = ident;
    }

    /**
     * @return the projects
     */
    @JsonDeserialize
    public Project[] getProjects() {

        return (Project[]) projects.toArray();
    }

    /**
     * @param projects the projects to set
     */
    @JsonSerialize
    public void setProjects(List projects) {

        this.projects = projects;
    }
}
