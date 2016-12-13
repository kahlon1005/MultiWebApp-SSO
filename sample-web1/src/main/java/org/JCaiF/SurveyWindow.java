/*
 * ComponentContainer.java
 *
 * Created on August 26, 2004, 1:27 PM
 */

package org.JCaiF;

/**
 *
 * @author  User
 */
public interface SurveyWindow {
    
    public void alert(String msg);
    
    public boolean confirm(String msg);
    
    public String prompt(String msg);

}
