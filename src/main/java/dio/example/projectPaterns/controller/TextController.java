package dio.example.projectPaterns.controller;

import dio.example.projectPaterns.model.TextEntity;
import dio.example.projectPaterns.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("texts")
public class TextController {
    @Autowired
    private TextService textService;

    @GetMapping
    public ResponseEntity<Iterable<TextEntity>> getAll(){
        return ResponseEntity.ok(textService.getAllTexts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TextEntity> getById(@PathVariable Long id){
        return ResponseEntity.ok(textService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TextEntity> save(@RequestBody String text){
        return ResponseEntity.ok(textService.saveText(text));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        textService.deleteText(id);
        return ResponseEntity.ok().build();
    }

}
