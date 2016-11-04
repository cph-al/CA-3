package facades;

import security.IUserFacade;
import entity.User;
import entity.Role;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import security.IUser;
import security.PasswordStorage;

public class UserFacade implements IUserFacade
{

    EntityManagerFactory emf;

    public UserFacade(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    private EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    @Override
    public IUser getUserByUserId(String id)
    {
        EntityManager em = getEntityManager();
        try
        {
            return em.find(User.class, id);
        } finally
        {
            em.close();
        }
    }

    @Override
    public List<String> authenticateUser(String userName, String password)
    {
        IUser user = getUserByUserId(userName);
        try
        {
            return user != null && PasswordStorage.verifyPassword(password, user.getPassword()) ? user.getRolesAsStrings() : null;
        } catch (PasswordStorage.CannotPerformOperationException | PasswordStorage.InvalidHashException ex)
        {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public User createUser(String userName, String password)
    {
        try
        {
            IUser u = getUserByUserId(userName);
            User user = new User(userName, password);
            if (u == null)
            {
                EntityManager em = getEntityManager();
                em.getTransaction().begin();
                Role role = new Role("User");
                user.addRole(role);
                em.persist(user);
                em.getTransaction().commit();
                return user;
            }
            return null;
        } catch (PasswordStorage.CannotPerformOperationException ex)
        {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<User> getAllUsers()
    {
        EntityManager em = getEntityManager();
        TypedQuery<User> users = (TypedQuery<User>) em.createNativeQuery("SELECT USERNAME FROM SEED_USER");
        return users.getResultList();
    }

    public User deleteUser(String username)
    {
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();
            User user = em.find(User.class, username);
            em.remove(user);
            em.getTransaction().commit();
            System.out.println("Deleted "+username);
            return user;
        } catch (Exception ex)
        {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally
        {
            em.close();
        }
    }
}
