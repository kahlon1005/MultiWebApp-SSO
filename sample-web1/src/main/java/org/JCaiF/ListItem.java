/*
 * SelectItem.java
 *
 * Created on August 25, 2004, 10:29 PM
 */

package org.JCaiF;

/**
 *
 * @author  User
 */
public interface ListItem extends SurveyObject {
    
    public String getText();

    public void setText(String s);

    public boolean isSelected();

    public void setSelected(boolean selected);

    public boolean isExclusive();

    public void setExclusive(boolean exclusive);
    
    public ListItem clone();
}
