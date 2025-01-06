package org.example.expert.domain.manager.dto;

import org.example.expert.domain.manager.dto.response.ManagerResponse;
import org.example.expert.domain.manager.dto.response.ManagerSaveResponse;
import org.example.expert.domain.manager.entity.Manager;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapper {


    public ManagerResponse toResponse(Manager manager) {
        User user = manager.getUser();
        return new ManagerResponse(
                manager.getId(),
                new UserResponse(user.getId(), user.getEmail())
        );
    }


    public ManagerSaveResponse toSaveResponse(Manager manager) {
        User user = manager.getUser();
        return new ManagerSaveResponse(
                manager.getId(),
                new UserResponse(user.getId(), user.getEmail())
        );
    }
}