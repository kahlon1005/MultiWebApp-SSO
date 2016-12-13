package com.web.login;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.JCaiF.ResponseList;
import org.JCaiF.SurveyComponent;
import org.JCaiF.CoreComponents.SimpleContainer;
import org.JCaiF.CoreComponents.SimpleResponseList;
import org.JCaiF.CoreComponents.SingleSelectQuestion;
import org.JCaiF.CoreComponents.TextListItem;

@Named("classification")
@ViewScoped
public class ClassificationController extends SimpleContainer implements Serializable {

	private static final long serialVersionUID = 7934702053668909263L;
	
	@PostConstruct
	public void init(){		 
		this.setId("Test1");
		this.setText("Loan Application");
		
		SingleSelectQuestion q1 = new SingleSelectQuestion();
		q1.setId("question1");
		q1.setText("Are you 19 or over? ....");
		q1.setValue("Yes");
		
		ResponseList q1resp = new SimpleResponseList();
		q1resp.addListItem(new TextListItem("2", "No"));
		q1resp.addListItem(new TextListItem("1", "Yes"));
		
		q1.setResponseList(q1resp);
		this.addSurveyComponent(q1);		
		
	}
	
	public List<SurveyComponent> getQuestions(){	
		 return (List<SurveyComponent>) this.getComponents();		 
	}

	
}
