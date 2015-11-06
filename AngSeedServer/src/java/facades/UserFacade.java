package facades;

import deploy.DeploymentConfiguration;
import entity.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class UserFacade {

    private EntityManagerFactory emf;

    public UserFacade() {
        this(Persistence.createEntityManagerFactory("AngSeedServerPU"));
        //this(Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME));
        persistUsers();
    }

    public UserFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void persistUsers() {
        //Test Users
        User userThomas = new User("userThomas", "test");
        userThomas.AddRole("User");

        User adminLars = new User("adminLars", "test");
        adminLars.AddRole("Admin");

        User both = new User("user_adminIb", "test");
        both.AddRole("User");
        both.AddRole("Admin");

        EntityManager e = getEntityManager();

        try {
            e.getTransaction().begin();
            if (!doesUserExist(userThomas.getUserName())) {
                e.persist(userThomas);
            }
            if (!doesUserExist(adminLars.getUserName())) {
                e.persist(adminLars);
            }
            if (!doesUserExist(both.getUserName())) {
                e.persist(both);
            }
            e.getTransaction().commit();
        } finally {
            e.close();
        }
    }

    //Finds User by ID
    public User getUserByUserId(String username) {
        EntityManager em = getEntityManager();
        User u = em.find(User.class, username);
        return u;
    }

    //Creates new user in database
    public User createUser(User us) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (!doesUserExist(us.getUserName())) {
                em.persist(us);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return us;
    }

    public boolean deleteUser(String username) {
        EntityManager em = getEntityManager();
        if (doesUserExist(username)) {

            try {
                em.getTransaction().begin();
                User us = em.find(User.class, username);
                em.remove(us);
                em.getTransaction().commit();

            } finally {
                em.close();

            }
            return true;
        }

        return false;
    }

    public List<User> getUsers() {
        EntityManager em = getEntityManager();
        List<User> allUsers = null;
        try {
            Query query = em.createQuery("SELECT p FROM User p");
            allUsers = query.getResultList();
        } finally {
            em.close();
        }
        return allUsers;
    }

    public List<String> authenticateUser(String userName, String password) {
        User user = getUserByUserId(userName);
        return user != null && user.getPassword().equals(password) ? user.getRoles() : null;
    }

    public boolean doesUserExist(String username) {
        //UserFacade facade = new UserFacade();
        User checkUser = getUserByUserId(username);
        if (checkUser != null) {

            return true;
        }

        return false;
    }

}
