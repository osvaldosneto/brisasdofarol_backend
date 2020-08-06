# Gerenciador de Hospedagem

Neste sistema temos o backEnd desenvolvido em java para nos auxiliar na gestão de algumas pousadas que disponibilizamos para locação.
Para desenvolver este sistema foi utilizado o conceito de arquitetura em camadas, o que nos auxilia na distribuição de tarefas. 
Segue abaixo diagrama das camadas.

<div>
  <img src="diagrama.jpg"/>
  <h4>Tela LogIn</h4>
</div>

Todo a comunicação com o nosso frontEnd será entregue via este sistema. Nossa camada de entrada é chamada de Controler, onde nela recebemos parâmetros passados por linha de comando ou como objeto para então poder tomar as decisões necessárias, nesta camada temos as atividades de Rest sendo aplicadas.
Na camada de serviços temos como principal tarefa oferecer uma boa estrutura as demais, ou seja, esta camada acaba sendo a intermediária entre os recursos e a disponibilidade dos mesmos para outras camadas. Temos também a camada de repositório, onde nela podemos gerar toda a comunicação com nosso banco de dados MYSQL e na camada de domínio temos os objetos que iremos utilizar na aplicação.
