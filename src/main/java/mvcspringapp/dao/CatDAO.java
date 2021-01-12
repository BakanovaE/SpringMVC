package mvcspringapp.dao;

import mvcspringapp.models.Cat;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CatDAO {
    private static int CATS_COUNT;
    private List<Cat> cats;

    {
        cats = new ArrayList<>();
        cats.add(new Cat(++CATS_COUNT, "Tom"));
        cats.add(new Cat(++CATS_COUNT, "Murka"));
        cats.add(new Cat(++CATS_COUNT, "Fluffy"));
        cats.add(new Cat(++CATS_COUNT, "JerryTheCat"));
    }

    public List<Cat> index() {
        return cats;
    }
    public Cat show(int id) {
        return cats.stream().filter(cat -> cat.getId() == id).findAny().orElse(null);
    }
    public void save(Cat cat) {
        cat.setId(++CATS_COUNT);
        cats.add(cat);
    }

    public void update(int id, Cat updatedCat) {
        Cat catToBeUpdated = show(id);
        catToBeUpdated.setName(updatedCat.getName());
    }

    public void delete(int id) {
        cats.removeIf(cat -> cat.getId() == id);
    }
}
