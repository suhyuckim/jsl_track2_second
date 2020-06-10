package command.news;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface CommandNews {
	public void execute(HttpServletRequest request, Model model);
}