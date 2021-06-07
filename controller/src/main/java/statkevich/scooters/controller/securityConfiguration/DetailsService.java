package statkevich.scooters.controller.securityConfiguration;

import statkevich.scooters.dao.IDao.IUserDao;
import statkevich.scooters.entity.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DetailsService implements UserDetailsService {

    @Autowired
    private IUserDao userDAO;

//    @Override
//    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//        Users user = userDAO.readByName(name);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("Unknown user: " + name);
//        }
//        UserDetails userDetails = User.builder()
//                .username(user.getName())
//                .password(user.getPass())
//                .roles(user.getRole().getTitle())
//                .build();
//        return userDetails;
//    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUserByUserPhone(String phone) throws UsernameNotFoundException {
        Users user = userDAO.readByPhone(phone);

        if (user == null) {
            throw new UsernameNotFoundException("Unknown user with phone: " + phone);
        }
        UserDetails userDetails = User.builder()
                .username(user.getName())
                .password(user.getPass())
                .roles(user.getRole().getTitle())
                .build();
        return userDetails;
    }

}
