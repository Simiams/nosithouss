package fr.arosaje.nosithouss.services;

import fr.arosaje.nosithouss.models.GuardingPost;
import fr.arosaje.nosithouss.models.Image;
import fr.arosaje.nosithouss.repositories.PostRepository;
import fr.arosaje.nosithouss.utils.FileManager;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private FileManager fileManager;

    @InjectMocks
    private PostService postService;

    @Test
    void upload() throws IOException {
        // given
        Long postId = 1L;
        MultipartFile file = new MockMultipartFile("file", "originalFileName", "text/plain", "content".getBytes());
        Mockito.when(fileManager.saveImage(any(MultipartFile.class))).thenReturn(new Image("image.jpg", new byte[]{}));
        Mockito.when(postRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new GuardingPost()));

        // then
        assertDoesNotThrow(() -> postService.upload(file, postId));
    }
}
