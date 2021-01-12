package mvcspringapp.controllers;

import mvcspringapp.dao.CatDAO;
import mvcspringapp.models.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cats")
public class CatsController {

    private final CatDAO catDao;

    @Autowired
    public CatsController(CatDAO catDao) {
        this.catDao = catDao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("cats", catDao.index());
        return "cats/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("cat", catDao.show(id));
        return "/cats/show";
    }

    @GetMapping("/new")
    public String newCat(@ModelAttribute("cat") Cat cat) {
        return "cats/new";
    }

    @PostMapping
    public String create(@ModelAttribute("cat") Cat cat) {
        catDao.save(cat);
        return "redirect:/cats";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("cat", catDao.show(id));
        return "cats/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("cat") Cat cat, @PathVariable("id") int id) {
        catDao.update(id, cat);
        return "redirect:/cats";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id) {
        catDao.delete(id);
        return "redirect:/cats";
    }
}
