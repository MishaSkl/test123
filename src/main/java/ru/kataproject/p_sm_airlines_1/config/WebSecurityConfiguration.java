package ru.kataproject.p_sm_airlines_1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import ru.kataproject.p_sm_airlines_1.security.jwt.JwtConfigurer;
import ru.kataproject.p_sm_airlines_1.security.jwt.JwtTokenProvider;

/**
 *Класс для настройки безопасности
 *
 * @author Toboe512
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorizeRequests -> authorizeRequests
//                        .antMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/login").permitAll());
////                        .anyRequest().authenticated())
////                .oauth2ResourceServer(resourceServerConfigurer -> resourceServerConfigurer
////                        .jwt(jwtConfigurer -> jwtConfigurer
////                                .jwtAuthenticationConverter(jwtAuthenticationConverter()))
////                );
//        return http.build();
//    }
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring()
//                .antMatchers(AuthenticateController.BASE_NAME);
//    }

//    @Bean
//    public Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter() {
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter());
//        return jwtAuthenticationConverter;
//    }

    /**
     * Бин в котором оргинизована конвертация ролей, он принимается JWT остаёт от туда роли добавляет к ним
     * префикс ROLE_ который принят в Spring Security и собирает GrantedAuthority которй уже используется в
     * дальнейшей авторизации
     *
     * @return Converter<Jwt, Collection<GrantedAuthority>>
     */
//    @Bean
//    public Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter() {
//        JwtGrantedAuthoritiesConverter delegate = new JwtGrantedAuthoritiesConverter();
//
//        return new Converter<>() {
//            @Override
//            public Collection<GrantedAuthority> convert(@NonNull Jwt jwt) {
//                Collection<GrantedAuthority> grantedAuthorities = delegate.convert(jwt);
//
//                if (jwt.getClaim("realm_access") == null) {
//                    return grantedAuthorities;
//                }
//                JSONObject realmAccess = jwt.getClaim("realm_access");
//                if (realmAccess.get("roles") == null) {
//                    return grantedAuthorities;
//                }
//                JSONArray roles = (JSONArray) realmAccess.get("roles");
//
//                final List<SimpleGrantedAuthority> keycloakAuthorities =
//                        roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList());
//                grantedAuthorities.addAll(keycloakAuthorities);
//
//                return grantedAuthorities;
//            }
//        };
//    }

    private final JwtTokenProvider jwtTokenProvider;

    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";

    @Autowired
    public WebSecurityConfiguration(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider))
        ;
    }
}
