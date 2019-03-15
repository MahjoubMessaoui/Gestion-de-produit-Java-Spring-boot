package myapp.demo.service;

import myapp.demo.dao.ProduitRepository;

import myapp.demo.model.Produit;
import myapp.demo.model.Response;
import myapp.demo.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/produit")
@CrossOrigin("*")
public class ProduitController {
    @Autowired
    private ProduitRepository produitRepository;
@Autowired
    StorageService storageService;
@GetMapping("")
public String home(){
    return "okkk";
}

@GetMapping("/all")
    public List<Produit> gettAllProduit(Long ref){
    return produitRepository.findAll();
}
    @GetMapping("/ById")
    public Produit getProduitById(Long ref){
    return produitRepository.getOne(ref);
    }

@PostMapping("/add")
    public Produit addProduit(Produit produit, MultipartFile img)
{
    try {
        storageService.store(img);
        produit.setFile(storageService.store(img));
        Produit p=produitRepository.saveAndFlush(produit);
        return p;

    }
    catch ( Exception e){
        return null;
    }

}

@DeleteMapping("/remove")

    public Response deleteProduit(Long ref) {
    Response resp = new Response();
    try {
        resp.setMessage("supp avec succe");
        produitRepository.deleteById(ref);
        return resp;
    } catch (Exception e) {
        resp.setMessage("erruer");
        return resp;
    }
}

@PutMapping("/update")
public Produit updateProduit(@RequestBody Produit produit, @RequestParam Long ref)
{

    produit.setRef(ref);
    return produitRepository.saveAndFlush(produit);
}
@GetMapping("/allref")
    public Produit getByRef(Long ref){
    System.out.println("ref "+ref);
    return produitRepository.getByRef(ref);
}

@GetMapping("/allPrice")
    public List<Produit> getByPrice(String price){
    System.out.println("price" +price);
    return produitRepository.getByPrice(price);
}
}
