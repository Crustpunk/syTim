/*
 * 27.07.2014
 */
package org.synyx.sytim.spring.boot.domain;

import java.util.HashSet;
import java.util.Set;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * Represents a SyTim Project.
 *
 * @author Joachim Arrasz synyx GmbH & Co. KG
 */
@NodeEntity
public class Project {

    @GraphId
    public Long graphId;

    public String ident;

    public String project;

    @RelatedTo(type = "TIMEENTRY", direction = Direction.OUTGOING)
    @Fetch
    @JsonSerialize
    public Set<TimeEntry> timeentries;

    /**
     * Adds the Timentry to this project.
     *
     * @param entry
     */
    public void addTimeEntry(TimeEntry entry) {

        if (timeentries == null) {
            timeentries = new HashSet<TimeEntry>();
        }

        timeentries.add(entry);
    }
}
