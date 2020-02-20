# controleponto

Especificação das apis
	- formato de requisição e resposta  = JSON

Alunos
	- POST /api/v1/aluno - serviço para cadastro de alunos
		EX de requisição:
		{
			"nomeCompleto":"Camila Luchiari",
			"email":"teste@test.com",
			"cpf":35207774875,
			"dataCadastro":"21/01/2020"(dd/mm/yyyy)
		}
		
	- GET  /api/v1/aluno/{idAluno} - serviço de consulta de dados do aluno.
		"idAluno"=>(código numerico de um aluno existente)
		EX de resposta:
		{
	        "nomeCompleto": "João de deus",
	        "cpf": 12345678923,
	        "email": "teste@test.com",
	        "dataCadastro": "20/02/2020"
	    }	
	- PUT  /api/v1/aluno/{idAluno} - serviço para alterar os dados do aluno informado no parametro "idAluno"
		"idAluno"=>(código numerico de um aluno existente)
		EX de requisição(mesmo informando a data de cadastro a mesma não será alterada):
		{
			"nomeCompleto":"camila Luchiari Duarte",
			"email":"teste@test.com.br",
			"cpf":12345678923,
			"dataCadastro":"19/01/2020"
		}
	
	- GET  /api/v1/alunos - serviço que irá retornar todos os alunos.
	  EX de resposta:
		[
	    	{
		        "nomeCompleto": "João de deus",
		        "cpf": 12345678923,
		        "email": "teste@test.com",
		        "dataCadastro": "20/02/2020"
	    	}
	    ]

Batidas
	- POST /api/v1/batida - serviço par ainclusão de batida de ponto
		você deverá informar as seguintes informações:
		"idUsuario"=>(código de um aluno existente)
		"tipoBatida" =>(numerico)-(1- entrada e 2- saída)

	
	- GET /api/v1/batida/{idAluno} - serviço de consulta de todas as batidas de ponto do aluno e irá retornar o totalde horas trabalhadas.
		- idAluno = deve se informar o código do aluno cadastrado,segue exemplo:
		
		{
	    "nomeUsuario": "João de deus",
	    "batidas": [
	        {
	            "tipoBatida": "Entrada",
	            "dataHoraBatida": "20-02-2020 12:22:12"
	        },
	        {
	            "tipoBatida": "Saída",
	            "dataHoraBatida": "20-02-2020 12:22:18"
	        },
	        {
	            "tipoBatida": "Saída",
	            "dataHoraBatida": "20-02-2020 12:22:39"
	        }
	    ],
    "horasTrabalhadas": "00:00:05 horas"
	} 
		O campo "horasTrabalhadas" somente terá um valor diferente de zero se houver pelo menos uma sequencia de entrada seguida de saída, 
		caso haja somente entradas ou o contrário as horas ficarão zeradas.