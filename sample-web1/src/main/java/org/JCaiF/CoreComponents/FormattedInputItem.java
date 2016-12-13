/*
 * FormattedInputItem.java
 *
 * Created on October 19, 2006 10:20pm
 */

package org.JCaiF.CoreComponents;
import java.util.regex.PatternSyntaxException;

import org.JCaiF.ListItem;
import org.JCaiF.SurveyEngine;
import org.JCaiF.SurveyWindow;
/**
 *
 * @author  Michael Hall
 */
public class FormattedInputItem implements ListItem {
    public SurveyWindow window;
    public SurveyEngine engine;
    private String value = "";
    private String text = "";
    private String label;
    private boolean selected = false;
    private boolean exclusive = false;
    private String pattern = ".*";
    /** Creates a new instance of FormattedInputItem */
    public FormattedInputItem() {
        this("", "", ".*");
    }

    public FormattedInputItem(String v, String t, String pattern) {
        this.setValue(v);
        this.setText(t);
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
            System.out.println("FormattedInputItem Pattern Syntax Exception: "+pe);
            return false;
        }
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String s) {
        this.value = s;
    }

    public void setText(String t) {
        this.text = t;
    }

    public void setId(String l) {
        this.label = l;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
    }

    public boolean isSelected() {
        return selected;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return label;
    }
    
    public ListItem clone() {
        FormattedInputItem copy = new FormattedInputItem(this.getValue(), this.getText(), this.getPattern());
        copy.setExclusive(this.isExclusive());
        copy.setId(this.getId());
        return copy;
    }

    public String toString() {
        return this.getText() + this.getValue();
    }

    public void init(SurveyWindow w, SurveyEngine e) {
        this.window = w;
        this.engine = e;
    }
}
