/*
 * TextDisplay.java
 *
 * Created on August 26, 2004, 2:09 PM
 */

package org.JCaiF.CoreComponents;
import org.JCaiF.SurveyEngine;
import org.JCaiF.SurveyQuestion;
import org.JCaiF.SurveyWindow;

/**
 *
 * @author  Michael Hall
 */
public class TextInputQuestion extends SimpleSurveyQuestion {
    private String userInput = "";
    /** Creates a new instance of TextInputQuestion */
    public TextInputQuestion() {
        this("", "");
    }

    public TextInputQuestion(String label) {
        this(label, "");
    }

    public TextInputQuestion(String label, String text) {
        this.setId(label);
        this.setText(text);
    }

    public String getValue() {
        return userInput;
    }

    public void setValue(String s) {
        userInput = s;
    }

    public void init(SurveyWindow w, SurveyEngine e) {
        this.window = w;
        this.engine = e;
    }
    
    public boolean onLoad() {
        return true;
    }
    
    public boolean onContinue() {
        return true;
    }
    
    public SurveyQuestion clone() {
        TextInputQuestion copy = new TextInputQuestion();
        copy.setId(this.getId());
        copy.setParent(this.getParent());
        copy.setText(this.getText());
        copy.setValue(this.getValue());
        return copy;
    }

}
