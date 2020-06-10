package command.faq;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface CommandFaq {
	public void execute(HttpServletRequest request, Model model);
}