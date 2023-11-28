package digitalhands.az.controller;

import digitalhands.az.request.CollectionRequest;
import digitalhands.az.response.CollectionResponse;
import digitalhands.az.response.CollectionResponseList;
import digitalhands.az.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collection")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<CollectionResponse> createCollection(@RequestBody CollectionRequest collectionRequest,
                                                               @PathVariable(name = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(collectionService.createCollection(collectionRequest, userId));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<CollectionResponse> updateCollection(@RequestBody CollectionRequest collectionRequest,
                                                               @PathVariable(name = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(collectionService.updateCollection(collectionRequest, userId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<CollectionResponseList> getAllCollection() {
        return ResponseEntity.status(HttpStatus.OK).body(collectionService.getAllCollection());
    }

    @GetMapping("/get/{collectionId}")
    public ResponseEntity<CollectionResponse> getCollectionById(@PathVariable(name = "collectionId") Long collectionId) {
        return ResponseEntity.status(HttpStatus.OK).body(collectionService.getCollectionById(collectionId));
    }

    @DeleteMapping("/{userId}/delete/{collectionId}")
    public void deleteCollection(@PathVariable(name = "userId") Long userId,
                                 @PathVariable(name = "collectionId") Long collectionId) {
        collectionService.deleteCollection(userId, collectionId);
    }

}