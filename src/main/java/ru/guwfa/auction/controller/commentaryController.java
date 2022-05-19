package ru.guwfa.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.guwfa.auction.controller.util.ControllerUtils;
import ru.guwfa.auction.domain.LotInAu;
import ru.guwfa.auction.domain.StatusLot;
import ru.guwfa.auction.domain.User;
import ru.guwfa.auction.domain.commentary;
import ru.guwfa.auction.service.commentaryService;
import ru.guwfa.auction.validator.commentaryValidator;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/LotInAu/{LotInAu}/commentary")
public class commentaryController {
    private final static int PAGE_SIZE = 5;
    @Autowired
    private commentaryService commentaryService;

    @Autowired
    private commentaryValidator commentaryValidator;

    @InitBinder("commentary")
    protected void initcommentaryBinder(WebDataBinder binder){
        binder.setValidator(commentaryValidator);
    }

    @GetMapping
    public String commentaryList(
            @PathVariable LotInAu LotInAu,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        if (LotInAu.getStatus().equals(StatusLot.ACTIVE.name())) { // если лот все еще активен
            model.addAttribute("LotInAu", LotInAu);
            model.addAttribute("url", "/LotInAu/" + LotInAu.getId() + "/commentary");
            model.addAttribute("page", commentaryService.getAllByLotInAuId(LotInAu.getId(), pageable));
            return "commentary/LotInAucommentaryList";
        } else {
            return "redirect:/LotInAu";
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('User')")
    public String addCommentary(
            @AuthenticationPrincipal User User,
            @PathVariable LotInAu LotInAu,
            Model model,
            @Valid commentary commentary,
            BindingResult bindingResult,
            @PageableDefault(size = PAGE_SIZE, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable

    ) {
        if (bindingResult.hasErrors()) {
            Map<String, List<String>> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);
            model.addAttribute("LotInAu", LotInAu);
            model.addAttribute("url", "/LotInAu/" + LotInAu.getId() + "/commentary");
            model.addAttribute("page", commentaryService.getAllByLotInAuId(LotInAu.getId(), pageable));
            return "commentary/LotInAucommentaryList";
        } else {
            if (LotInAu.getStatus().equals(StatusLot.ACTIVE.name())) { //если лот все еще активен

                commentaryService.addCommentary(commentary, LotInAu, User);
                return "redirect:/LotInAu/" + LotInAu.getId() + "/commentary";
            } else {
                return "redirect:/LotInAu";
            }
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{commentary}/delete")
    public String deletecommentary(
            @PathVariable LotInAu LotInAu,
            @PathVariable commentary commentary
    ) {
        if (LotInAu.getStatus().equals(StatusLot.ACTIVE.name())) { //если лот все еще активен

            commentaryService.removeCommentary(commentary);
            return "redirect:/LotInAu/" + LotInAu.getId() + "/commentary";
        } else {
            return "redirect:/LotInAu";
        }
    }
}
