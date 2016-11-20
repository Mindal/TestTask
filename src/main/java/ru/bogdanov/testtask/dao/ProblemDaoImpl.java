package ru.bogdanov.testtask.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Repository;
import ru.bogdanov.testtask.commons.Helper;
import ru.bogdanov.testtask.commons.SearchType;
import ru.bogdanov.testtask.model.Problem;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProblemDaoImpl implements ProblemDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void addProblem(Problem problem) {
        Session session = sessionFactory.getCurrentSession();
        session.save(problem);
    }

    @Override
    public void updateProblem(Problem problem) {
        Session session = sessionFactory.getCurrentSession();
        session.update(problem);
    }

    @Override
    public Problem getProblemById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Problem.class, id);
    }

    @Override
    public void removeProblem(int id) {
        Session session = sessionFactory.getCurrentSession();
        Problem problem = session.load(Problem.class, id);
        if (problem != null) {
            session.delete(problem);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Problem> listProblems(SearchType searchType, int page) {
        PagedListHolder holder = Helper.getPagedListHolder(page, listProblems(searchType));
        return holder.getPageList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Problem> listProblems(SearchType searchType) {
        Session session = sessionFactory.getCurrentSession();
        List<Problem> list = session.createQuery("from Problem").list();
        switch (searchType) {
            case ALL:
                return list;
            case SOLVED: {
                return list.stream().filter(Problem::isDone).collect(Collectors.toList());
            }
            case UNSOLVED: {
                return list.stream().filter((problem) -> !problem.isDone()).collect(Collectors.toList());
            }
        }
        return null;
    }


}
