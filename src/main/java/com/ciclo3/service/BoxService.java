package com.ciclo3.service;

import com.ciclo3.model.Box;
import com.ciclo3.repository.BoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoxService {
    @Autowired
    private BoxRepository boxRepository;

    public List<Box> getAllBoxes() {
        return (List<Box>) boxRepository.getAllBoxes();
    }

    public Optional<Box> getBoxById(Integer id) {
        return boxRepository.getBoxById(id);
    }

    public Box saveBox(Box b) {
        if (b.getId() == null) {
            return boxRepository.saveBox(b);
        } else {
            Optional<Box> bx = boxRepository.getBoxById(b.getId());
            if (bx.isEmpty()) {
                return boxRepository.saveBox(b);
            } else {
                return b;
            }
        }
    }

    public Box updateBox(Box box) {
        if (box.getId() != null) {
            Optional<Box> e = boxRepository.getBoxById(box.getId());
            if (!e.isEmpty()) {
                if (box.getDescription() != null) {
                    e.get().setDescription(box.getDescription());
                }
                if (box.getCapacity() != null) {
                    e.get().setCapacity(box.getCapacity());
                }
                if (box.getName() != null) {
                    e.get().setName(box.getName());
                }
                if (box.getLocation() != null) {
                    e.get().setLocation(box.getLocation());
                }
                boxRepository.saveBox(e.get());
                return e.get();
            }
        }
        return box;
    }

    public boolean deleteBox(Integer id) {
        Boolean d = getBoxById(id).map(box -> {
            boxRepository.deleteBox(box);
            return true;
        }).orElse(false);
        return d;
    }
}
