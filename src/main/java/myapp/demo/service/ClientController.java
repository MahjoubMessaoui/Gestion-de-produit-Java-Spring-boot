package myapp.demo.service;

import myapp.demo.dao.ClientRepository;

import myapp.demo.model.Client;
import myapp.demo.model.Response;
import myapp.demo.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/client")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    StorageService storageService;
    @GetMapping("")
    public String home()
    {
        return"ok";
    }
    @GetMapping("/all")
    public List<Client> getAllClient(){

        return clientRepository.findAll();


    }

    @GetMapping("/ById")
    public Client getClientById(Long id){

        return clientRepository.getOne(id);


    }

@PostMapping("/add")
public Client addClient(Client client, @RequestParam ("img") MultipartFile img)
    {
        try {
            storageService.store(img);
            client.setFile(storageService.store(img));
            Client c=clientRepository.saveAndFlush(client);
            return c;

        }
        catch ( Exception e){
            return null;
        }

    }


    @DeleteMapping("/delete")
    public Response removeClient(Long id){
 Response resp = new Response();
    try{
        resp.setMessage("supp avec succe");
        clientRepository.deleteById(id);
        return resp;
    }
catch(Exception e){
        resp.setMessage("erruer");
        return resp;
        }

    }

    @PutMapping("/update/{id}")
    public Client updateClient( Client client, @RequestParam ("img") MultipartFile img, @PathVariable Long id)
    {   try {
        storageService.store(img);
        client.setFile(storageService.store(img));

        return clientRepository.saveAndFlush(client);

    }
    catch ( Exception e){
        return null;
    }



    }

    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = storageService.loadFile(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            //  logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


}
