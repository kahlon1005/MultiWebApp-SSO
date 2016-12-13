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
public class TextDisplay extends SimpleSurveyQuestion {
    public SurveyWindow window;
    public SurveyEngine engine;
    
    /** Creates a new instance of TextDisplay */
    public TextDisplay() {
        this("", "");
    }

    public TextDisplay(String label) {
        this(label, "");
    }

    public TextDisplay(String label, String text) {
        this.setId(label);
        this.setText(text);
    }

    public void init(SurveyWindow w, SurveyEngine e) {
        this.window = w;
        this.engine = e;
    }

    public SurveyQuestion clone() {
        TextDisplay copy = new TextDisplay();
        copy.setId(this.getId());
        copy.setParent(this.getParent());
        copy.setText(this.getText());
        return copy;
    }

}
