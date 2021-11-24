package jpa.java.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jpa.java.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional
public class UserDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;


    public List<User> getUser(){
        TypedQuery<User> getUser = entityManager.createQuery("SELECT us FROM User us", User.class);
        return getUser.getResultList();

    }

    public User getUserId(int id){

        TypedQuery<User> getUserId = entityManager.createQuery(
                "SELECT us FROM User us WHERE us.id =: id", User.class);
        getUserId.setParameter("id", id);
        return getUserId.getResultList().stream().findAny().orElse(new User("null", "null"));
    }

    public void save(User user){
        entityManager.persist(user);
    }

    public void update(int id, User user){
        User oldUser = getUserId(id);
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
    }

    public void delete(int id){

    }
}
