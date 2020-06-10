package command.manager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface CommandManager {
	public void execute(HttpServletRequest request, Model model);
}