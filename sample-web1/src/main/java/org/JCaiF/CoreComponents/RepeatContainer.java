/*
 * RepeatContainer.java
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
public class RepeatContainer implements ComponentContainer {
    public SurveyWindow window;
    public SurveyEngine engine;
    private String name = "";
    private String label = "";
    private String text = "";
    private IterationContainer questions;
    private ComponentContainer parent;
    private int questionIndex = -1;
    private ArrayList iterations;
    private int maxIterations = 5;
    private int currentIteration = 0;
    
    /** Creates a new instance of RepeatContainer */
    public RepeatContainer() {
        this.questions = new IterationContainer();
        this.questions.setParent(this);
        this.iterations = new ArrayList();
    }

    public void setIterations(int i) {
        this.maxIterations = i;
    }

    public int getIterations() {
        return this.maxIterations;
    }

    public int getCurrentIteration() {
        return this.currentIteration;
    }

    public void setCurrentIteration(int i) {
        this.currentIteration = i;
    }

    public int size() {
        return this.iterations.size();
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
        questions.setValue(v);
    }

    public void setId(String l) {
        this.label = l;
    }

    public void addSurveyComponent(SurveyComponent c) {
        this.questions.addSurveyComponent(c);
    }

    public void addSurveyComponentBefore(SurveyComponent c, int index) {
        this.questions.addSurveyComponentBefore(c, index);
    }

    public SurveyComponent getSurveyComponent(int index) {
        SurveyComponent c = (SurveyComponent)iterations.get(index);
        return c;
    }

    public List getComponents() {
        return this.iterations;
    }

    public int getComponentIndex(SurveyComponent c) {
        //System.out.println("Checking component index of "+this.getId()+": "+c+"\n");
        if (c != null) {
            //System.out.println("Finding index of "+c.getId()+"\n");
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
        return this.questions.getValue();
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
        //System.out.println("Initializing RepeatContainer "+this.getId()+"\n");
        for (int i = 1; i <= this.maxIterations; i++) {
            IterationContainer iter = (IterationContainer)this.questions.clone();
            iter.setId(this.getId()+"["+i+"]");
            iter.setIteration(i);
            this.iterations.add(iter);
            System.err.println("Initializing "+iter.getId());
            iter.init(w, e);
        }
    }

    public ComponentContainer clone() {
        RepeatContainer copy = new RepeatContainer();
        copy.setId(this.getId());
        copy.setParent(this.getParent());
        copy.setText(this.getText());
        copy.setIterations(this.getIterations());

        for(int i = 0; i < this.size(); i++) {
            SurveyComponent c = this.getSurveyComponent(i).clone();
            copy.addSurveyComponent(c.clone());
        }
        return copy;
    }
}
