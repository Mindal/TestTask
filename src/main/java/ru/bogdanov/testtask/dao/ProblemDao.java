package ru.bogdanov.testtask.dao;


import ru.bogdanov.testtask.commons.SearchType;
import ru.bogdanov.testtask.model.Problem;

import java.util.List;

public interface ProblemDao {
    void addProblem(Problem problem);

    void updateProblem(Problem problem);

    Problem getProblemById(int id);

    void removeProblem(int id);


    @SuppressWarnings("unchecked")
    List<Problem> listProblems(SearchType searchType, int page);

    List<Problem> listProblems(SearchType searchType);
}
