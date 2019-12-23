package ru.javawebinar.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.vote.model.Menu;
import ru.javawebinar.vote.repository.MenuRepo;

import java.util.List;

@Service
public class MenuService {
    private final MenuRepo repo;

    @Autowired
    public MenuService(MenuRepo repo) {
        this.repo = repo;
    }

    public List<Menu> getAll(){
        return repo.findAll();
    }

    public Menu get(int id){
        return repo.findById(id);
    }
}
