/*
 * IterationContainer.java
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
 * @author  Michael Hall
 */
public class IterationContainer implements ComponentContainer {
    public SurveyWindow window;
    public SurveyEngine engine;
    private String name = "";
    private String label = "";
    private String text = "";
    private ArrayList questions;
    private ComponentContainer parent;
    private int questionIndex = -1;
    public int iteration = 0;
    
    /** Creates a new instance of IterationContainer */
    public IterationContainer() {
        this.questions = new ArrayList();
    }

    public void setIteration(int i) {
        this.iteration = i;
    }

    public int getIteration() {
        return this.iteration;
    }

    public int size() {
        return this.questions.size();
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
        try {
            RepeatContainer parent = (RepeatContainer)this.getParent();
            parent.setCurrentIteration(this.getIteration());
        } catch (ClassCastException e) {
            // Parent is not a RepeatContainer, bad programmer!
        }
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
        //System.out.println("Initializing IterationContainer: "+this.getId()+"\n");
        for(int i = 0; i < this.size(); i++) {
            SurveyComponent c = this.getSurveyComponent(i);
            c.init(w, e);
        }
    }

    public ComponentContainer clone() {
        //System.out.println("Cloning IterationContainer: "+this.getId()+"\n");
        IterationContainer copy = new IterationContainer();
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
