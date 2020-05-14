# FJ22 estudos 

**Laboratório Java com testes, JSF e Design patterns**

Repositório para aplicar os estudos da apostila fj22  



# Projeto Argentum  

##### Tecnologias
* Java 11
* Gradle
* Junit 5

##### Modelando o sistema
* Trabalhando com dinheiro usando o BigDecimal  
* Imutabilidade de objetos  

### Classe imutável  
* Nenhum método pode modificar seu estado  
* A classe deve ser ```final```;  
* Os atributos devem ser ```final```, apenas para legibilidade de código, já que não há métodos que modificam o estado do objeto;  
* Caso a sua classe tenha composições com objetos mutáveis, eles devem ter acesso exclusivo pela sua classe.  

Exs: ```String```, classes ```wrapper```, ```BigInteger``` e ```BigDecimal```.  


### **Effective Java**
* Item 1: Considere utilizar Factory com métodos estáticos em vez de construtores

