package ru.bogdanov.testtask.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bogdanov.testtask.commons.SearchType;
import ru.bogdanov.testtask.dao.ProblemDao;
import ru.bogdanov.testtask.dao.ProblemDaoImpl;
import ru.bogdanov.testtask.model.Problem;

import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {
    private ProblemDao problemDao;

    public void setProblemDao(ProblemDaoImpl problemDao) {
        this.problemDao = problemDao;
    }

    public ProblemDao getProblemDao() {
        return problemDao;
    }

    @Transactional
    @Override
    public void addProblem(Problem problem) {
        problemDao.addProblem(problem);
    }

    @Transactional
    @Override
    public void updateProblem(Problem problem) {
        problemDao.updateProblem(problem);
    }

    @Transactional
    @Override
    public Problem getProblemById(int id) {
        return problemDao.getProblemById(id);
    }

    @Transactional
    @Override
    public void removeProblem(int id) {
        problemDao.removeProblem(id);
    }

    @Transactional
    @Override
    public List<Problem> listProblems(SearchType searchType, int page) {
        return problemDao.listProblems(searchType, page);
    }

    @Transactional
    @Override
    public List<Problem> listProblems(SearchType searchType) {
        return problemDao.listProblems(searchType);
    }
}
