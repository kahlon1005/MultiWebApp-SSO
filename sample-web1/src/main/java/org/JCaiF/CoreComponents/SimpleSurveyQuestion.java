/*
 * SimpleSurveyQuestion.java
 *
 * Created on December 4, 2004, 11:18 PM
 */

package org.JCaiF.CoreComponents;
import org.JCaiF.ComponentContainer;
import org.JCaiF.SurveyEngine;
import org.JCaiF.SurveyQuestion;
import org.JCaiF.SurveyWindow;

/**
 *
 * @author  Michael Hall
 */

public class SimpleSurveyQuestion implements SurveyQuestion {
    public SurveyWindow window;
    public SurveyEngine engine;
    private String value = "";
    private String text = "";
    private String id = "";
    private ComponentContainer parent;
    /** Creates a new instance of SimpleSurveyQuestion */
    public SimpleSurveyQuestion() {
        //System.out.println("Initializing SimpleSurveyQuestion\n");
    }

    public ComponentContainer getParent() {
        return this.parent;
    }

    public void setParent(ComponentContainer p) {
        this.parent = p;
    }

    public void setValue(String v) {
        this.value = v;
    }

    public void setText(String t) {
        this.text = t;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return this.getValue();
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return this.id;
    }
    
    public boolean onLoad() {
        return true;
    }

    public boolean onContinue() {
        return true;
    }

    public void init(SurveyWindow w, SurveyEngine e) {
        this.window = w;
        this.engine = e;
    }

    public SurveyQuestion clone() {
        //System.out.println("Cloning SimpleSurveyQuestion: "+this.getId()+"\n");
        SimpleSurveyQuestion copy = new SimpleSurveyQuestion();
        copy.setId(this.getId());
        copy.setParent(this.getParent());
        copy.setText(this.getText());
        return copy;
    }
}
