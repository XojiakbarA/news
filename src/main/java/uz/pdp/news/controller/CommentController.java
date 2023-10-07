package uz.pdp.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
import uz.pdp.news.dto.request.CommentRequest;
import uz.pdp.news.dto.response.Response;
import uz.pdp.news.dto.view.CommentView;
import uz.pdp.news.marker.OnCreate;
import uz.pdp.news.service.CommentService;

@Validated
@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    
    @PreAuthorize("hasAuthority(T(uz.pdp.news.enums.AuthorityType).GET_COMMENTS)")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<CommentView> comments = commentService.getAll(PageRequest.of(page, size));

        return new Response(HttpStatus.OK.name(), comments);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.news.enums.AuthorityType).GET_COMMENT)")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        CommentView comment = commentService.getById(id);

        return new Response(HttpStatus.OK.name(), comment);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.news.enums.AuthorityType).CREATE_COMMENT)")
    @Validated(OnCreate.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody CommentRequest request) {
        CommentView comment = commentService.create(request);

        return new Response(HttpStatus.CREATED.name(), comment);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.news.enums.AuthorityType).UPDATE_COMMENT)")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@Valid @RequestBody CommentRequest request, @PathVariable Long id) {
        CommentView comment = commentService.update(request, id);

        return new Response(HttpStatus.OK.name(), comment);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.news.enums.AuthorityType).DELETE_COMMENT)")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        commentService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
