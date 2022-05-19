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
import ru.guwfa.auction.domain.Role;
import ru.guwfa.auction.domain.User;
import ru.guwfa.auction.domain.dataTransferObject.UserDataTransferObject;
import ru.guwfa.auction.service.SubscriptionService;
import ru.guwfa.auction.service.UserService;
import ru.guwfa.auction.validator.UserValidator;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
public class UserController {
    private final int SUBSCRIPTION_PAGE_SIZE = 6;
    @Autowired
    private UserService UserService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserValidator UserValidator;

    @Autowired
    private ResourceBundle resourceBundle;

    @InitBinder("UserDataTransferObject")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(UserValidator);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/list")
    public String UserList(
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {

        model.addAttribute("url", "/User/list");
        model.addAttribute("page", UserService.getAll(pageable));
        return "User/UserList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{User}")
    public String UserEdit(
            Model model,
            @PathVariable User User
    ) {

        model.addAttribute("User", User);
        model.addAttribute("roles", Role.values());
        return "User/UserEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public String UserSave(
            @RequestParam() Map<String, String> form,
            @RequestParam(name = "balance") Long balance,
            @RequestParam(name = "User") User User
    ) {

        UserService.saveUser(User, form, balance);
        return "redirect:/User/list";
    }

    @GetMapping("/profile")
    public String getProfile(
            Model model,
            @AuthenticationPrincipal User User
    ) {

        Optional<User> optionalUser = UserService.getById(User.getId());
        model.addAttribute("User", optionalUser.orElse(User));
        return "User/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(
            @AuthenticationPrincipal User User,
            Model model,
            @Valid UserDataTransferObject UserFromForm,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, List<String>> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);
        } else {

            model.addAttribute("message", resourceBundle.getString("message.followLinkInEmail"));
            UserService.updateProfile(User, UserFromForm);
        }

        model.addAttribute("User", User);
        return "User/profile";
    }

    @PreAuthorize("hasAuthority('User')")
    @GetMapping("/profile/subscriptions")
    public String getSubscriptions(
            @PageableDefault(size = SUBSCRIPTION_PAGE_SIZE, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            @AuthenticationPrincipal User User,
            Model model
    ) {

        model.addAttribute("page", subscriptionService.getAllLotInAusFor(User.getId(), pageable));
        model.addAttribute("url", "/User/profile/subscriptions");
        return "User/UserSubscriptions";
    }

    @PreAuthorize("hasAuthority('User')")
    @GetMapping("/profile/subscriptions/{LotInAu}/unsubscribe")
    public String unsubscribe(
            @AuthenticationPrincipal User User,
            @PathVariable LotInAu LotInAu
    ) {

        subscriptionService.removeSubscription(LotInAu.getId(), User.getId());
        return "redirect:/User/profile/subscriptions";
    }
}
