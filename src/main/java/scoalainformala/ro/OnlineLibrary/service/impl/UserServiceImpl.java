package scoalainformala.ro.OnlineLibrary.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scoalainformala.ro.OnlineLibrary.domain.LibraryUser;
import scoalainformala.ro.OnlineLibrary.dto.UserEditDto;
import scoalainformala.ro.OnlineLibrary.dto.UserInsertDto;
import scoalainformala.ro.OnlineLibrary.exceptions.InvalidUserException;
import scoalainformala.ro.OnlineLibrary.repository.UserRepository;
import scoalainformala.ro.OnlineLibrary.service.UserService;
import scoalainformala.ro.OnlineLibrary.transformer.EntityVsEdit;
import scoalainformala.ro.OnlineLibrary.transformer.EntityVsInsert;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private EntityVsEdit converter;

    @Autowired
    private EntityVsInsert konverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public LibraryUser findByEmail(String email)  {

        LibraryUser existingUser = userRepository.findByEmail(email);
        return existingUser;
    }

    @Override
    public UserEditDto showUser(String email) {

        UserEditDto servedDto = new UserEditDto();
        LibraryUser libUser = userRepository.findByEmail(email);
        servedDto = converter.convertEntity(libUser);
        return servedDto;
    }

    @Override
    public LibraryUser saveNewUser(UserInsertDto userInsertDto) {

        LibraryUser librUser = konverter.convertDto(userInsertDto);
        log.info(librUser.getName());
        userRepository.save(librUser);
        return librUser;
    }

    @Override
    public UserEditDto saveUserEdit(UserEditDto userEditDto) {

        LibraryUser libU = converter.convertDto(userEditDto);
        return converter.convertEntity(libU);
    }

    @Override
    public void deleteById(UUID id) {
//TODO to be implemented
    }
}
