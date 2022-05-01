## Primeiros Passos

### Definição
Esse projeto foi criado como uma API de testes.

O sistema inicia, carrega os dados previamente definidos e disponibiliza uma API REST.

### Como iniciar Serviço
Para iniciar basta segiuir os seguintes passos:
* Na pasta raiz do projeto encontre e execute o arquivo "iniciarServico.bat".
* Caso não abra sozinho, após iniciar o serviço basta entrar em um navegador de sua preferencia e acessar a seguinte URL:

	http://localhost:8080/awards/statistics
	
### Como iniciar os Testes de Integração
Para iniciar basta segiuir os seguintes passos:
* Na pasta raiz do projeto encontre e execute o arquivo "iniciarTesteIntegracao.bat".
Caso não abra sozinho, após executar os testes abra o relatório gerado em:

	build\reports\tests\test\index.html
	
### Alteração dos dados
Para alterar os dados basta alterar/substituir o seguinte arquivo (mantendo o mesmo nome e estrutura de colunas):

	\build\libs\movielist.csv
