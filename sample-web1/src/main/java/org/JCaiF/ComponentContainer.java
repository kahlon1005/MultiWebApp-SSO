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
public interface ComponentContainer extends SurveyComponent {
    
    public java.util.List getComponents();
  
    public SurveyComponent getComponentById(String id);
    
    public SurveyComponent getSurveyComponent(int index);
    
    public int getComponentIndex(SurveyComponent c);
    
    public void addSurveyComponent(SurveyComponent c);
   
    public void addSurveyComponentBefore(SurveyComponent c, int index);
   
    public int size();

    public ComponentContainer clone();
}
