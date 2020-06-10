package command.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface CommandUser {
	public void execute(HttpServletRequest request, Model model);
}