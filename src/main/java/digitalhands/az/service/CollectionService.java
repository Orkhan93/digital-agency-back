package digitalhands.az.service;

import digitalhands.az.entity.Collection;
import digitalhands.az.entity.User;
import digitalhands.az.enums.UserRole;
import digitalhands.az.exception.CollectionNotFoundException;
import digitalhands.az.exception.UnauthorizedUserException;
import digitalhands.az.exception.UserNotFoundException;
import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.mappers.CollectionMapper;
import digitalhands.az.repository.CollectionRepository;
import digitalhands.az.repository.UserRepository;
import digitalhands.az.request.CollectionRequest;
import digitalhands.az.response.CollectionResponse;
import digitalhands.az.response.CollectionResponseList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    public CollectionResponse createCollection(CollectionRequest collectionRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Collection collection = collectionMapper.fromRequestToModel(collectionRequest);
            return collectionMapper.fromModelToResponse(collectionRepository.save(collection));
        }
        throw new UnauthorizedUserException(HttpStatus.UNAUTHORIZED.name());
    }

    public CollectionResponse updateCollection(CollectionRequest collectionRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Collection findCollection = collectionRepository.findById(collectionRequest.getId()).orElseThrow(
                    () -> new CollectionNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.COLLECTION_NOT_FOUND));
            if (Objects.isNull(findCollection)) {
                throw new CollectionNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.COLLECTION_NOT_FOUND);
            } else {
                return collectionMapper.fromModelToResponse(
                        collectionRepository.save(collectionMapper.fromRequestToModel(collectionRequest)));
            }
        }
        throw new UnauthorizedUserException(HttpStatus.UNAUTHORIZED.name());
    }

    public CollectionResponseList getAllCollection() {
        List<Collection> all = collectionRepository.findAll();
        CollectionResponseList list = new CollectionResponseList();
        List<CollectionResponse> collectionResponses = collectionMapper.fromModelListToResponseList(all);
        list.setCollectionResponses(collectionResponses);
        return list;
    }

    public CollectionResponse getCollectionById(Long collectionId) {
        Collection collection = collectionRepository.findById(collectionId).orElseThrow(
                () -> new CollectionNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.COLLECTION_NOT_FOUND));
        return collectionMapper.fromModelToResponse(collection);
    }

    public void deleteCollection(Long userId, Long collectionId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Collection collection = collectionRepository.findById(collectionId).orElseThrow(
                    () -> new CollectionNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.COLLECTION_NOT_FOUND));
            collectionRepository.deleteById(collectionId);
            log.info("deleteCollection {}", collection);
        } else
            throw new UnauthorizedUserException(ErrorMessage.UNAUTHORIZED_USER);
    }

}