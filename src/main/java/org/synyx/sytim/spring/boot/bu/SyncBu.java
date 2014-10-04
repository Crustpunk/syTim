/*
 * 04.10.2014
 */
package org.synyx.sytim.spring.boot.bu;

import org.synyx.sytim.spring.boot.Projects;
import org.synyx.sytim.spring.boot.domain.Project;
import org.synyx.sytim.spring.boot.repo.ProjectRepository;

/**
 * Business Unit to handle with all projecs.
 *
 * @author Joachim Arrasz synyx GmbH & Co. KG
 */
public class SyncBu {

    Projects projects;
    ProjectRepository repo;

    public SyncBu(Projects projects) {
    }

    public SyncBu(Projects projects, ProjectRepository repo) {

        this.projects = projects;
        this.repo = repo;
    }

    public void saveProjects() {

        for (Project proj : projects.getProjects()) {
            proj = repo.save(proj);
        }
    }
}
