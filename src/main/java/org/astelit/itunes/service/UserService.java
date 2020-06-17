package org.astelit.itunes.service;

import lombok.RequiredArgsConstructor;
import org.astelit.itunes.dto.SearchRequest;
import org.astelit.itunes.dto.user.CreateUserRequest;
import org.astelit.itunes.dto.user.UpdateUserRequest;
import org.astelit.itunes.dto.user.UserResponse;
import org.astelit.itunes.entity.User;
import org.astelit.itunes.exception.BadRequestException;
import org.astelit.itunes.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static org.astelit.itunes.utils.Exceptions.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse create(CreateUserRequest request) {
        if (userRepository.existsByLogin(request.getLogin()))
            throw new BadRequestException("Login " + request.getLogin() + " already taken");

        User user = new User();
        user.setLogin(request.getLogin());
        user.setName(request.getName());

        userRepository.save(user);
        return new UserResponse(user);
    }

    public UserResponse update(UpdateUserRequest request) {
        if (userRepository.existsByLoginAndIdNot(request.getLogin(), request.getId()))
            throw new BadRequestException("Login " + request.getLogin() + " already taken");

        User user = userRepository.findById(request.getId()).orElseThrow(USER_NOT_FOUND);
        user.setLogin(request.getLogin());
        user.setName(request.getName());

        userRepository.save(user);
        return new UserResponse(user);
    }

    public UserResponse view(long id) {
        User user = userRepository.findById(id).orElseThrow(USER_NOT_FOUND);
        return new UserResponse(user);
    }

    public void delete(long id) {
        User user = userRepository.findById(id).orElseThrow(USER_NOT_FOUND);
        userRepository.delete(user);
    }

    public Page<UserResponse> search(SearchRequest request) {
        return userRepository.findByLoginIsLikeOrderByLoginAsc(request.getQuery(), request.pageable())
                .map(UserResponse::new);
    }
}
