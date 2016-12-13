/*
 * TextDisplay.java
 *
 * Created on August 26, 2004, 2:09 PM
 */

package org.JCaiF.CoreComponents;
import java.util.regex.PatternSyntaxException;

import org.JCaiF.SurveyQuestion;
/**
 *
 * @author  Michael Hall
 */
public class NumberInputQuestion extends FormattedInputQuestion {
    private double max = 0;
    private double min = 0;
    /** Creates a new instance of TextInputQuestion */

    public boolean checkFormat(String value) {
        try {
            if (value.matches(this.getPattern())) {
                if (this.getMin() > Double.valueOf(value)) {
                    return false;
                }
                if (this.getMax() > this.getMin() && this.getMax() < Double.valueOf(value)) {
                    return false;
                }
            
                return true;
            } else {
                return false;
            }
        } catch (PatternSyntaxException pe) {
            System.out.println("FormattedInputQuestion Pattern Syntax Exception: "+pe);
            return false;
        }
    }

    public void setMax(double m) {
        this.max = m;
    }

    public double getMax() {
        return this.max;
    }

    public void setMin(double m) {
        this.min = m;
    }

    public double getMin() {
        return this.min;
    }

    public int intValue() {
        return Integer.valueOf(this.getValue());
    }

    public float floatValue() {
        return Float.valueOf(this.getValue());
    }

    public double doubleValue() {
        return Double.valueOf(this.getValue());
    }

    public SurveyQuestion clone() {
        NumberInputQuestion copy = new NumberInputQuestion();
        copy.setId(this.getId());
        copy.setParent(this.getParent());
        copy.setText(this.getText());
        copy.setPattern(this.getPattern());
        copy.setMin(this.getMin());
        copy.setMax(this.getMax());
        copy.setValue(this.getValue());
        return copy;
    }

}
