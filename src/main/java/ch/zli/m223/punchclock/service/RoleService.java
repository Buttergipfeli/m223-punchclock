package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Andre Kocher
 * @project punchclock
 * @package ch.zli.m223.punchclock.service
 * @date 13.07.2022
 */

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

}
