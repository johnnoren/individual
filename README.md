# Devops, individuell uppgift

## Teststrategi

### Enhetstester
När: Körs vid build  
Hur ofta: Många gånger per dag  
Verktyg: JUnit  
Mål: 95% kodtäckning  

### Kodtäckningstest
När: Körs vid push
Hur ofta: Flera gånger per dag.
Verktyg: JaCoCo
Mål: 80% kodtäckning

### Integrationstester
När: Körs vid build  
Hur ofta: Många gånger per dag  
Verktyg: Spring Test/MockMvc  
Mål: Testar kommunikation med servicelagret och att controllermetoderna ger rätt respons

### Integrationstester / Smoke test
När: Körs vid push  
Hur ofta: Flera gånger per dag  
Verktyg: Rest Assured  
Mål: Testar att API:et går nå och ger rätt respons  

### Acceptanstester
När: Körs vid release eller vid större förändringar  
Hur ofta: Några gånger per månad  
Verktyg: Manuellt  
Mål: Testar att web-UI:t fungerar som det ska  
