package com.example.demo.etudiant.document;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.etudiant.prinscription.Etudiant;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.lang.StackWalker.Option;
import java.nio.file.Files;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Document> uploadDocument(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("nom") String nom,
                                                   @RequestParam("etudiantId") Long etudiantId) {
                                                
       
        try {
            // Récupérer l'étudiant (vous devez avoir une méthode pour cela)
            Etudiant etudiant = new Etudiant(); // Remplacez ceci par la récupération réelle de l'étudiant
            etudiant.setId(etudiantId);

            Document document = documentService.uploadDocument(file, nom, file.getContentType(), etudiant);
            return new ResponseEntity<>(document, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<Document>> getDocumentsByEtudiant(@PathVariable Long etudiantId) {
        List<Document> documents = documentService.getDocumentsByEtudiant(etudiantId);
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable Long documentId) {
        Document document = documentService.getDocument(documentId);
        if (document == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            Path path = Paths.get(document.getCheminFichier());
            byte[] data = Files.readAllBytes(path);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + document.getNom() + "\"")
                    .body(data);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable("id") Long id) {
        documentService.deleteDocument(id);
    }
}