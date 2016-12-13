/*
 * TextDisplay.java
 *
 * Created on April 1, 2006 4:25 PM
 */

package org.JCaiF.CoreComponents;
import java.util.Date;

/**
 *
 * @author  Michael Hall
 */
public class Timestamp extends SimpleSurveyQuestion {
    private Date timestamp = new Date(0);

    public boolean onLoad() {
        this.timestamp = new Date();
        return false;
    }

    public boolean onContinue() {
        return true;
    }

    public String getValue() {
        return ""+this.timestamp.getTime();
    }

    public void setValue(String v) {

    }
}
