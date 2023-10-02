package edu.uog.thunbergTest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.uog.thunbergTest.mapper.OptionMapper;
import edu.uog.thunbergTest.mapper.QuestionMapper;
import edu.uog.thunbergTest.mapper.SurveyMapper;
import edu.uog.thunbergTest.model.entity.Option;
import edu.uog.thunbergTest.model.entity.Question;
import edu.uog.thunbergTest.model.entity.Survey;
import edu.uog.thunbergTest.service.QueryRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Description:
 * @Author: Xin Zhou(2756254z)
 * @Date: 2023/08/20/21:50
 */
@Service
public class QueryRateServiceImpl implements QueryRateService {

    @Autowired
    private SurveyMapper surveyMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private OptionMapper optionMapper;

    @Override
    public List<Survey> select(List<Integer> surveyId) {
        List<Survey> surveys = surveyMapper.selectBatchIds(surveyId);
        surveys.stream().forEach(survey -> {
            QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
            questionQueryWrapper.eq("survey_id",survey.getSurveyId());
            List<Question> questions = questionMapper.selectList(questionQueryWrapper);
            survey.setQuestionList(questions);

            if (CollectionUtils.isEmpty(questions)){
                throw new RuntimeException();
            }

            QueryWrapper<Option> optionWrapper = new QueryWrapper<>();
            optionWrapper.eq("question_id",6);
            List<Option> options = optionMapper.selectList(optionWrapper);

            questions.stream().forEach(question -> {
                question.setOptionList(options);
            });
        });
        return surveys;
    }
}
