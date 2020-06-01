![build](https://github.com/laercioferracini/fj22/workflows/Java%20CI%20with%20Gradle/badge.svg?branch=master)

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
  
---
### Classe imutável  
* Nenhum método pode modificar seu estado  
* A classe deve ser ```final```;  
* Os atributos devem ser ```final```, apenas para legibilidade de código, já que não há métodos que modificam o estado do objeto;  
* Caso a sua classe tenha composições com objetos mutáveis, eles devem ter acesso exclusivo pela sua classe.  

Exs: ```String```, classes ```wrapper```, ```BigInteger``` e ```BigDecimal```.  

---
### Desing patterns  
* Considere utilizar **Factory** com métodos estáticos em vez de construtores
* padrão de projeto **fluent interface** pode tornar o código mais conciso, sem perder a legibilidade  
* Construir um objeto complicado, ou confuso, costumamos usar o padrão **Builder** para resolver isso  
* A classe XStream (Parser XML) atua como **façade** de acesso para os principais recursos da biblioteca  

---
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
esqueça tudo e apenas ‘hackeie’”.*  
– **George Carrette**

*“Apenas duas coisas são infinitas: o universo e a estupidez humana. E eu não tenho certeza do primeiro.”*  
– **Albert Einstein**
 
*“Simplicity is the ultimate sophistication.”*  
–  **Leonardo da Vinci**

*“Se eu enxerguei longe, foi por ter subido nos ombros de gigantes.”*  
– **Isaac Newton**

*“Experiência sem teoria é cegueira, mas teoria sem experiência é mero jogo intelectual.”*  
– **Immanuel Kant**

*“Nenhum homem é uma ilha isolada; cada homem é uma partícula do continente, uma parte da terra”*  
 – **John Donne**

*“Eu não temo computadores, eu temo é a falta deles”*  
 – **Isaac Asimov**
 
*“Nunca confie em um computador que você não pode jogar pela janela.”*  
– **Steve Wozniak**

*“A única pessoa educada é aquela que aprendeu a aprender e a mudar.”*  
– **Carl Rogers**