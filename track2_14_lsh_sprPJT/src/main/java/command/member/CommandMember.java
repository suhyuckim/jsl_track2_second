package command.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface CommandMember {
	public void execute(HttpServletRequest request, Model model);
}