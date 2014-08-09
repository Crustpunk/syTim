/*
 * 01.08.2014
 */

package org.synyx.sytim.spring.boot.bu;

/**
 * Business Unit for all sync relevant methods.
 *
 * @author  Joachim Arrasz synyx GmbH & Co. KG
 */
public class SyncBU {

    void writeSyncDataToPersistence(String content, String ident) {

        // first we create domain objects from content which is JSON .. so we wanna transform this out of the box. with spring data json support
        // second is writing the graph.
        // third: pattern matching between graph predesessor and successor to decide what to merge or diff.
        // fourth: we read the graph and send it back to client ,re merge
    }
}
