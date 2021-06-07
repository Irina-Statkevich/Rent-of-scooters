package statkevich.scooters.service.serviceImpl;

import statkevich.scooters.dao.IDao.IRoleDao;
import statkevich.scooters.dto.RoleDTO;
import statkevich.scooters.service.ServicesI.RolesService;
import statkevich.scooters.service.mappers.IRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RolesService {

    @Autowired
    private IRoleDao roleDAO;

    @Autowired
    private IRoleMapper roleMapper;

    @Override
    public RoleDTO read(final Long id) {
        return roleMapper.roleToRoleDto(roleDAO.read(id));
    }


    @Override
    public RoleDTO readByTitle(String title) {
        return roleMapper.roleToRoleDto(roleDAO.readByTitle(title));
    }

}
