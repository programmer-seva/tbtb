/*
 * package kr.co.beauty.security;
 * 
 * import java.io.IOException;
 * 
 * import javax.naming.AuthenticationException;
 * 
 * import org.springframework.context.annotation.Configuration; import
 * org.springframework.security.authentication.AuthenticationServiceException;
 * import org.springframework.security.authentication.BadCredentialsException;
 * import
 * org.springframework.security.authentication.CredentialsExpiredException;
 * import org.springframework.security.authentication.DisabledException; import
 * org.springframework.security.authentication.LockedException; import
 * org.springframework.security.web.authentication.
 * SimpleUrlAuthenticationFailureHandler;
 * 
 * import jakarta.servlet.RequestDispatcher; import
 * jakarta.servlet.ServletException; import
 * jakarta.servlet.http.HttpServletRequest; import
 * jakarta.servlet.http.HttpServletResponse;
 * 
 * @Configuration public class AuthFailureHandler extends
 * SimpleUrlAuthenticationFailureHandler {
 * 
 * @Override public void onAuthenticationFailure(HttpServletRequest request,
 * HttpServletResponse response, AuthenticationException exception) throws
 * IOException, ServletException {
 * 
 * String errorMessage;
 * 
 * if (exception instanceof AuthenticationServiceException) { errorMessage =
 * "존재하지 않는 사용자입니다."; } else if(exception instanceof BadCredentialsException) {
 * errorMessage = "아이디 또는 비밀번호가 틀립니다."; }
 * 
 * errorMessage = URLEncoder.encode(errorMessage, "UTF-8"); 한글 인코딩 깨진 문제 방지
 * setDefaultFailureUrl("/auth/login?error=true&exception=" + errorMessage);
 * super.onAuthenticationFailure(request, response, exception); }
 * 
 * }
 */