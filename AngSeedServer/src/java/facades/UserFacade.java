package facades;

import entity.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class UserFacade {

    private final Map<String, User> users = new HashMap<>();

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("AngSeedServerPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public UserFacade() {
        //Test Users
        User userThomas = new User("userThomas", "test");
        userThomas.AddRole("User");
        users.put(userThomas.getUserName(), userThomas);
        User adminLars = new User("adminLars", "test");
        adminLars.AddRole("Admin");
        users.put(adminLars.getUserName(), adminLars);

        User both = new User("user_adminIb", "test");
        both.AddRole("User");
        both.AddRole("Admin");
        users.put(both.getUserName(), both);

        EntityManager e = getEntityManager();

        try {
            e.getTransaction().begin();
            e.persist(userThomas);
            e.persist(adminLars);
            e.persist(both);
            e.getTransaction().commit();
        } finally {
            e.close();
        }
    }

    //Finds User by ID
    public User getUserById(String id) {
        EntityManager em = getEntityManager();
        User u = em.find(User.class, id);
        return u;
    }

    //Creates new user in database
    public User createUser(User us) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(us);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return us;
    }
    
        public List<User> getUsers() {
        EntityManager em = getEntityManager();
        List<User> allUsers = null;
        try {
            TypedQuery<User> query = em.createQuery("SELECT p FROM User p", User.class);
            allUsers = query.getResultList();
        } finally {
            em.close();
        }
        return allUsers;
    }       

    public List<String> authenticateUser(String userName, String password) {
        User user = users.get(userName);
        return user != null && user.getPassword().equals(password) ? user.getRoles() : null;
    }

}