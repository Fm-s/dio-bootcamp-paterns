package dio.example.projectPaterns.service.impl;

import dio.example.projectPaterns.model.TextEntity;
import dio.example.projectPaterns.repository.TextRepository;
import dio.example.projectPaterns.service.BionicTextService;
import dio.example.projectPaterns.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TextServiceImpl implements TextService {
    @Autowired
    private TextRepository textRepository;
    @Autowired
    private BionicTextService bionicTextService;
    @Override
    public Iterable<TextEntity> getAllTexts() {
        return textRepository.findAll();
    }

    @Override
    public TextEntity getById(Long id) {
        Optional<TextEntity> textEntry = textRepository.findById(id);
        if(textEntry.isPresent()){
            return textEntry.get();
        }
        throw new RuntimeException("invalid id");
    }

    @Override
    public TextEntity saveText(String text) {
        TextEntity textEntry = new TextEntity();
        bionicTextService.setPostText(text);
        textEntry.setBionicText(bionicTextService.getBionicText());
        textEntry.setPlainText(text);
        textRepository.save(textEntry);
        return textEntry;
    }

    @Override
    public void deleteText(Long id) {
        textRepository.deleteById(id);
    }
}
