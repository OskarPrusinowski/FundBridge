package pl.hackyeah.hackyeah24_backend.services;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.hackyeah.hackyeah24_backend.exceptions.rest.RestNotFoundException;
import pl.hackyeah.hackyeah24_backend.models.BaseModel;
import pl.hackyeah.hackyeah24_backend.repositories.CrudRepository;

import java.util.List;

@Service
@Primary
public class CrudService<T extends CrudRepository,S extends BaseModel>  {

    protected T repository;

    @Autowired
    public CrudService(T repository) {
        this.repository = repository;
    }

    public List<S> findAll() {
        return repository.findAll();
    }

    @Transactional
    public S findById(int id)  {
        try {
            return (S) repository.findById(id).orElseThrow(()->new RestNotFoundException(id));
        }catch (Throwable e){
            throw new RestNotFoundException(id);
        }
    }

    @Transactional
    public S create(S t) {
        t.setId(0);
        return (S)repository.save(t);
    }

    @Transactional
    public S update(S t, int id) {
        if(!repository.existsById(id)){
            throw new RestNotFoundException(id);
        }
        t.setId(id);
        return (S)repository.save(t);
    }

    @Transactional
    public void delete(int id)  {
        S t = findById(id);
        repository.delete(t);
    }
}