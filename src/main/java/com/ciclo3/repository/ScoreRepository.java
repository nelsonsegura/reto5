package com.ciclo3.repository;

import com.ciclo3.model.Score;
import com.ciclo3.repository.crud.ScoreCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ScoreRepository {
    @Autowired
    ScoreCrudRepository scoreCrudRepository;

    public List<Score> getAllScore(){
        return (List<Score>) scoreCrudRepository.findAll();
    }
    public Optional<Score> getScoreById(Integer id){
        return scoreCrudRepository.findById(id);
    }
    public Score saveScore(Score s){
        return scoreCrudRepository.save(s);
    }

    public void deleteScore(Score s){
        scoreCrudRepository.delete(s);
    }
}
