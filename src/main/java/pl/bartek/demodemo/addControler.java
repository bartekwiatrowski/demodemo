package pl.bartek.demodemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class addControler {
    private OsobaRepo osobaRepo;

    @Autowired
    public addControler(OsobaRepo osobaRepo) {
        this.osobaRepo = osobaRepo;
    }

    @RequestMapping("/")
    public String menu(){
        return "menu";
    }

    @RequestMapping("/add")
    public String addUser(){
        return "dodaj";
    }


    @RequestMapping("/dodaj")
    public String dodajemyDane(
            //@RequestParam("id") Integer id,
            @RequestParam("imie") String imie,
            @RequestParam("nazwisko") String nazwisko,
            @RequestParam("telefon") String telefon,
            @RequestParam("email") String email,
            @RequestParam("opis") String opis,
            Model model)
            throws Exception {
        Osoba osoba = new Osoba(imie, nazwisko, telefon, email, opis, true);
        System.out.println(osoba);
        osobaRepo.save(osoba);
        model.addAttribute("osoba", osoba);
        return "widok";
    }

    @RequestMapping("/pokaz")
    public String pokaz( Model model){
        int i=0;
        for (Osoba osoba : osobaRepo.findAll()) {
            System.out.println(osoba);
        }
        model.addAttribute("osoba", osobaRepo.findAll());
        return "pokaz";
    }

    @RequestMapping("/kasuj")
    public String kasuj(@RequestParam("id") Integer id, Model model){
        osobaRepo.deleteById(id);

        model.addAttribute("osoba", osobaRepo.findAll());
        return "pokaz";
    }

    @RequestMapping("/wyszukaj")
    public String wyszukaj(@RequestParam("kryterium") String kryterium, Model model){
        model.addAttribute("osoba", osobaRepo.findAllBynazwisko(kryterium));
        return "pokaz";
    }


    @RequestMapping("/przekieruj")
    public String przekieruj(
            @RequestParam("id") Integer id, Model model
    )
            throws Exception {
        System.out.println(osobaRepo.findById(id));
        model.addAttribute("osoba", osobaRepo.findById(id));
        return "aktualizuj";
    }

    @RequestMapping("/aktualizuj")
    public String update(
            @RequestParam("id") Integer id,
            @RequestParam("imie") String imie,
            @RequestParam("nazwisko") String nazwisko,
            @RequestParam("telefon") String telefon,
            @RequestParam("email") String email,
            @RequestParam("opis") String opis, Model model)
            throws Exception {
        Osoba osoba = new Osoba(id, imie, nazwisko, telefon, email, opis, true);
        System.out.println(osoba);
        osobaRepo.save(osoba);
        model.addAttribute("osoba", osoba);
        return "widok";
    }
}


