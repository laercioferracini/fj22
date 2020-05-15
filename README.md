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
* Resumo diário das Negociações
  

### Classe imutável  
* Nenhum método pode modificar seu estado  
* A classe deve ser ```final```;  
* Os atributos devem ser ```final```, apenas para legibilidade de código, já que não há métodos que modificam o estado do objeto;  
* Caso a sua classe tenha composições com objetos mutáveis, eles devem ter acesso exclusivo pela sua classe.  

Exs: ```String```, classes ```wrapper```, ```BigInteger``` e ```BigDecimal```.  


### **Effective Java**
* Item 1: Considere utilizar Factory com métodos estáticos em vez de construtores
* Item 2: Considere usar um builder se o construtor tiver muitos parâmetros
* Item 10: Sempre reescreva o ```toString```
* Item 15: Minimize mutabilidade
* Item 47: Conheça e use as bibliotecas
* Item 39: Faça cópias defensivas quando necessário.

---
#### Quotes
*“Na maioria dos casos, as pessoas, inclusive os facínoras, são muito mais ingênuas e simples do que
 costumamos achar. Aliás, nós também.”*
 
– **Fiodór Dostoiévski**, em Irmãos Karamazov

*“Primeiro aprenda ciência da computação e toda a teoria. Depois desenvolva um estilo de programação. E aí
esqueça tudo e apenas ‘hackeie’.*

– **George Carrette**

*"Apenas duas coisas são infinitas: o universo e a estupidez humana. E eu não tenho certeza do primeiro."*

– **Albert Einstein**
 
*Simplicity is the ultimate sophistication.*
–  **Leonardo da Vinci**