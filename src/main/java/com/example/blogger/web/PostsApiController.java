package com.example.blogger.web;

import com.example.blogger.services.posts.PostsService;
import com.example.blogger.web.dto.PostsListResponseDto;
import com.example.blogger.web.dto.PostsResponseDto;
import com.example.blogger.web.dto.PostsSaveRequestDto;
import com.example.blogger.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
//@CrossOrigin(origins = "*")
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CUSTOMER_REP', 'ROLE_CUSTOMER')")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @GetMapping("/api/v1/posts")
    public List<PostsListResponseDto> findAllDesc() {
        return postsService.findAllDesc();
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
