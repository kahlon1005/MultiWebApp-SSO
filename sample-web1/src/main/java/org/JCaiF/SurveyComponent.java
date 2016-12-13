package org.JCaiF;
/*
 * SurveyComponent.java
 *
 * Created on August 25, 2004, 9:59 PM
 */

/**
 *
 * @author  User
 */
public interface SurveyComponent extends SurveyObject {

    public ComponentContainer getParent();

    public void setParent(ComponentContainer p);

    public String getText();

    public void setText(String text);

    public boolean onLoad();

    public boolean onContinue();

    public SurveyComponent clone();
    
}
