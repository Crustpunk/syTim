/*
 * 26.07.2014
 */

package org.synyx.samples.spring.boot.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

import java.util.Date;


/**
 * Represents a TimeEntry for SyTim.
 *
 * @author  Joachim Arrasz synyx GmbH & Co. KG
 */
@NodeEntity
public class TimeEntry {

    @GraphId
    public int graphId;

    public String Comment;
    public Date from;
    public Date to;
    public Long duration;
}
