package com.ciclo3.repository;

import com.ciclo3.model.Box;
import com.ciclo3.repository.crud.BoxCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BoxRepository {
    @Autowired
    private BoxCrudRepository boxCrudRepository;

    public List<Box> getAllBoxes(){
        return (List<Box>) boxCrudRepository.findAll();
    }
    public Optional<Box> getBoxById(Integer id){
        return boxCrudRepository.findById(id);
    }
    public Box saveBox(Box b){
        return boxCrudRepository.save(b);
    }
    public void deleteBox(Box b){
        boxCrudRepository.delete(b);
    }

}
