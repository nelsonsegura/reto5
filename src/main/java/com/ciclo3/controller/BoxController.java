package com.ciclo3.controller;

import com.ciclo3.model.Box;
import com.ciclo3.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Box")
@CrossOrigin(origins = "*")
public class BoxController {
    @Autowired
    private BoxService boxService;

    @GetMapping("/all")
    public List<Box> getAllBoxes(){
        return (List<Box>) boxService.getAllBoxes();
    }
    @GetMapping("/{id}")
    public Optional<Box> getBoxById(@PathVariable Integer id){
        return boxService.getBoxById(id);
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Box saveBox(@RequestBody Box b){
        return boxService.saveBox(b);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED )
    public Box updateBox(@RequestBody Box b) {
        return boxService.updateBox(b);
    };

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteBox(@PathVariable Integer id){
        return boxService.deleteBox(id);
    }

}
