/*
 * SimpleResponseList.java
 *
 * Created on December 4, 2004, 11:57 PM
 */

package org.JCaiF.CoreComponents;
import java.util.ArrayList;
import java.util.Collections;

import org.JCaiF.ListItem;
import org.JCaiF.ResponseList;
import org.JCaiF.SurveyEngine;
import org.JCaiF.SurveyWindow;

/**
 *
 * @author  Michael Hall
 */
public class SimpleResponseList implements ResponseList {
    public SurveyWindow window;
    public SurveyEngine engine;
    private ArrayList list;
    private String label;
    /** Creates a new instance of SimpleResponseList */
    public SimpleResponseList() {
        list = new ArrayList();
    }

    public int getItemIndex(ListItem i) {
        for(int x = 0; x < list.size(); x++) {
            ListItem item = (ListItem)list.get(x);
            if (item.equals(i)) {
                return x;
            }
        }
        return -1;
    }

    public void setId(String l) {
        this.label = l;
    }

    public void setValue(String v) {
    }

    public void setSelection(ResponseList selection) {
    }

    public String toString() {
        String retValue = "";
        for(int i = 0; i < list.size(); i++) {
            ListItem item = (ListItem)list.get(i);
            retValue += item.toString() + "\n";
        }
        return retValue;
    }

    public String getId() {
        return this.label;
    }

    public ResponseList getSelection() {
        SimpleResponseList newlist = new SimpleResponseList();
        newlist.setId(this.getId());
        for(int i = 0; i < list.size(); i++) {
            ListItem item = (ListItem)list.get(i);
            if (item.isSelected()) {
                newlist.addListItem(item.clone());
            }
        }
        return newlist;
    }

    public void clearSelection() {
        for(int i = 0; i < list.size(); i++) {
            ListItem item = (ListItem)list.get(i);
            item.setSelected(false);
        }
    }

    public String getValue() {
        String retValue = "";
        String comma = "";
        for(int i = 0; i < list.size(); i++) {
            ListItem item = (ListItem)list.get(i);
            if (item.isSelected()) {
                retValue = retValue + comma + item.getValue();
                comma = ",";
            }
        }
        return retValue;
    }

    public ResponseList invertSelection() {
        SimpleResponseList newlist = new SimpleResponseList();
        newlist.setId(this.getId());
        for(int i = 0; i < list.size(); i++) {
            ListItem item = (ListItem)list.get(i);
            if (!item.isSelected()) {
                newlist.addListItem(item.clone());
            }
        }
        return newlist;
    }

    public ResponseList randomize() {
        SimpleResponseList newlist = new SimpleResponseList();
        newlist.setId(this.getId());
        ArrayList ran = this.list;
        Collections.shuffle(ran);
        for(int i = 0; i < ran.size(); i++) {
            newlist.addListItem(((ListItem)ran.get(i)).clone());
        }
        return newlist;
    }

    public ResponseList reverse() {
        SimpleResponseList newlist = new SimpleResponseList();
        newlist.setId(this.getId());
        ArrayList rev = this.list;
        Collections.reverse(rev);
        for(int i = 0; i < rev.size(); i++) {
            newlist.addListItem(((ListItem)rev.get(i)).clone());
        }
        return newlist;
    }

    public int size() {
        return this.list.size();
    }

    public void addResponseList(ResponseList l)  {
        for(int i = 0; i < l.size(); i++) {
            ListItem item = l.getItemByIndex(i);
            list.add(item);
        }
    }

    public void addResponseListBefore(ResponseList l, int index)  {
        for(int i = 0; i < l.size(); i++) {
            ListItem item = l.getItemByIndex(i);
            list.add(index++, item);
        }
    }

    public void addListItem(ListItem i)  {
        list.add(i);
    }

    public void addListItemBefore(ListItem i, int index)  {
        list.add(index, i);
    }

    public void removeListItem(ListItem i)  {
        int index = list.lastIndexOf(i);
        if (index == -1)
            return;
        list.remove(index);
        list.trimToSize();
    }

    public ListItem getItemByText(String text) {
        for(int i = 0; i < list.size(); i++) {
            ListItem item = (ListItem)list.get(i);
            if (item.getText().equals(text))
                return item;
        }
        return null;
    }

    public ListItem getItemByLabel(String label) {
        for(int i = 0; i < list.size(); i++) {
            ListItem item = (ListItem)list.get(i);
            if (item.getId().equals(label))
                return item;
        }
        return null;
    }

    public ListItem getItemByIndex(int index) {
        return (ListItem)list.get(index);
    }
    
    public boolean containsText(String text) {
        for(int i = 0; i < list.size(); i++) {
            ListItem item = (ListItem)list.get(i);
            if (item.getText().equals(text))
                return true;
        }
        return false;
    }

    public ArrayList getItems() {
        return list;
    }

    public boolean containsLabel(String label) {
        for(int i = 0; i < list.size(); i++) {
            ListItem item = (ListItem)list.get(i);
            if (item.getId().equals(label))
                return true;
        }
        return false;
    }

    public boolean containsValue(String value) {
        for(int i = 0; i < list.size(); i++) {
            ListItem item = (ListItem)list.get(i);
            if (item.getValue().equals(value))
                return true;
        }
        return false;
    }

    public void init(SurveyWindow w, SurveyEngine e) {
        this.window = w;
        this.engine = e;
    }
    public ResponseList clone() {
        SimpleResponseList copy = new SimpleResponseList();
        copy.setId(this.getId());

        for(int i = 0; i < this.size(); i++) {
            ListItem l = this.getItemByIndex(i);
            copy.addListItem(l.clone());
        }
        return copy;
    }
}
