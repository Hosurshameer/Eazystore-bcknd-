package com.eazybytes.eazystore.scopes;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

@Component
@ApplicationScope
@Getter

@Slf4j
public class ApplicationScopedBean {

    private int visitorCount=0;
 public int incrementVisitorCount(){
   visitorCount++;

     return visitorCount;
 }

 public ApplicationScopedBean() {
  log.info("ApplicationScopedBean created");
 }


}
