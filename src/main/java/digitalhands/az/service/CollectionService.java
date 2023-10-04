package digitalhands.az.service;

import digitalhands.az.entity.Collection;
import digitalhands.az.entity.User;
import digitalhands.az.enums.UserRole;
import digitalhands.az.exception.CollectionNotFoundException;
import digitalhands.az.exception.UserNotFoundException;
import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.mappers.CollectionMapper;
import digitalhands.az.repository.CollectionRepository;
import digitalhands.az.repository.UserRepository;
import digitalhands.az.request.CollectionRequest;
import digitalhands.az.response.CollectionResponse;
import digitalhands.az.wrapper.CollectionWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;
    private final UserRepository userRepository;
    private final CollectionMapper collectionMapper;

    public ResponseEntity<CollectionResponse> createCollection(CollectionRequest collectionRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Collection collection = collectionMapper.fromRequestToModel(collectionRequest);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(collectionMapper.fromModelToResponse(collectionRepository.save(collection)));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public ResponseEntity<CollectionResponse> updateCollection(CollectionRequest collectionRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Collection findCollection = collectionRepository.findById(collectionRequest.getId()).orElseThrow(
                    () -> new CollectionNotFoundException(ErrorMessage.COLLECTION_NOT_FOUND));
            if (Objects.nonNull(findCollection)) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(collectionMapper.fromModelToResponse
                                (collectionRepository.save(collectionMapper.fromRequestToModel(collectionRequest))));
            } else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public ResponseEntity<List<CollectionWrapper>> getAllCollection() {
        return ResponseEntity.status(HttpStatus.OK).body(collectionRepository.getAllCollection());
    }

    public ResponseEntity<CollectionResponse> getCollectionById(Long collectionId) {
        Collection collection = collectionRepository.findById(collectionId).orElseThrow(
                () -> new CollectionNotFoundException(ErrorMessage.COLLECTION_NOT_FOUND));
        return ResponseEntity.status(HttpStatus.OK).body(collectionMapper.fromModelToResponse(collection));
    }

    public void deleteCollection(Long userId, Long collectionId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Collection collection = collectionRepository.findById(collectionId).orElseThrow(
                    () -> new CollectionNotFoundException(ErrorMessage.COLLECTION_NOT_FOUND));
            collectionRepository.deleteById(collectionId);
            log.info("deleteCollection {}", collection);
        }
    }

}