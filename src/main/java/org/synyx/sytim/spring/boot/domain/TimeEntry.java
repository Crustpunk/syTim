/*
 * 26.07.2014
 */
package org.synyx.sytim.spring.boot.domain;

import java.util.Date;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Represents a TimeEntry for SyTim.
 *
 * @author Joachim Arrasz synyx GmbH & Co. KG
 */
@NodeEntity
public class TimeEntry {

    @GraphId
    public int graphId;

    public String desc;
    public Date from;
    public Date to;
    public Date when;
}
