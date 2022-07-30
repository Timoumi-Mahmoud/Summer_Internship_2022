package com.openclassrom.watchlist;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WatchlistController {
    private List<WatchlistItem> watchlistItems= new ArrayList<WatchlistItem>();
    private static int index= 1;


    @GetMapping("/watchlistItemForm")
        public ModelAndView showWatchlistItemForm(@Valid WatchlistItem watchlistItem, BindingResult bindingResult) {


            String viewName = "watchlistItemForm";

            Map<String,Object> model = new HashMap<String,Object>();

            model.put("watchlistItem", new WatchlistItem());
        if (bindingResult.hasErrors()) {
            return new ModelAndView("watchlistItemForm");
        }
        return new ModelAndView(viewName,model);

    }
    @PostMapping("/watchlistItemForm")
    public ModelAndView submitWatchlistItemForm(WatchlistItem watchlistItem) {

        watchlistItem.setId(index++);
        watchlistItems.add(watchlistItem);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/watchlist");

        return new ModelAndView(redirectView);
    }



    @GetMapping("/watchlist")
    public ModelAndView getWatchlist() {

        String viewName = "watchlist";
        Map<String, Object> model = new HashMap<String, Object>();

        watchlistItems.clear();
        watchlistItems.add(new WatchlistItem( "Lion King","8.5","high","Hakuna matata!",1));
        watchlistItems.add(new WatchlistItem( "Frozen","7.5","medium","Let it go!",2));
        watchlistItems.add(new WatchlistItem( "Cars","7.1","low","Go go go!",3));
        watchlistItems.add(new WatchlistItem( "Wall-E","8.4","high","You are crying!",4));



        model.put("watchlistItems", watchlistItems);
        model.put("numberOfMovies", watchlistItems.size());

        return new ModelAndView(viewName , model);
    }
}
