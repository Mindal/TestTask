package ru.bogdanov.testtask.service;


import ru.bogdanov.testtask.commons.SearchType;
import ru.bogdanov.testtask.model.Problem;

import java.util.List;

public interface ProblemService {
    void addProblem(Problem problem);

    void updateProblem(Problem problem);

    Problem getProblemById(int id);

    void removeProblem(int id);



    List<Problem> listProblems(SearchType searchType, int page);

    List<Problem> listProblems(SearchType searchType);
}
