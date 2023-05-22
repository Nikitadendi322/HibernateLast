package service;

import model.Role;
import model.RoleType;
import model.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Application {
    static UserDao userDao = new UserDaoImpl();
    static RoleDao roleDao = new RoleDaoImpl();

    public static void main(String[] args) {

        Role dev = Role.builder().type(RoleType.DEVELOPER).build();
        Role analyst = Role.builder().type(RoleType.ANALYST).build();
        Role manager = Role.builder().type(RoleType.MANAGER).build();

        roleDao.add(dev);
        roleDao.add(analyst);
        roleDao.add(manager);

        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getById(1));
        User newUser = User.builder()
                .name("Nikita")
                .login("dendi")
                .password("dendidendi")
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .roles(roles)
                .build();
        User added = userDao.add(newUser);
        roles.add(roleDao.getById(2));
        added.setRoles(roles);
        userDao.updateUser(added);
    }
}
