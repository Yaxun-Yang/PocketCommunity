package org.yyx.pocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yyx.pocket.domain.Questionnaire;
import org.yyx.pocket.persistence.QuestionnaireMapper;

import java.util.List;

@Service
public class QuestionnaireService {

    @Autowired
    private QuestionnaireMapper questionnaireMapper;

    @Transactional
    public String insertQuestionnaire(Questionnaire questionnaire)
    {
        questionnaireMapper.insertQuestionnaire(questionnaire);
        return questionnaireMapper.getRecentQuestionnaireId();
    }

    public void deleteQuestionnaire(String questionnaireId)
    {
        questionnaireMapper.deleteQuestionnaire(questionnaireId);
    }

    public List<Questionnaire> getQuestionnaireList()
    {
        return questionnaireMapper.getQuestionnaireList();
    }


}
