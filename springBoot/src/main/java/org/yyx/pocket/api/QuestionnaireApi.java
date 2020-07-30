package org.yyx.pocket.api;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yyx.pocket.domain.Questionnaire;
import org.yyx.pocket.domain.ResponseTemplate;
import org.yyx.pocket.service.QuestionnaireService;

@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireApi {

    @Autowired
    private QuestionnaireService questionnaireService;

    @PostMapping("/questionnaire")
    public ResponseTemplate insertQuestionnaire(@RequestBody JSONObject req)
    {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setUrl(req.getString("url"));

        JSONObject data = new JSONObject();
        data.put("questionnaireId",  questionnaireService.insertQuestionnaire(questionnaire));

        return ResponseTemplate.builder()
                .data(data)
                .status(200)
                .statusText("OK")
                .build();
    }

    @DeleteMapping("/questionnaire")
    public ResponseTemplate deleteQuestionnaire(@RequestParam String questionnaireId)
    {
        questionnaireService.deleteQuestionnaire(questionnaireId);
        return ResponseTemplate.builder()
                .status(200)
                .statusText("OK")
                .build();
    }

    @GetMapping("/questionnaireList")
    public ResponseTemplate getQuestionnaireList()
    {
        JSONObject data = new JSONObject();
        data.put("questionnaireList", questionnaireService.getQuestionnaireList());
        return ResponseTemplate.builder()
                .data(data)
                .status(200)
                .statusText("OK")
                .build();
    }
}
