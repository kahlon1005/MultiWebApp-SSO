/*
 * TimedContainer.java
 *
 * Created on April 1, 2006 5:05 PM
 */

package org.JCaiF.CoreComponents;
import java.util.Date;
/**
 *
 * @author  Michael Hall
 */
public class TimedContainer extends SimpleContainer {
    private Date startstamp = new Date(0);
    private Date endstamp = new Date(0);

    public boolean onLoad() {
        startstamp = new Date();
        return true;
    }

    public boolean onContinue() {
        endstamp = new Date();
        return true;
    }

    public long getStartTime() {
        return startstamp.getTime();
    }

    public long getEndTime() {
        return endstamp.getTime();
    }

    public long getTime() {
        return this.getEndTime() - this.getStartTime();
    }

}
