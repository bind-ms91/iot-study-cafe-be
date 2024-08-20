package bind.iotstudycafe.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
public class SessionConfig {

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("JSESSIONID");
        serializer.setCookiePath("/");
        serializer.setDomainNamePattern("^.+?(\\w+\\.[a-z]+)$");
        serializer.setCookieMaxAge(90); // -1 은 브라우저 종료시 만료, 초 기준

//        serializer.setUseBase64Encoding(false);
        return serializer;
    }

}
