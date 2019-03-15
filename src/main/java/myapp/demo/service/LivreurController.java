package myapp.demo.service;

import myapp.demo.dao.LivreurRepository;
import myapp.demo.model.Livreur;
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
@CrossOrigin("*")
@RequestMapping("/livreur")
public class LivreurController {
    @Autowired
    private LivreurRepository livreurRepository;
    @Autowired
    StorageService storageService;


    @GetMapping("")
    public String home(){
        return "okkkk";
    }



    @GetMapping("/all")
    public List<Livreur> getAllLivreur(){
        return livreurRepository.findAll();
    }




    @GetMapping("/ById")
    public Livreur getLivreurById(Long id){
        return livreurRepository.getOne(id);
    }




@PostMapping("/add")
    public Livreur addLivreur(Livreur livreur , @RequestParam ("img")MultipartFile img){

        try{
            storageService.store(img);
            livreur.setFile(storageService.store(img));
            Livreur l=livreurRepository.saveAndFlush(livreur);
            return l;
        }
        catch(Exception e){
            return null;

    }

}


@PutMapping("/update/{id}")
public Livreur updateLivreur(Livreur livreur,  @RequestParam ("img") MultipartFile img, @PathVariable Long id)
{try{
    storageService.store(img);
    livreur.setFile(storageService.store(img));
    return livreurRepository.saveAndFlush(livreur);
}
    catch (Exception e){
    return null;
    }
}


   @DeleteMapping("/delete")
   public Response removeLivreur(Long id)
          {Response resp=new Response();
              try{
                   resp.setMessage("suppression avec succes");
                   livreurRepository.deleteById(id);
                   return resp;
              }
              catch (Exception e){
                  resp.setMessage("erreur");
                  return resp;
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
