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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WatchlistController {
    private List<WatchlistItem> watchlistItems= new ArrayList<WatchlistItem>();
    private static int index= 1;


    @GetMapping("/watchlistItemForm")
    public ModelAndView showWatchlistItemForm(@RequestParam(required=false) Integer id) {
        Map<String, Object> model = new HashMap<String, Object>();

        WatchlistItem watchlistItem = findWatchlistItemById(id);
        if (watchlistItem == null) {
            model.put("watchlistItem", new WatchlistItem());
        } else {
            model.put("watchlistItem", watchlistItem);
        }
        return new ModelAndView("watchlistItemForm" , model);
    }

    private WatchlistItem findWatchlistItemById(Integer id) {
        for (WatchlistItem watchlistItem : watchlistItems) {
            if (watchlistItem.getId()== id) {
                return watchlistItem;
            }
        }
        return null;
    }

    @PostMapping("/watchlistItemForm")
    public ModelAndView submitWatchlistForm( @Validated WatchlistItem watchlistItem , BindingResult bindingResult) {
        WatchlistItem existingItem = findWatchlistItemById(watchlistItem.getId());

        if (bindingResult.hasErrors()) {
            return new ModelAndView("watchlistItemForm");
        }
        if (existingItem == null) {
            watchlistItem.setId(index++);
            watchlistItems.add(watchlistItem);

            RedirectView redirect = new RedirectView();
            redirect.setUrl("/watchlistItem");
            return new ModelAndView(redirect);
        } else {
            existingItem.setComment(watchlistItem.getComment());
            existingItem.setPriority(watchlistItem.getPriority());
            existingItem.setRating(watchlistItem.getRating());
            existingItem.setTitle(watchlistItem.getTitle());

        }
        RedirectView redirect = new RedirectView();
        redirect.setUrl("/watchlistItem");

        return new ModelAndView(redirect);    }


    @GetMapping("/watchlistItem")
    public ModelAndView getWahtchlist(){
        String viewName="watchlist";
    Map<String, Object> model=new HashMap<String, Object>();
    model.put("watchlistItems",watchlistItems);
    model.put("numberOfMovies", watchlistItems.size());

        return new ModelAndView(viewName, model);
    }
}
