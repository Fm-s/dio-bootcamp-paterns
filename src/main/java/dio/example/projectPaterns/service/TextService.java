package dio.example.projectPaterns.service;

import dio.example.projectPaterns.model.TextEntity;

public interface TextService {

    Iterable<TextEntity> getAllTexts();

    TextEntity getById(Long id);

    TextEntity saveText(String text);

    void deleteText(Long id);
}
