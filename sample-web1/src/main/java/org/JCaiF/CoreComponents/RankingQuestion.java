/*
 * RankingQuestion.java
 *
 * Created on January 29, 2005
 */

package org.JCaiF.CoreComponents;
import org.JCaiF.ListItem;
import org.JCaiF.ResponseList;
/**
 *
 * @author  Michael Hall
 */
public class RankingQuestion extends MultiSelectQuestion {
    SimpleResponseList rank = new SimpleResponseList();

    public ResponseList getRanking() {
        return rank;
    }

    public void addToRank(ListItem i) {
        //System.out.println("addToRank: " + i.getText());
        rank.addListItem(i);
    }

    public void removeFromRank(ListItem i) {
        //System.out.println("removeFromRank: " + i.getText());
        rank.removeListItem(i);
    }

    public String getValue() {
        //System.out.println(this.getClass() + ".toString()");
        String v = "", comma = "";
        if (rank.size() > 0) {
            for (int i = 0; i < rank.size(); i++) {
                v += comma + rank.getItemByIndex(i).getValue();
                comma = ",";
            }
            return v;
        } else {
            return "";
        }
    }

    public String toString() {
        //System.out.println(this.getClass() + ".toString()");
        String v = "", comma = "";
        if (rank.size() > 0) {
            for (int i = 0; i < rank.size(); i++) {
                v += comma + rank.getItemByIndex(i).toString();
                comma = ",";
            }
            return v;
        } else {
            return "[NULL]";
        }
    }

}
