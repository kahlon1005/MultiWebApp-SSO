/*
 * ResponseList.java
 *
 * Created on August 25, 2004, 10:16 PM
 */

package org.JCaiF;

/**
 *
 * @author  User
 */
public interface ResponseList extends SurveyObject {
    
    public ResponseList getSelection();

    public ResponseList invertSelection();

    public ResponseList randomize();

    public ResponseList reverse();

    public void addResponseList(ResponseList l);

    public void addResponseListBefore(ResponseList l, int index);

    public void addListItem(ListItem i);

    public void addListItemBefore(ListItem i, int index);

    public void removeListItem(ListItem i);

    public boolean containsLabel(String label);

    public boolean containsText(String text);

    public boolean containsValue(String value);

    public ListItem getItemByLabel(String label);

    public ListItem getItemByText(String text);

    public ListItem getItemByIndex(int index);

    public int getItemIndex(ListItem i);

    public void setSelection(ResponseList selection);
    
    public void clearSelection();

    public int size();

    public ResponseList clone();
}
