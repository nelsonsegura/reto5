package com.ciclo3.controller;
import com.ciclo3.model.Score;
import com.ciclo3.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/Score")
@CrossOrigin(origins = "*")
public class ScoreController {
    @Autowired
    ScoreService scoreService;

    @GetMapping("/all")
    public List<Score> getAllReservation() {
        return (List<Score>) scoreService.getAllScore();
    }
    @GetMapping("/{id}")
    public Optional<Score> getScoreById(@PathVariable Integer id) {
        return scoreService.getScoreById(id);
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Score saveScore(@RequestBody Score s) {
        return scoreService.saveScore(s);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED )
    public Score updateScore(@RequestBody Score s) {
        return scoreService.updateScore(s);
    };
    @DeleteMapping("/{id}")
    public boolean deleteScore(@PathVariable Integer id){
        return scoreService.deleteScore(id);
    }

}
