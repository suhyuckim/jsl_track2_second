package command.notice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface CommandNotice {
	public void execute(HttpServletRequest request, Model model);
}