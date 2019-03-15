package myapp.demo.service;

import myapp.demo.dao.CommandeRepository;
import myapp.demo.model.Commande;
import myapp.demo.model.Livreur;
import myapp.demo.model.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/commandes")
public class CommandeController {
    @Autowired
    private CommandeRepository commandeRepository;
    @GetMapping("")
    public String home()
    {
        return "okkkkk";
    }

    @GetMapping("/all")
    public List<Commande> getAllComande(){
        return commandeRepository.findAll();
    }

    @GetMapping("/ById")
    public Commande getComandeById(Long id)
    {
        return commandeRepository.getOne(id);
    }

    @PostMapping("/add")

    public Commande addComande(@RequestBody Commande commande){
        return commandeRepository.saveAndFlush(commande);
    }

    @DeleteMapping("/remove")

    public String deletComande(Long id){
        try{
            commandeRepository.deleteById(id);
            return "yesssss";


        }

        catch (Exception e){
            return "noooooooooooo";
        }
    }

@PutMapping("/update")
public Commande updateComande(@RequestBody Commande commande,@RequestParam Long id){
        commande.setId(id);
        return commandeRepository.saveAndFlush(commande);
}

@GetMapping("/allprod")
    public List<Produit>gettallprod(Long idprod){
    List<Produit> listeprod = new ArrayList<>();
        for(Commande commande:commandeRepository.findAll()){
            for(Produit prod:commande.getProduit()){

                if(prod.getRef()== idprod){
                    listeprod.add(prod);


                }
            }
        }
        return listeprod;
}




    @GetMapping("/alllivreur")
    public List<Commande>gettAllLivreur(Long idliv){
        List<Commande> listlivreur=new ArrayList<>();
        for (Commande commande:commandeRepository.findAll()){

            if (commande.getLivreur().getId().equals(idliv)) {listlivreur.add(commande);}}
        return listlivreur;
        }

    @GetMapping("/alllclient")
    public List<Commande>gettAllLclient(Long idliv){
        List<Commande> listlivreur=new ArrayList<>();

        for (Commande commande:commandeRepository.findAll()){

            if (commande.getClient().getId()== idliv) {
                listlivreur.add(commande);
            }}
        return listlivreur;
    }
@GetMapping("commandebyliv")
public List<Commande> getbyliv(Livreur idliv){

        return commandeRepository.getbyliv(idliv);
}


}

