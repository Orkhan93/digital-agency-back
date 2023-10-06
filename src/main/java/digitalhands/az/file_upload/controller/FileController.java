package digitalhands.az.file_upload.controller;

import digitalhands.az.file_upload.response.FileResponse;
import digitalhands.az.file_upload.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> uploadVideo(@RequestParam("file") MultipartFile file) throws IOException {
         return fileService.saveVideo(file);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadVideo(@PathVariable Long id) throws FileNotFoundException {
        return fileService.getVideoById(id);
    }

}