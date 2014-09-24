/*
 * 26.07.2014
 */
package org.synyx.sytim.spring.boot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "GMT")
    public Date from;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "GMT")
    public Date to;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
    public Date when;
}
