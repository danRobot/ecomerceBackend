package com.ciclo4.app.repository;

import java.util.List;
import java.util.Optional;

import com.ciclo4.app.model.Users;
import com.ciclo4.app.repository.crud.UsersCrudRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepository {
    private static final String COLLECTION = "users";
    @Autowired
    private UsersCrudRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;
    /**
     * Operaciones GET
     */
    public List<Users> listAll() {
        return repository.findAll();
    }
    public Optional<Users> getUser(String email) {
        return repository.getUserByEmail(email);
    }
    public Optional<Users> getUserbyId(Integer id) {
        return repository.findById(id);
    }
    public Optional<Users> getUserId(String idf) {
        return repository.getUserByidentification(idf);
    }
    public List<Users> getUserByMonth(String month){
        /*int nmo;
        List<User> allUsers=repository.findAll();
        List<User> byMonth=new ArrayList<User>();
        for (User user : allUsers) {
            nmo=user.month();
            System.out.println(user.getBirthtDay());
            System.out.println(nmo);
            if(nmo==month){
                byMonth.add(user);
            }
        }
        return byMonth;*/
        return repository.getUserByMonthBirthDay(month);
    }
    public Users checkUser(String email, String password) {
        Optional<Users> optional=repository.checkUser(email, password);
        if(optional.isEmpty()){
            return new Users();
        }else{
            return optional.get();
        }
    }
    private long checkExistence(String key,String property) {
        Query query = new Query();
        query.addCriteria(Criteria.where(key).is(property));
        return mongoTemplate.count(query, COLLECTION);
    }
    /**
     * Operacion POST
     * @param user
     * @return
     */
    public Optional<Users> postUser(Users user){
        Optional<Users> u=null;
        String email= user.getEmail();
        u=repository.getUserByEmail(email);
        if(u.isEmpty()){
            if (user.getId()==null) {
                List<Users> all=repository.findAll();
                int maxId=0;
                for (Users user2 : all) {
                    if(user2.getId()>maxId){
                        maxId=user2.getId();
                    }
                }
                user.setId(maxId+1);
            }
            return Optional.of(repository.insert(user));
        }else{
            return Optional.empty();
        }
    }
    /**
     * Operacion PUT
     * @param user
     * @return
     */
    public Users putUser(Users user) {
        String id=user.getIdentification();
        long users=checkExistence("identification", id);
        if (users>0) {
            Integer newId=repository.getUserByidentification(id).get().getId();
            user.setId(newId);
            repository.save(user);
            //user=null;
            return user;
        } else {
            return new Users();
        }
    }
    /**
     * Operacion delete
     * @param user
     * @return
     */
    public void deleteUser(Integer id) {
        repository.deleteById(id);

    }
    public void deleteUsers(List<Integer> ids) {
        repository.deleteAllById(ids);
    }
    /**
     * Limpia colección de Usuarios
     */
    public void deleteAll(){
        repository.deleteAll();;
    }
}
