/*
 * SurveyObject.java
 *
 * Created on September 12, 2004, 9:51 PM
 */

package org.JCaiF;
/**
 *
 * @author  User
 */
public interface SurveyObject extends java.lang.Cloneable {
    public SurveyWindow window = null;
    public SurveyEngine engine = null;
    
    public String getId();
    
    public void setId(String id);

    public String getValue();

    public void setValue(String v);

    public void init(SurveyWindow w, SurveyEngine e);

    public SurveyObject clone();
}
