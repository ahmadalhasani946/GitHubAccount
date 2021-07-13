package com.example.GitHubAccount;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class UserController {

	@GetMapping("/")
	public String userForm(Model model) {
		model.addAttribute("user", new User());
		return "user";
	}

	@PostMapping("/")
	public String userSearch(@Valid User user, Model model, BindingResult result) {
		if (result.hasErrors()) {
			return "user";
		}

		Github github = new Github(user.getName());

		Repository[] repos = github.getMostForked(user.getForks());

//		for (Repository repo : repos) {
//			System.out.println(repo.getForks_count());
//			System.out.println(repo.getName());
//			System.out.println("");
//		}

		List<Contributor> contributors = github.getMostContributers(repos,user.getContributers());
//		System.out.println("contributors");
//		for (Contributor contributor : contributors) {
//			System.out.println();
//			System.out.println(contributor.getRepositoryName());
//			System.out.println(contributor.getUserName());
//			System.out.println(contributor.getContributionQuantity());
//			System.out.println(contributor.getFollowersQuantity());
//			System.out.println();
//		}

		CSV.createForksFile(repos, user.getName());
		CSV.createUsersFile(contributors, user.getName());

		model.addAttribute("user", user);
		return "result";
	}

}
