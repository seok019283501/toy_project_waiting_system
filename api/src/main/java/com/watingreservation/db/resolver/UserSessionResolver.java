package com.watingreservation.db.resolver;

import com.watingreservation.db.common.annotation.UserSession;
import com.watingreservation.db.domain.user.model.User;
import com.watingreservation.db.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class UserSessionResolver implements HandlerMethodArgumentResolver {
    private final UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //지원하는 파라미터 체크, 어노테이션 체크
        //1. 어노테이션이 있는지 체크
        var annotation = parameter.hasParameterAnnotation(UserSession.class);
        //2. 파라미터의 타입 체크
        var parameterType = parameter.getParameterType().equals(User.class);
        return (annotation && parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //support parameter에서 true변환시 여기 실행

        //requset context holder에서 찾아오기
        var requestContext = RequestContextHolder.getRequestAttributes();
        var userId = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);
        var userEntity = userService.getUserWithThrow(userId.toString());

        //사용자 정보 셋팅
        return User.builder()
                .id(userEntity.getId())
                .password(userEntity.getPassword())
                .name(userEntity.getName())
                .access(userEntity.getAccess())
                .phone_number(userEntity.phone_number)
                .status(userEntity.getStatus())
                .registered_at(userEntity.registered_at)
                .unregistered_at(userEntity.unregistered_at)
                .build();

    }
}
