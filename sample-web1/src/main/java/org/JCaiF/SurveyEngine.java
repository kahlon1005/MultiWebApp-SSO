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
public interface SurveyEngine {
    
    public SurveyComponent getMainComponent();
    
    public SurveyQuestion getCurrentQuestion();
    
    public void setCurrentQuestion(SurveyQuestion q);

    public void registerVariable(String name, Object variable);

    public String parseText(String input);
}
