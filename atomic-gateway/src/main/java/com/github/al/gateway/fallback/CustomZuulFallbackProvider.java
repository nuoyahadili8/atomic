package com.github.al.gateway.fallback;

import com.github.al.common.Constant;
import com.github.al.common.web.entity.RetEntity;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/13 14:05
 * @Modified By:
 */
@Component
public class CustomZuulFallbackProvider implements FallbackProvider {

    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return getClientHttpResponse(null);
    }

    @Override
    public ClientHttpResponse fallbackResponse(Throwable throwable) {
        return getClientHttpResponse(throwable);
    }

    ClientHttpResponse getClientHttpResponse(Throwable throwable) {

        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {}

            @Override
            public InputStream getBody() {
                RetEntity retEntity = RetEntity.error(
                        Constant.Code.SC_FEIGN_FALLBACK,
                        Constant.Message.SC_FEIGN_FALLBACK
                );
                if(throwable != null && throwable.getMessage() != null) {
                    //retMessage += "[" + throwable.getMessage() + "]";
                    retEntity.setBody(throwable.getMessage());
                }
                return new ByteArrayInputStream(retEntity.toString().getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return httpHeaders;
            }
        };
    }
}
