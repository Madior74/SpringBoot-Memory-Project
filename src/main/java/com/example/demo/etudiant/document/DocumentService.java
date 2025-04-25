package com.example.demo.etudiant.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.etudiant.Etudiant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final String uploadDir = "uploads/"; // Répertoire pour stocker les fichiers

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document uploadDocument(MultipartFile file, String nom, String type, Etudiant etudiant) throws IOException {
        // Créer un nouveau document
        Document document = new Document();
        document.setNom(nom);
        document.setType(type);
        document.setDateDepot(LocalDateTime.now());
        document.setEtudiant(etudiant);

        // Enregistrer le fichier sur le disque
        Path path = Paths.get(uploadDir + file.getOriginalFilename());
        Files.write(path, file.getBytes());

        // Définir le chemin du fichier dans le document
        document.setCheminFichier(path.toString());

        // Enregistrer le document dans la base de données
        return documentRepository.save(document);
    }

    public List<Document> getDocumentsByEtudiant(Long etudiantId) {
        return documentRepository.findByEtudiantId(etudiantId);
    }

    public Document getDocument(Long documentId) {
        return documentRepository.findById(documentId).orElse(null);
    }
}