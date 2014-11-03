/*
 * 27.07.2014
 */
package org.synyx.sytim.spring.boot.repo;

import java.util.List;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.synyx.sytim.spring.boot.domain.Project;

/**
 * Enables CRUD Operations on SyTim Projects.
 *
 * @author Joachim Arrasz synyx GmbH & Co. KG
 */
public interface ProjectRepository extends GraphRepository<Project> {

    /**
     * Finder for projects by name.
     *
     * @param project to find
     * @param ident from the person which want to find the project.
     *
     * @return the project with this name.
     */
    Project findByProjectAndIdent(String project, String ident);

    /**
     * Finder for all projects for a given ident.
     * 
     * @param ident from the person
     * @return List of projects
     */
    public List<Project> findByIdent(String ident);
}
