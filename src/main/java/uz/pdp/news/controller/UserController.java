package uz.pdp.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import uz.pdp.news.dto.request.UserRequest;
import uz.pdp.news.dto.response.Response;
import uz.pdp.news.dto.view.UserView;
import uz.pdp.news.marker.OnCreate;
import uz.pdp.news.service.UserService;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<UserView> users = userService.getAll(PageRequest.of(page, size));

        return new Response(HttpStatus.OK.name(), users);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        UserView user = userService.getById(id);

        return new Response(HttpStatus.OK.name(), user);
    }

    @Validated(OnCreate.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody UserRequest request) {
        UserView user = userService.create(request);

        return new Response(HttpStatus.CREATED.name(), user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@Valid @RequestBody UserRequest request, @PathVariable Long id) {
        UserView user = userService.update(request, id);

        return new Response(HttpStatus.OK.name(), user);
    }

    @PutMapping("/{id}/lock")
    @ResponseStatus(HttpStatus.OK)
    public Response lock(@PathVariable Long id) {
        userService.lock(id);

        return new Response(HttpStatus.OK.name());
    }

    @PutMapping("/{id}/unlock")
    @ResponseStatus(HttpStatus.OK)
    public Response unlock(@PathVariable Long id) {
        userService.unlock(id);

        return new Response(HttpStatus.OK.name());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        userService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
