/*
 * 27.07.2014
 */

package org.synyx.samples.spring.boot.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import org.synyx.samples.spring.boot.domain.Project;


/**
 * Enables CRUD Operations on SyTim Projects.
 *
 * @author  Joachim Arrasz synyx GmbH & Co. KG
 */
public interface ProjectRepository extends GraphRepository<Project> {

    /**
     * Finder for projects by name.
     *
     * @param  name  to find
     * @param  ident  from the person which want to find the project.
     *
     * @return  the project with this name.
     */
    Project findByNameAndIdent(String name, String ident);
}
