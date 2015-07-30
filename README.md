# MenorRota
Pesquisar Menor Rota entre dois pontos

####Servidor de Aplicação
Foi utilizado o servidor Wildfly, por oferecer a especificação jee7. (http://download.jboss.org/wildfly/9.0.1.Final/wildfly-9.0.1.Final.zip)

####Webservices
Foi utilizado o jax-rs com json, o jax-rs sendo parte da especificação jee e o json ser mais otimizado (trafega menos dados) do que o XML.

####Persistencia de dados
Para persistencia de dados foi utilizado o JPA que é parte da especificação jee. A implementação do JPA utilizada é o Hibernate que é o mais utilizado e o que vem junto com o servidor de aplicação Wildfly.
Para persistencia foi utilizado o h2 em memória a fins de facilitar o teste, mas para utilizar um outro banco de dados é só adicionar um datasource no Wildfly e configurar o persistence.xml com o jta-data-source com o jndi configurado e configurar o hibernate.dialect de acordo com o banco escolhido.

####Algorítmo de menor rota
Foi utilizado o Algoritmo de Dijkstra:
  "Resolve o problema com um vértice-fonte em grafos cujas arestas tenham peso maior ou igual a zero. Sem reduzir o desempenho, este algoritmo é capaz de determinar o caminho mínimo, partindo de um vértice de início v para todos os outros vértices do grafo"
  
Para gerar o grafo e encontrar o menor caminho foi utilizada a biblioteca Hipster4j (http://www.hipster4j.org/)
que faz a busca heurística em um grafo. Esta biblioteca foi utilizado por ser open source e poder ter uma otimização ao longo do tempo, bastando apenas uma atualização da biblioteca.

#####Testes
Para realização dos teste foi utilizado o JUnit, desta forma sabendo se os passos foram executados corretamente.
Para configurar a url do servidor de aplicação basta configurar o "server.url" no arquivo "rest-test.properties".
