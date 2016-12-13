/*
 * TextDisplay.java
 *
 * Created on August 26, 2004, 2:09 PM
 */

package org.JCaiF.CoreComponents;
import java.util.regex.PatternSyntaxException;

import org.JCaiF.SurveyEngine;
import org.JCaiF.SurveyQuestion;
import org.JCaiF.SurveyWindow;

/**
 *
 * @author  Michael Hall
 */
public class FormattedInputQuestion extends TextInputQuestion {
    private String userInput = "";
    private String pattern = ".*";
    /** Creates a new instance of TextInputQuestion */
    public FormattedInputQuestion() {
        this("", "", ".*");
    }

    public FormattedInputQuestion(String label) {
        this(label, "", ".*");
    }

    public FormattedInputQuestion(String label, String text) {
        this(label, text, ".*");
    }

    public FormattedInputQuestion(String label, String text, String pattern) {
        this.setId(label);
        this.setText(text);
        this.setPattern(pattern);
    }

    public String getPattern() {
        return this.pattern;
    }

    public void setPattern(String p) {
        this.pattern = p;
    }

    public boolean checkFormat() {
        return this.checkFormat(this.getValue());
    }
    public boolean checkFormat(String value) {
        try {
            if (value.matches(this.getPattern())) {
                return true;
            } else {
                return false;
            }
        } catch (PatternSyntaxException pe) {
            System.out.println("FormattedInputQuestion Pattern Syntax Exception: "+pe);
            return false;
        }
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
        return this.checkFormat();
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
