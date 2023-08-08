# drone-feeder
O Drone-Feeder é uma API REST para um sistema de entregas utilizando Drones.<br>
A API permite gerenciar os drones e as entregas exibindo informações como posição geográfica onde será entregue o pacote(latitude e longitude), data e horário da entrega, seu status (criada, em rota, finalizada e cancelada) e o link do vídeo do momento em que o pacote foi recebido. <br>
Todas essas informações são armazenadas em um banco de dados MySQL.<br>

## :mag: Tecnologias utilizadas
- Construção da API - [Java](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html) e [Quarkus](https://quarkus.io/)<br>
- Banco de dados - [MySQL](https://www.mysql.com/) <br>
-  ORM - [Hibernate ORM with Panache](https://quarkus.io/guides/hibernate-orm-panache) <br>
- Testes - [JUnit 5](https://junit.org/junit5/) e [Testcontainers](https://java.testcontainers.org/) <br>
- Cobertura de testes - [JaCoCo Java Code Coverage Library](https://www.eclemma.org/jacoco/)
- Deploy - []() <br>

 ## 📋 Execute o projeto em sua máquina com docker em dev mode

Clone o repositório:

```
git clone git@github.com:tamireshc/Java.git
```

Crie um container MySQL com Docker iniciando o banco drone_feeder
```
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=drone_feeder -d  mysql:latest
```

Execute
```
./mvnw compile quarkus:dev
```
*As migrations pré definidas serão executadas automaticamente criando as tabelas do projeto. 

## 🕵 Diagrama UML da API <br>
![Drone Feeder drawio](https://github.com/tamireshc/drone-feeder/assets/65035109/2be56cf8-fc37-4176-ba03-1663e45d4d5b)

## 🧪 Executando os testes
Inicie o banco MySQL com Docker iniciando o banco drone_feeder

```
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=drone_feeder -d  mysql:latest
```
Rode os testes:
```
./mvnw test
```

## :dart: Cobertura dos testes
>Após rodar os testes procure os resultados na pasta target/jacoco-report <br>
>O arquivo index.html estará com o resumo do resultado obtido.<br>

Os testes deste projeto contemplaram uma cobertura de 95% da linhas.<br>

<img width="1161" alt="Captura de Tela 2023-08-04 às 18 23 38" src="https://github.com/tamireshc/drone-feeder/assets/65035109/5e8b1811-1c4e-4a65-b88c-0098cf706de2">

## :hammer: Deploy
>O deploy da aplicação foi executado utilizando o <br>
>Os links do deploy são:<br>
### Backend
`https://` 
### Banco de dados:
`https://`


## 🔎 Documentação da API

<details>
<summary><strong>:space_invader: Drones  </strong></summary><br/>

- Cadastro de drone

```
  POST /drone
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `model` | `string` |   modelo do drone. |
| `brand` | `string` |   marca do drone. |

  Corpo da resposta: <br/>
  
  
  ```json
  {
	"brand": "DJI",
	"id": 1,
	"model": "Mavic 3 Pro"
  }
  ```
:white_check_mark: STATUS 201 Created

 - Atualizar drone

```
  PATCH /drone/:id
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `model` | `string` |   modelo do drone. |
| `brand` | `string` |   marca do drone. |

  Corpo da resposta: <br/>
  
  ```json
  {
	"brand": "DJI",
	"id": 1,
	"model": "Mavic 3 Pro"
  }
  ```
:white_check_mark: STATUS 200 OK

- Obter todos os drones

```
  GET /drone
```

  Corpo da resposta: <br/>
  
  ```json
[
	{
		"brand": "DJIx",
		"id": 1,
		"model": "Mavic 3 Pro"
	}
]
  ```
:white_check_mark: STATUS 200 OK

- Obter um drone por seu id

```
  GET /drone/:id
```

  Corpo da resposta: <br/>
  
  ```json
	{
		"brand": "DJIx",
		"id": 1,
		"model": "Mavic 3 Pro"
	}
  ```
:white_check_mark: STATUS 200 OK

- Deletar um drone por seu id

```
  DELETE /drone/:id
```

  Corpo da resposta: <br/>
  
  ```json
Drone Deleted
  ```
:white_check_mark: STATUS 200 OK
</details>


<details>
<summary><strong>:vhs: Vídeos  </strong></summary><br/>

- Cadastro de vídeo

```
  POST /video
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `link` | `string` |   link do vídeo. |

  Corpo da resposta: <br/>
  
  
  ```json
{
	"id": 1,
	"link": "http://video.com"
}
  ```
:white_check_mark: STATUS 201 Created

- Obter todos os vídeos

```
  GET /video
```

  Corpo da resposta: <br/>
  
  ```json
[
	{
		"id": 1,
		"link": "http://video.com"
	},
	{
		"id": 2,
		"link": "https://video.com"
	}
]
  ```
:white_check_mark: STATUS 200 OK

- Obter um vídeo por seu id

```
  GET /video/:id
```

  Corpo da resposta: <br/>
  
  ```json
{
	"id": 1,
	"link": "http://video.com"
}
  ```
:white_check_mark: STATUS 200 OK
</details>

<details>
<summary><strong>:mailbox_with_mail: Deliveries  </strong></summary><br/>

- Cadastro de  entrega

```
  POST /delivery
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `status` | `string` |   status da entrega - "CREATED". |
| `droneId` | `string` |  Drone que será utilizado na entrega. |
| `schedule_delivery` | `string` |   data agendada da entrega no formato - "dd/MM/yyyy-HH:mm". |
| `position` | `objeto` |   posição da entrega composto de latitude e longitude |

  Corpo da resposta: <br/>
  
  
  ```json
{
	"status": "CREATED",
	"droneId": 1,
	"schedule_delivery": "25/05/2023-16:34",
	"position": {
		"latitude": "40",
		"longitude": "50"
	}
}
  ```
:white_check_mark: STATUS 201 Created

 - Atualizar uma entrega

```
  PATCH /delivery/:id
```
** Qualquer um dos parametros pode ser atualizado de acordo com a necessidade

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `status` | `string` |   status da entrega - "CREATED". |
| `droneId` | `string` |  Drone que será utilizado na entrega. |
| `schedule_delivery` | `string` |   data agendada da entrega no formato - "dd/MM/yyyy-HH:mm". |
| `position` | `objeto` |   posição da entrega composto de latitude e longitude |


  Corpo da resposta: <br/>
  
  ```json
{
	"delivery_date": "2023-08-08T17:40:14.203344",
	"drone": {
		"brand": "DJI",
		"id": 1,
		"model": "Mavic 3 Pro"
	},
	"id": 1,
	"position": {
		"id": 1,
		"latitude": "40",
		"longitude": "50"
	},
	"schedule_delivery": "2023-05-25T16:34:00",
	"status": "FINISHED",
	"video": {
		"id": 2,
		"link": "https://video.com"
	}
}
  ```
:white_check_mark: STATUS 200 OK


 - Atualizar o status de uma entrega

```
  PATCH /delivery/status/:id
```
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `status` | `string` |   status da entrega . |


  Corpo da resposta: <br/>
  
  ```json
{
	"status": "CANCELED"
}
  ```
:white_check_mark: STATUS 200 OK

- Obter todos as entregas

```
  GET /delivery
```

  Corpo da resposta: <br/>
  
  ```json
[
	{
		"drone": {
			"brand": "DJI",
			"id": 1,
			"model": "Mavic 3 Pro"
		},
		"id": 1,
		"position": {
			"id": 1,
			"latitude": "40",
			"longitude": "50"
		},
		"schedule_delivery": "2023-05-25T16:34:00",
		"status": "CREATED"
	}
]
  ```
:white_check_mark: STATUS 200 OK


- Deletar um entrega por seu id

```
  DELETE /delivery/:id
```

:white_check_mark: STATUS 200 OK
</details>


### Casos de Falha
- Ao atualizar, deletar e buscar por id para um drone inexistente deve  emitir a exceção `NotFoundException`
 ```json
{
	"message": "Drone not found"
}
  ```
- Ao atualizar, deletar e buscar por id para um video inexistente deve emitir a exceção `NotFoundException`
 ```json
{
	"message": "Video not found"
}
  ```
- Ao atualizar o status de uma entrega para um valor diferente de "CANCELED", "ONROUTE", "FINISHED" ou "CANCELED"
 ```json
{
	"status": "status not allowed"
}
  ```
- Ao atualizar, deletar e buscar por id para uma entrega inexistente deve emitir a exceção `NotFoundException`
 ```json
{
"message": "Delivery not found"
}
  ```


