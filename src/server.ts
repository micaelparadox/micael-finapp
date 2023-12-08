import app from './app'; // Importa a instância do aplicativo Express configurada em app.ts
import http from 'http'; // Importa o módulo HTTP nativo do Node.js

// Define uma porta usando a variável de ambiente PORT, ou usa 3000 como padrão.
const port = process.env.PORT || 3000;

// Cria o servidor HTTP usando a instância do aplicativo Express.
const server = http.createServer(app);

// Coloca o servidor para escutar na porta especificada.
server.listen(port, () => {
  console.log(`Servidor rodando na porta ${port}`); // Imprime uma mensagem no console quando o servidor inicia.
});
