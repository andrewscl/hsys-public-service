package cl.hsys.publicservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

    @GetMapping("/home")
    public String showHomePage (Model model, HttpServletRequest request) {
        return render("/views/index-view", model, request);
    }

    // ==== helper: decide layout + view (deep link) vs solo view (SPA) ====
    private String render (String view, Model model, HttpServletRequest request) {

        //si el SPA (Single page application) hace fetch con este header, devuelve solo el view.
        //El navigation-handler pide solo el fragmento (view) mediante un fetch
        if("1".equals(request.getHeader("X-Fragment-Request"))
            || "1".equals(request.getAttribute("fragment"))){
            return view;
        }

        // Deep Link / F5: devuelve el layout e inserta el fragmento
        model.addAttribute("index", view);
        return "/layouts/public-layout";
    }

}
