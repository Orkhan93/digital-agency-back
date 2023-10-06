package digitalhands.az.file_upload.service;

import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.file_upload.repository.FileRepository;
import digitalhands.az.file_upload.entity.File;
import digitalhands.az.file_upload.response.FileResponse;
import digitalhands.az.mappers.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    public ResponseEntity<FileResponse> saveVideo(MultipartFile multipartFile) throws IOException {
        File file = new File();
        file.setName(multipartFile.getOriginalFilename());
        file.setContentType(multipartFile.getContentType());
        file.setFileData(multipartFile.getBytes());
        return ResponseEntity.status(HttpStatus.OK).body(fileMapper.fromModelToResponse(fileRepository.save(file)));
    }

    public ResponseEntity<?> getVideoById(Long id) throws FileNotFoundException {
        File fileOptional = fileRepository.findById(id).orElseThrow(
                () -> new FileNotFoundException(ErrorMessage.FILE_NOT_FOUND));
            return ResponseEntity.status(HttpStatus.OK)
                    .header("Content-Type", fileOptional.getContentType())
                    .body(fileOptional.getFileData());
    }

}