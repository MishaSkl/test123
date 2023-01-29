package ru.kataproject.p_sm_airlines_1.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kataproject.p_sm_airlines_1.entity.Document;
import ru.kataproject.p_sm_airlines_1.entity.Dto.DocumentDto;
import ru.kataproject.p_sm_airlines_1.entity.Passenger;
import ru.kataproject.p_sm_airlines_1.repository.DocumentRepository;
import ru.kataproject.p_sm_airlines_1.service.DocumentService;
import ru.kataproject.p_sm_airlines_1.service.PassengerService;
import ru.kataproject.p_sm_airlines_1.util.exceptions.DocumentNotFoundException;
import ru.kataproject.p_sm_airlines_1.util.mapper.mapStruct.DocumentMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class DocumentServiceImpl.
 * Implements DocumentService interface.
 *
 * @author Mishin Yura (mishin.inbox@gmail.com)
 * @since 06.10.2022
 */
@RequiredArgsConstructor
@Service
public class DocumentServiceImpl implements DocumentService {
    /**
     * Document Repository.
     */
    private final DocumentRepository documentRepository;
    private final PassengerService passengerService;

    /**
     * Method gets all documents.
     *
     * @return All Documents
     */
    @Override
    public List<DocumentDto> getAllDocuments() {
        return documentRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream().map(DocumentMapper.INSTANCE::mapToDocumentDto)
                .collect(Collectors.toList());
    }

    /**
     * Method gets document by id.
     *
     * @param id Id
     * @return DocumentDto
     */
    @Override
    public DocumentDto getDocumentById(Long id) {
        return DocumentMapper.INSTANCE.mapToDocumentDto(findDocumentById(id));
    }

    @Override
    public List<DocumentDto> getDocumentsByPassenger_Id(Long id) {
        return documentRepository.getDocumentsByPassenger_Id(id)
                .stream().map(DocumentMapper.INSTANCE::mapToDocumentDto)
                .collect(Collectors.toList());
    }

    /**
     * Method finds document by id.
     *
     * @param id Id
     * @return Document
     */
    private Document findDocumentById(Long id) {
        return documentRepository
                .findById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id));
    }

    /**
     * Method creates document.
     *
     * @param document Document
     */
    @Transactional
    @Override
    public void createDocument(Document document) {
        Passenger passenger = passengerService.getPassengerById(document.getPassenger().getId());
        passenger.addDocument(document);
        documentRepository.saveAndFlush(document);
    }

    /**
     * Method updates document.
     */
    @Transactional
    @Override
    public void updateDocument(Document document) {
        document.setPassenger(findDocumentById(document.getId()).getPassenger());
        documentRepository.saveAndFlush(document);
    }

    /**
     * Method deletes document by id.
     *
     * @param id Id
     */
    @Transactional
    @Override
    public void deleteDocumentById(Long id) {
        Document document = findDocumentById(id);
        document.getPassenger().removeDocument(document);
    }
}