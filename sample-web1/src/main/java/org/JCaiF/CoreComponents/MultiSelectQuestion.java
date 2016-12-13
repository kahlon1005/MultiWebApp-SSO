/*
 * MultiSelectQuestion.java
 *
 * Created on January 28, 2005
 */

package org.JCaiF.CoreComponents;
/**
 *
 * @author  Michael Hall
 */
public class MultiSelectQuestion extends ResponseListQuestion {
    
    public boolean onLoad(){
        return true;
    }
    
    public boolean onContinue() {
        return (this.getResponseList().getSelection().size() > 0) ? true : false;
    }

}
