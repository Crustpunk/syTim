/*
 * 14.08.2014
 */
package org.synyx.sytim.spring.boot;

import org.synyx.sytim.spring.boot.domain.Project;

/**
 * @author Joachim Arrasz synyx GmbH & Co. KG
 */
public class Projects {

    private String ident;

    private Project[] projects;

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
    public Project[] getProjects() {

        return projects;
    }

    /**
     * @param projects the projects to set
     */
    public void setProjects(Project[] projects) {

        this.projects = projects;
    }
}
