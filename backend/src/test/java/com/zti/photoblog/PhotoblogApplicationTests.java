package com.zti.photoblog;

import com.zti.photoblog.models.Account;
import com.zti.photoblog.models.Comment;
import com.zti.photoblog.models.Post;
import com.zti.photoblog.services.AccountService;
import com.zti.photoblog.services.CommentService;
import com.zti.photoblog.services.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class PhotoblogApplicationTests {

	@Autowired
	private MockMvc mockMvc;


    @MockBean
    private PostService postService;

    @MockBean
    private AccountService accountService;

    @MockBean
    private CommentService commentService;


	@Test
	public void getAllPostsShouldReturnListFromService() throws Exception {
		when(postService.getAllPosts()).thenReturn(Arrays.asList(new Post()));
		this.mockMvc.perform(get("/api/posts"))
				.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
	}

    @Test
    public void getInfoShouldReturnInstanceFromService() throws Exception {
        when(accountService.getInfo("")).thenReturn(new Account());
        this.mockMvc.perform(get("/api/accounts/test"))
                .andExpect(status().isOk());
    }

    @Test
    public void getCommentsByPostIdShouldReturnListFromService() throws Exception {
        when(commentService.getCommentsByPostId(1)).thenReturn(Arrays.asList(new Comment()));
        this.mockMvc.perform(get("/api/posts/1/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));;
    }

}
