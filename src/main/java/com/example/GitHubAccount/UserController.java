package com.example.GitHubAccount;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

		List<Contributor> contributors = github.getMostContributers(repos,user.getContributers());


		CSV.createForksFile(repos, user.getName());
		CSV.createUsersFile(contributors, user.getName());

		model.addAttribute("user", user);
		return "result";
	}

	@GetMapping(path = "/download")
	public ResponseEntity<Resource> download(@RequestParam("file") String fileName) throws IOException {
		File file = new File("files/" + fileName);

		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName);
		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");

		Path path = Paths.get(file.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

		return ResponseEntity.ok()
				.headers(header)
				.contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(resource);
	}


}
