package com.app.jujamanru.controller;

import com.app.jujamanru.dto.team.UserTeamUpdateRequest;
import com.app.jujamanru.dto.user.UserDto;
import com.app.jujamanru.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PutMapping("/{id}/team")
    public ResponseEntity<Long> changeTeam(@PathVariable(name = "id") String id, @RequestBody UserTeamUpdateRequest request) {
        return ResponseEntity.ok(userService.changeTeam(id, request.getTeamId()));
    }
}
