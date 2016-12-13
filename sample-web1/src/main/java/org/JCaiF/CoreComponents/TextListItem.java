/*
 * TextListItem.java
 *
 * Created on December 5, 2004, 1:18 AM
 */

package org.JCaiF.CoreComponents;
import org.JCaiF.ListItem;
import org.JCaiF.SurveyEngine;
import org.JCaiF.SurveyWindow;
/**
 *
 * @author  Michael Hall
 */
public class TextListItem implements ListItem {
    public SurveyWindow window;
    public SurveyEngine engine;
    private String value;
    private String text;
    private String label;
    private boolean selected = false;
    private boolean exclusive = false;
    /** Creates a new instance of TextListItem */
    public TextListItem() {
        this("", "");
    }

    public TextListItem(String v, String t) {
        this.setValue(v);
        this.setText(t);
    }

    public void setValue(String v) {
        this.value = v;
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

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return label;
    }
    
    public ListItem clone() {
        TextListItem copy = new TextListItem(this.getValue(), this.getText());
        copy.setExclusive(this.isExclusive());
        copy.setId(this.getId());
        return copy;
    }

    public void init(SurveyWindow w, SurveyEngine e) {
        this.window = w;
        this.engine = e;
    }
    
    public String toString() {
        return text;
    }
}
