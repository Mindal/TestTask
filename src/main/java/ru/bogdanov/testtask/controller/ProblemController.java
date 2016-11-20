package ru.bogdanov.testtask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bogdanov.testtask.commons.Helper;
import ru.bogdanov.testtask.commons.SearchType;
import ru.bogdanov.testtask.model.Problem;
import ru.bogdanov.testtask.service.ProblemService;

import java.util.List;

@Controller
public class ProblemController {
    private final ProblemService problemService;

    @Autowired
    public ProblemController(@Qualifier(value = "problemService") ProblemService problemService) {
        this.problemService = problemService;
    }

    //Основной метод - показывает список с выбранной опцией поиска(Все, решенные, нерешенные) и на нужной странице(по умолчанию 0)
    @RequestMapping(value = "todo", method = RequestMethod.GET)
    public String listProblems(@RequestParam(required = false, value = "page", defaultValue = "0") int page,
                               Model model,
                               @RequestParam(required = false, value = "searchType", defaultValue = "ALL") String searchType) {
        model.addAttribute("problem", new Problem());
        List<Problem> problems = problemService.listProblems(SearchType.valueOf(searchType), page);
        model.addAttribute("listProblems", problems);
        model.addAttribute("page", page);
        model.addAttribute("searchType", searchType);
        model.addAttribute("maxPage", Helper.getMaxPage(problemService.listProblems(SearchType.valueOf(searchType))));
        return "todo";
    }


    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("problem") Problem problem) {
        if (problem.getId() == 0) {
            problemService.addProblem(problem);
        } else {
            problemService.updateProblem(problem);
        }

        return "redirect:/todo";
    }

    @RequestMapping("/remove/{id}")
    public String removeProblem(@PathVariable("id") int id) {
        problemService.removeProblem(id);

        return "redirect:/todo";
    }

    @RequestMapping("edit/{id}")
    public String editProblem(@PathVariable("id") int id, Model model) {
        model.addAttribute("problem", problemService.getProblemById(id));
        model.addAttribute("listProblems", problemService.listProblems(SearchType.ALL, 0));
        return "todo";
    }

}
