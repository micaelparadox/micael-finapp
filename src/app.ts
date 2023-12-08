import express, { Request, Response, NextFunction, Express } from 'express';
import cors from 'cors';
import helmet from 'helmet';
import morgan from 'morgan';

// Cria uma instância do aplicativo express.
const app: Express = express();

// Aplica middlewares de segurança com o Helmet.
app.use(helmet());

// Habilita o CORS para todas as origens.
app.use(cors());

// Configura o Morgan para log de solicitações HTTP.
app.use(morgan('dev'));

// Analisa as solicitações de entrada com cargas JSON.
app.use(express.json());

// Analisa as solicitações de entrada com cargas URL-encoded (formulários).
app.use(express.urlencoded({ extended: true }));

// Middlewares personalizados
// app.use(logRequest); // Middleware para log de requisições
// app.use(yourMiddleware); // Substitua 'yourMiddleware' pelo nome do seu middleware

// Configura rotas
// app.use('/', indexRouter); // Rota principal
// app.use('/users', usersRouter); // Rota para usuários

// Aqui você pode adicionar novos middlewares e rotas conforme necessário
// app.use('/new-route', newRouteRouter);

// Middleware para captura de erros não tratados
app.use((err: any, req: Request, res: Response, next: NextFunction) => {
  // Middleware para manipulação de erros
  // errorHandler(err, req, res, next); // Substitua 'errorHandler' pelo seu middleware de erros personalizado
  // Caso não tenha um errorHandler personalizado, descomente as duas linhas abaixo
  console.error(err.stack);
  res.status(500).send('Algo deu errado!');
});

export default app;
