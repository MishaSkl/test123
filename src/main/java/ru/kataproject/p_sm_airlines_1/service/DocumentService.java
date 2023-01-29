package ru.kataproject.p_sm_airlines_1.service;

import ru.kataproject.p_sm_airlines_1.entity.Document;
import ru.kataproject.p_sm_airlines_1.entity.Dto.DocumentDto;

import java.util.List;

/**
 * Interface Document.
 * Declares Document Service API.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 06.10.2022
 */
public interface DocumentService {
    /**
     * Method gets all documents.
     *
     * @return All Documents
     */
    List<DocumentDto> getAllDocuments();

    /**
     * Method gets document by id.
     *
     * @param id Id
     * @return Document
     */
    DocumentDto getDocumentById(Long id);

    /**
     * Method gets documents by passenger_id.
     *
     * @param id Id
     * @return List<DocumentDTO>
     */
    List<DocumentDto> getDocumentsByPassenger_Id(Long id);

    /**
     * Method creates document.
     *
     * @param document Document
     */
    void createDocument(Document document);

    /**
     * Method updates document.
     *
     * @param document Document
     */
    void updateDocument(Document document);

    /**
     * Method deletes document by id.
     *
     * @param id Id
     */
    void deleteDocumentById(Long id);
}