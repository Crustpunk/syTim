/*
 * 27.07.2014
 */
package org.synyx.sytim.spring.boot.domain;

import org.neo4j.graphdb.Direction;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.HashSet;
import java.util.Set;


/**
 * Represents a SyTim Project.
 *
 * @author  Joachim Arrasz synyx GmbH & Co. KG
 */
@NodeEntity
public class Project {

    @GraphId
    public int graphId;

    public String name;
    public String ident;

    @RelatedTo(type = "TIMEENTRY", direction = Direction.OUTGOING)
    @Fetch
    public Set<TimeEntry> timeentries;

    public void addTimeEntry(TimeEntry entry) {

        if (timeentries == null) {
            timeentries = new HashSet<TimeEntry>();
        }

        timeentries.add(entry);
    }
}
