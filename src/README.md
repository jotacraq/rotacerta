# Sistema de Gerenciamento de Entregas (Delivery)

- Em Desenvolvimento...

Este projeto consiste em um sistema de gerenciamento de entregas, desenvolvido em Java, com o objetivo de controlar o cadastro de entregas e entregadores, bem como o acompanhamento do status das entregas.

O sistema permite:
- Cadastrar entregas a partir de um CEP
- Consultar e listar entregas registradas
- Cadastrar entregadores
- Associar entregadores às entregas
- Atualizar o status das entregas (pendente, em rota, entregue)

A aplicação utiliza arquitetura em camadas (model, repository e service), acesso a banco de dados via JDBC e armazenamento das informações em um banco de dados PostgreSQL, além de integração com API externa *(ViaCep)* para consulta de endereços via CEP.

Atualmente, o sistema encontra-se em desenvolvimento e sujeito a melhorias e novas funcionalidades.
