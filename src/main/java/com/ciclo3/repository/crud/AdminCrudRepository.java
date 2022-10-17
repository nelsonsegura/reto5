package com.ciclo3.repository.crud;

import com.ciclo3.model.Admin;
import com.ciclo3.model.Box;
import org.springframework.data.repository.CrudRepository;

public interface AdminCrudRepository extends CrudRepository <Admin, Integer>  {
}
