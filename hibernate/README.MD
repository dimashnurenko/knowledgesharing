**Audit**

Key moments: 
1. The table should contain fields: created_by, created_date, updated_by, updated_date
2. Use spring boot, data + hibernate
3. Add annotation `@EnableJpaAuditing` on spring boot starter class
4. Add following maven dependency 
```xml
     <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-aspects</artifactId>
     </dependency>
```
5. Create entity which will contain auditable fields. Example class Auditable.
6. Extend Auditable
7. Add `@EntityListeners(AuditingEntityListener.class)` annotations over entity which need to be auditable
8. Add implementation of AuditorAware to define **created_by, updated_by** fields. Example **audit.SpringSecurityAuditorAware**
9. Run and enjoy.

P.S. tests audit.AuditTest.
