package cn.com.taiji.taiji.config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * @author SongChong created by9:29 2018/11/29 0029
 * 自定义国际化类
 */
public class MyLocaleResolver implements LocaleResolver {

    private static final String LANGUAGE_SESSION = "language_session";

    @Override
    public Locale resolveLocale(HttpServletRequest req) {
        String language = req.getParameter("lang");
        Locale locale = Locale.getDefault();
        if (!StringUtils.isEmpty(language)) {
            String[] languages = language.split("_");
            locale = new Locale(languages[0], languages[1]);

            //将国际化语言保存到session
            HttpSession session = req.getSession();
            session.setAttribute(LANGUAGE_SESSION, locale);
        } else {
            //如果没有带国际化参数，则判断session有没有保存，有保存，则使用保存的，也就是之前设置的，避免之后的请求不带国际化参数造成语言显示不对
            HttpSession session = req.getSession();
            Locale localeInSession = (Locale) session.getAttribute(LANGUAGE_SESSION);
            if (localeInSession != null) {
                locale = localeInSession;
            }
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest req, HttpServletResponse res, Locale locale) {

    }

}  