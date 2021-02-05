package com.crewmaker.authentication;

        import org.springframework.security.access.prepost.PreAuthorize;
        import org.springframework.security.core.annotation.AuthenticationPrincipal;
        import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {

}
