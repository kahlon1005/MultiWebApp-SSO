/*
 * SingleSelectQuestion.java
 *
 * Created on December 5, 2004, 12:48 AM
 */

package org.JCaiF.CoreComponents;
import org.JCaiF.ListItem;
import org.JCaiF.SurveyQuestion;
/**
 *
 * @author  Michael Hall
 */
public class SingleSelectQuestion extends ResponseListQuestion {
    
    public boolean onContinue() {
        return (this.getResponseList().getSelection().size() > 0) ? true : false;
    }

    public ListItem getSelected() {
        return this.getResponseList().getSelection().getItemByIndex(0);
    }
    public SurveyQuestion clone() {
        SingleSelectQuestion copy = new SingleSelectQuestion();
        copy.setId(this.getId());
        copy.setParent(this.getParent());
        copy.setText(this.getText());
        copy.setResponseList(this.getResponseList().clone());
        return copy;
    }
}
