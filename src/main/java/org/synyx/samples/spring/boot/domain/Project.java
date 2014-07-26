/*
 * 27.07.2014
 */

package org.synyx.samples.spring.boot.domain;

import org.springframework.data.neo4j.annotation.NodeEntity;


/**
 * Represents a SyTim Project.
 *
 * @author  Joachim Arrasz synyx GmbH & Co. KG
 */
@NodeEntity
public class Project {

    public String name;
}
