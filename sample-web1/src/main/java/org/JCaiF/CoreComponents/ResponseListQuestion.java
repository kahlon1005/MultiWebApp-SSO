/*
 * AbstractResponseListQuestion.java
 *
 * Created on December 4, 2004, 11:33 PM
 */

package org.JCaiF.CoreComponents;
import org.JCaiF.ResponseList;
import org.JCaiF.SurveyEngine;
import org.JCaiF.SurveyQuestion;
import org.JCaiF.SurveyWindow;

/**
 *
 * @author  Michael Hall
 */
public class ResponseListQuestion extends SimpleSurveyQuestion {
    public SurveyWindow window;
    public SurveyEngine engine;
    private ResponseList list;
    /** Creates a new instance of ResponseListQuestion */
    public ResponseListQuestion() {
        list = new SimpleResponseList();
    }
    
    public ResponseList getResponseList() {
        return list;
    }
    
    public void setResponseList(ResponseList l) {
        this.list = l;
    }
    
    public String getValue() {
        String v = "", comma = "";
        ResponseList selection = list.getSelection();
        if (selection.size() > 0) {
            for (int i = 0; i < selection.size(); i++) {
                v += comma + selection.getItemByIndex(i).getValue();
                comma = ",";
            }
            return v;
        } else {
            return "";
        }
    }

    public void setValue(String v) {
        for (int i = 0; i < this.list.size(); i++) {
            if (v.equals(this.list.getItemByIndex(i).getValue())) {
                this.list.getItemByIndex(i).setSelected(true);
            }
        }
    }

    public String toString() {
        String v = "", comma = "";
        ResponseList selection = list.getSelection();
        if (selection.size() > 0) {
            for (int i = 0; i < selection.size(); i++) {
                v += comma + selection.getItemByIndex(i).toString();
                comma = ",";
            }
            return v;
        } else {
            return "[NULL]";
        }
    }

    public boolean onLoad(){
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
        //System.out.println("Cloning ResponseListQuestion: "+this.getId()+"\n");
        ResponseListQuestion copy = new ResponseListQuestion();
        copy.setId(this.getId());
        copy.setParent(this.getParent());
        copy.setText(this.getText());
        copy.setResponseList(this.getResponseList().clone());
        return copy;
    }
}
