package security;

import entity.User;
import java.util.List;

public interface IUserFacade {

    List<String> authenticateUser(String userName, String password);
    IUser getUserByUserId(String id);
    
}
