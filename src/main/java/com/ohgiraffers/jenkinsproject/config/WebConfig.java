package com.ohgiraffers.jenkinsproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    // back에서 cors를 처리하기 위한 설정용 클래스 ( servlet 기반 )
    // msa 기반은 설정이 좀 다름 ( gateway도 cors 처리를 해줘야함 - gateway는 servlet을 안 쓰기 때문이라함 )

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")                        // 모든 핸들러 메소드에 대해 허용

                    /* 단순 프론트 & 백엔드 CORS 처리 */
//                .allowedOrigins("http://localhost:5173")            // 경로지정

                    /* Ingress 적용 이전 프론트 워커노트 포트에 대한 CORS 처리 */
//                .allowedOrigins("http://localhost:30000")            //  프론트앤드 Worker Node에 대한 포트번호 설정

//               /* Ingress 적용 이후 CORS 불필요로 인한 경로 제거 */
                .allowedOrigins()
                .allowedMethods("GET", "POST", "PUT", "DELETE");    // 모든 요청에 대해 허용 ( 허용할 요청 직접 다 써줘야함 )
    }
}
