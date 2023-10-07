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
import uz.pdp.news.dto.request.PostRequest;
import uz.pdp.news.dto.response.Response;
import uz.pdp.news.dto.view.CommentView;
import uz.pdp.news.dto.view.PostView;
import uz.pdp.news.marker.OnCreate;
import uz.pdp.news.service.CommentService;
import uz.pdp.news.service.PostService;

@Validated
@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    @PreAuthorize("hasAuthority(T(uz.pdp.news.enums.AuthorityType).GET_POSTS)")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<PostView> posts = postService.getAll(PageRequest.of(page, size));

        return new Response(HttpStatus.OK.name(), posts);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.news.enums.AuthorityType).GET_POST)")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        PostView post = postService.getById(id);

        return new Response(HttpStatus.OK.name(), post);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.news.enums.AuthorityType).GET_COMMENTS)")
    @GetMapping("/{id}/comments")
    @ResponseStatus(HttpStatus.OK)
    public Response getAllCommentsByPostId(@PathVariable Long id, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Page<CommentView> comments = commentService.getAllByPostId(id, PageRequest.of(page, size));

        return new Response(HttpStatus.OK.name(), comments);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.news.enums.AuthorityType).CREATE_POST)")
    @Validated(OnCreate.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Valid @RequestBody PostRequest request) {
        PostView post = postService.create(request);

        return new Response(HttpStatus.CREATED.name(), post);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.news.enums.AuthorityType).UPDATE_POST)")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@Valid @RequestBody PostRequest request, @PathVariable Long id) {
        PostView post = postService.update(request, id);

        return new Response(HttpStatus.OK.name(), post);
    }

    @PreAuthorize("hasAuthority(T(uz.pdp.news.enums.AuthorityType).DELETE_POST)")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response deleteById(@PathVariable Long id) {
        postService.deleteById(id);

        return new Response(HttpStatus.ACCEPTED.name());
    }
}
