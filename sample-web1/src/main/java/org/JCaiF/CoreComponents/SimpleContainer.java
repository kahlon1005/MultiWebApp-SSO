/*
 * SimpleQuestionnaire.java
 *
 * Created on December 5, 2004, 12:54 AM
 */

package org.JCaiF.CoreComponents;
import java.util.ArrayList;
import java.util.List;

import org.JCaiF.ComponentContainer;
import org.JCaiF.SurveyComponent;
import org.JCaiF.SurveyEngine;
import org.JCaiF.SurveyWindow;
/**
 *
 */
public class SimpleContainer implements ComponentContainer {
    public SurveyWindow window;
    public SurveyEngine engine;
    private String name = "";
    private String label = "";
    private String text = "";
    private ArrayList questions;
    private ComponentContainer parent;
    private int questionIndex = -1;
    
    /** Creates a new instance of SimpleQuestionnaire */
    public SimpleContainer() {
        this.questions = new ArrayList();
    }

    public int size() {
        return this.questions.size();
    }

    public void reset() {
        this.questionIndex = -1;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    public void setText(String text) {
        this.text = text;
    }
    
    public String getText() {
        return text;
    }
    public void setValue(String v) {
        for(int i = 0; i < questions.size(); i++) {
            SurveyComponent c = (SurveyComponent)questions.get(i);
            if (c.getId().equals(label))
                questionIndex = i;
        }
    }

    public void setId(String l) {
        this.label = l;
    }

    public void addSurveyComponent(SurveyComponent c) {
        c.setParent(this);
        this.questions.add(c);
    }

    public void addSurveyComponentBefore(SurveyComponent c, int index) {
        c.setParent(this);
        this.questions.add(index, c);
    }

    public SurveyComponent getSurveyComponent(int index) {
        SurveyComponent c = (SurveyComponent)questions.get(index);
        return c;
    }

    public List getComponents() {
        return this.questions;
    }

    public int getComponentIndex(SurveyComponent c) {
        if (c != null) {
            for(int i = 0; i < this.size(); i++) {
                SurveyComponent com = this.getSurveyComponent(i);
                if (com == c)
                    return i;
            }
        }
        return -1;
    }

    public SurveyComponent getComponentById(String label) {
        for(int i = 0; i < this.size(); i++) {
            SurveyComponent c = this.getSurveyComponent(i);
            if (c.getId().equals(label))
                return c;
        }
        return null;
    }

    public boolean onLoad() {
        return true;
    }

    public boolean onContinue() {
        return true;
    }

    public String getValue() {
        return (String)questions.get(questionIndex);
    }

    public String getId() {
        return this.label;
    }
 
    public ComponentContainer getParent() {
        return this.parent;
    }

    public void setParent(ComponentContainer p) {
        this.parent = p;
    }

    public void init(SurveyWindow w, SurveyEngine e) {
        this.window = w;
        this.engine = e;
        for(int i = 0; i < this.size(); i++) {
            SurveyComponent c = this.getSurveyComponent(i);
            System.err.println("Initializing "+c.getId());
            c.init(w, e);
        }
    }

    public ComponentContainer clone() {
        SimpleContainer copy = new SimpleContainer();
        copy.setId(this.getId());
        copy.setParent(this.getParent());
        copy.setText(this.getText());

        for(int i = 0; i < this.size(); i++) {
            SurveyComponent c = this.getSurveyComponent(i);
            copy.addSurveyComponent(c.clone());
        }
        return copy;
    }
}
