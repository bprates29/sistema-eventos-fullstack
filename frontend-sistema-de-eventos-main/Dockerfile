# Etapa 1: Construção
FROM node:18 AS builder

WORKDIR /app

# Copiar os arquivos necessários
COPY package.json package-lock.json ./
RUN npm install

COPY . .

# Executar o build de produção do Next.js
RUN npm run build

# Etapa 2: Produção
FROM node:18-alpine

WORKDIR /app

# Copiar os arquivos da etapa de build
COPY --from=builder /app ./

# Expor a porta padrão do Next.js
EXPOSE 3000

# Iniciar o servidor de produção do Next.js
CMD ["npm", "start"]
