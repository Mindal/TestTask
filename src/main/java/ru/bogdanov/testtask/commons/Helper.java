package ru.bogdanov.testtask.commons;

import org.springframework.beans.support.PagedListHolder;
import ru.bogdanov.testtask.model.Problem;

import java.util.List;

/**
 * Общий класс, пока 2 функции - находить максимальную страницу которую можно показывать при paging
 * и выдавать нужный список с PAGE_SIZE строчками на странице.
 *
 */
public class Helper {

    public static final int PAGE_SIZE = 5;

    public static int getMaxPage(List<Problem> problems) {
        return (problems.size() % 5 == 0 ? problems.size() / 5 - 1 : problems.size() / 5);
    }
    @SuppressWarnings("unchecked")
    public static PagedListHolder getPagedListHolder(int page, List<Problem> list) {
        PagedListHolder holder = new PagedListHolder(list);
        holder.setPageSize(PAGE_SIZE);
        holder.setPage(page);
        return holder;
    }

}
