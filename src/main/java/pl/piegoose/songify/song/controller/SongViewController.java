package pl.piegoose.songify.song;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

///  MVC
@Controller
public class SongViewController {

    private Map<Integer, String> database = new HashMap<>();

    @GetMapping("/")
    public String home() {
        return "home.html";
    }

    @GetMapping("/view/songs")
    public String songs(Model model) {
        database.put(1, "shawnmenseds song1");
        database.put(2, "arianagrande");
        database.put(3, "Loses yourself");
        database.put(4, "Without me");
        database.put(5, "Candy shop");
        database.put(6, "Many men");
        database.put(7, "NWA");
        database.put(8, "Fuck the Police");
        database.put(9, "California love");
        database.put(10, "Still");

        model.addAttribute("songMap", database);
        return "songs.html";
    }


}
