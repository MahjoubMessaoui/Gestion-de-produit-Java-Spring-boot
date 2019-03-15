package myapp.demo.service;

import myapp.demo.dao.AdminRepository;
import myapp.demo.model.Admin;

import myapp.demo.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("admin/")
@CrossOrigin("*")
public class AdminController  {
   @Autowired
    private AdminRepository adminRepository;
   @Autowired
   StorageService storageService;

    @GetMapping("")
    public String home(){

        return "ok";
    }

    @GetMapping("/all")
    public List<Admin> getAllAdmin(){
        return adminRepository.findAll();
    }

    @GetMapping("/ById")
    public Admin getAdminById(Long id){
    return adminRepository.getOne(id);
    }


    @PostMapping("/add")
    public Admin addAdmin(Admin admin, @RequestParam ("img") MultipartFile img)
    {
        try {
            storageService.store(img);
            admin.setFile(storageService.store(img));
            Admin a=adminRepository.saveAndFlush(admin);
            return a;

        }
        catch ( Exception e){
            return null;
        }

    }


    @PutMapping("/update")
    private Admin updateAdmin(@RequestBody Admin admin ,Long id){
        admin.setId(id);
        return adminRepository.saveAndFlush(admin);
    }


    @DeleteMapping("/remove")
    public String deleteAdmin(Long id){
        try{
            adminRepository.deleteById(id);
            return "okkkk";
        }
        catch (Exception e){
            return "noooooooo";
        }
    }

    @PostMapping("/login")
    public Admin getloginByadmin (@RequestBody Admin a)
    {
        return adminRepository.login(a.getLogin(),a.getMotpasse());
    }
}
