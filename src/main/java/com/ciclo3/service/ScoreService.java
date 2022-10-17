package com.ciclo3.service;

import com.ciclo3.model.Score;
import com.ciclo3.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {
    @Autowired
    ScoreRepository scoreRepository;

    public List<Score> getAllScore() {
        return (List<Score>) scoreRepository.getAllScore();
    }

    public Optional<Score> getScoreById(Integer id) {
        return scoreRepository.getScoreById(id);
    }

    public Score saveScore(Score s) {
        if (s.getId() == null) {
            return scoreRepository.saveScore(s);
        } else {
            Optional<Score> sc = scoreRepository.getScoreById(s.getId());
            if (sc.isEmpty()) {
                return scoreRepository.saveScore(s);
            } else {
                return s;
            }
        }
    }

    public Score updateScore(Score score) {
        if (score.getId() != null) {
            Optional<Score> e = scoreRepository.getScoreById(score.getId());
            if (!e.isEmpty()) {
                if (score.getScore() != null) {
                    e.get().setScore(score.getScore());
                }
                scoreRepository.saveScore(e.get());
                return e.get();
            }
        }
        return score;
    }

    public boolean deleteScore(Integer id) {
        Boolean d = getScoreById(id).map(score -> {
            scoreRepository.deleteScore(score);
            return true;
        }).orElse(false);
        return d;
    }


}
