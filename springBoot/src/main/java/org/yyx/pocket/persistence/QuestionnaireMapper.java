package org.yyx.pocket.persistence;


import org.springframework.stereotype.Repository;
import org.yyx.pocket.domain.Questionnaire;

import java.util.List;

@Repository
public interface QuestionnaireMapper {
    void insertQuestionnaire(Questionnaire questionnaire);
    void deleteQuestionnaire(String questionnaireId);
    String getRecentQuestionnaireId();
    List<Questionnaire> getQuestionnaireList();
}
